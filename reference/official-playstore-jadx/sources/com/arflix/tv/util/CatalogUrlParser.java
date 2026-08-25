package com.arflix.tv.util;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import com.arflix.tv.data.model.CatalogSourceType;
import com.arflix.tv.util.ParsedCatalogUrl;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.text.o;
import kotlin.text.u;
import x6.c0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\u0005J\u0010\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\t\u001a\u00020\u0005J\u0010\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\t\u001a\u00020\u0005J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0002J\u0010\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0002J\u0014\u0010\u0011\u001a\u00020\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0005HÖ\u0081\u0004¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/util/CatalogUrlParser;", "", "<init>", "()V", "normalize", "", "raw", "detectSource", "Lcom/arflix/tv/data/model/CatalogSourceType;", "url", "parse", "Lcom/arflix/tv/util/ParsedCatalogUrl;", "parseTrakt", "isTraktHost", "", "urlOrHost", "isMdblistHost", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class CatalogUrlParser {
    public static final int $stable = 0;
    public static final CatalogUrlParser INSTANCE = new CatalogUrlParser();

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CatalogSourceType.values().length];
            try {
                iArr[CatalogSourceType.TRAKT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CatalogSourceType.MDBLIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private CatalogUrlParser() {
    }

    private final boolean isMdblistHost(String urlOrHost) {
        Object c0Var;
        if (o.T(urlOrHost, "://", false)) {
            try {
                c0Var = new URI(urlOrHost).getHost();
                if (c0Var == null) {
                    c0Var = "";
                }
            } catch (Throwable th) {
                c0Var = new c0(th);
            }
            urlOrHost = (String) (c0Var instanceof c0 ? "" : c0Var);
        }
        String lowerCase = urlOrHost.toLowerCase(Locale.ROOT);
        return lowerCase.equals("mdblist.com") || u.K(lowerCase, ".mdblist.com", false);
    }

    private final boolean isTraktHost(String urlOrHost) {
        Object c0Var;
        if (o.T(urlOrHost, "://", false)) {
            try {
                c0Var = new URI(urlOrHost).getHost();
                if (c0Var == null) {
                    c0Var = "";
                }
            } catch (Throwable th) {
                c0Var = new c0(th);
            }
            urlOrHost = (String) (c0Var instanceof c0 ? "" : c0Var);
        }
        String lowerCase = urlOrHost.toLowerCase(Locale.ROOT);
        return lowerCase.equals("trakt.tv") || u.K(lowerCase, ".trakt.tv", false);
    }

    public final CatalogSourceType detectSource(String url) {
        String strNormalize = normalize(url);
        if (isTraktHost(strNormalize)) {
            return CatalogSourceType.TRAKT;
        }
        if (isMdblistHost(strNormalize)) {
            return CatalogSourceType.MDBLIST;
        }
        return null;
    }

    public boolean equals(Object other) {
        return this == other || (other instanceof CatalogUrlParser);
    }

    public int hashCode() {
        return 186542288;
    }

    public final String normalize(String raw) {
        String string = o.L0(raw).toString();
        if (string.length() == 0) {
            return string;
        }
        if (!u.P(string, "http://", false) && !u.P(string, "https://", false)) {
            string = "https://".concat(string);
        }
        return o.s0(string, DomExceptionUtils.SEPARATOR);
    }

    public final ParsedCatalogUrl parse(String url) {
        String strNormalize = normalize(url);
        CatalogSourceType catalogSourceTypeDetectSource = detectSource(strNormalize);
        int i10 = catalogSourceTypeDetectSource == null ? -1 : WhenMappings.$EnumSwitchMapping$0[catalogSourceTypeDetectSource.ordinal()];
        if (i10 == 1) {
            return parseTrakt(strNormalize);
        }
        if (i10 != 2) {
            return null;
        }
        return new ParsedCatalogUrl.Mdblist(strNormalize);
    }

    public final ParsedCatalogUrl parseTrakt(String url) {
        Object c0Var;
        String host;
        try {
            c0Var = new URI(normalize(url));
        } catch (Throwable th) {
            c0Var = new c0(th);
        }
        if (c0Var instanceof c0) {
            c0Var = null;
        }
        URI uri = (URI) c0Var;
        if (uri != null && (host = uri.getHost()) != null && isTraktHost(host)) {
            List listX0 = o.x0(o.M0(uri.getPath(), '/'), new char[]{'/'}, 6);
            ArrayList arrayList = new ArrayList();
            for (Object obj : listX0) {
                if (!o.h0((String) obj)) {
                    arrayList.add(obj);
                }
            }
            if (arrayList.size() >= 4 && p.a(arrayList.get(0), "users") && p.a(arrayList.get(2), "lists")) {
                return new ParsedCatalogUrl.TraktUserList((String) arrayList.get(1), (String) arrayList.get(3));
            }
            if (arrayList.size() >= 2 && p.a(arrayList.get(0), "lists")) {
                return new ParsedCatalogUrl.TraktList((String) arrayList.get(1));
            }
        }
        return null;
    }

    public String toString() {
        return "CatalogUrlParser";
    }
}
