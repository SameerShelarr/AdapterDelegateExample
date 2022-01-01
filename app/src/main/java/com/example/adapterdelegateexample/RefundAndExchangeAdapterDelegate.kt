package com.example.adapterdelegateexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adapterdelegateexample.databinding.RefundAndExchangeLayoutBinding

class RefundAndExchangeAdapterDelegate(
    private val viewType: Int
) {

    fun getViewType() = viewType

    fun isForViewType(items: List<IBookingDetails>, position: Int) =
        items[position] is RefundAndExchange

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        BookingDetailsAdapter.RefundAndExchangeViewHolder(
            RefundAndExchangeLayoutBinding.inflate(
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
        val current: RefundAndExchange = items[position] as RefundAndExchange
        (holder as BookingDetailsAdapter.RefundAndExchangeViewHolder).bind(current)
    }
}