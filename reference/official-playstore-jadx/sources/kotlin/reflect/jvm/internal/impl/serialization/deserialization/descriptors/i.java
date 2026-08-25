package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import g8.y0;
import g8.z0;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class i extends v9.b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f19862c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final u9.s f19863d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ j8.b f19864e;

    /* JADX WARN: Illegal instructions before constructor call */
    public i(t8.i iVar) {
        this.f19864e = iVar;
        androidx.core.provider.e eVar = iVar.f21932t;
        super(((s8.a) eVar.f2140l).f21645a);
        this.f19863d = ((s8.a) eVar.f2140l).f21645a.d(new t8.g(iVar, 0));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01f9  */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v13, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v2, types: [java.lang.Iterable] */
    /* JADX WARN: Type inference failed for: r7v40 */
    @Override // v9.g
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.Collection c() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 810
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.i.c():java.util.Collection");
    }

    @Override // v9.b, v9.o0
    public final g8.h e() {
        switch (this.f19862c) {
            case 0:
                return (k) this.f19864e;
            default:
                return (t8.i) this.f19864e;
        }
    }

    @Override // v9.o0
    public final boolean f() {
        switch (this.f19862c) {
        }
        return true;
    }

    @Override // v9.o0
    public final List getParameters() {
        switch (this.f19862c) {
        }
        return (List) this.f19863d.invoke();
    }

    @Override // v9.g
    public final z0 h() {
        switch (this.f19862c) {
            case 0:
                return y0.f15559l;
            default:
                return ((s8.a) ((t8.i) this.f19864e).f21932t.f2140l).f21656m;
        }
    }

    @Override // v9.b
    /* JADX INFO: renamed from: n */
    public final g8.f e() {
        switch (this.f19862c) {
            case 0:
                return (k) this.f19864e;
            default:
                return (t8.i) this.f19864e;
        }
    }

    public final String toString() {
        switch (this.f19862c) {
            case 0:
                return ((k) this.f19864e).getName().f15368i;
            default:
                return ((t8.i) this.f19864e).getName().d();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public i(k kVar) {
        this.f19864e = kVar;
        s9.m mVar = kVar.f19873v;
        super(mVar.f21731a.f21706a);
        u9.p pVar = mVar.f21731a.f21706a;
        h hVar = new h(kVar, 0);
        pVar.getClass();
        this.f19863d = new u9.l(pVar, hVar);
    }
}
