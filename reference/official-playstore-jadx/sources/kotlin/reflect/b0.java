package kotlin.reflect;

import a8.l1;
import a8.r1;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EdgeEffect;
import androidx.appcompat.widget.r4;
import androidx.appcompat.widget.t4;
import androidx.media3.common.C;
import androidx.navigation.d0;
import androidx.navigation.n0;
import androidx.navigation.o0;
import cb.b1;
import cb.g0;
import cb.j1;
import cb.t0;
import cb.u1;
import com.google.common.util.concurrent.r0;
import io.sentry.ILogger;
import io.sentry.k3;
import io.sentry.protocol.i0;
import io.sentry.v4;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import ka.m0;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.q0;
import m2.f0;
import x6.j0;

/* JADX INFO: loaded from: classes5.dex */
public abstract class b0 {
    /* JADX WARN: Removed duplicated region for block: B:15:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static gb.h A(gb.a0 r26) {
        /*
            Method dump skipped, instruction units count: 475
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.b0.A(gb.a0):gb.h");
    }

    /* JADX WARN: Code restructure failed: missing block: B:176:0x02ba, code lost:
    
        throw new java.lang.IllegalArgumentException("");
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0107, code lost:
    
        r2 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x010c, code lost:
    
        if (r8 == r23.length()) goto L200;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0112, code lost:
    
        if (r3 == '+') goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0114, code lost:
    
        if (r3 == '-') goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0116, code lost:
    
        r3 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0118, code lost:
    
        r3 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x011c, code lost:
    
        if (r8 == (r20 + r3)) goto L201;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x011e, code lost:
    
        r6 = r18;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0194 A[LOOP:7: B:108:0x0192->B:109:0x0194, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:198:0x00ff A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0105 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:232:0x018d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0157 A[LOOP:5: B:97:0x0155->B:98:0x0157, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long B(java.lang.String r23) {
        /*
            Method dump skipped, instruction units count: 786
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.b0.B(java.lang.String):long");
    }

    public static final ya.h C(fb.b bVar, q qVar) {
        ya.h hVarL = f0.L(bVar, qVar, true);
        if (hVarL != null) {
            return hVarL;
        }
        b1.g(b1.f(qVar));
        throw null;
    }

    public static final ArrayList D(fb.b bVar, List list, boolean z) {
        if (z) {
            ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(C(bVar, (q) it.next()));
            }
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(list, 10));
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            ya.h hVarL = f0.L(bVar, (q) it2.next(), false);
            if (hVarL == null) {
                return null;
            }
            arrayList2.add(hVarL);
        }
        return arrayList2;
    }

    public static void E(View view, CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 26) {
            r4.a(view, charSequence);
            return;
        }
        t4 t4Var = t4.f1750u;
        if (t4Var != null && t4Var.f1752i == view) {
            t4.b(null);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            new t4(view, charSequence);
            return;
        }
        t4 t4Var2 = t4.f1751v;
        if (t4Var2 != null && t4Var2.f1752i == view) {
            t4Var2.a();
        }
        view.setOnLongClickListener(null);
        view.setLongClickable(false);
        view.setOnHoverListener(null);
    }

    public static final Object F(pa.v vVar, pa.v vVar2, r7.p pVar) throws Throwable {
        Object vVar3;
        Object objE;
        try {
            q0.e(2, pVar);
            vVar3 = pVar.invoke(vVar2, vVar);
        } catch (Throwable th) {
            vVar3 = new ka.v(th, false);
        }
        e7.a aVar = e7.a.f15033i;
        if (vVar3 == aVar || (objE = vVar.E(vVar3)) == m0.f19615e) {
            return aVar;
        }
        if (objE instanceof ka.v) {
            throw ((ka.v) objE).f19647a;
        }
        return m0.x(objE);
    }

    public static final x6.x G(Object obj, Object obj2) {
        return new x6.x(obj, obj2);
    }

    public static final long H(int i10, kotlin.time.e eVar) {
        if (eVar.compareTo(kotlin.time.e.SECONDS) > 0) {
            return I(i10, eVar);
        }
        long jConvert = kotlin.time.e.NANOSECONDS.f19969i.convert(i10, eVar.f19969i);
        io.sentry.util.l lVar = kotlin.time.c.f19956l;
        long j10 = jConvert << 1;
        int i11 = kotlin.time.d.f19961a;
        return j10;
    }

    public static final long I(long j10, kotlin.time.e eVar) {
        kotlin.time.e eVar2 = kotlin.time.e.NANOSECONDS;
        TimeUnit timeUnit = eVar.f19969i;
        TimeUnit timeUnit2 = eVar.f19969i;
        long jConvert = timeUnit.convert(4611686018426999999L, eVar2.f19969i);
        if ((-jConvert) <= j10 && j10 <= jConvert) {
            long jConvert2 = eVar2.f19969i.convert(j10, timeUnit2);
            io.sentry.util.l lVar = kotlin.time.c.f19956l;
            long j11 = jConvert2 << 1;
            int i10 = kotlin.time.d.f19961a;
            return j11;
        }
        kotlin.time.e eVar3 = kotlin.time.e.MILLISECONDS;
        if (eVar.compareTo(eVar3) < 0) {
            return o(qb.d.o(eVar3.f19969i.convert(j10, timeUnit2), -4611686018427387903L, 4611686018427387903L));
        }
        long jSignum = Long.signum(j10);
        if (j10 < C.TIME_UNSET) {
            j10 = -9223372036854775807L;
        }
        return o(f0.i(Math.abs(j10), eVar) * jSignum);
    }

    public static final String J(String str) {
        StringBuilder sb2 = new StringBuilder(str.length());
        int length = str.length();
        for (int i10 = 0; i10 < length; i10++) {
            char cCharAt = str.charAt(i10);
            if ('A' <= cCharAt && cCharAt < '[') {
                cCharAt = Character.toLowerCase(cCharAt);
            }
            sb2.append(cCharAt);
        }
        return sb2.toString();
    }

    public static CopyOnWriteArrayList K(CopyOnWriteArrayList copyOnWriteArrayList) {
        ArrayList arrayList = new ArrayList();
        if (copyOnWriteArrayList != null) {
            Iterator it = copyOnWriteArrayList.iterator();
            if (it.hasNext()) {
                it.next().getClass();
                throw new ClassCastException();
            }
        }
        return new CopyOnWriteArrayList(arrayList);
    }

    public static db.t a(r7.l lVar) {
        db.a aVar = db.b.f14877d;
        db.g gVar = new db.g();
        db.i iVar = aVar.f14878a;
        gVar.f14894a = iVar.f14901a;
        boolean z = iVar.f14905e;
        gVar.f14895b = iVar.f14902b;
        gVar.f14896c = iVar.f14903c;
        gVar.f14897d = iVar.f14904d;
        String str = iVar.f14906f;
        String str2 = iVar.f14907g;
        gVar.f14898e = iVar.f14908h;
        boolean z5 = iVar.f14909i;
        fb.b bVar = aVar.f14879b;
        lVar.invoke(gVar);
        if (!kotlin.jvm.internal.p.a(str, "    ")) {
            throw new IllegalArgumentException("Indent should not be specified when default printing mode is used");
        }
        db.t tVar = new db.t(new db.i(gVar.f14894a, gVar.f14895b, gVar.f14896c, gVar.f14897d, z, str, str2, gVar.f14898e, z5), bVar);
        kotlin.jvm.internal.p.a(bVar, fb.d.f15400a);
        return tVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0105  */
    /* JADX WARN: Type inference failed for: r0v39, types: [java.lang.Object, x6.s] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final b8.e b(a8.l1 r7, boolean r8) {
        /*
            Method dump skipped, instruction units count: 556
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.b0.b(a8.l1, boolean):b8.e");
    }

    public static final String c(Type type) {
        if (!(type instanceof Class)) {
            return type.toString();
        }
        Class cls = (Class) type;
        if (!cls.isArray()) {
            return cls.getName();
        }
        ga.m mVarM = ga.r.M(type, a0.f19755i);
        return ((Class) ga.r.P(mVarM)).getName() + kotlin.text.u.N(ga.r.F(mVarM), "[]");
    }

    public static final long d(long j10, long j11) {
        if (j10 != 4611686018427387903L && j10 != -4611686018427387903L) {
            return (j11 == 4611686018427387903L || j11 == -4611686018427387903L) ? j11 : qb.d.o(j10 + j11, -4611686018427387903L, 4611686018427387903L);
        }
        if ((-4611686018427387903L >= j11 || j11 >= 4611686018427387903L) && (j11 ^ j10) < 0) {
            return 9223372036854759646L;
        }
        return j10;
    }

    public static final String e(String str) {
        char cCharAt;
        if (str.length() == 0 || 'a' > (cCharAt = str.charAt(0)) || cCharAt >= '{') {
            return str;
        }
        return Character.toUpperCase(cCharAt) + str.substring(1);
    }

    public static void f(b8.e eVar, Object[] objArr) {
        if (eVar.a().size() == objArr.length) {
            return;
        }
        StringBuilder sb2 = new StringBuilder("Callable expects ");
        sb2.append(eVar.a().size());
        sb2.append(" arguments, but ");
        throw new IllegalArgumentException(androidx.compose.material3.d.j(objArr.length, " were provided.", sb2));
    }

    public static void g(long j10, String str) {
        if (j10 >= 0) {
            return;
        }
        throw new IllegalArgumentException(str + " (" + j10 + ") must be >= 0");
    }

    public static void h(boolean z) {
        if (!z) {
            throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final b8.u i(a8.l1 r4, boolean r5, java.lang.reflect.Field r6) {
        /*
            Method dump skipped, instruction units count: 208
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.b0.i(a8.l1, boolean, java.lang.reflect.Field):b8.u");
    }

    public static final boolean j(l1 l1Var) {
        return !v9.b1.e(l1Var.C().x().getType());
    }

    public static final Type k(q qVar, boolean z) {
        e eVarB = qVar.b();
        if (eVarB instanceof r) {
            return new y((r) eVarB);
        }
        if (!(eVarB instanceof d)) {
            throw new UnsupportedOperationException("Unsupported type classifier: " + qVar);
        }
        d dVar = (d) eVarB;
        Class clsN = z ? r0.n(dVar) : ((kotlin.jvm.internal.f) dVar).c();
        List listG = qVar.g();
        if (listG.isEmpty()) {
            return clsN;
        }
        if (!clsN.isArray()) {
            return m(listG, clsN);
        }
        if (clsN.getComponentType().isPrimitive()) {
            return clsN;
        }
        t tVar = (t) kotlin.collections.x.T0(listG);
        if (tVar == null) {
            throw new IllegalArgumentException("kotlin.Array must have exactly one type argument: " + qVar);
        }
        int i10 = tVar.f19911a;
        q qVar2 = tVar.f19912b;
        int i11 = i10 == 0 ? -1 : z.f19923a[h.f0.c(i10)];
        if (i11 == -1 || i11 == 1) {
            return clsN;
        }
        if (i11 != 2 && i11 != 3) {
            throw new NoWhenBranchMatchedException();
        }
        Type typeK = k(qVar2, false);
        return typeK instanceof Class ? clsN : new a(typeK);
    }

    public static androidx.emoji2.text.b0 l(Context context) {
        ProviderInfo providerInfo;
        androidx.core.provider.e eVar;
        ApplicationInfo applicationInfo;
        a1.a cVar = Build.VERSION.SDK_INT >= 28 ? new androidx.emoji2.text.c() : new a1.a();
        PackageManager packageManager = context.getPackageManager();
        p0.g.b(packageManager, "Package manager required to locate emoji font provider");
        Iterator<ResolveInfo> it = packageManager.queryIntentContentProviders(new Intent("androidx.content.action.LOAD_EMOJI_FONT"), 0).iterator();
        while (true) {
            if (!it.hasNext()) {
                providerInfo = null;
                break;
            }
            providerInfo = it.next().providerInfo;
            if (providerInfo != null && (applicationInfo = providerInfo.applicationInfo) != null && (applicationInfo.flags & 1) == 1) {
                break;
            }
        }
        if (providerInfo == null) {
            eVar = null;
        } else {
            try {
                String str = providerInfo.authority;
                String str2 = providerInfo.packageName;
                Signature[] signatureArrL = cVar.l(packageManager, str2);
                ArrayList arrayList = new ArrayList();
                for (Signature signature : signatureArrL) {
                    arrayList.add(signature.toByteArray());
                }
                eVar = new androidx.core.provider.e(str, str2, "emojicompat-emoji-font", Collections.singletonList(arrayList));
            } catch (PackageManager.NameNotFoundException e5) {
                Log.wtf("emoji2.text.DefaultEmojiConfig", e5);
                eVar = null;
            }
        }
        if (eVar == null) {
            return null;
        }
        return new androidx.emoji2.text.b0(new androidx.emoji2.text.a0(context, eVar));
    }

    public static final w m(List list, Class cls) {
        Class<?> declaringClass = cls.getDeclaringClass();
        if (declaringClass == null) {
            ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(u((t) it.next()));
            }
            return new w(cls, null, arrayList);
        }
        if (Modifier.isStatic(cls.getModifiers())) {
            ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(list, 10));
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                arrayList2.add(u((t) it2.next()));
            }
            return new w(cls, declaringClass, arrayList2);
        }
        int length = cls.getTypeParameters().length;
        w wVarM = m(list.subList(length, list.size()), declaringClass);
        List listSubList = list.subList(0, length);
        ArrayList arrayList3 = new ArrayList(kotlin.collections.s.U(listSubList, 10));
        Iterator it3 = listSubList.iterator();
        while (it3.hasNext()) {
            arrayList3.add(u((t) it3.next()));
        }
        return new w(cls, wVarM, arrayList3);
    }

    public static boolean n(v4 v4Var, String str, k3 k3Var, ILogger iLogger) {
        int i10;
        int i11;
        int i12;
        i10 = 8;
        i11 = 2;
        i12 = 0;
        switch (str) {
            case "debug_meta":
                v4Var.x = (io.sentry.protocol.d) k3Var.P(iLogger, new io.sentry.clientreport.a(i10));
                return true;
            case "server_name":
                v4Var.f17994u = k3Var.l0();
                return true;
            case "contexts":
                v4Var.f17985l.l(io.sentry.clientreport.a.c(k3Var, iLogger));
                return true;
            case "environment":
                v4Var.f17990q = k3Var.l0();
                return true;
            case "breadcrumbs":
                v4Var.f17996w = k3Var.I0(iLogger, new io.sentry.e(i12));
                return true;
            case "sdk":
                v4Var.f17986m = (io.sentry.protocol.t) k3Var.P(iLogger, new io.sentry.clientreport.a(21));
                return true;
            case "dist":
                v4Var.f17995v = k3Var.l0();
                return true;
            case "tags":
                v4Var.f17988o = k2.c.w((Map) k3Var.F0());
                return true;
            case "user":
                v4Var.f17992s = (i0) k3Var.P(iLogger, new io.sentry.protocol.c0(i11));
                return true;
            case "extra":
                v4Var.f17997y = k2.c.w((Map) k3Var.F0());
                return true;
            case "event_id":
                v4Var.f17984i = (io.sentry.protocol.v) k3Var.P(iLogger, new io.sentry.clientreport.a(23));
                return true;
            case "release":
                v4Var.f17989p = k3Var.l0();
                return true;
            case "request":
                v4Var.f17987n = (io.sentry.protocol.p) k3Var.P(iLogger, new io.sentry.clientreport.a(19));
                return true;
            case "platform":
                v4Var.f17991r = k3Var.l0();
                return true;
            default:
                return false;
        }
    }

    public static final long o(long j10) {
        long j11 = (j10 << 1) + 1;
        kotlin.time.c.f19956l.getClass();
        int i10 = kotlin.time.d.f19961a;
        return j11;
    }

    public static final m8.d p(Annotation[] annotationArr, f9.c cVar) {
        Annotation annotation;
        int length = annotationArr.length;
        int i10 = 0;
        while (true) {
            if (i10 >= length) {
                annotation = null;
                break;
            }
            annotation = annotationArr[i10];
            if (m8.c.a(((kotlin.jvm.internal.f) r0.k(annotation)).c()).b().equals(cVar)) {
                break;
            }
            i10++;
        }
        if (annotation != null) {
            return new m8.d(annotation);
        }
        return null;
    }

    public static final ArrayList q(Annotation[] annotationArr) {
        ArrayList arrayList = new ArrayList(annotationArr.length);
        for (Annotation annotation : annotationArr) {
            arrayList.add(new m8.d(annotation));
        }
        return arrayList;
    }

    public static final Object r(l1 l1Var) {
        r1 r1VarC = l1Var.C();
        return f0.g(r1VarC.f257t, r1VarC.x());
    }

    public static float s(EdgeEffect edgeEffect) {
        if (Build.VERSION.SDK_INT >= 31) {
            return u0.f.b(edgeEffect);
        }
        return 0.0f;
    }

    public static final Type t(q qVar) {
        Type typeK;
        return (!(qVar instanceof kotlin.jvm.internal.q) || (typeK = ((kotlin.jvm.internal.q) qVar).k()) == null) ? k(qVar, false) : typeK;
    }

    public static final Type u(t tVar) {
        int i10 = tVar.f19911a;
        if (i10 == 0) {
            return c0.f19756m;
        }
        q qVar = tVar.f19912b;
        int iC = h.f0.c(i10);
        if (iC == 0) {
            return k(qVar, true);
        }
        if (iC == 1) {
            return new c0(null, k(qVar, true));
        }
        if (iC == 2) {
            return new c0(k(qVar, true), null);
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean v(gb.k0 r2, gb.p0 r3) {
        /*
            int r0 = r3.f15783n
            r1 = 200(0xc8, float:2.8E-43)
            if (r0 == r1) goto L55
            r1 = 410(0x19a, float:5.75E-43)
            if (r0 == r1) goto L55
            r1 = 414(0x19e, float:5.8E-43)
            if (r0 == r1) goto L55
            r1 = 501(0x1f5, float:7.02E-43)
            if (r0 == r1) goto L55
            r1 = 203(0xcb, float:2.84E-43)
            if (r0 == r1) goto L55
            r1 = 204(0xcc, float:2.86E-43)
            if (r0 == r1) goto L55
            r1 = 307(0x133, float:4.3E-43)
            if (r0 == r1) goto L2e
            r1 = 308(0x134, float:4.32E-43)
            if (r0 == r1) goto L55
            r1 = 404(0x194, float:5.66E-43)
            if (r0 == r1) goto L55
            r1 = 405(0x195, float:5.68E-43)
            if (r0 == r1) goto L55
            switch(r0) {
                case 300: goto L55;
                case 301: goto L55;
                case 302: goto L2e;
                default: goto L2d;
            }
        L2d:
            goto L67
        L2e:
            gb.a0 r0 = r3.f15785p
            java.lang.String r1 = "Expires"
            java.lang.String r0 = r0.a(r1)
            if (r0 != 0) goto L39
            r0 = 0
        L39:
            if (r0 != 0) goto L55
            gb.h r0 = r3.i()
            int r0 = r0.f15690c
            r1 = -1
            if (r0 != r1) goto L55
            gb.h r0 = r3.i()
            boolean r0 = r0.f15693f
            if (r0 != 0) goto L55
            gb.h r0 = r3.i()
            boolean r0 = r0.f15692e
            if (r0 != 0) goto L55
            goto L67
        L55:
            gb.h r3 = r3.i()
            boolean r3 = r3.f15689b
            if (r3 != 0) goto L67
            gb.h r2 = r2.a()
            boolean r2 = r2.f15689b
            if (r2 != 0) goto L67
            r2 = 1
            return r2
        L67:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.b0.v(gb.k0, gb.p0):boolean");
    }

    public static final boolean w(int i10, String str) {
        char cCharAt = str.charAt(i10);
        return 'A' <= cCharAt && cCharAt < '[';
    }

    public static final n0 x(r7.l lVar) {
        o0 o0Var = new o0();
        lVar.invoke(o0Var);
        boolean z = o0Var.f4742b;
        boolean z5 = o0Var.f4743c;
        String str = o0Var.f4745e;
        androidx.navigation.m0 m0Var = o0Var.f4741a;
        if (str != null) {
            boolean z10 = o0Var.f4746f;
            boolean z11 = o0Var.f4747g;
            m0Var.f4720b = str;
            m0Var.f4719a = -1;
            m0Var.f4721c = z10;
            m0Var.f4722d = z11;
        } else {
            int i10 = o0Var.f4744d;
            boolean z12 = o0Var.f4746f;
            boolean z13 = o0Var.f4747g;
            m0Var.f4719a = i10;
            m0Var.f4720b = null;
            m0Var.f4721c = z12;
            m0Var.f4722d = z13;
        }
        String str2 = m0Var.f4720b;
        if (str2 == null) {
            return new n0(z, z5, m0Var.f4719a, m0Var.f4721c, m0Var.f4722d, m0Var.f4723e, m0Var.f4724f);
        }
        boolean z14 = m0Var.f4721c;
        boolean z15 = m0Var.f4722d;
        int i11 = m0Var.f4723e;
        int i12 = m0Var.f4724f;
        int i13 = d0.f4656r;
        n0 n0Var = new n0(z, z5, "android-app://androidx.navigation/".concat(str2).hashCode(), z14, z15, i11, i12);
        n0Var.f4734h = str2;
        return n0Var;
    }

    public static float y(EdgeEffect edgeEffect, float f10, float f11) {
        if (Build.VERSION.SDK_INT >= 31) {
            return u0.f.c(edgeEffect, f10, f11);
        }
        u0.e.a(edgeEffect, f10, f11);
        return f10;
    }

    public static final ya.h z(d dVar, ArrayList arrayList, r7.a aVar) {
        ya.h u1Var;
        ya.h j1Var;
        kotlin.jvm.internal.m0 m0Var = l0.f19747a;
        if (kotlin.jvm.internal.p.a(dVar, m0Var.b(Collection.class)) ? true : kotlin.jvm.internal.p.a(dVar, m0Var.b(List.class)) ? true : kotlin.jvm.internal.p.a(dVar, m0Var.b(List.class)) ? true : kotlin.jvm.internal.p.a(dVar, m0Var.b(ArrayList.class))) {
            u1Var = new cb.d((ya.h) arrayList.get(0), 0);
        } else if (kotlin.jvm.internal.p.a(dVar, m0Var.b(HashSet.class))) {
            u1Var = new cb.d((ya.h) arrayList.get(0), 1);
        } else {
            if (kotlin.jvm.internal.p.a(dVar, m0Var.b(Set.class)) ? true : kotlin.jvm.internal.p.a(dVar, m0Var.b(Set.class)) ? true : kotlin.jvm.internal.p.a(dVar, m0Var.b(LinkedHashSet.class))) {
                u1Var = new cb.d((ya.h) arrayList.get(0), 2);
            } else if (kotlin.jvm.internal.p.a(dVar, m0Var.b(HashMap.class))) {
                u1Var = new g0((ya.h) arrayList.get(0), (ya.h) arrayList.get(1), 0);
            } else {
                if (kotlin.jvm.internal.p.a(dVar, m0Var.b(Map.class)) ? true : kotlin.jvm.internal.p.a(dVar, m0Var.b(Map.class)) ? true : kotlin.jvm.internal.p.a(dVar, m0Var.b(LinkedHashMap.class))) {
                    u1Var = new g0((ya.h) arrayList.get(0), (ya.h) arrayList.get(1), 1);
                } else {
                    if (kotlin.jvm.internal.p.a(dVar, m0Var.b(Map.Entry.class))) {
                        j1Var = new t0((ya.h) arrayList.get(0), (ya.h) arrayList.get(1), 0);
                    } else if (kotlin.jvm.internal.p.a(dVar, m0Var.b(x6.x.class))) {
                        j1Var = new t0((ya.h) arrayList.get(0), (ya.h) arrayList.get(1), 1);
                    } else if (kotlin.jvm.internal.p.a(dVar, m0Var.b(j0.class))) {
                        u1Var = new u1((ya.h) arrayList.get(0), (ya.h) arrayList.get(1), (ya.h) arrayList.get(2));
                    } else if (((kotlin.jvm.internal.f) dVar).c().isArray()) {
                        j1Var = new j1((d) aVar.invoke(), (ya.h) arrayList.get(0));
                    } else {
                        u1Var = null;
                    }
                    u1Var = j1Var;
                }
            }
        }
        if (u1Var != null) {
            return u1Var;
        }
        ya.h[] hVarArr = (ya.h[]) arrayList.toArray(new ya.h[0]);
        return b1.d(dVar, (ya.h[]) Arrays.copyOf(hVarArr, hVarArr.length));
    }
}
