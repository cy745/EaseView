package com.lalilu.easeview.animatevalue

import kotlin.reflect.KProperty


abstract class MathAnimateValue<T : Comparable<T>> : AnimateValue {
    abstract var value: T
    private var needUpdate: Boolean = false

    abstract fun check(targetValue: T, value: T): Boolean
    abstract fun obtainTargetValue(): T
    abstract fun interpolate(targetValue: T, value: T): T

    override fun isNeedUpdate(): Boolean = needUpdate

    override fun update() {
        val targetValue = obtainTargetValue()
        if (value == targetValue) {
            needUpdate = false
            return
        }
        value = interpolate(targetValue, value)
        if (!check(targetValue, value)) {
            value = targetValue
        }
        if (value != targetValue) {
            needUpdate = true
        }
    }

    operator fun setValue(thisObj: Any?, property: KProperty<*>, value: T) {
        this.value = value
    }

    operator fun getValue(thisObj: Any?, property: KProperty<*>): T = this.value
}