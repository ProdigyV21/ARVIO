package io.github.jan.supabase.network;

import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.http.ContentType;
import io.ktor.http.HttpMessagePropertiesKt;
import io.ktor.http.HttpMethod;
import io.ktor.http.content.NullBody;
import io.ktor.http.content.OutgoingContent;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u00020\u0002\"\u0006\b\u0000\u0010\u0000\u0018\u0001*\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"T", "Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "io/github/jan/supabase/network/SupabaseHttpClient$delete$6", "<anonymous>"}, k = 3, mv = {1, 9, 0})
public final class SupabaseHttpClient$deleteJson$$inlined$delete$1 extends r implements l<HttpRequestBuilder, t0> {
    final /* synthetic */ Object $body;
    final /* synthetic */ l $builder;
    final /* synthetic */ ContentType $contentType;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SupabaseHttpClient$deleteJson$$inlined$delete$1(l lVar, ContentType contentType, Object obj) {
        super(1);
        this.$builder = lVar;
        this.$contentType = contentType;
        this.$body = obj;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((HttpRequestBuilder) obj);
        return t0.f22605a;
    }

    public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getDelete());
        this.$builder.invoke(httpRequestBuilder);
        HttpMessagePropertiesKt.contentType(httpRequestBuilder, this.$contentType);
        Object obj = this.$body;
        if (obj == null) {
            httpRequestBuilder.setBody(NullBody.INSTANCE);
            p.h();
            throw null;
        }
        if (obj instanceof OutgoingContent) {
            httpRequestBuilder.setBody(obj);
            httpRequestBuilder.setBodyType(null);
        } else {
            httpRequestBuilder.setBody(obj);
            p.h();
            throw null;
        }
    }
}
