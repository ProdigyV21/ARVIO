package com.arflix.tv.data.repository;

import android.content.Context;
import com.arflix.tv.util.DataStoresKt;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.util.LinkedHashMap;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0013\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J(\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0086@¢\u0006\u0004\b\f\u0010\rJ\u0018\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000eH\u0086@¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u000bH\u0086@¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u000bH\u0086@¢\u0006\u0004\b\u0014\u0010\u0013R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0015¨\u0006\u0017"}, d2 = {"Lcom/arflix/tv/data/repository/PlaybackTelemetryRepository;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "", "startupMs", "", "retries", "failoversBeforeStart", "Lx6/t0;", "recordStartup", "(JIILd7/d;)Ljava/lang/Object;", "", "success", "recordFailoverAttempt", "(ZLd7/d;)Ljava/lang/Object;", "recordLongRebuffer", "(Ld7/d;)Ljava/lang/Object;", "recordPlaybackFailure", "Landroid/content/Context;", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PlaybackTelemetryRepository {
    private final Context context;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final c1.e startupSamplesKey = new c1.e("telemetry_startup_samples_v1");
    private static final c1.e startupAvgMsKey = new c1.e("telemetry_startup_avg_ms_v1");
    private static final c1.e startupRetriesKey = new c1.e("telemetry_startup_retries_v1");
    private static final c1.e failoverAttemptsKey = new c1.e("telemetry_failover_attempts_v1");
    private static final c1.e failoverSuccessesKey = new c1.e("telemetry_failover_successes_v1");
    private static final c1.e longRebuffersKey = new c1.e("telemetry_long_rebuffers_v1");
    private static final c1.e playbackFailuresKey = new c1.e("telemetry_playback_failures_v1");
    private static final c1.e lastStartupMsKey = new c1.e("telemetry_last_startup_ms_v1");
    private static final c1.e lastSessionRetriesKey = new c1.e("telemetry_last_session_retries_v1");

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\tR\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\n\u0010\u0007\u001a\u0004\b\u000b\u0010\tR\u001d\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\f\u0010\u0007\u001a\u0004\b\r\u0010\tR\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u0007\u001a\u0004\b\u000f\u0010\tR\u001d\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0007\u001a\u0004\b\u0011\u0010\tR\u001d\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0007\u001a\u0004\b\u0013\u0010\tR\u001d\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0007\u001a\u0004\b\u0015\u0010\tR\u001d\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0007\u001a\u0004\b\u0017\u0010\tR\u001d\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u00048\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0007\u001a\u0004\b\u001a\u0010\t¨\u0006\u001b"}, d2 = {"Lcom/arflix/tv/data/repository/PlaybackTelemetryRepository$Companion;", "", "<init>", "()V", "Lc1/e;", "", "startupSamplesKey", "Lc1/e;", "getStartupSamplesKey", "()Lc1/e;", "startupAvgMsKey", "getStartupAvgMsKey", "startupRetriesKey", "getStartupRetriesKey", "failoverAttemptsKey", "getFailoverAttemptsKey", "failoverSuccessesKey", "getFailoverSuccessesKey", "longRebuffersKey", "getLongRebuffersKey", "playbackFailuresKey", "getPlaybackFailuresKey", "lastStartupMsKey", "getLastStartupMsKey", "", "lastSessionRetriesKey", "getLastSessionRetriesKey", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        public final c1.e getFailoverAttemptsKey() {
            return PlaybackTelemetryRepository.failoverAttemptsKey;
        }

        public final c1.e getFailoverSuccessesKey() {
            return PlaybackTelemetryRepository.failoverSuccessesKey;
        }

        public final c1.e getLastSessionRetriesKey() {
            return PlaybackTelemetryRepository.lastSessionRetriesKey;
        }

        public final c1.e getLastStartupMsKey() {
            return PlaybackTelemetryRepository.lastStartupMsKey;
        }

        public final c1.e getLongRebuffersKey() {
            return PlaybackTelemetryRepository.longRebuffersKey;
        }

        public final c1.e getPlaybackFailuresKey() {
            return PlaybackTelemetryRepository.playbackFailuresKey;
        }

        public final c1.e getStartupAvgMsKey() {
            return PlaybackTelemetryRepository.startupAvgMsKey;
        }

        public final c1.e getStartupRetriesKey() {
            return PlaybackTelemetryRepository.startupRetriesKey;
        }

        public final c1.e getStartupSamplesKey() {
            return PlaybackTelemetryRepository.startupSamplesKey;
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.PlaybackTelemetryRepository$recordFailoverAttempt$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.PlaybackTelemetryRepository$recordFailoverAttempt$2", f = "PlaybackTelemetryRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ boolean $success;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(boolean z, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$success = z;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$success, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            c1.e failoverAttemptsKey = PlaybackTelemetryRepository.Companion.getFailoverAttemptsKey();
            Long l10 = (Long) bVar.f7368a.get(PlaybackTelemetryRepository.Companion.getFailoverAttemptsKey());
            bVar.d(failoverAttemptsKey, new Long((l10 != null ? l10.longValue() : 0L) + 1));
            if (this.$success) {
                c1.e failoverSuccessesKey = PlaybackTelemetryRepository.Companion.getFailoverSuccessesKey();
                Long l11 = (Long) bVar.f7368a.get(PlaybackTelemetryRepository.Companion.getFailoverSuccessesKey());
                bVar.d(failoverSuccessesKey, new Long((l11 != null ? l11.longValue() : 0L) + 1));
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.PlaybackTelemetryRepository$recordLongRebuffer$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.PlaybackTelemetryRepository$recordLongRebuffer$2", f = "PlaybackTelemetryRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C09422 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public C09422(d7.d<? super C09422> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C09422 c09422 = new C09422(dVar);
            c09422.L$0 = obj;
            return c09422;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((C09422) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            c1.e longRebuffersKey = PlaybackTelemetryRepository.Companion.getLongRebuffersKey();
            Long l10 = (Long) bVar.f7368a.get(PlaybackTelemetryRepository.Companion.getLongRebuffersKey());
            bVar.d(longRebuffersKey, new Long((l10 != null ? l10.longValue() : 0L) + 1));
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.PlaybackTelemetryRepository$recordPlaybackFailure$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.PlaybackTelemetryRepository$recordPlaybackFailure$2", f = "PlaybackTelemetryRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C09432 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public C09432(d7.d<? super C09432> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C09432 c09432 = new C09432(dVar);
            c09432.L$0 = obj;
            return c09432;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((C09432) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            c1.e playbackFailuresKey = PlaybackTelemetryRepository.Companion.getPlaybackFailuresKey();
            Long l10 = (Long) bVar.f7368a.get(PlaybackTelemetryRepository.Companion.getPlaybackFailuresKey());
            bVar.d(playbackFailuresKey, new Long((l10 != null ? l10.longValue() : 0L) + 1));
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.PlaybackTelemetryRepository$recordStartup$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.PlaybackTelemetryRepository$recordStartup$2", f = "PlaybackTelemetryRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C09442 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ int $safeFailovers;
        final /* synthetic */ int $safeRetries;
        final /* synthetic */ long $safeStartup;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C09442(long j10, int i10, int i11, d7.d<? super C09442> dVar) {
            super(2, dVar);
            this.$safeStartup = j10;
            this.$safeRetries = i10;
            this.$safeFailovers = i11;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C09442 c09442 = new C09442(this.$safeStartup, this.$safeRetries, this.$safeFailovers, dVar);
            c09442.L$0 = obj;
            return c09442;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((C09442) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            c1.e startupSamplesKey = PlaybackTelemetryRepository.Companion.getStartupSamplesKey();
            LinkedHashMap linkedHashMap = bVar.f7368a;
            Long l10 = (Long) linkedHashMap.get(startupSamplesKey);
            long jLongValue = l10 != null ? l10.longValue() : 0L;
            Long l11 = (Long) linkedHashMap.get(PlaybackTelemetryRepository.Companion.getStartupAvgMsKey());
            long j10 = 1 + jLongValue;
            long jLongValue2 = jLongValue <= 0 ? this.$safeStartup : (((l11 != null ? l11.longValue() : 0L) * jLongValue) + this.$safeStartup) / j10;
            bVar.d(PlaybackTelemetryRepository.Companion.getStartupSamplesKey(), new Long(j10));
            bVar.d(PlaybackTelemetryRepository.Companion.getStartupAvgMsKey(), new Long(jLongValue2));
            bVar.d(PlaybackTelemetryRepository.Companion.getLastStartupMsKey(), new Long(this.$safeStartup));
            c1.e startupRetriesKey = PlaybackTelemetryRepository.Companion.getStartupRetriesKey();
            Long l12 = (Long) linkedHashMap.get(PlaybackTelemetryRepository.Companion.getStartupRetriesKey());
            bVar.d(startupRetriesKey, new Long((l12 != null ? l12.longValue() : 0L) + ((long) this.$safeRetries)));
            bVar.d(PlaybackTelemetryRepository.Companion.getLastSessionRetriesKey(), new Integer(this.$safeRetries));
            if (this.$safeFailovers > 0) {
                c1.e failoverAttemptsKey = PlaybackTelemetryRepository.Companion.getFailoverAttemptsKey();
                Long l13 = (Long) linkedHashMap.get(PlaybackTelemetryRepository.Companion.getFailoverAttemptsKey());
                bVar.d(failoverAttemptsKey, new Long((l13 != null ? l13.longValue() : 0L) + ((long) this.$safeFailovers)));
                c1.e failoverSuccessesKey = PlaybackTelemetryRepository.Companion.getFailoverSuccessesKey();
                Long l14 = (Long) linkedHashMap.get(PlaybackTelemetryRepository.Companion.getFailoverSuccessesKey());
                bVar.d(failoverSuccessesKey, new Long((l14 != null ? l14.longValue() : 0L) + ((long) this.$safeFailovers)));
            }
            return t0.f22605a;
        }
    }

    @Inject
    public PlaybackTelemetryRepository(@ApplicationContext Context context) {
        this.context = context;
    }

    public final Object recordFailoverAttempt(boolean z, d7.d<? super t0> dVar) {
        Object objO = androidx.work.impl.t.o(DataStoresKt.getSettingsDataStore(this.context), new AnonymousClass2(z, null), dVar);
        return objO == e7.a.f15033i ? objO : t0.f22605a;
    }

    public final Object recordLongRebuffer(d7.d<? super t0> dVar) {
        Object objO = androidx.work.impl.t.o(DataStoresKt.getSettingsDataStore(this.context), new C09422(null), dVar);
        return objO == e7.a.f15033i ? objO : t0.f22605a;
    }

    public final Object recordPlaybackFailure(d7.d<? super t0> dVar) {
        Object objO = androidx.work.impl.t.o(DataStoresKt.getSettingsDataStore(this.context), new C09432(null), dVar);
        return objO == e7.a.f15033i ? objO : t0.f22605a;
    }

    public final Object recordStartup(long j10, int i10, int i11, d7.d<? super t0> dVar) {
        Object objO = androidx.work.impl.t.o(DataStoresKt.getSettingsDataStore(this.context), new C09442(j10 < 0 ? 0L : j10, i10 < 0 ? 0 : i10, i11 < 0 ? 0 : i11, null), dVar);
        return objO == e7.a.f15033i ? objO : t0.f22605a;
    }
}
