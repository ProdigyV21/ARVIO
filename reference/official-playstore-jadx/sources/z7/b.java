package z7;

import a8.e2;
import a8.r1;
import a8.u;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import kotlin.reflect.g;
import kotlin.reflect.h;
import kotlin.reflect.m;

/* JADX INFO: loaded from: classes5.dex */
public abstract class b {
    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean a(r1 r1Var) {
        b8.e eVarU;
        b8.e eVarW;
        if (r1Var instanceof h) {
            Field fieldB = d.b(r1Var);
            if (!(fieldB != null ? fieldB.isAccessible() : true)) {
                return false;
            }
            Method methodC = d.c(r1Var.getGetter());
            if (!(methodC != null ? methodC.isAccessible() : true)) {
                return false;
            }
            Method methodC2 = d.c(((h) r1Var).getSetter());
            if (!(methodC2 != null ? methodC2.isAccessible() : true)) {
                return false;
            }
        } else if (androidx.compose.material3.d.B(r1Var)) {
            Field fieldB2 = d.b(r1Var);
            if (!(fieldB2 != null ? fieldB2.isAccessible() : true)) {
                return false;
            }
            Method methodC3 = d.c(r1Var.getGetter());
            if (!(methodC3 != null ? methodC3.isAccessible() : true)) {
                return false;
            }
        } else if (r1Var instanceof m.b) {
            Field fieldB3 = d.b(((m.b) r1Var).c());
            if (!(fieldB3 != null ? fieldB3.isAccessible() : true)) {
                return false;
            }
            Method methodC4 = d.c((g) r1Var);
            if (!(methodC4 != null ? methodC4.isAccessible() : true)) {
                return false;
            }
        } else if (r1Var instanceof h.a) {
            Field fieldB4 = d.b(((h.a) r1Var).c());
            if (!(fieldB4 != null ? fieldB4.isAccessible() : true)) {
                return false;
            }
            Method methodC5 = d.c((g) r1Var);
            if (!(methodC5 != null ? methodC5.isAccessible() : true)) {
                return false;
            }
        } else {
            if (!(r1Var instanceof g)) {
                throw new UnsupportedOperationException("Unknown callable: " + r1Var + " (" + r1Var.getClass() + ')');
            }
            g gVar = (g) r1Var;
            Method methodC6 = d.c(gVar);
            if (!(methodC6 != null ? methodC6.isAccessible() : true)) {
                return false;
            }
            u uVarA = e2.a(r1Var);
            Object objB = (uVarA == null || (eVarW = uVarA.w()) == null) ? null : eVarW.b();
            AccessibleObject accessibleObject = objB instanceof AccessibleObject ? (AccessibleObject) objB : null;
            if (!(accessibleObject != null ? accessibleObject.isAccessible() : true)) {
                return false;
            }
            u uVarA2 = e2.a(gVar);
            Member memberB = (uVarA2 == null || (eVarU = uVarA2.u()) == null) ? null : eVarU.b();
            Constructor constructor = memberB instanceof Constructor ? (Constructor) memberB : null;
            if (!(constructor != null ? constructor.isAccessible() : true)) {
                return false;
            }
        }
        return true;
    }

    public static final void b(kotlin.reflect.c cVar) {
        b8.e eVarU;
        b8.e eVarW;
        if (cVar instanceof h) {
            m mVar = (m) cVar;
            Field fieldB = d.b(mVar);
            if (fieldB != null) {
                fieldB.setAccessible(true);
            }
            Method methodC = d.c(mVar.getGetter());
            if (methodC != null) {
                methodC.setAccessible(true);
            }
            Method methodC2 = d.c(((h) cVar).getSetter());
            if (methodC2 == null) {
                return;
            }
            methodC2.setAccessible(true);
            return;
        }
        if (cVar instanceof m) {
            m mVar2 = (m) cVar;
            Field fieldB2 = d.b(mVar2);
            if (fieldB2 != null) {
                fieldB2.setAccessible(true);
            }
            Method methodC3 = d.c(mVar2.getGetter());
            if (methodC3 == null) {
                return;
            }
            methodC3.setAccessible(true);
            return;
        }
        if (cVar instanceof m.b) {
            Field fieldB3 = d.b(((m.b) cVar).c());
            if (fieldB3 != null) {
                fieldB3.setAccessible(true);
            }
            Method methodC4 = d.c((g) cVar);
            if (methodC4 == null) {
                return;
            }
            methodC4.setAccessible(true);
            return;
        }
        if (cVar instanceof h.a) {
            Field fieldB4 = d.b(((h.a) cVar).c());
            if (fieldB4 != null) {
                fieldB4.setAccessible(true);
            }
            Method methodC5 = d.c((g) cVar);
            if (methodC5 == null) {
                return;
            }
            methodC5.setAccessible(true);
            return;
        }
        if (!(cVar instanceof g)) {
            throw new UnsupportedOperationException("Unknown callable: " + cVar + " (" + cVar.getClass() + ')');
        }
        g gVar = (g) cVar;
        Method methodC6 = d.c(gVar);
        if (methodC6 != null) {
            methodC6.setAccessible(true);
        }
        u uVarA = e2.a(cVar);
        Object objB = (uVarA == null || (eVarW = uVarA.w()) == null) ? null : eVarW.b();
        AccessibleObject accessibleObject = objB instanceof AccessibleObject ? (AccessibleObject) objB : null;
        if (accessibleObject != null) {
            accessibleObject.setAccessible(true);
        }
        u uVarA2 = e2.a(gVar);
        Member memberB = (uVarA2 == null || (eVarU = uVarA2.u()) == null) ? null : eVarU.b();
        Constructor constructor = memberB instanceof Constructor ? (Constructor) memberB : null;
        if (constructor == null) {
            return;
        }
        constructor.setAccessible(true);
    }
}
