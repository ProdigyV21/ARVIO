package com.arflix.tv.updater;

import a0.c;
import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import v.f;
import y.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001BM\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\bHÆ\u0003J\t\u0010\u001e\u001a\u00020\bHÆ\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0003J]\u0010 \u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0001J\u0014\u0010!\u001a\u00020\b2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010#\u001a\u00020$HÖ\u0081\u0004J\n\u0010%\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\t\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006&"}, d2 = {"Lcom/arflix/tv/updater/GitHubReleaseDto;", "", "tagName", "", ContentDisposition.Parameters.Name, TtmlNode.TAG_BODY, "htmlUrl", "draft", "", "prerelease", "assets", "", "Lcom/arflix/tv/updater/GitHubAssetDto;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLjava/util/List;)V", "getTagName", "()Ljava/lang/String;", "getName", "getBody", "getHtmlUrl", "getDraft", "()Z", "getPrerelease", "getAssets", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class GitHubReleaseDto {

    @SerializedName("assets")
    private final List<GitHubAssetDto> assets;

    @SerializedName(TtmlNode.TAG_BODY)
    private final String body;

    @SerializedName("draft")
    private final boolean draft;

    @SerializedName("html_url")
    private final String htmlUrl;

    @SerializedName(ContentDisposition.Parameters.Name)
    private final String name;

    @SerializedName("prerelease")
    private final boolean prerelease;

    @SerializedName("tag_name")
    private final String tagName;

    public GitHubReleaseDto(String str, String str2, String str3, String str4, boolean z, boolean z5, List<GitHubAssetDto> list) {
        this.tagName = str;
        this.name = str2;
        this.body = str3;
        this.htmlUrl = str4;
        this.draft = z;
        this.prerelease = z5;
        this.assets = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ GitHubReleaseDto copy$default(GitHubReleaseDto gitHubReleaseDto, String str, String str2, String str3, String str4, boolean z, boolean z5, List list, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = gitHubReleaseDto.tagName;
        }
        if ((i10 & 2) != 0) {
            str2 = gitHubReleaseDto.name;
        }
        if ((i10 & 4) != 0) {
            str3 = gitHubReleaseDto.body;
        }
        if ((i10 & 8) != 0) {
            str4 = gitHubReleaseDto.htmlUrl;
        }
        if ((i10 & 16) != 0) {
            z = gitHubReleaseDto.draft;
        }
        if ((i10 & 32) != 0) {
            z5 = gitHubReleaseDto.prerelease;
        }
        if ((i10 & 64) != 0) {
            list = gitHubReleaseDto.assets;
        }
        boolean z10 = z5;
        List list2 = list;
        boolean z11 = z;
        String str5 = str3;
        return gitHubReleaseDto.copy(str, str2, str5, str4, z11, z10, list2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getTagName() {
        return this.tagName;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getBody() {
        return this.body;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getHtmlUrl() {
        return this.htmlUrl;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getDraft() {
        return this.draft;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getPrerelease() {
        return this.prerelease;
    }

    public final List<GitHubAssetDto> component7() {
        return this.assets;
    }

    public final GitHubReleaseDto copy(String tagName, String name, String body, String htmlUrl, boolean draft, boolean prerelease, List<GitHubAssetDto> assets) {
        return new GitHubReleaseDto(tagName, name, body, htmlUrl, draft, prerelease, assets);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GitHubReleaseDto)) {
            return false;
        }
        GitHubReleaseDto gitHubReleaseDto = (GitHubReleaseDto) other;
        return p.a(this.tagName, gitHubReleaseDto.tagName) && p.a(this.name, gitHubReleaseDto.name) && p.a(this.body, gitHubReleaseDto.body) && p.a(this.htmlUrl, gitHubReleaseDto.htmlUrl) && this.draft == gitHubReleaseDto.draft && this.prerelease == gitHubReleaseDto.prerelease && p.a(this.assets, gitHubReleaseDto.assets);
    }

    public final List<GitHubAssetDto> getAssets() {
        return this.assets;
    }

    public final String getBody() {
        return this.body;
    }

    public final boolean getDraft() {
        return this.draft;
    }

    public final String getHtmlUrl() {
        return this.htmlUrl;
    }

    public final String getName() {
        return this.name;
    }

    public final boolean getPrerelease() {
        return this.prerelease;
    }

    public final String getTagName() {
        return this.tagName;
    }

    public int hashCode() {
        String str = this.tagName;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.name;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.body;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.htmlUrl;
        return this.assets.hashCode() + ((((((iHashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31) + (this.draft ? 1231 : 1237)) * 31) + (this.prerelease ? 1231 : 1237)) * 31);
    }

    public String toString() {
        String str = this.tagName;
        String str2 = this.name;
        String str3 = this.body;
        String str4 = this.htmlUrl;
        boolean z = this.draft;
        boolean z5 = this.prerelease;
        List<GitHubAssetDto> list = this.assets;
        StringBuilder sbR = a2.r("GitHubReleaseDto(tagName=", str, ", name=", str2, ", body=");
        a.i(sbR, str3, ", htmlUrl=", str4, ", draft=");
        f.h(sbR, z, ", prerelease=", z5, ", assets=");
        return c.q(sbR, list, ")");
    }
}
