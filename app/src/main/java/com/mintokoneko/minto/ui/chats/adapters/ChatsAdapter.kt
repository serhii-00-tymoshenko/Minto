package com.mintokoneko.minto.ui.chats.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mintokoneko.minto.databinding.ItemChatPreviewBinding
import com.mintokoneko.minto.entities.ChatUserPreview

class ChatsAdapter(
    private val callback: (Int) -> Unit
) : ListAdapter<ChatUserPreview, ChatsAdapter.ChatsViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ChatUserPreview>() {
            override fun areItemsTheSame(oldItem: ChatUserPreview, newItem: ChatUserPreview)
                = oldItem.chatId == newItem.chatId

            override fun areContentsTheSame(oldItem: ChatUserPreview, newItem: ChatUserPreview)
                = oldItem == newItem
        }
    }

    inner class ChatsViewHolder(private val binding: ItemChatPreviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chatUserPreview: ChatUserPreview) {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                binding.root.setOnClickListener {
                    callback.invoke(getItem(adapterPosition).chatId)
                }
            }

            binding.apply {
                chatPreviewUserName.text = chatUserPreview.userName
                chatPreviewMessage.text = chatUserPreview.message
                chatPreviewUserPhoto.setImageResource(chatUserPreview.userPhoto)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsViewHolder {
        val binding = ItemChatPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatsViewHolder, position: Int) {
        val currentChatPreview = getItem(position)
        holder.bind(currentChatPreview)
    }
}