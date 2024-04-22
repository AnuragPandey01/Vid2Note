package com.example.vidsummary.data.api

import com.example.vidsummary.data.request.NotesRequest
import com.example.vidsummary.data.request.QuizRequest
import com.example.vidsummary.data.response.NotesResponse
import com.example.vidsummary.data.response.QuizResponse
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {

    companion object{
        const val BASE_URL = "https://707e-110-44-10-105.ngrok-free.app/vid2notes/"
    }

    @POST("notes_generator")
    suspend fun getNotes(
        @Body request: NotesRequest
    ): NotesResponse

    @POST("quiz_generator")
    suspend fun getQuiz(
        @Body request: QuizRequest
    ): QuizResponse

}