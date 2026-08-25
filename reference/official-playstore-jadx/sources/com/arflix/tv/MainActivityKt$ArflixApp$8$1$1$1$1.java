package com.arflix.tv;

import com.arflix.tv.data.repository.IptvRepository;
import com.arflix.tv.data.repository.ProfileManager;
import com.arflix.tv.data.repository.ProfileRepository;
import com.arflix.tv.data.repository.TraktRepository;
import com.arflix.tv.data.repository.WatchHistoryRepository;
import com.arflix.tv.data.repository.WatchlistRepository;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.MainActivityKt$ArflixApp$8$1$1$1$1", f = "MainActivity.kt", l = {691}, m = "invokeSuspend", v = 2)
public final class MainActivityKt$ArflixApp$8$1$1$1$1 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ IptvRepository $iptvRepository;
    final /* synthetic */ ProfileManager $profileManager;
    final /* synthetic */ ProfileRepository $profileRepository;
    final /* synthetic */ TraktRepository $traktRepository;
    final /* synthetic */ WatchHistoryRepository $watchHistoryRepository;
    final /* synthetic */ WatchlistRepository $watchlistRepository;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainActivityKt$ArflixApp$8$1$1$1$1(TraktRepository traktRepository, WatchHistoryRepository watchHistoryRepository, WatchlistRepository watchlistRepository, IptvRepository iptvRepository, ProfileManager profileManager, ProfileRepository profileRepository, d7.d<? super MainActivityKt$ArflixApp$8$1$1$1$1> dVar) {
        super(2, dVar);
        this.$traktRepository = traktRepository;
        this.$watchHistoryRepository = watchHistoryRepository;
        this.$watchlistRepository = watchlistRepository;
        this.$iptvRepository = iptvRepository;
        this.$profileManager = profileManager;
        this.$profileRepository = profileRepository;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new MainActivityKt$ArflixApp$8$1$1$1$1(this.$traktRepository, this.$watchHistoryRepository, this.$watchlistRepository, this.$iptvRepository, this.$profileManager, this.$profileRepository, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            this.$traktRepository.clearAllProfileCaches();
            this.$watchHistoryRepository.clearProfileCaches();
            this.$watchlistRepository.clearWatchlistCache();
            this.$iptvRepository.invalidateCache();
            this.$profileManager.setCurrentProfileId("default");
            this.$profileManager.setCurrentProfileName("default");
            ProfileRepository profileRepository = this.$profileRepository;
            this.label = 1;
            Object objClearActiveProfile = profileRepository.clearActiveProfile(this);
            e7.a aVar = e7.a.f15033i;
            if (objClearActiveProfile == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((MainActivityKt$ArflixApp$8$1$1$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
