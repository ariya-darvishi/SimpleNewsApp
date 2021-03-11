package com.example.simplenewsapp.data.remote

import androidx.room.TypeConverter
import com.example.simplenewsapp.data.local.models.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }


}