package com.example.probashop.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.probashop.data.ShopListRepositoryImpl
import com.example.probashop.domain.DeleteShopItemUseCase
import com.example.probashop.domain.EditShopItemUseCase
import com.example.probashop.domain.GetShopListUseCase
import com.example.probashop.domain.ShopItem

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = MutableLiveData<List<ShopItem>>()

    fun getShopList() {
        val list = getShopListUseCase.getShopList()
        shopList.value = list
    }
    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
        getShopList()
    }

    fun changeEnableState(shopItem: ShopItem) {
        val editedShopItem = shopItem.copy(enable = !shopItem.enable)
        editShopItemUseCase.editShopItem(editedShopItem)
        getShopList()
    }
}