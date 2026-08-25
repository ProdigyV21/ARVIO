package io.ktor.http;

import kotlin.Metadata;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\u001aj\u0010\t\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00012\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0007\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0018\u0010\u0005\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004\"\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0004¨\u0006\u0017"}, d2 = {"authority", "", "Lio/ktor/http/Url;", "getAuthority", "(Lio/ktor/http/Url;)Ljava/lang/String;", "encodedUserAndPassword", "getEncodedUserAndPassword", "protocolWithAuthority", "getProtocolWithAuthority", "copy", "protocol", "Lio/ktor/http/URLProtocol;", "host", "specifiedPort", "", "encodedPath", "parameters", "Lio/ktor/http/Parameters;", "fragment", "user", "password", "trailingQuery", "", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UrlKt {
    @e
    public static final Url copy(Url url, URLProtocol uRLProtocol, String str, int i10, String str2, Parameters parameters, String str3, String str4, String str5, boolean z) {
        throw new IllegalStateException("Please use URLBuilder(url)");
    }

    public static /* synthetic */ Url copy$default(Url url, URLProtocol uRLProtocol, String str, int i10, String str2, Parameters parameters, String str3, String str4, String str5, boolean z, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            uRLProtocol = url.getProtocol();
        }
        if ((i11 & 2) != 0) {
            str = url.getHost();
        }
        if ((i11 & 4) != 0) {
            i10 = url.getSpecifiedPort();
        }
        if ((i11 & 8) != 0) {
            str2 = url.getEncodedPath();
        }
        if ((i11 & 16) != 0) {
            parameters = url.getParameters();
        }
        if ((i11 & 32) != 0) {
            str3 = url.getFragment();
        }
        if ((i11 & 64) != 0) {
            str4 = url.getUser();
        }
        if ((i11 & 128) != 0) {
            str5 = url.getPassword();
        }
        if ((i11 & 256) != 0) {
            z = url.getTrailingQuery();
        }
        String str6 = str5;
        boolean z5 = z;
        String str7 = str3;
        String str8 = str4;
        Parameters parameters2 = parameters;
        int i12 = i10;
        return copy(url, uRLProtocol, str, i12, str2, parameters2, str7, str8, str6, z5);
    }

    public static final String getAuthority(Url url) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getEncodedUserAndPassword(url));
        if (url.getSpecifiedPort() == 0 || url.getSpecifiedPort() == url.getProtocol().getDefaultPort()) {
            sb2.append(url.getHost());
        } else {
            sb2.append(URLUtilsKt.getHostWithPort(url));
        }
        return sb2.toString();
    }

    public static final String getEncodedUserAndPassword(Url url) {
        StringBuilder sb2 = new StringBuilder();
        URLUtilsKt.appendUserAndPassword(sb2, url.getEncodedUser(), url.getEncodedPassword());
        return sb2.toString();
    }

    public static final String getProtocolWithAuthority(Url url) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(url.getProtocol().getName());
        sb2.append("://");
        sb2.append(getEncodedUserAndPassword(url));
        if (url.getSpecifiedPort() == 0 || url.getSpecifiedPort() == url.getProtocol().getDefaultPort()) {
            sb2.append(url.getHost());
        } else {
            sb2.append(URLUtilsKt.getHostWithPort(url));
        }
        return sb2.toString();
    }
}
