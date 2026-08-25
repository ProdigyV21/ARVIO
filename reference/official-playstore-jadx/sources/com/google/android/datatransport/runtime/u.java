package com.google.android.datatransport.runtime;

import androidx.appcompat.widget.z;
import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public final class u implements z2.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i f12649a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f12650b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final z2.b f12651c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final z2.d f12652d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final w f12653e;

    public u(i iVar, String str, z2.b bVar, z2.d dVar, w wVar) {
        this.f12649a = iVar;
        this.f12650b = str;
        this.f12651c = bVar;
        this.f12652d = dVar;
        this.f12653e = wVar;
    }

    @Override // z2.e
    public final void a(z2.a aVar) {
        b(aVar, new androidx.media3.extractor.mp4.b(23));
    }

    @Override // z2.e
    public final void b(z2.a aVar, z2.g gVar) {
        z2.d dVar = this.f12652d;
        if (dVar == null) {
            throw new NullPointerException("Null transformer");
        }
        w wVar = this.f12653e;
        j3.c cVar = wVar.f12657c;
        i iVarA = this.f12649a.a(aVar.f23162c);
        z zVar = new z();
        zVar.f1847f = new HashMap();
        zVar.f1845d = Long.valueOf(wVar.f12655a.a());
        zVar.f1846e = Long.valueOf(wVar.f12656b.a());
        zVar.f1842a = this.f12650b;
        zVar.f1844c = new n(this.f12651c, (byte[]) dVar.apply(aVar.f23161b));
        zVar.f1843b = aVar.f23160a;
        cVar.a(iVarA, zVar.c(), gVar);
    }
}
