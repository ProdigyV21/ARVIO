package com.arflix.tv.ui.components;

import com.arflix.tv.data.model.StreamBehaviorHints;
import com.arflix.tv.data.model.StreamSource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\u001a%\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001b\u0010\b\u001a\u0004\u0018\u00010\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u0000¢\u0006\u0004\b\b\u0010\t\u001a+\u0010\r\u001a\u00020\f*\b\u0012\u0004\u0012\u00020\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\r\u0010\u000e\u001a\u0017\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0010\u0010\t\"\u0014\u0010\u0012\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013\"\u0014\u0010\u0014\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0013\"\u0014\u0010\u0015\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0013\"\u0014\u0010\u0016\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0013\"\u0014\u0010\u0017\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0013\"\u0014\u0010\u0018\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0013\"\u0014\u0010\u0019\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u0013¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/data/model/StreamSource;", "stream", "", "addonLabel", "", "sourceAttributionLabels", "(Lcom/arflix/tv/data/model/StreamSource;Ljava/lang/String;)Ljava/util/List;", "raw", "cleanSourceAttribution", "(Ljava/lang/String;)Ljava/lang/String;", "", "label", "Lx6/t0;", "addAttributionLabel", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "value", "normalizedAttribution", "Lkotlin/text/m;", "attributionSeparators", "Lkotlin/text/m;", "attributionUrl", "attributionSize", "attributionTechnicalToken", "attributionEmptyWrappers", "attributionWhitespace", "attributionNonAlphanumeric", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class StreamSourceAttributionKt {
    private static final kotlin.text.m attributionSeparators = new kotlin.text.m("[\\r\\n|\\u2022\\u00B7]+");
    private static final kotlin.text.m attributionUrl = new kotlin.text.m("(?i)(?:https?://|magnet:)\\S+");
    private static final kotlin.text.m attributionSize = new kotlin.text.m("(?i)\\b\\d+(?:\\.\\d+)?\\s*(?:TB|GB|MB|KB)\\b");
    private static final kotlin.text.m attributionTechnicalToken = new kotlin.text.m("(?i)(?<![\\p{L}\\p{N}])(?:8K|4K|4320P|2160P|1080P|720P|576P|480P|UHD|FHD|SD|REMUX|BLU[ ._-]?RAY|BDRIP|WEB[ ._-]?DL|WEBRIP|HDTV|CAM|TELESYNC|HEVC|H[ ._-]?265|X265|AV1|AVC|H[ ._-]?264|X264|HDR10\\+?|HDR|DOLBY[ ._-]?VISION|DV|ATMOS|TRUEHD|DTS(?:[ ._-]?HD)?|DDP|DD\\+|E[ ._-]?AC3|AC3|AAC|7[ .]?1|5[ .]?1)(?![\\p{L}\\p{N}])");
    private static final kotlin.text.m attributionEmptyWrappers = new kotlin.text.m("\\(\\s*\\)|\\[\\s*]|\\{\\s*\\}");
    private static final kotlin.text.m attributionWhitespace = new kotlin.text.m("\\s+");
    private static final kotlin.text.m attributionNonAlphanumeric = new kotlin.text.m("[^\\p{L}\\p{N}]+");

    private static final void addAttributionLabel(List<String> list, String str, String str2) {
        if (str == null || kotlin.text.o.h0(str)) {
            return;
        }
        String strNormalizedAttribution = normalizedAttribution(str);
        String strNormalizedAttribution2 = normalizedAttribution(str2);
        if (kotlin.text.o.h0(strNormalizedAttribution) || kotlin.jvm.internal.p.a(strNormalizedAttribution2, strNormalizedAttribution) || kotlin.text.o.T(strNormalizedAttribution2, strNormalizedAttribution, false)) {
            return;
        }
        if (list == null || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                String strNormalizedAttribution3 = normalizedAttribution((String) it.next());
                if (kotlin.jvm.internal.p.a(strNormalizedAttribution3, strNormalizedAttribution) || kotlin.text.o.T(strNormalizedAttribution3, strNormalizedAttribution, false) || kotlin.text.o.T(strNormalizedAttribution, strNormalizedAttribution3, false)) {
                    return;
                }
            }
        }
        list.add(str);
    }

    public static final String cleanSourceAttribution(String str) {
        if (str != null && !kotlin.text.o.h0(str)) {
            String strM0 = kotlin.text.o.M0(attributionWhitespace.f(" ", attributionEmptyWrappers.f(" ", attributionTechnicalToken.f(" ", attributionSize.f(" ", attributionSeparators.f(" ", attributionUrl.f(" ", str)))))), ' ', '-', '_', '/', ',', ':');
            if (strM0.length() < 2) {
                strM0 = null;
            }
            if (strM0 != null) {
                return kotlin.text.o.I0(48, strM0);
            }
        }
        return null;
    }

    private static final String normalizedAttribution(String str) {
        return attributionNonAlphanumeric.f("", str.toLowerCase(Locale.ROOT));
    }

    public static final List<String> sourceAttributionLabels(StreamSource streamSource, String str) {
        StreamBehaviorHints behaviorHints = streamSource.getBehaviorHints();
        String strCleanSourceAttribution = cleanSourceAttribution(behaviorHints != null ? behaviorHints.getProvider() : null);
        String strCleanSourceAttribution2 = cleanSourceAttribution(streamSource.getRawLabel());
        if (strCleanSourceAttribution == null) {
            strCleanSourceAttribution = strCleanSourceAttribution2;
        }
        String strCleanSourceAttribution3 = cleanSourceAttribution(behaviorHints != null ? behaviorHints.getSourceLabel() : null);
        String strCleanSourceAttribution4 = cleanSourceAttribution(behaviorHints != null ? behaviorHints.getProviderCode() : null);
        String strCleanSourceAttribution5 = cleanSourceAttribution(behaviorHints != null ? behaviorHints.getIndexer() : null);
        if (strCleanSourceAttribution5 == null) {
            strCleanSourceAttribution5 = cleanSourceAttribution(behaviorHints != null ? behaviorHints.getIndexerCode() : null);
        }
        z6.c cVarN = t7.a.n();
        addAttributionLabel(cVarN, strCleanSourceAttribution, str);
        boolean z = true;
        boolean z5 = (strCleanSourceAttribution3 == null || strCleanSourceAttribution4 == null || !kotlin.jvm.internal.p.a(normalizedAttribution(strCleanSourceAttribution3), normalizedAttribution(strCleanSourceAttribution4))) ? false : true;
        if (strCleanSourceAttribution3 == null || strCleanSourceAttribution == null || strCleanSourceAttribution3.length() > 3) {
            z = false;
            break;
        }
        for (int i10 = 0; i10 < strCleanSourceAttribution3.length(); i10++) {
            char cCharAt = strCleanSourceAttribution3.charAt(i10);
            if (Character.isLetter(cCharAt) && !Character.isUpperCase(cCharAt)) {
                z = false;
                break;
            }
        }
        if (!z5 && !z) {
            addAttributionLabel(cVarN, strCleanSourceAttribution3, str);
        }
        addAttributionLabel(cVarN, strCleanSourceAttribution5, str);
        z6.c cVarE = t7.a.e(cVarN);
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        ListIterator listIterator = cVarE.listIterator(0);
        while (true) {
            z6.a aVar = (z6.a) listIterator;
            if (!aVar.hasNext()) {
                return arrayList;
            }
            Object next = aVar.next();
            if (hashSet.add(normalizedAttribution((String) next))) {
                arrayList.add(next);
            }
        }
    }
}
