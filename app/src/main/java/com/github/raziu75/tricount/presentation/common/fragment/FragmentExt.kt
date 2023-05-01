package com.github.raziu75.tricount.presentation.common.fragment

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit

fun FragmentManager.replaceFragmentLazy(
    @IdRes containerId: Int,
    tag: String,
    addToBackStack: Boolean = false,
    lazyFragment: () -> Fragment,
) {
    this.commit {
        replace(
            containerId,
            lazyFragment(),
            tag,
        )
        if (addToBackStack) addToBackStack(null)

        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
    }
}
