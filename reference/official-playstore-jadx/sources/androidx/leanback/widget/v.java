package androidx.leanback.widget;

import android.graphics.PointF;
import androidx.media3.session.MediaUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class v extends w {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ a0 f3149r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(a0 a0Var) {
        super(a0Var);
        this.f3149r = a0Var;
    }

    @Override // androidx.leanback.widget.w
    public final PointF b(int i10) {
        if (this.f3153b.x.w() == 0) {
            return null;
        }
        a0 a0Var = this.f3149r;
        int iL = androidx.recyclerview.widget.z0.L(a0Var.v(0));
        int i11 = ((a0Var.B & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) == 0 ? i10 >= iL : i10 <= iL) ? 1 : -1;
        return a0Var.f3069s == 0 ? new PointF(i11, 0.0f) : new PointF(0.0f, i11);
    }
}
