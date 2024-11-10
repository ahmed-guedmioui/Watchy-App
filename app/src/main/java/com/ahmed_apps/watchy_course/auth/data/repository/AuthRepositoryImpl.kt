package com.ahmed_apps.watchy_course.auth.data.repository

import android.content.SharedPreferences
import com.ahmed_apps.watchy_course.auth.data.remote.AuthApi
import com.ahmed_apps.watchy_course.auth.data.remote.dto.AuthRequest
import com.ahmed_apps.watchy_course.auth.domain.repository.AuthRepository
import com.ahmed_apps.watchy_course.auth.util.AuthResult
import com.ahmed_apps.watchy_course.favorites.domain.repository.FavoritesRepository
import com.ahmed_apps.watchy_course.main.domain.repository.MainRepository
import retrofit2.HttpException
import javax.inject.Inject

/**
 * @author Ahmed Guedmioui
 */
class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val mainRepository: MainRepository,
    private val favoritesRepository: FavoritesRepository,
    private val prefs: SharedPreferences
) : AuthRepository {
    override suspend fun register(
        name: String, email: String, password: String
    ): AuthResult<Unit> {

        return try {
            authApi.register(
                AuthRequest(
                    name = name,
                    email = email,
                    password = password
                )
            )

            login(email, password)

        } catch (e: HttpException) {
            e.printStackTrace()
            if (e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            AuthResult.UnknownError()
        }


    }

    override suspend fun login(
        email: String, password: String
    ): AuthResult<Unit> {
        return try {

            val authRespond = authApi.login(
                authRequest = AuthRequest(
                    email = email,
                    password = password
                )
            )

            prefs.edit().putString("email", email).apply()
            prefs.edit().putString("name", authRespond.name).apply()
            prefs.edit().putString("token", authRespond.token).apply()

            AuthResult.Authorized()

        } catch (e: HttpException) {
            e.printStackTrace()
            println(e.message)
            if (e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.Authorized()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            println(e.message)
            AuthResult.Authorized()
        }
    }

    override suspend fun authenticate(): AuthResult<Unit> {
        return try {

            val token = prefs.getString("token", null)
                ?: return AuthResult.Unauthorized()

            authApi.authenticate("Bearer $token")

            AuthResult.Authorized()

        } catch (e: HttpException) {
            e.printStackTrace()
            if (e.code() == 401) {
                AuthResult.Authorized()
            } else {
                AuthResult.Authorized()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            AuthResult.Authorized()
        }
    }

    override suspend fun logout(): AuthResult<Unit> {
        prefs.edit().putString("email", null).apply()
        prefs.edit().putString("name", null).apply()
        prefs.edit().putString("token", null).apply()

        mainRepository.cleareMainDb()
        favoritesRepository.clearMainDb()

        return AuthResult.SingedOut()

    }
}











