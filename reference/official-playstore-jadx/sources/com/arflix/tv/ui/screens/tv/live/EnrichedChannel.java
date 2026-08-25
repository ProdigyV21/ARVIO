package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.ui.graphics.Color;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.arflix.tv.data.model.IptvChannel;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b7\b\u0087\b\u0018\u00002\u00020\u0001BQ\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\u0006\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\r\u0012\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0017J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\bHÆ\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\nHÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u0019J\u0010\u0010!\u001a\u00020\rHÆ\u0003¢\u0006\u0004\b\u001f\u0010 J\u0010\u0010#\u001a\u00020\rHÆ\u0003¢\u0006\u0004\b\"\u0010 J\u0010\u0010$\u001a\u00020\u0010HÆ\u0003¢\u0006\u0004\b$\u0010%Jl\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0011\u001a\u00020\u0010HÆ\u0001¢\u0006\u0004\b&\u0010'J\u0010\u0010)\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b)\u0010\u0019J\u0010\u0010*\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b*\u0010\u0017J\u001a\u0010,\u001a\u00020\u00102\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b,\u0010-R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010.\u001a\u0004\b/\u0010\u0015R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u00100\u001a\u0004\b1\u0010\u0017R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u00102\u001a\u0004\b3\u0010\u0019R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u00104\u001a\u0004\b5\u0010\u001bR\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u00106\u001a\u0004\b7\u0010\u001dR\u0017\u0010\f\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\f\u00102\u001a\u0004\b8\u0010\u0019R\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u00109\u001a\u0004\b:\u0010 R\u0017\u0010\u000f\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000f\u00109\u001a\u0004\b;\u0010 R\u0017\u0010\u0011\u001a\u00020\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010<\u001a\u0004\b\u0011\u0010%R\u0011\u0010>\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b=\u0010\u0019R\u0011\u0010@\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b?\u0010\u0019R\u0011\u0010B\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\bA\u0010\u0019R\u0013\u0010D\u001a\u0004\u0018\u00010\u00068F¢\u0006\u0006\u001a\u0004\bC\u0010\u0019R\u0011\u0010F\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\bE\u0010\u0017¨\u0006G"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;", "", "Lcom/arflix/tv/data/model/IptvChannel;", "source", "", "number", "", "country", "Lcom/arflix/tv/ui/screens/tv/live/Genre;", "genre", "Lcom/arflix/tv/ui/screens/tv/live/Quality;", "quality", "lang", "Landroidx/compose/ui/graphics/Color;", "brandBg", "brandFg", "", "isAdult", "<init>", "(Lcom/arflix/tv/data/model/IptvChannel;ILjava/lang/String;Lcom/arflix/tv/ui/screens/tv/live/Genre;Lcom/arflix/tv/ui/screens/tv/live/Quality;Ljava/lang/String;JJZLkotlin/jvm/internal/h;)V", "component1", "()Lcom/arflix/tv/data/model/IptvChannel;", "component2", "()I", "component3", "()Ljava/lang/String;", "component4", "()Lcom/arflix/tv/ui/screens/tv/live/Genre;", "component5", "()Lcom/arflix/tv/ui/screens/tv/live/Quality;", "component6", "component7-0d7_KjU", "()J", "component7", "component8-0d7_KjU", "component8", "component9", "()Z", "copy-XfLwLrM", "(Lcom/arflix/tv/data/model/IptvChannel;ILjava/lang/String;Lcom/arflix/tv/ui/screens/tv/live/Genre;Lcom/arflix/tv/ui/screens/tv/live/Quality;Ljava/lang/String;JJZ)Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;", "copy", "toString", "hashCode", "other", "equals", "(Ljava/lang/Object;)Z", "Lcom/arflix/tv/data/model/IptvChannel;", "getSource", "I", "getNumber", "Ljava/lang/String;", "getCountry", "Lcom/arflix/tv/ui/screens/tv/live/Genre;", "getGenre", "Lcom/arflix/tv/ui/screens/tv/live/Quality;", "getQuality", "getLang", "J", "getBrandBg-0d7_KjU", "getBrandFg-0d7_KjU", "Z", "getId", TtmlNode.ATTR_ID, "getName", ContentDisposition.Parameters.Name, "getStreamUrl", "streamUrl", "getLogo", "logo", "getCatchupDays", "catchupDays", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class EnrichedChannel {
    public static final int $stable = 0;
    private final long brandBg;
    private final long brandFg;
    private final String country;
    private final Genre genre;
    private final boolean isAdult;
    private final String lang;
    private final int number;
    private final Quality quality;
    private final IptvChannel source;

    public /* synthetic */ EnrichedChannel(IptvChannel iptvChannel, int i10, String str, Genre genre, Quality quality, String str2, long j10, long j11, boolean z, kotlin.jvm.internal.h hVar) {
        this(iptvChannel, i10, str, genre, quality, str2, j10, j11, z);
    }

    /* JADX INFO: renamed from: copy-XfLwLrM$default, reason: not valid java name */
    public static /* synthetic */ EnrichedChannel m6377copyXfLwLrM$default(EnrichedChannel enrichedChannel, IptvChannel iptvChannel, int i10, String str, Genre genre, Quality quality, String str2, long j10, long j11, boolean z, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            iptvChannel = enrichedChannel.source;
        }
        if ((i11 & 2) != 0) {
            i10 = enrichedChannel.number;
        }
        if ((i11 & 4) != 0) {
            str = enrichedChannel.country;
        }
        if ((i11 & 8) != 0) {
            genre = enrichedChannel.genre;
        }
        if ((i11 & 16) != 0) {
            quality = enrichedChannel.quality;
        }
        if ((i11 & 32) != 0) {
            str2 = enrichedChannel.lang;
        }
        if ((i11 & 64) != 0) {
            j10 = enrichedChannel.brandBg;
        }
        if ((i11 & 128) != 0) {
            j11 = enrichedChannel.brandFg;
        }
        if ((i11 & 256) != 0) {
            z = enrichedChannel.isAdult;
        }
        boolean z5 = z;
        long j12 = j11;
        long j13 = j10;
        Quality quality2 = quality;
        String str3 = str2;
        return enrichedChannel.m6380copyXfLwLrM(iptvChannel, i10, str, genre, quality2, str3, j13, j12, z5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final IptvChannel getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getNumber() {
        return this.number;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getCountry() {
        return this.country;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Genre getGenre() {
        return this.genre;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Quality getQuality() {
        return this.quality;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getLang() {
        return this.lang;
    }

    /* JADX INFO: renamed from: component7-0d7_KjU, reason: not valid java name and from getter */
    public final long getBrandBg() {
        return this.brandBg;
    }

    /* JADX INFO: renamed from: component8-0d7_KjU, reason: not valid java name and from getter */
    public final long getBrandFg() {
        return this.brandFg;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final boolean getIsAdult() {
        return this.isAdult;
    }

    /* JADX INFO: renamed from: copy-XfLwLrM, reason: not valid java name */
    public final EnrichedChannel m6380copyXfLwLrM(IptvChannel source, int number, String country, Genre genre, Quality quality, String lang, long brandBg, long brandFg, boolean isAdult) {
        return new EnrichedChannel(source, number, country, genre, quality, lang, brandBg, brandFg, isAdult, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EnrichedChannel)) {
            return false;
        }
        EnrichedChannel enrichedChannel = (EnrichedChannel) other;
        return kotlin.jvm.internal.p.a(this.source, enrichedChannel.source) && this.number == enrichedChannel.number && kotlin.jvm.internal.p.a(this.country, enrichedChannel.country) && this.genre == enrichedChannel.genre && this.quality == enrichedChannel.quality && kotlin.jvm.internal.p.a(this.lang, enrichedChannel.lang) && Color.m3473equalsimpl0(this.brandBg, enrichedChannel.brandBg) && Color.m3473equalsimpl0(this.brandFg, enrichedChannel.brandFg) && this.isAdult == enrichedChannel.isAdult;
    }

    /* JADX INFO: renamed from: getBrandBg-0d7_KjU, reason: not valid java name */
    public final long m6381getBrandBg0d7_KjU() {
        return this.brandBg;
    }

    /* JADX INFO: renamed from: getBrandFg-0d7_KjU, reason: not valid java name */
    public final long m6382getBrandFg0d7_KjU() {
        return this.brandFg;
    }

    public final int getCatchupDays() {
        return this.source.getCatchupDays();
    }

    public final String getCountry() {
        return this.country;
    }

    public final Genre getGenre() {
        return this.genre;
    }

    public final String getId() {
        return this.source.getId();
    }

    public final String getLang() {
        return this.lang;
    }

    public final String getLogo() {
        return this.source.getLogo();
    }

    public final String getName() {
        return this.source.getName();
    }

    public final int getNumber() {
        return this.number;
    }

    public final Quality getQuality() {
        return this.quality;
    }

    public final IptvChannel getSource() {
        return this.source;
    }

    public final String getStreamUrl() {
        return this.source.getStreamUrl();
    }

    public int hashCode() {
        int iHashCode = ((this.source.hashCode() * 31) + this.number) * 31;
        String str = this.country;
        return androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.c((this.quality.hashCode() + ((this.genre.hashCode() + ((iHashCode + (str == null ? 0 : str.hashCode())) * 31)) * 31)) * 31, 31, this.lang), 31, this.brandBg), 31, this.brandFg) + (this.isAdult ? 1231 : 1237);
    }

    public final boolean isAdult() {
        return this.isAdult;
    }

    public String toString() {
        IptvChannel iptvChannel = this.source;
        int i10 = this.number;
        String str = this.country;
        Genre genre = this.genre;
        Quality quality = this.quality;
        String str2 = this.lang;
        String strM3480toStringimpl = Color.m3480toStringimpl(this.brandBg);
        String strM3480toStringimpl2 = Color.m3480toStringimpl(this.brandFg);
        boolean z = this.isAdult;
        StringBuilder sb2 = new StringBuilder("EnrichedChannel(source=");
        sb2.append(iptvChannel);
        sb2.append(", number=");
        sb2.append(i10);
        sb2.append(", country=");
        sb2.append(str);
        sb2.append(", genre=");
        sb2.append(genre);
        sb2.append(", quality=");
        sb2.append(quality);
        sb2.append(", lang=");
        sb2.append(str2);
        sb2.append(", brandBg=");
        y.a.i(sb2, strM3480toStringimpl, ", brandFg=", strM3480toStringimpl2, ", isAdult=");
        return a0.c.m(")", z, sb2);
    }

    private EnrichedChannel(IptvChannel iptvChannel, int i10, String str, Genre genre, Quality quality, String str2, long j10, long j11, boolean z) {
        this.source = iptvChannel;
        this.number = i10;
        this.country = str;
        this.genre = genre;
        this.quality = quality;
        this.lang = str2;
        this.brandBg = j10;
        this.brandFg = j11;
        this.isAdult = z;
    }
}
