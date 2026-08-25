package io.github.jan.supabase.postgrest.request;

import androidx.media3.extractor.text.ttml.TtmlNode;
import db.m;
import io.github.jan.supabase.postgrest.query.Count;
import io.github.jan.supabase.postgrest.query.Returning;
import io.github.jan.supabase.postgrest.request.PostgrestRequest;
import io.ktor.http.Headers;
import io.ktor.http.HttpMethod;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001B=\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000b\u0010\fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R&\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\b\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\n\u001a\u0004\u0018\u00010\t8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u00078\u0016X\u0096D¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001e\u001a\u00020\u001d8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R \u0010#\u001a\b\u0012\u0004\u0012\u00020\u00070\"8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&¨\u0006'"}, d2 = {"Lio/github/jan/supabase/postgrest/request/RpcRequest;", "Lio/github/jan/supabase/postgrest/request/PostgrestRequest;", "", TtmlNode.TAG_HEAD, "Lio/github/jan/supabase/postgrest/query/Count;", "count", "", "", "urlParams", "Ldb/m;", TtmlNode.TAG_BODY, "<init>", "(ZLio/github/jan/supabase/postgrest/query/Count;Ljava/util/Map;Ldb/m;)V", "Z", "getHead", "()Z", "Lio/github/jan/supabase/postgrest/query/Count;", "getCount", "()Lio/github/jan/supabase/postgrest/query/Count;", "Ljava/util/Map;", "getUrlParams", "()Ljava/util/Map;", "Ldb/m;", "getBody", "()Ldb/m;", "schema", "Ljava/lang/String;", "getSchema", "()Ljava/lang/String;", "Lio/ktor/http/HttpMethod;", "method", "Lio/ktor/http/HttpMethod;", "getMethod", "()Lio/ktor/http/HttpMethod;", "", "prefer", "Ljava/util/List;", "getPrefer", "()Ljava/util/List;", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class RpcRequest implements PostgrestRequest {
    private final m body;
    private final Count count;
    private final boolean head;
    private final HttpMethod method;
    private final List<String> prefer;
    private final String schema;
    private final Map<String, String> urlParams;

    public RpcRequest(boolean z, Count count, Map<String, String> map, m mVar) {
        List<String> listSingletonList;
        this.head = z;
        this.count = count;
        this.urlParams = map;
        this.body = mVar;
        this.schema = "";
        this.method = z ? HttpMethod.INSTANCE.getHead() : HttpMethod.INSTANCE.getPost();
        if (count != null) {
            listSingletonList = Collections.singletonList("count=" + count.getIdentifier());
        } else {
            listSingletonList = z.f19728i;
        }
        this.prefer = listSingletonList;
    }

    @Override // io.github.jan.supabase.postgrest.request.PostgrestRequest
    public m getBody() {
        return this.body;
    }

    public final Count getCount() {
        return this.count;
    }

    public final boolean getHead() {
        return this.head;
    }

    @Override // io.github.jan.supabase.postgrest.request.PostgrestRequest
    public Headers getHeaders() {
        return PostgrestRequest.DefaultImpls.getHeaders(this);
    }

    @Override // io.github.jan.supabase.postgrest.request.PostgrestRequest
    public HttpMethod getMethod() {
        return this.method;
    }

    @Override // io.github.jan.supabase.postgrest.request.PostgrestRequest
    public List<String> getPrefer() {
        return this.prefer;
    }

    @Override // io.github.jan.supabase.postgrest.request.PostgrestRequest
    public Returning getReturning() {
        return PostgrestRequest.DefaultImpls.getReturning(this);
    }

    @Override // io.github.jan.supabase.postgrest.request.PostgrestRequest
    public String getSchema() {
        return this.schema;
    }

    @Override // io.github.jan.supabase.postgrest.request.PostgrestRequest
    public Map<String, String> getUrlParams() {
        return this.urlParams;
    }

    public /* synthetic */ RpcRequest(boolean z, Count count, Map map, m mVar, int i10, h hVar) {
        this((i10 & 1) != 0 ? false : z, (i10 & 2) != 0 ? null : count, map, (i10 & 8) != 0 ? null : mVar);
    }
}
