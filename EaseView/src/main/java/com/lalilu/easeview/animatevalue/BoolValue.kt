package com.lalilu.easeview.animatevalue

class BoolValue(targetValue: Float = 1f) : AnimateValue {

    var value: Boolean = false
    val animateValue: Float
        get() = floatValue.value

    private val floatValue = object : FloatValue(targetValue) {
        override fun obtainTargetValue(): Float = if (this@BoolValue.value) targetValue else 0f
    }

    override fun isNeedUpdate(): Boolean = floatValue.isNeedUpdate()
    override fun update() = floatValue::update.invoke()
}