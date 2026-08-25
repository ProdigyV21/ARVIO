package com.arflix.tv.ui.screens.plugin;

import android.graphics.Bitmap;
import androidx.fragment.app.a2;
import com.arflix.tv.core.plugin.TestDiagnostics;
import com.arflix.tv.domain.model.LocalScraperResult;
import com.arflix.tv.domain.model.PluginRepository;
import com.arflix.tv.domain.model.ScraperInfo;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b+\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BÕ\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0012\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c¢\u0006\u0004\b\u001d\u0010\u001eJ\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J\u000f\u00105\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J\u000f\u00106\u001a\b\u0012\u0004\u0012\u00020\t0\u0006HÆ\u0003J\t\u00107\u001a\u00020\u0003HÆ\u0003J\t\u00108\u001a\u00020\u0003HÆ\u0003J\t\u00109\u001a\u00020\u0003HÆ\u0003J\u0011\u0010:\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0006HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\t\u0010?\u001a\u00020\u0003HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0017HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u001aHÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u001cHÆ\u0003J×\u0001\u0010D\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00062\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÆ\u0001J\u0014\u0010E\u001a\u00020\u00032\b\u0010F\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010G\u001a\u00020HHÖ\u0081\u0004J\n\u0010I\u001a\u00020\u0012HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010 R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006¢\u0006\b\n\u0000\u001a\u0004\b$\u0010#R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010 R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010 R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010 R\u0019\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b%\u0010#R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b*\u0010)R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b+\u0010)R\u0011\u0010\u0015\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010 R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b.\u0010)R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u001a¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u001c¢\u0006\b\n\u0000\u001a\u0004\b1\u00102¨\u0006J"}, d2 = {"Lcom/arflix/tv/ui/screens/plugin/PluginUiState;", "", "pluginsEnabled", "", "groupStreamsByRepository", "repositories", "", "Lcom/arflix/tv/domain/model/PluginRepository;", "scrapers", "Lcom/arflix/tv/domain/model/ScraperInfo;", "isLoading", "isAddingRepo", "isTesting", "testResults", "Lcom/arflix/tv/domain/model/LocalScraperResult;", "testDiagnostics", "Lcom/arflix/tv/core/plugin/TestDiagnostics;", "testScraperId", "", "errorMessage", "successMessage", "isQrModeActive", "qrCodeBitmap", "Landroid/graphics/Bitmap;", "serverUrl", "pendingRepoChange", "Lcom/arflix/tv/ui/screens/plugin/PendingRepoChangeInfo;", "pendingScraperEnable", "Lcom/arflix/tv/ui/screens/plugin/PendingScraperEnableInfo;", "<init>", "(ZZLjava/util/List;Ljava/util/List;ZZZLjava/util/List;Lcom/arflix/tv/core/plugin/TestDiagnostics;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLandroid/graphics/Bitmap;Ljava/lang/String;Lcom/arflix/tv/ui/screens/plugin/PendingRepoChangeInfo;Lcom/arflix/tv/ui/screens/plugin/PendingScraperEnableInfo;)V", "getPluginsEnabled", "()Z", "getGroupStreamsByRepository", "getRepositories", "()Ljava/util/List;", "getScrapers", "getTestResults", "getTestDiagnostics", "()Lcom/arflix/tv/core/plugin/TestDiagnostics;", "getTestScraperId", "()Ljava/lang/String;", "getErrorMessage", "getSuccessMessage", "getQrCodeBitmap", "()Landroid/graphics/Bitmap;", "getServerUrl", "getPendingRepoChange", "()Lcom/arflix/tv/ui/screens/plugin/PendingRepoChangeInfo;", "getPendingScraperEnable", "()Lcom/arflix/tv/ui/screens/plugin/PendingScraperEnableInfo;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class PluginUiState {
    public static final int $stable = 8;
    private final String errorMessage;
    private final boolean groupStreamsByRepository;
    private final boolean isAddingRepo;
    private final boolean isLoading;
    private final boolean isQrModeActive;
    private final boolean isTesting;
    private final PendingRepoChangeInfo pendingRepoChange;
    private final PendingScraperEnableInfo pendingScraperEnable;
    private final boolean pluginsEnabled;
    private final Bitmap qrCodeBitmap;
    private final List<PluginRepository> repositories;
    private final List<ScraperInfo> scrapers;
    private final String serverUrl;
    private final String successMessage;
    private final TestDiagnostics testDiagnostics;
    private final List<LocalScraperResult> testResults;
    private final String testScraperId;

    public PluginUiState() {
        this(false, false, null, null, false, false, false, null, null, null, null, null, false, null, null, null, null, 131071, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PluginUiState copy$default(PluginUiState pluginUiState, boolean z, boolean z5, List list, List list2, boolean z10, boolean z11, boolean z12, List list3, TestDiagnostics testDiagnostics, String str, String str2, String str3, boolean z13, Bitmap bitmap, String str4, PendingRepoChangeInfo pendingRepoChangeInfo, PendingScraperEnableInfo pendingScraperEnableInfo, int i10, Object obj) {
        PendingScraperEnableInfo pendingScraperEnableInfo2;
        PendingRepoChangeInfo pendingRepoChangeInfo2;
        boolean z14;
        PluginUiState pluginUiState2;
        String str5;
        boolean z15;
        List list4;
        List list5;
        boolean z16;
        boolean z17;
        boolean z18;
        List list6;
        TestDiagnostics testDiagnostics2;
        String str6;
        String str7;
        String str8;
        boolean z19;
        Bitmap bitmap2;
        boolean z20 = (i10 & 1) != 0 ? pluginUiState.pluginsEnabled : z;
        boolean z21 = (i10 & 2) != 0 ? pluginUiState.groupStreamsByRepository : z5;
        List list7 = (i10 & 4) != 0 ? pluginUiState.repositories : list;
        List list8 = (i10 & 8) != 0 ? pluginUiState.scrapers : list2;
        boolean z22 = (i10 & 16) != 0 ? pluginUiState.isLoading : z10;
        boolean z23 = (i10 & 32) != 0 ? pluginUiState.isAddingRepo : z11;
        boolean z24 = (i10 & 64) != 0 ? pluginUiState.isTesting : z12;
        List list9 = (i10 & 128) != 0 ? pluginUiState.testResults : list3;
        TestDiagnostics testDiagnostics3 = (i10 & 256) != 0 ? pluginUiState.testDiagnostics : testDiagnostics;
        String str9 = (i10 & 512) != 0 ? pluginUiState.testScraperId : str;
        String str10 = (i10 & 1024) != 0 ? pluginUiState.errorMessage : str2;
        String str11 = (i10 & 2048) != 0 ? pluginUiState.successMessage : str3;
        boolean z25 = (i10 & 4096) != 0 ? pluginUiState.isQrModeActive : z13;
        Bitmap bitmap3 = (i10 & 8192) != 0 ? pluginUiState.qrCodeBitmap : bitmap;
        boolean z26 = z20;
        String str12 = (i10 & 16384) != 0 ? pluginUiState.serverUrl : str4;
        PendingRepoChangeInfo pendingRepoChangeInfo3 = (i10 & 32768) != 0 ? pluginUiState.pendingRepoChange : pendingRepoChangeInfo;
        if ((i10 & 65536) != 0) {
            pendingRepoChangeInfo2 = pendingRepoChangeInfo3;
            pendingScraperEnableInfo2 = pluginUiState.pendingScraperEnable;
            str5 = str12;
            z15 = z21;
            list4 = list7;
            list5 = list8;
            z16 = z22;
            z17 = z23;
            z18 = z24;
            list6 = list9;
            testDiagnostics2 = testDiagnostics3;
            str6 = str9;
            str7 = str10;
            str8 = str11;
            z19 = z25;
            bitmap2 = bitmap3;
            z14 = z26;
            pluginUiState2 = pluginUiState;
        } else {
            pendingScraperEnableInfo2 = pendingScraperEnableInfo;
            pendingRepoChangeInfo2 = pendingRepoChangeInfo3;
            z14 = z26;
            pluginUiState2 = pluginUiState;
            str5 = str12;
            z15 = z21;
            list4 = list7;
            list5 = list8;
            z16 = z22;
            z17 = z23;
            z18 = z24;
            list6 = list9;
            testDiagnostics2 = testDiagnostics3;
            str6 = str9;
            str7 = str10;
            str8 = str11;
            z19 = z25;
            bitmap2 = bitmap3;
        }
        return pluginUiState2.copy(z14, z15, list4, list5, z16, z17, z18, list6, testDiagnostics2, str6, str7, str8, z19, bitmap2, str5, pendingRepoChangeInfo2, pendingScraperEnableInfo2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getPluginsEnabled() {
        return this.pluginsEnabled;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getTestScraperId() {
        return this.testScraperId;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getErrorMessage() {
        return this.errorMessage;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getSuccessMessage() {
        return this.successMessage;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final boolean getIsQrModeActive() {
        return this.isQrModeActive;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final Bitmap getQrCodeBitmap() {
        return this.qrCodeBitmap;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getServerUrl() {
        return this.serverUrl;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final PendingRepoChangeInfo getPendingRepoChange() {
        return this.pendingRepoChange;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final PendingScraperEnableInfo getPendingScraperEnable() {
        return this.pendingScraperEnable;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getGroupStreamsByRepository() {
        return this.groupStreamsByRepository;
    }

    public final List<PluginRepository> component3() {
        return this.repositories;
    }

    public final List<ScraperInfo> component4() {
        return this.scrapers;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getIsLoading() {
        return this.isLoading;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getIsAddingRepo() {
        return this.isAddingRepo;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getIsTesting() {
        return this.isTesting;
    }

    public final List<LocalScraperResult> component8() {
        return this.testResults;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final TestDiagnostics getTestDiagnostics() {
        return this.testDiagnostics;
    }

    public final PluginUiState copy(boolean pluginsEnabled, boolean groupStreamsByRepository, List<PluginRepository> repositories, List<ScraperInfo> scrapers, boolean isLoading, boolean isAddingRepo, boolean isTesting, List<LocalScraperResult> testResults, TestDiagnostics testDiagnostics, String testScraperId, String errorMessage, String successMessage, boolean isQrModeActive, Bitmap qrCodeBitmap, String serverUrl, PendingRepoChangeInfo pendingRepoChange, PendingScraperEnableInfo pendingScraperEnable) {
        return new PluginUiState(pluginsEnabled, groupStreamsByRepository, repositories, scrapers, isLoading, isAddingRepo, isTesting, testResults, testDiagnostics, testScraperId, errorMessage, successMessage, isQrModeActive, qrCodeBitmap, serverUrl, pendingRepoChange, pendingScraperEnable);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PluginUiState)) {
            return false;
        }
        PluginUiState pluginUiState = (PluginUiState) other;
        return this.pluginsEnabled == pluginUiState.pluginsEnabled && this.groupStreamsByRepository == pluginUiState.groupStreamsByRepository && p.a(this.repositories, pluginUiState.repositories) && p.a(this.scrapers, pluginUiState.scrapers) && this.isLoading == pluginUiState.isLoading && this.isAddingRepo == pluginUiState.isAddingRepo && this.isTesting == pluginUiState.isTesting && p.a(this.testResults, pluginUiState.testResults) && p.a(this.testDiagnostics, pluginUiState.testDiagnostics) && p.a(this.testScraperId, pluginUiState.testScraperId) && p.a(this.errorMessage, pluginUiState.errorMessage) && p.a(this.successMessage, pluginUiState.successMessage) && this.isQrModeActive == pluginUiState.isQrModeActive && p.a(this.qrCodeBitmap, pluginUiState.qrCodeBitmap) && p.a(this.serverUrl, pluginUiState.serverUrl) && p.a(this.pendingRepoChange, pluginUiState.pendingRepoChange) && p.a(this.pendingScraperEnable, pluginUiState.pendingScraperEnable);
    }

    public final String getErrorMessage() {
        return this.errorMessage;
    }

    public final boolean getGroupStreamsByRepository() {
        return this.groupStreamsByRepository;
    }

    public final PendingRepoChangeInfo getPendingRepoChange() {
        return this.pendingRepoChange;
    }

    public final PendingScraperEnableInfo getPendingScraperEnable() {
        return this.pendingScraperEnable;
    }

    public final boolean getPluginsEnabled() {
        return this.pluginsEnabled;
    }

    public final Bitmap getQrCodeBitmap() {
        return this.qrCodeBitmap;
    }

    public final List<PluginRepository> getRepositories() {
        return this.repositories;
    }

    public final List<ScraperInfo> getScrapers() {
        return this.scrapers;
    }

    public final String getServerUrl() {
        return this.serverUrl;
    }

    public final String getSuccessMessage() {
        return this.successMessage;
    }

    public final TestDiagnostics getTestDiagnostics() {
        return this.testDiagnostics;
    }

    public final List<LocalScraperResult> getTestResults() {
        return this.testResults;
    }

    public final String getTestScraperId() {
        return this.testScraperId;
    }

    public int hashCode() {
        int iE = (((((androidx.compose.material3.d.e(this.scrapers, androidx.compose.material3.d.e(this.repositories, (((this.pluginsEnabled ? 1231 : 1237) * 31) + (this.groupStreamsByRepository ? 1231 : 1237)) * 31, 31), 31) + (this.isLoading ? 1231 : 1237)) * 31) + (this.isAddingRepo ? 1231 : 1237)) * 31) + (this.isTesting ? 1231 : 1237)) * 31;
        List<LocalScraperResult> list = this.testResults;
        int iHashCode = (iE + (list == null ? 0 : list.hashCode())) * 31;
        TestDiagnostics testDiagnostics = this.testDiagnostics;
        int iHashCode2 = (iHashCode + (testDiagnostics == null ? 0 : testDiagnostics.hashCode())) * 31;
        String str = this.testScraperId;
        int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.errorMessage;
        int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.successMessage;
        int iHashCode5 = (((iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31) + (this.isQrModeActive ? 1231 : 1237)) * 31;
        Bitmap bitmap = this.qrCodeBitmap;
        int iHashCode6 = (iHashCode5 + (bitmap == null ? 0 : bitmap.hashCode())) * 31;
        String str4 = this.serverUrl;
        int iHashCode7 = (iHashCode6 + (str4 == null ? 0 : str4.hashCode())) * 31;
        PendingRepoChangeInfo pendingRepoChangeInfo = this.pendingRepoChange;
        int iHashCode8 = (iHashCode7 + (pendingRepoChangeInfo == null ? 0 : pendingRepoChangeInfo.hashCode())) * 31;
        PendingScraperEnableInfo pendingScraperEnableInfo = this.pendingScraperEnable;
        return iHashCode8 + (pendingScraperEnableInfo != null ? pendingScraperEnableInfo.hashCode() : 0);
    }

    public final boolean isAddingRepo() {
        return this.isAddingRepo;
    }

    public final boolean isLoading() {
        return this.isLoading;
    }

    public final boolean isQrModeActive() {
        return this.isQrModeActive;
    }

    public final boolean isTesting() {
        return this.isTesting;
    }

    public String toString() {
        boolean z = this.pluginsEnabled;
        boolean z5 = this.groupStreamsByRepository;
        List<PluginRepository> list = this.repositories;
        List<ScraperInfo> list2 = this.scrapers;
        boolean z10 = this.isLoading;
        boolean z11 = this.isAddingRepo;
        boolean z12 = this.isTesting;
        List<LocalScraperResult> list3 = this.testResults;
        TestDiagnostics testDiagnostics = this.testDiagnostics;
        String str = this.testScraperId;
        String str2 = this.errorMessage;
        String str3 = this.successMessage;
        boolean z13 = this.isQrModeActive;
        Bitmap bitmap = this.qrCodeBitmap;
        String str4 = this.serverUrl;
        PendingRepoChangeInfo pendingRepoChangeInfo = this.pendingRepoChange;
        PendingScraperEnableInfo pendingScraperEnableInfo = this.pendingScraperEnable;
        StringBuilder sb2 = new StringBuilder("PluginUiState(pluginsEnabled=");
        sb2.append(z);
        sb2.append(", groupStreamsByRepository=");
        sb2.append(z5);
        sb2.append(", repositories=");
        a2.A(sb2, list, ", scrapers=", list2, ", isLoading=");
        v.f.h(sb2, z10, ", isAddingRepo=", z11, ", isTesting=");
        sb2.append(z12);
        sb2.append(", testResults=");
        sb2.append(list3);
        sb2.append(", testDiagnostics=");
        sb2.append(testDiagnostics);
        sb2.append(", testScraperId=");
        sb2.append(str);
        sb2.append(", errorMessage=");
        y.a.i(sb2, str2, ", successMessage=", str3, ", isQrModeActive=");
        sb2.append(z13);
        sb2.append(", qrCodeBitmap=");
        sb2.append(bitmap);
        sb2.append(", serverUrl=");
        sb2.append(str4);
        sb2.append(", pendingRepoChange=");
        sb2.append(pendingRepoChangeInfo);
        sb2.append(", pendingScraperEnable=");
        sb2.append(pendingScraperEnableInfo);
        sb2.append(")");
        return sb2.toString();
    }

    public PluginUiState(boolean z, boolean z5, List<PluginRepository> list, List<ScraperInfo> list2, boolean z10, boolean z11, boolean z12, List<LocalScraperResult> list3, TestDiagnostics testDiagnostics, String str, String str2, String str3, boolean z13, Bitmap bitmap, String str4, PendingRepoChangeInfo pendingRepoChangeInfo, PendingScraperEnableInfo pendingScraperEnableInfo) {
        this.pluginsEnabled = z;
        this.groupStreamsByRepository = z5;
        this.repositories = list;
        this.scrapers = list2;
        this.isLoading = z10;
        this.isAddingRepo = z11;
        this.isTesting = z12;
        this.testResults = list3;
        this.testDiagnostics = testDiagnostics;
        this.testScraperId = str;
        this.errorMessage = str2;
        this.successMessage = str3;
        this.isQrModeActive = z13;
        this.qrCodeBitmap = bitmap;
        this.serverUrl = str4;
        this.pendingRepoChange = pendingRepoChangeInfo;
        this.pendingScraperEnable = pendingScraperEnableInfo;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ PluginUiState(boolean z, boolean z5, List list, List list2, boolean z10, boolean z11, boolean z12, List list3, TestDiagnostics testDiagnostics, String str, String str2, String str3, boolean z13, Bitmap bitmap, String str4, PendingRepoChangeInfo pendingRepoChangeInfo, PendingScraperEnableInfo pendingScraperEnableInfo, int i10, kotlin.jvm.internal.h hVar) {
        boolean z14 = (i10 & 1) != 0 ? true : z;
        boolean z15 = (i10 & 2) != 0 ? false : z5;
        int i11 = i10 & 4;
        List list4 = z.f19728i;
        this(z14, z15, i11 != 0 ? list4 : list, (i10 & 8) == 0 ? list2 : list4, (i10 & 16) != 0 ? false : z10, (i10 & 32) != 0 ? false : z11, (i10 & 64) != 0 ? false : z12, (i10 & 128) != 0 ? null : list3, (i10 & 256) != 0 ? null : testDiagnostics, (i10 & 512) != 0 ? null : str, (i10 & 1024) != 0 ? null : str2, (i10 & 2048) != 0 ? null : str3, (i10 & 4096) == 0 ? z13 : false, (i10 & 8192) != 0 ? null : bitmap, (i10 & 16384) != 0 ? null : str4, (i10 & 32768) != 0 ? null : pendingRepoChangeInfo, (i10 & 65536) != 0 ? null : pendingScraperEnableInfo);
    }
}
