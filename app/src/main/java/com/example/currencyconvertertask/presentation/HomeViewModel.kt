package com.example.currencyconvertertask.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconvertertask.datalayer.data.CurrencyRateData
import com.example.currencyconvertertask.repository.NetworkState
import com.example.currencyconvertertask.repository.local.OfflineData

import com.teraninjas.mazadat.repository.Repository
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import java.lang.Exception

import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: Repository) :ViewModel() {
    //  check for response loading
    private val _currencyRateData = MutableLiveData<CurrencyRateData>()
    val currencyRateData: LiveData<CurrencyRateData>
        get() = _currencyRateData

    fun getCurrencyDataOnline(){
        viewModelScope.launch(Dispatchers.IO) {
            _currencyRateData.postValue(CurrencyRateData(NetworkState.LOADING))

         //   try {
                val baseresponse=   repository.getCurrencyRates()

                baseresponse.body()
                    .let { _currencyRateData.postValue(CurrencyRateData(NetworkState.LOADED,it?.rates,it?.base))
                    }
         //   }
         //   catch (ex:Exception){
             //   _currencyRateData.postValue(CurrencyRateData(NetworkState.error(ex.message)))

          //  }




        }
    }

    fun getCurrencyDataOfline(){
        viewModelScope.launch(Dispatchers.IO) {
            _currencyRateData.postValue(CurrencyRateData(NetworkState.LOADING))

            try {
                val dataList=   OfflineData.getCurrencyRate()


                dataList.let { _currencyRateData.postValue(CurrencyRateData(NetworkState.LOADED,it.map,it?.base))
                    }
            }catch (ex:Exception){
                _currencyRateData.postValue(CurrencyRateData(NetworkState.error(ex.message)))

            }




        }
    }


}