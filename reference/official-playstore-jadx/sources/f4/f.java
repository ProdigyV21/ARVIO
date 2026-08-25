package f4;

import a9.k1;
import a9.p0;
import a9.x1;
import android.util.Log;
import android.util.LongSparseArray;
import android.view.View;
import androidx.appcompat.app.b1;
import com.arvio.tv.R;
import com.google.common.util.concurrent.r0;
import g8.b0;
import g8.e1;
import ga.n;
import ga.r;
import h.f0;
import io.ktor.http.ContentDisposition;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.RoundingMode;
import java.net.IDN;
import java.net.InetAddress;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.x;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;
import kotlin.text.u;
import s9.o;
import u9.p;
import x6.e0;
import x6.i0;
import x6.q0;
import x6.s;
import x6.u0;

/* JADX INFO: loaded from: classes4.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static ClassLoader f15266a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Thread f15267b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Field f15268c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f15269d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Class f15270e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static boolean f15271f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static Field f15272g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static boolean f15273h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static Field f15274i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static boolean f15275j;
    public static io.sentry.android.replay.viewhierarchy.c k;

    public static final k1 A(x1 x1Var, androidx.work.impl.constraints.j jVar) {
        int i10 = x1Var.f736m;
        if ((i10 & 4) == 4) {
            return x1Var.f739p;
        }
        if ((i10 & 8) == 8) {
            return jVar.b(x1Var.f740q);
        }
        throw new IllegalStateException("No type in ProtoBuf.ValueParameter");
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00b7 A[Catch: all -> 0x00b3, PHI: r2
      0x00b7: PHI (r2v1 java.lang.Thread) = (r2v0 java.lang.Thread), (r2v11 java.lang.Thread) binds: [B:7:0x000c, B:47:0x00b0] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #3 {, blocks: (B:4:0x0003, B:6:0x0007, B:8:0x000e, B:46:0x00ae, B:61:0x00e5, B:12:0x0023, B:52:0x00b6, B:53:0x00b7, B:64:0x00e9, B:65:0x00ea, B:13:0x0024, B:15:0x0031, B:25:0x004b, B:26:0x0052, B:28:0x005d, B:34:0x0072, B:35:0x0079, B:43:0x008a, B:44:0x00ac, B:18:0x0040, B:54:0x00b8, B:60:0x00e4, B:59:0x00c2), top: B:76:0x0003, inners: #2, #6 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static synchronized java.lang.ClassLoader B() {
        /*
            Method dump skipped, instruction units count: 240
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: f4.f.B():java.lang.ClassLoader");
    }

    public static String C(String str, Object... objArr) {
        int length;
        int iIndexOf;
        StringBuilder sb2 = new StringBuilder(str.length() + (objArr.length * 16));
        int i10 = 0;
        int i11 = 0;
        while (true) {
            length = objArr.length;
            if (i10 >= length || (iIndexOf = str.indexOf("%s", i11)) == -1) {
                break;
            }
            sb2.append((CharSequence) str, i11, iIndexOf);
            sb2.append(D(objArr[i10]));
            i11 = iIndexOf + 2;
            i10++;
        }
        sb2.append((CharSequence) str, i11, str.length());
        if (i10 < length) {
            String str2 = " [";
            while (i10 < objArr.length) {
                sb2.append(str2);
                sb2.append(D(objArr[i10]));
                i10++;
                str2 = ", ";
            }
            sb2.append(']');
        }
        return sb2.toString();
    }

    public static String D(Object obj) {
        if (obj == null) {
            return "null";
        }
        try {
            return obj.toString();
        } catch (Exception e5) {
            String name = obj.getClass().getName();
            String hexString = Integer.toHexString(System.identityHashCode(obj));
            String strQ = androidx.compose.material3.d.q(new StringBuilder(name.length() + 1 + String.valueOf(hexString).length()), name, "@", hexString);
            Logger.getLogger("com.google.common.base.Strings").logp(Level.WARNING, "com.google.common.base.Strings", "lenientToString", "Exception during lenientFormat for ".concat(strQ), (Throwable) e5);
            String name2 = e5.getClass().getName();
            StringBuilder sb2 = new StringBuilder(strQ.length() + 8 + name2.length() + 1);
            y.a.i(sb2, "<", strQ, " threw ", name2);
            sb2.append(">");
            return sb2.toString();
        }
    }

    public static /* synthetic */ void a(int i10) {
        String str = i10 != 18 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i10 != 18 ? 3 : 2];
        switch (i10) {
            case 1:
            case 7:
            case 13:
                objArr[0] = "membersFromSupertypes";
                break;
            case 2:
            case 8:
            case 14:
                objArr[0] = "membersFromCurrent";
                break;
            case 3:
            case 9:
            case 15:
                objArr[0] = "classDescriptor";
                break;
            case 4:
            case 10:
            case 16:
                objArr[0] = "errorReporter";
                break;
            case 5:
            case 11:
            case 17:
                objArr[0] = "overridingUtil";
                break;
            case 6:
            case 12:
            case 19:
            default:
                objArr[0] = ContentDisposition.Parameters.Name;
                break;
            case 18:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/load/java/components/DescriptorResolverUtils";
                break;
            case 20:
                objArr[0] = "annotationClass";
                break;
        }
        if (i10 != 18) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/components/DescriptorResolverUtils";
        } else {
            objArr[1] = "resolveOverrides";
        }
        switch (i10) {
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                objArr[2] = "resolveOverridesForStaticMembers";
                break;
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
                objArr[2] = "resolveOverrides";
                break;
            case 18:
                break;
            case 19:
            case 20:
                objArr[2] = "getAnnotationParameterByName";
                break;
            default:
                objArr[2] = "resolveOverridesForNonStaticMembers";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i10 == 18) {
            throw new IllegalStateException(str2);
        }
    }

    public static void b(Appendable appendable, Object obj, r7.l lVar) throws IOException {
        if (lVar != null) {
            appendable.append((CharSequence) lVar.invoke(obj));
            return;
        }
        if (obj == null ? true : obj instanceof CharSequence) {
            appendable.append((CharSequence) obj);
        } else if (obj instanceof Character) {
            appendable.append(((Character) obj).charValue());
        } else {
            appendable.append(obj.toString());
        }
    }

    public static int d(int i10, int i11) {
        long j10 = ((long) i10) + ((long) i11);
        int i12 = (int) j10;
        if (j10 == ((long) i12)) {
            return i12;
        }
        throw new ArithmeticException(androidx.compose.foundation.c.s("overflow: checkedAdd(", i10, ", ", i11, ")"));
    }

    public static ClassLoader e(ClassLoader classLoader) {
        if (classLoader != null) {
            return classLoader;
        }
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        return contextClassLoader != null ? contextClassLoader : ClassLoader.getSystemClassLoader();
    }

    public static final int f(int i10) {
        int iC = f0.c(i10);
        if (iC == 0) {
            return 3;
        }
        if (iC == 1) {
            return 1;
        }
        if (iC == 2) {
            return 2;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static t9.c g(f9.c cVar, p pVar, b0 b0Var, InputStream inputStream) throws IOException {
        p0 p0Var;
        c0 c0Var;
        try {
            b9.a aVar = b9.a.f7258f;
            b9.a aVarL = qb.d.L(inputStream);
            b9.a aVar2 = b9.a.f7258f;
            int i10 = aVarL.f7426c;
            int i11 = aVar2.f7426c;
            int i12 = aVar2.f7425b;
            int i13 = aVarL.f7425b;
            if (i13 == 0) {
                if (i12 == 0 && i10 == i11) {
                    kotlin.reflect.jvm.internal.impl.protobuf.l lVar = new kotlin.reflect.jvm.internal.impl.protobuf.l();
                    b9.b.a(lVar);
                    a9.a aVar3 = p0.f615u;
                    aVar3.getClass();
                    kotlin.reflect.jvm.internal.impl.protobuf.i iVar = new kotlin.reflect.jvm.internal.impl.protobuf.i(inputStream);
                    c0Var = (c0) aVar3.b(iVar, lVar);
                    try {
                        iVar.a(0);
                        kotlin.reflect.jvm.internal.impl.protobuf.d.c(c0Var);
                        p0Var = (p0) c0Var;
                    } catch (InvalidProtocolBufferException e5) {
                        e5.f19759i = c0Var;
                        throw e5;
                    }
                }
                p0Var = null;
            } else if (i13 != i12 || i10 > i11) {
                p0Var = null;
            } else {
                kotlin.reflect.jvm.internal.impl.protobuf.l lVar2 = new kotlin.reflect.jvm.internal.impl.protobuf.l();
                b9.b.a(lVar2);
                a9.a aVar32 = p0.f615u;
                aVar32.getClass();
                kotlin.reflect.jvm.internal.impl.protobuf.i iVar2 = new kotlin.reflect.jvm.internal.impl.protobuf.i(inputStream);
                c0Var = (c0) aVar32.b(iVar2, lVar2);
                iVar2.a(0);
                kotlin.reflect.jvm.internal.impl.protobuf.d.c(c0Var);
                p0Var = (p0) c0Var;
            }
            p0 p0Var2 = p0Var;
            inputStream.close();
            if (p0Var2 != null) {
                return new t9.c(cVar, pVar, b0Var, p0Var2, aVarL);
            }
            throw new UnsupportedOperationException("Kotlin built-in definition format version is not supported: expected " + aVar2 + ", actual " + aVarL + ". Please update Kotlin");
        } finally {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x00cb, code lost:
    
        if (r7 == 16) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00cd, code lost:
    
        if (r8 != (-1)) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00d1, code lost:
    
        r0 = r7 - r8;
        java.lang.System.arraycopy(r3, r8, r3, 16 - r0, r0);
        java.util.Arrays.fill(r3, r8, (16 - r7) + r8, (byte) 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00e1, code lost:
    
        return java.net.InetAddress.getByAddress(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:?, code lost:
    
        return null;
     */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00a2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.net.InetAddress h(int r17, int r18, java.lang.String r19) {
        /*
            Method dump skipped, instruction units count: 226
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: f4.f.h(int, int, java.lang.String):java.net.InetAddress");
    }

    public static int i(int i10, int i11) {
        RoundingMode roundingMode = RoundingMode.CEILING;
        roundingMode.getClass();
        if (i11 == 0) {
            throw new ArithmeticException("/ by zero");
        }
        int i12 = i10 / i11;
        int i13 = i10 - (i11 * i12);
        if (i13 == 0) {
            return i12;
        }
        int i14 = ((i10 ^ i11) >> 31) | 1;
        switch (com.google.common.math.e.f14165a[roundingMode.ordinal()]) {
            case 1:
                kotlin.reflect.b0.h(i13 == 0);
                return i12;
            case 2:
                return i12;
            case 3:
                if (i14 >= 0) {
                    return i12;
                }
                break;
            case 4:
                break;
            case 5:
                if (i14 <= 0) {
                    return i12;
                }
                break;
            case 6:
            case 7:
            case 8:
                int iAbs = Math.abs(i13);
                int iAbs2 = iAbs - (Math.abs(i11) - iAbs);
                if (iAbs2 == 0) {
                    RoundingMode roundingMode2 = RoundingMode.HALF_UP;
                    RoundingMode roundingMode3 = RoundingMode.HALF_EVEN;
                    return i12;
                }
                if (iAbs2 <= 0) {
                    return i12;
                }
                break;
            default:
                throw new AssertionError();
        }
        return i12 + i14;
    }

    public static void j(Object obj) {
        LongSparseArray longSparseArray;
        if (!f15271f) {
            try {
                f15270e = Class.forName("android.content.res.ThemedResourceCache");
            } catch (ClassNotFoundException e5) {
                Log.e("ResourcesFlusher", "Could not find ThemedResourceCache class", e5);
            }
            f15271f = true;
        }
        Class cls = f15270e;
        if (cls == null) {
            return;
        }
        if (!f15273h) {
            try {
                Field declaredField = cls.getDeclaredField("mUnthemedEntries");
                f15272g = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e6) {
                Log.e("ResourcesFlusher", "Could not retrieve ThemedResourceCache#mUnthemedEntries field", e6);
            }
            f15273h = true;
        }
        Field field = f15272g;
        if (field == null) {
            return;
        }
        try {
            longSparseArray = (LongSparseArray) field.get(obj);
        } catch (IllegalAccessException e10) {
            Log.e("ResourcesFlusher", "Could not retrieve value from ThemedResourceCache#mUnthemedEntries", e10);
            longSparseArray = null;
        }
        if (longSparseArray != null) {
            b1.a(longSparseArray);
        }
    }

    public static final b2.g k(View view) {
        return (b2.g) r.K(r.Q(r.M(view, b2.h.f7123l), b2.h.f7124m));
    }

    public static e1 l(f9.f fVar, g8.f fVar2) {
        if (fVar == null) {
            a(19);
            throw null;
        }
        if (fVar2 == null) {
            a(20);
            throw null;
        }
        Collection collectionH = fVar2.h();
        if (collectionH.size() != 1) {
            return null;
        }
        for (e1 e1Var : ((g8.e) collectionH.iterator().next()).e()) {
            if (e1Var.getName().equals(fVar)) {
                return e1Var;
            }
        }
        return null;
    }

    public static int m(int i10) {
        if (i10 == 1) {
            return 0;
        }
        if (i10 == 2) {
            return 1;
        }
        if (i10 == 4) {
            return 2;
        }
        if (i10 == 8) {
            return 3;
        }
        if (i10 == 16) {
            return 4;
        }
        if (i10 == 32) {
            return 5;
        }
        if (i10 == 64) {
            return 6;
        }
        if (i10 == 128) {
            return 7;
        }
        if (i10 == 256) {
            return 8;
        }
        throw new IllegalArgumentException(a0.c.i(i10, "type needs to be >= FIRST and <= LAST, type="));
    }

    public static n n(r7.p pVar) {
        n nVar = new n();
        nVar.f15596n = t7.a.m(nVar, nVar, pVar);
        return nVar;
    }

    public static s o(int i10, r7.a aVar) {
        q0 q0Var = q0.f22597a;
        int iC = f0.c(i10);
        if (iC == 0) {
            return new i0(aVar);
        }
        if (iC == 1) {
            e0 e0Var = new e0();
            e0Var.f22582i = aVar;
            e0Var.f22583l = q0Var;
            return e0Var;
        }
        if (iC != 2) {
            throw new NoWhenBranchMatchedException();
        }
        u0 u0Var = new u0();
        u0Var.f22606i = aVar;
        u0Var.f22607l = q0Var;
        return u0Var;
    }

    public static int p(int i10) {
        RoundingMode roundingMode = RoundingMode.UNNECESSARY;
        if (i10 <= 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.o(i10, "x (", ") must be > 0"));
        }
        switch (com.google.common.math.e.f14165a[roundingMode.ordinal()]) {
            case 1:
                kotlin.reflect.b0.h((i10 > 0) & (((i10 + (-1)) & i10) == 0));
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return 32 - Integer.numberOfLeadingZeros(i10 - 1);
            case 6:
            case 7:
            case 8:
                int iNumberOfLeadingZeros = Integer.numberOfLeadingZeros(i10);
                return (31 - iNumberOfLeadingZeros) + ((~(~(((-1257966797) >>> iNumberOfLeadingZeros) - i10))) >>> 31);
            default:
                throw new AssertionError();
        }
        return 31 - Integer.numberOfLeadingZeros(i10);
    }

    public static final ArrayList q(LinkedHashMap linkedHashMap, r7.l lVar) {
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            androidx.navigation.g gVar = (androidx.navigation.g) entry.getValue();
            if (gVar != null && !gVar.f4667b && !gVar.f4668c) {
                linkedHashMap2.put(entry.getKey(), entry.getValue());
            }
        }
        Set setKeySet = linkedHashMap2.keySet();
        ArrayList arrayList = new ArrayList();
        for (Object obj : setKeySet) {
            if (((Boolean) lVar.invoke((String) obj)).booleanValue()) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final void r(db.c0 c0Var, String str, String str2) {
        c0Var.b(db.n.c(str2), str);
    }

    public static final void s(db.c0 c0Var, r7.l lVar) {
        db.c0 c0Var2 = new db.c0();
        lVar.invoke(c0Var2);
        c0Var.b(c0Var2.a(), "gotrue_meta_security");
    }

    public static LinkedHashSet t(f9.f fVar, Collection collection, Collection collection2, g8.f fVar2, o oVar, i9.n nVar, boolean z) {
        if (fVar == null) {
            a(12);
            throw null;
        }
        if (collection == null) {
            a(13);
            throw null;
        }
        if (fVar2 == null) {
            a(15);
            throw null;
        }
        if (oVar == null) {
            a(16);
            throw null;
        }
        if (nVar == null) {
            a(17);
            throw null;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        nVar.h(fVar, collection, collection2, fVar2, new q8.a(oVar, linkedHashSet, z));
        return linkedHashSet;
    }

    public static LinkedHashSet u(f9.f fVar, AbstractCollection abstractCollection, Collection collection, g8.f fVar2, o oVar, i9.n nVar) {
        if (fVar == null) {
            a(0);
            throw null;
        }
        if (fVar2 == null) {
            a(3);
            throw null;
        }
        if (oVar == null) {
            a(4);
            throw null;
        }
        if (nVar != null) {
            return t(fVar, abstractCollection, collection, fVar2, oVar, nVar, false);
        }
        a(5);
        throw null;
    }

    public static LinkedHashSet v(f9.f fVar, Collection collection, AbstractCollection abstractCollection, t8.i iVar, o oVar, i9.n nVar) {
        if (fVar == null) {
            a(6);
            throw null;
        }
        if (collection == null) {
            a(7);
            throw null;
        }
        if (iVar == null) {
            a(9);
            throw null;
        }
        if (oVar == null) {
            a(10);
            throw null;
        }
        if (nVar != null) {
            return t(fVar, collection, abstractCollection, iVar, oVar, nVar, true);
        }
        a(11);
        throw null;
    }

    public static final void w(View view, b2.g gVar) {
        view.setTag(R.id.view_tree_saved_state_registry_owner, gVar);
    }

    public static final String x(String str) {
        int i10 = 0;
        int i11 = -1;
        if (!kotlin.text.o.T(str, ":", false)) {
            try {
                String lowerCase = IDN.toASCII(str).toLowerCase(Locale.US);
                if (lowerCase.length() == 0) {
                    return null;
                }
                int length = lowerCase.length();
                for (int i12 = 0; i12 < length; i12++) {
                    char cCharAt = lowerCase.charAt(i12);
                    if (kotlin.jvm.internal.p.c(cCharAt, 31) <= 0 || kotlin.jvm.internal.p.c(cCharAt, 127) >= 0 || kotlin.text.o.e0(" #%/:?@[\\]", cCharAt, 0, false, 6) != -1) {
                        return null;
                    }
                }
                return lowerCase;
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }
        InetAddress inetAddressH = (u.P(str, "[", false) && u.K(str, "]", false)) ? h(1, str.length() - 1, str) : h(0, str.length(), str);
        if (inetAddressH == null) {
            return null;
        }
        byte[] address = inetAddressH.getAddress();
        if (address.length != 16) {
            if (address.length == 4) {
                return inetAddressH.getHostAddress();
            }
            throw new AssertionError(androidx.compose.material3.d.i('\'', "Invalid IPv6 address: '", str));
        }
        int i13 = 0;
        int i14 = 0;
        while (i13 < address.length) {
            int i15 = i13;
            while (i15 < 16 && address[i15] == 0 && address[i15 + 1] == 0) {
                i15 += 2;
            }
            int i16 = i15 - i13;
            if (i16 > i14 && i16 >= 4) {
                i11 = i13;
                i14 = i16;
            }
            i13 = i15 + 2;
        }
        xb.j jVar = new xb.j();
        while (i10 < address.length) {
            if (i10 == i11) {
                jVar.M0(58);
                i10 += i14;
                if (i10 == 16) {
                    jVar.M0(58);
                }
            } else {
                if (i10 > 0) {
                    jVar.M0(58);
                }
                byte b10 = address[i10];
                byte[] bArr = ib.c.f16268a;
                jVar.O0(((b10 & 255) << 8) | (address[i10 + 1] & 255));
                i10 += 2;
            }
        }
        return jVar.t0();
    }

    public static String y(String str) {
        int length;
        List listN0 = kotlin.text.o.n0(str);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listN0) {
            if (!kotlin.text.o.h0((String) obj)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (true) {
            length = 0;
            if (!it.hasNext()) {
                break;
            }
            String str2 = (String) it.next();
            int length2 = str2.length();
            while (true) {
                if (length >= length2) {
                    length = -1;
                    break;
                }
                if (!r0.u(str2.charAt(length))) {
                    break;
                }
                length++;
            }
            if (length == -1) {
                length = str2.length();
            }
            arrayList2.add(Integer.valueOf(length));
        }
        Integer num = (Integer) x.C0(arrayList2);
        int iIntValue = num != null ? num.intValue() : 0;
        int length3 = str.length();
        listN0.size();
        int iW = t7.a.w(listN0);
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : listN0) {
            int i10 = length + 1;
            if (length < 0) {
                t7.a.Q();
                throw null;
            }
            String str3 = (String) obj2;
            String strV = ((length == 0 || length == iW) && kotlin.text.o.h0(str3)) ? null : kotlin.text.o.V(iIntValue, str3);
            if (strV != null) {
                arrayList3.add(strV);
            }
            length = i10;
        }
        StringBuilder sb2 = new StringBuilder(length3);
        x.t0(arrayList3, sb2, "\n", null, null, null, 124);
        return sb2.toString();
    }

    public static String z(String str) throws IOException {
        if (kotlin.text.o.h0("|")) {
            throw new IllegalArgumentException("marginPrefix must be non-blank string.");
        }
        List listN0 = kotlin.text.o.n0(str);
        int length = str.length();
        listN0.size();
        int iW = t7.a.w(listN0);
        ArrayList arrayList = new ArrayList();
        int i10 = 0;
        for (Object obj : listN0) {
            int i11 = i10 + 1;
            String strSubstring = null;
            if (i10 < 0) {
                t7.a.Q();
                throw null;
            }
            String str2 = (String) obj;
            if ((i10 != 0 && i10 != iW) || !kotlin.text.o.h0(str2)) {
                int length2 = str2.length();
                int i12 = 0;
                while (true) {
                    if (i12 >= length2) {
                        i12 = -1;
                        break;
                    }
                    if (!r0.u(str2.charAt(i12))) {
                        break;
                    }
                    i12++;
                }
                if (i12 != -1 && str2.startsWith("|", i12)) {
                    strSubstring = str2.substring("|".length() + i12);
                }
                if (strSubstring == null) {
                    strSubstring = str2;
                }
            }
            if (strSubstring != null) {
                arrayList.add(strSubstring);
            }
            i10 = i11;
        }
        StringBuilder sb2 = new StringBuilder(length);
        x.t0(arrayList, sb2, "\n", null, null, null, 124);
        return sb2.toString();
    }

    public abstract String c();
}
