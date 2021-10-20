package com.example.recyclerviewfromapi

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIInterface {
    @GET("/people/")
    fun doGetListResources(): Call<List<PeopleDetails.Datum>>

}