package androidx.compose.material3;

import android.view.accessibility.AccessibilityManager;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\b\u0012\u0004\u0012\u00020\u00040\u0003B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u000b\u0010\nJ\u0015\u0010\u000e\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0010\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u0010\u0010\u000fR+\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00048B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\nR+\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00048B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\nR\u0014\u0010\u001e\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u0006\u001f"}, d2 = {"Landroidx/compose/material3/Listener;", "Landroid/view/accessibility/AccessibilityManager$AccessibilityStateChangeListener;", "Landroid/view/accessibility/AccessibilityManager$TouchExplorationStateChangeListener;", "Landroidx/compose/runtime/State;", "", "<init>", "()V", "it", "Lx6/t0;", "onAccessibilityStateChanged", "(Z)V", "onTouchExplorationStateChanged", "Landroid/view/accessibility/AccessibilityManager;", "am", "register", "(Landroid/view/accessibility/AccessibilityManager;)V", "unregister", "<set-?>", "accessibilityEnabled$delegate", "Landroidx/compose/runtime/MutableState;", "getAccessibilityEnabled", "()Z", "setAccessibilityEnabled", "accessibilityEnabled", "touchExplorationEnabled$delegate", "getTouchExplorationEnabled", "setTouchExplorationEnabled", "touchExplorationEnabled", "getValue", "()Ljava/lang/Boolean;", "value", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class Listener implements AccessibilityManager.AccessibilityStateChangeListener, AccessibilityManager.TouchExplorationStateChangeListener, State<Boolean> {

    /* JADX INFO: renamed from: accessibilityEnabled$delegate, reason: from kotlin metadata */
    private final MutableState accessibilityEnabled;

    /* JADX INFO: renamed from: touchExplorationEnabled$delegate, reason: from kotlin metadata */
    private final MutableState touchExplorationEnabled;

    public Listener() {
        Boolean bool = Boolean.FALSE;
        this.accessibilityEnabled = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        this.touchExplorationEnabled = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean getAccessibilityEnabled() {
        return ((Boolean) this.accessibilityEnabled.getValue()).booleanValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean getTouchExplorationEnabled() {
        return ((Boolean) this.touchExplorationEnabled.getValue()).booleanValue();
    }

    private final void setAccessibilityEnabled(boolean z) {
        this.accessibilityEnabled.setValue(Boolean.valueOf(z));
    }

    private final void setTouchExplorationEnabled(boolean z) {
        this.touchExplorationEnabled.setValue(Boolean.valueOf(z));
    }

    @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
    public void onAccessibilityStateChanged(boolean it) {
        setAccessibilityEnabled(it);
    }

    @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
    public void onTouchExplorationStateChanged(boolean it) {
        setTouchExplorationEnabled(it);
    }

    public final void register(AccessibilityManager am) {
        setAccessibilityEnabled(am.isEnabled());
        setTouchExplorationEnabled(am.isTouchExplorationEnabled());
        am.addTouchExplorationStateChangeListener(this);
        am.addAccessibilityStateChangeListener(this);
    }

    public final void unregister(AccessibilityManager am) {
        am.removeTouchExplorationStateChangeListener(this);
        am.removeAccessibilityStateChangeListener(this);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.compose.runtime.State
    public Boolean getValue() {
        return Boolean.valueOf(getAccessibilityEnabled() && getTouchExplorationEnabled());
    }
}
