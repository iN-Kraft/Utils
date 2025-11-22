package dev.datlag.inkraft

import android.content.Context
import dev.datlag.inkraft.fetcher.FetcherHolder

class INKraft(override val context: Context) : FetcherHolder() {

    init {
        init(context)
    }

    fun isTelevision(): Boolean {
        return context.isTelevision()
    }

    fun isWatch(): Boolean {
        return context.isWatch()
    }

    companion object : FetcherHolder() {
        private val contextInstance = WeakReference<Context>(null)

        override val context: Context
            get() = contextInstance.getOrNull() ?: throw IllegalStateException("Cleared context, should not happen! Are you providing a proper applicationContext?")

        @JvmStatic
        fun init(context: Context) {
            if (contextInstance.getOrNull() == null) {
                contextInstance.set(context.applicationContext ?: context)
            }
        }

        @JvmStatic
        fun isTelevision(): Boolean {
            return context.isTelevision()
        }

        @JvmStatic
        fun isWatch(): Boolean {
            return context.isWatch()
        }
    }
}