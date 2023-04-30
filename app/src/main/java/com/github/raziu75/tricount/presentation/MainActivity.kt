package com.github.raziu75.tricount.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.raziu75.tricount.presentation.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showHomeUi()
    }

    private fun showHomeUi() {
        supportFragmentManager
            .beginTransaction()
            .replace(
                android.R.id.content,
                HomeFragment.newInstance(),
                HomeFragment.TAG,
            )
            .commitNow()
    }
}
