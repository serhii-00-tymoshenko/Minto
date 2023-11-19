package com.mintokoneko.minto.ui.chats.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mintokoneko.minto.databinding.ItemChatPreviewBinding
import com.mintokoneko.minto.entities.ChatPreview

class ChatsAdapter(
    private val callback: (Int) -> Unit
) : ListAdapter<ChatPreview, ChatsAdapter.ChatsViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ChatPreview>() {
            override fun areItemsTheSame(oldItem: ChatPreview, newItem: ChatPreview)
                = oldItem.chatId == newItem.chatId

            override fun areContentsTheSame(oldItem: ChatPreview, newItem: ChatPreview)
                = oldItem == newItem
        }
    }

    inner class ChatsViewHolder(private val binding: ItemChatPreviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chatPreview: ChatPreview) {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                binding.root.setOnClickListener {
                    callback.invoke(getItem(adapterPosition).chatId)
                }
            }

            binding.apply {
                chatPreviewUserName.text = chatPreview.userName
                chatPreviewMessage.text = chatPreview.message
                chatPreviewUserPhoto.setImageResource(chatPreview.userPhoto)
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