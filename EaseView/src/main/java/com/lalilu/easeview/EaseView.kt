package com.lalilu.easeview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.lalilu.easeview.animatevalue.AnimateValue
import com.lalilu.easeview.animatevalue.AnimateValueProvider

abstract class EaseView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), AnimateValueProvider {
    override val values = mutableListOf<AnimateValue>()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (!onPreDraw(canvas)) return
        if (!onDoDraw(canvas)) return
        onPostDraw(canvas)
    }

    /**
     * 此处进行Draw的预处理
     *
     * @return 返回值决定是否进行接下来的draw操作
     */
    open fun onPreDraw(canvas: Canvas): Boolean {
        update()
        return true
    }

    /**
     * 此处进行实际的Draw处理
     *
     * @return 返回值决定是否进行接下来的draw操作
     */
    open fun onDoDraw(canvas: Canvas): Boolean {
        return true
    }

    /**
     * 此处进行Draw的后处理
     */
    open fun onPostDraw(canvas: Canvas) {
        if (shouldPostUpdate()) {
            postInvalidate()
        }
    }
}