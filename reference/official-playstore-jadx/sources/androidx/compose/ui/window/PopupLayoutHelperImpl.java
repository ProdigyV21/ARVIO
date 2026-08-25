package androidx.compose.ui.window;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0012\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ'\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ'\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Landroidx/compose/ui/window/PopupLayoutHelperImpl;", "Landroidx/compose/ui/window/PopupLayoutHelper;", "<init>", "()V", "Landroid/view/View;", "composeView", "Landroid/graphics/Rect;", "outRect", "Lx6/t0;", "getWindowVisibleDisplayFrame", "(Landroid/view/View;Landroid/graphics/Rect;)V", "", "width", "height", "setGestureExclusionRects", "(Landroid/view/View;II)V", "Landroid/view/WindowManager;", "windowManager", "popupView", "Landroid/view/ViewGroup$LayoutParams;", "params", "updateViewLayout", "(Landroid/view/WindowManager;Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
class PopupLayoutHelperImpl implements PopupLayoutHelper {
    @Override // androidx.compose.ui.window.PopupLayoutHelper
    public void getWindowVisibleDisplayFrame(View composeView, Rect outRect) {
        composeView.getWindowVisibleDisplayFrame(outRect);
    }

    @Override // androidx.compose.ui.window.PopupLayoutHelper
    public void setGestureExclusionRects(View composeView, int width, int height) {
    }

    @Override // androidx.compose.ui.window.PopupLayoutHelper
    public void updateViewLayout(WindowManager windowManager, View popupView, ViewGroup.LayoutParams params) {
        windowManager.updateViewLayout(popupView, params);
    }
}
