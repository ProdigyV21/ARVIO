package com.arflix.tv.ui.screens.settings;

import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import com.arflix.tv.data.model.CatalogDiscoveryResult;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "androidx/compose/foundation/lazy/LazyDslKt$itemsIndexed$4", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class SettingsScreenKt$CatalogDiscoveryModal$lambda$7$0$0$0$4$2$0$$inlined$itemsIndexed$default$3 extends kotlin.jvm.internal.r implements r7.r<LazyItemScope, Integer, Composer, Integer, x6.t0> {
    final /* synthetic */ boolean $isCompact$inlined;
    final /* synthetic */ List $items;
    final /* synthetic */ Set $normalizedAddedCatalogUrls$inlined;
    final /* synthetic */ r7.l $onAddResult$inlined;
    final /* synthetic */ MutableState $optimisticAddedUrls$delegate$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SettingsScreenKt$CatalogDiscoveryModal$lambda$7$0$0$0$4$2$0$$inlined$itemsIndexed$default$3(List list, Set set, r7.l lVar, boolean z, MutableState mutableState) {
        super(4);
        this.$items = list;
        this.$normalizedAddedCatalogUrls$inlined = set;
        this.$onAddResult$inlined = lVar;
        this.$isCompact$inlined = z;
        this.$optimisticAddedUrls$delegate$inlined = mutableState;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return x6.t0.f22605a;
    }

    public final void invoke(LazyItemScope lazyItemScope, int i10, Composer composer, int i11) {
        int i12;
        if ((i11 & 14) == 0) {
            i12 = (composer.changed(lazyItemScope) ? 4 : 2) | i11;
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
        final CatalogDiscoveryResult catalogDiscoveryResult = (CatalogDiscoveryResult) this.$items.get(i10);
        composer.startReplaceGroup(-1040813274);
        boolean z = this.$normalizedAddedCatalogUrls$inlined.contains(SettingsScreenKt.normalizeCatalogDiscoveryUrl(catalogDiscoveryResult.getSourceUrl())) || SettingsScreenKt.CatalogDiscoveryModal$lambda$4(this.$optimisticAddedUrls$delegate$inlined).contains(SettingsScreenKt.normalizeCatalogDiscoveryUrl(catalogDiscoveryResult.getSourceUrl()));
        boolean zChanged = composer.changed(this.$normalizedAddedCatalogUrls$inlined) | composer.changed(this.$onAddResult$inlined) | composer.changed(catalogDiscoveryResult);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            final Set set = this.$normalizedAddedCatalogUrls$inlined;
            final r7.l lVar = this.$onAddResult$inlined;
            final MutableState mutableState = this.$optimisticAddedUrls$delegate$inlined;
            objRememberedValue = new r7.a<x6.t0>() { // from class: com.arflix.tv.ui.screens.settings.SettingsScreenKt$CatalogDiscoveryModal$1$1$1$1$5$3$1$2$1$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6299invoke();
                    return x6.t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6299invoke() {
                    SettingsScreenKt.CatalogDiscoveryModal$addResult(set, lVar, mutableState, catalogDiscoveryResult);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        SettingsScreenKt.CatalogDiscoveryResultRow(catalogDiscoveryResult, z, (r7.a) objRememberedValue, this.$isCompact$inlined, composer, 0, 0);
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
