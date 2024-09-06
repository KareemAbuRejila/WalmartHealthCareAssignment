package com.dotech.walmarthealthcareassignment.app.ui.adapters.view_holders

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dotech.walmarthealthcareassignment.databinding.ItemCountryBinding
import com.dotech.walmarthealthcareassignment.domain.models.Country

class CountryItemViewHolder(private val itemCountryBinding: ItemCountryBinding) :
    ViewHolder(itemCountryBinding.root) {
    fun bind(country: Country){
        itemCountryBinding.country = country
    }
}