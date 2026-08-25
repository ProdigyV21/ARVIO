package s9;

import a9.k1;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes5.dex */
public final class k implements l, n, p, s, i, o {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final k f21725c = new k(0);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final k f21726d = new k(1);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final k f21727e = new k(2);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final k f21728f = new k(3);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final k f21729g = new k(4);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f21730b;

    public /* synthetic */ k(int i10) {
        this.f21730b = i10;
    }

    @Override // s9.o
    public void a(g8.c cVar) {
        if (cVar == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "descriptor", "kotlin/reflect/jvm/internal/impl/serialization/deserialization/ErrorReporter$1", "reportCannotInferVisibility"));
        }
    }

    @Override // s9.p
    public v9.w c(k1 k1Var, String str, v9.b0 b0Var, v9.b0 b0Var2) {
        throw new IllegalArgumentException("This method should not be used.");
    }

    @Override // s9.n
    public Boolean d() {
        switch (this.f21730b) {
            case 1:
                return null;
            default:
                return Boolean.TRUE;
        }
    }

    @Override // s9.o
    public void b(g8.f fVar, ArrayList arrayList) {
    }
}
