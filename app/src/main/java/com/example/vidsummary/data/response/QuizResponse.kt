package com.example.vidsummary.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizResponse(
    val question_1:String?,
    val question_2:String?,
    val question_3:String?,
    val question_4:String?,
    val question_5:String?,
    val question_6:String?,
    val question_7:String?,
    val question_8:String?,
    val question_9:String?,
    val question_10:String?,
    val answer_1:String?,
    val answer_2:String?,
    val answer_3:String?,
    val answer_4:String?,
    val answer_5:String?,
    val answer_6:String?,
    val answer_7:String?,
    val answer_8:String?,
    val answer_9:String?,
    val answer_10:String?
): Parcelable