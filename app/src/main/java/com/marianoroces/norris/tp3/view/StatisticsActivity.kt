package com.marianoroces.norris.tp3.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.marianoroces.norris.tp3.R
import com.marianoroces.norris.tp3.adapter.ViewPagerAdapter

class StatisticsActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var viewPager: ViewPager2
    lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        initializeElements()
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewPagerAdapter: ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "PERSONAS"
                1 -> tab.text = "DESEMPLEADOS"
                2 -> tab.text = "POBREZA"
                3 -> tab.text = "DISTRIBUCION"
            }
        }.attach()


    }

    private fun initializeElements() {
        toolbar = findViewById(R.id.s_toolbar)
        viewPager = findViewById(R.id.s_view_pager)
        tabLayout = findViewById(R.id.s_tab_layout)
    }
}