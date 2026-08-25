package p;

import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class a extends f {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final HashMap f21021o = new HashMap();

    @Override // p.f
    public final c a(Object obj) {
        return (c) this.f21021o.get(obj);
    }

    @Override // p.f
    public final Object b(Object obj) {
        Object objB = super.b(obj);
        this.f21021o.remove(obj);
        return objB;
    }
}
