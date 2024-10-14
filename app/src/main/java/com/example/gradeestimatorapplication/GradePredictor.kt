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
        val model = loadModelFile(context)
        tflite = Interpreter(model)
    }

    private fun loadModelFile(context: Context): MappedByteBuffer {
        val fileDescriptor: AssetFileDescriptor = context.assets.openFd("model.tflite")
        val fileInputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel: FileChannel = fileInputStream.channel
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, fileDescriptor.startOffset, fileDescriptor.declaredLength)
    }

    fun predict(location: Float, workingTime: Float, attendance: Float): Float {
        val input = arrayOf(floatArrayOf(location, workingTime, attendance))
        val output = FloatArray(1) // Adjust based on model output
        tflite?.run(input, output)
        return output[0]
    }

    fun close() {
        tflite?.close()
    }
}