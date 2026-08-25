package io.github.jan.supabase.postgrest.request;

import androidx.media3.extractor.text.ttml.TtmlNode;
import db.m;
import io.github.jan.supabase.annotations.SupabaseInternal;
import io.github.jan.supabase.postgrest.query.Returning;
import io.ktor.http.Headers;
import io.ktor.http.HttpMethod;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bw\u0018\u00002\u00020\u0001R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0014\u0010\t\u001a\u00020\u00068&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR \u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\n8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0012\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0016\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00178&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001d\u001a\u00020\u000b8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\u0082\u0001\u0005\u001e\u001f !\"¨\u0006#"}, d2 = {"Lio/github/jan/supabase/postgrest/request/PostgrestRequest;", "", "Ldb/m;", "getBody", "()Ldb/m;", TtmlNode.TAG_BODY, "Lio/ktor/http/HttpMethod;", "getMethod", "()Lio/ktor/http/HttpMethod;", "method", "", "", "getUrlParams", "()Ljava/util/Map;", "urlParams", "Lio/ktor/http/Headers;", "getHeaders", "()Lio/ktor/http/Headers;", "headers", "Lio/github/jan/supabase/postgrest/query/Returning;", "getReturning", "()Lio/github/jan/supabase/postgrest/query/Returning;", "returning", "", "getPrefer", "()Ljava/util/List;", "prefer", "getSchema", "()Ljava/lang/String;", "schema", "Lio/github/jan/supabase/postgrest/request/DeleteRequest;", "Lio/github/jan/supabase/postgrest/request/InsertRequest;", "Lio/github/jan/supabase/postgrest/request/RpcRequest;", "Lio/github/jan/supabase/postgrest/request/SelectRequest;", "Lio/github/jan/supabase/postgrest/request/UpdateRequest;", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SupabaseInternal
public interface PostgrestRequest {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static m getBody(PostgrestRequest postgrestRequest) {
            return null;
        }

        public static Headers getHeaders(PostgrestRequest postgrestRequest) {
            return Headers.INSTANCE.getEmpty();
        }

        public static Returning getReturning(PostgrestRequest postgrestRequest) {
            return Returning.Minimal.INSTANCE;
        }
    }

    m getBody();

    Headers getHeaders();

    HttpMethod getMethod();

    List<String> getPrefer();

    Returning getReturning();

    String getSchema();

    Map<String, String> getUrlParams();
}
