package androidx.core.provider;

import eb.c0;
import io.sentry.ILogger;
import io.sentry.w5;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.InetAddress;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerArray;
import v9.d1;

/* JADX INFO: loaded from: classes3.dex */
public final class j implements c0, p0.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2158a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2159b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f2160c;

    public /* synthetic */ j(int i10, Object obj, int i11) {
        this.f2158a = i11;
        this.f2159b = i10;
        this.f2160c = obj;
    }

    public Object a() {
        int i10 = this.f2159b;
        if (i10 <= 0) {
            return null;
        }
        int i11 = i10 - 1;
        Object[] objArr = (Object[]) this.f2160c;
        Object obj = objArr[i11];
        objArr[i11] = null;
        this.f2159b = i10 - 1;
        return obj;
    }

    public void b(int i10, int i11) {
        int i12 = i11 + i10;
        char[] cArr = (char[]) this.f2160c;
        if (cArr.length <= i12) {
            int i13 = i10 * 2;
            if (i12 < i13) {
                i12 = i13;
            }
            this.f2160c = Arrays.copyOf(cArr, i12);
        }
    }

    public boolean c() {
        return this.f2159b < ((ArrayList) this.f2160c).size();
    }

    public HashMap d(Map map, ILogger iLogger) {
        HashMap map2 = new HashMap();
        for (Object obj : map.keySet()) {
            Object obj2 = map.get(obj);
            if (obj2 != null) {
                map2.put(obj.toString(), g(iLogger, obj2));
            } else {
                map2.put(obj.toString(), null);
            }
        }
        return map2;
    }

    public void e() {
        eb.g gVar = eb.g.f15137c;
        char[] cArr = (char[]) this.f2160c;
        synchronized (gVar) {
            int i10 = gVar.f15147b;
            if (cArr.length + i10 < eb.e.f15127a) {
                gVar.f15147b = i10 + cArr.length;
                gVar.f15146a.addLast(cArr);
            }
        }
    }

    public boolean f(Object obj) {
        boolean z;
        Object[] objArr = (Object[]) this.f2160c;
        int i10 = this.f2159b;
        int i11 = 0;
        while (true) {
            if (i11 >= i10) {
                z = false;
                break;
            }
            if (objArr[i11] == obj) {
                z = true;
                break;
            }
            i11++;
        }
        if (z) {
            throw new IllegalStateException("Already in the pool!");
        }
        int i12 = this.f2159b;
        if (i12 >= objArr.length) {
            return false;
        }
        objArr[i12] = obj;
        this.f2159b = i12 + 1;
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.util.HashMap] */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.util.HashMap] */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r4v7, types: [java.util.ArrayList] */
    public Object g(ILogger iLogger, Object obj) {
        Object objH;
        HashSet hashSet = (HashSet) this.f2160c;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Character) {
            return obj.toString();
        }
        if ((obj instanceof Number) || (obj instanceof Boolean) || (obj instanceof String)) {
            return obj;
        }
        if (obj instanceof Locale) {
            return obj.toString();
        }
        int i10 = 0;
        if (obj instanceof AtomicIntegerArray) {
            AtomicIntegerArray atomicIntegerArray = (AtomicIntegerArray) obj;
            Charset charset = io.sentry.util.i.f17960a;
            int length = atomicIntegerArray.length();
            ArrayList arrayList = new ArrayList(length);
            while (i10 < length) {
                arrayList.add(Integer.valueOf(atomicIntegerArray.get(i10)));
                i10++;
            }
            return arrayList;
        }
        if (obj instanceof AtomicBoolean) {
            return Boolean.valueOf(((AtomicBoolean) obj).get());
        }
        if (obj instanceof URI) {
            return obj.toString();
        }
        if (obj instanceof InetAddress) {
            return obj.toString();
        }
        if (obj instanceof UUID) {
            return obj.toString();
        }
        if (obj instanceof Currency) {
            return obj.toString();
        }
        if (obj instanceof Calendar) {
            return io.sentry.util.i.a((Calendar) obj);
        }
        if (obj.getClass().isEnum()) {
            return obj.toString();
        }
        if (hashSet.contains(obj)) {
            iLogger.q(w5.INFO, "Cyclic reference detected. Calling toString() on object.", new Object[0]);
            return obj.toString();
        }
        hashSet.add(obj);
        try {
            if (hashSet.size() > this.f2159b) {
                hashSet.remove(obj);
                iLogger.q(w5.INFO, "Max depth exceeded. Calling toString() on object.", new Object[0]);
                return obj.toString();
            }
            try {
                if (obj.getClass().isArray()) {
                    Object[] objArr = (Object[]) obj;
                    objH = new ArrayList();
                    int length2 = objArr.length;
                    while (i10 < length2) {
                        objH.add(g(iLogger, objArr[i10]));
                        i10++;
                    }
                } else if (obj instanceof Collection) {
                    objH = new ArrayList();
                    Iterator it = ((Collection) obj).iterator();
                    while (it.hasNext()) {
                        objH.add(g(iLogger, it.next()));
                    }
                } else if (obj instanceof Map) {
                    objH = d((Map) obj, iLogger);
                } else {
                    objH = h(iLogger, obj);
                    if (objH.isEmpty()) {
                        objH = obj.toString();
                    }
                }
                hashSet.remove(obj);
                return objH;
            } catch (Exception e5) {
                iLogger.l(w5.INFO, "Not serializing object due to throwing sub-path.", e5);
                hashSet.remove(obj);
                return null;
            }
        } catch (Throwable th) {
            hashSet.remove(obj);
            throw th;
        }
    }

    public HashMap h(ILogger iLogger, Object obj) {
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        HashMap map = new HashMap();
        for (Field field : declaredFields) {
            if (!Modifier.isTransient(field.getModifiers()) && !Modifier.isStatic(field.getModifiers())) {
                String name = field.getName();
                try {
                    field.setAccessible(true);
                    map.put(name, g(iLogger, field.get(obj)));
                    field.setAccessible(false);
                } catch (Exception unused) {
                    iLogger.q(w5.INFO, a0.c.l("Cannot access field ", name, "."), new Object[0]);
                }
            }
        }
        return map;
    }

    public void i(String str) {
        int length = str.length();
        if (length == 0) {
            return;
        }
        b(this.f2159b, length);
        str.getChars(0, str.length(), (char[]) this.f2160c, this.f2159b);
        this.f2159b += length;
    }

    public void j(long j10) {
        i(String.valueOf(j10));
    }

    public String toString() {
        switch (this.f2158a) {
            case 2:
                return new String((char[]) this.f2160c, 0, this.f2159b);
            default:
                return super.toString();
        }
    }

    public j(int i10, int i11) {
        this.f2158a = i11;
        switch (i11) {
            case 7:
                if (i10 <= 0) {
                    throw new IllegalArgumentException("The max pool size must be > 0");
                }
                this.f2160c = new Object[i10];
                return;
            default:
                this.f2160c = new HashSet();
                this.f2159b = i10;
                return;
        }
    }

    public j(d1 d1Var, int i10) {
        this.f2158a = 8;
        this.f2160c = d1Var;
        this.f2159b = i10;
    }

    public j(ArrayList arrayList) {
        this.f2158a = 5;
        this.f2160c = arrayList;
    }

    public j(int i10, byte b10) {
        this.f2158a = i10;
        switch (i10) {
            case 6:
                this.f2159b = 4;
                break;
        }
    }
}
