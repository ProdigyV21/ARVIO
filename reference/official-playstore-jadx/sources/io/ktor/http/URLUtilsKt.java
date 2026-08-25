package io.ktor.http;

import androidx.compose.foundation.c;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import io.ktor.util.StringValuesKt;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.s;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import kotlin.text.o;
import kotlin.text.u;
import r7.l;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\u001a\u0015\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0015\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0003\u0010\u0007\u001a\u0015\u0010\b\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u0015\u0010\b\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\u000b\u001a\u0015\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\f\u001a\u0019\u0010\r\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005¢\u0006\u0004\b\r\u0010\u000e\u001a\u0019\u0010\r\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\n\u001a\u00020\u0002¢\u0006\u0004\b\r\u0010\u000f\u001a/\u0010\u0017\u001a\u00020\u0016*\u00060\u0010j\u0002`\u00112\u0006\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0014H\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a-\u0010\u0017\u001a\u00020\u0016*\u00060\u0010j\u0002`\u00112\u0006\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0017\u0010\u001b\u001a+\u0010 \u001a\u00020\u0016*\u00060\u001cj\u0002`\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u00002\b\u0010\u001f\u001a\u0004\u0018\u00010\u0000H\u0000¢\u0006\u0004\b \u0010!\"\u0015\u0010$\u001a\u00020\u0000*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\"\u0010#\"\u0015\u0010&\u001a\u00020\u0000*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b%\u0010#\"\u0015\u0010'\u001a\u00020\u0014*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b'\u0010(\"\u0015\u0010)\u001a\u00020\u0014*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b)\u0010(\"\u0015\u0010'\u001a\u00020\u0014*\u00020\u00058F¢\u0006\u0006\u001a\u0004\b'\u0010*\"\u0015\u0010)\u001a\u00020\u0014*\u00020\u00058F¢\u0006\u0006\u001a\u0004\b)\u0010*¨\u0006+"}, d2 = {"", "urlString", "Lio/ktor/http/Url;", "Url", "(Ljava/lang/String;)Lio/ktor/http/Url;", "Lio/ktor/http/URLBuilder;", "builder", "(Lio/ktor/http/URLBuilder;)Lio/ktor/http/Url;", "URLBuilder", "(Ljava/lang/String;)Lio/ktor/http/URLBuilder;", "url", "(Lio/ktor/http/Url;)Lio/ktor/http/URLBuilder;", "(Lio/ktor/http/URLBuilder;)Lio/ktor/http/URLBuilder;", "takeFrom", "(Lio/ktor/http/URLBuilder;Lio/ktor/http/URLBuilder;)Lio/ktor/http/URLBuilder;", "(Lio/ktor/http/URLBuilder;Lio/ktor/http/Url;)Lio/ktor/http/URLBuilder;", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "encodedPath", "encodedQuery", "", "trailingQuery", "Lx6/t0;", "appendUrlFullPath", "(Ljava/lang/Appendable;Ljava/lang/String;Ljava/lang/String;Z)V", "Lio/ktor/http/ParametersBuilder;", "encodedQueryParameters", "(Ljava/lang/Appendable;Ljava/lang/String;Lio/ktor/http/ParametersBuilder;Z)V", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "encodedUser", "encodedPassword", "appendUserAndPassword", "(Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)V", "getFullPath", "(Lio/ktor/http/Url;)Ljava/lang/String;", "fullPath", "getHostWithPort", "hostWithPort", "isAbsolutePath", "(Lio/ktor/http/Url;)Z", "isRelativePath", "(Lio/ktor/http/URLBuilder;)Z", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class URLUtilsKt {

    /* JADX INFO: renamed from: io.ktor.http.URLUtilsKt$appendUrlFullPath$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u00032\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lx6/x;", "", "it", "", "invoke", "(Lx6/x;)Ljava/lang/CharSequence;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<x, CharSequence> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        @Override // r7.l
        public final CharSequence invoke(x xVar) {
            String str = (String) xVar.f22608i;
            Object obj = xVar.f22609l;
            return obj == null ? str : c.m('=', str, String.valueOf(obj));
        }
    }

    public static final URLBuilder URLBuilder(String str) {
        return URLParserKt.takeFrom(new URLBuilder(null, null, 0, null, null, null, null, null, false, 511, null), str);
    }

    public static final Url Url(String str) {
        return URLBuilder(str).build();
    }

    public static final void appendUrlFullPath(Appendable appendable, String str, String str2, boolean z) throws IOException {
        if (!o.h0(str) && !u.P(str, DomExceptionUtils.SEPARATOR, false)) {
            appendable.append('/');
        }
        appendable.append(str);
        if (str2.length() > 0 || z) {
            appendable.append("?");
        }
        appendable.append(str2);
    }

    public static final void appendUserAndPassword(StringBuilder sb2, String str, String str2) {
        if (str == null) {
            return;
        }
        sb2.append(str);
        if (str2 != null) {
            sb2.append(':');
            sb2.append(str2);
        }
        sb2.append("@");
    }

    public static final String getFullPath(Url url) throws IOException {
        StringBuilder sb2 = new StringBuilder();
        appendUrlFullPath(sb2, url.getEncodedPath(), url.getEncodedQuery(), url.getTrailingQuery());
        return sb2.toString();
    }

    public static final String getHostWithPort(Url url) {
        return url.getHost() + ':' + url.getPort();
    }

    public static final boolean isAbsolutePath(Url url) {
        return p.a(kotlin.collections.x.o0(url.getPathSegments()), "");
    }

    public static final boolean isRelativePath(Url url) {
        return !isAbsolutePath(url);
    }

    public static final URLBuilder takeFrom(URLBuilder uRLBuilder, URLBuilder uRLBuilder2) {
        uRLBuilder.setProtocol(uRLBuilder2.getProtocol());
        uRLBuilder.setHost(uRLBuilder2.getHost());
        uRLBuilder.setPort(uRLBuilder2.getPort());
        uRLBuilder.setEncodedPathSegments(uRLBuilder2.getEncodedPathSegments());
        uRLBuilder.setEncodedUser(uRLBuilder2.getEncodedUser());
        uRLBuilder.setEncodedPassword(uRLBuilder2.getEncodedPassword());
        ParametersBuilder parametersBuilderParametersBuilder$default = ParametersKt.ParametersBuilder$default(0, 1, null);
        StringValuesKt.appendAll(parametersBuilderParametersBuilder$default, uRLBuilder2.getEncodedParameters());
        uRLBuilder.setEncodedParameters(parametersBuilderParametersBuilder$default);
        uRLBuilder.setEncodedFragment(uRLBuilder2.getEncodedFragment());
        uRLBuilder.setTrailingQuery(uRLBuilder2.getTrailingQuery());
        return uRLBuilder;
    }

    public static final URLBuilder URLBuilder(Url url) {
        return takeFrom(new URLBuilder(null, null, 0, null, null, null, null, null, false, 511, null), url);
    }

    public static final Url Url(URLBuilder uRLBuilder) {
        return takeFrom(new URLBuilder(null, null, 0, null, null, null, null, null, false, 511, null), uRLBuilder).build();
    }

    public static final boolean isAbsolutePath(URLBuilder uRLBuilder) {
        return p.a(kotlin.collections.x.o0(uRLBuilder.getPathSegments()), "");
    }

    public static final boolean isRelativePath(URLBuilder uRLBuilder) {
        return !isAbsolutePath(uRLBuilder);
    }

    public static final URLBuilder URLBuilder(URLBuilder uRLBuilder) {
        return takeFrom(new URLBuilder(null, null, 0, null, null, null, null, null, false, 511, null), uRLBuilder);
    }

    public static final void appendUrlFullPath(Appendable appendable, String str, ParametersBuilder parametersBuilder, boolean z) {
        List listSingletonList;
        if (!o.h0(str) && !u.P(str, DomExceptionUtils.SEPARATOR, false)) {
            appendable.append('/');
        }
        appendable.append(str);
        if (!parametersBuilder.isEmpty() || z) {
            appendable.append("?");
        }
        Set<Map.Entry<String, List<String>>> setEntries = parametersBuilder.entries();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = setEntries.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String str2 = (String) entry.getKey();
            List list = (List) entry.getValue();
            if (list.isEmpty()) {
                listSingletonList = Collections.singletonList(new x(str2, null));
            } else {
                ArrayList arrayList2 = new ArrayList(s.U(list, 10));
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(new x(str2, (String) it2.next()));
                }
                listSingletonList = arrayList2;
            }
            kotlin.collections.x.b0(arrayList, listSingletonList);
        }
        kotlin.collections.x.t0(arrayList, appendable, "&", null, null, AnonymousClass2.INSTANCE, 60);
    }

    public static final URLBuilder takeFrom(URLBuilder uRLBuilder, Url url) {
        uRLBuilder.setProtocol(url.getProtocol());
        uRLBuilder.setHost(url.getHost());
        uRLBuilder.setPort(url.getPort());
        URLBuilderKt.setEncodedPath(uRLBuilder, url.getEncodedPath());
        uRLBuilder.setEncodedUser(url.getEncodedUser());
        uRLBuilder.setEncodedPassword(url.getEncodedPassword());
        ParametersBuilder parametersBuilderParametersBuilder$default = ParametersKt.ParametersBuilder$default(0, 1, null);
        parametersBuilderParametersBuilder$default.appendAll(QueryKt.parseQueryString$default(url.getEncodedQuery(), 0, 0, false, 6, null));
        uRLBuilder.setEncodedParameters(parametersBuilderParametersBuilder$default);
        uRLBuilder.setEncodedFragment(url.getEncodedFragment());
        uRLBuilder.setTrailingQuery(url.getTrailingQuery());
        return uRLBuilder;
    }
}
