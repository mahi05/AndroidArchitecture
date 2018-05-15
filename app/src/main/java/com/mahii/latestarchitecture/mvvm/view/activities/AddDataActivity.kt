package com.mahii.latestarchitecture.mvvm.view.activities

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.mahii.latestarchitecture.R
import com.mahii.latestarchitecture.databinding.ActivityAddDataBinding
import com.mahii.latestarchitecture.utils.AppConstants.Companion.EXTRA_DESIGNATION
import com.mahii.latestarchitecture.utils.AppConstants.Companion.EXTRA_NAME

class AddDataActivity : AppCompatActivity() {

    private lateinit var activityAddDataBinding: ActivityAddDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAddDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_data)

        activityAddDataBinding.btnSave.setOnClickListener {

            if (activityAddDataBinding.etName.text != null &&
                    activityAddDataBinding.etDesignation.text != null) {
                val name = activityAddDataBinding.etName.text.toString()
                val designation = activityAddDataBinding.etDesignation.text.toString()

                val i = Intent()
                i.putExtra(EXTRA_NAME, name)
                i.putExtra(EXTRA_DESIGNATION, designation)
                setResult(Activity.RESULT_OK, i)
                finish()

            } else {
                Toast.makeText(this@AddDataActivity, "Please provide all details!", Toast.LENGTH_LONG).show()
            }
        }

    }
}
