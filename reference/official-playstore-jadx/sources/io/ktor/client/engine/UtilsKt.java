package io.ktor.client.engine;

import d7.d;
import d7.j;
import io.ktor.client.utils.HeadersKt;
import io.ktor.http.ContentDisposition;
import io.ktor.http.ContentType;
import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaders;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.InternalAPI;
import io.ktor.util.PlatformUtils;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import ka.u1;
import ka.v1;
import ka.z0;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\"\n\u0002\b\u0003\u001aW\u0010\f\u001a\u00020\n2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u000226\u0010\u000b\u001a2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0004H\u0007¢\u0006\u0004\b\f\u0010\r\u001a\u0013\u0010\u000f\u001a\u00020\u000eH\u0087@ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u001b\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0011H\u0080Hø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u000f\u0010\u0016\u001a\u00020\u0015H\u0002¢\u0006\u0004\b\u0016\u0010\u0017\" \u0010\u0018\u001a\u00020\u00058\u0006X\u0087D¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u0012\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001b\"\u001a\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00050\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 \u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, d2 = {"Lio/ktor/http/Headers;", "requestHeaders", "Lio/ktor/http/content/OutgoingContent;", "content", "Lkotlin/Function2;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "key", "value", "Lx6/t0;", "block", "mergeHeaders", "(Lio/ktor/http/Headers;Lio/ktor/http/content/OutgoingContent;Lr7/p;)V", "Ld7/j;", "callContext", "(Ld7/d;)Ljava/lang/Object;", "Lka/v1;", "callJob", "attachToUserJob", "(Lka/v1;Ld7/d;)Ljava/lang/Object;", "", "needUserAgent", "()Z", "KTOR_DEFAULT_USER_AGENT", "Ljava/lang/String;", "getKTOR_DEFAULT_USER_AGENT", "()Ljava/lang/String;", "getKTOR_DEFAULT_USER_AGENT$annotations", "()V", "", "DATE_HEADERS", "Ljava/util/Set;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UtilsKt {
    private static final Set<String> DATE_HEADERS;
    private static final String KTOR_DEFAULT_USER_AGENT = "Ktor client";

    /* JADX INFO: renamed from: io.ktor.client.engine.UtilsKt$attachToUserJob$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<Throwable, t0> {
        final /* synthetic */ z0 $cleanupHandler;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(z0 z0Var) {
            super(1);
            this.$cleanupHandler = z0Var;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return t0.f22605a;
        }

        public final void invoke(Throwable th) {
            this.$cleanupHandler.dispose();
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.engine.UtilsKt$mergeHeaders$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/http/HeadersBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/http/HeadersBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<HeadersBuilder, t0> {
        final /* synthetic */ OutgoingContent $content;
        final /* synthetic */ Headers $requestHeaders;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Headers headers, OutgoingContent outgoingContent) {
            super(1);
            this.$requestHeaders = headers;
            this.$content = outgoingContent;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HeadersBuilder) obj);
            return t0.f22605a;
        }

        public final void invoke(HeadersBuilder headersBuilder) {
            headersBuilder.appendAll(this.$requestHeaders);
            headersBuilder.appendAll(this.$content.getHeaders());
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.engine.UtilsKt$mergeHeaders$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00000\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"", "key", "", "values", "Lx6/t0;", "invoke", "(Ljava/lang/String;Ljava/util/List;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17292 extends r implements p<String, List<? extends String>, t0> {
        final /* synthetic */ p<String, String, t0> $block;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C17292(p<? super String, ? super String, t0> pVar) {
            super(2);
            this.$block = pVar;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((String) obj, (List<String>) obj2);
            return t0.f22605a;
        }

        public final void invoke(String str, List<String> list) {
            HttpHeaders httpHeaders = HttpHeaders.INSTANCE;
            if (kotlin.jvm.internal.p.a(httpHeaders.getContentLength(), str) || kotlin.jvm.internal.p.a(httpHeaders.getContentType(), str)) {
                return;
            }
            if (!UtilsKt.DATE_HEADERS.contains(str)) {
                this.$block.invoke(str, x.u0(list, kotlin.jvm.internal.p.a(httpHeaders.getCookie(), str) ? "; " : ",", null, null, null, 62));
                return;
            }
            p<String, String, t0> pVar = this.$block;
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                pVar.invoke(str, (String) it.next());
            }
        }
    }

    static {
        HttpHeaders httpHeaders = HttpHeaders.INSTANCE;
        DATE_HEADERS = kotlin.collections.r.p0(new String[]{httpHeaders.getDate(), httpHeaders.getExpires(), httpHeaders.getLastModified(), httpHeaders.getIfModifiedSince(), httpHeaders.getIfUnmodifiedSince()});
    }

    public static final Object attachToUserJob(v1 v1Var, d<? super t0> dVar) {
        v1 v1Var2 = (v1) dVar.getContext().get(u1.f19642i);
        t0 t0Var = t0.f22605a;
        if (v1Var2 == null) {
            return t0Var;
        }
        v1Var.invokeOnCompletion(new AnonymousClass2(v1Var2.invokeOnCompletion((2 & 1) == 0, (2 & 2) != 0, new UtilsKt$attachToUserJob$cleanupHandler$1(v1Var))));
        return t0Var;
    }

    private static final Object attachToUserJob$$forInline(v1 v1Var, d<? super t0> dVar) {
        throw null;
    }

    @InternalAPI
    public static final Object callContext(d<? super j> dVar) {
        return ((KtorCallContextElement) dVar.getContext().get(KtorCallContextElement.INSTANCE)).getCallContext();
    }

    public static final String getKTOR_DEFAULT_USER_AGENT() {
        return KTOR_DEFAULT_USER_AGENT;
    }

    @InternalAPI
    public static /* synthetic */ void getKTOR_DEFAULT_USER_AGENT$annotations() {
    }

    @InternalAPI
    public static final void mergeHeaders(Headers headers, OutgoingContent outgoingContent, p<? super String, ? super String, t0> pVar) {
        String string;
        String string2;
        HeadersKt.buildHeaders(new AnonymousClass1(headers, outgoingContent)).forEach(new C17292(pVar));
        HttpHeaders httpHeaders = HttpHeaders.INSTANCE;
        if (headers.get(httpHeaders.getUserAgent()) == null && outgoingContent.getHeaders().get(httpHeaders.getUserAgent()) == null && needUserAgent()) {
            pVar.invoke(httpHeaders.getUserAgent(), KTOR_DEFAULT_USER_AGENT);
        }
        ContentType contentType = outgoingContent.getContentType();
        if ((contentType == null || (string = contentType.toString()) == null) && (string = outgoingContent.getHeaders().get(httpHeaders.getContentType())) == null) {
            string = headers.get(httpHeaders.getContentType());
        }
        Long contentLength = outgoingContent.getContentLength();
        if ((contentLength == null || (string2 = contentLength.toString()) == null) && (string2 = outgoingContent.getHeaders().get(httpHeaders.getContentLength())) == null) {
            string2 = headers.get(httpHeaders.getContentLength());
        }
        if (string != null) {
            pVar.invoke(httpHeaders.getContentType(), string);
        }
        if (string2 != null) {
            pVar.invoke(httpHeaders.getContentLength(), string2);
        }
    }

    private static final boolean needUserAgent() {
        return !PlatformUtils.INSTANCE.getIS_BROWSER();
    }
}
