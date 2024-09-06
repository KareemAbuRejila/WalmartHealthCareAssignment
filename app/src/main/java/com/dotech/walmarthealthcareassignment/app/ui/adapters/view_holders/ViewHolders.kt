package com.dotech.walmarthealthcareassignment.app.ui.adapters.view_holders

sealed class  ViewHolders(val type:Int) {
    data object COUNTRY_ITEM:ViewHolders(1)
}