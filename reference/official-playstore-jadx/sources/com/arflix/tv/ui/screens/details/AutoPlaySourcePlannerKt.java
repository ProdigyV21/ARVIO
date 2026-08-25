package com.arflix.tv.ui.screens.details;

import com.arflix.tv.data.model.StreamBehaviorHints;
import com.arflix.tv.data.model.StreamSource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a'\u0010\b\u001a\u0004\u0018\u00010\u00002\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00000\u00052\u0006\u0010\u0007\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a\u0017\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\rH\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u0017\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u0017\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0014\u0010\u0013\u001a)\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0017\u001a\u00020\nH\u0000¢\u0006\u0004\b\u0018\u0010\u0019\"\u0014\u0010\u001a\u001a\u00020\n8\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u001a\u0010\u001b\"\u0014\u0010\u001c\u001a\u00020\n8\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u001c\u0010\u001b\"\u0014\u0010\u001d\u001a\u00020\n8\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u001d\u0010\u001b\"\u0014\u0010\u001e\u001a\u00020\u00028\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u001e\u0010\u001f\"\u0014\u0010!\u001a\u00020 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"\"\u0014\u0010#\u001a\u00020 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010\"¨\u0006$"}, d2 = {"Lcom/arflix/tv/data/model/StreamSource;", "stream", "", "qualityScoreForAutoPlay", "(Lcom/arflix/tv/data/model/StreamSource;)I", "", "streams", "minQualityScore", "bestAutoPlayStream", "(Ljava/util/List;I)Lcom/arflix/tv/data/model/StreamSource;", "", "autoPlaySizeBytes", "(Lcom/arflix/tv/data/model/StreamSource;)J", "", "value", "minQualityThreshold", "(Ljava/lang/String;)I", "", "isAutoPlayableStream", "(Lcom/arflix/tv/data/model/StreamSource;)Z", "isPendingDebridStream", "isLoadingStreams", "selectedStream", "elapsedMs", "shouldWaitForAutoPlaySources", "(ZLcom/arflix/tv/data/model/StreamSource;J)Z", "AUTOPLAY_MAX_WAIT_MS", "J", "AUTOPLAY_TOP_TIER_SETTLE_MS", "AUTOPLAY_SOURCE_RECHECK_MS", "TOP_TIER_QUALITY_SCORE", "I", "Lkotlin/text/m;", "fourKRegex", "Lkotlin/text/m;", "sizeRegex", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class AutoPlaySourcePlannerKt {
    public static final long AUTOPLAY_MAX_WAIT_MS = 2000;
    public static final long AUTOPLAY_SOURCE_RECHECK_MS = 120;
    public static final long AUTOPLAY_TOP_TIER_SETTLE_MS = 450;
    private static final int TOP_TIER_QUALITY_SCORE = 4;
    private static final kotlin.text.m fourKRegex = new kotlin.text.m("\\b4[kK]\\b");
    private static final kotlin.text.m sizeRegex = new kotlin.text.m("(?i)(\\d+(?:[\\.,]\\d+)?)\\s*(TB|GB|MB|KB|B|GiB|MiB|KiB)?");

    /* JADX WARN: Removed duplicated region for block: B:46:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00c3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final long autoPlaySizeBytes(com.arflix.tv.data.model.StreamSource r4) {
        /*
            Method dump skipped, instruction units count: 203
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.AutoPlaySourcePlannerKt.autoPlaySizeBytes(com.arflix.tv.data.model.StreamSource):long");
    }

    public static final StreamSource bestAutoPlayStream(List<StreamSource> list, int i10) {
        ga.i iVarI = ga.r.I(new ga.p(list, 3), new f1(i10, 1));
        final Comparator comparator = new Comparator() { // from class: com.arflix.tv.ui.screens.details.AutoPlaySourcePlannerKt$bestAutoPlayStream$$inlined$compareByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                return com.google.common.util.concurrent.r0.e(Integer.valueOf(AutoPlaySourcePlannerKt.qualityScoreForAutoPlay((StreamSource) t10)), Integer.valueOf(AutoPlaySourcePlannerKt.qualityScoreForAutoPlay((StreamSource) t2)));
            }
        };
        final Comparator comparator2 = new Comparator() { // from class: com.arflix.tv.ui.screens.details.AutoPlaySourcePlannerKt$bestAutoPlayStream$$inlined$thenByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                int iCompare = comparator.compare(t2, t10);
                return iCompare != 0 ? iCompare : com.google.common.util.concurrent.r0.e(Long.valueOf(AutoPlaySourcePlannerKt.autoPlaySizeBytes((StreamSource) t10)), Long.valueOf(AutoPlaySourcePlannerKt.autoPlaySizeBytes((StreamSource) t2)));
            }
        };
        final Comparator comparator3 = new Comparator() { // from class: com.arflix.tv.ui.screens.details.AutoPlaySourcePlannerKt$bestAutoPlayStream$$inlined$thenByDescending$2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                int iCompare = comparator2.compare(t2, t10);
                if (iCompare != 0) {
                    return iCompare;
                }
                StreamBehaviorHints behaviorHints = ((StreamSource) t10).getBehaviorHints();
                Integer num = (behaviorHints == null || !behaviorHints.getNotWebReady()) ? 1 : num;
                StreamBehaviorHints behaviorHints2 = ((StreamSource) t2).getBehaviorHints();
                return com.google.common.util.concurrent.r0.e(num, (behaviorHints2 == null || !behaviorHints2.getNotWebReady()) ? 1 : 0);
            }
        };
        final Comparator comparator4 = new Comparator() { // from class: com.arflix.tv.ui.screens.details.AutoPlaySourcePlannerKt$bestAutoPlayStream$$inlined$thenByDescending$3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                int iCompare = comparator3.compare(t2, t10);
                if (iCompare != 0) {
                    return iCompare;
                }
                StreamBehaviorHints behaviorHints = ((StreamSource) t10).getBehaviorHints();
                Integer num = behaviorHints != null ? kotlin.jvm.internal.p.a(behaviorHints.getCached(), Boolean.TRUE) : false ? num : 0;
                StreamBehaviorHints behaviorHints2 = ((StreamSource) t2).getBehaviorHints();
                return com.google.common.util.concurrent.r0.e(num, behaviorHints2 != null ? kotlin.jvm.internal.p.a(behaviorHints2.getCached(), Boolean.TRUE) : false ? 1 : 0);
            }
        };
        final Comparator comparator5 = new Comparator() { // from class: com.arflix.tv.ui.screens.details.AutoPlaySourcePlannerKt$bestAutoPlayStream$$inlined$thenBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                int iCompare = comparator4.compare(t2, t10);
                if (iCompare != 0) {
                    return iCompare;
                }
                String addonName = ((StreamSource) t2).getAddonName();
                Locale locale = Locale.ROOT;
                return com.google.common.util.concurrent.r0.e(addonName.toLowerCase(locale), ((StreamSource) t10).getAddonName().toLowerCase(locale));
            }
        };
        Comparator comparator6 = new Comparator() { // from class: com.arflix.tv.ui.screens.details.AutoPlaySourcePlannerKt$bestAutoPlayStream$$inlined$thenBy$2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                int iCompare = comparator5.compare(t2, t10);
                if (iCompare != 0) {
                    return iCompare;
                }
                String source = ((StreamSource) t2).getSource();
                Locale locale = Locale.ROOT;
                return com.google.common.util.concurrent.r0.e(source.toLowerCase(locale), ((StreamSource) t10).getSource().toLowerCase(locale));
            }
        };
        List listV = ga.r.V(iVarI);
        kotlin.collections.w.X(comparator6, listV);
        Iterator it = ((ArrayList) listV).iterator();
        return (StreamSource) (!it.hasNext() ? null : it.next());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean bestAutoPlayStream$lambda$0(int i10, StreamSource streamSource) {
        return qualityScoreForAutoPlay(streamSource) >= i10;
    }

    public static final boolean isAutoPlayableStream(StreamSource streamSource) {
        String url = streamSource.getUrl();
        String string = url != null ? kotlin.text.o.L0(url).toString() : null;
        if (string == null) {
            string = "";
        }
        if (kotlin.text.u.P(string, "http", true)) {
            return !isPendingDebridStream(streamSource);
        }
        return false;
    }

    public static final boolean isPendingDebridStream(StreamSource streamSource) {
        String lowerCase = kotlin.collections.x.u0(kotlin.collections.r.X(new String[]{streamSource.getSource(), streamSource.getAddonName(), streamSource.getQuality(), streamSource.getUrl(), streamSource.getDescription()}), " ", null, null, null, 62).toLowerCase(Locale.ROOT);
        List listE = t7.a.E("torrent being downloaded", "being downloaded", "still downloading", "queued", "not cached", "uncached", "cache pending", "caching", "processing torrent", "download in progress");
        if (!listE.isEmpty()) {
            Iterator it = listE.iterator();
            while (it.hasNext()) {
                if (kotlin.text.o.T(lowerCase, (String) it.next(), false)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final int minQualityThreshold(String str) {
        String lowerCase = kotlin.text.o.L0(str).toString().toLowerCase(Locale.ROOT);
        switch (lowerCase.hashCode()) {
            case -1263417397:
                return !lowerCase.equals("fullhd") ? 0 : 3;
            case 1719:
                return !lowerCase.equals("4k") ? 0 : 4;
            case 3324:
                return lowerCase.equals("hd") ? 2 : 0;
            case 101346:
                return !lowerCase.equals("fhd") ? 0 : 3;
            case 115761:
                return !lowerCase.equals("uhd") ? 0 : 4;
            case 1688155:
                return !lowerCase.equals("720p") ? 0 : 2;
            case 46737913:
                return !lowerCase.equals("1080p") ? 0 : 3;
            case 47689303:
                return !lowerCase.equals("2160p") ? 0 : 4;
            default:
                return 0;
        }
    }

    public static final int qualityScoreForAutoPlay(StreamSource streamSource) {
        String filename;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(streamSource.getQuality());
        sb2.append(' ');
        sb2.append(streamSource.getSource());
        sb2.append(' ');
        sb2.append(streamSource.getAddonName());
        StreamBehaviorHints behaviorHints = streamSource.getBehaviorHints();
        if (behaviorHints != null && (filename = behaviorHints.getFilename()) != null) {
            sb2.append(' ');
            sb2.append(filename);
        }
        String description = streamSource.getDescription();
        if (description != null) {
            sb2.append(' ');
            sb2.append(description);
        }
        String string = sb2.toString();
        if (kotlin.text.o.T(string, "2160p", true) || fourKRegex.a(string)) {
            return 4;
        }
        if (kotlin.text.o.T(string, "1080p", true)) {
            return 3;
        }
        if (kotlin.text.o.T(string, "720p", true)) {
            return 2;
        }
        return kotlin.text.o.T(string, "480p", true) ? 1 : 0;
    }

    public static final boolean shouldWaitForAutoPlaySources(boolean z, StreamSource streamSource, long j10) {
        if (j10 >= 2000) {
            return false;
        }
        return (streamSource != null && qualityScoreForAutoPlay(streamSource) >= 4) ? j10 < 450 : z;
    }
}
