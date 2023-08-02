package com.rk.presentation.common

import android.app.Activity
import android.content.Context
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

// VIEW BINDING DELEGATE

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(crossinline factory: (LayoutInflater) -> T): Lazy<T> {
    return lazy(LazyThreadSafetyMode.NONE) {
        factory(layoutInflater)
    }
}

fun <T : ViewBinding> Fragment.viewBinding(factory: (View) -> T): ReadOnlyProperty<Fragment, T> = object : ReadOnlyProperty<Fragment, T>,
    DefaultLifecycleObserver {

    private var binding: T? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T = binding ?: factory(requireView()).also {

        if (viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            viewLifecycleOwner.lifecycle.addObserver(this)
            binding = it
        }
    }

    override fun onDestroy(owner: LifecycleOwner) { binding = null }
}

// ACTIVITY EXTENSION

fun Activity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

// FRAGMENT EXTENSIONS

fun Fragment.next(id: Int, target: Int) { requireActivity().findViewById<ViewPager2>(id).currentItem = target }

fun Fragment.toast(message: String) {
    Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
}
fun Fragment.network(): Boolean {
    val connectivity = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val active = connectivity.activeNetwork
    return active != null
}

