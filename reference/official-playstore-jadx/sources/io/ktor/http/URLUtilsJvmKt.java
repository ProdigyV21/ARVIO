package io.ktor.http;

import java.net.URI;
import java.net.URL;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.p;
import kotlin.text.o;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0012\u0010\u0004\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0012\u0010\u0004\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007\u001a\n\u0010\b\u001a\u00020\u0003*\u00020\u0001¨\u0006\t"}, d2 = {"Url", "Lio/ktor/http/Url;", "uri", "Ljava/net/URI;", "takeFrom", "Lio/ktor/http/URLBuilder;", "url", "Ljava/net/URL;", "toURI", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class URLUtilsJvmKt {
    public static final Url Url(URI uri) {
        return takeFrom(new URLBuilder(null, null, 0, null, null, null, null, null, false, 511, null), uri).build();
    }

    public static final URLBuilder takeFrom(URLBuilder uRLBuilder, URI uri) {
        String scheme = uri.getScheme();
        if (scheme != null) {
            uRLBuilder.setProtocol(URLProtocol.INSTANCE.createOrDefault(scheme));
            uRLBuilder.setPort(uRLBuilder.getProtocol().getDefaultPort());
        }
        if (uri.getPort() > 0) {
            uRLBuilder.setPort(uri.getPort());
        } else {
            String scheme2 = uri.getScheme();
            if (p.a(scheme2, "http")) {
                uRLBuilder.setPort(80);
            } else if (p.a(scheme2, "https")) {
                uRLBuilder.setPort(443);
            }
        }
        if (uri.getRawUserInfo() != null && uri.getRawUserInfo().length() > 0) {
            List listY0 = o.y0(uri.getRawUserInfo(), new String[]{":"}, 0, 6);
            uRLBuilder.setEncodedUser((String) x.m0(listY0));
            uRLBuilder.setEncodedPassword((String) x.p0(1, listY0));
        }
        String host = uri.getHost();
        if (host != null) {
            uRLBuilder.setHost(host);
        }
        URLBuilderKt.setEncodedPath(uRLBuilder, uri.getRawPath());
        String rawQuery = uri.getRawQuery();
        if (rawQuery != null) {
            ParametersBuilder parametersBuilderParametersBuilder$default = ParametersKt.ParametersBuilder$default(0, 1, null);
            parametersBuilderParametersBuilder$default.appendAll(QueryKt.parseQueryString$default(rawQuery, 0, 0, false, 6, null));
            uRLBuilder.setEncodedParameters(parametersBuilderParametersBuilder$default);
        }
        String query = uri.getQuery();
        if (query != null && query.length() == 0) {
            uRLBuilder.setTrailingQuery(true);
        }
        String rawFragment = uri.getRawFragment();
        if (rawFragment != null) {
            uRLBuilder.setEncodedFragment(rawFragment);
        }
        return uRLBuilder;
    }

    public static final URI toURI(Url url) {
        return new URI(url.getUrlString());
    }

    public static final URLBuilder takeFrom(URLBuilder uRLBuilder, URL url) {
        return o.U(url.getHost(), '_') ? URLParserKt.takeFrom(uRLBuilder, url.toString()) : takeFrom(uRLBuilder, url.toURI());
    }
}
