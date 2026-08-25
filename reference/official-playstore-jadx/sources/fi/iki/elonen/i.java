package fi.iki.elonen;

import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
public final class i {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final i f15430i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final i f15431l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final i f15432m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final i f15433n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final /* synthetic */ i[] f15434o;

    static {
        i iVar = new i("GET", 0);
        f15430i = iVar;
        i iVar2 = new i("PUT", 1);
        f15431l = iVar2;
        i iVar3 = new i(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST, 2);
        f15432m = iVar3;
        i iVar4 = new i("DELETE", 3);
        i iVar5 = new i("HEAD", 4);
        f15433n = iVar5;
        f15434o = new i[]{iVar, iVar2, iVar3, iVar4, iVar5, new i("OPTIONS", 5), new i("TRACE", 6), new i("CONNECT", 7), new i("PATCH", 8), new i("PROPFIND", 9), new i("PROPPATCH", 10), new i("MKCOL", 11), new i("MOVE", 12), new i("COPY", 13), new i("LOCK", 14), new i("UNLOCK", 15)};
    }

    public static i a(String str) {
        if (str == null) {
            return null;
        }
        try {
            return valueOf(str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public static i valueOf(String str) {
        return (i) Enum.valueOf(i.class, str);
    }

    public static i[] values() {
        return (i[]) f15434o.clone();
    }
}
