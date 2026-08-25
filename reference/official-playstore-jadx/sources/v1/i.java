package v1;

import com.google.firebase.components.ComponentRegistrar;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.text.o;
import kotlin.text.u;
import org.slf4j.ILoggerFactory;
import org.slf4j.spi.MDCAdapter;
import xb.f0;

/* JADX INFO: loaded from: classes3.dex */
public final class i implements ILoggerFactory, MDCAdapter, com.google.firebase.components.g, bc.b {
    public static final void b(xb.e eVar, long j10, boolean z) {
        xb.e eVar2;
        ReentrantLock reentrantLock = xb.e.f22724h;
        if (xb.e.f22727l == null) {
            xb.e.f22727l = new xb.e();
            f4.e eVar3 = new f4.e("Okio Watchdog");
            eVar3.setDaemon(true);
            eVar3.start();
        }
        long jNanoTime = System.nanoTime();
        if (j10 != 0 && z) {
            eVar.f22730g = Math.min(j10, eVar.c() - jNanoTime) + jNanoTime;
        } else if (j10 != 0) {
            eVar.f22730g = j10 + jNanoTime;
        } else {
            if (!z) {
                throw new AssertionError();
            }
            eVar.f22730g = eVar.c();
        }
        long j11 = eVar.f22730g - jNanoTime;
        xb.e eVar4 = xb.e.f22727l;
        while (true) {
            eVar2 = eVar4.f22729f;
            if (eVar2 == null || j11 < eVar2.f22730g - jNanoTime) {
                break;
            } else {
                eVar4 = eVar2;
            }
        }
        eVar.f22729f = eVar2;
        eVar4.f22729f = eVar;
        if (eVar4 == xb.e.f22727l) {
            xb.e.f22725i.signal();
        }
    }

    public static final boolean c(f0 f0Var) {
        f0 f0Var2 = yb.i.f23038d;
        xb.m mVarR = f0Var.f22734i;
        xb.m mVar = yb.f.f23028a;
        mVarR.getClass();
        int iN = mVarR.n(mVar.l());
        if (iN == -1) {
            xb.m mVar2 = f0Var.f22734i;
            xb.m mVar3 = yb.f.f23029b;
            mVar2.getClass();
            iN = mVar2.n(mVar3.l());
        }
        if (iN != -1) {
            mVarR = xb.m.r(mVarR, iN + 1, 0, 2);
        } else if (f0Var.k() != null && mVarR.f() == 2) {
            mVarR = xb.m.f22772n;
        }
        return !u.K(mVarR.t(), ".class", true);
    }

    public static xb.e d() throws InterruptedException {
        xb.e eVar = xb.e.f22727l.f22729f;
        if (eVar == null) {
            long jNanoTime = System.nanoTime();
            xb.e.f22725i.await(xb.e.f22726j, TimeUnit.MILLISECONDS);
            if (xb.e.f22727l.f22729f != null || System.nanoTime() - jNanoTime < xb.e.k) {
                return null;
            }
            return xb.e.f22727l;
        }
        long jNanoTime2 = eVar.f22730g - System.nanoTime();
        if (jNanoTime2 > 0) {
            xb.e.f22725i.await(jNanoTime2, TimeUnit.NANOSECONDS);
            return null;
        }
        xb.e.f22727l.f22729f = eVar.f22729f;
        eVar.f22729f = null;
        eVar.f22728e = 2;
        return eVar;
    }

