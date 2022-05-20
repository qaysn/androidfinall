package com.kz.coronastat.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("Country")
    val name: String,
    @SerializedName("Slug")
    val slug: String,
    @SerializedName("ISO2")
    val iso2: String
)
