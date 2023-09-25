package com.game2piliptsev.backintime.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.game2piliptsev.backintime.R
import com.game2piliptsev.backintime.databinding.ActivityMainBinding
import com.game2piliptsev.backintime.presentation.fragments.GameFragment
import com.game2piliptsev.backintime.presentation.fragments.GameModeFragment
import com.game2piliptsev.backintime.presentation.fragments.ResultFragment

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {}

    fun launchGameFragmentInCasualMode() {
        launchFragment(GameFragment.newInstance(GameFragment.Companion.GameMode.CASUAL))
    }

    fun launchGameFragmentInPlayForTimeMode() {
        launchFragment(GameFragment.newInstance(GameFragment.Companion.GameMode.SURVIVAL))
    }

    fun launchResultFragment(isPlayerLost: Boolean) {
        launchFragment(ResultFragment.newInstance(isPlayerLost))
    }

    fun launchGameModeFragment() {
        launchFragment(GameModeFragment.newInstance())
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(
                binding.fragmentContainer.id,
                fragment
            )
            .commit()
    }
}