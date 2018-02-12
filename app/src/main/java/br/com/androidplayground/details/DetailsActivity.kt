package br.com.androidplayground.details

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.androidplayground.R
import kotlinx.android.synthetic.main.layout_details.*

/**
 * @rodrigohsb
 */
class DetailsActivity: AppCompatActivity() {

    companion object {
        fun startActivity(activity: Activity, label : String) {
            val intent = Intent(activity, DetailsActivity::class.java)
            intent.putExtra("LABEL_ARG", label)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_details)
        val label = intent.getStringExtra("LABEL_ARG")
        detailsLabel.text = label
    }
}