package com.mintokoneko.minto.ui.chats.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mintokoneko.minto.databinding.ItemChatPreviewBinding
import com.mintokoneko.minto.entities.user_chat.UserChat
import com.mintokoneko.minto.entities.user_chat.ChatDetailsCompact
import com.mintokoneko.minto.entities.user_chat.mappers.toChatDetailCompact
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toDuration

class ChatsAdapter(
    private val transitionListener: MotionLayout.TransitionListener,
    private val callback: (ChatDetailsCompact) -> Unit,
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
        @SuppressLint("ClickableViewAccessibility")
        fun bind(userChat: UserChat) {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                binding.chatPreviewMotionLayout.apply {
                    setTransitionListener(transitionListener)
                    // TODO: KILL ALL GOOGLE DEVS
                    setOnTouchListener { _, event ->
                        if (event.action == MotionEvent.ACTION_UP && this.progress == 0.0f) {
                            callback.invoke(toChatDetailCompact(getItem(adapterPosition)))
                        }
                        false
                    }
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