package com.arflix.tv.ui.screens.home;

import android.os.SystemClock;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import com.arflix.tv.data.model.Category;
import com.arflix.tv.data.model.MediaItem;
import java.util.List;
import kotlin.Metadata;
import na.y0;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$HomeScreen$17$1", f = "HomeScreen.kt", l = {824}, m = "invokeSuspend", v = 2)
public final class HomeScreenKt$HomeScreen$17$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ boolean $allowHomeBackgroundWork;
    final /* synthetic */ long $fastScrollThresholdMs;
    final /* synthetic */ HomeFocusState $focusState;
    final /* synthetic */ State<List<Category>> $latestDisplayCategories$delegate;
    final /* synthetic */ State<MediaItem> $latestDisplayHeroItem$delegate;
    final /* synthetic */ HomeViewModel $viewModel;
    int label;

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.home.HomeScreenKt$HomeScreen$17$1$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/arflix/tv/ui/screens/home/HomeFocusedHeroSnapshot;", "focusSnapshot", "Lx6/t0;", "<anonymous>", "(Lcom/arflix/tv/ui/screens/home/HomeFocusedHeroSnapshot;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$HomeScreen$17$1$2", f = "HomeScreen.kt", l = {837}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<HomeFocusedHeroSnapshot, d7.d<? super t0>, Object> {
        final /* synthetic */ long $fastScrollThresholdMs;
        final /* synthetic */ HomeFocusState $focusState;
        final /* synthetic */ State<List<Category>> $latestDisplayCategories$delegate;
        final /* synthetic */ HomeViewModel $viewModel;
        int I$0;
        long J$0;
        /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(HomeFocusState homeFocusState, long j10, HomeViewModel homeViewModel, State<? extends List<Category>> state, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$focusState = homeFocusState;
            this.$fastScrollThresholdMs = j10;
            this.$viewModel = homeViewModel;
            this.$latestDisplayCategories$delegate = state;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$focusState, this.$fastScrollThresholdMs, this.$viewModel, this.$latestDisplayCategories$delegate, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(HomeFocusedHeroSnapshot homeFocusedHeroSnapshot, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(homeFocusedHeroSnapshot, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Category category;
            List<MediaItem> items;
            Category category2;
            List<MediaItem> items2;
            MediaItem mediaItem;
            HomeFocusedHeroSnapshot homeFocusedHeroSnapshot = (HomeFocusedHeroSnapshot) this.L$0;
            int i10 = this.label;
            t0 t0Var = t0.f22605a;
            if (i10 == 0) {
                k2.c.G(obj);
                List listHomeScreen$lambda$19 = HomeScreenKt.HomeScreen$lambda$19(this.$latestDisplayCategories$delegate);
                if (!listHomeScreen$lambda$19.isEmpty() && !this.$focusState.isSidebarFocused() && !kotlin.text.o.h0(homeFocusedHeroSnapshot.getFocusedItemKey()) && !kotlin.jvm.internal.p.a(homeFocusedHeroSnapshot.getFocusedItemKey(), homeFocusedHeroSnapshot.getHeroItemKey()) && (category = (Category) kotlin.collections.x.p0(homeFocusedHeroSnapshot.getRowIndex(), listHomeScreen$lambda$19)) != null && (items = category.getItems()) != null && ((MediaItem) kotlin.collections.x.p0(homeFocusedHeroSnapshot.getItemIndex(), items)) != null) {
                    long jElapsedRealtime = SystemClock.elapsedRealtime();
                    int i11 = jElapsedRealtime - this.$focusState.getLastNavEventTime() < this.$fastScrollThresholdMs ? 1 : 0;
                    if (i11 != 0) {
                        this.L$0 = homeFocusedHeroSnapshot;
                        this.L$1 = null;
                        this.J$0 = jElapsedRealtime;
                        this.I$0 = i11;
                        this.label = 1;
                        Object objA = ka.s0.a(360L, this);
                        e7.a aVar = e7.a.f15033i;
                        if (objA == aVar) {
                            return aVar;
                        }
                    }
                    category2 = (Category) kotlin.collections.x.p0(homeFocusedHeroSnapshot.getRowIndex(), HomeScreenKt.HomeScreen$lambda$19(this.$latestDisplayCategories$delegate));
                    if (category2 != null || (items2 = category2.getItems()) == null || (mediaItem = (MediaItem) kotlin.collections.x.p0(homeFocusedHeroSnapshot.getItemIndex(), items2)) == null || !kotlin.jvm.internal.p.a(HomeScreenKt.homeRowItemKey(mediaItem), homeFocusedHeroSnapshot.getFocusedItemKey())) {
                        return t0Var;
                    }
                    this.$viewModel.onFocusChanged(homeFocusedHeroSnapshot.getRowIndex(), homeFocusedHeroSnapshot.getItemIndex(), true);
                    this.$viewModel.updateHeroItem(mediaItem);
                }
                return t0Var;
            }
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            if (this.$focusState.getCurrentRowIndex() != homeFocusedHeroSnapshot.getRowIndex() || this.$focusState.getCurrentItemIndex() != homeFocusedHeroSnapshot.getItemIndex() || this.$focusState.isSidebarFocused()) {
                return t0Var;
            }
            category2 = (Category) kotlin.collections.x.p0(homeFocusedHeroSnapshot.getRowIndex(), HomeScreenKt.HomeScreen$lambda$19(this.$latestDisplayCategories$delegate));
            if (category2 != null) {
            }
            return t0Var;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public HomeScreenKt$HomeScreen$17$1(boolean z, HomeFocusState homeFocusState, State<? extends List<Category>> state, State<MediaItem> state2, long j10, HomeViewModel homeViewModel, d7.d<? super HomeScreenKt$HomeScreen$17$1> dVar) {
        super(2, dVar);
        this.$allowHomeBackgroundWork = z;
        this.$focusState = homeFocusState;
        this.$latestDisplayCategories$delegate = state;
        this.$latestDisplayHeroItem$delegate = state2;
        this.$fastScrollThresholdMs = j10;
        this.$viewModel = homeViewModel;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final HomeFocusedHeroSnapshot invokeSuspend$lambda$0(HomeFocusState homeFocusState, State state, State state2) {
        List<MediaItem> items;
        Category category = (Category) kotlin.collections.x.p0(homeFocusState.getCurrentRowIndex(), HomeScreenKt.HomeScreen$lambda$19(state));
        MediaItem mediaItem = (category == null || (items = category.getItems()) == null) ? null : (MediaItem) kotlin.collections.x.p0(homeFocusState.getCurrentItemIndex(), items);
        int currentRowIndex = homeFocusState.getCurrentRowIndex();
        int currentItemIndex = homeFocusState.getCurrentItemIndex();
        String strHomeRowItemKey = mediaItem != null ? HomeScreenKt.homeRowItemKey(mediaItem) : null;
        if (strHomeRowItemKey == null) {
            strHomeRowItemKey = "";
        }
        MediaItem mediaItemHomeScreen$lambda$20 = HomeScreenKt.HomeScreen$lambda$20(state2);
        String strHomeRowItemKey2 = mediaItemHomeScreen$lambda$20 != null ? HomeScreenKt.homeRowItemKey(mediaItemHomeScreen$lambda$20) : null;
        return new HomeFocusedHeroSnapshot(currentRowIndex, currentItemIndex, strHomeRowItemKey, strHomeRowItemKey2 != null ? strHomeRowItemKey2 : "");
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeScreenKt$HomeScreen$17$1(this.$allowHomeBackgroundWork, this.$focusState, this.$latestDisplayCategories$delegate, this.$latestDisplayHeroItem$delegate, this.$fastScrollThresholdMs, this.$viewModel, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        t0 t0Var = t0.f22605a;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return t0Var;
        }
        k2.c.G(obj);
        if (this.$allowHomeBackgroundWork) {
            na.j jVarH = y0.h(SnapshotStateKt.snapshotFlow(new h0(this.$focusState, this.$latestDisplayCategories$delegate, this.$latestDisplayHeroItem$delegate, 2)));
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$focusState, this.$fastScrollThresholdMs, this.$viewModel, this.$latestDisplayCategories$delegate, null);
            this.label = 1;
            Object objG = y0.g(jVarH, anonymousClass2, this);
            e7.a aVar = e7.a.f15033i;
            if (objG == aVar) {
                return aVar;
            }
        }
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((HomeScreenKt$HomeScreen$17$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
