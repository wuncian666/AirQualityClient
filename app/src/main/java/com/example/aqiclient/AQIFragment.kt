package com.example.aqiclient

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aqiclient.data.util.Resource
import com.example.aqiclient.databinding.FragmentAqiBinding
import com.example.aqiclient.presentation.adapter.AQIPerHourAdapter
import com.example.aqiclient.presentation.viewmodel.AQIPerHourViewModel


class AQIFragment : Fragment() {
    private lateinit var viewModel: AQIPerHourViewModel
    private lateinit var aqiPerHourAdapter: AQIPerHourAdapter
    private lateinit var fragmentAqiBinding: FragmentAqiBinding

    private var page = 1
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_aqi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentAqiBinding = FragmentAqiBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        aqiPerHourAdapter = (activity as MainActivity).aqiPerHourAdapter
        initRecyclerView()
        viewNewsList()
    }

    private fun initRecyclerView() {
        fragmentAqiBinding.rvAQI.apply {
            adapter = aqiPerHourAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@AQIFragment.onScrollListener)
        }
    }

    private fun viewNewsList() {
        viewModel.getAQIPerHour()
        viewModel.aqiPerHour.observe(viewLifecycleOwner) {response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        aqiPerHourAdapter.differ.submitList(it.records.toList())
//                        if (it.total.toInt() % 20  ==0) {
//                            pages = it.total.toInt() / 20
//                        } else {
//                            pages = it.total.toInt() / 20 + 1
//                        }
//                        isLastPage = page == pages
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e("MYTAG", "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }

        }
    }

    private fun showProgressBar() {
        isLoading = true
        fragmentAqiBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading = false
        fragmentAqiBinding.progressBar.visibility = View.INVISIBLE
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = fragmentAqiBinding.rvAQI.layoutManager as LinearLayoutManager
            val sizeOfTheCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd = topPosition + visibleItems >= sizeOfTheCurrentList
            val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScrolling
            if (shouldPaginate) {
                page++
                //viewModel.getAQIPerHour()
                isScrolling = false
            }
        }
    }
}