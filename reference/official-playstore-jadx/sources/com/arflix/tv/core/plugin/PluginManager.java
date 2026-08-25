package com.arflix.tv.core.plugin;

import androidx.work.impl.constraints.i;
import com.arflix.tv.domain.model.LocalScraperResult;
import com.arflix.tv.domain.model.PluginRepository;
import com.arflix.tv.domain.model.RemotePluginInfo;
import com.arflix.tv.domain.model.ScraperInfo;
import d7.d;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.collections.z;
import na.j;
import x6.c0;
import x6.d0;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0007\u0018\u00002\u00020\u0001B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0003J\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0086@¢\u0006\u0004\b\n\u0010\u000bJ\u0018\u0010\u000e\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0006H\u0086@¢\u0006\u0004\b\u000e\u0010\u000bJ\u0010\u0010\u000f\u001a\u00020\u0004H\u0086@¢\u0006\u0004\b\u000f\u0010\u0010J(\u0010\u0016\u001a\u00020\u00042\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\b\b\u0002\u0010\u0015\u001a\u00020\u0014H\u0086@¢\u0006\u0004\b\u0016\u0010\u0017J(\u0010\u0016\u001a\u00020\u00042\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u00112\b\b\u0002\u0010\u0015\u001a\u00020\u0014H\u0087@¢\u0006\u0004\b\u0019\u0010\u0017J\u001e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\u0006\u0010\r\u001a\u00020\u0006H\u0086@¢\u0006\u0004\b\u001a\u0010\u000bJ \u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u0014H\u0086@¢\u0006\u0004\b\u001e\u0010\u001fJ \u0010 \u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u0014H\u0086@¢\u0006\u0004\b \u0010\u001fJ\u0018\u0010!\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0014H\u0086@¢\u0006\u0004\b!\u0010\"J\u0018\u0010#\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0014H\u0086@¢\u0006\u0004\b#\u0010\"J>\u0010*\u001a\b\u0012\u0004\u0012\u00020)0\u00112\u0006\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u00062\n\b\u0002\u0010'\u001a\u0004\u0018\u00010&2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010&H\u0086@¢\u0006\u0004\b*\u0010+JO\u0010/\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020.\u0012\f\u0012\n\u0012\u0004\u0012\u00020)\u0018\u00010\u00110-0,2\u0006\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u00062\n\b\u0002\u0010'\u001a\u0004\u0018\u00010&2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010&¢\u0006\u0004\b/\u00100JB\u00102\u001a\b\u0012\u0004\u0012\u00020)0\u00112\u0006\u00101\u001a\u00020.2\u0006\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u00062\b\u0010'\u001a\u0004\u0018\u00010&2\b\u0010(\u001a\u0004\u0018\u00010&H\u0086@¢\u0006\u0004\b2\u00103J0\u00106\u001a\u001a\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u0011\u0012\u0004\u0012\u0002040-0\b2\u0006\u0010\u001c\u001a\u00020\u0006H\u0086@¢\u0006\u0004\b5\u0010\u000bR#\u00107\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00110,8\u0006¢\u0006\f\n\u0004\b7\u00108\u001a\u0004\b9\u0010:R#\u0010;\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020.0\u00110,8\u0006¢\u0006\f\n\u0004\b;\u00108\u001a\u0004\b<\u0010:R\u001d\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00140,8\u0006¢\u0006\f\n\u0004\b=\u00108\u001a\u0004\b>\u0010:R\u001d\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00140,8\u0006¢\u0006\f\n\u0004\b?\u00108\u001a\u0004\b@\u0010:R\"\u0010A\u001a\u00020\u00148\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bA\u0010B\u001a\u0004\bA\u0010C\"\u0004\bD\u0010ER#\u0010F\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020.0\u00110,8\u0006¢\u0006\f\n\u0004\bF\u00108\u001a\u0004\bG\u0010:¨\u0006H"}, d2 = {"Lcom/arflix/tv/core/plugin/PluginManager;", "", "<init>", "()V", "Lx6/t0;", "flushPendingSync", "", "manifestUrl", "Lx6/d0;", "Lcom/arflix/tv/domain/model/PluginRepository;", "addRepository-gIAlu-s", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "addRepository", "repoId", "removeRepository", "clearAllPlugins", "(Ld7/d;)Ljava/lang/Object;", "", "Lcom/arflix/tv/domain/model/RemotePluginInfo;", "remotePlugins", "", "removeMissingLocal", "reconcileWithRemoteRepoUrls", "(Ljava/util/List;ZLd7/d;)Ljava/lang/Object;", "remoteUrls", "reconcileWithRemoteRepoUrlStrings", "refreshRepository-gIAlu-s", "refreshRepository", "scraperId", "enabled", "toggleScraper", "(Ljava/lang/String;ZLd7/d;)Ljava/lang/Object;", "toggleAllScrapersForRepo", "setPluginsEnabled", "(ZLd7/d;)Ljava/lang/Object;", "setGroupStreamsByRepository", "tmdbId", "mediaType", "", "season", "episode", "Lcom/arflix/tv/domain/model/LocalScraperResult;", "executeScrapers", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "Lna/j;", "Lx6/x;", "Lcom/arflix/tv/domain/model/ScraperInfo;", "executeScrapersStreaming", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Lna/j;", "scraper", "executeScraper", "(Lcom/arflix/tv/domain/model/ScraperInfo;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/core/plugin/TestDiagnostics;", "testScraper-gIAlu-s", "testScraper", "repositories", "Lna/j;", "getRepositories", "()Lna/j;", "scrapers", "getScrapers", "pluginsEnabled", "getPluginsEnabled", "groupStreamsByRepository", "getGroupStreamsByRepository", "isSyncingFromRemote", "Z", "()Z", "setSyncingFromRemote", "(Z)V", "enabledScrapers", "getEnabledScrapers", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PluginManager {
    public static final int $stable = 8;
    private final j<List<ScraperInfo>> enabledScrapers;
    private final j<Boolean> groupStreamsByRepository;
    private boolean isSyncingFromRemote;
    private final j<Boolean> pluginsEnabled;
    private final j<List<PluginRepository>> repositories;
    private final j<List<ScraperInfo>> scrapers;

    @Inject
    public PluginManager() {
        int i10 = 2;
        z zVar = z.f19728i;
        this.repositories = new i(zVar, i10);
        this.scrapers = new i(zVar, i10);
        Boolean bool = Boolean.FALSE;
        int i11 = 2;
        this.pluginsEnabled = new i(bool, i11);
        this.groupStreamsByRepository = new i(bool, i11);
        this.enabledScrapers = new i(zVar, 2);
    }

    public static /* synthetic */ Object executeScrapers$default(PluginManager pluginManager, String str, String str2, Integer num, Integer num2, d dVar, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            num = null;
        }
        if ((i10 & 8) != 0) {
            num2 = null;
        }
        return pluginManager.executeScrapers(str, str2, num, num2, dVar);
    }

    public static /* synthetic */ j executeScrapersStreaming$default(PluginManager pluginManager, String str, String str2, Integer num, Integer num2, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            num = null;
        }
        if ((i10 & 8) != 0) {
            num2 = null;
        }
        return pluginManager.executeScrapersStreaming(str, str2, num, num2);
    }

    public static /* synthetic */ Object reconcileWithRemoteRepoUrlStrings$default(PluginManager pluginManager, List list, boolean z, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = true;
        }
        return pluginManager.reconcileWithRemoteRepoUrlStrings(list, z, dVar);
    }

    public static /* synthetic */ Object reconcileWithRemoteRepoUrls$default(PluginManager pluginManager, List list, boolean z, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = true;
        }
        return pluginManager.reconcileWithRemoteRepoUrls(list, z, dVar);
    }

    /* JADX INFO: renamed from: addRepository-gIAlu-s, reason: not valid java name */
    public final Object m6064addRepositorygIAlus(String str, d<? super d0> dVar) {
        return new c0(new Exception("Plugins are not supported in this version"));
    }

    public final Object clearAllPlugins(d<? super t0> dVar) {
        return t0.f22605a;
    }

    public final Object executeScraper(ScraperInfo scraperInfo, String str, String str2, Integer num, Integer num2, d<? super List<LocalScraperResult>> dVar) {
        return z.f19728i;
    }

    public final Object executeScrapers(String str, String str2, Integer num, Integer num2, d<? super List<LocalScraperResult>> dVar) {
        return z.f19728i;
    }

    public final j<x> executeScrapersStreaming(String tmdbId, String mediaType, Integer season, Integer episode) {
        return na.i.f20581i;
    }

    public final void flushPendingSync() {
    }

    public final j<List<ScraperInfo>> getEnabledScrapers() {
        return this.enabledScrapers;
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

    public final j<List<ScraperInfo>> getScrapers() {
        return this.scrapers;
    }

    /* JADX INFO: renamed from: isSyncingFromRemote, reason: from getter */
    public final boolean getIsSyncingFromRemote() {
        return this.isSyncingFromRemote;
    }

    public final Object reconcileWithRemoteRepoUrlStrings(List<String> list, boolean z, d<? super t0> dVar) {
        return t0.f22605a;
    }

    public final Object reconcileWithRemoteRepoUrls(List<RemotePluginInfo> list, boolean z, d<? super t0> dVar) {
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: refreshRepository-gIAlu-s, reason: not valid java name */
    public final Object m6065refreshRepositorygIAlus(String str, d<? super d0> dVar) {
        return new c0(new Exception("Plugins are not supported in this version"));
    }

    public final Object removeRepository(String str, d<? super t0> dVar) {
        return t0.f22605a;
    }

    public final Object setGroupStreamsByRepository(boolean z, d<? super t0> dVar) {
        return t0.f22605a;
    }

    public final Object setPluginsEnabled(boolean z, d<? super t0> dVar) {
        return t0.f22605a;
    }

    public final void setSyncingFromRemote(boolean z) {
        this.isSyncingFromRemote = z;
    }

    /* JADX INFO: renamed from: testScraper-gIAlu-s, reason: not valid java name */
    public final Object m6066testScrapergIAlus(String str, d<? super d0> dVar) {
        return new c0(new Exception("Plugins are not supported in this version"));
    }

    public final Object toggleAllScrapersForRepo(String str, boolean z, d<? super t0> dVar) {
        return t0.f22605a;
    }

    public final Object toggleScraper(String str, boolean z, d<? super t0> dVar) {
        return t0.f22605a;
    }
}
