package com.mobile.mvvm_assignment.api

import com.mobile.mvvm_assignment.model.AllCharacters
import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("characters")
    fun getCharactersList(): Call<AllCharacters>

    @GET("characters/students")
    fun getStudents(): Call<AllCharacters>

    @GET("characters/staff")
    fun getStaff(): Call<AllCharacters>
}