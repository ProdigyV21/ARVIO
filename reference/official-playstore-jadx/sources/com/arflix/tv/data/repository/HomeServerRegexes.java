package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\bR\u0017\u0010\r\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\u0006\u001a\u0004\b\u000e\u0010\bR\u0017\u0010\u000f\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0006\u001a\u0004\b\u0010\u0010\bR\u0017\u0010\u0011\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0006\u001a\u0004\b\u0012\u0010\bR\u0017\u0010\u0013\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0006\u001a\u0004\b\u0014\u0010\bR\u0017\u0010\u0015\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0006\u001a\u0004\b\u0016\u0010\b¨\u0006\u0017"}, d2 = {"Lcom/arflix/tv/data/repository/HomeServerRegexes;", "", "<init>", "()V", "Lkotlin/text/m;", "DIACRITICS_REGEX", "Lkotlin/text/m;", "getDIACRITICS_REGEX", "()Lkotlin/text/m;", "NON_ALPHA_NUM_REGEX", "getNON_ALPHA_NUM_REGEX", "ARTICLES_REGEX", "getARTICLES_REGEX", "MULTI_SPACE_REGEX", "getMULTI_SPACE_REGEX", "CONNECTION_ID_SANITIZER_REGEX", "getCONNECTION_ID_SANITIZER_REGEX", "NON_ALPHA_NUM_STRICT_REGEX", "getNON_ALPHA_NUM_STRICT_REGEX", "PLEX_DEVICE_REGEX", "getPLEX_DEVICE_REGEX", "PLEX_CONNECTION_REGEX", "getPLEX_CONNECTION_REGEX", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class HomeServerRegexes {
    public static final HomeServerRegexes INSTANCE = new HomeServerRegexes();
    private static final kotlin.text.m DIACRITICS_REGEX = new kotlin.text.m("\\p{Mn}+");
    private static final kotlin.text.m NON_ALPHA_NUM_REGEX = new kotlin.text.m("[^a-z0-9]+");
    private static final kotlin.text.m ARTICLES_REGEX = new kotlin.text.m("\\b(the|a|an)\\b");
    private static final kotlin.text.m MULTI_SPACE_REGEX = new kotlin.text.m("\\s+");
    private static final kotlin.text.m CONNECTION_ID_SANITIZER_REGEX = new kotlin.text.m("[^a-z0-9:._-]+");
    private static final kotlin.text.m NON_ALPHA_NUM_STRICT_REGEX = new kotlin.text.m("[^a-z0-9]");
    private static final kotlin.text.m PLEX_DEVICE_REGEX = new kotlin.text.m("<Device\\b([^>]*)>(.*?)</Device>|<Device\\b([^>]*)/>", kotlin.collections.r.p0(new kotlin.text.n[]{kotlin.text.n.IGNORE_CASE, kotlin.text.n.DOT_MATCHES_ALL}));
    private static final kotlin.text.m PLEX_CONNECTION_REGEX = new kotlin.text.m("<Connection\\b([^>]*)/?\\s*>", 0);
    public static final int $stable = 8;

    private HomeServerRegexes() {
    }

    public final kotlin.text.m getARTICLES_REGEX() {
        return ARTICLES_REGEX;
    }

    public final kotlin.text.m getCONNECTION_ID_SANITIZER_REGEX() {
        return CONNECTION_ID_SANITIZER_REGEX;
    }

    public final kotlin.text.m getDIACRITICS_REGEX() {
        return DIACRITICS_REGEX;
    }

    public final kotlin.text.m getMULTI_SPACE_REGEX() {
        return MULTI_SPACE_REGEX;
    }

    public final kotlin.text.m getNON_ALPHA_NUM_REGEX() {
        return NON_ALPHA_NUM_REGEX;
    }

    public final kotlin.text.m getNON_ALPHA_NUM_STRICT_REGEX() {
        return NON_ALPHA_NUM_STRICT_REGEX;
    }

    public final kotlin.text.m getPLEX_CONNECTION_REGEX() {
        return PLEX_CONNECTION_REGEX;
    }

    public final kotlin.text.m getPLEX_DEVICE_REGEX() {
        return PLEX_DEVICE_REGEX;
    }
}
