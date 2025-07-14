package com.example.newsapp.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.newsapp.domain.manger.LocalUserManger
import com.example.newsapp.util.Constant.APP_ENTRY
import com.example.newsapp.util.Constant.USER_SETTING
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserMangerImpl(
    private val context: Context,
) : LocalUserManger {
    override suspend fun saveOnboardingState() {
        context.dataStore.edit {preference ->
            preference[PreferenceKey.state] = true
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return context.dataStore.data.map {preference->
            preference[PreferenceKey.state] ?: false
        }
    }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name =USER_SETTING)
private object PreferenceKey {
    val state = booleanPreferencesKey(APP_ENTRY)
}