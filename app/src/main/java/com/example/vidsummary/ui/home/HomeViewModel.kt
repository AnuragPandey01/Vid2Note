package com.example.vidsummary.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vidsummary.data.api.ApiService
import com.example.vidsummary.data.request.NotesRequest
import com.example.vidsummary.data.response.NotesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiService: ApiService
): ViewModel() {
    private val _state = MutableLiveData<HomeState>(HomeState.Idle)
    val state : LiveData<HomeState> = _state

    fun getNotes(url:String){
        _state.value = HomeState.Loading
        viewModelScope.launch {
            try{
                val res = apiService.getNotes(NotesRequest(url))
                _state.value = HomeState.Success(res)
                _state.value = HomeState.Idle
            }catch (e:Exception){
                _state.value = HomeState.Error(e.message ?: "An error occurred")
            }
        }
    }
}

sealed class HomeState {
    data object Loading : HomeState()
    data object Idle : HomeState()
    data class Success(val data: NotesResponse) : HomeState()
    data class Error(val message: String) : HomeState()
}