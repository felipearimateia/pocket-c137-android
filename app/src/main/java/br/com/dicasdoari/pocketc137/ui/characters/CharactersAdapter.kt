package br.com.dicasdoari.pocketc137.ui.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.dicasdoari.pocketc137.data.dto.CharacterDTO
import br.com.dicasdoari.pocketc137.databinding.ItemCharactersBinding
import coil.load

class CharactersAdapter: RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>(){
    private var items: MutableList<CharacterDTO> = arrayListOf()

    var addOrRemoveFavorites: ((CharacterDTO) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCharactersBinding.inflate(layoutInflater, parent, false)
        return CharactersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val character = items[position]
        holder.bind(character)
    }

    fun addAll(elements: Collection<CharacterDTO>) {
        items.clear()
        items.addAll(elements)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    class CharactersViewHolder(private val binding: ItemCharactersBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(dto: CharacterDTO) {
            binding.photo.load(dto.image)
            binding.lblName.text = dto.name
            binding.lblSpecies.text = dto.species
            binding.lblStatus.text = dto.status
        }
    }
}