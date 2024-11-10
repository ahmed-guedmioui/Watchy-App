package com.ahmed_apps.watchy_course.favorites.data.repository

import android.content.SharedPreferences
import com.ahmed_apps.watchy_course.favorites.data.remote.api.BackendMediaApi
import com.ahmed_apps.watchy_course.favorites.data.remote.dto.request.MediaByIdRequest
import com.ahmed_apps.watchy_course.favorites.data.remote.dto.request.MediaRequest
import com.ahmed_apps.watchy_course.favorites.data.remote.dto.request.UpsertMediaRequest
import com.ahmed_apps.watchy_course.favorites.data.remote.dto.respond.BackendMediaDto
import com.ahmed_apps.watchy_course.favorites.domain.repository.BackendFavoritesRepository
import com.google.gson.JsonIOException
import retrofit2.HttpException
import javax.inject.Inject

/**
 * @author Ahmed Guedmioui
 */
class BackendFavoritesRepositoryImpl @Inject constructor(
    private val backendMediaApi: BackendMediaApi,
    private val prefs: SharedPreferences
) : BackendFavoritesRepository {
    override suspend fun getLikedMediaList(): List<BackendMediaDto>? {
        val email = prefs.getString("email", null)
            ?: return null

        return try {
            backendMediaApi.getLikedMediaList(email)
        } catch (e: HttpException) {
            null
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getBookmarkedMediaList(): List<BackendMediaDto>? {
        val email = prefs.getString("email", null)
            ?: return null

        return try {
            backendMediaApi.getBookmarkedMediaList(email)
        } catch (e: HttpException) {
            null
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getMediaById(
        mediaId: Int
    ): BackendMediaDto? {
        val email = prefs.getString("email", null)
            ?: return null

        return try {
            backendMediaApi.getMediaById(
                mediaId.toString(), email
            )
        } catch (e: HttpException) {
            null
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun upsertMediaToUser(
        mediaRequest: MediaRequest
    ): Boolean {
        val email = prefs.getString("email", null)
            ?: return false

        return try {
            backendMediaApi.upsertMediaToUser(
                UpsertMediaRequest(
                    mediaRequest, email
                )
            )
            true
        } catch (e: HttpException) {
            false
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun deleteMediaFromUser(
        mediaId: Int
    ): Boolean {
        val email = prefs.getString("email", null)
            ?: return false

        return try {
            backendMediaApi.deleteMediaFromUser(
                MediaByIdRequest(
                    mediaId, email
                )
            )
            true
        } catch (e: JsonIOException) {
            true
        } catch (e: HttpException) {
            false
        } catch (e: Exception) {
            false
        }
    }
}


















