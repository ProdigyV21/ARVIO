package androidx.compose.material3;

import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.window.PopupProperties;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class AndroidMenu_androidKt$DropdownMenu$2 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$default;
    final /* synthetic */ q<ColumnScope, Composer, Integer, t0> $content;
    final /* synthetic */ boolean $expanded;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ long $offset;
    final /* synthetic */ r7.a<t0> $onDismissRequest;
    final /* synthetic */ PopupProperties $properties;
    final /* synthetic */ ScrollState $scrollState;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AndroidMenu_androidKt$DropdownMenu$2(boolean z, r7.a<t0> aVar, Modifier modifier, long j10, ScrollState scrollState, PopupProperties popupProperties, q<? super ColumnScope, ? super Composer, ? super Integer, t0> qVar, int i10, int i11) {
        super(2);
        this.$expanded = z;
        this.$onDismissRequest = aVar;
        this.$modifier = modifier;
        this.$offset = j10;
        this.$scrollState = scrollState;
        this.$properties = popupProperties;
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
        AndroidMenu_androidKt.m1221DropdownMenu4kj_NE(this.$expanded, this.$onDismissRequest, this.$modifier, this.$offset, this.$scrollState, this.$properties, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
    }
}
