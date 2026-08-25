package com.arflix.tv.util;

import a0.c;
import androidx.fragment.app.a2;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lcom/arflix/tv/util/ParsedCatalogUrl;", "", "<init>", "()V", "TraktUserList", "TraktList", "Mdblist", "Lcom/arflix/tv/util/ParsedCatalogUrl$Mdblist;", "Lcom/arflix/tv/util/ParsedCatalogUrl$TraktList;", "Lcom/arflix/tv/util/ParsedCatalogUrl$TraktUserList;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class ParsedCatalogUrl {
    public static final int $stable = 0;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/arflix/tv/util/ParsedCatalogUrl$Mdblist;", "Lcom/arflix/tv/util/ParsedCatalogUrl;", "url", "", "<init>", "(Ljava/lang/String;)V", "getUrl", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Mdblist extends ParsedCatalogUrl {
        public static final int $stable = 0;
        private final String url;

        public Mdblist(String str) {
            super(null);
            this.url = str;
        }

        public static /* synthetic */ Mdblist copy$default(Mdblist mdblist, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = mdblist.url;
            }
            return mdblist.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        public final Mdblist copy(String url) {
            return new Mdblist(url);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Mdblist) && p.a(this.url, ((Mdblist) other).url);
        }

        public final String getUrl() {
            return this.url;
        }

        public int hashCode() {
            return this.url.hashCode();
        }

        public String toString() {
            return c.l("Mdblist(url=", this.url, ")");
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/arflix/tv/util/ParsedCatalogUrl$TraktList;", "Lcom/arflix/tv/util/ParsedCatalogUrl;", "listId", "", "<init>", "(Ljava/lang/String;)V", "getListId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class TraktList extends ParsedCatalogUrl {
        public static final int $stable = 0;
        private final String listId;

        public TraktList(String str) {
            super(null);
            this.listId = str;
        }

        public static /* synthetic */ TraktList copy$default(TraktList traktList, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = traktList.listId;
            }
            return traktList.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getListId() {
            return this.listId;
        }

        public final TraktList copy(String listId) {
            return new TraktList(listId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof TraktList) && p.a(this.listId, ((TraktList) other).listId);
        }

        public final String getListId() {
            return this.listId;
        }

        public int hashCode() {
            return this.listId.hashCode();
        }

        public String toString() {
            return c.l("TraktList(listId=", this.listId, ")");
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0083\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/arflix/tv/util/ParsedCatalogUrl$TraktUserList;", "Lcom/arflix/tv/util/ParsedCatalogUrl;", "username", "", "listId", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getUsername", "()Ljava/lang/String;", "getListId", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class TraktUserList extends ParsedCatalogUrl {
        public static final int $stable = 0;
        private final String listId;
        private final String username;

        public TraktUserList(String str, String str2) {
            super(null);
            this.username = str;
            this.listId = str2;
        }

        public static /* synthetic */ TraktUserList copy$default(TraktUserList traktUserList, String str, String str2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = traktUserList.username;
            }
            if ((i10 & 2) != 0) {
                str2 = traktUserList.listId;
            }
            return traktUserList.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUsername() {
            return this.username;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getListId() {
            return this.listId;
        }

        public final TraktUserList copy(String username, String listId) {
            return new TraktUserList(username, listId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TraktUserList)) {
                return false;
            }
            TraktUserList traktUserList = (TraktUserList) other;
            return p.a(this.username, traktUserList.username) && p.a(this.listId, traktUserList.listId);
        }

        public final String getListId() {
            return this.listId;
        }

        public final String getUsername() {
            return this.username;
        }

        public int hashCode() {
            return this.listId.hashCode() + (this.username.hashCode() * 31);
        }

        public String toString() {
            return a2.m("TraktUserList(username=", this.username, ", listId=", this.listId, ")");
        }
    }

    public /* synthetic */ ParsedCatalogUrl(h hVar) {
        this();
    }

    private ParsedCatalogUrl() {
    }
}
