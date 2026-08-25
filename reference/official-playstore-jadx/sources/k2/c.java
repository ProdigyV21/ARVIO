package k2;

import a8.g0;
import a9.f2;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import androidx.appcompat.widget.x4;
import androidx.emoji2.text.e0;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.session.legacy.MediaSessionCompat;
import androidx.versionedparcelable.ParcelImpl;
import com.google.common.util.concurrent.r0;
import d8.k;
import db.j;
import g8.b1;
import gb.a0;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;
import kotlin.collections.b0;
import kotlin.collections.r;
import kotlin.collections.s;
import kotlin.jvm.internal.l0;
import kotlin.text.o;
import r7.p;
import v9.k0;
import v9.t;
import v9.v0;
import x6.c0;
import x6.i0;
import x6.m0;
import x6.o0;
import x6.t0;
import x6.x;
import xb.m;

/* JADX INFO: loaded from: classes3.dex */
public abstract class c implements tc.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static e0 f19461a;

    public static long A(long j10, long j11) {
        int iNumberOfLeadingZeros = Long.numberOfLeadingZeros(~j11) + Long.numberOfLeadingZeros(j11) + Long.numberOfLeadingZeros(~j10) + Long.numberOfLeadingZeros(j10);
        if (iNumberOfLeadingZeros > 65) {
            return j10 * j11;
        }
        long j12 = ((j10 ^ j11) >>> 63) + Long.MAX_VALUE;
        if (!((iNumberOfLeadingZeros < 64) | ((j11 == Long.MIN_VALUE) & (j10 < 0)))) {
            long j13 = j10 * j11;
            if (j10 == 0 || j13 / j10 == j11) {
                return j13;
            }
        }
        return j12;
    }

    public static void F(p pVar, ka.a aVar, ka.a aVar2) {
        try {
            pa.a.g(t7.a.A(t7.a.m(aVar, aVar2, pVar)), t0.f22605a);
        } catch (Throwable th) {
            aVar2.resumeWith(new c0(th));
            throw th;
        }
    }

    public static final void G(Object obj) {
        if (obj instanceof c0) {
            throw ((c0) obj).f22576i;
        }
    }

    public static final m0 H(String str) {
        int i10;
        r0.c(10);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        int i11 = 0;
        char cCharAt = str.charAt(0);
        if (kotlin.jvm.internal.p.c(cCharAt, 48) < 0) {
            i10 = 1;
            if (length == 1 || cCharAt != '+') {
                return null;
            }
        } else {
            i10 = 0;
        }
        int i12 = 119304647;
        while (i10 < length) {
            int iDigit = Character.digit((int) str.charAt(i10), 10);
            if (iDigit < 0) {
                return null;
            }
            int i13 = i11 ^ Integer.MIN_VALUE;
            if (Integer.compare(i13, i12 ^ Integer.MIN_VALUE) > 0) {
                if (i12 != 119304647) {
                    return null;
                }
                i12 = (int) ((((long) (-1)) & 4294967295L) / (4294967295L & ((long) 10)));
                if (Integer.compare(i13, i12 ^ Integer.MIN_VALUE) > 0) {
                    return null;
                }
            }
            int i14 = i11 * 10;
            int i15 = iDigit + i14;
            if (Integer.compare(i15 ^ Integer.MIN_VALUE, i14 ^ Integer.MIN_VALUE) < 0) {
                return null;
            }
            i10++;
            i11 = i15;
        }
        return new m0(i11);
    }

    public static final o0 I(String str) {
        int i10;
        long j10;
        int i11 = 10;
        r0.c(10);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        char cCharAt = str.charAt(0);
        int i12 = 1;
        if (kotlin.jvm.internal.p.c(cCharAt, 48) >= 0) {
            i10 = 0;
        } else {
            if (length == 1 || cCharAt != '+') {
                return null;
            }
            i10 = 1;
        }
        long j11 = 10;
        long j12 = 0;
        long j13 = 512409557603043100L;
        while (i10 < length) {
            int iDigit = Character.digit((int) str.charAt(i10), i11);
            if (iDigit < 0) {
                return null;
            }
            int i13 = length;
            long j14 = j12 ^ Long.MIN_VALUE;
            int i14 = i10;
            if (Long.compare(j14, j13 ^ Long.MIN_VALUE) <= 0) {
                j10 = j11;
            } else {
                if (j13 != 512409557603043100L) {
                    return null;
                }
                if (j11 >= 0) {
                    long j15 = (Long.MAX_VALUE / j11) << i12;
                    j10 = j11;
                    j13 = j15 + ((long) ((((-1) - (j15 * j11)) ^ Long.MIN_VALUE) >= (j11 ^ Long.MIN_VALUE) ? i12 : 0));
                } else if (Long.MAX_VALUE < (j11 ^ Long.MIN_VALUE)) {
                    j10 = j11;
                    j13 = 0;
                } else {
                    j13 = 1;
                    j10 = j11;
                }
                if (Long.compare(j14, j13 ^ Long.MIN_VALUE) > 0) {
                    return null;
                }
            }
            long j16 = j12 * j10;
            long j17 = (((long) iDigit) & 4294967295L) + j16;
            if (Long.compare(j17 ^ Long.MIN_VALUE, j16 ^ Long.MIN_VALUE) < 0) {
                return null;
            }
            i10 = i14 + 1;
            j12 = j17;
            length = i13;
            j11 = j10;
            i11 = 10;
            i12 = 1;
        }
        return new o0(j12);
    }

    public static Set J(a0 a0Var) {
        int size = a0Var.size();
        TreeSet treeSet = null;
        for (int i10 = 0; i10 < size; i10++) {
            if ("Vary".equalsIgnoreCase(a0Var.b(i10))) {
                String strE = a0Var.e(i10);
                if (treeSet == null) {
                    treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
                }
                Iterator it = o.x0(strE, new char[]{','}, 6).iterator();
                while (it.hasNext()) {
                    treeSet.add(o.L0((String) it.next()).toString());
                }
            }
        }
        return treeSet == null ? b0.f19686i : treeSet;
    }

    public static v0 K(v0 v0Var) {
        if (!(v0Var instanceof t)) {
            return new j9.d(v0Var, 0);
        }
        t tVar = (t) v0Var;
        b1[] b1VarArr = tVar.f22247b;
        ArrayList<x> arrayListQ0 = r.q0(tVar.f22248c, b1VarArr);
        ArrayList arrayList = new ArrayList(s.U(arrayListQ0, 10));
        for (x xVar : arrayListQ0) {
            arrayList.add(g((v9.r0) xVar.f22608i, (b1) xVar.f22609l));
        }
        return new t(b1VarArr, (v9.r0[]) arrayList.toArray(new v9.r0[0]), true);
    }

    public static final void a(bb.e eVar) {
        if ((eVar instanceof db.s ? (db.s) eVar : null) != null) {
            return;
        }
        throw new IllegalStateException(g8.b.h(l0.f19747a, eVar.getClass(), new StringBuilder("This serializer can be used only with Json format.Expected Encoder to be JsonEncoder, got ")));
    }

    public static final j b(bb.d dVar) {
        j jVar = dVar instanceof j ? (j) dVar : null;
        if (jVar != null) {
            return jVar;
        }
        throw new IllegalStateException(g8.b.h(l0.f19747a, dVar.getClass(), new StringBuilder("This serializer can be used only with Json format.Expected Decoder to be JsonDecoder, got ")));
    }

    public static long d(long j10, long j11) {
        long j12 = j10 + j11;
        if (((j10 ^ j11) < 0) || ((j10 ^ j12) >= 0)) {
            return j12;
        }
        throw new ArithmeticException(a0.c.j(j11, ")", androidx.compose.material3.d.r(j10, "overflow: checkedAdd(", ", ")));
    }

    public static c9.g e(f2 f2Var) {
        return f2Var.f443l.size() == 0 ? c9.g.f7455b : new c9.g(f2Var.f443l);
    }

    public static final Object f(Class cls, Map map, List list) {
        i0 i0Var = new i0(new g0(map, 13));
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new b8.b(cls, map, new i0(new a8.l0(cls, map, 6)), i0Var, list));
    }

    public static final v9.r0 g(v9.r0 r0Var, b1 b1Var) {
        if (b1Var == null || r0Var.c() == 1) {
            return r0Var;
        }
        if (b1Var.y() == r0Var.c()) {
            return r0Var.b() ? new v9.t0(1, new v9.x(u9.p.f22089e, new j8.t0(r0Var, 1))) : new v9.t0(r0Var.getType());
        }
        j9.c cVar = new j9.c(r0Var);
        k0.f22220l.getClass();
        return new v9.t0(1, new j9.a(r0Var, cVar, false, k0.f22221m));
    }

    public static final c0 h(Throwable th) {
        return new c0(th);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static long i(long j10, long j11, RoundingMode roundingMode) {
        roundingMode.getClass();
        long j12 = j10 / j11;
        long j13 = j10 - (j11 * j12);
        if (j13 == 0) {
            return j12;
        }
        int i10 = ((int) ((j10 ^ j11) >> 63)) | 1;
        switch (com.google.common.math.f.f14166a[roundingMode.ordinal()]) {
            case 1:
                kotlin.reflect.b0.h(j13 == 0);
                return j12;
            case 2:
                return j12;
            case 3:
                if (i10 >= 0) {
                    return j12;
                }
                return j12 + ((long) i10);
            case 4:
                return j12 + ((long) i10);
            case 5:
                if (i10 <= 0) {
                    return j12;
                }
                return j12 + ((long) i10);
            case 6:
            case 7:
            case 8:
                long jAbs = Math.abs(j13);
                long jAbs2 = jAbs - (Math.abs(j11) - jAbs);
                if (jAbs2 == 0) {
                    if (roundingMode != RoundingMode.HALF_UP && (roundingMode != RoundingMode.HALF_EVEN || (1 & j12) == 0)) {
                        return j12;
                    }
                } else if (jAbs2 <= 0) {
                    return j12;
                }
                return j12 + ((long) i10);
            default:
                throw new AssertionError();
        }
    }

    public static BigDecimal j(Double d4) {
        return BigDecimal.valueOf(d4.doubleValue()).setScale(6, RoundingMode.DOWN);
    }

    public static final ya.s k(cb.b bVar, bb.e eVar, Object obj) {
        ya.s sVarB = bVar.b(eVar, obj);
        if (sVarB != null) {
            return sVarB;
        }
        kotlin.reflect.d dVarB = l0.f19747a.b(obj.getClass());
        kotlin.reflect.d dVarC = bVar.c();
        String strR = dVarB.r();
        if (strR == null) {
            strR = String.valueOf(dVarB);
        }
        cb.b1.i(strR, dVarC);
        throw null;
    }

    public static long l(long j10, long j11) {
        kotlin.reflect.b0.g(j10, CmcdData.OBJECT_TYPE_AUDIO_ONLY);
        kotlin.reflect.b0.g(j11, "b");
        if (j10 == 0) {
            return j11;
        }
        if (j11 == 0) {
            return j10;
        }
        int iNumberOfTrailingZeros = Long.numberOfTrailingZeros(j10);
        long jNumberOfTrailingZeros = j10 >> iNumberOfTrailingZeros;
        int iNumberOfTrailingZeros2 = Long.numberOfTrailingZeros(j11);
        long j12 = j11 >> iNumberOfTrailingZeros2;
        while (jNumberOfTrailingZeros != j12) {
            long j13 = jNumberOfTrailingZeros - j12;
            long j14 = (j13 >> 63) & j13;
            long j15 = (j13 - j14) - j14;
            j12 += j14;
            jNumberOfTrailingZeros = j15 >> Long.numberOfTrailingZeros(j15);
        }
        return jNumberOfTrailingZeros << Math.min(iNumberOfTrailingZeros, iNumberOfTrailingZeros2);
    }

    public static Date m() {
        return Calendar.getInstance(io.sentry.vendor.gson.internal.bind.util.a.f18003a).getTime();
    }

    public static Date n(long j10) {
        Calendar calendar = Calendar.getInstance(io.sentry.vendor.gson.internal.bind.util.a.f18003a);
        calendar.setTimeInMillis(j10);
        return calendar.getTime();
    }

    public static Date o(String str) {
        try {
            return io.sentry.vendor.gson.internal.bind.util.a.c(str, new ParsePosition(0));
        } catch (ParseException unused) {
            throw new IllegalArgumentException(androidx.compose.material3.d.C("timestamp is not ISO format ", str));
        }
    }

    public static Date p(String str) {
        try {
            return n(new BigDecimal(str).setScale(3, RoundingMode.DOWN).movePointRight(3).longValue());
        } catch (NumberFormatException unused) {
            throw new IllegalArgumentException(androidx.compose.material3.d.C("timestamp is not millis format ", str));
        }
    }

    public static String q(Context context, int i10) {
        if (i10 <= 16777215) {
            return String.valueOf(i10);
        }
        try {
            return context.getResources().getResourceName(i10);
        } catch (Resources.NotFoundException unused) {
            return String.valueOf(i10);
        }
    }

    public static String r(Date date) {
        TimeZone timeZone = io.sentry.vendor.gson.internal.bind.util.a.f18003a;
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb2 = new StringBuilder(23 + (timeZone.getRawOffset() == 0 ? 1 : 6));
        io.sentry.vendor.gson.internal.bind.util.a.b(sb2, gregorianCalendar.get(1), 4);
        sb2.append('-');
        io.sentry.vendor.gson.internal.bind.util.a.b(sb2, gregorianCalendar.get(2) + 1, 2);
        sb2.append('-');
        io.sentry.vendor.gson.internal.bind.util.a.b(sb2, gregorianCalendar.get(5), 2);
        sb2.append('T');
        io.sentry.vendor.gson.internal.bind.util.a.b(sb2, gregorianCalendar.get(11), 2);
        sb2.append(':');
        io.sentry.vendor.gson.internal.bind.util.a.b(sb2, gregorianCalendar.get(12), 2);
        sb2.append(':');
        io.sentry.vendor.gson.internal.bind.util.a.b(sb2, gregorianCalendar.get(13), 2);
        sb2.append('.');
        io.sentry.vendor.gson.internal.bind.util.a.b(sb2, gregorianCalendar.get(14), 3);
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int i10 = offset / ExoPlayer.DEFAULT_STUCK_PLAYING_NOT_ENDING_TIMEOUT_MS;
            int iAbs = Math.abs(i10 / 60);
            int iAbs2 = Math.abs(i10 % 60);
            sb2.append(offset >= 0 ? '+' : '-');
            io.sentry.vendor.gson.internal.bind.util.a.b(sb2, iAbs, 2);
            sb2.append(':');
            io.sentry.vendor.gson.internal.bind.util.a.b(sb2, iAbs2, 2);
        } else {
            sb2.append('Z');
        }
        return sb2.toString();
    }

    public static f s(Bundle bundle) {
        try {
            Bundle bundle2 = (Bundle) bundle.getParcelable(MediaSessionCompat.KEY_SESSION2_TOKEN);
            if (bundle2 == null) {
                return null;
            }
            bundle2.setClassLoader(c.class.getClassLoader());
            Parcelable parcelable = bundle2.getParcelable(CmcdData.OBJECT_TYPE_AUDIO_ONLY);
            if (parcelable instanceof ParcelImpl) {
                return ((ParcelImpl) parcelable).f6696i;
            }
            throw new IllegalArgumentException("Invalid parcel");
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public static boolean t(g8.c cVar) {
        if (!p8.g.f21143d.contains(cVar.getName())) {
            return false;
        }
        if (kotlin.collections.x.f0(p8.g.f21142c, m9.d.c(cVar)) && cVar.e().isEmpty()) {
            return true;
        }
        if (!k.z(cVar)) {
            return false;
        }
        Collection collectionK = cVar.k();
        if (collectionK.isEmpty()) {
            return false;
        }
        Iterator it = collectionK.iterator();
        while (it.hasNext()) {
            if (t((g8.c) it.next())) {
                return true;
            }
        }
        return false;
    }

    public static String v(gb.b0 b0Var) throws NoSuchAlgorithmException {
        byte[] bytes = b0Var.f15625i.getBytes(kotlin.text.a.f19924a);
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(bytes, 0, bytes.length);
        return new m(messageDigest.digest()).i();
    }

    public static ConcurrentHashMap w(Map map) {
        if (map == null) {
            return null;
        }
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                concurrentHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return concurrentHashMap;
    }

    public static void x(InputConnection inputConnection, EditorInfo editorInfo, TextView textView) {
        if (inputConnection == null || editorInfo.hintText != null) {
            return;
        }
        for (ViewParent parent = textView.getParent(); parent instanceof View; parent = parent.getParent()) {
            if (parent instanceof x4) {
                editorInfo.hintText = ((x4) parent).a();
                return;
            }
        }
    }

    public static void y(Bundle bundle, f fVar) {
        if (fVar == null) {
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable(CmcdData.OBJECT_TYPE_AUDIO_ONLY, new ParcelImpl(fVar));
        bundle.putParcelable(MediaSessionCompat.KEY_SESSION2_TOKEN, bundle2);
    }

    public static int z(xb.i0 i0Var) throws IOException {
        byte bX;
        try {
            xb.j jVar = i0Var.f22756l;
            i0Var.X(1L);
            long j10 = 0;
            while (true) {
                long j11 = j10 + 1;
                if (!i0Var.request(j11)) {
                    break;
                }
                bX = jVar.x(j10);
                if ((bX < 48 || bX > 57) && (j10 != 0 || bX != 45)) {
                    break;
                }
                j10 = j11;
            }
            if (j10 == 0) {
                r0.c(16);
                throw new NumberFormatException("Expected a digit or '-' but was 0x".concat(Integer.toString(bX, 16)));
            }
            long jV = jVar.V();
            String strB = i0Var.B(Long.MAX_VALUE);
            if (jV >= 0 && jV <= 2147483647L && strB.length() <= 0) {
                return (int) jV;
            }
            throw new IOException("expected an int but was \"" + jV + strB + '\"');
        } catch (NumberFormatException e5) {
            throw new IOException(e5.getMessage());
        }
    }

    public abstract void C();

    public abstract void D();

    public abstract void E();

    public abstract String c();

    public abstract void u();

    public void B() {
    }
}
