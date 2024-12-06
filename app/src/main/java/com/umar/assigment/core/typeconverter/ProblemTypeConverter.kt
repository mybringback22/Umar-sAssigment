package com.umar.assigment.core.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.umar.assigment.domain.model.AssociatedDrug

class ProblemTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromAssociatedDrugList(associatedDrug: List<AssociatedDrug>): String {
        return gson.toJson(associatedDrug)
    }

    @TypeConverter
    fun toAssociatedDrugList(data: String): List<AssociatedDrug> {
        val type = object : TypeToken<List<AssociatedDrug>>() {}.type
        return gson.fromJson(data, type)
    }
}