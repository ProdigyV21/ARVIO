package com.arflix.tv.ui.screens.search;

import android.content.res.Configuration;
import android.os.SystemClock;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.BoxWithConstraintsKt;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.foundation.lazy.LazyListStateKt;
import androidx.compose.foundation.lazy.grid.GridCells;
import androidx.compose.foundation.lazy.grid.LazyGridDslKt;
import androidx.compose.foundation.lazy.grid.LazyGridItemScope;
import androidx.compose.foundation.lazy.grid.LazyGridScope;
import androidx.compose.foundation.lazy.grid.LazyGridState;
import androidx.compose.foundation.lazy.grid.LazyGridStateKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.BasicTextFieldKt;
import androidx.compose.foundation.text.KeyboardActionScope;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.SearchKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextFieldDefaults;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusChangedModifierKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.media3.common.C;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.tv.material3.TextKt;
import com.arflix.tv.R;
import com.arflix.tv.data.model.Category;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.model.ModelsKt;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.ui.components.CardLayoutMode;
import com.arflix.tv.ui.components.CardLayoutModeKt;
import com.arflix.tv.ui.components.MediaCardKt;
import com.arflix.tv.ui.components.x1;
import com.arflix.tv.ui.components.z1;
import com.arflix.tv.ui.focus.ArvioDpadFocusKt;
import com.arflix.tv.ui.skin.ArvioFocusKt;
import com.arflix.tv.ui.skin.ArvioSkin;
import com.arflix.tv.ui.theme.AnimationConstants;
import com.arflix.tv.ui.theme.ArflixTypography;
import com.arflix.tv.ui.theme.ColorKt;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0004\u001a¡\u0001\u0010\u0010\u001a\u00020\u00072\b\b\u0002\u0010\u0001\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u001a\b\u0002\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00042\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0007¢\u0006\u0004\b\u0010\u0010\u0011\u001a¯\u0001\u0010'\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001a2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00070\u001d2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00070\t2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0003¢\u0006\u0004\b%\u0010&\u001a\u009d\u0001\u00104\u001a\u00020\u00072\f\u0010*\u001a\b\u0012\u0004\u0012\u00020)0(2\u0006\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020\u00062\u0006\u0010.\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u00100\u001a\u00020/2\b\b\u0002\u00101\u001a\u00020\u00162\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u001d2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0003¢\u0006\u0004\b4\u00105\u001a[\u0010;\u001a\u00020\u00072\u0006\u00106\u001a\u00020\u00122\u0006\u00107\u001a\u00020\u00162\b\b\u0002\u00108\u001a\u00020\u00162\b\b\u0002\u00100\u001a\u00020/2\u000e\b\u0002\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00070\t2\b\b\u0002\u00109\u001a\u00020\u00162\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0003¢\u0006\u0004\b;\u0010<\u001au\u0010H\u001a\u00020\u00072\f\u0010>\u001a\b\u0012\u0004\u0012\u00020=0(2\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00120?2\u0006\u0010A\u001a\u00020\u00062\u0006\u0010B\u001a\u00020\u00062\u0006\u0010D\u001a\u00020C2\u0006\u0010E\u001a\u00020C2\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0012\u0010G\u001a\u000e\u0012\u0004\u0012\u00020F\u0012\u0004\u0012\u00020\u00070\u001dH\u0003¢\u0006\u0004\bH\u0010I\u001aW\u0010N\u001a\u00020\u00072\f\u0010J\u001a\b\u0012\u0004\u0012\u00020F0(2\u0006\u0010K\u001a\u00020\u00162\u0006\u0010L\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0012\u0010G\u001a\u000e\u0012\u0004\u0012\u00020F\u0012\u0004\u0012\u00020\u00070\u001d2\f\u0010M\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0003¢\u0006\u0004\bN\u0010O\u001a\u0017\u0010Q\u001a\u00020\u00122\u0006\u0010P\u001a\u00020FH\u0002¢\u0006\u0004\bQ\u0010R\u001a\u0017\u0010S\u001a\u00020\u00122\u0006\u0010P\u001a\u00020FH\u0003¢\u0006\u0004\bS\u0010T\u001a1\u0010W\u001a\b\u0012\u0004\u0012\u00020F0(2\f\u0010U\u001a\b\u0012\u0004\u0012\u00020F0(2\f\u0010V\u001a\b\u0012\u0004\u0012\u00020F0(H\u0002¢\u0006\u0004\bW\u0010X¨\u0006g²\u0006\f\u0010Z\u001a\u00020Y8\nX\u008a\u0084\u0002²\u0006\u000e\u0010,\u001a\u00020+8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010[\u001a\u00020\u00068\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010\\\u001a\u00020\u00168\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010]\u001a\u00020C8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010A\u001a\u00020\u00068\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010B\u001a\u00020\u00068\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010-\u001a\u00020\u00068\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010^\u001a\u00020C8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010_\u001a\u00020\u00168\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010`\u001a\u00020\u00068\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010a\u001a\u00020\u00168\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010b\u001a\u00020\u00068\n@\nX\u008a\u008e\u0002²\u0006\f\u0010d\u001a\u00020c8\nX\u008a\u0084\u0002²\u0006\u000e\u0010e\u001a\u00020\u00068\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010f\u001a\u00020\u00068\n@\nX\u008a\u008e\u0002"}, d2 = {"Lcom/arflix/tv/ui/screens/search/SearchViewModel;", "viewModel", "Lcom/arflix/tv/data/model/Profile;", "currentProfile", "Lkotlin/Function2;", "Lcom/arflix/tv/data/model/MediaType;", "", "Lx6/t0;", "onNavigateToDetails", "Lkotlin/Function0;", "onNavigateToHome", "onNavigateToWatchlist", "onNavigateToTv", "onNavigateToSettings", "onSwitchProfile", "onBack", "SearchScreen", "(Lcom/arflix/tv/ui/screens/search/SearchViewModel;Lcom/arflix/tv/data/model/Profile;Lr7/p;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;II)V", "", "query", "Landroidx/compose/ui/unit/Dp;", "searchBarWidth", "", "isTouchDevice", "isFocused", "isEditing", "Landroidx/compose/ui/focus/FocusRequester;", "searchFocusRequester", "textInputFocusRequester", "Lkotlin/Function1;", "onQueryChange", "onSearch", "onFocused", "onFocusLost", "onStartEditing", "onMoveUp", "onMoveDown", "SearchInputBar-yZaabhs", "(Ljava/lang/String;FZZZLandroidx/compose/ui/focus/FocusRequester;Landroidx/compose/ui/focus/FocusRequester;Lr7/l;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;II)V", "SearchInputBar", "", "Lcom/arflix/tv/ui/screens/search/DiscoverQuickFilter;", "filters", "Lcom/arflix/tv/ui/screens/search/FocusZone;", "focusZone", "focusedFilterIndex", "filtersFocusRequester", "Landroidx/compose/ui/Modifier;", "modifier", "isRtl", "onMoveLeft", "onMoveRight", "DiscoverFilterStrip", "(Ljava/util/List;Lcom/arflix/tv/ui/screens/search/FocusZone;ILandroidx/compose/ui/focus/FocusRequester;ZLandroidx/compose/ui/Modifier;ZLr7/l;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;III)V", "label", "isSelected", "isVisuallyFocused", "useSystemFocusForVisuals", "onSelect", "GlowChip", "(Ljava/lang/String;ZZLandroidx/compose/ui/Modifier;Lr7/a;ZLr7/a;Landroidx/compose/runtime/Composer;II)V", "Lcom/arflix/tv/data/model/Category;", "categories", "", "cardLogoUrls", "currentRowIndex", "currentItemIndex", "", "lastNavEventTime", "fastScrollThresholdMs", "Lcom/arflix/tv/data/model/MediaItem;", "onItemClick", "RowsLayer", "(Ljava/util/List;Ljava/util/Map;IIJJZZLr7/l;Landroidx/compose/runtime/Composer;I)V", "items", "usePosterCards", "isLoading", "onLoadMore", "ContentGrid", "(Ljava/util/List;ZZZLr7/l;Lr7/a;Landroidx/compose/runtime/Composer;I)V", "item", "buildCardTitle", "(Lcom/arflix/tv/data/model/MediaItem;)Ljava/lang/String;", "buildCardSubtitle", "(Lcom/arflix/tv/data/model/MediaItem;Landroidx/compose/runtime/Composer;I)Ljava/lang/String;", "movies", "shows", "interleaveSearchResults", "(Ljava/util/List;Ljava/util/List;)Ljava/util/List;", "Lcom/arflix/tv/ui/screens/search/SearchUiState;", "uiState", "sidebarFocusIndex", "isSearchInputFocused", "suppressSelectUntilMs", "resultsLastNavEventTime", "isSearchEditing", "searchEditRequestNonce", "systemFocused", "lastAppliedTargetIndex", "", "rowAlpha", "lastScrollIndex", "lastScrollOffset", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class SearchScreenKt {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[FocusZone.values().length];
            try {
                iArr[FocusZone.RESULTS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FocusZone.FILTERS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FocusZone.SEARCH_INPUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[FocusZone.SIDEBAR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[MediaType.values().length];
            try {
                iArr2[MediaType.TV.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[MediaType.MOVIE.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    private static final void ContentGrid(final List<MediaItem> list, final boolean z, boolean z5, final boolean z10, r7.l<? super MediaItem, t0> lVar, r7.a<t0> aVar, Composer composer, int i10) {
        int i11;
        boolean z11;
        r7.l<? super MediaItem, t0> lVar2;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1737990926);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(list) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            z11 = z5;
            i11 |= composerStartRestartGroup.changed(z11) ? 256 : 128;
        } else {
            z11 = z5;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changed(z10) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            lVar2 = lVar;
            i11 |= composerStartRestartGroup.changedInstance(lVar2) ? 16384 : 8192;
        } else {
            lVar2 = lVar;
        }
        if ((196608 & i10) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar) ? 131072 : 65536;
        }
        if ((74899 & i11) == 74898 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1737990926, i11, -1, "com.arflix.tv.ui.screens.search.ContentGrid (SearchScreen.kt:1085)");
            }
            int i12 = ((Configuration) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalConfiguration())).screenHeightDp;
            final float fM5678constructorimpl = Dp.m5678constructorimpl(z ? 105 : 210);
            LazyGridState lazyGridStateRememberLazyGridState = LazyGridStateKt.rememberLazyGridState(0, 0, composerStartRestartGroup, 0, 3);
            Integer numValueOf = Integer.valueOf(lazyGridStateRememberLazyGridState.getFirstVisibleItemIndex());
            Integer numValueOf2 = Integer.valueOf(list.size());
            int i13 = i11 & 14;
            boolean zChanged = composerStartRestartGroup.changed(lazyGridStateRememberLazyGridState) | (i13 == 4) | ((i11 & 458752) == 131072);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            kotlin.jvm.internal.h hVar = null;
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new SearchScreenKt$ContentGrid$1$1(lazyGridStateRememberLazyGridState, list, aVar, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            EffectsKt.LaunchedEffect(numValueOf, numValueOf2, (r7.p) objRememberedValue, composerStartRestartGroup, 0);
            float fM5678constructorimpl2 = Dp.m5678constructorimpl(z10 ? 14 : 24);
            GridCells.Adaptive adaptive = new GridCells.Adaptive(Dp.m5678constructorimpl(fM5678constructorimpl + fM5678constructorimpl2), hVar);
            PaddingValues paddingValuesM523PaddingValuesYgX7TsA = PaddingKt.m523PaddingValuesYgX7TsA(fM5678constructorimpl2, fM5678constructorimpl2);
            Arrangement arrangement = Arrangement.INSTANCE;
            Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = arrangement.m441spacedBy0680j_4(Dp.m5678constructorimpl(18));
            Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_42 = arrangement.m441spacedBy0680j_4(Dp.m5678constructorimpl(26));
            Modifier modifierArvioDpadFocusGroup$default = ArvioDpadFocusKt.arvioDpadFocusGroup$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, false, 3, null);
            boolean zChanged2 = (i13 == 4) | composerStartRestartGroup.changed(fM5678constructorimpl) | ((i11 & 112) == 32) | ((57344 & i11) == 16384) | ((i11 & 7168) == 2048) | ((i11 & 896) == 256);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                final boolean z12 = z11;
                final r7.l<? super MediaItem, t0> lVar3 = lVar2;
                r7.l lVar4 = new r7.l() { // from class: com.arflix.tv.ui.screens.search.y
                    @Override // r7.l
                    public final Object invoke(Object obj) {
                        return SearchScreenKt.ContentGrid$lambda$1$0(list, z12, fM5678constructorimpl, z, lVar3, z10, (LazyGridScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(lVar4);
                objRememberedValue2 = lVar4;
            }
            composer2 = composerStartRestartGroup;
            LazyGridDslKt.LazyVerticalGrid(adaptive, modifierArvioDpadFocusGroup$default, lazyGridStateRememberLazyGridState, paddingValuesM523PaddingValuesYgX7TsA, false, horizontalOrVerticalM441spacedBy0680j_42, horizontalOrVerticalM441spacedBy0680j_4, null, false, (r7.l) objRememberedValue2, composer2, 1769472, MediaError.DetailedErrorCode.MANIFEST_UNKNOWN);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new z1(list, z, z5, z10, lVar, aVar, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ContentGrid$lambda$1$0(final List list, boolean z, final float f10, final boolean z5, final r7.l lVar, final boolean z10, LazyGridScope lazyGridScope) {
        LazyGridScope.CC.b(lazyGridScope, list.size(), new n(list, 1), null, null, ComposableLambdaKt.composableLambdaInstance(-1832561671, true, new r7.r() { // from class: com.arflix.tv.ui.screens.search.w
            @Override // r7.r
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                int iIntValue = ((Integer) obj4).intValue();
                return SearchScreenKt.ContentGrid$lambda$1$0$1(list, f10, z5, lVar, z10, (LazyGridItemScope) obj, ((Integer) obj2).intValue(), (Composer) obj3, iIntValue);
            }
        }), 12, null);
        if (z) {
            LazyGridScope.CC.a(lazyGridScope, null, null, null, ComposableSingletons$SearchScreenKt.INSTANCE.getLambda$1420748741$app_playRelease(), 7, null);
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object ContentGrid$lambda$1$0$0(List list, int i10) {
        return ((MediaItem) list.get(i10)).getMediaType() + "_" + ((MediaItem) list.get(i10)).getId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ContentGrid$lambda$1$0$1(List list, float f10, boolean z, final r7.l lVar, boolean z5, LazyGridItemScope lazyGridItemScope, int i10, Composer composer, int i11) {
        int i12;
        Modifier modifierM214clickableXHw0xAI$default;
        if ((i11 & 48) == 0) {
            i12 = i11 | (composer.changed(i10) ? 32 : 16);
        } else {
            i12 = i11;
        }
        if ((i12 & 145) == 144 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1832561671, i12, -1, "com.arflix.tv.ui.screens.search.ContentGrid.<anonymous>.<anonymous>.<anonymous> (SearchScreen.kt:1095)");
            }
            final MediaItem mediaItem = (MediaItem) list.get(i10);
            MediaItem mediaItemCopy$default = MediaItem.copy$default(mediaItem, 0, buildCardTitle(mediaItem), buildCardSubtitle(mediaItem, composer, 0), null, "", null, null, null, null, null, null, null, null, 0, false, null, null, null, null, null, false, null, null, null, null, null, null, null, null, false, null, 0.0f, 0L, 0, false, null, false, false, null, null, null, null, -55, AnalyticsListener.EVENT_DRM_KEYS_LOADED, null);
            boolean z10 = !z;
            Object objRememberedValue = composer.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = new b(8);
                composer.updateRememberedValue(objRememberedValue);
            }
            r7.a aVar = (r7.a) objRememberedValue;
            boolean zChanged = composer.changed(lVar) | composer.changed(mediaItem);
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChanged || objRememberedValue2 == companion.getEmpty()) {
                final int i13 = 0;
                objRememberedValue2 = new r7.a() { // from class: com.arflix.tv.ui.screens.search.z
                    @Override // r7.a
                    public final Object invoke() {
                        switch (i13) {
                            case 0:
                                return SearchScreenKt.ContentGrid$lambda$1$0$1$1$0(lVar, mediaItem);
                            default:
                                return SearchScreenKt.ContentGrid$lambda$1$0$1$2$0(lVar, mediaItem);
                        }
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            r7.a aVar2 = (r7.a) objRememberedValue2;
            if (z5) {
                composer.startReplaceGroup(-155156520);
                Modifier.Companion companion2 = Modifier.INSTANCE;
                boolean zChanged2 = composer.changed(lVar) | composer.changed(mediaItem);
                Object objRememberedValue3 = composer.rememberedValue();
                if (zChanged2 || objRememberedValue3 == companion.getEmpty()) {
                    final int i14 = 1;
                    objRememberedValue3 = new r7.a() { // from class: com.arflix.tv.ui.screens.search.z
                        @Override // r7.a
                        public final Object invoke() {
                            switch (i14) {
                                case 0:
                                    return SearchScreenKt.ContentGrid$lambda$1$0$1$1$0(lVar, mediaItem);
                                default:
                                    return SearchScreenKt.ContentGrid$lambda$1$0$1$2$0(lVar, mediaItem);
                            }
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue3);
                }
                modifierM214clickableXHw0xAI$default = ClickableKt.m214clickableXHw0xAI$default(companion2, false, null, null, (r7.a) objRememberedValue3, 7, null);
                composer.endReplaceGroup();
            } else {
                composer.startReplaceGroup(-155155359);
                composer.endReplaceGroup();
                modifierM214clickableXHw0xAI$default = Modifier.INSTANCE;
            }
            MediaCardKt.m6165MediaCardL7BDBA(mediaItemCopy$default, f10, z10, null, null, false, false, false, false, false, false, false, 2, 1, false, 0.0f, true, aVar, aVar2, null, modifierM214clickableXHw0xAI$default, composer, 805306368, 14183808, 0, 560632);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ContentGrid$lambda$1$0$1$1$0(r7.l lVar, MediaItem mediaItem) {
        lVar.invoke(mediaItem);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ContentGrid$lambda$1$0$1$2$0(r7.l lVar, MediaItem mediaItem) {
        lVar.invoke(mediaItem);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ContentGrid$lambda$2(List list, boolean z, boolean z5, boolean z10, r7.l lVar, r7.a aVar, int i10, Composer composer, int i11) {
        ContentGrid(list, z, z5, z10, lVar, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    private static final void DiscoverFilterStrip(final List<DiscoverQuickFilter> list, final FocusZone focusZone, final int i10, final FocusRequester focusRequester, final boolean z, Modifier modifier, boolean z5, final r7.l<? super Integer, t0> lVar, final r7.a<t0> aVar, final r7.a<t0> aVar2, final r7.a<t0> aVar3, final r7.a<t0> aVar4, Composer composer, final int i11, final int i12, final int i13) {
        int i14;
        FocusRequester focusRequester2;
        Modifier modifier2;
        boolean z10;
        int i15;
        final boolean z11;
        final Modifier modifier3;
        r7.p<? super Composer, ? super Integer, t0> pVar;
        ScopeUpdateScope scopeUpdateScope;
        Composer composerStartRestartGroup = composer.startRestartGroup(882646708);
        if ((i11 & 6) == 0) {
            i14 = (composerStartRestartGroup.changed(list) ? 4 : 2) | i11;
        } else {
            i14 = i11;
        }
        if ((i11 & 48) == 0) {
            i14 |= composerStartRestartGroup.changed(focusZone.ordinal()) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i14 |= composerStartRestartGroup.changed(i10) ? 256 : 128;
        }
        if ((i11 & 3072) == 0) {
            focusRequester2 = focusRequester;
            i14 |= composerStartRestartGroup.changed(focusRequester2) ? 2048 : 1024;
        } else {
            focusRequester2 = focusRequester;
        }
        if ((i11 & 24576) == 0) {
            i14 |= composerStartRestartGroup.changed(z) ? 16384 : 8192;
        }
        int i16 = i13 & 32;
        if (i16 != 0) {
            i14 |= 196608;
            modifier2 = modifier;
        } else {
            modifier2 = modifier;
            if ((i11 & 196608) == 0) {
                i14 |= composerStartRestartGroup.changed(modifier2) ? 131072 : 65536;
            }
        }
        int i17 = i13 & 64;
        if (i17 != 0) {
            i14 |= 1572864;
            z10 = z5;
        } else {
            z10 = z5;
            if ((i11 & 1572864) == 0) {
                i14 |= composerStartRestartGroup.changed(z10) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
            }
        }
        if ((i11 & 12582912) == 0) {
            i14 |= composerStartRestartGroup.changedInstance(lVar) ? 8388608 : 4194304;
        }
        if ((i11 & 100663296) == 0) {
            i14 |= composerStartRestartGroup.changedInstance(aVar) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        }
        if ((i11 & 805306368) == 0) {
            i14 |= composerStartRestartGroup.changedInstance(aVar2) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        if ((i12 & 6) == 0) {
            i15 = i12 | (composerStartRestartGroup.changedInstance(aVar3) ? 4 : 2);
        } else {
            i15 = i12;
        }
        if ((i12 & 48) == 0) {
            i15 |= composerStartRestartGroup.changedInstance(aVar4) ? 32 : 16;
        }
        int i18 = i15;
        if ((i14 & 306783379) == 306783378 && (i18 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            z11 = z10;
        } else {
            Modifier modifier4 = i16 != 0 ? Modifier.INSTANCE : modifier2;
            final boolean z12 = i17 != 0 ? false : z10;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(882646708, i14, i18, "com.arflix.tv.ui.screens.search.DiscoverFilterStrip (SearchScreen.kt:785)");
            }
            if (list.isEmpty()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final int i19 = 0;
                    final FocusRequester focusRequester3 = focusRequester2;
                    final boolean z13 = z12;
                    final Modifier modifier5 = modifier4;
                    pVar = new r7.p() { // from class: com.arflix.tv.ui.screens.search.k
                        @Override // r7.p
                        public final Object invoke(Object obj, Object obj2) {
                            Composer composer2 = (Composer) obj;
                            switch (i19) {
                                case 0:
                                    return SearchScreenKt.DiscoverFilterStrip$lambda$0(list, focusZone, i10, focusRequester3, z, modifier5, z13, lVar, aVar, aVar2, aVar3, aVar4, i11, i12, i13, composer2, ((Integer) obj2).intValue());
                                default:
                                    return SearchScreenKt.DiscoverFilterStrip$lambda$4(list, focusZone, i10, focusRequester3, z, modifier5, z13, lVar, aVar, aVar2, aVar3, aVar4, i11, i12, i13, composer2, ((Integer) obj2).intValue());
                            }
                        }
                    };
                    scopeUpdateScope = scopeUpdateScopeEndRestartGroup;
                    scopeUpdateScope.updateScope(pVar);
                }
                return;
            }
            LazyListState lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
            Integer numValueOf = Integer.valueOf(i10);
            Integer numValueOf2 = Integer.valueOf(list.size());
            int i20 = i14 & 896;
            int i21 = i14 & 14;
            boolean zChanged = (i20 == 256) | (i21 == 4) | composerStartRestartGroup.changed(lazyListStateRememberLazyListState);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new SearchScreenKt$DiscoverFilterStrip$2$1(i10, list, lazyListStateRememberLazyListState, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            EffectsKt.LaunchedEffect(numValueOf, numValueOf2, (r7.p) objRememberedValue, composerStartRestartGroup, (i14 >> 6) & 14);
            Modifier modifierM533paddingqDBjuR0$default = PaddingKt.m533paddingqDBjuR0$default(modifier4, 0.0f, 0.0f, 0.0f, Dp.m5678constructorimpl(z ? 10 : 8), 7, null);
            Modifier modifier6 = modifier4;
            boolean z14 = ((234881024 & i14) == 67108864) | ((1879048192 & i14) == 536870912) | ((3670016 & i14) == 1048576) | ((i18 & 112) == 32) | ((i18 & 14) == 4);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z14 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new r7.l<KeyEvent, Boolean>() { // from class: com.arflix.tv.ui.screens.search.SearchScreenKt$DiscoverFilterStrip$3$1
                    @Override // r7.l
                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        return m6285invokeZmokQxo(((KeyEvent) obj).m4390unboximpl());
                    }

                    /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                    public final Boolean m6285invokeZmokQxo(android.view.KeyEvent keyEvent) {
                        if (!KeyEventType.m4394equalsimpl0(KeyEvent_androidKt.m4402getTypeZmokQxo(keyEvent), KeyEventType.INSTANCE.m4398getKeyDownCS__XNY())) {
                            return Boolean.FALSE;
                        }
                        long jM4401getKeyZmokQxo = KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent);
                        Key.Companion companion = Key.INSTANCE;
                        boolean z15 = true;
                        if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion.m4171getDirectionUpEK5gGoQ())) {
                            aVar.invoke();
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion.m4166getDirectionDownEK5gGoQ())) {
                            aVar2.invoke();
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion.m4169getDirectionLeftEK5gGoQ())) {
                            (z12 ? aVar4 : aVar3).invoke();
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion.m4170getDirectionRightEK5gGoQ())) {
                            (z12 ? aVar3 : aVar4).invoke();
                        } else {
                            z15 = false;
                            if (!Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion.m4179getEnterEK5gGoQ())) {
                                Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion.m4165getDirectionCenterEK5gGoQ());
                            }
                        }
                        return Boolean.valueOf(z15);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            Modifier modifierArvioDpadFocusGroup$default = ArvioDpadFocusKt.arvioDpadFocusGroup$default(KeyInputModifierKt.onPreviewKeyEvent(modifierM533paddingqDBjuR0$default, (r7.l) objRememberedValue2), null, false, 3, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = Arrangement.INSTANCE.m441spacedBy0680j_4(Dp.m5678constructorimpl(z ? 7 : 9));
            float f10 = 4;
            PaddingValues paddingValuesM525PaddingValuesa9UjIt4 = PaddingKt.m525PaddingValuesa9UjIt4(Dp.m5678constructorimpl(z ? 12 : 22), Dp.m5678constructorimpl(f10), Dp.m5678constructorimpl(z ? 12 : 22), Dp.m5678constructorimpl(f10));
            boolean z15 = (i21 == 4) | ((57344 & i14) == 16384) | ((i14 & 112) == 32) | (i20 == 256) | ((i14 & 7168) == 2048) | ((i14 & 29360128) == 8388608);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (z15 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                r7.l lVar2 = new r7.l() { // from class: com.arflix.tv.ui.screens.search.l
                    @Override // r7.l
                    public final Object invoke(Object obj) {
                        return SearchScreenKt.DiscoverFilterStrip$lambda$3$0(list, z, focusZone, i10, focusRequester, lVar, (LazyListScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(lVar2);
                objRememberedValue3 = lVar2;
            }
            LazyDslKt.LazyRow(modifierArvioDpadFocusGroup$default, lazyListStateRememberLazyListState, paddingValuesM525PaddingValuesa9UjIt4, false, horizontalOrVerticalM441spacedBy0680j_4, null, null, false, (r7.l) objRememberedValue3, composerStartRestartGroup, 0, 232);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            z11 = z12;
            modifier3 = modifier6;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup2 = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup2 != null) {
            final int i22 = 1;
            pVar = new r7.p() { // from class: com.arflix.tv.ui.screens.search.k
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    Composer composer2 = (Composer) obj;
                    switch (i22) {
                        case 0:
                            return SearchScreenKt.DiscoverFilterStrip$lambda$0(list, focusZone, i10, focusRequester, z, modifier3, z11, lVar, aVar, aVar2, aVar3, aVar4, i11, i12, i13, composer2, ((Integer) obj2).intValue());
                        default:
                            return SearchScreenKt.DiscoverFilterStrip$lambda$4(list, focusZone, i10, focusRequester, z, modifier3, z11, lVar, aVar, aVar2, aVar3, aVar4, i11, i12, i13, composer2, ((Integer) obj2).intValue());
                    }
                }
            };
            scopeUpdateScope = scopeUpdateScopeEndRestartGroup2;
            scopeUpdateScope.updateScope(pVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 DiscoverFilterStrip$lambda$0(List list, FocusZone focusZone, int i10, FocusRequester focusRequester, boolean z, Modifier modifier, boolean z5, r7.l lVar, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, int i11, int i12, int i13, Composer composer, int i14) {
        DiscoverFilterStrip(list, focusZone, i10, focusRequester, z, modifier, z5, lVar, aVar, aVar2, aVar3, aVar4, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), RecomposeScopeImplKt.updateChangedFlags(i12), i13);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 DiscoverFilterStrip$lambda$3$0(List list, boolean z, FocusZone focusZone, int i10, FocusRequester focusRequester, r7.l lVar, LazyListScope lazyListScope) {
        lazyListScope.items(list.size(), new SearchScreenKt$DiscoverFilterStrip$lambda$3$0$$inlined$itemsIndexed$default$1(new x(0), list), new SearchScreenKt$DiscoverFilterStrip$lambda$3$0$$inlined$itemsIndexed$default$2(list), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new SearchScreenKt$DiscoverFilterStrip$lambda$3$0$$inlined$itemsIndexed$default$3(list, z, focusZone, i10, focusRequester, lVar)));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object DiscoverFilterStrip$lambda$3$0$0(int i10, DiscoverQuickFilter discoverQuickFilter) {
        return discoverQuickFilter.getKey();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 DiscoverFilterStrip$lambda$4(List list, FocusZone focusZone, int i10, FocusRequester focusRequester, boolean z, Modifier modifier, boolean z5, r7.l lVar, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, int i11, int i12, int i13, Composer composer, int i14) {
        DiscoverFilterStrip(list, focusZone, i10, focusRequester, z, modifier, z5, lVar, aVar, aVar2, aVar3, aVar4, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), RecomposeScopeImplKt.updateChangedFlags(i12), i13);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:101:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0234  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0324  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x032a  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0373  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0381  */
    /* JADX WARN: Removed duplicated region for block: B:176:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0116  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void GlowChip(java.lang.String r67, boolean r68, boolean r69, androidx.compose.ui.Modifier r70, r7.a<x6.t0> r71, boolean r72, r7.a<x6.t0> r73, androidx.compose.runtime.Composer r74, int r75, int r76) {
        /*
            Method dump skipped, instruction units count: 912
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.search.SearchScreenKt.GlowChip(java.lang.String, boolean, boolean, androidx.compose.ui.Modifier, r7.a, boolean, r7.a, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final boolean GlowChip$lambda$2(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void GlowChip$lambda$3(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 GlowChip$lambda$4$0(r7.a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 GlowChip$lambda$5$0(r7.a aVar, MutableState mutableState, FocusState focusState) {
        GlowChip$lambda$3(mutableState, focusState.isFocused());
        if (focusState.isFocused()) {
            aVar.invoke();
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 GlowChip$lambda$7(String str, boolean z, boolean z5, Modifier modifier, r7.a aVar, boolean z10, r7.a aVar2, int i10, int i11, Composer composer, int i12) {
        GlowChip(str, z, z5, modifier, aVar, z10, aVar2, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }

    private static final void RowsLayer(final List<Category> list, final Map<String, String> map, final int i10, final int i11, final long j10, final long j11, final boolean z, final boolean z5, final r7.l<? super MediaItem, t0> lVar, Composer composer, final int i12) {
        int i13;
        boolean z10;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1156613373);
        if ((i12 & 6) == 0) {
            i13 = (composerStartRestartGroup.changed(list) ? 4 : 2) | i12;
        } else {
            i13 = i12;
        }
        if ((i12 & 48) == 0) {
            i13 |= composerStartRestartGroup.changed(map) ? 32 : 16;
        }
        if ((i12 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i13 |= composerStartRestartGroup.changed(i10) ? 256 : 128;
        }
        if ((i12 & 3072) == 0) {
            i13 |= composerStartRestartGroup.changed(i11) ? 2048 : 1024;
        }
        if ((i12 & 24576) == 0) {
            i13 |= composerStartRestartGroup.changed(j10) ? 16384 : 8192;
        }
        if ((196608 & i12) == 0) {
            i13 |= composerStartRestartGroup.changed(j11) ? 131072 : 65536;
        }
        if ((1572864 & i12) == 0) {
            z10 = z;
            i13 |= composerStartRestartGroup.changed(z10) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        } else {
            z10 = z;
        }
        if ((12582912 & i12) == 0) {
            i13 |= composerStartRestartGroup.changed(z5) ? 8388608 : 4194304;
        }
        if ((100663296 & i12) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(lVar) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        }
        if ((i13 & 38347923) == 38347922 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1156613373, i13, -1, "com.arflix.tv.ui.screens.search.RowsLayer (SearchScreen.kt:910)");
            }
            Configuration configuration = (Configuration) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalConfiguration());
            final Density density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
            final int i14 = configuration.screenHeightDp;
            final float fM5678constructorimpl = Dp.m5678constructorimpl(z5 ? 14 : 22);
            LazyListState lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(-1);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableIntState mutableIntState = (MutableIntState) objRememberedValue;
            int size = list.size() - 1;
            if (size < 0) {
                size = 0;
            }
            int iN = qb.d.n(i10, 0, size);
            Integer numValueOf = Integer.valueOf(iN);
            Long lValueOf = Long.valueOf(j10);
            LazyListState lazyListState = lazyListStateRememberLazyListState;
            boolean zChanged = composerStartRestartGroup.changed(lazyListStateRememberLazyListState) | composerStartRestartGroup.changed(iN) | ((i13 & 57344) == 16384) | ((458752 & i13) == 131072);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue2 == companion.getEmpty()) {
                SearchScreenKt$RowsLayer$1$1 searchScreenKt$RowsLayer$1$1 = new SearchScreenKt$RowsLayer$1$1(lazyListState, iN, j10, j11, mutableIntState, null);
                lazyListState = lazyListState;
                composerStartRestartGroup.updateRememberedValue(searchScreenKt$RowsLayer$1$1);
                objRememberedValue2 = searchScreenKt$RowsLayer$1$1;
            }
            EffectsKt.LaunchedEffect(numValueOf, lValueOf, (r7.p) objRememberedValue2, composerStartRestartGroup, (i13 >> 9) & 112);
            final boolean z11 = z10;
            final LazyListState lazyListState2 = lazyListState;
            composer2 = composerStartRestartGroup;
            BoxWithConstraintsKt.BoxWithConstraints(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, false, ComposableLambdaKt.rememberComposableLambda(-572967187, true, new r7.q() { // from class: com.arflix.tv.ui.screens.search.q
                @Override // r7.q
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    int iIntValue = ((Integer) obj3).intValue();
                    return SearchScreenKt.RowsLayer$lambda$4(fM5678constructorimpl, lazyListState2, list, z11, i10, z5, i14, i11, j10, density, j11, map, lVar, (BoxWithConstraintsScope) obj, (Composer) obj2, iIntValue);
                }
            }, composer2, 54), composer2, 3078, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new r7.p() { // from class: com.arflix.tv.ui.screens.search.r
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    return SearchScreenKt.RowsLayer$lambda$5(list, map, i10, i11, j10, j11, z, z5, lVar, i12, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 RowsLayer$lambda$4(final float f10, LazyListState lazyListState, final List list, final boolean z, final int i10, final boolean z5, final int i11, final int i12, final long j10, final Density density, final long j11, final Map map, final r7.l lVar, BoxWithConstraintsScope boxWithConstraintsScope, Composer composer, int i13) {
        BoxWithConstraintsScope boxWithConstraintsScope2;
        int i14;
        if ((i13 & 6) == 0) {
            boxWithConstraintsScope2 = boxWithConstraintsScope;
            i14 = i13 | (composer.changed(boxWithConstraintsScope2) ? 4 : 2);
        } else {
            boxWithConstraintsScope2 = boxWithConstraintsScope;
            i14 = i13;
        }
        if ((i14 & 19) == 18 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-572967187, i14, -1, "com.arflix.tv.ui.screens.search.RowsLayer.<anonymous> (SearchScreen.kt:946)");
            }
            PaddingValues paddingValuesM526PaddingValuesa9UjIt4$default = PaddingKt.m526PaddingValuesa9UjIt4$default(0.0f, Dp.m5678constructorimpl(f10 / 2), 0.0f, Dp.m5678constructorimpl(boxWithConstraintsScope2.mo466getMaxHeightD9Ej5fM() * 0.6f), 5, null);
            Modifier modifierArvioDpadFocusGroup$default = ArvioDpadFocusKt.arvioDpadFocusGroup$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, false, 3, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = Arrangement.INSTANCE.m441spacedBy0680j_4(Dp.m5678constructorimpl(0));
            boolean zChanged = composer.changed(list) | composer.changed(z) | composer.changed(i10) | composer.changed(z5) | composer.changed(i11) | composer.changed(f10) | composer.changed(i12) | composer.changed(j10) | composer.changed(density) | composer.changed(j11) | composer.changed(map) | composer.changed(lVar);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new r7.l() { // from class: com.arflix.tv.ui.screens.search.p
                    @Override // r7.l
                    public final Object invoke(Object obj) {
                        return SearchScreenKt.RowsLayer$lambda$4$0$0(list, z, i10, z5, i11, f10, i12, j10, density, j11, map, lVar, (LazyListScope) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            LazyDslKt.LazyColumn(modifierArvioDpadFocusGroup$default, lazyListState, paddingValuesM526PaddingValuesa9UjIt4$default, false, horizontalOrVerticalM441spacedBy0680j_4, null, null, false, (r7.l) objRememberedValue, composer, 24576, 232);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 RowsLayer$lambda$4$0$0(final List list, final boolean z, final int i10, final boolean z5, final int i11, final float f10, final int i12, final long j10, final Density density, final long j11, final Map map, final r7.l lVar, LazyListScope lazyListScope) {
        LazyListScope.CC.k(lazyListScope, list.size(), new n(list, 0), null, ComposableLambdaKt.composableLambdaInstance(-60869264, true, new r7.r() { // from class: com.arflix.tv.ui.screens.search.o
            @Override // r7.r
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                int iIntValue = ((Integer) obj4).intValue();
                return SearchScreenKt.RowsLayer$lambda$4$0$0$1(list, z, i10, z5, i11, f10, i12, j10, density, j11, map, lVar, (LazyItemScope) obj, ((Integer) obj2).intValue(), (Composer) obj3, iIntValue);
            }
        }), 4, null);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object RowsLayer$lambda$4$0$0$0(List list, int i10) {
        return ((Category) list.get(i10)).getId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 RowsLayer$lambda$4$0$0$1(List list, boolean z, int i10, final boolean z5, int i11, float f10, final int i12, long j10, Density density, long j11, final Map map, final r7.l lVar, LazyItemScope lazyItemScope, int i13, Composer composer, int i14) {
        int i15;
        float fM5678constructorimpl;
        float fM5678constructorimpl2;
        Object searchScreenKt$RowsLayer$2$1$1$2$2$1$2$1;
        int i16;
        boolean z10;
        boolean z11;
        Category category;
        LazyListState lazyListState;
        float f11;
        Composer composer2;
        if ((i14 & 48) == 0) {
            i15 = i14 | (composer.changed(i13) ? 32 : 16);
        } else {
            i15 = i14;
        }
        if ((i15 & 145) == 144 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-60869264, i15, -1, "com.arflix.tv.ui.screens.search.RowsLayer.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SearchScreen.kt:953)");
            }
            Category category2 = (Category) list.get(i13);
            boolean z12 = z && i13 == i10;
            boolean zChanged = composer.changed(category2.getId());
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = "search:" + category2.getId();
                composer.updateRememberedValue(objRememberedValue);
            }
            final boolean zIsPortrait = ModelsKt.isPortrait(category2, CardLayoutModeKt.rememberCatalogueRowLayoutMode((String) objRememberedValue, composer, 0) == CardLayoutMode.POSTER);
            if (z5) {
                fM5678constructorimpl = Dp.m5678constructorimpl(zIsPortrait ? MediaError.DetailedErrorCode.SOURCE_BUFFER_FAILURE : 170);
            } else {
                fM5678constructorimpl = Dp.m5678constructorimpl(zIsPortrait ? 105 : 210);
            }
            float f12 = fM5678constructorimpl;
            if (z5) {
                fM5678constructorimpl2 = Dp.m5678constructorimpl(zIsPortrait ? 260 : 190);
            } else if (zIsPortrait) {
                fM5678constructorimpl2 = Dp.m5678constructorimpl(i11 <= 640 ? 271 : 309);
            } else {
                fM5678constructorimpl2 = Dp.m5678constructorimpl(i11 <= 640 ? 210 : 274);
            }
            float fM5678constructorimpl3 = Dp.m5678constructorimpl(fM5678constructorimpl2 + f10);
            State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState((!z || i13 <= i10) ? 1.0f : 0.3f, AnimationSpecKt.tween$default(AnimationConstants.DURATION_IMAGE_CROSSFADE, 0, null, 6, null), 0.0f, "rowAlpha", null, composer, 3120, 20);
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierM564height3ABfNKs = SizeKt.m564height3ABfNKs(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), fM5678constructorimpl3);
            boolean zChanged2 = composer.changed(stateAnimateFloatAsState);
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new com.arflix.tv.ui.components.c(stateAnimateFloatAsState, 7);
                composer.updateRememberedValue(objRememberedValue2);
            }
            Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(modifierM564height3ABfNKs, (r7.l) objRememberedValue2);
            composer.startReplaceableGroup(733328855);
            Alignment.Companion companion2 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyD = a0.c.d(companion2, false, composer, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierGraphicsLayer);
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
            r7.p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            composer.startReplaceableGroup(-483455358);
            Arrangement arrangement = Arrangement.INSTANCE;
            MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(companion2, arrangement.getTop(), composer, 0, -1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(companion);
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
            r7.p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyK, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composer, qVarModifierMaterializerOf2, composer, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            float f13 = 4;
            Modifier modifierM533paddingqDBjuR0$default = PaddingKt.m533paddingqDBjuR0$default(companion, f10, Dp.m5678constructorimpl(f13), 0.0f, Dp.m5678constructorimpl(f13), 4, null);
            float f14 = 8;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(androidx.compose.foundation.c.g(f14, arrangement, composer, 693286680), companion2.getCenterVertically(), composer, 54);
            composer.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor3 = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierM533paddingqDBjuR0$default);
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor3);
            } else {
                composer.useNode();
            }
            Composer composerM2991constructorimpl3 = Updater.m2991constructorimpl(composer);
            r7.p pVarU3 = a0.c.u(companion3, composerM2991constructorimpl3, measurePolicyRowMeasurePolicy, composerM2991constructorimpl3, currentCompositionLocalMap3);
            if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
            }
            a0.c.y(composer, qVarModifierMaterializerOf3, composer, 0, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            boolean z13 = z12;
            TextKt.m6020Text4IGK_g(category2.getTitle(), null, Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), z12 ? 0.9f : 0.5f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(ArvioSkin.INSTANCE.getTypography(composer, 6).getSectionTitle(), 0L, TextUnitKt.getSp(15), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composer, 0, 0, 65530);
            androidx.compose.foundation.c.y(composer);
            LazyListState lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composer, 0, 3);
            boolean zChanged3 = composer.changed(category2.getId());
            Object objRememberedValue3 = composer.rememberedValue();
            if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = SnapshotIntStateKt.mutableIntStateOf(-1);
                composer.updateRememberedValue(objRememberedValue3);
            }
            MutableIntState mutableIntState = (MutableIntState) objRememberedValue3;
            boolean zChanged4 = composer.changed(category2.getId());
            Object objRememberedValue4 = composer.rememberedValue();
            if (zChanged4 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = SnapshotIntStateKt.mutableIntStateOf(Integer.MIN_VALUE);
                composer.updateRememberedValue(objRememberedValue4);
            }
            MutableIntState mutableIntState2 = (MutableIntState) objRememberedValue4;
            Boolean boolValueOf = Boolean.valueOf(z13);
            Integer numValueOf = Integer.valueOf(i12);
            Long lValueOf = Long.valueOf(j10);
            boolean zChanged5 = composer.changed(z13) | composer.changed(i12) | composer.changed(category2) | composer.changed(lazyListStateRememberLazyListState) | composer.changed(density) | composer.changed(f12) | composer.changed(mutableIntState) | composer.changed(mutableIntState2) | composer.changed(j10) | composer.changed(j11);
            Object objRememberedValue5 = composer.rememberedValue();
            if (zChanged5 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                i16 = 3;
                z10 = false;
                z11 = z13;
                category = category2;
                searchScreenKt$RowsLayer$2$1$1$2$2$1$2$1 = new SearchScreenKt$RowsLayer$2$1$1$2$2$1$2$1(z11, i12, category, lazyListStateRememberLazyListState, density, j10, j11, f12, mutableIntState, mutableIntState2, null);
                lazyListState = lazyListStateRememberLazyListState;
                f11 = f12;
                composer.updateRememberedValue(searchScreenKt$RowsLayer$2$1$1$2$2$1$2$1);
            } else {
                lazyListState = lazyListStateRememberLazyListState;
                category = category2;
                f11 = f12;
                z10 = false;
                searchScreenKt$RowsLayer$2$1$1$2$2$1$2$1 = objRememberedValue5;
                z11 = z13;
                i16 = 3;
            }
            LazyListState lazyListState2 = lazyListState;
            EffectsKt.LaunchedEffect(boolValueOf, numValueOf, lValueOf, (r7.p) searchScreenKt$RowsLayer$2$1$1$2$2$1$2$1, composer, 0);
            Modifier modifierArvioDpadFocusGroup$default = ArvioDpadFocusKt.arvioDpadFocusGroup$default(companion, null, z10, i16, null);
            PaddingValues paddingValuesM525PaddingValuesa9UjIt4 = PaddingKt.m525PaddingValuesa9UjIt4(f10, Dp.m5678constructorimpl(f14), Dp.m5678constructorimpl(Dp.m5678constructorimpl(56) + f11), Dp.m5678constructorimpl(Dp.m5678constructorimpl(12) + f10));
            Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = arrangement.m441spacedBy0680j_4(Dp.m5678constructorimpl(18));
            boolean zChanged6 = composer.changed(category) | composer.changed(z11) | composer.changed(i12) | composer.changed(f11) | composer.changed(zIsPortrait) | composer.changed(map) | composer.changed(lVar) | composer.changed(z5);
            Object objRememberedValue6 = composer.rememberedValue();
            if (zChanged6 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                final boolean z14 = z11;
                final Category category3 = category;
                final float f15 = f11;
                composer2 = composer;
                r7.l lVar2 = new r7.l() { // from class: com.arflix.tv.ui.screens.search.j
                    @Override // r7.l
                    public final Object invoke(Object obj) {
                        return SearchScreenKt.RowsLayer$lambda$4$0$0$1$3$0$8$0(category3, z14, i12, f15, zIsPortrait, map, lVar, z5, (LazyListScope) obj);
                    }
                };
                composer2.updateRememberedValue(lVar2);
                objRememberedValue6 = lVar2;
            } else {
                composer2 = composer;
            }
            LazyDslKt.LazyRow(modifierArvioDpadFocusGroup$default, lazyListState2, paddingValuesM525PaddingValuesa9UjIt4, false, horizontalOrVerticalM441spacedBy0680j_4, null, null, false, (r7.l) objRememberedValue6, composer2, 24576, 232);
            if (androidx.compose.foundation.c.A(composer)) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    private static final float RowsLayer$lambda$4$0$0$1$1(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 RowsLayer$lambda$4$0$0$1$2$0(State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setAlpha(RowsLayer$lambda$4$0$0$1$1(state));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 RowsLayer$lambda$4$0$0$1$3$0$8$0(Category category, boolean z, int i10, float f10, boolean z5, Map map, r7.l lVar, boolean z10, LazyListScope lazyListScope) {
        List<MediaItem> items = category.getItems();
        lazyListScope.items(items.size(), new SearchScreenKt$RowsLayer$lambda$4$0$0$1$3$0$8$0$$inlined$itemsIndexed$default$1(new x(3), items), new SearchScreenKt$RowsLayer$lambda$4$0$0$1$3$0$8$0$$inlined$itemsIndexed$default$2(items), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new SearchScreenKt$RowsLayer$lambda$4$0$0$1$3$0$8$0$$inlined$itemsIndexed$default$3(items, z, i10, f10, z5, map, lVar, z10)));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object RowsLayer$lambda$4$0$0$1$3$0$8$0$0(int i10, MediaItem mediaItem) {
        return mediaItem.getMediaType() + "_" + mediaItem.getId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 RowsLayer$lambda$5(List list, Map map, int i10, int i11, long j10, long j11, boolean z, boolean z5, r7.l lVar, int i12, Composer composer, int i13) {
        RowsLayer(list, map, i10, i11, j10, j11, z, z5, lVar, composer, RecomposeScopeImplKt.updateChangedFlags(i12 | 1));
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: SearchInputBar-yZaabhs, reason: not valid java name */
    private static final void m6284SearchInputBaryZaabhs(final String str, final float f10, final boolean z, final boolean z5, final boolean z10, final FocusRequester focusRequester, final FocusRequester focusRequester2, final r7.l<? super String, t0> lVar, final r7.a<t0> aVar, final r7.a<t0> aVar2, final r7.a<t0> aVar3, final r7.a<t0> aVar4, final r7.a<t0> aVar5, final r7.a<t0> aVar6, Composer composer, final int i10, final int i11) {
        int i12;
        boolean z11;
        int i13;
        int i14;
        float fM5678constructorimpl;
        boolean z12;
        float fM5678constructorimpl2;
        Composer composer2;
        r7.p<? super Composer, ? super Integer, t0> pVar;
        ScopeUpdateScope scopeUpdateScope;
        Composer composerStartRestartGroup = composer.startRestartGroup(27131827);
        if ((i10 & 6) == 0) {
            i12 = i10 | (composerStartRestartGroup.changed(str) ? 4 : 2);
        } else {
            i12 = i10;
        }
        if ((i10 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(f10) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changed(z) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i12 |= composerStartRestartGroup.changed(z5) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            z11 = z10;
            i12 |= composerStartRestartGroup.changed(z11) ? 16384 : 8192;
        } else {
            z11 = z10;
        }
        if ((i10 & 196608) == 0) {
            i12 |= composerStartRestartGroup.changed(focusRequester) ? 131072 : 65536;
        }
        if ((i10 & 1572864) == 0) {
            i12 |= composerStartRestartGroup.changed(focusRequester2) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((i10 & 12582912) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(lVar) ? 8388608 : 4194304;
        }
        if ((i10 & 100663296) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(aVar) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        }
        if ((i10 & 805306368) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(aVar2) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        int i15 = i12;
        if ((i11 & 6) == 0) {
            i13 = i11 | (composerStartRestartGroup.changedInstance(aVar3) ? 4 : 2);
        } else {
            i13 = i11;
        }
        if ((i11 & 48) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(aVar4) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(aVar5) ? 256 : 128;
        }
        if ((i11 & 3072) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(aVar6) ? 2048 : 1024;
        }
        int i16 = i13;
        if ((i15 & 306783379) == 306783378 && (i16 & 1171) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(27131827, i15, i16, "com.arflix.tv.ui.screens.search.SearchInputBar (SearchScreen.kt:677)");
            }
            if (z) {
                composerStartRestartGroup.startReplaceGroup(830902666);
                TextStyle textStyleM5205copyp1EtxEg$default = TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getBody(), ColorKt.getTextPrimary(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null);
                KeyboardOptions keyboardOptions = new KeyboardOptions(0, false, 0, ImeAction.INSTANCE.m5373getSearcheUduSuo(), null, 23, null);
                boolean z13 = (i15 & 234881024) == 67108864;
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z13 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new m(1, aVar);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                KeyboardActions keyboardActions = new KeyboardActions(null, null, null, null, (r7.l) objRememberedValue, null, 47, null);
                TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                long textPrimary = ColorKt.getTextPrimary();
                long textPrimary2 = ColorKt.getTextPrimary();
                Color.Companion companion = Color.INSTANCE;
                TextFieldColors textFieldColorsM2123colors0hiis_0 = textFieldDefaults.m2123colors0hiis_0(textPrimary, textPrimary2, 0L, 0L, ColorKt.getBackgroundCard(), ColorKt.getBackgroundCard(), 0L, 0L, companion.m3509getWhite0d7_KjU(), 0L, null, companion.m3509getWhite0d7_KjU(), Color.m3471copywmQWz5c$default(companion.m3509getWhite0d7_KjU(), 0.18f, 0.0f, 0.0f, 0.0f, 14, null), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 100884534, 432, 0, 0, 3072, 2147477196, 4095);
                RoundedCornerShape roundedCornerShapeM798RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(10));
                Modifier modifierFocusRequester = FocusRequesterModifierKt.focusRequester(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), focusRequester);
                boolean z14 = ((i15 & 1879048192) == 536870912) | ((i16 & 14) == 4);
                Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z14 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    final int i17 = 0;
                    objRememberedValue2 = new r7.l() { // from class: com.arflix.tv.ui.screens.search.s
                        @Override // r7.l
                        public final Object invoke(Object obj) {
                            switch (i17) {
                                case 0:
                                    return SearchScreenKt.SearchInputBar_yZaabhs$lambda$1$0(aVar2, aVar3, (FocusState) obj);
                                default:
                                    return SearchScreenKt.SearchInputBar_yZaabhs$lambda$5$0(aVar2, aVar3, ((Boolean) obj).booleanValue());
                            }
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                OutlinedTextFieldKt.OutlinedTextField(str, lVar, FocusChangedModifierKt.onFocusChanged(modifierFocusRequester, (r7.l) objRememberedValue2), false, false, textStyleM5205copyp1EtxEg$default, (r7.p<? super Composer, ? super Integer, t0>) null, (r7.p<? super Composer, ? super Integer, t0>) ComposableSingletons$SearchScreenKt.INSTANCE.getLambda$1196201055$app_playRelease(), (r7.p<? super Composer, ? super Integer, t0>) ComposableLambdaKt.rememberComposableLambda(1082590560, true, new t(z5, 0), composerStartRestartGroup, 54), (r7.p<? super Composer, ? super Integer, t0>) null, (r7.p<? super Composer, ? super Integer, t0>) null, (r7.p<? super Composer, ? super Integer, t0>) null, (r7.p<? super Composer, ? super Integer, t0>) null, false, (VisualTransformation) null, keyboardOptions, keyboardActions, true, 0, 0, (MutableInteractionSource) null, (Shape) roundedCornerShapeM798RoundedCornerShape0680j_4, textFieldColorsM2123colors0hiis_0, composerStartRestartGroup, (i15 & 14) | 113246208 | ((i15 >> 18) & 112), 12779520, 0, 1867352);
                composerStartRestartGroup.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final int i18 = 0;
                    final boolean z15 = z11;
                    pVar = new r7.p() { // from class: com.arflix.tv.ui.screens.search.u
                        @Override // r7.p
                        public final Object invoke(Object obj, Object obj2) {
                            Composer composer3 = (Composer) obj;
                            switch (i18) {
                                case 0:
                                    return SearchScreenKt.SearchInputBar_yZaabhs$lambda$3(str, f10, z, z5, z15, focusRequester, focusRequester2, lVar, aVar, aVar2, aVar3, aVar4, aVar5, aVar6, i10, i11, composer3, ((Integer) obj2).intValue());
                                default:
                                    return SearchScreenKt.SearchInputBar_yZaabhs$lambda$7(str, f10, z, z5, z15, focusRequester, focusRequester2, lVar, aVar, aVar2, aVar3, aVar4, aVar5, aVar6, i10, i11, composer3, ((Integer) obj2).intValue());
                            }
                        }
                    };
                    scopeUpdateScope = scopeUpdateScopeEndRestartGroup;
                    scopeUpdateScope.updateScope(pVar);
                }
                return;
            }
            composerStartRestartGroup.startReplaceGroup(832229807);
            composerStartRestartGroup.endReplaceGroup();
            Shape shapeM6502rememberArvioCardShapekHDZbjc = ArvioFocusKt.m6502rememberArvioCardShapekHDZbjc(Dp.m5678constructorimpl(10), composerStartRestartGroup, 6, 0);
            Modifier modifierM564height3ABfNKs = SizeKt.m564height3ABfNKs(SizeKt.m583width3ABfNKs(Modifier.INSTANCE, f10), Dp.m5678constructorimpl(54));
            boolean z16 = ((i16 & 896) == 256) | ((i16 & 7168) == 2048) | ((i16 & 112) == 32);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (z16 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new r7.l<KeyEvent, Boolean>() { // from class: com.arflix.tv.ui.screens.search.SearchScreenKt$SearchInputBar$5$1
                    @Override // r7.l
                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        return m6290invokeZmokQxo(((KeyEvent) obj).m4390unboximpl());
                    }

                    /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                    public final Boolean m6290invokeZmokQxo(android.view.KeyEvent keyEvent) {
                        if (!KeyEventType.m4394equalsimpl0(KeyEvent_androidKt.m4402getTypeZmokQxo(keyEvent), KeyEventType.INSTANCE.m4398getKeyDownCS__XNY())) {
                            return Boolean.FALSE;
                        }
                        long jM4401getKeyZmokQxo = KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent);
                        Key.Companion companion2 = Key.INSTANCE;
                        boolean z17 = true;
                        if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion2.m4171getDirectionUpEK5gGoQ())) {
                            aVar5.invoke();
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion2.m4166getDirectionDownEK5gGoQ())) {
                            aVar6.invoke();
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion2.m4179getEnterEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion2.m4165getDirectionCenterEK5gGoQ())) {
                            aVar4.invoke();
                        } else {
                            z17 = false;
                        }
                        return Boolean.valueOf(z17);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            Modifier modifierFocusRequester2 = FocusRequesterModifierKt.focusRequester(KeyInputModifierKt.onPreviewKeyEvent(modifierM564height3ABfNKs, (r7.l) objRememberedValue3), focusRequester);
            Color.Companion companion2 = Color.INSTANCE;
            long jM3471copywmQWz5c$default = Color.m3471copywmQWz5c$default(companion2.m3509getWhite0d7_KjU(), z5 ? 0.075f : 0.045f, 0.0f, 0.0f, 0.0f, 14, null);
            long jM3509getWhite0d7_KjU = companion2.m3509getWhite0d7_KjU();
            if (z5) {
                fM5678constructorimpl = Dp.m5678constructorimpl(3);
                i14 = 2;
            } else {
                i14 = 2;
                fM5678constructorimpl = Dp.m5678constructorimpl(2);
            }
            if (z5) {
                fM5678constructorimpl2 = Dp.m5678constructorimpl(i14);
                z12 = false;
            } else {
                z12 = false;
                fM5678constructorimpl2 = Dp.m5678constructorimpl(0);
            }
            float f11 = fM5678constructorimpl2;
            boolean z17 = (i15 & 1879048192) == 536870912 ? true : z12;
            if ((i16 & 14) == 4) {
                z12 = true;
            }
            boolean z18 = z17 | z12;
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (z18 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                final int i19 = 1;
                objRememberedValue4 = new r7.l() { // from class: com.arflix.tv.ui.screens.search.s
                    @Override // r7.l
                    public final Object invoke(Object obj) {
                        switch (i19) {
                            case 0:
                                return SearchScreenKt.SearchInputBar_yZaabhs$lambda$1$0(aVar2, aVar3, (FocusState) obj);
                            default:
                                return SearchScreenKt.SearchInputBar_yZaabhs$lambda$5$0(aVar2, aVar3, ((Boolean) obj).booleanValue());
                        }
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            ArvioFocusKt.m6499ArvioFocusableSurfaceaewEPWQ(modifierFocusRequester2, shapeM6502rememberArvioCardShapekHDZbjc, jM3471copywmQWz5c$default, 1.0f, 0.985f, fM5678constructorimpl, f11, 0.22f, jM3509getWhite0d7_KjU, 0.0f, false, 0L, 0L, false, false, false, false, false, z5, aVar4, null, (r7.l) objRememberedValue4, ComposableLambdaKt.rememberComposableLambda(493864185, true, new r7.r() { // from class: com.arflix.tv.ui.screens.search.v
                @Override // r7.r
                public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                    int iIntValue = ((Integer) obj4).intValue();
                    return SearchScreenKt.SearchInputBar_yZaabhs$lambda$6(z5, z10, aVar, focusRequester2, str, lVar, (BoxScope) obj, ((Boolean) obj2).booleanValue(), (Composer) obj3, iIntValue);
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, 113273856, ((i15 << 15) & 234881024) | 12582912 | ((i16 << 24) & 1879048192), RendererCapabilities.DECODER_SUPPORT_MASK, 1179136);
            composer2 = composerStartRestartGroup;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup2 = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup2 != null) {
            final int i20 = 1;
            pVar = new r7.p() { // from class: com.arflix.tv.ui.screens.search.u
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    Composer composer3 = (Composer) obj;
                    switch (i20) {
                        case 0:
                            return SearchScreenKt.SearchInputBar_yZaabhs$lambda$3(str, f10, z, z5, z10, focusRequester, focusRequester2, lVar, aVar, aVar2, aVar3, aVar4, aVar5, aVar6, i10, i11, composer3, ((Integer) obj2).intValue());
                        default:
                            return SearchScreenKt.SearchInputBar_yZaabhs$lambda$7(str, f10, z, z5, z10, focusRequester, focusRequester2, lVar, aVar, aVar2, aVar3, aVar4, aVar5, aVar6, i10, i11, composer3, ((Integer) obj2).intValue());
                    }
                }
            };
            scopeUpdateScope = scopeUpdateScopeEndRestartGroup2;
            scopeUpdateScope.updateScope(pVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchInputBar_yZaabhs$lambda$0$0(r7.a aVar, KeyboardActionScope keyboardActionScope) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchInputBar_yZaabhs$lambda$1$0(r7.a aVar, r7.a aVar2, FocusState focusState) {
        if (focusState.isFocused()) {
            aVar.invoke();
        } else {
            aVar2.invoke();
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchInputBar_yZaabhs$lambda$2(boolean z, Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1082590560, i10, -1, "com.arflix.tv.ui.screens.search.SearchInputBar.<anonymous> (SearchScreen.kt:683)");
            }
            IconKt.m1610Iconww6aTOc(SearchKt.getSearch(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m578size3ABfNKs(Modifier.INSTANCE, Dp.m5678constructorimpl(22)), z ? ColorKt.getPink() : ColorKt.getTextSecondary(), composer, 432, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchInputBar_yZaabhs$lambda$3(String str, float f10, boolean z, boolean z5, boolean z10, FocusRequester focusRequester, FocusRequester focusRequester2, r7.l lVar, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, r7.a aVar5, r7.a aVar6, int i10, int i11, Composer composer, int i12) {
        m6284SearchInputBaryZaabhs(str, f10, z, z5, z10, focusRequester, focusRequester2, lVar, aVar, aVar2, aVar3, aVar4, aVar5, aVar6, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), RecomposeScopeImplKt.updateChangedFlags(i11));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchInputBar_yZaabhs$lambda$5$0(r7.a aVar, r7.a aVar2, boolean z) {
        if (z) {
            aVar.invoke();
        } else {
            aVar2.invoke();
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchInputBar_yZaabhs$lambda$6(boolean z, boolean z5, r7.a aVar, FocusRequester focusRequester, String str, r7.l lVar, BoxScope boxScope, boolean z10, Composer composer, int i10) {
        if ((i10 & TsExtractor.TS_STREAM_TYPE_AC3) == 128 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(493864185, i10, -1, "com.arflix.tv.ui.screens.search.SearchInputBar.<anonymous> (SearchScreen.kt:736)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            kotlin.jvm.internal.h hVar = null;
            Modifier modifierM531paddingVpY3zN4$default = PaddingKt.m531paddingVpY3zN4$default(SizeKt.fillMaxSize$default(companion, 0.0f, 1, null), Dp.m5678constructorimpl(18), 0.0f, 2, null);
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            composer.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyG = androidx.compose.material3.d.g(Arrangement.INSTANCE, centerVertically, composer, 48, -1323940314);
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
            r7.p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyG, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            IconKt.m1610Iconww6aTOc(SearchKt.getSearch(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(22)), z ? Color.INSTANCE.m3509getWhite0d7_KjU() : ColorKt.getTextSecondary(), composer, 432, 0);
            androidx.compose.material3.d.w(12, companion, composer, 6);
            boolean z11 = !z5;
            TextStyle textStyleM5205copyp1EtxEg$default = TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getBody(), ColorKt.getTextPrimary(), TextUnitKt.getSp(17), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777212, null);
            SolidColor solidColor = new SolidColor(Color.INSTANCE.m3509getWhite0d7_KjU(), hVar);
            KeyboardOptions keyboardOptions = new KeyboardOptions(0, false, 0, ImeAction.INSTANCE.m5373getSearcheUduSuo(), null, 23, null);
            boolean zChanged = composer.changed(aVar);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new m(0, aVar);
                composer.updateRememberedValue(objRememberedValue);
            }
            BasicTextFieldKt.BasicTextField(str, (r7.l<? super String, t0>) lVar, FocusRequesterModifierKt.focusRequester(androidx.compose.foundation.layout.e.a(rowScopeInstance, companion, 1.0f, false, 2, null), focusRequester), false, z11, textStyleM5205copyp1EtxEg$default, keyboardOptions, new KeyboardActions(null, null, null, null, (r7.l) objRememberedValue, null, 47, null), true, 0, 0, (VisualTransformation) null, (r7.l<? super TextLayoutResult, t0>) null, (MutableInteractionSource) null, (Brush) solidColor, (r7.q<? super r7.p<? super Composer, ? super Integer, t0>, ? super Composer, ? super Integer, t0>) ComposableLambdaKt.rememberComposableLambda(230769272, true, new x1(str, 3), composer, 54), composer, 102236160, 221184, 15880);
            if (androidx.compose.foundation.c.D(composer)) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchInputBar_yZaabhs$lambda$6$0$0$0(r7.a aVar, KeyboardActionScope keyboardActionScope) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchInputBar_yZaabhs$lambda$6$0$1(String str, r7.p pVar, Composer composer, int i10) {
        int i11;
        int i12;
        Composer composer2 = composer;
        if ((i10 & 6) == 0) {
            i11 = i10 | (composer2.changedInstance(pVar) ? 4 : 2);
        } else {
            i11 = i10;
        }
        if ((i11 & 19) == 18 && composer2.getSkipping()) {
            composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(230769272, i11, -1, "com.arflix.tv.ui.screens.search.SearchInputBar.<anonymous>.<anonymous>.<anonymous> (SearchScreen.kt:757)");
            }
            if (str.length() == 0) {
                composer2.startReplaceGroup(-1395173864);
                i12 = i11;
                TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.search, composer2, 0), null, Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.32f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getBody(), 0L, TextUnitKt.getSp(17), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
                composer2 = composer;
                composer2.endReplaceGroup();
            } else {
                i12 = i11;
                composer2.startReplaceGroup(-1394919478);
                composer2.endReplaceGroup();
            }
            if (a0.c.A(composer2, i12 & 14, pVar)) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchInputBar_yZaabhs$lambda$7(String str, float f10, boolean z, boolean z5, boolean z10, FocusRequester focusRequester, FocusRequester focusRequester2, r7.l lVar, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, r7.a aVar5, r7.a aVar6, int i10, int i11, Composer composer, int i12) {
        m6284SearchInputBaryZaabhs(str, f10, z, z5, z10, focusRequester, focusRequester2, lVar, aVar, aVar2, aVar3, aVar4, aVar5, aVar6, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), RecomposeScopeImplKt.updateChangedFlags(i11));
        return t0.f22605a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x02f1  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0304  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0323  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0328  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x039e  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x03b9  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x03e3  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0404  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x0522  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x057d  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0586  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x059a  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x05ab  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x05ad  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x05bc  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x05d1  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x05ea  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x05ff  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0609  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x0619  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x062e  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0643  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x0658  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x0666  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0674  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x068b  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x069d  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x06af  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x06d1  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x0708  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x0747  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x0785  */
    /* JADX WARN: Removed duplicated region for block: B:333:0x07c4  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x0806  */
    /* JADX WARN: Removed duplicated region for block: B:355:0x0848  */
    /* JADX WARN: Removed duplicated region for block: B:366:0x0894  */
    /* JADX WARN: Removed duplicated region for block: B:371:0x08aa  */
    /* JADX WARN: Removed duplicated region for block: B:374:0x08b7  */
    /* JADX WARN: Removed duplicated region for block: B:377:0x08c2  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:381:0x08eb  */
    /* JADX WARN: Removed duplicated region for block: B:386:0x0901  */
    /* JADX WARN: Removed duplicated region for block: B:391:0x0914  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x0951  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:404:0x0964  */
    /* JADX WARN: Removed duplicated region for block: B:412:0x09a2  */
    /* JADX WARN: Removed duplicated region for block: B:417:0x09b5  */
    /* JADX WARN: Removed duplicated region for block: B:420:0x09cd  */
    /* JADX WARN: Removed duplicated region for block: B:421:0x09dc  */
    /* JADX WARN: Removed duplicated region for block: B:430:0x0a23  */
    /* JADX WARN: Removed duplicated region for block: B:433:0x0a2e  */
    /* JADX WARN: Removed duplicated region for block: B:437:0x0a46  */
    /* JADX WARN: Removed duplicated region for block: B:438:0x0a51  */
    /* JADX WARN: Removed duplicated region for block: B:451:0x0aab  */
    /* JADX WARN: Removed duplicated region for block: B:452:0x0ab6  */
    /* JADX WARN: Removed duplicated region for block: B:465:0x0b10  */
    /* JADX WARN: Removed duplicated region for block: B:466:0x0b1b  */
    /* JADX WARN: Removed duplicated region for block: B:480:0x0b77  */
    /* JADX WARN: Removed duplicated region for block: B:481:0x0b82  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:492:0x0bdd  */
    /* JADX WARN: Removed duplicated region for block: B:493:0x0be8  */
    /* JADX WARN: Removed duplicated region for block: B:504:0x0c43  */
    /* JADX WARN: Removed duplicated region for block: B:505:0x0c4e  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:521:0x0cf4  */
    /* JADX WARN: Removed duplicated region for block: B:528:0x0d33  */
    /* JADX WARN: Removed duplicated region for block: B:531:0x0d64  */
    /* JADX WARN: Removed duplicated region for block: B:532:0x0d6d  */
    /* JADX WARN: Removed duplicated region for block: B:535:0x0d78  */
    /* JADX WARN: Removed duplicated region for block: B:536:0x0d7d  */
    /* JADX WARN: Removed duplicated region for block: B:539:0x0d8a  */
    /* JADX WARN: Removed duplicated region for block: B:546:0x0dc4  */
    /* JADX WARN: Removed duplicated region for block: B:553:0x0dff  */
    /* JADX WARN: Removed duplicated region for block: B:560:0x0e42  */
    /* JADX WARN: Removed duplicated region for block: B:563:0x0e67  */
    /* JADX WARN: Removed duplicated region for block: B:564:0x0e69  */
    /* JADX WARN: Removed duplicated region for block: B:567:0x0e80  */
    /* JADX WARN: Removed duplicated region for block: B:568:0x0e82  */
    /* JADX WARN: Removed duplicated region for block: B:575:0x0e94  */
    /* JADX WARN: Removed duplicated region for block: B:578:0x0eb5  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:614:0x0fa4  */
    /* JADX WARN: Removed duplicated region for block: B:617:0x101b  */
    /* JADX WARN: Removed duplicated region for block: B:620:0x1027  */
    /* JADX WARN: Removed duplicated region for block: B:621:0x102b  */
    /* JADX WARN: Removed duplicated region for block: B:624:0x103c  */
    /* JADX WARN: Removed duplicated region for block: B:627:0x104d  */
    /* JADX WARN: Removed duplicated region for block: B:631:0x105e  */
    /* JADX WARN: Removed duplicated region for block: B:637:0x10a1  */
    /* JADX WARN: Removed duplicated region for block: B:640:0x10b9  */
    /* JADX WARN: Removed duplicated region for block: B:642:0x10c3  */
    /* JADX WARN: Removed duplicated region for block: B:645:0x10d8  */
    /* JADX WARN: Removed duplicated region for block: B:647:0x10e4  */
    /* JADX WARN: Removed duplicated region for block: B:652:0x1120  */
    /* JADX WARN: Removed duplicated region for block: B:655:0x112c  */
    /* JADX WARN: Removed duplicated region for block: B:656:0x1130  */
    /* JADX WARN: Removed duplicated region for block: B:659:0x1141  */
    /* JADX WARN: Removed duplicated region for block: B:663:0x1156  */
    /* JADX WARN: Removed duplicated region for block: B:667:0x116c  */
    /* JADX WARN: Removed duplicated region for block: B:669:0x1175  */
    /* JADX WARN: Removed duplicated region for block: B:671:0x117a  */
    /* JADX WARN: Removed duplicated region for block: B:673:0x1182  */
    /* JADX WARN: Removed duplicated region for block: B:675:0x1186  */
    /* JADX WARN: Removed duplicated region for block: B:677:0x1190  */
    /* JADX WARN: Removed duplicated region for block: B:680:0x11d2  */
    /* JADX WARN: Removed duplicated region for block: B:683:0x11de  */
    /* JADX WARN: Removed duplicated region for block: B:684:0x11e2  */
    /* JADX WARN: Removed duplicated region for block: B:687:0x11f3  */
    /* JADX WARN: Removed duplicated region for block: B:691:0x1208  */
    /* JADX WARN: Removed duplicated region for block: B:695:0x1223  */
    /* JADX WARN: Removed duplicated region for block: B:699:0x122c  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:704:0x1241  */
    /* JADX WARN: Removed duplicated region for block: B:709:0x1265  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:712:0x127c  */
    /* JADX WARN: Removed duplicated region for block: B:715:0x1293  */
    /* JADX WARN: Removed duplicated region for block: B:718:0x12ab  */
    /* JADX WARN: Removed duplicated region for block: B:723:0x12c7  */
    /* JADX WARN: Removed duplicated region for block: B:730:0x1306  */
    /* JADX WARN: Removed duplicated region for block: B:733:0x1351  */
    /* JADX WARN: Removed duplicated region for block: B:755:0x1428  */
    /* JADX WARN: Removed duplicated region for block: B:758:0x1441  */
    /* JADX WARN: Removed duplicated region for block: B:771:0x14ea  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:861:0x18c3  */
    /* JADX WARN: Removed duplicated region for block: B:865:0x18db  */
    /* JADX WARN: Removed duplicated region for block: B:886:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x011e  */
    /* JADX WARN: Type inference failed for: r114v1, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r114v3, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r114v4, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r115v10, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r115v6, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r115v7, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r13v11, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r13v12, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r13v14, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r13v15, types: [androidx.compose.runtime.Composer, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v17, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r13v19, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r13v21, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r13v22, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r13v24, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r13v27 */
    /* JADX WARN: Type inference failed for: r13v29 */
    /* JADX WARN: Type inference failed for: r13v30, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r13v32 */
    /* JADX WARN: Type inference failed for: r13v34 */
    /* JADX WARN: Type inference failed for: r13v35 */
    /* JADX WARN: Type inference failed for: r13v36 */
    /* JADX WARN: Type inference failed for: r13v37 */
    /* JADX WARN: Type inference failed for: r13v38 */
    /* JADX WARN: Type inference failed for: r13v39 */
    /* JADX WARN: Type inference failed for: r13v40 */
    /* JADX WARN: Type inference failed for: r13v41 */
    /* JADX WARN: Type inference failed for: r13v42 */
    /* JADX WARN: Type inference failed for: r13v43 */
    /* JADX WARN: Type inference failed for: r13v44 */
    /* JADX WARN: Type inference failed for: r21v12, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r21v15, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r21v9, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r23v3, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r26v3, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v20, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v91 */
    /* JADX WARN: Type inference failed for: r52v0, types: [boolean] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SearchScreen(com.arflix.tv.ui.screens.search.SearchViewModel r110, com.arflix.tv.data.model.Profile r111, r7.p<? super com.arflix.tv.data.model.MediaType, ? super java.lang.Integer, x6.t0> r112, r7.a<x6.t0> r113, r7.a<x6.t0> r114, r7.a<x6.t0> r115, r7.a<x6.t0> r116, r7.a<x6.t0> r117, r7.a<x6.t0> r118, androidx.compose.runtime.Composer r119, int r120, int r121) {
        /*
            Method dump skipped, instruction units count: 6377
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.search.SearchScreenKt.SearchScreen(com.arflix.tv.ui.screens.search.SearchViewModel, com.arflix.tv.data.model.Profile, r7.p, r7.a, r7.a, r7.a, r7.a, r7.a, r7.a, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchScreen$lambda$0$0(MediaType mediaType, int i10) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FocusZone SearchScreen$lambda$11(MutableState<FocusZone> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean SearchScreen$lambda$17(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void SearchScreen$lambda$18(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean SearchScreen$lambda$35(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SearchScreen$lambda$36(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchScreen$lambda$50$0(SearchViewModel searchViewModel) {
        searchViewModel.setDiscoverFilters(DiscoverType.ALL, null, null);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchScreen$lambda$51$0(SearchViewModel searchViewModel) {
        searchViewModel.setDiscoverFilters(DiscoverType.MOVIES, null, null);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchScreen$lambda$52$0(SearchViewModel searchViewModel) {
        searchViewModel.setDiscoverFilters(DiscoverType.TV_SHOWS, null, null);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchScreen$lambda$53$0(SearchViewModel searchViewModel) {
        searchViewModel.setDiscoverFilters(DiscoverType.ANIME, null, null);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchScreen$lambda$54$0$0(SearchViewModel searchViewModel, Genre genre, State state) {
        searchViewModel.setDiscoverFilters(SearchScreen$lambda$7(state).getSelectedType(), genre, SearchScreen$lambda$7(state).getSelectedCountry());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchScreen$lambda$55$0$0(SearchViewModel searchViewModel, Genre genre, State state) {
        searchViewModel.setDiscoverFilters(SearchScreen$lambda$7(state).getSelectedType(), genre, SearchScreen$lambda$7(state).getSelectedCountry());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchScreen$lambda$56$0$0(SearchViewModel searchViewModel, Genre genre, State state) {
        searchViewModel.setDiscoverFilters(SearchScreen$lambda$7(state).getSelectedType(), genre, SearchScreen$lambda$7(state).getSelectedCountry());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchScreen$lambda$57$0$0(SearchViewModel searchViewModel, Genre genre, State state) {
        searchViewModel.setDiscoverFilters(SearchScreen$lambda$7(state).getSelectedType(), genre, SearchScreen$lambda$7(state).getSelectedCountry());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchScreen$lambda$58$0$0(SearchViewModel searchViewModel, Country country, State state) {
        searchViewModel.setDiscoverFilters(SearchScreen$lambda$7(state).getSelectedType(), SearchScreen$lambda$7(state).getSelectedGenre(), country);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchScreen$lambda$59$0$0(SearchViewModel searchViewModel, Country country, State state) {
        searchViewModel.setDiscoverFilters(SearchScreen$lambda$7(state).getSelectedType(), SearchScreen$lambda$7(state).getSelectedGenre(), country);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchScreen$lambda$60$0$0(SearchViewModel searchViewModel, Country country, State state) {
        searchViewModel.setDiscoverFilters(SearchScreen$lambda$7(state).getSelectedType(), SearchScreen$lambda$7(state).getSelectedGenre(), country);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchScreen$lambda$67$0(SoftwareKeyboardController softwareKeyboardController, boolean z, List list, FocusRequester focusRequester, FocusRequester focusRequester2, r7.a aVar, MutableState mutableState, MutableState mutableState2, MutableIntState mutableIntState) {
        if (SearchScreen$lambda$35(mutableState)) {
            SearchScreen$lambda$36(mutableState, false);
            if (softwareKeyboardController != null) {
                softwareKeyboardController.hide();
            }
            try {
                focusRequester2.requestFocus();
            } catch (Exception unused) {
            }
        } else {
            int i10 = WhenMappings.$EnumSwitchMapping$0[SearchScreen$lambda$11(mutableState2).ordinal()];
            if (i10 != 1) {
                if (i10 == 2) {
                    mutableState2.setValue(FocusZone.SEARCH_INPUT);
                    focusRequester2.requestFocus();
                } else if (i10 == 3) {
                    mutableState2.setValue(FocusZone.SIDEBAR);
                } else {
                    if (i10 != 4) {
                        throw new NoWhenBranchMatchedException();
                    }
                    aVar.invoke();
                }
            } else if (!z || list.isEmpty()) {
                mutableState2.setValue(FocusZone.SEARCH_INPUT);
                focusRequester2.requestFocus();
            } else {
                mutableState2.setValue(FocusZone.FILTERS);
                int intValue = mutableIntState.getIntValue();
                int size = list.size() - 1;
                if (size < 0) {
                    size = 0;
                }
                mutableIntState.setIntValue(qb.d.n(intValue, 0, size));
                focusRequester.requestFocus();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchScreen$lambda$69$0$0$0$0(SearchViewModel searchViewModel, String str) {
        searchViewModel.updateQuery(str);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchScreen$lambda$69$0$0$1$0(SearchViewModel searchViewModel, SoftwareKeyboardController softwareKeyboardController, MutableState mutableState) {
        searchViewModel.search();
        if (softwareKeyboardController != null) {
            softwareKeyboardController.hide();
        }
        SearchScreen$lambda$36(mutableState, false);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchScreen$lambda$69$0$0$2$0(MutableState mutableState, MutableState mutableState2) {
        mutableState.setValue(FocusZone.SEARCH_INPUT);
        SearchScreen$lambda$18(mutableState2, true);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchScreen$lambda$69$0$0$3$0(MutableState mutableState) {
        SearchScreen$lambda$18(mutableState, false);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchScreen$lambda$69$0$0$4$0(MutableState mutableState, MutableState mutableState2, MutableIntState mutableIntState) {
        mutableState.setValue(FocusZone.SEARCH_INPUT);
        SearchScreen$lambda$36(mutableState2, true);
        mutableIntState.setIntValue(mutableIntState.getIntValue() + 1);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchScreen$lambda$69$0$0$5$0(SoftwareKeyboardController softwareKeyboardController, MutableState mutableState, MutableState mutableState2) {
        SearchScreen$lambda$36(mutableState, false);
        if (softwareKeyboardController != null) {
            softwareKeyboardController.hide();
        }
        mutableState2.setValue(FocusZone.SIDEBAR);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchScreen$lambda$69$0$0$6$0(SoftwareKeyboardController softwareKeyboardController, boolean z, List list, RowScope rowScope, List list2, boolean z5, MutableState mutableState, MutableState mutableState2, MutableIntState mutableIntState, FocusRequester focusRequester, MutableLongState mutableLongState, MutableIntState mutableIntState2, MutableIntState mutableIntState3) {
        SearchScreen$lambda$36(mutableState, false);
        if (softwareKeyboardController != null) {
            softwareKeyboardController.hide();
        }
        if (z && !list.isEmpty()) {
            mutableState2.setValue(FocusZone.FILTERS);
            mutableIntState.setIntValue(0);
            try {
                focusRequester.requestFocus();
            } catch (Throwable unused) {
            }
        } else if (!list2.isEmpty() || z5) {
            mutableLongState.setLongValue(SystemClock.elapsedRealtime());
            mutableState2.setValue(FocusZone.RESULTS);
            mutableIntState2.setIntValue(0);
            mutableIntState3.setIntValue(0);
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchScreen$lambda$69$0$1$0(MutableState mutableState, MutableIntState mutableIntState, int i10) {
        mutableState.setValue(FocusZone.FILTERS);
        mutableIntState.setIntValue(i10);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchScreen$lambda$69$0$12$0(r7.p pVar, MediaItem mediaItem) {
        pVar.invoke(mediaItem.getMediaType(), Integer.valueOf(mediaItem.getId()));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchScreen$lambda$69$0$2$0(ColumnScope columnScope, MutableState mutableState, FocusRequester focusRequester) {
        mutableState.setValue(FocusZone.SEARCH_INPUT);
        try {
            focusRequester.requestFocus();
        } catch (Throwable unused) {
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchScreen$lambda$69$0$3$0(List list, boolean z, MutableLongState mutableLongState, MutableState mutableState, MutableIntState mutableIntState, MutableIntState mutableIntState2) {
        if (!list.isEmpty() || z) {
            mutableLongState.setLongValue(SystemClock.elapsedRealtime());
            mutableState.setValue(FocusZone.RESULTS);
            mutableIntState.setIntValue(0);
            mutableIntState2.setIntValue(0);
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchScreen$lambda$69$0$4$0(ColumnScope columnScope, MutableIntState mutableIntState, MutableState mutableState, FocusRequester focusRequester) {
        if (mutableIntState.getIntValue() > 0) {
            mutableIntState.setIntValue(mutableIntState.getIntValue() - 1);
        } else {
            mutableState.setValue(FocusZone.SEARCH_INPUT);
            try {
                focusRequester.requestFocus();
            } catch (Throwable unused) {
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchScreen$lambda$69$0$5$0(List list, MutableIntState mutableIntState) {
        if (mutableIntState.getIntValue() < list.size() - 1) {
            mutableIntState.setIntValue(mutableIntState.getIntValue() + 1);
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchScreen$lambda$69$0$8$0(r7.p pVar, MediaItem mediaItem) {
        pVar.invoke(mediaItem.getMediaType(), Integer.valueOf(mediaItem.getId()));
        return t0.f22605a;
    }

    private static final SearchUiState SearchScreen$lambda$7(State<SearchUiState> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SearchScreen$lambda$70(SearchViewModel searchViewModel, Profile profile, r7.p pVar, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, r7.a aVar5, r7.a aVar6, int i10, int i11, Composer composer, int i12) {
        SearchScreen(searchViewModel, profile, pVar, aVar, aVar2, aVar3, aVar4, aVar5, aVar6, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String buildCardSubtitle(MediaItem mediaItem, Composer composer, int i10) {
        String strStringResource;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2091526326, i10, -1, "com.arflix.tv.ui.screens.search.buildCardSubtitle (SearchScreen.kt:1116)");
        }
        int i11 = WhenMappings.$EnumSwitchMapping$1[mediaItem.getMediaType().ordinal()];
        if (i11 == 1) {
            composer.startReplaceGroup(-87891435);
            strStringResource = StringResources_androidKt.stringResource(R.string.series, composer, 0);
            composer.endReplaceGroup();
        } else {
            if (i11 != 2) {
                composer.startReplaceGroup(-87892859);
                composer.endReplaceGroup();
                throw new NoWhenBranchMatchedException();
            }
            composer.startReplaceGroup(-87889548);
            strStringResource = StringResources_androidKt.stringResource(R.string.movie, composer, 0);
            composer.endReplaceGroup();
        }
        String year = mediaItem.getYear();
        if (kotlin.text.o.h0(year)) {
            year = null;
        }
        if (year != null) {
            strStringResource = androidx.compose.foundation.c.t(strStringResource, " · ", year);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return strStringResource;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String buildCardTitle(MediaItem mediaItem) {
        return mediaItem.getTitle();
    }

    private static final List<MediaItem> interleaveSearchResults(List<MediaItem> list, List<MediaItem> list2) {
        ArrayList arrayList = new ArrayList(list2.size() + list.size());
        int iMax = Math.max(list.size(), list2.size());
        for (int i10 = 0; i10 < iMax; i10++) {
            if (i10 < list.size()) {
                arrayList.add(list.get(i10));
            }
            if (i10 < list2.size()) {
                arrayList.add(list2.get(i10));
            }
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            MediaItem mediaItem = (MediaItem) obj;
            if (hashSet.add(mediaItem.getMediaType() + "_" + mediaItem.getId())) {
                arrayList2.add(obj);
            }
        }
        return arrayList2;
    }
}
