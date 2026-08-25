package h8;

import g8.v0;
import java.util.Map;
import v9.b0;
import v9.w;

/* JADX INFO: loaded from: classes5.dex */
public final class c implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final w f15844a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map f15845b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final v0 f15846c;

    public c(b0 b0Var, Map map, v0 v0Var) {
        if (b0Var == null) {
            d(0);
            throw null;
        }
        if (map == null) {
            d(1);
            throw null;
        }
        this.f15844a = b0Var;
        this.f15845b = map;
        this.f15846c = v0Var;
    }

    public static /* synthetic */ void d(int i10) {
        String str = (i10 == 3 || i10 == 4 || i10 == 5) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i10 == 3 || i10 == 4 || i10 == 5) ? 2 : 3];
        if (i10 == 1) {
            objArr[0] = "valueArguments";
        } else if (i10 == 2) {
            objArr[0] = "source";
        } else if (i10 == 3 || i10 == 4 || i10 == 5) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotationDescriptorImpl";
        } else {
            objArr[0] = "annotationType";
        }
        if (i10 == 3) {
            objArr[1] = "getType";
        } else if (i10 == 4) {
            objArr[1] = "getAllValueArguments";
        } else if (i10 != 5) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotationDescriptorImpl";
        } else {
            objArr[1] = "getSource";
        }
        if (i10 != 3 && i10 != 4 && i10 != 5) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i10 != 3 && i10 != 4 && i10 != 5) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // h8.b
    public final Map a() {
        Map map = this.f15845b;
        if (map != null) {
            return map;
        }
        d(4);
        throw null;
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
        v0 v0Var = this.f15846c;
        if (v0Var != null) {
            return v0Var;
        }
        d(5);
        throw null;
    }

    @Override // h8.b
    public final w getType() {
        w wVar = this.f15844a;
        if (wVar != null) {
            return wVar;
        }
        d(3);
        throw null;
    }

    public final String toString() {
        return h9.h.f15907c.w(this, null);
    }
}
