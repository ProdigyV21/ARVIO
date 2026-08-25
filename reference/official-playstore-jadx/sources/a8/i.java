package a8;

import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class i extends f4.f {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final List f201l;

    public i(Class cls) {
        this.f201l = kotlin.collections.r.k0(cls.getDeclaredMethods(), new h(0));
    }

    @Override // f4.f
    public final String c() {
        return kotlin.collections.x.u0(this.f201l, "", "<init>(", ")V", b.f154q, 24);
    }
}
