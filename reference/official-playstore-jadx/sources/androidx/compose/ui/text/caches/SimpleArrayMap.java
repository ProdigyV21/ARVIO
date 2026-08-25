package androidx.compose.ui.text.caches;

import io.ktor.http.ContentDisposition;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b#\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\t\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003B\u0013\b\u0017\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007B\u001f\b\u0016\u0012\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0000¢\u0006\u0004\b\u0006\u0010\tJ\u001f\u0010\f\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0004H\u0004¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0004H\u0004¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0004¢\u0006\u0004\b\u0014\u0010\u0007J\u0015\u0010\u0016\u001a\u00020\u00152\u0006\u0010\n\u001a\u00028\u0000¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0018\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00028\u0001H\u0000¢\u0006\u0004\b\u001b\u0010\u0019J\u0015\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00028\u0001¢\u0006\u0004\b\u001d\u0010\u0017J\u001a\u0010\u001e\u001a\u0004\u0018\u00018\u00012\u0006\u0010\n\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\u001d\u0010!\u001a\u00028\u00012\u0006\u0010\n\u001a\u00028\u00002\u0006\u0010 \u001a\u00028\u0001¢\u0006\u0004\b!\u0010\"J\u0015\u0010$\u001a\u00028\u00002\u0006\u0010#\u001a\u00020\u0004¢\u0006\u0004\b$\u0010%J\u0015\u0010&\u001a\u00028\u00012\u0006\u0010#\u001a\u00020\u0004¢\u0006\u0004\b&\u0010%J\u001d\u0010'\u001a\u00028\u00012\u0006\u0010#\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00028\u0001¢\u0006\u0004\b'\u0010(J\r\u0010)\u001a\u00020\u0015¢\u0006\u0004\b)\u0010*J\u001f\u0010+\u001a\u0004\u0018\u00018\u00012\u0006\u0010\n\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00028\u0001¢\u0006\u0004\b+\u0010\"J%\u0010-\u001a\u00020\u00102\u0016\u0010,\u001a\u0012\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0006\b\u0001\u0012\u00028\u00010\u0000¢\u0006\u0004\b-\u0010\tJ\u001f\u0010.\u001a\u0004\u0018\u00018\u00012\u0006\u0010\n\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00028\u0001¢\u0006\u0004\b.\u0010\"J\u0017\u0010/\u001a\u0004\u0018\u00018\u00012\u0006\u0010\n\u001a\u00028\u0000¢\u0006\u0004\b/\u0010\u001fJ\u001d\u0010/\u001a\u00020\u00152\u0006\u0010\n\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00028\u0001¢\u0006\u0004\b/\u00100J\u0017\u00101\u001a\u0004\u0018\u00018\u00012\u0006\u0010#\u001a\u00020\u0004¢\u0006\u0004\b1\u0010%J\u001f\u00102\u001a\u0004\u0018\u00018\u00012\u0006\u0010\n\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00028\u0001¢\u0006\u0004\b2\u0010\"J%\u00102\u001a\u00020\u00152\u0006\u0010\n\u001a\u00028\u00002\u0006\u00103\u001a\u00028\u00012\u0006\u00104\u001a\u00028\u0001¢\u0006\u0004\b2\u00105J\u001a\u00107\u001a\u00020\u00152\b\u00106\u001a\u0004\u0018\u00010\u0003H\u0096\u0002¢\u0006\u0004\b7\u0010\u0017J\u000f\u00108\u001a\u00020\u0004H\u0016¢\u0006\u0004\b8\u0010\u000fJ\u000f\u0010:\u001a\u000209H\u0016¢\u0006\u0004\b:\u0010;R\u0016\u0010=\u001a\u00020<8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b=\u0010>R\u001e\u0010@\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030?8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b@\u0010AR\"\u0010B\u001a\u00020\u00048\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\bB\u0010C\u001a\u0004\bD\u0010\u000f\"\u0004\bE\u0010\u0007R\u0017\u0010F\u001a\u00020\u00048G¢\u0006\f\u0012\u0004\bG\u0010\u0012\u001a\u0004\bF\u0010\u000f¨\u0006H"}, d2 = {"Landroidx/compose/ui/text/caches/SimpleArrayMap;", "K", "V", "", "", "capacity", "<init>", "(I)V", "map", "(Landroidx/compose/ui/text/caches/SimpleArrayMap;)V", "key", "hash", "indexOf", "(Ljava/lang/Object;I)I", "indexOfNull", "()I", "Lx6/t0;", "clear", "()V", "minimumCapacity", "ensureCapacity", "", "containsKey", "(Ljava/lang/Object;)Z", "indexOfKey", "(Ljava/lang/Object;)I", "value", "indexOfValue$ui_text_release", "indexOfValue", "containsValue", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "defaultValue", "getOrDefault", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "index", "keyAt", "(I)Ljava/lang/Object;", "valueAt", "setValueAt", "(ILjava/lang/Object;)Ljava/lang/Object;", "isEmpty", "()Z", "put", "array", "putAll", "putIfAbsent", "remove", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "removeAt", "replace", "oldValue", "newValue", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z", "other", "equals", "hashCode", "", "toString", "()Ljava/lang/String;", "", "hashes", "[I", "", "keyValues", "[Ljava/lang/Object;", "_size", "I", "get_size", "set_size", ContentDisposition.Parameters.Size, "size$annotations", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SimpleArrayMap<K, V> {
    public static final int $stable = 8;
    private int _size;
    private int[] hashes;
    private Object[] keyValues;

    public SimpleArrayMap() {
        this(0, 1, null);
    }

    public static /* synthetic */ void size$annotations() {
    }

    public final void clear() {
        if (this._size > 0) {
            this.hashes = ContainerHelpersKt.EMPTY_INTS;
            this.keyValues = ContainerHelpersKt.EMPTY_OBJECTS;
            this._size = 0;
        }
        if (this._size > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public final boolean containsKey(K key) {
        return indexOfKey(key) >= 0;
    }

    public final boolean containsValue(V value) {
        return indexOfValue$ui_text_release(value) >= 0;
    }

    public final void ensureCapacity(int minimumCapacity) {
        int i10 = this._size;
        int[] iArr = this.hashes;
        if (iArr.length < minimumCapacity) {
            this.hashes = Arrays.copyOf(iArr, minimumCapacity);
            this.keyValues = Arrays.copyOf(this.keyValues, minimumCapacity << 1);
        }
        if (this._size != i10) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        try {
            if (other instanceof SimpleArrayMap) {
                SimpleArrayMap simpleArrayMap = (SimpleArrayMap) other;
                int i10 = this._size;
                if (i10 != simpleArrayMap._size) {
                    return false;
                }
                for (int i11 = 0; i11 < i10; i11++) {
                    K kKeyAt = keyAt(i11);
                    V vValueAt = valueAt(i11);
                    Object obj = simpleArrayMap.get(kKeyAt);
                    if (vValueAt == null) {
                        if (obj != null || !simpleArrayMap.containsKey(kKeyAt)) {
                            return false;
                        }
                    } else if (!vValueAt.equals(obj)) {
                        return false;
                    }
                }
                return true;
            }
            if (!(other instanceof Map) || this._size != ((Map) other).size()) {
                return false;
            }
            int i12 = this._size;
            for (int i13 = 0; i13 < i12; i13++) {
                K kKeyAt2 = keyAt(i13);
                V vValueAt2 = valueAt(i13);
                Object obj2 = ((Map) other).get(kKeyAt2);
                if (vValueAt2 == null) {
                    if (obj2 != null || !((Map) other).containsKey(kKeyAt2)) {
                        return false;
                    }
                } else if (!vValueAt2.equals(obj2)) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException | NullPointerException unused) {
        }
        return false;
    }

    public final V get(K key) {
        int iIndexOfKey = indexOfKey(key);
        if (iIndexOfKey >= 0) {
            return (V) this.keyValues[(iIndexOfKey << 1) + 1];
        }
        return null;
    }

    public final V getOrDefault(K key, V defaultValue) {
        int iIndexOfKey = indexOfKey(key);
        return iIndexOfKey >= 0 ? (V) this.keyValues[(iIndexOfKey << 1) + 1] : defaultValue;
    }

    public final int get_size() {
        return this._size;
    }

    public int hashCode() {
        int[] iArr = this.hashes;
        Object[] objArr = this.keyValues;
        int i10 = this._size;
        int i11 = 1;
        int i12 = 0;
        int iHashCode = 0;
        while (i12 < i10) {
            Object obj = objArr[i11];
            iHashCode += (obj != null ? obj.hashCode() : 0) ^ iArr[i12];
            i12++;
            i11 += 2;
        }
        return iHashCode;
    }

    public final int indexOf(Object key, int hash) {
        int i10 = this._size;
        if (i10 == 0) {
            return -1;
        }
        int iBinarySearchInternal = ContainerHelpersKt.binarySearchInternal(this.hashes, i10, hash);
        if (iBinarySearchInternal < 0 || p.a(key, this.keyValues[iBinarySearchInternal << 1])) {
            return iBinarySearchInternal;
        }
        int i11 = iBinarySearchInternal + 1;
        while (i11 < i10 && this.hashes[i11] == hash) {
            if (p.a(key, this.keyValues[i11 << 1])) {
                return i11;
            }
            i11++;
        }
        for (int i12 = iBinarySearchInternal - 1; i12 >= 0 && this.hashes[i12] == hash; i12--) {
            if (p.a(key, this.keyValues[i12 << 1])) {
                return i12;
            }
        }
        return ~i11;
    }

    public final int indexOfKey(Object key) {
        return key == null ? indexOfNull() : indexOf(key, key.hashCode());
    }

    public final int indexOfNull() {
        int i10 = this._size;
        if (i10 == 0) {
            return -1;
        }
        int iBinarySearchInternal = ContainerHelpersKt.binarySearchInternal(this.hashes, i10, 0);
        if (iBinarySearchInternal < 0 || this.keyValues[iBinarySearchInternal << 1] == null) {
            return iBinarySearchInternal;
        }
        int i11 = iBinarySearchInternal + 1;
        while (i11 < i10 && this.hashes[i11] == 0) {
            if (this.keyValues[i11 << 1] == null) {
                return i11;
            }
            i11++;
        }
        for (int i12 = iBinarySearchInternal - 1; i12 >= 0 && this.hashes[i12] == 0; i12--) {
            if (this.keyValues[i12 << 1] == null) {
                return i12;
            }
        }
        return ~i11;
    }

    public final int indexOfValue$ui_text_release(V value) {
        int i10 = this._size << 1;
        Object[] objArr = this.keyValues;
        if (value == null) {
            for (int i11 = 1; i11 < i10; i11 += 2) {
                if (objArr[i11] == null) {
                    return i11 >> 1;
                }
            }
            return -1;
        }
        for (int i12 = 1; i12 < i10; i12 += 2) {
            if (value.equals(objArr[i12])) {
                return i12 >> 1;
            }
        }
        return -1;
    }

    public final boolean isEmpty() {
        return this._size <= 0;
    }

    public final K keyAt(int index) {
        return (K) this.keyValues[index << 1];
    }

    public final V put(K key, V value) {
        int iHashCode;
        int iIndexOf;
        int i10 = this._size;
        if (key == null) {
            iIndexOf = indexOfNull();
            iHashCode = 0;
        } else {
            iHashCode = key.hashCode();
            iIndexOf = indexOf(key, iHashCode);
        }
        if (iIndexOf >= 0) {
            int i11 = (iIndexOf << 1) + 1;
            Object[] objArr = this.keyValues;
            V v2 = (V) objArr[i11];
            objArr[i11] = value;
            return v2;
        }
        int i12 = ~iIndexOf;
        int[] iArr = this.hashes;
        if (i10 >= iArr.length) {
            int i13 = 8;
            if (i10 >= 8) {
                i13 = (i10 >> 1) + i10;
            } else if (i10 < 4) {
                i13 = 4;
            }
            this.hashes = Arrays.copyOf(iArr, i13);
            this.keyValues = Arrays.copyOf(this.keyValues, i13 << 1);
            if (i10 != this._size) {
                throw new ConcurrentModificationException();
            }
        }
        if (i12 < i10) {
            int[] iArr2 = this.hashes;
            int i14 = i12 + 1;
            r.K(i14, i12, i10, iArr2, iArr2);
            Object[] objArr2 = this.keyValues;
            r.N(objArr2, objArr2, i14 << 1, i12 << 1, this._size << 1);
        }
        int i15 = this._size;
        if (i10 == i15) {
            int[] iArr3 = this.hashes;
            if (i12 < iArr3.length) {
                iArr3[i12] = iHashCode;
                Object[] objArr3 = this.keyValues;
                int i16 = i12 << 1;
                objArr3[i16] = key;
                objArr3[i16 + 1] = value;
                this._size = i15 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public final void putAll(SimpleArrayMap<? extends K, ? extends V> array) {
        int i10 = array._size;
        ensureCapacity(this._size + i10);
        if (this._size != 0) {
            for (int i11 = 0; i11 < i10; i11++) {
                put(array.keyAt(i11), array.valueAt(i11));
            }
        } else if (i10 > 0) {
            r.K(0, 0, i10, array.hashes, this.hashes);
            r.N(array.keyValues, this.keyValues, 0, 0, i10 << 1);
            this._size = i10;
        }
    }

    public final V putIfAbsent(K key, V value) {
        V v2 = get(key);
        return v2 == null ? put(key, value) : v2;
    }

    public final V remove(K key) {
        int iIndexOfKey = indexOfKey(key);
        if (iIndexOfKey >= 0) {
            return removeAt(iIndexOfKey);
        }
        return null;
    }

    public final V removeAt(int index) {
        Object[] objArr = this.keyValues;
        int i10 = index << 1;
        V v2 = (V) objArr[i10 + 1];
        int i11 = this._size;
        if (i11 <= 1) {
            clear();
            return v2;
        }
        int i12 = i11 - 1;
        int[] iArr = this.hashes;
        if (iArr.length <= 8 || i11 >= iArr.length / 3) {
            if (index < i12) {
                int i13 = index + 1;
                r.K(index, i13, i11, iArr, iArr);
                Object[] objArr2 = this.keyValues;
                r.N(objArr2, objArr2, i10, i13 << 1, i11 << 1);
            }
            Object[] objArr3 = this.keyValues;
            int i14 = i12 << 1;
            objArr3[i14] = null;
            objArr3[i14 + 1] = null;
        } else {
            int i15 = i11 > 8 ? i11 + (i11 >> 1) : 8;
            int[] iArr2 = new int[i15];
            this.hashes = iArr2;
            this.keyValues = new Object[i15 << 1];
            if (index > 0) {
                r.K(0, 0, index, iArr, iArr2);
                r.N(objArr, this.keyValues, 0, 0, i10);
            }
            if (index < i12) {
                int i16 = index + 1;
                r.K(index, i16, i11, iArr, this.hashes);
                r.N(objArr, this.keyValues, i10, i16 << 1, i11 << 1);
            }
        }
        if (i11 != this._size) {
            throw new ConcurrentModificationException();
        }
        this._size = i12;
        return v2;
    }

    public final V replace(K key, V value) {
        int iIndexOfKey = indexOfKey(key);
        if (iIndexOfKey >= 0) {
            return setValueAt(iIndexOfKey, value);
        }
        return null;
    }

    public final V setValueAt(int index, V value) {
        int i10 = (index << 1) + 1;
        Object[] objArr = this.keyValues;
        V v2 = (V) objArr[i10];
        objArr[i10] = value;
        return v2;
    }

    public final void set_size(int i10) {
        this._size = i10;
    }

    public final int size() {
        return this._size;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb2 = new StringBuilder(this._size * 28);
        sb2.append('{');
        int i10 = this._size;
        for (int i11 = 0; i11 < i10; i11++) {
            if (i11 > 0) {
                sb2.append(", ");
            }
            K kKeyAt = keyAt(i11);
            if (kKeyAt != this) {
                sb2.append(kKeyAt);
            } else {
                sb2.append("(this Map)");
            }
            sb2.append('=');
            V vValueAt = valueAt(i11);
            if (vValueAt != this) {
                sb2.append(vValueAt);
            } else {
                sb2.append("(this Map)");
            }
        }
        sb2.append('}');
        return sb2.toString();
    }

    public final V valueAt(int index) {
        return (V) this.keyValues[(index << 1) + 1];
    }

    public SimpleArrayMap(int i10) {
        if (i10 == 0) {
            this.hashes = ContainerHelpersKt.EMPTY_INTS;
            this.keyValues = ContainerHelpersKt.EMPTY_OBJECTS;
        } else {
            this.hashes = new int[i10];
            this.keyValues = new Object[i10 << 1];
        }
        this._size = 0;
    }

    public final boolean remove(K key, V value) {
        int iIndexOfKey = indexOfKey(key);
        if (iIndexOfKey < 0 || !p.a(value, valueAt(iIndexOfKey))) {
            return false;
        }
        removeAt(iIndexOfKey);
        return true;
    }

    public final boolean replace(K key, V oldValue, V newValue) {
        int iIndexOfKey = indexOfKey(key);
        if (iIndexOfKey < 0 || valueAt(iIndexOfKey) != oldValue) {
            return false;
        }
        setValueAt(iIndexOfKey, newValue);
        return true;
    }

    public /* synthetic */ SimpleArrayMap(int i10, int i11, h hVar) {
        this((i11 & 1) != 0 ? 0 : i10);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SimpleArrayMap(SimpleArrayMap<K, V> simpleArrayMap) {
        this(0, 1, null);
        if (simpleArrayMap != 0) {
            putAll(simpleArrayMap);
        }
    }
}
