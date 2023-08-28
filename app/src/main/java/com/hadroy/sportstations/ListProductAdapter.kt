package com.hadroy.sportstations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hadroy.sportstations.databinding.ItemRowProductBinding

class ListProductAdapter(private val listProducts: ArrayList<Product>): RecyclerView.Adapter<ListProductAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemRowProductBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listProducts.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, price, highlight, feature, number, photo) = listProducts[position]

        holder.binding.tvItemName.text = name
        holder.binding.tvItemPrice.text = price
        holder.binding.tvItemHighlight.text = highlight
        holder.binding.imgItemPhoto.setImageResource(photo)

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listProducts[holder.adapterPosition]) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Product)
    }
}