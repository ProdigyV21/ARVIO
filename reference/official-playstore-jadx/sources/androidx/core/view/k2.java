package androidx.core.view;

import android.view.View;
import android.view.ViewParent;

/* JADX INFO: loaded from: classes3.dex */
public abstract class k2 {
    public static boolean a(ViewParent viewParent, View view, float f10, float f11, boolean z) {
        return viewParent.onNestedFling(view, f10, f11, z);
    }

    public static boolean b(ViewParent viewParent, View view, float f10, float f11) {
        return viewParent.onNestedPreFling(view, f10, f11);
    }

    public static void c(ViewParent viewParent, View view, int i10, int i11, int[] iArr) {
        viewParent.onNestedPreScroll(view, i10, i11, iArr);
    }

    public static void d(ViewParent viewParent, View view, int i10, int i11, int i12, int i13) {
        viewParent.onNestedScroll(view, i10, i11, i12, i13);
    }

    public static void e(ViewParent viewParent, View view, View view2, int i10) {
        viewParent.onNestedScrollAccepted(view, view2, i10);
    }

    public static boolean f(ViewParent viewParent, View view, View view2, int i10) {
        return viewParent.onStartNestedScroll(view, view2, i10);
    }

    public static void g(ViewParent viewParent, View view) {
        viewParent.onStopNestedScroll(view);
    }
}
