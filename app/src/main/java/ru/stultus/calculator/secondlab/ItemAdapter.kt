package ru.stultus.calculator.secondlab

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.stultus.calculator.R

class ItemAdapter(private val dataSet: Metall) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewCode: TextView = view.findViewById(R.id.code)
        val textViewDate: TextView = view.findViewById(R.id.date)
        val textViewBuy: TextView = view.findViewById(R.id.buy)
        val textViewSell: TextView = view.findViewById(R.id.sell)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewCode.text = dataSet.list[position].code
        holder.textViewDate.text = dataSet.list[position].date
        holder.textViewBuy.text = dataSet.list[position].buy
        holder.textViewSell.text = dataSet.list[position].sell
    }

    override fun getItemCount(): Int = dataSet.list.size

}