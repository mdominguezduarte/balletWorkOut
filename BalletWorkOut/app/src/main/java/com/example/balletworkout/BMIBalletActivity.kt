package com.example.balletworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_bmi_ballet.*
import java.math.BigDecimal
import java.math.RoundingMode


class BMIBalletActivity : AppCompatActivity() {

    companion object {
        private const val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW" // Metric Unit View
        private const val US_UNITS_VIEW = "US_UNIT_VIEW" // US Unit View
    }

    private var currentVisibleView: String =
        METRIC_UNITS_VIEW // A variable to hold a value to make visible a selected view

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_ballet)

        setSupportActionBar(toolbar_bmi_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //set back button
        supportActionBar?.title = "CALCULATE BMI" // Setting an title in the action bar.

        toolbar_bmi_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        makeVisibleMetricUnitsView()

        // Radio Group change listener is set to the radio group which is added in XML.
        rgUnits.setOnCheckedChangeListener { radioGroup: RadioGroup, checkedId: Int ->

            // Here is the checkId is METRIC UNITS view then make the view visible else US UNITS view.
            if (checkedId == R.id.rbMetricUnits) {
                makeVisibleMetricUnitsView()
            } else {
                makeVisibleUsUnitsView()
            }
        }

        // Button will calculate the input values in Metric Units
        btnCalculateUnits.setOnClickListener {

            if (currentVisibleView.equals(METRIC_UNITS_VIEW)) {
                // The values are validated.
                if (validateMetricUnits()) {

                    val heightValue: Float = etMetricUnitHeight.text.toString().toFloat() / 100


                    val weightValue: Float = etMetricUnitWeight.text.toString().toFloat()


                    val bmi = weightValue / (heightValue * heightValue)

                    displayBMIResult(bmi)
                } else {
                    Toast.makeText(
                        this@BMIBalletActivity,
                        "Please enter valid values.",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            } else {

                // The values are validated.
                if (validateUsUnits()) {

                    val usUnitHeightValueFeet: String =
                        etUsUnitHeightFeet.text.toString()
                    val usUnitHeightValueInch: String =
                        etUsUnitHeightInch.text.toString()
                    val usUnitWeightValue: Float = etUsUnitWeight.text.toString()
                        .toFloat()


                    val heightValue =
                        usUnitHeightValueInch.toFloat() + usUnitHeightValueFeet.toFloat() * 12


                    val bmi = 703 * (usUnitWeightValue / (heightValue * heightValue))

                    displayBMIResult(bmi) // Displaying the result into UI
                } else {
                    Toast.makeText(
                        this@BMIBalletActivity,
                        "Please enter valid values.",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

    /**
     * Function is used to make visible the METRIC UNITS VIEW and hide the US UNITS VIEW.
     */
    private fun makeVisibleMetricUnitsView() {
        currentVisibleView = METRIC_UNITS_VIEW
        llMetricUnitsView.visibility = View.VISIBLE
        llUsUnitsView.visibility = View.GONE

        etMetricUnitHeight.text!!.clear()
        etMetricUnitWeight.text!!.clear()

        tvYourBMI.visibility = View.INVISIBLE
        tvBMIValue.visibility = View.INVISIBLE
        tvBMIType.visibility = View.INVISIBLE
        tvBMIDescription.visibility = View.INVISIBLE
    }

    /**
     * Function is used to make visible the US UNITS VIEW and hide the METRIC UNITS VIEW.
     */
    private fun makeVisibleUsUnitsView() {
        currentVisibleView = US_UNITS_VIEW
        llMetricUnitsView.visibility = View.GONE
        llUsUnitsView.visibility = View.VISIBLE

        etUsUnitWeight.text!!.clear()
        etUsUnitHeightFeet.text!!.clear()
        etUsUnitHeightInch.text!!.clear()

        tvYourBMI.visibility = View.INVISIBLE
        tvBMIValue.visibility = View.INVISIBLE
        tvBMIType.visibility = View.INVISIBLE
        tvBMIDescription.visibility = View.INVISIBLE
    }

    /**
     * Function is used to validate the input values for METRIC UNITS.
     */
    private fun validateMetricUnits(): Boolean {
        var isValid = true

        if (etMetricUnitWeight.text.toString().isEmpty()) {
            isValid = false
        } else if (etMetricUnitHeight.text.toString().isEmpty()) {
            isValid = false
        }

        return isValid
    }


    private fun validateUsUnits(): Boolean {
        var isValid = true

        if (etUsUnitWeight.text.toString().isEmpty()) {
            isValid = false
        } else if (etUsUnitHeightFeet.text.toString().isEmpty()) {
            isValid = false
        } else if (etUsUnitHeightInch.text.toString().isEmpty()) {
            isValid = false
        }

        return isValid
    }


    private fun displayBMIResult(bmi: Float) {

        val bmiLabel: String
        val bmiDescription: String

        if (java.lang.Float.compare(bmi, 15f) <= 0) {
            bmiLabel = "Muy poco peso!"
            bmiDescription = "Tienes que ir a un nuestricionista o/y aumentar tus calorías"
        } else if (java.lang.Float.compare(bmi, 15f) > 0 && java.lang.Float.compare(
                bmi,
                16f
            ) <= 0
        ) {
            bmiLabel = "Poco peso"
            bmiDescription = "Estás comiendo suficiente calorías?"
        } else if (java.lang.Float.compare(bmi, 16f) > 0 && java.lang.Float.compare(
                bmi,
                18.5f
            ) <= 0
        ) {
            bmiLabel = "Bajo peso"
            bmiDescription = "Estás comiendo las calorías recomendadas para tu constitución física?"
        } else if (java.lang.Float.compare(bmi, 18.5f) > 0 && java.lang.Float.compare(
                bmi,
                25f
            ) <= 0
        ) {
            bmiLabel = "Normal"
            bmiDescription = "Parece que estás en buena forma!"
        } else if (java.lang.Float.compare(bmi, 25f) > 0 && java.lang.Float.compare(
                bmi,
                30f
            ) <= 0
        ) {
            bmiLabel = "Sobrepeso"
            bmiDescription = "Cuida  tu peso. Aumenta el ejercicio físico!"
        } else if (java.lang.Float.compare(bmi, 30f) > 0 && java.lang.Float.compare(
                bmi,
                35f
            ) <= 0
        ) {
            bmiLabel = "Moderadamente obeso"
            bmiDescription = "Cuida de tu cuerpo!"
        } else if (java.lang.Float.compare(bmi, 35f) > 0 && java.lang.Float.compare(
                bmi,
                40f
            ) <= 0
        ) {
            bmiLabel = "Severamente obeso"
            bmiDescription = "Con ejercicio y buena alimentación puedes mejorar!"
        } else {
            bmiLabel = "Muy obeso"
            bmiDescription = "Por favor, cuídate!"
        }

        tvYourBMI.visibility = View.VISIBLE
        tvBMIValue.visibility = View.VISIBLE
        tvBMIType.visibility = View.VISIBLE
        tvBMIDescription.visibility = View.VISIBLE


        val bmiValue = BigDecimal(bmi.toDouble()).setScale(1, RoundingMode.HALF_EVEN).toString()

        tvBMIValue.text = bmiValue
        tvBMIType.text = bmiLabel
        tvBMIDescription.text = bmiDescription
    }
}