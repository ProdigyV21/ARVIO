package androidx.tv.foundation.lazy.grid;

import androidx.compose.runtime.MutableIntState;

/* JADX INFO: loaded from: classes3.dex */
public final class j extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final j f5253l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final j f5254m;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f5255i;

    static {
        int i10 = 2;
        f5253l = new j(i10, 0);
        f5254m = new j(i10, 1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ j(int i10, int i11) {
        super(i10);
        this.f5255i = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f5255i) {
            case 0:
                ((Number) obj2).intValue();
                return new o0(1);
            default:
                b1 b1Var = (b1) obj2;
                return t7.a.E(Integer.valueOf(((MutableIntState) b1Var.f5153a.f5232b).getIntValue()), Integer.valueOf(((MutableIntState) b1Var.f5153a.f5233c).getIntValue()));
        }
    }
}
