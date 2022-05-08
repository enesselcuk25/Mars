package com.eneselcuk.connecttointernet.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eneselcuk.connecttointernet.MarsApiFilter
import com.eneselcuk.connecttointernet.MarsApiStatus
import com.eneselcuk.connecttointernet.network.MarsApi
import com.eneselcuk.connecttointernet.network.model.MarsData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class OverViewViewModel @Inject constructor() : ViewModel() {

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _status = MutableLiveData<MarsApiStatus>()
    val status: LiveData<MarsApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<MarsData>>()
    val properties: LiveData<List<MarsData>>
        get() = _properties

    init {
        getMarsRealEstateProperties(MarsApiFilter.SHOW_ALL)
    }

    private fun getMarsRealEstateProperties(filter: MarsApiFilter) {
        coroutineScope.launch {
            val getPropertiesDeferred = MarsApi.retrofitService.getPropertiesAsync(filter.value)

            try {
                _status.postValue(MarsApiStatus.LOADING)
                var listResult = getPropertiesDeferred.await()

                if (listResult.isNotEmpty()) {
                    _properties.value = listOf(listResult[0])
                }
                _properties.postValue(listResult)
                _status.postValue(MarsApiStatus.DONE)
            } catch (ex: Exception) {
                _status.postValue(MarsApiStatus.ERROR)
                // hata oluştuğun da arrayList liveData boş bir array ile temizliyor
                _properties.postValue(ArrayList())
            }
        }
//        _status.value = "Set the Mars API Response here!"
    }

    // Navigation
    private val _navigateToSelectedProperty = MutableLiveData<MarsData?>()
    val navigateToSelectedProperty: LiveData<MarsData?>
        get() = _navigateToSelectedProperty

    // Navigaton işlemi için kullanıldı
    fun displayPropertyDetails(marsData: MarsData) {
        _navigateToSelectedProperty.postValue(marsData)
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.postValue(null)
    }

    fun updateFilter(filter: MarsApiFilter) {
        getMarsRealEstateProperties(filter)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}