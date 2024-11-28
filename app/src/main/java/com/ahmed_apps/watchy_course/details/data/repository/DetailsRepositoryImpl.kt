package com.ahmed_apps.watchy_course.details.data.repository

import android.app.Application
import com.ahmed_apps.watchy_course.R
import com.ahmed_apps.watchy_course.details.data.remote.api.DetailsApi
import com.ahmed_apps.watchy_course.details.data.remote.dto.DetailsDto
import com.ahmed_apps.watchy_course.details.domain.repository.DetailsRepository
import com.ahmed_apps.watchy_course.main.domain.model.Media
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
class DetailsRepositoryImpl @Inject constructor(
    private val detailsApi: DetailsApi,
    private val mainRepository: MainRepository,
    private val application: Application
) : DetailsRepository {

    override suspend fun getDetails(
        id: Int,
        isRefreshing: Boolean
    ): Flow<Resource<Media>> {
        return flow {
            emit(Resource.Loading(true))

            val media = mainRepository.getMediaById(id)

            val doDetailsExist =
                media.runTime != 0 || media.tagLine.isNotEmpty()

            if (doDetailsExist && !isRefreshing) {
                emit(Resource.Success(media))
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteDetailsDto = getRemoteDetails(
                type = media.mediaType,
                id = id
            )

            remoteDetailsDto?.let { detailsDto ->

                val mediaWithDetails = media.copy(
                    runTime = detailsDto.runtime ?: 0,
                    tagLine = detailsDto.tagline ?: ""
                )

                mainRepository.upsertMediaItem(mediaWithDetails)

                emit(Resource.Success(mainRepository.getMediaById(id)))
                emit(Resource.Loading(false))
                return@flow
            }

            emit(
                Resource.Error(
                    application.getString(R.string.couldn_t_load_data)
                )
            )
            emit(Resource.Loading(false))
        }
    }

    private suspend fun getRemoteDetails(
        type: String,
        id: Int
    ): DetailsDto? {

        val remoteDetails = try {
            detailsApi.getDetails(
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

        return remoteDetails
    }

}






















