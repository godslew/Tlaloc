package handler

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import dagger.hilt.android.qualifiers.ApplicationContext
import ui.debug.DebugActivity
import javax.inject.Inject
import kotlin.math.sqrt

class DebugHandler
  @Inject
  constructor(
    @ApplicationContext private val context: Context,
  ) : ShakeDetector.OnShakeListener {
    private var shakeDetector: ShakeDetector? = null

    fun initialize() {
      shakeDetector = ShakeDetector(context, this)
      shakeDetector?.start()
    }

    fun onStop() {
      shakeDetector?.stop()
    }

    override fun onShake() {
      context.startActivity(
        Intent(context, DebugActivity::class.java).apply {
          addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        },
      )
    }
  }

class ShakeDetector(
  context: Context,
  private val shakeListener: OnShakeListener,
) : SensorEventListener {
  interface OnShakeListener {
    fun onShake()
  }

  private val sensorManager: SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
  private val accelerometer: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
  private var shakeThreshold = 12.0f
  private var lastShakeTime: Long = 0

  fun start() {
    accelerometer?.also { accel ->
      sensorManager.registerListener(this, accel, SensorManager.SENSOR_DELAY_UI)
    }
  }

  fun stop() {
    sensorManager.unregisterListener(this)
  }

  override fun onSensorChanged(event: SensorEvent?) {
    event?.let {
      if (it.sensor.type == Sensor.TYPE_ACCELEROMETER) {
        val x = it.values[0]
        val y = it.values[1]
        val z = it.values[2]

        val acceleration = sqrt(x * x + y * y + z * z) - SensorManager.GRAVITY_EARTH

        if (acceleration > shakeThreshold) {
          val currentTime = System.currentTimeMillis()
          if (currentTime - lastShakeTime > 1000) { // 1秒間の遅延
            lastShakeTime = currentTime
            shakeListener.onShake()
          }
        }
      }
    }
  }

  override fun onAccuracyChanged(
    sensor: Sensor?,
    accuracy: Int,
  ) {
    // センサーの精度が変更された場合の処理
  }
}
