package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.arflix.tv.data.model.IptvChannel;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import gb.g0;
import gb.h0;
import gb.j0;
import gb.s0;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.a0;
import kotlin.jvm.internal.p;
import kotlin.text.o;
import kotlin.text.u;
import x6.x;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\r\b\u0007\u0018\u00002\u00020\u0001:\t!\"#$%&'()B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\b\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000b\u001a\u00020\nH\u0086@¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\nH\u0086@¢\u0006\u0004\b\r\u0010\fJ\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0086@¢\u0006\u0004\b\u0010\u0010\fJ\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0011\u001a\u00020\u0002H\u0086@¢\u0006\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0014R\u0014\u0010\u0004\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001b\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u0014R\u0016\u0010\u001c\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u0014R \u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u001d8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u0006*"}, d2 = {"Lcom/arflix/tv/data/api/StalkerApi;", "", "", "portalUrl", "macAddress", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "url", "doGet", "(Ljava/lang/String;)Ljava/lang/String;", "", "handshake", "(Ld7/d;)Ljava/lang/Object;", "getProfile", "", "Lcom/arflix/tv/data/model/IptvChannel;", "getChannels", "cmd", "resolveStreamUrl", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "Ljava/lang/String;", "Lgb/h0;", "client", "Lgb/h0;", "Lcom/google/gson/Gson;", "gson", "Lcom/google/gson/Gson;", "token", "serialNumber", "", "getBaseHeaders", "()Ljava/util/Map;", "baseHeaders", "StalkerHandshakeResponse", "StalkerToken", "StalkerGenreResponse", "StalkerGenre", "StalkerChannelResponse", "StalkerChannelData", "StalkerChannel", "StalkerLinkResponse", "StalkerLink", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class StalkerApi {
    public static final int $stable = 8;
    private final h0 client;
    private final Gson gson;
    private final String macAddress;
    private final String portalUrl;
    private String serialNumber;
    private String token;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B9\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003JJ\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0019J\u0014\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u0005HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000f¨\u0006\u001f"}, d2 = {"Lcom/arflix/tv/data/api/StalkerApi$StalkerChannel;", "", TtmlNode.ATTR_ID, "", ContentDisposition.Parameters.Name, "", "logo", "cmd", "tvGenreId", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getName", "()Ljava/lang/String;", "getLogo", "getCmd", "getTvGenreId", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/data/api/StalkerApi$StalkerChannel;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class StalkerChannel {
        public static final int $stable = 0;
        private final String cmd;
        private final Integer id;
        private final String logo;
        private final String name;

        @SerializedName("tv_genre_id")
        private final String tvGenreId;

        public StalkerChannel(Integer num, String str, String str2, String str3, String str4) {
            this.id = num;
            this.name = str;
            this.logo = str2;
            this.cmd = str3;
            this.tvGenreId = str4;
        }

        public static /* synthetic */ StalkerChannel copy$default(StalkerChannel stalkerChannel, Integer num, String str, String str2, String str3, String str4, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                num = stalkerChannel.id;
            }
            if ((i10 & 2) != 0) {
                str = stalkerChannel.name;
            }
            if ((i10 & 4) != 0) {
                str2 = stalkerChannel.logo;
            }
            if ((i10 & 8) != 0) {
                str3 = stalkerChannel.cmd;
            }
            if ((i10 & 16) != 0) {
                str4 = stalkerChannel.tvGenreId;
            }
            String str5 = str4;
            String str6 = str2;
            return stalkerChannel.copy(num, str, str6, str3, str5);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getLogo() {
            return this.logo;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getCmd() {
            return this.cmd;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getTvGenreId() {
            return this.tvGenreId;
        }

        public final StalkerChannel copy(Integer id, String name, String logo, String cmd, String tvGenreId) {
            return new StalkerChannel(id, name, logo, cmd, tvGenreId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StalkerChannel)) {
                return false;
            }
            StalkerChannel stalkerChannel = (StalkerChannel) other;
            return p.a(this.id, stalkerChannel.id) && p.a(this.name, stalkerChannel.name) && p.a(this.logo, stalkerChannel.logo) && p.a(this.cmd, stalkerChannel.cmd) && p.a(this.tvGenreId, stalkerChannel.tvGenreId);
        }

        public final String getCmd() {
            return this.cmd;
        }

        public final Integer getId() {
            return this.id;
        }

        public final String getLogo() {
            return this.logo;
        }

        public final String getName() {
            return this.name;
        }

        public final String getTvGenreId() {
            return this.tvGenreId;
        }

        public int hashCode() {
            Integer num = this.id;
            int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
            String str = this.name;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.logo;
            int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.cmd;
            int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.tvGenreId;
            return iHashCode4 + (str4 != null ? str4.hashCode() : 0);
        }

        public String toString() {
            Integer num = this.id;
            String str = this.name;
            String str2 = this.logo;
            String str3 = this.cmd;
            String str4 = this.tvGenreId;
            StringBuilder sb2 = new StringBuilder("StalkerChannel(id=");
            sb2.append(num);
            sb2.append(", name=");
            sb2.append(str);
            sb2.append(", logo=");
            y.a.i(sb2, str2, ", cmd=", str3, ", tvGenreId=");
            return a0.c.p(sb2, str4, ")");
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\u0011\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\rJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\rJ8\u0010\u0013\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u000f\u0010\r¨\u0006\u001b"}, d2 = {"Lcom/arflix/tv/data/api/StalkerApi$StalkerChannelData;", "", "data", "", "Lcom/arflix/tv/data/api/StalkerApi$StalkerChannel;", "totalItems", "", "maxPageItems", "<init>", "(Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getData", "()Ljava/util/List;", "getTotalItems", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMaxPageItems", "component1", "component2", "component3", "copy", "(Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/arflix/tv/data/api/StalkerApi$StalkerChannelData;", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class StalkerChannelData {
        public static final int $stable = 0;
        private final List<StalkerChannel> data;

        @SerializedName("max_page_items")
        private final Integer maxPageItems;

        @SerializedName("total_items")
        private final Integer totalItems;

        public StalkerChannelData(List<StalkerChannel> list, Integer num, Integer num2) {
            this.data = list;
            this.totalItems = num;
            this.maxPageItems = num2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ StalkerChannelData copy$default(StalkerChannelData stalkerChannelData, List list, Integer num, Integer num2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                list = stalkerChannelData.data;
            }
            if ((i10 & 2) != 0) {
                num = stalkerChannelData.totalItems;
            }
            if ((i10 & 4) != 0) {
                num2 = stalkerChannelData.maxPageItems;
            }
            return stalkerChannelData.copy(list, num, num2);
        }

        public final List<StalkerChannel> component1() {
            return this.data;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getTotalItems() {
            return this.totalItems;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getMaxPageItems() {
            return this.maxPageItems;
        }

        public final StalkerChannelData copy(List<StalkerChannel> data, Integer totalItems, Integer maxPageItems) {
            return new StalkerChannelData(data, totalItems, maxPageItems);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StalkerChannelData)) {
                return false;
            }
            StalkerChannelData stalkerChannelData = (StalkerChannelData) other;
            return p.a(this.data, stalkerChannelData.data) && p.a(this.totalItems, stalkerChannelData.totalItems) && p.a(this.maxPageItems, stalkerChannelData.maxPageItems);
        }

        public final List<StalkerChannel> getData() {
            return this.data;
        }

        public final Integer getMaxPageItems() {
            return this.maxPageItems;
        }

        public final Integer getTotalItems() {
            return this.totalItems;
        }

        public int hashCode() {
            List<StalkerChannel> list = this.data;
            int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
            Integer num = this.totalItems;
            int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.maxPageItems;
            return iHashCode2 + (num2 != null ? num2.hashCode() : 0);
        }

        public String toString() {
            return "StalkerChannelData(data=" + this.data + ", totalItems=" + this.totalItems + ", maxPageItems=" + this.maxPageItems + ")";
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/arflix/tv/data/api/StalkerApi$StalkerChannelResponse;", "", "js", "Lcom/arflix/tv/data/api/StalkerApi$StalkerChannelData;", "<init>", "(Lcom/arflix/tv/data/api/StalkerApi$StalkerChannelData;)V", "getJs", "()Lcom/arflix/tv/data/api/StalkerApi$StalkerChannelData;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class StalkerChannelResponse {
        public static final int $stable = 0;
        private final StalkerChannelData js;

        public StalkerChannelResponse(StalkerChannelData stalkerChannelData) {
            this.js = stalkerChannelData;
        }

        public static /* synthetic */ StalkerChannelResponse copy$default(StalkerChannelResponse stalkerChannelResponse, StalkerChannelData stalkerChannelData, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                stalkerChannelData = stalkerChannelResponse.js;
            }
            return stalkerChannelResponse.copy(stalkerChannelData);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final StalkerChannelData getJs() {
            return this.js;
        }

        public final StalkerChannelResponse copy(StalkerChannelData js) {
            return new StalkerChannelResponse(js);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof StalkerChannelResponse) && p.a(this.js, ((StalkerChannelResponse) other).js);
        }

        public final StalkerChannelData getJs() {
            return this.js;
        }

        public int hashCode() {
            StalkerChannelData stalkerChannelData = this.js;
            if (stalkerChannelData == null) {
                return 0;
            }
            return stalkerChannelData.hashCode();
        }

        public String toString() {
            return "StalkerChannelResponse(js=" + this.js + ")";
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/arflix/tv/data/api/StalkerApi$StalkerGenre;", "", TtmlNode.ATTR_ID, "", LinkHeader.Parameters.Title, "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getTitle", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class StalkerGenre {
        public static final int $stable = 0;
        private final String id;
        private final String title;

        public StalkerGenre(String str, String str2) {
            this.id = str;
            this.title = str2;
        }

        public static /* synthetic */ StalkerGenre copy$default(StalkerGenre stalkerGenre, String str, String str2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = stalkerGenre.id;
            }
            if ((i10 & 2) != 0) {
                str2 = stalkerGenre.title;
            }
            return stalkerGenre.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        public final StalkerGenre copy(String id, String title) {
            return new StalkerGenre(id, title);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StalkerGenre)) {
                return false;
            }
            StalkerGenre stalkerGenre = (StalkerGenre) other;
            return p.a(this.id, stalkerGenre.id) && p.a(this.title, stalkerGenre.title);
        }

        public final String getId() {
            return this.id;
        }

        public final String getTitle() {
            return this.title;
        }

        public int hashCode() {
            String str = this.id;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.title;
            return iHashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return a2.m("StalkerGenre(id=", this.id, ", title=", this.title, ")");
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u001b\u0010\n\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/arflix/tv/data/api/StalkerApi$StalkerGenreResponse;", "", "js", "", "Lcom/arflix/tv/data/api/StalkerApi$StalkerGenre;", "<init>", "(Ljava/util/List;)V", "getJs", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class StalkerGenreResponse {
        public static final int $stable = 0;
        private final List<StalkerGenre> js;

        public StalkerGenreResponse(List<StalkerGenre> list) {
            this.js = list;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ StalkerGenreResponse copy$default(StalkerGenreResponse stalkerGenreResponse, List list, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                list = stalkerGenreResponse.js;
            }
            return stalkerGenreResponse.copy(list);
        }

        public final List<StalkerGenre> component1() {
            return this.js;
        }

        public final StalkerGenreResponse copy(List<StalkerGenre> js) {
            return new StalkerGenreResponse(js);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof StalkerGenreResponse) && p.a(this.js, ((StalkerGenreResponse) other).js);
        }

        public final List<StalkerGenre> getJs() {
            return this.js;
        }

        public int hashCode() {
            List<StalkerGenre> list = this.js;
            if (list == null) {
                return 0;
            }
            return list.hashCode();
        }

        public String toString() {
            return "StalkerGenreResponse(js=" + this.js + ")";
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/arflix/tv/data/api/StalkerApi$StalkerHandshakeResponse;", "", "js", "Lcom/arflix/tv/data/api/StalkerApi$StalkerToken;", "<init>", "(Lcom/arflix/tv/data/api/StalkerApi$StalkerToken;)V", "getJs", "()Lcom/arflix/tv/data/api/StalkerApi$StalkerToken;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class StalkerHandshakeResponse {
        public static final int $stable = 0;
        private final StalkerToken js;

        public StalkerHandshakeResponse(StalkerToken stalkerToken) {
            this.js = stalkerToken;
        }

        public static /* synthetic */ StalkerHandshakeResponse copy$default(StalkerHandshakeResponse stalkerHandshakeResponse, StalkerToken stalkerToken, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                stalkerToken = stalkerHandshakeResponse.js;
            }
            return stalkerHandshakeResponse.copy(stalkerToken);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final StalkerToken getJs() {
            return this.js;
        }

        public final StalkerHandshakeResponse copy(StalkerToken js) {
            return new StalkerHandshakeResponse(js);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof StalkerHandshakeResponse) && p.a(this.js, ((StalkerHandshakeResponse) other).js);
        }

        public final StalkerToken getJs() {
            return this.js;
        }

        public int hashCode() {
            StalkerToken stalkerToken = this.js;
            if (stalkerToken == null) {
                return 0;
            }
            return stalkerToken.hashCode();
        }

        public String toString() {
            return "StalkerHandshakeResponse(js=" + this.js + ")";
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/arflix/tv/data/api/StalkerApi$StalkerLink;", "", "cmd", "", "<init>", "(Ljava/lang/String;)V", "getCmd", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class StalkerLink {
        public static final int $stable = 0;
        private final String cmd;

        public StalkerLink(String str) {
            this.cmd = str;
        }

        public static /* synthetic */ StalkerLink copy$default(StalkerLink stalkerLink, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = stalkerLink.cmd;
            }
            return stalkerLink.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getCmd() {
            return this.cmd;
        }

        public final StalkerLink copy(String cmd) {
            return new StalkerLink(cmd);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof StalkerLink) && p.a(this.cmd, ((StalkerLink) other).cmd);
        }

        public final String getCmd() {
            return this.cmd;
        }

        public int hashCode() {
            String str = this.cmd;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        public String toString() {
            return a0.c.l("StalkerLink(cmd=", this.cmd, ")");
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/arflix/tv/data/api/StalkerApi$StalkerLinkResponse;", "", "js", "Lcom/arflix/tv/data/api/StalkerApi$StalkerLink;", "<init>", "(Lcom/arflix/tv/data/api/StalkerApi$StalkerLink;)V", "getJs", "()Lcom/arflix/tv/data/api/StalkerApi$StalkerLink;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class StalkerLinkResponse {
        public static final int $stable = 0;
        private final StalkerLink js;

        public StalkerLinkResponse(StalkerLink stalkerLink) {
            this.js = stalkerLink;
        }

        public static /* synthetic */ StalkerLinkResponse copy$default(StalkerLinkResponse stalkerLinkResponse, StalkerLink stalkerLink, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                stalkerLink = stalkerLinkResponse.js;
            }
            return stalkerLinkResponse.copy(stalkerLink);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final StalkerLink getJs() {
            return this.js;
        }

        public final StalkerLinkResponse copy(StalkerLink js) {
            return new StalkerLinkResponse(js);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof StalkerLinkResponse) && p.a(this.js, ((StalkerLinkResponse) other).js);
        }

        public final StalkerLink getJs() {
            return this.js;
        }

        public int hashCode() {
            StalkerLink stalkerLink = this.js;
            if (stalkerLink == null) {
                return 0;
            }
            return stalkerLink.hashCode();
        }

        public String toString() {
            return "StalkerLinkResponse(js=" + this.js + ")";
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/arflix/tv/data/api/StalkerApi$StalkerToken;", "", "token", "", "<init>", "(Ljava/lang/String;)V", "getToken", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class StalkerToken {
        public static final int $stable = 0;
        private final String token;

        public StalkerToken(String str) {
            this.token = str;
        }

        public static /* synthetic */ StalkerToken copy$default(StalkerToken stalkerToken, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = stalkerToken.token;
            }
            return stalkerToken.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getToken() {
            return this.token;
        }

        public final StalkerToken copy(String token) {
            return new StalkerToken(token);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof StalkerToken) && p.a(this.token, ((StalkerToken) other).token);
        }

        public final String getToken() {
            return this.token;
        }

        public int hashCode() {
            String str = this.token;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        public String toString() {
            return a0.c.l("StalkerToken(token=", this.token, ")");
        }
    }

    public StalkerApi(String str, String str2) {
        this.portalUrl = str;
        this.macAddress = str2;
        g0 g0Var = new g0();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        g0Var.b(15L, timeUnit);
        g0Var.e(15L, timeUnit);
        this.client = new h0(g0Var);
        this.gson = new Gson();
        this.token = "";
        this.serialNumber = "";
    }

    private final String doGet(String url) {
        j0 j0Var = new j0();
        j0Var.i(url);
        for (Map.Entry<String, String> entry : getBaseHeaders().entrySet()) {
            j0Var.a(entry.getKey(), entry.getValue());
        }
        s0 s0Var = this.client.a(j0Var.b()).d().f15786q;
        return s0Var != null ? s0Var.x() : "";
    }

    private final Map<String, String> getBaseHeaders() {
        return kotlin.collections.h0.t0(new x("User-Agent", "Mozilla/5.0 (QtEmbedded; U; Linux; C) AppleWebKit/533.3 (KHTML, like Gecko) MAG200 stbapp ver: 2 rev: 250 Safari/533.3"), new x("Cookie", a0.c.l("mac=", this.macAddress, "; stb_lang=en; timezone=Europe/London")), new x("X-User-Agent", "Model: MAG250; Link: WiFi"), new x("Authorization", androidx.compose.material3.d.C("Bearer ", this.token)));
    }

    public final Object getChannels(d7.d<? super List<IptvChannel>> dVar) {
        Map mapA0;
        StalkerChannelData js;
        List<StalkerChannel> data;
        Integer maxPageItems;
        Integer totalItems;
        String str;
        String strValueOf;
        List<StalkerGenre> js2;
        x xVar;
        ArrayList arrayList = new ArrayList();
        try {
            StalkerGenreResponse stalkerGenreResponse = (StalkerGenreResponse) this.gson.fromJson(doGet(this.portalUrl + "/server/load.php?type=itv&action=get_genres&JsHttpRequest=1-xml"), StalkerGenreResponse.class);
            if (stalkerGenreResponse == null || (js2 = stalkerGenreResponse.getJs()) == null) {
                mapA0 = a0.f19683i;
            } else {
                ArrayList arrayList2 = new ArrayList();
                for (StalkerGenre stalkerGenre : js2) {
                    String id = stalkerGenre.getId();
                    if (id != null) {
                        String title = stalkerGenre.getTitle();
                        if (title == null) {
                            title = "Unknown";
                        }
                        xVar = new x(id, title);
                    } else {
                        xVar = null;
                    }
                    if (xVar != null) {
                        arrayList2.add(xVar);
                    }
                }
                mapA0 = kotlin.collections.h0.A0(arrayList2);
            }
            boolean z = true;
            int i10 = 1;
            while (z) {
                StalkerChannelResponse stalkerChannelResponse = (StalkerChannelResponse) this.gson.fromJson(doGet(this.portalUrl + "/server/load.php?type=itv&action=get_all_channels&p=" + i10 + "&JsHttpRequest=1-xml"), StalkerChannelResponse.class);
                if (stalkerChannelResponse == null || (js = stalkerChannelResponse.getJs()) == null || (data = js.getData()) == null) {
                    break;
                }
                for (StalkerChannel stalkerChannel : data) {
                    String cmd = stalkerChannel.getCmd();
                    if (cmd != null) {
                        String tvGenreId = stalkerChannel.getTvGenreId();
                        if (tvGenreId == null || (str = (String) mapA0.get(tvGenreId)) == null) {
                            str = "Uncategorized";
                        }
                        String str2 = str;
                        Integer id2 = stalkerChannel.getId();
                        if (id2 != null && (strValueOf = String.valueOf(id2.intValue())) != null) {
                            String name = stalkerChannel.getName();
                            arrayList.add(new IptvChannel(strValueOf, name == null ? "Unknown" : name, cmd, str2, stalkerChannel.getLogo(), null, null, null, 0, null, null, null, null, null, null, null, null, null, null, 524256, null));
                        }
                    }
                }
                StalkerChannelData js3 = stalkerChannelResponse.getJs();
                int iIntValue = (js3 == null || (totalItems = js3.getTotalItems()) == null) ? 0 : totalItems.intValue();
                StalkerChannelData js4 = stalkerChannelResponse.getJs();
                z = ((js4 == null || (maxPageItems = js4.getMaxPageItems()) == null) ? 20 : maxPageItems.intValue()) * i10 < iIntValue;
                i10++;
            }
            return arrayList;
        } catch (Exception e5) {
            if (e5 instanceof CancellationException) {
                throw e5;
            }
            a2.v("[Stalker] Get channels failed: ", e5.getMessage(), System.err);
            return arrayList;
        }
    }

    public final Object getProfile(d7.d<? super Boolean> dVar) {
        boolean zT = false;
        try {
            zT = o.T(doGet(this.portalUrl + "/server/load.php?type=stb&action=get_profile&JsHttpRequest=1-xml"), "\"id\"", false);
        } catch (Exception unused) {
        }
        return Boolean.valueOf(zT);
    }

    public final Object handshake(d7.d<? super Boolean> dVar) {
        boolean z;
        StalkerHandshakeResponse stalkerHandshakeResponse;
        StalkerToken js;
        String token;
        try {
            stalkerHandshakeResponse = (StalkerHandshakeResponse) this.gson.fromJson(doGet(this.portalUrl + "/server/load.php?type=stb&action=handshake&token=&JsHttpRequest=1-xml"), StalkerHandshakeResponse.class);
        } catch (Exception e5) {
            if (e5 instanceof CancellationException) {
                throw e5;
            }
            a2.v("[Stalker] Handshake failed: ", e5.getMessage(), System.err);
            z = false;
        }
        if (stalkerHandshakeResponse != null && (js = stalkerHandshakeResponse.getJs()) != null && (token = js.getToken()) != null) {
            this.token = token;
            z = true;
            return Boolean.valueOf(z);
        }
        return Boolean.FALSE;
    }

    public final Object resolveStreamUrl(String str, d7.d<? super String> dVar) {
        StalkerLink js;
        String cmd;
        try {
            String strEncode = URLEncoder.encode(str, "UTF-8");
            StalkerLinkResponse stalkerLinkResponse = (StalkerLinkResponse) this.gson.fromJson(doGet(this.portalUrl + "/server/load.php?type=itv&action=create_link&cmd=" + strEncode + "&forced_storage=undefined&disable_ad=0&JsHttpRequest=1-xml"), StalkerLinkResponse.class);
            if (stalkerLinkResponse == null || (js = stalkerLinkResponse.getJs()) == null || (cmd = js.getCmd()) == null) {
                return null;
            }
            return o.L0(u.O(cmd, "ffmpeg ", "", false)).toString();
        } catch (Exception e5) {
            if (e5 instanceof CancellationException) {
                throw e5;
            }
            a2.v("[Stalker] Resolve stream failed: ", e5.getMessage(), System.err);
            return null;
        }
    }
}
