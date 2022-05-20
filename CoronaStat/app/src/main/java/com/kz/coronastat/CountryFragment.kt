package com.kz.coronastat


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kz.coronastat.adapter.CountryAdapter
import com.kz.coronastat.model.Country
import com.kz.coronastat.network.CountryApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CountryFragment : Fragment(), OnclickCountry {

    private lateinit var recyclerView: RecyclerView
    private lateinit var countryAdapter: CountryAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_country, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        countryAdapter = CountryAdapter(emptyList(), this)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = countryAdapter
        getCountries()
        return view
    }

    private fun getCountries() {
        CountryApi.create().getCountries().enqueue(object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                if (response.isSuccessful) {
                    updateData(response.body())
                } else {
                    Toast.makeText(requireContext(), "Api Error", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_SHORT).show()
            }

        })
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(countries: List<Country>?) {
        countries?.let {
            countryAdapter.listCountry = it
            countryAdapter.notifyDataSetChanged()
        }
    }

    override fun goDetail(slug: String) {
        val detail = DetailFragment(slug)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, detail)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}