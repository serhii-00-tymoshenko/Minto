package com.mintokoneko.minto.ui.chats.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mintokoneko.minto.databinding.ItemChatPreviewBinding
import com.mintokoneko.minto.entities.user_chat.UserChat
import com.mintokoneko.minto.entities.user_chat.ChatDetailsCompact
import com.mintokoneko.minto.entities.user_chat.mappers.toUserChatCompact

class ChatsAdapter(
    private val callback: (ChatDetailsCompact) -> Unit
) : ListAdapter<UserChat, ChatsAdapter.ChatsViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserChat>() {
            override fun areItemsTheSame(oldItem: UserChat, newItem: UserChat) =
                oldItem.chatId == newItem.chatId

            override fun areContentsTheSame(oldItem: UserChat, newItem: UserChat) =
                oldItem == newItem
        }
    }

    inner class ChatsViewHolder(private val binding: ItemChatPreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userChat: UserChat) {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                binding.root.setOnClickListener {
                    callback.invoke(toUserChatCompact(userChat))
                }
            }

            binding.apply {
                chatPreviewUserName.text = userChat.userName
                chatPreviewMessage.text = userChat.message
                chatPreviewUserPhoto.setImageResource(userChat.userPhoto)
                chatPreviewMessageStatusImage.setImageResource(userChat.messageStatusImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsViewHolder {
        val binding =
            ItemChatPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatsViewHolder, position: Int) {
        val currentChatPreview = getItem(position)
        holder.bind(currentChatPreview)
    }
}