    public static xb.m e(String str) {
        int i10;
        char cCharAt;
        byte[] bArr = xb.a.f22714a;
        int length = str.length();
        while (length > 0 && ((cCharAt = str.charAt(length - 1)) == '=' || cCharAt == '\n' || cCharAt == '\r' || cCharAt == ' ' || cCharAt == '\t')) {
            length--;
        }
        int i11 = (int) ((((long) length) * 6) / 8);
        byte[] bArrCopyOf = new byte[i11];
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        while (true) {
            if (i12 < length) {
                char cCharAt2 = str.charAt(i12);
                if ('A' <= cCharAt2 && cCharAt2 < '[') {
                    i10 = cCharAt2 - 'A';
                } else if ('a' <= cCharAt2 && cCharAt2 < '{') {
                    i10 = cCharAt2 - 'G';
                } else if ('0' <= cCharAt2 && cCharAt2 < ':') {
                    i10 = cCharAt2 + 4;
                } else if (cCharAt2 != '+' && cCharAt2 != '-') {
                    if (cCharAt2 != '/' && cCharAt2 != '_') {
                        if (cCharAt2 != '\n' && cCharAt2 != '\r' && cCharAt2 != ' ' && cCharAt2 != '\t') {
                            break;
                        }
                        i12++;
                    } else {
                        i10 = 63;
                    }
                } else {
                    i10 = 62;
                }
                i14 = (i14 << 6) | i10;
                i13++;
                if (i13 % 4 == 0) {
                    bArrCopyOf[i15] = (byte) (i14 >> 16);
                    int i16 = i15 + 2;
                    bArrCopyOf[i15 + 1] = (byte) (i14 >> 8);
                    i15 += 3;
                    bArrCopyOf[i16] = (byte) i14;
                }
                i12++;
            } else {
                int i17 = i13 % 4;
                if (i17 != 1) {
                    if (i17 == 2) {
                        bArrCopyOf[i15] = (byte) ((i14 << 12) >> 16);
                        i15++;
                    } else if (i17 == 3) {
                        int i18 = i14 << 6;
                        int i19 = i15 + 1;
                        bArrCopyOf[i15] = (byte) (i18 >> 16);
                        i15 += 2;
                        bArrCopyOf[i19] = (byte) (i18 >> 8);
                    }
                    if (i15 != i11) {
                        bArrCopyOf = Arrays.copyOf(bArrCopyOf, i15);
                    }
                }
            }
        }
        bArrCopyOf = null;
        if (bArrCopyOf != null) {
            return new xb.m(bArrCopyOf);
        }
        return null;
    }

    public static xb.m f(String str) {
        if (str.length() % 2 != 0) {
            throw new IllegalArgumentException("Unexpected hex string: ".concat(str).toString());
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i10 = 0; i10 < length; i10++) {
            int i11 = i10 * 2;
            bArr[i10] = (byte) (yb.b.a(str.charAt(i11 + 1)) + (yb.b.a(str.charAt(i11)) << 4));
        }
        return new xb.m(bArr);
    }

    public static xb.m g(String str) {
        xb.m mVar = new xb.m(str.getBytes(kotlin.text.a.f19924a));
        mVar.f22775m = str;
        return mVar;
    }

    public static f0 h(File file) {
        String str = f0.f22733l;
        String string = file.toString();
        xb.m mVar = yb.f.f23028a;
        xb.j jVar = new xb.j();
        jVar.S0(string);
        return yb.f.d(jVar, false);
    }

    public static f0 i(String str) {
        xb.m mVar = yb.f.f23028a;
        xb.j jVar = new xb.j();
        jVar.S0(str);
        return yb.f.d(jVar, false);
    }

    public static f0 j(Path path) {
        String str = f0.f22733l;
        String string = path.toString();
        xb.m mVar = yb.f.f23028a;
        xb.j jVar = new xb.j();
        jVar.S0(string);
        return yb.f.d(jVar, false);
    }

    public static xb.m k(byte[] bArr) {
        int length = bArr.length;
        xb.b.c(bArr.length, 0, length);
        qb.l.j(length, bArr.length);
        return new xb.m(Arrays.copyOfRange(bArr, 0, length));
    }

    public static f0 m(f0 f0Var, f0 f0Var2) {
        return yb.i.f23038d.i(o.r0(f0Var.f22734i.t(), f0Var2.f22734i.t()).replace('\\', '/'));
    }

    @Override // org.slf4j.ILoggerFactory
    public vc.b a(String str) {
        return xc.a.f22811i;
    }

    public List l(ComponentRegistrar componentRegistrar) {
        ArrayList arrayList = new ArrayList();
        for (com.google.firebase.components.b bVar : componentRegistrar.getComponents()) {
            String str = bVar.f14255a;
            if (str != null) {
                bVar = new com.google.firebase.components.b(str, bVar.f14256b, bVar.f14257c, bVar.f14258d, bVar.f14259e, new androidx.media3.exoplayer.analytics.b(str, bVar, 24), bVar.f14261g);
            }
            arrayList.add(bVar);
        }
        return arrayList;
    }
}
