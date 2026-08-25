package xc;

import a8.f0;
import a8.u1;
import a8.x1;
import ab.e;
import ab.g;
import ab.i;
import ab.k;
import ab.n;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.a2;
import androidx.leanback.transition.FadeAndShortSlide;
import cb.h1;
import cb.i1;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException;
import com.google.common.base.m;
import com.google.common.collect.e1;
import com.google.common.collect.f1;
import com.google.common.collect.o3;
import com.google.common.util.concurrent.d1;
import com.google.common.util.concurrent.r0;
import com.google.common.util.concurrent.s0;
import com.google.common.util.concurrent.v0;
import com.google.common.util.concurrent.w0;
import com.google.common.util.concurrent.y0;
import com.google.common.util.concurrent.z0;
import g8.b1;
import g8.f;
import g8.h0;
import h8.h;
import io.sentry.u5;
import java.io.Closeable;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.c0;
import kotlin.collections.d0;
import kotlin.collections.r;
import kotlin.collections.s;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.i0;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.q0;
import kotlin.reflect.t;
import kotlin.text.o;
import kotlin.text.u;
import kotlinx.serialization.SerializationException;
import r7.l;
import s7.g;
import v9.b0;
import v9.g0;
import v9.k0;
import v9.o0;
import v9.q;
import v9.t0;
import v9.w;

