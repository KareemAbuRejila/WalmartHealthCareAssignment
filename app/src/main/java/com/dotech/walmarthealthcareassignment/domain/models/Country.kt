package com.dotech.walmarthealthcareassignment.domain.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "country_table")
data class Country(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "code") val code: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "capital") val capital: String,
    @ColumnInfo(name = "region") val region: String
) : Parcelable {
    fun compare(country: Country) = when {
        this == country -> true
        code != country.code -> false
        name != country.name -> false
        capital != country.capital -> false
        region != country.region -> false
        else -> true
    }
}
