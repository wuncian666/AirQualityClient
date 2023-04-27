package com.example.aqiclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.aqiclient.data.util.Resource
import com.example.aqiclient.databinding.ActivityMainBinding
import com.example.aqiclient.presentation.adapter.AQIPerHourAdapter
import com.example.aqiclient.presentation.viewmodel.AQIPerHourViewModel
import com.example.aqiclient.presentation.viewmodel.AQIPerHourViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: AQIPerHourViewModelFactory
    lateinit var viewModel: AQIPerHourViewModel

    @Inject
    lateinit var aqiPerHourAdapter: AQIPerHourAdapter

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvMenu.setupWithNavController(navController)

        viewModel = ViewModelProvider(this, factory).get(AQIPerHourViewModel::class.java)
    }
}