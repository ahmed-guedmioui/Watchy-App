package com.ahmed_apps.watchy_course.details.domain.repository

import com.ahmed_apps.watchy_course.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * @author Ahmed Guedmioui
 */
interface VideosRepository {
    suspend fun getVideos(
        id: Int,
        isRefreshing: Boolean
    ): Flow<Resource<List<String>>>
}
















