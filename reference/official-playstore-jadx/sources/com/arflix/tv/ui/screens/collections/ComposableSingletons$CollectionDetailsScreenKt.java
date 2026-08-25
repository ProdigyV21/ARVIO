package com.arflix.tv.ui.screens.collections;

import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import androidx.tv.foundation.lazy.grid.q0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final class ComposableSingletons$CollectionDetailsScreenKt {
    public static final ComposableSingletons$CollectionDetailsScreenKt INSTANCE = new ComposableSingletons$CollectionDetailsScreenKt();

    /* JADX INFO: renamed from: lambda$-1294403185, reason: not valid java name */
    private static r7.q<q0, Composer, Integer, t0> f59lambda$1294403185;
    private static r7.q<q0, Composer, Integer, t0> lambda$1281883188;

    static {
        final int i10 = 0;
        lambda$1281883188 = ComposableLambdaKt.composableLambdaInstance(1281883188, false, new r7.q() { // from class: com.arflix.tv.ui.screens.collections.r
            @Override // r7.q
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                int i11 = i10;
                q0 q0Var = (q0) obj;
                Composer composer = (Composer) obj2;
                int iIntValue = ((Integer) obj3).intValue();
                switch (i11) {
                    case 0:
                        return ComposableSingletons$CollectionDetailsScreenKt.lambda_1281883188$lambda$0(q0Var, composer, iIntValue);
                    default:
                        return ComposableSingletons$CollectionDetailsScreenKt.lambda__1294403185$lambda$0(q0Var, composer, iIntValue);
                }
            }
        });
        final int i11 = 1;
        f59lambda$1294403185 = ComposableLambdaKt.composableLambdaInstance(-1294403185, false, new r7.q() { // from class: com.arflix.tv.ui.screens.collections.r
            @Override // r7.q
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                int i112 = i11;
                q0 q0Var = (q0) obj;
                Composer composer = (Composer) obj2;
                int iIntValue = ((Integer) obj3).intValue();
                switch (i112) {
                    case 0:
                        return ComposableSingletons$CollectionDetailsScreenKt.lambda_1281883188$lambda$0(q0Var, composer, iIntValue);
                    default:
                        return ComposableSingletons$CollectionDetailsScreenKt.lambda__1294403185$lambda$0(q0Var, composer, iIntValue);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 lambda_1281883188$lambda$0(q0 q0Var, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1281883188, i10, -1, "com.arflix.tv.ui.screens.collections.ComposableSingletons$CollectionDetailsScreenKt.lambda$1281883188.<anonymous> (CollectionDetailsScreen.kt:942)");
            }
            BoxKt.Box(SizeKt.m564height3ABfNKs(Modifier.INSTANCE, Dp.m5678constructorimpl(6)), composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 lambda__1294403185$lambda$0(q0 q0Var, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1294403185, i10, -1, "com.arflix.tv.ui.screens.collections.ComposableSingletons$CollectionDetailsScreenKt.lambda$-1294403185.<anonymous> (CollectionDetailsScreen.kt:1003)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierM531paddingVpY3zN4$default = PaddingKt.m531paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), 0.0f, Dp.m5678constructorimpl(16), 1, null);
            Alignment center = Alignment.INSTANCE.getCenter();
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer, 6);
            composer.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM531paddingVpY3zN4$default);
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
            r7.p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ProgressIndicatorKt.m1762CircularProgressIndicatorLxG7B9w(SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(20)), ColorKt.Color(4283400112L), Dp.m5678constructorimpl(2), 0L, 0, composer, 438, 24);
            if (androidx.compose.foundation.c.D(composer)) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: getLambda$-1294403185$app_playRelease, reason: not valid java name */
    public final r7.q<q0, Composer, Integer, t0> m6203getLambda$1294403185$app_playRelease() {
        return f59lambda$1294403185;
    }

    public final r7.q<q0, Composer, Integer, t0> getLambda$1281883188$app_playRelease() {
        return lambda$1281883188;
    }
}
