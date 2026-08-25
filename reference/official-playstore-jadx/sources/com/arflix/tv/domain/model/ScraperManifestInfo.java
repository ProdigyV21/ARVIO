package com.arflix.tv.domain.model;

import androidx.compose.foundation.c;
import androidx.compose.material3.d;
import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaTrack;
import com.squareup.moshi.m;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import y.a;

/* JADX INFO: loaded from: classes3.dex */
@m(generateAdapter = true)
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b2\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BË\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\u000f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00030\tHÆ\u0003J\t\u00100\u001a\u00020\u000bHÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u00102\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tHÆ\u0003J\u0011\u00103\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tHÆ\u0003J\u0011\u00104\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tHÆ\u0003J\u0011\u00105\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tHÆ\u0003J\u0011\u00106\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tHÆ\u0003J\u0010\u00107\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010'J\u0010\u00108\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010'JÚ\u0001\u00109\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t2\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t2\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t2\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u000bHÆ\u0001¢\u0006\u0002\u0010:J\u0014\u0010;\u001a\u00020\u000b2\b\u0010<\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010=\u001a\u00020>HÖ\u0081\u0004J\n\u0010?\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0017R\u0019\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001dR\u0019\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001dR\u0019\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001dR\u0019\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001dR\u0019\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001dR\u0015\u0010\u0012\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010(\u001a\u0004\b&\u0010'R\u0015\u0010\u0013\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010(\u001a\u0004\b)\u0010'¨\u0006@"}, d2 = {"Lcom/arflix/tv/domain/model/ScraperManifestInfo;", "", TtmlNode.ATTR_ID, "", ContentDisposition.Parameters.Name, MediaTrack.ROLE_DESCRIPTION, "version", ContentDisposition.Parameters.FileName, "supportedTypes", "", "enabled", "", "logo", "contentLanguage", "supportedPlatforms", "disabledPlatforms", "formats", "supportedFormats", "supportsExternalPlayer", "limited", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ZLjava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "getId", "()Ljava/lang/String;", "getName", "getDescription", "getVersion", "getFilename", "getSupportedTypes", "()Ljava/util/List;", "getEnabled", "()Z", "getLogo", "getContentLanguage", "getSupportedPlatforms", "getDisabledPlatforms", "getFormats", "getSupportedFormats", "getSupportsExternalPlayer", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getLimited", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ZLjava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/Boolean;Ljava/lang/Boolean;)Lcom/arflix/tv/domain/model/ScraperManifestInfo;", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ScraperManifestInfo {
    public static final int $stable = 0;
    private final List<String> contentLanguage;
    private final String description;
    private final List<String> disabledPlatforms;
    private final boolean enabled;
    private final String filename;
    private final List<String> formats;
    private final String id;
    private final Boolean limited;
    private final String logo;
    private final String name;
    private final List<String> supportedFormats;
    private final List<String> supportedPlatforms;
    private final List<String> supportedTypes;
    private final Boolean supportsExternalPlayer;
    private final String version;

    public ScraperManifestInfo(String str, String str2, String str3, String str4, String str5, List<String> list, boolean z, String str6, List<String> list2, List<String> list3, List<String> list4, List<String> list5, List<String> list6, Boolean bool, Boolean bool2) {
        this.id = str;
        this.name = str2;
        this.description = str3;
        this.version = str4;
        this.filename = str5;
        this.supportedTypes = list;
        this.enabled = z;
        this.logo = str6;
        this.contentLanguage = list2;
        this.supportedPlatforms = list3;
        this.disabledPlatforms = list4;
        this.formats = list5;
        this.supportedFormats = list6;
        this.supportsExternalPlayer = bool;
        this.limited = bool2;
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    public final List<String> component10() {
        return this.supportedPlatforms;
    }

    public final List<String> component11() {
        return this.disabledPlatforms;
    }

    public final List<String> component12() {
        return this.formats;
    }

    public final List<String> component13() {
        return this.supportedFormats;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final Boolean getSupportsExternalPlayer() {
        return this.supportsExternalPlayer;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final Boolean getLimited() {
        return this.limited;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getVersion() {
        return this.version;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getFilename() {
        return this.filename;
    }

    public final List<String> component6() {
        return this.supportedTypes;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getEnabled() {
        return this.enabled;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getLogo() {
        return this.logo;
    }

    public final List<String> component9() {
        return this.contentLanguage;
    }

    public final ScraperManifestInfo copy(String id, String name, String description, String version, String filename, List<String> supportedTypes, boolean enabled, String logo, List<String> contentLanguage, List<String> supportedPlatforms, List<String> disabledPlatforms, List<String> formats, List<String> supportedFormats, Boolean supportsExternalPlayer, Boolean limited) {
        return new ScraperManifestInfo(id, name, description, version, filename, supportedTypes, enabled, logo, contentLanguage, supportedPlatforms, disabledPlatforms, formats, supportedFormats, supportsExternalPlayer, limited);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ScraperManifestInfo)) {
            return false;
        }
        ScraperManifestInfo scraperManifestInfo = (ScraperManifestInfo) other;
        return p.a(this.id, scraperManifestInfo.id) && p.a(this.name, scraperManifestInfo.name) && p.a(this.description, scraperManifestInfo.description) && p.a(this.version, scraperManifestInfo.version) && p.a(this.filename, scraperManifestInfo.filename) && p.a(this.supportedTypes, scraperManifestInfo.supportedTypes) && this.enabled == scraperManifestInfo.enabled && p.a(this.logo, scraperManifestInfo.logo) && p.a(this.contentLanguage, scraperManifestInfo.contentLanguage) && p.a(this.supportedPlatforms, scraperManifestInfo.supportedPlatforms) && p.a(this.disabledPlatforms, scraperManifestInfo.disabledPlatforms) && p.a(this.formats, scraperManifestInfo.formats) && p.a(this.supportedFormats, scraperManifestInfo.supportedFormats) && p.a(this.supportsExternalPlayer, scraperManifestInfo.supportsExternalPlayer) && p.a(this.limited, scraperManifestInfo.limited);
    }

    public final List<String> getContentLanguage() {
        return this.contentLanguage;
    }

    public final String getDescription() {
        return this.description;
    }

    public final List<String> getDisabledPlatforms() {
        return this.disabledPlatforms;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final String getFilename() {
        return this.filename;
    }

    public final List<String> getFormats() {
        return this.formats;
    }

    public final String getId() {
        return this.id;
    }

    public final Boolean getLimited() {
        return this.limited;
    }

    public final String getLogo() {
        return this.logo;
    }

    public final String getName() {
        return this.name;
    }

    public final List<String> getSupportedFormats() {
        return this.supportedFormats;
    }

    public final List<String> getSupportedPlatforms() {
        return this.supportedPlatforms;
    }

    public final List<String> getSupportedTypes() {
        return this.supportedTypes;
    }

    public final Boolean getSupportsExternalPlayer() {
        return this.supportsExternalPlayer;
    }

    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        int iC = c.c(this.id.hashCode() * 31, 31, this.name);
        String str = this.description;
        int iE = (d.e(this.supportedTypes, c.c(c.c((iC + (str == null ? 0 : str.hashCode())) * 31, 31, this.version), 31, this.filename), 31) + (this.enabled ? 1231 : 1237)) * 31;
        String str2 = this.logo;
        int iHashCode = (iE + (str2 == null ? 0 : str2.hashCode())) * 31;
        List<String> list = this.contentLanguage;
        int iHashCode2 = (iHashCode + (list == null ? 0 : list.hashCode())) * 31;
        List<String> list2 = this.supportedPlatforms;
        int iHashCode3 = (iHashCode2 + (list2 == null ? 0 : list2.hashCode())) * 31;
        List<String> list3 = this.disabledPlatforms;
        int iHashCode4 = (iHashCode3 + (list3 == null ? 0 : list3.hashCode())) * 31;
        List<String> list4 = this.formats;
        int iHashCode5 = (iHashCode4 + (list4 == null ? 0 : list4.hashCode())) * 31;
        List<String> list5 = this.supportedFormats;
        int iHashCode6 = (iHashCode5 + (list5 == null ? 0 : list5.hashCode())) * 31;
        Boolean bool = this.supportsExternalPlayer;
        int iHashCode7 = (iHashCode6 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.limited;
        return iHashCode7 + (bool2 != null ? bool2.hashCode() : 0);
    }

    public String toString() {
        String str = this.id;
        String str2 = this.name;
        String str3 = this.description;
        String str4 = this.version;
        String str5 = this.filename;
        List<String> list = this.supportedTypes;
        boolean z = this.enabled;
        String str6 = this.logo;
        List<String> list2 = this.contentLanguage;
        List<String> list3 = this.supportedPlatforms;
        List<String> list4 = this.disabledPlatforms;
        List<String> list5 = this.formats;
        List<String> list6 = this.supportedFormats;
        Boolean bool = this.supportsExternalPlayer;
        Boolean bool2 = this.limited;
        StringBuilder sbR = a2.r("ScraperManifestInfo(id=", str, ", name=", str2, ", description=");
        a.i(sbR, str3, ", version=", str4, ", filename=");
        sbR.append(str5);
        sbR.append(", supportedTypes=");
        sbR.append(list);
        sbR.append(", enabled=");
        a2.B(sbR, z, ", logo=", str6, ", contentLanguage=");
        a2.A(sbR, list2, ", supportedPlatforms=", list3, ", disabledPlatforms=");
        a2.A(sbR, list4, ", formats=", list5, ", supportedFormats=");
        sbR.append(list6);
        sbR.append(", supportsExternalPlayer=");
        sbR.append(bool);
        sbR.append(", limited=");
        sbR.append(bool2);
        sbR.append(")");
        return sbR.toString();
    }

    public /* synthetic */ ScraperManifestInfo(String str, String str2, String str3, String str4, String str5, List list, boolean z, String str6, List list2, List list3, List list4, List list5, List list6, Boolean bool, Boolean bool2, int i10, h hVar) {
        this(str, str2, (i10 & 4) != 0 ? null : str3, str4, str5, (i10 & 32) != 0 ? t7.a.E("movie", "tv") : list, (i10 & 64) != 0 ? true : z, (i10 & 128) != 0 ? null : str6, (i10 & 256) != 0 ? null : list2, (i10 & 512) != 0 ? null : list3, (i10 & 1024) != 0 ? null : list4, (i10 & 2048) != 0 ? null : list5, (i10 & 4096) != 0 ? null : list6, (i10 & 8192) != 0 ? null : bool, (i10 & 16384) != 0 ? null : bool2);
    }
}
