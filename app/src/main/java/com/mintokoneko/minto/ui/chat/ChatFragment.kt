package com.mintokoneko.minto.ui.chat

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mintokoneko.minto.databinding.FragmentChatBinding
import com.mintokoneko.minto.utils.NO_VALUE
import com.mintokoneko.minto.utils.getWidth

class ChatFragment : Fragment() {
    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!
    private var chatId: Int = NO_VALUE

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

        chatId = getChatId(requireContext())
        binding.chatId.text = chatId.toString()
    }

    private fun getChatId(context: Context): Int {
        val width = getWidth(context)
        if (width < 600) {
            return navArgs<ChatFragmentArgs>().value.chatId
        } else {
            arguments?.let { bundle ->
                return bundle.getInt("chat_id", -1)
            }
        }
        return -1
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}