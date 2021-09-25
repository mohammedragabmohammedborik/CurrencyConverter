package com.example.currencyconvertertask.presentation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
class ShareDataBetweenFragmentViewModel() :ViewModel() {

    private val _baseName = MutableLiveData<String?>()
    val baseName: LiveData<String?>
        get() = _baseName
    fun setBase(baseNameValue:String){
        _baseName.value=baseNameValue
    }

    //



}