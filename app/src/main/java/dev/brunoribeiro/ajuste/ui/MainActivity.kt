package dev.brunoribeiro.ajuste.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.brunoribeiro.ajuste.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTheme(R.style.Theme_Ajuste)
    }
}