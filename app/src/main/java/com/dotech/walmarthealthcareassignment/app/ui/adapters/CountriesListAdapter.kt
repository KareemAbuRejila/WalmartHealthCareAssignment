package com.dotech.walmarthealthcareassignment.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dotech.walmarthealthcareassignment.app.ui.adapters.view_holders.CountryItemViewHolder
import com.dotech.walmarthealthcareassignment.databinding.ItemCountryBinding
import com.dotech.walmarthealthcareassignment.domain.models.Country

class CountriesListAdapter(
    private val onItemClicked:(country: Country)->Unit
) : ListAdapter<Country,ViewHolder>(CountryItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return when (viewType) {
            COUNTRY_ITEM -> {
                val itemBinding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                CountryItemViewHolder(itemBinding,onItemClicked)
            }

            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (val item =getItem(position)){
            is Country -> (holder as CountryItemViewHolder).bind(country = item)
        }
    }

    override fun getItemViewType(position: Int) =
        when(getItem(position)){
            is Country -> COUNTRY_ITEM
            else -> 0
        }


    class CountryItemCallback:
        DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country) = oldItem.code == newItem.code

        override fun areContentsTheSame(oldItem: Country, newItem: Country) = oldItem.compare(newItem)

    }

    companion object{
        const val COUNTRY_ITEM = 1
    }

}