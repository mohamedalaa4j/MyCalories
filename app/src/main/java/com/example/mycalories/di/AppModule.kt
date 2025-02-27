package com.example.mycalories.di

import android.content.Context
import androidx.room.Room
import com.example.mycalories.data.AppDatabase
import com.example.mycalories.data.RepositoryImpl
import com.example.mycalories.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideYourDatabase(@ApplicationContext app: Context): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, "day_intake_record")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideRepository(db: AppDatabase): Repository {
        return RepositoryImpl(db.dao())
    }
}