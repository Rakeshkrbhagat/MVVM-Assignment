package com.mobile.mvvm_assignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobile.mvvm_assignment.model.AllCharacters
import com.mobile.mvvm_assignment.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val characterAPIRepository: CharacterRepository) :
    ViewModel() {


    private val charactersData = MutableLiveData<AllCharacters>()
    private val staffData = MutableLiveData<AllCharacters>()
    private val studentsData = MutableLiveData<AllCharacters>()

    val charactersLiveData: LiveData<AllCharacters> = charactersData
    val staffLiveData: LiveData<AllCharacters> = staffData
    val studentsLiveData: LiveData<AllCharacters> = studentsData

    init {
        fetchCharacters()
        fetchStaff()
        fetchStudents()
    }

    fun fetchCharacters() {
        val charactersCall: Call<AllCharacters> = characterAPIRepository.getCharactersList()
        charactersCall.enqueue(object : Callback<AllCharacters> {
            override fun onResponse(call: Call<AllCharacters>, response: Response<AllCharacters>) {
                if (response.isSuccessful) {
                    val charactersList = response.body()
                    charactersList?.let {
                        charactersData.postValue(it)
                    }
                }
            }

            override fun onFailure(call: Call<AllCharacters>, t: Throwable) {
                // Handle failure
            }
        })
    }


    private fun fetchStaff() {
        val charactersCall: Call<AllCharacters> = characterAPIRepository.getStaff()
        charactersCall.enqueue(object : Callback<AllCharacters> {
            override fun onResponse(call: Call<AllCharacters>, response: Response<AllCharacters>) {
                if (response.isSuccessful) {
                    val charactersList = response.body()
                    charactersList?.let {
                        staffData.postValue(it)
                    }
                }
            }

            override fun onFailure(call: Call<AllCharacters>, t: Throwable) {
                // Handle failure
            }
        })
    }

    private fun fetchStudents() {
        val charactersCall: Call<AllCharacters> = characterAPIRepository.getStudents()
        charactersCall.enqueue(object : Callback<AllCharacters> {
            override fun onResponse(call: Call<AllCharacters>, response: Response<AllCharacters>) {
                if (response.isSuccessful) {
                    val charactersList = response.body()
                    charactersList?.let {
                        studentsData.postValue(it)
                    }
                }
            }

            override fun onFailure(call: Call<AllCharacters>, t: Throwable) {
                // Handle failure
            }
        })
    }
}