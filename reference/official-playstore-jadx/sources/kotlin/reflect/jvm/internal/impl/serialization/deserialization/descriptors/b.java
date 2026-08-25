package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.b0;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;

/* JADX INFO: loaded from: classes5.dex */
public class b implements h8.h {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final /* synthetic */ kotlin.reflect.m[] f19851l;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final u9.s f19852i;

    static {
        m0 m0Var = l0.f19747a;
        f19851l = new kotlin.reflect.m[]{m0Var.g(new b0(m0Var.b(b.class), "annotations", "getAnnotations()Ljava/util/List;"))};
    }

    public b(u9.p pVar, r7.a aVar) {
        pVar.getClass();
        this.f19852i = new u9.l(pVar, aVar);
    }

    @Override // h8.h
    public boolean isEmpty() {
        kotlin.reflect.m mVar = f19851l[0];
        return ((List) this.f19852i.invoke()).isEmpty();
    }

    @Override // java.lang.Iterable
    public final Iterator<h8.b> iterator() {
        kotlin.reflect.m mVar = f19851l[0];
        return ((List) this.f19852i.invoke()).iterator();
    }

    @Override // h8.h
    public final h8.b j(f9.c cVar) {
        h8.b next;
        Iterator<h8.b> it = iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (kotlin.jvm.internal.p.a(next.c(), cVar)) {
                break;
            }
        }
        return next;
    }

    @Override // h8.h
    public final boolean x(f9.c cVar) {
        return j(cVar) != null;
    }
}
