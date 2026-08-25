package androidx.compose.material3;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@f7.e(c = "androidx.compose.material3.EnterAlwaysScrollBehavior$nestedScrollConnection$1", f = "AppBar.kt", l = {2118, 2119}, m = "onPostFling-RZ2iAVY")
public final class EnterAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1 extends f7.c {
    long J$0;
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ EnterAlwaysScrollBehavior$nestedScrollConnection$1 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EnterAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1(EnterAlwaysScrollBehavior$nestedScrollConnection$1 enterAlwaysScrollBehavior$nestedScrollConnection$1, d7.d<? super EnterAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1> dVar) {
        super(dVar);
        this.this$0 = enterAlwaysScrollBehavior$nestedScrollConnection$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.mo368onPostFlingRZ2iAVY(0L, 0L, this);
    }
}
