package f1;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes3.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f15213a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f15214b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f15215c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final byte[] f15216d;

    public c(int i10, int i11, byte[] bArr) {
        this(-1L, bArr, i10, i11);
    }

    public static c a(long j10, ByteOrder byteOrder) {
        long[] jArr = {j10};
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[i.E[4]]);
        byteBufferWrap.order(byteOrder);
        byteBufferWrap.putInt((int) jArr[0]);
        return new c(4, 1, byteBufferWrap.array());
    }

    public static c b(g gVar, ByteOrder byteOrder) {
        g[] gVarArr = {gVar};
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[i.E[5]]);
        byteBufferWrap.order(byteOrder);
        g gVar2 = gVarArr[0];
        byteBufferWrap.putInt((int) gVar2.f15221a);
        byteBufferWrap.putInt((int) gVar2.f15222b);
        return new c(5, 1, byteBufferWrap.array());
    }

    public static c c(int i10, ByteOrder byteOrder) {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[i.E[3]]);
        byteBufferWrap.order(byteOrder);
        byteBufferWrap.putShort((short) new int[]{i10}[0]);
        return new c(3, 1, byteBufferWrap.array());
    }

    public final double d(ByteOrder byteOrder) throws Throwable {
        Object objG = g(byteOrder);
        if (objG == null) {
            throw new NumberFormatException("NULL can't be converted to a double value");
        }
        if (objG instanceof String) {
            return Double.parseDouble((String) objG);
        }
        if (objG instanceof long[]) {
            if (((long[]) objG).length == 1) {
                return r5[0];
            }
            throw new NumberFormatException("There are more than one component");
        }
        if (objG instanceof int[]) {
            if (((int[]) objG).length == 1) {
                return r5[0];
            }
            throw new NumberFormatException("There are more than one component");
        }
        if (objG instanceof double[]) {
            double[] dArr = (double[]) objG;
            if (dArr.length == 1) {
                return dArr[0];
            }
            throw new NumberFormatException("There are more than one component");
        }
        if (!(objG instanceof g[])) {
            throw new NumberFormatException("Couldn't find a double value");
        }
        g[] gVarArr = (g[]) objG;
        if (gVarArr.length != 1) {
            throw new NumberFormatException("There are more than one component");
        }
        g gVar = gVarArr[0];
        return gVar.f15221a / gVar.f15222b;
    }

    public final int e(ByteOrder byteOrder) {
        Object objG = g(byteOrder);
        if (objG == null) {
            throw new NumberFormatException("NULL can't be converted to a integer value");
        }
        if (objG instanceof String) {
            return Integer.parseInt((String) objG);
        }
        if (objG instanceof long[]) {
            long[] jArr = (long[]) objG;
            if (jArr.length == 1) {
                return (int) jArr[0];
            }
            throw new NumberFormatException("There are more than one component");
        }
        if (!(objG instanceof int[])) {
            throw new NumberFormatException("Couldn't find a integer value");
        }
        int[] iArr = (int[]) objG;
        if (iArr.length == 1) {
            return iArr[0];
        }
        throw new NumberFormatException("There are more than one component");
    }

    public final String f(ByteOrder byteOrder) throws Throwable {
        Object objG = g(byteOrder);
        if (objG == null) {
            return null;
        }
        if (objG instanceof String) {
            return (String) objG;
        }
        StringBuilder sb2 = new StringBuilder();
        int i10 = 0;
        if (objG instanceof long[]) {
            long[] jArr = (long[]) objG;
            while (i10 < jArr.length) {
                sb2.append(jArr[i10]);
                i10++;
                if (i10 != jArr.length) {
                    sb2.append(",");
                }
            }
            return sb2.toString();
        }
        if (objG instanceof int[]) {
            int[] iArr = (int[]) objG;
            while (i10 < iArr.length) {
                sb2.append(iArr[i10]);
                i10++;
                if (i10 != iArr.length) {
                    sb2.append(",");
                }
            }
            return sb2.toString();
        }
        if (objG instanceof double[]) {
            double[] dArr = (double[]) objG;
            while (i10 < dArr.length) {
                sb2.append(dArr[i10]);
                i10++;
                if (i10 != dArr.length) {
                    sb2.append(",");
                }
            }
            return sb2.toString();
        }
        if (!(objG instanceof g[])) {
            return null;
        }
        g[] gVarArr = (g[]) objG;
        while (i10 < gVarArr.length) {
            sb2.append(gVarArr[i10].f15221a);
            sb2.append('/');
            sb2.append(gVarArr[i10].f15222b);
            i10++;
            if (i10 != gVarArr.length) {
                sb2.append(",");
            }
        }
        return sb2.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0032: MOVE (r3 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]) (LINE:51), block:B:17:0x0032 */
    /* JADX WARN: Removed duplicated region for block: B:103:0x012e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r14v11, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v19, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v23, types: [int[]] */
    /* JADX WARN: Type inference failed for: r14v24, types: [long[]] */
    /* JADX WARN: Type inference failed for: r14v25, types: [f1.g[]] */
    /* JADX WARN: Type inference failed for: r14v26, types: [int[]] */
    /* JADX WARN: Type inference failed for: r14v27, types: [int[]] */
    /* JADX WARN: Type inference failed for: r14v28, types: [f1.g[]] */
    /* JADX WARN: Type inference failed for: r14v29, types: [double[]] */
    /* JADX WARN: Type inference failed for: r14v30, types: [java.io.Serializable] */
    /* JADX WARN: Type inference failed for: r14v31, types: [double[]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.io.Serializable g(java.nio.ByteOrder r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 340
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: f1.c.g(java.nio.ByteOrder):java.io.Serializable");
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("(");
        sb2.append(i.D[this.f15213a]);
        sb2.append(", data length:");
        return androidx.compose.material3.d.j(this.f15216d.length, ")", sb2);
    }

    public c(long j10, byte[] bArr, int i10, int i11) {
        this.f15213a = i10;
        this.f15214b = i11;
        this.f15215c = j10;
        this.f15216d = bArr;
    }
}
