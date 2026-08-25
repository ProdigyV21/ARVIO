package com.arflix.tv.ui.screens.player;

import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.AutoAwesomeKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.tv.material3.TextKt;
import com.arflix.tv.R;
import com.arflix.tv.ui.theme.ArflixTypography;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final class ComposableSingletons$PlayerScreenKt {
    public static final ComposableSingletons$PlayerScreenKt INSTANCE = new ComposableSingletons$PlayerScreenKt();
    private static r7.q<AnimatedVisibilityScope, Composer, Integer, x6.t0> lambda$1581803292 = ComposableLambdaKt.composableLambdaInstance(1581803292, false, new com.arflix.tv.ui.components.v(3));

    /* JADX INFO: renamed from: lambda$-619373845, reason: not valid java name */
    private static r7.q<LazyItemScope, Composer, Integer, x6.t0> f69lambda$619373845 = ComposableLambdaKt.composableLambdaInstance(-619373845, false, new com.arflix.tv.ui.components.v(4));

    /* JADX INFO: renamed from: lambda$-406282834, reason: not valid java name */
    private static r7.q<LazyItemScope, Composer, Integer, x6.t0> f68lambda$406282834 = ComposableLambdaKt.composableLambdaInstance(-406282834, false, new com.arflix.tv.ui.components.v(5));

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 lambda_1581803292$lambda$0(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1581803292, i10, -1, "com.arflix.tv.ui.screens.player.ComposableSingletons$PlayerScreenKt.lambda$1581803292.<anonymous> (PlayerScreen.kt:3303)");
        }
        Modifier.Companion companion = Modifier.INSTANCE;
        Color.Companion companion2 = Color.INSTANCE;
        float f10 = 4;
        Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(com.arflix.tv.data.repository.g.j(20, companion, Color.m3471copywmQWz5c$default(companion2.m3498getBlack0d7_KjU(), 0.65f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(10), Dp.m5678constructorimpl(f10));
        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(androidx.compose.foundation.c.g(f10, Arrangement.INSTANCE, composer, 693286680), Alignment.INSTANCE.getCenterVertically(), composer, 54);
        composer.startReplaceableGroup(-1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion3.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM530paddingVpY3zN4);
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
        r7.p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
        IconKt.m1610Iconww6aTOc(AutoAwesomeKt.getAutoAwesome(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(12)), ColorKt.Color(4286498976L), composer, 3504, 0);
        TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.player_ai_translating, composer, 0), null, Color.m3471copywmQWz5c$default(companion2.m3509getWhite0d7_KjU(), 0.85f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getLabelSmall(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
        if (androidx.compose.foundation.c.D(composer)) {
            ComposerKt.traceEventEnd();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 lambda__406282834$lambda$0(LazyItemScope lazyItemScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-406282834, i10, -1, "com.arflix.tv.ui.screens.player.ComposableSingletons$PlayerScreenKt.lambda$-406282834.<anonymous> (PlayerScreen.kt:5275)");
            }
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.no_audio_tracks, composer, 0), PaddingKt.m529padding3ABfNKs(Modifier.INSTANCE, Dp.m5678constructorimpl(16)), com.arflix.tv.ui.theme.ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getBody(), 0L, TextUnitKt.getSp(14), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composer, 48, 0, 65528);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 lambda__619373845$lambda$0(LazyItemScope lazyItemScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-619373845, i10, -1, "com.arflix.tv.ui.screens.player.ComposableSingletons$PlayerScreenKt.lambda$-619373845.<anonymous> (PlayerScreen.kt:4978)");
            }
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.no_audio_tracks, composer, 0), PaddingKt.m529padding3ABfNKs(Modifier.INSTANCE, Dp.m5678constructorimpl(16)), com.arflix.tv.ui.theme.ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, ArflixTypography.INSTANCE.getBody(), composer, 48, 0, 65528);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: renamed from: getLambda$-406282834$app_playRelease, reason: not valid java name */
    public final r7.q<LazyItemScope, Composer, Integer, x6.t0> m6260getLambda$406282834$app_playRelease() {
        return f68lambda$406282834;
    }

    /* JADX INFO: renamed from: getLambda$-619373845$app_playRelease, reason: not valid java name */
    public final r7.q<LazyItemScope, Composer, Integer, x6.t0> m6261getLambda$619373845$app_playRelease() {
        return f69lambda$619373845;
    }

    public final r7.q<AnimatedVisibilityScope, Composer, Integer, x6.t0> getLambda$1581803292$app_playRelease() {
        return lambda$1581803292;
    }
}
