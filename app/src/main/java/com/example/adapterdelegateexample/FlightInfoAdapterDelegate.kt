package com.example.adapterdelegateexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adapterdelegateexample.databinding.FlightInfoLayoutBinding

class FlightInfoAdapterDelegate(
    private val viewType: Int
) {

    fun getViewType() = viewType

    fun isForViewType(items: List<IBookingDetails>, position: Int) =
        items[position] is FlightInfo

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        BookingDetailsAdapter.FlightInfoViewHolder(
            FlightInfoLayoutBinding.inflate(
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
        val current: FlightInfo = items[position] as FlightInfo
        (holder as BookingDetailsAdapter.FlightInfoViewHolder).bind(current)
    }
}