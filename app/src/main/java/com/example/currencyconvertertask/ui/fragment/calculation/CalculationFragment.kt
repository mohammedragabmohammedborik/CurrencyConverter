package com.example.currencyconvertertask.ui.fragment.calculation
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.currencyconvertertask.databinding.HomeFragmentBinding
import com.example.currencyconvertertask.databinding.LayoutCalculationBinding
import com.example.currencyconvertertask.datalayer.data.ModelRateData
import com.example.currencyconvertertask.presentation.HomeViewModel
import com.example.currencyconvertertask.presentation.ShareDataBetweenFragmentViewModel
import com.example.currencyconvertertask.repository.NetworkState
import com.example.currencyconvertertask.ui.MainActivity
import com.example.currencyconvertertask.ui.fragment.adapter.ClickedItem
import com.example.currencyconvertertask.ui.fragment.adapter.CurrencyRateAdapter
import com.teraninjas.mazadat.presentationlayer.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 home fragment
 *
 */


class CalculationFragment :Fragment() {
    private  val TAG = "CalculationFragment"
    private var _binding: LayoutCalculationBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    lateinit var homeViewModel: HomeViewModel
    private val shareDataBetweenFragmentViewModel: ShareDataBetweenFragmentViewModel by activityViewModels()
    // inject factory view model
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var rateCurrency:Double=0.0


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).homeComponent.inject(this)
        homeViewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LayoutCalculationBinding.inflate(inflater, container, false)
        val view = binding.root
        rateCurrency= requireArguments()?.getDouble("type")!!

        listener()


        //
        return view
    }

   private  fun listener(){
       binding.editTextNumberBase.addTextChangedListener() {
           updateFromInput()

       }
       binding.editTextNumberBase.setOnEditorActionListener { _, actionId, _ ->
           if (actionId == EditorInfo.IME_ACTION_GO) {
               updateFromInput()
               true
           } else {
               false
           }
       }
       binding.editTextNumberBase.setOnKeyListener { _, keyCode, event ->
           if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
               updateFromInput()
               true
           } else {
               false
           }
       }


   }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    fun initObserver(){


    }
    private fun updateFromInput() {
        binding.editTextNumberBase.text.trim().let {
            if (it.isNotEmpty()) {
             val number=   it.toString().toDouble()
                binding.otherCurrencyNumber.text="$number*$rateCurrency"


            }
        }
    }


}