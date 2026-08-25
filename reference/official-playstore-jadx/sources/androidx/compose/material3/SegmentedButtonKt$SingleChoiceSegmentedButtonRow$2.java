package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class SegmentedButtonKt$SingleChoiceSegmentedButtonRow$2 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$default;
    final /* synthetic */ q<SingleChoiceSegmentedButtonRowScope, Composer, Integer, t0> $content;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ float $space;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SegmentedButtonKt$SingleChoiceSegmentedButtonRow$2(Modifier modifier, float f10, q<? super SingleChoiceSegmentedButtonRowScope, ? super Composer, ? super Integer, t0> qVar, int i10, int i11) {
        super(2);
        this.$modifier = modifier;
        this.$space = f10;
        this.$content = qVar;
        this.$$changed = i10;
        this.$$default = i11;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        SegmentedButtonKt.m1846SingleChoiceSegmentedButtonRowuFdPcIQ(this.$modifier, this.$space, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
    }
}
