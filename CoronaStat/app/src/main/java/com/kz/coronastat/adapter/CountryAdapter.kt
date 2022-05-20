package com.kz.coronastat.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.kz.coronastat.DetailFragment
import com.kz.coronastat.OnclickCountry
import com.kz.coronastat.R
import com.kz.coronastat.model.Country


class CountryAdapter(
    var listCountry: List<Country>,
    val listener: OnclickCountry
) : RecyclerView.Adapter<CountryAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var layout: LinearLayout = itemView.findViewById(R.id.main_layout)
        var countryName : TextView = itemView.findViewById(R.id.country_name)
        var slug : TextView = itemView.findViewById(R.id.slug)
        var iso2 : TextView = itemView.findViewById(R.id.iso)

        fun bind(country: Country) {
            countryName.text = country.name
            slug.text = country.slug
            iso2.text = country.iso2

            layout.setOnClickListener {
                listener.goDetail(country.slug)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.e("bind", listCountry[position].toString())
        holder.bind(listCountry[position])
    }

    override fun getItemCount() = listCountry.size
}