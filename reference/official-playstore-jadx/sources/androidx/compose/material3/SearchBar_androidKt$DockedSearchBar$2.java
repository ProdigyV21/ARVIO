package androidx.compose.material3;

import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class SearchBar_androidKt$DockedSearchBar$2 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ boolean $active;
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
    final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SearchBar_androidKt$DockedSearchBar$2(String str, l<? super String, t0> lVar, l<? super String, t0> lVar2, boolean z, l<? super Boolean, t0> lVar3, boolean z5, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, SearchBarColors searchBarColors, MutableInteractionSource mutableInteractionSource, q<? super ColumnScope, ? super Composer, ? super Integer, t0> qVar) {
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
        this.$content = qVar;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1088676554, i10, -1, "androidx.compose.material3.DockedSearchBar.<anonymous> (SearchBar.android.kt:377)");
        }
        String str = this.$query;
        l<String, t0> lVar = this.$onQueryChange;
        l<String, t0> lVar2 = this.$onSearch;
        boolean z = this.$active;
        l<Boolean, t0> lVar3 = this.$onActiveChange;
        boolean z5 = this.$enabled;
        p<Composer, Integer, t0> pVar = this.$placeholder;
        p<Composer, Integer, t0> pVar2 = this.$leadingIcon;
        p<Composer, Integer, t0> pVar3 = this.$trailingIcon;
        SearchBarColors searchBarColors = this.$colors;
        MutableInteractionSource mutableInteractionSource = this.$interactionSource;
        q<ColumnScope, Composer, Integer, t0> qVar = this.$content;
        composer.startReplaceableGroup(-483455358);
        Modifier.Companion companion = Modifier.INSTANCE;
        MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(Alignment.INSTANCE, Arrangement.INSTANCE.getTop(), composer, 0, -1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion2.getConstructor();
        q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(companion);
        if (composer.getApplier() == null) {
            ComposablesKt.invalidApplier();
        }
        composer.startReusableNode();
        if (composer.getInserting()) {
            composer.createNode(constructor);
        } else {
            composer.useNode();
        }
        Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composer);
        p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyK, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
        SearchBar_androidKt.SearchBarInputField(str, lVar, lVar2, z, lVar3, null, z5, pVar, pVar2, pVar3, searchBarColors.getInputFieldColors(), mutableInteractionSource, composer, 0, 0, 32);
        AnimatedVisibilityKt.AnimatedVisibility(columnScopeInstance, z, (Modifier) null, SearchBar_androidKt.DockedEnterTransition, SearchBar_androidKt.DockedExitTransition, (String) null, ComposableLambdaKt.composableLambda(composer, 1804538904, true, new SearchBar_androidKt$DockedSearchBar$2$1$1(searchBarColors, qVar)), composer, 1600518, 18);
        if (androidx.compose.foundation.c.D(composer)) {
            ComposerKt.traceEventEnd();
        }
    }
}
