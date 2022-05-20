package com.kz.coronastat.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kz.coronastat.R
import com.kz.coronastat.model.CountryDetail

class DetailAdapter(
    var listCountryDetail: List<CountryDetail>
) : RecyclerView.Adapter<DetailAdapter.MyViewHolder>() {

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        var active : TextView = itemView.findViewById(R.id.active)
        var city : TextView = itemView.findViewById(R.id.city)
        var cityCode : TextView = itemView.findViewById(R.id.cityCode)
        var confirmed : TextView = itemView.findViewById(R.id.confirmed)
        var country : TextView = itemView.findViewById(R.id.country)
        var countryCode : TextView = itemView.findViewById(R.id.countryCode)
        var date : TextView = itemView.findViewById(R.id.date)
        var deaths : TextView = itemView.findViewById(R.id.deaths)
        var id : TextView = itemView.findViewById(R.id.id)
        var lat : TextView = itemView.findViewById(R.id.lat)
        var lon : TextView = itemView.findViewById(R.id.lon)
        var province : TextView = itemView.findViewById(R.id.province)
        var recovered : TextView = itemView.findViewById(R.id.recovered)

        fun bind(countryDetail: CountryDetail) {
            active.text = countryDetail.active.toString()
            city.text = countryDetail.city
            cityCode.text = countryDetail.cityCode
            confirmed.text = countryDetail.confirmed.toString()
            country.text = countryDetail.country
            countryCode.text = countryDetail.countryCode
            date.text = countryDetail.date
            deaths.text = countryDetail.deaths.toString()
            id.text = countryDetail.id
            lat.text = countryDetail.lat
            lon.text = countryDetail.lon
            province.text = countryDetail.province
            recovered.text = countryDetail.recovered.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_detail_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listCountryDetail[position])
    }

    override fun getItemCount() = listCountryDetail.size
}