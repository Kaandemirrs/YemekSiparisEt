package com.example.yemeksipariset.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yemeksipariset.data.entity.Favoriler
import com.example.yemeksipariset.data.entity.Yemekler
import com.example.yemeksipariset.data.repo.YemeklerRepostory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnaSayfaViewModel @Inject constructor(var yrepo:YemeklerRepostory): ViewModel() {


    var TumyemeklerListesi = MutableLiveData<List<Yemekler>>()

    init {
        yemekleriYukle()
    }

    fun yemekleriYukle(){

        viewModelScope.launch {
            try {
                val gelenListe = yrepo.yemekleriYukleRp()
                Log.d("anasayfaviewmodel", "Gelen Liste: ${gelenListe.size}") // 👈 EKLE BUNU
                TumyemeklerListesi.value = gelenListe
            } catch (e:Exception){

                Log.e("anasayfaviewmodel", "hata : ${e.message}")

            }
        }


    }







}