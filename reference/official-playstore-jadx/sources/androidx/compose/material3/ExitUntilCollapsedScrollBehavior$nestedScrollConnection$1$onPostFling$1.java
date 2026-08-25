package androidx.compose.material3;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@f7.e(c = "androidx.compose.material3.ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1", f = "AppBar.kt", l = {2203, 2204}, m = "onPostFling-RZ2iAVY")
public final class ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1 extends f7.c {
    long J$0;
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1(ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1 exitUntilCollapsedScrollBehavior$nestedScrollConnection$1, d7.d<? super ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1> dVar) {
        super(dVar);
        this.this$0 = exitUntilCollapsedScrollBehavior$nestedScrollConnection$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.mo368onPostFlingRZ2iAVY(0L, 0L, this);
    }
}
