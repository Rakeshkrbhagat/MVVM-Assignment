package com.mobile.mvvm_assignment.apitest

import com.mobile.mvvm_assignment.api.APIService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.mockwebserver.MockResponse
import org.junit.After
import org.junit.Assert
import org.junit.Test

class StaffTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var staffAPI: APIService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        staffAPI = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(APIService::class.java)
    }

    @Test
    fun testStaffListAndReturnSuccess() {

        val mockResponse = MockResponse()
        mockResponse.setResponseCode(200)
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)

        runBlocking {
            val response = staffAPI.getStaff().execute()
            mockWebServer.takeRequest()
            assert(response.isSuccessful)
            assert(response.body() != null)
        }
    }

    @Test
    fun testStaffDataAndReturnError() {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(404)
        mockResponse.setBody("Error fetching data from api")
        mockWebServer.enqueue(mockResponse)

        runBlocking {
            val response = staffAPI.getStaff().execute()
            mockWebServer.takeRequest()
            assert(!response.isSuccessful)
            Assert.assertEquals(404, response.code())
        }
    }

    @After
    fun tearDown() {
        // Shutdown the mock web server
        mockWebServer.shutdown()
    }
}

