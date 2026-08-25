package z7;

import a8.e2;
import a8.r1;
import a8.u;
import a8.u1;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import kotlin.jvm.internal.p;
import kotlin.reflect.b0;
import kotlin.reflect.g;
import kotlin.reflect.m;
import kotlin.reflect.q;

/* JADX INFO: loaded from: classes5.dex */
public abstract class d {
    public static final g a(Collection collection, Method method) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            kotlin.reflect.c cVar = (kotlin.reflect.c) it.next();
            if ((cVar instanceof g) && p.a(cVar.getName(), method.getName())) {
                g gVar = (g) cVar;
                if (p.a(c(gVar), method)) {
                    return gVar;
                }
            }
        }
        Iterator it2 = collection.iterator();
        while (it2.hasNext()) {
            kotlin.reflect.c cVar2 = (kotlin.reflect.c) it2.next();
            if ((cVar2 instanceof g) && !p.a(cVar2.getName(), method.getName())) {
                g gVar2 = (g) cVar2;
                if (p.a(c(gVar2), method)) {
                    return gVar2;
                }
            }
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object, x6.s] */
    public static final Field b(m mVar) {
        r1 r1VarC = e2.c(mVar);
        if (r1VarC != null) {
            return (Field) r1VarC.f258u.getValue();
        }
        return null;
    }

    public static final Method c(g gVar) {
        b8.e eVarU;
        u uVarA = e2.a(gVar);
        Member memberB = (uVarA == null || (eVarU = uVarA.u()) == null) ? null : eVarU.b();
        if (memberB instanceof Method) {
            return (Method) memberB;
        }
        return null;
    }

    public static final Type d(q qVar) {
        Type typeK = ((u1) qVar).k();
        return typeK == null ? b0.t(qVar) : typeK;
    }
}
