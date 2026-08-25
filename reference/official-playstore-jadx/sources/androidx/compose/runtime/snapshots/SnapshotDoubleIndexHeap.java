package androidx.compose.runtime.snapshots;

import a0.c;
import androidx.media3.exoplayer.upstream.CmcdData;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.collections.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u0015\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\t\u0010\bJ\u001f\u0010\f\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u000f\u0010\bJ\u000f\u0010\u0010\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0013\u0010\bJ\u0017\u0010\u0015\u001a\u00020\u00042\b\b\u0002\u0010\u0014\u001a\u00020\u0004¢\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0004¢\u0006\u0004\b\u0018\u0010\u0016J\u0015\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0004¢\u0006\u0004\b\u0019\u0010\bJ\u000f\u0010\u001a\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u001a\u0010\u0003J\u001f\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u001b\u0010\rR$\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00048\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010\u0011R\u0016\u0010!\u001a\u00020 8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010\"R\u0016\u0010\u0005\u001a\u00020 8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0005\u0010\"R\u0016\u0010#\u001a\u00020 8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010\"R\u0016\u0010$\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010\u001e¨\u0006%"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotDoubleIndexHeap;", "", "<init>", "()V", "", "index", "Lx6/t0;", "shiftUp", "(I)V", "shiftDown", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "swap", "(II)V", "atLeast", "ensure", "allocateHandle", "()I", "handle", "freeHandle", "default", "lowestOrDefault", "(I)I", "value", "add", "remove", "validate", "validateHandle", "<set-?>", ContentDisposition.Parameters.Size, "I", "getSize", "", "values", "[I", "handles", "firstFreeHandle", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SnapshotDoubleIndexHeap {
    public static final int $stable = 8;
    private int firstFreeHandle;
    private int[] handles;
    private int size;
    private int[] values = new int[16];
    private int[] index = new int[16];

    public SnapshotDoubleIndexHeap() {
        int[] iArr = new int[16];
        int i10 = 0;
        while (i10 < 16) {
            int i11 = i10 + 1;
            iArr[i10] = i11;
            i10 = i11;
        }
        this.handles = iArr;
    }

    private final int allocateHandle() {
        int length = this.handles.length;
        if (this.firstFreeHandle >= length) {
            int i10 = length * 2;
            int[] iArr = new int[i10];
            int i11 = 0;
            while (i11 < i10) {
                int i12 = i11 + 1;
                iArr[i11] = i12;
                i11 = i12;
            }
            r.O(0, 0, 14, this.handles, iArr);
            this.handles = iArr;
        }
        int i13 = this.firstFreeHandle;
        this.firstFreeHandle = this.handles[i13];
        return i13;
    }

    private final void ensure(int atLeast) {
        int[] iArr = this.values;
        int length = iArr.length;
        if (atLeast <= length) {
            return;
        }
        int i10 = length * 2;
        int[] iArr2 = new int[i10];
        int[] iArr3 = new int[i10];
        r.O(0, 0, 14, iArr, iArr2);
        r.O(0, 0, 14, this.index, iArr3);
        this.values = iArr2;
        this.index = iArr3;
    }

    private final void freeHandle(int handle) {
        this.handles[handle] = this.firstFreeHandle;
        this.firstFreeHandle = handle;
    }

    public static /* synthetic */ int lowestOrDefault$default(SnapshotDoubleIndexHeap snapshotDoubleIndexHeap, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = 0;
        }
        return snapshotDoubleIndexHeap.lowestOrDefault(i10);
    }

    private final void shiftDown(int index) {
        int i10;
        int[] iArr = this.values;
        int i11 = this.size >> 1;
        while (index < i11) {
            int i12 = (index + 1) << 1;
            int i13 = i12 - 1;
            if (i12 >= this.size || (i10 = iArr[i12]) >= iArr[i13]) {
                if (iArr[i13] >= iArr[index]) {
                    return;
                }
                swap(i13, index);
                index = i13;
            } else {
                if (i10 >= iArr[index]) {
                    return;
                }
                swap(i12, index);
                index = i12;
            }
        }
    }

    private final void shiftUp(int index) {
        int[] iArr = this.values;
        int i10 = iArr[index];
        while (index > 0) {
            int i11 = ((index + 1) >> 1) - 1;
            if (iArr[i11] <= i10) {
                return;
            }
            swap(i11, index);
            index = i11;
        }
    }

    private final void swap(int a10, int b10) {
        int[] iArr = this.values;
        int[] iArr2 = this.index;
        int[] iArr3 = this.handles;
        int i10 = iArr[a10];
        iArr[a10] = iArr[b10];
        iArr[b10] = i10;
        int i11 = iArr2[a10];
        iArr2[a10] = iArr2[b10];
        iArr2[b10] = i11;
        iArr3[iArr2[a10]] = a10;
        iArr3[iArr2[b10]] = b10;
    }

    public final int add(int value) {
        ensure(this.size + 1);
        int i10 = this.size;
        this.size = i10 + 1;
        int iAllocateHandle = allocateHandle();
        this.values[i10] = value;
        this.index[i10] = iAllocateHandle;
        this.handles[iAllocateHandle] = i10;
        shiftUp(i10);
        return iAllocateHandle;
    }

    public final int getSize() {
        return this.size;
    }

    public final int lowestOrDefault(int i10) {
        return this.size > 0 ? this.values[0] : i10;
    }

    public final void remove(int handle) {
        int i10 = this.handles[handle];
        swap(i10, this.size - 1);
        this.size--;
        shiftUp(i10);
        shiftDown(i10);
        freeHandle(handle);
    }

    public final void validate() {
        int i10 = this.size;
        int i11 = 1;
        while (i11 < i10) {
            int i12 = i11 + 1;
            int[] iArr = this.values;
            if (iArr[(i12 >> 1) - 1] > iArr[i11]) {
                throw new IllegalStateException(("Index " + i11 + " is out of place").toString());
            }
            i11 = i12;
        }
    }

    public final void validateHandle(int handle, int value) {
        int i10 = this.handles[handle];
        if (this.index[i10] != handle) {
            throw new IllegalStateException(("Index for handle " + handle + " is corrupted").toString());
        }
        if (this.values[i10] == value) {
            return;
        }
        StringBuilder sbS = c.s(handle, "Value for handle ", " was ");
        sbS.append(this.values[i10]);
        sbS.append(" but was supposed to be ");
        sbS.append(value);
        throw new IllegalStateException(sbS.toString().toString());
    }
}
