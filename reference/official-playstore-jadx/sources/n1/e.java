package n1;

import androidx.compose.runtime.Composer;
import androidx.lifecycle.compose.LifecycleEffectKt;
import androidx.lifecycle.y;
import java.util.Arrays;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class e extends r implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f20451i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object[] f20452l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ y f20453m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ l f20454n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f20455o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f20456p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e(Object[] objArr, y yVar, l lVar, int i10, int i11, int i12) {
        super(2);
        this.f20451i = i12;
        this.f20452l = objArr;
        this.f20453m = yVar;
        this.f20454n = lVar;
        this.f20455o = i10;
        this.f20456p = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f20451i) {
            case 0:
                Composer composer = (Composer) obj;
                ((Number) obj2).intValue();
                Object[] objArr = this.f20452l;
                LifecycleEffectKt.LifecycleResumeEffect(Arrays.copyOf(objArr, objArr.length), this.f20453m, (l<? super i, ? extends h>) this.f20454n, composer, this.f20455o | 1, this.f20456p);
                break;
            default:
                Composer composer2 = (Composer) obj;
                ((Number) obj2).intValue();
                Object[] objArr2 = this.f20452l;
                LifecycleEffectKt.LifecycleStartEffect(Arrays.copyOf(objArr2, objArr2.length), this.f20453m, (l<? super j, ? extends k>) this.f20454n, composer2, this.f20455o | 1, this.f20456p);
                break;
        }
        return t0.f22605a;
    }
}
