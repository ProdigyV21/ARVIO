package androidx.compose.foundation.layout;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@f7.e(c = "androidx.compose.foundation.layout.WindowInsetsNestedScrollConnection", f = "WindowInsetsConnection.android.kt", l = {320, 346, 371}, m = "fling-huYlsQE")
public final class WindowInsetsNestedScrollConnection$fling$1 extends f7.c {
    float F$0;
    long J$0;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ WindowInsetsNestedScrollConnection this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WindowInsetsNestedScrollConnection$fling$1(WindowInsetsNestedScrollConnection windowInsetsNestedScrollConnection, d7.d<? super WindowInsetsNestedScrollConnection$fling$1> dVar) {
        super(dVar);
        this.this$0 = windowInsetsNestedScrollConnection;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.m606flinghuYlsQE(0L, 0.0f, false, this);
    }
}
