package com.mintokoneko.minto.ui.chat

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mintokoneko.minto.databinding.FragmentChatBinding
import com.mintokoneko.minto.entities.user_chat.ChatDetailsCompact
import com.mintokoneko.minto.utils.getWidth

class ChatFragment : Fragment() {
    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!
    private lateinit var chatDetailsCompact: ChatDetailsCompact

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatDetailsCompact = getUser(requireContext())
    }

    private fun getUser(context: Context): ChatDetailsCompact {
        return if (getWidth(context) < 600) {
            navArgs<ChatFragmentArgs>().value.userChatCompact
        } else {
            getArgumentsParcelable()
        }
    }

    private fun getArgumentsParcelable(): ChatDetailsCompact {
        val emptyChatDetailsCompact = ChatDetailsCompact(
            "",
            0,
        )
        arguments?.let { bundle ->
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable(
                    "user_chat_compact",
                    ChatDetailsCompact::class.java
                ) as ChatDetailsCompact
            } else {
                bundle.getParcelable("user_chat_compact") ?: emptyChatDetailsCompact
            }
        }
        return emptyChatDetailsCompact
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}