package m8;

import g8.i1;
import g8.k1;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class o extends s implements g, y, w8.g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f20288a;

    public o(Class cls) {
        this.f20288a = cls;
    }

    @Override // w8.g
    public final List A() {
        return ga.r.U(new ga.t(ga.r.J(kotlin.collections.r.G(this.f20288a.getDeclaredFields()), l.f20285i), m.f20286i));
    }

    @Override // w8.g
    public final boolean D() {
        return this.f20288a.isInterface();
    }

    @Override // w8.r
    public final boolean G() {
        return Modifier.isStatic(this.f20288a.getModifiers());
    }

    @Override // w8.g
    public final boolean a() {
        androidx.emoji2.text.e0 e0Var = k2.c.f19461a;
        if (e0Var == null) {
            try {
                e0Var = new androidx.emoji2.text.e0(Class.class.getMethod("isSealed", null), Class.class.getMethod("getPermittedSubclasses", null), Class.class.getMethod("isRecord", null), Class.class.getMethod("getRecordComponents", null), 12);
            } catch (NoSuchMethodException unused) {
                e0Var = new androidx.emoji2.text.e0(bool, bool, bool, bool, 12);
            }
            k2.c.f19461a = e0Var;
        }
        Method method = (Method) e0Var.f2600l;
        bool = method != null ? (Boolean) method.invoke(this.f20288a, null) : null;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    @Override // w8.g
    public final f9.c c() {
        return c.a(this.f20288a).b();
    }

    @Override // w8.g
    public final Collection d() {
        Class cls;
        Class cls2 = this.f20288a;
        cls = Object.class;
        if (kotlin.jvm.internal.p.a(cls2, cls)) {
            return kotlin.collections.z.f19728i;
        }
        com.squareup.moshi.w wVar = new com.squareup.moshi.w(2);
        Type genericSuperclass = cls2.getGenericSuperclass();
        wVar.a(genericSuperclass != null ? genericSuperclass : Object.class);
        wVar.b(cls2.getGenericInterfaces());
        ArrayList arrayList = wVar.f14489a;
        List listE = t7.a.E(arrayList.toArray(new Type[arrayList.size()]));
        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(listE, 10));
        Iterator it = listE.iterator();
        while (it.hasNext()) {
            arrayList2.add(new q((Type) it.next()));
        }
        return arrayList2;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof o) {
            return kotlin.jvm.internal.p.a(this.f20288a, ((o) obj).f20288a);
        }
        return false;
    }

    @Override // w8.d
    public final Collection getAnnotations() {
        Annotation[] declaredAnnotations;
        Class cls = this.f20288a;
        return (cls == null || (declaredAnnotations = cls.getDeclaredAnnotations()) == null) ? kotlin.collections.z.f19728i : kotlin.reflect.b0.q(declaredAnnotations);
    }

    @Override // w8.s
    public final f9.f getName() {
        Class cls = this.f20288a;
        return cls.isAnonymousClass() ? f9.f.j(kotlin.text.o.F0(cls.getName(), ".")) : f9.f.j(cls.getSimpleName());
    }

    @Override // w8.y
    public final ArrayList getTypeParameters() {
        TypeVariable[] typeParameters = this.f20288a.getTypeParameters();
        ArrayList arrayList = new ArrayList(typeParameters.length);
        for (TypeVariable typeVariable : typeParameters) {
            arrayList.add(new d0(typeVariable));
        }
        return arrayList;
    }

    @Override // w8.r
    public final k1 getVisibility() {
        int modifiers = this.f20288a.getModifiers();
        return Modifier.isPublic(modifiers) ? i1.k : Modifier.isPrivate(modifiers) ? i1.f15511h : Modifier.isProtected(modifiers) ? Modifier.isStatic(modifiers) ? k8.a.f19524f : k8.a.f19523e : k8.a.f19522d;
    }

    public final int hashCode() {
        return this.f20288a.hashCode();
    }

    @Override // w8.g
    public final List i() {
        return ga.r.U(new ga.t(ga.r.J(kotlin.collections.r.G(this.f20288a.getDeclaredConstructors()), j.f20283i), k.f20284i));
    }

    @Override // w8.r
    public final boolean isAbstract() {
        return Modifier.isAbstract(this.f20288a.getModifiers());
    }

    @Override // w8.r
    public final boolean isFinal() {
        return Modifier.isFinal(this.f20288a.getModifiers());
    }

    @Override // w8.d
    public final w8.a j(f9.c cVar) {
        Annotation[] declaredAnnotations;
        Class cls = this.f20288a;
        if (cls == null || (declaredAnnotations = cls.getDeclaredAnnotations()) == null) {
            return null;
        }
        return kotlin.reflect.b0.p(declaredAnnotations, cVar);
    }

    @Override // w8.g
    public final ArrayList k() {
        androidx.emoji2.text.e0 e0Var = k2.c.f19461a;
        if (e0Var == null) {
            try {
                e0Var = new androidx.emoji2.text.e0(Class.class.getMethod("isSealed", null), Class.class.getMethod("getPermittedSubclasses", null), Class.class.getMethod("isRecord", null), Class.class.getMethod("getRecordComponents", null), 12);
            } catch (NoSuchMethodException unused) {
                e0Var = new androidx.emoji2.text.e0(objArr, objArr, objArr, objArr, 12);
            }
            k2.c.f19461a = e0Var;
        }
        Method method = (Method) e0Var.f2603o;
        objArr = method != null ? (Object[]) method.invoke(this.f20288a, null) : null;
        if (objArr == null) {
            objArr = new Object[0];
        }
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object obj : objArr) {
            arrayList.add(new b0(obj));
        }
        return arrayList;
    }

    @Override // w8.g
    public final boolean m() {
        return this.f20288a.isAnnotation();
    }

    @Override // w8.g
    public final o n() {
        Class<?> declaringClass = this.f20288a.getDeclaringClass();
        if (declaringClass != null) {
            return new o(declaringClass);
        }
        return null;
    }

    @Override // w8.g
    public final boolean o() {
        androidx.emoji2.text.e0 e0Var = k2.c.f19461a;
        if (e0Var == null) {
            try {
                e0Var = new androidx.emoji2.text.e0(Class.class.getMethod("isSealed", null), Class.class.getMethod("getPermittedSubclasses", null), Class.class.getMethod("isRecord", null), Class.class.getMethod("getRecordComponents", null), 12);
            } catch (NoSuchMethodException unused) {
                e0Var = new androidx.emoji2.text.e0(bool, bool, bool, bool, 12);
            }
            k2.c.f19461a = e0Var;
        }
        Method method = (Method) e0Var.f2602n;
        bool = method != null ? (Boolean) method.invoke(this.f20288a, null) : null;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    @Override // w8.g
    public final List q() {
        return ga.r.U(new ga.t(ga.r.I(kotlin.collections.r.G(this.f20288a.getDeclaredMethods()), new ab.h(this, 26)), n.f20287i));
    }

    @Override // w8.g
    public final boolean s() {
        return this.f20288a.isEnum();
    }

    public final String toString() {
        return o.class.getName() + ": " + this.f20288a;
    }

    @Override // w8.g
    public final Collection w() {
        androidx.emoji2.text.e0 e0Var = k2.c.f19461a;
        if (e0Var == null) {
            try {
                e0Var = new androidx.emoji2.text.e0(Class.class.getMethod("isSealed", null), Class.class.getMethod("getPermittedSubclasses", null), Class.class.getMethod("isRecord", null), Class.class.getMethod("getRecordComponents", null), 12);
            } catch (NoSuchMethodException unused) {
                e0Var = new androidx.emoji2.text.e0(clsArr, clsArr, clsArr, clsArr, 12);
            }
            k2.c.f19461a = e0Var;
        }
        Method method = (Method) e0Var.f2601m;
        clsArr = method != null ? (Class[]) method.invoke(this.f20288a, null) : null;
        if (clsArr == null) {
            return kotlin.collections.z.f19728i;
        }
        ArrayList arrayList = new ArrayList(clsArr.length);
        for (Class cls : clsArr) {
            arrayList.add(new q(cls));
        }
        return arrayList;
    }

    @Override // w8.g
    public final List x() {
        return ga.r.U(ga.r.Q(ga.r.J(kotlin.collections.r.G(this.f20288a.getDeclaredClasses()), b.f20262n), b.f20263o));
    }
}
