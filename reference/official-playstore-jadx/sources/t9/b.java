package t9;

import androidx.emoji2.text.e0;
import d8.q;
import f4.f;
import g8.b0;
import g8.k0;
import io.sentry.util.l;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import kotlin.collections.s;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.j;
import u9.p;

/* JADX INFO: loaded from: classes5.dex */
public final class b implements d8.c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f22000b = new d();

    @Override // d8.c
    public final k0 a(p pVar, b0 b0Var, Iterable iterable, i8.d dVar, i8.b bVar, boolean z) {
        Set set = q.f14771q;
        j jVar = new j(1, this.f22000b, 5);
        Set<f9.c> set2 = set;
        ArrayList arrayList = new ArrayList(s.U(set2, 10));
        for (f9.c cVar : set2) {
            a.f21999m.getClass();
            String strA = a.a(cVar);
            InputStream inputStream = (InputStream) jVar.invoke(strA);
            if (inputStream == null) {
                throw new IllegalStateException(androidx.compose.material3.d.C("Resource not found in classpath: ", strA));
            }
            arrayList.add(f.g(cVar, pVar, b0Var, inputStream));
        }
        k0 k0Var = new k0(arrayList);
        e0 e0Var = new e0(pVar, b0Var);
        kb.d dVar2 = new kb.d(k0Var, 15);
        a aVar = a.f21999m;
        s9.j jVar2 = new s9.j(pVar, b0Var, dVar2, new fi.iki.elonen.f(b0Var, e0Var, aVar), k0Var, iterable, e0Var, bVar, dVar, aVar.f21393a, null, new l(pVar), 851968);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((c) it.next()).G0(jVar2);
        }
        return k0Var;
    }
}
