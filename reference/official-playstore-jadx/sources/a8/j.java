package a8;

import java.lang.reflect.Constructor;

/* JADX INFO: loaded from: classes5.dex */
public final class j extends f4.f {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Constructor f203l;

    public j(Constructor constructor) {
        this.f203l = constructor;
    }

    @Override // f4.f
    public final String c() {
        return kotlin.collections.r.f0(this.f203l.getParameterTypes(), "", "<init>(", ")V", b.f155r, 24);
    }
}
