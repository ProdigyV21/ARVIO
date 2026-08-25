package androidx.compose.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.State;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class SearchBar_androidKt$SearchBar$4 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ boolean $active;
    final /* synthetic */ State<Float> $animationProgress;
    final /* synthetic */ SearchBarColors $colors;
    final /* synthetic */ q<ColumnScope, Composer, Integer, t0> $content;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
    final /* synthetic */ l<Boolean, t0> $onActiveChange;
    final /* synthetic */ l<String, t0> $onQueryChange;
    final /* synthetic */ l<String, t0> $onSearch;
    final /* synthetic */ p<Composer, Integer, t0> $placeholder;
    final /* synthetic */ String $query;
    final /* synthetic */ State<Dp> $topPadding;
    final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SearchBar_androidKt$SearchBar$4(String str, l<? super String, t0> lVar, l<? super String, t0> lVar2, boolean z, l<? super Boolean, t0> lVar3, boolean z5, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, SearchBarColors searchBarColors, MutableInteractionSource mutableInteractionSource, State<Float> state, State<Dp> state2, q<? super ColumnScope, ? super Composer, ? super Integer, t0> qVar) {
        super(2);
        this.$query = str;
        this.$onQueryChange = lVar;
        this.$onSearch = lVar2;
        this.$active = z;
        this.$onActiveChange = lVar3;
        this.$enabled = z5;
        this.$placeholder = pVar;
        this.$leadingIcon = pVar2;
        this.$trailingIcon = pVar3;
        this.$colors = searchBarColors;
        this.$interactionSource = mutableInteractionSource;
        this.$animationProgress = state;
        this.$topPadding = state2;
        this.$content = qVar;
    }

    private static final boolean invoke$lambda$5$lambda$2(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0217  */
    /* JADX WARN: Removed duplicated region for block: B:60:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void invoke(androidx.compose.runtime.Composer r33, int r34) {
        /*
            Method dump skipped, instruction units count: 539
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SearchBar_androidKt$SearchBar$4.invoke(androidx.compose.runtime.Composer, int):void");
    }
}
