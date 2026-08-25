package k9;

import g8.b0;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class b extends g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final kotlin.jvm.internal.r f19526b;

    /* JADX WARN: Multi-variable type inference failed */
    public b(List list, r7.l lVar) {
        super(list);
        this.f19526b = (kotlin.jvm.internal.r) lVar;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [kotlin.jvm.internal.r, r7.l] */
    @Override // k9.g
    public final v9.w a(b0 b0Var) {
        v9.w wVar = (v9.w) this.f19526b.invoke(b0Var);
        if (!d8.k.y(wVar) && !d8.k.F(wVar) && !d8.k.B(wVar, d8.p.V.i()) && !d8.k.B(wVar, d8.p.W.i()) && !d8.k.B(wVar, d8.p.X.i())) {
            d8.k.B(wVar, d8.p.Y.i());
        }
        return wVar;
    }
}
