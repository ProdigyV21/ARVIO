package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;

/* JADX INFO: loaded from: classes3.dex */
public final class m2 extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f6209i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f6210l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f6211m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f6212n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ r7.p f6213o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f6214p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f6215q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m2(Modifier modifier, p1 p1Var, r7.p pVar, r7.r rVar, int i10, int i11) {
        super(2);
        this.f6211m = modifier;
        this.f6212n = p1Var;
        this.f6213o = pVar;
        this.f6210l = rVar;
        this.f6214p = i10;
        this.f6215q = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f6209i) {
            case 0:
                ((Number) obj2).intValue();
                Modifier modifier = (Modifier) this.f6211m;
                p1 p1Var = (p1) this.f6212n;
                r7.r rVar = (r7.r) this.f6210l;
                NavigationDrawerKt.DrawerSheet(modifier, p1Var, this.f6213o, rVar, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f6214p | 1), this.f6215q);
                break;
            case 1:
                ((Number) obj2).intValue();
                r7.r rVar2 = (r7.r) this.f6210l;
                Modifier modifier2 = (Modifier) this.f6211m;
                p1 p1Var2 = (p1) this.f6212n;
                NavigationDrawerKt.NavigationDrawer(rVar2, modifier2, p1Var2, this.f6213o, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f6214p | 1), this.f6215q);
                break;
            default:
                ((Number) obj2).intValue();
                h1 h1Var = (h1) this.f6211m;
                d3 d3Var = (d3) this.f6212n;
                p4 p4Var = (p4) this.f6210l;
                MaterialThemeKt.MaterialTheme(h1Var, d3Var, p4Var, this.f6213o, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f6214p | 1), this.f6215q);
                break;
        }
        return x6.t0.f22605a;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m2(h1 h1Var, d3 d3Var, p4 p4Var, r7.p pVar, int i10, int i11) {
        super(2);
        this.f6211m = h1Var;
        this.f6212n = d3Var;
        this.f6210l = p4Var;
        this.f6213o = pVar;
        this.f6214p = i10;
        this.f6215q = i11;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m2(r7.r rVar, Modifier modifier, p1 p1Var, r7.p pVar, int i10, int i11) {
        super(2);
        this.f6210l = rVar;
        this.f6211m = modifier;
        this.f6212n = p1Var;
        this.f6213o = pVar;
        this.f6214p = i10;
        this.f6215q = i11;
    }
}
