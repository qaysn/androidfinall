package com.kz.coronastat.network

import com.kz.coronastat.model.CountryDetail
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailApi {

    @GET("country/{slug}")
    fun getCountries(@Path("slug") slug: String) : Call<List<CountryDetail>>

    companion object {

        private var BASE_URL = "https://api.covid19api.com/"

        fun create() : DetailApi {

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(DetailApi::class.java)

        }
    }
}