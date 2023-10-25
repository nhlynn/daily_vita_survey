package com.nhlynn.daily_vita_survey.data

data class DietVO(
    val id: Int? = null,
    val name: String? = null,
    val tool_tip: String? = null,
    var isChecked: Boolean = false
)