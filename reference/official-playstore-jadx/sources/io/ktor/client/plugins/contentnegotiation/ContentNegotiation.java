package io.ktor.client.plugins.contentnegotiation;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.client.HttpClient;
import io.ktor.client.plugins.HttpClientPlugin;
import io.ktor.client.request.HttpRequestPipeline;
import io.ktor.client.statement.HttpResponsePipeline;
import io.ktor.http.ContentType;
import io.ktor.http.ContentTypeMatcher;
import io.ktor.http.LinkHeader;
import io.ktor.http.Url;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.serialization.Configuration;
import io.ktor.serialization.ContentConverter;
import io.ktor.util.AttributeKey;
import io.ktor.util.KtorDsl;
import io.ktor.util.reflect.TypeInfo;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.q0;
import kotlin.collections.x;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import kotlin.reflect.d;
import kotlin.text.a;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 \"2\u00020\u0001:\u0002#\"B)\b\u0000\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0010\u0010\u0007\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¢\u0006\u0004\b\b\u0010\tJ%\u0010\u000f\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0001H\u0080@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJC\u0010\u001b\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00142\f\b\u0002\u0010\u0018\u001a\u00060\u0016j\u0002`\u0017H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aR \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR$\u0010\u0007\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u00058\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0007\u0010\u001f\u001a\u0004\b \u0010!\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006$"}, d2 = {"Lio/ktor/client/plugins/contentnegotiation/ContentNegotiation;", "", "", "Lio/ktor/client/plugins/contentnegotiation/ContentNegotiation$Config$ConverterRegistration;", "registrations", "", "Lkotlin/reflect/d;", "ignoredTypes", "<init>", "(Ljava/util/List;Ljava/util/Set;)V", "Lio/ktor/client/request/HttpRequestBuilder;", "request", TtmlNode.TAG_BODY, "convertRequest$ktor_client_content_negotiation", "(Lio/ktor/client/request/HttpRequestBuilder;Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "convertRequest", "Lio/ktor/http/Url;", "requestUrl", "Lio/ktor/util/reflect/TypeInfo;", "info", "Lio/ktor/http/ContentType;", "responseContentType", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", HttpAuthHeader.Parameters.Charset, "convertResponse$ktor_client_content_negotiation", "(Lio/ktor/http/Url;Lio/ktor/util/reflect/TypeInfo;Ljava/lang/Object;Lio/ktor/http/ContentType;Ljava/nio/charset/Charset;Ld7/d;)Ljava/lang/Object;", "convertResponse", "Ljava/util/List;", "getRegistrations$ktor_client_content_negotiation", "()Ljava/util/List;", "Ljava/util/Set;", "getIgnoredTypes$ktor_client_content_negotiation", "()Ljava/util/Set;", "Plugin", "Config", "ktor-client-content-negotiation"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ContentNegotiation {

    /* JADX INFO: renamed from: Plugin, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AttributeKey<ContentNegotiation> key = new AttributeKey<>("ContentNegotiation");
    private final Set<d<?>> ignoredTypes;
    private final List<Config.ConverterRegistration> registrations;

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001'B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJB\u0010\u0011\u001a\u00020\u000e\"\b\b\u0000\u0010\n*\u00020\t2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00028\u00002\u0017\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0002\b\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012JH\u0010\u0011\u001a\u00020\u000e\"\b\b\u0000\u0010\n*\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\f\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00020\u00062\u0017\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0002\b\u000f¢\u0006\u0004\b\u0011\u0010\u0015J\u0018\u0010\u0016\u001a\u00020\u000e\"\u0006\b\u0000\u0010\n\u0018\u0001H\u0086\b¢\u0006\u0004\b\u0016\u0010\u0003J\u0018\u0010\u0017\u001a\u00020\u000e\"\u0006\b\u0000\u0010\n\u0018\u0001H\u0086\b¢\u0006\u0004\b\u0017\u0010\u0003J\u0019\u0010\u0017\u001a\u00020\u000e2\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u0018¢\u0006\u0004\b\u0017\u0010\u001aJ\u0019\u0010\u0016\u001a\u00020\u000e2\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u0018¢\u0006\u0004\b\u0016\u0010\u001aJ\r\u0010\u001b\u001a\u00020\u000e¢\u0006\u0004\b\u001b\u0010\u0003R$\u0010\u001d\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00180\u001c8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R \u0010#\u001a\b\u0012\u0004\u0012\u00020\"0!8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&¨\u0006("}, d2 = {"Lio/ktor/client/plugins/contentnegotiation/ContentNegotiation$Config;", "Lio/ktor/serialization/Configuration;", "<init>", "()V", "Lio/ktor/http/ContentType;", "pattern", "Lio/ktor/http/ContentTypeMatcher;", "defaultMatcher", "(Lio/ktor/http/ContentType;)Lio/ktor/http/ContentTypeMatcher;", "Lio/ktor/serialization/ContentConverter;", "T", "contentType", "converter", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "configuration", "register", "(Lio/ktor/http/ContentType;Lio/ktor/serialization/ContentConverter;Lr7/l;)V", "contentTypeToSend", "contentTypeMatcher", "(Lio/ktor/http/ContentType;Lio/ktor/serialization/ContentConverter;Lio/ktor/http/ContentTypeMatcher;Lr7/l;)V", "ignoreType", "removeIgnoredType", "Lkotlin/reflect/d;", LinkHeader.Parameters.Type, "(Lkotlin/reflect/d;)V", "clearIgnoredTypes", "", "ignoredTypes", "Ljava/util/Set;", "getIgnoredTypes$ktor_client_content_negotiation", "()Ljava/util/Set;", "", "Lio/ktor/client/plugins/contentnegotiation/ContentNegotiation$Config$ConverterRegistration;", "registrations", "Ljava/util/List;", "getRegistrations$ktor_client_content_negotiation", "()Ljava/util/List;", "ConverterRegistration", "ktor-client-content-negotiation"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Config implements Configuration {
        private final Set<d<?>> ignoredTypes = x.f1(q0.b0(DefaultIgnoredTypesJvmKt.getDefaultIgnoredTypes(), ContentNegotiationKt.getDefaultCommonIgnoredTypes()));
        private final List<ConverterRegistration> registrations = new ArrayList();

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/client/plugins/contentnegotiation/ContentNegotiation$Config$ConverterRegistration;", "", "converter", "Lio/ktor/serialization/ContentConverter;", "contentTypeToSend", "Lio/ktor/http/ContentType;", "contentTypeMatcher", "Lio/ktor/http/ContentTypeMatcher;", "(Lio/ktor/serialization/ContentConverter;Lio/ktor/http/ContentType;Lio/ktor/http/ContentTypeMatcher;)V", "getContentTypeMatcher", "()Lio/ktor/http/ContentTypeMatcher;", "getContentTypeToSend", "()Lio/ktor/http/ContentType;", "getConverter", "()Lio/ktor/serialization/ContentConverter;", "ktor-client-content-negotiation"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class ConverterRegistration {
            private final ContentTypeMatcher contentTypeMatcher;
            private final ContentType contentTypeToSend;
            private final ContentConverter converter;

            public ConverterRegistration(ContentConverter contentConverter, ContentType contentType, ContentTypeMatcher contentTypeMatcher) {
                this.converter = contentConverter;
                this.contentTypeToSend = contentType;
                this.contentTypeMatcher = contentTypeMatcher;
            }

            public final ContentTypeMatcher getContentTypeMatcher() {
                return this.contentTypeMatcher;
            }

            public final ContentType getContentTypeToSend() {
                return this.contentTypeToSend;
            }

            public final ContentConverter getConverter() {
                return this.converter;
            }
        }

        private final ContentTypeMatcher defaultMatcher(final ContentType pattern) {
            return new ContentTypeMatcher() { // from class: io.ktor.client.plugins.contentnegotiation.ContentNegotiation$Config$defaultMatcher$1
                @Override // io.ktor.http.ContentTypeMatcher
                public boolean contains(ContentType contentType) {
                    return contentType.match(pattern);
                }
            };
        }

        public final void clearIgnoredTypes() {
            this.ignoredTypes.clear();
        }

        public final Set<d<?>> getIgnoredTypes$ktor_client_content_negotiation() {
            return this.ignoredTypes;
        }

        public final List<ConverterRegistration> getRegistrations$ktor_client_content_negotiation() {
            return this.registrations;
        }

        public final void ignoreType(d<?> type) {
            this.ignoredTypes.add(type);
        }

        @Override // io.ktor.serialization.Configuration
        public <T extends ContentConverter> void register(ContentType contentType, T converter, l<? super T, t0> configuration) {
            register(contentType, converter, contentType.equals(ContentType.Application.INSTANCE.getJson()) ? JsonContentTypeMatcher.INSTANCE : defaultMatcher(contentType), configuration);
        }

        public final void removeIgnoredType(d<?> type) {
            this.ignoredTypes.remove(type);
        }

        public final <T> void ignoreType() {
            p.h();
            throw null;
        }

        public final <T> void removeIgnoredType() {
            p.h();
            throw null;
        }

        public final <T extends ContentConverter> void register(ContentType contentTypeToSend, T converter, ContentTypeMatcher contentTypeMatcher, l<? super T, t0> configuration) {
            configuration.invoke(converter);
            this.registrations.add(new ConverterRegistration(converter, contentTypeToSend, contentTypeMatcher));
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.contentnegotiation.ContentNegotiation$Plugin, reason: from kotlin metadata */
    @KtorDsl
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J(\u0010\n\u001a\u00020\u00032\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000f\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u00118\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lio/ktor/client/plugins/contentnegotiation/ContentNegotiation$Plugin;", "Lio/ktor/client/plugins/HttpClientPlugin;", "Lio/ktor/client/plugins/contentnegotiation/ContentNegotiation$Config;", "Lio/ktor/client/plugins/contentnegotiation/ContentNegotiation;", "<init>", "()V", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "block", "prepare", "(Lr7/l;)Lio/ktor/client/plugins/contentnegotiation/ContentNegotiation;", "plugin", "Lio/ktor/client/HttpClient;", "scope", "install", "(Lio/ktor/client/plugins/contentnegotiation/ContentNegotiation;Lio/ktor/client/HttpClient;)V", "Lio/ktor/util/AttributeKey;", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "ktor-client-content-negotiation"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion implements HttpClientPlugin<Config, ContentNegotiation> {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public AttributeKey<ContentNegotiation> getKey() {
            return ContentNegotiation.key;
        }

        private Companion() {
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public void install(ContentNegotiation plugin, HttpClient scope) {
            scope.getRequestPipeline().intercept(HttpRequestPipeline.INSTANCE.getTransform(), new ContentNegotiation$Plugin$install$1(plugin, null));
            scope.getResponsePipeline().intercept(HttpResponsePipeline.INSTANCE.getTransform(), new ContentNegotiation$Plugin$install$2(plugin, null));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.ktor.client.plugins.HttpClientPlugin
        public ContentNegotiation prepare(l<? super Config, t0> block) {
            Config config = new Config();
            block.invoke(config);
            return new ContentNegotiation(config.getRegistrations$ktor_client_content_negotiation(), config.getIgnoredTypes$ktor_client_content_negotiation());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ContentNegotiation(List<Config.ConverterRegistration> list, Set<? extends d<?>> set) {
        this.registrations = list;
        this.ignoredTypes = set;
    }

    public static /* synthetic */ Object convertResponse$ktor_client_content_negotiation$default(ContentNegotiation contentNegotiation, Url url, TypeInfo typeInfo, Object obj, ContentType contentType, Charset charset, d7.d dVar, int i10, Object obj2) {
        if ((i10 & 16) != 0) {
            charset = a.f19924a;
        }
        return contentNegotiation.convertResponse$ktor_client_content_negotiation(url, typeInfo, obj, contentType, charset, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x020a  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0215 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:69:0x01d9 -> B:70:0x01df). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object convertRequest$ktor_client_content_negotiation(io.ktor.client.request.HttpRequestBuilder r11, java.lang.Object r12, d7.d<java.lang.Object> r13) throws io.ktor.client.plugins.contentnegotiation.ContentConverterException {
        /*
            Method dump skipped, instruction units count: 628
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.contentnegotiation.ContentNegotiation.convertRequest$ktor_client_content_negotiation(io.ktor.client.request.HttpRequestBuilder, java.lang.Object, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object convertResponse$ktor_client_content_negotiation(io.ktor.http.Url r8, io.ktor.util.reflect.TypeInfo r9, java.lang.Object r10, io.ktor.http.ContentType r11, java.nio.charset.Charset r12, d7.d<java.lang.Object> r13) {
        /*
            Method dump skipped, instruction units count: 312
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.contentnegotiation.ContentNegotiation.convertResponse$ktor_client_content_negotiation(io.ktor.http.Url, io.ktor.util.reflect.TypeInfo, java.lang.Object, io.ktor.http.ContentType, java.nio.charset.Charset, d7.d):java.lang.Object");
    }

    public final Set<d<?>> getIgnoredTypes$ktor_client_content_negotiation() {
        return this.ignoredTypes;
    }

    public final List<Config.ConverterRegistration> getRegistrations$ktor_client_content_negotiation() {
        return this.registrations;
    }
}
