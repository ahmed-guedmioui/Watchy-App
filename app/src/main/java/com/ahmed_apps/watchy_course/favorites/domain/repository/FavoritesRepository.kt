package com.ahmed_apps.watchy_course.favorites.domain.repository

import com.ahmed_apps.watchy_course.main.domain.model.Media
import kotlinx.coroutines.flow.Flow

/**
 * @author Ahmed Guedmioui
 */
interface FavoritesRepository {

    suspend fun favoritesDbUpdateEventFlow(): Flow<Boolean>

    suspend fun upsetFavoritesMediaItem(
        media: Media
    )

    suspend fun deleteFavoritesMediaItem(
        media: Media
    )

    suspend fun getMediaItemById(
        mediaId: Int
    ): Media?

    suspend fun getLikedMediaList(): List<Media>
    suspend fun getBookmarkedMediaList(): List<Media>

    suspend fun clearMainDb()
}





















