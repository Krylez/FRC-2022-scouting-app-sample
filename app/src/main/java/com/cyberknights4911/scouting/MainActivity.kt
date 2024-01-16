package com.cyberknights4911.scouting

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.cyberknights4911.scouting.compose.Events
import com.cyberknights4911.scouting.databinding.ActivityMainBinding
import com.cyberknights4911.ui.theme.CyberKnightsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initCompose()
    }

    private fun init() {
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)
    }

    private fun initCompose() {
        setContent {
            CyberKnightsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Events()
                }
            }
//            MaterialTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Events()
//                }
//            }
        }
    }
}