package n1;

import androidx.compose.runtime.Composer;
import androidx.lifecycle.compose.LifecycleEffectKt;
import androidx.lifecycle.y;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class d extends r implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f20443i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f20444l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f20445m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f20446n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ y f20447o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ l f20448p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f20449q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f20450r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d(Object obj, Object obj2, Object obj3, y yVar, l lVar, int i10, int i11, int i12) {
        super(2);
        this.f20443i = i12;
        this.f20444l = obj;
        this.f20445m = obj2;
        this.f20446n = obj3;
        this.f20447o = yVar;
        this.f20448p = lVar;
        this.f20449q = i10;
        this.f20450r = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f20443i) {
            case 0:
                ((Number) obj2).intValue();
                LifecycleEffectKt.LifecycleResumeEffect(this.f20444l, this.f20445m, this.f20446n, this.f20447o, this.f20448p, (Composer) obj, this.f20449q | 1, this.f20450r);
                break;
            default:
                ((Number) obj2).intValue();
                LifecycleEffectKt.LifecycleStartEffect(this.f20444l, this.f20445m, this.f20446n, this.f20447o, this.f20448p, (Composer) obj, this.f20449q | 1, this.f20450r);
                break;
        }
        return t0.f22605a;
    }
}
