package n1;

import androidx.compose.runtime.Composer;
import androidx.lifecycle.compose.LifecycleEffectKt;
import androidx.lifecycle.y;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class c extends r implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f20436i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f20437l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f20438m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ y f20439n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ l f20440o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f20441p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f20442q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c(Object obj, Object obj2, y yVar, l lVar, int i10, int i11, int i12) {
        super(2);
        this.f20436i = i12;
        this.f20437l = obj;
        this.f20438m = obj2;
        this.f20439n = yVar;
        this.f20440o = lVar;
        this.f20441p = i10;
        this.f20442q = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f20436i) {
            case 0:
                ((Number) obj2).intValue();
                LifecycleEffectKt.LifecycleResumeEffect(this.f20437l, this.f20438m, this.f20439n, this.f20440o, (Composer) obj, this.f20441p | 1, this.f20442q);
                break;
            default:
                ((Number) obj2).intValue();
                LifecycleEffectKt.LifecycleStartEffect(this.f20437l, this.f20438m, this.f20439n, this.f20440o, (Composer) obj, this.f20441p | 1, this.f20442q);
                break;
        }
        return t0.f22605a;
    }
}
