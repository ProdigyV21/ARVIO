package io.github.jan.supabase.gotrue.providers.builtin;

import db.b0;
import g8.b;
import io.github.jan.supabase.gotrue.UtilsKt;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.http.ContentType;
import io.ktor.http.HttpMessagePropertiesKt;
import io.ktor.http.HttpMethod;
import io.ktor.http.content.NullBody;
import io.ktor.http.content.OutgoingContent;
import kotlin.Metadata;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.r;
import kotlin.reflect.q;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u00020\u0002\"\u0006\b\u0000\u0010\u0000\u0018\u0001*\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"T", "Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "io/github/jan/supabase/network/SupabaseHttpClient$postJson$$inlined$post$1", "<anonymous>"}, k = 3, mv = {1, 9, 0})
public final class DefaultAuthProvider$DefaultImpls$login$$inlined$postJson$1 extends r implements l<HttpRequestBuilder, t0> {
    final /* synthetic */ Object $body;
    final /* synthetic */ ContentType $contentType;
    final /* synthetic */ String $redirectUrl$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultAuthProvider$DefaultImpls$login$$inlined$postJson$1(ContentType contentType, Object obj, String str) {
        super(1);
        this.$contentType = contentType;
        this.$body = obj;
        this.$redirectUrl$inlined = str;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((HttpRequestBuilder) obj);
        return t0.f22605a;
    }

    public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
        String str = this.$redirectUrl$inlined;
        if (str != null) {
            UtilsKt.redirectTo(httpRequestBuilder, str);
        }
        HttpMessagePropertiesKt.contentType(httpRequestBuilder, this.$contentType);
        Object obj = this.$body;
        if (obj == null) {
            httpRequestBuilder.setBody(NullBody.INSTANCE);
            q qVarA = l0.a(b0.class);
            b.o(l0.f19747a, b0.class, kotlin.reflect.b0.t(qVarA), qVarA, httpRequestBuilder);
            return;
        }
        if (obj instanceof OutgoingContent) {
            httpRequestBuilder.setBody(obj);
            httpRequestBuilder.setBodyType(null);
        } else {
            httpRequestBuilder.setBody(obj);
            q qVarA2 = l0.a(b0.class);
            b.o(l0.f19747a, b0.class, kotlin.reflect.b0.t(qVarA2), qVarA2, httpRequestBuilder);
        }
    }
}
