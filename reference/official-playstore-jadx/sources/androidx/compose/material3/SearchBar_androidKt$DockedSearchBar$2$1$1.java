package androidx.compose.material3;

import android.content.res.Configuration;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/animation/AnimatedVisibilityScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/AnimatedVisibilityScope;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class SearchBar_androidKt$DockedSearchBar$2$1$1 extends r implements q<AnimatedVisibilityScope, Composer, Integer, t0> {
    final /* synthetic */ SearchBarColors $colors;
    final /* synthetic */ q<ColumnScope, Composer, Integer, t0> $content;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SearchBar_androidKt$DockedSearchBar$2$1$1(SearchBarColors searchBarColors, q<? super ColumnScope, ? super Composer, ? super Integer, t0> qVar) {
        super(3);
        this.$colors = searchBarColors;
        this.$content = qVar;
    }

    @Override // r7.q
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke((AnimatedVisibilityScope) obj, (Composer) obj2, ((Number) obj3).intValue());
        return t0.f22605a;
    }

    public final void invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1804538904, i10, -1, "androidx.compose.material3.DockedSearchBar.<anonymous>.<anonymous>.<anonymous> (SearchBar.android.kt:397)");
        }
        float fM5678constructorimpl = Dp.m5678constructorimpl(((Configuration) composer.consume(AndroidCompositionLocals_androidKt.getLocalConfiguration())).screenHeightDp);
        composer.startReplaceableGroup(747855826);
        boolean zChanged = composer.changed(fM5678constructorimpl);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = Dp.m5676boximpl(Dp.m5678constructorimpl(fM5678constructorimpl * 0.6666667f));
            composer.updateRememberedValue(objRememberedValue);
        }
        float fM5692unboximpl = ((Dp) objRememberedValue).m5692unboximpl();
        composer.endReplaceableGroup();
        composer.startReplaceableGroup(747855974);
        boolean zChanged2 = composer.changed(fM5692unboximpl);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            Dp dpM5676boximpl = Dp.m5676boximpl(SearchBar_androidKt.getDockedActiveTableMinHeight());
            Dp dpM5676boximpl2 = Dp.m5676boximpl(fM5692unboximpl);
            if (dpM5676boximpl.compareTo(dpM5676boximpl2) > 0) {
                dpM5676boximpl = dpM5676boximpl2;
            }
            objRememberedValue2 = Dp.m5676boximpl(dpM5676boximpl.m5692unboximpl());
            composer.updateRememberedValue(objRememberedValue2);
        }
        float fM5692unboximpl2 = ((Dp) objRememberedValue2).m5692unboximpl();
        composer.endReplaceableGroup();
        Modifier modifierM565heightInVpY3zN4 = SizeKt.m565heightInVpY3zN4(Modifier.INSTANCE, fM5692unboximpl2, fM5692unboximpl);
        SearchBarColors searchBarColors = this.$colors;
        q<ColumnScope, Composer, Integer, t0> qVar = this.$content;
        composer.startReplaceableGroup(-483455358);
        MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(Alignment.INSTANCE, Arrangement.INSTANCE.getTop(), composer, 0, -1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion.getConstructor();
        q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM565heightInVpY3zN4);
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
        p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyK, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
        DividerKt.m1534HorizontalDivider9IZ8Weo(null, 0.0f, searchBarColors.getDividerColor(), composer, 0, 3);
        qVar.invoke(columnScopeInstance, composer, 6);
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
