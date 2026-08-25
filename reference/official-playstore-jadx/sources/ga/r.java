package ga;

import com.arflix.tv.data.repository.TraktRepository$getContinueWatching$2$invokeSuspend$$inlined$sortedByDescending$1;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.collections.b0;
import kotlin.collections.z;

/* JADX INFO: loaded from: classes5.dex */
public abstract class r extends f4.f {
    public static m E(Iterator it) {
        return new a(new p(it, 1));
    }

    public static int F(m mVar) {
        Iterator it = mVar.iterator();
        int i10 = 0;
        while (it.hasNext()) {
            it.next();
            i10++;
            if (i10 < 0) {
                t7.a.P();
                throw null;
            }
        }
        return i10;
    }

    public static c G(m mVar) {
        return new c(mVar, new com.arflix.tv.util.a(3), 0);
    }

    public static m H(m mVar, int i10) {
        if (i10 >= 0) {
            return i10 == 0 ? mVar : mVar instanceof f ? ((f) mVar).a(i10) : new e(mVar, i10, 0);
        }
        throw new IllegalArgumentException(androidx.compose.foundation.c.o(i10, "Requested element count ", " is less than zero.").toString());
    }

    public static i I(m mVar, r7.l lVar) {
        return new i(mVar, true, lVar);
    }

    public static i J(m mVar, r7.l lVar) {
        return new i(mVar, false, lVar);
    }

    public static Object K(m mVar) {
        Iterator it = mVar.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    public static j L(m mVar, r7.l lVar) {
        return new j(mVar, lVar, q.f15599i);
    }

    public static m M(Object obj, r7.l lVar) {
        return obj == null ? g.f15574a : new l(new com.arflix.tv.d(obj, 4), lVar, 0);
    }

    public static m N(r7.a aVar) {
        return new a(new l(aVar, new com.arflix.tv.cast.a(aVar), 0));
    }

    public static String O(m mVar, String str, String str2, com.arflix.tv.data.repository.k kVar, int i10) {
        if ((i10 & 1) != 0) {
            str = ", ";
        }
        String str3 = (i10 & 2) != 0 ? "" : "[";
        if ((i10 & 4) != 0) {
            str2 = "";
        }
        if ((i10 & 32) != 0) {
            kVar = null;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append((CharSequence) str3);
        int i11 = 0;
        for (Object obj : mVar) {
            i11++;
            if (i11 > 1) {
                sb2.append((CharSequence) str);
            }
            f4.f.b(sb2, obj, kVar);
        }
        sb2.append((CharSequence) str2);
        return sb2.toString();
    }

    public static Object P(m mVar) {
        Iterator it = mVar.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException("Sequence is empty.");
        }
        Object next = it.next();
        while (it.hasNext()) {
            next = it.next();
        }
        return next;
    }

    public static i Q(m mVar, r7.l lVar) {
        return J(new t(mVar, lVar), new com.arflix.tv.util.a(4));
    }

    public static l R(i iVar, TraktRepository$getContinueWatching$2$invokeSuspend$$inlined$sortedByDescending$1 traktRepository$getContinueWatching$2$invokeSuspend$$inlined$sortedByDescending$1) {
        return new l(iVar, traktRepository$getContinueWatching$2$invokeSuspend$$inlined$sortedByDescending$1, 1);
    }

    public static m S(m mVar, int i10) {
        if (i10 >= 0) {
            return i10 == 0 ? g.f15574a : mVar instanceof f ? ((f) mVar).b(i10) : new e(mVar, i10, 1);
        }
        throw new IllegalArgumentException(androidx.compose.foundation.c.o(i10, "Requested element count ", " is less than zero.").toString());
    }

    public static void T(m mVar, AbstractCollection abstractCollection) {
        Iterator it = mVar.iterator();
        while (it.hasNext()) {
            abstractCollection.add(it.next());
        }
    }

    public static List U(m mVar) {
        Iterator it = mVar.iterator();
        if (!it.hasNext()) {
            return z.f19728i;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return Collections.singletonList(next);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(next);
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public static List V(m mVar) {
        ArrayList arrayList = new ArrayList();
        T(mVar, arrayList);
        return arrayList;
    }

    public static Set W(m mVar) {
        Iterator it = mVar.iterator();
        if (!it.hasNext()) {
            return b0.f19686i;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return Collections.singleton(next);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(next);
        while (it.hasNext()) {
            linkedHashSet.add(it.next());
        }
        return linkedHashSet;
    }
}
