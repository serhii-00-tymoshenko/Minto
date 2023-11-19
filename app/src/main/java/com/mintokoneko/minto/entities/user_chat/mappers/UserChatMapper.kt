package com.mintokoneko.minto.entities.user_chat.mappers

import com.mintokoneko.minto.entities.user_chat.UserChat
import com.mintokoneko.minto.entities.user_chat.ChatDetailsCompact

fun toUserChatCompact(userChat: UserChat): ChatDetailsCompact =
    ChatDetailsCompact(userName = userChat.userName, chatId = userChat.chatId)