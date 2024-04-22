package com.example.vidsummary.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NotesResponse(
    val title:String?,
    val content_block_1:String?,
    val content_block_2:String?,
    val content_block_3:String?,
    val thumbnail_url:String?,
): Parcelable