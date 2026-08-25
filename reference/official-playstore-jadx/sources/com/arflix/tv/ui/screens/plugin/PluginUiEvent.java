package com.arflix.tv.ui.screens.plugin;

import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0012\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0082\u0001\u0012\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%¨\u0006&À\u0006\u0003"}, d2 = {"Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent;", "", "AddRepository", "RemoveRepository", "RefreshRepository", "ToggleScraper", "ToggleAllScrapersForRepo", "TestScraper", "SetPluginsEnabled", "SetGroupStreamsByRepository", "ClearTestResults", "ClearError", "ClearSuccess", "StartQrMode", "StopQrMode", "ConfirmPendingRepoChange", "RejectPendingRepoChange", "ConfirmPendingScraperEnable", "DismissPendingScraperEnable", "ResetAllPlugins", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$AddRepository;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$ClearError;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$ClearSuccess;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$ClearTestResults;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$ConfirmPendingRepoChange;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$ConfirmPendingScraperEnable;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$DismissPendingScraperEnable;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$RefreshRepository;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$RejectPendingRepoChange;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$RemoveRepository;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$ResetAllPlugins;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$SetGroupStreamsByRepository;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$SetPluginsEnabled;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$StartQrMode;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$StopQrMode;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$TestScraper;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$ToggleAllScrapersForRepo;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$ToggleScraper;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface PluginUiEvent {

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$AddRepository;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent;", "url", "", "<init>", "(Ljava/lang/String;)V", "getUrl", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class AddRepository implements PluginUiEvent {
        public static final int $stable = 0;
        private final String url;

        public AddRepository(String str) {
            this.url = str;
        }

        public static /* synthetic */ AddRepository copy$default(AddRepository addRepository, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = addRepository.url;
            }
            return addRepository.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        public final AddRepository copy(String url) {
            return new AddRepository(url);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof AddRepository) && p.a(this.url, ((AddRepository) other).url);
        }

        public final String getUrl() {
            return this.url;
        }

        public int hashCode() {
            return this.url.hashCode();
        }

        public String toString() {
            return a0.c.l("AddRepository(url=", this.url, ")");
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$ClearError;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ClearError implements PluginUiEvent {
        public static final int $stable = 0;
        public static final ClearError INSTANCE = new ClearError();

        private ClearError() {
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof ClearError);
        }

        public int hashCode() {
            return -1443063519;
        }

        public String toString() {
            return "ClearError";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$ClearSuccess;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ClearSuccess implements PluginUiEvent {
        public static final int $stable = 0;
        public static final ClearSuccess INSTANCE = new ClearSuccess();

        private ClearSuccess() {
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof ClearSuccess);
        }

        public int hashCode() {
            return 102212828;
        }

        public String toString() {
            return "ClearSuccess";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$ClearTestResults;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ClearTestResults implements PluginUiEvent {
        public static final int $stable = 0;
        public static final ClearTestResults INSTANCE = new ClearTestResults();

        private ClearTestResults() {
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof ClearTestResults);
        }

        public int hashCode() {
            return -1417218211;
        }

        public String toString() {
            return "ClearTestResults";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$ConfirmPendingRepoChange;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ConfirmPendingRepoChange implements PluginUiEvent {
        public static final int $stable = 0;
        public static final ConfirmPendingRepoChange INSTANCE = new ConfirmPendingRepoChange();

        private ConfirmPendingRepoChange() {
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof ConfirmPendingRepoChange);
        }

        public int hashCode() {
            return 748084927;
        }

        public String toString() {
            return "ConfirmPendingRepoChange";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$ConfirmPendingScraperEnable;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ConfirmPendingScraperEnable implements PluginUiEvent {
        public static final int $stable = 0;
        public static final ConfirmPendingScraperEnable INSTANCE = new ConfirmPendingScraperEnable();

        private ConfirmPendingScraperEnable() {
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof ConfirmPendingScraperEnable);
        }

        public int hashCode() {
            return -1410748092;
        }

        public String toString() {
            return "ConfirmPendingScraperEnable";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$DismissPendingScraperEnable;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class DismissPendingScraperEnable implements PluginUiEvent {
        public static final int $stable = 0;
        public static final DismissPendingScraperEnable INSTANCE = new DismissPendingScraperEnable();

        private DismissPendingScraperEnable() {
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof DismissPendingScraperEnable);
        }

        public int hashCode() {
            return 624604174;
        }

        public String toString() {
            return "DismissPendingScraperEnable";
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$RefreshRepository;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent;", "repoId", "", "<init>", "(Ljava/lang/String;)V", "getRepoId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class RefreshRepository implements PluginUiEvent {
        public static final int $stable = 0;
        private final String repoId;

        public RefreshRepository(String str) {
            this.repoId = str;
        }

        public static /* synthetic */ RefreshRepository copy$default(RefreshRepository refreshRepository, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = refreshRepository.repoId;
            }
            return refreshRepository.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getRepoId() {
            return this.repoId;
        }

        public final RefreshRepository copy(String repoId) {
            return new RefreshRepository(repoId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof RefreshRepository) && p.a(this.repoId, ((RefreshRepository) other).repoId);
        }

        public final String getRepoId() {
            return this.repoId;
        }

        public int hashCode() {
            return this.repoId.hashCode();
        }

        public String toString() {
            return a0.c.l("RefreshRepository(repoId=", this.repoId, ")");
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$RejectPendingRepoChange;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class RejectPendingRepoChange implements PluginUiEvent {
        public static final int $stable = 0;
        public static final RejectPendingRepoChange INSTANCE = new RejectPendingRepoChange();

        private RejectPendingRepoChange() {
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof RejectPendingRepoChange);
        }

        public int hashCode() {
            return 1902616884;
        }

        public String toString() {
            return "RejectPendingRepoChange";
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$RemoveRepository;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent;", "repoId", "", "<init>", "(Ljava/lang/String;)V", "getRepoId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class RemoveRepository implements PluginUiEvent {
        public static final int $stable = 0;
        private final String repoId;

        public RemoveRepository(String str) {
            this.repoId = str;
        }

        public static /* synthetic */ RemoveRepository copy$default(RemoveRepository removeRepository, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = removeRepository.repoId;
            }
            return removeRepository.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getRepoId() {
            return this.repoId;
        }

        public final RemoveRepository copy(String repoId) {
            return new RemoveRepository(repoId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof RemoveRepository) && p.a(this.repoId, ((RemoveRepository) other).repoId);
        }

        public final String getRepoId() {
            return this.repoId;
        }

        public int hashCode() {
            return this.repoId.hashCode();
        }

        public String toString() {
            return a0.c.l("RemoveRepository(repoId=", this.repoId, ")");
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$ResetAllPlugins;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ResetAllPlugins implements PluginUiEvent {
        public static final int $stable = 0;
        public static final ResetAllPlugins INSTANCE = new ResetAllPlugins();

        private ResetAllPlugins() {
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof ResetAllPlugins);
        }

        public int hashCode() {
            return -968131640;
        }

        public String toString() {
            return "ResetAllPlugins";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$SetGroupStreamsByRepository;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent;", "enabled", "", "<init>", "(Z)V", "getEnabled", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SetGroupStreamsByRepository implements PluginUiEvent {
        public static final int $stable = 0;
        private final boolean enabled;

        public SetGroupStreamsByRepository(boolean z) {
            this.enabled = z;
        }

        public static /* synthetic */ SetGroupStreamsByRepository copy$default(SetGroupStreamsByRepository setGroupStreamsByRepository, boolean z, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                z = setGroupStreamsByRepository.enabled;
            }
            return setGroupStreamsByRepository.copy(z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getEnabled() {
            return this.enabled;
        }

        public final SetGroupStreamsByRepository copy(boolean enabled) {
            return new SetGroupStreamsByRepository(enabled);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof SetGroupStreamsByRepository) && this.enabled == ((SetGroupStreamsByRepository) other).enabled;
        }

        public final boolean getEnabled() {
            return this.enabled;
        }

        public int hashCode() {
            return this.enabled ? 1231 : 1237;
        }

        public String toString() {
            return "SetGroupStreamsByRepository(enabled=" + this.enabled + ")";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$SetPluginsEnabled;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent;", "enabled", "", "<init>", "(Z)V", "getEnabled", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SetPluginsEnabled implements PluginUiEvent {
        public static final int $stable = 0;
        private final boolean enabled;

        public SetPluginsEnabled(boolean z) {
            this.enabled = z;
        }

        public static /* synthetic */ SetPluginsEnabled copy$default(SetPluginsEnabled setPluginsEnabled, boolean z, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                z = setPluginsEnabled.enabled;
            }
            return setPluginsEnabled.copy(z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getEnabled() {
            return this.enabled;
        }

        public final SetPluginsEnabled copy(boolean enabled) {
            return new SetPluginsEnabled(enabled);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof SetPluginsEnabled) && this.enabled == ((SetPluginsEnabled) other).enabled;
        }

        public final boolean getEnabled() {
            return this.enabled;
        }

        public int hashCode() {
            return this.enabled ? 1231 : 1237;
        }

        public String toString() {
            return "SetPluginsEnabled(enabled=" + this.enabled + ")";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$StartQrMode;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class StartQrMode implements PluginUiEvent {
        public static final int $stable = 0;
        public static final StartQrMode INSTANCE = new StartQrMode();

        private StartQrMode() {
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof StartQrMode);
        }

        public int hashCode() {
            return -1723512384;
        }

        public String toString() {
            return "StartQrMode";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$StopQrMode;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class StopQrMode implements PluginUiEvent {
        public static final int $stable = 0;
        public static final StopQrMode INSTANCE = new StopQrMode();

        private StopQrMode() {
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof StopQrMode);
        }

        public int hashCode() {
            return -2095467668;
        }

        public String toString() {
            return "StopQrMode";
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$TestScraper;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent;", "scraperId", "", "<init>", "(Ljava/lang/String;)V", "getScraperId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class TestScraper implements PluginUiEvent {
        public static final int $stable = 0;
        private final String scraperId;

        public TestScraper(String str) {
            this.scraperId = str;
        }

        public static /* synthetic */ TestScraper copy$default(TestScraper testScraper, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = testScraper.scraperId;
            }
            return testScraper.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getScraperId() {
            return this.scraperId;
        }

        public final TestScraper copy(String scraperId) {
            return new TestScraper(scraperId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof TestScraper) && p.a(this.scraperId, ((TestScraper) other).scraperId);
        }

        public final String getScraperId() {
            return this.scraperId;
        }

        public int hashCode() {
            return this.scraperId.hashCode();
        }

        public String toString() {
            return a0.c.l("TestScraper(scraperId=", this.scraperId, ")");
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$ToggleAllScrapersForRepo;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent;", "repoId", "", "enabled", "", "<init>", "(Ljava/lang/String;Z)V", "getRepoId", "()Ljava/lang/String;", "getEnabled", "()Z", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ToggleAllScrapersForRepo implements PluginUiEvent {
        public static final int $stable = 0;
        private final boolean enabled;
        private final String repoId;

        public ToggleAllScrapersForRepo(String str, boolean z) {
            this.repoId = str;
            this.enabled = z;
        }

        public static /* synthetic */ ToggleAllScrapersForRepo copy$default(ToggleAllScrapersForRepo toggleAllScrapersForRepo, String str, boolean z, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = toggleAllScrapersForRepo.repoId;
            }
            if ((i10 & 2) != 0) {
                z = toggleAllScrapersForRepo.enabled;
            }
            return toggleAllScrapersForRepo.copy(str, z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getRepoId() {
            return this.repoId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getEnabled() {
            return this.enabled;
        }

        public final ToggleAllScrapersForRepo copy(String repoId, boolean enabled) {
            return new ToggleAllScrapersForRepo(repoId, enabled);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ToggleAllScrapersForRepo)) {
                return false;
            }
            ToggleAllScrapersForRepo toggleAllScrapersForRepo = (ToggleAllScrapersForRepo) other;
            return p.a(this.repoId, toggleAllScrapersForRepo.repoId) && this.enabled == toggleAllScrapersForRepo.enabled;
        }

        public final boolean getEnabled() {
            return this.enabled;
        }

        public final String getRepoId() {
            return this.repoId;
        }

        public int hashCode() {
            return (this.repoId.hashCode() * 31) + (this.enabled ? 1231 : 1237);
        }

        public String toString() {
            return "ToggleAllScrapersForRepo(repoId=" + this.repoId + ", enabled=" + this.enabled + ")";
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent$ToggleScraper;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent;", "scraperId", "", "enabled", "", "<init>", "(Ljava/lang/String;Z)V", "getScraperId", "()Ljava/lang/String;", "getEnabled", "()Z", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ToggleScraper implements PluginUiEvent {
        public static final int $stable = 0;
        private final boolean enabled;
        private final String scraperId;

        public ToggleScraper(String str, boolean z) {
            this.scraperId = str;
            this.enabled = z;
        }

        public static /* synthetic */ ToggleScraper copy$default(ToggleScraper toggleScraper, String str, boolean z, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = toggleScraper.scraperId;
            }
            if ((i10 & 2) != 0) {
                z = toggleScraper.enabled;
            }
            return toggleScraper.copy(str, z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getScraperId() {
            return this.scraperId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getEnabled() {
            return this.enabled;
        }

        public final ToggleScraper copy(String scraperId, boolean enabled) {
            return new ToggleScraper(scraperId, enabled);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ToggleScraper)) {
                return false;
            }
            ToggleScraper toggleScraper = (ToggleScraper) other;
            return p.a(this.scraperId, toggleScraper.scraperId) && this.enabled == toggleScraper.enabled;
        }

        public final boolean getEnabled() {
            return this.enabled;
        }

        public final String getScraperId() {
            return this.scraperId;
        }

        public int hashCode() {
            return (this.scraperId.hashCode() * 31) + (this.enabled ? 1231 : 1237);
        }

        public String toString() {
            return "ToggleScraper(scraperId=" + this.scraperId + ", enabled=" + this.enabled + ")";
        }
    }
}
