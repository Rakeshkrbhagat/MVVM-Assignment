package com.mobile.mvvm_assignment.apitest

import com.mobile.mvvm_assignment.api.APIService
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharacterTest {


    private lateinit var mockWebServer: MockWebServer
    private lateinit var characterApi: APIService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        characterApi = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(APIService::class.java)
    }

    @Test
    fun testCharactersListAndReturnSuccess() {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(200)
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)

        runBlocking {
            val response = characterApi.getCharactersList().execute()
            mockWebServer.takeRequest()

            assert(response.isSuccessful)
            assert(response.body() != null)
        }
    }

    @Test
    fun testCharactersListAndReturnError() {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(404)
        mockResponse.setBody("Error fetching data from api")
        mockWebServer.enqueue(mockResponse)

        runBlocking {
            val response = characterApi.getCharactersList().execute()
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