package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.SearchKt;
import androidx.compose.material3.IconKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Dp;
import androidx.media3.extractor.WavUtil;
import androidx.tv.material3.TextKt;
import com.arflix.tv.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u001aK\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00072\b\b\u0002\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0004\b\u000b\u0010\f\u001a%\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0003¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryTree;", "tree", "", "selectedId", "Lkotlin/Function1;", "Lx6/t0;", "onSelect", "Lkotlin/Function0;", "onOpenSearch", "Landroidx/compose/ui/Modifier;", "modifier", "TouchCategoryRail", "(Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryTree;Ljava/lang/String;Lr7/l;Lr7/a;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "", "Lcom/arflix/tv/ui/screens/tv/live/TouchCategoryRailItem;", "rememberTouchRailItems", "(Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryTree;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)Ljava/util/List;", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class TouchCategoryRailKt {
    /* JADX WARN: Removed duplicated region for block: B:48:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:79:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void TouchCategoryRail(com.arflix.tv.ui.screens.tv.live.LiveCategoryTree r19, java.lang.String r20, r7.l<? super java.lang.String, x6.t0> r21, r7.a<x6.t0> r22, androidx.compose.ui.Modifier r23, androidx.compose.runtime.Composer r24, int r25, int r26) {
        /*
            Method dump skipped, instruction units count: 301
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.TouchCategoryRailKt.TouchCategoryRail(com.arflix.tv.ui.screens.tv.live.LiveCategoryTree, java.lang.String, r7.l, r7.a, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 TouchCategoryRail$lambda$0$0(List list, r7.a aVar, String str, r7.l lVar, LazyListScope lazyListScope) {
        LazyListScope.CC.i(lazyListScope, "search", null, ComposableLambdaKt.composableLambdaInstance(-11793997, true, new d1(aVar, 2)), 2, null);
        lazyListScope.items(list.size(), new TouchCategoryRailKt$TouchCategoryRail$lambda$0$0$$inlined$itemsIndexed$default$1(new l0(3), list), new TouchCategoryRailKt$TouchCategoryRail$lambda$0$0$$inlined$itemsIndexed$default$2(list), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new TouchCategoryRailKt$TouchCategoryRail$lambda$0$0$$inlined$itemsIndexed$default$3(list, str, lVar)));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 TouchCategoryRail$lambda$0$0$0(r7.a aVar, LazyItemScope lazyItemScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-11793997, i10, -1, "com.arflix.tv.ui.screens.tv.live.TouchCategoryRail.<anonymous>.<anonymous>.<anonymous> (TouchCategoryRail.kt:52)");
            }
            Modifier modifierN = com.arflix.tv.data.repository.g.n(SizeKt.m564height3ABfNKs(Modifier.INSTANCE, Dp.m5678constructorimpl(38)), 12);
            LiveColors liveColors = LiveColors.INSTANCE;
            Modifier modifierM531paddingVpY3zN4$default = PaddingKt.m531paddingVpY3zN4$default(ClickableKt.m214clickableXHw0xAI$default(BackgroundKt.m179backgroundbw27NRU$default(modifierN, liveColors.m6411getPanelRaised0d7_KjU(), null, 2, null), false, null, null, aVar, 7, null), Dp.m5678constructorimpl(14), 0.0f, 2, null);
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(androidx.compose.foundation.c.g(8, Arrangement.INSTANCE, composer, 693286680), Alignment.INSTANCE.getCenterVertically(), composer, 54);
            composer.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM531paddingVpY3zN4$default);
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
            r7.p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            IconKt.m1610Iconww6aTOc(SearchKt.getSearch(Icons.Filled.INSTANCE), StringResources_androidKt.stringResource(R.string.search, composer, 0), (Modifier) null, liveColors.m6403getFgDim0d7_KjU(), composer, 0, 4);
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.live_label_search_channels, composer, 0), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(LiveType.INSTANCE.getCatLabel(), liveColors.m6402getFg0d7_KjU(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null), composer, 0, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
            if (androidx.compose.foundation.c.D(composer)) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object TouchCategoryRail$lambda$0$0$1(int i10, TouchCategoryRailItem touchCategoryRailItem) {
        return androidx.fragment.app.a2.j(i10, touchCategoryRailItem.getId(), "#");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 TouchCategoryRail$lambda$1(LiveCategoryTree liveCategoryTree, String str, r7.l lVar, r7.a aVar, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        TouchCategoryRail(liveCategoryTree, str, lVar, aVar, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    private static final List<TouchCategoryRailItem> rememberTouchRailItems(LiveCategoryTree liveCategoryTree, String str, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-143174894, i10, -1, "com.arflix.tv.ui.screens.tv.live.rememberTouchRailItems (TouchCategoryRail.kt:114)");
        }
        composer.startReplaceGroup(-152115097);
        z6.c cVarN = t7.a.n();
        composer.startReplaceGroup(-152114554);
        for (LiveCategory liveCategory : liveCategoryTree.getTop()) {
            cVarN.add(new TouchCategoryRailItem(liveCategory.getId(), LiveCategoryKt.liveCategoryLabel(liveCategory.getLabel(), composer, 0), liveCategory.getCount()));
        }
        composer.endReplaceGroup();
        composer.startReplaceGroup(-152110842);
        for (LiveCategory liveCategory2 : liveCategoryTree.getGlobal().getCategories()) {
            cVarN.add(new TouchCategoryRailItem(liveCategory2.getId(), LiveCategoryKt.liveCategoryLabel(liveCategory2.getLabel(), composer, 0), liveCategory2.getCount()));
        }
        composer.endReplaceGroup();
        composer.startReplaceGroup(-152107034);
        for (LiveCategory liveCategory3 : liveCategoryTree.getCountries().getCategories()) {
            cVarN.add(new TouchCategoryRailItem(liveCategory3.getId(), LiveCategoryKt.liveCategoryLabel(liveCategory3.getLabel(), composer, 0), liveCategory3.getCount()));
        }
        composer.endReplaceGroup();
        composer.startReplaceGroup(-152103354);
        for (LiveCategory liveCategory4 : liveCategoryTree.getAdult().getCategories()) {
            cVarN.add(new TouchCategoryRailItem(liveCategory4.getId(), LiveCategoryKt.liveCategoryLabel(liveCategory4.getLabel(), composer, 0), liveCategory4.getCount()));
        }
        composer.endReplaceGroup();
        z6.c cVarE = t7.a.e(cVarN);
        composer.endReplaceGroup();
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        ListIterator listIterator = cVarE.listIterator(0);
        while (true) {
            z6.a aVar = (z6.a) listIterator;
            if (!aVar.hasNext()) {
                break;
            }
            Object next = aVar.next();
            if (hashSet.add(((TouchCategoryRailItem) next).getId())) {
                arrayList.add(next);
            }
        }
        ArrayList arrayList2 = new ArrayList(arrayList);
        LiveCategory liveCategoryById = liveCategoryTree.byId(str);
        if (liveCategoryById == null) {
            composer.startReplaceGroup(-419895056);
            composer.endReplaceGroup();
            break;
        }
        if (!arrayList2.isEmpty()) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                if (kotlin.jvm.internal.p.a(((TouchCategoryRailItem) it.next()).getId(), str)) {
                    composer.startReplaceGroup(-419895056);
                    composer.endReplaceGroup();
                    break;
                }
            }
        }
        composer.startReplaceGroup(-420004610);
        arrayList2.add(0, new TouchCategoryRailItem(liveCategoryById.getId(), LiveCategoryKt.liveCategoryLabel(liveCategoryById.getLabel(), composer, 0), liveCategoryById.getCount()));
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return arrayList2;
    }
}
