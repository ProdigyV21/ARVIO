package k3;

import android.content.Context;
import androidx.appcompat.app.i1;
import androidx.appcompat.widget.z;
import androidx.media3.exoplayer.analytics.x;
import com.google.android.datatransport.runtime.n;
import com.google.android.datatransport.runtime.q;
import j$.util.Objects;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.Executor;
import l3.a;

/* JADX INFO: loaded from: classes4.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f19488a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c3.e f19489b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final com.google.android.datatransport.runtime.scheduling.persistence.d f19490c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final j f19491d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Executor f19492e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final l3.a f19493f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final m3.a f19494g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final m3.a f19495h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final com.google.android.datatransport.runtime.scheduling.persistence.c f19496i;

    public f(Context context, c3.e eVar, com.google.android.datatransport.runtime.scheduling.persistence.d dVar, j jVar, Executor executor, l3.a aVar, m3.a aVar2, m3.a aVar3, com.google.android.datatransport.runtime.scheduling.persistence.c cVar) {
        this.f19488a = context;
        this.f19489b = eVar;
        this.f19490c = dVar;
        this.f19491d = jVar;
        this.f19492e = executor;
        this.f19493f = aVar;
        this.f19494g = aVar2;
        this.f19495h = aVar3;
        this.f19496i = cVar;
    }

    public final void a(final com.google.android.datatransport.runtime.i iVar, int i10) {
        c3.b bVarA;
        byte[] bArr = iVar.f12590b;
        c3.j jVar = this.f19489b.get(iVar.f12589a);
        long jMax = 0;
        while (true) {
            final int i11 = 0;
            a.InterfaceC0242a interfaceC0242a = new a.InterfaceC0242a(this) { // from class: k3.d

                /* JADX INFO: renamed from: l, reason: collision with root package name */
                public final /* synthetic */ f f19482l;

                {
                    this.f19482l = this;
                }

                @Override // l3.a.InterfaceC0242a
                public final Object execute() {
                    switch (i11) {
                        case 0:
                            return Boolean.valueOf(this.f19482l.f19490c.w0(iVar));
                        default:
                            return this.f19482l.f19490c.j0(iVar);
                    }
                }
            };
            l3.a aVar = this.f19493f;
            if (!((Boolean) aVar.j(interfaceC0242a)).booleanValue()) {
                aVar.j(new x(this, iVar, jMax));
                return;
            }
            final int i12 = 1;
            Iterable iterable = (Iterable) aVar.j(new a.InterfaceC0242a(this) { // from class: k3.d

                /* JADX INFO: renamed from: l, reason: collision with root package name */
                public final /* synthetic */ f f19482l;

                {
                    this.f19482l = this;
                }

                @Override // l3.a.InterfaceC0242a
                public final Object execute() {
                    switch (i12) {
                        case 0:
                            return Boolean.valueOf(this.f19482l.f19490c.w0(iVar));
                        default:
                            return this.f19482l.f19490c.j0(iVar);
                    }
                }
            });
            if (!iterable.iterator().hasNext()) {
                return;
            }
            if (jVar == null) {
                ac.b.x("Uploader", iVar, "Unknown backend for %s, deleting event batch for it...");
                bVarA = new c3.b(3, -1L);
            } else {
                ArrayList arrayList = new ArrayList();
                Iterator it = iterable.iterator();
                while (it.hasNext()) {
                    arrayList.add(((com.google.android.datatransport.runtime.scheduling.persistence.b) it.next()).f12617c);
                }
                if (bArr != null) {
                    com.google.android.datatransport.runtime.scheduling.persistence.c cVar = this.f19496i;
                    Objects.requireNonNull(cVar);
                    g3.a aVar2 = (g3.a) aVar.j(new io.sentry.cache.a(cVar, 6));
                    z zVar = new z();
                    zVar.f1847f = new HashMap();
                    zVar.f1845d = Long.valueOf(this.f19494g.a());
                    zVar.f1846e = Long.valueOf(this.f19495h.a());
                    zVar.f1842a = "GDT_CLIENT_METRICS";
                    z2.b bVar = new z2.b("proto");
                    aVar2.getClass();
                    i1 i1Var = q.f12604a;
                    i1Var.getClass();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        i1Var.l(aVar2, byteArrayOutputStream);
                    } catch (IOException unused) {
                    }
                    zVar.f1844c = new n(bVar, byteArrayOutputStream.toByteArray());
                    arrayList.add(jVar.b(zVar.c()));
                }
                bVarA = jVar.a(new c3.a(arrayList, bArr));
            }
            int i13 = bVarA.f7387a;
            if (i13 == 2) {
                com.google.android.datatransport.runtime.i iVar2 = iVar;
                aVar.j(new com.google.firebase.crashlytics.internal.a(this, iterable, iVar2, jMax));
                this.f19491d.b(iVar2, i10 + 1, true);
                return;
            }
            com.google.android.datatransport.runtime.i iVar3 = iVar;
            aVar.j(new androidx.media3.exoplayer.analytics.b(this, iterable, 21));
            if (i13 == 1) {
                jMax = Math.max(jMax, bVarA.f7388b);
                if (bArr != null) {
                    aVar.j(new io.sentry.cache.a(this, 5));
                }
            } else if (i13 == 4) {
                HashMap map = new HashMap();
                Iterator it2 = iterable.iterator();
                while (it2.hasNext()) {
                    String str = ((com.google.android.datatransport.runtime.scheduling.persistence.b) it2.next()).f12617c.f12583a;
                    if (map.containsKey(str)) {
                        map.put(str, Integer.valueOf(((Integer) map.get(str)).intValue() + 1));
                    } else {
                        map.put(str, 1);
                    }
                }
                aVar.j(new androidx.media3.exoplayer.analytics.b(this, map, 22));
            }
            iVar = iVar3;
        }
    }
}
