package com.arflix.tv.data.model;

import androidx.compose.foundation.c;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import kotlin.text.o;
import q7.g;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\b\u0087@\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004¢\u0006\u0004\b\u000f\u0010\u0010J\u0011\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004¢\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004¢\u0006\u0004\b\u0016\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\t\u0010\u0005R\u0011\u0010\n\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0005\u0088\u0001\u0002¨\u0006\u0018"}, d2 = {"Lcom/arflix/tv/data/model/PlaylistGroupKey;", "", "key", "", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "getKey", "()Ljava/lang/String;", "playlistId", "getPlaylistId-impl", "groupName", "getGroupName-impl", "equals", "", "other", "equals-impl", "(Ljava/lang/String;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Ljava/lang/String;)I", "toString", "toString-impl", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
@g
public final class PlaylistGroupKey {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String key;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005¨\u0006\b"}, d2 = {"Lcom/arflix/tv/data/model/PlaylistGroupKey$Companion;", "", "<init>", "()V", "build", "", "playlistId", "groupName", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final String build(String playlistId, String groupName) {
            return c.t(playlistId, "|", groupName);
        }

        private Companion() {
        }
    }

    private /* synthetic */ PlaylistGroupKey(String str) {
        this.key = str;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ PlaylistGroupKey m6067boximpl(String str) {
        return new PlaylistGroupKey(str);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static String m6068constructorimpl(String str) {
        return str;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m6069equalsimpl(String str, Object obj) {
        return (obj instanceof PlaylistGroupKey) && p.a(str, ((PlaylistGroupKey) obj).m6075unboximpl());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m6070equalsimpl0(String str, String str2) {
        return p.a(str, str2);
    }

    /* JADX INFO: renamed from: getGroupName-impl, reason: not valid java name */
    public static final String m6071getGroupNameimpl(String str) {
        return o.C0('|', str, str);
    }

    /* JADX INFO: renamed from: getPlaylistId-impl, reason: not valid java name */
    public static final String m6072getPlaylistIdimpl(String str) {
        return o.G0('|', str, str);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m6073hashCodeimpl(String str) {
        return str.hashCode();
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m6074toStringimpl(String str) {
        return a0.c.l("PlaylistGroupKey(key=", str, ")");
    }

    public boolean equals(Object obj) {
        return m6069equalsimpl(this.key, obj);
    }

    public final String getKey() {
        return this.key;
    }

    public int hashCode() {
        return m6073hashCodeimpl(this.key);
    }

    public String toString() {
        return m6074toStringimpl(this.key);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ String m6075unboximpl() {
        return this.key;
    }
}
