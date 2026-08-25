package androidx.navigation.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.navigation.h0;
import androidx.navigation.l0;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class w extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4618i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ l0 f4619l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ h0 f4620m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Modifier f4621n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Alignment f4622o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ r7.l f4623p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ r7.l f4624q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ r7.l f4625r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ r7.l f4626s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ int f4627t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ int f4628u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ w(l0 l0Var, h0 h0Var, Modifier modifier, Alignment alignment, r7.l lVar, r7.l lVar2, r7.l lVar3, r7.l lVar4, int i10, int i11, int i12) {
        super(2);
        this.f4618i = i12;
        this.f4619l = l0Var;
        this.f4620m = h0Var;
        this.f4621n = modifier;
        this.f4622o = alignment;
        this.f4623p = lVar;
        this.f4624q = lVar2;
        this.f4625r = lVar3;
        this.f4626s = lVar4;
        this.f4627t = i10;
        this.f4628u = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f4618i) {
            case 0:
                ((Number) obj2).intValue();
                NavHostKt.NavHost(this.f4619l, this.f4620m, this.f4621n, this.f4622o, this.f4623p, this.f4624q, this.f4625r, this.f4626s, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f4627t | 1), this.f4628u);
                break;
            case 1:
                ((Number) obj2).intValue();
                NavHostKt.NavHost(this.f4619l, this.f4620m, this.f4621n, this.f4622o, this.f4623p, this.f4624q, this.f4625r, this.f4626s, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f4627t | 1), this.f4628u);
                break;
            default:
                ((Number) obj2).intValue();
                NavHostKt.NavHost(this.f4619l, this.f4620m, this.f4621n, this.f4622o, this.f4623p, this.f4624q, this.f4625r, this.f4626s, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f4627t | 1), this.f4628u);
                break;
        }
        return t0.f22605a;
    }
}
