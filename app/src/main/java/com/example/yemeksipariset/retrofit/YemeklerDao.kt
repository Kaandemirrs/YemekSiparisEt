package com.example.yemeksipariset.retrofit

import com.example.yemeksipariset.data.entity.CRUDCevap
import com.example.yemeksipariset.data.entity.SepetYemekCevap
import com.example.yemeksipariset.data.entity.YemeklerCevap
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface YemeklerDao {


    //http://kasimadalan.pe.hu/yemekler/tumYemekleriGetir.php
    //http://kasimadalan.pe.hu/ -> Base url
    //yemekler/tumYemekleriGetir.php  -> webservis url

    @GET("yemekler/tumYemekleriGetir.php")

    suspend fun yemekleriYukle() : YemeklerCevap


    //http://kasimadalan.pe.hu/yemekler/sepeteYemekEkle.php

    @FormUrlEncoded
    @POST("yemekler/sepeteYemekEkle.php")
    suspend fun sepeteEkle(
        @Field("yemek_adi") yemek_adi : String,
        @Field("yemek_resim_adi") yemek_resim_adi : String,
        @Field("yemek_fiyat") yemek_fiyat : Int,
        @Field("yemek_siparis_adet") yemek_siparis_adet : Int,
        @Field("kullanici_adi") kullanici_adi: String
    ) : CRUDCevap

    //http://kasimadalan.pe.hu/yemekler/sepettekiYemekleriGetir.php

    @FormUrlEncoded
    @POST("yemekler/sepettekiYemekleriGetir.php")
    suspend fun sepetiYukle(
        @Field("kullanici_adi") kullanici_adi: String
    ) : SepetYemekCevap



    //http://kasimadalan.pe.hu/yemekler/sepettenYemekSil.php

    @FormUrlEncoded
    @POST("yemekler/sepettenYemekSil.php")
    suspend fun sepettenSil(@Field("sepet_yemek_id") sepetYemekId:Int,
                            @Field("kullanici_adi") kullaniciAdi:String) : CRUDCevap


}