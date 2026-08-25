package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.ui.screens.tv.TvUiState;
import com.arflix.tv.ui.screens.tv.TvViewModel;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$15$1", f = "LiveTvScreen.kt", l = {834, 847, 857, MediaError.DetailedErrorCode.LOAD_INTERRUPTED}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$15$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableState<String> $filteredChannelsCategoryKey$delegate;
    final /* synthetic */ MutableState<List<EnrichedChannel>> $filteredChannelsState;
    final /* synthetic */ MutableState<List<x6.j0>> $lastKnownPlaylistGroupCounts$delegate;
    final /* synthetic */ MutableIntState $pagedLoadedLimit$delegate;
    final /* synthetic */ MutableState<LinkedHashSet<String>> $recents;
    final /* synthetic */ MutableState<String> $selectedCategoryId$delegate;
    final /* synthetic */ State<TvUiState> $state$delegate;
    final /* synthetic */ TvViewModel $viewModel;
    final /* synthetic */ MutableState<EnrichedChannels> $visibleEnrichedState;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$15$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$15$1$1", f = "LiveTvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends EnrichedChannel>>, Object> {
        final /* synthetic */ List<IptvChannel> $directChannels;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(List<IptvChannel> list, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$directChannels = list;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return new AnonymousClass1(this.$directChannels, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            List<IptvChannel> list = this.$directChannels;
            ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
            int i10 = 0;
            for (Object obj2 : list) {
                int i11 = i10 + 1;
                if (i10 < 0) {
                    t7.a.Q();
                    throw null;
                }
                arrayList.add(LiveCategoryKt.enrichForFastStartup((IptvChannel) obj2, i11));
                i10 = i11;
            }
            return arrayList;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super List<EnrichedChannel>> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$15$1(MutableState<EnrichedChannels> mutableState, MutableState<List<EnrichedChannel>> mutableState2, MutableState<String> mutableState3, MutableState<LinkedHashSet<String>> mutableState4, State<TvUiState> state, TvViewModel tvViewModel, MutableState<List<x6.j0>> mutableState5, MutableIntState mutableIntState, MutableState<String> mutableState6, d7.d<? super LiveTvScreenKt$LiveTvScreen$15$1> dVar) {
        super(2, dVar);
        this.$visibleEnrichedState = mutableState;
        this.$filteredChannelsState = mutableState2;
        this.$selectedCategoryId$delegate = mutableState3;
        this.$recents = mutableState4;
        this.$state$delegate = state;
        this.$viewModel = tvViewModel;
        this.$lastKnownPlaylistGroupCounts$delegate = mutableState5;
        this.$pagedLoadedLimit$delegate = mutableIntState;
        this.$filteredChannelsCategoryKey$delegate = mutableState6;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$15$1(this.$visibleEnrichedState, this.$filteredChannelsState, this.$selectedCategoryId$delegate, this.$recents, this.$state$delegate, this.$viewModel, this.$lastKnownPlaylistGroupCounts$delegate, this.$pagedLoadedLimit$delegate, this.$filteredChannelsCategoryKey$delegate, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x00e1, code lost:
    
        if (r5 == r8) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x01c5, code lost:
    
        if (r2 == r8) goto L64;
     */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0184 A[PHI: r1 r3 r4 r10
      0x0184: PHI (r1v9 int) = (r1v7 int), (r1v18 int) binds: [B:58:0x0181, B:11:0x0037] A[DONT_GENERATE, DONT_INLINE]
      0x0184: PHI (r3v2 java.lang.Object) = (r3v1 java.lang.Object), (r3v6 java.lang.Object) binds: [B:58:0x0181, B:11:0x0037] A[DONT_GENERATE, DONT_INLINE]
      0x0184: PHI (r4v3 int) = (r4v1 int), (r4v13 int) binds: [B:58:0x0181, B:11:0x0037] A[DONT_GENERATE, DONT_INLINE]
      0x0184: PHI (r10v6 java.util.List) = (r10v4 java.util.List), (r10v13 java.util.List) binds: [B:58:0x0181, B:11:0x0037] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01d3 A[ADDED_TO_REGION] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 570
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$15$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$15$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
