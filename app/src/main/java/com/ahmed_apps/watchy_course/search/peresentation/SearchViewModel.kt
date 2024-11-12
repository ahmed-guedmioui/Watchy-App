package com.ahmed_apps.watchy_course.search.peresentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed_apps.watchy_course.main.domain.repository.MainRepository
import com.ahmed_apps.watchy_course.search.domain.repository.SearchRepository
import com.ahmed_apps.watchy_course.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Ahmed Guedmioui
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val _searchState = MutableStateFlow(SearchState())
    val searchState = _searchState.asStateFlow()

    private val _navigateToDetailsChannel = Channel<Int>()
    val navigateToDetailsChannel = _navigateToDetailsChannel.receiveAsFlow()

    private var searchJob: Job? = null


    fun onEvent(searchUiEvent: SearchUiEvents) {
        when (searchUiEvent) {
            is SearchUiEvents.OnSearchItemClick -> {
                viewModelScope.launch {
                    mainRepository.upsertMediaItem(
                        searchUiEvent.media
                    )
                    _navigateToDetailsChannel.send(
                        searchUiEvent.media.mediaId
                    )
                }
            }

            is SearchUiEvents.OnSearchQueryChange -> {
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500)
                    _searchState.update {
                        it.copy(
                            searchQuery = searchUiEvent.searchQuery,
                            searchList = emptyList()
                        )
                    }
                    if (searchState.value.searchQuery.isNotEmpty()) {
                        loadSearchList()
                    }
                }
            }

            SearchUiEvents.OnPaginate -> {
                _searchState.update {
                    it.copy(
                        searchPage = it.searchPage + 1
                    )
                }
                loadSearchList()
            }
        }
    }

    private fun loadSearchList() {
        viewModelScope.launch {
            searchRepository.getSearchList(
                query = searchState.value.searchQuery,
                page = searchState.value.searchPage
            ).collect { result ->
                when (result) {
                    is Resource.Error -> Unit

                    is Resource.Loading -> {
                        _searchState.update {
                            it.copy(
                                isLoading = result.isLoading
                            )
                        }
                    }

                    is Resource.Success -> {
                        result.data?.let { mediaList ->
                            _searchState.update {
                                it.copy(
                                    searchList = it.searchList + mediaList
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}






















