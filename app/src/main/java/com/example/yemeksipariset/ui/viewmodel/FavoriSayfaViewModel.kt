package com.example.yemeksipariset.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yemeksipariset.data.entity.Favoriler
import com.example.yemeksipariset.data.entity.Yemekler
import com.example.yemeksipariset.data.repo.FavorilerRepostory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriSayfaViewModel @Inject constructor(private val favorilerRepo : FavorilerRepostory) : ViewModel() {



    val favoriListesi = MutableLiveData<List<Favoriler>>()

    fun favoriYukle(){
        viewModelScope.launch {
            favoriListesi.value = favorilerRepo.getFavoriler()
            Log.d("favori yükle","favoriler: ${favoriListesi.value}")
        }
    }

    fun favoriEkle(yemek:Favoriler){
        viewModelScope.launch {
            favorilerRepo.favoriEkle(yemek)
            favoriYukle()
        }
    }

    fun favoriSil(yemek:Favoriler){
        viewModelScope.launch {
            favorilerRepo.favoriSil(yemek)
            favoriYukle()
        }
    }

    fun favoriKontrolEt(id:Int,onSonuc: (Boolean) -> Unit){
        viewModelScope.launch {
            val varMİ = favorilerRepo.favoriVarMi(id)
            onSonuc(varMİ)
        }
    }





}

