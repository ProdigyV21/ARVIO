package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;

/* JADX INFO: loaded from: classes3.dex */
public final class n4 extends kotlin.jvm.internal.r implements r7.p {
    public final /* synthetic */ TextStyle A;
    public final /* synthetic */ int B;
    public final /* synthetic */ int C;
    public final /* synthetic */ int D;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ String f6238i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Modifier f6239l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f6240m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ long f6241n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ FontStyle f6242o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ FontWeight f6243p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ FontFamily f6244q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ long f6245r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ TextDecoration f6246s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ TextAlign f6247t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ long f6248u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ int f6249v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ boolean f6250w;
    public final /* synthetic */ int x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final /* synthetic */ int f6251y;
    public final /* synthetic */ r7.l z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n4(String str, Modifier modifier, long j10, long j11, FontStyle fontStyle, FontWeight fontWeight, FontFamily fontFamily, long j12, TextDecoration textDecoration, TextAlign textAlign, long j13, int i10, boolean z, int i11, int i12, r7.l lVar, TextStyle textStyle, int i13, int i14, int i15) {
        super(2);
        this.f6238i = str;
        this.f6239l = modifier;
        this.f6240m = j10;
        this.f6241n = j11;
        this.f6242o = fontStyle;
        this.f6243p = fontWeight;
        this.f6244q = fontFamily;
        this.f6245r = j12;
        this.f6246s = textDecoration;
        this.f6247t = textAlign;
        this.f6248u = j13;
        this.f6249v = i10;
        this.f6250w = z;
        this.x = i11;
        this.f6251y = i12;
        this.z = lVar;
        this.A = textStyle;
        this.B = i13;
        this.C = i14;
        this.D = i15;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iUpdateChangedFlags = RecomposeScopeImplKt.updateChangedFlags(this.B | 1);
        int iUpdateChangedFlags2 = RecomposeScopeImplKt.updateChangedFlags(this.C);
        int i10 = this.D;
        TextKt.m6020Text4IGK_g(this.f6238i, this.f6239l, this.f6240m, this.f6241n, this.f6242o, this.f6243p, this.f6244q, this.f6245r, this.f6246s, this.f6247t, this.f6248u, this.f6249v, this.f6250w, this.x, this.f6251y, this.z, this.A, (Composer) obj, iUpdateChangedFlags, iUpdateChangedFlags2, i10);
        return x6.t0.f22605a;
    }
}
