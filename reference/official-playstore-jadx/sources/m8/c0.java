package m8;

import java.lang.reflect.Type;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public abstract class c0 implements w8.w {
    public abstract Type H();

    public final boolean equals(Object obj) {
        return (obj instanceof c0) && kotlin.jvm.internal.p.a(H(), ((c0) obj).H());
    }

    public final int hashCode() {
        return H().hashCode();
    }

    @Override // w8.d
    public w8.a j(f9.c cVar) {
        Object next;
        Iterator it = getAnnotations().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (kotlin.jvm.internal.p.a(((w8.a) next).h().b(), cVar)) {
                break;
            }
        }
        return (w8.a) next;
    }

    public final String toString() {
        return getClass().getName() + ": " + H();
    }
}
