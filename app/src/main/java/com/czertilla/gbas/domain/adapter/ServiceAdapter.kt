package com.czertilla.gbas.domain.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.czertilla.gbas.R
import com.czertilla.gbas.domain.model.ServiceCard
import com.google.android.material.button.MaterialButton
import com.czertilla.gbas.domain.diff.ServiceDiffCallback

class ServiceAdapter :
    ListAdapter<ServiceCard, ServiceAdapter.ServiceViewHolder>(ServiceDiffCallback()) {

    inner class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.img_service_card)
        private val name: TextView = itemView.findViewById(R.id.txt_service_card)
        private val price: MaterialButton = itemView.findViewById(R.id.btn_service_card)

        fun bind(item: ServiceCard) {
            Glide.with(itemView.context)
                .load(item.imageUrl)
                .error(R.drawable.ic_menu_gallery)
                .centerCrop()
                .into(image)
            name.text = item.name
            price.text = item.pricePerDay.toString() + " руб./сут"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_service_card, parent, false)
        return ServiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}