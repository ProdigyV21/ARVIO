package ib;

import androidx.compose.material3.d;
import gb.a0;
import gb.b0;
import gb.h0;
import gb.m0;
import gb.p0;
import gb.r0;
import j$.util.DesugarTimeZone;
import java.io.Closeable;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.p;
import kotlin.text.m;
import kotlin.text.o;
import v1.i;
import xb.e0;
import xb.j;
import xb.l;
import xb.o0;

/* JADX INFO: loaded from: classes5.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f16268a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a0 f16269b = t7.a.H(new String[0]);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final r0 f16270c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final m0 f16271d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final e0 f16272e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final TimeZone f16273f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final m f16274g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f16275h;

    static {
        byte[] bArr = new byte[0];
        f16268a = bArr;
        j jVar = new j();
        jVar.write(bArr, 0, 0);
        long j10 = 0;
        f16270c = new r0(null, j10, jVar, 0);
        c(j10, j10, j10);
        f16271d = new m0(null, 0, bArr);
        f16272e = xb.b.d(i.f("efbbbf"), i.f("feff"), i.f("fffe"), i.f("0000ffff"), i.f("ffff0000"));
        f16273f = DesugarTimeZone.getTimeZone("GMT");
        f16274g = new m("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");
        f16275h = o.s0(o.r0(h0.class.getName(), "okhttp3."), "Client");
    }

    public static final boolean a(b0 b0Var, b0 b0Var2) {
        return p.a(b0Var.f15620d, b0Var2.f15620d) && b0Var.f15621e == b0Var2.f15621e && p.a(b0Var.f15617a, b0Var2.f15617a);
    }

    public static final int b(String str, long j10, TimeUnit timeUnit) {
        if (j10 < 0) {
            throw new IllegalStateException(str.concat(" < 0").toString());
        }
        if (timeUnit == null) {
            throw new IllegalStateException("unit == null");
        }
        long millis = timeUnit.toMillis(j10);
        if (millis > 2147483647L) {
            throw new IllegalArgumentException(str.concat(" too large.").toString());
        }
        if (millis != 0 || j10 <= 0) {
            return (int) millis;
        }
        throw new IllegalArgumentException(str.concat(" too small.").toString());
    }

    public static final void c(long j10, long j11, long j12) {
        if ((j11 | j12) < 0 || j11 > j10 || j10 - j11 < j12) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static final void d(Closeable closeable) {
        try {
            closeable.close();
        } catch (RuntimeException e5) {
            throw e5;
        } catch (Exception unused) {
        }
    }

    public static final void e(Socket socket) {
        try {
            socket.close();
        } catch (AssertionError e5) {
            throw e5;
        } catch (RuntimeException e6) {
            if (!p.a(e6.getMessage(), "bio == null")) {
                throw e6;
            }
        } catch (Exception unused) {
        }
    }

    public static final int f(int i10, int i11, String str, String str2) {
        while (i10 < i11) {
            if (o.U(str2, str.charAt(i10))) {
                return i10;
            }
            i10++;
        }
        return i11;
    }

    public static final int g(String str, int i10, int i11, char c10) {
        while (i10 < i11) {
            if (str.charAt(i10) == c10) {
                return i10;
            }
            i10++;
        }
        return i11;
    }

    public static /* synthetic */ int h(String str, char c10, int i10, int i11, int i12) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = str.length();
        }
        return g(str, i10, i11, c10);
    }

    public static final String i(String str, Object... objArr) {
        Locale locale = Locale.US;
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        return String.format(locale, str, Arrays.copyOf(objArrCopyOf, objArrCopyOf.length));
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0033, code lost:
    
        r2 = r2 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean j(java.lang.String[] r7, java.lang.String[] r8, java.util.Comparator r9) {
        /*
            int r0 = r7.length
            r1 = 0
            if (r0 != 0) goto L5
            goto L36
        L5:
            if (r8 == 0) goto L36
            int r0 = r8.length
            if (r0 != 0) goto Lb
            goto L36
        Lb:
            int r0 = r7.length
            r2 = r1
        Ld:
            if (r2 >= r0) goto L36
            r3 = r7[r2]
            r4 = r1
        L12:
            int r5 = r8.length
            r6 = 1
            if (r4 >= r5) goto L18
            r5 = r6
            goto L19
        L18:
            r5 = r1
        L19:
            if (r5 == 0) goto L33
            int r5 = r4 + 1
            r4 = r8[r4]     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L28
            int r4 = r9.compare(r3, r4)
            if (r4 != 0) goto L26
            return r6
        L26:
            r4 = r5
            goto L12
        L28:
            r7 = move-exception
            java.util.NoSuchElementException r8 = new java.util.NoSuchElementException
            java.lang.String r7 = r7.getMessage()
            r8.<init>(r7)
            throw r8
        L33:
            int r2 = r2 + 1
            goto Ld
        L36:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: ib.c.j(java.lang.String[], java.lang.String[], java.util.Comparator):boolean");
    }

    public static final long k(p0 p0Var) {
        String strA = p0Var.f15785p.a("Content-Length");
        if (strA == null) {
            return -1L;
        }
        try {
            return Long.parseLong(strA);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    public static final List l(Object... objArr) {
        Object[] objArr2 = (Object[]) objArr.clone();
        return Collections.unmodifiableList(t7.a.E(Arrays.copyOf(objArr2, objArr2.length)));
    }

    public static final int m(String str) {
        int length = str.length();
        for (int i10 = 0; i10 < length; i10++) {
            char cCharAt = str.charAt(i10);
            if (p.c(cCharAt, 31) <= 0 || p.c(cCharAt, 127) >= 0) {
                return i10;
            }
        }
        return -1;
    }

    public static final int n(int i10, int i11, String str) {
        while (i10 < i11) {
            char cCharAt = str.charAt(i10);
            if (cCharAt != '\t' && cCharAt != '\n' && cCharAt != '\f' && cCharAt != '\r' && cCharAt != ' ') {
                return i10;
            }
            i10++;
        }
        return i11;
    }

    public static final int o(int i10, int i11, String str) {
        int i12 = i11 - 1;
        if (i10 <= i12) {
            while (true) {
                char cCharAt = str.charAt(i12);
                if (cCharAt != '\t' && cCharAt != '\n' && cCharAt != '\f' && cCharAt != '\r' && cCharAt != ' ') {
                    return i12 + 1;
                }
                if (i12 == i10) {
                    break;
                }
                i12--;
            }
        }
        return i10;
    }

    public static final String[] p(String[] strArr, String[] strArr2, Comparator comparator) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            int length = strArr2.length;
            int i10 = 0;
            while (true) {
                if (i10 >= length) {
                    break;
                }
                if (comparator.compare(str, strArr2[i10]) == 0) {
                    arrayList.add(str);
                    break;
                }
                i10++;
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static final boolean q(String str) {
        return str.equalsIgnoreCase("Authorization") || str.equalsIgnoreCase("Cookie") || str.equalsIgnoreCase("Proxy-Authorization") || str.equalsIgnoreCase("Set-Cookie");
    }

    public static final int r(char c10) {
        if ('0' <= c10 && c10 < ':') {
            return c10 - '0';
        }
        if ('a' <= c10 && c10 < 'g') {
            return c10 - 'W';
        }
        if ('A' > c10 || c10 >= 'G') {
            return -1;
        }
        return c10 - '7';
    }

    public static final Charset s(l lVar, Charset charset) {
        int iD0 = lVar.d0(f16272e);
        if (iD0 == -1) {
            return charset;
        }
        if (iD0 == 0) {
            return StandardCharsets.UTF_8;
        }
        if (iD0 == 1) {
            return StandardCharsets.UTF_16BE;
        }
        if (iD0 == 2) {
            return StandardCharsets.UTF_16LE;
        }
        if (iD0 == 3) {
            Charset charset2 = kotlin.text.a.f19924a;
            Charset charset3 = kotlin.text.a.f19928e;
            if (charset3 != null) {
                return charset3;
            }
            Charset charsetForName = Charset.forName("UTF-32BE");
            kotlin.text.a.f19928e = charsetForName;
            return charsetForName;
        }
        if (iD0 != 4) {
            throw new AssertionError();
        }
        Charset charset4 = kotlin.text.a.f19924a;
        Charset charset5 = kotlin.text.a.f19927d;
        if (charset5 != null) {
            return charset5;
        }
        Charset charsetForName2 = Charset.forName("UTF-32LE");
        kotlin.text.a.f19927d = charsetForName2;
        return charsetForName2;
    }

    public static final int t(l lVar) {
        return (lVar.readByte() & 255) | ((lVar.readByte() & 255) << 16) | ((lVar.readByte() & 255) << 8);
    }

    public static final boolean u(o0 o0Var, int i10) {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        long jNanoTime = System.nanoTime();
        long jC = o0Var.timeout().e() ? o0Var.timeout().c() - jNanoTime : Long.MAX_VALUE;
        o0Var.timeout().d(Math.min(jC, timeUnit.toNanos(i10)) + jNanoTime);
        try {
            j jVar = new j();
            while (o0Var.read(jVar, 8192L) != -1) {
                jVar.i();
            }
            if (jC == Long.MAX_VALUE) {
                o0Var.timeout().a();
                return true;
            }
            o0Var.timeout().d(jNanoTime + jC);
            return true;
        } catch (InterruptedIOException unused) {
            if (jC == Long.MAX_VALUE) {
                o0Var.timeout().a();
                return false;
            }
            o0Var.timeout().d(jNanoTime + jC);
            return false;
        } catch (Throwable th) {
            if (jC == Long.MAX_VALUE) {
                o0Var.timeout().a();
            } else {
                o0Var.timeout().d(jNanoTime + jC);
            }
            throw th;
        }
    }

    public static final a0 v(List list) {
        ArrayList arrayList = new ArrayList(20);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ob.a aVar = (ob.a) it.next();
            xb.m mVar = aVar.f20871a;
            xb.m mVar2 = aVar.f20872b;
            String strT = mVar.t();
            String strT2 = mVar2.t();
            arrayList.add(strT);
            arrayList.add(o.L0(strT2).toString());
        }
        return new a0((String[]) arrayList.toArray(new String[0]));
    }

    public static final String w(b0 b0Var, boolean z) {
        int i10 = b0Var.f15621e;
        String strI = b0Var.f15620d;
        if (o.T(strI, ":", false)) {
            strI = d.i(']', "[", strI);
        }
        if (!z) {
            String str = b0Var.f15617a;
            if (i10 == (str.equals("http") ? 80 : str.equals("https") ? 443 : -1)) {
                return strI;
            }
        }
        return strI + ':' + i10;
    }

    public static final List x(List list) {
        return Collections.unmodifiableList(new ArrayList(list));
    }

    public static final int y(String str, int i10) {
        if (str != null) {
            try {
                long j10 = Long.parseLong(str);
                if (j10 > 2147483647L) {
                    return Integer.MAX_VALUE;
                }
                if (j10 < 0) {
                    return 0;
                }
                return (int) j10;
            } catch (NumberFormatException unused) {
            }
        }
        return i10;
    }

    public static final String z(int i10, int i11, String str) {
        int iN = n(i10, i11, str);
        return str.substring(iN, o(iN, i11, str));
    }
}
