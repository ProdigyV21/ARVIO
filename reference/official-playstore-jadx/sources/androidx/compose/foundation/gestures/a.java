package androidx.compose.foundation.gestures;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class a {
    public static /* synthetic */ void a(AnchoredDragScope anchoredDragScope, float f10, float f11, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: dragTo");
        }
        if ((i10 & 2) != 0) {
            f11 = 0.0f;
        }
        anchoredDragScope.dragTo(f10, f11);
    }
}
