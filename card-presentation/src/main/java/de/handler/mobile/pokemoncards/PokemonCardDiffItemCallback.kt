package de.handler.mobile.pokemoncards

import androidx.recyclerview.widget.DiffUtil
import de.handler.mobile.core.PokemonCard

class PokemonCardDiffItemCallback : DiffUtil.ItemCallback<PokemonCard?>() {
    override fun areItemsTheSame(oldItem: PokemonCard, newItem: PokemonCard): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: PokemonCard, newItem: PokemonCard): Boolean = oldItem == newItem
}