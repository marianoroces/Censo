package com.marianoroces.norris.tp3.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Pie
import com.anychart.enums.Align
import com.anychart.enums.LegendLayout
import com.marianoroces.norris.tp3.R
import com.marianoroces.norris.tp3.viewmodel.PersonViewModel

class DistributionFragment : Fragment() {

    lateinit var chartView: AnyChartView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_distribution, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val personVM: PersonViewModel = ViewModelProvider(this).get(PersonViewModel::class.java)

        initializeElements()
        setupPieChart(personVM.getDistribution(activity?.applicationContext!!))
    }

    private fun initializeElements() {
        chartView = view?.findViewById(R.id.fd_chart_view)!!
    }

    private fun setupPieChart(lista:Array<Int>) {

        val types = arrayOf("Hombres", "Mujeres")
        val pie: Pie = AnyChart.pie()
        val dataEntry: ArrayList<DataEntry> = ArrayList()

        dataEntry.add(ValueDataEntry(types[0], lista[0]))
        dataEntry.add(ValueDataEntry(types[1], lista[1]))

        pie.data(dataEntry)
        pie.legend()
            .position("center-bottom")
            .itemsLayout(LegendLayout.HORIZONTAL)
            .align(Align.CENTER)
        pie.labels().position("outside")
        pie.connectorStroke("2 black .7")
        pie.height(500)
        chartView.setChart(pie)
    }
}