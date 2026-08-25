package androidx.appcompat.app;

import android.window.OnBackInvokedDispatcher;
import androidx.compose.foundation.gestures.snapping.SnapPositionInLayout;
import androidx.compose.foundation.pager.PagerStateKt;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class j0 implements SnapPositionInLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1064a;

    public /* synthetic */ j0(int i10) {
        this.f1064a = i10;
    }

    public static /* bridge */ /* synthetic */ OnBackInvokedDispatcher d(Object obj) {
        return (OnBackInvokedDispatcher) obj;
    }

    @Override // androidx.compose.foundation.gestures.snapping.SnapPositionInLayout
    public int position(int i10, int i11, int i12, int i13, int i14) {
        switch (this.f1064a) {
            case 4:
                return SnapPositionInLayout.Companion.CenterToCenter$lambda$0(i10, i11, i12, i13, i14);
            default:
                return PagerStateKt.SnapAlignmentStartToStart$lambda$2(i10, i11, i12, i13, i14);
        }
    }
}
