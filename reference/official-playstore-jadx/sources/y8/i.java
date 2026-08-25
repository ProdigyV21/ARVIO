package y8;

import java.util.ArrayList;
import y8.y;

/* JADX INFO: loaded from: classes5.dex */
public final class i implements y.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ m f22939a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ m f22940b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ l f22941c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ f9.f f22942d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ ArrayList f22943e;

    public i(m mVar, l lVar, f9.f fVar, ArrayList arrayList) {
        this.f22940b = mVar;
        this.f22941c = lVar;
        this.f22942d = fVar;
        this.f22943e = arrayList;
        this.f22939a = mVar;
    }

    @Override // y8.y.a
    public final void a() {
        this.f22940b.a();
        ((m) this.f22941c).f22953b.put(this.f22942d, new k9.a((h8.b) kotlin.collections.x.R0(this.f22943e)));
    }

    @Override // y8.y.a
    public final y.b b(f9.f fVar) {
        return this.f22939a.b(fVar);
    }

    @Override // y8.y.a
    public final y.a c(f9.b bVar, f9.f fVar) {
        return this.f22939a.c(bVar, fVar);
    }

    @Override // y8.y.a
    public final void d(f9.f fVar, f9.b bVar, f9.f fVar2) {
        this.f22939a.d(fVar, bVar, fVar2);
    }

    @Override // y8.y.a
    public final void e(f9.f fVar, k9.f fVar2) {
        this.f22939a.e(fVar, fVar2);
    }

    @Override // y8.y.a
    public final void f(f9.f fVar, Object obj) {
        this.f22939a.f(fVar, obj);
    }
}
