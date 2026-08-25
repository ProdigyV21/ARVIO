package com.arflix.tv.data.model;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.collections.s;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.text.o;
import kotlin.text.u;
import x6.c0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\u0012\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\u0013\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005J\u000e\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0005J\u0010\u0010\u0016\u001a\u00020\u00102\b\u0010\u0017\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0017\u001a\u00020\u0005J\u000e\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020#J\u000e\u0010$\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020#J\u000e\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u0005J\u0010\u0010(\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010)\u001a\u00020\u00102\u0006\u0010*\u001a\u00020\u0005H\u0002J%\u0010+\u001a\u00020\u00052\u0016\u0010,\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00050-\"\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0002\u0010.J\u001e\u0010/\u001a\u00020\u00102\u0006\u00100\u001a\u00020\u00052\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u00050\fH\u0002J\u0012\u00102\u001a\b\u0012\u0004\u0012\u00020403*\u00020\u001fH\u0002J\u0012\u00105\u001a\b\u0012\u0004\u0012\u00020#03*\u00020\u001fH\u0002J\u0012\u00106\u001a\b\u0012\u0004\u0012\u00020\u000503*\u00020\u001fH\u0002J\f\u00107\u001a\u00020\u0005*\u000204H\u0002J\u0012\u00106\u001a\b\u0012\u0004\u0012\u00020\u000503*\u000204H\u0002J\u0010\u00108\u001a\u00020\u00102\b\u00109\u001a\u0004\u0018\u00010\u0005J\u0010\u0010:\u001a\u00020\u00102\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bJ\u000e\u0010;\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u001fJa\u0010<\u001a\u00020\u00102\n\b\u0002\u0010=\u001a\u0004\u0018\u00010>2\n\b\u0002\u0010?\u001a\u0004\u0018\u00010&2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010A\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010B\u001a\u00020\u00102\u000e\b\u0002\u0010C\u001a\b\u0012\u0004\u0012\u00020\u001b03¢\u0006\u0002\u0010DR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006E"}, d2 = {"Lcom/arflix/tv/data/model/SportsAddonCapabilities;", "", "<init>", "()V", "SPORTS_CATEGORY_ROW_ID", "", "POPULAR_LIVE_TV_ROW_ID", "SPORTS_STATUS_PREFIX", "SPORTS_LOCKED_STATUS_PREFIX", "SPORTS_EVENT_STATUS_PREFIX", "SPORTS_COLLECTION_PREFIX", "sportTerms", "", "liveTerms", "explicitVodTypes", "isSportsHomeStatus", "", "status", "isSportsCategoryStatus", "isSportsEventStatus", "sportsCollectionCatalogId", "sportId", "isSportsCollectionCatalogId", "catalogId", "sportIdFromCollectionCatalogId", "isSportsLiveTvAddon", "addon", "Lcom/arflix/tv/data/model/Addon;", "isSportsOnlyLiveTvAddon", "isSportsOnlyLiveTvManifest", "manifest", "Lcom/arflix/tv/data/model/AddonManifest;", "isSportsLiveTvManifest", "isSportsCatalog", "catalog", "Lcom/arflix/tv/data/model/AddonCatalog;", "isLiveSportsCatalog", "sportsSyntheticId", "", "raw", "manifestDeclaresVodStreams", "isExplicitVodType", "value", "normalizedText", "parts", "", "([Ljava/lang/String;)Ljava/lang/String;", "containsAny", "text", "terms", "safeResources", "", "Lcom/arflix/tv/data/model/AddonResource;", "safeCatalogs", "safeTypes", "safeName", "isLiveStreamAddonId", "addonId", "isLiveStreamAddon", "isLiveStreamManifest", "isLiveStreamOrSportsItem", "mediaType", "Lcom/arflix/tv/data/model/MediaType;", TtmlNode.ATTR_ID, "streamAddonId", LinkHeader.Parameters.Title, "isLiveStream", "addons", "(Lcom/arflix/tv/data/model/MediaType;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/util/List;)Z", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SportsAddonCapabilities {
    public static final int $stable = 0;
    public static final String POPULAR_LIVE_TV_ROW_ID = "popular_live_tv";
    public static final String SPORTS_COLLECTION_PREFIX = "sports_collection:";
    public static final String SPORTS_EVENT_STATUS_PREFIX = "sports_event:";
    public static final String SPORTS_LOCKED_STATUS_PREFIX = "sports_locked:";
    public static final String SPORTS_STATUS_PREFIX = "sports:";
    public static final SportsAddonCapabilities INSTANCE = new SportsAddonCapabilities();
    public static final String SPORTS_CATEGORY_ROW_ID = "sports";
    private static final Set<String> sportTerms = r.p0(new String[]{"sport", SPORTS_CATEGORY_ROW_ID, "football", "soccer", "basketball", "tennis", "motorsport", "formula", "racing", "rugby", "hockey", "baseball", "boxing", "ufc", "mma", "cricket", "golf"});
    private static final Set<String> liveTerms = r.p0(new String[]{"live", "event", "events", "match", "matches", "game", "games"});
    private static final Set<String> explicitVodTypes = r.p0(new String[]{"movie", "film", "series", "show", "anime"});

    private SportsAddonCapabilities() {
    }

    private final boolean containsAny(String text, Set<String> terms) {
        Set<String> set = terms;
        if (!(set instanceof Collection) || !set.isEmpty()) {
            Iterator<T> it = set.iterator();
            while (it.hasNext()) {
                if (o.T(text, (String) it.next(), false)) {
                    return true;
                }
            }
        }
        return false;
    }

    private final boolean isExplicitVodType(String value) {
        return explicitVodTypes.contains(o.L0(value).toString().toLowerCase(Locale.US));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ boolean isLiveStreamOrSportsItem$default(SportsAddonCapabilities sportsAddonCapabilities, MediaType mediaType, Integer num, String str, String str2, String str3, boolean z, List list, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            mediaType = null;
        }
        if ((i10 & 2) != 0) {
            num = null;
        }
        if ((i10 & 4) != 0) {
            str = null;
        }
        if ((i10 & 8) != 0) {
            str2 = null;
        }
        if ((i10 & 16) != 0) {
            str3 = null;
        }
        if ((i10 & 32) != 0) {
            z = false;
        }
        if ((i10 & 64) != 0) {
            list = z.f19728i;
        }
        return sportsAddonCapabilities.isLiveStreamOrSportsItem(mediaType, num, str, str2, str3, z, list);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0067  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean manifestDeclaresVodStreams(com.arflix.tv.data.model.AddonManifest r6) {
        /*
            r5 = this;
            java.util.List r0 = r5.safeTypes(r6)
            r1 = 1
            if (r0 == 0) goto Le
            boolean r2 = r0.isEmpty()
            if (r2 == 0) goto Le
            goto L26
        Le:
            java.util.Iterator r0 = r0.iterator()
        L12:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L26
            java.lang.Object r2 = r0.next()
            java.lang.String r2 = (java.lang.String) r2
            boolean r2 = r5.isExplicitVodType(r2)
            if (r2 == 0) goto L12
            goto Laa
        L26:
            java.util.List r0 = r5.safeCatalogs(r6)
            if (r0 == 0) goto L33
            boolean r2 = r0.isEmpty()
            if (r2 == 0) goto L33
            goto L50
        L33:
            java.util.Iterator r0 = r0.iterator()
        L37:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L50
            java.lang.Object r2 = r0.next()
            com.arflix.tv.data.model.AddonCatalog r2 = (com.arflix.tv.data.model.AddonCatalog) r2
            com.arflix.tv.data.model.SportsAddonCapabilities r3 = com.arflix.tv.data.model.SportsAddonCapabilities.INSTANCE
            java.lang.String r2 = r2.getType()
            boolean r2 = r3.isExplicitVodType(r2)
            if (r2 == 0) goto L37
            goto Laa
        L50:
            java.util.List r6 = r5.safeResources(r6)
            if (r6 == 0) goto L5d
            boolean r0 = r6.isEmpty()
            if (r0 == 0) goto L5d
            goto Lab
        L5d:
            java.util.Iterator r6 = r6.iterator()
        L61:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto Lab
            java.lang.Object r0 = r6.next()
            com.arflix.tv.data.model.AddonResource r0 = (com.arflix.tv.data.model.AddonResource) r0
            com.arflix.tv.data.model.SportsAddonCapabilities r2 = com.arflix.tv.data.model.SportsAddonCapabilities.INSTANCE
            java.lang.String r3 = r2.safeName(r0)
            java.lang.String r4 = "stream"
            boolean r3 = kotlin.text.u.L(r3, r4, r1)
            if (r3 != 0) goto L87
            java.lang.String r3 = r2.safeName(r0)
            java.lang.String r4 = "streams"
            boolean r3 = kotlin.text.u.L(r3, r4, r1)
            if (r3 == 0) goto L61
        L87:
            java.util.List r0 = r2.safeTypes(r0)
            if (r0 == 0) goto L94
            boolean r3 = r0.isEmpty()
            if (r3 == 0) goto L94
            goto L61
        L94:
            java.util.Iterator r0 = r0.iterator()
        L98:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L61
            java.lang.Object r3 = r0.next()
            java.lang.String r3 = (java.lang.String) r3
            boolean r3 = r2.isExplicitVodType(r3)
            if (r3 == 0) goto L98
        Laa:
            return r1
        Lab:
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.model.SportsAddonCapabilities.manifestDeclaresVodStreams(com.arflix.tv.data.model.AddonManifest):boolean");
    }

    private final String normalizedText(String... parts) {
        return x.u0(r.X(parts), " ", null, null, null, 62).toLowerCase(Locale.US);
    }

    private final List<AddonCatalog> safeCatalogs(AddonManifest addonManifest) {
        Object c0Var;
        try {
            c0Var = addonManifest.getCatalogs();
        } catch (Throwable th) {
            c0Var = new c0(th);
        }
        if (c0Var instanceof c0) {
            c0Var = null;
        }
        List<AddonCatalog> list = (List) c0Var;
        return list == null ? z.f19728i : list;
    }

    private final String safeName(AddonResource addonResource) {
        Object c0Var;
        try {
            c0Var = addonResource.getName();
        } catch (Throwable th) {
            c0Var = new c0(th);
        }
        if (c0Var instanceof c0) {
            c0Var = null;
        }
        String str = (String) c0Var;
        return str == null ? "" : str;
    }

    private final List<AddonResource> safeResources(AddonManifest addonManifest) {
        Object c0Var;
        try {
            c0Var = addonManifest.getResources();
        } catch (Throwable th) {
            c0Var = new c0(th);
        }
        if (c0Var instanceof c0) {
            c0Var = null;
        }
        List<AddonResource> list = (List) c0Var;
        return list == null ? z.f19728i : list;
    }

    private final List<String> safeTypes(AddonManifest addonManifest) {
        Object c0Var;
        try {
            c0Var = addonManifest.getTypes();
        } catch (Throwable th) {
            c0Var = new c0(th);
        }
        if (c0Var instanceof c0) {
            c0Var = null;
        }
        List<String> list = (List) c0Var;
        return list == null ? z.f19728i : list;
    }

    public final boolean isLiveSportsCatalog(AddonCatalog catalog) {
        return isSportsCatalog(catalog) || containsAny(normalizedText(catalog.getType(), catalog.getId(), catalog.getName()), liveTerms);
    }

    public final boolean isLiveStreamAddon(Addon addon) {
        if (addon == null) {
            return false;
        }
        AddonManifest manifest = addon.getManifest();
        return manifest != null ? isLiveStreamManifest(manifest) : isLiveStreamAddonId(addon.getId()) || isLiveStreamAddonId(addon.getName());
    }

    public final boolean isLiveStreamAddonId(String addonId) {
        String string;
        if (addonId != null && (string = o.L0(addonId).toString()) != null) {
            String lowerCase = string.toLowerCase(Locale.US);
            if (!o.T(lowerCase, "cinemeta", false) && !o.T(lowerCase, "tmdb", false) && !o.T(lowerCase, "torrentio", false) && (o.T(lowerCase, "livetv", false) || o.T(lowerCase, "live_tv", false) || o.T(lowerCase, "live-tv", false) || o.T(lowerCase, "live_stream", false) || o.T(lowerCase, "livestream", false) || o.T(lowerCase, "live-stream", false) || o.T(lowerCase, "tvchannels", false) || o.T(lowerCase, "tv-channels", false) || o.T(lowerCase, "iptv", false) || o.T(lowerCase, SPORTS_CATEGORY_ROW_ID, false))) {
                return true;
            }
        }
        return false;
    }

    public final boolean isLiveStreamManifest(AddonManifest manifest) {
        if (manifestDeclaresVodStreams(manifest)) {
            return false;
        }
        if (isSportsLiveTvManifest(manifest) || isLiveStreamAddonId(manifest.getId()) || isLiveStreamAddonId(manifest.getName())) {
            return true;
        }
        List<AddonResource> listSafeResources = safeResources(manifest);
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = listSafeResources.iterator();
        while (it.hasNext()) {
            x.b0(arrayList, INSTANCE.safeTypes((AddonResource) it.next()));
        }
        ArrayList arrayListI0 = x.I0(arrayList, safeTypes(manifest));
        List<AddonCatalog> listSafeCatalogs = safeCatalogs(manifest);
        ArrayList arrayList2 = new ArrayList(s.U(listSafeCatalogs, 10));
        Iterator<T> it2 = listSafeCatalogs.iterator();
        while (it2.hasNext()) {
            arrayList2.add(((AddonCatalog) it2.next()).getType());
        }
        ArrayList arrayListI02 = x.I0(arrayListI0, arrayList2);
        ArrayList arrayList3 = new ArrayList(s.U(arrayListI02, 10));
        Iterator it3 = arrayListI02.iterator();
        while (it3.hasNext()) {
            arrayList3.add(o.L0((String) it3.next()).toString().toLowerCase(Locale.US));
        }
        Set setP0 = r.p0(new String[]{"tv", "channel", "channels", "live", SPORTS_CATEGORY_ROW_ID, "tvchannel", "live_tv", "livestream"});
        if (arrayList3.isEmpty()) {
            return false;
        }
        Iterator it4 = arrayList3.iterator();
        while (it4.hasNext()) {
            if (setP0.contains((String) it4.next())) {
                return (arrayList3.contains("movie") || arrayList3.contains("series")) ? false : true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean isLiveStreamOrSportsItem(com.arflix.tv.data.model.MediaType r3, java.lang.Integer r4, java.lang.String r5, java.lang.String r6, java.lang.String r7, boolean r8, java.util.List<com.arflix.tv.data.model.Addon> r9) {
        /*
            r2 = this;
            r3 = 1
            if (r8 == 0) goto L5
            goto L7e
        L5:
            java.lang.String r8 = "live:"
            r0 = 0
            if (r5 == 0) goto L27
            boolean r1 = r2.isSportsHomeStatus(r5)
            if (r1 != 0) goto L7e
            java.lang.String r1 = "iptv:"
            boolean r1 = kotlin.text.u.P(r5, r1, r0)
            if (r1 != 0) goto L7e
            boolean r1 = kotlin.text.u.P(r5, r8, r0)
            if (r1 != 0) goto L7e
            java.lang.String r1 = "channel:"
            boolean r5 = kotlin.text.u.P(r5, r1, r0)
            if (r5 == 0) goto L27
            goto L7e
        L27:
            if (r4 == 0) goto L30
            int r4 = r4.intValue()
            if (r4 > 0) goto L30
            goto L7e
        L30:
            if (r6 == 0) goto L68
            boolean r4 = kotlin.text.o.h0(r6)
            if (r4 == 0) goto L39
            goto L68
        L39:
            java.util.Iterator r4 = r9.iterator()
        L3d:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L55
            java.lang.Object r5 = r4.next()
            r9 = r5
            com.arflix.tv.data.model.Addon r9 = (com.arflix.tv.data.model.Addon) r9
            java.lang.String r9 = r9.getId()
            boolean r9 = kotlin.text.u.L(r9, r6, r3)
            if (r9 == 0) goto L3d
            goto L56
        L55:
            r5 = 0
        L56:
            com.arflix.tv.data.model.Addon r5 = (com.arflix.tv.data.model.Addon) r5
            if (r5 == 0) goto L61
            boolean r4 = r2.isLiveStreamAddon(r5)
            if (r4 == 0) goto L68
            goto L7e
        L61:
            boolean r4 = r2.isLiveStreamAddonId(r6)
            if (r4 == 0) goto L68
            goto L7e
        L68:
            if (r7 == 0) goto L7f
            java.util.Locale r4 = java.util.Locale.US
            java.lang.String r4 = r7.toLowerCase(r4)
            boolean r5 = kotlin.text.u.P(r4, r8, r0)
            if (r5 != 0) goto L7e
            java.lang.String r5 = "[live]"
            boolean r4 = kotlin.text.u.P(r4, r5, r0)
            if (r4 == 0) goto L7f
        L7e:
            return r3
        L7f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.model.SportsAddonCapabilities.isLiveStreamOrSportsItem(com.arflix.tv.data.model.MediaType, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, boolean, java.util.List):boolean");
    }

    public final boolean isSportsCatalog(AddonCatalog catalog) {
        String type = catalog.getType();
        String id = catalog.getId();
        String name = catalog.getName();
        List<String> genres = catalog.getGenres();
        if (genres == null) {
            genres = z.f19728i;
        }
        return containsAny(normalizedText(type, id, name, x.u0(genres, " ", null, null, null, 62)), sportTerms);
    }

    public final boolean isSportsCategoryStatus(String status) {
        return status != null && u.P(status, SPORTS_STATUS_PREFIX, false);
    }

    public final boolean isSportsCollectionCatalogId(String catalogId) {
        return catalogId != null && u.P(catalogId, SPORTS_COLLECTION_PREFIX, false);
    }

    public final boolean isSportsEventStatus(String status) {
        return status != null && u.P(status, SPORTS_EVENT_STATUS_PREFIX, false);
    }

    public final boolean isSportsHomeStatus(String status) {
        return status != null && (u.P(status, SPORTS_STATUS_PREFIX, false) || u.P(status, SPORTS_LOCKED_STATUS_PREFIX, false) || u.P(status, SPORTS_EVENT_STATUS_PREFIX, false));
    }

    public final boolean isSportsLiveTvAddon(Addon addon) {
        AddonManifest manifest = addon.getManifest();
        return manifest != null && isSportsLiveTvManifest(manifest);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0079 A[EDGE_INSN: B:59:0x0079->B:29:0x0079 BREAK  A[LOOP:2: B:20:0x004c->B:61:?], EDGE_INSN: B:60:0x0079->B:29:0x0079 BREAK  A[LOOP:2: B:20:0x004c->B:61:?]] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x007b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean isSportsLiveTvManifest(com.arflix.tv.data.model.AddonManifest r13) {
        /*
            Method dump skipped, instruction units count: 257
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.model.SportsAddonCapabilities.isSportsLiveTvManifest(com.arflix.tv.data.model.AddonManifest):boolean");
    }

    public final boolean isSportsOnlyLiveTvAddon(Addon addon) {
        AddonManifest manifest = addon.getManifest();
        return manifest != null && isSportsOnlyLiveTvManifest(manifest);
    }

    public final boolean isSportsOnlyLiveTvManifest(AddonManifest manifest) {
        return isSportsLiveTvManifest(manifest) && !manifestDeclaresVodStreams(manifest);
    }

    public final String sportIdFromCollectionCatalogId(String catalogId) {
        if (!INSTANCE.isSportsCollectionCatalogId(catalogId)) {
            catalogId = null;
        }
        if (catalogId != null) {
            String strR0 = o.r0(catalogId, SPORTS_COLLECTION_PREFIX);
            if (!o.h0(strR0)) {
                return strR0;
            }
        }
        return null;
    }

    public final String sportsCollectionCatalogId(String sportId) {
        return SPORTS_COLLECTION_PREFIX.concat(sportId);
    }

    public final int sportsSyntheticId(String raw) {
        Integer numValueOf = Integer.valueOf(raw.hashCode() & Integer.MAX_VALUE);
        if (numValueOf.intValue() <= 0) {
            numValueOf = null;
        }
        if (numValueOf != null) {
            return numValueOf.intValue();
        }
        return 1;
    }

    private final List<String> safeTypes(AddonResource addonResource) {
        Object c0Var;
        try {
            c0Var = addonResource.getTypes();
        } catch (Throwable th) {
            c0Var = new c0(th);
        }
        if (c0Var instanceof c0) {
            c0Var = null;
        }
        List<String> list = (List) c0Var;
        return list == null ? z.f19728i : list;
    }
}
