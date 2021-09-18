package com.marianoroces.norris.tp3.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marianoroces.norris.tp3.R
import com.marianoroces.norris.tp3.adapter.FullPersonAdapter
import com.marianoroces.norris.tp3.viewmodel.PersonViewModel

class PeopleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_people, container, false)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val recyclerView: RecyclerView = view.findViewById(R.id.fp_recycler)
        val personVM: PersonViewModel = ViewModelProvider(this).get(PersonViewModel::class.java)

        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        recyclerView.adapter = FullPersonAdapter(personVM.getAll(activity?.applicationContext!!), personVM, activity?.applicationContext!!)
    }
}