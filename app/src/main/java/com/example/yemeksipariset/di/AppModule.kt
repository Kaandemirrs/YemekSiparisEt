package com.example.yemeksipariset.di

import android.content.Context
import androidx.room.Room
import com.example.yemeksipariset.data.datasource.YemeklerDataSource
import com.example.yemeksipariset.data.repo.FavorilerRepostory
import com.example.yemeksipariset.data.repo.YemeklerRepostory
import com.example.yemeksipariset.retrofit.ApiUtils
import com.example.yemeksipariset.retrofit.YemeklerDao
import com.example.yemeksipariset.room.FavorilerDao
import com.example.yemeksipariset.room.Veritabani
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
class AppModule {



    @Provides
    @Singleton
    fun provideYemeklerDao() : YemeklerDao{
        return ApiUtils.getYemeklerDao()
    }


     @Provides
     @Singleton
    fun provideYemeklerDataSource( vdao : YemeklerDao) : YemeklerDataSource{
        return YemeklerDataSource(vdao)
    }

    @Provides
    @Singleton
    fun provideYemeklerRepostory(yds: YemeklerDataSource) : YemeklerRepostory{
        return YemeklerRepostory(yds)
    }

    @Provides
    @Singleton
    fun provideFavorilerRepostory(dao: FavorilerDao) : FavorilerRepostory{
        return FavorilerRepostory(dao)
    }


    //fav room bağlantıları
    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): Veritabani {
        return Room.databaseBuilder(context, Veritabani::class.java, "favoriler_revize.sqlite")
            .createFromAsset("favoriler_revize.sqlite")
            .build()
    }



    @Provides
    @Singleton
    fun provideFavorilerDao(veritabani: Veritabani) : FavorilerDao{
        return veritabani.getFavorilerDao()
    }



//hiyerarsık sıraladım dao->datasource->repostory
}