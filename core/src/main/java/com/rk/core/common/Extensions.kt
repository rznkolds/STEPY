package com.rk.core.common

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.net.ConnectivityManager
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.ViewPager2
import com.rk.core.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Locale
import kotlin.coroutines.CoroutineContext
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import kotlin.time.Duration.Companion.seconds

// VIEW BINDING DELEGATE

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(crossinline factory: (LayoutInflater) -> T): Lazy<T> {
    return lazy(LazyThreadSafetyMode.NONE) {
        factory(layoutInflater)
    }
}

fun <T : ViewBinding> Fragment.viewBinding(factory: (View) -> T): ReadOnlyProperty<Fragment, T> = object : ReadOnlyProperty<Fragment, T>, DefaultLifecycleObserver {

    private var binding: T? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T = binding ?: factory(requireView()).also {

        if (viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            viewLifecycleOwner.lifecycle.addObserver(this)
            binding = it
        }
    }

    override fun onDestroy(owner: LifecycleOwner) { binding = null }
}

// FRAGMENT EXTENSIONS

fun Fragment.toast(message: String) = Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()

fun Fragment.next(id: Int, target: Int) { requireActivity().findViewById<ViewPager2>(id).currentItem = target }

fun Fragment.network(): Boolean {
    val connectivity = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val active = connectivity.activeNetwork
    return active != null
}

// FLOW EXTENSIONS

fun <T> Flow<T>.collect(lifecycleOwner: LifecycleOwner, function: (T) -> Unit) {
    lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            collect {
                function(it)
            }
        }
    }
}

fun <T> Flow<T>.collect(scope: CoroutineScope, function: (T) -> Unit) {
    scope.launch {
        collect {
            function(it)
        }
    }
}

// SERVICE EXTENSIONS

fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

// COMMON USES EXTENSIONS

fun Int.convert(): String {

    println("Kilometer : ${this / 1000.0}")

    return String.format("%.1f", (this / 1000).toDouble())
}

