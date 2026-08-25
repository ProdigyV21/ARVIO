package m8;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collection;

/* JADX INFO: loaded from: classes5.dex */
public final class q extends c0 implements w8.j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Type f20290a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s f20291b;

    public q(Type type) {
        s oVar;
        this.f20290a = type;
        if (type instanceof Class) {
            oVar = new o((Class) type);
        } else if (type instanceof TypeVariable) {
            oVar = new d0((TypeVariable) type);
        } else {
            if (!(type instanceof ParameterizedType)) {
                throw new IllegalStateException("Not a classifier type (" + type.getClass() + "): " + type);
            }
            oVar = new o((Class) ((ParameterizedType) type).getRawType());
        }
        this.f20291b = oVar;
    }

    @Override // w8.j
    public final String B() {
        throw new UnsupportedOperationException("Type not found: " + this.f20290a);
    }

    @Override // m8.c0
    public final Type H() {
        return this.f20290a;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [m8.s, w8.i] */
    @Override // w8.j
    public final w8.i b() {
        return this.f20291b;
    }

    @Override // w8.d
    public final Collection getAnnotations() {
        return kotlin.collections.z.f19728i;
    }

    @Override // m8.c0, w8.d
    public final w8.a j(f9.c cVar) {
        return null;
    }

    @Override // w8.j
    public final boolean r() {
        Type type = this.f20290a;
        if (type instanceof Class) {
            if (!(((Class) type).getTypeParameters().length == 0)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0034  */
    @Override // w8.j
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.ArrayList u() {
        /*
            r6 = this;
            java.lang.reflect.Type r0 = r6.f20290a
            java.util.List r0 = m8.c.c(r0)
            java.util.ArrayList r1 = new java.util.ArrayList
            r2 = 10
            int r2 = kotlin.collections.s.U(r0, r2)
            r1.<init>(r2)
            java.util.Iterator r0 = r0.iterator()
        L15:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L61
            java.lang.Object r2 = r0.next()
            java.lang.reflect.Type r2 = (java.lang.reflect.Type) r2
            boolean r3 = r2 instanceof java.lang.Class
            if (r3 == 0) goto L34
            r4 = r2
            java.lang.Class r4 = (java.lang.Class) r4
            boolean r5 = r4.isPrimitive()
            if (r5 == 0) goto L34
            m8.a0 r2 = new m8.a0
            r2.<init>(r4)
            goto L5d
        L34:
            boolean r4 = r2 instanceof java.lang.reflect.GenericArrayType
            if (r4 != 0) goto L57
            if (r3 == 0) goto L44
            r3 = r2
            java.lang.Class r3 = (java.lang.Class) r3
            boolean r3 = r3.isArray()
            if (r3 == 0) goto L44
            goto L57
        L44:
            boolean r3 = r2 instanceof java.lang.reflect.WildcardType
            if (r3 == 0) goto L51
            m8.f0 r3 = new m8.f0
            java.lang.reflect.WildcardType r2 = (java.lang.reflect.WildcardType) r2
            r3.<init>(r2)
        L4f:
            r2 = r3
            goto L5d
        L51:
            m8.q r3 = new m8.q
            r3.<init>(r2)
            goto L4f
        L57:
            m8.i r3 = new m8.i
            r3.<init>(r2)
            goto L4f
        L5d:
            r1.add(r2)
            goto L15
        L61:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: m8.q.u():java.util.ArrayList");
    }

    @Override // w8.j
    public final String z() {
        return this.f20290a.toString();
    }
}
