package com.darleyleal.consultordecpf.presenter.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.darleyleal.consultordecpf.presenter.app.theme.ConsultorDeCPFTheme
import com.darleyleal.consultordecpf.presenter.screen.HomeScreen
import com.darleyleal.consultordecpf.presenter.viewmodel.ZipCodeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ConsultorDeCPFTheme {
                val viewModel: ZipCodeViewModel by viewModels()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        paddingValues = innerPadding,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}