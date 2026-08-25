package androidx.compose.ui.input.pointer.util;

import androidx.compose.ui.input.pointer.PointerId;
import io.ktor.http.ContentDisposition;
import java.util.Arrays;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0016\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0086\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0006ø\u0001\u0001¢\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u000e\u0010\rJ\u0015\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\u000b¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000f¢\u0006\u0004\b\u0016\u0010\rJ\u0018\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0006ø\u0001\u0001¢\u0006\u0004\b\u0017\u0010\rJ \u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u000fH\u0086\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ#\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0006H\u0086\u0002ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u001aJ\r\u0010\u001c\u001a\u00020\u0018¢\u0006\u0004\b\u001c\u0010\u0003J\u0018\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0006ø\u0001\u0001¢\u0006\u0004\b\u001d\u0010\rJ\u0015\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u001e\u0010\rR$\u0010 \u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u00048\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u0016\u0010%\u001a\u00020$8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b%\u0010&R\u0012\u0010(\u001a\u00020\u00048Æ\u0002¢\u0006\u0006\u001a\u0004\b'\u0010#\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006)"}, d2 = {"Landroidx/compose/ui/input/pointer/util/PointerIdArray;", "", "<init>", "()V", "", "index", "Landroidx/compose/ui/input/pointer/PointerId;", "get-_I2yYro", "(I)J", "get", "pointerId", "", "remove-0FcD4WY", "(J)Z", "remove", "", "pointerIdValue", "removeAt", "(I)Z", "isEmpty", "()Z", "value", "add", "add-0FcD4WY", "Lx6/t0;", "set", "(IJ)V", "set-DmW0f2w", "clear", "contains-0FcD4WY", "contains", "<set-?>", ContentDisposition.Parameters.Size, "I", "getSize", "()I", "", "internalArray", "[J", "getLastIndex", "lastIndex", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PointerIdArray {
    public static final int $stable = 8;
    private long[] internalArray = new long[2];
    private int size;

    public final boolean add(long value) {
        if (contains(value)) {
            return false;
        }
        set(this.size, value);
        return true;
    }

    /* JADX INFO: renamed from: add-0FcD4WY, reason: not valid java name */
    public final boolean m4598add0FcD4WY(long pointerId) {
        return add(pointerId);
    }

    public final void clear() {
        this.size = 0;
    }

    public final boolean contains(long pointerIdValue) {
        int i10 = this.size;
        for (int i11 = 0; i11 < i10; i11++) {
            if (this.internalArray[i11] == pointerIdValue) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: contains-0FcD4WY, reason: not valid java name */
    public final boolean m4599contains0FcD4WY(long pointerId) {
        return contains(pointerId);
    }

    /* JADX INFO: renamed from: get-_I2yYro, reason: not valid java name */
    public final long m4600get_I2yYro(int index) {
        return PointerId.m4498constructorimpl(this.internalArray[index]);
    }

    public final int getLastIndex() {
        return getSize() - 1;
    }

    public final int getSize() {
        return this.size;
    }

    public final boolean isEmpty() {
        return this.size == 0;
    }

    public final boolean remove(long pointerIdValue) {
        int i10 = this.size;
        for (int i11 = 0; i11 < i10; i11++) {
            if (pointerIdValue == m4600get_I2yYro(i11)) {
                removeAt(i11);
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: remove-0FcD4WY, reason: not valid java name */
    public final boolean m4601remove0FcD4WY(long pointerId) {
        return remove(pointerId);
    }

    public final boolean removeAt(int index) {
        int i10 = this.size;
        if (index >= i10) {
            return false;
        }
        int i11 = i10 - 1;
        while (index < i11) {
            long[] jArr = this.internalArray;
            int i12 = index + 1;
            jArr[index] = jArr[i12];
            index = i12;
        }
        this.size--;
        return true;
    }

    public final void set(int index, long value) {
        long[] jArr = this.internalArray;
        if (index >= jArr.length) {
            this.internalArray = Arrays.copyOf(jArr, Math.max(index + 1, jArr.length * 2));
        }
        this.internalArray[index] = value;
        if (index >= this.size) {
            this.size = index + 1;
        }
    }

    /* JADX INFO: renamed from: set-DmW0f2w, reason: not valid java name */
    public final void m4602setDmW0f2w(int index, long pointerId) {
        set(index, pointerId);
    }
}
