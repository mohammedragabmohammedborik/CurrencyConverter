package com.example.currencyconvertertask.datalayer

  data class BaseResponse(val success:Boolean, val base:String,val rates:MutableMap<String,Double>?=null) {

  }