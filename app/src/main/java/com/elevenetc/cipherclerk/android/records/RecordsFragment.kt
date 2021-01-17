package com.elevenetc.cipherclerk.android.records

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elevenetc.cipherclerk.android.R
import com.elevenetc.cipherclerk.android.common.Record
import com.elevenetc.cipherclerk.android.common.RecordsRepository
import com.elevenetc.cipherclerk.android.details.RecordDetailsFragment
import com.elevenetc.cipherclerk.android.navigation.Navigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import kotlin.coroutines.CoroutineContext

class RecordsFragment : Fragment(), CoroutineScope {

    val recordsRepository: RecordsRepository by inject()
    val navigator: Navigator by inject()

    val backScope = CoroutineScope(Dispatchers.IO)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val allRecords = recordsRepository.allRecords

        view.findViewById<View>(R.id.btn_add_record).setOnClickListener {
            backScope.launch {
                val time = System.currentTimeMillis().toString()
                recordsRepository.insert(Record("id: $time", time))
            }
        }

        val recordsRecycler = view.findViewById<RecyclerView>(R.id.records_recycler)
        recordsRecycler.layoutManager = LinearLayoutManager(requireContext())

        launch {
            allRecords.collect { records ->
                recordsRecycler.adapter = RecordsAdapter(records) { selectedRecord ->
                    openDetails(selectedRecord)
                }
            }
        }
    }

    private fun openDetails(record: Record) {
        val fragment = RecordDetailsFragment.create(record.id)
        navigator.addRootScreen(fragment, true)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}