package com.game2piliptsev.backintime.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.game2piliptsev.backintime.databinding.FragmentMenuBinding
import com.game2piliptsev.backintime.presentation.MainActivity

class MenuFragment : Fragment() {

    private val binding by lazy {
        FragmentMenuBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonPlay.setOnClickListener {
            (activity as MainActivity).launchGameModeFragment()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MenuFragment()
    }
}