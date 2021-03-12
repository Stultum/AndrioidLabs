package ru.stultus.calculator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import ru.stultus.calculator.databinding.ActivitySecondLabBinding
import ru.stultus.calculator.secondlab.FirstScreenFragment
import ru.stultus.calculator.secondlab.SecondScreenFragment
import ru.stultus.calculator.secondlab.ThirdScreenFragment
import java.net.URL

class SecondLabActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondLabBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
//        Log.d("inertq", intent.extras?.getString("is").toString())
        binding = ActivitySecondLabBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, FirstScreenFragment()).commit()
        with(binding) {
            tabSelector.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.text) {
                        "1 экран" -> {
                                supportFragmentManager.beginTransaction()
                                    .replace(R.id.container, FirstScreenFragment()).commit()
                        }
                        "2 экран" -> {
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.container, SecondScreenFragment()).commit()
                        }
                        "3 экран" -> {
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.container, ThirdScreenFragment()).commit()
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }
            })

            if(intent.extras?.getBoolean("is") == true){
                tabSelector.selectTab(tabSelector.getTabAt(1))
            }

        }
    }
}