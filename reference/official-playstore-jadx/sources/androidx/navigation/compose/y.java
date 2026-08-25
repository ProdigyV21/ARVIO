package androidx.navigation.compose;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.navigation.l0;
import androidx.tv.material3.CardKt;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class y extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4637i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Modifier f4638l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4639m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f4640n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f4641o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f4642p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f4643q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f4644r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f4645s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ Object f4646t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ Object f4647u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ Object f4648v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ x6.o f4649w;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y(l0 l0Var, String str, Modifier modifier, Alignment alignment, String str2, r7.l lVar, r7.l lVar2, r7.l lVar3, r7.l lVar4, r7.l lVar5, int i10, int i11) {
        super(2);
        this.f4641o = l0Var;
        this.f4642p = str;
        this.f4638l = modifier;
        this.f4644r = alignment;
        this.f4643q = str2;
        this.f4645s = lVar;
        this.f4646t = lVar2;
        this.f4647u = lVar3;
        this.f4648v = lVar4;
        this.f4649w = lVar5;
        this.f4639m = i10;
        this.f4640n = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f4637i) {
            case 0:
                ((Number) obj2).intValue();
                l0 l0Var = (l0) this.f4641o;
                String str = (String) this.f4642p;
                Alignment alignment = (Alignment) this.f4644r;
                String str2 = (String) this.f4643q;
                r7.l lVar = (r7.l) this.f4645s;
                r7.l lVar2 = (r7.l) this.f4646t;
                r7.l lVar3 = (r7.l) this.f4647u;
                r7.l lVar4 = (r7.l) this.f4648v;
                r7.l lVar5 = (r7.l) this.f4649w;
                NavHostKt.NavHost(l0Var, str, this.f4638l, alignment, str2, lVar, lVar2, lVar3, lVar4, lVar5, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f4639m | 1), this.f4640n);
                break;
            default:
                ((Number) obj2).intValue();
                r7.a aVar = (r7.a) this.f4641o;
                r7.a aVar2 = (r7.a) this.f4642p;
                androidx.tv.material3.w wVar = (androidx.tv.material3.w) this.f4643q;
                androidx.tv.material3.n nVar = (androidx.tv.material3.n) this.f4644r;
                androidx.tv.material3.v vVar = (androidx.tv.material3.v) this.f4645s;
                androidx.tv.material3.m mVar = (androidx.tv.material3.m) this.f4646t;
                androidx.tv.material3.r rVar = (androidx.tv.material3.r) this.f4647u;
                MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) this.f4648v;
                r7.q qVar = (r7.q) this.f4649w;
                CardKt.Card(aVar, this.f4638l, aVar2, wVar, nVar, vVar, mVar, rVar, mutableInteractionSource, qVar, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f4639m | 1), this.f4640n);
                break;
        }
        return t0.f22605a;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y(r7.a aVar, Modifier modifier, r7.a aVar2, androidx.tv.material3.w wVar, androidx.tv.material3.n nVar, androidx.tv.material3.v vVar, androidx.tv.material3.m mVar, androidx.tv.material3.r rVar, MutableInteractionSource mutableInteractionSource, r7.q qVar, int i10, int i11) {
        super(2);
        this.f4641o = aVar;
        this.f4638l = modifier;
        this.f4642p = aVar2;
        this.f4643q = wVar;
        this.f4644r = nVar;
        this.f4645s = vVar;
        this.f4646t = mVar;
        this.f4647u = rVar;
        this.f4648v = mutableInteractionSource;
        this.f4649w = qVar;
        this.f4639m = i10;
        this.f4640n = i11;
    }
}
