package androidx.compose.foundation.text;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class ClickableTextKt$ClickableText$6 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$default;
    final /* synthetic */ int $maxLines;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ l<Integer, t0> $onClick;
    final /* synthetic */ l<Integer, t0> $onHover;
    final /* synthetic */ l<TextLayoutResult, t0> $onTextLayout;
    final /* synthetic */ int $overflow;
    final /* synthetic */ boolean $softWrap;
    final /* synthetic */ TextStyle $style;
    final /* synthetic */ AnnotatedString $text;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ClickableTextKt$ClickableText$6(AnnotatedString annotatedString, l<? super Integer, t0> lVar, Modifier modifier, TextStyle textStyle, boolean z, int i10, int i11, l<? super TextLayoutResult, t0> lVar2, l<? super Integer, t0> lVar3, int i12, int i13) {
        super(2);
        this.$text = annotatedString;
        this.$onHover = lVar;
        this.$modifier = modifier;
        this.$style = textStyle;
        this.$softWrap = z;
        this.$overflow = i10;
        this.$maxLines = i11;
        this.$onTextLayout = lVar2;
        this.$onClick = lVar3;
        this.$$changed = i12;
        this.$$default = i13;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        ClickableTextKt.m811ClickableText03UYbkw(this.$text, this.$onHover, this.$modifier, this.$style, this.$softWrap, this.$overflow, this.$maxLines, this.$onTextLayout, this.$onClick, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
    }
}
