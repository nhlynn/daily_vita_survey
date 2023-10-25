package com.nhlynn.daily_vita_survey.delegate

import com.nhlynn.daily_vita_survey.data.DietVO

interface DietDelegate {
    fun setCheckedChange(diet: DietVO)
}