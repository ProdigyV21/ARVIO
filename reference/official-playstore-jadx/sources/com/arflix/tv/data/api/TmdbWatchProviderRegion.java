package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Bc\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u000b\u0010\fJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003Je\u0010\u001b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001f\u001a\u00020 HÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010¨\u0006\""}, d2 = {"Lcom/arflix/tv/data/api/TmdbWatchProviderRegion;", "", "link", "", "flatrate", "", "Lcom/arflix/tv/data/api/TmdbWatchProvider;", "free", "ads", "rent", "buy", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getLink", "()Ljava/lang/String;", "getFlatrate", "()Ljava/util/List;", "getFree", "getAds", "getRent", "getBuy", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbWatchProviderRegion {
    public static final int $stable = 0;
    private final List<TmdbWatchProvider> ads;
    private final List<TmdbWatchProvider> buy;
    private final List<TmdbWatchProvider> flatrate;
    private final List<TmdbWatchProvider> free;
    private final String link;
    private final List<TmdbWatchProvider> rent;

    public TmdbWatchProviderRegion() {
        this(null, null, null, null, null, null, 63, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TmdbWatchProviderRegion copy$default(TmdbWatchProviderRegion tmdbWatchProviderRegion, String str, List list, List list2, List list3, List list4, List list5, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = tmdbWatchProviderRegion.link;
        }
        if ((i10 & 2) != 0) {
            list = tmdbWatchProviderRegion.flatrate;
        }
        if ((i10 & 4) != 0) {
            list2 = tmdbWatchProviderRegion.free;
        }
        if ((i10 & 8) != 0) {
            list3 = tmdbWatchProviderRegion.ads;
        }
        if ((i10 & 16) != 0) {
            list4 = tmdbWatchProviderRegion.rent;
        }
        if ((i10 & 32) != 0) {
            list5 = tmdbWatchProviderRegion.buy;
        }
        List list6 = list4;
        List list7 = list5;
        return tmdbWatchProviderRegion.copy(str, list, list2, list3, list6, list7);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getLink() {
        return this.link;
    }

    public final List<TmdbWatchProvider> component2() {
        return this.flatrate;
    }

    public final List<TmdbWatchProvider> component3() {
        return this.free;
    }

    public final List<TmdbWatchProvider> component4() {
        return this.ads;
    }

    public final List<TmdbWatchProvider> component5() {
        return this.rent;
    }

    public final List<TmdbWatchProvider> component6() {
        return this.buy;
    }

    public final TmdbWatchProviderRegion copy(String link, List<TmdbWatchProvider> flatrate, List<TmdbWatchProvider> free, List<TmdbWatchProvider> ads, List<TmdbWatchProvider> rent, List<TmdbWatchProvider> buy) {
        return new TmdbWatchProviderRegion(link, flatrate, free, ads, rent, buy);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TmdbWatchProviderRegion)) {
            return false;
        }
        TmdbWatchProviderRegion tmdbWatchProviderRegion = (TmdbWatchProviderRegion) other;
        return p.a(this.link, tmdbWatchProviderRegion.link) && p.a(this.flatrate, tmdbWatchProviderRegion.flatrate) && p.a(this.free, tmdbWatchProviderRegion.free) && p.a(this.ads, tmdbWatchProviderRegion.ads) && p.a(this.rent, tmdbWatchProviderRegion.rent) && p.a(this.buy, tmdbWatchProviderRegion.buy);
    }

    public final List<TmdbWatchProvider> getAds() {
        return this.ads;
    }

    public final List<TmdbWatchProvider> getBuy() {
        return this.buy;
    }

    public final List<TmdbWatchProvider> getFlatrate() {
        return this.flatrate;
    }

    public final List<TmdbWatchProvider> getFree() {
        return this.free;
    }

    public final String getLink() {
        return this.link;
    }

    public final List<TmdbWatchProvider> getRent() {
        return this.rent;
    }

    public int hashCode() {
        String str = this.link;
        return this.buy.hashCode() + androidx.compose.material3.d.e(this.rent, androidx.compose.material3.d.e(this.ads, androidx.compose.material3.d.e(this.free, androidx.compose.material3.d.e(this.flatrate, (str == null ? 0 : str.hashCode()) * 31, 31), 31), 31), 31);
    }

    public String toString() {
        String str = this.link;
        List<TmdbWatchProvider> list = this.flatrate;
        List<TmdbWatchProvider> list2 = this.free;
        List<TmdbWatchProvider> list3 = this.ads;
        List<TmdbWatchProvider> list4 = this.rent;
        List<TmdbWatchProvider> list5 = this.buy;
        StringBuilder sb2 = new StringBuilder("TmdbWatchProviderRegion(link=");
        sb2.append(str);
        sb2.append(", flatrate=");
        sb2.append(list);
        sb2.append(", free=");
        a2.A(sb2, list2, ", ads=", list3, ", rent=");
        sb2.append(list4);
        sb2.append(", buy=");
        sb2.append(list5);
        sb2.append(")");
        return sb2.toString();
    }

    public TmdbWatchProviderRegion(String str, List<TmdbWatchProvider> list, List<TmdbWatchProvider> list2, List<TmdbWatchProvider> list3, List<TmdbWatchProvider> list4, List<TmdbWatchProvider> list5) {
        this.link = str;
        this.flatrate = list;
        this.free = list2;
        this.ads = list3;
        this.rent = list4;
        this.buy = list5;
    }

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException
        */
    public /* synthetic */ TmdbWatchProviderRegion(java.lang.String r2, java.util.List r3, java.util.List r4, java.util.List r5, java.util.List r6, java.util.List r7, int r8, kotlin.jvm.internal.h r9) {
        /*
            r1 = this;
            r9 = r8 & 1
            if (r9 == 0) goto L5
            r2 = 0
        L5:
            r9 = r8 & 2
            kotlin.collections.z r0 = kotlin.collections.z.f19728i
            if (r9 == 0) goto Lc
            r3 = r0
        Lc:
            r9 = r8 & 4
            if (r9 == 0) goto L11
            r4 = r0
        L11:
            r9 = r8 & 8
            if (r9 == 0) goto L16
            r5 = r0
        L16:
            r9 = r8 & 16
            if (r9 == 0) goto L1b
            r6 = r0
        L1b:
            r8 = r8 & 32
            if (r8 == 0) goto L27
            r9 = r0
            r7 = r5
            r8 = r6
            r5 = r3
            r6 = r4
            r3 = r1
            r4 = r2
            goto L2e
        L27:
            r9 = r7
            r8 = r6
            r6 = r4
            r7 = r5
            r4 = r2
            r5 = r3
            r3 = r1
        L2e:
            r3.<init>(r4, r5, r6, r7, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.api.TmdbWatchProviderRegion.<init>(java.lang.String, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, int, kotlin.jvm.internal.h):void");
    }
}
