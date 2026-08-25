package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class ListItemKt$ListItem$3 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$default;
    final /* synthetic */ ListItemColors $colors;
    final /* synthetic */ p<Composer, Integer, t0> $headlineContent;
    final /* synthetic */ p<Composer, Integer, t0> $leadingContent;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ p<Composer, Integer, t0> $overlineContent;
    final /* synthetic */ float $shadowElevation;
    final /* synthetic */ p<Composer, Integer, t0> $supportingContent;
    final /* synthetic */ float $tonalElevation;
    final /* synthetic */ p<Composer, Integer, t0> $trailingContent;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ListItemKt$ListItem$3(p<? super Composer, ? super Integer, t0> pVar, Modifier modifier, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5, ListItemColors listItemColors, float f10, float f11, int i10, int i11) {
        super(2);
        this.$headlineContent = pVar;
        this.$modifier = modifier;
        this.$overlineContent = pVar2;
        this.$supportingContent = pVar3;
        this.$leadingContent = pVar4;
        this.$trailingContent = pVar5;
        this.$colors = listItemColors;
        this.$tonalElevation = f10;
        this.$shadowElevation = f11;
        this.$$changed = i10;
        this.$$default = i11;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        ListItemKt.m1651ListItemHXNGIdc(this.$headlineContent, this.$modifier, this.$overlineContent, this.$supportingContent, this.$leadingContent, this.$trailingContent, this.$colors, this.$tonalElevation, this.$shadowElevation, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
    }
}
