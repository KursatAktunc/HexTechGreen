package com.example.hextechgreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.hextechgreen.databinding.ActivityMainBinding
import com.example.hextechgreen.repository.Repository
import com.example.hextechgreen.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        setRefreshSwitchState()
    }

    private fun checkSwitchState() {
        viewModel.getPost()
        viewModel.myResponse.observe(this, { response ->
            binding.pin1.isChecked = response.p1
            binding.pin2.isChecked = response.p2
            binding.pin3.isChecked = response.p3
            binding.pin4.isChecked = response.p4
        })
    }

    private fun setRefreshSwitchState() {
        lifecycleScope.launch {
            while (true) {
                checkSwitchState()
                delay(15000)
            }
        }
    }
}