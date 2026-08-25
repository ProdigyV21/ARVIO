package com.arflix.tv.data.repository;

import android.util.Base64;
import com.google.common.util.concurrent.r0;
import java.nio.charset.StandardCharsets;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0000\u001a\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0000\u001a\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0002¨\u0006\u0006"}, d2 = {"normalizeIptvStreamUrl", "", "raw", "normalizeIptvLogoUrlOrNull", "decodeLegacyHttpUrl", "value", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class IptvUrlNormalizerKt {
    private static final String decodeLegacyHttpUrl(String str) {
        Object obj = null;
        if (str.length() < 12) {
            return null;
        }
        for (int i10 = 0; i10 < str.length(); i10++) {
            if (r0.u(str.charAt(i10))) {
                return null;
            }
        }
        ga.h hVar = new ga.h(ga.r.Q(new ga.p(t7.a.E(0, 10), 3), new x(str, 5)));
        while (hVar.hasNext()) {
            Object next = hVar.next();
            String str2 = (String) next;
            if (kotlin.text.u.P(str2, "http://", true) || kotlin.text.u.P(str2, "https://", true)) {
                obj = next;
                break;
            }
        }
        return (String) obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String decodeLegacyHttpUrl$lambda$1(String str, int i10) {
        try {
            return kotlin.text.o.L0(new String(Base64.decode(str, i10), StandardCharsets.UTF_8)).toString();
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public static final String normalizeIptvLogoUrlOrNull(String str) {
        String string = str != null ? kotlin.text.o.L0(str).toString() : null;
        if (string == null) {
            string = "";
        }
        if (!kotlin.text.o.h0(string)) {
            if (!kotlin.text.u.P(string, "http://", true) && !kotlin.text.u.P(string, "https://", true)) {
                string = kotlin.text.u.P(string, "//", false) ? "https:".concat(string) : decodeLegacyHttpUrl(string);
            }
            if (string != null && (kotlin.text.u.P(string, "http://", true) || kotlin.text.u.P(string, "https://", true))) {
                return string;
            }
        }
        return null;
    }

    public static final String normalizeIptvStreamUrl(String str) {
        String strDecodeLegacyHttpUrl;
        String string = kotlin.text.o.L0(str).toString();
        return kotlin.text.o.h0(string) ? "" : kotlin.text.u.P(string, "//", false) ? "https:".concat(string) : (kotlin.text.o.T(string, "://", true) || (strDecodeLegacyHttpUrl = decodeLegacyHttpUrl(string)) == null) ? string : strDecodeLegacyHttpUrl;
    }
}
