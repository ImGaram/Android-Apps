package com.example.rxjavaapplication.example3.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import com.example.rxjavaapplication.R
import com.example.rxjavaapplication.example3.Util
import com.example.rxjavaapplication.example3.model.CountryModel

class CountryListAdapter(private val countryList:MutableList<CountryModel>): RecyclerView.Adapter<CountryListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CountryListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_countries, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryListAdapter.ViewHolder, position: Int) {
        holder.bind(countryList[position])
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateCountries(newCountries: MutableList<CountryModel>) {
        countryList.clear()
        countryList.addAll(newCountries)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val countryImage: ImageView = itemView.findViewById(R.id.imageView)
        private val countryName: TextView = itemView.findViewById(R.id.name)
        private val countryCapital: TextView = itemView.findViewById(R.id.capital)

        fun bind(countryModel: CountryModel) {
            countryName.text = countryModel.countryName
            countryCapital.text = countryModel.capital
            val util = Util()
            util.loadImage(countryImage, countryModel.flag, util.getProgressDrawable(countryImage.context))
        }

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}