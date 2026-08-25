package androidx.compose.foundation;

import d7.d;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class b {
    public static /* synthetic */ Object a(BasicTooltipState basicTooltipState, MutatePriority mutatePriority, d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: show");
        }
        if ((i10 & 1) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return basicTooltipState.show(mutatePriority, dVar);
    }
}
