package okhttp3.internal.publicsuffix;

import ga.r;
import io.sentry.util.l;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.IDN;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.p;
import kotlin.text.o;
import qb.n;
import xb.b;
import xb.i0;

/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lokhttp3/internal/publicsuffix/PublicSuffixDatabase;", "", "<init>", "()V", "io/sentry/util/l", "okhttp"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PublicSuffixDatabase {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final byte[] f20990e = {42};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final List f20991f = Collections.singletonList("*");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final PublicSuffixDatabase f20992g = new PublicSuffixDatabase();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicBoolean f20993a = new AtomicBoolean(false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CountDownLatch f20994b = new CountDownLatch(1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f20995c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f20996d;

    public static List c(String str) {
        List listX0 = o.x0(str, new char[]{'.'}, 6);
        return p.a(x.w0(listX0), "") ? x.i0(1, listX0) : listX0;
    }

    public final String a(String str) {
        String strC;
        String strC2;
        String strC3;
        int size;
        int size2;
        List listC = c(IDN.toUnicode(str));
        List listX0 = z.f19728i;
        if (this.f20993a.get() || !this.f20993a.compareAndSet(false, true)) {
            try {
                this.f20994b.await();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        } else {
            boolean z = false;
            while (true) {
                try {
                    try {
                        b();
                        break;
                    } catch (InterruptedIOException unused2) {
                        Thread.interrupted();
                        z = true;
                    } catch (IOException e5) {
                        n nVar = n.f21385a;
                        n.f21385a.getClass();
                        n.i("Failed to read public suffix list", e5, 5);
                        if (z) {
                        }
                    }
                } finally {
                    if (z) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
        if (this.f20995c == null) {
            throw new IllegalStateException("Unable to load publicsuffixes.gz resource from the classpath.");
        }
        int size3 = listC.size();
        byte[][] bArr = new byte[size3][];
        for (int i10 = 0; i10 < size3; i10++) {
            bArr[i10] = ((String) listC.get(i10)).getBytes(StandardCharsets.UTF_8);
        }
        int i11 = 0;
        while (true) {
            if (i11 >= size3) {
                strC = null;
                break;
            }
            byte[] bArr2 = this.f20995c;
            if (bArr2 == null) {
                p.i("publicSuffixListBytes");
                throw null;
            }
            strC = l.c(bArr2, bArr, i11);
            if (strC != null) {
                break;
            }
            i11++;
        }
        if (size3 > 1) {
            byte[][] bArr3 = (byte[][]) bArr.clone();
            int length = bArr3.length - 1;
            for (int i12 = 0; i12 < length; i12++) {
                bArr3[i12] = f20990e;
                byte[] bArr4 = this.f20995c;
                if (bArr4 == null) {
                    p.i("publicSuffixListBytes");
                    throw null;
                }
                strC2 = l.c(bArr4, bArr3, i12);
                if (strC2 != null) {
                    break;
                }
            }
            strC2 = null;
        } else {
            strC2 = null;
        }
        if (strC2 != null) {
            int i13 = size3 - 1;
            for (int i14 = 0; i14 < i13; i14++) {
                byte[] bArr5 = this.f20996d;
                if (bArr5 == null) {
                    p.i("publicSuffixExceptionListBytes");
                    throw null;
                }
                strC3 = l.c(bArr5, bArr, i14);
                if (strC3 != null) {
                    break;
                }
            }
            strC3 = null;
        } else {
            strC3 = null;
        }
        if (strC3 != null) {
            listX0 = o.x0("!".concat(strC3), new char[]{'.'}, 6);
        } else if (strC == null && strC2 == null) {
            listX0 = f20991f;
        } else {
            List listX02 = strC != null ? o.x0(strC, new char[]{'.'}, 6) : listX0;
            if (strC2 != null) {
                listX0 = o.x0(strC2, new char[]{'.'}, 6);
            }
            if (listX02.size() > listX0.size()) {
                listX0 = listX02;
            }
        }
        if (listC.size() == listX0.size() && ((String) listX0.get(0)).charAt(0) != '!') {
            return null;
        }
        if (((String) listX0.get(0)).charAt(0) == '!') {
            size = listC.size();
            size2 = listX0.size();
        } else {
            size = listC.size();
            size2 = listX0.size() + 1;
        }
        return r.O(r.H(new ga.p(c(str), 3), size - size2), ".", null, null, 62);
    }

    public final void b() {
        try {
            InputStream resourceAsStream = PublicSuffixDatabase.class.getResourceAsStream("publicsuffixes.gz");
            if (resourceAsStream != null) {
                i0 i0Var = new i0(new xb.x(b.g(resourceAsStream)));
                try {
                    long j10 = i0Var.readInt();
                    i0Var.X(j10);
                    byte[] bArrT = i0Var.f22756l.T(j10);
                    long j11 = i0Var.readInt();
                    i0Var.X(j11);
                    byte[] bArrT2 = i0Var.f22756l.T(j11);
                    i0Var.close();
                    synchronized (this) {
                        this.f20995c = bArrT;
                        this.f20996d = bArrT2;
                    }
                } finally {
                }
            }
        } finally {
            this.f20994b.countDown();
        }
    }
}
