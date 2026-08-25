package androidx.compose.runtime.external.kotlinx.collections.immutable.internal;

import a0.c;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0080\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0002H\u0086\u0002¢\u0006\u0004\b\b\u0010\u0005J\u0010\u0010\t\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000e\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0010\u0010\nJ\u001a\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0013\u0010\u0014R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\n\"\u0004\b\u0017\u0010\u0005¨\u0006\u0018"}, d2 = {"Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/DeltaCounter;", "", "", "count", "<init>", "(I)V", "that", "Lx6/t0;", "plusAssign", "component1", "()I", "copy", "(I)Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/DeltaCounter;", "", "toString", "()Ljava/lang/String;", "hashCode", "other", "", "equals", "(Ljava/lang/Object;)Z", "I", "getCount", "setCount", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class DeltaCounter {
    public static final int $stable = 8;
    private int count;

    public DeltaCounter() {
        this(0, 1, null);
    }

    public static /* synthetic */ DeltaCounter copy$default(DeltaCounter deltaCounter, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = deltaCounter.count;
        }
        return deltaCounter.copy(i10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getCount() {
        return this.count;
    }

    public final DeltaCounter copy(int count) {
        return new DeltaCounter(count);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof DeltaCounter) && this.count == ((DeltaCounter) other).count;
    }

    public final int getCount() {
        return this.count;
    }

    public int hashCode() {
        return this.count;
    }

    public final void plusAssign(int that) {
        this.count += that;
    }

    public final void setCount(int i10) {
        this.count = i10;
    }

    public String toString() {
        return c.o(new StringBuilder("DeltaCounter(count="), this.count, ')');
    }

    public DeltaCounter(int i10) {
        this.count = i10;
    }

    public /* synthetic */ DeltaCounter(int i10, int i11, h hVar) {
        this((i11 & 1) != 0 ? 0 : i10);
    }
}
