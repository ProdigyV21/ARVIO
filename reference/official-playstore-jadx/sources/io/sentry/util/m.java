package io.sentry.util;

import io.sentry.ILogger;
import io.sentry.k3;
import io.sentry.u1;
import io.sentry.w5;
import j$.util.DesugarTimeZone;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes5.dex */
public final class m implements k3 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayDeque f17966i;

    public m(Map map) {
        ArrayDeque arrayDeque = new ArrayDeque();
        this.f17966i = arrayDeque;
        arrayDeque.addLast(new AbstractMap.SimpleEntry(null, map));
    }

    @Override // io.sentry.k3
    public final Float B0() throws IOException {
        Object objI = i();
        if (objI instanceof Number) {
            return Float.valueOf(((Number) objI).floatValue());
        }
        return null;
    }

    @Override // io.sentry.k3
    public final Double F() throws IOException {
        Object objI = i();
        if (objI instanceof Number) {
            return Double.valueOf(((Number) objI).doubleValue());
        }
        return null;
    }

    @Override // io.sentry.k3
    public final Object F0() {
        return i();
    }

    @Override // io.sentry.k3
    public final Date I(ILogger iLogger) {
        String str = (String) i();
        if (str == null) {
            return null;
        }
        try {
            try {
                return k2.c.o(str);
            } catch (Exception unused) {
                return k2.c.p(str);
            }
        } catch (Exception e5) {
            iLogger.l(w5.ERROR, "Error when deserializing millis timestamp format.", e5);
            return null;
        }
    }

    @Override // io.sentry.k3
    public final ArrayList I0(ILogger iLogger, u1 u1Var) throws IOException {
        if (peek() == io.sentry.vendor.gson.stream.b.NULL) {
            if (i() == null) {
                return null;
            }
            throw new IOException("Expected null but was " + peek());
        }
        try {
            beginArray();
            ArrayList arrayList = new ArrayList();
            while (peek() != io.sentry.vendor.gson.stream.b.END_ARRAY) {
                int size = this.f17966i.size();
                try {
                    arrayList.add(u1Var.a(this, iLogger));
                } catch (Exception e5) {
                    iLogger.l(w5.WARNING, "Failed to deserialize object in list.", e5);
                    j(size);
                }
            }
            endArray();
            return arrayList;
        } catch (Exception e6) {
            throw new IOException(e6);
        }
    }

    @Override // io.sentry.k3
    public final Boolean L() {
        return (Boolean) i();
    }

    @Override // io.sentry.k3
    public final Object P(ILogger iLogger, u1 u1Var) {
        ArrayDeque arrayDeque = this.f17966i;
        Map.Entry entry = (Map.Entry) arrayDeque.peekLast();
        if (entry == null) {
            return null;
        }
        Object value = entry.getValue();
        if (iLogger != null) {
            return u1Var.a(this, iLogger);
        }
        arrayDeque.removeLast();
        return value;
    }

    @Override // io.sentry.k3
    public final void beginArray() throws IOException {
        ArrayDeque arrayDeque = this.f17966i;
        Map.Entry entry = (Map.Entry) arrayDeque.peekLast();
        if (entry == null) {
            throw new IOException("No more entries");
        }
        Object value = entry.getValue();
        if (!(value instanceof List)) {
            throw new IOException("Current token is not an object");
        }
        arrayDeque.removeLast();
        arrayDeque.addLast(new AbstractMap.SimpleEntry(null, io.sentry.vendor.gson.stream.b.END_ARRAY));
        List list = (List) value;
        for (int size = list.size() - 1; size >= 0; size--) {
            arrayDeque.addLast(new AbstractMap.SimpleEntry(null, list.get(size)));
        }
    }

    @Override // io.sentry.k3
    public final void beginObject() throws IOException {
        ArrayDeque arrayDeque = this.f17966i;
        Map.Entry entry = (Map.Entry) arrayDeque.peekLast();
        if (entry == null) {
            throw new IOException("No more entries");
        }
        Object value = entry.getValue();
        if (!(value instanceof Map)) {
            throw new IOException("Current token is not an object");
        }
        arrayDeque.removeLast();
        arrayDeque.addLast(new AbstractMap.SimpleEntry(null, io.sentry.vendor.gson.stream.b.END_OBJECT));
        Iterator it = ((Map) value).entrySet().iterator();
        while (it.hasNext()) {
            arrayDeque.addLast((Map.Entry) it.next());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f17966i.clear();
    }

    @Override // io.sentry.k3
    public final void endArray() {
        ArrayDeque arrayDeque = this.f17966i;
        if (arrayDeque.size() > 1) {
            arrayDeque.removeLast();
        }
    }

    @Override // io.sentry.k3
    public final void endObject() {
        ArrayDeque arrayDeque = this.f17966i;
        if (arrayDeque.size() > 1) {
            arrayDeque.removeLast();
        }
    }

    @Override // io.sentry.k3
    public final Integer f0() throws IOException {
        Object objI = i();
        if (objI instanceof Number) {
            return Integer.valueOf(((Number) objI).intValue());
        }
        return null;
    }

    @Override // io.sentry.k3
    public final Long h0() throws IOException {
        Object objI = i();
        if (objI instanceof Number) {
            return Long.valueOf(((Number) objI).longValue());
        }
        return null;
    }

    @Override // io.sentry.k3
    public final boolean hasNext() {
        return !this.f17966i.isEmpty();
    }

    public final Object i() throws IOException {
        try {
            ArrayDeque arrayDeque = this.f17966i;
            Map.Entry entry = (Map.Entry) arrayDeque.peekLast();
            if (entry == null) {
                return null;
            }
            Object value = entry.getValue();
            arrayDeque.removeLast();
            return value;
        } catch (Exception e5) {
            throw new IOException(e5);
        }
    }

    public final void j(int i10) {
        while (true) {
            ArrayDeque arrayDeque = this.f17966i;
            if (arrayDeque.isEmpty() || arrayDeque.size() < i10) {
                return;
            } else {
                arrayDeque.removeLast();
            }
        }
    }

    @Override // io.sentry.k3
    public final String l0() {
        return (String) i();
    }

    @Override // io.sentry.k3
    public final HashMap m0(ILogger iLogger, u1 u1Var) throws IOException {
        if (peek() == io.sentry.vendor.gson.stream.b.NULL) {
            if (i() == null) {
                return null;
            }
            throw new IOException("Expected null but was " + peek());
        }
        try {
            beginObject();
            HashMap map = new HashMap();
            if (peek() == io.sentry.vendor.gson.stream.b.NAME) {
                while (true) {
                    String strNextName = nextName();
                    int size = this.f17966i.size();
                    try {
                        map.put(strNextName, u1Var.a(this, iLogger));
                    } catch (Exception e5) {
                        iLogger.l(w5.WARNING, "Failed to deserialize object in map.", e5);
                        j(size);
                    }
                    if (peek() != io.sentry.vendor.gson.stream.b.BEGIN_OBJECT && peek() != io.sentry.vendor.gson.stream.b.NAME) {
                        break;
                    }
                }
            }
            endObject();
            return map;
        } catch (Exception e6) {
            throw new IOException(e6);
        }
    }

    @Override // io.sentry.k3
    public final double nextDouble() throws IOException {
        Object objI = i();
        if (objI instanceof Number) {
            return ((Number) objI).doubleValue();
        }
        throw new IOException("Expected double");
    }

    @Override // io.sentry.k3
    public final float nextFloat() throws IOException {
        Object objI = i();
        if (objI instanceof Number) {
            return ((Number) objI).floatValue();
        }
        throw new IOException("Expected float");
    }

    @Override // io.sentry.k3
    public final int nextInt() throws IOException {
        Object objI = i();
        if (objI instanceof Number) {
            return ((Number) objI).intValue();
        }
        throw new IOException("Expected int");
    }

    @Override // io.sentry.k3
    public final long nextLong() throws IOException {
        Object objI = i();
        if (objI instanceof Number) {
            return ((Number) objI).longValue();
        }
        throw new IOException("Expected long");
    }

    @Override // io.sentry.k3
    public final String nextName() throws IOException {
        Map.Entry entry = (Map.Entry) this.f17966i.peekLast();
        if (entry != null && entry.getKey() != null) {
            return (String) entry.getKey();
        }
        throw new IOException("Expected a name but was " + peek());
    }

    @Override // io.sentry.k3
    public final String nextString() throws IOException {
        String str = (String) i();
        if (str != null) {
            return str;
        }
        throw new IOException("Expected string");
    }

    @Override // io.sentry.k3
    public final io.sentry.vendor.gson.stream.b peek() {
        ArrayDeque arrayDeque = this.f17966i;
        if (arrayDeque.isEmpty()) {
            return io.sentry.vendor.gson.stream.b.END_DOCUMENT;
        }
        Map.Entry entry = (Map.Entry) arrayDeque.peekLast();
        if (entry == null) {
            return io.sentry.vendor.gson.stream.b.END_DOCUMENT;
        }
        if (entry.getKey() != null) {
            return io.sentry.vendor.gson.stream.b.NAME;
        }
        Object value = entry.getValue();
        return value instanceof Map ? io.sentry.vendor.gson.stream.b.BEGIN_OBJECT : value instanceof List ? io.sentry.vendor.gson.stream.b.BEGIN_ARRAY : value instanceof String ? io.sentry.vendor.gson.stream.b.STRING : value instanceof Number ? io.sentry.vendor.gson.stream.b.NUMBER : value instanceof Boolean ? io.sentry.vendor.gson.stream.b.BOOLEAN : value instanceof io.sentry.vendor.gson.stream.b ? (io.sentry.vendor.gson.stream.b) value : io.sentry.vendor.gson.stream.b.END_DOCUMENT;
    }

    @Override // io.sentry.k3
    public final void setLenient(boolean z) {
    }

    @Override // io.sentry.k3
    public final void skipValue() {
        ArrayDeque arrayDeque = this.f17966i;
        if (arrayDeque.isEmpty()) {
            return;
        }
        arrayDeque.removeLast();
    }

    @Override // io.sentry.k3
    public final void t(ILogger iLogger, AbstractMap abstractMap, String str) {
        int size = this.f17966i.size();
        try {
            abstractMap.put(str, i());
        } catch (Exception e5) {
            iLogger.k(w5.ERROR, e5, "Error deserializing unknown key: %s", str);
            j(size);
        }
    }

    @Override // io.sentry.k3
    public final TimeZone v(ILogger iLogger) {
        String str = (String) i();
        if (str != null) {
            return DesugarTimeZone.getTimeZone(str);
        }
        return null;
    }
}
