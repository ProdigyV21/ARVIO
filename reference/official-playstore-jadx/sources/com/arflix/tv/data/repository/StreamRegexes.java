package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\b¨\u0006\r"}, d2 = {"Lcom/arflix/tv/data/repository/StreamRegexes;", "", "<init>", "()V", "Lkotlin/text/m;", "MANIFEST_TYPO_REGEX", "Lkotlin/text/m;", "getMANIFEST_TYPO_REGEX", "()Lkotlin/text/m;", "NUVIO_REGEX", "getNUVIO_REGEX", "SIZE_REGEX", "getSIZE_REGEX", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class StreamRegexes {
    public static final StreamRegexes INSTANCE = new StreamRegexes();
    private static final kotlin.text.m MANIFEST_TYPO_REGEX = new kotlin.text.m("(?i)/manifest\\.json[a-z0-9_-]+(?=($|[?]))");
    private static final kotlin.text.m NUVIO_REGEX = new kotlin.text.m("nuvio", 0);
    private static final kotlin.text.m SIZE_REGEX = new kotlin.text.m("([\\d.]+)\\s*(GB|MB|KB|TB)", 0);

    private StreamRegexes() {
    }

    public final kotlin.text.m getMANIFEST_TYPO_REGEX() {
        return MANIFEST_TYPO_REGEX;
    }

    public final kotlin.text.m getNUVIO_REGEX() {
        return NUVIO_REGEX;
    }

    public final kotlin.text.m getSIZE_REGEX() {
        return SIZE_REGEX;
    }
}
