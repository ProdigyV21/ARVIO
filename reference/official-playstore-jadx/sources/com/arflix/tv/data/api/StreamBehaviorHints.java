package com.arflix.tv.data.api;

import androidx.compose.ui.layout.LayoutKt;
import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.ContentDisposition;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b3\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BÍ\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u00102\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001bJ\u0010\u00103\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001bJ\u000b\u00104\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0011\u00105\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\bHÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\nHÆ\u0003J\u0017\u00107\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\fHÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u00109\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0002\u0010(J\u000b\u0010:\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0012HÆ\u0003JÔ\u0001\u0010A\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0012HÆ\u0001¢\u0006\u0002\u0010BJ\u0014\u0010C\u001a\u00020\u00032\b\u0010D\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010E\u001a\u00020FHÖ\u0081\u0004J\n\u0010G\u001a\u00020\u0006HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001a\u0010\u001bR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001d\u0010\u001bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R$\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001fR\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\n\n\u0002\u0010)\u001a\u0004\b'\u0010(R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001fR\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b-\u0010,R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b.\u0010,R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b/\u0010,R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b0\u0010,R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b1\u0010,¨\u0006H"}, d2 = {"Lcom/arflix/tv/data/api/StreamBehaviorHints;", "", "notWebReady", "", "cached", "bingeGroup", "", "countryWhitelist", "", "proxyHeaders", "Lcom/arflix/tv/data/api/StremioProxyHeaders;", "headers", "", "videoHash", "videoSize", "", ContentDisposition.Parameters.FileName, "provider", "Lcom/google/gson/JsonElement;", "providerCode", "source", "indexer", "indexerCode", "language", "<init>", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/util/List;Lcom/arflix/tv/data/api/StremioProxyHeaders;Ljava/util/Map;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Lcom/google/gson/JsonElement;Lcom/google/gson/JsonElement;Lcom/google/gson/JsonElement;Lcom/google/gson/JsonElement;Lcom/google/gson/JsonElement;Lcom/google/gson/JsonElement;)V", "getNotWebReady", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getCached", "getBingeGroup", "()Ljava/lang/String;", "getCountryWhitelist", "()Ljava/util/List;", "getProxyHeaders", "()Lcom/arflix/tv/data/api/StremioProxyHeaders;", "getHeaders", "()Ljava/util/Map;", "getVideoHash", "getVideoSize", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getFilename", "getProvider", "()Lcom/google/gson/JsonElement;", "getProviderCode", "getSource", "getIndexer", "getIndexerCode", "getLanguage", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "copy", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/util/List;Lcom/arflix/tv/data/api/StremioProxyHeaders;Ljava/util/Map;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Lcom/google/gson/JsonElement;Lcom/google/gson/JsonElement;Lcom/google/gson/JsonElement;Lcom/google/gson/JsonElement;Lcom/google/gson/JsonElement;Lcom/google/gson/JsonElement;)Lcom/arflix/tv/data/api/StreamBehaviorHints;", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class StreamBehaviorHints {
    public static final int $stable = 8;
    private final String bingeGroup;
    private final Boolean cached;
    private final List<String> countryWhitelist;
    private final String filename;

    @SerializedName("headers")
    private final Map<String, String> headers;
    private final JsonElement indexer;
    private final JsonElement indexerCode;
    private final JsonElement language;
    private final Boolean notWebReady;
    private final JsonElement provider;
    private final JsonElement providerCode;
    private final StremioProxyHeaders proxyHeaders;
    private final JsonElement source;
    private final String videoHash;
    private final Long videoSize;

    public StreamBehaviorHints() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, LayoutKt.LargeDimension, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Boolean getNotWebReady() {
        return this.notWebReady;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final JsonElement getProvider() {
        return this.provider;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final JsonElement getProviderCode() {
        return this.providerCode;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final JsonElement getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final JsonElement getIndexer() {
        return this.indexer;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final JsonElement getIndexerCode() {
        return this.indexerCode;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final JsonElement getLanguage() {
        return this.language;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Boolean getCached() {
        return this.cached;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getBingeGroup() {
        return this.bingeGroup;
    }

    public final List<String> component4() {
        return this.countryWhitelist;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final StremioProxyHeaders getProxyHeaders() {
        return this.proxyHeaders;
    }

    public final Map<String, String> component6() {
        return this.headers;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getVideoHash() {
        return this.videoHash;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final Long getVideoSize() {
        return this.videoSize;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getFilename() {
        return this.filename;
    }

    public final StreamBehaviorHints copy(Boolean notWebReady, Boolean cached, String bingeGroup, List<String> countryWhitelist, StremioProxyHeaders proxyHeaders, Map<String, String> headers, String videoHash, Long videoSize, String filename, JsonElement provider, JsonElement providerCode, JsonElement source, JsonElement indexer, JsonElement indexerCode, JsonElement language) {
        return new StreamBehaviorHints(notWebReady, cached, bingeGroup, countryWhitelist, proxyHeaders, headers, videoHash, videoSize, filename, provider, providerCode, source, indexer, indexerCode, language);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StreamBehaviorHints)) {
            return false;
        }
        StreamBehaviorHints streamBehaviorHints = (StreamBehaviorHints) other;
        return p.a(this.notWebReady, streamBehaviorHints.notWebReady) && p.a(this.cached, streamBehaviorHints.cached) && p.a(this.bingeGroup, streamBehaviorHints.bingeGroup) && p.a(this.countryWhitelist, streamBehaviorHints.countryWhitelist) && p.a(this.proxyHeaders, streamBehaviorHints.proxyHeaders) && p.a(this.headers, streamBehaviorHints.headers) && p.a(this.videoHash, streamBehaviorHints.videoHash) && p.a(this.videoSize, streamBehaviorHints.videoSize) && p.a(this.filename, streamBehaviorHints.filename) && p.a(this.provider, streamBehaviorHints.provider) && p.a(this.providerCode, streamBehaviorHints.providerCode) && p.a(this.source, streamBehaviorHints.source) && p.a(this.indexer, streamBehaviorHints.indexer) && p.a(this.indexerCode, streamBehaviorHints.indexerCode) && p.a(this.language, streamBehaviorHints.language);
    }

    public final String getBingeGroup() {
        return this.bingeGroup;
    }

    public final Boolean getCached() {
        return this.cached;
    }

    public final List<String> getCountryWhitelist() {
        return this.countryWhitelist;
    }

    public final String getFilename() {
        return this.filename;
    }

    public final Map<String, String> getHeaders() {
        return this.headers;
    }

    public final JsonElement getIndexer() {
        return this.indexer;
    }

    public final JsonElement getIndexerCode() {
        return this.indexerCode;
    }

    public final JsonElement getLanguage() {
        return this.language;
    }

    public final Boolean getNotWebReady() {
        return this.notWebReady;
    }

    public final JsonElement getProvider() {
        return this.provider;
    }

    public final JsonElement getProviderCode() {
        return this.providerCode;
    }

    public final StremioProxyHeaders getProxyHeaders() {
        return this.proxyHeaders;
    }

    public final JsonElement getSource() {
        return this.source;
    }

    public final String getVideoHash() {
        return this.videoHash;
    }

    public final Long getVideoSize() {
        return this.videoSize;
    }

    public int hashCode() {
        Boolean bool = this.notWebReady;
        int iHashCode = (bool == null ? 0 : bool.hashCode()) * 31;
        Boolean bool2 = this.cached;
        int iHashCode2 = (iHashCode + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        String str = this.bingeGroup;
        int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        List<String> list = this.countryWhitelist;
        int iHashCode4 = (iHashCode3 + (list == null ? 0 : list.hashCode())) * 31;
        StremioProxyHeaders stremioProxyHeaders = this.proxyHeaders;
        int iHashCode5 = (iHashCode4 + (stremioProxyHeaders == null ? 0 : stremioProxyHeaders.hashCode())) * 31;
        Map<String, String> map = this.headers;
        int iHashCode6 = (iHashCode5 + (map == null ? 0 : map.hashCode())) * 31;
        String str2 = this.videoHash;
        int iHashCode7 = (iHashCode6 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Long l10 = this.videoSize;
        int iHashCode8 = (iHashCode7 + (l10 == null ? 0 : l10.hashCode())) * 31;
        String str3 = this.filename;
        int iHashCode9 = (iHashCode8 + (str3 == null ? 0 : str3.hashCode())) * 31;
        JsonElement jsonElement = this.provider;
        int iHashCode10 = (iHashCode9 + (jsonElement == null ? 0 : jsonElement.hashCode())) * 31;
        JsonElement jsonElement2 = this.providerCode;
        int iHashCode11 = (iHashCode10 + (jsonElement2 == null ? 0 : jsonElement2.hashCode())) * 31;
        JsonElement jsonElement3 = this.source;
        int iHashCode12 = (iHashCode11 + (jsonElement3 == null ? 0 : jsonElement3.hashCode())) * 31;
        JsonElement jsonElement4 = this.indexer;
        int iHashCode13 = (iHashCode12 + (jsonElement4 == null ? 0 : jsonElement4.hashCode())) * 31;
        JsonElement jsonElement5 = this.indexerCode;
        int iHashCode14 = (iHashCode13 + (jsonElement5 == null ? 0 : jsonElement5.hashCode())) * 31;
        JsonElement jsonElement6 = this.language;
        return iHashCode14 + (jsonElement6 != null ? jsonElement6.hashCode() : 0);
    }

    public String toString() {
        return "StreamBehaviorHints(notWebReady=" + this.notWebReady + ", cached=" + this.cached + ", bingeGroup=" + this.bingeGroup + ", countryWhitelist=" + this.countryWhitelist + ", proxyHeaders=" + this.proxyHeaders + ", headers=" + this.headers + ", videoHash=" + this.videoHash + ", videoSize=" + this.videoSize + ", filename=" + this.filename + ", provider=" + this.provider + ", providerCode=" + this.providerCode + ", source=" + this.source + ", indexer=" + this.indexer + ", indexerCode=" + this.indexerCode + ", language=" + this.language + ")";
    }

    public StreamBehaviorHints(Boolean bool, Boolean bool2, String str, List<String> list, StremioProxyHeaders stremioProxyHeaders, Map<String, String> map, String str2, Long l10, String str3, JsonElement jsonElement, JsonElement jsonElement2, JsonElement jsonElement3, JsonElement jsonElement4, JsonElement jsonElement5, JsonElement jsonElement6) {
        this.notWebReady = bool;
        this.cached = bool2;
        this.bingeGroup = str;
        this.countryWhitelist = list;
        this.proxyHeaders = stremioProxyHeaders;
        this.headers = map;
        this.videoHash = str2;
        this.videoSize = l10;
        this.filename = str3;
        this.provider = jsonElement;
        this.providerCode = jsonElement2;
        this.source = jsonElement3;
        this.indexer = jsonElement4;
        this.indexerCode = jsonElement5;
        this.language = jsonElement6;
    }

    public /* synthetic */ StreamBehaviorHints(Boolean bool, Boolean bool2, String str, List list, StremioProxyHeaders stremioProxyHeaders, Map map, String str2, Long l10, String str3, JsonElement jsonElement, JsonElement jsonElement2, JsonElement jsonElement3, JsonElement jsonElement4, JsonElement jsonElement5, JsonElement jsonElement6, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : bool, (i10 & 2) != 0 ? null : bool2, (i10 & 4) != 0 ? null : str, (i10 & 8) != 0 ? null : list, (i10 & 16) != 0 ? null : stremioProxyHeaders, (i10 & 32) != 0 ? null : map, (i10 & 64) != 0 ? null : str2, (i10 & 128) != 0 ? null : l10, (i10 & 256) != 0 ? null : str3, (i10 & 512) != 0 ? null : jsonElement, (i10 & 1024) != 0 ? null : jsonElement2, (i10 & 2048) != 0 ? null : jsonElement3, (i10 & 4096) != 0 ? null : jsonElement4, (i10 & 8192) != 0 ? null : jsonElement5, (i10 & 16384) != 0 ? null : jsonElement6);
    }
}
