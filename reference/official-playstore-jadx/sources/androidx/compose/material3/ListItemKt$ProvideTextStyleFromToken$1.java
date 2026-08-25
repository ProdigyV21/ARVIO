package androidx.compose.material3;

import androidx.compose.material3.tokens.TypographyKeyTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class ListItemKt$ProvideTextStyleFromToken$1 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ long $color;
    final /* synthetic */ p<Composer, Integer, t0> $content;
    final /* synthetic */ TypographyKeyTokens $textToken;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ListItemKt$ProvideTextStyleFromToken$1(long j10, TypographyKeyTokens typographyKeyTokens, p<? super Composer, ? super Integer, t0> pVar, int i10) {
        super(2);
        this.$color = j10;
        this.$textToken = typographyKeyTokens;
        this.$content = pVar;
        this.$$changed = i10;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        ListItemKt.m1652ProvideTextStyleFromToken3JVO9M(this.$color, this.$textToken, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
    }
}
