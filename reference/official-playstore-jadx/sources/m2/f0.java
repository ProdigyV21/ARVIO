package m2;

import a8.e2;
import a8.x1;
import a8.z1;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.ActionMode;
import android.widget.TextView;
import androidx.appcompat.widget.j3;
import androidx.appcompat.widget.o1;
import androidx.navigation.z0;
import com.arflix.tv.ui.theme.AnimationConstants;
import com.google.common.util.concurrent.r0;
import g8.c1;
import g8.e1;
import g8.g1;
import g8.p0;
import g8.s0;
import io.sentry.ILogger;
import io.sentry.i1;
import io.sentry.j5;
import io.sentry.v4;
import io.sentry.w5;
import io.sentry.y6;
import j8.v0;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.security.cert.X509Certificate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import v9.b1;

/* JADX INFO: loaded from: classes3.dex */
public abstract class f0 {
    public static boolean A(j5 j5Var, y6 y6Var) {
        i1 serializer = y6Var.getSerializer();
        ILogger logger = y6Var.getLogger();
        Charset charset = io.sentry.util.i.f17960a;
        long j10 = 0;
        if (j5Var != null) {
            try {
                io.sentry.util.h hVar = new io.sentry.util.h();
                serializer.a(hVar, j5Var);
                j10 = hVar.f17959i;
            } catch (Throwable th) {
                logger.l(w5.ERROR, "Could not calculate size of serializable", th);
            }
        }
        return j10 <= 1048576;
    }

