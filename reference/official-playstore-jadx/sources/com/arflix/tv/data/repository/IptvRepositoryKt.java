package com.arflix.tv.data.repository;

import io.ktor.http.ContentDisposition;
import java.util.Locale;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"LargeIptvListChannelCount", "", "IPTV_GROUP_ORDER_SCHEMA", "normalizeIptvSortOrder", "", "value", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class IptvRepositoryKt {
    public static final int IPTV_GROUP_ORDER_SCHEMA = 3;
    private static final int LargeIptvListChannelCount = 10000;

    public static final String normalizeIptvSortOrder(String str) {
        String string;
        String lowerCase = (str == null || (string = kotlin.text.o.L0(str).toString()) == null) ? null : string.toLowerCase(Locale.ROOT);
        return kotlin.jvm.internal.p.a(lowerCase, "number") ? "number" : kotlin.jvm.internal.p.a(lowerCase, ContentDisposition.Parameters.Name) ? ContentDisposition.Parameters.Name : "provider";
    }
}
