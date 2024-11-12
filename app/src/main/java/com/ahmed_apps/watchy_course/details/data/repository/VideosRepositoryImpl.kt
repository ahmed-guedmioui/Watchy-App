package com.ahmed_apps.watchy_course.details.data.repository

import android.app.Application
import com.ahmed_apps.watchy_course.R
import com.ahmed_apps.watchy_course.details.data.remote.api.DetailsApi
import com.ahmed_apps.watchy_course.details.domain.repository.VideosRepository
import com.ahmed_apps.watchy_course.main.domain.repository.MainRepository
import com.ahmed_apps.watchy_course.util.APIConstants.GET_TAG
import com.ahmed_apps.watchy_course.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

/**
 * @author Ahmed Guedmioui
 */
class VideosRepositoryImpl @Inject constructor(
    private val detailsApi: DetailsApi,
    private val mainRepository: MainRepository,
    private val application: Application
) : VideosRepository {
    override suspend fun getVideos(
        id: Int,
        isRefreshing: Boolean
    ): Flow<Resource<List<String>>> {
        return flow {

            emit(Resource.Loading(true))

            val media = mainRepository.getMediaById(id)

            val doVideosExist = media.videosIds.isNotEmpty()

            if (doVideosExist && !isRefreshing) {
                emit(Resource.Success(media.videosIds))
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteVideos = getVideoFromRemote(
                type = media.mediaType,
                id = id
            )

            remoteVideos?.let { videosIds ->
                if (videosIds.isNotEmpty()) {
                    mainRepository.upsertMediaItem(
                        media.copy(videosIds = videosIds)
                    )
                    emit(
                        Resource.Success(
                            mainRepository.getMediaById(id).videosIds
                        )
                    )
                } else {
                    emit(
                        Resource.Error(
                            application.getString(R.string.couldn_t_load_data)
                        )
                    )
                }

                emit(Resource.Loading(false))
                return@flow

            }

            emit(
                Resource.Error(
                    application.getString(R.string.couldn_t_load_data)
                )
            )
            emit(Resource.Loading(false))
            return@flow
        }
    }

    private suspend fun getVideoFromRemote(
        type: String,
        id: Int
    ): List<String>? {
        val remoteVideos = try {
            detailsApi.getVideos(
                type, id
            )
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } catch (e: HttpException) {
            e.printStackTrace()
            null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

        val videos = remoteVideos?.results?.filter {
            it.site == "YouTube" && it.key?.isNotEmpty() == true
        }

        return videos?.map { it.key!! }
    }
}






















