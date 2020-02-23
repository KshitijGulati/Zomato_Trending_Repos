package com.kshitij.trendingrepos.ui.homescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kshitij.trendingrepos.R
import com.kshitij.trendingrepos.TrendingReposApp
import com.kshitij.trendingrepos.data.model.NetworkResponse
import com.kshitij.trendingrepos.data.model.SortType
import com.kshitij.trendingrepos.databinding.FragmentHomeBinding
import com.kshitij.trendingrepos.ui.base.BaseFragment
import com.kshitij.trendingrepos.utils.hide
import com.kshitij.trendingrepos.utils.setDivider
import com.kshitij.trendingrepos.utils.show

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun getLayoutRes(): Int = R.layout.fragment_home

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        TrendingReposApp.get().applicationComponent.inject(this)
        setHasOptionsMenu(true)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        viewModel.getRepositories()
    }

    private fun initUI() {
        binding.toolbarLayout.ivMore.setOnClickListener { showPopupMenu(binding.toolbarLayout.ivMore) }
        binding.rvRepository.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvRepository.setDivider(R.drawable.divider_drawable)
        initObservers()
        initListeners()
    }

    private fun initListeners() {
        binding.errorLayout.btnRetry.setOnClickListener {
            viewModel.getRepositories()
        }
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.forceRefreshRepositories()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun initObservers() {
        viewModel.getResponseLiveData().observe(this, Observer {
            when (it) {
                is NetworkResponse.Loading -> {
                    showLoading()
                }
                is NetworkResponse.Success -> {
                    loadList(it.data)
                }
                is NetworkResponse.ResponseError -> {
                    showError()
                }
            }
        })
    }

    private fun showError() {
        binding.errorLayout.root.show()
        binding.viewLoading.hide()
        binding.rvRepository.hide()
    }

    private fun showLoading() {
        binding.viewLoading.show()
        binding.rvRepository.hide()
        binding.errorLayout.root.hide()
    }

    private fun loadList(list: List<RepositoryItem>) {
        binding.viewLoading.hide()
        binding.rvRepository.show()
        binding.errorLayout.root.hide()
        binding.rvRepository.adapter = RepositoryAdapter(list)
    }

    private fun showPopupMenu(view: View) = PopupMenu(view.context, view).run {
        menuInflater.inflate(R.menu.sort_item_list, menu)
        setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_name -> {
                    viewModel.sortRepoList(SortType.SortByName)
                }
                R.id.menu_stars -> {
                    viewModel.sortRepoList(SortType.SortByStars)
                }
            }
            true
        }
        show()
    }

}