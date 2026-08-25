package v9;

import java.util.ArrayDeque;

/* JADX INFO: loaded from: classes5.dex */
public class n0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f22229a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f22230b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final y9.p f22231c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final w9.d f22232d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final w9.e f22233e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f22234f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ArrayDeque f22235g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ea.l f22236h;

    public n0(boolean z, boolean z5, w9.b bVar, w9.d dVar, w9.e eVar) {
        this.f22229a = z;
        this.f22230b = z5;
        this.f22231c = bVar;
        this.f22232d = dVar;
        this.f22233e = eVar;
    }

    public final void a() {
        this.f22235g.clear();
        this.f22236h.clear();
    }

    public final void b() {
        if (this.f22235g == null) {
            this.f22235g = new ArrayDeque(4);
        }
        if (this.f22236h == null) {
            this.f22236h = new ea.l();
        }
    }
}
