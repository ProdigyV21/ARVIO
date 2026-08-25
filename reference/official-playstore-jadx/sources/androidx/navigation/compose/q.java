package androidx.navigation.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.saveable.SaveableStateHolder;
import androidx.lifecycle.compose.LifecycleEffectKt;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class q extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4595i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f4596l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ androidx.lifecycle.y f4597m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f4598n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ x6.o f4599o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ q(androidx.lifecycle.y yVar, Object obj, x6.o oVar, int i10, int i11) {
        super(2);
        this.f4595i = i11;
        this.f4597m = yVar;
        this.f4598n = obj;
        this.f4599o = oVar;
        this.f4596l = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        int i10 = this.f4595i;
        Composer composer = (Composer) obj;
        ((Number) obj2).intValue();
        switch (i10) {
            case 0:
                NavBackStackEntryProviderKt.LocalOwnersProvider((androidx.navigation.i) this.f4597m, (SaveableStateHolder) this.f4598n, (r7.p) this.f4599o, composer, RecomposeScopeImplKt.updateChangedFlags(this.f4596l | 1));
                break;
            case 1:
                LifecycleEffectKt.LifecycleResumeEffectImpl(this.f4597m, (n1.i) this.f4598n, (r7.l) this.f4599o, composer, this.f4596l | 1);
                break;
            default:
                LifecycleEffectKt.LifecycleStartEffectImpl(this.f4597m, (n1.j) this.f4598n, (r7.l) this.f4599o, composer, this.f4596l | 1);
                break;
        }
        return t0.f22605a;
    }
}
