package androidx.tv.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.TextStyle;

/* JADX INFO: loaded from: classes3.dex */
public final class z1 extends kotlin.jvm.internal.r implements r7.p {
    public final /* synthetic */ y1 A;
    public final /* synthetic */ float B;
    public final /* synthetic */ float C;
    public final /* synthetic */ TextStyle D;
    public final /* synthetic */ TextStyle E;
    public final /* synthetic */ MutableInteractionSource F;
    public final /* synthetic */ int G;
    public final /* synthetic */ int H;
    public final /* synthetic */ int I;
    public final /* synthetic */ int J;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ boolean f6584i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.a f6585l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ PaddingValues f6586m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.p f6587n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Modifier f6588o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ boolean f6589p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ r7.a f6590q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ r7.p f6591r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ r7.p f6592s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ r7.q f6593t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ r7.p f6594u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ float f6595v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ d2 f6596w;
    public final /* synthetic */ x1 x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final /* synthetic */ c2 f6597y;
    public final /* synthetic */ w1 z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z1(boolean z, r7.a aVar, PaddingValues paddingValues, r7.p pVar, Modifier modifier, boolean z5, r7.a aVar2, r7.p pVar2, r7.p pVar3, r7.q qVar, r7.p pVar4, float f10, d2 d2Var, x1 x1Var, c2 c2Var, w1 w1Var, y1 y1Var, float f11, float f12, TextStyle textStyle, TextStyle textStyle2, MutableInteractionSource mutableInteractionSource, int i10, int i11, int i12, int i13) {
        super(2);
        this.f6584i = z;
        this.f6585l = aVar;
        this.f6586m = paddingValues;
        this.f6587n = pVar;
        this.f6588o = modifier;
        this.f6589p = z5;
        this.f6590q = aVar2;
        this.f6591r = pVar2;
        this.f6592s = pVar3;
        this.f6593t = qVar;
        this.f6594u = pVar4;
        this.f6595v = f10;
        this.f6596w = d2Var;
        this.x = x1Var;
        this.f6597y = c2Var;
        this.z = w1Var;
        this.A = y1Var;
        this.B = f11;
        this.C = f12;
        this.D = textStyle;
        this.E = textStyle2;
        this.F = mutableInteractionSource;
        this.G = i10;
        this.H = i11;
        this.I = i12;
        this.J = i13;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iUpdateChangedFlags = RecomposeScopeImplKt.updateChangedFlags(this.G | 1);
        int iUpdateChangedFlags2 = RecomposeScopeImplKt.updateChangedFlags(this.H);
        int iUpdateChangedFlags3 = RecomposeScopeImplKt.updateChangedFlags(this.I);
        int i10 = this.J;
        ListItemKt.m5995BaseListItemd3nVH8(this.f6584i, this.f6585l, this.f6586m, this.f6587n, this.f6588o, this.f6589p, this.f6590q, this.f6591r, this.f6592s, this.f6593t, this.f6594u, this.f6595v, this.f6596w, this.x, this.f6597y, this.z, this.A, this.B, this.C, this.D, this.E, this.F, (Composer) obj, iUpdateChangedFlags, iUpdateChangedFlags2, iUpdateChangedFlags3, i10);
        return x6.t0.f22605a;
    }
}
