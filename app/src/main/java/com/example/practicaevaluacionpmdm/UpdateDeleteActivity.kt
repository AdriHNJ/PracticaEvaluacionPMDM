package com.example.practicaevaluacionpmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practicaevaluacionpmdm.databinding.ActivityUpdateDeleteBinding

class UpdateDeleteActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateDeleteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUpdateDeleteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}