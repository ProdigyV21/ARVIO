package yb;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import com.google.common.util.concurrent.r0;
import io.ktor.util.date.GMTDateParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import kotlin.collections.h0;
import kotlin.jvm.internal.j0;
import kotlin.jvm.internal.k0;
import kotlin.text.o;
import kotlin.text.u;
import r7.p;
import x6.x;
import xb.f0;
import xb.i0;
import xb.l0;
import xb.q;

/* JADX INFO: loaded from: classes5.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final char[] f23007a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', GMTDateParser.DAY_OF_MONTH, 'e', 'f'};

    public static final int a(char c10) {
        if ('0' <= c10 && c10 < ':') {
            return c10 - '0';
        }
        if ('a' <= c10 && c10 < 'g') {
            return c10 - 'W';
        }
        if ('A' <= c10 && c10 < 'G') {
            return c10 - '7';
        }
        throw new IllegalArgumentException("Unexpected hex digit: " + c10);
    }

    public static final LinkedHashMap b(ArrayList arrayList) {
        String str = f0.f22733l;
        f0 f0VarI = v1.i.i(DomExceptionUtils.SEPARATOR);
        LinkedHashMap linkedHashMapU0 = h0.u0(new x(f0VarI, new j(f0VarI)));
        for (j jVar : kotlin.collections.x.W0(arrayList, new a8.h(10))) {
            if (((j) linkedHashMapU0.put(jVar.f23042a, jVar)) == null) {
                while (true) {
                    f0 f0Var = jVar.f23042a;
                    f0 f0VarD = f0Var.d();
                    if (f0VarD != null) {
                        j jVar2 = (j) linkedHashMapU0.get(f0VarD);
                        if (jVar2 != null) {
                            jVar2.f23049h.add(f0Var);
                            break;
                        }
                        j jVar3 = new j(f0VarD);
                        linkedHashMapU0.put(f0VarD, jVar3);
                        jVar3.f23049h.add(f0Var);
                        jVar = jVar3;
                    }
                }
            }
        }
        return linkedHashMapU0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x00d3, code lost:
    
        if (r0 != false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00d5, code lost:
    
        if (r15 != 0) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00d7, code lost:
    
        r5.addLast(r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00da, code lost:
    
        r3 = r3.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00de, code lost:
    
        r3 = r1;
        r1 = r2;
        r2 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0139, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x013a, code lost:
    
        r9 = r5;
     */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0159 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object c(ga.o r25, xb.s r26, kotlin.collections.p r27, xb.f0 r28, boolean r29, boolean r30, f7.a r31) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 346
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: yb.b.c(ga.o, xb.s, kotlin.collections.p, xb.f0, boolean, boolean, f7.a):java.lang.Object");
    }

    public static final String d(int i10) {
        r0.c(16);
        return "0x".concat(Integer.toString(i10, 16));
    }

    public static final j e(i0 i0Var) throws IOException {
        Long lValueOf;
        int iJ = i0Var.j();
        if (iJ != 33639248) {
            throw new IOException("bad zip: expected " + d(33639248) + " but was " + d(iJ));
        }
        i0Var.skip(4L);
        short sL = i0Var.l();
        int i10 = sL & 65535;
        if ((sL & 1) != 0) {
            throw new IOException("unsupported zip: general purpose bit flag=" + d(i10));
        }
        int iL = i0Var.l() & 65535;
        short sL2 = i0Var.l();
        int i11 = sL2 & 65535;
        short sL3 = i0Var.l();
        int i12 = sL3 & 65535;
        if (i11 == -1) {
            lValueOf = null;
        } else {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.set(14, 0);
            gregorianCalendar.set(((i12 >> 9) & 127) + 1980, ((i12 >> 5) & 15) - 1, sL3 & 31, (i11 >> 11) & 31, (i11 >> 5) & 63, (sL2 & 31) << 1);
            lValueOf = Long.valueOf(gregorianCalendar.getTime().getTime());
        }
        Long l10 = lValueOf;
        i0Var.j();
        j0 j0Var = new j0();
        j0Var.f19745i = ((long) i0Var.j()) & 4294967295L;
        j0 j0Var2 = new j0();
        j0Var2.f19745i = ((long) i0Var.j()) & 4294967295L;
        int iL2 = i0Var.l() & 65535;
        int iL3 = i0Var.l() & 65535;
        int iL4 = i0Var.l() & 65535;
        i0Var.skip(8L);
        j0 j0Var3 = new j0();
        j0Var3.f19745i = ((long) i0Var.j()) & 4294967295L;
        String strO = i0Var.o(iL2);
        if (o.U(strO, (char) 0)) {
            throw new IOException("bad zip: filename contains 0x00");
        }
        long j10 = j0Var2.f19745i == 4294967295L ? 8 : 0L;
        if (j0Var.f19745i == 4294967295L) {
            j10 += (long) 8;
        }
        if (j0Var3.f19745i == 4294967295L) {
            j10 += (long) 8;
        }
        kotlin.jvm.internal.f0 f0Var = new kotlin.jvm.internal.f0();
        f(i0Var, iL3, new k(f0Var, j10, j0Var2, i0Var, j0Var, j0Var3));
        if (j10 > 0 && !f0Var.f19738i) {
            throw new IOException("bad zip: zip64 extra required but absent");
        }
        i0Var.o(iL4);
        String str = f0.f22733l;
        return new j(v1.i.i(DomExceptionUtils.SEPARATOR).i(strO), u.K(strO, DomExceptionUtils.SEPARATOR, false), j0Var.f19745i, j0Var2.f19745i, iL, l10, j0Var3.f19745i);
    }

    public static final void f(i0 i0Var, int i10, p pVar) throws IOException {
        xb.j jVar = i0Var.f22756l;
        long j10 = i10;
        while (j10 != 0) {
            if (j10 < 4) {
                throw new IOException("bad zip: truncated header in extra field");
            }
            int iL = i0Var.l() & 65535;
            long jL = ((long) i0Var.l()) & 65535;
            long j11 = j10 - ((long) 4);
            if (j11 < jL) {
                throw new IOException("bad zip: truncated value in extra field");
            }
            i0Var.X(jL);
            long j12 = jVar.f22759l;
            pVar.invoke(Integer.valueOf(iL), Long.valueOf(jL));
            long j13 = (jVar.f22759l + jL) - j12;
            if (j13 < 0) {
                throw new IOException(a0.c.i(iL, "unsupported zip: too many bytes processed for "));
            }
            if (j13 > 0) {
                jVar.skip(j13);
            }
            j10 = j11 - jL;
        }
    }

    public static final q g(i0 i0Var, q qVar) throws IOException {
        k0 k0Var = new k0();
        k0Var.f19746i = qVar != null ? qVar.f22788f : null;
        k0 k0Var2 = new k0();
        k0 k0Var3 = new k0();
        int iJ = i0Var.j();
        if (iJ != 67324752) {
            throw new IOException("bad zip: expected " + d(67324752) + " but was " + d(iJ));
        }
        i0Var.skip(2L);
        short sL = i0Var.l();
        int i10 = sL & 65535;
        if ((sL & 1) != 0) {
            throw new IOException("unsupported zip: general purpose bit flag=" + d(i10));
        }
        i0Var.skip(18L);
        long jL = ((long) i0Var.l()) & 65535;
        int iL = i0Var.l() & 65535;
        i0Var.skip(jL);
        if (qVar == null) {
            i0Var.skip(iL);
            return null;
        }
        f(i0Var, iL, new l(i0Var, k0Var, k0Var2, k0Var3));
        return new q(qVar.f22783a, qVar.f22784b, null, qVar.f22786d, (Long) k0Var3.f19746i, (Long) k0Var.f19746i, (Long) k0Var2.f19746i);
    }

    public static final int h(l0 l0Var, int i10) {
        int i11;
        int[] iArr = l0Var.f22771p;
        int i12 = i10 + 1;
        int length = l0Var.f22770o.length - 1;
        int i13 = 0;
        while (true) {
            if (i13 <= length) {
                i11 = (i13 + length) >>> 1;
                int i14 = iArr[i11];
                if (i14 >= i12) {
                    if (i14 <= i12) {
                        break;
                    }
                    length = i11 - 1;
                } else {
                    i13 = i11 + 1;
                }
            } else {
                i11 = (-i13) - 1;
                break;
            }
        }
        return i11 >= 0 ? i11 : ~i11;
    }
}
