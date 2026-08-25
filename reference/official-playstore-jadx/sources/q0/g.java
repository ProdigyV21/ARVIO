package q0;

import android.view.accessibility.AccessibilityNodeInfo;

/* JADX INFO: loaded from: classes3.dex */
public abstract class g {
    public static j a(boolean z, int i10, int i11, int i12, int i13, boolean z5, String str, String str2) {
        return new j(new AccessibilityNodeInfo.CollectionItemInfo.Builder().setHeading(z).setColumnIndex(i10).setRowIndex(i11).setColumnSpan(i12).setRowSpan(i13).setSelected(z5).setRowTitle(str).setColumnTitle(str2).build());
    }

    public static k b(AccessibilityNodeInfo accessibilityNodeInfo, int i10, int i11) {
        AccessibilityNodeInfo child = accessibilityNodeInfo.getChild(i10, i11);
        if (child != null) {
            return new k((Object) child);
        }
        return null;
    }

    public static String c(Object obj) {
        return ((AccessibilityNodeInfo.CollectionItemInfo) obj).getColumnTitle();
    }

    public static String d(Object obj) {
        return ((AccessibilityNodeInfo.CollectionItemInfo) obj).getRowTitle();
    }

    public static AccessibilityNodeInfo.ExtraRenderingInfo e(AccessibilityNodeInfo accessibilityNodeInfo) {
        return accessibilityNodeInfo.getExtraRenderingInfo();
    }

    public static k f(AccessibilityNodeInfo accessibilityNodeInfo, int i10) {
        AccessibilityNodeInfo parent = accessibilityNodeInfo.getParent(i10);
        if (parent != null) {
            return new k((Object) parent);
        }
        return null;
    }

    public static String g(AccessibilityNodeInfo accessibilityNodeInfo) {
        return accessibilityNodeInfo.getUniqueId();
    }

    public static boolean h(AccessibilityNodeInfo accessibilityNodeInfo) {
        return accessibilityNodeInfo.isTextSelectable();
    }

    public static void i(AccessibilityNodeInfo accessibilityNodeInfo, boolean z) {
        accessibilityNodeInfo.setTextSelectable(z);
    }

    public static void j(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        accessibilityNodeInfo.setUniqueId(str);
    }
}
