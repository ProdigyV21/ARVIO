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
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0007\b\u0001\u0018\u00002\u00020\u0001BA\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0014X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u0018X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\t\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR \u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e¨\u0006\u001f"}, d2 = {"Lio/github/jan/supabase/postgrest/request/SelectRequest;", "Lio/github/jan/supabase/postgrest/request/PostgrestRequest;", TtmlNode.TAG_HEAD, "", "count", "Lio/github/jan/supabase/postgrest/query/Count;", "urlParams", "", "", "schema", "headers", "Lio/ktor/http/Headers;", "(ZLio/github/jan/supabase/postgrest/query/Count;Ljava/util/Map;Ljava/lang/String;Lio/ktor/http/Headers;)V", "getCount", "()Lio/github/jan/supabase/postgrest/query/Count;", "getHead", "()Z", "getHeaders", "()Lio/ktor/http/Headers;", "method", "Lio/ktor/http/HttpMethod;", "getMethod", "()Lio/ktor/http/HttpMethod;", "prefer", "", "getPrefer", "()Ljava/util/List;", "getSchema", "()Ljava/lang/String;", "getUrlParams", "()Ljava/util/Map;", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SelectRequest implements PostgrestRequest {
    private final Count count;
    private final boolean head;
    private final Headers headers;
    private final HttpMethod method;
    private final List<String> prefer;
    private final String schema;
    private final Map<String, String> urlParams;

    public SelectRequest(boolean z, Count count, Map<String, String> map, String str, Headers headers) {
        List<String> listSingletonList;
        this.head = z;
        this.count = count;
        this.urlParams = map;
        this.schema = str;
        this.headers = headers;
        this.method = z ? HttpMethod.INSTANCE.getHead() : HttpMethod.INSTANCE.getGet();
        if (count != null) {
            listSingletonList = Collections.singletonList("count=" + count.getIdentifier());
        } else {
            listSingletonList = z.f19728i;
        }
        this.prefer = listSingletonList;
    }

    @Override // io.github.jan.supabase.postgrest.request.PostgrestRequest
    public m getBody() {
        return PostgrestRequest.DefaultImpls.getBody(this);
    }

    public final Count getCount() {
        return this.count;
    }

    public final boolean getHead() {
        return this.head;
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

    public /* synthetic */ SelectRequest(boolean z, Count count, Map map, String str, Headers headers, int i10, h hVar) {
        this((i10 & 1) != 0 ? false : z, (i10 & 2) != 0 ? null : count, map, str, (i10 & 16) != 0 ? Headers.INSTANCE.getEmpty() : headers);
    }
}
