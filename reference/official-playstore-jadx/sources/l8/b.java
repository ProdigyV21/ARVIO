package l8;

import com.google.common.util.concurrent.r0;
import d8.m;
import d8.p;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import kotlin.collections.r;
import y8.y;

/* JADX INFO: loaded from: classes5.dex */
public abstract class b {
    /* JADX WARN: Type inference failed for: r3v12, types: [java.lang.Object, x6.s] */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.lang.Object, x6.s] */
    public static k9.f a(Class cls) {
        int i10 = 0;
        while (cls.isArray()) {
            i10++;
            cls = cls.getComponentType();
        }
        if (cls.isPrimitive()) {
            if (cls.equals(Void.TYPE)) {
                return new k9.f(f9.b.j(p.f14736d.g()), i10);
            }
            m mVarI = n9.c.d(cls.getName()).i();
            return i10 > 0 ? new k9.f(f9.b.j((f9.c) mVarI.f14724n.getValue()), i10 - 1) : new k9.f(f9.b.j((f9.c) mVarI.f14723m.getValue()), i10);
        }
        f9.b bVarA = m8.c.a(cls);
        String str = f8.d.f15297a;
        f9.b bVar = (f9.b) f8.d.f15304h.get(bVarA.b().i());
        if (bVar != null) {
            bVarA = bVar;
        }
        return new k9.f(bVarA, i10);
    }

    public static void b(y.c cVar, Annotation annotation) throws InvocationTargetException {
        Class clsC = ((kotlin.jvm.internal.f) r0.k(annotation)).c();
        y.a aVarB = cVar.b(m8.c.a(clsC), new a(annotation));
        if (aVarB != null) {
            c(aVarB, annotation, clsC);
        }
    }

    public static void c(y.a aVar, Annotation annotation, Class cls) throws InvocationTargetException {
        for (Method method : cls.getDeclaredMethods()) {
            try {
                Object objInvoke = method.invoke(annotation, null);
                f9.f fVarJ = f9.f.j(method.getName());
                Class<?> enclosingClass = objInvoke.getClass();
                if (enclosingClass.equals(Class.class)) {
                    aVar.e(fVarJ, a((Class) objInvoke));
                } else if (f.f20065a.contains(enclosingClass)) {
                    aVar.f(fVarJ, objInvoke);
                } else {
                    List list = m8.c.f20266a;
                    if (Enum.class.isAssignableFrom(enclosingClass)) {
                        if (!enclosingClass.isEnum()) {
                            enclosingClass = enclosingClass.getEnclosingClass();
                        }
                        aVar.d(fVarJ, m8.c.a(enclosingClass), f9.f.j(((Enum) objInvoke).name()));
                    } else if (Annotation.class.isAssignableFrom(enclosingClass)) {
                        Class cls2 = (Class) r.j0(enclosingClass.getInterfaces());
                        y.a aVarC = aVar.c(m8.c.a(cls2), fVarJ);
                        if (aVarC != null) {
                            c(aVarC, (Annotation) objInvoke, cls2);
                        }
                    } else {
                        if (!enclosingClass.isArray()) {
                            throw new UnsupportedOperationException("Unsupported annotation argument value (" + enclosingClass + "): " + objInvoke);
                        }
                        y.b bVarB = aVar.b(fVarJ);
                        if (bVarB != null) {
                            Class<?> componentType = enclosingClass.getComponentType();
                            if (componentType.isEnum()) {
                                f9.b bVarA = m8.c.a(componentType);
                                for (Object obj : (Object[]) objInvoke) {
                                    bVarB.b(bVarA, f9.f.j(((Enum) obj).name()));
                                }
                            } else if (componentType.equals(Class.class)) {
                                for (Object obj2 : (Object[]) objInvoke) {
                                    bVarB.e(a((Class) obj2));
                                }
                            } else if (Annotation.class.isAssignableFrom(componentType)) {
                                for (Object obj3 : (Object[]) objInvoke) {
                                    y.a aVarC2 = bVarB.c(m8.c.a(componentType));
                                    if (aVarC2 != null) {
                                        c(aVarC2, (Annotation) obj3, componentType);
                                    }
                                }
                            } else {
                                for (Object obj4 : (Object[]) objInvoke) {
                                    bVarB.d(obj4);
                                }
                            }
                            bVarB.a();
                        }
                    }
                }
            } catch (IllegalAccessException unused) {
            }
        }
        aVar.a();
    }
}
