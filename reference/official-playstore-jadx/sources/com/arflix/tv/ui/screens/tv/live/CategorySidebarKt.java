package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.AppsKt;
import androidx.compose.material.icons.filled.ChildCareKt;
import androidx.compose.material.icons.filled.GridViewKt;
import androidx.compose.material.icons.filled.HistoryKt;
import androidx.compose.material.icons.filled.KeyboardArrowDownKt;
import androidx.compose.material.icons.filled.KeyboardArrowUpKt;
import androidx.compose.material.icons.filled.LibraryBooksKt;
import androidx.compose.material.icons.filled.LibraryMusicKt;
import androidx.compose.material.icons.filled.LockKt;
import androidx.compose.material.icons.filled.MovieKt;
import androidx.compose.material.icons.filled.NewspaperKt;
import androidx.compose.material.icons.filled.PublicKt;
import androidx.compose.material.icons.filled.SportsSoccerKt;
import androidx.compose.material.icons.filled.StarKt;
import androidx.compose.material.icons.filled.VisibilityKt;
import androidx.compose.material.icons.filled.VisibilityOffKt;
import androidx.compose.material3.IconKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.window.AndroidPopup_androidKt;
import androidx.compose.ui.window.PopupProperties;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.WavUtil;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.tv.material3.TextKt;
import com.arflix.tv.R;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aá\u0002\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\u001c\b\u0002\u0010\u0010\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\u000f2\u001c\b\u0002\u0010\u0011\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\u000f2\u001c\b\u0002\u0010\u0012\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\u000f2\u001c\b\u0002\u0010\u0013\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\u000f2\u001c\b\u0002\u0010\u0014\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\u000f2\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\u0014\b\u0002\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000b0\n2\b\b\u0002\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001b\u001a\u00020\u00192\b\b\u0002\u0010\u001c\u001a\u00020\u00042\b\b\u0002\u0010\u001e\u001a\u00020\u001dH\u0007¢\u0006\u0004\b\u001f\u0010 \u001aq\u0010&\u001a\u00020\u000b2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\u0006\u0010\u0005\u001a\u00020\u00042\u000e\b\u0002\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\u000e\b\u0002\u0010#\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\u0014\b\u0002\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000b0\n2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010%\u001a\u00020\u0004H\u0003¢\u0006\u0004\b&\u0010'\u001a\u001f\u0010)\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0003¢\u0006\u0004\b)\u0010*\u001a·\u0001\u0010;\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020\u00022\u0006\u0010+\u001a\u00020\u00192\b\u0010-\u001a\u0004\u0018\u00010,2\u0006\u0010.\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\u0010\b\u0002\u0010/\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\r2\u0010\b\u0002\u00100\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\r2\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u00103\u001a\u00020\u00042\b\b\u0002\u00104\u001a\u00020\u00042\b\b\u0002\u00106\u001a\u0002052\b\b\u0002\u00108\u001a\u0002072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\bH\u0003¢\u0006\u0004\b9\u0010:\u001a[\u0010C\u001a\u00020\u000b2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\f\u0010?\u001a\b\u0012\u0004\u0012\u00020>0=2\u0006\u0010@\u001a\u00020\u00192\u0012\u0010A\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u000b0\n2\u0012\u0010B\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u000b0\nH\u0003¢\u0006\u0004\bC\u0010D\u001as\u0010K\u001a\b\u0012\u0004\u0012\u00020>0=2\u0006\u0010E\u001a\u00020\u00042\u0006\u0010F\u001a\u00020\u00042\u0006\u0010G\u001a\u00020\u00042\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\f\u0010I\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u000b0\rH\u0002¢\u0006\u0004\bK\u0010L\u001a-\u0010O\u001a\u00020\u000b2\u0006\u0010M\u001a\u00020>2\u0006\u0010N\u001a\u00020\u00042\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000b0\rH\u0003¢\u0006\u0004\bO\u0010P\u001a!\u0010Q\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\bQ\u0010R\u001a\u001b\u0010U\u001a\u00020\u0004*\u00020S2\u0006\u0010T\u001a\u00020\u0002H\u0002¢\u0006\u0004\bU\u0010V\u001a\u0019\u0010X\u001a\u0004\u0018\u00010,2\u0006\u0010W\u001a\u00020SH\u0002¢\u0006\u0004\bX\u0010Y\u001a\u0015\u0010[\u001a\u00020\u00022\u0006\u0010Z\u001a\u00020\u0019¢\u0006\u0004\b[\u0010\\¨\u0006i²\u0006\f\u0010]\u001a\u0002058\nX\u008a\u0084\u0002²\u0006\u0010\u0010^\u001a\u0004\u0018\u00010\u00028\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010_\u001a\u00020\u00048\n@\nX\u008a\u008e\u0002²\u0006\u0010\u0010a\u001a\u0004\u0018\u00010`8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010b\u001a\u00020\u00048\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010c\u001a\u00020\u00048\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010d\u001a\u00020\u00048\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010N\u001a\u00020\u00048\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010N\u001a\u00020\u00048\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010e\u001a\u00020\u00048\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010f\u001a\u00020\u00048\n@\nX\u008a\u008e\u0002²\u0006\u0010\u0010h\u001a\u0004\u0018\u00010g8\n@\nX\u008a\u008e\u0002"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryTree;", "tree", "", "selectedId", "", "expanded", "Landroidx/compose/foundation/lazy/LazyListState;", "listState", "Landroidx/compose/ui/focus/FocusRequester;", "focusRequester", "Lkotlin/Function1;", "Lx6/t0;", "onSelect", "Lkotlin/Function0;", "onOpenSearch", "Lkotlin/Function2;", "onHideCategory", "onUnhideCategory", "onMoveCategoryUp", "onMoveCategoryToTop", "onMoveCategoryDown", "onFocusEnter", "onMoveRight", "onMoveUpFromSearch", "onTopBoundaryFocusChanged", "", "focusSearchSignal", "focusCategorySignal", "isTouchDevice", "Landroidx/compose/ui/Modifier;", "modifier", "CategorySidebar", "(Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryTree;Ljava/lang/String;ZLandroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/ui/focus/FocusRequester;Lr7/l;Lr7/a;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/a;Lr7/a;Lr7/a;Lr7/l;IIZLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;III)V", "onClick", "onMoveUp", "onMoveDown", "onFocusChanged", "focusable", "SearchEntry", "(Lr7/a;ZLr7/a;Lr7/a;Lr7/l;Landroidx/compose/ui/focus/FocusRequester;ZLandroidx/compose/runtime/Composer;II)V", "label", "SectionHeader", "(Ljava/lang/String;ZLandroidx/compose/runtime/Composer;I)V", "count", "Landroidx/compose/ui/graphics/vector/ImageVector;", "icon", "active", "onFocused", "onLongClick", "flagEmoji", "leadingCode", "hasChildren", "isOpenGroup", "Landroidx/compose/ui/unit/Dp;", "indent", "Landroidx/compose/ui/unit/TextUnit;", "labelSize", "SidebarRow-cR7IE_o", "(Ljava/lang/String;ILandroidx/compose/ui/graphics/vector/ImageVector;ZZLr7/a;Lr7/a;Lr7/a;Ljava/lang/String;Ljava/lang/String;ZZFJLandroidx/compose/ui/focus/FocusRequester;Landroidx/compose/runtime/Composer;III)V", "SidebarRow", "onDismiss", "", "Lcom/arflix/tv/ui/screens/tv/live/CategoryMenuAction;", "actions", "focusedIndex", "onFocusedIndexChange", "onAction", "CategoryContextMenu", "(Lr7/a;Ljava/util/List;ILr7/l;Lr7/l;Landroidx/compose/runtime/Composer;I)V", "canHide", "canUnhide", "canMove", "onHide", "onUnhide", "onMoveToTop", "buildCategoryMenuActions", "(ZZZLr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;)Ljava/util/List;", "action", "focused", "CategoryMenuItem", "(Lcom/arflix/tv/ui/screens/tv/live/CategoryMenuAction;ZLr7/a;Landroidx/compose/runtime/Composer;I)V", "selectedCountryGroupId", "(Ljava/lang/String;Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryTree;)Ljava/lang/String;", "Lcom/arflix/tv/ui/screens/tv/live/LiveCategory;", TtmlNode.ATTR_ID, "containsId", "(Lcom/arflix/tv/ui/screens/tv/live/LiveCategory;Ljava/lang/String;)Z", "cat", "iconFor", "(Lcom/arflix/tv/ui/screens/tv/live/LiveCategory;)Landroidx/compose/ui/graphics/vector/ImageVector;", "n", "formatCount", "(I)Ljava/lang/String;", "animatedWidth", "expandedCountry", "expandedAll", "Lcom/arflix/tv/ui/screens/tv/live/CategoryMenuState;", "activeMenu", "searchHasFocus", "userChoseSearch", "categoryHasHadFocus", "consumedLongPress", "selectPressed", "Lka/v1;", "longPressJob", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class CategorySidebarKt {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CategoryIcon.values().length];
            try {
                iArr[CategoryIcon.Favorite.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CategoryIcon.Recent.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CategoryIcon.All.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[CategoryIcon.Grid.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[CategoryIcon.Sport.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[CategoryIcon.Movie.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[CategoryIcon.News.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[CategoryIcon.Kids.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[CategoryIcon.Docs.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[CategoryIcon.Music.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[CategoryIcon.Lock.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[CategoryIcon.Country.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[CategoryIcon.SubEntry.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static final void CategoryContextMenu(final r7.a<x6.t0> aVar, final List<CategoryMenuAction> list, final int i10, final r7.l<? super Integer, x6.t0> lVar, final r7.l<? super Integer, x6.t0> lVar2, Composer composer, final int i11) {
        final r7.a<x6.t0> aVar2;
        int i12;
        int i13;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        r7.p<? super Composer, ? super Integer, x6.t0> pVar;
        Composer composerStartRestartGroup = composer.startRestartGroup(509195579);
        if ((i11 & 6) == 0) {
            aVar2 = aVar;
            i12 = (composerStartRestartGroup.changedInstance(aVar2) ? 4 : 2) | i11;
        } else {
            aVar2 = aVar;
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(list) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changed(i10) ? 256 : 128;
        }
        if ((i12 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            i13 = i10;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(509195579, i12, -1, "com.arflix.tv.ui.screens.tv.live.CategoryContextMenu (CategorySidebar.kt:808)");
            }
            if (list.isEmpty()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final int i14 = 0;
                    pVar = new r7.p() { // from class: com.arflix.tv.ui.screens.tv.live.o
                        @Override // r7.p
                        public final Object invoke(Object obj, Object obj2) {
                            switch (i14) {
                                case 0:
                                    int iIntValue = ((Integer) obj2).intValue();
                                    return CategorySidebarKt.CategoryContextMenu$lambda$0(aVar2, list, i10, lVar, lVar2, i11, (Composer) obj, iIntValue);
                                default:
                                    int iIntValue2 = ((Integer) obj2).intValue();
                                    return CategorySidebarKt.CategoryContextMenu$lambda$2(aVar2, list, i10, lVar, lVar2, i11, (Composer) obj, iIntValue2);
                            }
                        }
                    };
                    scopeUpdateScopeEndRestartGroup.updateScope(pVar);
                }
                return;
            }
            i13 = i10;
            AndroidPopup_androidKt.m5932PopupK5zGePQ(Alignment.INSTANCE.getCenterEnd(), 0L, aVar, new PopupProperties(false, false, false, null, false, false, 56, null), ComposableLambdaKt.rememberComposableLambda(2062366942, true, new com.arflix.tv.ui.screens.collections.k(list, i13, 1), composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | 27654, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final int i15 = 1;
            final int i16 = i13;
            pVar = new r7.p() { // from class: com.arflix.tv.ui.screens.tv.live.o
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    switch (i15) {
                        case 0:
                            int iIntValue = ((Integer) obj2).intValue();
                            return CategorySidebarKt.CategoryContextMenu$lambda$0(aVar, list, i16, lVar, lVar2, i11, (Composer) obj, iIntValue);
                        default:
                            int iIntValue2 = ((Integer) obj2).intValue();
                            return CategorySidebarKt.CategoryContextMenu$lambda$2(aVar, list, i16, lVar, lVar2, i11, (Composer) obj, iIntValue2);
                    }
                }
            };
            scopeUpdateScopeEndRestartGroup.updateScope(pVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategoryContextMenu$lambda$0(r7.a aVar, List list, int i10, r7.l lVar, r7.l lVar2, int i11, Composer composer, int i12) {
        CategoryContextMenu(aVar, list, i10, lVar, lVar2, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategoryContextMenu$lambda$1(List list, int i10, Composer composer, int i11) {
        if ((i11 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2062366942, i11, -1, "com.arflix.tv.ui.screens.tv.live.CategoryContextMenu.<anonymous> (CategorySidebar.kt:820)");
            }
            Modifier modifierM583width3ABfNKs = SizeKt.m583width3ABfNKs(Modifier.INSTANCE, Dp.m5678constructorimpl(184));
            LiveColors liveColors = LiveColors.INSTANCE;
            float f10 = 10;
            Modifier modifierM529padding3ABfNKs = PaddingKt.m529padding3ABfNKs(com.arflix.tv.data.repository.g.k(f10, com.arflix.tv.data.repository.g.l(f10, modifierM583width3ABfNKs, liveColors.m6411getPanelRaised0d7_KjU()), Dp.m5678constructorimpl(1), Color.m3471copywmQWz5c$default(liveColors.m6406getFocusRing0d7_KjU(), 0.7f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(4));
            MeasurePolicy measurePolicyJ = androidx.compose.foundation.c.j(Alignment.INSTANCE, androidx.compose.foundation.c.g(3, Arrangement.INSTANCE, composer, -483455358), composer, 6, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM529padding3ABfNKs);
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
            r7.p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyJ, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            composer.startReplaceGroup(-1067210113);
            int i12 = 0;
            for (Object obj : list) {
                int i13 = i12 + 1;
                if (i12 < 0) {
                    t7.a.Q();
                    throw null;
                }
                CategoryMenuAction categoryMenuAction = (CategoryMenuAction) obj;
                CategoryMenuItem(categoryMenuAction, i12 == i10, categoryMenuAction.getOnClick(), composer, 0);
                i12 = i13;
            }
            composer.endReplaceGroup();
            composer.endReplaceableGroup();
            composer.endNode();
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategoryContextMenu$lambda$2(r7.a aVar, List list, int i10, r7.l lVar, r7.l lVar2, int i11, Composer composer, int i12) {
        CategoryContextMenu(aVar, list, i10, lVar, lVar2, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1));
        return x6.t0.f22605a;
    }

    private static final void CategoryMenuItem(final CategoryMenuAction categoryMenuAction, final boolean z, final r7.a<x6.t0> aVar, Composer composer, final int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(1046540011);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(categoryMenuAction) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar) ? 256 : 128;
        }
        if ((i11 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1046540011, i11, -1, "com.arflix.tv.ui.screens.tv.live.CategoryMenuItem (CategorySidebar.kt:884)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            float f10 = 8;
            Modifier modifierM179backgroundbw27NRU$default = BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.n(SizeKt.m564height3ABfNKs(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), Dp.m5678constructorimpl(36)), f10), z ? LiveColors.INSTANCE.m6406getFocusRing0d7_KjU() : Color.INSTANCE.m3507getTransparent0d7_KjU(), null, 2, null);
            int i12 = i11 & 896;
            int i13 = 0;
            boolean z5 = i12 == 256;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z5 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new l(i13, aVar);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Modifier modifierM214clickableXHw0xAI$default = ClickableKt.m214clickableXHw0xAI$default(modifierM179backgroundbw27NRU$default, false, null, null, (r7.a) objRememberedValue, 7, null);
            boolean z10 = i12 == 256;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z10 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new CategorySidebarKt$CategoryMenuItem$2$1(aVar, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            Modifier modifierM531paddingVpY3zN4$default = PaddingKt.m531paddingVpY3zN4$default(SuspendingPointerInputFilterKt.pointerInput(modifierM214clickableXHw0xAI$default, aVar, (r7.p<? super PointerInputScope, ? super d7.d<? super x6.t0>, ? extends Object>) objRememberedValue2), Dp.m5678constructorimpl(10), 0.0f, 2, null);
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(androidx.compose.foundation.c.g(f10, Arrangement.INSTANCE, composerStartRestartGroup, 693286680), Alignment.INSTANCE.getCenterVertically(), composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM531paddingVpY3zN4$default);
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composerStartRestartGroup);
            r7.p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            IconKt.m1610Iconww6aTOc(categoryMenuAction.getIcon(), (String) null, SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(16)), z ? Color.INSTANCE.m3498getBlack0d7_KjU() : LiveColors.INSTANCE.m6403getFgDim0d7_KjU(), composerStartRestartGroup, 432, 0);
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(categoryMenuAction.getLabelRes(), composerStartRestartGroup, 0), null, 0L, 0L, null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m5632getEllipsisgIe3tQ8(), false, 1, 0, null, TextStyle.m5205copyp1EtxEg$default(LiveType.INSTANCE.getCatLabel(), z ? Color.INSTANCE.m3498getBlack0d7_KjU() : LiveColors.INSTANCE.m6402getFg0d7_KjU(), TextUnitKt.getSp(11), FontWeight.INSTANCE.getSemiBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777208, null), composerStartRestartGroup, 0, 3120, 55294);
            composerStartRestartGroup = composerStartRestartGroup;
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new r7.p() { // from class: com.arflix.tv.ui.screens.tv.live.m
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return CategorySidebarKt.CategoryMenuItem$lambda$3(categoryMenuAction, z, aVar, i10, (Composer) obj, iIntValue);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategoryMenuItem$lambda$0$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategoryMenuItem$lambda$3(CategoryMenuAction categoryMenuAction, boolean z, r7.a aVar, int i10, Composer composer, int i11) {
        CategoryMenuItem(categoryMenuAction, z, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0259  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x02f7  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x0310  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x032d  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0331  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x034a  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x034e  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x0367  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0386  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x038c  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x038f  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0393  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0396  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x039a  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x039d  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x03a1  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x03a6  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x03ae  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x03b9  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x03c4  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x0400  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x0435  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x0465  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x0470  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x047e  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x0490  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x04a2  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x04bc  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x04ca  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x05ad  */
    /* JADX WARN: Removed duplicated region for block: B:346:0x05b5  */
    /* JADX WARN: Removed duplicated region for block: B:348:0x05b9  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x05c7  */
    /* JADX WARN: Removed duplicated region for block: B:354:0x05f2  */
    /* JADX WARN: Removed duplicated region for block: B:355:0x05fe  */
    /* JADX WARN: Removed duplicated region for block: B:358:0x060c  */
    /* JADX WARN: Removed duplicated region for block: B:359:0x061a  */
    /* JADX WARN: Removed duplicated region for block: B:362:0x062c  */
    /* JADX WARN: Removed duplicated region for block: B:365:0x0676  */
    /* JADX WARN: Removed duplicated region for block: B:366:0x0678  */
    /* JADX WARN: Removed duplicated region for block: B:369:0x0687  */
    /* JADX WARN: Removed duplicated region for block: B:370:0x0689  */
    /* JADX WARN: Removed duplicated region for block: B:377:0x069f  */
    /* JADX WARN: Removed duplicated region for block: B:380:0x06c1  */
    /* JADX WARN: Removed duplicated region for block: B:381:0x06c3  */
    /* JADX WARN: Removed duplicated region for block: B:386:0x06d0  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x06e8  */
    /* JADX WARN: Removed duplicated region for block: B:390:0x06ea  */
    /* JADX WARN: Removed duplicated region for block: B:397:0x070a  */
    /* JADX WARN: Removed duplicated region for block: B:400:0x0738  */
    /* JADX WARN: Removed duplicated region for block: B:403:0x0770  */
    /* JADX WARN: Removed duplicated region for block: B:405:0x0777  */
    /* JADX WARN: Removed duplicated region for block: B:408:0x0783  */
    /* JADX WARN: Removed duplicated region for block: B:409:0x0785  */
    /* JADX WARN: Removed duplicated region for block: B:416:0x0796  */
    /* JADX WARN: Removed duplicated region for block: B:419:0x07b1  */
    /* JADX WARN: Removed duplicated region for block: B:420:0x07b3  */
    /* JADX WARN: Removed duplicated region for block: B:427:0x07c5  */
    /* JADX WARN: Removed duplicated region for block: B:431:0x0821  */
    /* JADX WARN: Removed duplicated region for block: B:434:0x082d  */
    /* JADX WARN: Removed duplicated region for block: B:435:0x0831  */
    /* JADX WARN: Removed duplicated region for block: B:440:0x0850  */
    /* JADX WARN: Removed duplicated region for block: B:443:0x0870  */
    /* JADX WARN: Removed duplicated region for block: B:444:0x0872  */
    /* JADX WARN: Removed duplicated region for block: B:451:0x0884  */
    /* JADX WARN: Removed duplicated region for block: B:454:0x0897  */
    /* JADX WARN: Removed duplicated region for block: B:455:0x0899  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:462:0x08ac  */
    /* JADX WARN: Removed duplicated region for block: B:465:0x091d  */
    /* JADX WARN: Removed duplicated region for block: B:466:0x091f  */
    /* JADX WARN: Removed duplicated region for block: B:469:0x092b  */
    /* JADX WARN: Removed duplicated region for block: B:470:0x092d  */
    /* JADX WARN: Removed duplicated region for block: B:473:0x0933  */
    /* JADX WARN: Removed duplicated region for block: B:474:0x0935  */
    /* JADX WARN: Removed duplicated region for block: B:477:0x0939  */
    /* JADX WARN: Removed duplicated region for block: B:478:0x093b  */
    /* JADX WARN: Removed duplicated region for block: B:481:0x0943  */
    /* JADX WARN: Removed duplicated region for block: B:482:0x0946  */
    /* JADX WARN: Removed duplicated region for block: B:487:0x095b  */
    /* JADX WARN: Removed duplicated region for block: B:507:0x0a2e  */
    /* JADX WARN: Removed duplicated region for block: B:510:0x0a3d  */
    /* JADX WARN: Removed duplicated region for block: B:514:0x0a63  */
    /* JADX WARN: Removed duplicated region for block: B:516:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x011c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void CategorySidebar(final com.arflix.tv.ui.screens.tv.live.LiveCategoryTree r60, final java.lang.String r61, final boolean r62, final androidx.compose.foundation.lazy.LazyListState r63, androidx.compose.ui.focus.FocusRequester r64, final r7.l<? super java.lang.String, x6.t0> r65, final r7.a<x6.t0> r66, r7.p<? super java.lang.String, ? super java.lang.String, x6.t0> r67, r7.p<? super java.lang.String, ? super java.lang.String, x6.t0> r68, r7.p<? super java.lang.String, ? super java.lang.String, x6.t0> r69, r7.p<? super java.lang.String, ? super java.lang.String, x6.t0> r70, r7.p<? super java.lang.String, ? super java.lang.String, x6.t0> r71, r7.a<x6.t0> r72, r7.a<x6.t0> r73, r7.a<x6.t0> r74, r7.l<? super java.lang.Boolean, x6.t0> r75, int r76, int r77, boolean r78, androidx.compose.ui.Modifier r79, androidx.compose.runtime.Composer r80, final int r81, final int r82, final int r83) {
        /*
            Method dump skipped, instruction units count: 2691
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.CategorySidebarKt.CategorySidebar(com.arflix.tv.ui.screens.tv.live.LiveCategoryTree, java.lang.String, boolean, androidx.compose.foundation.lazy.LazyListState, androidx.compose.ui.focus.FocusRequester, r7.l, r7.a, r7.p, r7.p, r7.p, r7.p, r7.p, r7.a, r7.a, r7.a, r7.l, int, int, boolean, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategorySidebar$lambda$0$0(String str, String str2) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategorySidebar$lambda$1$0(String str, String str2) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState CategorySidebar$lambda$10$0() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String CategorySidebar$lambda$11(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState CategorySidebar$lambda$13$0() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean CategorySidebar$lambda$14(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void CategorySidebar$lambda$15(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CategoryMenuState CategorySidebar$lambda$17(MutableState<CategoryMenuState> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategorySidebar$lambda$2$0(String str, String str2) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategorySidebar$lambda$22$0$0(r7.p pVar, CategoryMenuState categoryMenuState, MutableState mutableState) {
        mutableState.setValue(null);
        pVar.invoke(categoryMenuState.getPlaylistId(), categoryMenuState.getGroupName());
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategorySidebar$lambda$22$1$0(r7.p pVar, CategoryMenuState categoryMenuState, MutableState mutableState) {
        mutableState.setValue(null);
        pVar.invoke(categoryMenuState.getPlaylistId(), categoryMenuState.getGroupName());
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategorySidebar$lambda$22$2$0(r7.p pVar, CategoryMenuState categoryMenuState, MutableState mutableState) {
        mutableState.setValue(null);
        pVar.invoke(categoryMenuState.getPlaylistId(), categoryMenuState.getGroupName());
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategorySidebar$lambda$22$3$0(r7.p pVar, CategoryMenuState categoryMenuState, MutableState mutableState) {
        mutableState.setValue(null);
        pVar.invoke(categoryMenuState.getPlaylistId(), categoryMenuState.getGroupName());
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategorySidebar$lambda$22$4$0(r7.p pVar, CategoryMenuState categoryMenuState, MutableState mutableState) {
        mutableState.setValue(null);
        pVar.invoke(categoryMenuState.getPlaylistId(), categoryMenuState.getGroupName());
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategorySidebar$lambda$23$0(MutableState mutableState) {
        mutableState.setValue(null);
        return x6.t0.f22605a;
    }

    private static final boolean CategorySidebar$lambda$25(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void CategorySidebar$lambda$26(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean CategorySidebar$lambda$28(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void CategorySidebar$lambda$29(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategorySidebar$lambda$3$0(String str, String str2) {
        return x6.t0.f22605a;
    }

    private static final boolean CategorySidebar$lambda$31(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void CategorySidebar$lambda$32(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategorySidebar$lambda$36$0(r7.a aVar, FocusState focusState) {
        if (focusState.getHasFocus()) {
            aVar.invoke();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategorySidebar$lambda$38$0$0(LiveCategoryTree liveCategoryTree, r7.l lVar) {
        LiveCategory liveCategory = (LiveCategory) kotlin.collections.x.o0(liveCategoryTree.getTop());
        if (liveCategory != null) {
            lVar.invoke(liveCategory.getId());
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategorySidebar$lambda$38$1$0(r7.l lVar, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, boolean z) {
        if (z && CategorySidebar$lambda$31(mutableState)) {
            CategorySidebar$lambda$29(mutableState2, true);
        }
        CategorySidebar$lambda$26(mutableState3, z);
        lVar.invoke(Boolean.valueOf(z));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategorySidebar$lambda$38$2$0(final LiveCategoryTree liveCategoryTree, String str, FocusRequester focusRequester, FocusRequester focusRequester2, boolean z, MutableState mutableState, r7.l lVar, r7.l lVar2, MutableState mutableState2, MutableState mutableState3, r7.p pVar, MutableState mutableState4, LazyListScope lazyListScope) {
        List<LiveCategory> top = liveCategoryTree.getTop();
        final boolean z5 = z;
        lazyListScope.items(top.size(), new CategorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$1(new l0(9), top), new CategorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$2(top), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new CategorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$3(top, str, focusRequester, focusRequester2, z, mutableState, lVar, lVar2, mutableState2)));
        if (!liveCategoryTree.getGlobal().getCategories().isEmpty()) {
            final int i10 = 0;
            LazyListScope.CC.i(lazyListScope, null, null, ComposableLambdaKt.composableLambdaInstance(1941865336, true, new r7.q() { // from class: com.arflix.tv.ui.screens.tv.live.k
                @Override // r7.q
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    int i11 = i10;
                    LazyItemScope lazyItemScope = (LazyItemScope) obj;
                    Composer composer = (Composer) obj2;
                    int iIntValue = ((Integer) obj3).intValue();
                    switch (i11) {
                        case 0:
                            return CategorySidebarKt.CategorySidebar$lambda$38$2$0$2(liveCategoryTree, z5, lazyItemScope, composer, iIntValue);
                        case 1:
                            return CategorySidebarKt.CategorySidebar$lambda$38$2$0$5(liveCategoryTree, z5, lazyItemScope, composer, iIntValue);
                        case 2:
                            return CategorySidebarKt.CategorySidebar$lambda$38$2$0$8(liveCategoryTree, z5, lazyItemScope, composer, iIntValue);
                        default:
                            return CategorySidebarKt.CategorySidebar$lambda$38$2$0$11(liveCategoryTree, z5, lazyItemScope, composer, iIntValue);
                    }
                }
            }), 3, null);
            List<LiveCategory> categories = liveCategoryTree.getGlobal().getCategories();
            l0 l0Var = new l0(10);
            int size = categories.size();
            CategorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$4 categorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$4 = new CategorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$4(l0Var, categories);
            CategorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$5 categorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$5 = new CategorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$5(categories);
            CategorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$6 categorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$6 = new CategorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$6(categories, str, focusRequester, z5, lVar, lVar2, mutableState2, mutableState3);
            z5 = z5;
            lazyListScope.items(size, categorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$4, categorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$5, ComposableLambdaKt.composableLambdaInstance(-1091073711, true, categorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$6));
        }
        if (!liveCategoryTree.getHidden().getCategories().isEmpty()) {
            final int i11 = 1;
            LazyListScope.CC.i(lazyListScope, null, null, ComposableLambdaKt.composableLambdaInstance(-893944593, true, new r7.q() { // from class: com.arflix.tv.ui.screens.tv.live.k
                @Override // r7.q
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    int i112 = i11;
                    LazyItemScope lazyItemScope = (LazyItemScope) obj;
                    Composer composer = (Composer) obj2;
                    int iIntValue = ((Integer) obj3).intValue();
                    switch (i112) {
                        case 0:
                            return CategorySidebarKt.CategorySidebar$lambda$38$2$0$2(liveCategoryTree, z5, lazyItemScope, composer, iIntValue);
                        case 1:
                            return CategorySidebarKt.CategorySidebar$lambda$38$2$0$5(liveCategoryTree, z5, lazyItemScope, composer, iIntValue);
                        case 2:
                            return CategorySidebarKt.CategorySidebar$lambda$38$2$0$8(liveCategoryTree, z5, lazyItemScope, composer, iIntValue);
                        default:
                            return CategorySidebarKt.CategorySidebar$lambda$38$2$0$11(liveCategoryTree, z5, lazyItemScope, composer, iIntValue);
                    }
                }
            }), 3, null);
            List<LiveCategory> categories2 = liveCategoryTree.getHidden().getCategories();
            boolean z10 = z5;
            z5 = z10;
            lazyListScope.items(categories2.size(), new CategorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$7(new l0(11), categories2), new CategorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$8(categories2), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new CategorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$9(categories2, str, focusRequester, z10, pVar, lVar2, mutableState2, mutableState3)));
        }
        if (!liveCategoryTree.getCountries().getCategories().isEmpty()) {
            final int i12 = 2;
            LazyListScope.CC.i(lazyListScope, null, null, ComposableLambdaKt.composableLambdaInstance(191413232, true, new r7.q() { // from class: com.arflix.tv.ui.screens.tv.live.k
                @Override // r7.q
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    int i112 = i12;
                    LazyItemScope lazyItemScope = (LazyItemScope) obj;
                    Composer composer = (Composer) obj2;
                    int iIntValue = ((Integer) obj3).intValue();
                    switch (i112) {
                        case 0:
                            return CategorySidebarKt.CategorySidebar$lambda$38$2$0$2(liveCategoryTree, z5, lazyItemScope, composer, iIntValue);
                        case 1:
                            return CategorySidebarKt.CategorySidebar$lambda$38$2$0$5(liveCategoryTree, z5, lazyItemScope, composer, iIntValue);
                        case 2:
                            return CategorySidebarKt.CategorySidebar$lambda$38$2$0$8(liveCategoryTree, z5, lazyItemScope, composer, iIntValue);
                        default:
                            return CategorySidebarKt.CategorySidebar$lambda$38$2$0$11(liveCategoryTree, z5, lazyItemScope, composer, iIntValue);
                    }
                }
            }), 3, null);
            List<LiveCategory> categories3 = liveCategoryTree.getCountries().getCategories();
            boolean z11 = z5;
            z5 = z11;
            lazyListScope.items(categories3.size(), new CategorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$10(new l0(12), categories3), new CategorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$11(categories3), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new CategorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$12(categories3, str, focusRequester, z11, mutableState4, lVar, lVar2, mutableState2)));
        }
        if (!liveCategoryTree.getAdult().getCategories().isEmpty()) {
            final int i13 = 3;
            LazyListScope.CC.i(lazyListScope, null, null, ComposableLambdaKt.composableLambdaInstance(1276771057, true, new r7.q() { // from class: com.arflix.tv.ui.screens.tv.live.k
                @Override // r7.q
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    int i112 = i13;
                    LazyItemScope lazyItemScope = (LazyItemScope) obj;
                    Composer composer = (Composer) obj2;
                    int iIntValue = ((Integer) obj3).intValue();
                    switch (i112) {
                        case 0:
                            return CategorySidebarKt.CategorySidebar$lambda$38$2$0$2(liveCategoryTree, z5, lazyItemScope, composer, iIntValue);
                        case 1:
                            return CategorySidebarKt.CategorySidebar$lambda$38$2$0$5(liveCategoryTree, z5, lazyItemScope, composer, iIntValue);
                        case 2:
                            return CategorySidebarKt.CategorySidebar$lambda$38$2$0$8(liveCategoryTree, z5, lazyItemScope, composer, iIntValue);
                        default:
                            return CategorySidebarKt.CategorySidebar$lambda$38$2$0$11(liveCategoryTree, z5, lazyItemScope, composer, iIntValue);
                    }
                }
            }), 3, null);
            List<LiveCategory> categories4 = liveCategoryTree.getAdult().getCategories();
            lazyListScope.items(categories4.size(), new CategorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$13(new l0(13), categories4), new CategorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$14(categories4), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new CategorySidebarKt$CategorySidebar$lambda$38$2$0$$inlined$itemsIndexed$default$15(categories4, str, focusRequester, z5, lVar, lVar2, mutableState2)));
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object CategorySidebar$lambda$38$2$0$0(int i10, LiveCategory liveCategory) {
        return "top:" + liveCategory.getId() + ":" + i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategorySidebar$lambda$38$2$0$11(LiveCategoryTree liveCategoryTree, boolean z, LazyItemScope lazyItemScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1276771057, i10, -1, "com.arflix.tv.ui.screens.tv.live.CategorySidebar.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CategorySidebar.kt:479)");
            }
            SectionHeader(LiveCategoryKt.liveSectionLabel(liveCategoryTree.getAdult().getLabel(), composer, 0), z, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object CategorySidebar$lambda$38$2$0$12(int i10, LiveCategory liveCategory) {
        return "adult:" + liveCategory.getId() + ":" + i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategorySidebar$lambda$38$2$0$2(LiveCategoryTree liveCategoryTree, boolean z, LazyItemScope lazyItemScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1941865336, i10, -1, "com.arflix.tv.ui.screens.tv.live.CategorySidebar.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CategorySidebar.kt:394)");
            }
            SectionHeader(LiveCategoryKt.liveSectionLabel(liveCategoryTree.getGlobal().getLabel(), composer, 0), z, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object CategorySidebar$lambda$38$2$0$3(int i10, LiveCategory liveCategory) {
        return "global:" + liveCategory.getId() + ":" + i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategorySidebar$lambda$38$2$0$5(LiveCategoryTree liveCategoryTree, boolean z, LazyItemScope lazyItemScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-893944593, i10, -1, "com.arflix.tv.ui.screens.tv.live.CategorySidebar.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CategorySidebar.kt:412)");
            }
            SectionHeader(LiveCategoryKt.liveSectionLabel(liveCategoryTree.getHidden().getLabel(), composer, 0), z, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object CategorySidebar$lambda$38$2$0$6(int i10, LiveCategory liveCategory) {
        return "hidden:" + liveCategory.getId() + ":" + i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategorySidebar$lambda$38$2$0$8(LiveCategoryTree liveCategoryTree, boolean z, LazyItemScope lazyItemScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(191413232, i10, -1, "com.arflix.tv.ui.screens.tv.live.CategorySidebar.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CategorySidebar.kt:433)");
            }
            SectionHeader(LiveCategoryKt.liveSectionLabel(liveCategoryTree.getCountries().getLabel(), composer, 0), z, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object CategorySidebar$lambda$38$2$0$9(int i10, LiveCategory liveCategory) {
        return "country:" + liveCategory.getId() + ":" + i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategorySidebar$lambda$38$3$0(MutableState mutableState) {
        mutableState.setValue(null);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategorySidebar$lambda$38$4$0(CategoryMenuState categoryMenuState, List list, MutableState mutableState, int i10) {
        mutableState.setValue(CategoryMenuState.copy$default(categoryMenuState, null, null, null, false, false, false, qb.d.n(i10, 0, t7.a.w(list)), 63, null));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategorySidebar$lambda$38$5$0(List list, int i10) {
        CategorySidebar$runActiveMenuAction(list, i10);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategorySidebar$lambda$39(LiveCategoryTree liveCategoryTree, String str, boolean z, LazyListState lazyListState, FocusRequester focusRequester, r7.l lVar, r7.a aVar, r7.p pVar, r7.p pVar2, r7.p pVar3, r7.p pVar4, r7.p pVar5, r7.a aVar2, r7.a aVar3, r7.a aVar4, r7.l lVar2, int i10, int i11, boolean z5, Modifier modifier, int i12, int i13, int i14, Composer composer, int i15) {
        CategorySidebar(liveCategoryTree, str, z, lazyListState, focusRequester, lVar, aVar, pVar, pVar2, pVar3, pVar4, pVar5, aVar2, aVar3, aVar4, lVar2, i10, i11, z5, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i12 | 1), RecomposeScopeImplKt.updateChangedFlags(i13), i14);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategorySidebar$lambda$4$0(String str, String str2) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CategorySidebar$lambda$8$0(boolean z) {
        return x6.t0.f22605a;
    }

    private static final float CategorySidebar$lambda$9(State<Dp> state) {
        return state.getValue().m5692unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void CategorySidebar$onCategoryFocused(r7.l<? super Boolean, x6.t0> lVar, MutableState<Boolean> mutableState) {
        CategorySidebar$lambda$32(mutableState, true);
        lVar.invoke(Boolean.FALSE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void CategorySidebar$openCategoryMenu(MutableState<CategoryMenuState> mutableState, LiveCategory liveCategory, boolean z) {
        String playlistGroupName = liveCategory.getPlaylistGroupName();
        if (playlistGroupName == null) {
            return;
        }
        String id = liveCategory.getId();
        if (z) {
            id = androidx.compose.material3.d.C("hidden:", id);
        }
        mutableState.setValue(new CategoryMenuState(id, liveCategory.getPlaylistId(), playlistGroupName, !z, !z, z, 0, 64, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void CategorySidebar$runActiveMenuAction(List<CategoryMenuAction> list, int i10) {
        r7.a<x6.t0> onClick;
        int size = list.size() - 1;
        if (size < 0) {
            size = 0;
        }
        CategoryMenuAction categoryMenuAction = (CategoryMenuAction) kotlin.collections.x.p0(qb.d.n(i10, 0, size), list);
        if (categoryMenuAction == null || (onClick = categoryMenuAction.getOnClick()) == null) {
            return;
        }
        onClick.invoke();
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x028b  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x02de  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x030d  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x034e  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x03fc  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x040b  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0419  */
    /* JADX WARN: Removed duplicated region for block: B:203:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x012e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void SearchEntry(final r7.a<x6.t0> r68, final boolean r69, r7.a<x6.t0> r70, r7.a<x6.t0> r71, r7.l<? super java.lang.Boolean, x6.t0> r72, androidx.compose.ui.focus.FocusRequester r73, boolean r74, androidx.compose.runtime.Composer r75, final int r76, final int r77) {
        /*
            Method dump skipped, instruction units count: 1064
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.CategorySidebarKt.SearchEntry(r7.a, boolean, r7.a, r7.a, r7.l, androidx.compose.ui.focus.FocusRequester, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SearchEntry$lambda$11(r7.a aVar, boolean z, r7.a aVar2, r7.a aVar3, r7.l lVar, FocusRequester focusRequester, boolean z5, int i10, int i11, Composer composer, int i12) {
        SearchEntry(aVar, z, aVar2, aVar3, lVar, focusRequester, z5, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SearchEntry$lambda$2$0(boolean z) {
        return x6.t0.f22605a;
    }

    private static final boolean SearchEntry$lambda$4(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void SearchEntry$lambda$5(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SearchEntry$lambda$6$0(r7.l lVar, MutableState mutableState, FocusState focusState) {
        SearchEntry$lambda$5(mutableState, focusState.isFocused());
        lVar.invoke(Boolean.valueOf(focusState.isFocused()));
        return x6.t0.f22605a;
    }

    private static final void SectionHeader(String str, boolean z, Composer composer, int i10) {
        int i11;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(2114823127);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i11 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2114823127, i11, -1, "com.arflix.tv.ui.screens.tv.live.SectionHeader (CategorySidebar.kt:610)");
            }
            int i12 = 0;
            if (!z) {
                composerStartRestartGroup.startReplaceGroup(-179414289);
                SpacerKt.Spacer(SizeKt.m564height3ABfNKs(Modifier.INSTANCE, Dp.m5678constructorimpl(8)), composerStartRestartGroup, 6);
                composerStartRestartGroup.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new i(str, i10, i12, z));
                    return;
                }
                return;
            }
            composerStartRestartGroup.startReplaceGroup(-179356629);
            composerStartRestartGroup.endReplaceGroup();
            float f10 = 8;
            Modifier modifierM532paddingqDBjuR0 = PaddingKt.m532paddingqDBjuR0(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m5678constructorimpl(f10), Dp.m5678constructorimpl(14), Dp.m5678constructorimpl(f10), Dp.m5678constructorimpl(4));
            composerStartRestartGroup.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyI = androidx.compose.foundation.c.i(Alignment.INSTANCE, Arrangement.INSTANCE.getStart(), composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM532paddingqDBjuR0);
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composerStartRestartGroup);
            r7.p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyI, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            composer2 = composerStartRestartGroup;
            TextKt.m6020Text4IGK_g(str, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(LiveType.INSTANCE.getSectionTag(), LiveColors.INSTANCE.m6404getFgMute0d7_KjU(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null), composer2, i11 & 14, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup2 = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup2 != null) {
            scopeUpdateScopeEndRestartGroup2.updateScope(new i(str, i10, 1, z));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SectionHeader$lambda$0(String str, boolean z, int i10, Composer composer, int i11) {
        SectionHeader(str, z, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SectionHeader$lambda$2(String str, boolean z, int i10, Composer composer, int i11) {
        SectionHeader(str, z, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:100:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x026a  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x02e6  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x02ed  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0365  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0369  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x037a  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x0388  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x0395  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x03cd  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x03e3  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x03e9  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0413  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0415  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0422  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x0433  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0438  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x0444  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x044a  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0456  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0461  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x0479  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x049d  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x049f  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x04ac  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x04ae  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x04c2  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x04e8  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x04ec  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x04f0  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x04f3  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x0506  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x0552  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x055e  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x0562  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x0586  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x0593  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x062e  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x0703  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x0866  */
    /* JADX WARN: Removed duplicated region for block: B:342:0x0875  */
    /* JADX WARN: Removed duplicated region for block: B:346:0x088f  */
    /* JADX WARN: Removed duplicated region for block: B:348:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x011f  */
    /* JADX INFO: renamed from: SidebarRow-cR7IE_o, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6342SidebarRowcR7IE_o(final java.lang.String r106, final int r107, final androidx.compose.ui.graphics.vector.ImageVector r108, final boolean r109, final boolean r110, final r7.a<x6.t0> r111, r7.a<x6.t0> r112, r7.a<x6.t0> r113, java.lang.String r114, java.lang.String r115, boolean r116, boolean r117, float r118, long r119, androidx.compose.ui.focus.FocusRequester r121, androidx.compose.runtime.Composer r122, final int r123, final int r124, final int r125) {
        /*
            Method dump skipped, instruction units count: 2222
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.CategorySidebarKt.m6342SidebarRowcR7IE_o(java.lang.String, int, androidx.compose.ui.graphics.vector.ImageVector, boolean, boolean, r7.a, r7.a, r7.a, java.lang.String, java.lang.String, boolean, boolean, float, long, androidx.compose.ui.focus.FocusRequester, androidx.compose.runtime.Composer, int, int, int):void");
    }

    private static final boolean SidebarRow_cR7IE_o$lambda$1(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ka.v1 SidebarRow_cR7IE_o$lambda$10(MutableState<ka.v1> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SidebarRow_cR7IE_o$lambda$12$0$0(r7.a aVar, MutableState mutableState, FocusState focusState) {
        SidebarRow_cR7IE_o$lambda$2(mutableState, focusState.isFocused());
        if (focusState.isFocused() && aVar != null) {
            aVar.invoke();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SidebarRow_cR7IE_o$lambda$13(String str, int i10, ImageVector imageVector, boolean z, boolean z5, r7.a aVar, r7.a aVar2, r7.a aVar3, String str2, String str3, boolean z10, boolean z11, float f10, long j10, FocusRequester focusRequester, int i11, int i12, int i13, Composer composer, int i14) {
        m6342SidebarRowcR7IE_o(str, i10, imageVector, z, z5, aVar, aVar2, aVar3, str2, str3, z10, z11, f10, j10, focusRequester, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), RecomposeScopeImplKt.updateChangedFlags(i12), i13);
        return x6.t0.f22605a;
    }

    private static final void SidebarRow_cR7IE_o$lambda$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean SidebarRow_cR7IE_o$lambda$4(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SidebarRow_cR7IE_o$lambda$5(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean SidebarRow_cR7IE_o$lambda$7(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SidebarRow_cR7IE_o$lambda$8(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final List<CategoryMenuAction> buildCategoryMenuActions(boolean z, boolean z5, boolean z10, r7.a<x6.t0> aVar, r7.a<x6.t0> aVar2, r7.a<x6.t0> aVar3, r7.a<x6.t0> aVar4, r7.a<x6.t0> aVar5) {
        z6.c cVarN = t7.a.n();
        if (z10) {
            int i10 = R.string.live_menu_move_top;
            Icons.Filled filled = Icons.Filled.INSTANCE;
            cVarN.add(new CategoryMenuAction(i10, KeyboardArrowUpKt.getKeyboardArrowUp(filled), aVar4));
            cVarN.add(new CategoryMenuAction(R.string.live_menu_move_up, KeyboardArrowUpKt.getKeyboardArrowUp(filled), aVar3));
            cVarN.add(new CategoryMenuAction(R.string.live_menu_move_down, KeyboardArrowDownKt.getKeyboardArrowDown(filled), aVar5));
        }
        if (z) {
            cVarN.add(new CategoryMenuAction(R.string.live_menu_hide_category, VisibilityOffKt.getVisibilityOff(Icons.Filled.INSTANCE), aVar));
        }
        if (z5) {
            cVarN.add(new CategoryMenuAction(R.string.live_menu_unhide_category, VisibilityKt.getVisibility(Icons.Filled.INSTANCE), aVar2));
        }
        return t7.a.e(cVarN);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean containsId(LiveCategory liveCategory, String str) {
        if (kotlin.jvm.internal.p.a(liveCategory.getId(), str)) {
            return true;
        }
        List<LiveCategory> children = liveCategory.getChildren();
        if (children != null && children.isEmpty()) {
            return false;
        }
        Iterator<T> it = children.iterator();
        while (it.hasNext()) {
            if (containsId((LiveCategory) it.next(), str)) {
                return true;
            }
        }
        return false;
    }

    public static final String formatCount(int i10) {
        if (i10 < 1000) {
            return String.valueOf(i10);
        }
        double d4 = ((double) i10) / 1000.0d;
        return d4 < 10.0d ? String.format("%.1fk", Arrays.copyOf(new Object[]{Double.valueOf(d4)}, 1)) : androidx.fragment.app.a2.i((int) d4, "k");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ImageVector iconFor(LiveCategory liveCategory) {
        switch (WhenMappings.$EnumSwitchMapping$0[liveCategory.getIconToken().ordinal()]) {
            case 1:
                return StarKt.getStar(Icons.Filled.INSTANCE);
            case 2:
                return HistoryKt.getHistory(Icons.Filled.INSTANCE);
            case 3:
                return AppsKt.getApps(Icons.Filled.INSTANCE);
            case 4:
                return GridViewKt.getGridView(Icons.Filled.INSTANCE);
            case 5:
                return SportsSoccerKt.getSportsSoccer(Icons.Filled.INSTANCE);
            case 6:
                return MovieKt.getMovie(Icons.Filled.INSTANCE);
            case 7:
                return NewspaperKt.getNewspaper(Icons.Filled.INSTANCE);
            case 8:
                return ChildCareKt.getChildCare(Icons.Filled.INSTANCE);
            case 9:
                return LibraryBooksKt.getLibraryBooks(Icons.Filled.INSTANCE);
            case 10:
                return LibraryMusicKt.getLibraryMusic(Icons.Filled.INSTANCE);
            case 11:
                return LockKt.getLock(Icons.Filled.INSTANCE);
            case 12:
                return PublicKt.getPublic(Icons.Filled.INSTANCE);
            case 13:
                return null;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String selectedCountryGroupId(String str, LiveCategoryTree liveCategoryTree) {
        Object next;
        Iterator<T> it = liveCategoryTree.getCountries().getCategories().iterator();
        loop0: while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            LiveCategory liveCategory = (LiveCategory) next;
            if (kotlin.jvm.internal.p.a(liveCategory.getId(), str)) {
                break;
            }
            List<LiveCategory> children = liveCategory.getChildren();
            if (children == null || !children.isEmpty()) {
                Iterator<T> it2 = children.iterator();
                while (it2.hasNext()) {
                    if (kotlin.jvm.internal.p.a(((LiveCategory) it2.next()).getId(), str)) {
                        break loop0;
                    }
                }
            }
        }
        LiveCategory liveCategory2 = (LiveCategory) next;
        if (liveCategory2 != null) {
            return liveCategory2.getId();
        }
        return null;
    }
}
