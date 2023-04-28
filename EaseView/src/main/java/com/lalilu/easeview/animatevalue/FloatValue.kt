package com.lalilu.easeview.animatevalue

import kotlin.math.abs

open class FloatValue(
    var targetValue: Float,
    var precision: Float = 0.01f,
    var stepPercent: Float = 0.05f
) : MathAnimateValue<Float>() {
    override var value: Float = 0f

    override fun obtainTargetValue(): Float = targetValue

    override fun interpolate(targetValue: Float, value: Float): Float {
        return value + (targetValue - value) * stepPercent
    }

    override fun check(targetValue: Float, value: Float): Boolean {
        return abs(targetValue - value) > precision
    }
}