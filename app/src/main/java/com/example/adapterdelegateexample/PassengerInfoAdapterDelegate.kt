package com.example.adapterdelegateexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adapterdelegateexample.databinding.PassengerInfoLayoutBinding

class PassengerInfoAdapterDelegate(
    private val viewType: Int
) {

    fun getViewType() = viewType

    fun isForViewType(items: List<IBookingDetails>, position: Int) =
        items[position] is PassengerInfo

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        BookingDetailsAdapter.PassengerInfoViewHolder(
            PassengerInfoLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    fun onBindViewHolder(
        items: List<IBookingDetails>,
        position: Int,
        holder: RecyclerView.ViewHolder
    ) {
        val current: PassengerInfo = items[position] as PassengerInfo
        (holder as BookingDetailsAdapter.PassengerInfoViewHolder).bind(current)
    }
}