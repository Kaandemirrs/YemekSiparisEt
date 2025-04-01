package com.example.yemeksipariset.data.repo

import com.example.yemeksipariset.data.entity.Favoriler
import com.example.yemeksipariset.room.FavorilerDao
import javax.inject.Inject

class FavorilerRepostory @Inject constructor(private val dao : FavorilerDao) {

   suspend fun favoriEkle(yemek:Favoriler){
       dao.favoriEkle(yemek)
   }
    suspend fun favoriSil(yemek: Favoriler){
        dao.favoriSil(yemek)
    }
    suspend fun getFavoriler() : List<Favoriler>{
        return dao.getFavoriler()
    }

    suspend fun favoriVarMi(id:Int) : Boolean{
        return dao.favoriVarMi(id) != null
    }

}