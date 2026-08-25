package com.arflix.tv.data.model;

import androidx.fragment.app.a2;
import io.ktor.http.ContentDisposition;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import y.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b1\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B³\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0015\u0010\u0016J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\u0010\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001aJ\u000b\u0010/\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0011\u00100\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\bHÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u00103\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010$J\u000b\u00104\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0006HÆ\u0003Jº\u0001\u0010;\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010<J\u0014\u0010=\u001a\u00020\u00032\b\u0010>\u001a\u0004\u0018\u00010?HÖ\u0083\u0004J\n\u0010@\u001a\u00020AHÖ\u0081\u0004J\n\u0010B\u001a\u00020\u0006HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001dR\u0015\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\n\n\u0002\u0010%\u001a\u0004\b#\u0010$R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001dR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001dR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001dR\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001dR\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001dR\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001dR\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001d¨\u0006C"}, d2 = {"Lcom/arflix/tv/data/model/StreamBehaviorHints;", "Ljava/io/Serializable;", "notWebReady", "", "cached", "bingeGroup", "", "countryWhitelist", "", "proxyHeaders", "Lcom/arflix/tv/data/model/ProxyHeaders;", "videoHash", "videoSize", "", ContentDisposition.Parameters.FileName, "provider", "providerCode", "sourceLabel", "indexer", "indexerCode", "language", "<init>", "(ZLjava/lang/Boolean;Ljava/lang/String;Ljava/util/List;Lcom/arflix/tv/data/model/ProxyHeaders;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getNotWebReady", "()Z", "getCached", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getBingeGroup", "()Ljava/lang/String;", "getCountryWhitelist", "()Ljava/util/List;", "getProxyHeaders", "()Lcom/arflix/tv/data/model/ProxyHeaders;", "getVideoHash", "getVideoSize", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getFilename", "getProvider", "getProviderCode", "getSourceLabel", "getIndexer", "getIndexerCode", "getLanguage", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "(ZLjava/lang/Boolean;Ljava/lang/String;Ljava/util/List;Lcom/arflix/tv/data/model/ProxyHeaders;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/data/model/StreamBehaviorHints;", "equals", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class StreamBehaviorHints implements Serializable {
    public static final int $stable = 0;
    private final String bingeGroup;
    private final Boolean cached;
    private final List<String> countryWhitelist;
    private final String filename;
    private final String indexer;
    private final String indexerCode;
    private final String language;
    private final boolean notWebReady;
    private final String provider;
    private final String providerCode;
    private final ProxyHeaders proxyHeaders;
    private final String sourceLabel;
    private final String videoHash;
    private final Long videoSize;

    public StreamBehaviorHints() {
        this(false, null, null, null, null, null, null, null, null, null, null, null, null, null, 16383, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getNotWebReady() {
        return this.notWebReady;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getProviderCode() {
        return this.providerCode;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getSourceLabel() {
        return this.sourceLabel;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getIndexer() {
        return this.indexer;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getIndexerCode() {
        return this.indexerCode;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getLanguage() {
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
    public final ProxyHeaders getProxyHeaders() {
        return this.proxyHeaders;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getVideoHash() {
        return this.videoHash;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Long getVideoSize() {
        return this.videoSize;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getFilename() {
        return this.filename;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getProvider() {
        return this.provider;
    }

    public final StreamBehaviorHints copy(boolean notWebReady, Boolean cached, String bingeGroup, List<String> countryWhitelist, ProxyHeaders proxyHeaders, String videoHash, Long videoSize, String filename, String provider, String providerCode, String sourceLabel, String indexer, String indexerCode, String language) {
        return new StreamBehaviorHints(notWebReady, cached, bingeGroup, countryWhitelist, proxyHeaders, videoHash, videoSize, filename, provider, providerCode, sourceLabel, indexer, indexerCode, language);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StreamBehaviorHints)) {
            return false;
        }
        StreamBehaviorHints streamBehaviorHints = (StreamBehaviorHints) other;
        return this.notWebReady == streamBehaviorHints.notWebReady && p.a(this.cached, streamBehaviorHints.cached) && p.a(this.bingeGroup, streamBehaviorHints.bingeGroup) && p.a(this.countryWhitelist, streamBehaviorHints.countryWhitelist) && p.a(this.proxyHeaders, streamBehaviorHints.proxyHeaders) && p.a(this.videoHash, streamBehaviorHints.videoHash) && p.a(this.videoSize, streamBehaviorHints.videoSize) && p.a(this.filename, streamBehaviorHints.filename) && p.a(this.provider, streamBehaviorHints.provider) && p.a(this.providerCode, streamBehaviorHints.providerCode) && p.a(this.sourceLabel, streamBehaviorHints.sourceLabel) && p.a(this.indexer, streamBehaviorHints.indexer) && p.a(this.indexerCode, streamBehaviorHints.indexerCode) && p.a(this.language, streamBehaviorHints.language);
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

    public final String getIndexer() {
        return this.indexer;
    }

    public final String getIndexerCode() {
        return this.indexerCode;
    }

    public final String getLanguage() {
        return this.language;
    }

    public final boolean getNotWebReady() {
        return this.notWebReady;
    }

    public final String getProvider() {
        return this.provider;
    }

    public final String getProviderCode() {
        return this.providerCode;
    }

    public final ProxyHeaders getProxyHeaders() {
        return this.proxyHeaders;
    }

    public final String getSourceLabel() {
        return this.sourceLabel;
    }

    public final String getVideoHash() {
        return this.videoHash;
    }

    public final Long getVideoSize() {
        return this.videoSize;
    }

    public int hashCode() {
        int i10 = (this.notWebReady ? 1231 : 1237) * 31;
        Boolean bool = this.cached;
        int iHashCode = (i10 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str = this.bingeGroup;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        List<String> list = this.countryWhitelist;
        int iHashCode3 = (iHashCode2 + (list == null ? 0 : list.hashCode())) * 31;
        ProxyHeaders proxyHeaders = this.proxyHeaders;
        int iHashCode4 = (iHashCode3 + (proxyHeaders == null ? 0 : proxyHeaders.hashCode())) * 31;
        String str2 = this.videoHash;
        int iHashCode5 = (iHashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Long l10 = this.videoSize;
        int iHashCode6 = (iHashCode5 + (l10 == null ? 0 : l10.hashCode())) * 31;
        String str3 = this.filename;
        int iHashCode7 = (iHashCode6 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.provider;
        int iHashCode8 = (iHashCode7 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.providerCode;
        int iHashCode9 = (iHashCode8 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.sourceLabel;
        int iHashCode10 = (iHashCode9 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.indexer;
        int iHashCode11 = (iHashCode10 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.indexerCode;
        int iHashCode12 = (iHashCode11 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.language;
        return iHashCode12 + (str9 != null ? str9.hashCode() : 0);
    }

    public String toString() {
        boolean z = this.notWebReady;
        Boolean bool = this.cached;
        String str = this.bingeGroup;
        List<String> list = this.countryWhitelist;
        ProxyHeaders proxyHeaders = this.proxyHeaders;
        String str2 = this.videoHash;
        Long l10 = this.videoSize;
        String str3 = this.filename;
        String str4 = this.provider;
        String str5 = this.providerCode;
        String str6 = this.sourceLabel;
        String str7 = this.indexer;
        String str8 = this.indexerCode;
        String str9 = this.language;
        StringBuilder sb2 = new StringBuilder("StreamBehaviorHints(notWebReady=");
        sb2.append(z);
        sb2.append(", cached=");
        sb2.append(bool);
        sb2.append(", bingeGroup=");
        sb2.append(str);
        sb2.append(", countryWhitelist=");
        sb2.append(list);
        sb2.append(", proxyHeaders=");
        sb2.append(proxyHeaders);
        sb2.append(", videoHash=");
        sb2.append(str2);
        sb2.append(", videoSize=");
        sb2.append(l10);
        sb2.append(", filename=");
        sb2.append(str3);
        sb2.append(", provider=");
        a.i(sb2, str4, ", providerCode=", str5, ", sourceLabel=");
        a.i(sb2, str6, ", indexer=", str7, ", indexerCode=");
        return a2.n(sb2, str8, ", language=", str9, ")");
    }

    public StreamBehaviorHints(boolean z, Boolean bool, String str, List<String> list, ProxyHeaders proxyHeaders, String str2, Long l10, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        this.notWebReady = z;
        this.cached = bool;
        this.bingeGroup = str;
        this.countryWhitelist = list;
        this.proxyHeaders = proxyHeaders;
        this.videoHash = str2;
        this.videoSize = l10;
        this.filename = str3;
        this.provider = str4;
        this.providerCode = str5;
        this.sourceLabel = str6;
        this.indexer = str7;
        this.indexerCode = str8;
        this.language = str9;
    }

    public /* synthetic */ StreamBehaviorHints(boolean z, Boolean bool, String str, List list, ProxyHeaders proxyHeaders, String str2, Long l10, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i10, h hVar) {
        this((i10 & 1) != 0 ? false : z, (i10 & 2) != 0 ? null : bool, (i10 & 4) != 0 ? null : str, (i10 & 8) != 0 ? null : list, (i10 & 16) != 0 ? null : proxyHeaders, (i10 & 32) != 0 ? null : str2, (i10 & 64) != 0 ? null : l10, (i10 & 128) != 0 ? null : str3, (i10 & 256) != 0 ? null : str4, (i10 & 512) != 0 ? null : str5, (i10 & 1024) != 0 ? null : str6, (i10 & 2048) != 0 ? null : str7, (i10 & 4096) != 0 ? null : str8, (i10 & 8192) != 0 ? null : str9);
    }
}
