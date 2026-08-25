package androidx.compose.foundation.gestures;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@f7.e(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt", f = "DragGestureDetector.kt", l = {884}, m = "horizontalDrag-jO51t88")
public final class DragGestureDetectorKt$horizontalDrag$1 extends f7.c {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;

    public DragGestureDetectorKt$horizontalDrag$1(d7.d<? super DragGestureDetectorKt$horizontalDrag$1> dVar) {
        super(dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return DragGestureDetectorKt.m322horizontalDragjO51t88(null, 0L, null, this);
    }
}
