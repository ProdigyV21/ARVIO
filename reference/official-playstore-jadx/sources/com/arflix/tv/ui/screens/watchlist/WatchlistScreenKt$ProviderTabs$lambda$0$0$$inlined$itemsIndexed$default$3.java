package com.arflix.tv.ui.screens.watchlist;

import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.media3.exoplayer.RendererCapabilities;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "androidx/compose/foundation/lazy/LazyDslKt$itemsIndexed$4", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class WatchlistScreenKt$ProviderTabs$lambda$0$0$$inlined$itemsIndexed$default$3 extends r implements r7.r<LazyItemScope, Integer, Composer, Integer, t0> {
    final /* synthetic */ int $focusedIndex$inlined;
    final /* synthetic */ boolean $isMobile$inlined;
    final /* synthetic */ List $items;
    final /* synthetic */ r7.l $onSelect$inlined;
    final /* synthetic */ int $selectedIndex$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchlistScreenKt$ProviderTabs$lambda$0$0$$inlined$itemsIndexed$default$3(List list, int i10, int i11, boolean z, r7.l lVar) {
        super(4);
        this.$items = list;
        this.$selectedIndex$inlined = i10;
        this.$focusedIndex$inlined = i11;
        this.$isMobile$inlined = z;
        this.$onSelect$inlined = lVar;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return t0.f22605a;
    }

    public final void invoke(LazyItemScope lazyItemScope, final int i10, Composer composer, int i11) {
        int i12;
        int i13;
        boolean z;
        LibraryProviderOption libraryProviderOption;
        boolean z5;
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
        LibraryProviderOption libraryProviderOption2 = (LibraryProviderOption) this.$items.get(i10);
        composer.startReplaceGroup(-480854448);
        long jProviderAccent = libraryProviderOption2.isHomeServer() ? WatchlistScreenKt.providerAccent(libraryProviderOption2.getHomeServerKind()) : p.a(libraryProviderOption2.getLabel(), "Trakt") ? ColorKt.Color(4293729316L) : p.a(libraryProviderOption2.getLabel(), "Simkl") ? ColorKt.Color(4278233013L) : Color.INSTANCE.m3509getWhite0d7_KjU();
        String label = libraryProviderOption2.getLabel();
        if (i10 == this.$selectedIndex$inlined) {
            i13 = i12;
            z = true;
        } else {
            i13 = i12;
            z = false;
        }
        if (i10 == this.$focusedIndex$inlined) {
            libraryProviderOption = libraryProviderOption2;
            z5 = true;
        } else {
            libraryProviderOption = libraryProviderOption2;
            z5 = false;
        }
        Modifier.Companion companion = Modifier.INSTANCE;
        boolean z10 = this.$isMobile$inlined;
        boolean zChanged = composer.changed(this.$onSelect$inlined) | ((((i13 & 112) ^ 48) > 32 && composer.changed(i10)) || (i13 & 48) == 32);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            final r7.l lVar = this.$onSelect$inlined;
            objRememberedValue = new r7.a<t0>() { // from class: com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt$ProviderTabs$1$1$2$1$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6466invoke();
                    return t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6466invoke() {
                    lVar.invoke(Integer.valueOf(i10));
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        WatchlistScreenKt.m6457SelectablePilldR3JOV0(label, z, z5, jProviderAccent, ClickableKt.m214clickableXHw0xAI$default(companion, z10, null, null, (r7.a) objRememberedValue, 6, null), libraryProviderOption.isWatchlist() ? null : Color.m3462boximpl(jProviderAccent), true, null, false, composer, 1572864, RendererCapabilities.DECODER_SUPPORT_MASK);
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
