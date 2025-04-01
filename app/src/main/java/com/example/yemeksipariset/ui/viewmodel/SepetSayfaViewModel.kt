package com.example.yemeksipariset.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yemeksipariset.data.entity.SepetYemek
import com.example.yemeksipariset.data.entity.Yemekler
import com.example.yemeksipariset.data.repo.YemeklerRepostory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SepetSayfaViewModel @Inject constructor(var yrpo : YemeklerRepostory) : ViewModel() {

    //en son hılt kurduk ondan sonra apiye gecicez


   fun sepeteEkle(yemek:Yemekler,adet:Int,kullaniciAdi:String){


       viewModelScope.launch {
           val cevap = yrpo.sepetEkle(yemek,adet,kullaniciAdi)
           Log.d("sepeteEkle","Durum: ${cevap.succes}, mesaj: ${cevap.message}")
       }

   }

    fun sepetteEkleAkilli(yemek: Yemekler,yeniAdet:Int,kullaniciAdi: String){

        viewModelScope.launch {
            try {
                val mevcutSepet = yrpo.sepetiYukle(kullaniciAdi)

                val ayniYemek = mevcutSepet.find { it.yemek_adi == yemek.yemek_adi }


                if (ayniYemek != null) {
                    val eskiAdet = ayniYemek.yemek_siparis_adet.toIntOrNull() ?: 0
                    val toplamAdet = eskiAdet + yeniAdet

                    // Önce sil
                    yrpo.sepettenSilRepo(ayniYemek.sepet_yemek_id.toInt(), kullaniciAdi)

                    // Sonra yeni toplamla yeniden ekle
                    yrpo.sepetEkle(yemek, toplamAdet, kullaniciAdi)

                    Log.d("sepeteekle", "Var olan güncellendi: ${yemek.yemek_adi}, toplam: $toplamAdet")
                } else {
                    // Direkt yeni ekle
                    yrpo.sepetEkle(yemek, yeniAdet, kullaniciAdi)
                    Log.d("sepeteEkle", "Yeni eklendi: ${yemek.yemek_adi}, adet: $yeniAdet")
                }

                sepetiGetir(kullaniciAdi)
            }catch (e:Exception){
                Log.e("sepeteEkleHata","hata: ${e.localizedMessage}")
            }
        }



    }

    var sepetListesi = MutableLiveData<List<SepetYemek>>()

    fun sepetiGetir(kullaniciAdi: String){
        viewModelScope.launch {
            try {
                val gelenSepet = yrpo.sepetiYukle(kullaniciAdi)
                sepetListesi.value = gelenSepet
            }catch (e:Exception){
                Log.e("sepetviewmodel", "HATA: ${e.message}")
            }

        }
    }


    fun sepettenSil(sepetYemekId:Int,kullaniciAdi: String){

        viewModelScope.launch {
            val cevap = yrpo.sepettenSilRepo(sepetYemekId,kullaniciAdi)
            Log.d("sepettenSil","durum: ${cevap.succes},Mesaj: ${cevap.message}")
            sepetiGetir(kullaniciAdi) //guncelleme ıcın
        }

    }





}