package ac;

import a9.t0;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.work.impl.constraints.j;
import androidx.work.impl.t;
import c9.f;
import com.google.common.util.concurrent.p0;
import com.google.common.util.concurrent.r0;
import com.google.firebase.components.DependencyCycleException;
import com.google.firebase.components.k;
import com.google.firebase.components.l;
import com.google.firebase.components.m;
import com.google.firebase.components.s;
import d9.e;
import d9.h;
import d9.q;
import e9.d;
import h.f0;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.p;
import kotlin.text.o;
import org.fusesource.jansi.internal.Kernel32;
import p9.n;
import v9.w;
import xb.g;
import y8.a0;
import z6.i;

/* JADX INFO: loaded from: classes5.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f823a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Boolean f824b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static io.sentry.android.replay.viewhierarchy.c f825c;

    public static void A(ArrayList arrayList) {
        HashMap map = new HashMap(arrayList.size());
        Iterator it = arrayList.iterator();
        while (true) {
            int i10 = 0;
            if (!it.hasNext()) {
                Iterator it2 = map.values().iterator();
                while (it2.hasNext()) {
                    for (k kVar : (Set) it2.next()) {
                        for (m mVar : kVar.f14276a.f14257c) {
                            if (mVar.f14283c == 0) {
                                Set<k> set = (Set) map.get(new l(mVar.f14281a, mVar.f14282b == 2));
                                if (set != null) {
                                    for (k kVar2 : set) {
                                        kVar.f14277b.add(kVar2);
                                        kVar2.f14278c.add(kVar);
                                    }
                                }
                            }
                        }
                    }
                }
                HashSet<k> hashSet = new HashSet();
                Iterator it3 = map.values().iterator();
                while (it3.hasNext()) {
                    hashSet.addAll((Set) it3.next());
                }
                HashSet hashSet2 = new HashSet();
                for (k kVar3 : hashSet) {
                    if (kVar3.f14278c.isEmpty()) {
                        hashSet2.add(kVar3);
                    }
                }
                while (!hashSet2.isEmpty()) {
                    k kVar4 = (k) hashSet2.iterator().next();
                    hashSet2.remove(kVar4);
                    i10++;
                    for (k kVar5 : kVar4.f14277b) {
                        kVar5.f14278c.remove(kVar4);
                        if (kVar5.f14278c.isEmpty()) {
                            hashSet2.add(kVar5);
                        }
                    }
                }
                if (i10 == arrayList.size()) {
                    return;
                }
                ArrayList arrayList2 = new ArrayList();
                for (k kVar6 : hashSet) {
                    if (!kVar6.f14278c.isEmpty() && !kVar6.f14277b.isEmpty()) {
                        arrayList2.add(kVar6.f14276a);
                    }
                }
                throw new DependencyCycleException("Dependency cycle detected: " + Arrays.toString(arrayList2.toArray()));
            }
            com.google.firebase.components.b bVar = (com.google.firebase.components.b) it.next();
            k kVar7 = new k(bVar);
            for (s sVar : bVar.f14256b) {
                boolean z = bVar.f14259e == 0;
                l lVar = new l(sVar, !z);
                if (!map.containsKey(lVar)) {
                    map.put(lVar, new HashSet());
                }
                Set set2 = (Set) map.get(lVar);
                if (!set2.isEmpty() && z) {
                    throw new IllegalArgumentException("Multiple components provide " + sVar + ".");
                }
                set2.add(kVar7);
            }
        }
    }

    public static void B(String str, String str2, Exception exc) {
        String strI = I(str);
        if (Log.isLoggable(strI, 6)) {
            Log.e(strI, str2, exc);
        }
    }

    public static boolean C(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    public static List D(LayoutNode layoutNode) {
        Boolean bool = f824b;
        Boolean bool2 = Boolean.FALSE;
        if (p.a(bool, bool2)) {
            return layoutNode.getChildren$ui();
        }
        if (p.a(bool, Boolean.TRUE)) {
            return (List) E().f17072a.invoke(layoutNode, null);
        }
        if (bool != null) {
            throw new NoWhenBranchMatchedException();
        }
        try {
            List children$ui = layoutNode.getChildren$ui();
            f824b = bool2;
            return children$ui;
        } catch (NoSuchMethodError unused) {
            f824b = Boolean.TRUE;
            return (List) E().f17072a.invoke(layoutNode, null);
        }
    }

    public static io.sentry.android.replay.viewhierarchy.c E() {
        Method declaredMethod;
        io.sentry.android.replay.viewhierarchy.c cVar = f825c;
        if (cVar != null) {
            return cVar;
        }
        Method method = null;
        try {
            declaredMethod = LayoutNode.class.getDeclaredMethod("getChildren$ui_release", null);
            declaredMethod.setAccessible(true);
        } catch (NoSuchMethodException unused) {
            declaredMethod = null;
        }
        try {
            Method declaredMethod2 = LayoutNode.class.getDeclaredMethod("getOuterCoordinator$ui_release", null);
            declaredMethod2.setAccessible(true);
            method = declaredMethod2;
        } catch (NoSuchMethodException unused2) {
        }
        io.sentry.android.replay.viewhierarchy.c cVar2 = new io.sentry.android.replay.viewhierarchy.c(declaredMethod, method);
        f825c = cVar2;
        return cVar2;
    }

    public static String F() {
        byte[] bArr = new byte[160];
        Kernel32.FormatMessageW(0, 0L, Kernel32.GetLastError(), 0, bArr, 160, null);
        try {
            return new String(bArr, "UTF-16LE").trim();
        } catch (UnsupportedEncodingException e5) {
            throw new IllegalStateException(e5);
        }
    }

    public static final a0 G(t0 t0Var, f fVar, j jVar, boolean z, boolean z5, boolean z10) {
        h hVar = (h) r0.l(t0Var, q.f14866d);
        if (hVar != null) {
            if (z) {
                kotlin.reflect.jvm.internal.impl.protobuf.l lVar = e9.h.f15080a;
                d dVarB = e9.h.b(t0Var, fVar, jVar, z10);
                if (dVarB != null) {
                    return p0.k(dVarB);
                }
            } else if (z5 && (hVar.f14819l & 2) == 2) {
                e eVar = hVar.f14821n;
                return new a0(fVar.getString(eVar.f14806m).concat(fVar.getString(eVar.f14807n)));
            }
        }
        return null;
    }

    public static String I(String str) {
        if (Build.VERSION.SDK_INT >= 26) {
            return "TRuntime.".concat(str);
        }
        String strConcat = "TRuntime.".concat(str);
        return strConcat.length() > 23 ? strConcat.substring(0, 23) : strConcat;
    }

    public static String J() {
        URL resource = b.class.getResource("/org/fusesource/jansi/jansi.properties");
        String property = "unknown";
        if (resource != null) {
            try {
                Properties properties = new Properties();
                properties.load(resource.openStream());
                property = properties.getProperty("version", "unknown");
                return property.trim().replaceAll("[^0-9.]", "");
            } catch (IOException e5) {
                System.err.println(e5);
            }
        }
        return property;
    }

    public static synchronized boolean K() {
        if (!f823a) {
            u();
        }
        try {
            S();
        } catch (Exception e5) {
            if (!Boolean.parseBoolean(System.getProperty("jansi.graceful", "true"))) {
                throw new RuntimeException("Unable to load jansi native library. You may want set the `jansi.graceful` system property to true to be able to use Jansi on your platform", e5);
            }
        }
        return f823a;
    }

    public static final int L(int i10) {
        if (i10 == 0) {
            return 1;
        }
        if (i10 == 1) {
            return 2;
        }
        throw new IllegalArgumentException(androidx.compose.foundation.c.o(i10, "Could not convert ", " to BackoffPolicy"));
    }

    public static final int M(int i10) {
        if (i10 == 0) {
            return 1;
        }
        if (i10 == 1) {
            return 2;
        }
        if (i10 == 2) {
            return 3;
        }
        if (i10 == 3) {
            return 4;
        }
        if (i10 == 4) {
            return 5;
        }
        if (Build.VERSION.SDK_INT < 30 || i10 != 5) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.o(i10, "Could not convert ", " to NetworkType"));
        }
        return 6;
    }

    public static final int N(int i10) {
        if (i10 == 0) {
            return 1;
        }
        if (i10 == 1) {
            return 2;
        }
        throw new IllegalArgumentException(androidx.compose.foundation.c.o(i10, "Could not convert ", " to OutOfQuotaPolicy"));
    }

    public static final int O(int i10) {
        if (i10 == 0) {
            return 1;
        }
        if (i10 == 1) {
            return 2;
        }
        if (i10 == 2) {
            return 3;
        }
        if (i10 == 3) {
            return 4;
        }
        if (i10 == 4) {
            return 5;
        }
        if (i10 == 5) {
            return 6;
        }
        throw new IllegalArgumentException(androidx.compose.foundation.c.o(i10, "Could not convert ", " to State"));
    }

    public static boolean P(LinearLayout linearLayout, View view) {
        while (view != null) {
            if (view == linearLayout) {
                return true;
            }
            Object parent = view.getParent();
            if (!(parent instanceof View)) {
                return false;
            }
            view = (View) parent;
        }
        return false;
    }

    public static boolean Q(LayoutNode layoutNode) {
        Boolean bool = f824b;
        Boolean bool2 = Boolean.FALSE;
        if (p.a(bool, bool2)) {
            return layoutNode.getOuterCoordinator$ui().isTransparent();
        }
        if (p.a(bool, Boolean.TRUE)) {
            return ((NodeCoordinator) E().f17073b.invoke(layoutNode, null)).isTransparent();
        }
        if (bool != null) {
            throw new NoWhenBranchMatchedException();
        }
        try {
            boolean zIsTransparent = layoutNode.getOuterCoordinator$ui().isTransparent();
            f824b = bool2;
            return zIsTransparent;
        } catch (NoSuchMethodError unused) {
            f824b = Boolean.TRUE;
            return ((NodeCoordinator) E().f17073b.invoke(layoutNode, null)).isTransparent();
        }
    }

    public static boolean R(Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            return androidx.core.os.s.a(context);
        }
        return true;
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01a8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void S() throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 590
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ac.b.S():void");
    }

    public static boolean T(File file) {
        if (file.exists()) {
            try {
                System.load(file.getAbsolutePath());
                return true;
            } catch (UnsatisfiedLinkError e5) {
                if (file.canExecute()) {
                    System.err.printf("Failed to load native library:%s. osinfo: %s%n", file.getName(), c.b());
                } else {
                    System.err.printf("Failed to load native library:%s. The native library file at %s is not executable, make sure that the directory is mounted on a partition without the noexec flag, or set the jansi.tmpdir system property to point to a proper location.  osinfo: %s%n", file.getName(), file, c.b());
                }
                System.err.println(e5);
            }
        }
        return false;
    }

    public static final String U(Reader reader) throws IOException {
        StringWriter stringWriter = new StringWriter();
        char[] cArr = new char[8192];
        int i10 = reader.read(cArr);
        while (i10 >= 0) {
            stringWriter.write(cArr, 0, i10);
            i10 = reader.read(cArr);
        }
        return stringWriter.toString();
    }

    public static void V(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException(str);
        }
    }

    public static final int W(int i10) {
        int iC = f0.c(i10);
        if (iC == 0) {
            return 0;
        }
        int i11 = 1;
        if (iC != 1) {
            i11 = 2;
            if (iC != 2) {
                i11 = 3;
                if (iC != 3) {
                    i11 = 4;
                    if (iC != 4) {
                        if (iC == 5) {
                            return 5;
                        }
                        throw new NoWhenBranchMatchedException();
                    }
                }
            }
        }
        return i11;
    }

    public static final c1.e X() {
        return new c1.e("focus_border_color");
    }

    public static void Y(g gVar, byte[] bArr) {
        long j10;
        int length = bArr.length;
        int i10 = 0;
        do {
            byte[] bArr2 = gVar.f22739o;
            int i11 = gVar.f22740p;
            int i12 = gVar.f22741q;
            if (bArr2 != null) {
                while (i11 < i12) {
                    int i13 = i10 % length;
                    bArr2[i11] = (byte) (bArr2[i11] ^ bArr[i13]);
                    i11++;
                    i10 = i13 + 1;
                }
            }
            j10 = gVar.f22738n;
            if (j10 == gVar.f22735i.f22759l) {
                throw new IllegalStateException("no more bytes");
            }
        } while (gVar.j(j10 == -1 ? 0L : j10 + ((long) (gVar.f22741q - gVar.f22740p))) != -1);
    }

    public static Object a(Parcel parcel, Parcelable.Creator creator) {
        if (parcel.readInt() != 0) {
            return creator.createFromParcel(parcel);
        }
        return null;
    }

    public static String b(int i10, int i11, String str) {
        if (i10 < 0) {
            return t.y("%s (%s) must not be negative", str, Integer.valueOf(i10));
        }
        if (i11 >= 0) {
            return t.y("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i10), Integer.valueOf(i11));
        }
        throw new IllegalArgumentException(a0.c.i(i11, "negative size: "));
    }

    public static i c(i iVar) {
        iVar.f23241i.c();
        return iVar.size() > 0 ? iVar : i.f23240l;
    }

    public static String d(byte[] bArr) {
        StringBuilder sb2 = new StringBuilder(bArr.length * 2);
        for (byte b10 : bArr) {
            sb2.append(String.format("%02x", Byte.valueOf(b10)));
        }
        return sb2.toString();
    }

    public static final LinkedHashSet e(byte[] bArr) throws IOException {
        ObjectInputStream objectInputStream;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (bArr.length == 0) {
            return linkedHashSet;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            try {
                objectInputStream = new ObjectInputStream(byteArrayInputStream);
            } catch (IOException e5) {
                e5.printStackTrace();
            }
            try {
                int i10 = objectInputStream.readInt();
                for (int i11 = 0; i11 < i10; i11++) {
                    linkedHashSet.add(new m2.e(Uri.parse(objectInputStream.readUTF()), objectInputStream.readBoolean()));
                }
                objectInputStream.close();
                byteArrayInputStream.close();
                return linkedHashSet;
            } finally {
            }
        } finally {
        }
    }

    public static void f(int i10, String str, boolean z) {
        if (!z) {
            throw new IllegalArgumentException(t.y(str, Integer.valueOf(i10)));
        }
    }

    public static void g(long j10, String str, boolean z) {
        if (!z) {
            throw new IllegalArgumentException(t.y(str, Long.valueOf(j10)));
        }
    }

    public static void h(String str, int i10, int i11, boolean z) {
        if (!z) {
            throw new IllegalArgumentException(t.y(str, Integer.valueOf(i10), Integer.valueOf(i11)));
        }
    }

    public static void i(String str, long j10, long j11, boolean z) {
        if (!z) {
            throw new IllegalArgumentException(t.y(str, Long.valueOf(j10), Long.valueOf(j11)));
        }
    }

    public static void j(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void k(boolean z, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    public static void l(boolean z, String str, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(t.y(str, obj));
        }
    }

    public static void m(boolean z, String str, Object obj, Comparable comparable) {
        if (!z) {
            throw new IllegalArgumentException(t.y(str, obj, comparable));
        }
    }

    public static void n(int i10, int i11) {
        String strY;
        if (i10 < 0 || i10 >= i11) {
            if (i10 < 0) {
                strY = t.y("%s (%s) must not be negative", "index", Integer.valueOf(i10));
            } else {
                if (i11 < 0) {
                    throw new IllegalArgumentException(a0.c.i(i11, "negative size: "));
                }
                strY = t.y("%s (%s) must be less than size (%s)", "index", Integer.valueOf(i10), Integer.valueOf(i11));
            }
            throw new IndexOutOfBoundsException(strY);
        }
    }

    public static void o(Object obj) {
        obj.getClass();
    }

    public static void p(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException(String.valueOf(obj2));
        }
    }

    public static void q(int i10, int i11) {
        if (i10 < 0 || i10 > i11) {
            throw new IndexOutOfBoundsException(b(i10, i11, "index"));
        }
    }

    public static void r(int i10, int i11, int i12) {
        if (i10 < 0 || i11 < i10 || i11 > i12) {
            throw new IndexOutOfBoundsException((i10 < 0 || i10 > i12) ? b(i10, i12, "start index") : (i11 < 0 || i11 > i12) ? b(i11, i12, "end index") : t.y("end index (%s) must not be less than start index (%s)", Integer.valueOf(i11), Integer.valueOf(i10)));
        }
    }

    public static void s(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    public static void t(boolean z, Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    public static void u() {
        File[] fileArrListFiles = new File(new File(System.getProperty("jansi.tmpdir", System.getProperty("java.io.tmpdir"))).getAbsolutePath()).listFiles(new a());
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                if (!new File(file.getAbsolutePath() + ".lck").exists()) {
                    try {
                        file.delete();
                    } catch (SecurityException e5) {
                        System.err.println("Failed to delete old native lib" + e5.getMessage());
                    }
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static long[] v(Serializable serializable) {
        if (!(serializable instanceof int[])) {
            if (serializable instanceof long[]) {
                return (long[]) serializable;
            }
            return null;
        }
        int[] iArr = (int[]) serializable;
        long[] jArr = new long[iArr.length];
        for (int i10 = 0; i10 < iArr.length; i10++) {
            jArr[i10] = iArr[i10];
        }
        return jArr;
    }

    public static n w(String str, Collection collection) {
        Collection collection2 = collection;
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(collection2, 10));
        Iterator it = collection2.iterator();
        while (it.hasNext()) {
            arrayList.add(((w) it.next()).l());
        }
        ea.i iVarV = r0.v(arrayList);
        int i10 = iVarV.f15090i;
        n aVar = i10 != 0 ? i10 != 1 ? new p9.a(str, (n[]) iVarV.toArray(new n[0])) : (n) iVarV.get(0) : p9.m.f21235b;
        return iVarV.f15090i <= 1 ? aVar : new p9.j(aVar);
    }

    public static void x(String str, Object obj, String str2) {
        String strI = I(str);
        if (Log.isLoggable(strI, 3)) {
            Log.d(strI, String.format(str2, obj));
        }
    }

    public static boolean y(String str, String str2) {
        if (str.equals(str2)) {
            return true;
        }
        if (str.length() != 0) {
            int i10 = 0;
            int i11 = 0;
            int i12 = 0;
            while (true) {
                if (i10 < str.length()) {
                    char cCharAt = str.charAt(i10);
                    int i13 = i12 + 1;
                    if (i12 == 0 && cCharAt != '(') {
                        break;
                    }
                    if (cCharAt != '(') {
                        if (cCharAt == ')' && i11 - 1 == 0 && i12 != str.length() - 1) {
                            break;
                        }
                    } else {
                        i11++;
                    }
                    i10++;
                    i12 = i13;
                } else if (i11 == 0) {
                    return p.a(o.L0(str.substring(1, str.length() - 1)).toString(), str2);
                }
            }
        }
        return false;
    }

    public static t3.b z(byte[] bArr, Parcelable.Creator creator) {
        com.google.android.gms.common.internal.t.i(creator);
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.unmarshall(bArr, 0, bArr.length);
        parcelObtain.setDataPosition(0);
        t3.b bVar = (t3.b) creator.createFromParcel(parcelObtain);
        parcelObtain.recycle();
        return bVar;
    }
}
