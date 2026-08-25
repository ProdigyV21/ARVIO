package x8;

import g8.v0;
import java.util.Map;
import v9.w;

/* JADX INFO: loaded from: classes5.dex */
public final class b implements h8.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f22638a = new b();

    @Override // h8.b
    public final Map a() {
        throw new IllegalStateException("No methods should be called on this descriptor. Only its presence matters");
    }

    @Override // h8.b
    public final f9.c c() {
        g8.f fVarD = m9.d.d(this);
        if (fVarD != null) {
            if (x9.k.e(fVarD)) {
                fVarD = null;
            }
            if (fVarD != null) {
                return m9.d.c(fVarD);
            }
        }
        return null;
    }

    @Override // h8.b
    public final v0 f() {
        throw new IllegalStateException("No methods should be called on this descriptor. Only its presence matters");
    }

    @Override // h8.b
    public final w getType() {
        throw new IllegalStateException("No methods should be called on this descriptor. Only its presence matters");
    }

    public final String toString() {
        return "[EnhancedType]";
    }
}
