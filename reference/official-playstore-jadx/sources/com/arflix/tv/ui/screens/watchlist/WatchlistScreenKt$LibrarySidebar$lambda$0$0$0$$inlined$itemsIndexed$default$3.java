package com.arflix.tv.ui.screens.watchlist;

import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.outlined.MovieKt;
import androidx.compose.material.icons.outlined.TvKt;
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
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.tv.material3.TextKt;
import com.arflix.tv.data.repository.HomeServerCatalogCandidate;
import com.arflix.tv.ui.theme.ArflixTypography;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "androidx/compose/foundation/lazy/LazyDslKt$itemsIndexed$4", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class WatchlistScreenKt$LibrarySidebar$lambda$0$0$0$$inlined$itemsIndexed$default$3 extends r implements r7.r<LazyItemScope, Integer, Composer, Integer, t0> {
    final /* synthetic */ int $focusedIndex$inlined;
    final /* synthetic */ List $items;
    final /* synthetic */ p $onSelect$inlined;
    final /* synthetic */ int $selectedIndex$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchlistScreenKt$LibrarySidebar$lambda$0$0$0$$inlined$itemsIndexed$default$3(List list, int i10, int i11, p pVar) {
        super(4);
        this.$items = list;
        this.$selectedIndex$inlined = i10;
        this.$focusedIndex$inlined = i11;
        this.$onSelect$inlined = pVar;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return t0.f22605a;
    }

    public final void invoke(LazyItemScope lazyItemScope, final int i10, Composer composer, int i11) {
        int i12;
        long jM3471copywmQWz5c$default;
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
        final HomeServerCatalogCandidate homeServerCatalogCandidate = (HomeServerCatalogCandidate) this.$items.get(i10);
        composer.startReplaceGroup(809626119);
        boolean z = true;
        boolean z5 = i10 == this.$selectedIndex$inlined;
        boolean z10 = i10 == this.$focusedIndex$inlined;
        Modifier.Companion companion = Modifier.INSTANCE;
        Modifier modifierM564height3ABfNKs = SizeKt.m564height3ABfNKs(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), Dp.m5678constructorimpl(44));
        if (z5) {
            jM3471copywmQWz5c$default = Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), z10 ? 0.14f : 0.1f, 0.0f, 0.0f, 0.0f, 14, null);
        } else {
            jM3471copywmQWz5c$default = z10 ? Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.06f, 0.0f, 0.0f, 0.0f, 14, null) : Color.INSTANCE.m3507getTransparent0d7_KjU();
        }
        float f10 = 6;
        Modifier modifierK = com.arflix.tv.data.repository.g.k(f10, com.arflix.tv.data.repository.g.l(f10, modifierM564height3ABfNKs, jM3471copywmQWz5c$default), Dp.m5678constructorimpl(z10 ? 2 : 1), z10 ? Color.INSTANCE.m3509getWhite0d7_KjU() : z5 ? Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.3f, 0.0f, 0.0f, 0.0f, 14, null) : Color.INSTANCE.m3507getTransparent0d7_KjU());
        boolean zChanged = composer.changed(this.$onSelect$inlined);
        if ((((i12 & 112) ^ 48) <= 32 || !composer.changed(i10)) && (i12 & 48) != 32) {
            z = false;
        }
        boolean zChanged2 = zChanged | z | composer.changed(homeServerCatalogCandidate);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged2 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            final p pVar = this.$onSelect$inlined;
            objRememberedValue = new r7.a<t0>() { // from class: com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt$LibrarySidebar$1$1$1$2$1$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6465invoke();
                    return t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6465invoke() {
                    pVar.invoke(Integer.valueOf(i10), homeServerCatalogCandidate);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        Modifier modifierM531paddingVpY3zN4$default = PaddingKt.m531paddingVpY3zN4$default(ClickableKt.m214clickableXHw0xAI$default(modifierK, false, null, null, (r7.a) objRememberedValue, 6, null), Dp.m5678constructorimpl(12), 0.0f, 2, null);
        Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
        composer.startReplaceableGroup(693286680);
        MeasurePolicy measurePolicyG = androidx.compose.material3.d.g(Arrangement.INSTANCE, centerVertically, composer, 48, -1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion2.getConstructor();
        q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM531paddingVpY3zN4$default);
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
        p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyG, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
        String lowerCase = homeServerCatalogCandidate.getCollectionType().toLowerCase(Locale.ROOT);
        ImageVector movie = (kotlin.text.o.T(lowerCase, "movie", false) || kotlin.text.o.T(lowerCase, "film", false)) ? MovieKt.getMovie(Icons.Outlined.INSTANCE) : TvKt.getTv(Icons.Outlined.INSTANCE);
        Color.Companion companion3 = Color.INSTANCE;
        IconKt.m1610Iconww6aTOc(movie, (String) null, SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(17)), Color.m3471copywmQWz5c$default(companion3.m3509getWhite0d7_KjU(), (z5 || z10) ? 0.92f : 0.62f, 0.0f, 0.0f, 0.0f, 14, null), composer, 432, 0);
        String collectionName = homeServerCatalogCandidate.getCollectionName();
        if (kotlin.text.o.h0(collectionName)) {
            collectionName = homeServerCatalogCandidate.getTitle();
        }
        TextStyle body = ArflixTypography.INSTANCE.getBody();
        long sp = TextUnitKt.getSp(14);
        FontWeight.Companion companion4 = FontWeight.INSTANCE;
        TextKt.m6020Text4IGK_g(collectionName, PaddingKt.m533paddingqDBjuR0$default(companion, Dp.m5678constructorimpl(10), 0.0f, 0.0f, 0.0f, 14, null), Color.m3471copywmQWz5c$default(companion3.m3509getWhite0d7_KjU(), (z5 || z10) ? 1.0f : 0.66f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, TextUnitKt.getSp(16), TextOverflow.INSTANCE.m5632getEllipsisgIe3tQ8(), false, 2, 0, null, TextStyle.m5205copyp1EtxEg$default(body, 0L, sp, z5 ? companion4.getSemiBold() : companion4.getMedium(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composer, 48, 3126, 54264);
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
