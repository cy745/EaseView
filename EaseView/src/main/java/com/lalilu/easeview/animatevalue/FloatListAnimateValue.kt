package com.lalilu.easeview.animatevalue

class FloatListAnimateValue(
    val defaultTargetValue: Float = 0f,
    precision: Float = 0.01f,
    stepPercent: Float = 0.05f
) : FloatValue(defaultTargetValue, precision, stepPercent) {
    private val valueMap = LinkedHashMap<Int, Float>()
    private val targetMap = LinkedHashMap<Int, Float>()

    fun getValueByIndex(index: Int): Float = valueMap[index] ?: 0f

    fun getTargetValueByIndex(index: Int): Float {
        if (targetMap[index] == null) {
            targetMap[index] = defaultTargetValue
        }
        return targetMap[index]!!
    }

    fun updateTargetValue(index: Int, targetValue: Float) {
        targetMap[index] = targetValue
    }

    override fun isNeedUpdate(): Boolean = needUpdate || valueMap.any {
        !check(getTargetValueByIndex(it.key), it.value)
    }

    private var targetValueTemp: Float = 0f
    private var resultValueTemp: Float = 0f

    override fun update() {
        for ((index, currentValue) in valueMap) {
            targetValueTemp = getTargetValueByIndex(index)
            resultValueTemp = interpolate(targetValue, currentValue)

            valueMap[index] = if (!check(targetValue, resultValueTemp)) {
                targetValue
            } else {
                needUpdate = true
                resultValueTemp
            }
        }
    }
}