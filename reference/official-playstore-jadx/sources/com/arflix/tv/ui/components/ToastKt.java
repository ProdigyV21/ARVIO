package com.arflix.tv.ui.components;

import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.CheckKt;
import androidx.compose.material.icons.filled.CloseKt;
import androidx.compose.material.icons.filled.InfoKt;
import androidx.compose.material3.IconKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.tv.material3.TextKt;
import com.arflix.tv.ui.theme.ArflixTypography;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aC\u0010\u000b\u001a\u00020\t2\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0007¢\u0006\u0004\b\u000b\u0010\f¨\u0006\u000e²\u0006\u000e\u0010\r\u001a\u00020\u00048\n@\nX\u008a\u008e\u0002"}, d2 = {"", "message", "Lcom/arflix/tv/ui/components/ToastType;", LinkHeader.Parameters.Type, "", "isVisible", "", "durationMs", "Lkotlin/Function0;", "Lx6/t0;", "onDismiss", "Toast", "(Ljava/lang/String;Lcom/arflix/tv/ui/components/ToastType;ZJLr7/a;Landroidx/compose/runtime/Composer;II)V", "visible", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ToastKt {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ToastType.values().length];
            try {
                iArr[ToastType.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ToastType.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ToastType.INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:135:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x012a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Toast(final java.lang.String r22, com.arflix.tv.ui.components.ToastType r23, final boolean r24, long r25, r7.a<x6.t0> r27, androidx.compose.runtime.Composer r28, final int r29, final int r30) {
        /*
            Method dump skipped, instruction units count: 620
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.ToastKt.Toast(java.lang.String, com.arflix.tv.ui.components.ToastType, boolean, long, r7.a, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final boolean Toast$lambda$2(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void Toast$lambda$3(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int Toast$lambda$5$0$0(int i10) {
        return i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int Toast$lambda$5$1$0(int i10) {
        return i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 Toast$lambda$5$2(ToastType toastType, String str, AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i10) {
        x6.j0 j0Var;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(93193037, i10, -1, "com.arflix.tv.ui.components.Toast.<anonymous>.<anonymous> (Toast.kt:77)");
        }
        int i11 = WhenMappings.$EnumSwitchMapping$0[toastType.ordinal()];
        if (i11 == 1) {
            j0Var = new x6.j0(Color.m3462boximpl(ColorKt.Color(4281652121L)), CheckKt.getCheck(Icons.INSTANCE.getDefault()), Color.m3462boximpl(ColorKt.Color(573887385)));
        } else if (i11 == 2) {
            j0Var = new x6.j0(Color.m3462boximpl(ColorKt.Color(4294472049L)), CloseKt.getClose(Icons.INSTANCE.getDefault()), Color.m3462boximpl(ColorKt.Color(586707313)));
        } else {
            if (i11 != 3) {
                throw new NoWhenBranchMatchedException();
            }
            j0Var = new x6.j0(Color.m3462boximpl(ColorKt.Color(4284524026L)), InfoKt.getInfo(Icons.INSTANCE.getDefault()), Color.m3462boximpl(ColorKt.Color(576759290)));
        }
        long jM3482unboximpl = ((Color) j0Var.f22587i).m3482unboximpl();
        ImageVector imageVector = (ImageVector) j0Var.f22588l;
        long jM3482unboximpl2 = ((Color) j0Var.f22589m).m3482unboximpl();
        Modifier.Companion companion = Modifier.INSTANCE;
        Modifier modifierM533paddingqDBjuR0$default = PaddingKt.m533paddingqDBjuR0$default(companion, 0.0f, 0.0f, 0.0f, Dp.m5678constructorimpl(48), 7, null);
        float f10 = 18;
        float fM5678constructorimpl = Dp.m5678constructorimpl(f10);
        RoundedCornerShape roundedCornerShapeM798RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f10));
        Color.Companion companion2 = Color.INSTANCE;
        Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(SizeKt.m585widthInVpY3zN4$default(com.arflix.tv.data.repository.g.k(f10, BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.n(ShadowKt.m3146shadows4CzXII$default(modifierM533paddingqDBjuR0$default, fM5678constructorimpl, roundedCornerShapeM798RoundedCornerShape0680j_4, false, Color.m3471copywmQWz5c$default(companion2.m3498getBlack0d7_KjU(), 0.32f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3471copywmQWz5c$default(companion2.m3498getBlack0d7_KjU(), 0.32f, 0.0f, 0.0f, 0.0f, 14, null), 4, null), f10), ColorKt.Color(3860471336L), null, 2, null), Dp.m5678constructorimpl(1), Color.m3471copywmQWz5c$default(jM3482unboximpl, 0.55f, 0.0f, 0.0f, 0.0f, 14, null)), 0.0f, Dp.m5678constructorimpl(560), 1, null), Dp.m5678constructorimpl(f10), Dp.m5678constructorimpl(13));
        Alignment.Companion companion3 = Alignment.INSTANCE;
        Alignment.Vertical centerVertically = companion3.getCenterVertically();
        Arrangement.Horizontal start = Arrangement.INSTANCE.getStart();
        composer.startReplaceableGroup(693286680);
        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(start, centerVertically, composer, 54);
        composer.startReplaceableGroup(-1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion4.getConstructor();
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
        r7.p pVarU = a0.c.u(companion4, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
        Modifier modifierM179backgroundbw27NRU$default = BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.n(SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(28)), 8), jM3482unboximpl2, null, 2, null);
        Alignment center = companion3.getCenter();
        composer.startReplaceableGroup(733328855);
        MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer, 6);
        composer.startReplaceableGroup(-1323940314);
        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor2 = companion4.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM179backgroundbw27NRU$default);
        if (composer.getApplier() == null) {
            ComposablesKt.invalidApplier();
        }
        composer.startReusableNode();
        if (composer.getInserting()) {
            composer.createNode(constructor2);
        } else {
            composer.useNode();
        }
        Composer composerM2991constructorimpl2 = Updater.m2991constructorimpl(composer);
        r7.p pVarU2 = a0.c.u(companion4, composerM2991constructorimpl2, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl2, currentCompositionLocalMap2);
        if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
            a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
        }
        a0.c.y(composer, qVarModifierMaterializerOf2, composer, 0, 2058660585);
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        IconKt.m1610Iconww6aTOc(imageVector, (String) null, SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(16)), jM3482unboximpl, composer, 432, 0);
        androidx.compose.foundation.c.y(composer);
        androidx.compose.material3.d.w(12, companion, composer, 6);
        TextKt.m6020Text4IGK_g(str, null, companion2.m3509getWhite0d7_KjU(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, ArflixTypography.INSTANCE.getBody(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 1572864, 65530);
        if (androidx.compose.foundation.c.D(composer)) {
            ComposerKt.traceEventEnd();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 Toast$lambda$6(String str, ToastType toastType, boolean z, long j10, r7.a aVar, int i10, int i11, Composer composer, int i12) {
        Toast(str, toastType, z, j10, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }
}
