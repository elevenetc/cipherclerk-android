package com.elevenetc.cipherclerk.android.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elevenetc.cipherclerk.android.R
import com.elevenetc.cipherclerk.android.common.Record
import com.elevenetc.cipherclerk.android.common.RecordsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import kotlin.coroutines.CoroutineContext

class HomeFragment : Fragment(), CoroutineScope {

    private lateinit var homeViewModel: HomeViewModel

    val recordsRepository: RecordsRepository by inject()
    val backScope = CoroutineScope(Dispatchers.IO)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val allRecords = recordsRepository.allRecords

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            //textView.text = it
        })

        root.findViewById<View>(R.id.btn_add_record).setOnClickListener {
            backScope.launch {
                val time = System.currentTimeMillis().toString()
                recordsRepository.insert(Record("id: $time", time))
            }
        }

        val recordsRecycler = root.findViewById<RecyclerView>(R.id.records_recycler)
        recordsRecycler.layoutManager = LinearLayoutManager(requireContext())

        launch {
            allRecords.collect {

                recordsRecycler.adapter = RecordsAdapter(it)
            }
        }

        return root
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}