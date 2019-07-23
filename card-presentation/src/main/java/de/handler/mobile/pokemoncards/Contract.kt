package de.handler.mobile.pokemoncards

import de.handler.mobile.core.PokemonCard

interface Contract {
    fun onDisplayPokemonCards(pokemonCards: List<PokemonCard>)
}