package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class o4 extends kotlin.jvm.internal.r implements r7.p {
    public final /* synthetic */ r7.l A;
    public final /* synthetic */ TextStyle B;
    public final /* synthetic */ int C;
    public final /* synthetic */ int D;
    public final /* synthetic */ int E;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ AnnotatedString f6262i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Modifier f6263l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f6264m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ long f6265n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ FontStyle f6266o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ FontWeight f6267p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ FontFamily f6268q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ long f6269r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ TextDecoration f6270s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ TextAlign f6271t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ long f6272u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ int f6273v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ boolean f6274w;
    public final /* synthetic */ int x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final /* synthetic */ int f6275y;
    public final /* synthetic */ Map z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o4(AnnotatedString annotatedString, Modifier modifier, long j10, long j11, FontStyle fontStyle, FontWeight fontWeight, FontFamily fontFamily, long j12, TextDecoration textDecoration, TextAlign textAlign, long j13, int i10, boolean z, int i11, int i12, Map map, r7.l lVar, TextStyle textStyle, int i13, int i14, int i15) {
        super(2);
        this.f6262i = annotatedString;
        this.f6263l = modifier;
        this.f6264m = j10;
        this.f6265n = j11;
        this.f6266o = fontStyle;
        this.f6267p = fontWeight;
        this.f6268q = fontFamily;
        this.f6269r = j12;
        this.f6270s = textDecoration;
        this.f6271t = textAlign;
        this.f6272u = j13;
        this.f6273v = i10;
        this.f6274w = z;
        this.x = i11;
        this.f6275y = i12;
        this.z = map;
        this.A = lVar;
        this.B = textStyle;
        this.C = i13;
        this.D = i14;
        this.E = i15;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iUpdateChangedFlags = RecomposeScopeImplKt.updateChangedFlags(this.C | 1);
        int iUpdateChangedFlags2 = RecomposeScopeImplKt.updateChangedFlags(this.D);
        int i10 = this.E;
        TextKt.m6021TextIbK3jfQ(this.f6262i, this.f6263l, this.f6264m, this.f6265n, this.f6266o, this.f6267p, this.f6268q, this.f6269r, this.f6270s, this.f6271t, this.f6272u, this.f6273v, this.f6274w, this.x, this.f6275y, this.z, this.A, this.B, (Composer) obj, iUpdateChangedFlags, iUpdateChangedFlags2, i10);
        return x6.t0.f22605a;
    }
}
