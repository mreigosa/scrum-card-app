package com.mreigar.scrumpoker.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mreigar.scrumpoker.databinding.ItemCardBinding
import com.mreigar.scrumpoker.model.Card

class CardAdapter(
    private val listener: CardClickListener
) : ListAdapter<Card, CardAdapter.CardViewHolder>(

    object : DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean = oldItem == newItem
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CardViewHolder(ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) = holder.bind(getItem(position), listener)

    class CardViewHolder(private val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Card, cardClickListener: CardClickListener) = with(binding) {
            card = item
            listener = cardClickListener
            executePendingBindings()
        }
    }

    interface CardClickListener {
        fun onCardClick(card: Card)
    }
}