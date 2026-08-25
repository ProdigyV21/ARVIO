package t7;

import a8.u1;
import a8.x1;
import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.appcompat.widget.f0;
import ba.e;
import c9.f;
import cb.k1;
import com.google.common.util.concurrent.n0;
import com.google.common.util.concurrent.r0;
import d7.d;
import d7.j;
import d7.k;
import e7.b;
import ea.i;
import f7.c;
import g8.h;
import gb.a0;
import gb.i0;
import i7.n;
import io.sentry.j3;
import io.sentry.w5;
import io.sentry.y6;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.ProtocolException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.o;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.l0;
import kotlin.reflect.q;
import kotlin.reflect.r;
import kotlin.text.u;
import p9.m;
import r7.l;
import r7.p;
import r8.g;

/* JADX INFO: loaded from: classes5.dex */
public abstract class a {
    public static d A(d dVar) {
        d<Object> dVarIntercepted;
        c cVar = dVar instanceof c ? (c) dVar : null;
        return (cVar == null || (dVarIntercepted = cVar.intercepted()) == null) ? dVar : dVarIntercepted;
    }

    public static String B(e eVar, g gVar) {
        if (eVar.c(gVar)) {
            return null;
        }
        return eVar.a();
    }

    public static boolean C(char c10) {
        return c10 >= 'A' && c10 <= 'Z';
    }

    public static List D(Object obj) {
        return Collections.singletonList(obj);
    }

    public static List E(Object... objArr) {
        return objArr.length > 0 ? Arrays.asList(objArr) : z.f19728i;
    }

    public static List F(Object obj) {
        return obj != null ? Collections.singletonList(obj) : z.f19728i;
    }

    public static ArrayList G(Object... objArr) {
        return objArr.length == 0 ? new ArrayList() : new ArrayList(new o(objArr, true));
    }

    public static a0 H(String... strArr) {
        if (strArr.length % 2 != 0) {
            throw new IllegalArgumentException("Expected alternating header names and values");
        }
        String[] strArr2 = (String[]) strArr.clone();
        int length = strArr2.length;
        int i10 = 0;
        for (int i11 = 0; i11 < length; i11++) {
            String str = strArr2[i11];
            if (str == null) {
                throw new IllegalArgumentException("Headers cannot be null");
            }
            strArr2[i11] = kotlin.text.o.L0(str).toString();
        }
        int iA = n.a(0, strArr2.length - 1, 2);
        if (iA >= 0) {
            while (true) {
                String str2 = strArr2[i10];
                String str3 = strArr2[i10 + 1];
                g(str2);
                h(str3, str2);
                if (i10 == iA) {
                    break;
                }
                i10 += 2;
            }
        }
        return new a0(strArr2);
    }

    public static final List I(List list) {
        int size = list.size();
        return size != 0 ? size != 1 ? list : Collections.singletonList(list.get(0)) : z.f19728i;
    }

    public static f0 J(String str) throws ProtocolException {
        int i10;
        String strSubstring;
        boolean zP = u.P(str, "HTTP/1.", false);
        i0 i0Var = i0.HTTP_1_0;
        if (zP) {
            i10 = 9;
            if (str.length() < 9 || str.charAt(8) != ' ') {
                throw new ProtocolException("Unexpected status line: ".concat(str));
            }
            int iCharAt = str.charAt(7) - '0';
            if (iCharAt != 0) {
                if (iCharAt != 1) {
                    throw new ProtocolException("Unexpected status line: ".concat(str));
                }
                i0Var = i0.HTTP_1_1;
            }
        } else {
            if (!u.P(str, "ICY ", false)) {
                throw new ProtocolException("Unexpected status line: ".concat(str));
            }
            i10 = 4;
        }
        int i11 = i10 + 3;
        if (str.length() < i11) {
            throw new ProtocolException("Unexpected status line: ".concat(str));
        }
        try {
            int i12 = Integer.parseInt(str.substring(i10, i11));
            if (str.length() <= i11) {
                strSubstring = "";
            } else {
                if (str.charAt(i11) != ' ') {
                    throw new ProtocolException("Unexpected status line: ".concat(str));
                }
                strSubstring = str.substring(i10 + 4);
            }
            return new f0(i0Var, i12, strSubstring);
        } catch (NumberFormatException unused) {
            throw new ProtocolException("Unexpected status line: ".concat(str));
        }
    }

