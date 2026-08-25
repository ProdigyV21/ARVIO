package com.arflix.tv.ui.components;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.AspectRatioKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.tv.material3.TextKt;
import coil.compose.SingletonAsyncImageKt;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.ui.theme.ArflixTypography;
import com.google.android.gms.cast.MediaError;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "androidx/compose/foundation/lazy/LazyDslKt$itemsIndexed$4", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class PersonModalKt$MobilePersonContent$lambda$0$3$0$$inlined$itemsIndexed$default$3 extends kotlin.jvm.internal.r implements r7.r<LazyItemScope, Integer, Composer, Integer, x6.t0> {
    final /* synthetic */ List $items;
    final /* synthetic */ r7.p $onMediaClick$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PersonModalKt$MobilePersonContent$lambda$0$3$0$$inlined$itemsIndexed$default$3(List list, r7.p pVar) {
        super(4);
        this.$items = list;
        this.$onMediaClick$inlined = pVar;
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
        final MediaItem mediaItem = (MediaItem) this.$items.get(i10);
        composer.startReplaceGroup(-2141437596);
        Modifier.Companion companion = Modifier.INSTANCE;
        Modifier modifierM583width3ABfNKs = SizeKt.m583width3ABfNKs(companion, Dp.m5678constructorimpl(MediaError.DetailedErrorCode.SOURCE_BUFFER_FAILURE));
        boolean zChanged = composer.changed(this.$onMediaClick$inlined) | composer.changed(mediaItem);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            final r7.p pVar = this.$onMediaClick$inlined;
            objRememberedValue = new r7.a<x6.t0>() { // from class: com.arflix.tv.ui.components.PersonModalKt$MobilePersonContent$1$4$1$1$1$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6175invoke();
                    return x6.t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6175invoke() {
                    pVar.invoke(mediaItem.getMediaType(), Integer.valueOf(mediaItem.getId()));
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        Modifier modifierM214clickableXHw0xAI$default = ClickableKt.m214clickableXHw0xAI$default(modifierM583width3ABfNKs, false, null, null, (r7.a) objRememberedValue, 7, null);
        composer.startReplaceableGroup(-483455358);
        Arrangement.Vertical top = Arrangement.INSTANCE.getTop();
        Alignment.Companion companion2 = Alignment.INSTANCE;
        MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(companion2, top, composer, 0, -1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion3.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM214clickableXHw0xAI$default);
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
        r7.p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyK, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
        Modifier modifierM179backgroundbw27NRU$default = BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.n(AspectRatioKt.aspectRatio$default(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), 0.6666667f, false, 2, null), 8), ColorKt.Color(4279900698L), null, 2, null);
        composer.startReplaceableGroup(733328855);
        MeasurePolicy measurePolicyD = a0.c.d(companion2, false, composer, 0, -1323940314);
        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
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
        r7.p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyD, composerM2991constructorimpl2, currentCompositionLocalMap2);
        if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
            a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
        }
        a0.c.y(composer, qVarModifierMaterializerOf2, composer, 0, 2058660585);
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        SingletonAsyncImageKt.m6053AsyncImage3HmZ8SU(mediaItem.getImage(), mediaItem.getTitle(), SizeKt.fillMaxSize$default(companion, 0.0f, 1, null), null, null, null, ContentScale.INSTANCE.getCrop(), 0.0f, null, 0, composer, 1573248, 952);
        androidx.compose.foundation.c.y(composer);
        SpacerKt.Spacer(SizeKt.m564height3ABfNKs(companion, Dp.m5678constructorimpl(6)), composer, 6);
        TextKt.m6020Text4IGK_g(mediaItem.getTitle(), null, com.arflix.tv.ui.theme.ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m5632getEllipsisgIe3tQ8(), false, 2, 0, null, TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getCaption(), 0L, TextUnitKt.getSp(12), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 3120, 55290);
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
