package com.mobile.mvvm_assignment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobile.mvvm_assignment.R
import com.mobile.mvvm_assignment.fragment.CharacterBottomSheetFragment
import com.mobile.mvvm_assignment.model.AllCharactersItem



class CharacterAdapter(private val fragmentManager: FragmentManager) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var characterList: MutableList<AllCharactersItem> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(characters: List<AllCharactersItem>) {
        characterList.clear()
        characterList.addAll(characters)
        notifyDataSetChanged()

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.character_item_list, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characterList[position]
        holder.bind(character)

        holder.itemView.setOnClickListener {
            val bottomSheetFragment = CharacterBottomSheetFragment(character)
            bottomSheetFragment.show(fragmentManager, bottomSheetFragment.tag)
        }
    }

    override fun getItemCount(): Int {
        return characterList.size
    }


    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.tvName)
        private val actorTextView: TextView = itemView.findViewById(R.id.tvActorName)
        private val dobTextView: TextView = itemView.findViewById(R.id.tvDOB)
        private val imageView: ImageView = itemView.findViewById(R.id.ivCharacterImage)


        @SuppressLint("SetTextI18n")
        fun bind(character: AllCharactersItem) {
            nameTextView.text = "Name : ${character.name}"
            actorTextView.text = "Actor Name : ${character.actor}"
            dobTextView.text = "DOB : ${character.dateOfBirth}"

            // Load character image using Picasso (or Glide)
            Glide.with(itemView.context).load(character.image)
                .placeholder(R.drawable.error_image_placeholder).into(imageView)
        }
    }

}



