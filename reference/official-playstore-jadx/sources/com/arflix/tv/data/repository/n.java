package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.StremioStream;
import com.arflix.tv.data.model.Addon;
import com.arflix.tv.data.repository.IptvRepository;
import com.arflix.tv.data.repository.SportsRepository;
import ka.q0;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class n implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7674i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f7675l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f7676m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f7677n;

    public /* synthetic */ n(int i10, Object obj, Object obj2, String str) {
        this.f7674i = i10;
        this.f7676m = obj;
        this.f7675l = str;
        this.f7677n = obj2;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7674i) {
            case 0:
                return IptvRepository.findMovieCandidatesIndexed$lambda$7((IptvRepository) this.f7676m, this.f7675l, (Integer) this.f7677n, (IptvRepository.XtreamVodStream) obj);
            case 1:
                return SportsRepository.C09752.invokeSuspend$lambda$1((SportsRepository) this.f7676m, (Addon) this.f7677n, this.f7675l, (StremioStream) obj);
            default:
                return HomeServerRepository.resolveSourcesCached$lambda$0$1$0((HomeServerRepository) this.f7676m, this.f7675l, (q0) this.f7677n, (Throwable) obj);
        }
    }

    public /* synthetic */ n(SportsRepository sportsRepository, Addon addon, String str) {
        this.f7674i = 1;
        this.f7676m = sportsRepository;
        this.f7677n = addon;
        this.f7675l = str;
    }
}
