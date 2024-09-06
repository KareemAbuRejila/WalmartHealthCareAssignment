package com.dotech.walmarthealthcareassignment.data.models

import com.dotech.walmarthealthcareassignment.domain.models.Country
import com.google.gson.annotations.SerializedName

data class CountryDto(
    @SerializedName("capital") val capital: String,
    @SerializedName("code") val code: String,
    @SerializedName("currency") val currency: Currency,
    @SerializedName("flag") val flag: String,
    @SerializedName("language") val language: Language,
    @SerializedName("name") val name: String,
    @SerializedName("region") val region: String
) {
    fun toCountry() = Country(
        name = name,
        region = region,
        code = code,
        capital = capital
    )
}