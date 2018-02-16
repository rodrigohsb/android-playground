package br.com.androidplayground.home.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import br.com.androidplayground.R
import br.com.androidplayground.details.DetailsActivity
import br.com.androidplayground.home.adapter.HomeAdapter
import br.com.androidplayground.home.entryModel.ContactEntryModel
import br.com.androidplayground.home.viewmodel.HomeViewModel
import br.com.androidplayground.register.ui.RegisterActivity
import br.com.androidplayground.screenbehaviors.EmptyState
import br.com.androidplayground.screenbehaviors.LoadingView
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import kotlinx.android.synthetic.main.layout_home.*

/**
 * @rodrigohsb
 */
class HomeActivity : AppCompatActivity(),
        LoadingView, EmptyState, HomeAdapter.OnItemClickListener {

    val kodein by lazy { LazyKodein(appKodein) }
    val userAdapter by lazy { HomeAdapter(this) }
    val lManager by lazy { LinearLayoutManager(this)}

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>)= kodein.value.instance<HomeViewModel>() as T
        }
        ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_home)

        homeAddBtn.setOnClickListener({
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
        viewModel.contacts.removeObservers(this)
    }

    private fun initObservers() {

        viewModel.isLoading.observe(this,
                Observer<Boolean> {
                    clearViews()
                    when(it){
                        true -> {
                            showLoading()
                        }
                        false -> hideLoading()
                    }
                })

        viewModel.contacts.observe(this,
                Observer<List<ContactEntryModel>> {
                    it?.let {
                        if(it.isEmpty()){
                            showEmptyView()
                            return@Observer
                        }
                        createRecyclerView(it)
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
        recyclerView.visibility = View.GONE
    }

    private fun createRecyclerView(array: List<ContactEntryModel>) {

        with(recyclerView) {
            layoutManager = lManager
            userAdapter.clear()
            userAdapter.addData(array)
            adapter = userAdapter
            recyclerView.visibility = View.VISIBLE
        }
    }

    override fun onItemClick(prefix: String) {
        DetailsActivity.startActivity(this,prefix)
    }

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility = View.GONE
    }

    override fun hideEmptyView() {
        homeEmptyView.visibility = View.GONE
    }

    override fun showEmptyView() {
        homeEmptyView.visibility = View.VISIBLE
    }
}