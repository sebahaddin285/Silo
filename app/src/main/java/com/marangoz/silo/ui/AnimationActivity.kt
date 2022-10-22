package com.marangoz.silo.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.OvershootInterpolator
import androidx.databinding.DataBindingUtil
import com.marangoz.silo.R
import com.marangoz.silo.databinding.ActivityAnimationBinding

class AnimationActivity : AppCompatActivity() {
    private lateinit var design : ActivityAnimationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       design = DataBindingUtil.setContentView(this,R.layout.activity_animation)
        animation()
        val counter = object : CountDownTimer(2500, 1000) {
            override fun onTick(p0: Long) {
            }
            override fun onFinish() {
                val intent = Intent(applicationContext,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        counter.start()

    }
    fun animation(){
        val scaleXAnimation = ObjectAnimator.ofFloat(design.imageView, "scaleX", 0.4f, 0.8f)
        val scaleYAnimation = ObjectAnimator.ofFloat(design.imageView, "scaleY", 0.4f, 0.8f)
        val an = AnimatorSet().apply {
            duration = 2500
            interpolator = OvershootInterpolator()
            playTogether(scaleXAnimation, scaleYAnimation)
        }
        an.start()


    }
}