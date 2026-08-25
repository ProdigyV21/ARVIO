package i2;

import androidx.compose.foundation.gestures.ScrollExtensionsKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.semantics.CollectionInfo;
import androidx.tv.foundation.lazy.list.k0;
import androidx.tv.foundation.lazy.list.m0;
import androidx.tv.material3.w3;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class n implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ m0 f16058a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ boolean f16059b;

    public n(m0 m0Var, boolean z) {
        this.f16058a = m0Var;
        this.f16059b = z;
    }

    @Override // i2.m
    public final Object a(int i10, k0 k0Var) {
        Saver saver = m0.z;
        Object objD = this.f16058a.d(i10, 0, k0Var);
        return objD == e7.a.f15033i ? objD : t0.f22605a;
    }

    @Override // i2.m
    public final Object b(float f10, w3 w3Var) {
        Object objAnimateScrollBy$default = ScrollExtensionsKt.animateScrollBy$default(this.f16058a, f10, null, w3Var, 2, null);
        return objAnimateScrollBy$default == e7.a.f15033i ? objAnimateScrollBy$default : t0.f22605a;
    }

    @Override // i2.m
    public final CollectionInfo collectionInfo() {
        return this.f16059b ? new CollectionInfo(-1, 1) : new CollectionInfo(1, -1);
    }

    @Override // i2.m
    public final boolean getCanScrollForward() {
        return this.f16058a.getCanScrollForward();
    }

    @Override // i2.m
    public final float getCurrentPosition() {
        m0 m0Var = this.f16058a;
        return (m0Var.b() / 100000.0f) + m0Var.a();
    }
}
