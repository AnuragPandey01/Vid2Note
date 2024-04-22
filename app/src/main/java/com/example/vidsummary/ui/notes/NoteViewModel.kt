package com.example.vidsummary.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vidsummary.data.api.ApiService
import com.example.vidsummary.data.request.QuizRequest
import com.example.vidsummary.data.response.NotesResponse
import com.example.vidsummary.data.response.QuizResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val apiService: ApiService
): ViewModel() {

    private val _state = MutableLiveData<NoteViewModelState>(NoteViewModelState.Idle)
    val state: LiveData<NoteViewModelState>
        get() = _state

    fun getQuiz(
        notesResponse: NotesResponse
    ){
        _state.value = NoteViewModelState.Loading
        viewModelScope.launch {
            try{
                val res = apiService.getQuiz(
                    QuizRequest(
                        notesResponse.title,
                        notesResponse.content_block_1,
                        notesResponse.content_block_2,
                        notesResponse.content_block_3
                    )
                )
                _state.value = NoteViewModelState.Success(res)
            }catch (e: Exception){
                _state.value = NoteViewModelState.Error(e.message ?: "An error occurred")
            }
        }
    }
}

sealed class NoteViewModelState{
    data object Idle: NoteViewModelState()
    data object Loading: NoteViewModelState()
    data class Success(val data: QuizResponse): NoteViewModelState()
    data class Error(val message: String): NoteViewModelState()
}