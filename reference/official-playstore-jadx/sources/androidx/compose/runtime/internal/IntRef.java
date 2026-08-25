package androidx.compose.runtime.internal;

import com.google.common.util.concurrent.r0;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\tH\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\n"}, d2 = {"Landroidx/compose/runtime/internal/IntRef;", "", "element", "", "(I)V", "getElement", "()I", "setElement", "toString", "", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class IntRef {
    public static final int $stable = 8;
    private int element;

    public IntRef() {
        this(0, 1, null);
    }

    public final int getElement() {
        return this.element;
    }

    public final void setElement(int i10) {
        this.element = i10;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("IntRef(element = ");
        sb2.append(this.element);
        sb2.append(")@");
        int iHashCode = hashCode();
        r0.c(16);
        sb2.append(Integer.toString(iHashCode, 16));
        return sb2.toString();
    }

    public IntRef(int i10) {
        this.element = i10;
    }

    public /* synthetic */ IntRef(int i10, int i11, h hVar) {
        this((i11 & 1) != 0 ? 0 : i10);
    }
}
