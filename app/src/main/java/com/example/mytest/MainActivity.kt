package com.example.mytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mytest.R

import android.preference.PreferenceManager


import android.widget.EditText

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.DatePicker
import androidx.core.widget.addTextChangedListener
import java.util.*

class MainActivity : AppCompatActivity() {
    // The helper that manages writing to SharedPreferences.
    private var mSharedPreferencesHelper: SharedPreferencesHelper? = null

    // The input field where the user enters his name.
    private var mNameText: EditText? = null

    // The date picker where the user enters his date of birth.
    private var mDobPicker: DatePicker? = null

    // The input field where the user enters his email.
    private var mEmailText: EditText? = null

    // The validator for the email input field.
    private var mEmailValidator: EmailValidator? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Shortcuts to input fields.
        mNameText = findViewById<View>(R.id.userNameInput) as EditText
        mDobPicker = findViewById<View>(R.id.dateOfBirthInput) as DatePicker
        mEmailText = findViewById<View>(R.id.emailInput) as EditText
        mEmailText = findViewById<View>(R.id.emailInput) as EditText
        // Setup field validators.
        mEmailValidator = EmailValidator()
        mEmailText!!.addTextChangedListener(mEmailValidator)

        // Instantiate a SharedPreferencesHelper.
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        mSharedPreferencesHelper = SharedPreferencesHelper(sharedPreferences)
        // Fill input fields from data retrieved from the SharedPreferences.
        populateUi()
    }

    /**
     * Initialize all fields from the personal info saved in the SharedPreferences.
     */
    private fun populateUi() {
        val sharedPreferenceEntry: SharedPreferenceEntry?
        sharedPreferenceEntry = mSharedPreferencesHelper?.personalInfo
        mNameText?.setText(sharedPreferenceEntry?.name)
        val dateOfBirth: Calendar? = sharedPreferenceEntry?.dateOfBirth
        dateOfBirth?.get(Calendar.MONTH)?.let {
            mDobPicker?.init(
                dateOfBirth?.get(Calendar.YEAR), it,
                dateOfBirth?.get(Calendar.DAY_OF_MONTH), null
            )
        }
        mEmailText?.setText(sharedPreferenceEntry?.email)
    }

    /**
     * Called when the "Save" button is clicked.
     */
    fun onSaveClick(view: View?) {
        // Don't save if the fields do not validate.
        if (mEmailValidator?.isValid == false) {
            mEmailText!!.error = "Invalid email"
            Log.w(MainActivity.Companion.TAG, "Not saving personal information: Invalid email")
            return
        }
        // Get the text from the input fields.
        val name = mNameText!!.text.toString()
        val dateOfBirth: Calendar = Calendar.getInstance()
        mDobPicker?.let { dateOfBirth.set(it.year, it.getMonth(), it.dayOfMonth) }
        val email = mEmailText!!.text.toString()
        // Create a Setting model class to persist.
        val sharedPreferenceEntry = SharedPreferenceEntry(name, dateOfBirth, email)
        // Persist the personal information.
        val isSuccess: Boolean = mSharedPreferencesHelper?.savePersonalInfo(sharedPreferenceEntry) == true
        if (isSuccess) {
            Toast.makeText(this, "Personal information saved", Toast.LENGTH_LONG).show()
            Log.i(MainActivity.Companion.TAG, "Personal information saved")
        } else {
            Log.e(
                MainActivity.Companion.TAG,
                "Failed to write personal information to SharedPreferences"
            )
        }
    }

    /**
     * Called when the "Revert" button is clicked.
     */
    fun onRevertClick(view: View?) {
        populateUi()
        Toast.makeText(this, "Personal information reverted", Toast.LENGTH_LONG).show()
        Log.i(MainActivity.Companion.TAG, "Personal information reverted")
    }

    companion object {
        // Logger for this class.
        private const val TAG = "MainActivity"
    }
}