    public static z1 B(g8.c cVar, r7.a aVar) {
        if (aVar != null) {
            return new z1(cVar, aVar);
        }
        throw new IllegalArgumentException("Argument for @NotNull parameter 'initializer' of kotlin/reflect/jvm/internal/ReflectProperties.lazySoft must not be null");
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x00e9, code lost:
    
        if (m9.d.g(r2).equals(m9.d.g(r0)) == false) goto L53;
     */
    /* JADX WARN: Removed duplicated region for block: B:33:0x009d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static y8.v C(g8.w r5, g8.e1 r6) {
        /*
            Method dump skipped, instruction units count: 257
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: m2.f0.C(g8.w, g8.e1):y8.v");
    }

    public static String F(X509Certificate x509Certificate) {
        if (androidx.compose.material3.d.B(x509Certificate)) {
            return "sha256/".concat(xb.a.a(v1.i.k(x509Certificate.getPublicKey().getEncoded()).d("SHA-256").f22773i, xb.a.f22714a));
        }
        throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
    }

    public static int J(long j10) {
        if (j10 > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (j10 < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        return (int) j10;
    }

    public static void K(v4 v4Var, fi.iki.elonen.f fVar, ILogger iLogger) {
        if (v4Var.f17984i != null) {
            fVar.S("event_id");
            fVar.b0(iLogger, v4Var.f17984i);
        }
        fVar.S("contexts");
        fVar.b0(iLogger, v4Var.f17985l);
        if (v4Var.f17986m != null) {
            fVar.S("sdk");
            fVar.b0(iLogger, v4Var.f17986m);
        }
        if (v4Var.f17987n != null) {
            fVar.S("request");
            fVar.b0(iLogger, v4Var.f17987n);
        }
        AbstractMap abstractMap = v4Var.f17988o;
        if (abstractMap != null && !abstractMap.isEmpty()) {
            fVar.S("tags");
            fVar.b0(iLogger, v4Var.f17988o);
        }
        if (v4Var.f17989p != null) {
            fVar.S("release");
            fVar.value(v4Var.f17989p);
        }
        if (v4Var.f17990q != null) {
            fVar.S("environment");
            fVar.value(v4Var.f17990q);
        }
        if (v4Var.f17991r != null) {
            fVar.S("platform");
            fVar.value(v4Var.f17991r);
        }
        if (v4Var.f17992s != null) {
            fVar.S("user");
            fVar.b0(iLogger, v4Var.f17992s);
        }
        if (v4Var.f17994u != null) {
            fVar.S("server_name");
            fVar.value(v4Var.f17994u);
        }
        if (v4Var.f17995v != null) {
            fVar.S("dist");
            fVar.value(v4Var.f17995v);
        }
        List list = v4Var.f17996w;
        if (list != null && !list.isEmpty()) {
            fVar.S("breadcrumbs");
            fVar.b0(iLogger, v4Var.f17996w);
        }
        if (v4Var.x != null) {
            fVar.S("debug_meta");
            fVar.b0(iLogger, v4Var.x);
        }
        AbstractMap abstractMap2 = v4Var.f17997y;
        if (abstractMap2 == null || abstractMap2.isEmpty()) {
            return;
        }
        fVar.S("extra");
        fVar.b0(iLogger, v4Var.f17997y);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x008c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final ya.h L(fb.b r5, kotlin.reflect.q r6, boolean r7) {
        /*
            kotlin.reflect.d r0 = cb.b1.f(r6)
            boolean r1 = r6.j()
            java.util.List r2 = r6.g()
            java.util.ArrayList r3 = new java.util.ArrayList
            r4 = 10
            int r4 = kotlin.collections.s.U(r2, r4)
            r3.<init>(r4)
            java.util.Iterator r2 = r2.iterator()
        L1b:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L47
            java.lang.Object r4 = r2.next()
            kotlin.reflect.t r4 = (kotlin.reflect.t) r4
            kotlin.reflect.q r4 = r4.f19912b
            if (r4 == 0) goto L2f
            r3.add(r4)
            goto L1b
        L2f:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r7 = "Star projections in type arguments are not allowed, but had "
            r5.<init>(r7)
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r5 = r5.toString()
            r6.<init>(r5)
            throw r6
        L47:
            boolean r6 = r3.isEmpty()
            r2 = 0
            if (r6 == 0) goto L62
            if (r1 != 0) goto L5b
            cb.m1 r6 = ya.w.f23002a
            ya.h r6 = r6.b(r0)
            if (r6 == 0) goto L59
            goto L83
        L59:
            r6 = r2
            goto L83
        L5b:
            cb.m1 r6 = ya.w.f23003b
            ya.h r6 = r6.b(r0)
            goto L83
        L62:
            if (r1 != 0) goto L6b
            cb.a1 r6 = ya.w.f23004c
            java.lang.Object r6 = r6.a(r0, r3)
            goto L71
        L6b:
            cb.a1 r6 = ya.w.f23005d
            java.lang.Object r6 = r6.a(r0, r3)
        L71:
            if (r7 == 0) goto L7b
            boolean r4 = r6 instanceof x6.c0
            if (r4 == 0) goto L78
            r6 = r2
        L78:
            ya.h r6 = (ya.h) r6
            goto L83
        L7b:
            java.lang.Throwable r4 = x6.d0.a(r6)
            if (r4 != 0) goto Lab
            ya.h r6 = (ya.h) r6
        L83:
            if (r6 == 0) goto L86
            return r6
        L86:
            boolean r6 = r3.isEmpty()
            if (r6 == 0) goto L8e
        L8c:
            r5 = r2
            goto La2
        L8e:
            java.util.ArrayList r5 = kotlin.reflect.b0.D(r5, r3, r7)
            if (r5 != 0) goto L95
            goto Lab
        L95:
            w9.h r6 = new w9.h
            r7 = 2
            r6.<init>(r7, r3)
            ya.h r5 = kotlin.reflect.b0.z(r0, r5, r6)
            if (r5 != 0) goto La2
            goto L8c
        La2:
            if (r5 == 0) goto Lab
            if (r1 == 0) goto Laa
            ya.h r5 = a.a.Q(r5)
        Laa:
            return r5
        Lab:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: m2.f0.L(fb.b, kotlin.reflect.q, boolean):ya.h");
    }

    public static void M(TextView textView, int i10) {
        if (i10 < 0) {
            throw new IllegalArgumentException();
        }
        if (Build.VERSION.SDK_INT >= 28) {
            u0.o.d(textView, i10);
            return;
        }
        Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        int i11 = textView.getIncludeFontPadding() ? fontMetricsInt.top : fontMetricsInt.ascent;
        if (i10 > Math.abs(i11)) {
            textView.setPadding(textView.getPaddingLeft(), i10 + i11, textView.getPaddingRight(), textView.getPaddingBottom());
        }
    }

    public static void N(TextView textView, int i10) {
        if (i10 < 0) {
            throw new IllegalArgumentException();
        }
        Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        int i11 = textView.getIncludeFontPadding() ? fontMetricsInt.bottom : fontMetricsInt.descent;
        if (i10 > Math.abs(i11)) {
            textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), i10 - i11);
        }
    }

    public static int[] O(Collection collection) {
        if (collection instanceof com.google.common.primitives.c) {
            com.google.common.primitives.c cVar = (com.google.common.primitives.c) collection;
            return Arrays.copyOfRange(cVar.f14180i, cVar.f14181l, cVar.f14182m);
        }
        Object[] array = collection.toArray();
        int length = array.length;
        int[] iArr = new int[length];
        for (int i10 = 0; i10 < length; i10++) {
            Object obj = array[i10];
            obj.getClass();
            iArr[i10] = ((Number) obj).intValue();
        }
        return iArr;
    }

    public static final Class P(g8.k kVar) {
        if (!(kVar instanceof g8.f) || !i9.h.b(kVar)) {
            return null;
        }
        g8.f fVar = (g8.f) kVar;
        Class clsI = e2.i(fVar);
        if (clsI != null) {
            return clsI;
        }
        throw new x1("Class object for the class " + fVar.getName() + " cannot be found (classId=" + m9.d.f((g8.h) kVar) + ')');
    }

    public static final Class Q(v9.w wVar) {
        v9.b0 b0VarG;
        Class clsP = P(wVar.I0().e());
        if (clsP == null) {
            return null;
        }
        if (b1.e(wVar) && ((b0VarG = i9.h.g(wVar)) == null || b1.e(b0VarG) || d8.k.G(b0VarG))) {
            return null;
        }
        return clsP;
    }

    public static void R(j5 j5Var, y6 y6Var) {
        ArrayList arrayListD = j5Var.d();
        if (arrayListD != null) {
            Iterator it = arrayListD.iterator();
            while (it.hasNext()) {
                io.sentry.protocol.b0 b0Var = ((io.sentry.protocol.u) it.next()).f17704o;
                if (b0Var != null) {
                    S(b0Var, j5Var, y6Var, "Truncated exception stack frames of event %s");
                }
            }
        }
        ArrayList arrayListE = j5Var.e();
        if (arrayListE != null) {
            Iterator it2 = arrayListE.iterator();
            while (it2.hasNext()) {
                io.sentry.protocol.b0 b0Var2 = ((io.sentry.protocol.d0) it2.next()).f17564s;
                if (b0Var2 != null) {
                    S(b0Var2, j5Var, y6Var, "Truncated thread stack frames for event %s");
                }
            }
        }
    }

    public static void S(io.sentry.protocol.b0 b0Var, j5 j5Var, y6 y6Var, String str) {
        List list = b0Var.f17545i;
        if (list == null || list.size() <= 500) {
            return;
        }
        ArrayList arrayList = new ArrayList(500);
        arrayList.addAll(list.subList(0, AnimationConstants.DURATION_IMAGE_CROSSFADE));
        arrayList.addAll(list.subList(list.size() - AnimationConstants.DURATION_IMAGE_CROSSFADE, list.size()));
        b0Var.f17545i = arrayList;
        y6Var.getLogger().q(w5.DEBUG, str, j5Var.f17984i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:4:0x000b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Integer T(java.lang.String r19) {
        /*
            r0 = r19
            r0.getClass()
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L10
        Lb:
            r0 = 0
            r16 = 0
            goto L86
        L10:
            r1 = 0
            char r3 = r0.charAt(r1)
            r4 = 45
            if (r3 != r4) goto L1a
            r1 = 1
        L1a:
            int r3 = r0.length()
            if (r1 != r3) goto L21
            goto Lb
        L21:
            int r3 = r1 + 1
            char r4 = r0.charAt(r1)
            r5 = -1
            r6 = 128(0x80, float:1.8E-43)
            if (r4 >= r6) goto L31
            byte[] r7 = com.google.common.primitives.d.f14183a
            r4 = r7[r4]
            goto L34
        L31:
            byte[] r4 = com.google.common.primitives.d.f14183a
            r4 = r5
        L34:
            if (r4 < 0) goto L60
            r7 = 10
            if (r4 < r7) goto L3b
            goto L60
        L3b:
            int r4 = -r4
            long r8 = (long) r4
            long r10 = (long) r7
            r12 = -9223372036854775808
            long r14 = r12 / r10
        L42:
            int r4 = r0.length()
            if (r3 >= r4) goto L73
            int r4 = r3 + 1
            char r3 = r0.charAt(r3)
            if (r3 >= r6) goto L55
            byte[] r16 = com.google.common.primitives.d.f14183a
            r3 = r16[r3]
            goto L58
        L55:
            byte[] r3 = com.google.common.primitives.d.f14183a
            r3 = r5
        L58:
            if (r3 < 0) goto L60
            if (r3 >= r7) goto L60
            int r16 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            if (r16 >= 0) goto L63
        L60:
            r16 = 0
            goto L6d
        L63:
            long r8 = r8 * r10
            r16 = 0
            long r2 = (long) r3
            long r17 = r2 + r12
            int r17 = (r8 > r17 ? 1 : (r8 == r17 ? 0 : -1))
            if (r17 >= 0) goto L70
        L6d:
            r0 = r16
            goto L86
        L70:
            long r8 = r8 - r2
            r3 = r4
            goto L42
        L73:
            r16 = 0
            if (r1 == 0) goto L7c
            java.lang.Long r0 = java.lang.Long.valueOf(r8)
            goto L86
        L7c:
            int r0 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r0 != 0) goto L81
            goto L6d
        L81:
            long r0 = -r8
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
        L86:
            if (r0 == 0) goto L9f
            long r1 = r0.longValue()
            int r3 = r0.intValue()
            long r3 = (long) r3
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L96
            goto L9f
        L96:
            int r0 = r0.intValue()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            return r0
        L9f:
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: m2.f0.T(java.lang.String):java.lang.Integer");
    }

    public static final double U(long j10) {
        return ((j10 >>> 11) * ((double) 2048)) + (j10 & 2047);
    }

    public static final String V(int i10, long j10) {
        if (j10 >= 0) {
            r0.c(i10);
            return Long.toString(j10, i10);
        }
        long j11 = i10;
        long j12 = ((j10 >>> 1) / j11) << 1;
        long j13 = j10 - (j12 * j11);
        if (j13 >= j11) {
            j13 -= j11;
            j12++;
        }
        r0.c(i10);
        String string = Long.toString(j12, i10);
        r0.c(i10);
        return string.concat(Long.toString(j13, i10));
    }

    public static ActionMode.Callback W(ActionMode.Callback callback) {
        return (!(callback instanceof u0.q) || Build.VERSION.SDK_INT < 26) ? callback : ((u0.q) callback).f22036a;
    }

    public static ActionMode.Callback X(ActionMode.Callback callback, TextView textView) {
        int i10 = Build.VERSION.SDK_INT;
        return (i10 < 26 || i10 > 27 || (callback instanceof u0.q) || callback == null) ? callback : new u0.q(callback, textView);
    }

    public static ma.f a(int i10, int i11, int i12) {
        if ((i12 & 2) != 0) {
            i11 = 1;
        }
        if (i10 == -2) {
            if (i11 != 1) {
                return new ma.q(1, i11);
            }
            ma.j.f20343f.getClass();
            return new ma.f(ma.i.f20342b);
        }
        if (i10 != -1) {
            return i10 != 0 ? i10 != Integer.MAX_VALUE ? i11 == 1 ? new ma.f(i10) : new ma.q(i10, i11) : new ma.f(Integer.MAX_VALUE) : i11 == 1 ? new ma.f(0) : new ma.q(1, i11);
        }
        if (i11 == 1) {
            return new ma.q(1, 2);
        }
        throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow");
    }

    public static List b(int... iArr) {
        return iArr.length == 0 ? Collections.EMPTY_LIST : new com.google.common.primitives.c(0, iArr.length, iArr);
    }

    public static int f(long j10) {
        int i10 = (int) j10;
        ac.b.g(j10, "Out of range: %s", ((long) i10) == j10);
        return i10;
    }

    public static final Object g(Object obj, g8.c cVar) {
        v9.w wVarP;
        Class clsQ;
        return (((cVar instanceof p0) && i9.h.d((g1) cVar)) || (wVarP = p(cVar)) == null || (clsQ = Q(wVarP)) == null) ? obj : q(clsQ, cVar).invoke(obj, null);
    }

    public static int h(int i10, int i11) {
        ac.b.h("min (%s) must be less than or equal to max (%s)", i11, 1073741823, i11 <= 1073741823);
        return Math.min(Math.max(i10, i11), 1073741823);
    }

    public static final long i(long j10, kotlin.time.e eVar) {
        long j11;
        int iOrdinal = eVar.ordinal();
        if (iOrdinal == 2) {
            j11 = 1;
        } else if (iOrdinal == 3) {
            j11 = 1000;
        } else if (iOrdinal == 4) {
            j11 = 60000;
        } else if (iOrdinal == 5) {
            j11 = 3600000;
        } else {
            if (iOrdinal != 6) {
                throw new IllegalStateException(("Wrong unit for millisMultiplier: " + eVar).toString());
            }
            j11 = 86400000;
        }
        if (j10 == 0) {
            return 0L;
        }
        if (j10 == 1) {
            if (j11 <= 4611686018427387903L) {
                return j11;
            }
        } else if (j11 != 1) {
            int iNumberOfLeadingZeros = (128 - Long.numberOfLeadingZeros(j10)) - Long.numberOfLeadingZeros(j11);
            if (iNumberOfLeadingZeros < 63) {
                return j10 * j11;
            }
            if (iNumberOfLeadingZeros <= 63) {
                long j12 = j10 * j11;
                if (j12 <= 4611686018427387903L) {
                    return j12;
                }
            }
        } else if (j10 <= 4611686018427387903L) {
            return j10;
        }
        return 4611686018427387903L;
    }

    public static final ArrayList j(ArrayList arrayList, List list, g8.w wVar) {
        v9.w wVarF;
        arrayList.size();
        list.size();
        ArrayList<x6.x> arrayListI1 = kotlin.collections.x.i1(arrayList, list);
        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(arrayListI1, 10));
        for (x6.x xVar : arrayListI1) {
            v9.w wVar2 = (v9.w) xVar.f22608i;
            e1 e1Var = (e1) xVar.f22609l;
            int index = e1Var.getIndex();
            h8.h annotations = e1Var.getAnnotations();
            f9.f name = e1Var.getName();
            boolean zW0 = e1Var.w0();
            boolean zM0 = e1Var.m0();
            boolean zL0 = e1Var.l0();
            if (e1Var.q0() != null) {
                int i10 = m9.d.f20302a;
                wVarF = i9.f.d(wVar).j().f(wVar2);
            } else {
                wVarF = null;
            }
            arrayList2.add(new v0(wVar, null, index, annotations, name, wVar2, zW0, zM0, zL0, wVarF, e1Var.f()));
        }
        return arrayList2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x009a, code lost:
    
        if ((r0 != null ? i9.h.e(r0) : false) == true) goto L44;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0086  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final b8.e k(b8.e r3, g8.c r4, boolean r5) {
        /*
            boolean r0 = i9.h.a(r4)
            if (r0 != 0) goto L9e
            java.util.List r0 = r4.s0()
            boolean r1 = r0.isEmpty()
            r2 = 0
            if (r1 == 0) goto L12
            goto L3a
        L12:
            java.util.Iterator r0 = r0.iterator()
        L16:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L3a
            java.lang.Object r1 = r0.next()
            g8.s0 r1 = (g8.s0) r1
            v9.w r1 = r1.getType()
            v9.o0 r1 = r1.I0()
            g8.h r1 = r1.e()
            if (r1 == 0) goto L35
            boolean r1 = i9.h.e(r1)
            goto L36
        L35:
            r1 = r2
        L36:
            if (r1 == 0) goto L16
            goto L9e
        L3a:
            java.util.List r0 = r4.e()
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L45
            goto L6c
        L45:
            java.util.Iterator r0 = r0.iterator()
        L49:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L6c
            java.lang.Object r1 = r0.next()
            g8.e1 r1 = (g8.e1) r1
            v9.w r1 = r1.getType()
            v9.o0 r1 = r1.I0()
            g8.h r1 = r1.e()
            if (r1 == 0) goto L68
            boolean r1 = i9.h.e(r1)
            goto L69
        L68:
            r1 = r2
        L69:
            if (r1 == 0) goto L49
            goto L9e
        L6c:
            v9.w r0 = r4.getReturnType()
            r1 = 1
            if (r0 == 0) goto L86
            v9.o0 r0 = r0.I0()
            g8.h r0 = r0.e()
            if (r0 == 0) goto L82
            boolean r0 = i9.h.b(r0)
            goto L83
        L82:
            r0 = r2
        L83:
            if (r0 != r1) goto L86
            goto L9e
        L86:
            v9.w r0 = p(r4)
            if (r0 == 0) goto L9d
            v9.o0 r0 = r0.I0()
            g8.h r0 = r0.e()
            if (r0 == 0) goto L9a
            boolean r2 = i9.h.e(r0)
        L9a:
            if (r2 != r1) goto L9d
            goto L9e
        L9d:
            return r3
        L9e:
            b8.a0 r0 = new b8.a0
            r0.<init>(r3, r4, r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m2.f0.k(b8.e, g8.c, boolean):b8.e");
    }

    public static boolean m(g8.a aVar, g8.a aVar2) {
        if (!(aVar2 instanceof r8.g) || !(aVar instanceof g8.w)) {
            return false;
        }
        r8.g gVar = (r8.g) aVar2;
        gVar.e().size();
        g8.w wVar = (g8.w) aVar;
        wVar.e().size();
        for (x6.x xVar : kotlin.collections.x.i1(gVar.a().e(), wVar.a().e())) {
            if ((C((g8.w) aVar2, (e1) xVar.f22608i) instanceof y8.u) != (C(wVar, (e1) xVar.f22609l) instanceof y8.u)) {
                return true;
            }
        }
        return false;
    }

    public static int n(byte b10, byte b11, byte b12, byte b13) {
        return (b10 << 24) | ((b11 & 255) << 16) | ((b12 & 255) << 8) | (b13 & 255);
    }

    public static Drawable o(Context context, int i10) {
        return j3.c().e(context, i10);
    }

    public static final v9.w p(g8.c cVar) {
        s0 s0VarM = cVar.M();
        s0 s0VarI = cVar.I();
        if (s0VarM != null) {
            return s0VarM.getType();
        }
        if (s0VarI != null) {
            if (cVar instanceof g8.j) {
                return s0VarI.getType();
            }
            g8.k kVarD = cVar.d();
            g8.f fVar = kVarD instanceof g8.f ? (g8.f) kVarD : null;
            if (fVar != null) {
                return fVar.m();
            }
        }
        return null;
    }

    public static final Method q(Class cls, g8.c cVar) {
        try {
            return cls.getDeclaredMethod("unbox-impl", null);
        } catch (NoSuchMethodException unused) {
            throw new x1("No unbox method found in inline class: " + cls + " (calling " + cVar + ')');
        }
    }

    public static final ArrayList r(v9.b0 b0Var) {
        ArrayList arrayListS = s(v9.c.b(b0Var));
        if (arrayListS == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(arrayListS, 10));
        Iterator it = arrayListS.iterator();
        while (it.hasNext()) {
            arrayList.add("unbox-impl-" + ((String) it.next()));
        }
        Class clsI = e2.i((g8.f) b0Var.I0().e());
        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(arrayList, 10));
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList2.add(clsI.getDeclaredMethod((String) it2.next(), null));
        }
        return arrayList2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.lang.Iterable] */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.util.ArrayList] */
    public static final ArrayList s(v9.b0 b0Var) {
        ?? SingletonList;
        if (!i9.h.f(b0Var)) {
            return null;
        }
        g8.f fVar = (g8.f) b0Var.I0().e();
        int i10 = m9.d.f20302a;
        c1 c1VarS = fVar.S();
        ArrayList<x6.x> arrayList = (c1VarS instanceof g8.c0 ? (g8.c0) c1VarS : null).f15495a;
        ArrayList arrayList2 = new ArrayList();
        for (x6.x xVar : arrayList) {
            f9.f fVar2 = (f9.f) xVar.f22608i;
            ArrayList arrayListS = s((v9.b0) xVar.f22609l);
            if (arrayListS != null) {
                SingletonList = new ArrayList(kotlin.collections.s.U(arrayListS, 10));
                Iterator it = arrayListS.iterator();
                while (it.hasNext()) {
                    SingletonList.add(fVar2.f() + '-' + ((String) it.next()));
                }
            } else {
                SingletonList = Collections.singletonList(fVar2.f());
            }
            kotlin.collections.x.b0(arrayList2, SingletonList);
        }
        return arrayList2;
    }

