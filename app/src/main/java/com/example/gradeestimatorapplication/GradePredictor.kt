package com.example.gradeestimatorapplication

import android.content.Context
import org.tensorflow.lite.Interpreter
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import android.content.res.AssetFileDescriptor
import java.io.FileInputStream

class GradePredictor(context: Context) {

    private var tflite: Interpreter? = null

    init {
        // load the TensorFlow Lite model file
        val model = loadModelFile(context)
        tflite = Interpreter(model)
    }

    // load the model file from assets
    private fun loadModelFile(context: Context): MappedByteBuffer {

        val fileDescriptor: AssetFileDescriptor = context.assets.openFd("model.tflite")
        val fileInputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel: FileChannel = fileInputStream.channel

        return fileChannel.map(FileChannel.MapMode.READ_ONLY, fileDescriptor.startOffset, fileDescriptor.declaredLength)
    }

    // predict the grade using the model
    fun predict(location: Float, workingTime: Float, attendance: Float): Float {

        val input = floatArrayOf(location, workingTime, attendance)
        val output = Array(1) { FloatArray(1) }  // outputs the predicted grade

        tflite?.run(arrayOf(input), output) // running the model
        return output[0][0] // returning predicted grade
    }

    fun close() {
        tflite?.close()
    }
}