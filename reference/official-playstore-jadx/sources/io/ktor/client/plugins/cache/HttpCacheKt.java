package io.ktor.client.plugins.cache;

import io.ktor.client.engine.UtilsKt;
import io.ktor.http.ContentType;
import io.ktor.http.HttpHeaders;
import io.ktor.http.URLProtocol;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;
import vc.b;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aU\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0001\u001a\u00020\u00002\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\u001a\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00050\u0002H\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a\u0013\u0010\u000b\u001a\u00020\n*\u00020\tH\u0002¢\u0006\u0004\b\u000b\u0010\f\"\u001e\u0010\u000f\u001a\u00060\rj\u0002`\u000e8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lio/ktor/http/content/OutgoingContent;", "content", "Lkotlin/Function1;", "", "headerExtractor", "", "allHeadersExtractor", "mergedHeadersLookup", "(Lio/ktor/http/content/OutgoingContent;Lr7/l;Lr7/l;)Lr7/l;", "Lio/ktor/http/URLProtocol;", "", "canStore", "(Lio/ktor/http/URLProtocol;)Z", "Lvc/b;", "Lio/ktor/util/logging/Logger;", "LOGGER", "Lvc/b;", "getLOGGER", "()Lvc/b;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HttpCacheKt {
    private static final b LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.HttpCache");

    /* JADX INFO: renamed from: io.ktor.client.plugins.cache.HttpCacheKt$mergedHeadersLookup$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "header", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<String, String> {
        final /* synthetic */ l<String, List<String>> $allHeadersExtractor;
        final /* synthetic */ OutgoingContent $content;
        final /* synthetic */ l<String, String> $headerExtractor;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(OutgoingContent outgoingContent, l<? super String, String> lVar, l<? super String, ? extends List<String>> lVar2) {
            super(1);
            this.$content = outgoingContent;
            this.$headerExtractor = lVar;
            this.$allHeadersExtractor = lVar2;
        }

        @Override // r7.l
        public final String invoke(String str) {
            String string;
            String string2;
            HttpHeaders httpHeaders = HttpHeaders.INSTANCE;
            if (str.equals(httpHeaders.getContentLength())) {
                Long contentLength = this.$content.getContentLength();
                return (contentLength == null || (string2 = contentLength.toString()) == null) ? "" : string2;
            }
            if (str.equals(httpHeaders.getContentType())) {
                ContentType contentType = this.$content.getContentType();
                return (contentType == null || (string = contentType.toString()) == null) ? "" : string;
            }
            if (str.equals(httpHeaders.getUserAgent())) {
                String str2 = this.$content.getHeaders().get(httpHeaders.getUserAgent());
                if (str2 != null) {
                    return str2;
                }
                String str3 = (String) this.$headerExtractor.invoke(httpHeaders.getUserAgent());
                return str3 == null ? UtilsKt.getKTOR_DEFAULT_USER_AGENT() : str3;
            }
            List<String> all = this.$content.getHeaders().getAll(str);
            if (all == null && (all = (List) this.$allHeadersExtractor.invoke(str)) == null) {
                all = z.f19728i;
            }
            return x.u0(all, ";", null, null, null, 62);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean canStore(URLProtocol uRLProtocol) {
        return p.a(uRLProtocol.getName(), "http") || p.a(uRLProtocol.getName(), "https");
    }

    public static final b getLOGGER() {
        return LOGGER;
    }

    public static final l<String, String> mergedHeadersLookup(OutgoingContent outgoingContent, l<? super String, String> lVar, l<? super String, ? extends List<String>> lVar2) {
        return new AnonymousClass1(outgoingContent, lVar, lVar2);
    }
}
