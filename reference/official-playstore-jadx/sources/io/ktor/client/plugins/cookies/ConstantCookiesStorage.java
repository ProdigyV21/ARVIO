package io.ktor.client.plugins.cookies;

import d7.d;
import io.ktor.http.Cookie;
import io.ktor.http.URLBuilder;
import io.ktor.http.Url;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0002\"\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J!\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\u0006\u0010\b\u001a\u00020\u0007H\u0096@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ#\u0010\u000e\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0003H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lio/ktor/client/plugins/cookies/ConstantCookiesStorage;", "Lio/ktor/client/plugins/cookies/CookiesStorage;", "", "Lio/ktor/http/Cookie;", "cookies", "<init>", "([Lio/ktor/http/Cookie;)V", "Lio/ktor/http/Url;", "requestUrl", "", "get", "(Lio/ktor/http/Url;Ld7/d;)Ljava/lang/Object;", "cookie", "Lx6/t0;", "addCookie", "(Lio/ktor/http/Url;Lio/ktor/http/Cookie;Ld7/d;)Ljava/lang/Object;", "close", "()V", "storage", "Ljava/util/List;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ConstantCookiesStorage implements CookiesStorage {
    private final List<Cookie> storage;

    public ConstantCookiesStorage(Cookie... cookieArr) {
        ArrayList arrayList = new ArrayList(cookieArr.length);
        for (Cookie cookie : cookieArr) {
            arrayList.add(CookiesStorageKt.fillDefaults(cookie, new URLBuilder(null, null, 0, null, null, null, null, null, false, 511, null).build()));
        }
        this.storage = x.c1(arrayList);
    }

    @Override // io.ktor.client.plugins.cookies.CookiesStorage
    public Object addCookie(Url url, Cookie cookie, d<? super t0> dVar) {
        return t0.f22605a;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // io.ktor.client.plugins.cookies.CookiesStorage
    public Object get(Url url, d<? super List<Cookie>> dVar) {
        List<Cookie> list = this.storage;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (CookiesStorageKt.matches((Cookie) obj, url)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}
