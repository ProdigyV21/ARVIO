package io.sentry;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes4.dex */
public final class a0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Pattern f16294e = Pattern.compile("^o(\\d+)\\.");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f16295a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f16296b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final URI f16297c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f16298d;

    public a0(String str) {
        try {
            ac.b.V(str, "The DSN is required.");
            String strTrim = str.trim();
            if (strTrim.isEmpty()) {
                throw new IllegalArgumentException("The DSN is empty.");
            }
            URI uriNormalize = new URI(strTrim).normalize();
            String scheme = uriNormalize.getScheme();
            if (!"http".equalsIgnoreCase(scheme) && !"https".equalsIgnoreCase(scheme)) {
                throw new IllegalArgumentException("Invalid DSN scheme: " + scheme);
            }
            String userInfo = uriNormalize.getUserInfo();
            if (userInfo == null || userInfo.isEmpty()) {
                throw new IllegalArgumentException("Invalid DSN: No public key provided.");
            }
            String[] strArrSplit = userInfo.split(":", -1);
            String str2 = strArrSplit[0];
            this.f16296b = str2;
            if (str2 == null || str2.isEmpty()) {
                throw new IllegalArgumentException("Invalid DSN: No public key provided.");
            }
            String strGroup = null;
            this.f16295a = strArrSplit.length > 1 ? strArrSplit[1] : null;
            String path = uriNormalize.getPath();
            path = path.endsWith(DomExceptionUtils.SEPARATOR) ? path.substring(0, path.length() - 1) : path;
            int iLastIndexOf = path.lastIndexOf(DomExceptionUtils.SEPARATOR) + 1;
            String strSubstring = path.substring(0, iLastIndexOf);
            strSubstring = strSubstring.endsWith(DomExceptionUtils.SEPARATOR) ? strSubstring : strSubstring.concat(DomExceptionUtils.SEPARATOR);
            String strSubstring2 = path.substring(iLastIndexOf);
            if (strSubstring2.isEmpty()) {
                throw new IllegalArgumentException("Invalid DSN: A Project Id is required.");
            }
            this.f16297c = new URI(scheme, null, uriNormalize.getHost(), uriNormalize.getPort(), strSubstring + "api/" + strSubstring2, null, null);
            String host = uriNormalize.getHost();
            if (host != null) {
                Matcher matcher = f16294e.matcher(host);
                if (matcher.find()) {
                    strGroup = matcher.group(1);
                }
            }
            this.f16298d = strGroup;
        } catch (Throwable th) {
            throw new IllegalArgumentException(th);
        }
    }
}
