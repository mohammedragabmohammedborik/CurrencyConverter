package com.example.currencyconvertertask.ui.fragment.calculation
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconvertertask.databinding.LayoutCalculationBinding
import com.example.currencyconvertertask.datalayer.data.ModelRateData
import com.example.currencyconvertertask.presentation.HomeViewModel
import com.example.currencyconvertertask.presentation.ShareDataBetweenFragmentViewModel
import com.example.currencyconvertertask.ui.MainActivity

import com.teraninjas.mazadat.presentationlayer.ViewModelFactory

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

        val modelRate= requireArguments()?.getParcelable<ModelRateData>("model")!! as ModelRateData
        modelRate?.let {
            it ->  rateCurrency=it.rateValue
            binding.otherCurrencyName.text=it.key
            binding.otherCurrencyNumber.text="$rateCurrency"
            binding.editTextNumberBase.setText("1")

        }
        observer()
        listener()


        //
        return view
    }

   private  fun listener(){
       binding.editTextNumberBase.addTextChangedListener() {
           updateFromInput(binding.editTextNumberBase.text.trim()
               .toString())

       }
       binding.editTextNumberBase.setOnEditorActionListener { _, actionId, _ ->
           if (actionId == EditorInfo.IME_ACTION_GO) {
               updateFromInput(binding.editTextNumberBase.text.trim()
                   .toString())
               true
           } else {
               false
           }
       }
       binding.editTextNumberBase.setOnKeyListener { _, keyCode, event ->
           if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
               updateFromInput(binding.editTextNumberBase.text.trim()
                   .toString())
               true
           } else {
               false
           }
       }


   }

    private fun observer(){
        shareDataBetweenFragmentViewModel.baseName.observe(viewLifecycleOwner, Observer {
         it?.let {
             binding.baseCurrencyName.text=it
         }
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onResume() {
        super.onResume()
        binding.editTextNumberBase.isFocusableInTouchMode = true;
        binding.editTextNumberBase.requestFocus()
    }

    /**
     * function take pramter input and multiplay it for currency rate
     */
    private fun updateFromInput(input:String) {
        input.let {
            if (it.isNotEmpty()) {
             val number=   it.toString().toDouble()
                val total=number*rateCurrency
                binding.otherCurrencyNumber.text="$total"
            }else{
                binding.otherCurrencyNumber.text=""
            }
        }
    }


}