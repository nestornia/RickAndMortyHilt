package com.example.interview.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.interview.databinding.ItemCharacterBinding
import com.example.interview.model.Character
import com.example.interview.util.extensions.inflater
import com.example.interview.util.extensions.listen

class CharacterAdapter(
    private val listener: (Character) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private val characters = mutableListOf<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        ItemCharacterBinding.inflate(parent.inflater, parent, false).let {
            CharacterViewHolder(it).listen { position -> listener.invoke(characters[position]) }
        }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.loadCharacter(characters[position])
    }

    override fun getItemCount() = characters.size

    fun loadCharacters(allCharacters: List<Character>) {
        characters.clear()
        characters.addAll(allCharacters)
        notifyDataSetChanged()
    }

    class CharacterViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun loadCharacter(character: Character) = with(binding) {
            tvName.text = character.name
        }
    }
}
