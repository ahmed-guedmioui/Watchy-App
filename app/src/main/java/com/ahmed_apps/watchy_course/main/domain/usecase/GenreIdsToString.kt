package com.ahmed_apps.watchy_course.main.domain.usecase

import com.ahmed_apps.watchy_course.util.APIConstants.genres

/**
 * @author Ahmed Guedmioui
 */
object GenreIdsToString {
    fun genreIdsToString(genreIds: List<String>): String {
        return genreIds.map { id ->
            genres.find { genre ->
                genre.genreId.toString() == id
            }?.genreName
        }.joinToString(" - ")
    }
}



















