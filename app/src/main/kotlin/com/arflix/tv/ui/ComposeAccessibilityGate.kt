package com.arflix.tv.ui

import android.accessibilityservice.AccessibilityServiceInfo
import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityManager

/**
 * Keeps Compose from maintaining its accessibility tree for services that cannot read it.
 *
 * Compose rebuilds and diffs the semantics of the whole screen while any accessibility service
 * is enabled. Many TVs run one that never reads app content, such as a custom launcher's
 * key-remapping service. On a TCL Android 14 TV that work took ~15% of the main thread on Home
 * and raised janky frames from ~20% to ~28%. A service without
 * [AccessibilityServiceInfo.CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT] is never given the node tree,
 * so for those services the tree is pure cost.
 *
 * When every enabled service lacks that capability, Compose's cached service list is replaced
 * by an empty one, which its delegate reads as "accessibility off". As soon as a service that
 * can read the screen (TalkBack, Switch Access, a password manager) is enabled, Compose's own
 * listener clears the cache and the real list applies again. This uses Compose internals, so
 * every step is guarded: if the fields are not found, nothing changes.
 */
internal object ComposeAccessibilityGate {
    private var listenersInstalled = false
    private var currentRoot = java.lang.ref.WeakReference<View>(null)

    fun install(root: View) {
        val manager = root.context.getSystemService(Context.ACCESSIBILITY_SERVICE) as? AccessibilityManager ?: return
        currentRoot = java.lang.ref.WeakReference(root)
        apply(root, manager)
        if (listenersInstalled) return
        listenersInstalled = true
        // Compose resets its cached list on these changes; apply again once it has.
        manager.addAccessibilityStateChangeListener { reapply(manager) }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            manager.addAccessibilityServicesStateChangeListener { reapply(manager) }
        }
    }

    private fun reapply(manager: AccessibilityManager) {
        val root = currentRoot.get() ?: return
        root.post { apply(root, manager) }
    }

    private fun apply(root: View, manager: AccessibilityManager) {
        if (!manager.isEnabled || anyServiceCanReadScreen(manager)) return
        runCatching { forEachComposeView(root) { view -> silence(view) } }
    }

    internal fun anyServiceCanReadScreen(manager: AccessibilityManager): Boolean =
        manager.isTouchExplorationEnabled ||
            manager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_ALL_MASK).any(::canReadScreen)

    internal fun canReadScreen(info: AccessibilityServiceInfo): Boolean =
        info.capabilities and AccessibilityServiceInfo.CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT != 0

    private fun forEachComposeView(view: View, action: (View) -> Unit) {
        if (view.javaClass.name == COMPOSE_VIEW_CLASS) action(view)
        if (view is ViewGroup) for (i in 0 until view.childCount) forEachComposeView(view.getChildAt(i), action)
    }

    private fun silence(composeView: View) {
        val delegate = composeView.javaClass.getDeclaredField("composeAccessibilityDelegate")
            .apply { isAccessible = true }.get(composeView) ?: return
        delegate.javaClass.getDeclaredField("_enabledServices")
            .apply { isAccessible = true }.set(delegate, emptyList<AccessibilityServiceInfo>())
    }

    private const val COMPOSE_VIEW_CLASS = "androidx.compose.ui.platform.AndroidComposeView"
}
