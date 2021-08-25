package com.ravitej.sliderbutton.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.ravitej.sliderbutton.OnSliderCompleteListener
import com.ravitej.sliderbutton.R
import com.ravitej.sliderbutton.databinding.ComponentSliderButtonBinding

class SliderLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding: ComponentSliderButtonBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.component_slider_button,
        this,
        true
    )
    private lateinit var listener: OnSliderCompleteListener
    private var legalContentChecked: Boolean = false

    fun sliderListener(listener: OnSliderCompleteListener) {
        this.listener = listener
    }

    fun legalDisclaimerChecked(checked: Boolean) {
        legalContentChecked = checked
    }

    fun setButtonText(buttonName: String) {
        binding.name.text = buttonName
    }

    init {
        binding.componentSlider.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {
            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {
                Log.e("TAG - Chnage", "$startId - $endId")
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                if (legalContentChecked) {
//                    binding.componentSlider.enableTransition(R.id.transition, false)
                    listener.onComplete()
                } else {
                    motionLayout?.transitionToStart()
                }
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {
                Log.e("TAG - Trigger", "Completed")
            }
        })
    }
}