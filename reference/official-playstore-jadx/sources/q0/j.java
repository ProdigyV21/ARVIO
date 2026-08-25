package q0;

import android.view.accessibility.AccessibilityNodeInfo;

/* JADX INFO: loaded from: classes3.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f21317a;

    public j(AccessibilityNodeInfo.CollectionItemInfo collectionItemInfo) {
        this.f21317a = collectionItemInfo;
    }

    public static j a(int i10, int i11, int i12, int i13, boolean z) {
        return new j(AccessibilityNodeInfo.CollectionItemInfo.obtain(i10, i11, i12, i13, false, z));
    }
}
