package com.arflix.tv.domain.model;

import androidx.compose.foundation.c;
import androidx.compose.material3.d;
import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.s;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import v.f;
import y.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0087\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\t\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\u0014J\u000e\u0010&\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\u000f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00030\tHÆ\u0003J\t\u0010-\u001a\u00020\u000bHÆ\u0003J\t\u0010.\u001a\u00020\u000bHÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00030\tHÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\u0011\u00102\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tHÆ\u0003J\t\u00103\u001a\u00020\u0012HÆ\u0003J¡\u0001\u00104\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\b\b\u0002\u0010\u000f\u001a\u00020\u00032\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t2\b\b\u0002\u0010\u0011\u001a\u00020\u0012HÆ\u0001J\u0014\u00105\u001a\u00020\u000b2\b\u00106\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00107\u001a\u000208HÖ\u0081\u0004J\n\u00109\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0016R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001cR\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0016R\u0019\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001cR\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%¨\u0006:"}, d2 = {"Lcom/arflix/tv/domain/model/ScraperInfo;", "", TtmlNode.ATTR_ID, "", ContentDisposition.Parameters.Name, MediaTrack.ROLE_DESCRIPTION, "version", ContentDisposition.Parameters.FileName, "supportedTypes", "", "enabled", "", "manifestEnabled", "logo", "contentLanguage", "repositoryId", "formats", LinkHeader.Parameters.Type, "Lcom/arflix/tv/domain/model/RepositoryType;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ZZLjava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Lcom/arflix/tv/domain/model/RepositoryType;)V", "getId", "()Ljava/lang/String;", "getName", "getDescription", "getVersion", "getFilename", "getSupportedTypes", "()Ljava/util/List;", "getEnabled", "()Z", "getManifestEnabled", "getLogo", "getContentLanguage", "getRepositoryId", "getFormats", "getType", "()Lcom/arflix/tv/domain/model/RepositoryType;", "supportsType", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ScraperInfo {
    public static final int $stable = 0;
    private final List<String> contentLanguage;
    private final String description;
    private final boolean enabled;
    private final String filename;
    private final List<String> formats;
    private final String id;
    private final String logo;
    private final boolean manifestEnabled;
    private final String name;
    private final String repositoryId;
    private final List<String> supportedTypes;
    private final RepositoryType type;
    private final String version;

    public ScraperInfo(String str, String str2, String str3, String str4, String str5, List<String> list, boolean z, boolean z5, String str6, List<String> list2, String str7, List<String> list3, RepositoryType repositoryType) {
        this.id = str;
        this.name = str2;
        this.description = str3;
        this.version = str4;
        this.filename = str5;
        this.supportedTypes = list;
        this.enabled = z;
        this.manifestEnabled = z5;
        this.logo = str6;
        this.contentLanguage = list2;
        this.repositoryId = str7;
        this.formats = list3;
        this.type = repositoryType;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ScraperInfo copy$default(ScraperInfo scraperInfo, String str, String str2, String str3, String str4, String str5, List list, boolean z, boolean z5, String str6, List list2, String str7, List list3, RepositoryType repositoryType, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = scraperInfo.id;
        }
        return scraperInfo.copy(str, (i10 & 2) != 0 ? scraperInfo.name : str2, (i10 & 4) != 0 ? scraperInfo.description : str3, (i10 & 8) != 0 ? scraperInfo.version : str4, (i10 & 16) != 0 ? scraperInfo.filename : str5, (i10 & 32) != 0 ? scraperInfo.supportedTypes : list, (i10 & 64) != 0 ? scraperInfo.enabled : z, (i10 & 128) != 0 ? scraperInfo.manifestEnabled : z5, (i10 & 256) != 0 ? scraperInfo.logo : str6, (i10 & 512) != 0 ? scraperInfo.contentLanguage : list2, (i10 & 1024) != 0 ? scraperInfo.repositoryId : str7, (i10 & 2048) != 0 ? scraperInfo.formats : list3, (i10 & 4096) != 0 ? scraperInfo.type : repositoryType);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x003c, code lost:
    
        if (r0.equals("shows") == false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0045, code lost:
    
        if (r0.equals("other") == false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004e, code lost:
    
        if (r0.equals("anime") != false) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0057, code lost:
    
        if (r0.equals("show") == false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0069, code lost:
    
        if (r0.equals("ova") == false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0070, code lost:
    
        if (r0.equals("tv") == false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0085, code lost:
    
        if (r0.equals("series") == false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0094, code lost:
    
        return "tv";
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0020, code lost:
    
        if (r0.equals("donghua") == false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x002a, code lost:
    
        if (r0.equals("cartoon") == false) goto L42;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final java.lang.String supportsType$normalize(java.lang.String r4) {
        /*
            java.lang.CharSequence r0 = kotlin.text.o.L0(r4)
            java.lang.String r0 = r0.toString()
            java.util.Locale r1 = java.util.Locale.ROOT
            java.lang.String r0 = r0.toLowerCase(r1)
            int r2 = r0.hashCode()
            java.lang.String r3 = "tv"
            switch(r2) {
                case -905838985: goto L7f;
                case -196215899: goto L73;
                case 3714: goto L6c;
                case 110426: goto L63;
                case 3143044: goto L5a;
                case 3529469: goto L51;
                case 92962932: goto L48;
                case 106069776: goto L3f;
                case 109413654: goto L36;
                case 422307644: goto L2d;
                case 554426222: goto L24;
                case 1838650096: goto L1a;
                default: goto L18;
            }
        L18:
            goto L87
        L1a:
            java.lang.String r2 = "donghua"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L94
            goto L87
        L24:
            java.lang.String r2 = "cartoon"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L94
            goto L87
        L2d:
            java.lang.String r2 = "animemovie"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L7c
            goto L87
        L36:
            java.lang.String r2 = "shows"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L94
            goto L87
        L3f:
            java.lang.String r2 = "other"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L94
            goto L87
        L48:
            java.lang.String r2 = "anime"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L87
            goto L94
        L51:
            java.lang.String r2 = "show"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L94
            goto L87
        L5a:
            java.lang.String r2 = "film"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L7c
            goto L87
        L63:
            java.lang.String r2 = "ova"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L94
            goto L87
        L6c:
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L94
            goto L87
        L73:
            java.lang.String r2 = "anime_movie"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L7c
            goto L87
        L7c:
            java.lang.String r4 = "movie"
            return r4
        L7f:
            java.lang.String r2 = "series"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L94
        L87:
            java.lang.CharSequence r4 = kotlin.text.o.L0(r4)
            java.lang.String r4 = r4.toString()
            java.lang.String r4 = r4.toLowerCase(r1)
            return r4
        L94:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.domain.model.ScraperInfo.supportsType$normalize(java.lang.String):java.lang.String");
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    public final List<String> component10() {
        return this.contentLanguage;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getRepositoryId() {
        return this.repositoryId;
    }

    public final List<String> component12() {
        return this.formats;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final RepositoryType getType() {
        return this.type;
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
    public final boolean getManifestEnabled() {
        return this.manifestEnabled;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getLogo() {
        return this.logo;
    }

    public final ScraperInfo copy(String id, String name, String description, String version, String filename, List<String> supportedTypes, boolean enabled, boolean manifestEnabled, String logo, List<String> contentLanguage, String repositoryId, List<String> formats, RepositoryType type) {
        return new ScraperInfo(id, name, description, version, filename, supportedTypes, enabled, manifestEnabled, logo, contentLanguage, repositoryId, formats, type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ScraperInfo)) {
            return false;
        }
        ScraperInfo scraperInfo = (ScraperInfo) other;
        return p.a(this.id, scraperInfo.id) && p.a(this.name, scraperInfo.name) && p.a(this.description, scraperInfo.description) && p.a(this.version, scraperInfo.version) && p.a(this.filename, scraperInfo.filename) && p.a(this.supportedTypes, scraperInfo.supportedTypes) && this.enabled == scraperInfo.enabled && this.manifestEnabled == scraperInfo.manifestEnabled && p.a(this.logo, scraperInfo.logo) && p.a(this.contentLanguage, scraperInfo.contentLanguage) && p.a(this.repositoryId, scraperInfo.repositoryId) && p.a(this.formats, scraperInfo.formats) && this.type == scraperInfo.type;
    }

    public final List<String> getContentLanguage() {
        return this.contentLanguage;
    }

    public final String getDescription() {
        return this.description;
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

    public final String getLogo() {
        return this.logo;
    }

    public final boolean getManifestEnabled() {
        return this.manifestEnabled;
    }

    public final String getName() {
        return this.name;
    }

    public final String getRepositoryId() {
        return this.repositoryId;
    }

    public final List<String> getSupportedTypes() {
        return this.supportedTypes;
    }

    public final RepositoryType getType() {
        return this.type;
    }

    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        int iE = (((d.e(this.supportedTypes, c.c(c.c(c.c(c.c(this.id.hashCode() * 31, 31, this.name), 31, this.description), 31, this.version), 31, this.filename), 31) + (this.enabled ? 1231 : 1237)) * 31) + (this.manifestEnabled ? 1231 : 1237)) * 31;
        String str = this.logo;
        int iC = c.c(d.e(this.contentLanguage, (iE + (str == null ? 0 : str.hashCode())) * 31, 31), 31, this.repositoryId);
        List<String> list = this.formats;
        return this.type.hashCode() + ((iC + (list != null ? list.hashCode() : 0)) * 31);
    }

    public final boolean supportsType(String type) {
        String strSupportsType$normalize = supportsType$normalize(type);
        List<String> list = this.supportedTypes;
        ArrayList arrayList = new ArrayList(s.U(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(supportsType$normalize((String) it.next()));
        }
        return arrayList.contains(strSupportsType$normalize);
    }

    public String toString() {
        String str = this.id;
        String str2 = this.name;
        String str3 = this.description;
        String str4 = this.version;
        String str5 = this.filename;
        List<String> list = this.supportedTypes;
        boolean z = this.enabled;
        boolean z5 = this.manifestEnabled;
        String str6 = this.logo;
        List<String> list2 = this.contentLanguage;
        String str7 = this.repositoryId;
        List<String> list3 = this.formats;
        RepositoryType repositoryType = this.type;
        StringBuilder sbR = a2.r("ScraperInfo(id=", str, ", name=", str2, ", description=");
        a.i(sbR, str3, ", version=", str4, ", filename=");
        sbR.append(str5);
        sbR.append(", supportedTypes=");
        sbR.append(list);
        sbR.append(", enabled=");
        f.h(sbR, z, ", manifestEnabled=", z5, ", logo=");
        sbR.append(str6);
        sbR.append(", contentLanguage=");
        sbR.append(list2);
        sbR.append(", repositoryId=");
        sbR.append(str7);
        sbR.append(", formats=");
        sbR.append(list3);
        sbR.append(", type=");
        sbR.append(repositoryType);
        sbR.append(")");
        return sbR.toString();
    }

    public /* synthetic */ ScraperInfo(String str, String str2, String str3, String str4, String str5, List list, boolean z, boolean z5, String str6, List list2, String str7, List list3, RepositoryType repositoryType, int i10, h hVar) {
        this(str, str2, str3, str4, str5, list, z, z5, str6, list2, str7, list3, (i10 & 4096) != 0 ? RepositoryType.NUVIO_JS : repositoryType);
    }
}
