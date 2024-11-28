package com.ahmed_apps.watchy_course.main.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

/**
 * @author Ahmed Guedmioui
 */
@Dao
interface MediaDao {

    @Upsert
    suspend fun upsertMediaList(mediaEntities: List<MediaEntity>)

    @Upsert
    suspend fun upsertMediaItem(mediaEntity: MediaEntity)



    @Query("SELECT * FROM MediaEntity")
    suspend fun getAllMediaList(): List<MediaEntity>

    @Query("SELECT * FROM MediaEntity WHERE mediaType = :mediaType AND category = :category")
    suspend fun getMediaListByTypeAndCategory(
        mediaType: String, category: String
    ): List<MediaEntity>

    @Query("SELECT * FROM MediaEntity WHERE category = :category")
    suspend fun getMediaListByCategory(
        category: String
    ): List<MediaEntity>

    @Query("SELECT * FROM MediaEntity WHERE mediaId = :mediaId")
    suspend fun getMediaItemById(
        mediaId: Int
    ): MediaEntity

    @Query("SELECT COUNT(*) FROM MediaEntity WHERE mediaId = :mediaId")
    suspend fun doesMediaItemExists(
        mediaId: Int
    ): Int

    @Query("SELECT * FROM MediaEntity WHERE mediaId IN (:ids)")
    suspend fun getMediaListByIds(
       ids: List<Int>
    ): List<MediaEntity>


    @Query("DELETE FROM MediaEntity")
    suspend fun deleteAllMediaItem()

    @Query("DELETE FROM MediaEntity WHERE mediaType = :mediaType AND category = :category")
    suspend fun deleteAllMediaListByTypeAndCategory(
        mediaType: String, category: String
    )

    @Query("DELETE FROM MediaEntity WHERE category = :category")
    suspend fun deleteAllMediaListByCategory(
        category: String
    )

}




















