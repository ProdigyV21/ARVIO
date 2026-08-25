package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\bR\u0017\u0010\r\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\u0006\u001a\u0004\b\u000e\u0010\bR\u0017\u0010\u000f\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0006\u001a\u0004\b\u0010\u0010\b¨\u0006\u0011"}, d2 = {"Lcom/arflix/tv/data/repository/IptvRepositoryRegexes;", "", "<init>", "()V", "Lkotlin/text/m;", "DURATION_PLACEHOLDER_REGEX", "Lkotlin/text/m;", "getDURATION_PLACEHOLDER_REGEX", "()Lkotlin/text/m;", "IPTV_URL_REDACT_SECRETS_REGEX", "getIPTV_URL_REDACT_SECRETS_REGEX", "IPTV_URL_REDACT_PATH_REGEX", "getIPTV_URL_REDACT_PATH_REGEX", "RESOLUTION_TAG_REGEX", "getRESOLUTION_TAG_REGEX", "BRACKET_PAREN_REGEX", "getBRACKET_PAREN_REGEX", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class IptvRepositoryRegexes {
    public static final IptvRepositoryRegexes INSTANCE = new IptvRepositoryRegexes();
    private static final kotlin.text.m DURATION_PLACEHOLDER_REGEX = new kotlin.text.m("\\$\\{duration:(\\d+)\\}|\\{duration:(\\d+)\\}");
    private static final kotlin.text.m IPTV_URL_REDACT_SECRETS_REGEX = new kotlin.text.m("(?i)([?&](?:username|user|uname|password|pass|pwd)=)[^&]+");
    private static final kotlin.text.m IPTV_URL_REDACT_PATH_REGEX = new kotlin.text.m("(?i)(/(?:live|movie|series|timeshift)/)([^/]+)/([^/]+)(/)");
    private static final kotlin.text.m RESOLUTION_TAG_REGEX = new kotlin.text.m("\\b(4K|UHD|FHD|HD|SD|2160P?|1080P?|720P?|576P?|480P?)\\b", 0);
    private static final kotlin.text.m BRACKET_PAREN_REGEX = new kotlin.text.m("\\[[^\\]]*]|\\([^)]*\\)");

    private IptvRepositoryRegexes() {
    }

    public final kotlin.text.m getBRACKET_PAREN_REGEX() {
        return BRACKET_PAREN_REGEX;
    }

    public final kotlin.text.m getDURATION_PLACEHOLDER_REGEX() {
        return DURATION_PLACEHOLDER_REGEX;
    }

    public final kotlin.text.m getIPTV_URL_REDACT_PATH_REGEX() {
        return IPTV_URL_REDACT_PATH_REGEX;
    }

    public final kotlin.text.m getIPTV_URL_REDACT_SECRETS_REGEX() {
        return IPTV_URL_REDACT_SECRETS_REGEX;
    }

    public final kotlin.text.m getRESOLUTION_TAG_REGEX() {
        return RESOLUTION_TAG_REGEX;
    }
}
