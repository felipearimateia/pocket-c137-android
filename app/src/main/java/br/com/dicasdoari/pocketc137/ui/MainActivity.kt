package br.com.dicasdoari.pocketc137.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.dicasdoari.pocketc137.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}