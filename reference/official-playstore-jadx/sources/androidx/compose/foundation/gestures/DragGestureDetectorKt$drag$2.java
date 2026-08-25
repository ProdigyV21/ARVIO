package androidx.compose.foundation.gestures;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
@f7.e(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt", f = "DragGestureDetector.kt", l = {878}, m = "drag-VnAYq1g")
public final class DragGestureDetectorKt$drag$2 extends f7.c {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;

    public DragGestureDetectorKt$drag$2(d7.d<? super DragGestureDetectorKt$drag$2> dVar) {
        super(dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return DragGestureDetectorKt.m319dragVnAYq1g(null, 0L, null, null, null, this);
    }
}
