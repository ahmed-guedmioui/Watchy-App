package com.ahmed_apps.watchy_course.profile.domain.repository

/**
 * @author Ahmed Guedmioui
 */
interface ProfileRepository {
    suspend fun getName(): String
    suspend fun getEmail(): String
}