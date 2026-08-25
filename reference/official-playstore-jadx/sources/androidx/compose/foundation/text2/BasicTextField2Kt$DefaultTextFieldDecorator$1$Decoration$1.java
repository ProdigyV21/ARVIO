package androidx.compose.foundation.text2;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class BasicTextField2Kt$DefaultTextFieldDecorator$1$Decoration$1 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ p<Composer, Integer, t0> $it;
    final /* synthetic */ BasicTextField2Kt$DefaultTextFieldDecorator$1 $tmp0_rcvr;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public BasicTextField2Kt$DefaultTextFieldDecorator$1$Decoration$1(BasicTextField2Kt$DefaultTextFieldDecorator$1 basicTextField2Kt$DefaultTextFieldDecorator$1, p<? super Composer, ? super Integer, t0> pVar, int i10) {
        super(2);
        this.$tmp0_rcvr = basicTextField2Kt$DefaultTextFieldDecorator$1;
        this.$it = pVar;
        this.$$changed = i10;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        this.$tmp0_rcvr.Decoration(this.$it, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
    }
}
