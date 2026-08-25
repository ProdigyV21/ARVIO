package s9;

import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j f21731a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c9.f f21732b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final g8.k f21733c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final androidx.work.impl.constraints.j f21734d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final c9.g f21735e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final c9.a f21736f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.l f21737g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final g0 f21738h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final x f21739i;

    public m(j jVar, c9.f fVar, g8.k kVar, androidx.work.impl.constraints.j jVar2, c9.g gVar, c9.a aVar, kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.l lVar, g0 g0Var, List list) {
        String strE;
        this.f21731a = jVar;
        this.f21732b = fVar;
        this.f21733c = kVar;
        this.f21734d = jVar2;
        this.f21735e = gVar;
        this.f21736f = aVar;
        this.f21737g = lVar;
        this.f21738h = new g0(this, g0Var, list, "Deserializer for \"" + kVar.getName() + '\"', (lVar == null || (strE = lVar.e()) == null) ? "[container not found]" : strE);
        this.f21739i = new x(this);
    }

    public final m a(g8.k kVar, List list, c9.f fVar, androidx.work.impl.constraints.j jVar, c9.g gVar, c9.a aVar) {
        int i10 = aVar.f7425b;
        if ((i10 != 1 || aVar.f7426c < 4) && i10 <= 1) {
            gVar = this.f21735e;
        }
        return new m(this.f21731a, fVar, kVar, jVar, gVar, aVar, this.f21737g, this.f21738h, list);
    }
}
