package com.ahmed_apps.watchy_course.favorites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed_apps.watchy_course.favorites.domain.repository.FavoritesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Ahmed Guedmioui
 */
@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val _favoritesState = MutableStateFlow(FavoritesState())
    val favoritesState = _favoritesState.asStateFlow()

    init {
        load()

        viewModelScope.launch {
            favoritesRepository.favoritesDbUpdateEventFlow().collect { isUpdated ->
                if (isUpdated) {
                    load()
                }
            }
        }
    }

    fun onEvent(favoritesUiEvent: FavoritesUiEvents) {
        when (favoritesUiEvent) {
            FavoritesUiEvents.Refresh -> {
                load()
            }
        }
    }

    private fun load() {
        viewModelScope.launch {
            loadLikedList()
            loadBookmarksList()
        }

    }

    private suspend fun loadLikedList() {
        viewModelScope.launch {
            _favoritesState.update {
                it.copy(
                    likedList = favoritesRepository.getLikedMediaList()
                )
            }
        }
    }

    private suspend fun loadBookmarksList() {
        viewModelScope.launch {
            _favoritesState.update {
                it.copy(
                    bookmarksList = favoritesRepository.getBookmarkedMediaList()
                )
            }
        }
    }

}




















