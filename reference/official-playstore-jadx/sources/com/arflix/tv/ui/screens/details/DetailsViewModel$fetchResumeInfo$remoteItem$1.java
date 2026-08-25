package com.arflix.tv.ui.screens.details;

import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.repository.ContinueWatchingItem;
import com.arflix.tv.data.repository.sync.RemoteSyncManager;
import java.util.Iterator;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/repository/ContinueWatchingItem;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/repository/ContinueWatchingItem;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$fetchResumeInfo$remoteItem$1", f = "DetailsViewModel.kt", l = {2403}, m = "invokeSuspend", v = 2)
public final class DetailsViewModel$fetchResumeInfo$remoteItem$1 extends f7.j implements r7.p<ka.k0, d7.d<? super ContinueWatchingItem>, Object> {
    final /* synthetic */ MediaType $mediaType;
    final /* synthetic */ Integer $preferredEpisode;
    final /* synthetic */ Integer $preferredSeason;
    final /* synthetic */ int $tmdbId;
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    final /* synthetic */ DetailsViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DetailsViewModel$fetchResumeInfo$remoteItem$1(DetailsViewModel detailsViewModel, int i10, MediaType mediaType, Integer num, Integer num2, d7.d<? super DetailsViewModel$fetchResumeInfo$remoteItem$1> dVar) {
        super(2, dVar);
        this.this$0 = detailsViewModel;
        this.$tmdbId = i10;
        this.$mediaType = mediaType;
        this.$preferredSeason = num;
        this.$preferredEpisode = num2;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        DetailsViewModel$fetchResumeInfo$remoteItem$1 detailsViewModel$fetchResumeInfo$remoteItem$1 = new DetailsViewModel$fetchResumeInfo$remoteItem$1(this.this$0, this.$tmdbId, this.$mediaType, this.$preferredSeason, this.$preferredEpisode, dVar);
        detailsViewModel$fetchResumeInfo$remoteItem$1.L$0 = obj;
        return detailsViewModel$fetchResumeInfo$remoteItem$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Object c0Var;
        int i10;
        MediaType mediaType;
        Integer num;
        Integer num2;
        Object next;
        int i11 = this.label;
        try {
            if (i11 == 0) {
                k2.c.G(obj);
                DetailsViewModel detailsViewModel = this.this$0;
                i10 = this.$tmdbId;
                mediaType = this.$mediaType;
                num = this.$preferredSeason;
                Integer num3 = this.$preferredEpisode;
                RemoteSyncManager remoteSyncManager = detailsViewModel.remoteSyncManager;
                this.L$0 = null;
                this.L$1 = mediaType;
                this.L$2 = num;
                this.L$3 = num3;
                this.L$4 = null;
                this.I$0 = i10;
                this.I$1 = 0;
                this.label = 1;
                obj = RemoteSyncManager.getContinueWatching$default(remoteSyncManager, false, this, 1, null);
                e7.a aVar = e7.a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
                num2 = num3;
            } else {
                if (i11 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                i10 = this.I$0;
                num2 = (Integer) this.L$3;
                num = (Integer) this.L$2;
                mediaType = (MediaType) this.L$1;
                k2.c.G(obj);
            }
            Iterator it = ((Iterable) obj).iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                ContinueWatchingItem continueWatchingItem = (ContinueWatchingItem) next;
                if (continueWatchingItem.getId() == i10 && continueWatchingItem.getMediaType() == mediaType && continueWatchingItem.getProgress() > 0) {
                    if (num != null && num2 != null && mediaType == MediaType.TV) {
                        Integer season = continueWatchingItem.getSeason();
                        int iIntValue = num.intValue();
                        if (season != null && season.intValue() == iIntValue) {
                            Integer episode = continueWatchingItem.getEpisode();
                            int iIntValue2 = num2.intValue();
                            if (episode != null && episode.intValue() == iIntValue2) {
                                break;
                            }
                        }
                    } else {
                        break;
                    }
                }
            }
            c0Var = (ContinueWatchingItem) next;
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        if (c0Var instanceof x6.c0) {
            return null;
        }
        return c0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super ContinueWatchingItem> dVar) {
        return ((DetailsViewModel$fetchResumeInfo$remoteItem$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
