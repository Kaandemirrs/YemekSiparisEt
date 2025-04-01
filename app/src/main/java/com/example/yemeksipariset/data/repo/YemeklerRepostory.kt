package com.example.yemeksipariset.data.repo

import com.example.yemeksipariset.data.datasource.YemeklerDataSource
import com.example.yemeksipariset.data.entity.CRUDCevap
import com.example.yemeksipariset.data.entity.SepetYemek
import com.example.yemeksipariset.data.entity.Yemekler

class YemeklerRepostory(var yds: YemeklerDataSource) {



    suspend fun yemekleriYukleRp() : List<Yemekler> = yds.tumyemekleriYukle()

    suspend fun sepetEkle(yemek:Yemekler,adet:Int,kullaniciAdi:String) : CRUDCevap {
        return yds.sepeteEkle(yemek,adet,kullaniciAdi)
    }

    suspend fun sepetiYukle(kullaniciAdi: String) : List<SepetYemek>{
        return yds.sepetYukle(kullaniciAdi)
    }

    suspend fun sepettenSilRepo(sepetYemekId:Int,kullaniciAdi: String) = yds.sepettenSil(sepetYemekId,kullaniciAdi)
}