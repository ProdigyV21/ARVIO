package com.arflix.tv.ui.components;

import androidx.compose.ui.graphics.Color;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.session.MediaUtils;
import com.arflix.tv.data.model.StreamSource;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\bL\b\u0083\b\u0018\u00002\u00020\u0001Bç\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\b\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u000e\u001a\u00020\u000b\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004\u0012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\b\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\u0006\u0010\u0018\u001a\u00020\u0017\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\u0006\u0010\u001b\u001a\u00020\u0019\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\"\u0010#J\u0010\u0010$\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b$\u0010%J\u0010\u0010&\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b&\u0010%J\u0010\u0010'\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b'\u0010%J\u0016\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00040\bHÆ\u0003¢\u0006\u0004\b(\u0010)J\u0010\u0010*\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b*\u0010%J\u0010\u0010+\u001a\u00020\u000bHÆ\u0003¢\u0006\u0004\b+\u0010,J\u0012\u0010-\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b-\u0010%J\u0010\u0010.\u001a\u00020\u000bHÆ\u0003¢\u0006\u0004\b.\u0010,J\u0012\u0010/\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b/\u0010%J\u0012\u00100\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b0\u0010%J\u0012\u00101\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b1\u0010%J\u0012\u00102\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b2\u0010%J\u0012\u00103\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b3\u0010%J\u0016\u00104\u001a\b\u0012\u0004\u0012\u00020\u00040\bHÆ\u0003¢\u0006\u0004\b4\u0010)J\u0010\u00107\u001a\u00020\u0015HÆ\u0003¢\u0006\u0004\b5\u00106J\u0010\u00108\u001a\u00020\u0017HÆ\u0003¢\u0006\u0004\b8\u00106J\u0010\u00109\u001a\u00020\u0019HÆ\u0003¢\u0006\u0004\b9\u0010:J\u0010\u0010;\u001a\u00020\u0019HÆ\u0003¢\u0006\u0004\b;\u0010:J\u0012\u0010<\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b<\u0010%J\u0012\u0010=\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b=\u0010%J\u0012\u0010>\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b>\u0010%J\u0012\u0010?\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b?\u0010%J\u0096\u0002\u0010B\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u000b2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00042\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001b\u001a\u00020\u00192\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0004\b@\u0010AJ\u0010\u0010C\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\bC\u0010%J\u0010\u0010D\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\bD\u0010,J\u001a\u0010F\u001a\u00020\u00192\b\u0010E\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\bF\u0010GR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010H\u001a\u0004\bI\u0010#R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010J\u001a\u0004\bK\u0010%R\u0017\u0010\u0006\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010J\u001a\u0004\bL\u0010%R\u0017\u0010\u0007\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0007\u0010J\u001a\u0004\bM\u0010%R\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\b8\u0006¢\u0006\f\n\u0004\b\t\u0010N\u001a\u0004\bO\u0010)R\u0017\u0010\n\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\n\u0010J\u001a\u0004\bP\u0010%R\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010Q\u001a\u0004\bR\u0010,R\u0019\u0010\r\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010J\u001a\u0004\bS\u0010%R\u0017\u0010\u000e\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\u000e\u0010Q\u001a\u0004\bT\u0010,R\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u000f\u0010J\u001a\u0004\bU\u0010%R\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0010\u0010J\u001a\u0004\bV\u0010%R\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0011\u0010J\u001a\u0004\bW\u0010%R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0012\u0010J\u001a\u0004\bX\u0010%R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0013\u0010J\u001a\u0004\bY\u0010%R\u001d\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\b8\u0006¢\u0006\f\n\u0004\b\u0014\u0010N\u001a\u0004\bZ\u0010)R\u0017\u0010\u0016\u001a\u00020\u00158\u0006¢\u0006\f\n\u0004\b\u0016\u0010[\u001a\u0004\b\\\u00106R\u0017\u0010\u0018\u001a\u00020\u00178\u0006¢\u0006\f\n\u0004\b\u0018\u0010[\u001a\u0004\b]\u00106R\u0017\u0010\u001a\u001a\u00020\u00198\u0006¢\u0006\f\n\u0004\b\u001a\u0010^\u001a\u0004\b_\u0010:R\u0017\u0010\u001b\u001a\u00020\u00198\u0006¢\u0006\f\n\u0004\b\u001b\u0010^\u001a\u0004\b`\u0010:R\u0019\u0010\u001c\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u001c\u0010J\u001a\u0004\ba\u0010%R\u0019\u0010\u001d\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u001d\u0010J\u001a\u0004\bb\u0010%R\u0019\u0010\u001e\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u001e\u0010J\u001a\u0004\bc\u0010%R\u0019\u0010\u001f\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u001f\u0010J\u001a\u0004\bd\u0010%¨\u0006e"}, d2 = {"Lcom/arflix/tv/ui/components/SourcePresentation;", "", "Lcom/arflix/tv/data/model/StreamSource;", "stream", "", LinkHeader.Parameters.Title, "rawTitle", "addonLabel", "", "attributionLabels", "resolutionLabel", "", "resolutionScore", "releaseLabel", "releaseScore", "codecLabel", "audioLabel", "transportLabel", "multiSourceLabel", "languageLabel", "chips", "Landroidx/compose/ui/graphics/Color;", "qualityColor", "", "sizeBytes", "", "sortCached", "sortDirect", MediaTrack.ROLE_DESCRIPTION, "bitrateLabel", "editionLabel", "upstreamLabel", "<init>", "(Lcom/arflix/tv/data/model/StreamSource;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;JJZZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/internal/h;)V", "component1", "()Lcom/arflix/tv/data/model/StreamSource;", "component2", "()Ljava/lang/String;", "component3", "component4", "component5", "()Ljava/util/List;", "component6", "component7", "()I", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16-0d7_KjU", "()J", "component16", "component17", "component18", "()Z", "component19", "component20", "component21", "component22", "component23", "copy-7yi0FK8", "(Lcom/arflix/tv/data/model/StreamSource;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;JJZZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/ui/components/SourcePresentation;", "copy", "toString", "hashCode", "other", "equals", "(Ljava/lang/Object;)Z", "Lcom/arflix/tv/data/model/StreamSource;", "getStream", "Ljava/lang/String;", "getTitle", "getRawTitle", "getAddonLabel", "Ljava/util/List;", "getAttributionLabels", "getResolutionLabel", "I", "getResolutionScore", "getReleaseLabel", "getReleaseScore", "getCodecLabel", "getAudioLabel", "getTransportLabel", "getMultiSourceLabel", "getLanguageLabel", "getChips", "J", "getQualityColor-0d7_KjU", "getSizeBytes", "Z", "getSortCached", "getSortDirect", "getDescription", "getBitrateLabel", "getEditionLabel", "getUpstreamLabel", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class SourcePresentation {
    private final String addonLabel;
    private final List<String> attributionLabels;
    private final String audioLabel;
    private final String bitrateLabel;
    private final List<String> chips;
    private final String codecLabel;
    private final String description;
    private final String editionLabel;
    private final String languageLabel;
    private final String multiSourceLabel;
    private final long qualityColor;
    private final String rawTitle;
    private final String releaseLabel;
    private final int releaseScore;
    private final String resolutionLabel;
    private final int resolutionScore;
    private final long sizeBytes;
    private final boolean sortCached;
    private final boolean sortDirect;
    private final StreamSource stream;
    private final String title;
    private final String transportLabel;
    private final String upstreamLabel;

    public /* synthetic */ SourcePresentation(StreamSource streamSource, String str, String str2, String str3, List list, String str4, int i10, String str5, int i11, String str6, String str7, String str8, String str9, String str10, List list2, long j10, long j11, boolean z, boolean z5, String str11, String str12, String str13, String str14, kotlin.jvm.internal.h hVar) {
        this(streamSource, str, str2, str3, list, str4, i10, str5, i11, str6, str7, str8, str9, str10, list2, j10, j11, z, z5, str11, str12, str13, str14);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: copy-7yi0FK8$default, reason: not valid java name */
    public static /* synthetic */ SourcePresentation m6187copy7yi0FK8$default(SourcePresentation sourcePresentation, StreamSource streamSource, String str, String str2, String str3, List list, String str4, int i10, String str5, int i11, String str6, String str7, String str8, String str9, String str10, List list2, long j10, long j11, boolean z, boolean z5, String str11, String str12, String str13, String str14, int i12, Object obj) {
        String str15;
        String str16;
        StreamSource streamSource2 = (i12 & 1) != 0 ? sourcePresentation.stream : streamSource;
        String str17 = (i12 & 2) != 0 ? sourcePresentation.title : str;
        String str18 = (i12 & 4) != 0 ? sourcePresentation.rawTitle : str2;
        String str19 = (i12 & 8) != 0 ? sourcePresentation.addonLabel : str3;
        List list3 = (i12 & 16) != 0 ? sourcePresentation.attributionLabels : list;
        String str20 = (i12 & 32) != 0 ? sourcePresentation.resolutionLabel : str4;
        int i13 = (i12 & 64) != 0 ? sourcePresentation.resolutionScore : i10;
        String str21 = (i12 & 128) != 0 ? sourcePresentation.releaseLabel : str5;
        int i14 = (i12 & 256) != 0 ? sourcePresentation.releaseScore : i11;
        String str22 = (i12 & 512) != 0 ? sourcePresentation.codecLabel : str6;
        String str23 = (i12 & 1024) != 0 ? sourcePresentation.audioLabel : str7;
        String str24 = (i12 & 2048) != 0 ? sourcePresentation.transportLabel : str8;
        String str25 = (i12 & 4096) != 0 ? sourcePresentation.multiSourceLabel : str9;
        String str26 = (i12 & 8192) != 0 ? sourcePresentation.languageLabel : str10;
        StreamSource streamSource3 = streamSource2;
        List list4 = (i12 & 16384) != 0 ? sourcePresentation.chips : list2;
        long j12 = (i12 & 32768) != 0 ? sourcePresentation.qualityColor : j10;
        long j13 = (i12 & 65536) != 0 ? sourcePresentation.sizeBytes : j11;
        boolean z10 = (i12 & 131072) != 0 ? sourcePresentation.sortCached : z;
        boolean z11 = (i12 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? sourcePresentation.sortDirect : z5;
        boolean z12 = z10;
        String str27 = (i12 & 524288) != 0 ? sourcePresentation.description : str11;
        String str28 = (i12 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? sourcePresentation.bitrateLabel : str12;
        String str29 = (i12 & 2097152) != 0 ? sourcePresentation.editionLabel : str13;
        if ((i12 & 4194304) != 0) {
            str16 = str29;
            str15 = sourcePresentation.upstreamLabel;
        } else {
            str15 = str14;
            str16 = str29;
        }
        return sourcePresentation.m6189copy7yi0FK8(streamSource3, str17, str18, str19, list3, str20, i13, str21, i14, str22, str23, str24, str25, str26, list4, j12, j13, z12, z11, str27, str28, str16, str15);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final StreamSource getStream() {
        return this.stream;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getCodecLabel() {
        return this.codecLabel;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getAudioLabel() {
        return this.audioLabel;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getTransportLabel() {
        return this.transportLabel;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getMultiSourceLabel() {
        return this.multiSourceLabel;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getLanguageLabel() {
        return this.languageLabel;
    }

    public final List<String> component15() {
        return this.chips;
    }

    /* JADX INFO: renamed from: component16-0d7_KjU, reason: not valid java name and from getter */
    public final long getQualityColor() {
        return this.qualityColor;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final long getSizeBytes() {
        return this.sizeBytes;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final boolean getSortCached() {
        return this.sortCached;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final boolean getSortDirect() {
        return this.sortDirect;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final String getBitrateLabel() {
        return this.bitrateLabel;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final String getEditionLabel() {
        return this.editionLabel;
    }

    /* JADX INFO: renamed from: component23, reason: from getter */
    public final String getUpstreamLabel() {
        return this.upstreamLabel;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getRawTitle() {
        return this.rawTitle;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getAddonLabel() {
        return this.addonLabel;
    }

    public final List<String> component5() {
        return this.attributionLabels;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getResolutionLabel() {
        return this.resolutionLabel;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getResolutionScore() {
        return this.resolutionScore;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getReleaseLabel() {
        return this.releaseLabel;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getReleaseScore() {
        return this.releaseScore;
    }

    /* JADX INFO: renamed from: copy-7yi0FK8, reason: not valid java name */
    public final SourcePresentation m6189copy7yi0FK8(StreamSource stream, String title, String rawTitle, String addonLabel, List<String> attributionLabels, String resolutionLabel, int resolutionScore, String releaseLabel, int releaseScore, String codecLabel, String audioLabel, String transportLabel, String multiSourceLabel, String languageLabel, List<String> chips, long qualityColor, long sizeBytes, boolean sortCached, boolean sortDirect, String description, String bitrateLabel, String editionLabel, String upstreamLabel) {
        return new SourcePresentation(stream, title, rawTitle, addonLabel, attributionLabels, resolutionLabel, resolutionScore, releaseLabel, releaseScore, codecLabel, audioLabel, transportLabel, multiSourceLabel, languageLabel, chips, qualityColor, sizeBytes, sortCached, sortDirect, description, bitrateLabel, editionLabel, upstreamLabel, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SourcePresentation)) {
            return false;
        }
        SourcePresentation sourcePresentation = (SourcePresentation) other;
        return kotlin.jvm.internal.p.a(this.stream, sourcePresentation.stream) && kotlin.jvm.internal.p.a(this.title, sourcePresentation.title) && kotlin.jvm.internal.p.a(this.rawTitle, sourcePresentation.rawTitle) && kotlin.jvm.internal.p.a(this.addonLabel, sourcePresentation.addonLabel) && kotlin.jvm.internal.p.a(this.attributionLabels, sourcePresentation.attributionLabels) && kotlin.jvm.internal.p.a(this.resolutionLabel, sourcePresentation.resolutionLabel) && this.resolutionScore == sourcePresentation.resolutionScore && kotlin.jvm.internal.p.a(this.releaseLabel, sourcePresentation.releaseLabel) && this.releaseScore == sourcePresentation.releaseScore && kotlin.jvm.internal.p.a(this.codecLabel, sourcePresentation.codecLabel) && kotlin.jvm.internal.p.a(this.audioLabel, sourcePresentation.audioLabel) && kotlin.jvm.internal.p.a(this.transportLabel, sourcePresentation.transportLabel) && kotlin.jvm.internal.p.a(this.multiSourceLabel, sourcePresentation.multiSourceLabel) && kotlin.jvm.internal.p.a(this.languageLabel, sourcePresentation.languageLabel) && kotlin.jvm.internal.p.a(this.chips, sourcePresentation.chips) && Color.m3473equalsimpl0(this.qualityColor, sourcePresentation.qualityColor) && this.sizeBytes == sourcePresentation.sizeBytes && this.sortCached == sourcePresentation.sortCached && this.sortDirect == sourcePresentation.sortDirect && kotlin.jvm.internal.p.a(this.description, sourcePresentation.description) && kotlin.jvm.internal.p.a(this.bitrateLabel, sourcePresentation.bitrateLabel) && kotlin.jvm.internal.p.a(this.editionLabel, sourcePresentation.editionLabel) && kotlin.jvm.internal.p.a(this.upstreamLabel, sourcePresentation.upstreamLabel);
    }

    public final String getAddonLabel() {
        return this.addonLabel;
    }

    public final List<String> getAttributionLabels() {
        return this.attributionLabels;
    }

    public final String getAudioLabel() {
        return this.audioLabel;
    }

    public final String getBitrateLabel() {
        return this.bitrateLabel;
    }

    public final List<String> getChips() {
        return this.chips;
    }

    public final String getCodecLabel() {
        return this.codecLabel;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getEditionLabel() {
        return this.editionLabel;
    }

    public final String getLanguageLabel() {
        return this.languageLabel;
    }

    public final String getMultiSourceLabel() {
        return this.multiSourceLabel;
    }

    /* JADX INFO: renamed from: getQualityColor-0d7_KjU, reason: not valid java name */
    public final long m6190getQualityColor0d7_KjU() {
        return this.qualityColor;
    }

    public final String getRawTitle() {
        return this.rawTitle;
    }

    public final String getReleaseLabel() {
        return this.releaseLabel;
    }

    public final int getReleaseScore() {
        return this.releaseScore;
    }

    public final String getResolutionLabel() {
        return this.resolutionLabel;
    }

    public final int getResolutionScore() {
        return this.resolutionScore;
    }

    public final long getSizeBytes() {
        return this.sizeBytes;
    }

    public final boolean getSortCached() {
        return this.sortCached;
    }

    public final boolean getSortDirect() {
        return this.sortDirect;
    }

    public final StreamSource getStream() {
        return this.stream;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getTransportLabel() {
        return this.transportLabel;
    }

    public final String getUpstreamLabel() {
        return this.upstreamLabel;
    }

    public int hashCode() {
        int iC = (androidx.compose.foundation.c.c(androidx.compose.material3.d.e(this.attributionLabels, androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.stream.hashCode() * 31, 31, this.title), 31, this.rawTitle), 31, this.addonLabel), 31), 31, this.resolutionLabel) + this.resolutionScore) * 31;
        String str = this.releaseLabel;
        int iHashCode = (((iC + (str == null ? 0 : str.hashCode())) * 31) + this.releaseScore) * 31;
        String str2 = this.codecLabel;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.audioLabel;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.transportLabel;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.multiSourceLabel;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.languageLabel;
        int iA = androidx.compose.foundation.c.a(androidx.compose.material3.d.e(this.chips, (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31, 31), 31, this.qualityColor);
        long j10 = this.sizeBytes;
        int i10 = (((((iA + ((int) (j10 ^ (j10 >>> 32)))) * 31) + (this.sortCached ? 1231 : 1237)) * 31) + (this.sortDirect ? 1231 : 1237)) * 31;
        String str7 = this.description;
        int iHashCode6 = (i10 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.bitrateLabel;
        int iHashCode7 = (iHashCode6 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.editionLabel;
        int iHashCode8 = (iHashCode7 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.upstreamLabel;
        return iHashCode8 + (str10 != null ? str10.hashCode() : 0);
    }

    public String toString() {
        StreamSource streamSource = this.stream;
        String str = this.title;
        String str2 = this.rawTitle;
        String str3 = this.addonLabel;
        List<String> list = this.attributionLabels;
        String str4 = this.resolutionLabel;
        int i10 = this.resolutionScore;
        String str5 = this.releaseLabel;
        int i11 = this.releaseScore;
        String str6 = this.codecLabel;
        String str7 = this.audioLabel;
        String str8 = this.transportLabel;
        String str9 = this.multiSourceLabel;
        String str10 = this.languageLabel;
        List<String> list2 = this.chips;
        String strM3480toStringimpl = Color.m3480toStringimpl(this.qualityColor);
        long j10 = this.sizeBytes;
        boolean z = this.sortCached;
        boolean z5 = this.sortDirect;
        String str11 = this.description;
        String str12 = this.bitrateLabel;
        String str13 = this.editionLabel;
        String str14 = this.upstreamLabel;
        StringBuilder sb2 = new StringBuilder("SourcePresentation(stream=");
        sb2.append(streamSource);
        sb2.append(", title=");
        sb2.append(str);
        sb2.append(", rawTitle=");
        y.a.i(sb2, str2, ", addonLabel=", str3, ", attributionLabels=");
        sb2.append(list);
        sb2.append(", resolutionLabel=");
        sb2.append(str4);
        sb2.append(", resolutionScore=");
        sb2.append(i10);
        sb2.append(", releaseLabel=");
        sb2.append(str5);
        sb2.append(", releaseScore=");
        sb2.append(i11);
        sb2.append(", codecLabel=");
        sb2.append(str6);
        sb2.append(", audioLabel=");
        y.a.i(sb2, str7, ", transportLabel=", str8, ", multiSourceLabel=");
        y.a.i(sb2, str9, ", languageLabel=", str10, ", chips=");
        sb2.append(list2);
        sb2.append(", qualityColor=");
        sb2.append(strM3480toStringimpl);
        sb2.append(", sizeBytes=");
        sb2.append(j10);
        sb2.append(", sortCached=");
        sb2.append(z);
        sb2.append(", sortDirect=");
        sb2.append(z5);
        sb2.append(", description=");
        sb2.append(str11);
        y.a.i(sb2, ", bitrateLabel=", str12, ", editionLabel=", str13);
        return androidx.compose.material3.d.q(sb2, ", upstreamLabel=", str14, ")");
    }

    private SourcePresentation(StreamSource streamSource, String str, String str2, String str3, List<String> list, String str4, int i10, String str5, int i11, String str6, String str7, String str8, String str9, String str10, List<String> list2, long j10, long j11, boolean z, boolean z5, String str11, String str12, String str13, String str14) {
        this.stream = streamSource;
        this.title = str;
        this.rawTitle = str2;
        this.addonLabel = str3;
        this.attributionLabels = list;
        this.resolutionLabel = str4;
        this.resolutionScore = i10;
        this.releaseLabel = str5;
        this.releaseScore = i11;
        this.codecLabel = str6;
        this.audioLabel = str7;
        this.transportLabel = str8;
        this.multiSourceLabel = str9;
        this.languageLabel = str10;
        this.chips = list2;
        this.qualityColor = j10;
        this.sizeBytes = j11;
        this.sortCached = z;
        this.sortDirect = z5;
        this.description = str11;
        this.bitrateLabel = str12;
        this.editionLabel = str13;
        this.upstreamLabel = str14;
    }

    public /* synthetic */ SourcePresentation(StreamSource streamSource, String str, String str2, String str3, List list, String str4, int i10, String str5, int i11, String str6, String str7, String str8, String str9, String str10, List list2, long j10, long j11, boolean z, boolean z5, String str11, String str12, String str13, String str14, int i12, kotlin.jvm.internal.h hVar) {
        this(streamSource, str, str2, str3, list, str4, i10, str5, i11, str6, str7, str8, str9, str10, list2, j10, j11, z, z5, (i12 & 524288) != 0 ? null : str11, (i12 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? null : str12, (i12 & 2097152) != 0 ? null : str13, (i12 & 4194304) != 0 ? null : str14, null);
    }
}
