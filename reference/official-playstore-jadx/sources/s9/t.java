package s9;

import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class t extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f21747i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ x f21748l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.c0 f21749m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f21750n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ t(x xVar, kotlin.reflect.jvm.internal.impl.protobuf.c0 c0Var, int i10, int i11) {
        super(0);
        this.f21747i = i11;
        this.f21748l = xVar;
        this.f21749m = c0Var;
        this.f21750n = i10;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f21747i) {
            case 0:
                x xVar = this.f21748l;
                m mVar = xVar.f21764a;
                androidx.appcompat.view.menu.e eVarA = xVar.a(mVar.f21733c);
                List listC1 = eVarA != null ? kotlin.collections.x.c1(mVar.f21731a.f21710e.i(eVarA, this.f21749m, this.f21750n)) : null;
                if (listC1 == null) {
                }
                break;
            default:
                x xVar2 = this.f21748l;
                m mVar2 = xVar2.f21764a;
                androidx.appcompat.view.menu.e eVarA2 = xVar2.a(mVar2.f21733c);
                List listB = eVarA2 != null ? mVar2.f21731a.f21710e.b(eVarA2, this.f21749m, this.f21750n) : null;
                if (listB == null) {
                }
                break;
        }
        return kotlin.collections.z.f19728i;
    }
}
