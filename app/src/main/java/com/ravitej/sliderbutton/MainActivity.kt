package com.ravitej.sliderbutton

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ravitej.sliderbutton.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnSliderCompleteListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
    }

    private fun init() {
        binding.slider.setButtonText("Slide to pay")
        binding.slider.sliderListener(this)
        binding.radioButton.setOnCheckedChangeListener { _, isChecked ->
            binding.slider.legalDisclaimerChecked(isChecked)
        }
    }

    override fun onComplete() {
        Log.e("MainActivity", "Completed")
    }
}