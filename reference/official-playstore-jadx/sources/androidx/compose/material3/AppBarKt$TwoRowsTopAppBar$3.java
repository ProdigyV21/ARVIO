package androidx.compose.material3;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.foundation.layout.WindowInsetsSides;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextStyle;
import kotlin.Metadata;
import kotlin.jvm.internal.h0;
import kotlin.jvm.internal.i0;
import kotlin.jvm.internal.r;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class AppBarKt$TwoRowsTopAppBar$3 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ p<Composer, Integer, t0> $actionsRow;
    final /* synthetic */ float $bottomTitleAlpha;
    final /* synthetic */ TopAppBarColors $colors;
    final /* synthetic */ boolean $hideBottomRowSemantics;
    final /* synthetic */ boolean $hideTopRowSemantics;
    final /* synthetic */ h0 $maxHeightPx;
    final /* synthetic */ p<Composer, Integer, t0> $navigationIcon;
    final /* synthetic */ h0 $pinnedHeightPx;
    final /* synthetic */ TopAppBarScrollBehavior $scrollBehavior;
    final /* synthetic */ p<Composer, Integer, t0> $smallTitle;
    final /* synthetic */ TextStyle $smallTitleTextStyle;
    final /* synthetic */ p<Composer, Integer, t0> $title;
    final /* synthetic */ i0 $titleBottomPaddingPx;
    final /* synthetic */ TextStyle $titleTextStyle;
    final /* synthetic */ float $topTitleAlpha;
    final /* synthetic */ WindowInsets $windowInsets;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AppBarKt$TwoRowsTopAppBar$3(WindowInsets windowInsets, h0 h0Var, TopAppBarColors topAppBarColors, p<? super Composer, ? super Integer, t0> pVar, TextStyle textStyle, float f10, boolean z, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, h0 h0Var2, TopAppBarScrollBehavior topAppBarScrollBehavior, p<? super Composer, ? super Integer, t0> pVar4, TextStyle textStyle2, float f11, i0 i0Var, boolean z5) {
        super(2);
        this.$windowInsets = windowInsets;
        this.$pinnedHeightPx = h0Var;
        this.$colors = topAppBarColors;
        this.$smallTitle = pVar;
        this.$smallTitleTextStyle = textStyle;
        this.$topTitleAlpha = f10;
        this.$hideTopRowSemantics = z;
        this.$navigationIcon = pVar2;
        this.$actionsRow = pVar3;
        this.$maxHeightPx = h0Var2;
        this.$scrollBehavior = topAppBarScrollBehavior;
        this.$title = pVar4;
        this.$titleTextStyle = textStyle2;
        this.$bottomTitleAlpha = f11;
        this.$titleBottomPaddingPx = i0Var;
        this.$hideBottomRowSemantics = z5;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        TopAppBarState state;
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1985938853, i10, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1808)");
        }
        WindowInsets windowInsets = this.$windowInsets;
        h0 h0Var = this.$pinnedHeightPx;
        TopAppBarColors topAppBarColors = this.$colors;
        p<Composer, Integer, t0> pVar = this.$smallTitle;
        TextStyle textStyle = this.$smallTitleTextStyle;
        float f10 = this.$topTitleAlpha;
        boolean z = this.$hideTopRowSemantics;
        p<Composer, Integer, t0> pVar2 = this.$navigationIcon;
        p<Composer, Integer, t0> pVar3 = this.$actionsRow;
        h0 h0Var2 = this.$maxHeightPx;
        TopAppBarScrollBehavior topAppBarScrollBehavior = this.$scrollBehavior;
        p<Composer, Integer, t0> pVar4 = this.$title;
        TextStyle textStyle2 = this.$titleTextStyle;
        float f11 = this.$bottomTitleAlpha;
        i0 i0Var = this.$titleBottomPaddingPx;
        boolean z5 = this.$hideBottomRowSemantics;
        composer.startReplaceableGroup(-483455358);
        Modifier.Companion companion = Modifier.INSTANCE;
        Arrangement arrangement = Arrangement.INSTANCE;
        MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(Alignment.INSTANCE, arrangement.getTop(), composer, 0, -1323940314);
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
        AppBarKt.m1227TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(companion, windowInsets)), h0Var.f19742i, topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), pVar, textStyle, f10, arrangement.getCenter(), arrangement.getStart(), 0, z, pVar2, pVar3, composer, 905969664, 3078);
        Modifier modifierClipToBounds = ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(companion, WindowInsetsKt.m604onlybOOhFvg(windowInsets, WindowInsetsSides.INSTANCE.m624getHorizontalJoeWqyM())));
        float heightOffset = ((topAppBarScrollBehavior == null || (state = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state.getHeightOffset()) + (h0Var2.f19742i - h0Var.f19742i);
        long navigationIconContentColor = topAppBarColors.getNavigationIconContentColor();
        long titleContentColor = topAppBarColors.getTitleContentColor();
        long actionIconContentColor = topAppBarColors.getActionIconContentColor();
        Arrangement.Vertical bottom = arrangement.getBottom();
        Arrangement.Horizontal start = arrangement.getStart();
        int i11 = i0Var.f19744i;
        ComposableSingletons$AppBarKt composableSingletons$AppBarKt = ComposableSingletons$AppBarKt.INSTANCE;
        AppBarKt.m1227TopAppBarLayoutkXwM9vE(modifierClipToBounds, heightOffset, navigationIconContentColor, titleContentColor, actionIconContentColor, pVar4, textStyle2, f11, bottom, start, i11, z5, composableSingletons$AppBarKt.m1406getLambda11$material3_release(), composableSingletons$AppBarKt.m1407getLambda12$material3_release(), composer, 905969664, 3456);
        if (androidx.compose.foundation.c.D(composer)) {
            ComposerKt.traceEventEnd();
        }
    }
}
