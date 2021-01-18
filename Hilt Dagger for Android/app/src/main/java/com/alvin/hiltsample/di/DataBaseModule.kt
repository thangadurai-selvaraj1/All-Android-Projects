package com.alvin.hiltsample.di

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.room.Room
import com.alvin.hiltsample.local.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataBaseModule {

    private const val ROOM_DB_NAME = "DB"
    private const val PREFERENCE_DB_NAME = "DB"

    @Singleton
    @Provides
    fun providesDatabase(context: Context): AppDatabase {
        synchronized(this) {
            return Room
                .databaseBuilder(context, AppDatabase::class.java, ROOM_DB_NAME)
                .allowMainThreadQueries()
                .build()
        }
    }

    @Provides
    @Singleton
    fun providesDataStore(
        context: Context
    ): DataStore<Preferences> {
        return context.createDataStore(name = PREFERENCE_DB_NAME)
    }

}