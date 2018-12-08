package com.dream.architecturecomponents.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "movie")
data class Movie(

    @PrimaryKey
    var id: Int = 0,

    var title: String = "Sans titre",

    var overview: String = "Inconnu",

    @ColumnInfo(name = "release_date")
    var releaseDate: Date = Date(),

    @ColumnInfo(name = "is_for_adult_only")
    var isForAdultsOnly: Boolean = false

)