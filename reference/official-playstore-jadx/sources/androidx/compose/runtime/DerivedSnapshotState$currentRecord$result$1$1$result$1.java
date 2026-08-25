package androidx.compose.runtime;

import androidx.compose.runtime.internal.IntRef;
import androidx.compose.runtime.snapshots.StateObject;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import t.y;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "", "it", "Lx6/t0;", "invoke", "(Ljava/lang/Object;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class DerivedSnapshotState$currentRecord$result$1$1$result$1 extends r implements r7.l<Object, t0> {
    final /* synthetic */ IntRef $calculationLevelRef;
    final /* synthetic */ int $nestedCalculationLevel;
    final /* synthetic */ y $newDependencies;
    final /* synthetic */ DerivedSnapshotState<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DerivedSnapshotState$currentRecord$result$1$1$result$1(DerivedSnapshotState<T> derivedSnapshotState, IntRef intRef, y yVar, int i10) {
        super(1);
        this.this$0 = derivedSnapshotState;
        this.$calculationLevelRef = intRef;
        this.$newDependencies = yVar;
        this.$nestedCalculationLevel = i10;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        m2952invoke(obj);
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m2952invoke(Object obj) {
        if (obj == this.this$0) {
            throw new IllegalStateException("A derived state calculation cannot read itself");
        }
        if (obj instanceof StateObject) {
            int element = this.$calculationLevelRef.getElement();
            y yVar = this.$newDependencies;
            int i10 = element - this.$nestedCalculationLevel;
            int iA = yVar.a(obj);
            int iMin = Math.min(i10, iA >= 0 ? yVar.f21806c[iA] : Integer.MAX_VALUE);
            int iC = yVar.c(obj);
            if (iC < 0) {
                iC = ~iC;
            }
            yVar.f21805b[iC] = obj;
            yVar.f21806c[iC] = iMin;
        }
    }
}
