package androidx.compose.foundation.gestures;

import androidx.compose.foundation.MutatePriority;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class c {
    public static /* synthetic */ Object a(Draggable2DState draggable2DState, MutatePriority mutatePriority, p pVar, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drag");
        }
        if ((i10 & 1) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return draggable2DState.drag(mutatePriority, pVar, dVar);
    }
}
