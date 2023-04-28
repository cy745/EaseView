package com.lalilu.easeview.animatevalue

class BoolValue(
    defaultValue: Boolean = false,
    var trueTargetValue: Float = 1f,
    var falseTargetValue: Float = 0f,
    var precision: Float = 0.01f,
    var stepPercent: Float = 0.05f
) : AnimateValue {

    var value: Boolean = defaultValue
    val animateValue: Float
        get() = floatValue.value

    private val floatValue = object : FloatValue(trueTargetValue, precision, stepPercent) {
        override fun obtainTargetValue(): Float {
            return if (this@BoolValue.value) trueTargetValue else falseTargetValue
        }
    }

    override fun isNeedUpdate(): Boolean = floatValue.isNeedUpdate()
    override fun update() = floatValue::update.invoke()
}