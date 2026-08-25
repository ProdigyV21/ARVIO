package androidx.compose.runtime;

import io.ktor.http.ContentDisposition;
import java.util.Arrays;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\t\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\f\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u0004¢\u0006\u0004\b\u000e\u0010\nJ\r\u0010\u000f\u001a\u00020\u0004¢\u0006\u0004\b\u000f\u0010\nJ\u0015\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004¢\u0006\u0004\b\u000e\u0010\rJ\r\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\u0011¢\u0006\u0004\b\u0014\u0010\u0013J\r\u0010\u0015\u001a\u00020\u0006¢\u0006\u0004\b\u0015\u0010\u0003J\u0015\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0016\u0010\rR\u0016\u0010\u0018\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001a\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001d\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\n¨\u0006\u001e"}, d2 = {"Landroidx/compose/runtime/IntStack;", "", "<init>", "()V", "", "value", "Lx6/t0;", "push", "(I)V", "pop", "()I", "default", "peekOr", "(I)I", "peek", "peek2", "index", "", "isEmpty", "()Z", "isNotEmpty", "clear", "indexOf", "", "slots", "[I", "tos", "I", "getSize", ContentDisposition.Parameters.Size, "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class IntStack {
    public static final int $stable = 8;
    private int[] slots = new int[10];
    private int tos;

    public final void clear() {
        this.tos = 0;
    }

    /* JADX INFO: renamed from: getSize, reason: from getter */
    public final int getTos() {
        return this.tos;
    }

    public final int indexOf(int value) {
        int i10 = this.tos;
        for (int i11 = 0; i11 < i10; i11++) {
            if (this.slots[i11] == value) {
                return i11;
            }
        }
        return -1;
    }

    public final boolean isEmpty() {
        return this.tos == 0;
    }

    public final boolean isNotEmpty() {
        return this.tos != 0;
    }

    public final int peek() {
        return this.slots[this.tos - 1];
    }

    public final int peek2() {
        return this.slots[this.tos - 2];
    }

    public final int peekOr(int i10) {
        return this.tos > 0 ? peek() : i10;
    }

    public final int pop() {
        int[] iArr = this.slots;
        int i10 = this.tos - 1;
        this.tos = i10;
        return iArr[i10];
    }

    public final void push(int value) {
        int i10 = this.tos;
        int[] iArr = this.slots;
        if (i10 >= iArr.length) {
            this.slots = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.slots;
        int i11 = this.tos;
        this.tos = i11 + 1;
        iArr2[i11] = value;
    }

    public final int peek(int index) {
        return this.slots[index];
    }
}
