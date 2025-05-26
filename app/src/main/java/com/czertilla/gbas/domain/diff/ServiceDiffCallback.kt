package com.czertilla.gbas.domain.diff

import androidx.recyclerview.widget.DiffUtil
import com.czertilla.gbas.domain.model.ServiceCard

class ServiceDiffCallback : DiffUtil.ItemCallback<ServiceCard>() {
    override fun areItemsTheSame(oldItem: ServiceCard, newItem: ServiceCard): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ServiceCard, newItem: ServiceCard): Boolean {
        return oldItem == newItem
    }
}