package com.kz.coronastat.network

import com.kz.coronastat.model.Country
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CountryApi {

    @GET("countries")
    fun getCountries() : Call<List<Country>>

    companion object {

        private var BASE_URL = "https://api.covid19api.com/"

        fun create() : CountryApi {

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(CountryApi::class.java)

        }
    }
}