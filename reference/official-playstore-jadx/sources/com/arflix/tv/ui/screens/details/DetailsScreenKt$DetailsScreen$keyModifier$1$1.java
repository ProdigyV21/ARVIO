package com.arflix.tv.ui.screens.details;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.input.key.KeyEvent;
import com.arflix.tv.data.model.Episode;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.ui.components.SidebarItem;
import com.arflix.tv.ui.focus.ArvioDpadRepeatGate;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final class DetailsScreenKt$DetailsScreen$keyModifier$1$1 implements r7.l<KeyEvent, Boolean> {
    final /* synthetic */ MutableIntState $autoPlayWaitTick$delegate;
    final /* synthetic */ MutableIntState $buttonIndex$delegate;
    final /* synthetic */ MutableIntState $castIndex$delegate;
    final /* synthetic */ MutableIntState $collectionIndex$delegate;
    final /* synthetic */ MutableState<Episode> $contextMenuEpisode$delegate;
    final /* synthetic */ MutableIntState $contextMenuSeason$delegate;
    final /* synthetic */ ArvioDpadRepeatGate $dpadRepeatGate;
    final /* synthetic */ MutableIntState $episodeIndex$delegate;
    final /* synthetic */ MutableState<FocusSection> $focusedSection$delegate;
    final /* synthetic */ boolean $hasProfile;
    final /* synthetic */ boolean $isRtlLayoutDirection;
    final /* synthetic */ MutableState<Boolean> $isSidebarFocused$delegate;
    final /* synthetic */ int $maxSidebarIndex;
    final /* synthetic */ MediaType $mediaType;
    final /* synthetic */ r7.a<x6.t0> $onBack;
    final /* synthetic */ r7.p<MediaType, Integer, x6.t0> $onNavigateToDetails;
    final /* synthetic */ r7.a<x6.t0> $onNavigateToHome;
    final /* synthetic */ r7.a<x6.t0> $onNavigateToSearch;
    final /* synthetic */ r7.a<x6.t0> $onNavigateToSettings;
    final /* synthetic */ r7.a<x6.t0> $onNavigateToTv;
    final /* synthetic */ r7.a<x6.t0> $onNavigateToWatchlist;
    final /* synthetic */ r7.a<x6.t0> $onSwitchProfile;
    final /* synthetic */ MutableState<PendingAutoPlayRequest> $pendingAutoPlayRequest$delegate;
    final /* synthetic */ MutableIntState $ratingsIndex$delegate;
    final /* synthetic */ MutableIntState $reviewIndex$delegate;
    final /* synthetic */ MutableIntState $seasonIndex$delegate;
    final /* synthetic */ MutableLongState $seasonSelectDownAtMs$delegate;
    final /* synthetic */ MutableState<Boolean> $showEpisodeContextMenu$delegate;
    final /* synthetic */ MutableState<Boolean> $showSeasonContextMenu$delegate;
    final /* synthetic */ MutableState<Boolean> $showStreamSelector$delegate;
    final /* synthetic */ MutableState<Boolean> $showTrailerPlayer$delegate;
    final /* synthetic */ MutableIntState $sidebarFocusIndex$delegate;
    final /* synthetic */ MutableIntState $similarIndex$delegate;
    final /* synthetic */ State<DetailsUiState> $uiState$delegate;
    final /* synthetic */ DetailsViewModel $viewModel;

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[FocusSection.values().length];
            try {
                iArr[FocusSection.BUTTONS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FocusSection.EPISODES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FocusSection.RATINGS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[FocusSection.SEASONS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[FocusSection.CAST.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[FocusSection.REVIEWS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[FocusSection.SIMILAR.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[FocusSection.COLLECTION.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[SidebarItem.values().length];
            try {
                iArr2[SidebarItem.SEARCH.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr2[SidebarItem.HOME.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr2[SidebarItem.WATCHLIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr2[SidebarItem.TV.ordinal()] = 4;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr2[SidebarItem.SETTINGS.ordinal()] = 5;
            } catch (NoSuchFieldError unused13) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DetailsScreenKt$DetailsScreen$keyModifier$1$1(ArvioDpadRepeatGate arvioDpadRepeatGate, boolean z, r7.a<x6.t0> aVar, int i10, MediaType mediaType, boolean z5, r7.a<x6.t0> aVar2, r7.a<x6.t0> aVar3, r7.a<x6.t0> aVar4, r7.a<x6.t0> aVar5, r7.a<x6.t0> aVar6, r7.a<x6.t0> aVar7, DetailsViewModel detailsViewModel, r7.p<? super MediaType, ? super Integer, x6.t0> pVar, MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, MutableState<Boolean> mutableState3, State<DetailsUiState> state, MutableState<Boolean> mutableState4, MutableState<Boolean> mutableState5, MutableIntState mutableIntState, MutableState<FocusSection> mutableState6, MutableIntState mutableIntState2, MutableIntState mutableIntState3, MutableIntState mutableIntState4, MutableIntState mutableIntState5, MutableIntState mutableIntState6, MutableIntState mutableIntState7, MutableIntState mutableIntState8, MutableIntState mutableIntState9, MutableLongState mutableLongState, MutableIntState mutableIntState10, MutableState<PendingAutoPlayRequest> mutableState7, MutableState<Episode> mutableState8, MutableIntState mutableIntState11) {
        this.$dpadRepeatGate = arvioDpadRepeatGate;
        this.$isRtlLayoutDirection = z;
        this.$onBack = aVar;
        this.$maxSidebarIndex = i10;
        this.$mediaType = mediaType;
        this.$hasProfile = z5;
        this.$onSwitchProfile = aVar2;
        this.$onNavigateToSearch = aVar3;
        this.$onNavigateToHome = aVar4;
        this.$onNavigateToWatchlist = aVar5;
        this.$onNavigateToTv = aVar6;
        this.$onNavigateToSettings = aVar7;
        this.$viewModel = detailsViewModel;
        this.$onNavigateToDetails = pVar;
        this.$showStreamSelector$delegate = mutableState;
        this.$showEpisodeContextMenu$delegate = mutableState2;
        this.$showSeasonContextMenu$delegate = mutableState3;
        this.$uiState$delegate = state;
        this.$showTrailerPlayer$delegate = mutableState4;
        this.$isSidebarFocused$delegate = mutableState5;
        this.$sidebarFocusIndex$delegate = mutableIntState;
        this.$focusedSection$delegate = mutableState6;
        this.$buttonIndex$delegate = mutableIntState2;
        this.$episodeIndex$delegate = mutableIntState3;
        this.$ratingsIndex$delegate = mutableIntState4;
        this.$seasonIndex$delegate = mutableIntState5;
        this.$castIndex$delegate = mutableIntState6;
        this.$reviewIndex$delegate = mutableIntState7;
        this.$similarIndex$delegate = mutableIntState8;
        this.$collectionIndex$delegate = mutableIntState9;
        this.$seasonSelectDownAtMs$delegate = mutableLongState;
        this.$autoPlayWaitTick$delegate = mutableIntState10;
        this.$pendingAutoPlayRequest$delegate = mutableState7;
        this.$contextMenuEpisode$delegate = mutableState8;
        this.$contextMenuSeason$delegate = mutableIntState11;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invoke_ZmokQxo$lambda$0(MutableIntState mutableIntState, int i10) {
        mutableIntState.setIntValue(i10);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invoke_ZmokQxo$lambda$1(MutableIntState mutableIntState, int i10) {
        mutableIntState.setIntValue(i10);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invoke_ZmokQxo$lambda$10(MutableIntState mutableIntState, int i10) {
        mutableIntState.setIntValue(i10);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invoke_ZmokQxo$lambda$11(MutableIntState mutableIntState, int i10) {
        mutableIntState.setIntValue(i10);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invoke_ZmokQxo$lambda$12(MutableIntState mutableIntState, int i10) {
        mutableIntState.setIntValue(i10);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invoke_ZmokQxo$lambda$13(MutableIntState mutableIntState, int i10) {
        mutableIntState.setIntValue(i10);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invoke_ZmokQxo$lambda$14(MutableIntState mutableIntState, int i10) {
        mutableIntState.setIntValue(i10);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invoke_ZmokQxo$lambda$15(MutableIntState mutableIntState, int i10) {
        mutableIntState.setIntValue(i10);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invoke_ZmokQxo$lambda$2(MutableIntState mutableIntState, int i10) {
        mutableIntState.setIntValue(i10);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invoke_ZmokQxo$lambda$3(MutableIntState mutableIntState, int i10) {
        mutableIntState.setIntValue(i10);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invoke_ZmokQxo$lambda$4(MutableIntState mutableIntState, int i10) {
        mutableIntState.setIntValue(i10);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invoke_ZmokQxo$lambda$5(MutableIntState mutableIntState, int i10) {
        mutableIntState.setIntValue(i10);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invoke_ZmokQxo$lambda$6(MutableIntState mutableIntState, int i10) {
        mutableIntState.setIntValue(i10);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invoke_ZmokQxo$lambda$7(MutableIntState mutableIntState, int i10) {
        mutableIntState.setIntValue(i10);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invoke_ZmokQxo$lambda$8(MutableIntState mutableIntState, int i10) {
        mutableIntState.setIntValue(i10);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invoke_ZmokQxo$lambda$9(MutableIntState mutableIntState, int i10) {
        mutableIntState.setIntValue(i10);
        return x6.t0.f22605a;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return m6233invokeZmokQxo(((KeyEvent) obj).m4390unboximpl());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:126:0x03a6  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x03ac  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x03bc  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x03d2  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x03da  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x03fb  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x03fe  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0415  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0427  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x04b0  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x04bd  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0507  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x050d  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0517  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x051b  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x052b  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0541  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0569  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0582  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x059e  */
    /* JADX WARN: Removed duplicated region for block: B:358:0x0858 A[PHI: r1
      0x0858: PHI (r1v94 java.lang.Integer) = (r1v81 java.lang.Integer), (r1v88 java.lang.Integer) binds: [B:357:0x0856, B:363:0x087f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:372:0x089a A[PHI: r1
      0x089a: PHI (r1v77 java.lang.Integer) = (r1v64 java.lang.Integer), (r1v71 java.lang.Integer) binds: [B:371:0x0898, B:377:0x08c1] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:396:0x091b  */
    /* JADX WARN: Removed duplicated region for block: B:415:0x0970  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0188  */
    /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Boolean m6233invokeZmokQxo(android.view.KeyEvent r21) {
        /*
            Method dump skipped, instruction units count: 2728
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsScreenKt$DetailsScreen$keyModifier$1$1.m6233invokeZmokQxo(android.view.KeyEvent):java.lang.Boolean");
    }
}
