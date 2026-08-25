package n1;

import androidx.compose.runtime.Composer;
import androidx.lifecycle.compose.LifecycleEffectKt;
import androidx.lifecycle.y;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class b extends r implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f20430i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f20431l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ y f20432m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ l f20433n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f20434o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f20435p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b(Object obj, y yVar, l lVar, int i10, int i11, int i12) {
        super(2);
        this.f20430i = i12;
        this.f20431l = obj;
        this.f20432m = yVar;
        this.f20433n = lVar;
        this.f20434o = i10;
        this.f20435p = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f20430i) {
            case 0:
                ((Number) obj2).intValue();
                LifecycleEffectKt.LifecycleResumeEffect(this.f20431l, this.f20432m, (l<? super i, ? extends h>) this.f20433n, (Composer) obj, this.f20434o | 1, this.f20435p);
                break;
            default:
                ((Number) obj2).intValue();
                LifecycleEffectKt.LifecycleStartEffect(this.f20431l, this.f20432m, (l<? super j, ? extends k>) this.f20433n, (Composer) obj, this.f20434o | 1, this.f20435p);
                break;
        }
        return t0.f22605a;
    }
}
