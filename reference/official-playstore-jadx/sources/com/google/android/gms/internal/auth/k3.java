package com.google.android.gms.internal.auth;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public abstract class k3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class f13055a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final m3 f13056b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final m3 f13057c;

    static {
        Class<?> cls;
        Class<?> cls2;
        m3 m3Var = null;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        f13055a = cls;
        try {
            cls2 = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused2) {
            cls2 = null;
        }
        if (cls2 != null) {
            try {
                m3Var = (m3) cls2.getConstructor(null).newInstance(null);
            } catch (Throwable unused3) {
            }
        }
        f13056b = m3Var;
        f13057c = new m3();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static void a(m3 m3Var, Object obj, Object obj2) {
        m3Var.getClass();
        d2 d2Var = (d2) obj;
        l3 l3Var = d2Var.zzc;
        l3 l3Var2 = ((d2) obj2).zzc;
        l3 l3Var3 = l3.f13065e;
        if (!l3Var3.equals(l3Var2)) {
            if (l3Var3.equals(l3Var)) {
                int i10 = l3Var.f13066a + l3Var2.f13066a;
                int[] iArrCopyOf = Arrays.copyOf(l3Var.f13067b, i10);
                System.arraycopy(l3Var2.f13067b, 0, iArrCopyOf, l3Var.f13066a, l3Var2.f13066a);
                Object[] objArrCopyOf = Arrays.copyOf(l3Var.f13068c, i10);
                System.arraycopy(l3Var2.f13068c, 0, objArrCopyOf, l3Var.f13066a, l3Var2.f13066a);
                l3Var = new l3(i10, iArrCopyOf, objArrCopyOf, true);
            } else {
                l3Var.getClass();
                if (!l3Var2.equals(l3Var3)) {
                    if (!l3Var.f13069d) {
                        throw new UnsupportedOperationException();
                    }
                    int i11 = l3Var.f13066a + l3Var2.f13066a;
                    l3Var.c(i11);
                    System.arraycopy(l3Var2.f13067b, 0, l3Var.f13067b, l3Var.f13066a, l3Var2.f13066a);
                    System.arraycopy(l3Var2.f13068c, 0, l3Var.f13068c, l3Var.f13066a, l3Var2.f13066a);
                    l3Var.f13066a = i11;
                }
            }
        }
        d2Var.zzc = l3Var;
    }

    public static boolean b(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }
}
