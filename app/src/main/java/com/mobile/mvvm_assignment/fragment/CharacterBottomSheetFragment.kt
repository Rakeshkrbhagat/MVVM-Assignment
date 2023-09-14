package com.mobile.mvvm_assignment.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mobile.mvvm_assignment.R
import com.mobile.mvvm_assignment.model.AllCharactersItem

class CharacterBottomSheetFragment(private var character: AllCharactersItem) :
    BottomSheetDialogFragment() {
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_character_bottom_sheet, container, false)

        // Access and set the views in the bottom sheet layout using character data
        val imageView: ImageView = view.findViewById(R.id.bottomImageView)
        val nameTextView: TextView = view.findViewById(R.id.bottomName)
        val actorTextView: TextView = view.findViewById(R.id.bottomActor)
        val dobTextView: TextView = view.findViewById(R.id.bottomDOB)
        val genderTextView: TextView = view.findViewById(R.id.bottomGender)
        val hairColorTextView: TextView = view.findViewById(R.id.bottomHairColor)
        val yobTextView: TextView = view.findViewById(R.id.bottomYearOfBirth)
        val eyeColorTextView: TextView = view.findViewById(R.id.bottomEyeColor)

        // Load character image using Picasso (or Glide)
        Glide.with(this).load(character.image).placeholder(R.drawable.error_image_placeholder)
            .into(imageView)
        nameTextView.text = "Name : ${character.name}"
        actorTextView.text = "Actor : ${character.actor}"
        dobTextView.text = "DOB : ${character.dateOfBirth}"
        genderTextView.text = "Gender : ${character.gender}"
        hairColorTextView.text = "Hair Color : ${character.hairColour}"
        yobTextView.text = "YOB : ${character.yearOfBirth}"
        eyeColorTextView.text = "Eye Color : ${character.eyeColour}"

        return view
    }
}