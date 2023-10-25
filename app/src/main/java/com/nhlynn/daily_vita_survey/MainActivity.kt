package com.nhlynn.daily_vita_survey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.nhlynn.daily_vita_survey.adapter.VitaViewPagerAdapter
import com.nhlynn.daily_vita_survey.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var mVitaViewPagerAdapter: VitaViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        renderUI()
    }

    private fun renderUI() {
        binding.viewPager.isUserInputEnabled = false
        mVitaViewPagerAdapter = VitaViewPagerAdapter(this)
        binding.viewPager.adapter = mVitaViewPagerAdapter


        watchViewPager()
    }

    private fun watchViewPager() {
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    1 -> {
                        binding.progressBar.progress = 25
                    }

                    2 -> {
                        binding.progressBar.progress = 50
                    }

                    3 -> {
                        binding.progressBar.progress = 75
                    }

                    4 -> {
                        binding.progressBar.progress = 100
                    }

                    else -> {
                        binding.progressBar.progress = 0
                    }
                }
            }
        })
    }

}