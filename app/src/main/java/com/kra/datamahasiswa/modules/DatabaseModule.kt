package com.kra.datamahasiswa.modules

import android.content.Context
import androidx.room.Room
import com.kra.datamahasiswa.database.KraDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun providesRoom(@ApplicationContext context: Context): KraDb {
        return Room.databaseBuilder(context, KraDb::class.java, "kra.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}