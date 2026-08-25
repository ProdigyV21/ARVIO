package com.arflix.tv.ui.screens.collections;

import android.content.Context;
import android.widget.Toast;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.focus.FocusChangedModifierKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.fragment.app.a2;
import androidx.lifecycle.w;
import androidx.lifecycle.y;
import androidx.media3.common.C;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.tv.foundation.lazy.grid.LazyGridDslKt;
import androidx.tv.foundation.lazy.grid.b1;
import androidx.tv.foundation.lazy.grid.o0;
import androidx.tv.foundation.lazy.grid.q0;
import androidx.tv.foundation.lazy.grid.s0;
import androidx.tv.foundation.lazy.grid.v0;
import androidx.tv.material3.TextKt;
import com.arflix.tv.R;
import com.arflix.tv.data.model.CatalogConfig;
import com.arflix.tv.data.model.CollectionGroupKind;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.data.model.SportsAddonCapabilities;
import com.arflix.tv.ui.components.w0;
import com.arflix.tv.ui.focus.ArvioDpadFocusKt;
import com.arflix.tv.ui.theme.ArflixTypography;
import com.arflix.tv.ui.theme.ColorKt;
import com.arflix.tv.ui.theme.ThemeKt;
import com.google.android.gms.cast.MediaError;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.x;
import kotlin.text.u;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\r\u001aÛ\u0001\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0001\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u0018\u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00062.\u0010\f\u001a*\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u0000\u0012\u0004\u0012\u00020\t0\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\r2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\r2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\r2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\rH\u0007¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u0019\u0010\u0019\u001a\u00020\t2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017H\u0003¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u0019\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001bH\u0002¢\u0006\u0004\b\u001e\u0010\u001f\u001aS\u0010+\u001a\u00020\t2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020 2\u0006\u0010$\u001a\u00020#2\u0006\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020%2\u0006\u0010(\u001a\u00020 2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\t0)H\u0003¢\u0006\u0004\b+\u0010,\u001a5\u00101\u001a\u00020\t2\u0006\u0010-\u001a\u00020\u00002\u0006\u0010.\u001a\u00020 2\u0006\u0010/\u001a\u00020%2\f\u00100\u001a\b\u0012\u0004\u0012\u00020\t0\rH\u0003¢\u0006\u0004\b1\u00102\u001a¡\u0002\u0010J\u001a\u00020\t2\f\u00105\u001a\b\u0012\u0004\u0012\u000204032\u0006\u00106\u001a\u00020\b2\u0006\u00108\u001a\u0002072\u0006\u00109\u001a\u00020 2\u0006\u0010;\u001a\u00020:2\u0006\u0010<\u001a\u00020\b2\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020 2\u0012\u0010?\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000>2\u0006\u0010$\u001a\u00020#2\u0006\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020%2\u0006\u0010(\u001a\u00020 2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\t0)2\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020\t0)2\u0018\u0010A\u001a\u0014\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00062\u0018\u0010B\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020403\u0012\u0004\u0012\u00020\t0)2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010D\u001a\u00020 2\u0006\u0010E\u001a\u00020 2\u0006\u0010F\u001a\u00020\u00002\u0006\u0010G\u001a\u000207H\u0003¢\u0006\u0004\bH\u0010I\u001a\u0017\u0010L\u001a\u00020\t2\u0006\u0010K\u001a\u00020\u0000H\u0003¢\u0006\u0004\bL\u0010M\"\u0014\u0010N\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\bN\u0010O¨\u0006]²\u0006\f\u0010Q\u001a\u00020P8\nX\u008a\u0084\u0002²\u0006\u0018\u0010?\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000>8\nX\u008a\u0084\u0002²\u0006\u000e\u0010$\u001a\u00020#8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010R\u001a\u00020 8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010S\u001a\u00020\b8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010T\u001a\u00020\b8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010<\u001a\u00020\b8\n@\nX\u008a\u008e\u0002²\u0006\f\u0010U\u001a\u00020#8\nX\u008a\u0084\u0002²\u0006\f\u0010V\u001a\u00020 8\nX\u008a\u0084\u0002²\u0006\f\u0010W\u001a\u00020 8\nX\u008a\u0084\u0002²\u0006\u000e\u0010X\u001a\u00020 8\n@\nX\u008a\u008e\u0002²\u0006\u0012\u0010Y\u001a\b\u0012\u0004\u0012\u000204038\nX\u008a\u0084\u0002²\u0006\f\u0010Z\u001a\u00020\b8\nX\u008a\u0084\u0002²\u0006\u001e\u0010[\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020403\u0012\u0004\u0012\u00020\t0)8\nX\u008a\u0084\u0002²\u0006\u0012\u0010\\\u001a\b\u0012\u0004\u0012\u00020\t0\r8\nX\u008a\u0084\u0002"}, d2 = {"", "catalogId", "Lcom/arflix/tv/data/model/Profile;", "currentProfile", "Lcom/arflix/tv/ui/screens/collections/CollectionDetailsViewModel;", "viewModel", "Lkotlin/Function2;", "Lcom/arflix/tv/data/model/MediaType;", "", "Lx6/t0;", "onNavigateToDetails", "Lkotlin/Function5;", "onNavigateToPlayer", "Lkotlin/Function0;", "onNavigateToHome", "onNavigateToSearch", "onNavigateToWatchlist", "onNavigateToTv", "onNavigateToSettings", "onSwitchProfile", "onBack", "CollectionDetailsScreen", "(Ljava/lang/String;Lcom/arflix/tv/data/model/Profile;Lcom/arflix/tv/ui/screens/collections/CollectionDetailsViewModel;Lr7/p;Lr7/s;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;III)V", "Lcom/arflix/tv/data/model/CatalogConfig;", "catalog", "CollectionBackdrop", "(Lcom/arflix/tv/data/model/CatalogConfig;Landroidx/compose/runtime/Composer;I)V", "Lcom/arflix/tv/data/model/CollectionGroupKind;", "group", "Landroidx/compose/ui/graphics/Color;", "collectionAccentColor", "(Lcom/arflix/tv/data/model/CollectionGroupKind;)J", "", "hasMovies", "hasSeries", "Lcom/arflix/tv/ui/screens/collections/CollectionTab;", "selectedTab", "Landroidx/compose/ui/focus/FocusRequester;", "moviesTabFocusRequester", "seriesTabFocusRequester", "isSportsCollection", "Lkotlin/Function1;", "onTabSelected", "CollectionTabBar", "(ZZLcom/arflix/tv/ui/screens/collections/CollectionTab;Landroidx/compose/ui/focus/FocusRequester;Landroidx/compose/ui/focus/FocusRequester;ZLr7/l;Landroidx/compose/runtime/Composer;I)V", "label", "isSelected", "focusRequester", "onClick", "CollectionTabChip", "(Ljava/lang/String;ZLandroidx/compose/ui/focus/FocusRequester;Lr7/a;Landroidx/compose/runtime/Composer;I)V", "", "Lcom/arflix/tv/data/model/MediaItem;", "items", "gridColumns", "Landroidx/compose/ui/unit/Dp;", "cardWidth", "usePosterCards", "Landroidx/tv/foundation/lazy/grid/b1;", "gridState", "pendingFocusIndex", "onClearPendingFocus", "", "cardLogoUrls", "onItemClick", "onItemFocused", "onVisibleItemsChanged", "onNearEnd", "isLoading", "isLoadingMore", "emptyMessage", "topContentPadding", "CollectionItemsGrid-_F44eQ8", "(Ljava/util/List;IFZLandroidx/tv/foundation/lazy/grid/b1;ILr7/a;ZZLjava/util/Map;Lcom/arflix/tv/ui/screens/collections/CollectionTab;Landroidx/compose/ui/focus/FocusRequester;Landroidx/compose/ui/focus/FocusRequester;ZLr7/l;Lr7/l;Lr7/p;Lr7/l;Lr7/a;ZZLjava/lang/String;FLandroidx/compose/runtime/Composer;III)V", "CollectionItemsGrid", "message", "CollectionEmptyState", "(Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "COLLECTION_LOAD_FAILED_ERROR", "Ljava/lang/String;", "Lcom/arflix/tv/ui/screens/collections/CollectionDetailsUiState;", "uiState", "hasReceivedInitialFocus", "lastFocusedMovieIndex", "lastFocusedSeriesIndex", "currentTab", "currentSupportsMovies", "currentSupportsSeries", "isFocused", "latestItems", "latestGridColumns", "latestOnVisibleItemsChanged", "latestOnNearEnd", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class CollectionDetailsScreenKt {
    private static final String COLLECTION_LOAD_FAILED_ERROR = "__collection_load_failed__";

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[CollectionTab.values().length];
            try {
                iArr[CollectionTab.MOVIES.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CollectionTab.SERIES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[CollectionGroupKind.values().length];
            try {
                iArr2[CollectionGroupKind.FEATURED.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[CollectionGroupKind.SERVICE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[CollectionGroupKind.GENRE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[CollectionGroupKind.DECADE.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[CollectionGroupKind.FRANCHISE.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[CollectionGroupKind.NETWORK.ordinal()] = 6;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0204  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void CollectionBackdrop(com.arflix.tv.data.model.CatalogConfig r33, androidx.compose.runtime.Composer r34, int r35) {
        /*
            Method dump skipped, instruction units count: 538
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.collections.CollectionDetailsScreenKt.CollectionBackdrop(com.arflix.tv.data.model.CatalogConfig, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CollectionBackdrop$lambda$3(CatalogConfig catalogConfig, int i10, Composer composer, int i11) {
        CollectionBackdrop(catalogConfig, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:150:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x02b4 A[ADDED_TO_REGION, REMOVE] */
    /* JADX WARN: Removed duplicated region for block: B:171:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x02d0  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x02f0  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0337  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x034d  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x037a  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x03a1  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x03c3  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x03d2  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x03e1  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x03e6  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x041e  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x044e  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0495  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x04ef  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0567  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x059f  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x05a1  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x05ae  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x05d8  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x05da  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x05e7  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x061e  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x062a  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x062e  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x064d  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x0674  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x067d  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0687  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0690  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x069a  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x06a3  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x06ad  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x06b0  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x06c0  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x06f7  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x071d  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x071f  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x0729  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x072c  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x0740  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x077b  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x0795  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x07be  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x07d5  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x07e7  */
    /* JADX WARN: Removed duplicated region for block: B:327:0x0813  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x081d  */
    /* JADX WARN: Removed duplicated region for block: B:334:0x0858  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void CollectionDetailsScreen(java.lang.String r52, com.arflix.tv.data.model.Profile r53, com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel r54, r7.p<? super com.arflix.tv.data.model.MediaType, ? super java.lang.Integer, x6.t0> r55, r7.s<? super com.arflix.tv.data.model.MediaType, ? super java.lang.Integer, ? super java.lang.String, ? super java.lang.String, ? super java.lang.String, x6.t0> r56, r7.a<x6.t0> r57, r7.a<x6.t0> r58, r7.a<x6.t0> r59, r7.a<x6.t0> r60, r7.a<x6.t0> r61, r7.a<x6.t0> r62, final r7.a<x6.t0> r63, androidx.compose.runtime.Composer r64, int r65, int r66, int r67) {
        /*
            Method dump skipped, instruction units count: 2186
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.collections.CollectionDetailsScreenKt.CollectionDetailsScreen(java.lang.String, com.arflix.tv.data.model.Profile, com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel, r7.p, r7.s, r7.a, r7.a, r7.a, r7.a, r7.a, r7.a, r7.a, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CollectionDetailsUiState CollectionDetailsScreen$lambda$1(State<CollectionDetailsUiState> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState CollectionDetailsScreen$lambda$10$0() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean CollectionDetailsScreen$lambda$11(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void CollectionDetailsScreen$lambda$12(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState CollectionDetailsScreen$lambda$13$0() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(-1, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int CollectionDetailsScreen$lambda$14(MutableState<Integer> mutableState) {
        return mutableState.getValue().intValue();
    }

    private static final void CollectionDetailsScreen$lambda$15(MutableState<Integer> mutableState, int i10) {
        mutableState.setValue(Integer.valueOf(i10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState CollectionDetailsScreen$lambda$16$0() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(-1, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int CollectionDetailsScreen$lambda$17(MutableState<Integer> mutableState) {
        return mutableState.getValue().intValue();
    }

    private static final void CollectionDetailsScreen$lambda$18(MutableState<Integer> mutableState, int i10) {
        mutableState.setValue(Integer.valueOf(i10));
    }

    private static final Map<String, String> CollectionDetailsScreen$lambda$2(State<? extends Map<String, String>> state) {
        return state.getValue();
    }

    private static final int CollectionDetailsScreen$lambda$20(MutableState<Integer> mutableState) {
        return mutableState.getValue().intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void CollectionDetailsScreen$lambda$21(MutableState<Integer> mutableState, int i10) {
        mutableState.setValue(Integer.valueOf(i10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CollectionTab CollectionDetailsScreen$lambda$23(State<? extends CollectionTab> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean CollectionDetailsScreen$lambda$24(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean CollectionDetailsScreen$lambda$25(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult CollectionDetailsScreen$lambda$28$0(final y yVar, final k0 k0Var, final b1 b1Var, final b1 b1Var2, final MutableState mutableState, final FocusRequester focusRequester, final FocusRequester focusRequester2, final State state, final State state2, final State state3, final MutableState mutableState2, final MutableState mutableState3, final MutableState mutableState4, DisposableEffectScope disposableEffectScope) {
        final w wVar = new w() { // from class: com.arflix.tv.ui.screens.collections.a
            @Override // androidx.lifecycle.w
            public final void onStateChanged(y yVar2, androidx.lifecycle.p pVar) {
                CollectionDetailsScreenKt.CollectionDetailsScreen$lambda$28$0$0(k0Var, b1Var, b1Var2, mutableState, focusRequester, focusRequester2, state, state2, state3, mutableState2, mutableState3, mutableState4, yVar2, pVar);
            }
        };
        yVar.getLifecycle().addObserver(wVar);
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.collections.CollectionDetailsScreenKt$CollectionDetailsScreen$lambda$28$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                yVar.getLifecycle().removeObserver(wVar);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void CollectionDetailsScreen$lambda$28$0$0(k0 k0Var, b1 b1Var, b1 b1Var2, MutableState mutableState, FocusRequester focusRequester, FocusRequester focusRequester2, State state, State state2, State state3, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, y yVar, androidx.lifecycle.p pVar) {
        if (pVar == androidx.lifecycle.p.ON_RESUME) {
            CollectionDetailsScreen$requestTabFocus(k0Var, b1Var, b1Var2, mutableState, focusRequester, focusRequester2, state, state2, state3, mutableState2, mutableState3, mutableState4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CollectionDetailsScreen$lambda$29$0(r7.a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CollectionDetailsScreen$lambda$31$0$0(MutableState mutableState) {
        CollectionDetailsScreen$lambda$21(mutableState, -1);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CollectionDetailsScreen$lambda$31$1$0(MutableState mutableState, CollectionTab collectionTab) {
        mutableState.setValue(collectionTab);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CollectionDetailsScreen$lambda$31$2$0(CollectionDetailsViewModel collectionDetailsViewModel, r7.s sVar, r7.p pVar, Context context, MediaItem mediaItem) {
        if (SportsAddonCapabilities.INSTANCE.isSportsEventStatus(mediaItem.getStatus())) {
            collectionDetailsViewModel.openSportsCollectionItem(mediaItem, new e(context, mediaItem, 1), sVar);
        } else {
            pVar.invoke(mediaItem.getMediaType(), Integer.valueOf(mediaItem.getId()));
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CollectionDetailsScreen$lambda$31$2$0$0(Context context, MediaItem mediaItem) {
        Toast.makeText(context, u.L(mediaItem.getBadge(), "LIVE", true) ? context.getString(R.string.home_sports_playback_failed) : context.getString(R.string.home_sports_event_not_live), 0).show();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CollectionDetailsScreen$lambda$31$3$0(CollectionDetailsViewModel collectionDetailsViewModel, CollectionTab collectionTab, MutableState mutableState, MutableState mutableState2, MediaItem mediaItem, int i10) {
        collectionDetailsViewModel.preloadLogos(Collections.singletonList(mediaItem));
        int i11 = WhenMappings.$EnumSwitchMapping$0[collectionTab.ordinal()];
        if (i11 == 1) {
            CollectionDetailsScreen$lambda$15(mutableState, i10);
        } else {
            if (i11 != 2) {
                throw new NoWhenBranchMatchedException();
            }
            CollectionDetailsScreen$lambda$18(mutableState2, i10);
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CollectionDetailsScreen$lambda$31$4$0(CollectionDetailsViewModel collectionDetailsViewModel, List list) {
        collectionDetailsViewModel.preloadLogos(list);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CollectionDetailsScreen$lambda$31$5$0(CollectionDetailsViewModel collectionDetailsViewModel, CollectionTab collectionTab) {
        collectionDetailsViewModel.loadMoreIfNeeded(collectionTab);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CollectionDetailsScreen$lambda$32(String str, Profile profile, CollectionDetailsViewModel collectionDetailsViewModel, r7.p pVar, r7.s sVar, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, r7.a aVar5, r7.a aVar6, r7.a aVar7, int i10, int i11, int i12, Composer composer, int i13) {
        CollectionDetailsScreen(str, profile, collectionDetailsViewModel, pVar, sVar, aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), RecomposeScopeImplKt.updateChangedFlags(i11), i12);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState CollectionDetailsScreen$lambda$5$0(CollectionTab collectionTab) {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(collectionTab, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CollectionTab CollectionDetailsScreen$lambda$6(MutableState<CollectionTab> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void CollectionDetailsScreen$requestTabFocus(k0 k0Var, b1 b1Var, b1 b1Var2, MutableState<Boolean> mutableState, FocusRequester focusRequester, FocusRequester focusRequester2, State<? extends CollectionTab> state, State<Boolean> state2, State<Boolean> state3, MutableState<Integer> mutableState2, MutableState<Integer> mutableState3, MutableState<Integer> mutableState4) {
        m0.p(k0Var, null, 0, new CollectionDetailsScreenKt$CollectionDetailsScreen$requestTabFocus$1(b1Var, b1Var2, mutableState, focusRequester, focusRequester2, state, state2, state3, mutableState2, mutableState3, mutableState4, null), 3);
    }

    private static final void CollectionEmptyState(String str, Composer composer, int i10) {
        int i11;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1727565478);
        if ((i10 & 6) == 0) {
            i11 = i10 | (composerStartRestartGroup.changed(str) ? 4 : 2);
        } else {
            i11 = i10;
        }
        if ((i11 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1727565478, i11, -1, "com.arflix.tv.ui.screens.collections.CollectionEmptyState (CollectionDetailsScreen.kt:1021)");
            }
            Modifier modifierM564height3ABfNKs = SizeKt.m564height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m5678constructorimpl(320));
            Alignment center = Alignment.INSTANCE.getCenter();
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM564height3ABfNKs);
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
            r7.p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            composer2 = composerStartRestartGroup;
            TextKt.m6020Text4IGK_g(str, null, ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, ArflixTypography.INSTANCE.getBody(), composer2, (i11 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new b(str, i10, 0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CollectionEmptyState$lambda$1(String str, int i10, Composer composer, int i11) {
        CollectionEmptyState(str, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: CollectionItemsGrid-_F44eQ8, reason: not valid java name */
    private static final void m6196CollectionItemsGrid_F44eQ8(final List<MediaItem> list, final int i10, final float f10, final boolean z, final b1 b1Var, final int i11, final r7.a<t0> aVar, final boolean z5, final boolean z10, final Map<String, String> map, final CollectionTab collectionTab, final FocusRequester focusRequester, final FocusRequester focusRequester2, final boolean z11, final r7.l<? super CollectionTab, t0> lVar, final r7.l<? super MediaItem, t0> lVar2, final r7.p<? super MediaItem, ? super Integer, t0> pVar, final r7.l<? super List<MediaItem>, t0> lVar3, final r7.a<t0> aVar2, final boolean z12, final boolean z13, final String str, final float f11, Composer composer, final int i12, final int i13, final int i14) {
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        float fM5678constructorimpl;
        Object collectionDetailsScreenKt$CollectionItemsGrid$1$1;
        int i20;
        Composer composer2;
        final String str2;
        int i21;
        int i22;
        int i23;
        b1 b1Var2;
        int i24;
        androidx.tv.foundation.lazy.grid.m0 m0Var;
        Composer composer3;
        Composer composerStartRestartGroup = composer.startRestartGroup(1202824593);
        if ((i12 & 6) == 0) {
            i15 = (composerStartRestartGroup.changed(list) ? 4 : 2) | i12;
        } else {
            i15 = i12;
        }
        if ((i12 & 48) == 0) {
            i15 |= composerStartRestartGroup.changed(i10) ? 32 : 16;
        }
        if ((i12 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i15 |= composerStartRestartGroup.changed(f10) ? 256 : 128;
        }
        if ((i12 & 3072) == 0) {
            i15 |= composerStartRestartGroup.changed(z) ? 2048 : 1024;
        }
        if ((i12 & 24576) == 0) {
            i15 |= composerStartRestartGroup.changed(b1Var) ? 16384 : 8192;
        }
        if ((i12 & 196608) == 0) {
            i15 |= composerStartRestartGroup.changed(i11) ? 131072 : 65536;
        }
        if ((i12 & 1572864) == 0) {
            i15 |= composerStartRestartGroup.changedInstance(aVar) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((i12 & 12582912) == 0) {
            i15 |= composerStartRestartGroup.changed(z5) ? 8388608 : 4194304;
        }
        if ((i12 & 100663296) == 0) {
            i15 |= composerStartRestartGroup.changed(z10) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        }
        if ((i12 & 805306368) == 0) {
            i15 |= composerStartRestartGroup.changed(map) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        if ((i13 & 6) == 0) {
            i16 = 196608;
            i17 = (composerStartRestartGroup.changed(collectionTab.ordinal()) ? 4 : 2) | i13;
        } else {
            i16 = 196608;
            i17 = i13;
        }
        if ((i13 & 48) == 0) {
            i17 |= composerStartRestartGroup.changed(focusRequester) ? 32 : 16;
        }
        if ((i13 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i17 |= composerStartRestartGroup.changed(focusRequester2) ? 256 : 128;
        }
        if ((i13 & 3072) == 0) {
            i17 |= composerStartRestartGroup.changed(z11) ? 2048 : 1024;
        }
        if ((i13 & 24576) == 0) {
            i17 |= composerStartRestartGroup.changedInstance(lVar) ? 16384 : 8192;
        }
        if ((i13 & i16) == 0) {
            i17 |= composerStartRestartGroup.changedInstance(lVar2) ? 131072 : 65536;
        }
        if ((i13 & 1572864) == 0) {
            i17 |= composerStartRestartGroup.changedInstance(pVar) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((i13 & 12582912) == 0) {
            i17 |= composerStartRestartGroup.changedInstance(lVar3) ? 8388608 : 4194304;
        }
        if ((i13 & 100663296) == 0) {
            i17 |= composerStartRestartGroup.changedInstance(aVar2) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        }
        if ((i13 & 805306368) == 0) {
            i17 |= composerStartRestartGroup.changed(z12) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        if ((i14 & 6) == 0) {
            i18 = i14 | (composerStartRestartGroup.changed(z13) ? 4 : 2);
        } else {
            i18 = i14;
        }
        if ((i14 & 48) == 0) {
            i18 |= composerStartRestartGroup.changed(str) ? 32 : 16;
        }
        if ((i14 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i18 |= composerStartRestartGroup.changed(f11) ? 256 : 128;
        }
        int i25 = i18;
        if ((i15 & 306783379) == 306783378 && (i17 & 306783379) == 306783378 && (i25 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer3 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1202824593, i15, i17, "com.arflix.tv.ui.screens.collections.CollectionItemsGrid (CollectionDetailsScreen.kt:877)");
            }
            String str3 = z ? "poster_card" : "landscape_card";
            if (z) {
                i19 = i25;
                fM5678constructorimpl = Dp.m5678constructorimpl(10);
            } else {
                i19 = i25;
                fM5678constructorimpl = Dp.m5678constructorimpl(6);
            }
            int i26 = i15 & 14;
            int i27 = i15;
            State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(list, composerStartRestartGroup, i26);
            State stateRememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(Integer.valueOf(i10), composerStartRestartGroup, (i27 >> 3) & 14);
            State stateRememberUpdatedState3 = SnapshotStateKt.rememberUpdatedState(lVar3, composerStartRestartGroup, (i17 >> 21) & 14);
            State stateRememberUpdatedState4 = SnapshotStateKt.rememberUpdatedState(aVar2, composerStartRestartGroup, (i17 >> 24) & 14);
            float f12 = fM5678constructorimpl;
            boolean zChanged = ((i27 & 57344) == 16384) | composerStartRestartGroup.changed(stateRememberUpdatedState4) | composerStartRestartGroup.changed(stateRememberUpdatedState) | composerStartRestartGroup.changed(stateRememberUpdatedState2) | composerStartRestartGroup.changed(stateRememberUpdatedState3);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                i20 = i17;
                composer2 = composerStartRestartGroup;
                str2 = str3;
                i21 = i27;
                i22 = i26;
                i23 = 14;
                b1Var2 = b1Var;
                collectionDetailsScreenKt$CollectionItemsGrid$1$1 = new CollectionDetailsScreenKt$CollectionItemsGrid$1$1(b1Var2, stateRememberUpdatedState4, stateRememberUpdatedState, stateRememberUpdatedState2, stateRememberUpdatedState3, null);
                composer2.updateRememberedValue(collectionDetailsScreenKt$CollectionItemsGrid$1$1);
            } else {
                b1Var2 = b1Var;
                str2 = str3;
                composer2 = composerStartRestartGroup;
                i21 = i27;
                i22 = i26;
                i23 = 14;
                collectionDetailsScreenKt$CollectionItemsGrid$1$1 = objRememberedValue;
                i20 = i17;
            }
            EffectsKt.LaunchedEffect(b1Var2, (r7.p<? super k0, ? super d7.d<? super t0>, ? extends Object>) collectionDetailsScreenKt$CollectionItemsGrid$1$1, composer2, (i21 >> 12) & i23);
            androidx.tv.foundation.lazy.grid.m0 m0Var2 = new androidx.tv.foundation.lazy.grid.m0(i10);
            Modifier modifierClipToBounds = ClipKt.clipToBounds(ArvioDpadFocusKt.arvioDpadFocusGroup$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, false, 3, null));
            float f13 = 42;
            PaddingValues paddingValuesM525PaddingValuesa9UjIt4 = PaddingKt.m525PaddingValuesa9UjIt4(Dp.m5678constructorimpl(f13), f11, Dp.m5678constructorimpl(f13), Dp.m5678constructorimpl(Dp.m5678constructorimpl(48) + f12));
            Arrangement arrangement = Arrangement.INSTANCE;
            Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = arrangement.m441spacedBy0680j_4(Dp.m5678constructorimpl(z ? 18 : i23));
            Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_42 = arrangement.m441spacedBy0680j_4(Dp.m5678constructorimpl(z ? 18 : i23));
            boolean zChanged2 = ((i20 & 458752) == 131072) | (i22 == 4) | ((29360128 & i21) == 8388608) | ((i21 & 234881024) == 67108864) | ((i20 & 14) == 4) | ((i20 & 112) == 32) | ((i20 & 896) == 256) | ((i20 & 7168) == 2048) | ((i20 & 57344) == 16384) | ((1879048192 & i20) == 536870912) | ((i21 & 7168) == 2048) | ((i21 & 896) == 256) | ((i21 & 112) == 32) | ((i19 & 14) == 4) | ((i19 & 112) == 32) | composer2.changed(str2) | ((1879048192 & i21) == 536870912) | ((458752 & i21) == 131072) | ((3670016 & i21) == 1048576) | ((3670016 & i20) == 1048576) | ((i20 & 234881024) == 67108864);
            Object objRememberedValue2 = composer2.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Composer composer4 = composer2;
                i24 = i21;
                m0Var = m0Var2;
                r7.l lVar4 = new r7.l() { // from class: com.arflix.tv.ui.screens.collections.n
                    @Override // r7.l
                    public final Object invoke(Object obj) {
                        return CollectionDetailsScreenKt.CollectionItemsGrid__F44eQ8$lambda$5$0(z12, z, f10, i10, list, z13, z5, z10, collectionTab, focusRequester, focusRequester2, z11, lVar, str, str2, map, i11, aVar, pVar, aVar2, lVar2, (v0) obj);
                    }
                };
                composer3 = composer4;
                composer3.updateRememberedValue(lVar4);
                objRememberedValue2 = lVar4;
            } else {
                composer3 = composer2;
                i24 = i21;
                m0Var = m0Var2;
            }
            LazyGridDslKt.TvLazyVerticalGrid(m0Var, modifierClipToBounds, b1Var, paddingValuesM525PaddingValuesa9UjIt4, false, horizontalOrVerticalM441spacedBy0680j_4, horizontalOrVerticalM441spacedBy0680j_42, false, null, (r7.l) objRememberedValue2, composer3, (i24 >> 6) & 896, MediaError.DetailedErrorCode.MANIFEST_UNKNOWN);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new r7.p() { // from class: com.arflix.tv.ui.screens.collections.o
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return CollectionDetailsScreenKt.CollectionItemsGrid__F44eQ8$lambda$6(list, i10, f10, z, b1Var, i11, aVar, z5, z10, map, collectionTab, focusRequester, focusRequester2, z11, lVar, lVar2, pVar, lVar3, aVar2, z12, z13, str, f11, i12, i13, i14, (Composer) obj, iIntValue);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<MediaItem> CollectionItemsGrid__F44eQ8$lambda$0(State<? extends List<MediaItem>> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int CollectionItemsGrid__F44eQ8$lambda$1(State<Integer> state) {
        return state.getValue().intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final r7.l<List<MediaItem>, t0> CollectionItemsGrid__F44eQ8$lambda$2(State<? extends r7.l<? super List<MediaItem>, t0>> state) {
        return (r7.l) state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final r7.a<t0> CollectionItemsGrid__F44eQ8$lambda$3(State<? extends r7.a<t0>> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CollectionItemsGrid__F44eQ8$lambda$5$0(boolean z, boolean z5, float f10, int i10, List list, boolean z10, boolean z11, boolean z12, CollectionTab collectionTab, FocusRequester focusRequester, FocusRequester focusRequester2, boolean z13, r7.l lVar, final String str, String str2, Map map, int i11, r7.a aVar, r7.p pVar, r7.a aVar2, r7.l lVar2, v0 v0Var) {
        v0 v0Var2;
        v0Var.item(null, new q(2), "tabs", ComposableLambdaKt.composableLambdaInstance(55545149, true, new g(z11, z12, collectionTab, focusRequester, focusRequester2, z13, lVar)));
        q qVar = new q(3);
        ComposableSingletons$CollectionDetailsScreenKt composableSingletons$CollectionDetailsScreenKt = ComposableSingletons$CollectionDetailsScreenKt.INSTANCE;
        v0Var.item(null, qVar, "tabs_gap", composableSingletons$CollectionDetailsScreenKt.getLambda$1281883188$app_playRelease());
        if (z) {
            float fM5678constructorimpl = z5 ? Dp.m5678constructorimpl(1.5f * f10) : Dp.m5678constructorimpl(Dp.m5678constructorimpl(9.0f * f10) / 16.0f);
            List listC1 = x.c1(new x7.i(1, i10 * 3, 1));
            v0Var.items(listC1.size(), null, null, new CollectionDetailsScreenKt$CollectionItemsGrid__F44eQ8$lambda$5$0$$inlined$itemsIndexed$default$3(listC1), ComposableLambdaKt.composableLambdaInstance(-619965551, true, new CollectionDetailsScreenKt$CollectionItemsGrid__F44eQ8$lambda$5$0$$inlined$itemsIndexed$default$4(listC1, fM5678constructorimpl)));
            v0Var2 = v0Var;
        } else {
            v0Var2 = v0Var;
            if (!list.isEmpty() || z10) {
                v0Var2.items(list.size(), new CollectionDetailsScreenKt$CollectionItemsGrid__F44eQ8$lambda$5$0$$inlined$itemsIndexed$default$5(new m(), list), null, new CollectionDetailsScreenKt$CollectionItemsGrid__F44eQ8$lambda$5$0$$inlined$itemsIndexed$default$7(list, str2), ComposableLambdaKt.composableLambdaInstance(-619965551, true, new CollectionDetailsScreenKt$CollectionItemsGrid__F44eQ8$lambda$5$0$$inlined$itemsIndexed$default$8(list, map, i11, aVar, f10, z5, pVar, list, aVar2, lVar2)));
            } else {
                v0Var2.item(null, new q(4), "empty", ComposableLambdaKt.composableLambdaInstance(961479361, true, new r7.q() { // from class: com.arflix.tv.ui.screens.collections.l
                    @Override // r7.q
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        int iIntValue = ((Integer) obj3).intValue();
                        return CollectionDetailsScreenKt.CollectionItemsGrid__F44eQ8$lambda$5$0$6(str, (q0) obj, (Composer) obj2, iIntValue);
                    }
                }));
            }
        }
        if (z10) {
            v0Var2.item(null, new q(5), "loading_more", composableSingletons$CollectionDetailsScreenKt.m6203getLambda$1294403185$app_playRelease());
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final o0 CollectionItemsGrid__F44eQ8$lambda$5$0$0(s0 s0Var) {
        return new o0(s0Var.getMaxLineSpan());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CollectionItemsGrid__F44eQ8$lambda$5$0$1(boolean z, boolean z5, CollectionTab collectionTab, FocusRequester focusRequester, FocusRequester focusRequester2, boolean z10, r7.l lVar, q0 q0Var, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(55545149, i10, -1, "com.arflix.tv.ui.screens.collections.CollectionItemsGrid.<anonymous>.<anonymous>.<anonymous> (CollectionDetailsScreen.kt:928)");
            }
            CollectionTabBar(z, z5, collectionTab, focusRequester, focusRequester2, z10, lVar, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final o0 CollectionItemsGrid__F44eQ8$lambda$5$0$10(s0 s0Var) {
        return new o0(s0Var.getMaxLineSpan());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final o0 CollectionItemsGrid__F44eQ8$lambda$5$0$2(s0 s0Var) {
        return new o0(s0Var.getMaxLineSpan());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final o0 CollectionItemsGrid__F44eQ8$lambda$5$0$5(s0 s0Var) {
        return new o0(s0Var.getMaxLineSpan());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CollectionItemsGrid__F44eQ8$lambda$5$0$6(String str, q0 q0Var, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(961479361, i10, -1, "com.arflix.tv.ui.screens.collections.CollectionItemsGrid.<anonymous>.<anonymous>.<anonymous> (CollectionDetailsScreen.kt:960)");
            }
            CollectionEmptyState(str, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object CollectionItemsGrid__F44eQ8$lambda$5$0$7(int i10, MediaItem mediaItem) {
        return mediaItem.getMediaType() + "-" + mediaItem.getId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CollectionItemsGrid__F44eQ8$lambda$6(List list, int i10, float f10, boolean z, b1 b1Var, int i11, r7.a aVar, boolean z5, boolean z10, Map map, CollectionTab collectionTab, FocusRequester focusRequester, FocusRequester focusRequester2, boolean z11, r7.l lVar, r7.l lVar2, r7.p pVar, r7.l lVar3, r7.a aVar2, boolean z12, boolean z13, String str, float f11, int i12, int i13, int i14, Composer composer, int i15) {
        m6196CollectionItemsGrid_F44eQ8(list, i10, f10, z, b1Var, i11, aVar, z5, z10, map, collectionTab, focusRequester, focusRequester2, z11, lVar, lVar2, pVar, lVar3, aVar2, z12, z13, str, f11, composer, RecomposeScopeImplKt.updateChangedFlags(i12 | 1), RecomposeScopeImplKt.updateChangedFlags(i13), RecomposeScopeImplKt.updateChangedFlags(i14));
        return t0.f22605a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final void CollectionTabBar(final boolean z, final boolean z5, final CollectionTab collectionTab, final FocusRequester focusRequester, final FocusRequester focusRequester2, final boolean z10, final r7.l<? super CollectionTab, t0> lVar, Composer composer, final int i10) {
        int i11;
        int i12;
        final int i13;
        int i14;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1380533109);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(z5) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(collectionTab.ordinal()) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changed(focusRequester) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= composerStartRestartGroup.changed(focusRequester2) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i11 |= composerStartRestartGroup.changed(z10) ? 131072 : 65536;
        }
        if ((1572864 & i10) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(lVar) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((599187 & i11) == 599186 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1380533109, i11, -1, "com.arflix.tv.ui.screens.collections.CollectionTabBar (CollectionDetailsScreen.kt:758)");
            }
            boolean z11 = z || !z5;
            boolean z12 = z5 || !z;
            boolean z13 = z11 ^ z12;
            float f10 = 42;
            Modifier modifierM533paddingqDBjuR0$default = PaddingKt.m533paddingqDBjuR0$default(ArvioDpadFocusKt.arvioDpadFocusGroup$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), null, false, 3, null), Dp.m5678constructorimpl(f10), 0.0f, Dp.m5678constructorimpl(f10), 0.0f, 10, null);
            MeasurePolicy measurePolicyC = androidx.compose.foundation.c.C(Alignment.INSTANCE, androidx.compose.foundation.c.g(8, Arrangement.INSTANCE, composerStartRestartGroup, 693286680), composerStartRestartGroup, 6, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM533paddingqDBjuR0$default);
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
            r7.p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyC, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            composerStartRestartGroup.startReplaceGroup(-738392891);
            if (z10) {
                composerStartRestartGroup.startReplaceGroup(-738390846);
                boolean z14 = (3670016 & i11) == 1048576;
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z14 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    final int i15 = 0;
                    objRememberedValue = new r7.a() { // from class: com.arflix.tv.ui.screens.collections.i
                        @Override // r7.a
                        public final Object invoke() {
                            switch (i15) {
                                case 0:
                                    return CollectionDetailsScreenKt.CollectionTabBar$lambda$0$0$0(lVar);
                                case 1:
                                    return CollectionDetailsScreenKt.CollectionTabBar$lambda$0$1$0(lVar);
                                default:
                                    return CollectionDetailsScreenKt.CollectionTabBar$lambda$0$2$0(lVar);
                            }
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                CollectionTabChip("Live & Upcoming", true, focusRequester2, (r7.a) objRememberedValue, composerStartRestartGroup, ((i11 >> 6) & 896) | 54);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-738120805);
                composerStartRestartGroup.endReplaceGroup();
                if (z11) {
                    composerStartRestartGroup.startReplaceGroup(-738086271);
                    String strStringResource = StringResources_androidKt.stringResource(R.string.movies, composerStartRestartGroup, 0);
                    boolean z15 = collectionTab == CollectionTab.MOVIES || z13;
                    boolean z16 = (i11 & 3670016) == 1048576;
                    Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z16 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        i13 = 1;
                        objRememberedValue2 = new r7.a() { // from class: com.arflix.tv.ui.screens.collections.i
                            @Override // r7.a
                            public final Object invoke() {
                                switch (i13) {
                                    case 0:
                                        return CollectionDetailsScreenKt.CollectionTabBar$lambda$0$0$0(lVar);
                                    case 1:
                                        return CollectionDetailsScreenKt.CollectionTabBar$lambda$0$1$0(lVar);
                                    default:
                                        return CollectionDetailsScreenKt.CollectionTabBar$lambda$0$2$0(lVar);
                                }
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        i13 = 1;
                    }
                    i12 = 0;
                    i14 = 1048576;
                    CollectionTabChip(strStringResource, z15, focusRequester, (r7.a) objRememberedValue2, composerStartRestartGroup, (i11 >> 3) & 896);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    i12 = 0;
                    i13 = 1;
                    i14 = ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES;
                    composerStartRestartGroup.startReplaceGroup(-737784517);
                    composerStartRestartGroup.endReplaceGroup();
                }
                if (z12) {
                    composerStartRestartGroup.startReplaceGroup(-737749983);
                    String strStringResource2 = StringResources_androidKt.stringResource(R.string.series, composerStartRestartGroup, i12);
                    boolean z17 = (collectionTab == CollectionTab.SERIES || z13) ? i13 : i12;
                    int i16 = (3670016 & i11) == i14 ? i13 : i12;
                    Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (i16 != 0 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        final int i17 = 2;
                        objRememberedValue3 = new r7.a() { // from class: com.arflix.tv.ui.screens.collections.i
                            @Override // r7.a
                            public final Object invoke() {
                                switch (i17) {
                                    case 0:
                                        return CollectionDetailsScreenKt.CollectionTabBar$lambda$0$0$0(lVar);
                                    case 1:
                                        return CollectionDetailsScreenKt.CollectionTabBar$lambda$0$1$0(lVar);
                                    default:
                                        return CollectionDetailsScreenKt.CollectionTabBar$lambda$0$2$0(lVar);
                                }
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    CollectionTabChip(strStringResource2, z17, focusRequester2, (r7.a) objRememberedValue3, composerStartRestartGroup, (i11 >> 6) & 896);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-737448229);
                    composerStartRestartGroup.endReplaceGroup();
                }
            }
            composerStartRestartGroup.endReplaceGroup();
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new r7.p() { // from class: com.arflix.tv.ui.screens.collections.j
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return CollectionDetailsScreenKt.CollectionTabBar$lambda$1(z, z5, collectionTab, focusRequester, focusRequester2, z10, lVar, i10, (Composer) obj, iIntValue);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CollectionTabBar$lambda$0$0$0(r7.l lVar) {
        lVar.invoke(CollectionTab.SERIES);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CollectionTabBar$lambda$0$1$0(r7.l lVar) {
        lVar.invoke(CollectionTab.MOVIES);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CollectionTabBar$lambda$0$2$0(r7.l lVar) {
        lVar.invoke(CollectionTab.SERIES);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CollectionTabBar$lambda$1(boolean z, boolean z5, CollectionTab collectionTab, FocusRequester focusRequester, FocusRequester focusRequester2, boolean z10, r7.l lVar, int i10, Composer composer, int i11) {
        CollectionTabBar(z, z5, collectionTab, focusRequester, focusRequester2, z10, lVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    private static final void CollectionTabChip(String str, boolean z, FocusRequester focusRequester, r7.a<t0> aVar, Composer composer, int i10) {
        String str2;
        int i11;
        long jM3471copywmQWz5c$default;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-391731635);
        if ((i10 & 6) == 0) {
            str2 = str;
            i11 = (composerStartRestartGroup.changed(str2) ? 4 : 2) | i10;
        } else {
            str2 = str;
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(focusRequester) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar) ? 2048 : 1024;
        }
        if ((i11 & 1171) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-391731635, i11, -1, "com.arflix.tv.ui.screens.collections.CollectionTabChip (CollectionDetailsScreen.kt:803)");
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableState mutableState = (MutableState) objRememberedValue;
            RoundedCornerShape RoundedCornerShape = RoundedCornerShapeKt.RoundedCornerShape(50);
            long jM3509getWhite0d7_KjU = z ? Color.INSTANCE.m3509getWhite0d7_KjU() : CollectionTabChip$lambda$1(mutableState) ? Color.INSTANCE.m3507getTransparent0d7_KjU() : Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.08f, 0.0f, 0.0f, 0.0f, 14, null);
            if (z) {
                composerStartRestartGroup.startReplaceGroup(1503034112);
                jM3471copywmQWz5c$default = ThemeKt.appBackgroundDark(composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
            } else if (CollectionTabChip$lambda$1(mutableState)) {
                composerStartRestartGroup.startReplaceGroup(1503035602);
                composerStartRestartGroup.endReplaceGroup();
                jM3471copywmQWz5c$default = Color.INSTANCE.m3509getWhite0d7_KjU();
            } else {
                composerStartRestartGroup.startReplaceGroup(1503036704);
                composerStartRestartGroup.endReplaceGroup();
                jM3471copywmQWz5c$default = Color.m3471copywmQWz5c$default(ColorKt.getTextPrimary(), 0.75f, 0.0f, 0.0f, 0.0f, 14, null);
            }
            long j10 = jM3471copywmQWz5c$default;
            int i12 = 1;
            Modifier modifierFocusRequester = FocusRequesterModifierKt.focusRequester(BorderKt.m191borderxT4_qwU(BackgroundKt.m179backgroundbw27NRU$default(ClipKt.clip(Modifier.INSTANCE, RoundedCornerShape), jM3509getWhite0d7_KjU, null, 2, null), (z && CollectionTabChip$lambda$1(mutableState)) ? Dp.m5678constructorimpl(1) : CollectionTabChip$lambda$1(mutableState) ? Dp.m5678constructorimpl(2) : Dp.m5678constructorimpl(0), (z && CollectionTabChip$lambda$1(mutableState)) ? androidx.compose.ui.graphics.ColorKt.Color(4283400112L) : CollectionTabChip$lambda$1(mutableState) ? Color.INSTANCE.m3509getWhite0d7_KjU() : Color.INSTANCE.m3507getTransparent0d7_KjU(), RoundedCornerShape), focusRequester);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new h(mutableState, i12);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            Modifier modifierOnFocusChanged = FocusChangedModifierKt.onFocusChanged(modifierFocusRequester, (r7.l) objRememberedValue2);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = new r7.l<KeyEvent, Boolean>() { // from class: com.arflix.tv.ui.screens.collections.CollectionDetailsScreenKt$CollectionTabChip$2$1
                    @Override // r7.l
                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        return m6202invokeZmokQxo(((KeyEvent) obj).m4390unboximpl());
                    }

                    /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                    public final Boolean m6202invokeZmokQxo(android.view.KeyEvent keyEvent) {
                        return Boolean.valueOf(KeyEventType.m4394equalsimpl0(KeyEvent_androidKt.m4402getTypeZmokQxo(keyEvent), KeyEventType.INSTANCE.m4398getKeyDownCS__XNY()) && Key.m4093equalsimpl0(KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent), Key.INSTANCE.m4171getDirectionUpEK5gGoQ()));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            Modifier modifierH = a2.h(10, ClickableKt.m214clickableXHw0xAI$default(FocusableKt.focusable$default(KeyInputModifierKt.onPreviewKeyEvent(modifierOnFocusChanged, (r7.l) objRememberedValue3), false, null, 3, null), false, null, null, aVar, 7, null), Dp.m5678constructorimpl(22), composerStartRestartGroup, 733328855);
            MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierH);
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
            r7.p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            TextStyle sectionTitle = ArflixTypography.INSTANCE.getSectionTitle();
            long sp = TextUnitKt.getSp(14);
            FontWeight.Companion companion3 = FontWeight.INSTANCE;
            composer2 = composerStartRestartGroup;
            TextKt.m6020Text4IGK_g(str2, null, j10, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(sectionTitle, 0L, sp, z ? companion3.getBold() : companion3.getSemiBold(), null, null, null, null, TextUnitKt.getSp(0.4d), null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777081, null), composer2, i11 & 14, 0, 65530);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new w0(str, z, focusRequester, aVar, i10));
        }
    }

    private static final boolean CollectionTabChip$lambda$1(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void CollectionTabChip$lambda$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CollectionTabChip$lambda$3$0(MutableState mutableState, FocusState focusState) {
        CollectionTabChip$lambda$2(mutableState, focusState.isFocused());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CollectionTabChip$lambda$6(String str, boolean z, FocusRequester focusRequester, r7.a aVar, int i10, Composer composer, int i11) {
        CollectionTabChip(str, z, focusRequester, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    private static final long collectionAccentColor(CollectionGroupKind collectionGroupKind) {
        switch (collectionGroupKind == null ? -1 : WhenMappings.$EnumSwitchMapping$1[collectionGroupKind.ordinal()]) {
            case -1:
                return Color.INSTANCE.m3509getWhite0d7_KjU();
            case 0:
            default:
                throw new NoWhenBranchMatchedException();
            case 1:
                return androidx.compose.ui.graphics.ColorKt.Color(4293304892L);
            case 2:
                return androidx.compose.ui.graphics.ColorKt.Color(4279937004L);
            case 3:
                return androidx.compose.ui.graphics.ColorKt.Color(4291190075L);
            case 4:
                return androidx.compose.ui.graphics.ColorKt.Color(4290349874L);
            case 5:
                return androidx.compose.ui.graphics.ColorKt.Color(4281310357L);
            case 6:
                return androidx.compose.ui.graphics.ColorKt.Color(4283407721L);
        }
    }
}