    public static String t(Class cls) {
        LinkedHashMap linkedHashMap = androidx.navigation.b1.f4528b;
        String str = (String) linkedHashMap.get(cls);
        if (str != null) {
            return str;
        }
        z0 z0Var = (z0) cls.getAnnotation(z0.class);
        String strValue = z0Var != null ? z0Var.value() : null;
        if (strValue == null || strValue.length() <= 0) {
            throw new IllegalArgumentException("No @Navigator.Name annotation found for ".concat(cls.getSimpleName()).toString());
        }
        linkedHashMap.put(cls, strValue);
        return strValue;
    }

    public static Intent u(Context context, ComponentName componentName) throws PackageManager.NameNotFoundException {
        String strW = w(context, componentName);
        if (strW == null) {
            return null;
        }
        ComponentName componentName2 = new ComponentName(componentName.getPackageName(), strW);
        return w(context, componentName2) == null ? Intent.makeMainActivity(componentName2) : new Intent().setComponent(componentName2);
    }

    public static Intent v(androidx.appcompat.app.t tVar) {
        Intent parentActivityIntent = tVar.getParentActivityIntent();
        if (parentActivityIntent != null) {
            return parentActivityIntent;
        }
        try {
            String strW = w(tVar, tVar.getComponentName());
            if (strW == null) {
                return null;
            }
            ComponentName componentName = new ComponentName(tVar, strW);
            try {
                return w(tVar, componentName) == null ? Intent.makeMainActivity(componentName) : new Intent().setComponent(componentName);
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e("NavUtils", "getParentActivityIntent: bad parentActivityName '" + strW + "' in manifest");
                return null;
            }
        } catch (PackageManager.NameNotFoundException e5) {
            throw new IllegalArgumentException(e5);
        }
    }

