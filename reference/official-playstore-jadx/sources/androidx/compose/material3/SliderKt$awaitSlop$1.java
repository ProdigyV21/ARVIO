package androidx.compose.material3;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@f7.e(c = "androidx.compose.material3.SliderKt", f = "Slider.kt", l = {1262}, m = "awaitSlop-8vUncbI")
public final class SliderKt$awaitSlop$1 extends f7.c {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    public SliderKt$awaitSlop$1(d7.d<? super SliderKt$awaitSlop$1> dVar) {
        super(dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return SliderKt.m1892awaitSlop8vUncbI(null, 0L, 0, this);
    }
}
