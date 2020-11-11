package com.elevenetc.cipherclerk.android.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.elevenetc.cipherclerk.android.R
import com.elevenetc.cipherclerk.android.common.RecordsRepository
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    val recordsRepository: RecordsRepository by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val allRecords = recordsRepository.allRecords

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}