package com.example.currencyconvertertask.ui.fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconvertertask.databinding.ItemCurrencyRateBinding
import com.example.currencyconvertertask.datalayer.data.ModelRateData

class CurrencyRateAdapter(val context:Context, val clickedItem: ClickedItem):ListAdapter<ModelRateData, CurrencyRateAdapter.HomeViewHolder>(
    diffUtileCompartort
) {

    class HomeViewHolder private  constructor(val  binding:ItemCurrencyRateBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: ModelRateData){
            binding.model= model

            binding.executePendingBindings()
        }
        companion object{
            fun from(parent:ViewGroup): HomeViewHolder {
                val layoutInflater= LayoutInflater.from(parent.context)
                val binding=ItemCurrencyRateBinding.inflate(layoutInflater,parent,false)
                return HomeViewHolder(
                    binding

                )
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item =getItem(position)
        holder.bind(item)
       holder.binding.click=clickedItem







    }

companion object{
    val diffUtileCompartort= object:DiffUtil.ItemCallback<ModelRateData>(){
        override fun areItemsTheSame(
            oldItem: ModelRateData,
            newItem: ModelRateData
        ): Boolean {
            return  oldItem.key==newItem.key

        }

        override fun areContentsTheSame(
            oldItem: ModelRateData,
            newItem: ModelRateData
        ): Boolean {
            return  oldItem==newItem
        }


    }

}

}
