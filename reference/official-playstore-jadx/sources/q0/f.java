package q0;

import android.view.accessibility.AccessibilityNodeInfo;

/* JADX INFO: loaded from: classes3.dex */
public abstract class f {
    public static Object a(int i10, float f10, float f11, float f12) {
        return new AccessibilityNodeInfo.RangeInfo(i10, f10, f11, f12);
    }

    public static CharSequence b(AccessibilityNodeInfo accessibilityNodeInfo) {
        return accessibilityNodeInfo.getStateDescription();
    }

    public static void c(AccessibilityNodeInfo accessibilityNodeInfo, CharSequence charSequence) {
        accessibilityNodeInfo.setStateDescription(charSequence);
    }
}
