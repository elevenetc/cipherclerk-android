package com.elevenetc.cipherclerk.android.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elevenetc.cipherclerk.android.R
import com.elevenetc.cipherclerk.android.common.Record

class RecordsAdapter(
    val records: List<Record>,
    val listener: (Record) -> Unit
) : RecyclerView.Adapter<RecordsAdapter.VH>() {

    class VH(view: View) : RecyclerView.ViewHolder(view) {

        val textKey = view.findViewById<TextView>(R.id.text_key)
        val textValue = view.findViewById<TextView>(R.id.text_value)

        fun bind(record: Record) {
            textKey.text = record.key
            textValue.text = record.value
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item_record, parent, false)



        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val record = records[position]
        holder.bind(record)
        holder.itemView.setOnClickListener {
            listener(record)
        }
    }

    override fun getItemCount(): Int {
        return records.size
    }
}