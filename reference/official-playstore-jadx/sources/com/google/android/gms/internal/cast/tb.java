package com.google.android.gms.internal.cast;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public abstract class tb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final k5 f13618a;

    static {
        int i10 = da.f13235a;
        f13618a = new k5(25);
    }

    public static boolean a(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
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
    public static void b(Object obj, Object obj2) {
        pa paVar = (pa) obj;
        c cVar = paVar.zzc;
        c cVar2 = ((pa) obj2).zzc;
        c cVar3 = c.f13198e;
        if (!cVar3.equals(cVar2)) {
            if (cVar3.equals(cVar)) {
                cVar.getClass();
                cVar2.getClass();
                int[] iArrCopyOf = Arrays.copyOf(cVar.f13199a, 0);
                System.arraycopy(cVar2.f13199a, 0, iArrCopyOf, 0, 0);
                Object[] objArrCopyOf = Arrays.copyOf(cVar.f13200b, 0);
                System.arraycopy(cVar2.f13200b, 0, objArrCopyOf, 0, 0);
                cVar = new c(iArrCopyOf, objArrCopyOf, true);
            } else {
                cVar.getClass();
                if (!cVar2.equals(cVar3)) {
                    if (!cVar.f13202d) {
                        throw new UnsupportedOperationException();
                    }
                    int[] iArr = cVar.f13199a;
                    int length = iArr.length;
                    System.arraycopy(cVar2.f13199a, 0, iArr, 0, 0);
                    System.arraycopy(cVar2.f13200b, 0, cVar.f13200b, 0, 0);
                }
            }
        }
        paVar.zzc = cVar;
    }

    public static int c(List list) {
        int size = list.size();
        int i10 = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof cb)) {
            int iP = 0;
            while (i10 < size) {
                iP += ia.P(((Long) list.get(i10)).longValue());
                i10++;
            }
            return iP;
        }
        cb cbVar = (cb) list;
        int iP2 = 0;
        while (i10 < size) {
            iP2 += ia.P(cbVar.b(i10));
            i10++;
        }
        return iP2;
    }

    public static int d(List list) {
        int size = list.size();
        int i10 = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof cb)) {
            int iP = 0;
            while (i10 < size) {
                iP += ia.P(((Long) list.get(i10)).longValue());
                i10++;
            }
            return iP;
        }
        cb cbVar = (cb) list;
        int iP2 = 0;
        while (i10 < size) {
            iP2 += ia.P(cbVar.b(i10));
            i10++;
        }
        return iP2;
    }

    public static int e(List list) {
        int size = list.size();
        int i10 = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof cb)) {
            int iP = 0;
            while (i10 < size) {
                long jLongValue = ((Long) list.get(i10)).longValue();
                iP += ia.P((jLongValue >> 63) ^ (jLongValue + jLongValue));
                i10++;
            }
            return iP;
        }
        cb cbVar = (cb) list;
        int iP2 = 0;
        while (i10 < size) {
            long jB = cbVar.b(i10);
            iP2 += ia.P((jB >> 63) ^ (jB + jB));
            i10++;
        }
        return iP2;
    }

    public static int f(List list) {
        int size = list.size();
        int i10 = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof qa)) {
            int iP = 0;
            while (i10 < size) {
                iP += ia.P(((Integer) list.get(i10)).intValue());
                i10++;
            }
            return iP;
        }
        qa qaVar = (qa) list;
        int iP2 = 0;
        while (i10 < size) {
            iP2 += ia.P(qaVar.b(i10));
            i10++;
        }
        return iP2;
    }

    public static int g(List list) {
        int size = list.size();
        int i10 = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof qa)) {
            int iP = 0;
            while (i10 < size) {
                iP += ia.P(((Integer) list.get(i10)).intValue());
                i10++;
            }
            return iP;
        }
        qa qaVar = (qa) list;
        int iP2 = 0;
        while (i10 < size) {
            iP2 += ia.P(qaVar.b(i10));
            i10++;
        }
        return iP2;
    }

    public static int h(List list) {
        int size = list.size();
        int i10 = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof qa)) {
            int iO = 0;
            while (i10 < size) {
                iO += ia.O(((Integer) list.get(i10)).intValue());
                i10++;
            }
            return iO;
        }
        qa qaVar = (qa) list;
        int iO2 = 0;
        while (i10 < size) {
            iO2 += ia.O(qaVar.b(i10));
            i10++;
        }
        return iO2;
    }

    public static int i(List list) {
        int size = list.size();
        int i10 = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof qa)) {
            int iO = 0;
            while (i10 < size) {
                int iIntValue = ((Integer) list.get(i10)).intValue();
                iO += ia.O((iIntValue >> 31) ^ (iIntValue + iIntValue));
                i10++;
            }
            return iO;
        }
        qa qaVar = (qa) list;
        int iO2 = 0;
        while (i10 < size) {
            int iB = qaVar.b(i10);
            iO2 += ia.O((iB >> 31) ^ (iB + iB));
            i10++;
        }
        return iO2;
    }

    public static int j(int i10, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (ia.O(i10 << 3) + 4) * size;
    }

    public static int k(int i10, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (ia.O(i10 << 3) + 8) * size;
    }
}
