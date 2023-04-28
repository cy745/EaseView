package com.lalilu.easeview.animatevalue

class FloatListAnimateValue(
    private val defaultTargetValue: Float = 0f,
    precision: Float = 0.01f,
    stepPercent: Float = 0.05f
) : AnimateValue {
    private val valueMap = LinkedHashMap<Int, Float>()
    private val targetMap = LinkedHashMap<Int, Float>()

    private val floatValue = object : FloatValue(defaultTargetValue, precision, stepPercent) {
        private var targetValueTemp: Float = 0f
        private var resultValueTemp: Float = 0f

        override fun isNeedUpdate(): Boolean {
            return needUpdate || valueMap.any { !check(getTargetValueByIndex(it.key), it.value) }
        }

        override fun update() {
            for ((index, currentValue) in valueMap) {
                targetValueTemp = getTargetValueByIndex(index)
                resultValueTemp = interpolate(targetValueTemp, currentValue)

                valueMap[index] = if (!check(targetValueTemp, resultValueTemp)) {
                    targetValueTemp
                } else {
                    needUpdate = true
                    resultValueTemp
                }
            }
        }
    }

    override fun isNeedUpdate(): Boolean = floatValue::isNeedUpdate.invoke()
    override fun update() = floatValue::update.invoke()

    fun getValueByIndex(index: Int): Float {
        if (valueMap[index] == null) valueMap[index] = 0f
        return valueMap[index]!!
    }

    fun getTargetValueByIndex(index: Int): Float {
        if (targetMap[index] == null) targetMap[index] = defaultTargetValue
        return targetMap[index]!!
    }

    fun updateTargetValue(index: Int, targetValue: Float) {
        targetMap[index] = targetValue
    }
}