package com.google.common.util.concurrent;

import android.content.Context;
import android.graphics.Color;
import android.os.StrictMode;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import androidx.core.view.o2;
import com.arvio.tv.R;
import io.sentry.ILogger;
import io.sentry.q2;
import io.sentry.u2;
import io.sentry.w5;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.Callable;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes4.dex */
public abstract class p0 implements o2 {
    public static final String A(List list) {
        StringBuilder sb2 = new StringBuilder();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            f9.f fVar = (f9.f) it.next();
            if (sb2.length() > 0) {
                sb2.append(".");
            }
            sb2.append(z(fVar));
        }
        return sb2.toString();
    }

    public static final String B(String str, String str2, String str3, String str4, String str5) {
        if (!kotlin.text.u.P(str, str2, false) || !kotlin.text.u.P(str3, str4, false)) {
            return null;
        }
        String strSubstring = str.substring(str2.length());
        String strSubstring2 = str3.substring(str4.length());
        String strConcat = str5.concat(strSubstring);
        if (strSubstring.equals(strSubstring2)) {
            return strConcat;
        }
        if (!E(strSubstring, strSubstring2)) {
            return null;
        }
        return strConcat + '!';
    }

    public static final int C(int i10, int i11) {
        return (i10 << (32 - i11)) | (i10 >>> i11);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final f9.c D(f9.c r3, f9.c r4) {
        /*
            boolean r0 = r3.equals(r4)
            if (r0 == 0) goto L7
            goto L29
        L7:
            boolean r0 = r4.d()
            if (r0 == 0) goto Le
            goto L29
        Le:
            java.lang.String r0 = r3.b()
            java.lang.String r1 = r4.b()
            r2 = 0
            boolean r2 = kotlin.text.u.P(r0, r1, r2)
            if (r2 == 0) goto L51
            int r1 = r1.length()
            char r0 = r0.charAt(r1)
            r1 = 46
            if (r0 != r1) goto L51
        L29:
            boolean r0 = r4.d()
            if (r0 == 0) goto L30
            goto L51
        L30:
            boolean r0 = r3.equals(r4)
            if (r0 == 0) goto L39
            f9.c r3 = f9.c.f15358c
            return r3
        L39:
            f9.c r0 = new f9.c
            java.lang.String r3 = r3.b()
            java.lang.String r4 = r4.b()
            int r4 = r4.length()
            int r4 = r4 + 1
            java.lang.String r3 = r3.substring(r4)
            r0.<init>(r3)
            return r0
        L51:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.p0.D(f9.c, f9.c):f9.c");
    }

    public static final boolean E(String str, String str2) {
        if (str.equals(kotlin.text.u.O(str2, "?", "", false))) {
            return true;
        }
        if (kotlin.text.u.K(str2, "?", false) && kotlin.jvm.internal.p.a(str.concat("?"), str2)) {
            return true;
        }
        StringBuilder sb2 = new StringBuilder("(");
        sb2.append(str);
        sb2.append(")?");
        return kotlin.jvm.internal.p.a(sb2.toString(), str2);
    }

    public static Object F(Callable callable) {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        try {
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
            return callable.call();
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public static void G(int i10, Object[] objArr) {
        for (int i11 = 0; i11 < i10; i11++) {
            if (objArr[i11] == null) {
                throw new NullPointerException(androidx.compose.foundation.c.p(i11, "at index ", new StringBuilder(String.valueOf(i11).length() + 9)));
            }
        }
    }

    public static final int g(v9.w wVar) {
        h8.b bVarJ = wVar.getAnnotations().j(d8.p.f14748q);
        if (bVarJ == null) {
            return 0;
        }
        return ((Number) ((k9.k) ((k9.g) kotlin.collections.h0.s0(bVarJ.a(), d8.q.f14760e))).f19530a).intValue();
    }

    public static final v9.b0 h(d8.k kVar, h8.h hVar, v9.w wVar, List list, ArrayList arrayList, v9.w wVar2, boolean z) {
        g8.f fVarJ;
        int i10 = 0;
        ArrayList arrayList2 = new ArrayList(list.size() + arrayList.size() + (wVar != null ? 1 : 0) + 1);
        ArrayList arrayList3 = new ArrayList(kotlin.collections.s.U(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList3.add(new v9.t0((v9.w) it.next()));
        }
        arrayList2.addAll(arrayList3);
        ea.o.d(arrayList2, wVar != null ? new v9.t0(1, wVar) : null);
        int i11 = 0;
        for (Object obj : arrayList) {
            int i12 = i11 + 1;
            if (i11 < 0) {
                t7.a.Q();
                throw null;
            }
            arrayList2.add(new v9.t0((v9.w) obj));
            i11 = i12;
        }
        arrayList2.add(new v9.t0(wVar2));
        int size = list.size() + arrayList.size() + (wVar == null ? 0 : 1);
        if (z) {
            fVarJ = kVar.v(size);
        } else {
            f9.f fVar = d8.q.f14756a;
            fVarJ = kVar.j("Function" + size);
        }
        h8.h iVar = h8.g.f15863a;
        if (wVar != null) {
            f9.c cVar = d8.p.f14747p;
            if (!hVar.x(cVar)) {
                ArrayList arrayListG0 = kotlin.collections.x.G0(hVar, new h8.j(kVar, cVar, kotlin.collections.a0.f19683i));
                hVar = arrayListG0.isEmpty() ? iVar : new h8.i(arrayListG0, i10);
            }
        }
        if (!list.isEmpty()) {
            int size2 = list.size();
            f9.c cVar2 = d8.p.f14748q;
            if (!hVar.x(cVar2)) {
                ArrayList arrayListG02 = kotlin.collections.x.G0(hVar, new h8.j(kVar, cVar2, Collections.singletonMap(d8.q.f14760e, new k9.k(size2))));
                if (!arrayListG02.isEmpty()) {
                    iVar = new h8.i(arrayListG02, i10);
                }
                hVar = iVar;
            }
        }
        return v9.e.q(v9.c.t(hVar), fVarJ, arrayList2);
    }

    public static io.sentry.d i(io.sentry.d dVar, Boolean bool, Double d4, Double d10) {
        if (dVar == null) {
            dVar = new io.sentry.d(u2.f17942i);
        }
        if (dVar.f17207d == null) {
            Double d11 = dVar.f17206c;
            if (d11 != null) {
                d4 = d11;
            }
            Double dC = androidx.work.impl.t.c(bool, d10, d4);
            if (dVar.f17208e) {
                dVar.f17207d = dC;
            }
        }
        return dVar;
    }

    public static final f9.f j(v9.w wVar) {
        String str;
        h8.b bVarJ = wVar.getAnnotations().j(d8.p.f14749r);
        if (bVarJ == null) {
            return null;
        }
        Object objS0 = kotlin.collections.x.S0(bVarJ.a().values());
        k9.w wVar2 = objS0 instanceof k9.w ? (k9.w) objS0 : null;
        if (wVar2 != null && (str = (String) wVar2.f19530a) != null) {
            if (!f9.f.k(str)) {
                str = null;
            }
            if (str != null) {
                return f9.f.j(str);
            }
        }
        return null;
    }

    public static y8.a0 k(a.a aVar) {
        if (aVar instanceof e9.e) {
            e9.e eVar = (e9.e) aVar;
            return new y8.a0(eVar.f15071b.concat(eVar.f15072c));
        }
        if (!(aVar instanceof e9.d)) {
            throw new NoWhenBranchMatchedException();
        }
        e9.d dVar = (e9.d) aVar;
        return new y8.a0(androidx.compose.foundation.c.m('#', dVar.f15069b, dVar.f15070c));
    }

    public static final List l(v9.w wVar) {
        t(wVar);
        int iG = g(wVar);
        if (iG == 0) {
            return kotlin.collections.z.f19728i;
        }
        List listSubList = wVar.G0().subList(0, iG);
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(listSubList, 10));
        Iterator it = listSubList.iterator();
        while (it.hasNext()) {
            arrayList.add(((v9.r0) it.next()).getType());
        }
        return arrayList;
    }

    public static int m(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        int color = typedValue.resourceId != 0 ? context.getResources().getColor(typedValue.resourceId) : typedValue.data;
        ThreadLocal threadLocal = androidx.core.graphics.a.f2084a;
        if (Color.alpha(color) == 255) {
            double dB = androidx.core.graphics.a.b(Color.alpha(-1) < 255 ? androidx.core.graphics.a.c(-1, color) : -1) + 0.05d;
            double dB2 = androidx.core.graphics.a.b(color) + 0.05d;
            return Math.max(dB, dB2) / Math.min(dB, dB2) >= 3.0d ? -1 : -570425344;
        }
        throw new IllegalArgumentException("background can not be translucent: #" + Integer.toHexString(color));
    }

    public static final e8.j n(g8.h hVar) {
        e8.k kVarA;
        if (!(hVar instanceof g8.f) || !d8.k.J(hVar)) {
            return null;
        }
        int i10 = m9.d.f20302a;
        f9.e eVarG = i9.f.g(hVar);
        if (!eVarG.d() || eVarG.f15364a.isEmpty() || (kVarA = e8.l.f15063c.a(eVarG.g().e(), eVarG.f().d())) == null) {
            return null;
        }
        return kVarA.f15061a;
    }

    public static final v9.w o(v9.w wVar) {
        t(wVar);
        if (wVar.getAnnotations().j(d8.p.f14747p) == null) {
            return null;
        }
        return ((v9.r0) wVar.G0().get(g(wVar))).getType();
    }

    public static int p(ContextThemeWrapper contextThemeWrapper) {
        TypedValue typedValue = new TypedValue();
        return (!contextThemeWrapper.getTheme().resolveAttribute(R.attr.isLightTheme, typedValue, true) || typedValue.data == 0) ? m(contextThemeWrapper) == -570425344 ? R.style.Theme_MediaRouter_LightControlPanel : R.style.Theme_MediaRouter : m(contextThemeWrapper) == -570425344 ? R.style.Theme_MediaRouter_Light : R.style.Theme_MediaRouter_Light_DarkControlPanel;
    }

    public static int q(Context context, int i10) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i10, typedValue, true)) {
            return typedValue.resourceId;
        }
        return 0;
    }

    public static final List r(v9.w wVar) {
        t(wVar);
        List listG0 = wVar.G0();
        return listG0.subList(((!t(wVar) || wVar.getAnnotations().j(d8.p.f14747p) == null) ? 0 : 1) + g(wVar), listG0.size() - 1);
    }

    public static kotlin.reflect.t s(kotlin.reflect.q qVar) {
        return new kotlin.reflect.t(1, qVar);
    }

    public static final boolean t(v9.w wVar) {
        g8.h hVarE = wVar.I0().e();
        if (hVarE == null) {
            return false;
        }
        e8.j jVarN = n(hVarE);
        return kotlin.jvm.internal.p.a(jVarN, e8.f.f15055c) || kotlin.jvm.internal.p.a(jVarN, e8.i.f15058c);
    }

    public static io.sentry.p0 u(ILogger iLogger, String str, int i10, io.sentry.g1 g1Var) {
        q2 q2Var = q2.f17747i;
        try {
            Iterator it = ServiceLoader.load(io.sentry.profiling.a.class).iterator();
            io.sentry.profiling.a aVar = (io.sentry.profiling.a) (it.hasNext() ? it.next() : null);
            if (aVar != null) {
                iLogger.q(w5.DEBUG, "Loaded continuous profiler from provider: %s", aVar.getClass().getName());
                return aVar.a();
            }
            iLogger.q(w5.DEBUG, "No continuous profiler provider found, using NoOpContinuousProfiler", new Object[0]);
            return q2Var;
        } catch (Throwable th) {
            iLogger.l(w5.ERROR, "Failed to load continuous profiler provider, using NoOpContinuousProfiler", th);
            return q2Var;
        }
    }

    public static com.google.common.base.c0 v(com.google.common.base.c0 c0Var) {
        return ((c0Var instanceof com.google.common.base.e0) || (c0Var instanceof com.google.common.base.d0)) ? c0Var : c0Var instanceof Serializable ? new com.google.common.base.d0(c0Var) : new com.google.common.base.e0(c0Var);
    }

    public static String w(String str, String str2) {
        int length = str.length() - str2.length();
        if (length < 0 || length > 1) {
            throw new IllegalArgumentException("Invalid input received");
        }
        StringBuilder sb2 = new StringBuilder(str2.length() + str.length());
        for (int i10 = 0; i10 < str.length(); i10++) {
            sb2.append(str.charAt(i10));
            if (str2.length() > i10) {
                sb2.append(str2.charAt(i10));
            }
        }
        return sb2.toString();
    }

    public static final String z(f9.f fVar) {
        String strD = fVar.d();
        if (!h9.n.f15951a.contains(strD)) {
            int i10 = 0;
            while (true) {
                if (i10 < strD.length()) {
                    char cCharAt = strD.charAt(i10);
                    if (!Character.isLetterOrDigit(cCharAt) && cCharAt != '_') {
                        break;
                    }
                    i10++;
                } else if (strD.length() != 0 && Character.isJavaIdentifierStart(strD.codePointAt(0))) {
                    return fVar.d();
                }
            }
        }
        return "`".concat(fVar.d()) + '`';
    }

    public abstract boolean d(androidx.work.impl.utils.futures.i iVar, androidx.work.impl.utils.futures.d dVar, androidx.work.impl.utils.futures.d dVar2);

    public abstract boolean e(androidx.work.impl.utils.futures.i iVar, Object obj, Object obj2);

    public abstract boolean f(androidx.work.impl.utils.futures.i iVar, androidx.work.impl.utils.futures.h hVar, androidx.work.impl.utils.futures.h hVar2);

    public abstract void x(androidx.work.impl.utils.futures.h hVar, androidx.work.impl.utils.futures.h hVar2);

    public abstract void y(androidx.work.impl.utils.futures.h hVar, Thread thread);

    @Override // androidx.core.view.o2
    public void a() {
    }

    @Override // androidx.core.view.o2
    public void b() {
    }
}
