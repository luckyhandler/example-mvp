package de.handler.mobile.pokemoncards


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import de.handler.mobile.core.PokemonCard

class MainActivity : AppCompatActivity(), Contract {

    private lateinit var presenter: Presenter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        val adapter = PokemonAdapter { pokemonCard ->
            Toast.makeText(this@MainActivity, "${pokemonCard?.name} clicked", Toast.LENGTH_LONG).show()
        }
        recyclerView.adapter = adapter

        presenter = Presenter(this, Repository)
        presenter.loadPokemonCards()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_filter -> presenter.sortList()
        else -> super.onOptionsItemSelected(item)
    }

    override fun onDisplayPokemonCards(pokemonCards: List<PokemonCard>) {
        runOnUiThread {
            (recyclerView.adapter as? PokemonAdapter)?.submitList(pokemonCards)
        }
    }
}
