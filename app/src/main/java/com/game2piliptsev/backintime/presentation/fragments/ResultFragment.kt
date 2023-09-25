package com.game2piliptsev.backintime.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.game2piliptsev.backintime.BackInTimeApp
import com.game2piliptsev.backintime.R
import com.game2piliptsev.backintime.databinding.FragmentResultBinding
import com.game2piliptsev.backintime.presentation.MainActivity
import com.game2piliptsev.backintime.presentation.viewmodels.ResultViewModel
import com.game2piliptsev.backintime.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject

private const val ARG_PARAM_IS_LOST = "param_is_lost"

class ResultFragment : Fragment() {

    private var paramIsLost: Boolean = false

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val binding by lazy {
        FragmentResultBinding.inflate(layoutInflater)
    }

    private val viewModel: ResultViewModel by viewModels {
        viewModelFactory
    }

    private val component by lazy {
        (activity?.application as BackInTimeApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramIsLost = it.getBoolean(ARG_PARAM_IS_LOST)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonPlayAgain.setOnClickListener {
            (activity as MainActivity).launchGameModeFragment()
        }

        val countOfCorrectAnswers = viewModel.getCountOfCorrectAnswers()

        binding.score.text = if (paramIsLost)
            resources.getString(R.string.game_over)
        else String.format(
            resources.getString(R.string.result_of_the_game),
            countOfCorrectAnswers
        )
    }

    companion object {
        @JvmStatic
        fun newInstance(isPlayerLost: Boolean) = ResultFragment().apply {
            arguments = Bundle().apply {
                putBoolean(ARG_PARAM_IS_LOST, isPlayerLost)
            }
        }
    }
}