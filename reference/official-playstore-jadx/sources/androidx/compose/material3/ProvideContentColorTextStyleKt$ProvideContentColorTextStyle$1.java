package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.text.TextStyle;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class ProvideContentColorTextStyleKt$ProvideContentColorTextStyle$1 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ p<Composer, Integer, t0> $content;
    final /* synthetic */ long $contentColor;
    final /* synthetic */ TextStyle $textStyle;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ProvideContentColorTextStyleKt$ProvideContentColorTextStyle$1(long j10, TextStyle textStyle, p<? super Composer, ? super Integer, t0> pVar, int i10) {
        super(2);
        this.$contentColor = j10;
        this.$textStyle = textStyle;
        this.$content = pVar;
        this.$$changed = i10;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        ProvideContentColorTextStyleKt.m1782ProvideContentColorTextStyle3JVO9M(this.$contentColor, this.$textStyle, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
    }
}
