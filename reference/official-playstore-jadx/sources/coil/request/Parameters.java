package coil.request;

import android.content.Collections;
import androidx.compose.foundation.c;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.a0;
import kotlin.collections.i0;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import x6.x;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010(\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 )2\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001:\u0003*)+B\u001d\b\u0002\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0005¢\u0006\u0004\b\u0007\u0010\bB\t\b\u0016¢\u0006\u0004\b\u0007\u0010\tJ!\u0010\r\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u000b*\u00020\n2\u0006\u0010\f\u001a\u00020\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u000f\u001a\u0004\u0018\u00010\u00032\u0006\u0010\f\u001a\u00020\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0011\u001a\u0004\u0018\u00010\u00042\u0006\u0010\f\u001a\u00020\u0003¢\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0005¢\u0006\u0004\b\u0016\u0010\u0017J\u0019\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0004\b\u0018\u0010\u0017J\"\u0010\u001a\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0019H\u0096\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u001a\u0010\u001d\u001a\u00020\u00132\b\u0010\u001c\u001a\u0004\u0018\u00010\nH\u0096\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010 \u001a\u00020\u001fH\u0016¢\u0006\u0004\b \u0010!J\u000f\u0010\"\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\"\u0010#J\r\u0010%\u001a\u00020$¢\u0006\u0004\b%\u0010&R \u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010'R\u0011\u0010(\u001a\u00020\u001f8G¢\u0006\u0006\u001a\u0004\b(\u0010!¨\u0006,"}, d2 = {"Lcoil/request/Parameters;", "", "Lx6/x;", "", "Lcoil/request/Parameters$Entry;", "", "entries", "<init>", "(Ljava/util/Map;)V", "()V", "", "T", "key", "value", "(Ljava/lang/String;)Ljava/lang/Object;", "memoryCacheKey", "(Ljava/lang/String;)Ljava/lang/String;", "entry", "(Ljava/lang/String;)Lcoil/request/Parameters$Entry;", "", "isEmpty", "()Z", "values", "()Ljava/util/Map;", "memoryCacheKeys", "", "iterator", "()Ljava/util/Iterator;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "()Ljava/lang/String;", "Lcoil/request/Parameters$Builder;", "newBuilder", "()Lcoil/request/Parameters$Builder;", "Ljava/util/Map;", ContentDisposition.Parameters.Size, "Companion", "Builder", "Entry", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class Parameters implements Iterable<x>, s7.a {
    public static final Parameters EMPTY = new Parameters();
    private final Map<String, Entry> entries;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\n\u001a\u00020\u0004J\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\bJ&\u0010\r\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\bH\u0007R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcoil/request/Parameters$Builder;", "", "()V", "parameters", "Lcoil/request/Parameters;", "(Lcoil/request/Parameters;)V", "entries", "", "", "Lcoil/request/Parameters$Entry;", "build", "remove", "key", "set", "value", "memoryCacheKey", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Builder {
        private final Map<String, Entry> entries;

        public Builder() {
            this.entries = new LinkedHashMap();
        }

        public static /* synthetic */ Builder set$default(Builder builder, String str, Object obj, String str2, int i10, Object obj2) {
            if ((i10 & 4) != 0) {
                str2 = obj != null ? obj.toString() : null;
            }
            return builder.set(str, obj, str2);
        }

        public final Parameters build() {
            return new Parameters(Collections.toImmutableMap(this.entries), null);
        }

        public final Builder remove(String key) {
            this.entries.remove(key);
            return this;
        }

        public final Builder set(String str, Object obj) {
            return set$default(this, str, obj, null, 4, null);
        }

        public final Builder set(String key, Object value, String memoryCacheKey) {
            this.entries.put(key, new Entry(value, memoryCacheKey));
            return this;
        }

        public Builder(Parameters parameters) {
            this.entries = new LinkedHashMap(parameters.entries);
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0004H\u0016R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0010"}, d2 = {"Lcoil/request/Parameters$Entry;", "", "value", "memoryCacheKey", "", "(Ljava/lang/Object;Ljava/lang/String;)V", "getMemoryCacheKey", "()Ljava/lang/String;", "getValue", "()Ljava/lang/Object;", "equals", "", "other", "hashCode", "", "toString", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Entry {
        private final String memoryCacheKey;
        private final Object value;

        public Entry(Object obj, String str) {
            this.value = obj;
            this.memoryCacheKey = str;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) other;
            return p.a(this.value, entry.value) && p.a(this.memoryCacheKey, entry.memoryCacheKey);
        }

        public final String getMemoryCacheKey() {
            return this.memoryCacheKey;
        }

        public final Object getValue() {
            return this.value;
        }

        public int hashCode() {
            Object obj = this.value;
            int iHashCode = (obj != null ? obj.hashCode() : 0) * 31;
            String str = this.memoryCacheKey;
            return iHashCode + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("Entry(value=");
            sb2.append(this.value);
            sb2.append(", memoryCacheKey=");
            return c.u(sb2, this.memoryCacheKey, ')');
        }
    }

    public /* synthetic */ Parameters(Map map, h hVar) {
        this(map);
    }

    public final Entry entry(String key) {
        return this.entries.get(key);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof Parameters) && p.a(this.entries, ((Parameters) other).entries);
    }

    public int hashCode() {
        return this.entries.hashCode();
    }

    public final boolean isEmpty() {
        return this.entries.isEmpty();
    }

    @Override // java.lang.Iterable
    public Iterator<x> iterator() {
        Map<String, Entry> map = this.entries;
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<String, Entry> entry : map.entrySet()) {
            arrayList.add(new x(entry.getKey(), entry.getValue()));
        }
        return arrayList.iterator();
    }

    public final String memoryCacheKey(String key) {
        Entry entry = this.entries.get(key);
        if (entry != null) {
            return entry.getMemoryCacheKey();
        }
        return null;
    }

    public final Map<String, String> memoryCacheKeys() {
        if (isEmpty()) {
            return a0.f19683i;
        }
        Map<String, Entry> map = this.entries;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, Entry> entry : map.entrySet()) {
            String memoryCacheKey = entry.getValue().getMemoryCacheKey();
            if (memoryCacheKey != null) {
                linkedHashMap.put(entry.getKey(), memoryCacheKey);
            }
        }
        return linkedHashMap;
    }

    public final Builder newBuilder() {
        return new Builder(this);
    }

    public final int size() {
        return this.entries.size();
    }

    public String toString() {
        return "Parameters(entries=" + this.entries + ')';
    }

    public final <T> T value(String key) {
        Entry entry = this.entries.get(key);
        if (entry != null) {
            return (T) entry.getValue();
        }
        return null;
    }

    public final Map<String, Object> values() {
        if (isEmpty()) {
            return a0.f19683i;
        }
        Map<String, Entry> map = this.entries;
        LinkedHashMap linkedHashMap = new LinkedHashMap(i0.q0(map.size()));
        Iterator<T> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap.put(entry.getKey(), ((Entry) entry.getValue()).getValue());
        }
        return linkedHashMap;
    }

    private Parameters(Map<String, Entry> map) {
        this.entries = map;
    }

    public Parameters() {
        this(a0.f19683i);
    }
}
