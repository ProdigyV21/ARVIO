package com.arflix.tv.ui.screens.tv.live;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\bR\u0017\u0010\r\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\u0006\u001a\u0004\b\u000e\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/LiveTvScreenRegexes;", "", "<init>", "()V", "Lkotlin/text/m;", "IPTV_URL_REDACT_REGEX", "Lkotlin/text/m;", "getIPTV_URL_REDACT_REGEX", "()Lkotlin/text/m;", "QUALITY_REMOVAL", "getQUALITY_REMOVAL", "MULTI_SPACE", "getMULTI_SPACE", "QUERY_SECRETS", "getQUERY_SECRETS", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class LiveTvScreenRegexes {
    public static final LiveTvScreenRegexes INSTANCE = new LiveTvScreenRegexes();
    private static final kotlin.text.m IPTV_URL_REDACT_REGEX = new kotlin.text.m("(?i)(/(?:live|movie|series|timeshift)/)([^/]+)/([^/]+)(/)");
    private static final kotlin.text.m QUALITY_REMOVAL = new kotlin.text.m("(?i)\\b(?:4k|uhd|fhd|hd|sd|1080p|720p|60fps)\\b");
    private static final kotlin.text.m MULTI_SPACE = new kotlin.text.m("\\s+");
    private static final kotlin.text.m QUERY_SECRETS = new kotlin.text.m("(?i)([?&](?:username|user|uname|password|pass|pwd)=)[^&]+");

    private LiveTvScreenRegexes() {
    }

    public final kotlin.text.m getIPTV_URL_REDACT_REGEX() {
        return IPTV_URL_REDACT_REGEX;
    }

    public final kotlin.text.m getMULTI_SPACE() {
        return MULTI_SPACE;
    }

    public final kotlin.text.m getQUALITY_REMOVAL() {
        return QUALITY_REMOVAL;
    }

    public final kotlin.text.m getQUERY_SECRETS() {
        return QUERY_SECRETS;
    }
}
