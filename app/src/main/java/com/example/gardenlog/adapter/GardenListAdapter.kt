package com.example.gardenlog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gardenlog.R
import com.example.gardenlog.model.Garden

class GardenListAdapter(private val onItemClick: (Int) -> Unit) : androidx.recyclerview.widget.ListAdapter<Garden, GardenListAdapter.GardenViewHolder>(PLANT_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GardenViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)

        return GardenViewHolder.create(itemView, onItemClick)    }

    override fun onBindViewHolder(holder: GardenViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.gardenName)
    }

    class GardenViewHolder(itemView: View, onItemClick: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val gardenItemView: TextView = itemView.findViewById(R.id.nameView)

        init {
            itemView.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }

        fun bind(text: String?) {
            gardenItemView.text = text
        }

        companion object {
            fun create(itemView: View, onItemClick: (Int) -> Unit): GardenViewHolder {
                return GardenViewHolder(itemView, onItemClick)
            }
        }
    }


    companion object {
        private val PLANT_COMPARATOR = object : DiffUtil.ItemCallback<Garden>() {
            override fun areItemsTheSame(oldItem: Garden, newItem: Garden): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Garden, newItem: Garden): Boolean {
                return oldItem.gardenName == newItem.gardenName
            }
        }
    }
}