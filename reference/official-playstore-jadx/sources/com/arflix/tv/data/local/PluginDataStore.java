package com.arflix.tv.data.local;

import android.content.Context;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.work.impl.t;
import c1.b;
import c1.e;
import com.arflix.tv.domain.model.PluginRepository;
import com.arflix.tv.domain.model.ScraperInfo;
import com.squareup.moshi.e0;
import com.squareup.moshi.z;
import d7.d;
import dagger.hilt.android.qualifiers.ApplicationContext;
import f7.c;
import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import ka.k0;
import ka.m0;
import ka.x0;
import kotlin.Metadata;
import l7.i;
import na.j;
import na.y0;
import r7.p;
import x6.t0;
import z0.g;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0007\u0018\u0000 Y2\u00020\u0001:\u0001YB#\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u001e\u0010\u000e\u001a\u00020\r2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0086@¢\u0006\u0004\b\u000e\u0010\u000fJ\u0018\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000bH\u0086@¢\u0006\u0004\b\u0011\u0010\u0012J\u0018\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0013H\u0086@¢\u0006\u0004\b\u0015\u0010\u0016J\u0018\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000bH\u0086@¢\u0006\u0004\b\u0017\u0010\u0012J\u001e\u0010\u001a\u001a\u00020\r2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\nH\u0086@¢\u0006\u0004\b\u001a\u0010\u000fJ \u0010\u001e\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u001cH\u0086@¢\u0006\u0004\b\u001e\u0010\u001fJ\u0018\u0010 \u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u001cH\u0086@¢\u0006\u0004\b \u0010!J\u0018\u0010\"\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u001cH\u0086@¢\u0006\u0004\b\"\u0010!J\u0015\u0010$\u001a\u00020#2\u0006\u0010\u001b\u001a\u00020\u0013¢\u0006\u0004\b$\u0010%J \u0010'\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u0013H\u0086@¢\u0006\u0004\b'\u0010(J\u001a\u0010)\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u001b\u001a\u00020\u0013H\u0086@¢\u0006\u0004\b)\u0010\u0016J\u0018\u0010*\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u0013H\u0086@¢\u0006\u0004\b*\u0010\u0016J\u0010\u0010+\u001a\u00020\rH\u0086@¢\u0006\u0004\b+\u0010,J$\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00010-2\u0006\u0010\u001b\u001a\u00020\u0013H\u0086@¢\u0006\u0004\b.\u0010\u0016J,\u00100\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u00132\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00010-H\u0086@¢\u0006\u0004\b0\u00101J\u000f\u00103\u001a\u000202H\u0002¢\u0006\u0004\b3\u00104J\u001f\u00108\u001a\b\u0012\u0004\u0012\u000207062\b\b\u0002\u00105\u001a\u000202H\u0002¢\u0006\u0004\b8\u00109J\u0010\u0010:\u001a\u00020#H\u0082@¢\u0006\u0004\b:\u0010,R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010;R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010<R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010=R\u001a\u0010?\u001a\b\u0012\u0004\u0012\u0002020>8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010@R\u001a\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00130A8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bB\u0010CR\u001a\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00130A8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bD\u0010CR\u001a\u0010E\u001a\b\u0012\u0004\u0012\u00020\u001c0A8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bE\u0010CR\u001a\u0010F\u001a\b\u0012\u0004\u0012\u00020\u001c0A8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bF\u0010CR\u001a\u0010G\u001a\b\u0012\u0004\u0012\u00020\u00130A8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bG\u0010CR\u001c\u0010J\u001a\n I*\u0004\u0018\u00010H0H8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bJ\u0010KR\u001c\u0010L\u001a\n I*\u0004\u0018\u00010H0H8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bL\u0010KR\u001c\u0010M\u001a\n I*\u0004\u0018\u00010H0H8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bM\u0010KR#\u0010N\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0>8\u0006¢\u0006\f\n\u0004\bN\u0010@\u001a\u0004\bO\u0010PR#\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\n0>8\u0006¢\u0006\f\n\u0004\b\u0019\u0010@\u001a\u0004\bQ\u0010PR\u001d\u0010R\u001a\b\u0012\u0004\u0012\u00020\u001c0>8\u0006¢\u0006\f\n\u0004\bR\u0010@\u001a\u0004\bS\u0010PR\u001d\u0010T\u001a\b\u0012\u0004\u0012\u00020\u001c0>8\u0006¢\u0006\f\n\u0004\bT\u0010@\u001a\u0004\bU\u0010PR\u0011\u0010X\u001a\u00020#8F¢\u0006\u0006\u001a\u0004\bV\u0010W¨\u0006Z"}, d2 = {"Lcom/arflix/tv/data/local/PluginDataStore;", "", "Landroid/content/Context;", "context", "Lcom/squareup/moshi/z;", "moshi", "Lcom/arflix/tv/data/local/ProfileDataStoreFactory;", "factory", "<init>", "(Landroid/content/Context;Lcom/squareup/moshi/z;Lcom/arflix/tv/data/local/ProfileDataStoreFactory;)V", "", "Lcom/arflix/tv/domain/model/PluginRepository;", "repos", "Lx6/t0;", "saveRepositories", "(Ljava/util/List;Ld7/d;)Ljava/lang/Object;", "repo", "addRepository", "(Lcom/arflix/tv/domain/model/PluginRepository;Ld7/d;)Ljava/lang/Object;", "", "repoId", "removeRepository", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "updateRepository", "Lcom/arflix/tv/domain/model/ScraperInfo;", "scrapers", "saveScrapers", "scraperId", "", "enabled", "setScraperEnabled", "(Ljava/lang/String;ZLd7/d;)Ljava/lang/Object;", "setPluginsEnabled", "(ZLd7/d;)Ljava/lang/Object;", "setGroupStreamsByRepository", "Ljava/io/File;", "getScraperCodeFile", "(Ljava/lang/String;)Ljava/io/File;", "code", "saveScraperCode", "(Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "getScraperCode", "deleteScraperCode", "clearAllScraperCode", "(Ld7/d;)Ljava/lang/Object;", "", "getScraperSettings", "settings", "setScraperSettings", "(Ljava/lang/String;Ljava/util/Map;Ld7/d;)Ljava/lang/Object;", "", "effectiveProfileId", "()I", "profileId", "Lz0/g;", "Lc1/g;", "store", "(I)Lz0/g;", "ensureCodeDir", "Landroid/content/Context;", "Lcom/squareup/moshi/z;", "Lcom/arflix/tv/data/local/ProfileDataStoreFactory;", "Lna/j;", "effectiveProfileIdFlow", "Lna/j;", "Lc1/e;", "repositoriesKey", "Lc1/e;", "scrapersKey", "pluginsEnabledKey", "groupStreamsByRepositoryKey", "scraperSettingsKey", "Ljava/lang/reflect/ParameterizedType;", "kotlin.jvm.PlatformType", "repoListType", "Ljava/lang/reflect/ParameterizedType;", "scraperListType", "settingsMapType", "repositories", "getRepositories", "()Lna/j;", "getScrapers", "pluginsEnabled", "getPluginsEnabled", "groupStreamsByRepository", "getGroupStreamsByRepository", "getCodeDir", "()Ljava/io/File;", "codeDir", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PluginDataStore {
    private static final String FEATURE = "plugin_settings";
    private final Context context;
    private final j<Integer> effectiveProfileIdFlow;
    private final ProfileDataStoreFactory factory;
    private final j<Boolean> groupStreamsByRepository;
    private final e groupStreamsByRepositoryKey;
    private final z moshi;
    private final j<Boolean> pluginsEnabled;
    private final e pluginsEnabledKey;
    private final ParameterizedType repoListType;
    private final j<List<PluginRepository>> repositories;
    private final e repositoriesKey;
    private final ParameterizedType scraperListType;
    private final e scraperSettingsKey;
    private final j<List<ScraperInfo>> scrapers;
    private final e scrapersKey;
    private final ParameterizedType settingsMapType;
    public static final int $stable = 8;

    /* JADX INFO: renamed from: com.arflix.tv.data.local.PluginDataStore$addRepository$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.local.PluginDataStore", f = "PluginDataStore.kt", l = {96, 99}, m = "addRepository", v = 2)
    public static final class AnonymousClass1 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PluginDataStore.this.addRepository(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.local.PluginDataStore$clearAllScraperCode$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.local.PluginDataStore$clearAllScraperCode$2", f = "PluginDataStore.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements p<k0, d<? super t0>, Object> {
        int label;

        public AnonymousClass2(d<? super AnonymousClass2> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return PluginDataStore.this.new AnonymousClass2(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            File[] fileArrListFiles = PluginDataStore.this.getCodeDir().listFiles();
            if (fileArrListFiles == null) {
                return null;
            }
            for (File file : fileArrListFiles) {
                file.delete();
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.local.PluginDataStore$deleteScraperCode$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Z"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.local.PluginDataStore$deleteScraperCode$2", f = "PluginDataStore.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C06542 extends f7.j implements p<k0, d<? super Boolean>, Object> {
        final /* synthetic */ String $scraperId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06542(String str, d<? super C06542> dVar) {
            super(2, dVar);
            this.$scraperId = str;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return PluginDataStore.this.new C06542(this.$scraperId, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return Boolean.valueOf(new File(PluginDataStore.this.getCodeDir(), androidx.compose.material3.d.m(this.$scraperId, ".js")).delete());
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super Boolean> dVar) {
            return ((C06542) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.local.PluginDataStore$ensureCodeDir$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Ljava/io/File;", "<anonymous>", "(Lka/k0;)Ljava/io/File;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.local.PluginDataStore$ensureCodeDir$2", f = "PluginDataStore.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C06552 extends f7.j implements p<k0, d<? super File>, Object> {
        int label;

        public C06552(d<? super C06552> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return PluginDataStore.this.new C06552(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            File codeDir = PluginDataStore.this.getCodeDir();
            codeDir.mkdirs();
            return codeDir;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super File> dVar) {
            return ((C06552) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.local.PluginDataStore$getScraperCode$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/String;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.local.PluginDataStore$getScraperCode$2", f = "PluginDataStore.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C06562 extends f7.j implements p<k0, d<? super String>, Object> {
        final /* synthetic */ String $scraperId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06562(String str, d<? super C06562> dVar) {
            super(2, dVar);
            this.$scraperId = str;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return PluginDataStore.this.new C06562(this.$scraperId, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            File file = new File(PluginDataStore.this.getCodeDir(), androidx.compose.material3.d.m(this.$scraperId, ".js"));
            if (file.exists()) {
                return i.s0(file);
            }
            return null;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super String> dVar) {
            return ((C06562) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.local.PluginDataStore$getScraperSettings$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.local.PluginDataStore", f = "PluginDataStore.kt", l = {220}, m = "getScraperSettings", v = 2)
    public static final class C06571 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C06571(d<? super C06571> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PluginDataStore.this.getScraperSettings(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.local.PluginDataStore$removeRepository$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.local.PluginDataStore", f = "PluginDataStore.kt", l = {105, 107}, m = "removeRepository", v = 2)
    public static final class C06581 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C06581(d<? super C06581> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PluginDataStore.this.removeRepository(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.local.PluginDataStore$saveRepositories$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.local.PluginDataStore$saveRepositories$2", f = "PluginDataStore.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C06592 extends f7.j implements p<b, d<? super t0>, Object> {
        final /* synthetic */ String $json;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06592(String str, d<? super C06592> dVar) {
            super(2, dVar);
            this.$json = str;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C06592 c06592 = PluginDataStore.this.new C06592(this.$json, dVar);
            c06592.L$0 = obj;
            return c06592;
        }

        @Override // r7.p
        public final Object invoke(b bVar, d<? super t0> dVar) {
            return ((C06592) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            b bVar = (b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            bVar.d(PluginDataStore.this.repositoriesKey, this.$json);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.local.PluginDataStore$saveScraperCode$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.local.PluginDataStore", f = "PluginDataStore.kt", l = {193, 194}, m = "saveScraperCode", v = 2)
    public static final class C06601 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C06601(d<? super C06601> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PluginDataStore.this.saveScraperCode(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.local.PluginDataStore$saveScraperCode$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.local.PluginDataStore$saveScraperCode$2", f = "PluginDataStore.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C06612 extends f7.j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ String $code;
        final /* synthetic */ File $dir;
        final /* synthetic */ String $scraperId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06612(File file, String str, String str2, d<? super C06612> dVar) {
            super(2, dVar);
            this.$dir = file;
            this.$scraperId = str;
            this.$code = str2;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new C06612(this.$dir, this.$scraperId, this.$code, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            i.v0(new File(this.$dir, androidx.compose.material3.d.m(this.$scraperId, ".js")), this.$code);
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((C06612) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.local.PluginDataStore$saveScrapers$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.local.PluginDataStore$saveScrapers$2", f = "PluginDataStore.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C06622 extends f7.j implements p<b, d<? super t0>, Object> {
        final /* synthetic */ String $json;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06622(String str, d<? super C06622> dVar) {
            super(2, dVar);
            this.$json = str;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C06622 c06622 = PluginDataStore.this.new C06622(this.$json, dVar);
            c06622.L$0 = obj;
            return c06622;
        }

        @Override // r7.p
        public final Object invoke(b bVar, d<? super t0> dVar) {
            return ((C06622) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            b bVar = (b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            bVar.d(PluginDataStore.this.scrapersKey, this.$json);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.local.PluginDataStore$setGroupStreamsByRepository$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.local.PluginDataStore$setGroupStreamsByRepository$2", f = "PluginDataStore.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C06632 extends f7.j implements p<b, d<? super t0>, Object> {
        final /* synthetic */ boolean $enabled;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06632(boolean z, d<? super C06632> dVar) {
            super(2, dVar);
            this.$enabled = z;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C06632 c06632 = PluginDataStore.this.new C06632(this.$enabled, dVar);
            c06632.L$0 = obj;
            return c06632;
        }

        @Override // r7.p
        public final Object invoke(b bVar, d<? super t0> dVar) {
            return ((C06632) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            b bVar = (b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            bVar.d(PluginDataStore.this.groupStreamsByRepositoryKey, Boolean.valueOf(this.$enabled));
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.local.PluginDataStore$setPluginsEnabled$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.local.PluginDataStore$setPluginsEnabled$2", f = "PluginDataStore.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C06642 extends f7.j implements p<b, d<? super t0>, Object> {
        final /* synthetic */ boolean $enabled;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06642(boolean z, d<? super C06642> dVar) {
            super(2, dVar);
            this.$enabled = z;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C06642 c06642 = PluginDataStore.this.new C06642(this.$enabled, dVar);
            c06642.L$0 = obj;
            return c06642;
        }

        @Override // r7.p
        public final Object invoke(b bVar, d<? super t0> dVar) {
            return ((C06642) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            b bVar = (b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            bVar.d(PluginDataStore.this.pluginsEnabledKey, Boolean.valueOf(this.$enabled));
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.local.PluginDataStore$setScraperEnabled$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.local.PluginDataStore", f = "PluginDataStore.kt", l = {147, 154}, m = "setScraperEnabled", v = 2)
    public static final class C06651 extends c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C06651(d<? super C06651> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PluginDataStore.this.setScraperEnabled(null, false, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.local.PluginDataStore$setScraperSettings$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.local.PluginDataStore", f = "PluginDataStore.kt", l = {237, 253}, m = "setScraperSettings", v = 2)
    public static final class C06661 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public C06661(d<? super C06661> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PluginDataStore.this.setScraperSettings(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.local.PluginDataStore$setScraperSettings$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", TtmlNode.TAG_P, "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.local.PluginDataStore$setScraperSettings$2", f = "PluginDataStore.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C06672 extends f7.j implements p<b, d<? super t0>, Object> {
        final /* synthetic */ String $json;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06672(String str, d<? super C06672> dVar) {
            super(2, dVar);
            this.$json = str;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C06672 c06672 = PluginDataStore.this.new C06672(this.$json, dVar);
            c06672.L$0 = obj;
            return c06672;
        }

        @Override // r7.p
        public final Object invoke(b bVar, d<? super t0> dVar) {
            return ((C06672) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            b bVar = (b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            bVar.d(PluginDataStore.this.scraperSettingsKey, this.$json);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.local.PluginDataStore$updateRepository$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.local.PluginDataStore", f = "PluginDataStore.kt", l = {111, AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID}, m = "updateRepository", v = 2)
    public static final class C06681 extends c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C06681(d<? super C06681> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PluginDataStore.this.updateRepository(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.local.PluginDataStore$updateRepository$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.local.PluginDataStore$updateRepository$2", f = "PluginDataStore.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C06692 extends f7.j implements p<b, d<? super t0>, Object> {
        final /* synthetic */ String $json;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06692(String str, d<? super C06692> dVar) {
            super(2, dVar);
            this.$json = str;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C06692 c06692 = PluginDataStore.this.new C06692(this.$json, dVar);
            c06692.L$0 = obj;
            return c06692;
        }

        @Override // r7.p
        public final Object invoke(b bVar, d<? super t0> dVar) {
            return ((C06692) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            b bVar = (b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            bVar.d(PluginDataStore.this.repositoriesKey, this.$json);
            return t0.f22605a;
        }
    }

    @Inject
    public PluginDataStore(@ApplicationContext Context context, z zVar, ProfileDataStoreFactory profileDataStoreFactory) {
        this.context = context;
        this.moshi = zVar;
        this.factory = profileDataStoreFactory;
        androidx.work.impl.constraints.i iVar = new androidx.work.impl.constraints.i(1, 2);
        this.effectiveProfileIdFlow = iVar;
        this.repositoriesKey = new e("repositories");
        this.scrapersKey = new e("scrapers");
        this.pluginsEnabledKey = new e("plugins_enabled");
        this.groupStreamsByRepositoryKey = new e("group_streams_by_repository");
        this.scraperSettingsKey = new e("scraper_settings");
        this.repoListType = e0.f(List.class, PluginRepository.class);
        this.scraperListType = e0.f(List.class, ScraperInfo.class);
        this.settingsMapType = e0.f(Map.class, String.class, e0.f(Map.class, String.class, Object.class));
        this.repositories = y0.p(iVar, new PluginDataStore$special$$inlined$flatMapLatest$1(null, this));
        this.scrapers = y0.p(iVar, new PluginDataStore$special$$inlined$flatMapLatest$2(null, this));
        this.pluginsEnabled = y0.p(iVar, new PluginDataStore$special$$inlined$flatMapLatest$3(null, this));
        this.groupStreamsByRepository = y0.p(iVar, new PluginDataStore$special$$inlined$flatMapLatest$4(null, this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean addRepository$lambda$0(PluginRepository pluginRepository, PluginRepository pluginRepository2) {
        return kotlin.jvm.internal.p.a(pluginRepository2.getId(), pluginRepository.getId());
    }

    private final int effectiveProfileId() {
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object ensureCodeDir(d<? super File> dVar) {
        return m0.y(x0.f19655d, new C06552(null), dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean removeRepository$lambda$0(String str, PluginRepository pluginRepository) {
        return kotlin.jvm.internal.p.a(pluginRepository.getId(), str);
    }

    private final g<c1.g> store(int profileId) {
        return this.factory.get(profileId, FEATURE);
    }

    public static /* synthetic */ g store$default(PluginDataStore pluginDataStore, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = pluginDataStore.effectiveProfileId();
        }
        return pluginDataStore.store(i10);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0070, code lost:
    
        if (saveRepositories(r1, r0) == r4) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object addRepository(com.arflix.tv.domain.model.PluginRepository r6, d7.d<? super x6.t0> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.arflix.tv.data.local.PluginDataStore.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            com.arflix.tv.data.local.PluginDataStore$addRepository$1 r0 = (com.arflix.tv.data.local.PluginDataStore.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.local.PluginDataStore$addRepository$1 r0 = new com.arflix.tv.data.local.PluginDataStore$addRepository$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L42
            if (r1 == r3) goto L3a
            if (r1 != r2) goto L32
            java.lang.Object r6 = r0.L$1
            java.util.List r6 = (java.util.List) r6
            java.lang.Object r6 = r0.L$0
            com.arflix.tv.domain.model.PluginRepository r6 = (com.arflix.tv.domain.model.PluginRepository) r6
            k2.c.G(r7)
            goto L73
        L32:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3a:
            java.lang.Object r6 = r0.L$0
            com.arflix.tv.domain.model.PluginRepository r6 = (com.arflix.tv.domain.model.PluginRepository) r6
            k2.c.G(r7)
            goto L52
        L42:
            k2.c.G(r7)
            na.j<java.util.List<com.arflix.tv.domain.model.PluginRepository>> r7 = r5.repositories
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r7 = na.y0.l(r7, r0)
            if (r7 != r4) goto L52
            goto L72
        L52:
            java.util.Collection r7 = (java.util.Collection) r7
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>(r7)
            com.arflix.tv.cast.a r7 = new com.arflix.tv.cast.a
            r3 = 1
            r7.<init>(r6, r3)
            kotlin.collections.x.M0(r1, r7)
            r1.add(r6)
            r6 = 0
            r0.L$0 = r6
            r0.L$1 = r6
            r0.label = r2
            java.lang.Object r6 = r5.saveRepositories(r1, r0)
            if (r6 != r4) goto L73
        L72:
            return r4
        L73:
            x6.t0 r6 = x6.t0.f22605a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.local.PluginDataStore.addRepository(com.arflix.tv.domain.model.PluginRepository, d7.d):java.lang.Object");
    }

    public final Object clearAllScraperCode(d<? super t0> dVar) {
        return m0.y(x0.f19655d, new AnonymousClass2(null), dVar);
    }

    public final Object deleteScraperCode(String str, d<? super t0> dVar) throws Throwable {
        Object objY = m0.y(x0.f19655d, new C06542(str, null), dVar);
        return objY == e7.a.f15033i ? objY : t0.f22605a;
    }

    public final File getCodeDir() {
        int iEffectiveProfileId = effectiveProfileId();
        return new File(this.context.getFilesDir(), iEffectiveProfileId == 1 ? "plugin_code" : a0.c.i(iEffectiveProfileId, "plugin_code_p"));
    }

    public final j<Boolean> getGroupStreamsByRepository() {
        return this.groupStreamsByRepository;
    }

    public final j<Boolean> getPluginsEnabled() {
        return this.pluginsEnabled;
    }

    public final j<List<PluginRepository>> getRepositories() {
        return this.repositories;
    }

    public final Object getScraperCode(String str, d<? super String> dVar) {
        return m0.y(x0.f19655d, new C06562(str, null), dVar);
    }

    public final File getScraperCodeFile(String scraperId) {
        return new File(getCodeDir(), scraperId.concat(".js"));
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getScraperSettings(java.lang.String r6, d7.d<? super java.util.Map<java.lang.String, ? extends java.lang.Object>> r7) throws java.lang.Exception {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.arflix.tv.data.local.PluginDataStore.C06571
            if (r0 == 0) goto L13
            r0 = r7
            com.arflix.tv.data.local.PluginDataStore$getScraperSettings$1 r0 = (com.arflix.tv.data.local.PluginDataStore.C06571) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.local.PluginDataStore$getScraperSettings$1 r0 = new com.arflix.tv.data.local.PluginDataStore$getScraperSettings$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L32
            if (r1 != r3) goto L2a
            java.lang.Object r6 = r0.L$0
            java.lang.String r6 = (java.lang.String) r6
            k2.c.G(r7)
            goto L4b
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L32:
            k2.c.G(r7)
            r7 = 0
            z0.g r7 = store$default(r5, r7, r3, r2)
            na.j r7 = r7.getData()
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r7 = na.y0.l(r7, r0)
            e7.a r0 = e7.a.f15033i
            if (r7 != r0) goto L4b
            return r0
        L4b:
            c1.g r7 = (c1.g) r7
            c1.e r0 = r5.scraperSettingsKey
            c1.b r7 = (c1.b) r7
            java.util.LinkedHashMap r7 = r7.f7368a
            java.lang.Object r7 = r7.get(r0)
            java.lang.String r7 = (java.lang.String) r7
            kotlin.collections.a0 r0 = kotlin.collections.a0.f19683i
            if (r7 == 0) goto L7a
            com.squareup.moshi.z r1 = r5.moshi     // Catch: java.lang.Exception -> L73
            java.lang.reflect.ParameterizedType r3 = r5.settingsMapType     // Catch: java.lang.Exception -> L73
            r1.getClass()     // Catch: java.lang.Exception -> L73
            java.util.Set r4 = j6.e.f19235a     // Catch: java.lang.Exception -> L73
            com.squareup.moshi.l r1 = r1.a(r3, r4, r2)     // Catch: java.lang.Exception -> L73
            java.lang.Object r7 = r1.b(r7)     // Catch: java.lang.Exception -> L73
            java.util.Map r7 = (java.util.Map) r7     // Catch: java.lang.Exception -> L73
            if (r7 != 0) goto L7b
            goto L7a
        L73:
            r7 = move-exception
            boolean r1 = r7 instanceof java.util.concurrent.CancellationException
            if (r1 != 0) goto L79
            goto L7a
        L79:
            throw r7
        L7a:
            r7 = r0
        L7b:
            java.lang.Object r6 = r7.get(r6)
            boolean r7 = r6 instanceof java.util.Map
            if (r7 == 0) goto L86
            r2 = r6
            java.util.Map r2 = (java.util.Map) r2
        L86:
            if (r2 != 0) goto L89
            goto L8a
        L89:
            r0 = r2
        L8a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.local.PluginDataStore.getScraperSettings(java.lang.String, d7.d):java.lang.Object");
    }

    public final j<List<ScraperInfo>> getScrapers() {
        return this.scrapers;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x006d, code lost:
    
        if (saveRepositories(r1, r0) == r4) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object removeRepository(java.lang.String r6, d7.d<? super x6.t0> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.arflix.tv.data.local.PluginDataStore.C06581
            if (r0 == 0) goto L13
            r0 = r7
            com.arflix.tv.data.local.PluginDataStore$removeRepository$1 r0 = (com.arflix.tv.data.local.PluginDataStore.C06581) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.local.PluginDataStore$removeRepository$1 r0 = new com.arflix.tv.data.local.PluginDataStore$removeRepository$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L42
            if (r1 == r3) goto L3a
            if (r1 != r2) goto L32
            java.lang.Object r6 = r0.L$1
            java.util.List r6 = (java.util.List) r6
            java.lang.Object r6 = r0.L$0
            java.lang.String r6 = (java.lang.String) r6
            k2.c.G(r7)
            goto L70
        L32:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3a:
            java.lang.Object r6 = r0.L$0
            java.lang.String r6 = (java.lang.String) r6
            k2.c.G(r7)
            goto L52
        L42:
            k2.c.G(r7)
            na.j<java.util.List<com.arflix.tv.domain.model.PluginRepository>> r7 = r5.repositories
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r7 = na.y0.l(r7, r0)
            if (r7 != r4) goto L52
            goto L6f
        L52:
            java.util.Collection r7 = (java.util.Collection) r7
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>(r7)
            com.arflix.tv.data.local.a r7 = new com.arflix.tv.data.local.a
            r3 = 0
            r7.<init>(r6, r3)
            kotlin.collections.x.M0(r1, r7)
            r6 = 0
            r0.L$0 = r6
            r0.L$1 = r6
            r0.label = r2
            java.lang.Object r6 = r5.saveRepositories(r1, r0)
            if (r6 != r4) goto L70
        L6f:
            return r4
        L70:
            x6.t0 r6 = x6.t0.f22605a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.local.PluginDataStore.removeRepository(java.lang.String, d7.d):java.lang.Object");
    }

    public final Object saveRepositories(List<PluginRepository> list, d<? super t0> dVar) {
        z zVar = this.moshi;
        ParameterizedType parameterizedType = this.repoListType;
        zVar.getClass();
        Object objO = t.o(store$default(this, 0, 1, null), new C06592(zVar.a(parameterizedType, j6.e.f19235a, null).d(list), null), dVar);
        return objO == e7.a.f15033i ? objO : t0.f22605a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0071, code lost:
    
        if (ka.m0.y(r1, r3, r0) == r4) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object saveScraperCode(java.lang.String r7, java.lang.String r8, d7.d<? super x6.t0> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof com.arflix.tv.data.local.PluginDataStore.C06601
            if (r0 == 0) goto L13
            r0 = r9
            com.arflix.tv.data.local.PluginDataStore$saveScraperCode$1 r0 = (com.arflix.tv.data.local.PluginDataStore.C06601) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.local.PluginDataStore$saveScraperCode$1 r0 = new com.arflix.tv.data.local.PluginDataStore$saveScraperCode$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L4b
            if (r1 == r3) goto L3e
            if (r1 != r2) goto L36
            java.lang.Object r7 = r0.L$2
            java.io.File r7 = (java.io.File) r7
            java.lang.Object r7 = r0.L$1
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r7 = r0.L$0
            java.lang.String r7 = (java.lang.String) r7
            k2.c.G(r9)
            goto L74
        L36:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3e:
            java.lang.Object r7 = r0.L$1
            r8 = r7
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r7 = r0.L$0
            java.lang.String r7 = (java.lang.String) r7
            k2.c.G(r9)
            goto L5b
        L4b:
            k2.c.G(r9)
            r0.L$0 = r7
            r0.L$1 = r8
            r0.label = r3
            java.lang.Object r9 = r6.ensureCodeDir(r0)
            if (r9 != r4) goto L5b
            goto L73
        L5b:
            java.io.File r9 = (java.io.File) r9
            ra.c r1 = ka.x0.f19655d
            com.arflix.tv.data.local.PluginDataStore$saveScraperCode$2 r3 = new com.arflix.tv.data.local.PluginDataStore$saveScraperCode$2
            r5 = 0
            r3.<init>(r9, r7, r8, r5)
            r0.L$0 = r5
            r0.L$1 = r5
            r0.L$2 = r5
            r0.label = r2
            java.lang.Object r7 = ka.m0.y(r1, r3, r0)
            if (r7 != r4) goto L74
        L73:
            return r4
        L74:
            x6.t0 r7 = x6.t0.f22605a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.local.PluginDataStore.saveScraperCode(java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    public final Object saveScrapers(List<ScraperInfo> list, d<? super t0> dVar) {
        z zVar = this.moshi;
        ParameterizedType parameterizedType = this.scraperListType;
        zVar.getClass();
        Object objO = t.o(store$default(this, 0, 1, null), new C06622(zVar.a(parameterizedType, j6.e.f19235a, null).d(list), null), dVar);
        return objO == e7.a.f15033i ? objO : t0.f22605a;
    }

    public final Object setGroupStreamsByRepository(boolean z, d<? super t0> dVar) {
        Object objO = t.o(store$default(this, 0, 1, null), new C06632(z, null), dVar);
        return objO == e7.a.f15033i ? objO : t0.f22605a;
    }

    public final Object setPluginsEnabled(boolean z, d<? super t0> dVar) {
        Object objO = t.o(store$default(this, 0, 1, null), new C06642(z, null), dVar);
        return objO == e7.a.f15033i ? objO : t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object setScraperEnabled(java.lang.String r25, boolean r26, d7.d<? super x6.t0> r27) {
        /*
            Method dump skipped, instruction units count: 213
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.local.PluginDataStore.setScraperEnabled(java.lang.String, boolean, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x00dd, code lost:
    
        if (androidx.work.impl.t.o(r11, r12, r0) == r6) goto L34;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object setScraperSettings(java.lang.String r10, java.util.Map<java.lang.String, ? extends java.lang.Object> r11, d7.d<? super x6.t0> r12) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 227
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.local.PluginDataStore.setScraperSettings(java.lang.String, java.util.Map, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object updateRepository(com.arflix.tv.domain.model.PluginRepository r11, d7.d<? super x6.t0> r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof com.arflix.tv.data.local.PluginDataStore.C06681
            if (r0 == 0) goto L13
            r0 = r12
            com.arflix.tv.data.local.PluginDataStore$updateRepository$1 r0 = (com.arflix.tv.data.local.PluginDataStore.C06681) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.local.PluginDataStore$updateRepository$1 r0 = new com.arflix.tv.data.local.PluginDataStore$updateRepository$1
            r0.<init>(r12)
        L18:
            java.lang.Object r12 = r0.result
            int r1 = r0.label
            x6.t0 r2 = x6.t0.f22605a
            r3 = 2
            r4 = 1
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L48
            if (r1 == r4) goto L40
            if (r1 != r3) goto L38
            java.lang.Object r11 = r0.L$2
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r11 = r0.L$1
            java.util.List r11 = (java.util.List) r11
            java.lang.Object r11 = r0.L$0
            com.arflix.tv.domain.model.PluginRepository r11 = (com.arflix.tv.domain.model.PluginRepository) r11
            k2.c.G(r12)
            return r2
        L38:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L40:
            java.lang.Object r11 = r0.L$0
            com.arflix.tv.domain.model.PluginRepository r11 = (com.arflix.tv.domain.model.PluginRepository) r11
            k2.c.G(r12)
            goto L58
        L48:
            k2.c.G(r12)
            na.j<java.util.List<com.arflix.tv.domain.model.PluginRepository>> r12 = r10.repositories
            r0.L$0 = r11
            r0.label = r4
            java.lang.Object r12 = na.y0.l(r12, r0)
            if (r12 != r5) goto L58
            goto Lb4
        L58:
            java.util.Collection r12 = (java.util.Collection) r12
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>(r12)
            java.util.Iterator r12 = r1.iterator()
            r6 = 0
            r7 = r6
        L65:
            boolean r8 = r12.hasNext()
            if (r8 == 0) goto L83
            java.lang.Object r8 = r12.next()
            com.arflix.tv.domain.model.PluginRepository r8 = (com.arflix.tv.domain.model.PluginRepository) r8
            java.lang.String r8 = r8.getId()
            java.lang.String r9 = r11.getId()
            boolean r8 = kotlin.jvm.internal.p.a(r8, r9)
            if (r8 == 0) goto L80
            goto L84
        L80:
            int r7 = r7 + 1
            goto L65
        L83:
            r7 = -1
        L84:
            if (r7 < 0) goto Lb5
            r1.set(r7, r11)
            com.squareup.moshi.z r11 = r10.moshi
            java.lang.reflect.ParameterizedType r12 = r10.repoListType
            r11.getClass()
            java.util.Set r8 = j6.e.f19235a
            r9 = 0
            com.squareup.moshi.l r11 = r11.a(r12, r8, r9)
            java.lang.String r11 = r11.d(r1)
            z0.g r12 = store$default(r10, r6, r4, r9)
            com.arflix.tv.data.local.PluginDataStore$updateRepository$2 r1 = new com.arflix.tv.data.local.PluginDataStore$updateRepository$2
            r1.<init>(r11, r9)
            r0.L$0 = r9
            r0.L$1 = r9
            r0.L$2 = r9
            r0.I$0 = r7
            r0.label = r3
            java.lang.Object r11 = androidx.work.impl.t.o(r12, r1, r0)
            if (r11 != r5) goto Lb5
        Lb4:
            return r5
        Lb5:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.local.PluginDataStore.updateRepository(com.arflix.tv.domain.model.PluginRepository, d7.d):java.lang.Object");
    }
}
