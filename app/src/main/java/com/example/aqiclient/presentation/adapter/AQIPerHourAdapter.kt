package com.example.aqiclient.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aqiclient.data.model.Record
import com.example.aqiclient.databinding.AqiListItemBinding

class AQIPerHourAdapter: RecyclerView.Adapter<AQIPerHourAdapter.AQIViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Record> () {
        override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean {
            return oldItem.siteid == newItem.siteid
        }

        override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AQIViewHolder {
        val binding = AqiListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return AQIViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: AQIViewHolder, position: Int) {
        val record = differ.currentList[position]
        holder.bind(record)
    }

    inner class AQIViewHolder(val binding: AqiListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(record: Record) {
            binding.tvSiteName.text = record.sitename

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(record)
                }
            }
        }
    }

    private var onItemClickListener:((Record) -> Unit )? = null

    fun setOnItemClickListener(listener: (Record) -> Unit) {
        onItemClickListener = listener
    }
}