    public static String w(Context context, ComponentName componentName) throws PackageManager.NameNotFoundException {
        String string;
        PackageManager packageManager = context.getPackageManager();
        int i10 = Build.VERSION.SDK_INT;
        ActivityInfo activityInfo = packageManager.getActivityInfo(componentName, i10 >= 29 ? 269222528 : i10 >= 24 ? 787072 : 640);
        String str = activityInfo.parentActivityName;
        if (str != null) {
            return str;
        }
        Bundle bundle = activityInfo.metaData;
        if (bundle == null || (string = bundle.getString("android.support.PARENT_ACTIVITY")) == null) {
            return null;
        }
        if (string.charAt(0) != '.') {
            return string;
        }
        return context.getPackageName() + string;
    }

    public static final t8.c0 x(g8.f fVar) {
        g8.f fVar2;
        g8.h hVarE;
        int i10 = m9.d.f20302a;
        Iterator it = fVar.m().I0().d().iterator();
        while (true) {
            if (!it.hasNext()) {
                fVar2 = null;
                break;
            }
            v9.w wVar = (v9.w) it.next();
            if (!d8.k.x(wVar)) {
                hVarE = wVar.I0().e();
                if (i9.f.n(hVarE, 1) || i9.f.n(hVarE, 3)) {
                    break;
                }
            }
        }
        fVar2 = (g8.f) hVarE;
        if (fVar2 == null) {
            return null;
        }
        p9.n nVarJ0 = fVar2.j0();
        t8.c0 c0Var = nVarJ0 instanceof t8.c0 ? (t8.c0) nVarJ0 : null;
        return c0Var == null ? x(fVar2) : c0Var;
    }

