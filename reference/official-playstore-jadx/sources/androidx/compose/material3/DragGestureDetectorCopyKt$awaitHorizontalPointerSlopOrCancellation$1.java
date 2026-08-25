package androidx.compose.material3;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@f7.e(c = "androidx.compose.material3.DragGestureDetectorCopyKt", f = "DragGestureDetectorCopy.kt", l = {145, 183}, m = "awaitHorizontalPointerSlopOrCancellation-gDDlDlE")
public final class DragGestureDetectorCopyKt$awaitHorizontalPointerSlopOrCancellation$1 extends f7.c {
    float F$0;
    float F$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;

    public DragGestureDetectorCopyKt$awaitHorizontalPointerSlopOrCancellation$1(d7.d<? super DragGestureDetectorCopyKt$awaitHorizontalPointerSlopOrCancellation$1> dVar) {
        super(dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return DragGestureDetectorCopyKt.m1536awaitHorizontalPointerSlopOrCancellationgDDlDlE(null, 0L, 0, null, this);
    }
}
