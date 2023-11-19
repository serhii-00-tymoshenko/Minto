package com.mintokoneko.minto.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mintokoneko.minto.R
import com.mintokoneko.minto.entities.user_chat.MessageStatus
import com.mintokoneko.minto.entities.user_chat.UserChat

class MainViewModel : ViewModel() {
    private val _currentChatTitle = MutableLiveData<String>()
    val currentChatTitle: LiveData<String> = _currentChatTitle

    private val _userChats = MutableLiveData<List<UserChat>>(listOf())
    val userChats: LiveData<List<UserChat>> = _userChats

    fun setCurrentChatTitle(title: String) {
        _currentChatTitle.value = title
    }

    fun getUserChats() {
        _userChats.value = listOf(
            UserChat(
                "Serhii Tymoshenko",
                R.drawable.ic_launcher_background,
                "Hello World",
                MessageStatus.READ,
                0
            ),
            UserChat(
                "Serhii Tymoshenko1",
                R.drawable.ic_launcher_background,
                "Hello World1",
                MessageStatus.NEW,
                1
            ),
            UserChat(
                "Serhii Tymoshenko2",
                R.drawable.ic_launcher_background,
                "Hello World2",
                MessageStatus.DELIVERED,
                2
            ),
            UserChat(
                "Serhii Tymoshenko3",
                R.drawable.ic_launcher_background,
                "Hello World3",
                MessageStatus.SENT,
                3
            ),
        )
    }
}