package com.mobile.mvvm_assignment.apitest

import android.util.Log
import com.mobile.mvvm_assignment.api.APIService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StudentsTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var studentsAPI: APIService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        studentsAPI = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(APIService::class.java)
    }

    @Test
    fun testStudentsListAndReturnSuccess() {

        val mockResponse = MockResponse()
        mockResponse.setResponseCode(200)
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)

        runBlocking {
            val response = studentsAPI.getStudents().execute()
            mockWebServer.takeRequest()
            assert(response.isSuccessful)
            assert(response.body() != null)
        }
    }

    @Test
    fun testStudentsListAndReturnError() {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(404)
        mockResponse.setBody("Error fetching data from api")
        mockWebServer.enqueue(mockResponse)

        runBlocking {
            val response = studentsAPI.getStudents().execute()
            mockWebServer.takeRequest()
            assert(!response.isSuccessful)
            Assert.assertEquals(404, response.code())
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
