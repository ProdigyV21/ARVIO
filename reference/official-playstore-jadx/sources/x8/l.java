package x8;

import v9.a0;
import v9.d1;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public final class l extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final l f22663l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final l f22664m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final l f22665n;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f22666i;

    static {
        int i10 = 1;
        f22663l = new l(i10, 0);
        f22664m = new l(i10, 1);
        f22665n = new l(i10, 2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ l(int i10, int i11) {
        super(i10);
        this.f22666i = i11;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f22666i) {
            case 0:
                g8.h hVarE = ((d1) obj).I0().e();
                if (hVarE == null) {
                    return Boolean.FALSE;
                }
                f9.f name = hVarE.getName();
                f9.c cVar = f8.d.f15302f;
                return Boolean.valueOf(kotlin.jvm.internal.p.a(name, cVar.f()) && kotlin.jvm.internal.p.a(m9.d.c(hVarE), cVar));
            case 1:
                return ((g8.c) obj).M().getType();
            case 2:
                return Boolean.valueOf(((d1) obj) instanceof a0);
            default:
                String strConcat = "java/util/".concat("Spliterator");
                d dVar = j.f22658b;
                ((n) obj).b(strConcat, dVar, dVar);
                return t0.f22605a;
        }
    }
}
