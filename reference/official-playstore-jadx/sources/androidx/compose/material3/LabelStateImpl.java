package androidx.compose.material3;

import androidx.compose.foundation.MutatePriority;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0096@¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\tH\u0016¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\tH\u0016¢\u0006\u0004\b\u000e\u0010\rR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u000f\u001a\u0004\b\u0003\u0010\u0010R\u001a\u0010\u0004\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u000f\u001a\u0004\b\u0004\u0010\u0010¨\u0006\u0011"}, d2 = {"Landroidx/compose/material3/LabelStateImpl;", "Landroidx/compose/material3/BasicTooltipState;", "", "isVisible", "isPersistent", "<init>", "(ZZ)V", "Landroidx/compose/foundation/MutatePriority;", "mutatePriority", "Lx6/t0;", "show", "(Landroidx/compose/foundation/MutatePriority;Ld7/d;)Ljava/lang/Object;", "dismiss", "()V", "onDispose", "Z", "()Z", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class LabelStateImpl implements BasicTooltipState {
    private final boolean isPersistent;
    private final boolean isVisible;

    /* JADX WARN: Illegal instructions before constructor call */
    public LabelStateImpl() {
        boolean z = false;
        this(z, z, 3, null);
    }

    @Override // androidx.compose.material3.BasicTooltipState
    public void dismiss() {
    }

    @Override // androidx.compose.material3.BasicTooltipState
    /* JADX INFO: renamed from: isPersistent, reason: from getter */
    public boolean getIsPersistent() {
        return this.isPersistent;
    }

    @Override // androidx.compose.material3.BasicTooltipState
    /* JADX INFO: renamed from: isVisible, reason: from getter */
    public boolean getIsVisible() {
        return this.isVisible;
    }

    @Override // androidx.compose.material3.BasicTooltipState
    public void onDispose() {
    }

    @Override // androidx.compose.material3.BasicTooltipState
    public Object show(MutatePriority mutatePriority, d7.d<? super t0> dVar) {
        return t0.f22605a;
    }

    public LabelStateImpl(boolean z, boolean z5) {
        this.isVisible = z;
        this.isPersistent = z5;
    }

    public /* synthetic */ LabelStateImpl(boolean z, boolean z5, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? true : z, (i10 & 2) != 0 ? true : z5);
    }
}
