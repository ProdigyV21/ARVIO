package io.ktor.http;

import androidx.fragment.app.a2;
import io.ktor.http.LinkHeader;
import io.ktor.http.auth.HttpAuthHeader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.s;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.text.o;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0019\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001b\u0010\b\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\b\u0010\t\u001a!\u0010\f\u001a\u0004\u0018\u00010\u0003*\u00020\u00002\n\u0010\f\u001a\u00060\nj\u0002`\u000bH\u0007¢\u0006\u0004\b\f\u0010\r\u001a\u0019\u0010\u000f\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0006¢\u0006\u0004\b\u000f\u0010\t\u001a\u0019\u0010\u0012\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u0019\u0010\u0015\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0010¢\u0006\u0004\b\u0015\u0010\u0013\u001a\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0001*\u00020\u0000¢\u0006\u0004\b\u0004\u0010\u0016\u001a\u0019\u0010\f\u001a\n\u0018\u00010\nj\u0004\u0018\u0001`\u000b*\u00020\u0000¢\u0006\u0004\b\f\u0010\u0017\u001a\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0010*\u00020\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u0019\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u001a*\u00020\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u0013\u0010\b\u001a\u0004\u0018\u00010\u001d*\u00020\u0000¢\u0006\u0004\b\b\u0010\u001e\u001a\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0001*\u00020\u001f¢\u0006\u0004\b\u0004\u0010 \u001a\u0019\u0010\f\u001a\n\u0018\u00010\nj\u0004\u0018\u0001`\u000b*\u00020\u001f¢\u0006\u0004\b\f\u0010!\u001a\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0010*\u00020\u001f¢\u0006\u0004\b\u0018\u0010\"\u001a\u0019\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u001a*\u00020\u001f¢\u0006\u0004\b\u001b\u0010#\u001a\u0013\u0010\b\u001a\u0004\u0018\u00010\u001d*\u00020\u001f¢\u0006\u0004\b\b\u0010$\u001a\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020%0\u001a*\u00020\u001f¢\u0006\u0004\b&\u0010#\u001a\u0017\u0010'\u001a\b\u0012\u0004\u0012\u00020%0\u001a*\u00020\u0000¢\u0006\u0004\b'\u0010\u001c\u001a\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020(0\u001a*\u00020\u001f¢\u0006\u0004\b)\u0010#\u001a\u0019\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00100\u001a*\u00020\u0010H\u0000¢\u0006\u0004\b*\u0010+¨\u0006,"}, d2 = {"Lio/ktor/http/HttpMessageBuilder;", "Lio/ktor/http/ContentType;", LinkHeader.Parameters.Type, "Lx6/t0;", "contentType", "(Lio/ktor/http/HttpMessageBuilder;Lio/ktor/http/ContentType;)V", "", "length", "contentLength", "(Lio/ktor/http/HttpMessageBuilder;I)V", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", HttpAuthHeader.Parameters.Charset, "(Lio/ktor/http/HttpMessageBuilder;Ljava/nio/charset/Charset;)Lx6/t0;", "seconds", "maxAge", "", "value", "ifNoneMatch", "(Lio/ktor/http/HttpMessageBuilder;Ljava/lang/String;)V", "content", "userAgent", "(Lio/ktor/http/HttpMessageBuilder;)Lio/ktor/http/ContentType;", "(Lio/ktor/http/HttpMessageBuilder;)Ljava/nio/charset/Charset;", "etag", "(Lio/ktor/http/HttpMessageBuilder;)Ljava/lang/String;", "", "vary", "(Lio/ktor/http/HttpMessageBuilder;)Ljava/util/List;", "", "(Lio/ktor/http/HttpMessageBuilder;)Ljava/lang/Long;", "Lio/ktor/http/HttpMessage;", "(Lio/ktor/http/HttpMessage;)Lio/ktor/http/ContentType;", "(Lio/ktor/http/HttpMessage;)Ljava/nio/charset/Charset;", "(Lio/ktor/http/HttpMessage;)Ljava/lang/String;", "(Lio/ktor/http/HttpMessage;)Ljava/util/List;", "(Lio/ktor/http/HttpMessage;)Ljava/lang/Long;", "Lio/ktor/http/Cookie;", "setCookie", "cookies", "Lio/ktor/http/HeaderValue;", "cacheControl", "splitSetCookieHeader", "(Ljava/lang/String;)Ljava/util/List;", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HttpMessagePropertiesKt {
    public static final List<HeaderValue> cacheControl(HttpMessage httpMessage) {
        List<HeaderValue> headerValue;
        String str = httpMessage.getHeaders().get(HttpHeaders.INSTANCE.getCacheControl());
        return (str == null || (headerValue = HttpHeaderValueParserKt.parseHeaderValue(str)) == null) ? z.f19728i : headerValue;
    }

    @e
    public static final t0 charset(HttpMessageBuilder httpMessageBuilder, Charset charset) {
        ContentType contentType = contentType(httpMessageBuilder);
        if (contentType == null) {
            return null;
        }
        contentType(httpMessageBuilder, ContentTypesKt.withCharset(contentType, charset));
        return t0.f22605a;
    }

    @e
    public static final void contentLength(HttpMessageBuilder httpMessageBuilder, int i10) {
        httpMessageBuilder.getHeaders().set(HttpHeaders.INSTANCE.getContentLength(), String.valueOf(i10));
    }

    public static final void contentType(HttpMessageBuilder httpMessageBuilder, ContentType contentType) {
        httpMessageBuilder.getHeaders().set(HttpHeaders.INSTANCE.getContentType(), contentType.toString());
    }

    public static final List<Cookie> cookies(HttpMessageBuilder httpMessageBuilder) {
        List<String> all = httpMessageBuilder.getHeaders().getAll(HttpHeaders.INSTANCE.getSetCookie());
        if (all == null) {
            return z.f19728i;
        }
        ArrayList arrayList = new ArrayList(s.U(all, 10));
        Iterator<T> it = all.iterator();
        while (it.hasNext()) {
            arrayList.add(CookieKt.parseServerSetCookieHeader((String) it.next()));
        }
        return arrayList;
    }

    public static final String etag(HttpMessageBuilder httpMessageBuilder) {
        return httpMessageBuilder.getHeaders().get(HttpHeaders.INSTANCE.getETag());
    }

    public static final void ifNoneMatch(HttpMessageBuilder httpMessageBuilder, String str) {
        httpMessageBuilder.getHeaders().set(HttpHeaders.INSTANCE.getIfNoneMatch(), str);
    }

    public static final void maxAge(HttpMessageBuilder httpMessageBuilder, int i10) {
        httpMessageBuilder.getHeaders().append(HttpHeaders.INSTANCE.getCacheControl(), "max-age=" + i10);
    }

    public static final List<Cookie> setCookie(HttpMessage httpMessage) {
        List<String> all = httpMessage.getHeaders().getAll(HttpHeaders.INSTANCE.getSetCookie());
        if (all == null) {
            return z.f19728i;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = all.iterator();
        while (it.hasNext()) {
            x.b0(arrayList, splitSetCookieHeader((String) it.next()));
        }
        ArrayList arrayList2 = new ArrayList(s.U(arrayList, 10));
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList2.add(CookieKt.parseServerSetCookieHeader((String) it2.next()));
        }
        return arrayList2;
    }

    public static final List<String> splitSetCookieHeader(String str) {
        int i10;
        int iE0 = o.e0(str, ',', 0, false, 6);
        if (iE0 == -1) {
            return Collections.singletonList(str);
        }
        ArrayList arrayList = new ArrayList();
        int iE02 = o.e0(str, '=', iE0, false, 4);
        int iE03 = o.e0(str, ';', iE0, false, 4);
        int i11 = 0;
        while (i11 < str.length() && iE0 > 0) {
            if (iE02 < iE0) {
                iE02 = o.e0(str, '=', iE0, false, 4);
            }
            int iE04 = o.e0(str, ',', iE0 + 1, false, 4);
            while (true) {
                int i12 = iE04;
                i10 = iE0;
                iE0 = i12;
                if (iE0 < 0 || iE0 >= iE02) {
                    break;
                }
                iE04 = o.e0(str, ',', iE0 + 1, false, 4);
            }
            if (iE03 < i10) {
                iE03 = o.e0(str, ';', i10, false, 4);
            }
            if (iE02 < 0) {
                arrayList.add(str.substring(i11));
                return arrayList;
            }
            if (iE03 == -1 || iE03 > iE02) {
                arrayList.add(str.substring(i11, i10));
                i11 = i10 + 1;
            }
        }
        if (i11 < str.length()) {
            arrayList.add(str.substring(i11));
        }
        return arrayList;
    }

    public static final void userAgent(HttpMessageBuilder httpMessageBuilder, String str) {
        httpMessageBuilder.getHeaders().set(HttpHeaders.INSTANCE.getUserAgent(), str);
    }

    public static final List<String> vary(HttpMessageBuilder httpMessageBuilder) {
        List<String> all = httpMessageBuilder.getHeaders().getAll(HttpHeaders.INSTANCE.getVary());
        if (all == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = all.iterator();
        while (it.hasNext()) {
            List listY0 = o.y0((String) it.next(), new String[]{","}, 0, 6);
            ArrayList arrayList2 = new ArrayList(s.U(listY0, 10));
            Iterator it2 = listY0.iterator();
            while (it2.hasNext()) {
                a2.x((String) it2.next(), arrayList2);
            }
            x.b0(arrayList, arrayList2);
        }
        return arrayList;
    }

    public static final Charset charset(HttpMessageBuilder httpMessageBuilder) {
        ContentType contentType = contentType(httpMessageBuilder);
        if (contentType != null) {
            return ContentTypesKt.charset(contentType);
        }
        return null;
    }

    public static final Long contentLength(HttpMessageBuilder httpMessageBuilder) {
        String str = httpMessageBuilder.getHeaders().get(HttpHeaders.INSTANCE.getContentLength());
        if (str != null) {
            return Long.valueOf(Long.parseLong(str));
        }
        return null;
    }

    public static final ContentType contentType(HttpMessageBuilder httpMessageBuilder) {
        String str = httpMessageBuilder.getHeaders().get(HttpHeaders.INSTANCE.getContentType());
        if (str != null) {
            return ContentType.INSTANCE.parse(str);
        }
        return null;
    }

    public static final String etag(HttpMessage httpMessage) {
        return httpMessage.getHeaders().get(HttpHeaders.INSTANCE.getETag());
    }

    public static final Charset charset(HttpMessage httpMessage) {
        ContentType contentType = contentType(httpMessage);
        if (contentType != null) {
            return ContentTypesKt.charset(contentType);
        }
        return null;
    }

    public static final Long contentLength(HttpMessage httpMessage) {
        String str = httpMessage.getHeaders().get(HttpHeaders.INSTANCE.getContentLength());
        if (str != null) {
            return Long.valueOf(Long.parseLong(str));
        }
        return null;
    }

    public static final ContentType contentType(HttpMessage httpMessage) {
        String str = httpMessage.getHeaders().get(HttpHeaders.INSTANCE.getContentType());
        if (str != null) {
            return ContentType.INSTANCE.parse(str);
        }
        return null;
    }

    public static final List<String> vary(HttpMessage httpMessage) {
        List<String> all = httpMessage.getHeaders().getAll(HttpHeaders.INSTANCE.getVary());
        if (all == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = all.iterator();
        while (it.hasNext()) {
            List listY0 = o.y0((String) it.next(), new String[]{","}, 0, 6);
            ArrayList arrayList2 = new ArrayList(s.U(listY0, 10));
            Iterator it2 = listY0.iterator();
            while (it2.hasNext()) {
                a2.x((String) it2.next(), arrayList2);
            }
            x.b0(arrayList, arrayList2);
        }
        return arrayList;
    }
}
