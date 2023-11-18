package com.mintokoneko.minto.ui.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mintokoneko.minto.databinding.ContentBottomSheetBinding
import com.mintokoneko.minto.databinding.ContentSideSheetBinding

class BottomSheet : BottomSheetDialogFragment() {
    companion object {
        const val TAG = "ModalBottomSheet"
    }

    private var _binding: ContentBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ContentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}