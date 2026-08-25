package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.ActualJvm_jvmKt;
import androidx.compose.runtime.WeakReference;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.collections.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001f\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\b\u0010\tJ'\u0010\f\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0004\b\u000f\u0010\u0010J$\u0010\u0014\u001a\u00020\u00132\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000e0\u0011H\u0086\b¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0018\u001a\u00020\u000eH\u0001¢\u0006\u0004\b\u0016\u0010\u0017R\"\u0010\u0019\u001a\u00020\u00068\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\"\u0010 \u001a\u00020\u001f8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R0\u0010(\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010'0&8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-¨\u0006."}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotWeakSet;", "", "T", "<init>", "()V", "value", "", "hash", "find", "(Ljava/lang/Object;I)I", "midIndex", "valueHash", "findExactIndex", "(ILjava/lang/Object;I)I", "", "add", "(Ljava/lang/Object;)Z", "Lkotlin/Function1;", "block", "Lx6/t0;", "removeIf", "(Lr7/l;)V", "isValid$runtime_release", "()Z", "isValid", ContentDisposition.Parameters.Size, "I", "getSize$runtime_release", "()I", "setSize$runtime_release", "(I)V", "", "hashes", "[I", "getHashes$runtime_release", "()[I", "setHashes$runtime_release", "([I)V", "", "Landroidx/compose/runtime/WeakReference;", "values", "[Landroidx/compose/runtime/WeakReference;", "getValues$runtime_release", "()[Landroidx/compose/runtime/WeakReference;", "setValues$runtime_release", "([Landroidx/compose/runtime/WeakReference;)V", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SnapshotWeakSet<T> {
    public static final int $stable = 8;
    private int size;
    private int[] hashes = new int[16];
    private WeakReference<T>[] values = new WeakReference[16];

    private final int find(T value, int hash) {
        int i10 = this.size - 1;
        int i11 = 0;
        while (i11 <= i10) {
            int i12 = (i11 + i10) >>> 1;
            int i13 = this.hashes[i12];
            if (i13 < hash) {
                i11 = i12 + 1;
            } else {
                if (i13 <= hash) {
                    WeakReference<T> weakReference = this.values[i12];
                    return value == (weakReference != null ? weakReference.get() : null) ? i12 : findExactIndex(i12, value, hash);
                }
                i10 = i12 - 1;
            }
        }
        return -(i11 + 1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x001d, code lost:
    
        r4 = r4 + 1;
        r0 = r3.size;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0021, code lost:
    
        if (r4 >= r0) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0027, code lost:
    
        if (r3.hashes[r4] == r6) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x002c, code lost:
    
        return -(r4 + 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x002d, code lost:
    
        r2 = r3.values[r4];
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0031, code lost:
    
        if (r2 == null) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0033, code lost:
    
        r2 = r2.get();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0038, code lost:
    
        r2 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0039, code lost:
    
        if (r2 != r5) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x003b, code lost:
    
        return r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x003c, code lost:
    
        r4 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x003f, code lost:
    
        r4 = r3.size;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int findExactIndex(int r4, T r5, int r6) {
        /*
            r3 = this;
            int r0 = r4 + (-1)
        L2:
            r1 = 0
            r2 = -1
            if (r2 >= r0) goto L1d
            int[] r2 = r3.hashes
            r2 = r2[r0]
            if (r2 == r6) goto Ld
            goto L1d
        Ld:
            androidx.compose.runtime.WeakReference<T>[] r2 = r3.values
            r2 = r2[r0]
            if (r2 == 0) goto L17
            java.lang.Object r1 = r2.get()
        L17:
            if (r1 != r5) goto L1a
            return r0
        L1a:
            int r0 = r0 + (-1)
            goto L2
        L1d:
            int r4 = r4 + 1
            int r0 = r3.size
        L21:
            if (r4 >= r0) goto L3f
            int[] r2 = r3.hashes
            r2 = r2[r4]
            if (r2 == r6) goto L2d
        L29:
            int r4 = r4 + 1
            int r4 = -r4
            return r4
        L2d:
            androidx.compose.runtime.WeakReference<T>[] r2 = r3.values
            r2 = r2[r4]
            if (r2 == 0) goto L38
            java.lang.Object r2 = r2.get()
            goto L39
        L38:
            r2 = r1
        L39:
            if (r2 != r5) goto L3c
            return r4
        L3c:
            int r4 = r4 + 1
            goto L21
        L3f:
            int r4 = r3.size
            goto L29
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotWeakSet.findExactIndex(int, java.lang.Object, int):int");
    }

    public final boolean add(T value) {
        int iFind;
        int i10 = this.size;
        int iIdentityHashCode = ActualJvm_jvmKt.identityHashCode(value);
        if (i10 > 0) {
            iFind = find(value, iIdentityHashCode);
            if (iFind >= 0) {
                return false;
            }
        } else {
            iFind = -1;
        }
        int i11 = -(iFind + 1);
        WeakReference<T>[] weakReferenceArr = this.values;
        int length = weakReferenceArr.length;
        if (i10 == length) {
            int i12 = length * 2;
            WeakReference<T>[] weakReferenceArr2 = new WeakReference[i12];
            int[] iArr = new int[i12];
            int i13 = i11 + 1;
            r.N(weakReferenceArr, weakReferenceArr2, i13, i11, i10);
            r.R(this.values, weakReferenceArr2, 0, 0, i11, 6);
            r.K(i13, i11, i10, this.hashes, iArr);
            r.O(0, i11, 6, this.hashes, iArr);
            this.values = weakReferenceArr2;
            this.hashes = iArr;
        } else {
            int i14 = i11 + 1;
            r.N(weakReferenceArr, weakReferenceArr, i14, i11, i10);
            int[] iArr2 = this.hashes;
            r.K(i14, i11, i10, iArr2, iArr2);
        }
        this.values[i11] = new WeakReference<>(value);
        this.hashes[i11] = iIdentityHashCode;
        this.size++;
        return true;
    }

    /* JADX INFO: renamed from: getHashes$runtime_release, reason: from getter */
    public final int[] getHashes() {
        return this.hashes;
    }

    /* JADX INFO: renamed from: getSize$runtime_release, reason: from getter */
    public final int getSize() {
        return this.size;
    }

    public final WeakReference<T>[] getValues$runtime_release() {
        return this.values;
    }

    public final boolean isValid$runtime_release() {
        WeakReference<T> weakReference;
        int i10 = this.size;
        WeakReference<T>[] weakReferenceArr = this.values;
        int[] iArr = this.hashes;
        int length = weakReferenceArr.length;
        if (i10 > length) {
            return false;
        }
        int i11 = Integer.MIN_VALUE;
        int i12 = 0;
        while (i12 < i10) {
            int i13 = iArr[i12];
            if (i13 < i11 || (weakReference = weakReferenceArr[i12]) == null) {
                return false;
            }
            T t2 = weakReference.get();
            if (t2 != null && i13 != ActualJvm_jvmKt.identityHashCode(t2)) {
                return false;
            }
            i12++;
            i11 = i13;
        }
        while (i10 < length) {
            if (iArr[i10] != 0 || weakReferenceArr[i10] != null) {
                return false;
            }
            i10++;
        }
        return true;
    }

    public final void removeIf(l<? super T, Boolean> block) {
        int size = getSize();
        int i10 = 0;
        int i11 = 0;
        while (true) {
            if (i10 >= size) {
                break;
            }
            WeakReference<T> weakReference = getValues$runtime_release()[i10];
            T t2 = weakReference != null ? weakReference.get() : null;
            if (t2 != null && !((Boolean) block.invoke(t2)).booleanValue()) {
                if (i11 != i10) {
                    getValues$runtime_release()[i11] = weakReference;
                    getHashes()[i11] = getHashes()[i10];
                }
                i11++;
            }
            i10++;
        }
        for (int i12 = i11; i12 < size; i12++) {
            getValues$runtime_release()[i12] = null;
            getHashes()[i12] = 0;
        }
        if (i11 != size) {
            setSize$runtime_release(i11);
        }
    }

    public final void setHashes$runtime_release(int[] iArr) {
        this.hashes = iArr;
    }

    public final void setSize$runtime_release(int i10) {
        this.size = i10;
    }

    public final void setValues$runtime_release(WeakReference<T>[] weakReferenceArr) {
        this.values = weakReferenceArr;
    }
}
