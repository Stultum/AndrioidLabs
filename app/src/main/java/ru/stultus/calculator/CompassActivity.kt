package ru.stultus.calculator

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.lifecycle.coroutineScope
import kotlinx.android.synthetic.main.activity_compass.*

class CompassActivity : AppCompatActivity(), SensorEventListener {

    var manager: SensorManager? = null
    var currentDegree: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.coroutineScope
        val db =
        setContentView(R.layout.activity_compass)
        manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()
        manager?.registerListener(this, manager?.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        super.onPause()
        manager?.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val degree = event?.values?.get(0)?.toInt()
        degrees_text.text = degree.toString()
        val rotationAnim = RotateAnimation(currentDegree.toFloat(), (-degree!!).toFloat(), Animation.RELATIVE_TO_SELF,
        0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotationAnim.duration = 210
        rotationAnim.fillAfter = true
        currentDegree = -degree
        compass.startAnimation(rotationAnim)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}