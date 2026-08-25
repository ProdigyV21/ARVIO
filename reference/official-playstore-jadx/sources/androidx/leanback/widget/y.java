package androidx.leanback.widget;

import android.graphics.PointF;
import android.view.View;
import androidx.media3.session.MediaUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class y extends w {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f3177r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f3178s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ a0 f3179t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y(a0 a0Var, int i10, boolean z) {
        super(a0Var);
        this.f3179t = a0Var;
        this.f3178s = i10;
        this.f3177r = z;
        this.f3152a = -2;
    }

    @Override // androidx.leanback.widget.w
    public final PointF b(int i10) {
        int i11 = this.f3178s;
        if (i11 == 0) {
            return null;
        }
        a0 a0Var = this.f3179t;
        int i12 = ((a0Var.B & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) == 0 ? i11 >= 0 : i11 <= 0) ? 1 : -1;
        return a0Var.f3069s == 0 ? new PointF(i12, 0.0f) : new PointF(0.0f, i12);
    }

    @Override // androidx.leanback.widget.w
    public final void d() {
        super.d();
        this.f3178s = 0;
        View viewR = this.f3153b.x.r(this.f3152a);
        if (viewR != null) {
            this.f3179t.o1(viewR, viewR.findFocus(), true, 0, 0);
        }
    }
}
