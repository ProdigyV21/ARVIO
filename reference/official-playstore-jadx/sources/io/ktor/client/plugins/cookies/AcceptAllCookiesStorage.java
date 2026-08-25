package io.ktor.client.plugins.cookies;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import f7.c;
import io.ktor.http.Cookie;
import io.ktor.util.date.GMTDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.r;
import r7.l;
import ua.a;
import ua.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ!\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\n\u001a\u00020\tH\u0096@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ#\u0010\u0010\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\fH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0012\u0010\u0003R\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\f0\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, d2 = {"Lio/ktor/client/plugins/cookies/AcceptAllCookiesStorage;", "Lio/ktor/client/plugins/cookies/CookiesStorage;", "<init>", "()V", "", "timestamp", "Lx6/t0;", "cleanup", "(J)V", "Lio/ktor/http/Url;", "requestUrl", "", "Lio/ktor/http/Cookie;", "get", "(Lio/ktor/http/Url;Ld7/d;)Ljava/lang/Object;", "cookie", "addCookie", "(Lio/ktor/http/Url;Lio/ktor/http/Cookie;Ld7/d;)Ljava/lang/Object;", "close", "", TtmlNode.RUBY_CONTAINER, "Ljava/util/List;", "Lua/a;", "mutex", "Lua/a;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AcceptAllCookiesStorage implements CookiesStorage {
    private final List<Cookie> container = new ArrayList();
    private volatile /* synthetic */ long oldestCookie = 0;
    private final a mutex = e.a();

    /* JADX INFO: renamed from: io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$addCookie$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "io.ktor.client.plugins.cookies.AcceptAllCookiesStorage", f = "AcceptAllCookiesStorage.kt", l = {66}, m = "addCookie")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AcceptAllCookiesStorage.this.addCookie(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$cleanup$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "cookie", "Lio/ktor/http/Cookie;", "invoke", "(Lio/ktor/http/Cookie;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C17421 extends r implements l<Cookie, Boolean> {
        final /* synthetic */ long $timestamp;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C17421(long j10) {
            super(1);
            this.$timestamp = j10;
        }

        @Override // r7.l
        public final Boolean invoke(Cookie cookie) {
            GMTDate expires = cookie.getExpires();
            if (expires != null) {
                return Boolean.valueOf(expires.getTimestamp() < this.$timestamp);
            }
            return Boolean.FALSE;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$get$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "io.ktor.client.plugins.cookies.AcceptAllCookiesStorage", f = "AcceptAllCookiesStorage.kt", l = {66}, m = "get")
    public static final class C17431 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C17431(d<? super C17431> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AcceptAllCookiesStorage.this.get(null, this);
        }
    }

    private final void cleanup(long timestamp) {
        x.M0(this.container, new C17421(timestamp));
        Iterator<T> it = this.container.iterator();
        long jMin = Long.MAX_VALUE;
        while (it.hasNext()) {
            GMTDate expires = ((Cookie) it.next()).getExpires();
            if (expires != null) {
                jMin = Math.min(jMin, expires.getTimestamp());
            }
        }
        this.oldestCookie = jMin;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.ktor.client.plugins.cookies.CookiesStorage
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object addCookie(io.ktor.http.Url r5, io.ktor.http.Cookie r6, d7.d<? super x6.t0> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof io.ktor.client.plugins.cookies.AcceptAllCookiesStorage.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$addCookie$1 r0 = (io.ktor.client.plugins.cookies.AcceptAllCookiesStorage.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$addCookie$1 r0 = new io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$addCookie$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            e7.a r1 = e7.a.f15033i
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L41
            if (r2 != r3) goto L39
            java.lang.Object r5 = r0.L$3
            ua.a r5 = (ua.a) r5
            java.lang.Object r6 = r0.L$2
            io.ktor.http.Cookie r6 = (io.ktor.http.Cookie) r6
            java.lang.Object r1 = r0.L$1
            io.ktor.http.Url r1 = (io.ktor.http.Url) r1
            java.lang.Object r0 = r0.L$0
            io.ktor.client.plugins.cookies.AcceptAllCookiesStorage r0 = (io.ktor.client.plugins.cookies.AcceptAllCookiesStorage) r0
            k2.c.G(r7)
            r7 = r5
            r5 = r1
            goto L58
        L39:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L41:
            k2.c.G(r7)
            ua.a r7 = r4.mutex
            r0.L$0 = r4
            r0.L$1 = r5
            r0.L$2 = r6
            r0.L$3 = r7
            r0.label = r3
            java.lang.Object r0 = r7.c(r0)
            if (r0 != r1) goto L57
            return r1
        L57:
            r0 = r4
        L58:
            r1 = 0
            java.lang.String r2 = r6.getName()     // Catch: java.lang.Throwable -> L89
            boolean r2 = kotlin.text.o.h0(r2)     // Catch: java.lang.Throwable -> L89
            if (r2 != 0) goto L8b
            java.util.List<io.ktor.http.Cookie> r2 = r0.container     // Catch: java.lang.Throwable -> L89
            io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$addCookie$2$2 r3 = new io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$addCookie$2$2     // Catch: java.lang.Throwable -> L89
            r3.<init>(r6, r5)     // Catch: java.lang.Throwable -> L89
            kotlin.collections.x.M0(r2, r3)     // Catch: java.lang.Throwable -> L89
            java.util.List<io.ktor.http.Cookie> r2 = r0.container     // Catch: java.lang.Throwable -> L89
            io.ktor.http.Cookie r5 = io.ktor.client.plugins.cookies.CookiesStorageKt.fillDefaults(r6, r5)     // Catch: java.lang.Throwable -> L89
            r2.add(r5)     // Catch: java.lang.Throwable -> L89
            io.ktor.util.date.GMTDate r5 = r6.getExpires()     // Catch: java.lang.Throwable -> L89
            if (r5 == 0) goto L8b
            long r5 = r5.getTimestamp()     // Catch: java.lang.Throwable -> L89
            long r2 = r0.oldestCookie     // Catch: java.lang.Throwable -> L89
            int r2 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r2 <= 0) goto L8b
            r0.oldestCookie = r5     // Catch: java.lang.Throwable -> L89
            goto L8b
        L89:
            r5 = move-exception
            goto L91
        L8b:
            r7.b(r1)
            x6.t0 r5 = x6.t0.f22605a
            return r5
        L91:
            r7.b(r1)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cookies.AcceptAllCookiesStorage.addCookie(io.ktor.http.Url, io.ktor.http.Cookie, d7.d):java.lang.Object");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.ktor.client.plugins.cookies.CookiesStorage
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object get(io.ktor.http.Url r7, d7.d<? super java.util.List<io.ktor.http.Cookie>> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ktor.client.plugins.cookies.AcceptAllCookiesStorage.C17431
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$get$1 r0 = (io.ktor.client.plugins.cookies.AcceptAllCookiesStorage.C17431) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$get$1 r0 = new io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$get$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            e7.a r1 = e7.a.f15033i
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            java.lang.Object r7 = r0.L$2
            ua.a r7 = (ua.a) r7
            java.lang.Object r1 = r0.L$1
            io.ktor.http.Url r1 = (io.ktor.http.Url) r1
            java.lang.Object r0 = r0.L$0
            io.ktor.client.plugins.cookies.AcceptAllCookiesStorage r0 = (io.ktor.client.plugins.cookies.AcceptAllCookiesStorage) r0
            k2.c.G(r8)
            goto L52
        L33:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3b:
            k2.c.G(r8)
            ua.a r8 = r6.mutex
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r8
            r0.label = r3
            java.lang.Object r0 = r8.c(r0)
            if (r0 != r1) goto L4f
            return r1
        L4f:
            r0 = r6
            r1 = r7
            r7 = r8
        L52:
            r8 = 0
            long r2 = io.ktor.util.date.DateJvmKt.getTimeMillis()     // Catch: java.lang.Throwable -> L61
            long r4 = r0.oldestCookie     // Catch: java.lang.Throwable -> L61
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 < 0) goto L63
            r0.cleanup(r2)     // Catch: java.lang.Throwable -> L61
            goto L63
        L61:
            r0 = move-exception
            goto L89
        L63:
            java.util.List<io.ktor.http.Cookie> r0 = r0.container     // Catch: java.lang.Throwable -> L61
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L61
            r2.<init>()     // Catch: java.lang.Throwable -> L61
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L61
        L6e:
            boolean r3 = r0.hasNext()     // Catch: java.lang.Throwable -> L61
            if (r3 == 0) goto L85
            java.lang.Object r3 = r0.next()     // Catch: java.lang.Throwable -> L61
            r4 = r3
            io.ktor.http.Cookie r4 = (io.ktor.http.Cookie) r4     // Catch: java.lang.Throwable -> L61
            boolean r4 = io.ktor.client.plugins.cookies.CookiesStorageKt.matches(r4, r1)     // Catch: java.lang.Throwable -> L61
            if (r4 == 0) goto L6e
            r2.add(r3)     // Catch: java.lang.Throwable -> L61
            goto L6e
        L85:
            r7.b(r8)
            return r2
        L89:
            r7.b(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cookies.AcceptAllCookiesStorage.get(io.ktor.http.Url, d7.d):java.lang.Object");
    }
}
