package y8;

import java.util.ArrayList;
import y8.y;

/* JADX INFO: loaded from: classes5.dex */
public final class j implements y.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ m f22944a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ m f22945b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ k f22946c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ ArrayList f22947d;

    public j(m mVar, k kVar, ArrayList arrayList) {
        this.f22945b = mVar;
        this.f22946c = kVar;
        this.f22947d = arrayList;
        this.f22944a = mVar;
    }

    @Override // y8.y.a
    public final void a() {
        this.f22945b.a();
        this.f22946c.f22948a.add(new k9.a((h8.b) kotlin.collections.x.R0(this.f22947d)));
    }

    @Override // y8.y.a
    public final y.b b(f9.f fVar) {
        return this.f22944a.b(fVar);
    }

    @Override // y8.y.a
    public final y.a c(f9.b bVar, f9.f fVar) {
        return this.f22944a.c(bVar, fVar);
    }

    @Override // y8.y.a
    public final void d(f9.f fVar, f9.b bVar, f9.f fVar2) {
        this.f22944a.d(fVar, bVar, fVar2);
    }

    @Override // y8.y.a
    public final void e(f9.f fVar, k9.f fVar2) {
        this.f22944a.e(fVar, fVar2);
    }

    @Override // y8.y.a
    public final void f(f9.f fVar, Object obj) {
        this.f22944a.f(fVar, obj);
    }
}
