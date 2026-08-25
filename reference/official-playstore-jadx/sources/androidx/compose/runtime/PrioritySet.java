package androidx.compose.runtime;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010!\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\r\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\rJ\r\u0010\u000f\u001a\u00020\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\u0003¢\u0006\u0004\b\u0011\u0010\u0010J\r\u0010\u0012\u001a\u00020\b¢\u0006\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/compose/runtime/PrioritySet;", "", "", "", "list", "<init>", "(Ljava/util/List;)V", "value", "Lx6/t0;", "add", "(I)V", "", "isEmpty", "()Z", "isNotEmpty", "peek", "()I", "takeMax", "validateHeap", "()V", "Ljava/util/List;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PrioritySet {
    public static final int $stable = 8;
    private final List<Integer> list;

    /* JADX WARN: Multi-variable type inference failed */
    public PrioritySet() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public final void add(int value) {
        if (!this.list.isEmpty()) {
            if (this.list.get(0).intValue() == value) {
                return;
            }
            if (this.list.get(r0.size() - 1).intValue() == value) {
                return;
            }
        }
        int size = this.list.size();
        this.list.add(Integer.valueOf(value));
        while (size > 0) {
            int i10 = ((size + 1) >>> 1) - 1;
            int iIntValue = this.list.get(i10).intValue();
            if (value <= iIntValue) {
                break;
            }
            this.list.set(size, Integer.valueOf(iIntValue));
            size = i10;
        }
        this.list.set(size, Integer.valueOf(value));
    }

    public final boolean isEmpty() {
        return this.list.isEmpty();
    }

    public final boolean isNotEmpty() {
        return !this.list.isEmpty();
    }

    public final int peek() {
        return ((Number) x.m0(this.list)).intValue();
    }

    public final int takeMax() {
        int iIntValue;
        if (!(this.list.size() > 0)) {
            ComposerKt.composeImmediateRuntimeError("Set is empty");
        }
        int iIntValue2 = this.list.get(0).intValue();
        while (!this.list.isEmpty() && this.list.get(0).intValue() == iIntValue2) {
            List<Integer> list = this.list;
            list.set(0, (Integer) x.w0(list));
            List<Integer> list2 = this.list;
            list2.remove(list2.size() - 1);
            int size = this.list.size();
            int size2 = this.list.size() >>> 1;
            int i10 = 0;
            while (i10 < size2) {
                int iIntValue3 = this.list.get(i10).intValue();
                int i11 = (i10 + 1) * 2;
                int i12 = i11 - 1;
                int iIntValue4 = this.list.get(i12).intValue();
                if (i11 >= size || (iIntValue = this.list.get(i11).intValue()) <= iIntValue4) {
                    if (iIntValue4 > iIntValue3) {
                        this.list.set(i10, Integer.valueOf(iIntValue4));
                        this.list.set(i12, Integer.valueOf(iIntValue3));
                        i10 = i12;
                    }
                } else if (iIntValue > iIntValue3) {
                    this.list.set(i10, Integer.valueOf(iIntValue));
                    this.list.set(i11, Integer.valueOf(iIntValue3));
                    i10 = i11;
                }
            }
        }
        return iIntValue2;
    }

    public final void validateHeap() {
        int size = this.list.size();
        int i10 = size / 2;
        int i11 = 0;
        while (i11 < i10) {
            int i12 = i11 + 1;
            int i13 = i12 * 2;
            boolean z = true;
            if (!(this.list.get(i11).intValue() >= this.list.get(i13 + (-1)).intValue())) {
                PreconditionsKt.throwIllegalStateException("Check failed.");
            }
            if (i13 < size && this.list.get(i11).intValue() < this.list.get(i13).intValue()) {
                z = false;
            }
            if (!z) {
                PreconditionsKt.throwIllegalStateException("Check failed.");
            }
            i11 = i12;
        }
    }

    public PrioritySet(List<Integer> list) {
        this.list = list;
    }

    public /* synthetic */ PrioritySet(List list, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? new ArrayList() : list);
    }
}
