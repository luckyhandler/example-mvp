package de.handler.mobile.pokemoncards

import de.handler.mobile.core.CardProvider
import de.handler.mobile.core.PokemonCard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

object Repository : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    private val cardProvider = CardProvider()
    private val allCards = mutableMapOf<String, PokemonCard>()

    fun getAllPokemonCardsAsync(forceReload: Boolean = false): Deferred<List<PokemonCard?>> {
        return async {
            return@async if (forceReload || allCards.isEmpty()) {
                val pokemonCardWrapper = cardProvider.getPokemonCardsAsync().await()

                pokemonCardWrapper?.cards?.forEach { card ->
                    card.id?.let { allCards[it] = card }
                }

                pokemonCardWrapper?.cards.orEmpty()
            } else {
                allCards.values.toList()
            }
        }
    }

    fun getPokemonCard(id: String): Deferred<PokemonCard?> {
        return async {
            return@async if (allCards.contains(id)) {
                allCards[id]
            } else {
                null
            }
        }
    }
}