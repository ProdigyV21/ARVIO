package androidx.tv.foundation.lazy.grid;

import androidx.compose.foundation.gestures.ScrollExtensionsKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.ui.semantics.CollectionInfo;
import androidx.tv.material3.w3;

/* JADX INFO: loaded from: classes3.dex */
public final class l0 implements i2.m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ b1 f5270a;

    public l0(b1 b1Var) {
        this.f5270a = b1Var;
    }

    @Override // i2.m
    public final Object a(int i10, androidx.tv.foundation.lazy.list.k0 k0Var) {
        Object objA = b1.a(this.f5270a, i10, k0Var);
        return objA == e7.a.f15033i ? objA : x6.t0.f22605a;
    }

    @Override // i2.m
    public final Object b(float f10, w3 w3Var) {
        Object objAnimateScrollBy$default = ScrollExtensionsKt.animateScrollBy$default(this.f5270a, f10, null, w3Var, 2, null);
        return objAnimateScrollBy$default == e7.a.f15033i ? objAnimateScrollBy$default : x6.t0.f22605a;
    }

    @Override // i2.m
    public final CollectionInfo collectionInfo() {
        return new CollectionInfo(-1, -1);
    }

    @Override // i2.m
    public final boolean getCanScrollForward() {
        return this.f5270a.getCanScrollForward();
    }

    @Override // i2.m
    public final float getCurrentPosition() {
        b1 b1Var = this.f5270a;
        return (((MutableIntState) b1Var.f5153a.f5233c).getIntValue() / 100000.0f) + ((MutableIntState) b1Var.f5153a.f5232b).getIntValue();
    }
}
