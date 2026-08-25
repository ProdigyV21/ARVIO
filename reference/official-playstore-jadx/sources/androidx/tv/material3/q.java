package androidx.tv.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;

/* JADX INFO: loaded from: classes3.dex */
public final class q extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f6313i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.q f6314l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.p f6315m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Modifier f6316n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ r7.p f6317o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ r7.p f6318p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ o f6319q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ MutableInteractionSource f6320r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f6321s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ int f6322t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ q(r7.q qVar, r7.p pVar, Modifier modifier, r7.p pVar2, r7.p pVar3, o oVar, MutableInteractionSource mutableInteractionSource, int i10, int i11, int i12) {
        super(2);
        this.f6313i = i12;
        this.f6314l = qVar;
        this.f6315m = pVar;
        this.f6316n = modifier;
        this.f6317o = pVar2;
        this.f6318p = pVar3;
        this.f6319q = oVar;
        this.f6320r = mutableInteractionSource;
        this.f6321s = i10;
        this.f6322t = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f6313i) {
            case 0:
                ((Number) obj2).intValue();
                CardContainerKt.StandardCardContainer(this.f6314l, this.f6315m, this.f6316n, this.f6317o, this.f6318p, this.f6319q, this.f6320r, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f6321s | 1), this.f6322t);
                break;
            default:
                ((Number) obj2).intValue();
                CardContainerKt.WideCardContainer(this.f6314l, this.f6315m, this.f6316n, this.f6317o, this.f6318p, this.f6319q, this.f6320r, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f6321s | 1), this.f6322t);
                break;
        }
        return x6.t0.f22605a;
    }
}
