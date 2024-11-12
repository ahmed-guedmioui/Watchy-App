package com.ahmed_apps.watchy_course.details.domain.repository

import com.ahmed_apps.watchy_course.main.domain.model.Media
import com.ahmed_apps.watchy_course.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * @author Ahmed Guedmioui
 */
interface SimilarRepository {
    suspend fun getSimilarMedia(
        id: Int
    ): Flow<Resource<List<Media>>>
}
















