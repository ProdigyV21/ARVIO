package io.sentry;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* JADX INFO: loaded from: classes4.dex */
public final class f2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f17276a;

    public f2() {
        this.f17276a = new ArrayList();
    }

    public a2 a() {
        ArrayList arrayList = (ArrayList) this.f17276a;
        if (arrayList.isEmpty()) {
            return null;
        }
        return (a2) a0.c.h(1, arrayList);
    }

    public boolean b() {
        if (((ArrayList) this.f17276a).size() == 1) {
            return true;
        }
        a2 a2VarA = a();
        e();
        if (!(a() instanceof d2)) {
            if (!(a() instanceof b2)) {
                return false;
            }
            b2 b2Var = (b2) a();
            if (a2VarA == null || b2Var == null) {
                return false;
            }
            b2Var.f17098a.add(a2VarA.getValue());
            return false;
        }
        d2 d2Var = (d2) a();
        e();
        c2 c2Var = (c2) a();
        if (d2Var == null || a2VarA == null || c2Var == null) {
            return false;
        }
        c2Var.f17116a.put(d2Var.f17210a, a2VarA.getValue());
        return false;
    }

    public boolean c(z1 z1Var) {
        Object objC = z1Var.c();
        if (a() == null && objC != null) {
            ((ArrayList) this.f17276a).add(new e2(objC));
            return true;
        }
        if (a() instanceof d2) {
            d2 d2Var = (d2) a();
            e();
            ((c2) a()).f17116a.put(d2Var.f17210a, objC);
            return false;
        }
        if (!(a() instanceof b2)) {
            return false;
        }
        ((b2) a()).f17098a.add(objC);
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void d(final h2 h2Var) {
        boolean zB;
        ArrayList arrayList = (ArrayList) this.f17276a;
        int[] iArr = y1.f18060a;
        io.sentry.vendor.gson.stream.a aVar = h2Var.f17313i;
        switch (iArr[aVar.peek().ordinal()]) {
            case 1:
                h2Var.beginArray();
                arrayList.add(new b2());
                zB = false;
                break;
            case 2:
                h2Var.endArray();
                zB = b();
                break;
            case 3:
                h2Var.beginObject();
                arrayList.add(new c2());
                zB = false;
                break;
            case 4:
                h2Var.endObject();
                zB = b();
                break;
            case 5:
                arrayList.add(new d2(aVar.nextName()));
                zB = false;
                break;
            case 6:
                final int i10 = 0;
                zB = c(new z1() { // from class: io.sentry.v1
                    @Override // io.sentry.z1
                    public final Object c() {
                        switch (i10) {
                            case 0:
                                return h2Var.nextString();
                            default:
                                return Boolean.valueOf(h2Var.k());
                        }
                    }
                });
                break;
            case 7:
                zB = c(new w1(this, h2Var));
                break;
            case 8:
                final int i11 = 1;
                zB = c(new z1() { // from class: io.sentry.v1
                    @Override // io.sentry.z1
                    public final Object c() {
                        switch (i11) {
                            case 0:
                                return h2Var.nextString();
                            default:
                                return Boolean.valueOf(h2Var.k());
                        }
                    }
                });
                break;
            case 9:
                h2Var.l();
                zB = c(new x1(0));
                break;
            case 10:
                zB = true;
                break;
            default:
                zB = false;
                break;
        }
        if (zB) {
            return;
        }
        d(h2Var);
    }

    public void e() {
        ArrayList arrayList = (ArrayList) this.f17276a;
        if (arrayList.isEmpty()) {
            return;
        }
        arrayList.remove(arrayList.size() - 1);
    }

    public void f(fi.iki.elonen.f fVar, ILogger iLogger, Object obj) throws IOException {
        if (obj == null) {
            fVar.T();
            return;
        }
        if (obj instanceof Character) {
            fVar.value(Character.toString(((Character) obj).charValue()));
            return;
        }
        if (obj instanceof String) {
            fVar.value((String) obj);
            return;
        }
        if (obj instanceof Boolean) {
            fVar.e0(((Boolean) obj).booleanValue());
            return;
        }
        if (obj instanceof Number) {
            fVar.d0((Number) obj);
            return;
        }
        if (obj instanceof Date) {
            try {
                fVar.value(k2.c.r((Date) obj));
                return;
            } catch (Exception e5) {
                iLogger.l(w5.ERROR, "Error when serializing Date", e5);
                fVar.T();
                return;
            }
        }
        if (obj instanceof TimeZone) {
            try {
                fVar.value(((TimeZone) obj).getID());
                return;
            } catch (Exception e6) {
                iLogger.l(w5.ERROR, "Error when serializing TimeZone", e6);
                fVar.T();
                return;
            }
        }
        if (obj instanceof i2) {
            ((i2) obj).serialize(fVar, iLogger);
            return;
        }
        if (obj instanceof Collection) {
            g(fVar, iLogger, (Collection) obj);
            return;
        }
        int i10 = 0;
        if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            ArrayList arrayList = new ArrayList(zArr.length);
            int length = zArr.length;
            while (i10 < length) {
                arrayList.add(Boolean.valueOf(zArr[i10]));
                i10++;
            }
            g(fVar, iLogger, arrayList);
            return;
        }
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            ArrayList arrayList2 = new ArrayList(bArr.length);
            int length2 = bArr.length;
            while (i10 < length2) {
                arrayList2.add(Byte.valueOf(bArr[i10]));
                i10++;
            }
            g(fVar, iLogger, arrayList2);
            return;
        }
        if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            ArrayList arrayList3 = new ArrayList(sArr.length);
            int length3 = sArr.length;
            while (i10 < length3) {
                arrayList3.add(Short.valueOf(sArr[i10]));
                i10++;
            }
            g(fVar, iLogger, arrayList3);
            return;
        }
        if (obj instanceof char[]) {
            char[] cArr = (char[]) obj;
            ArrayList arrayList4 = new ArrayList(cArr.length);
            int length4 = cArr.length;
            while (i10 < length4) {
                arrayList4.add(Character.valueOf(cArr[i10]));
                i10++;
            }
            g(fVar, iLogger, arrayList4);
            return;
        }
        if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            ArrayList arrayList5 = new ArrayList(iArr.length);
            int length5 = iArr.length;
            while (i10 < length5) {
                arrayList5.add(Integer.valueOf(iArr[i10]));
                i10++;
            }
            g(fVar, iLogger, arrayList5);
            return;
        }
        if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            ArrayList arrayList6 = new ArrayList(jArr.length);
            int length6 = jArr.length;
            while (i10 < length6) {
                arrayList6.add(Long.valueOf(jArr[i10]));
                i10++;
            }
            g(fVar, iLogger, arrayList6);
            return;
        }
        if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            ArrayList arrayList7 = new ArrayList(fArr.length);
            int length7 = fArr.length;
            while (i10 < length7) {
                arrayList7.add(Float.valueOf(fArr[i10]));
                i10++;
            }
            g(fVar, iLogger, arrayList7);
            return;
        }
        if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            ArrayList arrayList8 = new ArrayList(dArr.length);
            int length8 = dArr.length;
            while (i10 < length8) {
                arrayList8.add(Double.valueOf(dArr[i10]));
                i10++;
            }
            g(fVar, iLogger, arrayList8);
            return;
        }
        if (obj.getClass().isArray()) {
            g(fVar, iLogger, Arrays.asList((Object[]) obj));
            return;
        }
        if (obj instanceof Map) {
            h(fVar, iLogger, (Map) obj);
            return;
        }
        if (obj instanceof Locale) {
            fVar.value(obj.toString());
            return;
        }
        if (obj instanceof AtomicIntegerArray) {
            AtomicIntegerArray atomicIntegerArray = (AtomicIntegerArray) obj;
            Charset charset = io.sentry.util.i.f17960a;
            int length9 = atomicIntegerArray.length();
            ArrayList arrayList9 = new ArrayList(length9);
            while (i10 < length9) {
                arrayList9.add(Integer.valueOf(atomicIntegerArray.get(i10)));
                i10++;
            }
            g(fVar, iLogger, arrayList9);
            return;
        }
        if (obj instanceof AtomicBoolean) {
            fVar.e0(((AtomicBoolean) obj).get());
            return;
        }
        if (obj instanceof URI) {
            fVar.value(obj.toString());
            return;
        }
        if (obj instanceof InetAddress) {
            fVar.value(obj.toString());
            return;
        }
        if (obj instanceof UUID) {
            fVar.value(obj.toString());
            return;
        }
        if (obj instanceof Currency) {
            fVar.value(obj.toString());
            return;
        }
        if (obj instanceof Calendar) {
            h(fVar, iLogger, io.sentry.util.i.a((Calendar) obj));
            return;
        }
        if (obj.getClass().isEnum()) {
            fVar.value(obj.toString());
            return;
        }
        try {
            f(fVar, iLogger, ((androidx.core.provider.j) this.f17276a).g(iLogger, obj));
        } catch (Exception e10) {
            iLogger.l(w5.ERROR, "Failed serializing unknown object.", e10);
            fVar.value("[OBJECT]");
        }
    }

    public void g(fi.iki.elonen.f fVar, ILogger iLogger, Collection collection) throws IOException {
        io.sentry.vendor.gson.stream.c cVar = (io.sentry.vendor.gson.stream.c) fVar.f15416i;
        cVar.y();
        cVar.i();
        int i10 = cVar.f18021m;
        int[] iArr = cVar.f18020l;
        if (i10 == iArr.length) {
            cVar.f18020l = Arrays.copyOf(iArr, i10 * 2);
        }
        int[] iArr2 = cVar.f18020l;
        int i11 = cVar.f18021m;
        cVar.f18021m = i11 + 1;
        iArr2[i11] = 1;
        cVar.f18019i.write(91);
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            f(fVar, iLogger, it.next());
        }
        cVar.j(1, 2, ']');
    }

    public void h(fi.iki.elonen.f fVar, ILogger iLogger, Map map) throws IOException {
        fVar.j();
        for (Object obj : map.keySet()) {
            if (obj instanceof String) {
                fVar.S((String) obj);
                f(fVar, iLogger, map.get(obj));
            }
        }
        fVar.K();
    }

    public f2(List list) {
        this.f17276a = new ArrayList(list == null ? new ArrayList(0) : list);
    }

    public f2(int i10) {
        this.f17276a = new androidx.core.provider.j(i10, 4);
    }
}
