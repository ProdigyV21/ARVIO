package m8;

import java.lang.reflect.Type;
import java.util.Collection;

/* JADX INFO: loaded from: classes5.dex */
public final class a0 extends c0 implements w8.u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f20259a;

    public a0(Class cls) {
        this.f20259a = cls;
    }

    @Override // m8.c0
    public final Type H() {
        return this.f20259a;
    }

    @Override // w8.d
    public final Collection getAnnotations() {
        return kotlin.collections.z.f19728i;
    }

    @Override // w8.u
    public final d8.m getType() {
        Class cls = Void.TYPE;
        Class cls2 = this.f20259a;
        if (kotlin.jvm.internal.p.a(cls2, cls)) {
            return null;
        }
        return n9.c.d(cls2.getName()).i();
    }
}
