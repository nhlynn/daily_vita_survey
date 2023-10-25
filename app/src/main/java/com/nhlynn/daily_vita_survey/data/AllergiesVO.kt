package com.nhlynn.daily_vita_survey.data

data class AllergiesVO(
    val id: Int,
    val name: String
) {
    override fun toString(): String {
        return name
    }
}
