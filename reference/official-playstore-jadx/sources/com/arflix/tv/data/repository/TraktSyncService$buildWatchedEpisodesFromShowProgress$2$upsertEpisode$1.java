package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.TraktSyncService;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.TraktSyncService$buildWatchedEpisodesFromShowProgress$2", f = "TraktSyncService.kt", l = {2086}, m = "invokeSuspend$upsertEpisode", v = 2)
public final class TraktSyncService$buildWatchedEpisodesFromShowProgress$2$upsertEpisode$1 extends f7.c {
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    int label;
    /* synthetic */ Object result;

    public TraktSyncService$buildWatchedEpisodesFromShowProgress$2$upsertEpisode$1(d7.d<? super TraktSyncService$buildWatchedEpisodesFromShowProgress$2$upsertEpisode$1> dVar) {
        super(dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return TraktSyncService.AnonymousClass2.invokeSuspend$upsertEpisode(null, null, null, null, null, 0, null, 0, 0, null, this);
    }
}
