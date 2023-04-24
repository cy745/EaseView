package com.lalilu.easeview.animatevalue

class FloatListAnimateValue : AnimateValue {
    private val list = LinkedHashMap<Int, FloatValue>()

    fun getValueByIndex(index: Int): Float {
        return (list[index] ?: FloatValue(0f).also { list[index] = it }).value
    }

    fun updateTargetValue(index: Int, targetValue: Float) {
        (list[index] ?: FloatValue(0f).also { list[index] = it }).targetValue = targetValue
    }

    override fun isNeedUpdate(): Boolean = list.values.any(AnimateValue::isNeedUpdate)

    override fun update() {
        list.values.forEach(AnimateValue::update)
    }
}