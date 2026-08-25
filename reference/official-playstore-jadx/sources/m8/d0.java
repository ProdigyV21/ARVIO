package m8;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: classes5.dex */
public final class d0 extends s implements g, w8.x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TypeVariable f20271a;

    public d0(TypeVariable typeVariable) {
        this.f20271a = typeVariable;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof d0) {
            return kotlin.jvm.internal.p.a(this.f20271a, ((d0) obj).f20271a);
        }
        return false;
    }

    @Override // w8.d
    public final Collection getAnnotations() {
        Annotation[] declaredAnnotations;
        TypeVariable typeVariable = this.f20271a;
        AnnotatedElement annotatedElement = typeVariable instanceof AnnotatedElement ? (AnnotatedElement) typeVariable : null;
        return (annotatedElement == null || (declaredAnnotations = annotatedElement.getDeclaredAnnotations()) == null) ? kotlin.collections.z.f19728i : kotlin.reflect.b0.q(declaredAnnotations);
    }

    @Override // w8.s
    public final f9.f getName() {
        return f9.f.j(this.f20271a.getName());
    }

    @Override // w8.x
    public final Collection getUpperBounds() {
        Type[] bounds = this.f20271a.getBounds();
        ArrayList arrayList = new ArrayList(bounds.length);
        for (Type type : bounds) {
            arrayList.add(new q(type));
        }
        q qVar = (q) kotlin.collections.x.T0(arrayList);
        return kotlin.jvm.internal.p.a(qVar != null ? qVar.f20290a : null, Object.class) ? kotlin.collections.z.f19728i : arrayList;
    }

    public final int hashCode() {
        return this.f20271a.hashCode();
    }

    @Override // w8.d
    public final w8.a j(f9.c cVar) {
        Annotation[] declaredAnnotations;
        TypeVariable typeVariable = this.f20271a;
        AnnotatedElement annotatedElement = typeVariable instanceof AnnotatedElement ? (AnnotatedElement) typeVariable : null;
        if (annotatedElement == null || (declaredAnnotations = annotatedElement.getDeclaredAnnotations()) == null) {
            return null;
        }
        return kotlin.reflect.b0.p(declaredAnnotations, cVar);
    }

    public final String toString() {
        return d0.class.getName() + ": " + this.f20271a;
    }
}
