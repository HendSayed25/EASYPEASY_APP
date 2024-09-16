package com.example.eatsygo_app.model

import androidx.room.TypeConverter
import com.google.gson.Gson

class RateConverter {
    @TypeConverter
    fun fromRating(rating: Rating): String {
        return Gson().toJson(rating)
    }

    @TypeConverter
    fun toRating(ratingString: String): Rating {
        return Gson().fromJson(ratingString, Rating::class.java)
    }
}