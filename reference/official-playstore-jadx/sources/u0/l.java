package u0;

import android.widget.PopupWindow;

/* JADX INFO: loaded from: classes3.dex */
public abstract class l {
    public static boolean a(PopupWindow popupWindow) {
        return popupWindow.getOverlapAnchor();
    }

    public static int b(PopupWindow popupWindow) {
        return popupWindow.getWindowLayoutType();
    }

    public static void c(PopupWindow popupWindow, boolean z) {
        popupWindow.setOverlapAnchor(z);
    }

    public static void d(PopupWindow popupWindow, int i10) {
        popupWindow.setWindowLayoutType(i10);
    }
}
