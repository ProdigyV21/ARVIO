package androidx.compose.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.ColumnScope;
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
public final class TabKt$Tab$6 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$default;
    final /* synthetic */ q<ColumnScope, Composer, Integer, t0> $content;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ r7.a<t0> $onClick;
    final /* synthetic */ boolean $selected;
    final /* synthetic */ long $selectedContentColor;
    final /* synthetic */ long $unselectedContentColor;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public TabKt$Tab$6(boolean z, r7.a<t0> aVar, Modifier modifier, boolean z5, long j10, long j11, MutableInteractionSource mutableInteractionSource, q<? super ColumnScope, ? super Composer, ? super Integer, t0> qVar, int i10, int i11) {
        super(2);
        this.$selected = z;
        this.$onClick = aVar;
        this.$modifier = modifier;
        this.$enabled = z5;
        this.$selectedContentColor = j10;
        this.$unselectedContentColor = j11;
        this.$interactionSource = mutableInteractionSource;
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
        TabKt.m2042TabbogVsAg(this.$selected, this.$onClick, this.$modifier, this.$enabled, this.$selectedContentColor, this.$unselectedContentColor, this.$interactionSource, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
    }
}
