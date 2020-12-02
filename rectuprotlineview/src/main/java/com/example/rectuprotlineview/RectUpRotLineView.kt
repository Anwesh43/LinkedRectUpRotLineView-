package com.example.rectuprotlineview

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.RectF

val parts : Int = 4
val scGap : Float = 0.02f / parts
val strokeFactor : Float = 90f
val sizeFactor : Float = 4.8f
val delay : Long = 20
val colors : Array<Int> = arrayOf(
    "#F44336",
    "#009688",
    "#03A9F4",
    "#FF9800",
    "#3F51B5"
).map {
    Color.parseColor(it)
}.toTypedArray()
val rot : Float = 90f

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawRectUpRotLine(scale : Float, w : Float, h : Float, paint : Paint) {
    val sf : Float = scale.sinify()
    val size : Float = Math.min(w, h) / sizeFactor
    save()
    translate(w / 2, h / 2)
    save()
    rotate(rot * sf.divideScale(1, parts))
    drawLine(
        0f,
        -size,
        0f,
        -size + 2 * size * sf.divideScale(0, parts),
        paint
    )
    restore()
    drawRect(
        RectF(
            0f,
            -size,
            size * sf.divideScale(2, parts),
            size
        ),
        paint
    )
    restore()
}

fun Canvas.drawRURLNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    paint.strokeCap = Paint.Cap.ROUND
    paint.color = colors[i]
    drawRectUpRotLine(
        scale,
        w,
        h,
        paint
    )
}

class RectUpRotLineView(ctx : Context) : View(ctx) {

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
}