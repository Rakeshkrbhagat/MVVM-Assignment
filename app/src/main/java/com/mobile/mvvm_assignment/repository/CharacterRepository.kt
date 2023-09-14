package com.mobile.mvvm_assignment.repository


import com.mobile.mvvm_assignment.api.APIService
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val apiService: APIService
) {
    fun getCharactersList() = apiService.getCharactersList()
    fun getStudents() = apiService.getStudents()
    fun getStaff() = apiService.getStaff()

}
