package com.arflix.tv.data.api;

import kotlin.Metadata;
import kotlin.text.m;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\bR\u0017\u0010\r\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\u0006\u001a\u0004\b\u000e\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/arflix/tv/data/api/StreamApiRegexes;", "", "<init>", "()V", "Lkotlin/text/m;", "QUALITY_TAGS_REGEX", "Lkotlin/text/m;", "getQUALITY_TAGS_REGEX", "()Lkotlin/text/m;", "EMOJI_SIZE_REGEX", "getEMOJI_SIZE_REGEX", "PLAIN_SIZE_REGEX", "getPLAIN_SIZE_REGEX", "SEEDER_REGEX", "getSEEDER_REGEX", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class StreamApiRegexes {
    public static final StreamApiRegexes INSTANCE = new StreamApiRegexes();
    private static final m QUALITY_TAGS_REGEX = new m(".*\\[.*\\].*");
    private static final m EMOJI_SIZE_REGEX = new m("💾\\s*([\\d.]+\\s*[GMKT]B)", 0);
    private static final m PLAIN_SIZE_REGEX = new m("(\\d+\\.?\\d*)\\s*(GB|MB|TB|KB)", 0);
    private static final m SEEDER_REGEX = new m("👤\\s*(\\d+)");

    private StreamApiRegexes() {
    }

    public final m getEMOJI_SIZE_REGEX() {
        return EMOJI_SIZE_REGEX;
    }

    public final m getPLAIN_SIZE_REGEX() {
        return PLAIN_SIZE_REGEX;
    }

    public final m getQUALITY_TAGS_REGEX() {
        return QUALITY_TAGS_REGEX;
    }

    public final m getSEEDER_REGEX() {
        return SEEDER_REGEX;
    }
}
