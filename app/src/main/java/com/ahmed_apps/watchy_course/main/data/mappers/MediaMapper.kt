package com.ahmed_apps.watchy_course.main.data.mappers

import com.ahmed_apps.watchy_course.main.data.local.MediaEntity
import com.ahmed_apps.watchy_course.main.data.remote.dto.MediaDto
import com.ahmed_apps.watchy_course.main.domain.model.Media
import com.ahmed_apps.watchy_course.util.APIConstants

/**
 * @author Ahmed Guedmioui
 */

fun Media.toMediaEntity(): MediaEntity {
    return MediaEntity(
        mediaId = mediaId,

        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        voteAverage = voteAverage,
        popularity = popularity,
        voteCount = voteCount,
        genreIds = try {
            genreIds.joinToString(",")
        } catch (e: Exception) {
            ""
        },
        adult = adult,
        mediaType = mediaType,
        originCountry = try {
            originCountry.joinToString(",")
        } catch (e: Exception) {
            ""
        },
        originalTitle = originalTitle,
        category = category,

        runTime = runTime,
        tagLine = tagLine,

        videosIds = try {
            videosIds.joinToString(",")
        } catch (e: Exception) {
            ""
        },
        similarMediaIds = try {
            similarMediaIds.joinToString(",")
        } catch (e: Exception) {
            ""
        }

    )
}

fun MediaEntity.toMedia(): Media {
    return Media(
        mediaId = mediaId,

        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        voteAverage = voteAverage,
        popularity = popularity,
        voteCount = voteCount,
        genreIds = try {
            genreIds.split(",").map { it }
        } catch (e: Exception) {
            emptyList()
        },
        adult = adult,
        mediaType = mediaType,
        originCountry = try {
            originCountry.split(",").map { it }
        } catch (e: Exception) {
            emptyList()
        },
        originalTitle = originalTitle,
        category = category,

        runTime = runTime,
        tagLine = tagLine,

        videosIds = if (videosIds.isEmpty()) {
            emptyList()
        } else {
            try {
                videosIds.split(",").map { it }
            } catch (e: Exception) {
                emptyList()
            }
        },
        similarMediaIds = if (similarMediaIds.isEmpty()) {
            emptyList()
        } else {
            try {
                similarMediaIds.split(",").map { it.toInt() }
            } catch (e: Exception) {
                emptyList()
            }
        }

    )
}

fun MediaDto.toMediaEntity(
    type: String,
    category: String
): MediaEntity {
    return MediaEntity(
        mediaId = id ?: 0,

        backdropPath = backdrop_path ?: "",
        originalLanguage = original_language ?: "",
        overview = overview ?: "",
        posterPath = poster_path ?: "",
        releaseDate = release_date ?: first_air_date ?: "",
        title = title ?: name ?: "",
        originalTitle = original_title ?: original_name ?: "",
        voteAverage = vote_average ?: 0.0,
        popularity = popularity ?: 0.0,
        voteCount = vote_count ?: 0,
        genreIds = try {
            genre_ids?.joinToString(",") ?: ""
        } catch (e: Exception) {
            ""
        },

        adult = adult ?: false,
        mediaType = type,
        category = category,
        originCountry = try {
            origin_country?.joinToString(",") ?: ""
        } catch (e: Exception) {
            ""
        },

        runTime = 0,
        tagLine = "",

        videosIds = "",
        similarMediaIds = ""

    )
}



fun MediaDto.toMedia(
    category: String
) : Media {
    return Media(
        mediaId = id ?: 0,

        backdropPath = backdrop_path ?: "",
        originalLanguage = original_language ?: "",
        overview = overview ?: "",
        posterPath = poster_path ?: "",
        releaseDate = release_date ?: first_air_date ?: "",
        title = title ?: name ?: "",
        voteAverage = vote_average ?: 0.0,
        popularity = popularity ?: 0.0,
        voteCount = vote_count ?: 0,
        genreIds = if (genre_ids?.isEmpty() == true) {
            emptyList()
        } else {
            try {
                genre_ids?.map { it.toString() } ?: emptyList()
            } catch (e: Exception) {
                emptyList()
            }
        },
        adult = adult ?: false,
        mediaType = media_type ?: APIConstants.MOVIE,
        category = category,
        originCountry = origin_country ?: emptyList(),
        originalTitle = original_title ?: original_name ?: "",

        runTime = 0,
        tagLine = "",

        videosIds = emptyList(),
        similarMediaIds = emptyList()
    )
}
















