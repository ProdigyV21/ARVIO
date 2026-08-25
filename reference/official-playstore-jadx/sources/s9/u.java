package s9;

import a9.t0;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class u extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ x f21751i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f21752l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ t0 f21753m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(x xVar, boolean z, t0 t0Var) {
        super(0);
        this.f21751i = xVar;
        this.f21752l = z;
        this.f21753m = t0Var;
    }

    @Override // r7.a
    public final Object invoke() {
        List listC1;
        x xVar = this.f21751i;
        m mVar = xVar.f21764a;
        j jVar = mVar.f21731a;
        androidx.appcompat.view.menu.e eVarA = xVar.a(mVar.f21733c);
        if (eVarA != null) {
            boolean z = this.f21752l;
            t0 t0Var = this.f21753m;
            listC1 = z ? kotlin.collections.x.c1(jVar.f21710e.B(eVarA, t0Var)) : kotlin.collections.x.c1(jVar.f21710e.A(eVarA, t0Var));
        } else {
            listC1 = null;
        }
        return listC1 == null ? kotlin.collections.z.f19728i : listC1;
    }
}
