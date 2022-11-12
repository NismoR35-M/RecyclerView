package com.recycler.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.recycler.views.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        // 1
        if (savedInstanceState == null) {
            // 2
            supportFragmentManager
                // 3
                .beginTransaction()
                // 4
                .add(R.id.fragment_container_view, MainFragment(), "MainFragment")
                // 5
                .commit()
        }


        setContentView(binding.root)
    }
}