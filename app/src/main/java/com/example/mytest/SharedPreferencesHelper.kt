package com.example.mytest

import android.content.SharedPreferences
import java.util.*

class SharedPreferencesHelper
/**
 * Constructor with dependency injection.
 *
 * @param sharedPreferences The [SharedPreferences] that will be used in this DAO.
 */(  // The injected SharedPreferences implementation to use for persistence.
    private val mSharedPreferences: SharedPreferences
) {
    /**
     * Saves the given [SharedPreferenceEntry] that contains the user's settings to
     * [SharedPreferences].
     *
     * @param sharedPreferenceEntry contains data to save to [SharedPreferences].
     * @return `true` if writing to [SharedPreferences] succeeded. `false`
     * otherwise.
     */
    fun savePersonalInfo(sharedPreferenceEntry: SharedPreferenceEntry): Boolean {
        // Start a SharedPreferences transaction.
        val editor = mSharedPreferences.edit()
        editor.putString(
            SharedPreferencesHelper.Companion.KEY_NAME,
            sharedPreferenceEntry.name
        )
        editor.putLong(
            SharedPreferencesHelper.Companion.KEY_DOB,
            sharedPreferenceEntry.dateOfBirth.getTimeInMillis()
        )
        editor.putString(
            SharedPreferencesHelper.Companion.KEY_EMAIL,
            sharedPreferenceEntry.email
        )
        // Commit changes to SharedPreferences.
        return editor.commit()
    }// Get data from the SharedPreferences.
    // Create and fill a SharedPreferenceEntry model object.
    /**
     * Retrieves the [SharedPreferenceEntry] containing the user's personal information from
     * [SharedPreferences].
     *
     * @return the Retrieved [SharedPreferenceEntry].
     */
    val personalInfo: SharedPreferenceEntry
        get() {
            // Get data from the SharedPreferences.
            val name = mSharedPreferences.getString(SharedPreferencesHelper.Companion.KEY_NAME, "")
            val dobMillis = mSharedPreferences.getLong(
                SharedPreferencesHelper.Companion.KEY_DOB,
                Calendar.getInstance().getTimeInMillis()
            )
            val dateOfBirth: Calendar = Calendar.getInstance()
            dateOfBirth.setTimeInMillis(dobMillis)
            val email =
                mSharedPreferences.getString(SharedPreferencesHelper.Companion.KEY_EMAIL, "")
            // Create and fill a SharedPreferenceEntry model object.
            return SharedPreferenceEntry(name!!, dateOfBirth, email!!)
        }

    companion object {
        // Keys for saving values in SharedPreferences.
        const val KEY_NAME = "key_name"
        const val KEY_DOB = "key_dob_millis"
        const val KEY_EMAIL = "key_email"
    }
}