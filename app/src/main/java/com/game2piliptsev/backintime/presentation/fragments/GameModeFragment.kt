package com.game2piliptsev.backintime.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.game2piliptsev.backintime.databinding.FragmentGameModeBinding
import com.game2piliptsev.backintime.presentation.MainActivity

class GameModeFragment : Fragment() {

    private val binding by lazy {
        FragmentGameModeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.survivalModeButton.setOnClickListener {
            (activity as MainActivity).launchGameFragmentInPlayForTimeMode()
        }
        binding.casualButton.setOnClickListener {
            (activity as MainActivity).launchGameFragmentInCasualMode()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = GameModeFragment()
    }
}