package com.ahmed_apps.watchy_course.favorites.data.remote.api

import com.ahmed_apps.watchy_course.favorites.data.remote.dto.request.MediaByIdRequest
import com.ahmed_apps.watchy_course.favorites.data.remote.dto.request.UpsertMediaRequest
import com.ahmed_apps.watchy_course.favorites.data.remote.dto.respond.BackendMediaDto
import com.ahmed_apps.watchy_course.util.BackendConstants
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * @author Ahmed Guedmioui
 */
interface BackendMediaApi {

    @GET(BackendConstants.GET_LIKED_MEDIA_LIST)
    suspend fun getLikedMediaList(
        @Path("email") email: String
    ): List<BackendMediaDto>

    @GET(BackendConstants.GET_BOOKMARKED_MEDIA_LIST)
    suspend fun getBookmarkedMediaList(
        @Path("email") email: String
    ): List<BackendMediaDto>

    @GET(BackendConstants.GET_MEDIA_BY_ID)
    suspend fun getMediaById(
        @Path("mediaId") mediaId: String,
        @Path("email") email: String,
    ): BackendMediaDto

    @POST(BackendConstants.UPSERT_MEDIA_TO_USER)
    suspend fun upsertMediaToUser(
        @Body upsertMediaRequest: UpsertMediaRequest
    ): String

    @POST(BackendConstants.DELETE_MEDIA_FROM_USER)
    suspend fun deleteMediaFromUser(
        @Body mediaByIdRequest: MediaByIdRequest
    ): String

}

















