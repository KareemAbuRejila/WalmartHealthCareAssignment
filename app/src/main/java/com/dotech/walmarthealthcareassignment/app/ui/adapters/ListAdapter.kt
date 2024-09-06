package com.dotech.walmarthealthcareassignment.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dotech.walmarthealthcareassignment.app.ui.adapters.view_holders.CountryItemViewHolder
import com.dotech.walmarthealthcareassignment.app.ui.adapters.view_holders.ViewHolders
import com.dotech.walmarthealthcareassignment.databinding.ItemCountryBinding
import com.dotech.walmarthealthcareassignment.domain.models.Country

class ListAdapter : RecyclerView.Adapter<ViewHolder>() {
    var countries: List<Country> = emptyList()
    var TYPE:Int=0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

//        return when (TYPE) {
//            ViewHolders.COUNTRY_ITEM.type -> {
//                val itemBinding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context))
//                CountryItemViewHolder(itemBinding)
//            }
//
//            else -> {
//                //TODO: do for another Item
//            }
//        }
        val itemBinding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CountryItemViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (TYPE){
            ViewHolders.COUNTRY_ITEM.type -> (holder as CountryItemViewHolder).bind(country = countries[position])
        }
    }
    fun updateCountries(list: List<Country>){
        this.countries = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return countries.size
    }


}