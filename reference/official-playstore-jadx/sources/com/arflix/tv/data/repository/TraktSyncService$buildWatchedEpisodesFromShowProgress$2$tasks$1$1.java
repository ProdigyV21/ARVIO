package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.TraktWatchedShow;
import com.arflix.tv.data.api.WatchedEpisodeRecord;
import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.TraktSyncService$buildWatchedEpisodesFromShowProgress$2$tasks$1$1", f = "TraktSyncService.kt", l = {2085, 1533, 1548, 1563}, m = "invokeSuspend", v = 2)
public final class TraktSyncService$buildWatchedEpisodesFromShowProgress$2$tasks$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ LinkedHashMap<String, WatchedEpisodeRecord> $byKey;
    final /* synthetic */ kotlin.jvm.internal.i0 $filtered;
    final /* synthetic */ TraktWatchedShow $item;
    final /* synthetic */ ua.a $mutex;
    final /* synthetic */ ua.f $semaphore;
    final /* synthetic */ AtomicInteger $skippedEpisodes;
    final /* synthetic */ AtomicInteger $skippedShows;
    final /* synthetic */ String $userId;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    int I$4;
    int I$5;
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$11;
    Object L$12;
    Object L$13;
    Object L$14;
    Object L$15;
    Object L$16;
    Object L$17;
    Object L$18;
    Object L$19;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    final /* synthetic */ TraktSyncService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TraktSyncService$buildWatchedEpisodesFromShowProgress$2$tasks$1$1(ua.f fVar, TraktWatchedShow traktWatchedShow, AtomicInteger atomicInteger, AtomicInteger atomicInteger2, TraktSyncService traktSyncService, ua.a aVar, LinkedHashMap<String, WatchedEpisodeRecord> linkedHashMap, String str, kotlin.jvm.internal.i0 i0Var, d7.d<? super TraktSyncService$buildWatchedEpisodesFromShowProgress$2$tasks$1$1> dVar) {
        super(2, dVar);
        this.$semaphore = fVar;
        this.$item = traktWatchedShow;
        this.$skippedShows = atomicInteger;
        this.$skippedEpisodes = atomicInteger2;
        this.this$0 = traktSyncService;
        this.$mutex = aVar;
        this.$byKey = linkedHashMap;
        this.$userId = str;
        this.$filtered = i0Var;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TraktSyncService$buildWatchedEpisodesFromShowProgress$2$tasks$1$1(this.$semaphore, this.$item, this.$skippedShows, this.$skippedEpisodes, this.this$0, this.$mutex, this.$byKey, this.$userId, this.$filtered, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x049f  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x033a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x01ef A[Catch: all -> 0x007d, TRY_LEAVE, TryCatch #4 {all -> 0x007d, blocks: (B:9:0x006e, B:79:0x0396, B:81:0x039c, B:83:0x03a8, B:109:0x04c7, B:113:0x04d1, B:18:0x00b9, B:76:0x0389, B:78:0x0391, B:46:0x01e9, B:48:0x01ef, B:30:0x0193, B:32:0x01a9, B:34:0x01b2, B:35:0x01b7, B:37:0x01bd, B:39:0x01ce, B:40:0x01d3, B:42:0x01d9, B:43:0x01dd, B:45:0x01e3), top: B:116:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x021a A[Catch: all -> 0x02cf, TryCatch #5 {all -> 0x02cf, blocks: (B:50:0x0214, B:52:0x021a, B:54:0x0226, B:57:0x022e), top: B:120:0x0214 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0312  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0336  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0391 A[Catch: all -> 0x007d, Exception -> 0x0080, TryCatch #0 {Exception -> 0x0080, blocks: (B:9:0x006e, B:79:0x0396, B:81:0x039c, B:83:0x03a8, B:18:0x00b9, B:76:0x0389, B:78:0x0391), top: B:116:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x039c A[Catch: all -> 0x007d, Exception -> 0x0080, TryCatch #0 {Exception -> 0x0080, blocks: (B:9:0x006e, B:79:0x0396, B:81:0x039c, B:83:0x03a8, B:18:0x00b9, B:76:0x0389, B:78:0x0391), top: B:116:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x03cc A[Catch: all -> 0x0474, Exception -> 0x0479, TryCatch #6 {Exception -> 0x0479, all -> 0x0474, blocks: (B:86:0x03c6, B:88:0x03cc, B:90:0x03d8, B:93:0x03e0, B:72:0x033a), top: B:122:0x033a }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:103:0x047e -> B:104:0x0496). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:107:0x04c0 -> B:106:0x04be). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x01fd -> B:120:0x0214). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:60:0x02a3 -> B:61:0x02b9). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:64:0x02d4 -> B:65:0x02eb). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:84:0x03ac -> B:85:0x03c4). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:97:0x0452 -> B:98:0x0465). Please report as a decompilation issue!!! */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1238
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.TraktSyncService$buildWatchedEpisodesFromShowProgress$2$tasks$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((TraktSyncService$buildWatchedEpisodesFromShowProgress$2$tasks$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
