package com.lalilu.easeview.animatevalue

interface AnimateValueProvider {
    val values: MutableList<AnimateValue>

    fun update() = values.forEach(AnimateValue::update)
    fun shouldPostUpdate() = values.any(AnimateValue::isNeedUpdate)

    fun registerValue(animateValue: AnimateValue) = values.add(animateValue)
    fun unregisterValue(animateValue: AnimateValue) = values.remove(animateValue)
}