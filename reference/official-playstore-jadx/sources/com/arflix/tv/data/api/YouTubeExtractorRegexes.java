package com.arflix.tv.data.api;

import kotlin.Metadata;
import kotlin.text.m;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\bR\u0017\u0010\r\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\u0006\u001a\u0004\b\u000e\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/arflix/tv/data/api/YouTubeExtractorRegexes;", "", "<init>", "()V", "Lkotlin/text/m;", "VIDEO_ID_REGEX", "Lkotlin/text/m;", "getVIDEO_ID_REGEX", "()Lkotlin/text/m;", "API_KEY_REGEX", "getAPI_KEY_REGEX", "VISITOR_DATA_REGEX", "getVISITOR_DATA_REGEX", "QUALITY_LABEL_REGEX", "getQUALITY_LABEL_REGEX", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class YouTubeExtractorRegexes {
    public static final YouTubeExtractorRegexes INSTANCE = new YouTubeExtractorRegexes();
    private static final m VIDEO_ID_REGEX = new m("^[a-zA-Z0-9_-]{11}$");
    private static final m API_KEY_REGEX = new m("\"INNERTUBE_API_KEY\":\"([^\"]+)\"");
    private static final m VISITOR_DATA_REGEX = new m("\"VISITOR_DATA\":\"([^\"]+)\"");
    private static final m QUALITY_LABEL_REGEX = new m("(\\d{2,4})p");

    private YouTubeExtractorRegexes() {
    }

    public final m getAPI_KEY_REGEX() {
        return API_KEY_REGEX;
    }

    public final m getQUALITY_LABEL_REGEX() {
        return QUALITY_LABEL_REGEX;
    }

    public final m getVIDEO_ID_REGEX() {
        return VIDEO_ID_REGEX;
    }

    public final m getVISITOR_DATA_REGEX() {
        return VISITOR_DATA_REGEX;
    }
}
