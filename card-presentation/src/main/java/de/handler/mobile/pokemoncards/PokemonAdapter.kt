package de.handler.mobile.pokemoncards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.handler.mobile.core.PokemonCard
import de.handler.mobile.pokemoncards.PokemonAdapter.PokemonCardViewHolder

class PokemonAdapter(
    private val onPokemonCardClickedAction: ((PokemonCard?) -> Unit)? = null
) : androidx.recyclerview.widget.ListAdapter<PokemonCard, PokemonCardViewHolder>(PokemonCardDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonCardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon_card, parent, false)
        return PokemonCardViewHolder(view, onPokemonCardClickedAction)
    }

    override fun onBindViewHolder(holder: PokemonCardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PokemonCardViewHolder(
        itemView: View,
        private val onPokemonCardClickedAction: ((PokemonCard?) -> Unit)?
    ) : RecyclerView.ViewHolder(itemView) {
        private val logoImageView = itemView.findViewById<ImageView>(R.id.logo_image_view)
        private val nameTextView =
            itemView.findViewById<TextView>(R.id.name_text_view)
        private val superTypeTextView =
            itemView.findViewById<TextView>(R.id.super_type_text_view)
        private val subTypeTextView =
            itemView.findViewById<TextView>(R.id.sub_type_text_view)


        fun bind(card: PokemonCard?) {
            itemView.setOnClickListener {
                card?.let { onPokemonCardClickedAction?.invoke(it) }
            }

            logoImageView.loadUrl(card?.imageUrl)
            nameTextView.text = card?.name
            superTypeTextView.text = card?.supertype
            subTypeTextView.text = card?.subtype
        }
    }
}
