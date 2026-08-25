package androidx.compose.foundation.gestures;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@f7.e(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt", f = "DragGestureDetector.kt", l = {884, 926}, m = "awaitVerticalPointerSlopOrCancellation-gDDlDlE")
public final class DragGestureDetectorKt$awaitVerticalPointerSlopOrCancellation$1 extends f7.c {
    float F$0;
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;

    public DragGestureDetectorKt$awaitVerticalPointerSlopOrCancellation$1(d7.d<? super DragGestureDetectorKt$awaitVerticalPointerSlopOrCancellation$1> dVar) {
        super(dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return DragGestureDetectorKt.m317awaitVerticalPointerSlopOrCancellationgDDlDlE(null, 0L, 0, null, this);
    }
}
