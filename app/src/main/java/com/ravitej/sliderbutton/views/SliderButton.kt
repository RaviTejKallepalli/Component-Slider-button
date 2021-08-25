package com.ravitej.sliderbutton.views

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View

class SliderButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bg_color = Color.BLACK
    private val button_color = Color.WHITE
    private val path = Path()
    private var move_x = 72.0
    private var old_x = 72.0

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = bg_color
        canvas.drawRoundRect(0.0f, 0.0f, 300.0.toPx, 72.0.toPx, 32.0.toPx, 32.0.toPx, paint)

        paint.color = button_color
        canvas.drawRoundRect(0.0f, 0.0f, move_x.toPx, 72.0.toPx, 32.0.toPx, 32.0.toPx, paint)

    }

    override fun performClick(): Boolean {
        return super.performClick()
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            // A gesture is starting, move the path to the pointer's location
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(event.x, event.y)
            }

            // Follow the pointer as it moves
            MotionEvent.ACTION_MOVE -> {
                if (old_x < 300.toPx) {
                    old_x = move_x
                    move_x = old_x + 2
                }
            }

            // A gesture finished, stop drawing
            MotionEvent.ACTION_UP -> {
            }

            else -> return false
        }
        invalidate()
        return true
    }
}


val Number.toPx
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    )