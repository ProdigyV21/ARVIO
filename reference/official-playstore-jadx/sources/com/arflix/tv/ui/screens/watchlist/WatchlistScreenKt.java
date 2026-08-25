package com.arflix.tv.ui.screens.watchlist;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.foundation.lazy.LazyListStateKt;
import androidx.compose.foundation.lazy.grid.GridItemSpan;
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope;
import androidx.compose.foundation.lazy.grid.LazyGridScope;
import androidx.compose.foundation.lazy.grid.LazyGridSpanKt;
import androidx.compose.foundation.lazy.grid.LazyGridState;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.automirrored.outlined.SortKt;
import androidx.compose.material.icons.outlined.ArrowDropDownKt;
import androidx.compose.material.icons.outlined.BookmarkKt;
import androidx.compose.material.icons.outlined.CheckKt;
import androidx.compose.material.icons.outlined.RefreshKt;
import androidx.compose.material.icons.outlined.SearchKt;
import androidx.compose.material3.AndroidMenu_androidKt;
import androidx.compose.material3.IconKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.fragment.app.a2;
import androidx.lifecycle.y;
import androidx.media3.common.C;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.tv.material3.TextKt;
import com.arflix.tv.data.model.CatalogSourceType;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.data.repository.HomeServerCatalogCandidate;
import com.arflix.tv.data.repository.HomeServerKind;
import com.arflix.tv.data.repository.HomeServerLibrarySort;
import com.arflix.tv.ui.components.LoadingIndicatorKt;
import com.arflix.tv.ui.components.e0;
import com.arflix.tv.ui.components.p1;
import com.arflix.tv.ui.screens.player.c0;
import com.arflix.tv.ui.screens.profile.f0;
import com.arflix.tv.ui.screens.settings.j2;
import com.arflix.tv.ui.screens.tv.live.a0;
import com.arflix.tv.ui.screens.watchlist.WatchlistSourceItem;
import com.arflix.tv.ui.theme.ArflixTypography;
import com.arflix.tv.ui.theme.ColorKt;
import com.arflix.tv.util.AppLanguageKt;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.LinkHeader;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import ka.k0;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.x;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Ü\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0003\u001a\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\u0002¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u001b\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0006\u0010\u0007\u001a§\u0001\u0010\u0019\u001a\u00020\u000f2\b\b\u0002\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\u001a\b\u0002\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\f2\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00112\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00112\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00112\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000f0\u00152\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00112\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0011H\u0007¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u0083\u0001\u0010*\u001a\u00020\u000f2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u000e2\u0006\u0010!\u001a\u00020 2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0\u001b2\u0006\u0010$\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020%2\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\u00152\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\u0015H\u0003¢\u0006\u0004\b*\u0010+\u001aá\u0001\u0010B\u001a\u00020\u000f*\u00020,2\u0006\u0010-\u001a\u00020 2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010.2\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00050\u001b2\u0006\u00101\u001a\u00020\u000e2\u0006\u00102\u001a\u00020\u000e2\u0006\u00103\u001a\u00020\u000e2\u0006\u00105\u001a\u0002042\u0006\u00106\u001a\u00020\u000e2\u0006\u00108\u001a\u0002072\u0006\u00109\u001a\u00020%2\u0006\u0010'\u001a\u00020%2\u0018\u0010:\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000f0\f2\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\u00152\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u000f0\u00152\u0012\u0010>\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u000f0\u00152\f\u0010?\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0011H\u0003¢\u0006\u0004\b@\u0010A\u001a?\u0010C\u001a\u00020\u000f2\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00050\u001b2\u0006\u0010\u001e\u001a\u00020\u000e2\u0018\u0010(\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000f0\fH\u0003¢\u0006\u0004\bC\u0010D\u001aG\u0010E\u001a\u00020\u000f2\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00050\u001b2\u0006\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u000e2\u0018\u0010(\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000f0\fH\u0003¢\u0006\u0004\bE\u0010F\u001aE\u0010I\u001a\u00020\u000f2\u0006\u0010-\u001a\u00020 2\u0006\u0010G\u001a\u00020\"2\u0006\u0010H\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020%2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0011H\u0003¢\u0006\u0004\bI\u0010J\u001aw\u0010Q\u001a\u00020\u000f2\u0018\u0010M\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020L0K0\u001b2\u0006\u0010N\u001a\u00020L2\u0006\u0010\u001f\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020%2\u0012\u0010O\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\u00152\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u00020\u000f0\u00152\f\u0010P\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0011H\u0003¢\u0006\u0004\bQ\u0010R\u001a©\u0001\u0010U\u001a\u00020\u000f*\u00020,2\u0006\u0010-\u001a\u00020 2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010.2\u0006\u00103\u001a\u00020\u000e2\u0006\u00105\u001a\u0002042\u0006\u00106\u001a\u00020\u000e2\u0006\u00108\u001a\u0002072\u0006\u00109\u001a\u00020%2\u0006\u0010'\u001a\u00020%2\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\u00152\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u000f0\u00152\u0012\u0010>\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u000f0\u00152\f\u0010?\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0011H\u0003¢\u0006\u0004\bS\u0010T\u001a»\u0001\u0010_\u001a\u00020\u000f*\u00020,2\u0006\u0010W\u001a\u00020V2\u001e\u0010X\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020<0\u001b0K0\u001b2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010.2\u0006\u00108\u001a\u0002072\u0006\u00109\u001a\u00020%2\u0006\u0010'\u001a\u00020%2\u0006\u0010Y\u001a\u00020\u000e2\u0006\u00103\u001a\u00020\u000e2\u0006\u0010[\u001a\u00020Z2\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\u00152\u0012\u0010>\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u000f0\u00152\u0012\u0010\\\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u000f0\u0015H\u0003¢\u0006\u0004\b]\u0010^\u001ae\u0010n\u001a\u00020\u000f2\u0006\u0010`\u001a\u00020\u00012\u0006\u0010a\u001a\u00020%2\u0006\u0010b\u001a\u00020%2\u0006\u0010d\u001a\u00020c2\b\b\u0002\u0010f\u001a\u00020e2\n\b\u0002\u0010g\u001a\u0004\u0018\u00010c2\b\b\u0002\u0010h\u001a\u00020%2\n\b\u0002\u0010j\u001a\u0004\u0018\u00010i2\b\b\u0002\u0010k\u001a\u00020%H\u0003¢\u0006\u0004\bl\u0010m\u001a\u0019\u0010q\u001a\u00020c2\b\u0010p\u001a\u0004\u0018\u00010oH\u0002¢\u0006\u0004\bq\u0010r\u001a\u000f\u0010s\u001a\u00020\u000fH\u0003¢\u0006\u0004\bs\u0010t\u001a\u001f\u0010w\u001a\u00020\u000f2\u0006\u0010u\u001a\u00020\u00012\u0006\u0010v\u001a\u00020\u0001H\u0003¢\u0006\u0004\bw\u0010x\u001a\u009d\u0001\u0010|\u001a\u00020\u000f2\u0006\u0010u\u001a\u00020\u00012\f\u0010y\u001a\b\u0012\u0004\u0012\u00020<0\u001b2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010.2\u0006\u00108\u001a\u0002072\u0006\u00109\u001a\u00020%2\b\b\u0002\u0010'\u001a\u00020%2\b\b\u0002\u00103\u001a\u00020\u000e2\u0014\b\u0002\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\u00152\u0012\u0010>\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u000f0\u00152\u0014\b\u0002\u0010\\\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u000f0\u0015H\u0003¢\u0006\u0004\bz\u0010{\"\u0014\u0010}\u001a\u00020\u00018\u0002X\u0082T¢\u0006\u0006\n\u0004\b}\u0010~¨\u0006\u0090\u0001²\u0006\f\u0010W\u001a\u00020V8\nX\u008a\u0084\u0002²\u0006\f\u0010!\u001a\u00020 8\nX\u008a\u0084\u0002²\u0006\u0018\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010.8\nX\u008a\u0084\u0002²\u0006\u000e\u0010\u007f\u001a\u00020\u000e8\n@\nX\u008a\u008e\u0002²\u0006\u0010\u0010\u0081\u0001\u001a\u00030\u0080\u00018\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0082\u0001\u001a\u00020\u000e8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0083\u0001\u001a\u00020\u000e8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0084\u0001\u001a\u00020\u000e8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010Y\u001a\u00020\u000e8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u00103\u001a\u00020\u000e8\n@\nX\u008a\u008e\u0002²\u0006\u0010\u0010\u0086\u0001\u001a\u00030\u0085\u00018\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0087\u0001\u001a\u00020%8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0088\u0001\u001a\u00020%8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0089\u0001\u001a\u00020\u000e8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u008a\u0001\u001a\u00020\u00018\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u008b\u0001\u001a\u00020\u00018\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u008c\u0001\u001a\u00020%8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010\u008e\u0001\u001a\u00030\u008d\u00018\nX\u008a\u0084\u0002²\u0006\u000e\u0010\u008f\u0001\u001a\u00030\u008d\u00018\nX\u008a\u0084\u0002"}, d2 = {"Lcom/arflix/tv/ui/screens/watchlist/WatchlistSourceItem;", "", "trackerProviderLabel", "(Lcom/arflix/tv/ui/screens/watchlist/WatchlistSourceItem;)Ljava/lang/String;", "providerLabel", "Lcom/arflix/tv/data/repository/HomeServerCatalogCandidate;", "asSidebarLibrary", "(Lcom/arflix/tv/ui/screens/watchlist/WatchlistSourceItem;Ljava/lang/String;)Lcom/arflix/tv/data/repository/HomeServerCatalogCandidate;", "Lcom/arflix/tv/ui/screens/watchlist/WatchlistViewModel;", "viewModel", "Lcom/arflix/tv/data/model/Profile;", "currentProfile", "Lkotlin/Function2;", "Lcom/arflix/tv/data/model/MediaType;", "", "Lx6/t0;", "onNavigateToDetails", "Lkotlin/Function0;", "onNavigateToHome", "onNavigateToSearch", "onNavigateToTv", "Lkotlin/Function1;", "onNavigateToSettings", "onSwitchProfile", "onBack", "WatchlistScreen", "(Lcom/arflix/tv/ui/screens/watchlist/WatchlistViewModel;Lcom/arflix/tv/data/model/Profile;Lr7/p;Lr7/a;Lr7/a;Lr7/a;Lr7/l;Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;II)V", "", "Lcom/arflix/tv/ui/screens/watchlist/LibraryProviderOption;", "providers", "selectedIndex", "focusedIndex", "Lcom/arflix/tv/ui/screens/watchlist/HomeLibraryUiState;", "libraryState", "Lcom/arflix/tv/ui/screens/watchlist/LibraryFilter;", "filters", "focusedFilterIndex", "", "showLibraryControls", "isMobile", "onSelect", "onFilterSelect", "ProviderTabs", "(Ljava/util/List;IILcom/arflix/tv/ui/screens/watchlist/HomeLibraryUiState;Ljava/util/List;IZZLr7/l;Lr7/l;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/foundation/layout/ColumnScope;", "state", "", "logoUrls", "libraries", "selectedLibraryIndex", "focusedLibraryIndex", "focusedItemIndex", "Landroidx/compose/foundation/lazy/grid/LazyGridState;", "gridState", "columns", "Landroidx/compose/ui/unit/Dp;", "cardWidth", "isLandscape", "onLibrarySelect", "onItemFocused", "Lcom/arflix/tv/data/model/MediaItem;", "onItemVisible", "onItemClick", "onLoadMore", "HomeLibraryContent-gZRGrFw", "(Landroidx/compose/foundation/layout/ColumnScope;Lcom/arflix/tv/ui/screens/watchlist/HomeLibraryUiState;Ljava/util/Map;Ljava/util/List;IIILandroidx/compose/foundation/lazy/grid/LazyGridState;IFZZLr7/p;Lr7/l;Lr7/l;Lr7/l;Lr7/a;Landroidx/compose/runtime/Composer;II)V", "HomeLibraryContent", "MobileLibrarySelector", "(Ljava/util/List;ILr7/p;Landroidx/compose/runtime/Composer;I)V", "LibrarySidebar", "(Ljava/util/List;IILr7/p;Landroidx/compose/runtime/Composer;I)V", "filter", "index", "LibraryFilterControl", "(Lcom/arflix/tv/ui/screens/watchlist/HomeLibraryUiState;Lcom/arflix/tv/ui/screens/watchlist/LibraryFilter;IIZLr7/a;Landroidx/compose/runtime/Composer;I)V", "Lx6/x;", "Lcom/arflix/tv/data/repository/HomeServerLibrarySort;", "options", "selectedSort", "onFocus", "onDismiss", "SortSelectionOverlay", "(Ljava/util/List;Lcom/arflix/tv/data/repository/HomeServerLibrarySort;IZLr7/l;Lr7/l;Lr7/a;Landroidx/compose/runtime/Composer;I)V", "LibraryResults-8u0NR3k", "(Landroidx/compose/foundation/layout/ColumnScope;Lcom/arflix/tv/ui/screens/watchlist/HomeLibraryUiState;Ljava/util/Map;ILandroidx/compose/foundation/lazy/grid/LazyGridState;IFZZLr7/l;Lr7/l;Lr7/l;Lr7/a;Landroidx/compose/runtime/Composer;II)V", "LibraryResults", "Lcom/arflix/tv/ui/screens/watchlist/WatchlistUiState;", "uiState", "sections", "focusedSectionIndex", "Landroidx/compose/foundation/lazy/LazyListState;", "listState", "onItemLongPress", "WatchlistContent-05tvjtU", "(Landroidx/compose/foundation/layout/ColumnScope;Lcom/arflix/tv/ui/screens/watchlist/WatchlistUiState;Ljava/util/List;Ljava/util/Map;FZZIILandroidx/compose/foundation/lazy/LazyListState;Lr7/l;Lr7/l;Lr7/l;Landroidx/compose/runtime/Composer;II)V", "WatchlistContent", "label", "selected", "focused", "Landroidx/compose/ui/graphics/Color;", "accent", "Landroidx/compose/ui/Modifier;", "modifier", "leading", "compact", "Landroidx/compose/ui/graphics/vector/ImageVector;", "icon", "iconOnly", "SelectablePill-dR3JOV0", "(Ljava/lang/String;ZZJLandroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Color;ZLandroidx/compose/ui/graphics/vector/ImageVector;ZLandroidx/compose/runtime/Composer;II)V", "SelectablePill", "Lcom/arflix/tv/data/repository/HomeServerKind;", "provider", "providerAccent", "(Lcom/arflix/tv/data/repository/HomeServerKind;)J", "CenteredLoading", "(Landroidx/compose/runtime/Composer;I)V", LinkHeader.Parameters.Title, MediaTrack.ROLE_SUBTITLE, "LibraryMessage", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "items", "WatchlistItemsSection-7vwJh_E", "(Ljava/lang/String;Ljava/util/List;Ljava/util/Map;FZZILr7/l;Lr7/l;Lr7/l;Landroidx/compose/runtime/Composer;II)V", "WatchlistItemsSection", "WATCHLIST_PROVIDER_ID", "Ljava/lang/String;", "sidebarFocusIndex", "Lcom/arflix/tv/ui/screens/watchlist/WatchlistFocusZone;", "focusZone", "providerFocusIndex", "libraryFocusIndex", "filterFocusIndex", "", "enterKeyDownTimeMs", "showSearchModal", "showSortMenu", "sortFocusIndex", "selectedProviderId", "trackerSearchQuery", "expanded", "", "contentAlpha", "scale", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class WatchlistScreenKt {
    private static final String WATCHLIST_PROVIDER_ID = "provider:watchlist";

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[HomeServerKind.values().length];
            try {
                iArr[HomeServerKind.PLEX.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[HomeServerKind.JELLYFIN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[HomeServerKind.EMBY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[HomeServerKind.UNKNOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ToastType.values().length];
            try {
                iArr2[ToastType.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[ToastType.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[ToastType.INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[HomeServerLibrarySort.values().length];
            try {
                iArr3[HomeServerLibrarySort.RECENTLY_ADDED.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr3[HomeServerLibrarySort.RATING.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr3[HomeServerLibrarySort.TITLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    private static final void CenteredLoading(Composer composer, int i10) {
        Composer composerStartRestartGroup = composer.startRestartGroup(481331182);
        if (i10 == 0 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(481331182, i10, -1, "com.arflix.tv.ui.screens.watchlist.CenteredLoading (WatchlistScreen.kt:1387)");
            }
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 1, null);
            Alignment center = Alignment.INSTANCE.getCenter();
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierFillMaxSize$default);
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
            p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            LoadingIndicatorKt.m6162LoadingIndicator5_Ve2oQ(null, Dp.m5678constructorimpl(56), ColorKt.getPink(), 0.0f, composerStartRestartGroup, 432, 9);
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new com.arflix.tv.j(i10, 8));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CenteredLoading$lambda$1(int i10, Composer composer, int i11) {
        CenteredLoading(composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:160:0x0311  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0345  */
    /* JADX INFO: renamed from: HomeLibraryContent-gZRGrFw, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m6455HomeLibraryContentgZRGrFw(final androidx.compose.foundation.layout.ColumnScope r36, final com.arflix.tv.ui.screens.watchlist.HomeLibraryUiState r37, final java.util.Map<java.lang.String, java.lang.String> r38, final java.util.List<com.arflix.tv.data.repository.HomeServerCatalogCandidate> r39, final int r40, final int r41, final int r42, final androidx.compose.foundation.lazy.grid.LazyGridState r43, final int r44, final float r45, final boolean r46, final boolean r47, final r7.p<? super java.lang.Integer, ? super com.arflix.tv.data.repository.HomeServerCatalogCandidate, x6.t0> r48, final r7.l<? super java.lang.Integer, x6.t0> r49, final r7.l<? super com.arflix.tv.data.model.MediaItem, x6.t0> r50, final r7.l<? super com.arflix.tv.data.model.MediaItem, x6.t0> r51, final r7.a<x6.t0> r52, androidx.compose.runtime.Composer r53, final int r54, final int r55) {
        /*
            Method dump skipped, instruction units count: 981
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt.m6455HomeLibraryContentgZRGrFw(androidx.compose.foundation.layout.ColumnScope, com.arflix.tv.ui.screens.watchlist.HomeLibraryUiState, java.util.Map, java.util.List, int, int, int, androidx.compose.foundation.lazy.grid.LazyGridState, int, float, boolean, boolean, r7.p, r7.l, r7.l, r7.l, r7.a, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeLibraryContent_gZRGrFw$lambda$1(ColumnScope columnScope, HomeLibraryUiState homeLibraryUiState, Map map, List list, int i10, int i11, int i12, LazyGridState lazyGridState, int i13, float f10, boolean z, boolean z5, p pVar, r7.l lVar, r7.l lVar2, r7.l lVar3, r7.a aVar, int i14, int i15, Composer composer, int i16) {
        m6455HomeLibraryContentgZRGrFw(columnScope, homeLibraryUiState, map, list, i10, i11, i12, lazyGridState, i13, f10, z, z5, pVar, lVar, lVar2, lVar3, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i14 | 1), RecomposeScopeImplKt.updateChangedFlags(i15));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LibraryFilterControl(HomeLibraryUiState homeLibraryUiState, final LibraryFilter libraryFilter, final int i10, final int i11, final boolean z, final r7.a<t0> aVar, Composer composer, final int i12) {
        HomeLibraryUiState homeLibraryUiState2;
        int i13;
        LibraryFilter libraryFilter2;
        String label;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-686577306);
        if ((i12 & 6) == 0) {
            homeLibraryUiState2 = homeLibraryUiState;
            i13 = (composerStartRestartGroup.changed(homeLibraryUiState2) ? 4 : 2) | i12;
        } else {
            homeLibraryUiState2 = homeLibraryUiState;
            i13 = i12;
        }
        if ((i12 & 48) == 0) {
            libraryFilter2 = libraryFilter;
            i13 |= composerStartRestartGroup.changed(libraryFilter2) ? 32 : 16;
        } else {
            libraryFilter2 = libraryFilter;
        }
        if ((i12 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i13 |= composerStartRestartGroup.changed(i10) ? 256 : 128;
        }
        if ((i12 & 3072) == 0) {
            i13 |= composerStartRestartGroup.changed(i11) ? 2048 : 1024;
        }
        if ((i12 & 24576) == 0) {
            i13 |= composerStartRestartGroup.changed(z) ? 16384 : 8192;
        }
        if ((196608 & i12) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(aVar) ? 131072 : 65536;
        }
        if ((74899 & i13) == 74898 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-686577306, i13, -1, "com.arflix.tv.ui.screens.watchlist.LibraryFilterControl (WatchlistScreen.kt:1033)");
            }
            boolean z5 = libraryFilter2.isSort() || (libraryFilter2.isSearch() && !kotlin.text.o.h0(homeLibraryUiState2.getSearchQuery()));
            if (libraryFilter.isSort()) {
                composerStartRestartGroup.startReplaceGroup(-1189884485);
                int i14 = WhenMappings.$EnumSwitchMapping$2[homeLibraryUiState2.getSort().ordinal()];
                if (i14 == 1) {
                    composerStartRestartGroup.startReplaceGroup(1624186714);
                    label = AppLanguageKt.tr("Recently added", composerStartRestartGroup, 6);
                    composerStartRestartGroup.endReplaceGroup();
                } else if (i14 == 2) {
                    composerStartRestartGroup.startReplaceGroup(1624188793);
                    label = AppLanguageKt.tr("Highest rated", composerStartRestartGroup, 6);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    if (i14 != 3) {
                        composerStartRestartGroup.startReplaceGroup(1624184615);
                        composerStartRestartGroup.endReplaceGroup();
                        throw new NoWhenBranchMatchedException();
                    }
                    composerStartRestartGroup.startReplaceGroup(1624190805);
                    label = AppLanguageKt.tr("Title A-Z", composerStartRestartGroup, 6);
                    composerStartRestartGroup.endReplaceGroup();
                }
                composerStartRestartGroup.endReplaceGroup();
            } else if (!libraryFilter.isSearch() || kotlin.text.o.h0(homeLibraryUiState2.getSearchQuery())) {
                composerStartRestartGroup.startReplaceGroup(1624194891);
                composerStartRestartGroup.endReplaceGroup();
                label = libraryFilter.getLabel();
            } else {
                composerStartRestartGroup.startReplaceGroup(1624193777);
                composerStartRestartGroup.endReplaceGroup();
                label = homeLibraryUiState2.getSearchQuery();
            }
            boolean z10 = i10 == i11;
            long jM3509getWhite0d7_KjU = Color.INSTANCE.m3509getWhite0d7_KjU();
            boolean iconOnly = libraryFilter.getIconOnly();
            ImageVector sort = libraryFilter.isSort() ? SortKt.getSort(Icons.AutoMirrored.Outlined.INSTANCE) : libraryFilter.isSearch() ? SearchKt.getSearch(Icons.Outlined.INSTANCE) : libraryFilter.isRefresh() ? RefreshKt.getRefresh(Icons.Outlined.INSTANCE) : null;
            Modifier.Companion companion = Modifier.INSTANCE;
            boolean z11 = (i13 & 458752) == 131072;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z11 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new f0(9, aVar);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composer2 = composerStartRestartGroup;
            m6457SelectablePilldR3JOV0(label, z5, z10, jM3509getWhite0d7_KjU, ClickableKt.m214clickableXHw0xAI$default(companion, z, null, null, (r7.a) objRememberedValue, 6, null), null, true, sort, iconOnly, composer2, 1575936, 32);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final HomeLibraryUiState homeLibraryUiState3 = homeLibraryUiState2;
            scopeUpdateScopeEndRestartGroup.updateScope(new p() { // from class: com.arflix.tv.ui.screens.watchlist.k
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return WatchlistScreenKt.LibraryFilterControl$lambda$1(homeLibraryUiState3, libraryFilter, i10, i11, z, aVar, i12, (Composer) obj, iIntValue);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 LibraryFilterControl$lambda$0$0(r7.a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 LibraryFilterControl$lambda$1(HomeLibraryUiState homeLibraryUiState, LibraryFilter libraryFilter, int i10, int i11, boolean z, r7.a aVar, int i12, Composer composer, int i13) {
        LibraryFilterControl(homeLibraryUiState, libraryFilter, i10, i11, z, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i12 | 1));
        return t0.f22605a;
    }

    private static final void LibraryMessage(String str, String str2, Composer composer, int i10) {
        int i11;
        Composer composer2;
        String str3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-274458274);
        if ((i10 & 6) == 0) {
            i11 = i10 | (composerStartRestartGroup.changed(str) ? 4 : 2);
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(str2) ? 32 : 16;
        }
        if ((i11 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            str3 = str2;
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-274458274, i11, -1, "com.arflix.tv.ui.screens.watchlist.LibraryMessage (WatchlistScreen.kt:1395)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), 0.0f, 1, null);
            Alignment.Companion companion2 = Alignment.INSTANCE;
            Alignment center = companion2.getCenter();
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierFillMaxSize$default);
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
            p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            Alignment.Horizontal centerHorizontally = companion2.getCenterHorizontally();
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyF = androidx.compose.material3.d.f(Arrangement.INSTANCE, centerHorizontally, composerStartRestartGroup, 48, -1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(companion);
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2991constructorimpl2 = Updater.m2991constructorimpl(composerStartRestartGroup);
            p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyF, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
            composer2 = composerStartRestartGroup;
            TextKt.m6020Text4IGK_g(str, null, ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arflixTypography.getSectionTitle(), composer2, (i11 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            SpacerKt.Spacer(SizeKt.m564height3ABfNKs(companion, Dp.m5678constructorimpl(8)), composer2, 6);
            str3 = str2;
            TextKt.m6020Text4IGK_g(str3, null, Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.45f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arflixTypography.getCaption(), composer2, ((i11 >> 3) & 14) | RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            if (androidx.compose.foundation.c.A(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new j2(str, str3, i10, 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 LibraryMessage$lambda$1(String str, String str2, int i10, Composer composer, int i11) {
        LibraryMessage(str, str2, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:248:0x0510  */
    /* JADX INFO: renamed from: LibraryResults-8u0NR3k, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m6456LibraryResults8u0NR3k(androidx.compose.foundation.layout.ColumnScope r34, com.arflix.tv.ui.screens.watchlist.HomeLibraryUiState r35, java.util.Map<java.lang.String, java.lang.String> r36, int r37, androidx.compose.foundation.lazy.grid.LazyGridState r38, int r39, float r40, boolean r41, boolean r42, r7.l<? super java.lang.Integer, x6.t0> r43, r7.l<? super com.arflix.tv.data.model.MediaItem, x6.t0> r44, r7.l<? super com.arflix.tv.data.model.MediaItem, x6.t0> r45, r7.a<x6.t0> r46, androidx.compose.runtime.Composer r47, int r48, int r49) {
        /*
            Method dump skipped, instruction units count: 1345
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt.m6456LibraryResults8u0NR3k(androidx.compose.foundation.layout.ColumnScope, com.arflix.tv.ui.screens.watchlist.HomeLibraryUiState, java.util.Map, int, androidx.compose.foundation.lazy.grid.LazyGridState, int, float, boolean, boolean, r7.l, r7.l, r7.l, r7.a, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final float LibraryResults_8u0NR3k$lambda$0(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 LibraryResults_8u0NR3k$lambda$1$0$0(State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setAlpha(LibraryResults_8u0NR3k$lambda$0(state));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 LibraryResults_8u0NR3k$lambda$1$1$0(HomeLibraryUiState homeLibraryUiState, r7.l lVar, float f10, boolean z, Map map, int i10, r7.l lVar2, r7.l lVar3, LazyGridScope lazyGridScope) {
        List<MediaItem> items = homeLibraryUiState.getItems();
        lazyGridScope.items(items.size(), new WatchlistScreenKt$LibraryResults_8u0NR3k$lambda$1$1$0$$inlined$itemsIndexed$default$1(new n(6), items), null, new WatchlistScreenKt$LibraryResults_8u0NR3k$lambda$1$1$0$$inlined$itemsIndexed$default$3(items), ComposableLambdaKt.composableLambdaInstance(1229287273, true, new WatchlistScreenKt$LibraryResults_8u0NR3k$lambda$1$1$0$$inlined$itemsIndexed$default$4(items, lVar, f10, z, map, i10, lVar2, lVar3)));
        if (homeLibraryUiState.isLoadingMore()) {
            LazyGridScope.CC.a(lazyGridScope, "library-loading-more", new j(1), null, ComposableSingletons$WatchlistScreenKt.INSTANCE.getLambda$1983092834$app_playRelease(), 4, null);
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object LibraryResults_8u0NR3k$lambda$1$1$0$0(int i10, MediaItem mediaItem) {
        return WatchlistViewModelKt.watchlistItemKey(mediaItem, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final GridItemSpan LibraryResults_8u0NR3k$lambda$1$1$0$3(LazyGridItemSpanScope lazyGridItemSpanScope) {
        return GridItemSpan.m644boximpl(LazyGridSpanKt.GridItemSpan(lazyGridItemSpanScope.getMaxLineSpan()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 LibraryResults_8u0NR3k$lambda$3(ColumnScope columnScope, HomeLibraryUiState homeLibraryUiState, Map map, int i10, LazyGridState lazyGridState, int i11, float f10, boolean z, boolean z5, r7.l lVar, r7.l lVar2, r7.l lVar3, r7.a aVar, int i12, int i13, Composer composer, int i14) {
        m6456LibraryResults8u0NR3k(columnScope, homeLibraryUiState, map, i10, lazyGridState, i11, f10, z, z5, lVar, lVar2, lVar3, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i12 | 1), RecomposeScopeImplKt.updateChangedFlags(i13));
        return t0.f22605a;
    }

    private static final void LibrarySidebar(final List<HomeServerCatalogCandidate> list, final int i10, final int i11, final p<? super Integer, ? super HomeServerCatalogCandidate, t0> pVar, Composer composer, final int i12) {
        int i13;
        String serverName;
        Composer composer2;
        String serverName2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1134220939);
        if ((i12 & 6) == 0) {
            i13 = (composerStartRestartGroup.changed(list) ? 4 : 2) | i12;
        } else {
            i13 = i12;
        }
        if ((i12 & 48) == 0) {
            i13 |= composerStartRestartGroup.changed(i10) ? 32 : 16;
        }
        if ((i12 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i13 |= composerStartRestartGroup.changed(i11) ? 256 : 128;
        }
        if ((i12 & 3072) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(pVar) ? 2048 : 1024;
        }
        if ((i13 & 1171) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1134220939, i13, -1, "com.arflix.tv.ui.screens.watchlist.LibrarySidebar (WatchlistScreen.kt:946)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            float f10 = 24;
            Modifier modifierM533paddingqDBjuR0$default = PaddingKt.m533paddingqDBjuR0$default(SizeKt.fillMaxHeight$default(SizeKt.m583width3ABfNKs(companion, Dp.m5678constructorimpl(184)), 0.0f, 1, null), Dp.m5678constructorimpl(f10), 0.0f, 0.0f, Dp.m5678constructorimpl(f10), 6, null);
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            Arrangement arrangement = Arrangement.INSTANCE;
            MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(Alignment.INSTANCE, arrangement.getTop(), composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM533paddingqDBjuR0$default);
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
            p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyK, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            HomeServerCatalogCandidate homeServerCatalogCandidate = (HomeServerCatalogCandidate) x.p0(i10, list);
            if (homeServerCatalogCandidate == null || (serverName2 = homeServerCatalogCandidate.getServerName()) == null) {
                HomeServerCatalogCandidate homeServerCatalogCandidate2 = (HomeServerCatalogCandidate) x.o0(list);
                serverName = homeServerCatalogCandidate2 != null ? homeServerCatalogCandidate2.getServerName() : null;
            } else {
                serverName = serverName2;
            }
            if (serverName == null) {
                composerStartRestartGroup.startReplaceGroup(760012562);
                serverName = AppLanguageKt.tr("Home server", composerStartRestartGroup, 6);
            } else {
                composerStartRestartGroup.startReplaceGroup(760009059);
            }
            composerStartRestartGroup.endReplaceGroup();
            ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
            TextStyle body = arflixTypography.getBody();
            long sp = TextUnitKt.getSp(14);
            FontWeight.Companion companion3 = FontWeight.INSTANCE;
            TextStyle textStyleM5205copyp1EtxEg$default = TextStyle.m5205copyp1EtxEg$default(body, 0L, sp, companion3.getSemiBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null);
            Color.Companion companion4 = Color.INSTANCE;
            float f11 = 10;
            TextKt.m6020Text4IGK_g(serverName, PaddingKt.m533paddingqDBjuR0$default(companion, Dp.m5678constructorimpl(f11), Dp.m5678constructorimpl(6), Dp.m5678constructorimpl(8), 0.0f, 8, null), Color.m3471copywmQWz5c$default(companion4.m3509getWhite0d7_KjU(), 0.92f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, TextUnitKt.getSp(16), TextOverflow.INSTANCE.m5632getEllipsisgIe3tQ8(), false, 2, 0, null, textStyleM5205copyp1EtxEg$default, composerStartRestartGroup, 432, 3126, 54264);
            TextKt.m6020Text4IGK_g(AppLanguageKt.tr("Libraries", composerStartRestartGroup, 6), PaddingKt.m533paddingqDBjuR0$default(companion, Dp.m5678constructorimpl(f11), Dp.m5678constructorimpl(2), 0.0f, Dp.m5678constructorimpl(7), 4, null), Color.m3471copywmQWz5c$default(companion4.m3509getWhite0d7_KjU(), 0.4f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCaption(), 0L, TextUnitKt.getSp(10), companion3.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composerStartRestartGroup, 432, 0, 65528);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = arrangement.m441spacedBy0680j_4(Dp.m5678constructorimpl(5));
            boolean z = ((i13 & 14) == 4) | ((i13 & 112) == 32) | ((i13 & 896) == 256) | ((i13 & 7168) == 2048);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                c0 c0Var = new c0(list, i10, i11, pVar, 3);
                composerStartRestartGroup.updateRememberedValue(c0Var);
                objRememberedValue = c0Var;
            }
            composer2 = composerStartRestartGroup;
            LazyDslKt.LazyColumn(null, null, null, false, horizontalOrVerticalM441spacedBy0680j_4, null, null, false, (r7.l) objRememberedValue, composer2, 24576, 239);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new p() { // from class: com.arflix.tv.ui.screens.watchlist.f
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return WatchlistScreenKt.LibrarySidebar$lambda$1(list, i10, i11, pVar, i12, (Composer) obj, iIntValue);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 LibrarySidebar$lambda$0$0$0(List list, int i10, int i11, p pVar, LazyListScope lazyListScope) {
        lazyListScope.items(list.size(), new WatchlistScreenKt$LibrarySidebar$lambda$0$0$0$$inlined$itemsIndexed$default$1(new n(7), list), new WatchlistScreenKt$LibrarySidebar$lambda$0$0$0$$inlined$itemsIndexed$default$2(list), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new WatchlistScreenKt$LibrarySidebar$lambda$0$0$0$$inlined$itemsIndexed$default$3(list, i10, i11, pVar)));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object LibrarySidebar$lambda$0$0$0$0(int i10, HomeServerCatalogCandidate homeServerCatalogCandidate) {
        return homeServerCatalogCandidate.getSourceRef();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 LibrarySidebar$lambda$1(List list, int i10, int i11, p pVar, int i12, Composer composer, int i13) {
        LibrarySidebar(list, i10, i11, pVar, composer, RecomposeScopeImplKt.updateChangedFlags(i12 | 1));
        return t0.f22605a;
    }

    private static final void MobileLibrarySelector(List<HomeServerCatalogCandidate> list, final int i10, final p<? super Integer, ? super HomeServerCatalogCandidate, t0> pVar, Composer composer, int i11) {
        int i12;
        Composer composer2;
        final List<HomeServerCatalogCandidate> list2 = list;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1143000115);
        if ((i11 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(list2) ? 4 : 2) | i11;
        } else {
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(i10) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar) ? 256 : 128;
        }
        if ((i12 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1143000115, i12, -1, "com.arflix.tv.ui.screens.watchlist.MobileLibrarySelector (WatchlistScreen.kt:871)");
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState = (MutableState) objRememberedValue;
            HomeServerCatalogCandidate homeServerCatalogCandidate = (HomeServerCatalogCandidate) x.p0(i10, list2);
            if (homeServerCatalogCandidate == null) {
                homeServerCatalogCandidate = (HomeServerCatalogCandidate) x.m0(list2);
            }
            HomeServerCatalogCandidate homeServerCatalogCandidate2 = homeServerCatalogCandidate;
            Modifier.Companion companion2 = Modifier.INSTANCE;
            float f10 = 1;
            Modifier modifierH = a2.h(f10, SizeKt.fillMaxWidth$default(companion2, 0.0f, 1, null), Dp.m5678constructorimpl(24), composerStartRestartGroup, 733328855);
            Alignment.Companion companion3 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyD = a0.c.d(companion3, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion4.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierH);
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
            p pVarU = a0.c.u(companion4, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            Modifier modifierM564height3ABfNKs = SizeKt.m564height3ABfNKs(SizeKt.fillMaxWidth$default(companion2, 0.0f, 1, null), Dp.m5678constructorimpl(48));
            Color.Companion companion5 = Color.INSTANCE;
            float f11 = 6;
            Modifier modifierK = com.arflix.tv.data.repository.g.k(f11, com.arflix.tv.data.repository.g.l(f11, modifierM564height3ABfNKs, Color.m3471copywmQWz5c$default(companion5.m3509getWhite0d7_KjU(), 0.08f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(f10), Color.m3471copywmQWz5c$default(companion5.m3509getWhite0d7_KjU(), 0.16f, 0.0f, 0.0f, 0.0f, 14, null));
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new com.arflix.tv.ui.screens.login.f(mutableState, 11);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            Modifier modifierM531paddingVpY3zN4$default = PaddingKt.m531paddingVpY3zN4$default(ClickableKt.m214clickableXHw0xAI$default(modifierK, false, null, null, (r7.a) objRememberedValue2, 7, null), Dp.m5678constructorimpl(14), 0.0f, 2, null);
            Alignment.Vertical centerVertically = companion3.getCenterVertically();
            composerStartRestartGroup.startReplaceableGroup(693286680);
            Arrangement arrangement = Arrangement.INSTANCE;
            MeasurePolicy measurePolicyG = androidx.compose.material3.d.g(arrangement, centerVertically, composerStartRestartGroup, 48, -1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion4.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM531paddingVpY3zN4$default);
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2991constructorimpl2 = Updater.m2991constructorimpl(composerStartRestartGroup);
            p pVarU2 = a0.c.u(companion4, composerM2991constructorimpl2, measurePolicyG, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            BoxKt.Box(com.arflix.tv.data.repository.g.l(2, SizeKt.m578size3ABfNKs(companion2, Dp.m5678constructorimpl(8)), Color.m3471copywmQWz5c$default(companion5.m3509getWhite0d7_KjU(), 0.7f, 0.0f, 0.0f, 0.0f, 14, null)), composerStartRestartGroup, 0);
            Modifier modifierA = androidx.compose.foundation.layout.e.a(rowScopeInstance, PaddingKt.m533paddingqDBjuR0$default(companion2, Dp.m5678constructorimpl(10), 0.0f, 0.0f, 0.0f, 14, null), 1.0f, false, 2, null);
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(companion3, arrangement.getTop(), composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor3 = companion4.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierA);
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor3);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2991constructorimpl3 = Updater.m2991constructorimpl(composerStartRestartGroup);
            p pVarU3 = a0.c.u(companion4, composerM2991constructorimpl3, measurePolicyK, composerM2991constructorimpl3, currentCompositionLocalMap3);
            if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf3, composerStartRestartGroup, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            String collectionName = homeServerCatalogCandidate2.getCollectionName();
            if (kotlin.text.o.h0(collectionName)) {
                collectionName = homeServerCatalogCandidate2.getTitle();
            }
            String str = collectionName;
            ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
            TextStyle textStyleM5205copyp1EtxEg$default = TextStyle.m5205copyp1EtxEg$default(arflixTypography.getBody(), 0L, TextUnitKt.getSp(14), FontWeight.INSTANCE.getSemiBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null);
            long jM3509getWhite0d7_KjU = companion5.m3509getWhite0d7_KjU();
            TextOverflow.Companion companion6 = TextOverflow.INSTANCE;
            TextKt.m6020Text4IGK_g(str, null, jM3509getWhite0d7_KjU, 0L, null, null, null, 0L, null, null, 0L, companion6.m5632getEllipsisgIe3tQ8(), false, 1, 0, null, textStyleM5205copyp1EtxEg$default, composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 3120, 55290);
            TextKt.m6020Text4IGK_g(homeServerCatalogCandidate2.getServerName(), null, Color.m3471copywmQWz5c$default(companion5.m3509getWhite0d7_KjU(), 0.45f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, companion6.m5632getEllipsisgIe3tQ8(), false, 1, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCaption(), 0L, TextUnitKt.getSp(10), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 3120, 55290);
            androidx.compose.foundation.c.y(composerStartRestartGroup);
            IconKt.m1610Iconww6aTOc(ArrowDropDownKt.getArrowDropDown(Icons.Outlined.INSTANCE), AppLanguageKt.tr("Choose library", composerStartRestartGroup, 6), (Modifier) null, Color.m3471copywmQWz5c$default(companion5.m3509getWhite0d7_KjU(), 0.72f, 0.0f, 0.0f, 0.0f, 14, null), composerStartRestartGroup, 3072, 4);
            androidx.compose.foundation.c.y(composerStartRestartGroup);
            boolean zMobileLibrarySelector$lambda$1 = MobileLibrarySelector$lambda$1(mutableState);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = new com.arflix.tv.ui.screens.login.f(mutableState, 12);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            list2 = list;
            composer2 = composerStartRestartGroup;
            AndroidMenu_androidKt.m1221DropdownMenu4kj_NE(zMobileLibrarySelector$lambda$1, (r7.a) objRememberedValue3, SizeKt.m583width3ABfNKs(BackgroundKt.m179backgroundbw27NRU$default(companion2, androidx.compose.ui.graphics.ColorKt.Color(4279572249L), null, 2, null), Dp.m5678constructorimpl(280)), 0L, null, null, ComposableLambdaKt.rememberComposableLambda(-592889612, true, new q() { // from class: com.arflix.tv.ui.screens.watchlist.b
                @Override // r7.q
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    int iIntValue = ((Integer) obj3).intValue();
                    return WatchlistScreenKt.MobileLibrarySelector$lambda$3$3(list2, pVar, i10, mutableState, (ColumnScope) obj, (Composer) obj2, iIntValue);
                }
            }, composerStartRestartGroup, 54), composer2, 1573296, 56);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new p1(list2, i10, pVar, i11, 8));
        }
    }

    private static final boolean MobileLibrarySelector$lambda$1(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void MobileLibrarySelector$lambda$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 MobileLibrarySelector$lambda$3$0$0(MutableState mutableState) {
        MobileLibrarySelector$lambda$2(mutableState, true);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 MobileLibrarySelector$lambda$3$2$0(MutableState mutableState) {
        MobileLibrarySelector$lambda$2(mutableState, false);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 MobileLibrarySelector$lambda$3$3(List list, p pVar, int i10, MutableState mutableState, ColumnScope columnScope, Composer composer, int i11) {
        Composer composer2 = composer;
        if ((i11 & 17) == 16 && composer2.getSkipping()) {
            composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-592889612, i11, -1, "com.arflix.tv.ui.screens.watchlist.MobileLibrarySelector.<anonymous>.<anonymous> (WatchlistScreen.kt:913)");
            }
            int i12 = 0;
            for (Object obj : list) {
                int i13 = i12 + 1;
                if (i12 < 0) {
                    t7.a.Q();
                    throw null;
                }
                HomeServerCatalogCandidate homeServerCatalogCandidate = (HomeServerCatalogCandidate) obj;
                ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-718738823, true, new com.arflix.tv.ui.screens.settings.telegram.f(i12, i10, 3, homeServerCatalogCandidate), composer2, 54);
                boolean zChanged = composer2.changed(pVar) | composer2.changed(i12) | composer2.changed(homeServerCatalogCandidate);
                Object objRememberedValue = composer2.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    a0 a0Var = new a0(pVar, i12, homeServerCatalogCandidate, mutableState, 1);
                    composer2.updateRememberedValue(a0Var);
                    objRememberedValue = a0Var;
                }
                AndroidMenu_androidKt.DropdownMenuItem(composableLambdaRememberComposableLambda, (r7.a) objRememberedValue, null, ComposableSingletons$WatchlistScreenKt.INSTANCE.getLambda$1087281494$app_playRelease(), null, false, null, null, null, composer2, 3078, 500);
                composer2 = composer;
                i12 = i13;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 MobileLibrarySelector$lambda$3$3$0$0(HomeServerCatalogCandidate homeServerCatalogCandidate, int i10, int i11, Composer composer, int i12) {
        if ((i12 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-718738823, i12, -1, "com.arflix.tv.ui.screens.watchlist.MobileLibrarySelector.<anonymous>.<anonymous>.<anonymous>.<anonymous> (WatchlistScreen.kt:916)");
            }
            String collectionName = homeServerCatalogCandidate.getCollectionName();
            if (kotlin.text.o.h0(collectionName)) {
                collectionName = homeServerCatalogCandidate.getTitle();
            }
            TextKt.m6020Text4IGK_g(collectionName, null, i10 == i11 ? Color.INSTANCE.m3509getWhite0d7_KjU() : Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.72f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m5632getEllipsisgIe3tQ8(), false, 1, 0, null, null, composer, 0, 3120, 120826);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 MobileLibrarySelector$lambda$3$3$0$1$0(p pVar, int i10, HomeServerCatalogCandidate homeServerCatalogCandidate, MutableState mutableState) {
        MobileLibrarySelector$lambda$2(mutableState, false);
        pVar.invoke(Integer.valueOf(i10), homeServerCatalogCandidate);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 MobileLibrarySelector$lambda$4(List list, int i10, p pVar, int i11, Composer composer, int i12) {
        MobileLibrarySelector(list, i10, pVar, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1));
        return t0.f22605a;
    }

    private static final void ProviderTabs(final List<LibraryProviderOption> list, final int i10, int i11, HomeLibraryUiState homeLibraryUiState, List<LibraryFilter> list2, int i12, final boolean z, final boolean z5, final r7.l<? super Integer, t0> lVar, final r7.l<? super Integer, t0> lVar2, Composer composer, int i13) {
        int i14;
        int i15;
        final HomeLibraryUiState homeLibraryUiState2;
        List<LibraryFilter> list3;
        int i16;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1060484257);
        if ((i13 & 6) == 0) {
            i14 = ((i13 & 8) == 0 ? composerStartRestartGroup.changed(list) : composerStartRestartGroup.changedInstance(list) ? 4 : 2) | i13;
        } else {
            i14 = i13;
        }
        if ((i13 & 48) == 0) {
            i14 |= composerStartRestartGroup.changed(i10) ? 32 : 16;
        }
        if ((i13 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i15 = i11;
            i14 |= composerStartRestartGroup.changed(i15) ? 256 : 128;
        } else {
            i15 = i11;
        }
        if ((i13 & 3072) == 0) {
            homeLibraryUiState2 = homeLibraryUiState;
            i14 |= composerStartRestartGroup.changed(homeLibraryUiState2) ? 2048 : 1024;
        } else {
            homeLibraryUiState2 = homeLibraryUiState;
        }
        if ((i13 & 24576) == 0) {
            list3 = list2;
            i14 |= composerStartRestartGroup.changed(list3) ? 16384 : 8192;
        } else {
            list3 = list2;
        }
        if ((196608 & i13) == 0) {
            i16 = i12;
            i14 |= composerStartRestartGroup.changed(i16) ? 131072 : 65536;
        } else {
            i16 = i12;
        }
        if ((i13 & 1572864) == 0) {
            i14 |= composerStartRestartGroup.changed(z) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((i13 & 12582912) == 0) {
            i14 |= composerStartRestartGroup.changed(z5) ? 8388608 : 4194304;
        }
        if ((i13 & 100663296) == 0) {
            i14 |= composerStartRestartGroup.changedInstance(lVar) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        }
        if ((i13 & 805306368) == 0) {
            i14 |= composerStartRestartGroup.changedInstance(lVar2) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        if ((i14 & 306783379) == 306783378 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1060484257, i14, -1, "com.arflix.tv.ui.screens.watchlist.ProviderTabs (WatchlistScreen.kt:754)");
            }
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            PaddingValues paddingValuesM523PaddingValuesYgX7TsA = PaddingKt.m523PaddingValuesYgX7TsA(Dp.m5678constructorimpl(24), Dp.m5678constructorimpl(1));
            Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = Arrangement.INSTANCE.m441spacedBy0680j_4(Dp.m5678constructorimpl(7));
            boolean z10 = ((i14 & 14) == 4 || ((i14 & 8) != 0 && composerStartRestartGroup.changedInstance(list))) | ((i14 & 112) == 32) | ((i14 & 896) == 256) | ((29360128 & i14) == 8388608) | ((234881024 & i14) == 67108864) | ((3670016 & i14) == 1048576) | ((57344 & i14) == 16384) | ((i14 & 7168) == 2048) | ((458752 & i14) == 131072) | ((i14 & 1879048192) == 536870912);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z10 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                final int i17 = i15;
                final List<LibraryFilter> list4 = list3;
                final int i18 = i16;
                r7.l lVar3 = new r7.l() { // from class: com.arflix.tv.ui.screens.watchlist.o
                    @Override // r7.l
                    public final Object invoke(Object obj) {
                        return WatchlistScreenKt.ProviderTabs$lambda$0$0(list, z, list4, i10, i17, z5, lVar, homeLibraryUiState2, i18, lVar2, (LazyListScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(lVar3);
                objRememberedValue = lVar3;
            }
            composer2 = composerStartRestartGroup;
            LazyDslKt.LazyRow(modifierFillMaxWidth$default, null, paddingValuesM523PaddingValuesYgX7TsA, false, horizontalOrVerticalM441spacedBy0680j_4, null, null, false, (r7.l) objRememberedValue, composer2, 24966, 234);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new com.arflix.tv.ui.components.b(list, i10, i11, homeLibraryUiState, list2, i12, z, z5, lVar, lVar2, i13));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProviderTabs$lambda$0$0(List list, boolean z, List list2, int i10, int i11, boolean z5, r7.l lVar, HomeLibraryUiState homeLibraryUiState, int i12, r7.l lVar2, LazyListScope lazyListScope) {
        lazyListScope.items(list.size(), new WatchlistScreenKt$ProviderTabs$lambda$0$0$$inlined$itemsIndexed$default$1(new n(0), list), new WatchlistScreenKt$ProviderTabs$lambda$0$0$$inlined$itemsIndexed$default$2(list), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new WatchlistScreenKt$ProviderTabs$lambda$0$0$$inlined$itemsIndexed$default$3(list, i10, i11, z5, lVar)));
        if (z) {
            lazyListScope.items(list2.size(), new WatchlistScreenKt$ProviderTabs$lambda$0$0$$inlined$itemsIndexed$default$4(new n(1), list2), new WatchlistScreenKt$ProviderTabs$lambda$0$0$$inlined$itemsIndexed$default$5(list2), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new WatchlistScreenKt$ProviderTabs$lambda$0$0$$inlined$itemsIndexed$default$6(list2, homeLibraryUiState, i12, z5, lVar2)));
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object ProviderTabs$lambda$0$0$0(int i10, LibraryProviderOption libraryProviderOption) {
        return libraryProviderOption.getId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object ProviderTabs$lambda$0$0$2(int i10, LibraryFilter libraryFilter) {
        return "library-control-" + i10 + "-" + libraryFilter.getLabel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProviderTabs$lambda$1(List list, int i10, int i11, HomeLibraryUiState homeLibraryUiState, List list2, int i12, boolean z, boolean z5, r7.l lVar, r7.l lVar2, int i13, Composer composer, int i14) {
        ProviderTabs(list, i10, i11, homeLibraryUiState, list2, i12, z, z5, lVar, lVar2, composer, RecomposeScopeImplKt.updateChangedFlags(i13 | 1));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:100:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0273  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x02d3  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0339  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x036a  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0378  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x03d2  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x03e1  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x03ef  */
    /* JADX WARN: Removed duplicated region for block: B:204:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x011a  */
    /* JADX INFO: renamed from: SelectablePill-dR3JOV0, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6457SelectablePilldR3JOV0(final java.lang.String r40, final boolean r41, final boolean r42, final long r43, androidx.compose.ui.Modifier r45, androidx.compose.ui.graphics.Color r46, boolean r47, androidx.compose.ui.graphics.vector.ImageVector r48, boolean r49, androidx.compose.runtime.Composer r50, final int r51, final int r52) {
        /*
            Method dump skipped, instruction units count: 1024
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt.m6457SelectablePilldR3JOV0(java.lang.String, boolean, boolean, long, androidx.compose.ui.Modifier, androidx.compose.ui.graphics.Color, boolean, androidx.compose.ui.graphics.vector.ImageVector, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final float SelectablePill_dR3JOV0$lambda$0(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SelectablePill_dR3JOV0$lambda$1$0(State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setScaleX(SelectablePill_dR3JOV0$lambda$0(state));
        graphicsLayerScope.setScaleY(SelectablePill_dR3JOV0$lambda$0(state));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SelectablePill_dR3JOV0$lambda$3(String str, boolean z, boolean z5, long j10, Modifier modifier, Color color, boolean z10, ImageVector imageVector, boolean z11, int i10, int i11, Composer composer, int i12) {
        m6457SelectablePilldR3JOV0(str, z, z5, j10, modifier, color, z10, imageVector, z11, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }

    private static final void SortSelectionOverlay(List<? extends x6.x> list, HomeServerLibrarySort homeServerLibrarySort, int i10, boolean z, r7.l<? super Integer, t0> lVar, r7.l<? super HomeServerLibrarySort, t0> lVar2, r7.a<t0> aVar, Composer composer, int i11) throws Throwable {
        char c10;
        int i12;
        int i13 = i10;
        Composer composerStartRestartGroup = composer.startRestartGroup(1086943110);
        int i14 = (i11 & 6) == 0 ? (composerStartRestartGroup.changed(list) ? 4 : 2) | i11 : i11;
        if ((i11 & 48) == 0) {
            i14 |= composerStartRestartGroup.changed(homeServerLibrarySort.ordinal()) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i14 |= composerStartRestartGroup.changed(i13) ? 256 : 128;
        }
        if ((i11 & 3072) == 0) {
            i14 |= composerStartRestartGroup.changed(z) ? 2048 : 1024;
        }
        if ((i11 & 24576) == 0) {
            i14 |= composerStartRestartGroup.changedInstance(lVar) ? 16384 : 8192;
        }
        if ((196608 & i11) == 0) {
            i14 |= composerStartRestartGroup.changedInstance(lVar2) ? 131072 : 65536;
        }
        if ((i11 & 1572864) == 0) {
            i14 |= composerStartRestartGroup.changedInstance(aVar) ? 1048576 : 524288;
        }
        if ((i14 & 599187) == 599186 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1086943110, i14, -1, "com.arflix.tv.ui.screens.watchlist.SortSelectionOverlay (WatchlistScreen.kt:1074)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(companion, 0.0f, 1, null);
            Color.Companion companion2 = Color.INSTANCE;
            Modifier modifierM179backgroundbw27NRU$default = BackgroundKt.m179backgroundbw27NRU$default(modifierFillMaxSize$default, Color.m3471copywmQWz5c$default(companion2.m3498getBlack0d7_KjU(), 0.62f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null);
            boolean z5 = (i14 & 3670016) == 1048576;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            int i15 = 10;
            if (z5 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new f0(i15, aVar);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            int i16 = i14;
            Modifier modifierM214clickableXHw0xAI$default = ClickableKt.m214clickableXHw0xAI$default(modifierM179backgroundbw27NRU$default, z, null, null, (r7.a) objRememberedValue, 6, null);
            Alignment.Companion companion3 = Alignment.INSTANCE;
            Alignment center = companion3.getCenter();
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion4.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM214clickableXHw0xAI$default);
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
            p pVarU = a0.c.u(companion4, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            float f10 = 8;
            float f11 = 1;
            Modifier modifierK = com.arflix.tv.data.repository.g.k(f10, com.arflix.tv.data.repository.g.l(f10, SizeKt.m583width3ABfNKs(companion, Dp.m5678constructorimpl(z ? 304 : 330)), androidx.compose.ui.graphics.ColorKt.Color(4279572249L)), Dp.m5678constructorimpl(f11), Color.m3471copywmQWz5c$default(companion2.m3509getWhite0d7_KjU(), 0.16f, 0.0f, 0.0f, 0.0f, 14, null));
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            int i17 = 5;
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new i(i17);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            float f12 = 10;
            Modifier modifierM529padding3ABfNKs = PaddingKt.m529padding3ABfNKs(ClickableKt.m214clickableXHw0xAI$default(modifierK, z, null, null, (r7.a) objRememberedValue2, 6, null), Dp.m5678constructorimpl(f12));
            int i18 = 6;
            MeasurePolicy measurePolicyJ = androidx.compose.foundation.c.j(companion3, androidx.compose.foundation.c.g(5, Arrangement.INSTANCE, composerStartRestartGroup, -483455358), composerStartRestartGroup, 6, -1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion4.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM529padding3ABfNKs);
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2991constructorimpl2 = Updater.m2991constructorimpl(composerStartRestartGroup);
            p pVarU2 = a0.c.u(companion4, composerM2991constructorimpl2, measurePolicyJ, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            String strTr = AppLanguageKt.tr("Sort library", composerStartRestartGroup, 6);
            TextStyle textStyleM5205copyp1EtxEg$default = TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getSectionTitle(), 0L, TextUnitKt.getSp(18), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null);
            long jM3509getWhite0d7_KjU = companion2.m3509getWhite0d7_KjU();
            Modifier modifierM533paddingqDBjuR0$default = PaddingKt.m533paddingqDBjuR0$default(companion, Dp.m5678constructorimpl(f12), Dp.m5678constructorimpl(4), 0.0f, Dp.m5678constructorimpl(7), 4, null);
            int i19 = 1;
            int i20 = 2;
            Throwable th = null;
            TextKt.m6020Text4IGK_g(strTr, modifierM533paddingqDBjuR0$default, jM3509getWhite0d7_KjU, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, textStyleM5205copyp1EtxEg$default, composerStartRestartGroup, 432, 0, 65528);
            composerStartRestartGroup = composerStartRestartGroup;
            composerStartRestartGroup.startReplaceGroup(-1975316414);
            int i21 = 0;
            for (Object obj : list) {
                int i22 = i21 + 1;
                if (i21 < 0) {
                    Throwable th2 = th;
                    t7.a.Q();
                    throw th2;
                }
                x6.x xVar = (x6.x) obj;
                String str = (String) xVar.f22608i;
                HomeServerLibrarySort homeServerLibrarySort2 = (HomeServerLibrarySort) xVar.f22609l;
                int i23 = i21 == i13 ? i19 : 0;
                int i24 = homeServerLibrarySort2 == homeServerLibrarySort ? i19 : 0;
                Modifier.Companion companion5 = Modifier.INSTANCE;
                Modifier modifierM564height3ABfNKs = SizeKt.m564height3ABfNKs(SizeKt.fillMaxWidth$default(companion5, 0.0f, i19, th), Dp.m5678constructorimpl(46));
                int i25 = i21;
                float f13 = i18;
                Modifier modifierL = com.arflix.tv.data.repository.g.l(f13, modifierM564height3ABfNKs, i24 != 0 ? Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), i23 != 0 ? 0.15f : 0.1f, 0.0f, 0.0f, 0.0f, 14, null) : i23 != 0 ? Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.06f, 0.0f, 0.0f, 0.0f, 14, null) : Color.INSTANCE.m3507getTransparent0d7_KjU());
                float fM5678constructorimpl = i23 != 0 ? Dp.m5678constructorimpl(i20) : Dp.m5678constructorimpl(f11);
                Color.Companion companion6 = Color.INSTANCE;
                Modifier modifierK2 = com.arflix.tv.data.repository.g.k(f13, modifierL, fM5678constructorimpl, i23 != 0 ? companion6.m3509getWhite0d7_KjU() : companion6.m3507getTransparent0d7_KjU());
                boolean zChanged = ((i16 & 57344) == 16384) | composerStartRestartGroup.changed(i25) | ((i16 & 458752) == 131072) | composerStartRestartGroup.changed(homeServerLibrarySort2.ordinal());
                Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    a0 a0Var = new a0(lVar, i25, lVar2, homeServerLibrarySort2, 2);
                    composerStartRestartGroup.updateRememberedValue(a0Var);
                    objRememberedValue3 = a0Var;
                }
                Modifier modifierM531paddingVpY3zN4$default = PaddingKt.m531paddingVpY3zN4$default(ClickableKt.m214clickableXHw0xAI$default(modifierK2, z, null, null, (r7.a) objRememberedValue3, 6, null), Dp.m5678constructorimpl(13), 0.0f, 2, null);
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                composerStartRestartGroup.startReplaceableGroup(693286680);
                MeasurePolicy measurePolicyG = androidx.compose.material3.d.g(Arrangement.INSTANCE, centerVertically, composerStartRestartGroup, 48, -1323940314);
                int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                ComposeUiNode.Companion companion7 = ComposeUiNode.INSTANCE;
                r7.a<ComposeUiNode> constructor3 = companion7.getConstructor();
                q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierM531paddingVpY3zN4$default);
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor3);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2991constructorimpl3 = Updater.m2991constructorimpl(composerStartRestartGroup);
                p pVarU3 = a0.c.u(companion7, composerM2991constructorimpl3, measurePolicyG, composerM2991constructorimpl3, currentCompositionLocalMap3);
                if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                    a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
                }
                a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf3, composerStartRestartGroup, 0, 2058660585);
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                TextStyle textStyleM5205copyp1EtxEg$default2 = TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getBody(), 0L, TextUnitKt.getSp(14), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null);
                Color.Companion companion8 = Color.INSTANCE;
                long jM3471copywmQWz5c$default = Color.m3471copywmQWz5c$default(companion8.m3509getWhite0d7_KjU(), (i23 == 0 && i24 == 0) ? 0.7f : 1.0f, 0.0f, 0.0f, 0.0f, 14, null);
                FontWeight.Companion companion9 = FontWeight.INSTANCE;
                Composer composer2 = composerStartRestartGroup;
                TextKt.m6020Text4IGK_g(str, androidx.compose.foundation.layout.e.a(rowScopeInstance, companion5, 1.0f, false, 2, null), jM3471copywmQWz5c$default, 0L, null, i24 != 0 ? companion9.getSemiBold() : companion9.getMedium(), null, 0L, null, null, 0L, 0, false, 0, 0, null, textStyleM5205copyp1EtxEg$default2, composer2, 0, 0, 65496);
                composerStartRestartGroup = composer2;
                if (i24 != 0) {
                    composerStartRestartGroup.startReplaceGroup(59921171);
                    ImageVector check = CheckKt.getCheck(Icons.Outlined.INSTANCE);
                    i12 = 6;
                    String strTr2 = AppLanguageKt.tr("Selected", composerStartRestartGroup, 6);
                    long jM3509getWhite0d7_KjU2 = companion8.m3509getWhite0d7_KjU();
                    c10 = 18;
                    IconKt.m1610Iconww6aTOc(check, strTr2, SizeKt.m578size3ABfNKs(companion5, Dp.m5678constructorimpl(18)), jM3509getWhite0d7_KjU2, composerStartRestartGroup, 3456, 0);
                    composerStartRestartGroup = composerStartRestartGroup;
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    c10 = 18;
                    i12 = 6;
                    composerStartRestartGroup.startReplaceGroup(60200543);
                    composerStartRestartGroup.endReplaceGroup();
                }
                androidx.compose.foundation.c.y(composerStartRestartGroup);
                i13 = i10;
                i20 = 2;
                i19 = 1;
                th = null;
                i21 = i22;
                i18 = i12;
            }
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new e0(list, homeServerLibrarySort, i10, z, lVar, lVar2, aVar, i11));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SortSelectionOverlay$lambda$0$0(r7.a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SortSelectionOverlay$lambda$1$1$0$0$0(r7.l lVar, int i10, r7.l lVar2, HomeServerLibrarySort homeServerLibrarySort) {
        lVar.invoke(Integer.valueOf(i10));
        lVar2.invoke(homeServerLibrarySort);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 SortSelectionOverlay$lambda$2(List list, HomeServerLibrarySort homeServerLibrarySort, int i10, boolean z, r7.l lVar, r7.l lVar2, r7.a aVar, int i11, Composer composer, int i12) throws Throwable {
        SortSelectionOverlay(list, homeServerLibrarySort, i10, z, lVar, lVar2, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1));
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: WatchlistContent-05tvjtU, reason: not valid java name */
    private static final void m6458WatchlistContent05tvjtU(ColumnScope columnScope, WatchlistUiState watchlistUiState, List<? extends x6.x> list, Map<String, String> map, float f10, final boolean z, final boolean z5, final int i10, final int i11, LazyListState lazyListState, final r7.l<? super Integer, t0> lVar, final r7.l<? super MediaItem, t0> lVar2, final r7.l<? super MediaItem, t0> lVar3, Composer composer, int i12, int i13) {
        ColumnScope columnScope2;
        int i14;
        List<? extends x6.x> list2;
        Map<String, String> map2;
        float f11;
        int i15;
        int i16;
        Composer composerStartRestartGroup = composer.startRestartGroup(-213883836);
        if ((i12 & 6) == 0) {
            columnScope2 = columnScope;
            i14 = (composerStartRestartGroup.changed(columnScope2) ? 4 : 2) | i12;
        } else {
            columnScope2 = columnScope;
            i14 = i12;
        }
        if ((i12 & 48) == 0) {
            i14 |= (i12 & 64) == 0 ? composerStartRestartGroup.changed(watchlistUiState) : composerStartRestartGroup.changedInstance(watchlistUiState) ? 32 : 16;
        }
        if ((i12 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            list2 = list;
            i14 |= composerStartRestartGroup.changed(list2) ? 256 : 128;
        } else {
            list2 = list;
        }
        if ((i12 & 3072) == 0) {
            map2 = map;
            i14 |= composerStartRestartGroup.changed(map2) ? 2048 : 1024;
        } else {
            map2 = map;
        }
        if ((i12 & 24576) == 0) {
            f11 = f10;
            i14 |= composerStartRestartGroup.changed(f11) ? 16384 : 8192;
        } else {
            f11 = f10;
        }
        if ((i12 & 196608) == 0) {
            i14 |= composerStartRestartGroup.changed(z) ? 131072 : 65536;
        }
        if ((i12 & 1572864) == 0) {
            i14 |= composerStartRestartGroup.changed(z5) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((i12 & 12582912) == 0) {
            i14 |= composerStartRestartGroup.changed(i10) ? 8388608 : 4194304;
        }
        if ((i12 & 100663296) == 0) {
            i14 |= composerStartRestartGroup.changed(i11) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        }
        if ((i12 & 805306368) == 0) {
            i14 |= composerStartRestartGroup.changed(lazyListState) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        if ((i13 & 6) == 0) {
            i15 = i13 | (composerStartRestartGroup.changedInstance(lVar) ? 4 : 2);
        } else {
            i15 = i13;
        }
        if ((i13 & 48) == 0) {
            i15 |= composerStartRestartGroup.changedInstance(lVar2) ? 32 : 16;
        }
        if ((i13 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i15 |= composerStartRestartGroup.changedInstance(lVar3) ? 256 : 128;
        }
        int i17 = i15;
        if ((i14 & 306783379) == 306783378 && (i17 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-213883836, i14, i17, "com.arflix.tv.ui.screens.watchlist.WatchlistContent (WatchlistScreen.kt:1257)");
            }
            int size = watchlistUiState.getSeries().size() + watchlistUiState.getMovies().size();
            if (watchlistUiState.isLoading()) {
                composerStartRestartGroup.startReplaceGroup(616081717);
                CenteredLoading(composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
            } else if (size == 0) {
                composerStartRestartGroup.startReplaceGroup(1918745358);
                Modifier.Companion companion = Modifier.INSTANCE;
                Modifier modifierA = androidx.compose.foundation.layout.d.a(columnScope2, SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), 1.0f, false, 2, null);
                Alignment.Companion companion2 = Alignment.INSTANCE;
                Alignment center = companion2.getCenter();
                composerStartRestartGroup.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composerStartRestartGroup, 6);
                composerStartRestartGroup.startReplaceableGroup(-1323940314);
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
                r7.a<ComposeUiNode> constructor = companion3.getConstructor();
                q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierA);
                if (!(composerStartRestartGroup.getApplier() != null)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composerStartRestartGroup);
                p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
                if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
                }
                a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                Alignment.Horizontal centerHorizontally = companion2.getCenterHorizontally();
                composerStartRestartGroup.startReplaceableGroup(-483455358);
                MeasurePolicy measurePolicyF = androidx.compose.material3.d.f(Arrangement.INSTANCE, centerHorizontally, composerStartRestartGroup, 48, -1323940314);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
                q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(companion);
                if (!(composerStartRestartGroup.getApplier() != null)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2991constructorimpl2 = Updater.m2991constructorimpl(composerStartRestartGroup);
                p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyF, composerM2991constructorimpl2, currentCompositionLocalMap2);
                if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
                }
                a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                ImageVector bookmark = BookmarkKt.getBookmark(Icons.Outlined.INSTANCE);
                Color.Companion companion4 = Color.INSTANCE;
                IconKt.m1610Iconww6aTOc(bookmark, (String) null, SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(80)), Color.m3471copywmQWz5c$default(companion4.m3509getWhite0d7_KjU(), 0.2f, 0.0f, 0.0f, 0.0f, 14, null), composerStartRestartGroup, 3504, 0);
                com.arflix.tv.data.repository.g.p(16, companion, composerStartRestartGroup, 6);
                String strTr = AppLanguageKt.tr("Your watchlist is empty", composerStartRestartGroup, 6);
                ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
                TextStyle body = arflixTypography.getBody();
                long jM3471copywmQWz5c$default = Color.m3471copywmQWz5c$default(companion4.m3509getWhite0d7_KjU(), 0.5f, 0.0f, 0.0f, 0.0f, 14, null);
                TextAlign.Companion companion5 = TextAlign.INSTANCE;
                TextKt.m6020Text4IGK_g(strTr, null, jM3471copywmQWz5c$default, 0L, null, null, null, 0L, null, TextAlign.m5575boximpl(companion5.m5582getCentere0LSkKk()), 0L, 0, false, 0, 0, null, body, composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65018);
                com.arflix.tv.data.repository.g.p(8, companion, composerStartRestartGroup, 6);
                TextKt.m6020Text4IGK_g(AppLanguageKt.tr("Add movies and shows for later", composerStartRestartGroup, 6), null, Color.m3471copywmQWz5c$default(companion4.m3509getWhite0d7_KjU(), 0.3f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, TextAlign.m5575boximpl(companion5.m5582getCentere0LSkKk()), 0L, 0, false, 0, 0, null, arflixTypography.getCaption(), composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65018);
                com.arflix.tv.data.repository.g.v(composerStartRestartGroup);
                composerStartRestartGroup = composerStartRestartGroup;
            } else {
                composerStartRestartGroup.startReplaceGroup(1919965704);
                float f12 = 24;
                Modifier modifierM533paddingqDBjuR0$default = PaddingKt.m533paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(androidx.compose.foundation.layout.d.a(columnScope, Modifier.INSTANCE, 1.0f, false, 2, null), 0.0f, 1, null), Dp.m5678constructorimpl(f12), 0.0f, Dp.m5678constructorimpl(48), 0.0f, 10, null);
                float f13 = 16;
                PaddingValues paddingValuesM526PaddingValuesa9UjIt4$default = PaddingKt.m526PaddingValuesa9UjIt4$default(0.0f, Dp.m5678constructorimpl(10), 0.0f, Dp.m5678constructorimpl(f13), 5, null);
                Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = Arrangement.INSTANCE.m441spacedBy0680j_4(z5 ? Dp.m5678constructorimpl(f12) : Dp.m5678constructorimpl(f13));
                boolean z10 = ((i14 & 896) == 256) | ((i14 & 7168) == 2048) | ((57344 & i14) == 16384) | ((458752 & i14) == 131072) | ((3670016 & i14) == 1048576) | ((i14 & 29360128) == 8388608) | ((234881024 & i14) == 67108864) | ((i17 & 14) == 4) | ((i17 & 112) == 32) | ((i17 & 896) == 256);
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z10 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    final float f14 = f11;
                    i16 = i14;
                    final List<? extends x6.x> list3 = list2;
                    final Map<String, String> map3 = map2;
                    objRememberedValue = new r7.l() { // from class: com.arflix.tv.ui.screens.watchlist.c
                        @Override // r7.l
                        public final Object invoke(Object obj) {
                            return WatchlistScreenKt.WatchlistContent_05tvjtU$lambda$1$0(list3, map3, f14, z, z5, i10, i11, lVar, lVar2, lVar3, (LazyListScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    i16 = i14;
                }
                composerStartRestartGroup = composerStartRestartGroup;
                LazyDslKt.LazyColumn(modifierM533paddingqDBjuR0$default, lazyListState, paddingValuesM526PaddingValuesa9UjIt4$default, false, horizontalOrVerticalM441spacedBy0680j_4, null, null, z5, (r7.l) objRememberedValue, composerStartRestartGroup, ((i16 >> 24) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK | ((i16 << 3) & 29360128), MediaError.DetailedErrorCode.MEDIA_SRC_NOT_SUPPORTED);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new d(columnScope, watchlistUiState, list, map, f10, z, z5, i10, i11, lazyListState, lVar, lVar2, lVar3, i12, i13));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WatchlistContent_05tvjtU$lambda$1$0(List list, Map map, float f10, boolean z, boolean z5, int i10, int i11, r7.l lVar, r7.l lVar2, r7.l lVar3, LazyListScope lazyListScope) {
        lazyListScope.items(list.size(), new WatchlistScreenKt$WatchlistContent_05tvjtU$lambda$1$0$$inlined$itemsIndexed$default$1(new n(4), list), new WatchlistScreenKt$WatchlistContent_05tvjtU$lambda$1$0$$inlined$itemsIndexed$default$2(list), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new WatchlistScreenKt$WatchlistContent_05tvjtU$lambda$1$0$$inlined$itemsIndexed$default$3(list, map, f10, z, z5, i10, i11, lVar, lVar2, lVar3)));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object WatchlistContent_05tvjtU$lambda$1$0$0(int i10, x6.x xVar) {
        return xVar.f22608i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WatchlistContent_05tvjtU$lambda$2(ColumnScope columnScope, WatchlistUiState watchlistUiState, List list, Map map, float f10, boolean z, boolean z5, int i10, int i11, LazyListState lazyListState, r7.l lVar, r7.l lVar2, r7.l lVar3, int i12, int i13, Composer composer, int i14) {
        m6458WatchlistContent05tvjtU(columnScope, watchlistUiState, list, map, f10, z, z5, i10, i11, lazyListState, lVar, lVar2, lVar3, composer, RecomposeScopeImplKt.updateChangedFlags(i12 | 1), RecomposeScopeImplKt.updateChangedFlags(i13));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: WatchlistItemsSection-7vwJh_E, reason: not valid java name */
    public static final void m6459WatchlistItemsSection7vwJh_E(final String str, final List<MediaItem> list, final Map<String, String> map, final float f10, final boolean z, boolean z5, int i10, r7.l<? super Integer, t0> lVar, final r7.l<? super MediaItem, t0> lVar2, r7.l<? super MediaItem, t0> lVar3, Composer composer, final int i11, final int i12) {
        int i13;
        float f11;
        int i14;
        r7.l<? super Integer, t0> lVar4;
        r7.l<? super Integer, t0> lVar5;
        r7.l<? super MediaItem, t0> lVar6;
        r7.l<? super MediaItem, t0> lVar7;
        int i15;
        r7.l<? super Integer, t0> lVar8;
        Composer composer2;
        final int i16;
        final r7.l<? super MediaItem, t0> lVar9;
        final r7.l<? super Integer, t0> lVar10;
        final boolean z10;
        Composer composerStartRestartGroup = composer.startRestartGroup(690613269);
        if ((i11 & 6) == 0) {
            i13 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i11;
        } else {
            i13 = i11;
        }
        if ((i11 & 48) == 0) {
            i13 |= composerStartRestartGroup.changed(list) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i13 |= composerStartRestartGroup.changed(map) ? 256 : 128;
        }
        if ((i11 & 3072) == 0) {
            f11 = f10;
            i13 |= composerStartRestartGroup.changed(f11) ? 2048 : 1024;
        } else {
            f11 = f10;
        }
        if ((i11 & 24576) == 0) {
            i13 |= composerStartRestartGroup.changed(z) ? 16384 : 8192;
        }
        int i17 = i12 & 64;
        if (i17 != 0) {
            i13 |= 1572864;
            i14 = i10;
        } else {
            i14 = i10;
            if ((i11 & 1572864) == 0) {
                i13 |= composerStartRestartGroup.changed(i14) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
            }
        }
        int i18 = i12 & 128;
        if (i18 != 0) {
            i13 |= 12582912;
            lVar4 = lVar;
        } else {
            lVar4 = lVar;
            if ((i11 & 12582912) == 0) {
                i13 |= composerStartRestartGroup.changedInstance(lVar4) ? 8388608 : 4194304;
            }
        }
        if ((i11 & 100663296) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(lVar2) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        }
        int i19 = i12 & 512;
        if (i19 != 0) {
            i13 |= 805306368;
        } else if ((i11 & 805306368) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(lVar3) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        if ((i13 & 306717843) == 306717842 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            z10 = z5;
            lVar9 = lVar3;
            composer2 = composerStartRestartGroup;
            lVar10 = lVar4;
            i16 = i14;
        } else {
            boolean z11 = (i12 & 32) != 0 ? false : z5;
            if (i17 != 0) {
                i14 = -1;
            }
            if (i18 != 0) {
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new j(2);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                lVar5 = (r7.l) objRememberedValue;
            } else {
                lVar5 = lVar4;
            }
            int i20 = 3;
            if (i19 != 0) {
                Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new j(i20);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                lVar6 = (r7.l) objRememberedValue2;
            } else {
                lVar6 = lVar3;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(690613269, i13, -1, "com.arflix.tv.ui.screens.watchlist.WatchlistItemsSection (WatchlistScreen.kt:1418)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            r7.l<? super MediaItem, t0> lVar11 = lVar6;
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null);
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            Arrangement arrangement = Arrangement.INSTANCE;
            r7.l<? super Integer, t0> lVar12 = lVar5;
            MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(Alignment.INSTANCE, arrangement.getTop(), composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierFillMaxWidth$default);
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
            p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyK, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            float f12 = 8;
            int i21 = i14;
            TextKt.m6020Text4IGK_g(str, PaddingKt.m533paddingqDBjuR0$default(companion, Dp.m5678constructorimpl(f12), 0.0f, 0.0f, Dp.m5678constructorimpl(f12), 6, null), ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, ArflixTypography.INSTANCE.getSectionTitle(), composerStartRestartGroup, (i13 & 14) | 432, 0, 65528);
            LazyListState lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
            Integer numValueOf = Integer.valueOf(i21);
            int i22 = 3670016 & i13;
            int i23 = i13 & 112;
            boolean zChanged = (i23 == 32) | (i22 == 1048576) | composerStartRestartGroup.changed(lazyListStateRememberLazyListState);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new WatchlistScreenKt$WatchlistItemsSection$3$1$1(i21, list, lazyListStateRememberLazyListState, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            EffectsKt.LaunchedEffect(numValueOf, (p<? super k0, ? super d7.d<? super t0>, ? extends Object>) objRememberedValue3, composerStartRestartGroup, (i13 >> 18) & 14);
            float f13 = 16;
            Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = arrangement.m441spacedBy0680j_4(Dp.m5678constructorimpl(f13));
            float f14 = 4;
            PaddingValues paddingValuesM525PaddingValuesa9UjIt4 = PaddingKt.m525PaddingValuesa9UjIt4(Dp.m5678constructorimpl(f12), Dp.m5678constructorimpl(f14), Dp.m5678constructorimpl(f13), Dp.m5678constructorimpl(f14));
            Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null);
            boolean z12 = ((i13 & 7168) == 2048) | (i23 == 32) | ((57344 & i13) == 16384) | ((i13 & 896) == 256) | (i22 == 1048576) | ((29360128 & i13) == 8388608) | ((234881024 & i13) == 67108864) | ((i13 & 1879048192) == 536870912);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (z12 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                lVar7 = lVar11;
                i15 = i21;
                lVar8 = lVar12;
                e eVar = new e(list, f11, z, map, i15, lVar8, lVar2, lVar7);
                composerStartRestartGroup.updateRememberedValue(eVar);
                objRememberedValue4 = eVar;
            } else {
                lVar7 = lVar11;
                i15 = i21;
                lVar8 = lVar12;
            }
            composer2 = composerStartRestartGroup;
            LazyDslKt.LazyRow(modifierFillMaxWidth$default2, lazyListStateRememberLazyListState, paddingValuesM525PaddingValuesa9UjIt4, false, horizontalOrVerticalM441spacedBy0680j_4, null, null, false, (r7.l) objRememberedValue4, composer2, 24582, 232);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
            i16 = i15;
            lVar9 = lVar7;
            lVar10 = lVar8;
            z10 = z11;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new p() { // from class: com.arflix.tv.ui.screens.watchlist.l
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    return WatchlistScreenKt.WatchlistItemsSection_7vwJh_E$lambda$3(str, list, map, f10, z, z10, i16, lVar10, lVar2, lVar9, i11, i12, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WatchlistItemsSection_7vwJh_E$lambda$0$0(int i10) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WatchlistItemsSection_7vwJh_E$lambda$1$0(MediaItem mediaItem) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WatchlistItemsSection_7vwJh_E$lambda$2$1$0(List list, float f10, boolean z, Map map, int i10, r7.l lVar, r7.l lVar2, r7.l lVar3, LazyListScope lazyListScope) {
        lazyListScope.items(list.size(), new WatchlistScreenKt$WatchlistItemsSection_7vwJh_E$lambda$2$1$0$$inlined$itemsIndexed$1(new n(3), list), new WatchlistScreenKt$WatchlistItemsSection_7vwJh_E$lambda$2$1$0$$inlined$itemsIndexed$2(list), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new WatchlistScreenKt$WatchlistItemsSection_7vwJh_E$lambda$2$1$0$$inlined$itemsIndexed$3(list, f10, z, map, i10, lVar, lVar2, lVar3)));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object WatchlistItemsSection_7vwJh_E$lambda$2$1$0$0(int i10, MediaItem mediaItem) {
        return WatchlistViewModelKt.watchlistItemKey(mediaItem, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WatchlistItemsSection_7vwJh_E$lambda$3(String str, List list, Map map, float f10, boolean z, boolean z5, int i10, r7.l lVar, r7.l lVar2, r7.l lVar3, int i11, int i12, Composer composer, int i13) {
        m6459WatchlistItemsSection7vwJh_E(str, list, map, f10, z, z5, i10, lVar, lVar2, lVar3, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), i12);
        return t0.f22605a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x02a7  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x031f  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0339  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0379  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x03c2  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x03c5  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x03c7  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x03da  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x03dc  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x03eb  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x03f3  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x03f7  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0409  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0423  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x043a  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0444  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0454  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x0469  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x047e  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x0493  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x04a8  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x04bf  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x04cd  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x04df  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x04f6  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x050d  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x051b  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x052b  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0539  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x0568  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x057f  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x05b2  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x0606  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x063e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x06b2  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x06f9  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x0715  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x0735  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x0747  */
    /* JADX WARN: Removed duplicated region for block: B:341:0x07af  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x07b5  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x07ed  */
    /* JADX WARN: Removed duplicated region for block: B:365:0x085c  */
    /* JADX WARN: Removed duplicated region for block: B:369:0x0870  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:385:0x08d3  */
    /* JADX WARN: Removed duplicated region for block: B:386:0x08d8  */
    /* JADX WARN: Removed duplicated region for block: B:392:0x0946  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x096b  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:400:0x09c8  */
    /* JADX WARN: Removed duplicated region for block: B:404:0x0a87  */
    /* JADX WARN: Removed duplicated region for block: B:414:0x0ac2  */
    /* JADX WARN: Removed duplicated region for block: B:417:0x0ade  */
    /* JADX WARN: Removed duplicated region for block: B:423:0x0aff  */
    /* JADX WARN: Removed duplicated region for block: B:426:0x0b08  */
    /* JADX WARN: Removed duplicated region for block: B:427:0x0b0a  */
    /* JADX WARN: Removed duplicated region for block: B:432:0x0b19  */
    /* JADX WARN: Removed duplicated region for block: B:437:0x0b4a  */
    /* JADX WARN: Removed duplicated region for block: B:440:0x0b63  */
    /* JADX WARN: Removed duplicated region for block: B:444:0x0b89 A[LOOP:4: B:442:0x0b83->B:444:0x0b89, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:449:0x0bae  */
    /* JADX WARN: Removed duplicated region for block: B:454:0x0bd3  */
    /* JADX WARN: Removed duplicated region for block: B:459:0x0bf8  */
    /* JADX WARN: Removed duplicated region for block: B:462:0x0c29  */
    /* JADX WARN: Removed duplicated region for block: B:466:0x0c39  */
    /* JADX WARN: Removed duplicated region for block: B:470:0x0c8e  */
    /* JADX WARN: Removed duplicated region for block: B:474:0x0c9e  */
    /* JADX WARN: Removed duplicated region for block: B:478:0x0ce9  */
    /* JADX WARN: Removed duplicated region for block: B:482:0x0cfe  */
    /* JADX WARN: Removed duplicated region for block: B:486:0x0d77  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:490:0x0d8f  */
    /* JADX WARN: Removed duplicated region for block: B:493:0x0dd9  */
    /* JADX WARN: Removed duplicated region for block: B:494:0x0de2  */
    /* JADX WARN: Removed duplicated region for block: B:497:0x0e1d  */
    /* JADX WARN: Removed duplicated region for block: B:501:0x0e2d  */
    /* JADX WARN: Removed duplicated region for block: B:505:0x0ea6  */
    /* JADX WARN: Removed duplicated region for block: B:506:0x0ea8  */
    /* JADX WARN: Removed duplicated region for block: B:509:0x0ef0  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:510:0x0ef2  */
    /* JADX WARN: Removed duplicated region for block: B:513:0x0efd  */
    /* JADX WARN: Removed duplicated region for block: B:514:0x0eff  */
    /* JADX WARN: Removed duplicated region for block: B:517:0x0f0b  */
    /* JADX WARN: Removed duplicated region for block: B:518:0x0f0d  */
    /* JADX WARN: Removed duplicated region for block: B:521:0x0f19  */
    /* JADX WARN: Removed duplicated region for block: B:522:0x0f1b  */
    /* JADX WARN: Removed duplicated region for block: B:525:0x0f27  */
    /* JADX WARN: Removed duplicated region for block: B:526:0x0f29  */
    /* JADX WARN: Removed duplicated region for block: B:529:0x0f43  */
    /* JADX WARN: Removed duplicated region for block: B:530:0x0f45  */
    /* JADX WARN: Removed duplicated region for block: B:533:0x0f50  */
    /* JADX WARN: Removed duplicated region for block: B:537:0x0f71  */
    /* JADX WARN: Removed duplicated region for block: B:541:0x0fe1  */
    /* JADX WARN: Removed duplicated region for block: B:544:0x0fed  */
    /* JADX WARN: Removed duplicated region for block: B:545:0x0ff1  */
    /* JADX WARN: Removed duplicated region for block: B:548:0x1002  */
    /* JADX WARN: Removed duplicated region for block: B:551:0x1013  */
    /* JADX WARN: Removed duplicated region for block: B:555:0x1024  */
    /* JADX WARN: Removed duplicated region for block: B:561:0x1070  */
    /* JADX WARN: Removed duplicated region for block: B:564:0x1089  */
    /* JADX WARN: Removed duplicated region for block: B:565:0x1090  */
    /* JADX WARN: Removed duplicated region for block: B:568:0x10e7  */
    /* JADX WARN: Removed duplicated region for block: B:571:0x10f3  */
    /* JADX WARN: Removed duplicated region for block: B:572:0x10f7  */
    /* JADX WARN: Removed duplicated region for block: B:575:0x1108  */
    /* JADX WARN: Removed duplicated region for block: B:579:0x111b  */
    /* JADX WARN: Removed duplicated region for block: B:582:0x1126  */
    /* JADX WARN: Removed duplicated region for block: B:583:0x11c6  */
    /* JADX WARN: Removed duplicated region for block: B:586:0x11d9  */
    /* JADX WARN: Removed duplicated region for block: B:587:0x11df  */
    /* JADX WARN: Removed duplicated region for block: B:590:0x11e8  */
    /* JADX WARN: Removed duplicated region for block: B:591:0x11ef  */
    /* JADX WARN: Removed duplicated region for block: B:596:0x1208  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:603:0x125c  */
    /* JADX WARN: Removed duplicated region for block: B:606:0x129c  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:645:0x139e  */
    /* JADX WARN: Removed duplicated region for block: B:673:0x1454  */
    /* JADX WARN: Removed duplicated region for block: B:674:0x145e  */
    /* JADX WARN: Removed duplicated region for block: B:697:0x14f8  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:700:0x1522  */
    /* JADX WARN: Removed duplicated region for block: B:703:0x1545  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:715:0x15b5  */
    /* JADX WARN: Removed duplicated region for block: B:718:0x15c4  */
    /* JADX WARN: Removed duplicated region for block: B:722:0x15dc  */
    /* JADX WARN: Removed duplicated region for block: B:725:0x0730 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:726:0x0768 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:728:0x095f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:756:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x011e  */
    /* JADX WARN: Type inference failed for: r139v12, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r13v13, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r13v14, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r13v15, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r13v19, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r13v20, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r13v22, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r13v24, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r13v26, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r13v29, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r13v32, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r13v33 */
    /* JADX WARN: Type inference failed for: r13v35 */
    /* JADX WARN: Type inference failed for: r13v36 */
    /* JADX WARN: Type inference failed for: r13v37 */
    /* JADX WARN: Type inference failed for: r13v38 */
    /* JADX WARN: Type inference failed for: r13v39 */
    /* JADX WARN: Type inference failed for: r13v40 */
    /* JADX WARN: Type inference failed for: r13v41 */
    /* JADX WARN: Type inference failed for: r15v40 */
    /* JADX WARN: Type inference failed for: r20v8, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r22v11, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r33v4, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r3v133 */
    /* JADX WARN: Type inference failed for: r3v134 */
    /* JADX WARN: Type inference failed for: r3v54 */
    /* JADX WARN: Type inference failed for: r3v55, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r3v56, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v57, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r3v58 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void WatchlistScreen(com.arflix.tv.ui.screens.watchlist.WatchlistViewModel r133, com.arflix.tv.data.model.Profile r134, r7.p<? super com.arflix.tv.data.model.MediaType, ? super java.lang.Integer, x6.t0> r135, r7.a<x6.t0> r136, r7.a<x6.t0> r137, r7.a<x6.t0> r138, r7.l<? super java.lang.String, x6.t0> r139, r7.a<x6.t0> r140, r7.a<x6.t0> r141, androidx.compose.runtime.Composer r142, int r143, int r144) {
        /*
            Method dump skipped, instruction units count: 5609
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt.WatchlistScreen(com.arflix.tv.ui.screens.watchlist.WatchlistViewModel, com.arflix.tv.data.model.Profile, r7.p, r7.a, r7.a, r7.a, r7.l, r7.a, r7.a, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void WatchlistScreen$activateFilter(List<LibraryFilter> list, boolean z, WatchlistViewModel watchlistViewModel, List<? extends x6.x> list2, MutableState<Boolean> mutableState, State<HomeLibraryUiState> state, MutableIntState mutableIntState, MutableState<Boolean> mutableState2, MutableIntState mutableIntState2, int i10) {
        LibraryFilter libraryFilter = (LibraryFilter) x.p0(i10, list);
        if (libraryFilter == null) {
            return;
        }
        if (libraryFilter.isSearch()) {
            WatchlistScreen$lambda$37(mutableState, true);
        } else if (libraryFilter.isRefresh()) {
            if (z) {
                watchlistViewModel.refreshLibrary();
            } else {
                watchlistViewModel.refresh();
            }
        } else if (libraryFilter.isSort()) {
            Iterator<? extends x6.x> it = list2.iterator();
            int i11 = 0;
            while (true) {
                if (!it.hasNext()) {
                    i11 = -1;
                    break;
                } else if (it.next().f22609l == WatchlistScreen$lambda$8(state).getSort()) {
                    break;
                } else {
                    i11++;
                }
            }
            if (i11 < 0) {
                i11 = 0;
            }
            mutableIntState.setIntValue(i11);
            WatchlistScreen$lambda$40(mutableState2, true);
        }
        mutableIntState2.setIntValue(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void WatchlistScreen$activateProvider(List<LibraryProviderOption> list, WatchlistViewModel watchlistViewModel, MutableIntState mutableIntState, MutableState<String> mutableState, MutableState<String> mutableState2, MutableIntState mutableIntState2, MutableIntState mutableIntState3, MutableIntState mutableIntState4, int i10) {
        int size = list.size() - 1;
        if (size < 0) {
            size = 0;
        }
        mutableIntState.setIntValue(qb.d.n(i10, 0, size));
        LibraryProviderOption libraryProviderOption = list.get(mutableIntState.getIntValue());
        mutableState.setValue(libraryProviderOption.getId());
        mutableState2.setValue("");
        if (libraryProviderOption.isWatchlist()) {
            watchlistViewModel.selectLibraryProvider(null);
            watchlistViewModel.selectSource(WatchlistSourceItem.MyWatchlist.INSTANCE.getId());
        } else if (libraryProviderOption.isHomeServer()) {
            watchlistViewModel.selectLibraryProvider(libraryProviderOption.getHomeServerKind());
        } else if (libraryProviderOption.isTracker()) {
            watchlistViewModel.selectLibraryProvider(null);
            WatchlistSourceItem watchlistSourceItem = (WatchlistSourceItem) x.o0(libraryProviderOption.getTrackerSources());
            if (watchlistSourceItem != null) {
                watchlistViewModel.selectSource(watchlistSourceItem.getId());
            }
        }
        mutableIntState2.setIntValue(0);
        mutableIntState3.setIntValue(0);
        mutableIntState4.setIntValue(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WatchlistScreen$lambda$0$0(MediaType mediaType, int i10) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WatchlistFocusZone WatchlistScreen$lambda$15(MutableState<WatchlistFocusZone> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean WatchlistScreen$lambda$36(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void WatchlistScreen$lambda$37(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean WatchlistScreen$lambda$39(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WatchlistScreen$lambda$4$0(String str) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void WatchlistScreen$lambda$40(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String WatchlistScreen$lambda$45(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String WatchlistScreen$lambda$48(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WatchlistScreen$lambda$60$0(MutableState mutableState, MutableState mutableState2) {
        WatchlistScreen$lambda$40(mutableState, false);
        mutableState2.setValue(WatchlistFocusZone.FILTERS);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WatchlistScreen$lambda$61$0(r7.a aVar, MutableState mutableState) {
        WatchlistFocusZone watchlistFocusZoneWatchlistScreen$lambda$15 = WatchlistScreen$lambda$15(mutableState);
        WatchlistFocusZone watchlistFocusZone = WatchlistFocusZone.TOP_BAR;
        if (watchlistFocusZoneWatchlistScreen$lambda$15 == watchlistFocusZone) {
            aVar.invoke();
        } else {
            mutableState.setValue(watchlistFocusZone);
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult WatchlistScreen$lambda$62$0(final y yVar, WatchlistViewModel watchlistViewModel, DisposableEffectScope disposableEffectScope) {
        final androidx.activity.i iVar = new androidx.activity.i(new kotlin.jvm.internal.f0(), watchlistViewModel, 3);
        yVar.getLifecycle().addObserver(iVar);
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt$WatchlistScreen$lambda$62$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                yVar.getLifecycle().removeObserver(iVar);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void WatchlistScreen$lambda$62$0$0(kotlin.jvm.internal.f0 f0Var, WatchlistViewModel watchlistViewModel, y yVar, androidx.lifecycle.p pVar) {
        if (pVar == androidx.lifecycle.p.ON_RESUME) {
            if (f0Var.f19738i) {
                watchlistViewModel.refreshAfterResume();
            } else {
                f0Var.f19738i = true;
            }
        }
    }

    private static final WatchlistUiState WatchlistScreen$lambda$7(State<WatchlistUiState> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WatchlistScreen$lambda$74$0$1$0(MutableIntState mutableIntState, List list, boolean z, WatchlistViewModel watchlistViewModel, List list2, MutableState mutableState, State state, MutableIntState mutableIntState2, MutableState mutableState2, MutableIntState mutableIntState3, int i10) {
        mutableIntState.setIntValue(i10);
        WatchlistScreen$activateFilter(list, z, watchlistViewModel, list2, mutableState, state, mutableIntState2, mutableState2, mutableIntState3, i10);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WatchlistScreen$lambda$74$0$2$0(boolean z, WatchlistViewModel watchlistViewModel, MutableIntState mutableIntState, MutableIntState mutableIntState2, int i10, HomeServerCatalogCandidate homeServerCatalogCandidate) {
        mutableIntState.setIntValue(i10);
        if (z) {
            watchlistViewModel.selectLibrary(homeServerCatalogCandidate.getSourceRef());
        } else {
            watchlistViewModel.selectSource(homeServerCatalogCandidate.getSourceRef());
        }
        mutableIntState2.setIntValue(0);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WatchlistScreen$lambda$74$0$3$0(MutableIntState mutableIntState, int i10) {
        mutableIntState.setIntValue(i10);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WatchlistScreen$lambda$74$0$5$0(p pVar, MediaItem mediaItem) {
        pVar.invoke(mediaItem.getMediaType(), Integer.valueOf(mediaItem.getId()));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WatchlistScreen$lambda$74$0$6$0(boolean z, WatchlistViewModel watchlistViewModel) {
        if (z) {
            watchlistViewModel.loadMoreLibrary();
        } else {
            watchlistViewModel.loadMoreActiveSource();
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WatchlistScreen$lambda$74$0$7$0(MutableIntState mutableIntState, int i10) {
        mutableIntState.setIntValue(i10);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WatchlistScreen$lambda$74$0$8$0(p pVar, MediaItem mediaItem) {
        pVar.invoke(mediaItem.getMediaType(), Integer.valueOf(mediaItem.getId()));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WatchlistScreen$lambda$74$2$0(boolean z, WatchlistViewModel watchlistViewModel, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, String str) {
        WatchlistScreen$lambda$37(mutableState, false);
        if (z) {
            watchlistViewModel.setLibrarySearch(kotlin.text.o.L0(str).toString());
        } else {
            mutableState2.setValue(kotlin.text.o.L0(str).toString());
        }
        mutableState3.setValue(WatchlistFocusZone.FILTERS);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WatchlistScreen$lambda$74$3$0(MutableState mutableState, MutableState mutableState2) {
        WatchlistScreen$lambda$37(mutableState, false);
        mutableState2.setValue(WatchlistFocusZone.FILTERS);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WatchlistScreen$lambda$74$4$0(MutableIntState mutableIntState, int i10) {
        mutableIntState.setIntValue(i10);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WatchlistScreen$lambda$74$5$0(WatchlistViewModel watchlistViewModel, MutableState mutableState, MutableState mutableState2, HomeServerLibrarySort homeServerLibrarySort) {
        watchlistViewModel.setLibrarySort(homeServerLibrarySort);
        WatchlistScreen$lambda$40(mutableState, false);
        mutableState2.setValue(WatchlistFocusZone.FILTERS);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WatchlistScreen$lambda$74$6$0(MutableState mutableState, MutableState mutableState2) {
        WatchlistScreen$lambda$40(mutableState, false);
        mutableState2.setValue(WatchlistFocusZone.FILTERS);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 WatchlistScreen$lambda$75(WatchlistViewModel watchlistViewModel, Profile profile, p pVar, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.l lVar, r7.a aVar4, r7.a aVar5, int i10, int i11, Composer composer, int i12) {
        WatchlistScreen(watchlistViewModel, profile, pVar, aVar, aVar2, aVar3, lVar, aVar4, aVar5, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }

    private static final HomeLibraryUiState WatchlistScreen$lambda$8(State<HomeLibraryUiState> state) {
        return state.getValue();
    }

    private static final Map<String, String> WatchlistScreen$lambda$9(State<? extends Map<String, String>> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void WatchlistScreen$moveToContent(boolean r1, java.util.List<com.arflix.tv.data.model.MediaItem> r2, java.util.List<? extends x6.x> r3, androidx.compose.runtime.MutableState<com.arflix.tv.ui.screens.watchlist.WatchlistFocusZone> r4, androidx.compose.runtime.MutableIntState r5) {
        /*
            com.arflix.tv.ui.screens.watchlist.WatchlistFocusZone r0 = com.arflix.tv.ui.screens.watchlist.WatchlistFocusZone.CONTENT
            WatchlistScreen$lambda$16(r4, r0)
            int r4 = WatchlistScreen$lambda$30(r5)
            if (r1 == 0) goto L14
            int r1 = r2.size()
        Lf:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            goto L28
        L14:
            java.lang.Object r1 = kotlin.collections.x.o0(r3)
            x6.x r1 = (x6.x) r1
            if (r1 == 0) goto L27
            java.lang.Object r1 = r1.f22609l
            java.util.List r1 = (java.util.List) r1
            if (r1 == 0) goto L27
            int r1 = r1.size()
            goto Lf
        L27:
            r1 = 0
        L28:
            r2 = 1
            if (r1 == 0) goto L30
            int r1 = r1.intValue()
            goto L31
        L30:
            r1 = r2
        L31:
            int r1 = r1 - r2
            r2 = 0
            int r1 = qb.d.n(r4, r2, r1)
            if (r1 >= 0) goto L3a
            goto L3b
        L3a:
            r2 = r1
        L3b:
            WatchlistScreen$lambda$31(r5, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt.WatchlistScreen$moveToContent(boolean, java.util.List, java.util.List, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableIntState):void");
    }

    private static final HomeServerCatalogCandidate asSidebarLibrary(WatchlistSourceItem watchlistSourceItem, String str) {
        return new HomeServerCatalogCandidate(watchlistSourceItem.getTitle(), watchlistSourceItem.getId(), str, watchlistSourceItem.getTitle(), "mixed", HomeServerKind.UNKNOWN, "tracker:".concat(str.toLowerCase(Locale.ROOT)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long providerAccent(HomeServerKind homeServerKind) {
        int i10 = homeServerKind == null ? -1 : WhenMappings.$EnumSwitchMapping$0[homeServerKind.ordinal()];
        return i10 != 1 ? i10 != 2 ? i10 != 3 ? Color.INSTANCE.m3509getWhite0d7_KjU() : androidx.compose.ui.graphics.ColorKt.Color(4283610443L) : androidx.compose.ui.graphics.ColorKt.Color(4288372197L) : androidx.compose.ui.graphics.ColorKt.Color(4293238797L);
    }

    private static final String trackerProviderLabel(WatchlistSourceItem watchlistSourceItem) {
        if (watchlistSourceItem instanceof WatchlistSourceItem.TrackerList) {
            return ((WatchlistSourceItem.TrackerList) watchlistSourceItem).getProvider().getDisplayName();
        }
        if (!(watchlistSourceItem instanceof WatchlistSourceItem.Catalog)) {
            return null;
        }
        WatchlistSourceItem.Catalog catalog = (WatchlistSourceItem.Catalog) watchlistSourceItem;
        String sourceUrl = catalog.getConfig().getSourceUrl();
        if (sourceUrl == null) {
            sourceUrl = "";
        }
        String id = catalog.getConfig().getId();
        String title = catalog.getConfig().getTitle();
        String addonName = catalog.getConfig().getAddonName();
        String strU0 = x.u0(t7.a.E(id, title, addonName != null ? addonName : "", sourceUrl), " ", null, null, null, 62);
        if (catalog.getConfig().getSourceType() == CatalogSourceType.TRAKT || kotlin.text.o.T(strU0, "trakt", true)) {
            return "Trakt";
        }
        if (kotlin.text.o.T(strU0, "simkl", true)) {
            return "Simkl";
        }
        return null;
    }
}
