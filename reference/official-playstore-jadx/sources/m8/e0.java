package m8;

import java.lang.annotation.Annotation;
import java.util.Collection;

/* JADX INFO: loaded from: classes5.dex */
public final class e0 extends s implements w8.z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c0 f20273a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Annotation[] f20274b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f20275c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f20276d;

    public e0(c0 c0Var, Annotation[] annotationArr, String str, boolean z) {
        this.f20273a = c0Var;
        this.f20274b = annotationArr;
        this.f20275c = str;
        this.f20276d = z;
    }

    @Override // w8.z
    public final boolean f() {
        return this.f20276d;
    }

    @Override // w8.d
    public final Collection getAnnotations() {
        return kotlin.reflect.b0.q(this.f20274b);
    }

    @Override // w8.z
    public final f9.f getName() {
        String str = this.f20275c;
        if (str != null) {
            return f9.f.i(str);
        }
        return null;
    }

    @Override // w8.z
    public final w8.w getType() {
        return this.f20273a;
    }

    @Override // w8.d
    public final w8.a j(f9.c cVar) {
        return kotlin.reflect.b0.p(this.f20274b, cVar);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(e0.class.getName());
        sb2.append(": ");
        sb2.append(this.f20276d ? "vararg " : "");
        sb2.append(getName());
        sb2.append(": ");
        sb2.append(this.f20273a);
        return sb2.toString();
    }
}
