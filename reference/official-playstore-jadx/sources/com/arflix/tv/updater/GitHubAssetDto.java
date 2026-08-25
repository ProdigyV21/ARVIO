package com.arflix.tv.updater;

import androidx.compose.foundation.c;
import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\rJ.\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u0013J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\r¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/updater/GitHubAssetDto;", "", ContentDisposition.Parameters.Name, "", "browserDownloadUrl", ContentDisposition.Parameters.Size, "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)V", "getName", "()Ljava/lang/String;", "getBrowserDownloadUrl", "getSize", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)Lcom/arflix/tv/updater/GitHubAssetDto;", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class GitHubAssetDto {
    public static final int $stable = 0;

    @SerializedName("browser_download_url")
    private final String browserDownloadUrl;

    @SerializedName(ContentDisposition.Parameters.Name)
    private final String name;

    @SerializedName(ContentDisposition.Parameters.Size)
    private final Long size;

    public GitHubAssetDto(String str, String str2, Long l10) {
        this.name = str;
        this.browserDownloadUrl = str2;
        this.size = l10;
    }

    public static /* synthetic */ GitHubAssetDto copy$default(GitHubAssetDto gitHubAssetDto, String str, String str2, Long l10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = gitHubAssetDto.name;
        }
        if ((i10 & 2) != 0) {
            str2 = gitHubAssetDto.browserDownloadUrl;
        }
        if ((i10 & 4) != 0) {
            l10 = gitHubAssetDto.size;
        }
        return gitHubAssetDto.copy(str, str2, l10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getBrowserDownloadUrl() {
        return this.browserDownloadUrl;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Long getSize() {
        return this.size;
    }

    public final GitHubAssetDto copy(String name, String browserDownloadUrl, Long size) {
        return new GitHubAssetDto(name, browserDownloadUrl, size);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GitHubAssetDto)) {
            return false;
        }
        GitHubAssetDto gitHubAssetDto = (GitHubAssetDto) other;
        return p.a(this.name, gitHubAssetDto.name) && p.a(this.browserDownloadUrl, gitHubAssetDto.browserDownloadUrl) && p.a(this.size, gitHubAssetDto.size);
    }

    public final String getBrowserDownloadUrl() {
        return this.browserDownloadUrl;
    }

    public final String getName() {
        return this.name;
    }

    public final Long getSize() {
        return this.size;
    }

    public int hashCode() {
        int iC = c.c(this.name.hashCode() * 31, 31, this.browserDownloadUrl);
        Long l10 = this.size;
        return iC + (l10 == null ? 0 : l10.hashCode());
    }

    public String toString() {
        String str = this.name;
        String str2 = this.browserDownloadUrl;
        Long l10 = this.size;
        StringBuilder sbR = a2.r("GitHubAssetDto(name=", str, ", browserDownloadUrl=", str2, ", size=");
        sbR.append(l10);
        sbR.append(")");
        return sbR.toString();
    }
}
