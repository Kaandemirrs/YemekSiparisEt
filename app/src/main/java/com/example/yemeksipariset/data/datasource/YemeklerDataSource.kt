package com.example.yemeksipariset.data.datasource

import com.example.yemeksipariset.data.entity.CRUDCevap
import com.example.yemeksipariset.data.entity.SepetYemek
import com.example.yemeksipariset.data.entity.Yemekler
import com.example.yemeksipariset.retrofit.YemeklerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield

class YemeklerDataSource(var vdao : YemeklerDao) {

    suspend fun tumyemekleriYukle() : List<Yemekler> = withContext(Dispatchers.IO){


     //   val TumYemeklerListesi = ArrayList<Yemekler>()


        return@withContext vdao.yemekleriYukle().yemekler

    }


    suspend fun sepeteEkle(yemek : Yemekler,adet : Int,kullaniciAdi : String) : CRUDCevap = withContext(Dispatchers.IO){

        return@withContext vdao.sepeteEkle(yemek.yemek_adi,yemek.yemek_resim_adi,yemek.yemek_fiyat,adet,kullaniciAdi)
    }

    suspend fun sepetYukle(kullaniciAdi: String) : List<SepetYemek> = withContext(Dispatchers.IO){
        return@withContext vdao.sepetiYukle(kullaniciAdi).sepet_yemekler

    }

    suspend fun sepettenSil(sepetYemekId:Int,kullaniciAdi: String) = withContext(Dispatchers.IO){
        return@withContext vdao.sepettenSil(sepetYemekId,kullaniciAdi)
    }

}