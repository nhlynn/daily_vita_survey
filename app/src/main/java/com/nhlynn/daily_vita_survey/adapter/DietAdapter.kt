package com.nhlynn.daily_vita_survey.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nhlynn.daily_vita_survey.data.DietVO
import com.nhlynn.daily_vita_survey.databinding.DietItemBinding
import com.nhlynn.daily_vita_survey.delegate.DietDelegate
import com.nhlynn.daily_vita_survey.view_holder.DietViewHolder

class DietAdapter(private val delegate: DietDelegate) :
    RecyclerView.Adapter<DietViewHolder>() {
    private var dietList = arrayListOf<DietVO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DietViewHolder {
        val binding = DietItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DietViewHolder(binding, delegate)
    }

    override fun getItemCount(): Int {
        return dietList.size
    }

    override fun onBindViewHolder(holder: DietViewHolder, position: Int) {
        val item = dietList[position]
        holder.bindData(item)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(dietList: ArrayList<DietVO>) {
        this.dietList = dietList
        notifyDataSetChanged()
    }
}