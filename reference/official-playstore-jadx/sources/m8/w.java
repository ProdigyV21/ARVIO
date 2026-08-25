package m8;

import g8.i1;
import g8.k1;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.Collection;

/* JADX INFO: loaded from: classes5.dex */
public abstract class w extends s implements g, y, w8.p {
    @Override // w8.r
    public final boolean G() {
        return Modifier.isStatic(H().getModifiers());
    }

    public abstract Member H();

    /* JADX WARN: Removed duplicated region for block: B:41:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0115  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.ArrayList I(java.lang.reflect.Type[] r13, java.lang.annotation.Annotation[][] r14, boolean r15) {
        /*
            Method dump skipped, instruction units count: 293
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: m8.w.I(java.lang.reflect.Type[], java.lang.annotation.Annotation[][], boolean):java.util.ArrayList");
    }

    public final boolean equals(Object obj) {
        return (obj instanceof w) && kotlin.jvm.internal.p.a(H(), ((w) obj).H());
    }

    @Override // w8.d
    public final Collection getAnnotations() {
        Annotation[] declaredAnnotations = ((AnnotatedElement) H()).getDeclaredAnnotations();
        return declaredAnnotations != null ? kotlin.reflect.b0.q(declaredAnnotations) : kotlin.collections.z.f19728i;
    }

    @Override // w8.s
    public final f9.f getName() {
        String name = H().getName();
        f9.f fVarJ = name != null ? f9.f.j(name) : null;
        return fVarJ == null ? f9.h.f15372a : fVarJ;
    }

    @Override // w8.r
    public final k1 getVisibility() {
        int modifiers = H().getModifiers();
        return Modifier.isPublic(modifiers) ? i1.k : Modifier.isPrivate(modifiers) ? i1.f15511h : Modifier.isProtected(modifiers) ? Modifier.isStatic(modifiers) ? k8.a.f19524f : k8.a.f19523e : k8.a.f19522d;
    }

    public final int hashCode() {
        return H().hashCode();
    }

    @Override // w8.r
    public final boolean isAbstract() {
        return Modifier.isAbstract(H().getModifiers());
    }

    @Override // w8.r
    public final boolean isFinal() {
        return Modifier.isFinal(H().getModifiers());
    }

    @Override // w8.d
    public final w8.a j(f9.c cVar) {
        Annotation[] declaredAnnotations = ((AnnotatedElement) H()).getDeclaredAnnotations();
        if (declaredAnnotations != null) {
            return kotlin.reflect.b0.p(declaredAnnotations, cVar);
        }
        return null;
    }

    @Override // w8.p
    public final o l() {
        return new o(H().getDeclaringClass());
    }

    public final String toString() {
        return getClass().getName() + ": " + H();
    }
}
