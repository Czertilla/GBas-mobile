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
import com.czertilla.gbas.databinding.ItemServiceCardBinding
import com.czertilla.gbas.domain.model.ServiceCard
import com.google.android.material.button.MaterialButton
import com.czertilla.gbas.domain.diff.ServiceDiffCallback

class ServiceAdapter (
    private val onClick: (ServiceCard) -> Unit
) :
    ListAdapter<ServiceCard, ServiceAdapter.ServiceViewHolder>(ServiceDiffCallback()) {

     class ServiceViewHolder(
        private val binding: ItemServiceCardBinding,
        private val onClick: (ServiceCard) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(service: ServiceCard) {
            binding.txtServiceCard.text = service.name
            binding.btnServiceCard.text = "${service.pricePerDay} руб./сут."
            Glide.with(binding.imgServiceCard)
                .load(service.imageUrl)
                .error(R.drawable.ic_menu_gallery)
                .centerCrop()
                .into(binding.imgServiceCard)

            binding.root.setOnClickListener { onClick(service) }
        }

        companion object {
            fun create(
                parent: ViewGroup,
                onClick: (ServiceCard) -> Unit
            ): ServiceViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemServiceCardBinding.inflate(inflater, parent, false)
                return ServiceViewHolder(binding, onClick)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        return ServiceViewHolder.create(parent, onClick)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}