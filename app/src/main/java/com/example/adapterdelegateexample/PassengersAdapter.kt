package com.example.adapterdelegateexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adapterdelegateexample.databinding.PassengerLayoutBinding

class PassengersAdapter(
    private val list: List<Passenger>
) : RecyclerView.Adapter<PassengersAdapter.PassengerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassengerViewHolder =
        PassengerViewHolder(
            PassengerLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PassengerViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    class PassengerViewHolder(private val binding: PassengerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(passenger: Passenger) {
            binding.apply {
                passengerNameText.text = passenger.name
                documentNoText.text = passenger.documentNumber
            }
        }
    }
}