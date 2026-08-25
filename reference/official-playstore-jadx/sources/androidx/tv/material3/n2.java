package androidx.tv.material3;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;

/* JADX INFO: loaded from: classes3.dex */
public final class n2 extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ p1 f6231i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Density f6232l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MutableState f6233m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n2(p1 p1Var, Density density, MutableState mutableState) {
        super(2);
        this.f6231i = p1Var;
        this.f6232l = density;
        this.f6233m = mutableState;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((IntSize) obj).getPackedValue();
        long packedValue = ((IntSize) obj2).getPackedValue();
        if (this.f6231i.a() == q1.f6329i) {
            this.f6233m.setValue(Dp.m5676boximpl(this.f6232l.mo282toDpu2uoSUM(IntSize.m5844getWidthimpl(packedValue))));
        }
        return x6.t0.f22605a;
    }
}
