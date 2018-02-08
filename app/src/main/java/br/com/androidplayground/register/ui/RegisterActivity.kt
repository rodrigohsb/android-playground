package br.com.androidplayground.register.ui

import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.androidplayground.R
import kotlinx.android.synthetic.main.layout_register_user.*

/**
 * @rodrigohsb
 */
class RegisterActivity : AppCompatActivity(){

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

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun initActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            this.setDisplayHomeAsUpEnabled(true)
            this.setDisplayShowHomeEnabled(true)
            this.setDisplayShowTitleEnabled(true)
            this.title = getString(R.string.register)
        }
    }
}