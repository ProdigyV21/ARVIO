package io.github.jan.supabase.postgrest.request;

import androidx.media3.extractor.text.ttml.TtmlNode;
import db.m;
import io.github.jan.supabase.postgrest.query.Count;
import io.github.jan.supabase.postgrest.query.Returning;
import io.ktor.http.Headers;
import io.ktor.http.HttpMethod;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import t7.a;
import z6.c;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001BK\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0013R&\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\b\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\n\u001a\u00020\t8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u000b\u001a\u00020\u00078\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\r\u001a\u00020\f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\r\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u001a\u0010!\u001a\u00020 8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$R \u0010&\u001a\b\u0012\u0004\u0012\u00020\u00070%8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)¨\u0006*"}, d2 = {"Lio/github/jan/supabase/postgrest/request/UpdateRequest;", "Lio/github/jan/supabase/postgrest/request/PostgrestRequest;", "Lio/github/jan/supabase/postgrest/query/Returning;", "returning", "Lio/github/jan/supabase/postgrest/query/Count;", "count", "", "", "urlParams", "Ldb/m;", TtmlNode.TAG_BODY, "schema", "Lio/ktor/http/Headers;", "headers", "<init>", "(Lio/github/jan/supabase/postgrest/query/Returning;Lio/github/jan/supabase/postgrest/query/Count;Ljava/util/Map;Ldb/m;Ljava/lang/String;Lio/ktor/http/Headers;)V", "Lio/github/jan/supabase/postgrest/query/Returning;", "getReturning", "()Lio/github/jan/supabase/postgrest/query/Returning;", "Lio/github/jan/supabase/postgrest/query/Count;", "Ljava/util/Map;", "getUrlParams", "()Ljava/util/Map;", "Ldb/m;", "getBody", "()Ldb/m;", "Ljava/lang/String;", "getSchema", "()Ljava/lang/String;", "Lio/ktor/http/Headers;", "getHeaders", "()Lio/ktor/http/Headers;", "Lio/ktor/http/HttpMethod;", "method", "Lio/ktor/http/HttpMethod;", "getMethod", "()Lio/ktor/http/HttpMethod;", "", "prefer", "Ljava/util/List;", "getPrefer", "()Ljava/util/List;", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class UpdateRequest implements PostgrestRequest {
    private final m body;
    private final Count count;
    private final Headers headers;
    private final HttpMethod method;
    private final List<String> prefer;
    private final Returning returning;
    private final String schema;
    private final Map<String, String> urlParams;

    public UpdateRequest(Returning returning, Count count, Map<String, String> map, m mVar, String str, Headers headers) {
        this.returning = returning;
        this.count = count;
        this.urlParams = map;
        this.body = mVar;
        this.schema = str;
        this.headers = headers;
        this.method = HttpMethod.INSTANCE.getPatch();
        c cVarN = a.n();
        cVarN.add("return=" + getReturning().getIdentifier());
        if (count != null) {
            cVarN.add("count=" + count.getIdentifier());
        }
        this.prefer = a.e(cVarN);
    }

    @Override // io.github.jan.supabase.postgrest.request.PostgrestRequest
    public m getBody() {
        return this.body;
    }

    @Override // io.github.jan.supabase.postgrest.request.PostgrestRequest
    public Headers getHeaders() {
        return this.headers;
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
        return this.returning;
    }

    @Override // io.github.jan.supabase.postgrest.request.PostgrestRequest
    public String getSchema() {
        return this.schema;
    }

    @Override // io.github.jan.supabase.postgrest.request.PostgrestRequest
    public Map<String, String> getUrlParams() {
        return this.urlParams;
    }

    public /* synthetic */ UpdateRequest(Returning returning, Count count, Map map, m mVar, String str, Headers headers, int i10, h hVar) {
        this((i10 & 1) != 0 ? Returning.Minimal.INSTANCE : returning, (i10 & 2) != 0 ? null : count, map, mVar, str, (i10 & 32) != 0 ? Headers.INSTANCE.getEmpty() : headers);
    }
}
