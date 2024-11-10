package com.ahmed_apps.watchy_course.favorites.data.repository

import com.ahmed_apps.watchy_course.favorites.data.loca.FavoriteMediaDatabase
import com.ahmed_apps.watchy_course.favorites.data.loca.FavoriteMediaEntity
import com.ahmed_apps.watchy_course.favorites.data.mapper.toFavoriteMediaEntity
import com.ahmed_apps.watchy_course.favorites.data.mapper.toMedia
import com.ahmed_apps.watchy_course.favorites.data.mapper.toMediaEntity
import com.ahmed_apps.watchy_course.favorites.data.mapper.toMediaRequest
import com.ahmed_apps.watchy_course.favorites.domain.repository.BackendFavoritesRepository
import com.ahmed_apps.watchy_course.favorites.domain.repository.FavoritesRepository
import com.ahmed_apps.watchy_course.main.data.local.MediaDatabase
import com.ahmed_apps.watchy_course.main.domain.model.Media
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

/**
 * @author Ahmed Guedmioui
 */
class FavoritesRepositoryImpl @Inject constructor(
    private val backendFavoritesRepository: BackendFavoritesRepository,
    favoriteMediaDatabase: FavoriteMediaDatabase,
    mediaDatabase: MediaDatabase
) : FavoritesRepository {

    private val mediaDao = mediaDatabase.mediaDao
    private val favoriteMediaDao = favoriteMediaDatabase.favoriteMediaDao

    private val _favoritesDbUpdateEventChannel = Channel<Boolean>()
    override suspend fun favoritesDbUpdateEventFlow(): Flow<Boolean> =
        _favoritesDbUpdateEventChannel.receiveAsFlow()

    override suspend fun upsetFavoritesMediaItem(media: Media) {
        favoriteMediaDao.upsertFavoriteMediaItem(
            media.toFavoriteMediaEntity()
        )

        syncFavoritesMedia()

        _favoritesDbUpdateEventChannel.send(true)
    }

    override suspend fun deleteFavoritesMediaItem(media: Media) {
        val favoriteMediaEntity =
            media.toFavoriteMediaEntity().copy(isDeletedLocally = true)

        favoriteMediaDao.upsertFavoriteMediaItem(
            favoriteMediaEntity
        )

        syncFavoritesMedia()

        _favoritesDbUpdateEventChannel.send(true)
    }

    override suspend fun getMediaItemById(
        mediaId: Int
    ): Media? {
        favoriteMediaDao.getFavoriteMediaItemById(
            mediaId
        )?.let {
            return it.toMedia()
        }

        backendFavoritesRepository.getMediaById(
            mediaId
        )?.let { backendMediaDto ->
            favoriteMediaDao.upsertFavoriteMediaItem(
                backendMediaDto.toFavoriteMediaEntity()
            )
            mediaDao.upsertMediaItem(
                backendMediaDto.toMediaEntity()
            )

            return favoriteMediaDao.getFavoriteMediaItemById(
                mediaId
            )?.toMedia()
        }

        return null

    }

    override suspend fun getLikedMediaList(): List<Media> {
        val localLikedList = favoriteMediaDao.getLikedMediaList()

        if (localLikedList.isNotEmpty()) {
            return localLikedList.map { it.toMedia() }
        }

        backendFavoritesRepository
            .getLikedMediaList()?.let { backendMediaDtos ->
                favoriteMediaDao.upsertFavoriteMediaList(
                    backendMediaDtos.map { it.toFavoriteMediaEntity() }
                )
                mediaDao.upsertMediaList(
                    backendMediaDtos.map { it.toMediaEntity() }
                )

                return favoriteMediaDao.getLikedMediaList().map {
                    it.toMedia()
                }
            }

        return emptyList()
    }

    override suspend fun getBookmarkedMediaList(): List<Media> {
        val localBookmarkedList = favoriteMediaDao.getBookmarkedList()

        if (localBookmarkedList.isNotEmpty()) {
            return localBookmarkedList.map { it.toMedia() }
        }

        backendFavoritesRepository
            .getBookmarkedMediaList()?.let { backendMediaDtos ->
                favoriteMediaDao.upsertFavoriteMediaList(
                    backendMediaDtos.map { it.toFavoriteMediaEntity() }
                )
                mediaDao.upsertMediaList(
                    backendMediaDtos.map { it.toMediaEntity() }
                )

                return favoriteMediaDao.getBookmarkedList().map {
                    it.toMedia()
                }
            }

        return emptyList()
    }

    private suspend fun syncFavoritesMedia() {
        val favoriteMediaEntities = favoriteMediaDao.getAllFavoriteMediaItem()

        favoriteMediaEntities.forEach { favoriteMediaEntity ->
            if (favoriteMediaEntity.isDeletedLocally) {
                syncLocallyDeleteMedia(favoriteMediaEntity)
            } else if (!favoriteMediaEntity.isSynced) {
                syncUnSyncedMedia(favoriteMediaEntity)
            }
        }
    }

    private suspend fun syncLocallyDeleteMedia(
        favoriteMediaEntity: FavoriteMediaEntity
    ) {

        val wasDeleted = backendFavoritesRepository.deleteMediaFromUser(
            favoriteMediaEntity.mediaId
        )

        if (wasDeleted) {
            favoriteMediaDao.deleteFavoriteMediaItem(
                favoriteMediaEntity
            )
        }

    }

    private suspend fun syncUnSyncedMedia(
        favoriteMediaEntity: FavoriteMediaEntity
    ) {
        val wasSynced = backendFavoritesRepository.upsertMediaToUser(
            favoriteMediaEntity.toMediaRequest()
        )

        if (wasSynced) {
            favoriteMediaDao.upsertFavoriteMediaItem(
                favoriteMediaEntity.copy(isSynced = true)
            )
        }
    }

    override suspend fun clearMainDb() {
        favoriteMediaDao.deleteAllFavoriteMediaItems()
    }

}





















