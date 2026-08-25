package androidx.compose.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class SearchBar_androidKt$DockedSearchBar$5 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$changed1;
    final /* synthetic */ int $$default;
    final /* synthetic */ boolean $active;
    final /* synthetic */ SearchBarColors $colors;
    final /* synthetic */ q<ColumnScope, Composer, Integer, t0> $content;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ l<Boolean, t0> $onActiveChange;
    final /* synthetic */ l<String, t0> $onQueryChange;
    final /* synthetic */ l<String, t0> $onSearch;
    final /* synthetic */ p<Composer, Integer, t0> $placeholder;
    final /* synthetic */ String $query;
    final /* synthetic */ float $shadowElevation;
    final /* synthetic */ Shape $shape;
    final /* synthetic */ float $tonalElevation;
    final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SearchBar_androidKt$DockedSearchBar$5(String str, l<? super String, t0> lVar, l<? super String, t0> lVar2, boolean z, l<? super Boolean, t0> lVar3, Modifier modifier, boolean z5, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, Shape shape, SearchBarColors searchBarColors, float f10, float f11, MutableInteractionSource mutableInteractionSource, q<? super ColumnScope, ? super Composer, ? super Integer, t0> qVar, int i10, int i11, int i12) {
        super(2);
        this.$query = str;
        this.$onQueryChange = lVar;
        this.$onSearch = lVar2;
        this.$active = z;
        this.$onActiveChange = lVar3;
        this.$modifier = modifier;
        this.$enabled = z5;
        this.$placeholder = pVar;
        this.$leadingIcon = pVar2;
        this.$trailingIcon = pVar3;
        this.$shape = shape;
        this.$colors = searchBarColors;
        this.$tonalElevation = f10;
        this.$shadowElevation = f11;
        this.$interactionSource = mutableInteractionSource;
        this.$content = qVar;
        this.$$changed = i10;
        this.$$changed1 = i11;
        this.$$default = i12;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        SearchBar_androidKt.m1816DockedSearchBareWTbjVg(this.$query, this.$onQueryChange, this.$onSearch, this.$active, this.$onActiveChange, this.$modifier, this.$enabled, this.$placeholder, this.$leadingIcon, this.$trailingIcon, this.$shape, this.$colors, this.$tonalElevation, this.$shadowElevation, this.$interactionSource, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
    }
}
