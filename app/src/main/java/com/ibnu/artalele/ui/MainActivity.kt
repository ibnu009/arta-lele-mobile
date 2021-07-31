package com.ibnu.artalele.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ibnu.artalele.R
import com.ibnu.artalele.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _bindingMainActivity: ActivityMainBinding? = null
    private val binding get() = _bindingMainActivity
    
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _bindingMainActivity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_bindingMainActivity?.root)

        binding?.bottomNav?.setupWithNavController(
            findNavController(this, R.id.container_fragment)
        )
    }
}