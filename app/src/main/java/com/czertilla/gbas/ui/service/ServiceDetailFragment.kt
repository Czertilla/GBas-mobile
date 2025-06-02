package com.czertilla.gbas.ui.service

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.czertilla.gbas.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ServiceDetailFragment : Fragment(R.layout.fragment_service_page) {

    private val args: ServiceDetailFragmentArgs by navArgs()
    private val viewModel: ServiceViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.servicePage.observe(viewLifecycleOwner) { service ->
            if (service == null) {
                Toast.makeText(requireContext(), "Ошибка загрузки услуги", Toast.LENGTH_SHORT).show()
                return@observe
            }
            view.findViewById<TextView>(R.id.txt_price).text = "${service?.price} руб./сут."
            view.findViewById<TextView>(R.id.txt_description).text = service.description

            Glide.with(this)
                .load(service.imageUrl)
                .into(view.findViewById(R.id.img_service))

            // Автор
            view.findViewById<TextView>(R.id.txt_author).text = service.author.displayName
            Glide.with(this)
                .load(service.author.photoUrl)
                .circleCrop()
                .into(view.findViewById(R.id.img_author))
        }

        viewModel.bindServiceById(args.serviceId)
    }
}