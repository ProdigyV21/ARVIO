package d7;

import com.arflix.tv.ui.components.l1;
import d7.j;
import java.io.Serializable;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes5.dex */
public final class c implements j, Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final j f14685i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final j.a f14686l;

    public c(j jVar, j.a aVar) {
        this.f14685i = jVar;
        this.f14686l = aVar;
    }

    public final boolean equals(Object obj) {
        boolean zA;
        if (this == obj) {
            return true;
        }
        if (obj instanceof c) {
            c cVar = (c) obj;
            int i10 = 2;
            c cVar2 = cVar;
            int i11 = 2;
            while (true) {
                j jVar = cVar2.f14685i;
                cVar2 = jVar instanceof c ? (c) jVar : null;
                if (cVar2 == null) {
                    break;
                }
                i11++;
            }
            c cVar3 = this;
            while (true) {
                j jVar2 = cVar3.f14685i;
                cVar3 = jVar2 instanceof c ? (c) jVar2 : null;
                if (cVar3 == null) {
                    break;
                }
                i10++;
            }
            if (i11 == i10) {
                c cVar4 = this;
                while (true) {
                    j.a aVar = cVar4.f14686l;
                    if (!p.a(cVar.get(aVar.getKey()), aVar)) {
                        zA = false;
                        break;
                    }
                    j jVar3 = cVar4.f14685i;
                    if (!(jVar3 instanceof c)) {
                        j.a aVar2 = (j.a) jVar3;
                        zA = p.a(cVar.get(aVar2.getKey()), aVar2);
                        break;
                    }
                    cVar4 = (c) jVar3;
                }
                if (zA) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // d7.j
    public final Object fold(Object obj, r7.p pVar) {
        return pVar.invoke(this.f14685i.fold(obj, pVar), this.f14686l);
    }

    @Override // d7.j
    public final j.a get(j.b bVar) {
        c cVar = this;
        while (true) {
            j.a aVar = cVar.f14686l.get(bVar);
            if (aVar != null) {
                return aVar;
            }
            j jVar = cVar.f14685i;
            if (!(jVar instanceof c)) {
                return jVar.get(bVar);
            }
            cVar = (c) jVar;
        }
    }

    public final int hashCode() {
        return this.f14686l.hashCode() + this.f14685i.hashCode();
    }

    @Override // d7.j
    public final j minusKey(j.b bVar) {
        j.a aVar = this.f14686l;
        j.a aVar2 = aVar.get(bVar);
        j jVar = this.f14685i;
        if (aVar2 != null) {
            return jVar;
        }
        j jVarMinusKey = jVar.minusKey(bVar);
        return jVarMinusKey == jVar ? this : jVarMinusKey == k.f14688i ? aVar : new c(jVarMinusKey, aVar);
    }

    @Override // d7.j
    public final /* bridge */ j plus(j jVar) {
        return h.a(this, jVar);
    }

    public final String toString() {
        return androidx.compose.foundation.c.u(new StringBuilder("["), (String) fold("", new l1(9)), ']');
    }
}
