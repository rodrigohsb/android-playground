package br.com.androidplayground.register.ui

import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.androidplayground.R
import br.com.androidplayground.register.viewmodel.RegisterViewModel
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import kotlinx.android.synthetic.main.layout_register_user.*

/**
 * @rodrigohsb
 */
class RegisterActivity : AppCompatActivity(){

    private val kodein by lazy { LazyKodein(appKodein) }
    private val viewModel: RegisterViewModel by kodein.instance()

    companion object {
        fun startActivity(activity: Activity) {
            activity.startActivity(Intent(activity, RegisterActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_register_user)
        initActionBar()
    }

    private fun initActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title = getString(R.string.register)
    }

    override fun onStart() {
        super.onStart()
        initObserver()
        viewModel.fetchLabels()
    }

    private fun initObserver() {
        viewModel.labels.observe(this,
                Observer<List<String>> {
                    it?.let { showLabels(it) }
                })
    }

    private fun showLabels(labels : List<String>) {
        first_input_layout.hint = labels[0]
        second_input_layout.hint = labels[1]
        third_input_layout.hint = labels[2]
        fourth_input_layout.hint = labels[3]
        fifth_input_layout.hint = labels[4]
        textView.text = labels[5]
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}