    public static androidx.core.text.f y(o1 o1Var) {
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 28) {
            return new androidx.core.text.f(u0.o.c(o1Var));
        }
        TextPaint textPaint = new TextPaint(o1Var.getPaint());
        TextDirectionHeuristic textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_LTR;
        int iA = u0.m.a(o1Var);
        int iD = u0.m.d(o1Var);
        if (o1Var.getTransformationMethod() instanceof PasswordTransformationMethod) {
            textDirectionHeuristic = TextDirectionHeuristics.LTR;
        } else if (i10 < 28 || (o1Var.getInputType() & 15) != 3) {
            boolean z = o1Var.getLayoutDirection() == 1;
            switch (o1Var.getTextDirection()) {
                case 2:
                    textDirectionHeuristic = TextDirectionHeuristics.ANYRTL_LTR;
                    break;
                case 3:
                    textDirectionHeuristic = TextDirectionHeuristics.LTR;
                    break;
                case 4:
                    textDirectionHeuristic = TextDirectionHeuristics.RTL;
                    break;
                case 5:
                    textDirectionHeuristic = TextDirectionHeuristics.LOCALE;
                    break;
                case 6:
                    break;
                case 7:
                    textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_RTL;
                    break;
                default:
                    if (z) {
                        textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_RTL;
                    }
                    break;
            }
        } else {
            byte directionality = Character.getDirectionality(u0.o.b(u0.n.a(o1Var.getTextLocale()))[0].codePointAt(0));
            textDirectionHeuristic = (directionality == 1 || directionality == 2) ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR;
        }
        return new androidx.core.text.f(textPaint, textDirectionHeuristic, iA, iD);
    }

    public static int z(int[] iArr, int i10, int i11, int i12) {
        while (i11 < i12) {
            if (iArr[i11] == i10) {
                return i11;
            }
            i11++;
        }
        return -1;
    }

    public abstract void D(Throwable th);

    public abstract void E(androidx.emoji2.text.e0 e0Var);

    public abstract void G(String str);

    public abstract void H(e0.f fVar, e0.f fVar2);

    public abstract void I(e0.f fVar, Thread thread);

    public abstract boolean c(e0.g gVar, e0.c cVar, e0.c cVar2);

    public abstract boolean d(e0.g gVar, Object obj, Object obj2);

    public abstract boolean e(e0.g gVar, e0.f fVar, e0.f fVar2);

    public boolean l(com.caverock.androidsvg.o1 o1Var) {
        return true;
    }
}
