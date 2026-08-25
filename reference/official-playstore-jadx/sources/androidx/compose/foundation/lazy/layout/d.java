package androidx.compose.foundation.lazy.layout;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class d {
    public static float a(LazyLayoutSemanticState lazyLayoutSemanticState) {
        return lazyLayoutSemanticState.getCanScrollForward() ? lazyLayoutSemanticState.pseudoScrollOffset() + 100 : lazyLayoutSemanticState.pseudoScrollOffset();
    }

    public static float b(LazyLayoutSemanticState lazyLayoutSemanticState) {
        return (lazyLayoutSemanticState.getFirstVisibleItemIndex() * 500) + lazyLayoutSemanticState.getFirstVisibleItemScrollOffset();
    }
}
