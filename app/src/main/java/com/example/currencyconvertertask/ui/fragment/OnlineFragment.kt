package com.example.currencyconvertertask.ui.fragment
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.currencyconvertertask.R
import com.example.currencyconvertertask.databinding.HomeFragmentBinding
import com.example.currencyconvertertask.datalayer.data.ModelRateData
import com.example.currencyconvertertask.presentation.HomeViewModel
import com.example.currencyconvertertask.presentation.ShareDataBetweenFragmentViewModel
import com.example.currencyconvertertask.repository.NetworkState
import com.example.currencyconvertertask.ui.MainActivity
import com.example.currencyconvertertask.ui.fragment.adapter.ClickedItem
import com.example.currencyconvertertask.ui.fragment.adapter.CurrencyRateAdapter
import com.example.currencyconvertertask.utility.Utile
import com.teraninjas.mazadat.presentationlayer.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 home fragment
 *
 */


class OnlineFragment :Fragment() {
    private  val TAG = "HomeFragment"
    private var _binding: HomeFragmentBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private  lateinit var  currencyRateAdapter:CurrencyRateAdapter
    lateinit var homeViewModel: HomeViewModel
    private val shareDataBetweenFragmentViewModel: ShareDataBetweenFragmentViewModel by activityViewModels()
    // inject factory view model
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private  var type:Int=0


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
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

            if (Utile.isOnline(requireActivity())) {
                homeViewModel.getCurrencyDataOnline()
            }else{
                Utile.showToast(requireActivity(),getString(R.string.no_internet))
            }

        initAdapter()
        initObserver()

        //
        return view
    }

    override fun onStart() {
        super.onStart()

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




    fun initAdapter(){
        currencyRateAdapter= CurrencyRateAdapter(requireActivity(),object: ClickedItem{

            override fun cliced(modelRateData: ModelRateData) {
                val bundle:Bundle= Bundle()
                   bundle.putParcelable("model",modelRateData)
                   findNavController().navigate(R.id.to_calculation,bundle)

            }
        })



        val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.currencyRateList.addItemDecoration(decoration)

        binding.currencyRateList.adapter=currencyRateAdapter
        //




    }



    fun initObserver(){

        homeViewModel.currencyRateData.observe(viewLifecycleOwner, Observer { it ->

            when (it.networkState) {
                NetworkState.LOADED -> {
                    val list=it.hashMap?.entries?.map { ModelRateData(it.key,it.value) }

                   currencyRateAdapter.submitList(list)
                    it.base?.let{
                        shareDataBetweenFragmentViewModel.setBase(it)
                    }
                    binding.pb.visibility=View.GONE

                }
                NetworkState.LOADING -> {
                    binding.pb.visibility=View.VISIBLE
                    // Loading
                }
                else -> {
                    it.networkState.message?.let { it1 -> Utile.showToast(requireActivity(), it1) }
                    binding.pb.visibility=View.GONE

                }
            }


        })

    }


}