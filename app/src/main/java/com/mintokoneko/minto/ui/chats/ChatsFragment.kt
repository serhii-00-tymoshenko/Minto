package com.mintokoneko.minto.ui.chats

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mintokoneko.minto.databinding.FragmentChatsBinding
import com.mintokoneko.minto.entities.user_chat.ChatDetailsCompact
import com.mintokoneko.minto.ui.MainViewModel
import com.mintokoneko.minto.ui.chat.ChatFragment
import com.mintokoneko.minto.ui.chats.adapters.ChatsAdapter
import com.mintokoneko.minto.utils.getWidth

class ChatsFragment : Fragment() {
    private var _binding: FragmentChatsBinding? = null
    private val binding get() = _binding!!
    private lateinit var chatsAdapter: ChatsAdapter
    private val sharedViewModel: MainViewModel by activityViewModels()

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

        setupChatsRecycler(requireActivity())
    }

    private fun setupChatsRecycler(activity: Activity) {
        chatsAdapter = ChatsAdapter { userChatCompact ->
            showChat(userChatCompact, activity)
            setTitle(userChatCompact.userName)
        }

        val chatsRecycler = binding.chatsRecycler
        chatsRecycler.apply {
            adapter = chatsAdapter
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }

        sharedViewModel.userChats.observe(viewLifecycleOwner) { userChats ->
            chatsAdapter.submitList(userChats)
        }
    }

    private fun setTitle(title: String) {
        sharedViewModel.setCurrentChatTitle(title)
    }

    private fun showChat(chatDetailsCompact: ChatDetailsCompact, activity: Activity) {
        if (getWidth(activity) < 600) {
            val showChatAction =
                ChatsFragmentDirections.actionChatsFragmentToChatFragment(chatDetailsCompact)
            requireView().findNavController().navigate(showChatAction)
        } else {
            val chatFragment = ChatFragment()
            val bundle = Bundle()
            bundle.putParcelable("user_chat_compact", chatDetailsCompact)
            chatFragment.arguments = bundle

            invokeChatTransaction(chatFragment, activity)
        }
    }

    private fun invokeChatTransaction(chatFragment: ChatFragment, activity: Activity) {
        val fragmentManager = (activity as FragmentActivity).supportFragmentManager
        fragmentManager
            .beginTransaction()
            .replace(binding.chatContainer!!.id, chatFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}