package com.mintokoneko.minto.ui.chats

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mintokoneko.minto.R
import com.mintokoneko.minto.databinding.FragmentChatsBinding
import com.mintokoneko.minto.entities.ChatPreview
import com.mintokoneko.minto.ui.chat.ChatFragment
import com.mintokoneko.minto.ui.chats.adapters.ChatsAdapter
import com.mintokoneko.minto.utils.getWidth

class ChatsFragment : Fragment() {
    private var _binding: FragmentChatsBinding? = null
    private val binding get() = _binding!!
    private lateinit var chatsAdapter: ChatsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupChatsRecycler(requireContext())
    }

    private fun setupChatsRecycler(context: Context) {
        chatsAdapter = ChatsAdapter { chatId ->
            showChat(chatId, context)
        }

        val chatsRecycler = binding.chatsRecycler
        chatsRecycler.apply {
            adapter = chatsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        chatsAdapter.submitList(
            listOf(
                ChatPreview(
                    "Serhii Tymoshenko",
                    R.drawable.ic_launcher_background,
                    "Hello World",
                    0
                ),
                ChatPreview(
                    "Serhii Tymoshenko",
                    R.drawable.ic_launcher_background,
                    "Hello World1",
                    1
                ),
                ChatPreview(
                    "Serhii Tymoshenko",
                    R.drawable.ic_launcher_background,
                    "Hello World2",
                    2
                ),
            )
        )
    }

    private fun showChat(chatId: Int, context: Context) {
        if (getWidth(context) < 600) {
            val showChatAction = ChatsFragmentDirections.actionChatsFragmentToChatFragment(chatId)
            requireView().findNavController().navigate(showChatAction)
        } else {
            val fragmentManager = requireActivity().supportFragmentManager


            val chatFragment = ChatFragment()
            val bundle = Bundle()
            bundle.putInt("chat_id", chatId)
            chatFragment.arguments = bundle

            fragmentManager
                .beginTransaction()
                .replace(binding.chatContainer!!.id, chatFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}