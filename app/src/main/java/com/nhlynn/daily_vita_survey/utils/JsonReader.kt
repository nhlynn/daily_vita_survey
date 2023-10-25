package com.nhlynn.daily_vita_survey.utils

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.nhlynn.daily_vita_survey.data.AllergiesVO
import com.nhlynn.daily_vita_survey.data.ConcernVO
import com.nhlynn.daily_vita_survey.data.DietVO


fun loadConcernList(context: Context): ArrayList<ConcernVO> {
    return try {
        val inputStream = context.assets.open("concern.json")
        val json = inputStream.bufferedReader().use { it.readText() }

        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()
        val type = object : TypeToken<List<ConcernVO>>() {}.type
        gson.fromJson(json, type)

    } catch (_: Exception) {
        arrayListOf()
    }
}

fun loadDietList(context: Context): ArrayList<DietVO> {
    return try {
        val inputStream = context.assets.open("diets.json")
        val json = inputStream.bufferedReader().use { it.readText() }

        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()
        val type = object : TypeToken<List<DietVO>>() {}.type
        gson.fromJson(json, type)

    } catch (_: Exception) {
        arrayListOf()
    }
}

fun loadAllergiesList(context: Context): ArrayList<AllergiesVO> {
    return try {
        val inputStream = context.assets.open("allergies.json")
        val json = inputStream.bufferedReader().use { it.readText() }

        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()
        val type = object : TypeToken<List<AllergiesVO>>() {}.type
        gson.fromJson(json, type)

    } catch (_: Exception) {
        arrayListOf()
    }
}