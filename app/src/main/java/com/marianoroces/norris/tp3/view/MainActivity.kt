package com.marianoroces.norris.tp3.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.marianoroces.norris.tp3.R

class MainActivity : AppCompatActivity() {

    lateinit var reports:Button
    lateinit var statistics:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initilizeElements()

        statistics.setOnClickListener{
            startActivity(Intent(this, StatisticsActivity::class.java))
        }

        reports.setOnClickListener{
            startActivity(Intent(this, ReportActivity::class.java))
        }

        //TODO: IMPLEMENTAR OTRA ACTIVIDAD PARA MODIFICAR PERSONAS
    }

    private fun initilizeElements(){
        reports = findViewById(R.id.m_reports)
        statistics = findViewById(R.id.m_statistics)
    }
}