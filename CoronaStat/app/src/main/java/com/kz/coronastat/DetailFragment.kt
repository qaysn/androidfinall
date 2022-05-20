package com.kz.coronastat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kz.coronastat.adapter.CountryAdapter
import com.kz.coronastat.adapter.DetailAdapter
import com.kz.coronastat.model.Country
import com.kz.coronastat.model.CountryDetail
import com.kz.coronastat.network.CountryApi
import com.kz.coronastat.network.DetailApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFragment(
    private val slug: String
) : Fragment() {

    lateinit var active: TextView
    lateinit var city: TextView
    lateinit var cityCode: TextView
    lateinit var confirmed: TextView
    lateinit var country: TextView
    lateinit var countryCode: TextView
    lateinit var date: TextView
    lateinit var deaths: TextView
    lateinit var id: TextView
    lateinit var lat: TextView
    lateinit var lon: TextView
    lateinit var province: TextView
    lateinit var recovered: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.country_detail_layout, container, false)

        active = view.findViewById(R.id.active)
        city = view.findViewById(R.id.city)
        cityCode = view.findViewById(R.id.cityCode)
        confirmed = view.findViewById(R.id.confirmed)
        country = view.findViewById(R.id.country)
        countryCode = view.findViewById(R.id.countryCode)
        date = view.findViewById(R.id.date)
        deaths = view.findViewById(R.id.deaths)
        id = view.findViewById(R.id.id)
        lat = view.findViewById(R.id.lat)
        lon = view.findViewById(R.id.lon)
        province = view.findViewById(R.id.province)
        recovered = view.findViewById(R.id.recovered)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDetail()
    }

    fun getDetail() {
        DetailApi.create().getCountries(slug).enqueue(object : Callback<List<CountryDetail>> {
            override fun onResponse(call: Call<List<CountryDetail>>, response: Response<List<CountryDetail>>) {
                if (response.isSuccessful) {
                    response.body()?.last()?.let { setData(it) }
                } else {
                    Toast.makeText(requireContext(), "Api Error", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<List<CountryDetail>>, t: Throwable) {
                Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setData(detail: CountryDetail) {
        active.text = detail.active.toString()
        city.text = detail.city
        cityCode.text = detail.cityCode
        confirmed.text = detail.confirmed.toString()
        country.text = detail.country
        countryCode.text = detail.countryCode
        date.text = detail.date
        deaths.text = detail.deaths.toString()
        id.text = detail.id
        lat.text = detail.lat
        lon.text = detail.lon
        province.text = detail.province
        recovered.text = detail.recovered.toString()
    }


}