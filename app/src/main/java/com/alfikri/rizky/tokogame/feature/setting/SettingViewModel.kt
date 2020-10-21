package com.alfikri.rizky.tokogame.feature.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is feature still not ready"
    }
    val text: LiveData<String> = _text
}