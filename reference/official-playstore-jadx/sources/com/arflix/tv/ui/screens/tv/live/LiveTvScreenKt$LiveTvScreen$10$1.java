package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.arflix.tv.ui.screens.tv.TvUiState;
import com.arflix.tv.ui.screens.tv.TvViewModel;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$10$1", f = "LiveTvScreen.kt", l = {470, 480, 481, 526, 555, 667, 702, 717, 720, 721}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$10$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableState<EnrichedChannels> $enrichedState;
    final /* synthetic */ Set<String> $favSet;
    final /* synthetic */ Set<String> $hiddenGroupSet;
    final /* synthetic */ MutableIntState $lastKnownPagedTotal$delegate;
    final /* synthetic */ MutableState<List<x6.j0>> $lastKnownPlaylistGroupCounts$delegate;
    final /* synthetic */ MutableIntState $pagedLoadedLimit$delegate;
    final /* synthetic */ MutableState<LinkedHashSet<String>> $recents;
    final /* synthetic */ MutableState<String> $selectedCategoryId$delegate;
    final /* synthetic */ State<TvUiState> $state$delegate;
    final /* synthetic */ TvViewModel $viewModel;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$10$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)I"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$10$1$1", f = "LiveTvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements r7.p<ka.k0, d7.d<? super Integer>, Object> {
        final /* synthetic */ TvViewModel $viewModel;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(TvViewModel tvViewModel, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$viewModel = tvViewModel;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return new AnonymousClass1(this.$viewModel, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return new Integer(this.$viewModel.getIptvRepository().pagedChannelsReady() ? this.$viewModel.getIptvRepository().pagedChannelCount(null) : 0);
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super Integer> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$10$1(TvViewModel tvViewModel, MutableState<EnrichedChannels> mutableState, Set<String> set, MutableState<LinkedHashSet<String>> mutableState2, State<TvUiState> state, MutableIntState mutableIntState, MutableState<String> mutableState3, MutableIntState mutableIntState2, Set<String> set2, MutableState<List<x6.j0>> mutableState4, d7.d<? super LiveTvScreenKt$LiveTvScreen$10$1> dVar) {
        super(2, dVar);
        this.$viewModel = tvViewModel;
        this.$enrichedState = mutableState;
        this.$favSet = set;
        this.$recents = mutableState2;
        this.$state$delegate = state;
        this.$lastKnownPagedTotal$delegate = mutableIntState;
        this.$selectedCategoryId$delegate = mutableState3;
        this.$pagedLoadedLimit$delegate = mutableIntState2;
        this.$hiddenGroupSet = set2;
        this.$lastKnownPlaylistGroupCounts$delegate = mutableState4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.x invokeSuspend$resolvePagedGroup(MutableState<List<x6.j0>> mutableState, MutableState<String> mutableState2, LiveCategoryTree liveCategoryTree) {
        Object next;
        Iterator it = LiveTvScreenKt.LiveTvScreen$lambda$30(mutableState).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            x6.j0 j0Var = (x6.j0) next;
            if (kotlin.jvm.internal.p.a(LiveCategoryKt.playlistGroupCategoryId((String) j0Var.f22587i, (String) j0Var.f22588l), LiveTvScreenKt.LiveTvScreen$lambda$13(mutableState2))) {
                break;
            }
        }
        x6.j0 j0Var2 = (x6.j0) next;
        x6.x xVar = j0Var2 != null ? new x6.x((String) j0Var2.f22587i, (String) j0Var2.f22588l) : null;
        if (xVar != null) {
            return xVar;
        }
        LiveCategory liveCategoryById = liveCategoryTree.byId(LiveTvScreenKt.LiveTvScreen$lambda$13(mutableState2));
        if ((liveCategoryById != null ? liveCategoryById.getPlaylistId() : null) == null || liveCategoryById.getPlaylistGroupName() == null) {
            return null;
        }
        return new x6.x(liveCategoryById.getPlaylistId(), liveCategoryById.getPlaylistGroupName());
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$10$1(this.$viewModel, this.$enrichedState, this.$favSet, this.$recents, this.$state$delegate, this.$lastKnownPagedTotal$delegate, this.$selectedCategoryId$delegate, this.$pagedLoadedLimit$delegate, this.$hiddenGroupSet, this.$lastKnownPlaylistGroupCounts$delegate, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:80:0x035b, code lost:
    
        if (r2 != r6) goto L82;
     */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0432  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x043d  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0478  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x04a4  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x02dc  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x02fa  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0371  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x0196 -> B:40:0x0198). Please report as a decompilation issue!!! */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1240
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$10$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$10$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
