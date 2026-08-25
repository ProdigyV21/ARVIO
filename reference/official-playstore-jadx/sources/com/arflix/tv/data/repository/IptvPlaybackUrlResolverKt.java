package com.arflix.tv.data.repository;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0010\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u000e\u0010\u0005\u001a\u00020\u0001*\u0004\u0018\u00010\u0003H\u0002\u001a\u000e\u0010\u0006\u001a\u00020\u0001*\u0004\u0018\u00010\u0003H\u0002¨\u0006\u0007"}, d2 = {"shouldResolveIptvPlaybackRedirect", "", "url", "", "looksLikeHlsPlaybackUrl", "isHlsContentType", "isDirectMediaContentType", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class IptvPlaybackUrlResolverKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isDirectMediaContentType(String str) {
        if (str == null) {
            str = "";
        }
        String lowerCase = str.toLowerCase(Locale.US);
        String string = kotlin.text.o.L0(kotlin.text.o.G0(';', lowerCase, lowerCase)).toString();
        return kotlin.text.u.P(string, "video/", false) || kotlin.text.u.P(string, "audio/", false) || string.equals("application/octet-stream");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isHlsContentType(String str) {
        if (str == null) {
            str = "";
        }
        String lowerCase = str.toLowerCase(Locale.US);
        return kotlin.text.o.T(lowerCase, "mpegurl", false) || kotlin.text.o.T(lowerCase, "vnd.apple.mpegurl", false);
    }

    public static final boolean looksLikeHlsPlaybackUrl(String str) {
        String lowerCase = str.toLowerCase(Locale.US);
        String strG0 = kotlin.text.o.G0('?', lowerCase, lowerCase);
        String strG02 = kotlin.text.o.G0('#', strG0, strG0);
        return kotlin.text.u.K(strG02, ".m3u8", false) || kotlin.text.o.T(strG02, "/hls/", false) || kotlin.text.o.T(lowerCase, "output=m3u8", false) || kotlin.text.o.T(lowerCase, "format=hls", false);
    }

    public static final boolean shouldResolveIptvPlaybackRedirect(String str) {
        URI uri;
        String string = kotlin.text.o.L0(str).toString();
        if ((kotlin.text.u.P(string, "http://", true) || kotlin.text.u.P(string, "https://", true)) && !looksLikeHlsPlaybackUrl(string)) {
            try {
                uri = new URI(string);
            } catch (Exception unused) {
                uri = null;
            }
            if (uri != null) {
                String path = uri.getPath();
                if (path == null) {
                    path = "";
                }
                String lowerCase = kotlin.text.o.O0(path, '/').toLowerCase(Locale.US);
                String strE0 = kotlin.text.o.E0('/', lowerCase, lowerCase);
                if (!kotlin.text.o.h0(strE0) && !kotlin.text.o.U(strE0, '.')) {
                    List listX0 = kotlin.text.o.x0(kotlin.text.o.M0(lowerCase, '/'), new char[]{'/'}, 6);
                    ArrayList arrayList = new ArrayList();
                    for (Object obj : listX0) {
                        if (!kotlin.text.o.h0((String) obj)) {
                            arrayList.add(obj);
                        }
                    }
                    return arrayList.size() >= 4 && kotlin.text.u.L((String) kotlin.collections.x.m0(arrayList), "live", true) && kotlin.text.u.S(strE0) == null;
                }
            }
        }
        return false;
    }
}
