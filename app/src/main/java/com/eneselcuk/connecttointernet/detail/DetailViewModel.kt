package com.eneselcuk.connecttointernet.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eneselcuk.connecttointernet.network.model.MarsData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {
    private val _selectedProperty = MutableLiveData<MarsData>()
    val selectedProperty: LiveData<MarsData>
        get() = _selectedProperty

    fun setMarsData(marsData: MarsData) {
        _selectedProperty.postValue(marsData)
    }
}