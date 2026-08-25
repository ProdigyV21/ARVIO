package com.arflix.tv.data.api;

import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/arflix/tv/data/api/TraktUserIds;", "", "slug", "", "<init>", "(Ljava/lang/String;)V", "getSlug", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktUserIds {
    public static final int $stable = 0;
    private final String slug;

    public TraktUserIds(String str) {
        this.slug = str;
    }

    public static /* synthetic */ TraktUserIds copy$default(TraktUserIds traktUserIds, String str, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = traktUserIds.slug;
        }
        return traktUserIds.copy(str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getSlug() {
        return this.slug;
    }

    public final TraktUserIds copy(String slug) {
        return new TraktUserIds(slug);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof TraktUserIds) && p.a(this.slug, ((TraktUserIds) other).slug);
    }

    public final String getSlug() {
        return this.slug;
    }

    public int hashCode() {
        String str = this.slug;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public String toString() {
        return a0.c.l("TraktUserIds(slug=", this.slug, ")");
    }
}
