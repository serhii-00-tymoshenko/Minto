package com.mintokoneko.minto.entities.user_chat

import com.mintokoneko.minto.R

data class UserChat(
    val userName: String,
    val userPhoto: Int,
    val message: String,
    val _messageStatus: MessageStatus,
    val chatId: Int,
) {
    val messageStatusImage: Int
        get() {
            return when(_messageStatus) {
                MessageStatus.READ -> R.drawable.ic_status_read
                MessageStatus.NEW -> R.drawable.ic_status_new
                MessageStatus.SENT -> R.drawable.ic_status_new
                MessageStatus.DELIVERED -> R.drawable.ic_status_delivered
            }
        }
}

enum class MessageStatus {
    READ, NEW, SENT, DELIVERED
}
