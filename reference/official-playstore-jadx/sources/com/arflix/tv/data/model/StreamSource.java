package com.arflix.tv.data.model;

import androidx.compose.foundation.c;
import androidx.compose.material3.d;
import androidx.fragment.app.a2;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.ContentDisposition;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import y.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b.\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B±\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\t\u00102\u001a\u00020\u0003HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\u0010\u00104\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010 J\u000b\u00105\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u00107\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010%J\u000b\u00108\u001a\u0004\u0018\u00010\u000fHÆ\u0003J\u000f\u00109\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011HÆ\u0003J\u000f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0003HÆ\u0003JÀ\u0001\u0010>\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u00112\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010?J\u0014\u0010@\u001a\u00020A2\b\u0010B\u001a\u0004\u0018\u00010CHÖ\u0083\u0004J\n\u0010D\u001a\u00020\rHÖ\u0081\u0004J\n\u0010E\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001aR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010!\u001a\u0004\b\u001f\u0010 R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001aR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001aR\u0015\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\n\n\u0002\u0010&\u001a\u0004\b$\u0010%R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011¢\u0006\b\n\u0000\u001a\u0004\b+\u0010*R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001aR\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001aR\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001a¨\u0006F"}, d2 = {"Lcom/arflix/tv/data/model/StreamSource;", "Ljava/io/Serializable;", "source", "", "addonName", "addonId", "quality", ContentDisposition.Parameters.Size, "sizeBytes", "", "url", "infoHash", "fileIdx", "", "behaviorHints", "Lcom/arflix/tv/data/model/StreamBehaviorHints;", "subtitles", "", "Lcom/arflix/tv/data/model/Subtitle;", "sources", MediaTrack.ROLE_DESCRIPTION, "rawLabel", "addonTitle", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Lcom/arflix/tv/data/model/StreamBehaviorHints;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getSource", "()Ljava/lang/String;", "getAddonName", "getAddonId", "getQuality", "getSize", "getSizeBytes", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getUrl", "getInfoHash", "getFileIdx", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getBehaviorHints", "()Lcom/arflix/tv/data/model/StreamBehaviorHints;", "getSubtitles", "()Ljava/util/List;", "getSources", "getDescription", "getRawLabel", "getAddonTitle", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Lcom/arflix/tv/data/model/StreamBehaviorHints;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/data/model/StreamSource;", "equals", "", "other", "", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class StreamSource implements Serializable {
    public static final int $stable = 0;
    private final String addonId;
    private final String addonName;
    private final String addonTitle;
    private final StreamBehaviorHints behaviorHints;
    private final String description;
    private final Integer fileIdx;
    private final String infoHash;
    private final String quality;
    private final String rawLabel;
    private final String size;
    private final Long sizeBytes;
    private final String source;
    private final List<String> sources;
    private final List<Subtitle> subtitles;
    private final String url;

    public StreamSource(String str, String str2, String str3, String str4, String str5, Long l10, String str6, String str7, Integer num, StreamBehaviorHints streamBehaviorHints, List<Subtitle> list, List<String> list2, String str8, String str9, String str10) {
        this.source = str;
        this.addonName = str2;
        this.addonId = str3;
        this.quality = str4;
        this.size = str5;
        this.sizeBytes = l10;
        this.url = str6;
        this.infoHash = str7;
        this.fileIdx = num;
        this.behaviorHints = streamBehaviorHints;
        this.subtitles = list;
        this.sources = list2;
        this.description = str8;
        this.rawLabel = str9;
        this.addonTitle = str10;
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final StreamBehaviorHints getBehaviorHints() {
        return this.behaviorHints;
    }

    public final List<Subtitle> component11() {
        return this.subtitles;
    }

    public final List<String> component12() {
        return this.sources;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getRawLabel() {
        return this.rawLabel;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getAddonTitle() {
        return this.addonTitle;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getAddonName() {
        return this.addonName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getAddonId() {
        return this.addonId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getQuality() {
        return this.quality;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getSize() {
        return this.size;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Long getSizeBytes() {
        return this.sizeBytes;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getInfoHash() {
        return this.infoHash;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final Integer getFileIdx() {
        return this.fileIdx;
    }

    public final StreamSource copy(String source, String addonName, String addonId, String quality, String size, Long sizeBytes, String url, String infoHash, Integer fileIdx, StreamBehaviorHints behaviorHints, List<Subtitle> subtitles, List<String> sources, String description, String rawLabel, String addonTitle) {
        return new StreamSource(source, addonName, addonId, quality, size, sizeBytes, url, infoHash, fileIdx, behaviorHints, subtitles, sources, description, rawLabel, addonTitle);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StreamSource)) {
            return false;
        }
        StreamSource streamSource = (StreamSource) other;
        return p.a(this.source, streamSource.source) && p.a(this.addonName, streamSource.addonName) && p.a(this.addonId, streamSource.addonId) && p.a(this.quality, streamSource.quality) && p.a(this.size, streamSource.size) && p.a(this.sizeBytes, streamSource.sizeBytes) && p.a(this.url, streamSource.url) && p.a(this.infoHash, streamSource.infoHash) && p.a(this.fileIdx, streamSource.fileIdx) && p.a(this.behaviorHints, streamSource.behaviorHints) && p.a(this.subtitles, streamSource.subtitles) && p.a(this.sources, streamSource.sources) && p.a(this.description, streamSource.description) && p.a(this.rawLabel, streamSource.rawLabel) && p.a(this.addonTitle, streamSource.addonTitle);
    }

    public final String getAddonId() {
        return this.addonId;
    }

    public final String getAddonName() {
        return this.addonName;
    }

    public final String getAddonTitle() {
        return this.addonTitle;
    }

    public final StreamBehaviorHints getBehaviorHints() {
        return this.behaviorHints;
    }

    public final String getDescription() {
        return this.description;
    }

    public final Integer getFileIdx() {
        return this.fileIdx;
    }

    public final String getInfoHash() {
        return this.infoHash;
    }

    public final String getQuality() {
        return this.quality;
    }

    public final String getRawLabel() {
        return this.rawLabel;
    }

    public final String getSize() {
        return this.size;
    }

    public final Long getSizeBytes() {
        return this.sizeBytes;
    }

    public final String getSource() {
        return this.source;
    }

    public final List<String> getSources() {
        return this.sources;
    }

    public final List<Subtitle> getSubtitles() {
        return this.subtitles;
    }

    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        int iC = c.c(c.c(c.c(c.c(this.source.hashCode() * 31, 31, this.addonName), 31, this.addonId), 31, this.quality), 31, this.size);
        Long l10 = this.sizeBytes;
        int iHashCode = (iC + (l10 == null ? 0 : l10.hashCode())) * 31;
        String str = this.url;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.infoHash;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num = this.fileIdx;
        int iHashCode4 = (iHashCode3 + (num == null ? 0 : num.hashCode())) * 31;
        StreamBehaviorHints streamBehaviorHints = this.behaviorHints;
        int iE = d.e(this.sources, d.e(this.subtitles, (iHashCode4 + (streamBehaviorHints == null ? 0 : streamBehaviorHints.hashCode())) * 31, 31), 31);
        String str3 = this.description;
        int iHashCode5 = (iE + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.rawLabel;
        int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.addonTitle;
        return iHashCode6 + (str5 != null ? str5.hashCode() : 0);
    }

    public String toString() {
        String str = this.source;
        String str2 = this.addonName;
        String str3 = this.addonId;
        String str4 = this.quality;
        String str5 = this.size;
        Long l10 = this.sizeBytes;
        String str6 = this.url;
        String str7 = this.infoHash;
        Integer num = this.fileIdx;
        StreamBehaviorHints streamBehaviorHints = this.behaviorHints;
        List<Subtitle> list = this.subtitles;
        List<String> list2 = this.sources;
        String str8 = this.description;
        String str9 = this.rawLabel;
        String str10 = this.addonTitle;
        StringBuilder sbR = a2.r("StreamSource(source=", str, ", addonName=", str2, ", addonId=");
        a.i(sbR, str3, ", quality=", str4, ", size=");
        sbR.append(str5);
        sbR.append(", sizeBytes=");
        sbR.append(l10);
        sbR.append(", url=");
        a.i(sbR, str6, ", infoHash=", str7, ", fileIdx=");
        sbR.append(num);
        sbR.append(", behaviorHints=");
        sbR.append(streamBehaviorHints);
        sbR.append(", subtitles=");
        a2.A(sbR, list, ", sources=", list2, ", description=");
        a.i(sbR, str8, ", rawLabel=", str9, ", addonTitle=");
        return a0.c.p(sbR, str10, ")");
    }

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.SSAVar.getPhiList()" because "resultVar" is null
        	at jadx.core.dex.visitors.InitCodeVariables.collectConnectedVars(InitCodeVariables.java:119)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:82)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:48)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:29)
        */
    public /* synthetic */ StreamSource(java.lang.String r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, java.lang.String r23, java.lang.Long r24, java.lang.String r25, java.lang.String r26, java.lang.Integer r27, com.arflix.tv.data.model.StreamBehaviorHints r28, java.util.List r29, java.util.List r30, java.lang.String r31, java.lang.String r32, java.lang.String r33, int r34, kotlin.jvm.internal.h r35) {
        /*
            r18 = this;
            r0 = r34
            r1 = r0 & 4
            if (r1 == 0) goto La
            java.lang.String r1 = ""
            r5 = r1
            goto Lc
        La:
            r5 = r21
        Lc:
            r1 = r0 & 32
            r2 = 0
            if (r1 == 0) goto L13
            r8 = r2
            goto L15
        L13:
            r8 = r24
        L15:
            r1 = r0 & 64
            if (r1 == 0) goto L1b
            r9 = r2
            goto L1d
        L1b:
            r9 = r25
        L1d:
            r1 = r0 & 128(0x80, float:1.8E-43)
            if (r1 == 0) goto L23
            r10 = r2
            goto L25
        L23:
            r10 = r26
        L25:
            r1 = r0 & 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L2b
            r11 = r2
            goto L2d
        L2b:
            r11 = r27
        L2d:
            r1 = r0 & 512(0x200, float:7.17E-43)
            if (r1 == 0) goto L33
            r12 = r2
            goto L35
        L33:
            r12 = r28
        L35:
            r1 = r0 & 1024(0x400, float:1.435E-42)
            kotlin.collections.z r3 = kotlin.collections.z.f19728i
            if (r1 == 0) goto L3d
            r13 = r3
            goto L3f
        L3d:
            r13 = r29
        L3f:
            r1 = r0 & 2048(0x800, float:2.87E-42)
            if (r1 == 0) goto L45
            r14 = r3
            goto L47
        L45:
            r14 = r30
        L47:
            r1 = r0 & 4096(0x1000, float:5.74E-42)
            if (r1 == 0) goto L4d
            r15 = r2
            goto L4f
        L4d:
            r15 = r31
        L4f:
            r1 = r0 & 8192(0x2000, float:1.148E-41)
            if (r1 == 0) goto L56
            r16 = r2
            goto L58
        L56:
            r16 = r32
        L58:
            r0 = r0 & 16384(0x4000, float:2.2959E-41)
            if (r0 == 0) goto L69
            r17 = r2
            r3 = r19
            r4 = r20
            r6 = r22
            r7 = r23
            r2 = r18
            goto L75
        L69:
            r17 = r33
            r2 = r18
            r3 = r19
            r4 = r20
            r6 = r22
            r7 = r23
        L75:
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.model.StreamSource.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.Integer, com.arflix.tv.data.model.StreamBehaviorHints, java.util.List, java.util.List, java.lang.String, java.lang.String, java.lang.String, int, kotlin.jvm.internal.h):void");
    }
}
