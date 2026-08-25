package com.arflix.tv.ui.screens.collections;

import androidx.lifecycle.d1;
import androidx.lifecycle.z0;
import com.arflix.tv.data.model.CatalogConfig;
import com.arflix.tv.data.model.CatalogKind;
import com.arflix.tv.data.model.CatalogSourceType;
import com.arflix.tv.data.model.CollectionGroupKind;
import com.arflix.tv.data.model.CollectionSourceConfig;
import com.arflix.tv.data.model.CollectionSourceKind;
import com.arflix.tv.data.model.CollectionTileShape;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.model.SportsAddonCapabilities;
import com.arflix.tv.data.repository.CatalogRepository;
import com.arflix.tv.data.repository.MediaRepository;
import com.arflix.tv.data.repository.SportsRepository;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import ka.k0;
import ka.m0;
import ka.s0;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.a0;
import kotlin.collections.h0;
import kotlin.collections.z;
import kotlin.text.u;
import na.h1;
import na.j1;
import na.q0;
import na.y0;
import x6.c0;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0005\b\u0007\u0018\u0000 I2\u00020\u0001:\u0001IB!\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\f\u0010\rJ\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J \u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0012H\u0082@¢\u0006\u0004\b\u0015\u0010\u0016J0\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0017H\u0082@¢\u0006\u0004\b\u001b\u0010\u001cJ%\u0010 \u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0002¢\u0006\u0004\b \u0010!J\u001f\u0010\"\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\"\u0010#J\u001f\u0010%\u001a\u00020$2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b%\u0010&J\u001f\u0010)\u001a\u00020$2\u0006\u0010(\u001a\u00020'2\u0006\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b)\u0010*J\u0015\u0010+\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b+\u0010,J\u0015\u0010-\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b-\u0010.J\u001b\u0010/\u001a\u00020\u00142\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d¢\u0006\u0004\b/\u00100JS\u00107\u001a\u00020\u00142\u0006\u00101\u001a\u00020\u001e2\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u0014022.\u00106\u001a*\u0012\u0004\u0012\u000205\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\n\u0012\u0004\u0012\u00020\u001404¢\u0006\u0004\b7\u00108R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u00109R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010:R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010;R\u001a\u0010>\u001a\b\u0012\u0004\u0012\u00020=0<8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b>\u0010?R\u001d\u0010A\u001a\b\u0012\u0004\u0012\u00020=0@8\u0006¢\u0006\f\n\u0004\bA\u0010B\u001a\u0004\bC\u0010DR&\u0010F\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0E0<8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bF\u0010?R)\u0010G\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0E0@8\u0006¢\u0006\f\n\u0004\bG\u0010B\u001a\u0004\bH\u0010D¨\u0006J"}, d2 = {"Lcom/arflix/tv/ui/screens/collections/CollectionDetailsViewModel;", "Landroidx/lifecycle/d1;", "Lcom/arflix/tv/data/repository/CatalogRepository;", "catalogRepository", "Lcom/arflix/tv/data/repository/MediaRepository;", "mediaRepository", "Lcom/arflix/tv/data/repository/SportsRepository;", "sportsRepository", "<init>", "(Lcom/arflix/tv/data/repository/CatalogRepository;Lcom/arflix/tv/data/repository/MediaRepository;Lcom/arflix/tv/data/repository/SportsRepository;)V", "", "catalogId", "normalizeCatalogId", "(Ljava/lang/String;)Ljava/lang/String;", "Lcom/arflix/tv/data/model/CatalogConfig;", "syntheticTmdbCollectionCatalog", "(Ljava/lang/String;)Lcom/arflix/tv/data/model/CatalogConfig;", "catalog", "Lcom/arflix/tv/ui/screens/collections/CollectionTab;", "tab", "Lx6/t0;", "loadInitialTab", "(Lcom/arflix/tv/data/model/CatalogConfig;Lcom/arflix/tv/ui/screens/collections/CollectionTab;Ld7/d;)Ljava/lang/Object;", "", "offset", "limit", "Lcom/arflix/tv/data/repository/MediaRepository$CategoryPageResult;", "loadCollectionPage", "(Lcom/arflix/tv/data/model/CatalogConfig;Lcom/arflix/tv/ui/screens/collections/CollectionTab;IILd7/d;)Ljava/lang/Object;", "", "Lcom/arflix/tv/data/model/MediaItem;", "items", "decorateSportsCatalogWithArtwork", "(Lcom/arflix/tv/data/model/CatalogConfig;Ljava/util/List;)Lcom/arflix/tv/data/model/CatalogConfig;", "catalogForTab", "(Lcom/arflix/tv/data/model/CatalogConfig;Lcom/arflix/tv/ui/screens/collections/CollectionTab;)Lcom/arflix/tv/data/model/CatalogConfig;", "", "supportsTab", "(Lcom/arflix/tv/data/model/CatalogConfig;Lcom/arflix/tv/ui/screens/collections/CollectionTab;)Z", "Lcom/arflix/tv/data/model/CollectionSourceConfig;", "source", "sourceMatchesTab", "(Lcom/arflix/tv/data/model/CollectionSourceConfig;Lcom/arflix/tv/ui/screens/collections/CollectionTab;)Z", "load", "(Ljava/lang/String;)V", "loadMoreIfNeeded", "(Lcom/arflix/tv/ui/screens/collections/CollectionTab;)V", "preloadLogos", "(Ljava/util/List;)V", "item", "Lkotlin/Function0;", "onUnavailable", "Lkotlin/Function5;", "Lcom/arflix/tv/data/model/MediaType;", "onNavigateToPlayer", "openSportsCollectionItem", "(Lcom/arflix/tv/data/model/MediaItem;Lr7/a;Lr7/s;)V", "Lcom/arflix/tv/data/repository/CatalogRepository;", "Lcom/arflix/tv/data/repository/MediaRepository;", "Lcom/arflix/tv/data/repository/SportsRepository;", "Lna/q0;", "Lcom/arflix/tv/ui/screens/collections/CollectionDetailsUiState;", "_uiState", "Lna/q0;", "Lna/h1;", "uiState", "Lna/h1;", "getUiState", "()Lna/h1;", "", "_cardLogoUrls", "cardLogoUrls", "getCardLogoUrls", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CollectionDetailsViewModel extends d1 {

    @Deprecated
    public static final long BACKGROUND_PREFETCH_DELAY_MS = 350;

    @Deprecated
    public static final int FIRST_PAGE = 8;

    @Deprecated
    public static final int PAGE_STEP = 12;

    @Deprecated
    public static final String TMDB_COLLECTION_PREFIX = "tmdb_collection:";
    private final q0<Map<String, String>> _cardLogoUrls;
    private final q0<CollectionDetailsUiState> _uiState;
    private final h1<Map<String, String>> cardLogoUrls;
    private final CatalogRepository catalogRepository;
    private final MediaRepository mediaRepository;
    private final SportsRepository sportsRepository;
    private final h1<CollectionDetailsUiState> uiState;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/arflix/tv/ui/screens/collections/CollectionDetailsViewModel$Companion;", "", "<init>", "()V", "FIRST_PAGE", "", "PAGE_STEP", "BACKGROUND_PREFETCH_DELAY_MS", "", "TMDB_COLLECTION_PREFIX", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        private Companion() {
        }
    }

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
            int[] iArr2 = new int[CollectionSourceKind.values().length];
            try {
                iArr2[CollectionSourceKind.TMDB_COLLECTION.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel$load$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel$load$1", f = "CollectionDetailsScreen.kt", l = {154, 177}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ String $catalogId;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel$load$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel$load$1$1", f = "CollectionDetailsScreen.kt", l = {179, 182}, m = "invokeSuspend", v = 2)
        public static final class C01381 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
            final /* synthetic */ CatalogConfig $catalog;
            final /* synthetic */ CollectionTab $primaryTab;
            Object L$0;
            int label;
            final /* synthetic */ CollectionDetailsViewModel this$0;

            /* JADX INFO: renamed from: com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel$load$1$1$WhenMappings */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            public static final /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

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
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01381(CollectionTab collectionTab, CollectionDetailsViewModel collectionDetailsViewModel, CatalogConfig catalogConfig, d7.d<? super C01381> dVar) {
                super(2, dVar);
                this.$primaryTab = collectionTab;
                this.this$0 = collectionDetailsViewModel;
                this.$catalog = catalogConfig;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                return new C01381(this.$primaryTab, this.this$0, this.$catalog, dVar);
            }

            /* JADX WARN: Code restructure failed: missing block: B:19:0x0050, code lost:
            
                if (r1.loadInitialTab(r3, r5, r20) == r4) goto L20;
             */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r21) {
                /*
                    r20 = this;
                    r0 = r20
                    int r1 = r0.label
                    r2 = 2
                    r3 = 1
                    e7.a r4 = e7.a.f15033i
                    if (r1 == 0) goto L23
                    if (r1 == r3) goto L1f
                    if (r1 != r2) goto L17
                    java.lang.Object r1 = r0.L$0
                    com.arflix.tv.ui.screens.collections.CollectionTab r1 = (com.arflix.tv.ui.screens.collections.CollectionTab) r1
                    k2.c.G(r21)
                    goto Lb9
                L17:
                    java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                    java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                    r1.<init>(r2)
                    throw r1
                L1f:
                    k2.c.G(r21)
                    goto L31
                L23:
                    k2.c.G(r21)
                    r0.label = r3
                    r5 = 1200(0x4b0, double:5.93E-321)
                    java.lang.Object r1 = ka.s0.a(r5, r0)
                    if (r1 != r4) goto L31
                    goto L52
                L31:
                    com.arflix.tv.ui.screens.collections.CollectionTab r1 = r0.$primaryTab
                    com.arflix.tv.ui.screens.collections.CollectionTab r5 = com.arflix.tv.ui.screens.collections.CollectionTab.MOVIES
                    if (r1 != r5) goto L39
                    com.arflix.tv.ui.screens.collections.CollectionTab r5 = com.arflix.tv.ui.screens.collections.CollectionTab.SERIES
                L39:
                    com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel r1 = r0.this$0
                    com.arflix.tv.data.model.CatalogConfig r6 = r0.$catalog
                    boolean r1 = com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel.access$supportsTab(r1, r6, r5)
                    if (r1 == 0) goto L53
                    com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel r1 = r0.this$0
                    com.arflix.tv.data.model.CatalogConfig r3 = r0.$catalog
                    r6 = 0
                    r0.L$0 = r6
                    r0.label = r2
                    java.lang.Object r1 = com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel.access$loadInitialTab(r1, r3, r5, r0)
                    if (r1 != r4) goto Lb9
                L52:
                    return r4
                L53:
                    com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel r1 = r0.this$0
                    na.q0 r1 = com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel.access$get_uiState$p(r1)
                    int[] r4 = com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel.AnonymousClass1.C01381.WhenMappings.$EnumSwitchMapping$0
                    int r5 = r5.ordinal()
                    r4 = r4[r5]
                    if (r4 == r3) goto L91
                    if (r4 != r2) goto L8b
                    com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel r2 = r0.this$0
                    na.q0 r2 = com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel.access$get_uiState$p(r2)
                    java.lang.Object r2 = r2.getValue()
                    r3 = r2
                    com.arflix.tv.ui.screens.collections.CollectionDetailsUiState r3 = (com.arflix.tv.ui.screens.collections.CollectionDetailsUiState) r3
                    r18 = 16319(0x3fbf, float:2.2868E-41)
                    r19 = 0
                    r4 = 0
                    r5 = 0
                    r6 = 0
                    r7 = 0
                    r8 = 0
                    r9 = 0
                    r10 = 0
                    r11 = 0
                    r12 = 0
                    r13 = 0
                    r14 = 0
                    r15 = 0
                    r16 = 0
                    r17 = 0
                    com.arflix.tv.ui.screens.collections.CollectionDetailsUiState r2 = com.arflix.tv.ui.screens.collections.CollectionDetailsUiState.copy$default(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
                    goto Lb6
                L8b:
                    kotlin.NoWhenBranchMatchedException r1 = new kotlin.NoWhenBranchMatchedException
                    r1.<init>()
                    throw r1
                L91:
                    com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel r2 = r0.this$0
                    na.q0 r2 = com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel.access$get_uiState$p(r2)
                    java.lang.Object r2 = r2.getValue()
                    r3 = r2
                    com.arflix.tv.ui.screens.collections.CollectionDetailsUiState r3 = (com.arflix.tv.ui.screens.collections.CollectionDetailsUiState) r3
                    r18 = 16351(0x3fdf, float:2.2913E-41)
                    r19 = 0
                    r4 = 0
                    r5 = 0
                    r6 = 0
                    r7 = 0
                    r8 = 0
                    r9 = 0
                    r10 = 0
                    r11 = 0
                    r12 = 0
                    r13 = 0
                    r14 = 0
                    r15 = 0
                    r16 = 0
                    r17 = 0
                    com.arflix.tv.ui.screens.collections.CollectionDetailsUiState r2 = com.arflix.tv.ui.screens.collections.CollectionDetailsUiState.copy$default(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
                Lb6:
                    r1.setValue(r2)
                Lb9:
                    x6.t0 r1 = x6.t0.f22605a
                    return r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel.AnonymousClass1.C01381.invokeSuspend(java.lang.Object):java.lang.Object");
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
                return ((C01381) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(String str, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$catalogId = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass1 anonymousClass1 = CollectionDetailsViewModel.this.new AnonymousClass1(this.$catalogId, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        /* JADX WARN: Code restructure failed: missing block: B:24:0x00bd, code lost:
        
            if (r4 == r7) goto L50;
         */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00fa  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x0120  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r27) {
            /*
                Method dump skipped, instruction units count: 416
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel$loadInitialTab$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel", f = "CollectionDetailsScreen.kt", l = {229}, m = "loadInitialTab", v = 2)
    public static final class C13061 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C13061(d7.d<? super C13061> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CollectionDetailsViewModel.this.loadInitialTab(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel$loadInitialTab$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel$loadInitialTab$2", f = "CollectionDetailsScreen.kt", l = {260}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ CollectionTab $tab;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(CollectionTab collectionTab, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$tab = collectionTab;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return CollectionDetailsViewModel.this.new AnonymousClass2(this.$tab, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                this.label = 1;
                Object objA = s0.a(350L, this);
                e7.a aVar = e7.a.f15033i;
                if (objA == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            CollectionDetailsViewModel.this.loadMoreIfNeeded(this.$tab);
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel$loadMoreIfNeeded$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel$loadMoreIfNeeded$1", f = "CollectionDetailsScreen.kt", l = {284}, m = "invokeSuspend", v = 2)
    public static final class C13071 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ CatalogConfig $catalog;
        final /* synthetic */ CollectionDetailsUiState $state;
        final /* synthetic */ CollectionTab $tab;
        int I$0;
        int I$1;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel$loadMoreIfNeeded$1$WhenMappings */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

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
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C13071(CatalogConfig catalogConfig, CollectionTab collectionTab, CollectionDetailsUiState collectionDetailsUiState, d7.d<? super C13071> dVar) {
            super(2, dVar);
            this.$catalog = catalogConfig;
            this.$tab = collectionTab;
            this.$state = collectionDetailsUiState;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C13071 c13071 = CollectionDetailsViewModel.this.new C13071(this.$catalog, this.$tab, this.$state, dVar);
            c13071.L$0 = obj;
            return c13071;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object c0Var;
            List<MediaItem> items;
            ArrayList arrayList;
            HashSet hashSet;
            CollectionDetailsUiState collectionDetailsUiStateCopy;
            int loadedMovieOffset;
            Object objLoadCollectionPage;
            int i10 = this.label;
            try {
                if (i10 == 0) {
                    k2.c.G(obj);
                    CatalogConfig catalogConfigCatalogForTab = CollectionDetailsViewModel.this.catalogForTab(this.$catalog, this.$tab);
                    int i11 = WhenMappings.$EnumSwitchMapping$0[this.$tab.ordinal()];
                    if (i11 == 1) {
                        loadedMovieOffset = this.$state.getLoadedMovieOffset();
                    } else {
                        if (i11 != 2) {
                            throw new NoWhenBranchMatchedException();
                        }
                        loadedMovieOffset = this.$state.getLoadedSeriesOffset();
                    }
                    int i12 = loadedMovieOffset;
                    CollectionDetailsViewModel collectionDetailsViewModel = CollectionDetailsViewModel.this;
                    CollectionTab collectionTab = this.$tab;
                    this.L$0 = null;
                    this.L$1 = null;
                    this.L$2 = null;
                    this.I$0 = i12;
                    this.I$1 = 0;
                    this.label = 1;
                    objLoadCollectionPage = collectionDetailsViewModel.loadCollectionPage(catalogConfigCatalogForTab, collectionTab, i12, 12, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objLoadCollectionPage == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    objLoadCollectionPage = obj;
                }
                c0Var = (MediaRepository.CategoryPageResult) objLoadCollectionPage;
            } catch (Throwable th) {
                c0Var = new c0(th);
            }
            if (c0Var instanceof c0) {
                c0Var = null;
            }
            MediaRepository.CategoryPageResult categoryPageResult = (MediaRepository.CategoryPageResult) c0Var;
            int i13 = WhenMappings.$EnumSwitchMapping$0[this.$tab.ordinal()];
            List<MediaItem> list = z.f19728i;
            if (i13 == 1) {
                items = categoryPageResult != null ? categoryPageResult.getItems() : null;
                if (items != null) {
                    list = items;
                }
                arrayList = new ArrayList();
                for (Object obj2 : list) {
                    if (((MediaItem) obj2).getMediaType() == MediaType.MOVIE) {
                        arrayList.add(obj2);
                    }
                }
            } else {
                if (i13 != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                items = categoryPageResult != null ? categoryPageResult.getItems() : null;
                if (items != null) {
                    list = items;
                }
                arrayList = new ArrayList();
                for (Object obj3 : list) {
                    if (((MediaItem) obj3).getMediaType() == MediaType.TV) {
                        arrayList.add(obj3);
                    }
                }
            }
            int i14 = WhenMappings.$EnumSwitchMapping$0[this.$tab.ordinal()];
            if (i14 == 1) {
                List<MediaItem> movieItems = this.$state.getMovieItems();
                hashSet = new HashSet();
                for (MediaItem mediaItem : movieItems) {
                    hashSet.add(new x(new Integer(mediaItem.getId()), mediaItem.getMediaType()));
                }
            } else {
                if (i14 != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                List<MediaItem> seriesItems = this.$state.getSeriesItems();
                hashSet = new HashSet();
                for (MediaItem mediaItem2 : seriesItems) {
                    hashSet.add(new x(new Integer(mediaItem2.getId()), mediaItem2.getMediaType()));
                }
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj4 : arrayList) {
                MediaItem mediaItem3 = (MediaItem) obj4;
                if (!hashSet.contains(new x(new Integer(mediaItem3.getId()), mediaItem3.getMediaType()))) {
                    arrayList2.add(obj4);
                }
            }
            q0 q0Var = CollectionDetailsViewModel.this._uiState;
            int i15 = WhenMappings.$EnumSwitchMapping$0[this.$tab.ordinal()];
            if (i15 == 1) {
                CollectionDetailsUiState collectionDetailsUiState = (CollectionDetailsUiState) CollectionDetailsViewModel.this._uiState.getValue();
                collectionDetailsUiStateCopy = collectionDetailsUiState.copy((16319 & 1) != 0 ? collectionDetailsUiState.catalog : null, (16319 & 2) != 0 ? collectionDetailsUiState.movieItems : kotlin.collections.x.I0(this.$state.getMovieItems(), arrayList2), (16319 & 4) != 0 ? collectionDetailsUiState.seriesItems : null, (16319 & 8) != 0 ? collectionDetailsUiState.supportsMovies : false, (16319 & 16) != 0 ? collectionDetailsUiState.supportsSeries : false, (16319 & 32) != 0 ? collectionDetailsUiState.isLoadingMovies : false, (16319 & 64) != 0 ? collectionDetailsUiState.isLoadingSeries : false, (16319 & 128) != 0 ? collectionDetailsUiState.isLoadingMoreMovies : false, (16319 & 256) != 0 ? collectionDetailsUiState.isLoadingMoreSeries : false, (16319 & 512) != 0 ? collectionDetailsUiState.hasMoreMovies : categoryPageResult != null && categoryPageResult.getHasMore(), (16319 & 1024) != 0 ? collectionDetailsUiState.hasMoreSeries : false, (16319 & 2048) != 0 ? collectionDetailsUiState.loadedMovieOffset : arrayList.size() + this.$state.getLoadedMovieOffset(), (16319 & 4096) != 0 ? collectionDetailsUiState.loadedSeriesOffset : 0, (16319 & 8192) != 0 ? collectionDetailsUiState.error : null);
            } else {
                if (i15 != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                CollectionDetailsUiState collectionDetailsUiState2 = (CollectionDetailsUiState) CollectionDetailsViewModel.this._uiState.getValue();
                collectionDetailsUiStateCopy = collectionDetailsUiState2.copy((16319 & 1) != 0 ? collectionDetailsUiState2.catalog : null, (16319 & 2) != 0 ? collectionDetailsUiState2.movieItems : null, (16319 & 4) != 0 ? collectionDetailsUiState2.seriesItems : kotlin.collections.x.I0(this.$state.getSeriesItems(), arrayList2), (16319 & 8) != 0 ? collectionDetailsUiState2.supportsMovies : false, (16319 & 16) != 0 ? collectionDetailsUiState2.supportsSeries : false, (16319 & 32) != 0 ? collectionDetailsUiState2.isLoadingMovies : false, (16319 & 64) != 0 ? collectionDetailsUiState2.isLoadingSeries : false, (16319 & 128) != 0 ? collectionDetailsUiState2.isLoadingMoreMovies : false, (16319 & 256) != 0 ? collectionDetailsUiState2.isLoadingMoreSeries : false, (16319 & 512) != 0 ? collectionDetailsUiState2.hasMoreMovies : false, (16319 & 1024) != 0 ? collectionDetailsUiState2.hasMoreSeries : categoryPageResult != null && categoryPageResult.getHasMore(), (16319 & 2048) != 0 ? collectionDetailsUiState2.loadedMovieOffset : 0, (16319 & 4096) != 0 ? collectionDetailsUiState2.loadedSeriesOffset : arrayList.size() + this.$state.getLoadedSeriesOffset(), (16319 & 8192) != 0 ? collectionDetailsUiState2.error : null);
            }
            q0Var.setValue(collectionDetailsUiStateCopy);
            CollectionDetailsViewModel.this.preloadLogos(arrayList2);
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C13071) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel$openSportsCollectionItem$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel$openSportsCollectionItem$1", f = "CollectionDetailsScreen.kt", l = {366}, m = "invokeSuspend", v = 2)
    public static final class C13081 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ MediaItem $item;
        final /* synthetic */ r7.s<MediaType, Integer, String, String, String, t0> $onNavigateToPlayer;
        final /* synthetic */ r7.a<t0> $onUnavailable;
        final /* synthetic */ String $status;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C13081(String str, MediaItem mediaItem, r7.a<t0> aVar, r7.s<? super MediaType, ? super Integer, ? super String, ? super String, ? super String, t0> sVar, d7.d<? super C13081> dVar) {
            super(2, dVar);
            this.$status = str;
            this.$item = mediaItem;
            this.$onUnavailable = aVar;
            this.$onNavigateToPlayer = sVar;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return CollectionDetailsViewModel.this.new C13081(this.$status, this.$item, this.$onUnavailable, this.$onNavigateToPlayer, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                SportsRepository sportsRepository = CollectionDetailsViewModel.this.sportsRepository;
                String str = this.$status;
                String title = this.$item.getTitle();
                this.label = 1;
                obj = sportsRepository.resolvePlayback(str, title, this);
                e7.a aVar = e7.a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SportsRepository.SportsPlayback sportsPlayback = (SportsRepository.SportsPlayback) obj;
            t0 t0Var = t0.f22605a;
            if (sportsPlayback == null) {
                this.$onUnavailable.invoke();
                return t0Var;
            }
            this.$onNavigateToPlayer.invoke(MediaType.TV, new Integer(sportsPlayback.getMediaId()), sportsPlayback.getStreamUrl(), sportsPlayback.getAddonId(), sportsPlayback.getSourceName());
            return t0Var;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C13081) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel$preloadLogos$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel$preloadLogos$1", f = "CollectionDetailsScreen.kt", l = {346}, m = "invokeSuspend", v = 2)
    public static final class C13091 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ List<MediaItem> $items;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C13091(List<MediaItem> list, d7.d<? super C13091> dVar) {
            super(2, dVar);
            this.$items = list;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C13091 c13091 = CollectionDetailsViewModel.this.new C13091(this.$items, dVar);
            c13091.L$0 = obj;
            return c13091;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Map mapA0;
            k0 k0Var = (k0) this.L$0;
            int i10 = this.label;
            t0 t0Var = t0.f22605a;
            if (i10 == 0) {
                k2.c.G(obj);
                LinkedHashMap linkedHashMap = new LinkedHashMap((Map) CollectionDetailsViewModel.this._cardLogoUrls.getValue());
                List<MediaItem> list = this.$items;
                ArrayList arrayList = new ArrayList();
                for (Object obj2 : list) {
                    if (!SportsAddonCapabilities.INSTANCE.isSportsEventStatus(((MediaItem) obj2).getStatus())) {
                        arrayList.add(obj2);
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                for (Object obj3 : arrayList) {
                    MediaItem mediaItem = (MediaItem) obj3;
                    if (!linkedHashMap.containsKey(mediaItem.getMediaType() + "_" + mediaItem.getId())) {
                        arrayList2.add(obj3);
                    }
                }
                List<MediaItem> listX0 = kotlin.collections.x.X0(arrayList2, 2);
                if (!listX0.isEmpty()) {
                    CollectionDetailsViewModel collectionDetailsViewModel = CollectionDetailsViewModel.this;
                    for (MediaItem mediaItem2 : listX0) {
                        String strPeekCachedLogoUrl = collectionDetailsViewModel.mediaRepository.peekCachedLogoUrl(mediaItem2.getMediaType(), mediaItem2.getId());
                        if (strPeekCachedLogoUrl != null) {
                            linkedHashMap.put(mediaItem2.getMediaType() + "_" + mediaItem2.getId(), strPeekCachedLogoUrl);
                        }
                    }
                    CollectionDetailsViewModel.this._cardLogoUrls.setValue(h0.B0(linkedHashMap));
                    ArrayList arrayList3 = new ArrayList();
                    for (Object obj4 : listX0) {
                        MediaItem mediaItem3 = (MediaItem) obj4;
                        if (!linkedHashMap.containsKey(mediaItem3.getMediaType() + "_" + mediaItem3.getId())) {
                            arrayList3.add(obj4);
                        }
                    }
                    if (!arrayList3.isEmpty()) {
                        CollectionDetailsViewModel collectionDetailsViewModel2 = CollectionDetailsViewModel.this;
                        ArrayList arrayList4 = new ArrayList(kotlin.collections.s.U(arrayList3, 10));
                        Iterator it = arrayList3.iterator();
                        while (it.hasNext()) {
                            arrayList4.add(m0.e(3, null, k0Var, new CollectionDetailsViewModel$preloadLogos$1$fetched$1$1((MediaItem) it.next(), collectionDetailsViewModel2, null)));
                        }
                        this.L$0 = null;
                        this.L$1 = null;
                        this.L$2 = null;
                        this.L$3 = null;
                        this.label = 1;
                        obj = m0.f(arrayList4, this);
                        e7.a aVar = e7.a.f15033i;
                        if (obj == aVar) {
                            return aVar;
                        }
                    }
                }
                return t0Var;
            }
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            ArrayList arrayListK0 = kotlin.collections.x.k0((Iterable) obj);
            if (!arrayListK0.isEmpty()) {
                q0 q0Var = CollectionDetailsViewModel.this._cardLogoUrls;
                Map map = (Map) CollectionDetailsViewModel.this._cardLogoUrls.getValue();
                if (map.isEmpty()) {
                    mapA0 = h0.A0(arrayListK0);
                } else {
                    LinkedHashMap linkedHashMap2 = new LinkedHashMap(map);
                    h0.x0(linkedHashMap2, arrayListK0);
                    mapA0 = linkedHashMap2;
                }
                q0Var.setValue(h0.B0(mapA0));
            }
            return t0Var;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C13091) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    @Inject
    public CollectionDetailsViewModel(CatalogRepository catalogRepository, MediaRepository mediaRepository, SportsRepository sportsRepository) {
        this.catalogRepository = catalogRepository;
        this.mediaRepository = mediaRepository;
        this.sportsRepository = sportsRepository;
        j1 j1VarB = y0.b(new CollectionDetailsUiState(null, null, null, false, false, false, false, false, false, false, false, 0, 0, null, 16383, null));
        this._uiState = j1VarB;
        this.uiState = y0.e(j1VarB);
        j1 j1VarB2 = y0.b(a0.f19683i);
        this._cardLogoUrls = j1VarB2;
        this.cardLogoUrls = y0.e(j1VarB2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CatalogConfig catalogForTab(CatalogConfig catalog, CollectionTab tab) {
        List<CollectionSourceConfig> collectionSources = catalog.getCollectionSources();
        ArrayList arrayList = new ArrayList();
        for (Object obj : collectionSources) {
            if (sourceMatchesTab((CollectionSourceConfig) obj, tab)) {
                arrayList.add(obj);
            }
        }
        return CatalogConfig.copy$default(catalog, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, arrayList, null, null, null, 31457279, null);
    }

    private final CatalogConfig decorateSportsCatalogWithArtwork(CatalogConfig catalog, List<MediaItem> items) {
        String image;
        Object next;
        Object next2;
        String backdrop;
        if (!SportsAddonCapabilities.INSTANCE.isSportsCollectionCatalogId(catalog.getId())) {
            return catalog;
        }
        Iterator<T> it = items.iterator();
        while (true) {
            image = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            String backdrop2 = ((MediaItem) next).getBackdrop();
            if (!(backdrop2 == null || kotlin.text.o.h0(backdrop2))) {
                break;
            }
        }
        MediaItem mediaItem = (MediaItem) next;
        if (mediaItem == null || (backdrop = mediaItem.getBackdrop()) == null) {
            Iterator<T> it2 = items.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    next2 = null;
                    break;
                }
                next2 = it2.next();
                if (!kotlin.text.o.h0(((MediaItem) next2).getImage())) {
                    break;
                }
            }
            MediaItem mediaItem2 = (MediaItem) next2;
            if (mediaItem2 != null) {
                image = mediaItem2.getImage();
            }
        } else {
            image = backdrop;
        }
        if (image == null || kotlin.text.o.h0(image)) {
            return catalog;
        }
        String collectionCoverImageUrl = catalog.getCollectionCoverImageUrl();
        String str = collectionCoverImageUrl == null ? image : collectionCoverImageUrl;
        String collectionHeroImageUrl = catalog.getCollectionHeroImageUrl();
        String str2 = collectionHeroImageUrl == null ? image : collectionHeroImageUrl;
        String collectionFocusGifUrl = catalog.getCollectionFocusGifUrl();
        return CatalogConfig.copy$default(catalog, null, null, null, null, null, false, null, null, null, null, null, null, null, str, collectionFocusGifUrl == null ? image : collectionFocusGifUrl, str2, null, null, null, null, false, null, null, null, null, 33497087, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object loadCollectionPage(CatalogConfig catalogConfig, CollectionTab collectionTab, int i10, int i11, d7.d<? super MediaRepository.CategoryPageResult> dVar) {
        return SportsAddonCapabilities.INSTANCE.isSportsCollectionCatalogId(catalogConfig.getId()) ? this.sportsRepository.loadSportsCollectionPage(catalogConfig.getId(), i10, i11, dVar) : this.mediaRepository.loadCollectionCatalogPage(catalogForTab(catalogConfig, collectionTab), i10, i11, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadInitialTab(com.arflix.tv.data.model.CatalogConfig r31, com.arflix.tv.ui.screens.collections.CollectionTab r32, d7.d<? super x6.t0> r33) {
        /*
            Method dump skipped, instruction units count: 492
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel.loadInitialTab(com.arflix.tv.data.model.CatalogConfig, com.arflix.tv.ui.screens.collections.CollectionTab, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String normalizeCatalogId(String catalogId) {
        Object c0Var;
        String string = kotlin.text.o.L0(catalogId).toString();
        try {
            c0Var = URLDecoder.decode(string, "UTF-8");
        } catch (Throwable th) {
            c0Var = new c0(th);
        }
        Object obj = string;
        if (!(c0Var instanceof c0)) {
            obj = c0Var;
        }
        return (String) obj;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean sourceMatchesTab(com.arflix.tv.data.model.CollectionSourceConfig r4, com.arflix.tv.ui.screens.collections.CollectionTab r5) {
        /*
            r3 = this;
            java.lang.String r0 = r4.getMediaType()
            if (r0 == 0) goto L17
            java.lang.CharSequence r0 = kotlin.text.o.L0(r0)
            java.lang.String r0 = r0.toString()
            if (r0 == 0) goto L17
            java.util.Locale r1 = java.util.Locale.ROOT
            java.lang.String r0 = r0.toLowerCase(r1)
            goto L18
        L17:
            r0 = 0
        L18:
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L92
            int r4 = r0.hashCode()
            switch(r4) {
                case 96673: goto L3f;
                case 96748: goto L36;
                case 3029889: goto L2d;
                case 103910395: goto L24;
                default: goto L23;
            }
        L23:
            goto L47
        L24:
            java.lang.String r4 = "mixed"
            boolean r4 = r0.equals(r4)
            if (r4 != 0) goto L91
            goto L47
        L2d:
            java.lang.String r4 = "both"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L47
            goto L91
        L36:
            java.lang.String r4 = "any"
            boolean r4 = r0.equals(r4)
            if (r4 != 0) goto L91
            goto L47
        L3f:
            java.lang.String r4 = "all"
            boolean r4 = r0.equals(r4)
            if (r4 != 0) goto L91
        L47:
            int[] r4 = com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel.WhenMappings.$EnumSwitchMapping$0
            int r5 = r5.ordinal()
            r4 = r4[r5]
            if (r4 == r2) goto L7f
            r5 = 2
            if (r4 != r5) goto L79
            java.lang.String r4 = "series"
            boolean r4 = r0.equals(r4)
            if (r4 != 0) goto L78
            java.lang.String r4 = "tv"
            boolean r4 = r0.equals(r4)
            if (r4 != 0) goto L78
            java.lang.String r4 = "show"
            boolean r4 = r0.equals(r4)
            if (r4 != 0) goto L78
            java.lang.String r4 = "anime"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L77
            goto L78
        L77:
            return r1
        L78:
            return r2
        L79:
            kotlin.NoWhenBranchMatchedException r4 = new kotlin.NoWhenBranchMatchedException
            r4.<init>()
            throw r4
        L7f:
            java.lang.String r4 = "movie"
            boolean r4 = r0.equals(r4)
            if (r4 != 0) goto L91
            java.lang.String r4 = "film"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L90
            goto L91
        L90:
            return r1
        L91:
            return r2
        L92:
            com.arflix.tv.data.model.CollectionSourceKind r4 = r4.getKind()
            int[] r0 = com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel.WhenMappings.$EnumSwitchMapping$1
            int r4 = r4.ordinal()
            r4 = r0[r4]
            if (r4 != r2) goto La6
            com.arflix.tv.ui.screens.collections.CollectionTab r4 = com.arflix.tv.ui.screens.collections.CollectionTab.MOVIES
            if (r5 != r4) goto La5
            return r2
        La5:
            return r1
        La6:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel.sourceMatchesTab(com.arflix.tv.data.model.CollectionSourceConfig, com.arflix.tv.ui.screens.collections.CollectionTab):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean supportsTab(CatalogConfig catalog, CollectionTab tab) {
        List<CollectionSourceConfig> collectionSources = catalog.getCollectionSources();
        if (collectionSources != null && collectionSources.isEmpty()) {
            return false;
        }
        Iterator<T> it = collectionSources.iterator();
        while (it.hasNext()) {
            if (sourceMatchesTab((CollectionSourceConfig) it.next(), tab)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final CatalogConfig syntheticTmdbCollectionCatalog(String catalogId) {
        if (u.P(catalogId, TMDB_COLLECTION_PREFIX, false)) {
            String strR0 = kotlin.text.o.r0(catalogId, TMDB_COLLECTION_PREFIX);
            Integer numR = u.R(kotlin.text.o.H0(strR0, ":"));
            if (numR != null) {
                String string = kotlin.text.o.L0(kotlin.text.o.D0(strR0, ":", "")).toString();
                String str = kotlin.text.o.h0(string) ? null : string;
                if (str == null) {
                    str = "Collection";
                }
                String str2 = null;
                String str3 = null;
                String str4 = null;
                String str5 = null;
                String str6 = null;
                String str7 = null;
                return new CatalogConfig(catalogId, str, CatalogSourceType.PREINSTALLED, null, null, true, null, str2, str3, str4, CatalogKind.COLLECTION, CollectionGroupKind.FRANCHISE, null, 0 == true ? 1 : 0, 0 == true ? 1 : 0, str5, str6, 0 == true ? 1 : 0, str7, CollectionTileShape.POSTER, false, Collections.singletonList(new CollectionSourceConfig(CollectionSourceKind.TMDB_COLLECTION, "movie", str2, str3, str4, null, null, numR, null, null, str5, str6, null, str7, 16252, null)), null, null, null, 30929880, null);
            }
        }
        return null;
    }

    public final h1<Map<String, String>> getCardLogoUrls() {
        return this.cardLogoUrls;
    }

    public final h1<CollectionDetailsUiState> getUiState() {
        return this.uiState;
    }

    public final void load(String catalogId) {
        m0.p(z0.h(this), null, 0, new AnonymousClass1(catalogId, null), 3);
    }

    public final void loadMoreIfNeeded(CollectionTab tab) {
        CatalogConfig catalogConfig;
        q0<CollectionDetailsUiState> q0Var;
        CollectionDetailsUiState collectionDetailsUiStateCopy;
        CollectionDetailsUiState collectionDetailsUiState = (CollectionDetailsUiState) this._uiState.getValue();
        CatalogConfig catalog = collectionDetailsUiState.getCatalog();
        if (catalog == null) {
            return;
        }
        int[] iArr = WhenMappings.$EnumSwitchMapping$0;
        int i10 = iArr[tab.ordinal()];
        if (i10 != 1) {
            if (i10 != 2) {
                throw new NoWhenBranchMatchedException();
            }
            if (collectionDetailsUiState.isLoadingSeries() || collectionDetailsUiState.isLoadingMoreSeries() || !collectionDetailsUiState.getHasMoreSeries()) {
                return;
            }
        } else if (collectionDetailsUiState.isLoadingMovies() || collectionDetailsUiState.isLoadingMoreMovies() || !collectionDetailsUiState.getHasMoreMovies()) {
            return;
        }
        q0<CollectionDetailsUiState> q0Var2 = this._uiState;
        int i11 = iArr[tab.ordinal()];
        if (i11 == 1) {
            catalogConfig = catalog;
            q0Var = q0Var2;
            collectionDetailsUiStateCopy = collectionDetailsUiState.copy((16319 & 1) != 0 ? collectionDetailsUiState.catalog : null, (16319 & 2) != 0 ? collectionDetailsUiState.movieItems : null, (16319 & 4) != 0 ? collectionDetailsUiState.seriesItems : null, (16319 & 8) != 0 ? collectionDetailsUiState.supportsMovies : false, (16319 & 16) != 0 ? collectionDetailsUiState.supportsSeries : false, (16319 & 32) != 0 ? collectionDetailsUiState.isLoadingMovies : false, (16319 & 64) != 0 ? collectionDetailsUiState.isLoadingSeries : false, (16319 & 128) != 0 ? collectionDetailsUiState.isLoadingMoreMovies : true, (16319 & 256) != 0 ? collectionDetailsUiState.isLoadingMoreSeries : false, (16319 & 512) != 0 ? collectionDetailsUiState.hasMoreMovies : false, (16319 & 1024) != 0 ? collectionDetailsUiState.hasMoreSeries : false, (16319 & 2048) != 0 ? collectionDetailsUiState.loadedMovieOffset : 0, (16319 & 4096) != 0 ? collectionDetailsUiState.loadedSeriesOffset : 0, (16319 & 8192) != 0 ? collectionDetailsUiState.error : null);
        } else {
            if (i11 != 2) {
                throw new NoWhenBranchMatchedException();
            }
            catalogConfig = catalog;
            q0Var = q0Var2;
            collectionDetailsUiStateCopy = collectionDetailsUiState.copy((16319 & 1) != 0 ? collectionDetailsUiState.catalog : null, (16319 & 2) != 0 ? collectionDetailsUiState.movieItems : null, (16319 & 4) != 0 ? collectionDetailsUiState.seriesItems : null, (16319 & 8) != 0 ? collectionDetailsUiState.supportsMovies : false, (16319 & 16) != 0 ? collectionDetailsUiState.supportsSeries : false, (16319 & 32) != 0 ? collectionDetailsUiState.isLoadingMovies : false, (16319 & 64) != 0 ? collectionDetailsUiState.isLoadingSeries : false, (16319 & 128) != 0 ? collectionDetailsUiState.isLoadingMoreMovies : false, (16319 & 256) != 0 ? collectionDetailsUiState.isLoadingMoreSeries : true, (16319 & 512) != 0 ? collectionDetailsUiState.hasMoreMovies : false, (16319 & 1024) != 0 ? collectionDetailsUiState.hasMoreSeries : false, (16319 & 2048) != 0 ? collectionDetailsUiState.loadedMovieOffset : 0, (16319 & 4096) != 0 ? collectionDetailsUiState.loadedSeriesOffset : 0, (16319 & 8192) != 0 ? collectionDetailsUiState.error : null);
        }
        q0Var.setValue(collectionDetailsUiStateCopy);
        m0.p(z0.h(this), null, 0, new C13071(catalogConfig, tab, collectionDetailsUiState, null), 3);
    }

    public final void openSportsCollectionItem(MediaItem item, r7.a<t0> onUnavailable, r7.s<? super MediaType, ? super Integer, ? super String, ? super String, ? super String, t0> onNavigateToPlayer) {
        String status = item.getStatus();
        if (status == null) {
            status = "";
        }
        String str = status;
        if (SportsAddonCapabilities.INSTANCE.isSportsEventStatus(str)) {
            if (u.L(item.getBadge(), "LIVE", true)) {
                m0.p(z0.h(this), null, 0, new C13081(str, item, onUnavailable, onNavigateToPlayer, null), 3);
            } else {
                onUnavailable.invoke();
            }
        }
    }

    public final void preloadLogos(List<MediaItem> items) {
        if (items.isEmpty()) {
            return;
        }
        m0.p(z0.h(this), null, 0, new C13091(items, null), 3);
    }
}
