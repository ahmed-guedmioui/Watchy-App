package com.ahmed_apps.watchy_course.main.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * @author Ahmed Guedmioui
 */
@Database(
    entities = [MediaEntity::class],
    version = 1
)
abstract class MediaDatabase: RoomDatabase() {
    abstract val mediaDao: MediaDao
}


