    public static final void K(int i10, int i11) {
        if (i11 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.o(i11, "fromIndex (0) is greater than toIndex (", ")."));
        }
        if (i11 > i10) {
            throw new IndexOutOfBoundsException(androidx.compose.foundation.c.s("toIndex (", i11, ") is greater than size (", i10, ")."));
        }
    }

    public static int L(double d4) {
        if (Double.isNaN(d4)) {
            throw new IllegalArgumentException("Cannot round NaN value.");
        }
        if (d4 > 2.147483647E9d) {
            return Integer.MAX_VALUE;
        }
        if (d4 < -2.147483648E9d) {
            return Integer.MIN_VALUE;
        }
        return (int) Math.round(d4);
    }

    public static int M(float f10) {
        if (Float.isNaN(f10)) {
            throw new IllegalArgumentException("Cannot round NaN value.");
        }
        return Math.round(f10);
    }

    public static long N(double d4) {
        if (Double.isNaN(d4)) {
            throw new IllegalArgumentException("Cannot round NaN value.");
        }
        return Math.round(d4);
    }

    public static boolean O(y6 y6Var, y6 y6Var2, boolean z) {
        boolean z5 = io.sentry.util.o.f17967a;
        if (!z5 && (y6Var2.getVersionDetector() instanceof j3)) {
            y6Var2.setVersionDetector(new io.sentry.u(y6Var2, 1));
        }
        if (!y6Var2.getVersionDetector().a()) {
            return !z || y6Var == null || y6Var2.isForceInit() || y6Var.getInitPriority().ordinal() <= y6Var2.getInitPriority().ordinal();
        }
        y6Var2.getLogger().q(w5.ERROR, "Not initializing Sentry because mixed SDK versions have been detected.", new Object[0]);
        throw new IllegalStateException(a0.c.l("Sentry SDK has detected a mix of versions. This is not supported and likely leads to crashes. Please always use the same version of all SDK modules (dependencies). See ", z5 ? "https://docs.sentry.io/platforms/android/troubleshooting/mixed-versions" : "https://docs.sentry.io/platforms/java/troubleshooting/mixed-versions", " for more details."));
    }

    public static void P() {
        throw new ArithmeticException("Count overflow has happened.");
    }

    public static void Q() {
        throw new ArithmeticException("Index overflow has happened.");
    }

    public static int R(Context context, int i10) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(R.style.Animation.Activity, new int[]{i10});
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, -1);
        typedArrayObtainStyledAttributes.recycle();
        return resourceId;
    }

    public static String S(String str) {
        int length = str.length();
        int i10 = 0;
        while (i10 < length) {
            if (C(str.charAt(i10))) {
                char[] charArray = str.toCharArray();
                while (i10 < length) {
                    char c10 = charArray[i10];
                    if (C(c10)) {
                        charArray[i10] = (char) (c10 ^ ' ');
                    }
                    i10++;
                }
                return String.valueOf(charArray);
            }
            i10++;
        }
        return str;
    }

    public static String T(String str) {
        int length = str.length();
        int i10 = 0;
        while (i10 < length) {
            char cCharAt = str.charAt(i10);
            if (cCharAt >= 'a' && cCharAt <= 'z') {
                char[] charArray = str.toCharArray();
                while (i10 < length) {
                    char c10 = charArray[i10];
                    if (c10 >= 'a' && c10 <= 'z') {
                        charArray[i10] = (char) (c10 ^ ' ');
                    }
                    i10++;
                }
                return String.valueOf(charArray);
            }
            i10++;
        }
        return str;
    }

    public static ArrayList a(Object... objArr) {
        return objArr.length == 0 ? new ArrayList() : new ArrayList(new o(objArr, true));
    }

    public static int b(List list, int i10, l lVar) {
        K(list.size(), i10);
        int i11 = i10 - 1;
        int i12 = 0;
        while (i12 <= i11) {
            int i13 = (i12 + i11) >>> 1;
            int iIntValue = ((Number) lVar.invoke(list.get(i13))).intValue();
            if (iIntValue < 0) {
                i12 = i13 + 1;
            } else {
                if (iIntValue <= 0) {
                    return i13;
                }
                i11 = i13 - 1;
            }
        }
        return -(i12 + 1);
    }

    public static int c(List list, Comparable comparable) {
        int size = list.size();
        K(list.size(), size);
        int i10 = size - 1;
        int i11 = 0;
        while (i11 <= i10) {
            int i12 = (i11 + i10) >>> 1;
            int iE = r0.e((Comparable) list.get(i12), comparable);
            if (iE < 0) {
                i11 = i12 + 1;
            } else {
                if (iE <= 0) {
                    return i12;
                }
                i10 = i12 - 1;
            }
        }
        return -(i11 + 1);
    }

    public static z6.c e(List list) {
        z6.c cVar = (z6.c) list;
        cVar.d();
        cVar.f23219m = true;
        return cVar.f23218l > 0 ? cVar : z6.c.f23216n;
    }

    public static String f(String str) {
        try {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(new BigInteger("10".concat(str), 16).toByteArray());
            byteBufferWrap.get();
            return String.format("%08x-%04x-%04x-%04x-%04x%08x", Integer.valueOf(byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN).getInt()), Short.valueOf(byteBufferWrap.getShort()), Short.valueOf(byteBufferWrap.getShort()), Short.valueOf(byteBufferWrap.order(ByteOrder.BIG_ENDIAN).getShort()), Short.valueOf(byteBufferWrap.getShort()), Integer.valueOf(byteBufferWrap.getInt()));
        } catch (NumberFormatException | BufferUnderflowException unused) {
            return null;
        }
    }

    public static void g(String str) {
        if (str.length() <= 0) {
            throw new IllegalArgumentException("name is empty");
        }
        int length = str.length();
        for (int i10 = 0; i10 < length; i10++) {
            char cCharAt = str.charAt(i10);
            if ('!' > cCharAt || cCharAt >= 127) {
                throw new IllegalArgumentException(ib.c.i("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(cCharAt), Integer.valueOf(i10), str).toString());
            }
        }
    }

    public static void h(String str, String str2) {
        int length = str.length();
        for (int i10 = 0; i10 < length; i10++) {
            char cCharAt = str.charAt(i10);
            if (cCharAt != '\t' && (' ' > cCharAt || cCharAt >= 127)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(ib.c.i("Unexpected char %#04x at %d in %s value", Integer.valueOf(cCharAt), Integer.valueOf(i10), str2));
                sb2.append(ib.c.q(str2) ? "" : ": ".concat(str));
                throw new IllegalArgumentException(sb2.toString().toString());
            }
        }
    }

    public static Object[] j(Object[] objArr, Object[]... objArr2) {
        int length = objArr.length;
        for (Object[] objArr3 : objArr2) {
            length += objArr3.length;
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr, length);
        int length2 = objArr.length;
        for (Object[] objArr4 : objArr2) {
            System.arraycopy(objArr4, 0, objArrCopyOf, length2, objArr4.length);
            length2 += objArr4.length;
        }
        return objArrCopyOf;
    }

    public static final long k(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[8192];
        int i10 = inputStream.read(bArr);
        long j10 = 0;
        while (i10 >= 0) {
            outputStream.write(bArr, 0, i10);
            j10 += (long) i10;
            i10 = inputStream.read(bArr);
        }
        return j10;
    }

    public static p9.n l(String str, List list) {
        m mVar;
        i iVar = new i();
        Iterator it = list.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            mVar = m.f21235b;
            if (!zHasNext) {
                break;
            }
            p9.n nVar = (p9.n) it.next();
            if (nVar != mVar) {
                if (nVar instanceof p9.a) {
                    x.c0(iVar, ((p9.a) nVar).f21197c);
                } else {
                    iVar.add(nVar);
                }
            }
        }
        int i10 = iVar.f15090i;
        return i10 != 0 ? i10 != 1 ? new p9.a(str, (p9.n[]) iVar.toArray(new p9.n[0])) : (p9.n) iVar.get(0) : mVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static d m(d dVar, d dVar2, p pVar) {
        if (pVar instanceof f7.a) {
            return ((f7.a) pVar).create(dVar, dVar2);
        }
        j context = dVar2.getContext();
        return context == k.f14688i ? new b(dVar2, dVar, pVar) : new e7.c(dVar2, context, pVar, dVar);
    }

    public static z6.c n() {
        return new z6.c(10);
    }

    public static boolean p(String str, String str2) {
        char c10;
        int length = str.length();
        if (str == str2) {
            return true;
        }
        if (length == str2.length()) {
            for (int i10 = 0; i10 < length; i10++) {
                if (str.charAt(i10) == str2.charAt(i10) || ((c10 = (char) ((r3 | ' ') - 97)) < 26 && c10 == ((char) ((r4 | ' ') - 97)))) {
                }
            }
            return true;
        }
        return false;
    }

    public static final kotlin.reflect.d q(ab.g gVar) {
        if (gVar instanceof ab.b) {
            return ((ab.b) gVar).f785b;
        }
        if (gVar instanceof k1) {
            return q(((k1) gVar).f7526a);
        }
        return null;
    }

    public static final f9.b r(f fVar, int i10) {
        return f9.b.e(fVar.o(i10), fVar.k(i10));
    }

    public static final ArrayList s(kotlin.reflect.d dVar) {
        Collection collectionN = dVar.n();
        ArrayList arrayList = new ArrayList();
        for (Object obj : collectionN) {
            if (obj instanceof kotlin.reflect.g) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static x7.i t(Collection collection) {
        return new x7.i(0, collection.size() - 1, 1);
    }

    public static final kotlin.reflect.d u(kotlin.reflect.e eVar) {
        Object obj;
        if (eVar instanceof kotlin.reflect.d) {
            return (kotlin.reflect.d) eVar;
        }
        if (!(eVar instanceof r)) {
            throw new x1("Cannot calculate JVM erasure for type: " + eVar);
        }
        List upperBounds = ((r) eVar).getUpperBounds();
        Iterator it = upperBounds.iterator();
        while (true) {
            obj = null;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            h hVarE = ((u1) ((q) next)).f282i.I0().e();
            g8.f fVar = hVarE instanceof g8.f ? (g8.f) hVarE : null;
            if (fVar != null && fVar.p0() != 2 && fVar.p0() != 5) {
                obj = next;
                break;
            }
        }
        q qVar = (q) obj;
        if (qVar == null) {
            qVar = (q) x.o0(upperBounds);
        }
        return qVar != null ? v(qVar) : l0.f19747a.b(Object.class);
    }

    public static final kotlin.reflect.d v(q qVar) {
        kotlin.reflect.e eVarB = qVar.b();
        if (eVarB != null) {
            return u(eVarB);
        }
        throw new x1("Cannot calculate JVM erasure for type: " + qVar);
    }

    public static int w(List list) {
        return list.size() - 1;
    }

    public static final f9.f x(f fVar, int i10) {
        return f9.f.i(fVar.getString(i10));
    }

    public static String y(y6 y6Var) {
        String profilingTracesDirPath = y6Var.getProfilingTracesDirPath();
        if (profilingTracesDirPath != null) {
            return profilingTracesDirPath;
        }
        File file = new File(System.getProperty("java.io.tmpdir"), "sentry_profiling_traces");
        if (file.mkdirs() || file.exists()) {
            String absolutePath = file.getAbsolutePath();
            y6Var.setProfilingTracesDirPath(absolutePath);
            return absolutePath;
        }
        throw new IllegalArgumentException("Creating a fallback directory for profiling failed in " + file.getAbsolutePath());
    }

    public static Object z(Bundle bundle, String str) {
        if (Build.VERSION.SDK_INT >= 34) {
            return androidx.core.os.e.a(bundle, str, androidx.activity.result.a.class);
        }
        Parcelable parcelable = bundle.getParcelable(str);
        if (androidx.activity.result.a.class.isInstance(parcelable)) {
            return parcelable;
        }
        return null;
    }

    public abstract List i(String str, List list);

    public abstract int o(n0 n0Var);
}
