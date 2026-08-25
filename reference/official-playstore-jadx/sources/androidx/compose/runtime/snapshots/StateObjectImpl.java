package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.AtomicInt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b \u0018\u00002\u00020\u0001B\t\b\u0000¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\r\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0004H\u0000ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0011"}, d2 = {"Landroidx/compose/runtime/snapshots/StateObjectImpl;", "Landroidx/compose/runtime/snapshots/StateObject;", "<init>", "()V", "Landroidx/compose/runtime/snapshots/ReaderKind;", "reader", "Lx6/t0;", "recordReadIn-h_f27i8$runtime_release", "(I)V", "recordReadIn", "", "isReadIn-h_f27i8$runtime_release", "(I)Z", "isReadIn", "Landroidx/compose/runtime/AtomicInt;", "readerKind", "Landroidx/compose/runtime/AtomicInt;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class StateObjectImpl implements StateObject {
    public static final int $stable = 0;
    private final AtomicInt readerKind = new AtomicInt(0);

    /* JADX INFO: renamed from: isReadIn-h_f27i8$runtime_release, reason: not valid java name */
    public final boolean m3107isReadInh_f27i8$runtime_release(int reader) {
        return (reader & ReaderKind.m3091constructorimpl(this.readerKind.get())) != 0;
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public /* synthetic */ StateRecord mergeRecords(StateRecord stateRecord, StateRecord stateRecord2, StateRecord stateRecord3) {
        return b.a(this, stateRecord, stateRecord2, stateRecord3);
    }

    /* JADX INFO: renamed from: recordReadIn-h_f27i8$runtime_release, reason: not valid java name */
    public final void m3108recordReadInh_f27i8$runtime_release(int reader) {
        int iM3091constructorimpl;
        do {
            iM3091constructorimpl = ReaderKind.m3091constructorimpl(this.readerKind.get());
            if ((iM3091constructorimpl & reader) != 0) {
                return;
            }
        } while (!this.readerKind.compareAndSet(iM3091constructorimpl, ReaderKind.m3091constructorimpl(iM3091constructorimpl | reader)));
    }
}
