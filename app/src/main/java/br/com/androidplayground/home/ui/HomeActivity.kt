package br.com.androidplayground.home.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import br.com.androidplayground.R
import br.com.androidplayground.extensions.hide
import br.com.androidplayground.extensions.show
import br.com.androidplayground.home.adapter.HomeAdapter
import br.com.androidplayground.home.viewmodel.HomeViewModel
import br.com.androidplayground.persistence.model.Client
import br.com.androidplayground.screenbehaviors.EmptyState
import br.com.androidplayground.screenbehaviors.LoadingView
import br.com.androidplayground.register.ui.RegisterActivity
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance

import kotlinx.android.synthetic.main.layout_home.*

/**
 * @rodrigohsb
 */
class HomeActivity : AppCompatActivity(),
        LoadingView, EmptyState, HomeAdapter.OnItemClickListener {

    private val kodein by lazy { LazyKodein(appKodein) }
    private val userAdapter by lazy { HomeAdapter(this) }
    private val lManager by lazy { LinearLayoutManager(this)}

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>)= kodein.value.instance<HomeViewModel>() as T
        }
        ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_home)

        home_add_user_btn.setOnClickListener({
            RegisterActivity.startActivity(this)
        })
    }

    override fun onStart() {
        super.onStart()
        initObservers()
        fetchContent()
    }

    override fun onPause() {
        super.onPause()
        removeObservers()
    }

    private fun removeObservers() {
        viewModel.isLoading.removeObservers(this)
        viewModel.repositories.removeObservers(this)
    }

    private fun initObservers() {

        viewModel.isLoading.observe(this,
            Observer<Boolean> {
                when(it){
                    true -> {
                        showLoading()
                        clearViews()
                    }
                    false -> hideLoading()
                }
            })

        viewModel.repositories.observe(this,
                Observer<List<Client>> {
                    it?.let {
                        if(it.isEmpty()){
                            showEmptyView()
                        } else{
                            createRecyclerView(it)
                        }
                        return@Observer
                    }
                    showEmptyView()
                })
    }

    private fun clearViews() {
        hideRecyclerView()
        hideEmptyView()
    }

    private fun fetchContent(){
        viewModel.loadContent()
    }

    private fun hideRecyclerView() {
        repository_rv.hide()
    }

    private fun createRecyclerView(array: List<Client>) {

        with(repository_rv) {

            layoutManager = lManager

            userAdapter.clear()
            userAdapter.addData(array)

            //TODO Adapter is always the same. Extract to bind just once
            adapter = userAdapter

            repository_rv.show()
        }
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this,"Should go to details",Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        loading.show()
    }

    override fun hideLoading() {
        loading.hide()
    }

    override fun hideEmptyView() {
        home_empty_state.hide()
    }

    override fun showEmptyView() {
        home_empty_state.show()
    }
}