/* JADX INFO: loaded from: classes5.dex */
public abstract class d implements bb.d, bb.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static c f22819a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f22820b = false;

    public static Integer A0(int i10, Parcel parcel) {
        int iC0 = C0(i10, parcel);
        if (iC0 == 0) {
            return null;
        }
        P0(parcel, iC0, 4);
        return Integer.valueOf(parcel.readInt());
    }

    public static long B0(int i10, Parcel parcel) {
        O0(parcel, i10, 8);
        return parcel.readLong();
    }

    public static int C0(int i10, Parcel parcel) {
        return (i10 & (-65536)) != -65536 ? (char) (i10 >> 16) : parcel.readInt();
    }

    public static final void D0(o8.d dVar, o8.b bVar, f fVar, f9.f fVar2) {
        if (dVar == o8.c.f20792a) {
            return;
        }
        bVar.getClass();
    }

    public static final void E0(o8.d dVar, o8.b bVar, h0 h0Var, f9.f fVar) {
        h0Var.c().b();
        fVar.d();
        if (dVar == o8.c.f20792a) {
            return;
        }
        bVar.getClass();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, java.util.Map] */
    public static final h1 F(String str) {
        e eVar = e.f797j;
        if (o.h0(str)) {
            throw new IllegalArgumentException("Blank serial names are prohibited");
        }
        Iterator it = i1.f7517a.keySet().iterator();
        while (it.hasNext()) {
            String strA = i1.a(((kotlin.reflect.d) it.next()).r());
            if (str.equalsIgnoreCase("kotlin." + strA) || str.equalsIgnoreCase(strA)) {
                StringBuilder sbT = a0.c.t("\n                The name of serial descriptor should uniquely identify associated serializer.\n                For serial name ", str, " there already exist ");
                sbT.append(i1.a(strA));
                sbT.append("Serializer.\n                Please refer to SerialDescriptor documentation for additional information.\n            ");
                throw new IllegalArgumentException(f4.f.y(sbT.toString()));
            }
        }
        return new h1(str, eVar);
    }

    public static final w F0(w wVar, h hVar) {
        return (wVar.getAnnotations().isEmpty() && hVar.isEmpty()) ? wVar : wVar.L0().O0(v9.c.n(wVar.H0(), hVar));
    }

    public static void G(d1 d1Var, v0 v0Var, Executor executor) {
        v0Var.getClass();
        d1Var.addListener(new w0(d1Var, v0Var, 0), executor);
    }

    public static final v9.d1 G0(w wVar) {
        v9.d1 d1VarM;
        v9.d1 d1VarL0 = wVar.L0();
        if (d1VarL0 instanceof q) {
            q qVar = (q) d1VarL0;
            b0 b0VarM = qVar.f22241l;
            if (!b0VarM.I0().getParameters().isEmpty() && b0VarM.I0().e() != null) {
                List parameters = b0VarM.I0().getParameters();
                ArrayList arrayList = new ArrayList(s.U(parameters, 10));
                Iterator it = parameters.iterator();
                while (it.hasNext()) {
                    arrayList.add(new g0((b1) it.next()));
                }
                b0VarM = v9.c.m(b0VarM, arrayList, null, 2);
            }
            b0 b0VarM2 = qVar.f22242m;
            if (!b0VarM2.I0().getParameters().isEmpty() && b0VarM2.I0().e() != null) {
                List parameters2 = b0VarM2.I0().getParameters();
                ArrayList arrayList2 = new ArrayList(s.U(parameters2, 10));
                Iterator it2 = parameters2.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(new g0((b1) it2.next()));
                }
                b0VarM2 = v9.c.m(b0VarM2, arrayList2, null, 2);
            }
            d1VarM = v9.e.j(b0VarM, b0VarM2);
        } else {
            if (!(d1VarL0 instanceof b0)) {
                throw new NoWhenBranchMatchedException();
            }
            b0 b0Var = (b0) d1VarL0;
            boolean zIsEmpty = b0Var.I0().getParameters().isEmpty();
            d1VarM = b0Var;
            if (!zIsEmpty) {
                g8.h hVarE = b0Var.I0().e();
                d1VarM = b0Var;
                if (hVarE != null) {
                    List parameters3 = b0Var.I0().getParameters();
                    ArrayList arrayList3 = new ArrayList(s.U(parameters3, 10));
                    Iterator it3 = parameters3.iterator();
                    while (it3.hasNext()) {
                        arrayList3.add(new g0((b1) it3.next()));
                    }
                    d1VarM = v9.c.m(b0Var, arrayList3, null, 2);
                }
            }
        }
        return v9.c.x(d1VarM, v9.c.d(d1VarL0));
    }

    public static void H(String str) {
        u5.d().a(str);
    }

    public static final void H0(String str) {
        a2.v("SLF4J: ", str, System.err);
    }

    public static final i I(String str, g[] gVarArr, l lVar) {
        if (o.h0(str)) {
            throw new IllegalArgumentException("Blank serial names are prohibited");
        }
        ab.a aVar = new ab.a(str);
        lVar.invoke(aVar);
        return new i(str, n.f818b, aVar.f779c.size(), r.o0(gVarArr), aVar);
    }

    public static final void I0(String str, Throwable th) {
        System.err.println(str);
        System.err.println("Reported exception:");
        th.printStackTrace();
    }

    public static final i J(String str, a.a aVar, g[] gVarArr, l lVar) {
        if (o.h0(str)) {
            throw new IllegalArgumentException("Blank serial names are prohibited");
        }
        if (aVar.equals(n.f818b)) {
            throw new IllegalArgumentException("For StructureKind.CLASS please use 'buildClassSerialDescriptor' instead");
        }
        ab.a aVar2 = new ab.a(str);
        lVar.invoke(aVar2);
        return new i(str, aVar, aVar2.f779c.size(), r.o0(gVarArr), aVar2);
    }

    public static void J0(int i10, Parcel parcel) {
        parcel.setDataPosition(parcel.dataPosition() + C0(i10, parcel));
    }

    public static i K(String str, a.a aVar, g[] gVarArr) {
        if (o.h0(str)) {
            throw new IllegalArgumentException("Blank serial names are prohibited");
        }
        if (aVar.equals(n.f818b)) {
            throw new IllegalArgumentException("For StructureKind.CLASS please use 'buildClassSerialDescriptor' instead");
        }
        ab.a aVar2 = new ab.a(str);
        return new i(str, aVar, aVar2.f779c.size(), r.o0(gVarArr), aVar2);
    }

    public static com.google.common.util.concurrent.r K0(d1 d1Var, m mVar) {
        com.google.common.util.concurrent.r rVar = new com.google.common.util.concurrent.r();
        d1Var.getClass();
        rVar.f14228i = d1Var;
        rVar.f14229l = mVar;
        d1Var.addListener(rVar, s0.f14233i);
        return rVar;
    }

    public static final void L(Closeable closeable, Throwable th) {
        if (closeable != null) {
            if (th == null) {
                closeable.close();
                return;
            }
            try {
                closeable.close();
            } catch (Throwable th2) {
                r0.a(th, th2);
            }
        }
    }

    public static int L0(Parcel parcel) {
        int i10 = parcel.readInt();
        int iC0 = C0(i10, parcel);
        char c10 = (char) i10;
        int iDataPosition = parcel.dataPosition();
        if (c10 != 20293) {
            throw new SafeParcelReader$ParseException("Expected object header. Got 0x".concat(String.valueOf(Integer.toHexString(i10))), parcel);
        }
        int i11 = iC0 + iDataPosition;
        if (i11 >= iDataPosition && i11 <= parcel.dataSize()) {
            return i11;
        }
        StringBuilder sb2 = new StringBuilder(String.valueOf(iDataPosition).length() + 32 + String.valueOf(i11).length());
        sb2.append("Size read is invalid start=");
        sb2.append(iDataPosition);
        sb2.append(" end=");
        sb2.append(i11);
        throw new SafeParcelReader$ParseException(sb2.toString(), parcel);
    }

    public static final boolean M(w wVar, o0 o0Var, Set set) {
        v9.r0 r0Var;
        b1 b1Var;
        if (p.a(wVar.I0(), o0Var)) {
            return true;
        }
        g8.h hVarE = wVar.I0().e();
        g8.i iVar = hVarE instanceof g8.i ? (g8.i) hVarE : null;
        List listN = iVar != null ? iVar.n() : null;
        Iterable iterableH1 = x.h1(wVar.G0());
        if (!(iterableH1 instanceof Collection) || !((Collection) iterableH1).isEmpty()) {
            Iterator it = iterableH1.iterator();
            do {
                ga.d dVar = (ga.d) it;
                if (dVar.f15570m.hasNext()) {
                    d0 d0Var = (d0) dVar.next();
                    int i10 = d0Var.f19688a;
                    r0Var = (v9.r0) d0Var.f19689b;
                    b1Var = listN != null ? (b1) x.p0(i10, listN) : null;
                }
            } while (!(((b1Var == null || set == null || !set.contains(b1Var)) && !r0Var.b()) ? M(r0Var.getType(), o0Var, set) : false));
            return true;
        }
        return false;
    }

    public static a8.e M0(Iterable iterable) {
        Object objF;
        f1 f1Var = com.google.common.collect.h1.f14020l;
        iterable.getClass();
        if (iterable instanceof Collection) {
            objF = com.google.common.collect.h1.n((Collection) iterable);
        } else {
            Iterator it = iterable.iterator();
            if (it.hasNext()) {
                Object next = it.next();
                if (it.hasNext()) {
                    e1 e1Var = new e1(4);
                    e1Var.c(next);
                    while (it.hasNext()) {
                        e1Var.c(it.next());
                    }
                    objF = e1Var.f();
                } else {
                    objF = com.google.common.collect.h1.s(next);
                }
            } else {
                objF = o3.f14078o;
            }
        }
        return new a8.e(objF, 11);
    }

    public static e8.e N(e8.c cVar, boolean z) {
        List list = cVar.f15054u;
        e8.e eVar = new e8.e(cVar, null, 1, z);
        g8.s0 s0VarF0 = cVar.F0();
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((b1) obj).y() != 2) {
                break;
            }
            arrayList.add(obj);
        }
        k kVarH1 = x.h1(arrayList);
        ArrayList arrayList2 = new ArrayList(s.U(kVarH1, 10));
        Iterator it = kVarH1.iterator();
        while (true) {
            ga.d dVar = (ga.d) it;
            if (!dVar.f15570m.hasNext()) {
                b0 b0VarM = ((b1) x.w0(list)).m();
                g8.p pVar = g8.q.f15528e;
                z zVar = z.f19728i;
                eVar.L0(null, s0VarF0, zVar, zVar, arrayList2, b0VarM, 4, pVar);
                e8.e eVar2 = eVar;
                eVar2.H = true;
                return eVar2;
            }
            d0 d0Var = (d0) dVar.next();
            int i10 = d0Var.f19688a;
            b1 b1Var = (b1) d0Var.f19689b;
            String strD = b1Var.getName().d();
            e8.e eVar3 = eVar;
            arrayList2.add(new j8.v0(eVar3, null, i10, h8.g.f15863a, f9.f.j(strD.equals("T") ? "instance" : strD.equals("E") ? "receiver" : strD.toLowerCase(Locale.ROOT)), b1Var.m(), false, false, false, null, g8.v0.f15554b));
            eVar = eVar3;
        }
    }

    public static final Bundle N0(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("com.google.android.libraries.identity.googleid.siwg.BUNDLE_KEY_SERVER_CLIENT_ID", str);
        bundle.putString("com.google.android.libraries.identity.googleid.siwg.BUNDLE_KEY_NONCE", str2);
        bundle.putString("com.google.android.libraries.identity.googleid.siwg.BUNDLE_KEY_HOSTED_DOMAIN_FILTER", null);
        bundle.putBoolean("com.google.android.libraries.identity.googleid.siwg.BUNDLE_KEY_AUTO_SELECT_ENABLED", true);
        bundle.putString("com.google.android.libraries.identity.googleid.BUNDLE_KEY_GOOGLE_ID_TOKEN_SUBTYPE", "com.google.android.libraries.identity.googleid.TYPE_GOOGLE_ID_TOKEN_SIWG_CREDENTIAL");
        return bundle;
    }

    public static BigDecimal O(int i10, Parcel parcel) {
        int iC0 = C0(i10, parcel);
        int iDataPosition = parcel.dataPosition();
        if (iC0 == 0) {
            return null;
        }
        byte[] bArrCreateByteArray = parcel.createByteArray();
        int i11 = parcel.readInt();
        parcel.setDataPosition(iDataPosition + iC0);
        return new BigDecimal(new BigInteger(bArrCreateByteArray), i11);
    }

    public static void O0(Parcel parcel, int i10, int i11) {
        int iC0 = C0(i10, parcel);
        if (iC0 == i11) {
            return;
        }
        String hexString = Integer.toHexString(iC0);
        int length = String.valueOf(i11).length();
        StringBuilder sb2 = new StringBuilder(String.valueOf(hexString).length() + length + 19 + String.valueOf(iC0).length() + 4 + 1);
        sb2.append("Expected size ");
        sb2.append(i11);
        sb2.append(" got ");
        sb2.append(iC0);
        throw new SafeParcelReader$ParseException(androidx.compose.material3.d.q(sb2, " (0x", hexString, ")"), parcel);
    }

    public static Bundle P(int i10, Parcel parcel) {
        int iC0 = C0(i10, parcel);
        int iDataPosition = parcel.dataPosition();
        if (iC0 == 0) {
            return null;
        }
        Bundle bundle = parcel.readBundle();
        parcel.setDataPosition(iDataPosition + iC0);
        return bundle;
    }

    public static void P0(Parcel parcel, int i10, int i11) {
        if (i10 == i11) {
            return;
        }
        String hexString = Integer.toHexString(i10);
        int length = String.valueOf(i11).length();
        StringBuilder sb2 = new StringBuilder(String.valueOf(hexString).length() + length + 19 + String.valueOf(i10).length() + 4 + 1);
        sb2.append("Expected size ");
        sb2.append(i11);
        sb2.append(" got ");
        sb2.append(i10);
        throw new SafeParcelReader$ParseException(androidx.compose.material3.d.q(sb2, " (0x", hexString, ")"), parcel);
    }

    public static byte[] Q(int i10, Parcel parcel) {
        int iC0 = C0(i10, parcel);
        int iDataPosition = parcel.dataPosition();
        if (iC0 == 0) {
            return null;
        }
        byte[] bArrCreateByteArray = parcel.createByteArray();
        parcel.setDataPosition(iDataPosition + iC0);
        return bArrCreateByteArray;
    }

    public static int[] R(int i10, Parcel parcel) {
        int iC0 = C0(i10, parcel);
        int iDataPosition = parcel.dataPosition();
        if (iC0 == 0) {
            return null;
        }
        int[] iArrCreateIntArray = parcel.createIntArray();
        parcel.setDataPosition(iDataPosition + iC0);
        return iArrCreateIntArray;
    }

    public static long[] S(int i10, Parcel parcel) {
        int iC0 = C0(i10, parcel);
        int iDataPosition = parcel.dataPosition();
        if (iC0 == 0) {
            return null;
        }
        long[] jArrCreateLongArray = parcel.createLongArray();
        parcel.setDataPosition(iDataPosition + iC0);
        return jArrCreateLongArray;
    }

    public static Parcelable T(Parcel parcel, int i10, Parcelable.Creator creator) {
        int iC0 = C0(i10, parcel);
        int iDataPosition = parcel.dataPosition();
        if (iC0 == 0) {
            return null;
        }
        Parcelable parcelable = (Parcelable) creator.createFromParcel(parcel);
        parcel.setDataPosition(iDataPosition + iC0);
        return parcelable;
    }

    public static final t0 U(w wVar, int i10, b1 b1Var) {
        if ((b1Var != null ? b1Var.y() : 0) == i10) {
            i10 = 1;
        }
        return new t0(i10, wVar);
    }

    public static String V(int i10, Parcel parcel) {
        int iC0 = C0(i10, parcel);
        int iDataPosition = parcel.dataPosition();
        if (iC0 == 0) {
            return null;
        }
        String string = parcel.readString();
        parcel.setDataPosition(iDataPosition + iC0);
        return string;
    }

    public static String[] W(int i10, Parcel parcel) {
        int iC0 = C0(i10, parcel);
        int iDataPosition = parcel.dataPosition();
        if (iC0 == 0) {
            return null;
        }
        String[] strArrCreateStringArray = parcel.createStringArray();
        parcel.setDataPosition(iDataPosition + iC0);
        return strArrCreateStringArray;
    }

    public static ArrayList X(int i10, Parcel parcel) {
        int iC0 = C0(i10, parcel);
        int iDataPosition = parcel.dataPosition();
        if (iC0 == 0) {
            return null;
        }
        ArrayList<String> arrayListCreateStringArrayList = parcel.createStringArrayList();
        parcel.setDataPosition(iDataPosition + iC0);
        return arrayListCreateStringArrayList;
    }

    public static final u1 Y(kotlin.reflect.e eVar, List list, boolean z, List list2) {
        g8.h descriptor;
        k0 k0Var;
        v9.r0 g0Var;
        f0 f0Var = eVar instanceof f0 ? (f0) eVar : null;
        if (f0Var == null || (descriptor = f0Var.getDescriptor()) == null) {
            throw new x1("Cannot create type for an unsupported classifier: " + eVar + " (" + eVar.getClass() + ')');
        }
        o0 o0VarG = descriptor.g();
        List parameters = o0VarG.getParameters();
        if (parameters.size() != list.size()) {
            throw new IllegalArgumentException("Class declares " + parameters.size() + " type parameters, but " + list.size() + " were provided.");
        }
        if (list2.isEmpty()) {
            k0.f22220l.getClass();
            k0Var = k0.f22221m;
        } else {
            k0.f22220l.getClass();
            k0Var = k0.f22221m;
        }
        List parameters2 = o0VarG.getParameters();
        ArrayList arrayList = new ArrayList(s.U(list, 10));
        int i10 = 0;
        for (Object obj : list) {
            int i11 = i10 + 1;
            if (i10 < 0) {
                t7.a.Q();
                throw null;
            }
            t tVar = (t) obj;
            u1 u1Var = (u1) tVar.f19912b;
            w wVar = u1Var != null ? u1Var.f282i : null;
            int i12 = tVar.f19911a;
            int i13 = i12 == 0 ? -1 : y7.a.f22909a[h.f0.c(i12)];
            if (i13 == -1) {
                g0Var = new g0((b1) parameters2.get(i10));
            } else if (i13 == 1) {
                g0Var = new t0(1, wVar);
            } else if (i13 == 2) {
                g0Var = new t0(2, wVar);
            } else {
                if (i13 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                g0Var = new t0(3, wVar);
            }
            arrayList.add(g0Var);
            i10 = i11;
        }
        return new u1(v9.e.r(arrayList, k0Var, o0VarG, z), null);
    }

    public static /* synthetic */ u1 Z(kotlin.reflect.d dVar, ArrayList arrayList, int i10) {
        int i11 = i10 & 1;
        z zVar = z.f19728i;
        List list = arrayList;
        if (i11 != 0) {
            list = zVar;
        }
        return Y(dVar, list, false, zVar);
    }

    public static Object[] a0(Parcel parcel, int i10, Parcelable.Creator creator) {
        int iC0 = C0(i10, parcel);
        int iDataPosition = parcel.dataPosition();
        if (iC0 == 0) {
            return null;
        }
        Object[] objArrCreateTypedArray = parcel.createTypedArray(creator);
        parcel.setDataPosition(iDataPosition + iC0);
        return objArrCreateTypedArray;
    }

    public static ArrayList b0(Parcel parcel, int i10, Parcelable.Creator creator) {
        int iC0 = C0(i10, parcel);
        int iDataPosition = parcel.dataPosition();
        if (iC0 == 0) {
            return null;
        }
        ArrayList arrayListCreateTypedArrayList = parcel.createTypedArrayList(creator);
        parcel.setDataPosition(iDataPosition + iC0);
        return arrayListCreateTypedArrayList;
    }

    public static Map d0(c0 c0Var) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator itSourceIterator = c0Var.sourceIterator();
        while (itSourceIterator.hasNext()) {
            Object objKeyOf = c0Var.keyOf(itSourceIterator.next());
            Object i0Var = linkedHashMap.get(objKeyOf);
            if (i0Var == null && !linkedHashMap.containsKey(objKeyOf)) {
                i0Var = new i0();
            }
            i0 i0Var2 = (i0) i0Var;
            i0Var2.f19744i++;
            linkedHashMap.put(objKeyOf, i0Var2);
        }
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            if ((entry instanceof s7.a) && !(entry instanceof g.a)) {
                q0.h(entry, "kotlin.collections.MutableMap.MutableEntry");
                throw null;
            }
            entry.setValue(Integer.valueOf(((i0) entry.getValue()).f19744i));
        }
        return q0.c(linkedHashMap);
    }

    public static void e0(int i10, Parcel parcel) {
        if (parcel.dataPosition() != i10) {
            throw new SafeParcelReader$ParseException(androidx.compose.foundation.c.p(i10, "Overread allowed size end=", new StringBuilder(String.valueOf(i10).length() + 26)), parcel);
        }
    }

    public static final void f0(w wVar, w wVar2, LinkedHashSet linkedHashSet, Set set) {
        g8.h hVarE = wVar.I0().e();
        if (hVarE instanceof b1) {
            if (!p.a(wVar.I0(), wVar2.I0())) {
                linkedHashSet.add(hVarE);
                return;
            }
            Iterator it = ((b1) hVarE).getUpperBounds().iterator();
            while (it.hasNext()) {
                f0((w) it.next(), wVar2, linkedHashSet, set);
            }
            return;
        }
        g8.h hVarE2 = wVar.I0().e();
        g8.i iVar = hVarE2 instanceof g8.i ? (g8.i) hVarE2 : null;
        List listN = iVar != null ? iVar.n() : null;
        int i10 = 0;
        for (v9.r0 r0Var : wVar.G0()) {
            int i11 = i10 + 1;
            b1 b1Var = listN != null ? (b1) x.p0(i10, listN) : null;
            if ((b1Var == null || set == null || !set.contains(b1Var)) && !r0Var.b() && !x.f0(linkedHashSet, r0Var.getType().I0().e()) && !p.a(r0Var.getType().I0(), wVar2.I0())) {
                f0(r0Var.getType(), wVar2, linkedHashSet, set);
            }
            i10 = i11;
        }
    }

    public static Object g0(Object obj, Object obj2) {
        if (obj != null) {
            return obj;
        }
        if (obj2 != null) {
            return obj2;
        }
        throw new NullPointerException("Both parameters are null");
    }

    public static final HashSet h0(Iterable iterable) {
        HashSet hashSet = new HashSet();
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            Set setD = ((p9.n) it.next()).d();
            if (setD == null) {
                return null;
            }
            x.b0(hashSet, setD);
        }
        return hashSet;
    }

    public static gb.d0 i0(String str) {
        Matcher matcher = gb.d0.f15633d.matcher(str);
        if (!matcher.lookingAt()) {
            throw new IllegalArgumentException(androidx.compose.material3.d.i('\"', "No subtype found for: \"", str).toString());
        }
        String strGroup = matcher.group(1);
        Locale locale = Locale.US;
        String lowerCase = strGroup.toLowerCase(locale);
        matcher.group(2).toLowerCase(locale);
        ArrayList arrayList = new ArrayList();
        Matcher matcher2 = gb.d0.f15634e.matcher(str);
        int iEnd = matcher.end();
        while (iEnd < str.length()) {
            matcher2.region(iEnd, str.length());
            if (!matcher2.lookingAt()) {
                throw new IllegalArgumentException(("Parameter is not formatted correctly: \"" + str.substring(iEnd) + "\" for: \"" + str + '\"').toString());
            }
            String strGroup2 = matcher2.group(1);
            if (strGroup2 == null) {
                iEnd = matcher2.end();
            } else {
                String strGroup3 = matcher2.group(2);
                if (strGroup3 == null) {
                    strGroup3 = matcher2.group(3);
                } else if (u.P(strGroup3, "'", false) && u.K(strGroup3, "'", false) && strGroup3.length() > 2) {
                    strGroup3 = strGroup3.substring(1, strGroup3.length() - 1);
                }
                arrayList.add(strGroup2);
                arrayList.add(strGroup3);
                iEnd = matcher2.end();
            }
        }
        return new gb.d0(str, lowerCase, (String[]) arrayList.toArray(new String[0]));
    }

    public static Object j0(Future future) {
        Object obj;
        boolean z = false;
        if (!future.isDone()) {
            throw new IllegalStateException(androidx.work.impl.t.y("Future was expected to be done: %s", future));
        }
        while (true) {
            try {
                obj = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }

    public static androidx.core.os.m m0(Configuration configuration) {
        return Build.VERSION.SDK_INT >= 24 ? new androidx.core.os.m(new androidx.core.os.p(androidx.core.os.h.a(configuration))) : androidx.core.os.m.a(configuration.locale);
    }

    public static final w n0(b1 b1Var) {
        Object obj;
        b1Var.getUpperBounds().isEmpty();
        Iterator it = b1Var.getUpperBounds().iterator();
        while (true) {
            obj = null;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            g8.h hVarE = ((w) next).I0().e();
            f fVar = hVarE instanceof f ? (f) hVarE : null;
            if (fVar != null && fVar.p0() != 2 && fVar.p0() != 5) {
                obj = next;
                break;
            }
        }
        w wVar = (w) obj;
        return wVar == null ? (w) x.m0(b1Var.getUpperBounds()) : wVar;
    }

    public static boolean o0(Context context) {
        ac.b.V(context, "The application context is required.");
        return context.checkPermission("android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) == 0;
    }

    public static final boolean p0(b1 b1Var, o0 o0Var, Set set) {
        List<w> upperBounds = b1Var.getUpperBounds();
        if (upperBounds.isEmpty()) {
            return false;
        }
        for (w wVar : upperBounds) {
            if (M(wVar, b1Var.m().I0(), set) && (o0Var == null || p.a(wVar.I0(), o0Var))) {
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ boolean q0(b1 b1Var, o0 o0Var, int i10) {
        if ((i10 & 2) != 0) {
            o0Var = null;
        }
        return p0(b1Var, o0Var, null);
    }

    public static y0 r0(RuntimeException runtimeException) {
        y0 y0Var = new y0();
        y0Var.setException(runtimeException);
        return y0Var;
    }

    public static z0 s0(Object obj) {
        return obj == null ? z0.f14245l : new z0(obj);
    }

    public static final void t0(Context context) {
        if (context.getDatabasePath("androidx.work.workdb").exists()) {
            m2.t.d().a(androidx.work.impl.o.f6960a, "Migrating WorkDatabase to the no-backup directory");
            File databasePath = context.getDatabasePath("androidx.work.workdb");
            File file = new File(androidx.work.impl.a.f6726a.a(context), "androidx.work.workdb");
            String[] strArr = androidx.work.impl.o.f6961b;
            int iQ0 = kotlin.collections.i0.q0(strArr.length);
            if (iQ0 < 16) {
                iQ0 = 16;
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap(iQ0);
            for (String str : strArr) {
                linkedHashMap.put(new File(databasePath.getPath() + str), new File(file.getPath() + str));
            }
            for (Map.Entry entry : kotlin.collections.h0.w0(linkedHashMap, new x6.x(databasePath, file)).entrySet()) {
                File file2 = (File) entry.getKey();
                File file3 = (File) entry.getValue();
                if (file2.exists()) {
                    if (file3.exists()) {
                        m2.t.d().g(androidx.work.impl.o.f6960a, "Over-writing contents of " + file3);
                    }
                    m2.t.d().a(androidx.work.impl.o.f6960a, file2.renameTo(file3) ? "Migrated " + file2 + "to " + file3 : "Renaming " + file2 + " to " + file3 + " failed");
                }
            }
        }
    }

    public static boolean u0(int i10, Parcel parcel) {
        O0(parcel, i10, 4);
        return parcel.readInt() != 0;
    }

    public static Boolean v0(int i10, Parcel parcel) {
        int iC0 = C0(i10, parcel);
        if (iC0 == 0) {
            return null;
        }
        P0(parcel, iC0, 4);
        return Boolean.valueOf(parcel.readInt() != 0);
    }

    public static double w0(int i10, Parcel parcel) {
        O0(parcel, i10, 8);
        return parcel.readDouble();
    }

    public static float x0(int i10, Parcel parcel) {
        O0(parcel, i10, 4);
        return parcel.readFloat();
    }

    public static IBinder y0(int i10, Parcel parcel) {
        int iC0 = C0(i10, parcel);
        int iDataPosition = parcel.dataPosition();
        if (iC0 == 0) {
            return null;
        }
        IBinder strongBinder = parcel.readStrongBinder();
        parcel.setDataPosition(iDataPosition + iC0);
        return strongBinder;
    }

    public static int z0(int i10, Parcel parcel) {
        O0(parcel, i10, 4);
        return parcel.readInt();
    }

    @Override // bb.b
    public double A(cb.f1 f1Var, int i10) {
        return p();
    }

    @Override // bb.b
    public short B(cb.f1 f1Var, int i10) {
        return m();
    }

    @Override // bb.d
    public abstract byte D();

    @Override // bb.b
    public byte E(cb.f1 f1Var, int i10) {
        return D();
    }

    @Override // bb.b
    public int c(ab.g gVar, int i10) {
        return g();
    }

    public void c0() {
        throw new SerializationException(l0.f19747a.b(getClass()) + " can't retrieve untyped values");
    }

    public Object d(ab.g gVar, int i10, ya.c cVar, Object obj) {
        return q(cVar);
    }

    @Override // bb.b
    public String f(ab.g gVar, int i10) {
        return v();
    }

    @Override // bb.d
    public abstract int g();

    @Override // bb.d
    public abstract long i();

    @Override // bb.b
    public Object j(ab.g gVar, int i10, ya.h hVar, Object obj) {
        if (hVar.getDescriptor().b() || x()) {
            return q(hVar);
        }
        return null;
    }

    @Override // bb.b
    public long k(ab.g gVar, int i10) {
        return i();
    }

    public float k0(FadeAndShortSlide fadeAndShortSlide, ViewGroup viewGroup, View view, int[] iArr) {
        return view.getTranslationX();
    }

    @Override // bb.b
    public float l(ab.g gVar, int i10) {
        return n();
    }

    public float l0(FadeAndShortSlide fadeAndShortSlide, ViewGroup viewGroup, View view, int[] iArr) {
        return view.getTranslationY();
    }

    @Override // bb.d
    public abstract short m();

    @Override // bb.d
    public float n() {
        c0();
        throw null;
    }

    @Override // bb.b
    public bb.d o(cb.f1 f1Var, int i10) {
        return h(f1Var.g(i10));
    }

    @Override // bb.d
    public double p() {
        c0();
        throw null;
    }

    @Override // bb.d
    public Object q(ya.c cVar) {
        return cVar.deserialize(this);
    }

    @Override // bb.d
    public boolean r() {
        c0();
        throw null;
    }

    @Override // bb.d
    public char s() {
        c0();
        throw null;
    }

    @Override // bb.b
    public boolean t(ab.g gVar, int i10) {
        return r();
    }

    @Override // bb.d
    public String v() {
        c0();
        throw null;
    }

    @Override // bb.d
    public boolean x() {
        return true;
    }

    @Override // bb.b
    public char y(cb.f1 f1Var, int i10) {
        return s();
    }

    @Override // bb.d
    public int z(ab.g gVar) {
        c0();
        throw null;
    }

    @Override // bb.d
    public bb.b b(ab.g gVar) {
        return this;
    }

    @Override // bb.d
    public bb.d h(ab.g gVar) {
        return this;
    }

    public void u(ab.g gVar) {
    }
}
