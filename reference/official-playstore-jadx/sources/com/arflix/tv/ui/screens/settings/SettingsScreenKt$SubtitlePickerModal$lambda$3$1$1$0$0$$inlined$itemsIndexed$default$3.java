package com.arflix.tv.ui.screens.settings;

import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.CheckKt;
import androidx.compose.material3.IconKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import androidx.tv.material3.TextKt;
import com.arflix.tv.ui.skin.ArvioSkinKt;
import com.arflix.tv.ui.theme.ArflixTypography;
import com.arflix.tv.ui.theme.ColorKt;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "androidx/compose/foundation/lazy/LazyDslKt$itemsIndexed$4", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class SettingsScreenKt$SubtitlePickerModal$lambda$3$1$1$0$0$$inlined$itemsIndexed$default$3 extends kotlin.jvm.internal.r implements r7.r<LazyItemScope, Integer, Composer, Integer, x6.t0> {
    final /* synthetic */ List $items;
    final /* synthetic */ r7.l $onSelect$inlined;
    final /* synthetic */ int $safeIndex$inlined;
    final /* synthetic */ String $selected$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SettingsScreenKt$SubtitlePickerModal$lambda$3$1$1$0$0$$inlined$itemsIndexed$default$3(List list, int i10, String str, r7.l lVar) {
        super(4);
        this.$items = list;
        this.$safeIndex$inlined = i10;
        this.$selected$inlined = str;
        this.$onSelect$inlined = lVar;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return x6.t0.f22605a;
    }

    public final void invoke(LazyItemScope lazyItemScope, int i10, Composer composer, int i11) {
        int i12;
        if ((i11 & 14) == 0) {
            i12 = i11 | (composer.changed(lazyItemScope) ? 4 : 2);
        } else {
            i12 = i11;
        }
        if ((i11 & 112) == 0) {
            i12 |= composer.changed(i10) ? 32 : 16;
        }
        if ((i12 & 731) == 146 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1091073711, i12, -1, "androidx.compose.foundation.lazy.itemsIndexed.<anonymous> (LazyDsl.kt:183)");
        }
        final String str = (String) this.$items.get(i10);
        composer.startReplaceGroup(1694726737);
        boolean z = i10 == this.$safeIndex$inlined;
        boolean zL = kotlin.text.u.L(str, this.$selected$inlined, true);
        long jM6519resolveAccentColorek8zF_U = ArvioSkinKt.m6519resolveAccentColorek8zF_U(ColorKt.getPink(), composer, 0);
        Modifier.Companion companion = Modifier.INSTANCE;
        float f10 = 10;
        Modifier modifierN = com.arflix.tv.data.repository.g.n(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), f10);
        Color.Companion companion2 = Color.INSTANCE;
        Modifier modifierL = com.arflix.tv.data.repository.g.l(f10, modifierN, z ? Color.m3471copywmQWz5c$default(companion2.m3509getWhite0d7_KjU(), 0.12f, 0.0f, 0.0f, 0.0f, 14, null) : companion2.m3507getTransparent0d7_KjU());
        float fM5678constructorimpl = Dp.m5678constructorimpl(z ? 2 : 1);
        if (!z) {
            jM6519resolveAccentColorek8zF_U = Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.1f, 0.0f, 0.0f, 0.0f, 14, null);
        }
        Modifier modifierK = com.arflix.tv.data.repository.g.k(f10, modifierL, fM5678constructorimpl, jM6519resolveAccentColorek8zF_U);
        boolean zChanged = composer.changed(this.$onSelect$inlined) | composer.changed(str);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            final r7.l lVar = this.$onSelect$inlined;
            objRememberedValue = new r7.a<x6.t0>() { // from class: com.arflix.tv.ui.screens.settings.SettingsScreenKt$SubtitlePickerModal$3$2$2$1$1$1$1$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6319invoke();
                    return x6.t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6319invoke() {
                    lVar.invoke(str);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(ClickableKt.m214clickableXHw0xAI$default(modifierK, false, null, null, (r7.a) objRememberedValue, 7, null), Dp.m5678constructorimpl(16), Dp.m5678constructorimpl(12));
        Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
        composer.startReplaceableGroup(693286680);
        MeasurePolicy measurePolicyG = androidx.compose.material3.d.g(Arrangement.INSTANCE, centerVertically, composer, 48, -1323940314);
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
        r7.p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyG, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
        TextKt.m6020Text4IGK_g(str, androidx.compose.foundation.layout.e.a(RowScopeInstance.INSTANCE, companion, 1.0f, false, 2, null), z ? ColorKt.getTextPrimary() : ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, ArflixTypography.INSTANCE.getBody(), composer, 0, 0, 65528);
        if (zL) {
            composer.startReplaceGroup(194831900);
            IconKt.m1610Iconww6aTOc(CheckKt.getCheck(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(18)), ColorKt.getSuccessGreen(), composer, 432, 0);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(195148038);
            composer.endReplaceGroup();
        }
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        SpacerKt.Spacer(SizeKt.m564height3ABfNKs(companion, Dp.m5678constructorimpl(f10)), composer, 6);
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
