package ru.stultus.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.stultus.calculator.databinding.DbFragmentBinding

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, DBLabFragment()).commit()
    }
}