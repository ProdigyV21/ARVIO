package h8;

import a8.g0;
import g8.v0;
import java.util.Map;
import v9.w;

/* JADX INFO: loaded from: classes5.dex */
public final class j implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d8.k f15866a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f9.c f15867b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map f15868c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f15869d = f4.f.o(2, new g0(this, 20));

    public j(d8.k kVar, f9.c cVar, Map map) {
        this.f15866a = kVar;
        this.f15867b = cVar;
        this.f15868c = map;
    }

    @Override // h8.b
    public final Map a() {
        return this.f15868c;
    }

    @Override // h8.b
    public final f9.c c() {
        return this.f15867b;
    }

    @Override // h8.b
    public final v0 f() {
        return v0.f15554b;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // h8.b
    public final w getType() {
        return (w) this.f15869d.getValue();
    }
}
