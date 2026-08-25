package org.jsoup.helper;

import java.io.UnsupportedEncodingException;
import java.net.IDN;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import org.jsoup.Connection;
import org.jsoup.internal.StringUtil;

/* JADX INFO: loaded from: classes5.dex */
final class UrlBuilder {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    StringBuilder f21006q;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    URL f21007u;

    public UrlBuilder(URL url) {
        this.f21007u = url;
        if (url.getQuery() != null) {
            StringBuilder sbBorrowBuilder = StringUtil.borrowBuilder();
            sbBorrowBuilder.append(this.f21007u.getQuery());
            this.f21006q = sbBorrowBuilder;
        }
    }

    private static void appendToAscii(String str, boolean z, StringBuilder sb2) throws UnsupportedEncodingException {
        int i10 = 0;
        while (i10 < str.length()) {
            int iCodePointAt = str.codePointAt(i10);
            if (iCodePointAt == 32) {
                sb2.append(z ? '+' : "%20");
            } else if (iCodePointAt > 127) {
                sb2.append(URLEncoder.encode(new String(Character.toChars(iCodePointAt)), DataUtil.UTF_8.name()));
                if (Character.charCount(iCodePointAt) == 2) {
                    i10++;
                }
            } else {
                sb2.append((char) iCodePointAt);
            }
            i10++;
        }
    }

    private static String decodePart(String str) {
        try {
            return URLDecoder.decode(str, DataUtil.UTF_8.name());
        } catch (UnsupportedEncodingException e5) {
            throw new RuntimeException(e5);
        }
    }

    public void appendKeyVal(Connection.KeyVal keyVal) throws UnsupportedEncodingException {
        StringBuilder sb2 = this.f21006q;
        if (sb2 == null) {
            this.f21006q = StringUtil.borrowBuilder();
        } else {
            sb2.append('&');
        }
        StringBuilder sb3 = this.f21006q;
        String strKey = keyVal.key();
        Charset charset = DataUtil.UTF_8;
        sb3.append(URLEncoder.encode(strKey, charset.name()));
        sb3.append('=');
        sb3.append(URLEncoder.encode(keyVal.value(), charset.name()));
    }

    public URL build() {
        try {
            URI uri = new URI(this.f21007u.getProtocol(), this.f21007u.getUserInfo(), IDN.toASCII(decodePart(this.f21007u.getHost())), this.f21007u.getPort(), null, null, null);
            StringBuilder sbBorrowBuilder = StringUtil.borrowBuilder();
            sbBorrowBuilder.append(uri.toASCIIString());
            appendToAscii(this.f21007u.getPath(), false, sbBorrowBuilder);
            if (this.f21006q != null) {
                sbBorrowBuilder.append('?');
                appendToAscii(StringUtil.releaseBuilder(this.f21006q), true, sbBorrowBuilder);
            }
            if (this.f21007u.getRef() != null) {
                sbBorrowBuilder.append('#');
                appendToAscii(this.f21007u.getRef(), false, sbBorrowBuilder);
            }
            URL url = new URL(StringUtil.releaseBuilder(sbBorrowBuilder));
            this.f21007u = url;
            return url;
        } catch (UnsupportedEncodingException | MalformedURLException | URISyntaxException unused) {
            return this.f21007u;
        }
    }
}
