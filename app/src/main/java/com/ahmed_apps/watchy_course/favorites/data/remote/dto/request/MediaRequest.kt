package com.ahmed_apps.watchy_course.favorites.data.remote.dto.request


data class MediaRequest(
    val mediaId: Int,

    val isLiked: Boolean,
    val isBookmarked: Boolean,

    val adult: Boolean,
    val backdropPath: String,
    val genreIds: String,
    var mediaType: String,
    val originCountry: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    var category: String,

    val runTime: Int,
    val tagLine: String,

    val videosIds: String,
    val similarMediaIds: String
)