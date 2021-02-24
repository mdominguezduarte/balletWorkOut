package com.example.balletworkout

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_ballet.*
import java.util.*
import kotlin.collections.ArrayList

class BalletActivity : AppCompatActivity(), TextToSpeech.OnInitListener  {

    private var restTimer: CountDownTimer? =
        null
    private var restProgress =
        0

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0

    private var exerciseList: ArrayList<ExerciseModel>? = null

    // Current Position of Exercise.
    private var currentExercisePosition = -1

    private var tts: TextToSpeech? = null // Variable for Text to Speech


    private var player: MediaPlayer? = null
    // END

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ballet)

        setSupportActionBar(toolbar_ballet_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Navigate the activity on click on back button of action bar.
        toolbar_ballet_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        tts = TextToSpeech(this, this)

        exerciseList = Constants.defaultExerciseList()

        setupRestView() // REST View is set in this function
    }

    public override fun onDestroy() {
        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }

        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }

        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }

        // TODO (Step 3 - When the activity is destroyed if the media player instance is not null then stop it.)
        // START
        if(player != null){
            player!!.stop()
        }
        // END

        super.onDestroy()
    }


    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.ROOT)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")
            }

        } else {
            Log.e("TTS", "Initialization Failed!")
        }
    }


    private fun setupRestView() {


        try {
            val soundURI =
                Uri.parse("android.resource://com.example.balletworkout/" + R.raw.press_start)
            player = MediaPlayer.create(applicationContext, soundURI)
            player!!.isLooping = false // Sets the player to be looping or non-looping.
            player!!.start() // Starts Playback.
        } catch (e: Exception) {
            e.printStackTrace()
        }
        // END

        // Here according to the view make it visible as this is Rest View so rest view is visible and exercise view is not.
        llRestView.visibility = View.VISIBLE
        llExerciseView.visibility = View.GONE


        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }

        tvUpcomingExerciseName.text = exerciseList!![currentExercisePosition + 1].getName()


        setRestProgressBar()
    }


    private fun setRestProgressBar() {

        progressBar.progress = restProgress // Sets the current progress to the specified value.


        // Here we have started a timer of 10 seconds so the 10000 is milliseconds is 10 seconds and the countdown interval is 1 second so it 1000.
        restTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++ // It is increased to ascending order
                progressBar.progress = 10 - restProgress // Indicates progress bar progress
                tvTimer.text =
                    (10 - restProgress).toString()  // Current progress is set to text view in terms of seconds.
            }

            override fun onFinish() {
                currentExercisePosition++

                setupExerciseView()
            }
        }.start()
    }


    private fun setupExerciseView() {

        // Here according to the view make it visible as this is Exercise View so exercise view is visible and rest view is not.
        llRestView.visibility = View.GONE
        llExerciseView.visibility = View.VISIBLE


        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }


        ivImage.setImageResource(exerciseList!![currentExercisePosition].getImage())
        tvExerciseName.text = exerciseList!![currentExercisePosition].getName()

        speakOut(exerciseList!![currentExercisePosition].getName())

        setExerciseProgressBar()
    }


    private fun setExerciseProgressBar() {

        progressBarExercise.progress = exerciseProgress

        exerciseTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                progressBarExercise.progress = 30 - exerciseProgress
                tvExerciseTimer.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {
                if (currentExercisePosition < 11) {
                    setupRestView()
                } else {

                    Toast.makeText(
                        this@BalletActivity,
                        "Congratulations! You have completed the 7 minutes workout.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }.start()
    }


    private fun speakOut(text: String) {
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
}