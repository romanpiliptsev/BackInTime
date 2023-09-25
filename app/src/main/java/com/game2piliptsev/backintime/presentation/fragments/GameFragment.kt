package com.game2piliptsev.backintime.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.game2piliptsev.backintime.BackInTimeApp
import com.game2piliptsev.backintime.R
import com.game2piliptsev.backintime.databinding.FragmentGameBinding
import com.game2piliptsev.backintime.domain.entities.LevelEntity
import com.game2piliptsev.backintime.presentation.MainActivity
import com.game2piliptsev.backintime.presentation.viewmodels.GameViewModel
import com.game2piliptsev.backintime.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject

private const val ARG_PARAM_GAME_MODE = "param_game_mode"

class GameFragment : Fragment() {

    private lateinit var paramGameMode: GameMode

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val binding by lazy {
        FragmentGameBinding.inflate(layoutInflater)
    }

    private val viewModel: GameViewModel by viewModels {
        viewModelFactory
    }

    private val component by lazy {
        (activity?.application as BackInTimeApp).component
    }

    private var levelNumber: Int = 0
    private var currentHeartQuantity = 3
    private lateinit var currentLevel: LevelEntity
    private var arrayForTips = arrayListOf(1, 2, 3, 4)

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramGameMode = it.getSerializable(ARG_PARAM_GAME_MODE) as GameMode
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

        if (paramGameMode == GameMode.CASUAL) {
            with(binding) {
                heart1.visibility = View.GONE
                heart2.visibility = View.GONE
                heart3.visibility = View.GONE
            }
        }

        viewModel.getLevelByNumber(levelNumber)

        viewModel.getLevelLiveData.observe(viewLifecycleOwner) {
            currentLevel = it
            with(binding) {
                image.setImageResource(it.image)
                resources.getStringArray(it.answerListId).let { arr ->
                    answer1TextView.text = arr[0]
                    answer2TextView.text = arr[1]
                    answer3TextView.text = arr[2]
                    answer4TextView.text = arr[3]
                }

                if (arrayForTips.size < 3) {
                    arrayListOf(
                        answer1Button, answer2Button, answer3Button, answer4Button
                    ).forEach { iv ->
                        iv.isEnabled = true
                    }

                    arrayListOf(
                        answer1TextView, answer2TextView, answer3TextView, answer4TextView
                    ).forEach { tv ->
                        tv.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                    }
                }
            }
            arrayForTips = arrayListOf(1, 2, 3, 4)
            arrayForTips.remove(it.correctAnswer)
        }

        viewModel.getCurrentScoreLiveData.observe(viewLifecycleOwner) {
            binding.score.text = it.toString()
        }

        binding.apply {
            val list = arrayListOf(answer1Button, answer2Button, answer3Button, answer4Button)
            list.indices.forEach {
                list[it].setOnClickListener { _ ->
                    viewModel.savePlayerAnswer(levelNumber, it + 1)

                    showToast(
                        if (currentLevel.correctAnswer == it + 1)
                            resources.getString(R.string.correct)
                        else
                            resources.getString(R.string.wrong)
                    )

                    if (paramGameMode == GameMode.SURVIVAL && currentLevel.correctAnswer != it + 1) {
                        when (currentHeartQuantity--) {
                            3 -> heart3.setImageResource(R.drawable.empty_heart)
                            2 -> heart2.setImageResource(R.drawable.empty_heart)
                            1 -> {
                                heart1.setImageResource(R.drawable.empty_heart)
                                (activity as MainActivity).launchResultFragment(true)
                            }
                        }
                    }

                    if (currentHeartQuantity != 0) {
                        if (++levelNumber == QUANTITY_OF_LEVELS) {
                            (requireActivity() as MainActivity).launchResultFragment(false)
                        } else {
                            viewModel.getLevelByNumber(levelNumber)
                        }
                    }
                }
            }
        }

        with(binding) {
            bulb.setOnClickListener {
                if (arrayForTips.isNotEmpty()) {
                    if (score.text.toString().toInt() >= 2) {
                        viewModel.subtractTwoPoints()
                        val numOfWrongAnswer = arrayForTips.random()
                        arrayForTips.remove(numOfWrongAnswer)

                        arrayListOf(
                            answer1Button, answer2Button, answer3Button, answer4Button
                        ).let {
                            it[numOfWrongAnswer - 1].isEnabled = false
                        }
                        arrayListOf(
                            answer1TextView, answer2TextView, answer3TextView, answer4TextView
                        ).let {
                            it[numOfWrongAnswer - 1].setTextColor(
                                ContextCompat.getColor(requireContext(), R.color.red)
                            )
                        }
                    } else {
                        showToast(resources.getString(R.string.not_enough_points))
                    }
                } else {
                    showToast(resources.getString(R.string.only_the_correct_answer))
                }
            }
        }
    }

    private fun showToast(text: String) = Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()

    companion object {

        @JvmStatic
        fun newInstance(mode: GameMode) =
            GameFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM_GAME_MODE, mode)
                }
            }

        enum class GameMode {
            CASUAL, SURVIVAL
        }

        private const val QUANTITY_OF_LEVELS = 14
    }
}