package com.ahmed_apps.watchy_course.main.data.remote.dto

data class MediaDto(
    val id: Int? = null,
    val adult: Boolean? = null,
    val backdrop_path: String? = null,
    val first_air_date: String? = null,
    val genre_ids: List<Int>? = null,
    var origin_country: List<String>? = null,
    val original_language: String? = null,
    val original_name: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val name: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val release_date: String? = null,
    val media_type: String? = null,
    val title: String? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null
)