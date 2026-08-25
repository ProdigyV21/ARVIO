package i2;

import androidx.tv.material3.w3;
import ka.k0;
import ka.m0;

/* JADX INFO: loaded from: classes3.dex */
public final class p extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ boolean f16066i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ k0 f16067l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ m f16068m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(boolean z, k0 k0Var, m mVar) {
        super(2);
        this.f16066i = z;
        this.f16067l = k0Var;
        this.f16068m = mVar;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        float fFloatValue = ((Number) obj).floatValue();
        float fFloatValue2 = ((Number) obj2).floatValue();
        if (this.f16066i) {
            fFloatValue = fFloatValue2;
        }
        m0.p(this.f16067l, null, 0, new w3(this.f16068m, fFloatValue, null, 1), 3);
        return Boolean.TRUE;
    }
}
