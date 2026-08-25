package androidx.compose.foundation.gestures;

import androidx.compose.foundation.MutatePriority;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class h {
    public static /* synthetic */ Object a(TransformableState transformableState, MutatePriority mutatePriority, p pVar, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: transform");
        }
        if ((i10 & 1) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return transformableState.transform(mutatePriority, pVar, dVar);
    }
}
