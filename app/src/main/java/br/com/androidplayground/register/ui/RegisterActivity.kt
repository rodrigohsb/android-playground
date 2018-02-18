package br.com.androidplayground.register.ui

import android.app.Activity
import android.app.DatePickerDialog
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import br.com.androidplayground.R
import br.com.androidplayground.register.entryModel.RegisterEntryModel
import br.com.androidplayground.register.viewmodel.RegisterViewModel
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import kotlinx.android.synthetic.main.layout_register_user.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * @rodrigohsb
 */
class RegisterActivity : AppCompatActivity(){

    private val kodein by lazy { LazyKodein(appKodein) }

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>)= kodein.value.instance<RegisterViewModel>() as T
        }
        ViewModelProviders.of(this, factory).get(RegisterViewModel::class.java)
    }

    private lateinit var cal: Calendar

    companion object {
        fun startActivity(activity: Activity) {
            activity.startActivity(Intent(activity, RegisterActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_register_user)
        initActionBar()

       cal = Calendar.getInstance()

        calendar.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this@RegisterActivity,
                    DatePickerDialog.OnDateSetListener { _, year, month, day ->
                        cal.set(Calendar.YEAR, year)
                        cal.set(Calendar.MONTH, month)
                        cal.set(Calendar.DAY_OF_MONTH, day)
                        updateDateInView()
                    },
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH))

                cal.add(Calendar.YEAR, -50)
                datePickerDialog.datePicker.minDate = cal.timeInMillis
                datePickerDialog.datePicker.maxDate = Date().time
                datePickerDialog.datePicker.id = R.integer.date_picker_id
                datePickerDialog.show()
        }

        next_btn.setOnClickListener {
            val name = first_input.text.toString()
            val email = second_input.text.toString()
            val phone = third_input.text.toString()
            val fantasyName = fourth_input.text.toString()
            val cnpj = fifth_input.text.toString()
            val isMEI = yes.isChecked

            val registerEntryModel = RegisterEntryModel(name, cnpj, cal.time, email, fantasyName, phone, isMEI)
            val isValid = viewModel.valid(registerEntryModel)

            if(isValid){
                Toast.makeText(this,"Sucess",Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        date.text = sdf.format(cal.time)
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

    override fun onStart() {
        super.onStart()
        val fetchLabels = viewModel.fetchLabels()
        showLabels(fetchLabels)
    }

    private fun showLabels(labels : List<String>) {

        if(labels.isEmpty()) return

        first_input_layout.hint = labels[0]
        second_input_layout.hint = labels[1]
        third_input_layout.hint = labels[2]
        fourth_input_layout.hint = labels[3]
        fifth_input_layout.hint = labels[4]
        textView.text = labels[5]
        textView2.text = labels[6]
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