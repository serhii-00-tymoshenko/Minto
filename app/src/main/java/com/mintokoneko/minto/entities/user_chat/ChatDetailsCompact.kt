package com.mintokoneko.minto.entities.user_chat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatDetailsCompact(
    val userName: String,
    val chatId: Int
) : Parcelable