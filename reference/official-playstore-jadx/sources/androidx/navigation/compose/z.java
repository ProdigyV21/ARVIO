package androidx.navigation.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.lifecycle.compose.LifecycleEffectKt;
import androidx.navigation.h0;
import androidx.navigation.l0;
import androidx.tv.material3.CardKt;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class z extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4650i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f4651l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4652m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f4653n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f4654o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f4655p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ z(int i10, int i11, int i12, Object obj, Object obj2, Object obj3) {
        super(2);
        this.f4650i = i12;
        this.f4653n = obj;
        this.f4654o = obj2;
        this.f4655p = obj3;
        this.f4651l = i10;
        this.f4652m = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f4650i) {
            case 0:
                ((Number) obj2).intValue();
                NavHostKt.NavHost((l0) this.f4653n, (h0) this.f4654o, (Modifier) this.f4655p, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f4651l | 1), this.f4652m);
                break;
            case 1:
                ((Number) obj2).intValue();
                CardKt.CardContent((r7.p) this.f4653n, (r7.p) this.f4654o, (r7.p) this.f4655p, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f4651l | 1), this.f4652m);
                break;
            default:
                ((Number) obj2).intValue();
                LifecycleEffectKt.LifecycleEventEffect((androidx.lifecycle.p) this.f4653n, (androidx.lifecycle.y) this.f4654o, (r7.a) this.f4655p, (Composer) obj, this.f4651l | 1, this.f4652m);
                break;
        }
        return t0.f22605a;
    }
}
