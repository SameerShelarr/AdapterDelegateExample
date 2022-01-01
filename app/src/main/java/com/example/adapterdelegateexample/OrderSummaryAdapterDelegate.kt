package com.example.adapterdelegateexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adapterdelegateexample.databinding.OrderSummaryLayoutBinding

class OrderSummaryAdapterDelegate(
    private val viewType: Int
) {

    fun getViewType() = viewType

    fun isForViewType(items: List<IBookingDetails>, position: Int) =
        items[position] is OrderSummary

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        BookingDetailsAdapter.OrderSummaryViewHolder(
            OrderSummaryLayoutBinding.inflate(
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
        val current: OrderSummary = items[position] as OrderSummary
        (holder as BookingDetailsAdapter.OrderSummaryViewHolder).bind(current)
    }
}