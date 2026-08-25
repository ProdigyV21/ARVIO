package m8;

import com.google.common.util.concurrent.r0;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.collections.h0;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;

/* JADX INFO: loaded from: classes5.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final List f20266a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Map f20267b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Map f20268c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Map f20269d;

    static {
        m0 m0Var = l0.f19747a;
        int i10 = 0;
        List<kotlin.reflect.d> listE = t7.a.E(m0Var.b(Boolean.TYPE), m0Var.b(Byte.TYPE), m0Var.b(Character.TYPE), m0Var.b(Double.TYPE), m0Var.b(Float.TYPE), m0Var.b(Integer.TYPE), m0Var.b(Long.TYPE), m0Var.b(Short.TYPE));
        f20266a = listE;
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(listE, 10));
        for (kotlin.reflect.d dVar : listE) {
            arrayList.add(new x6.x(r0.n(dVar), r0.o(dVar)));
        }
        f20267b = h0.A0(arrayList);
        List<kotlin.reflect.d> list = f20266a;
        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(list, 10));
        for (kotlin.reflect.d dVar2 : list) {
            arrayList2.add(new x6.x(r0.o(dVar2), r0.n(dVar2)));
        }
        f20268c = h0.A0(arrayList2);
        List listE2 = t7.a.E(r7.a.class, r7.l.class, r7.p.class, r7.q.class, r7.r.class, r7.s.class, r7.t.class, r7.u.class, r7.v.class, r7.w.class, r7.b.class, r7.c.class, r7.d.class, r7.e.class, r7.f.class, r7.g.class, r7.h.class, r7.i.class, r7.j.class, r7.k.class, r7.m.class, r7.n.class, r7.o.class);
        ArrayList arrayList3 = new ArrayList(kotlin.collections.s.U(listE2, 10));
        for (Object obj : listE2) {
            int i11 = i10 + 1;
            if (i10 < 0) {
                t7.a.Q();
                throw null;
            }
            arrayList3.add(new x6.x((Class) obj, Integer.valueOf(i10)));
            i10 = i11;
        }
        f20269d = h0.A0(arrayList3);
    }

    public static final f9.b a(Class cls) {
        if (cls.isPrimitive()) {
            throw new IllegalArgumentException("Can't compute ClassId for primitive type: " + cls);
        }
        if (cls.isArray()) {
            throw new IllegalArgumentException("Can't compute ClassId for array type: " + cls);
        }
        if (cls.getEnclosingMethod() == null && cls.getEnclosingConstructor() == null && cls.getSimpleName().length() != 0) {
            Class<?> declaringClass = cls.getDeclaringClass();
            return declaringClass != null ? a(declaringClass).d(f9.f.j(cls.getSimpleName())) : f9.b.j(new f9.c(cls.getName()));
        }
        f9.c cVar = new f9.c(cls.getName());
        return new f9.b(cVar.e(), f9.c.j(cVar.f()), true);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final String b(Class cls) {
        if (!cls.isPrimitive()) {
            if (cls.isArray()) {
                return cls.getName().replace('.', '/');
            }
            return "L" + cls.getName().replace('.', '/') + ';';
        }
        String name = cls.getName();
        switch (name.hashCode()) {
            case -1325958191:
                if (name.equals("double")) {
                    return "D";
                }
                break;
            case 104431:
                if (name.equals("int")) {
                    return "I";
                }
                break;
            case 3039496:
                if (name.equals("byte")) {
                    return "B";
                }
                break;
            case 3052374:
                if (name.equals("char")) {
                    return "C";
                }
                break;
            case 3327612:
                if (name.equals("long")) {
                    return "J";
                }
                break;
            case 3625364:
                if (name.equals("void")) {
                    return "V";
                }
                break;
            case 64711720:
                if (name.equals("boolean")) {
                    return "Z";
                }
                break;
            case 97526364:
                if (name.equals("float")) {
                    return "F";
                }
                break;
            case 109413500:
                if (name.equals("short")) {
                    return "S";
                }
                break;
        }
        throw new IllegalArgumentException("Unsupported primitive type: " + cls);
    }

    public static final List c(Type type) {
        if (!(type instanceof ParameterizedType)) {
            return kotlin.collections.z.f19728i;
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        return parameterizedType.getOwnerType() == null ? kotlin.collections.r.o0(parameterizedType.getActualTypeArguments()) : ga.r.U(ga.r.L(ga.r.M(type, b.f20260l), b.f20261m));
    }
}
