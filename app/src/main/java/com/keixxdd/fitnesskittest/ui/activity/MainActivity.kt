package com.keixxdd.fitnesskittest.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.keixxdd.fitnesskittest.R
import com.keixxdd.fitnesskittest.databinding.ActivityMainBinding
import com.keixxdd.fitnesskittest.ui.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchTrainings()
        setupBottomNavBar()
    }

    private fun setupBottomNavBar() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.trainings -> false
                R.id.requests -> false
                R.id.add -> false
                R.id.chat -> false
                R.id.more -> false
                else -> false
            }
        }
    }

    private fun fetchTrainings() {
        viewModel.getTrainings()
    }
}