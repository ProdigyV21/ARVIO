package io.sentry;

import j$.util.DesugarTimeZone;
import java.io.Reader;
import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes4.dex */
public final class h2 implements k3 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final io.sentry.vendor.gson.stream.a f17313i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ArrayDeque f17314l = new ArrayDeque();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f17315m = 0;

    public h2(Reader reader) {
        this.f17313i = new io.sentry.vendor.gson.stream.a(reader);
    }

    @Override // io.sentry.k3
    public final Float B0() {
        if (this.f17313i.peek() != io.sentry.vendor.gson.stream.b.NULL) {
            return Float.valueOf(nextFloat());
        }
        l();
        return null;
    }

    @Override // io.sentry.k3
    public final Double F() {
        if (this.f17313i.peek() != io.sentry.vendor.gson.stream.b.NULL) {
            return Double.valueOf(nextDouble());
        }
        l();
        return null;
    }

    @Override // io.sentry.k3
    public final Object F0() {
        f2 f2Var = new f2();
        f2Var.d(this);
        a2 a2VarA = f2Var.a();
        if (a2VarA != null) {
            return a2VarA.getValue();
        }
        return null;
    }

    @Override // io.sentry.k3
    public final Date I(ILogger iLogger) {
        if (this.f17313i.peek() == io.sentry.vendor.gson.stream.b.NULL) {
            l();
            return null;
        }
        String strNextString = nextString();
        if (strNextString == null) {
            return null;
        }
        try {
            try {
                return k2.c.o(strNextString);
            } catch (Exception e5) {
                iLogger.l(w5.ERROR, "Error when deserializing millis timestamp format.", e5);
                return null;
            }
        } catch (Exception unused) {
            return k2.c.p(strNextString);
        }
    }

    @Override // io.sentry.k3
    public final ArrayList I0(ILogger iLogger, u1 u1Var) {
        boolean z;
        io.sentry.vendor.gson.stream.a aVar = this.f17313i;
        if (aVar.peek() == io.sentry.vendor.gson.stream.b.NULL) {
            l();
            return null;
        }
        beginArray();
        ArrayList arrayList = new ArrayList();
        while (true) {
            if (!aVar.hasNext()) {
                break;
            }
            g2 g2Var = new g2(this.f17315m, aVar.peek());
            this.f17314l.addLast(g2Var);
            try {
                try {
                    arrayList.add(u1Var.a(this, iLogger));
                } catch (Exception e5) {
                    iLogger.l(w5.WARNING, "Failed to deserialize object in list.", e5);
                    try {
                        o(g2Var);
                        z = true;
                    } catch (Exception e6) {
                        iLogger.l(w5.ERROR, "Stream unrecoverable, aborting list deserialization.", e6);
                        z = false;
                    }
                    if (!z) {
                        i(g2Var);
                        endArray();
                        return arrayList;
                    }
                }
                i(g2Var);
            } catch (Throwable th) {
                i(g2Var);
                throw th;
            }
        }
        endArray();
        return arrayList;
    }

    @Override // io.sentry.k3
    public final Boolean L() {
        if (this.f17313i.peek() != io.sentry.vendor.gson.stream.b.NULL) {
            return Boolean.valueOf(k());
        }
        l();
        return null;
    }

    @Override // io.sentry.k3
    public final Object P(ILogger iLogger, u1 u1Var) {
        if (this.f17313i.peek() != io.sentry.vendor.gson.stream.b.NULL) {
            return u1Var.a(this, iLogger);
        }
        l();
        return null;
    }

    @Override // io.sentry.k3
    public final void beginArray() {
        io.sentry.vendor.gson.stream.a aVar = this.f17313i;
        int iJ = aVar.f18011r;
        if (iJ == 0) {
            iJ = aVar.j();
        }
        if (iJ != 3) {
            throw new IllegalStateException("Expected BEGIN_ARRAY but was " + aVar.peek() + aVar.o());
        }
        aVar.O(1);
        aVar.f18017y[aVar.f18016w - 1] = 0;
        aVar.f18011r = 0;
        j();
        this.f17315m++;
    }

    @Override // io.sentry.k3
    public final void beginObject() {
        io.sentry.vendor.gson.stream.a aVar = this.f17313i;
        int iJ = aVar.f18011r;
        if (iJ == 0) {
            iJ = aVar.j();
        }
        if (iJ != 1) {
            throw new IllegalStateException("Expected BEGIN_OBJECT but was " + aVar.peek() + aVar.o());
        }
        aVar.O(3);
        aVar.f18011r = 0;
        j();
        this.f17315m++;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f17313i.close();
    }

    @Override // io.sentry.k3
    public final void endArray() {
        io.sentry.vendor.gson.stream.a aVar = this.f17313i;
        int iJ = aVar.f18011r;
        if (iJ == 0) {
            iJ = aVar.j();
        }
        if (iJ != 4) {
            throw new IllegalStateException("Expected END_ARRAY but was " + aVar.peek() + aVar.o());
        }
        int i10 = aVar.f18016w;
        aVar.f18016w = i10 - 1;
        int[] iArr = aVar.f18017y;
        int i11 = i10 - 2;
        iArr[i11] = iArr[i11] + 1;
        aVar.f18011r = 0;
        this.f17315m--;
    }

    @Override // io.sentry.k3
    public final void endObject() {
        io.sentry.vendor.gson.stream.a aVar = this.f17313i;
        int iJ = aVar.f18011r;
        if (iJ == 0) {
            iJ = aVar.j();
        }
        if (iJ != 2) {
            throw new IllegalStateException("Expected END_OBJECT but was " + aVar.peek() + aVar.o());
        }
        int i10 = aVar.f18016w;
        int i11 = i10 - 1;
        aVar.f18016w = i11;
        aVar.x[i11] = null;
        int[] iArr = aVar.f18017y;
        int i12 = i10 - 2;
        iArr[i12] = iArr[i12] + 1;
        aVar.f18011r = 0;
        this.f17315m--;
    }

    @Override // io.sentry.k3
    public final Integer f0() {
        if (this.f17313i.peek() != io.sentry.vendor.gson.stream.b.NULL) {
            return Integer.valueOf(nextInt());
        }
        l();
        return null;
    }

    @Override // io.sentry.k3
    public final Long h0() {
        if (this.f17313i.peek() != io.sentry.vendor.gson.stream.b.NULL) {
            return Long.valueOf(nextLong());
        }
        l();
        return null;
    }

    @Override // io.sentry.k3
    public final boolean hasNext() {
        return this.f17313i.hasNext();
    }

    public final void i(g2 g2Var) {
        if (g2Var == null) {
            return;
        }
        ArrayDeque arrayDeque = this.f17314l;
        if (arrayDeque.isEmpty() || arrayDeque.peekLast() != g2Var) {
            arrayDeque.remove(g2Var);
        } else {
            arrayDeque.removeLast();
        }
    }

    public final void j() {
        g2 g2Var = (g2) this.f17314l.peekLast();
        if (g2Var != null) {
            g2Var.f17296c = true;
        }
    }

    public final boolean k() {
        io.sentry.vendor.gson.stream.a aVar = this.f17313i;
        int iJ = aVar.f18011r;
        if (iJ == 0) {
            iJ = aVar.j();
        }
        boolean z = false;
        if (iJ == 5) {
            aVar.f18011r = 0;
            int[] iArr = aVar.f18017y;
            int i10 = aVar.f18016w - 1;
            iArr[i10] = iArr[i10] + 1;
            z = true;
        } else {
            if (iJ != 6) {
                throw new IllegalStateException("Expected a boolean but was " + aVar.peek() + aVar.o());
            }
            aVar.f18011r = 0;
            int[] iArr2 = aVar.f18017y;
            int i11 = aVar.f18016w - 1;
            iArr2[i11] = iArr2[i11] + 1;
        }
        j();
        return z;
    }

    public final void l() {
        io.sentry.vendor.gson.stream.a aVar = this.f17313i;
        int iJ = aVar.f18011r;
        if (iJ == 0) {
            iJ = aVar.j();
        }
        if (iJ != 7) {
            throw new IllegalStateException("Expected null but was " + aVar.peek() + aVar.o());
        }
        aVar.f18011r = 0;
        int[] iArr = aVar.f18017y;
        int i10 = aVar.f18016w - 1;
        iArr[i10] = iArr[i10] + 1;
        j();
    }

    @Override // io.sentry.k3
    public final String l0() {
        if (this.f17313i.peek() != io.sentry.vendor.gson.stream.b.NULL) {
            return nextString();
        }
        l();
        return null;
    }

    @Override // io.sentry.k3
    public final HashMap m0(ILogger iLogger, u1 u1Var) {
        boolean z;
        io.sentry.vendor.gson.stream.a aVar = this.f17313i;
        if (aVar.peek() == io.sentry.vendor.gson.stream.b.NULL) {
            l();
            return null;
        }
        beginObject();
        HashMap map = new HashMap();
        if (aVar.hasNext()) {
            while (true) {
                String strNextName = aVar.nextName();
                g2 g2Var = new g2(this.f17315m, aVar.peek());
                this.f17314l.addLast(g2Var);
                try {
                    try {
                        map.put(strNextName, u1Var.a(this, iLogger));
                    } catch (Exception e5) {
                        iLogger.l(w5.WARNING, "Failed to deserialize object in map.", e5);
                        try {
                            o(g2Var);
                            z = true;
                        } catch (Exception e6) {
                            iLogger.l(w5.ERROR, "Stream unrecoverable, aborting map deserialization.", e6);
                            z = false;
                        }
                        if (!z) {
                            i(g2Var);
                            break;
                        }
                        endObject();
                        return map;
                    }
                    i(g2Var);
                    if (aVar.peek() != io.sentry.vendor.gson.stream.b.BEGIN_OBJECT && aVar.peek() != io.sentry.vendor.gson.stream.b.NAME) {
                        break;
                    }
                } catch (Throwable th) {
                    i(g2Var);
                    throw th;
                }
            }
        }
        endObject();
        return map;
    }

    @Override // io.sentry.k3
    public final double nextDouble() {
        double dNextDouble = this.f17313i.nextDouble();
        j();
        return dNextDouble;
    }

    @Override // io.sentry.k3
    public final float nextFloat() {
        double dNextDouble = this.f17313i.nextDouble();
        j();
        return (float) dNextDouble;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00d7  */
    @Override // io.sentry.k3
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int nextInt() {
        /*
            Method dump skipped, instruction units count: 242
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.h2.nextInt():int");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b8  */
    @Override // io.sentry.k3
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long nextLong() {
        /*
            Method dump skipped, instruction units count: 211
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.h2.nextLong():long");
    }

    @Override // io.sentry.k3
    public final String nextName() {
        return this.f17313i.nextName();
    }

    @Override // io.sentry.k3
    public final String nextString() {
        String str;
        io.sentry.vendor.gson.stream.a aVar = this.f17313i;
        int iJ = aVar.f18011r;
        if (iJ == 0) {
            iJ = aVar.j();
        }
        if (iJ == 10) {
            str = aVar.G();
        } else if (iJ == 8) {
            str = aVar.y('\'');
        } else if (iJ == 9) {
            str = aVar.y('\"');
        } else if (iJ == 11) {
            str = aVar.f18014u;
            aVar.f18014u = null;
        } else if (iJ == 15) {
            str = Long.toString(aVar.f18012s);
        } else {
            if (iJ != 16) {
                throw new IllegalStateException("Expected a string but was " + aVar.peek() + aVar.o());
            }
            str = new String(aVar.f18006m, aVar.f18007n, aVar.f18013t);
            aVar.f18007n += aVar.f18013t;
        }
        aVar.f18011r = 0;
        int[] iArr = aVar.f18017y;
        int i10 = aVar.f18016w - 1;
        iArr[i10] = iArr[i10] + 1;
        j();
        return str;
    }

    public final void o(g2 g2Var) {
        io.sentry.vendor.gson.stream.a aVar;
        while (true) {
            int i10 = this.f17315m;
            int i11 = g2Var.f17294a;
            aVar = this.f17313i;
            if (i10 <= i11) {
                break;
            }
            io.sentry.vendor.gson.stream.b bVarPeek = aVar.peek();
            if (bVarPeek == io.sentry.vendor.gson.stream.b.END_OBJECT) {
                endObject();
            } else if (bVarPeek == io.sentry.vendor.gson.stream.b.END_ARRAY) {
                endArray();
            } else {
                skipValue();
            }
        }
        if (g2Var.f17296c || aVar.peek() != g2Var.f17295b) {
            return;
        }
        skipValue();
    }

    @Override // io.sentry.k3
    public final io.sentry.vendor.gson.stream.b peek() {
        return this.f17313i.peek();
    }

    @Override // io.sentry.k3
    public final void setLenient(boolean z) {
        this.f17313i.f18005l = z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x00a3, code lost:
    
        r2.i();
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:61:0x009d. Please report as an issue. */
    @Override // io.sentry.k3
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void skipValue() {
        /*
            Method dump skipped, instruction units count: 214
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.h2.skipValue():void");
    }

    @Override // io.sentry.k3
    public final void t(ILogger iLogger, AbstractMap abstractMap, String str) throws Throwable {
        g2 g2Var;
        g2 g2Var2 = null;
        try {
            try {
                g2Var = new g2(this.f17315m, this.f17313i.peek());
                this.f17314l.addLast(g2Var);
            } catch (Throwable th) {
                th = th;
                i(g2Var2);
                throw th;
            }
        } catch (Exception e5) {
            e = e5;
        }
        try {
            abstractMap.put(str, F0());
            i(g2Var);
        } catch (Exception e6) {
            e = e6;
            g2Var2 = g2Var;
            iLogger.k(w5.ERROR, e, "Error deserializing unknown key: %s", str);
            if (g2Var2 != null) {
                try {
                    o(g2Var2);
                } catch (Exception e10) {
                    iLogger.l(w5.ERROR, "Stream unrecoverable after unknown key deserialization failure.", e10);
                }
            }
            i(g2Var2);
        } catch (Throwable th2) {
            th = th2;
            g2Var2 = g2Var;
            i(g2Var2);
            throw th;
        }
    }

    @Override // io.sentry.k3
    public final TimeZone v(ILogger iLogger) {
        if (this.f17313i.peek() == io.sentry.vendor.gson.stream.b.NULL) {
            l();
            return null;
        }
        try {
            return DesugarTimeZone.getTimeZone(nextString());
        } catch (Exception e5) {
            iLogger.l(w5.ERROR, "Error when deserializing TimeZone", e5);
            return null;
        }
    }
}
