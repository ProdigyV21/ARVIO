package com.arflix.tv.data.model;

import androidx.compose.foundation.c;
import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.session.MediaUtils;
import io.ktor.http.ContentDisposition;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.a0;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import y.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b1\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001Bá\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0012\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\u0004\b\u0019\u0010\u001aJ\t\u00104\u001a\u00020\u0003HÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0003HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010:\u001a\u00020\u0003HÆ\u0003J\u0010\u0010;\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010$J\t\u0010<\u001a\u00020\u000bHÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010A\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0012HÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0018HÆ\u0003Jð\u0001\u0010G\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÆ\u0001¢\u0006\u0002\u0010HJ\u0014\u0010I\u001a\u00020J2\b\u0010K\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010L\u001a\u00020\u000bHÖ\u0081\u0004J\n\u0010M\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001cR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001cR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001cR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001cR\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010%\u001a\u0004\b#\u0010$R\u0011\u0010\f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001cR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001cR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001cR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001cR\u001d\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0012¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001cR\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u001cR\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001cR\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u001cR\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\b\n\u0000\u001a\u0004\b2\u00103¨\u0006N"}, d2 = {"Lcom/arflix/tv/data/model/IptvChannel;", "", TtmlNode.ATTR_ID, "", ContentDisposition.Parameters.Name, "streamUrl", "group", "logo", "epgId", "rawTitle", "xtreamStreamId", "", "catchupDays", "catchupType", "catchupSource", "tvgName", "providerChannelNumber", "requestHeaders", "", "language", "country", "qualityLabel", "variantKey", "drmInfo", "Lcom/arflix/tv/data/model/DrmInfo;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/model/DrmInfo;)V", "getId", "()Ljava/lang/String;", "getName", "getStreamUrl", "getGroup", "getLogo", "getEpgId", "getRawTitle", "getXtreamStreamId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getCatchupDays", "()I", "getCatchupType", "getCatchupSource", "getTvgName", "getProviderChannelNumber", "getRequestHeaders", "()Ljava/util/Map;", "getLanguage", "getCountry", "getQualityLabel", "getVariantKey", "getDrmInfo", "()Lcom/arflix/tv/data/model/DrmInfo;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/model/DrmInfo;)Lcom/arflix/tv/data/model/IptvChannel;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class IptvChannel {
    public static final int $stable = 0;
    private final int catchupDays;
    private final String catchupSource;
    private final String catchupType;
    private final String country;
    private final DrmInfo drmInfo;
    private final String epgId;
    private final String group;
    private final String id;
    private final String language;
    private final String logo;
    private final String name;
    private final String providerChannelNumber;
    private final String qualityLabel;
    private final String rawTitle;
    private final Map<String, String> requestHeaders;
    private final String streamUrl;
    private final String tvgName;
    private final String variantKey;
    private final Integer xtreamStreamId;

    public IptvChannel(String str, String str2, String str3, String str4, String str5, String str6, String str7, Integer num, int i10, String str8, String str9, String str10, String str11, Map<String, String> map, String str12, String str13, String str14, String str15, DrmInfo drmInfo) {
        this.id = str;
        this.name = str2;
        this.streamUrl = str3;
        this.group = str4;
        this.logo = str5;
        this.epgId = str6;
        this.rawTitle = str7;
        this.xtreamStreamId = num;
        this.catchupDays = i10;
        this.catchupType = str8;
        this.catchupSource = str9;
        this.tvgName = str10;
        this.providerChannelNumber = str11;
        this.requestHeaders = map;
        this.language = str12;
        this.country = str13;
        this.qualityLabel = str14;
        this.variantKey = str15;
        this.drmInfo = drmInfo;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ IptvChannel copy$default(IptvChannel iptvChannel, String str, String str2, String str3, String str4, String str5, String str6, String str7, Integer num, int i10, String str8, String str9, String str10, String str11, Map map, String str12, String str13, String str14, String str15, DrmInfo drmInfo, int i11, Object obj) {
        DrmInfo drmInfo2;
        String str16;
        String str17 = (i11 & 1) != 0 ? iptvChannel.id : str;
        String str18 = (i11 & 2) != 0 ? iptvChannel.name : str2;
        String str19 = (i11 & 4) != 0 ? iptvChannel.streamUrl : str3;
        String str20 = (i11 & 8) != 0 ? iptvChannel.group : str4;
        String str21 = (i11 & 16) != 0 ? iptvChannel.logo : str5;
        String str22 = (i11 & 32) != 0 ? iptvChannel.epgId : str6;
        String str23 = (i11 & 64) != 0 ? iptvChannel.rawTitle : str7;
        Integer num2 = (i11 & 128) != 0 ? iptvChannel.xtreamStreamId : num;
        int i12 = (i11 & 256) != 0 ? iptvChannel.catchupDays : i10;
        String str24 = (i11 & 512) != 0 ? iptvChannel.catchupType : str8;
        String str25 = (i11 & 1024) != 0 ? iptvChannel.catchupSource : str9;
        String str26 = (i11 & 2048) != 0 ? iptvChannel.tvgName : str10;
        String str27 = (i11 & 4096) != 0 ? iptvChannel.providerChannelNumber : str11;
        Map map2 = (i11 & 8192) != 0 ? iptvChannel.requestHeaders : map;
        String str28 = str17;
        String str29 = (i11 & 16384) != 0 ? iptvChannel.language : str12;
        String str30 = (i11 & 32768) != 0 ? iptvChannel.country : str13;
        String str31 = (i11 & 65536) != 0 ? iptvChannel.qualityLabel : str14;
        String str32 = (i11 & 131072) != 0 ? iptvChannel.variantKey : str15;
        if ((i11 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0) {
            str16 = str32;
            drmInfo2 = iptvChannel.drmInfo;
        } else {
            drmInfo2 = drmInfo;
            str16 = str32;
        }
        return iptvChannel.copy(str28, str18, str19, str20, str21, str22, str23, num2, i12, str24, str25, str26, str27, map2, str29, str30, str31, str16, drmInfo2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getCatchupType() {
        return this.catchupType;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getCatchupSource() {
        return this.catchupSource;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getTvgName() {
        return this.tvgName;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getProviderChannelNumber() {
        return this.providerChannelNumber;
    }

    public final Map<String, String> component14() {
        return this.requestHeaders;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getLanguage() {
        return this.language;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getCountry() {
        return this.country;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final String getQualityLabel() {
        return this.qualityLabel;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final String getVariantKey() {
        return this.variantKey;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final DrmInfo getDrmInfo() {
        return this.drmInfo;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getStreamUrl() {
        return this.streamUrl;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getGroup() {
        return this.group;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getLogo() {
        return this.logo;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getEpgId() {
        return this.epgId;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getRawTitle() {
        return this.rawTitle;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final Integer getXtreamStreamId() {
        return this.xtreamStreamId;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getCatchupDays() {
        return this.catchupDays;
    }

    public final IptvChannel copy(String id, String name, String streamUrl, String group, String logo, String epgId, String rawTitle, Integer xtreamStreamId, int catchupDays, String catchupType, String catchupSource, String tvgName, String providerChannelNumber, Map<String, String> requestHeaders, String language, String country, String qualityLabel, String variantKey, DrmInfo drmInfo) {
        return new IptvChannel(id, name, streamUrl, group, logo, epgId, rawTitle, xtreamStreamId, catchupDays, catchupType, catchupSource, tvgName, providerChannelNumber, requestHeaders, language, country, qualityLabel, variantKey, drmInfo);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IptvChannel)) {
            return false;
        }
        IptvChannel iptvChannel = (IptvChannel) other;
        return p.a(this.id, iptvChannel.id) && p.a(this.name, iptvChannel.name) && p.a(this.streamUrl, iptvChannel.streamUrl) && p.a(this.group, iptvChannel.group) && p.a(this.logo, iptvChannel.logo) && p.a(this.epgId, iptvChannel.epgId) && p.a(this.rawTitle, iptvChannel.rawTitle) && p.a(this.xtreamStreamId, iptvChannel.xtreamStreamId) && this.catchupDays == iptvChannel.catchupDays && p.a(this.catchupType, iptvChannel.catchupType) && p.a(this.catchupSource, iptvChannel.catchupSource) && p.a(this.tvgName, iptvChannel.tvgName) && p.a(this.providerChannelNumber, iptvChannel.providerChannelNumber) && p.a(this.requestHeaders, iptvChannel.requestHeaders) && p.a(this.language, iptvChannel.language) && p.a(this.country, iptvChannel.country) && p.a(this.qualityLabel, iptvChannel.qualityLabel) && p.a(this.variantKey, iptvChannel.variantKey) && p.a(this.drmInfo, iptvChannel.drmInfo);
    }

    public final int getCatchupDays() {
        return this.catchupDays;
    }

    public final String getCatchupSource() {
        return this.catchupSource;
    }

    public final String getCatchupType() {
        return this.catchupType;
    }

    public final String getCountry() {
        return this.country;
    }

    public final DrmInfo getDrmInfo() {
        return this.drmInfo;
    }

    public final String getEpgId() {
        return this.epgId;
    }

    public final String getGroup() {
        return this.group;
    }

    public final String getId() {
        return this.id;
    }

    public final String getLanguage() {
        return this.language;
    }

    public final String getLogo() {
        return this.logo;
    }

    public final String getName() {
        return this.name;
    }

    public final String getProviderChannelNumber() {
        return this.providerChannelNumber;
    }

    public final String getQualityLabel() {
        return this.qualityLabel;
    }

    public final String getRawTitle() {
        return this.rawTitle;
    }

    public final Map<String, String> getRequestHeaders() {
        return this.requestHeaders;
    }

    public final String getStreamUrl() {
        return this.streamUrl;
    }

    public final String getTvgName() {
        return this.tvgName;
    }

    public final String getVariantKey() {
        return this.variantKey;
    }

    public final Integer getXtreamStreamId() {
        return this.xtreamStreamId;
    }

    public int hashCode() {
        int iC = c.c(c.c(c.c(this.id.hashCode() * 31, 31, this.name), 31, this.streamUrl), 31, this.group);
        String str = this.logo;
        int iHashCode = (iC + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.epgId;
        int iC2 = c.c((iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31, 31, this.rawTitle);
        Integer num = this.xtreamStreamId;
        int iHashCode2 = (((iC2 + (num == null ? 0 : num.hashCode())) * 31) + this.catchupDays) * 31;
        String str3 = this.catchupType;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.catchupSource;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.tvgName;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.providerChannelNumber;
        int iG = a2.g(this.requestHeaders, (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31, 31);
        String str7 = this.language;
        int iHashCode6 = (iG + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.country;
        int iHashCode7 = (iHashCode6 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.qualityLabel;
        int iHashCode8 = (iHashCode7 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.variantKey;
        int iHashCode9 = (iHashCode8 + (str10 == null ? 0 : str10.hashCode())) * 31;
        DrmInfo drmInfo = this.drmInfo;
        return iHashCode9 + (drmInfo != null ? drmInfo.hashCode() : 0);
    }

    public String toString() {
        String str = this.id;
        String str2 = this.name;
        String str3 = this.streamUrl;
        String str4 = this.group;
        String str5 = this.logo;
        String str6 = this.epgId;
        String str7 = this.rawTitle;
        Integer num = this.xtreamStreamId;
        int i10 = this.catchupDays;
        String str8 = this.catchupType;
        String str9 = this.catchupSource;
        String str10 = this.tvgName;
        String str11 = this.providerChannelNumber;
        Map<String, String> map = this.requestHeaders;
        String str12 = this.language;
        String str13 = this.country;
        String str14 = this.qualityLabel;
        String str15 = this.variantKey;
        DrmInfo drmInfo = this.drmInfo;
        StringBuilder sbR = a2.r("IptvChannel(id=", str, ", name=", str2, ", streamUrl=");
        a.i(sbR, str3, ", group=", str4, ", logo=");
        a.i(sbR, str5, ", epgId=", str6, ", rawTitle=");
        sbR.append(str7);
        sbR.append(", xtreamStreamId=");
        sbR.append(num);
        sbR.append(", catchupDays=");
        sbR.append(i10);
        sbR.append(", catchupType=");
        sbR.append(str8);
        sbR.append(", catchupSource=");
        a.i(sbR, str9, ", tvgName=", str10, ", providerChannelNumber=");
        sbR.append(str11);
        sbR.append(", requestHeaders=");
        sbR.append(map);
        sbR.append(", language=");
        a.i(sbR, str12, ", country=", str13, ", qualityLabel=");
        a.i(sbR, str14, ", variantKey=", str15, ", drmInfo=");
        sbR.append(drmInfo);
        sbR.append(")");
        return sbR.toString();
    }

    public /* synthetic */ IptvChannel(String str, String str2, String str3, String str4, String str5, String str6, String str7, Integer num, int i10, String str8, String str9, String str10, String str11, Map map, String str12, String str13, String str14, String str15, DrmInfo drmInfo, int i11, h hVar) {
        this(str, str2, str3, str4, (i11 & 16) != 0 ? null : str5, (i11 & 32) != 0 ? null : str6, (i11 & 64) != 0 ? str2 : str7, (i11 & 128) != 0 ? null : num, (i11 & 256) != 0 ? 0 : i10, (i11 & 512) != 0 ? null : str8, (i11 & 1024) != 0 ? null : str9, (i11 & 2048) != 0 ? null : str10, (i11 & 4096) != 0 ? null : str11, (i11 & 8192) != 0 ? a0.f19683i : map, (i11 & 16384) != 0 ? null : str12, (32768 & i11) != 0 ? null : str13, (65536 & i11) != 0 ? null : str14, (131072 & i11) != 0 ? null : str15, (i11 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? null : drmInfo);
    }
}
