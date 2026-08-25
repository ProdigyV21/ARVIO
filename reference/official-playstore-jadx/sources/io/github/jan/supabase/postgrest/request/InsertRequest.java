package io.github.jan.supabase.postgrest.request;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import db.d;
import io.github.jan.supabase.postgrest.query.Count;
import io.github.jan.supabase.postgrest.query.Returning;
import io.ktor.http.Headers;
import io.ktor.http.HttpMethod;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import t7.a;
import z6.c;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0005\b\u0081\b\u0018\u00002\u00020\u0001Bi\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0002\u0012\b\b\u0002\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0010\u000f\u001a\u00020\r\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0002HÂ\u0003¢\u0006\u0004\b\u0014\u0010\u0015J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u0006HÂ\u0003¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0002HÂ\u0003¢\u0006\u0004\b\u0018\u0010\u0015J\u0010\u0010\u0019\u001a\u00020\u0002HÂ\u0003¢\u0006\u0004\b\u0019\u0010\u0015J\u0010\u0010\u001a\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\nHÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\u001c\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\fHÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\rHÆ\u0003¢\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020\u0010HÆ\u0003¢\u0006\u0004\b\"\u0010#Jx\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\n2\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\f2\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0011\u001a\u00020\u0010HÆ\u0001¢\u0006\u0004\b$\u0010%J\u0010\u0010&\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b&\u0010!J\u0010\u0010(\u001a\u00020'HÖ\u0001¢\u0006\u0004\b(\u0010)J\u001a\u0010,\u001a\u00020\u00022\b\u0010+\u001a\u0004\u0018\u00010*HÖ\u0003¢\u0006\u0004\b,\u0010-R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010.R\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010/\u001a\u0004\b0\u0010\u001bR\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u00101R\u0014\u0010\b\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010.R\u0014\u0010\t\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010.R\u001a\u0010\u000b\u001a\u00020\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u00102\u001a\u0004\b3\u0010\u001dR&\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000e\u00104\u001a\u0004\b5\u0010\u001fR\u001a\u0010\u000f\u001a\u00020\r8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000f\u00106\u001a\u0004\b7\u0010!R\u001a\u0010\u0011\u001a\u00020\u00108\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0011\u00108\u001a\u0004\b9\u0010#R\u001a\u0010;\u001a\u00020:8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>R \u0010@\u001a\b\u0012\u0004\u0012\u00020\r0?8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b@\u0010A\u001a\u0004\bB\u0010C¨\u0006D"}, d2 = {"Lio/github/jan/supabase/postgrest/request/InsertRequest;", "Lio/github/jan/supabase/postgrest/request/PostgrestRequest;", "", "upsert", "Lio/github/jan/supabase/postgrest/query/Returning;", "returning", "Lio/github/jan/supabase/postgrest/query/Count;", "count", "ignoreDuplicates", "defaultToNull", "Ldb/d;", TtmlNode.TAG_BODY, "", "", "urlParams", "schema", "Lio/ktor/http/Headers;", "headers", "<init>", "(ZLio/github/jan/supabase/postgrest/query/Returning;Lio/github/jan/supabase/postgrest/query/Count;ZZLdb/d;Ljava/util/Map;Ljava/lang/String;Lio/ktor/http/Headers;)V", "component1", "()Z", "component3", "()Lio/github/jan/supabase/postgrest/query/Count;", "component4", "component5", "component2", "()Lio/github/jan/supabase/postgrest/query/Returning;", "component6", "()Ldb/d;", "component7", "()Ljava/util/Map;", "component8", "()Ljava/lang/String;", "component9", "()Lio/ktor/http/Headers;", "copy", "(ZLio/github/jan/supabase/postgrest/query/Returning;Lio/github/jan/supabase/postgrest/query/Count;ZZLdb/d;Ljava/util/Map;Ljava/lang/String;Lio/ktor/http/Headers;)Lio/github/jan/supabase/postgrest/request/InsertRequest;", "toString", "", "hashCode", "()I", "", "other", "equals", "(Ljava/lang/Object;)Z", "Z", "Lio/github/jan/supabase/postgrest/query/Returning;", "getReturning", "Lio/github/jan/supabase/postgrest/query/Count;", "Ldb/d;", "getBody", "Ljava/util/Map;", "getUrlParams", "Ljava/lang/String;", "getSchema", "Lio/ktor/http/Headers;", "getHeaders", "Lio/ktor/http/HttpMethod;", "method", "Lio/ktor/http/HttpMethod;", "getMethod", "()Lio/ktor/http/HttpMethod;", "", "prefer", "Ljava/util/List;", "getPrefer", "()Ljava/util/List;", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class InsertRequest implements PostgrestRequest {
    private final d body;
    private final Count count;
    private final boolean defaultToNull;
    private final Headers headers;
    private final boolean ignoreDuplicates;
    private final HttpMethod method;
    private final List<String> prefer;
    private final Returning returning;
    private final String schema;
    private final boolean upsert;
    private final Map<String, String> urlParams;

    public InsertRequest(boolean z, Returning returning, Count count, boolean z5, boolean z10, d dVar, Map<String, String> map, String str, Headers headers) {
        this.upsert = z;
        this.returning = returning;
        this.count = count;
        this.ignoreDuplicates = z5;
        this.defaultToNull = z10;
        this.body = dVar;
        this.urlParams = map;
        this.schema = str;
        this.headers = headers;
        this.method = HttpMethod.INSTANCE.getPost();
        c cVarN = a.n();
        cVarN.add("return=" + getReturning().getIdentifier());
        if (z) {
            StringBuilder sb2 = new StringBuilder("resolution=");
            sb2.append(z5 ? "ignore" : "merge");
            sb2.append("-duplicates");
            cVarN.add(sb2.toString());
        }
        if (!z10) {
            cVarN.add("missing=default");
        }
        if (count != null) {
            cVarN.add("count=" + count.getIdentifier());
        }
        this.prefer = a.e(cVarN);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    private final boolean getUpsert() {
        return this.upsert;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    private final Count getCount() {
        return this.count;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    private final boolean getIgnoreDuplicates() {
        return this.ignoreDuplicates;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    private final boolean getDefaultToNull() {
        return this.defaultToNull;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ InsertRequest copy$default(InsertRequest insertRequest, boolean z, Returning returning, Count count, boolean z5, boolean z10, d dVar, Map map, String str, Headers headers, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = insertRequest.upsert;
        }
        if ((i10 & 2) != 0) {
            returning = insertRequest.returning;
        }
        if ((i10 & 4) != 0) {
            count = insertRequest.count;
        }
        if ((i10 & 8) != 0) {
            z5 = insertRequest.ignoreDuplicates;
        }
        if ((i10 & 16) != 0) {
            z10 = insertRequest.defaultToNull;
        }
        if ((i10 & 32) != 0) {
            dVar = insertRequest.body;
        }
        if ((i10 & 64) != 0) {
            map = insertRequest.urlParams;
        }
        if ((i10 & 128) != 0) {
            str = insertRequest.schema;
        }
        if ((i10 & 256) != 0) {
            headers = insertRequest.headers;
        }
        String str2 = str;
        Headers headers2 = headers;
        d dVar2 = dVar;
        Map map2 = map;
        boolean z11 = z10;
        Count count2 = count;
        return insertRequest.copy(z, returning, count2, z5, z11, dVar2, map2, str2, headers2);
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Returning getReturning() {
        return this.returning;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final d getBody() {
        return this.body;
    }

    public final Map<String, String> component7() {
        return this.urlParams;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getSchema() {
        return this.schema;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final Headers getHeaders() {
        return this.headers;
    }

    public final InsertRequest copy(boolean upsert, Returning returning, Count count, boolean ignoreDuplicates, boolean defaultToNull, d body, Map<String, String> urlParams, String schema, Headers headers) {
        return new InsertRequest(upsert, returning, count, ignoreDuplicates, defaultToNull, body, urlParams, schema, headers);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InsertRequest)) {
            return false;
        }
        InsertRequest insertRequest = (InsertRequest) other;
        return this.upsert == insertRequest.upsert && p.a(this.returning, insertRequest.returning) && this.count == insertRequest.count && this.ignoreDuplicates == insertRequest.ignoreDuplicates && this.defaultToNull == insertRequest.defaultToNull && p.a(this.body, insertRequest.body) && p.a(this.urlParams, insertRequest.urlParams) && p.a(this.schema, insertRequest.schema) && p.a(this.headers, insertRequest.headers);
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

    public int hashCode() {
        int iHashCode = (this.returning.hashCode() + ((this.upsert ? 1231 : 1237) * 31)) * 31;
        Count count = this.count;
        return this.headers.hashCode() + androidx.compose.foundation.c.c(a2.g(this.urlParams, androidx.compose.material3.d.e(this.body.f14883i, (((((iHashCode + (count == null ? 0 : count.hashCode())) * 31) + (this.ignoreDuplicates ? 1231 : 1237)) * 31) + (this.defaultToNull ? 1231 : 1237)) * 31, 31), 31), 31, this.schema);
    }

    public String toString() {
        return "InsertRequest(upsert=" + this.upsert + ", returning=" + this.returning + ", count=" + this.count + ", ignoreDuplicates=" + this.ignoreDuplicates + ", defaultToNull=" + this.defaultToNull + ", body=" + this.body + ", urlParams=" + this.urlParams + ", schema=" + this.schema + ", headers=" + this.headers + ')';
    }

    @Override // io.github.jan.supabase.postgrest.request.PostgrestRequest
    public d getBody() {
        return this.body;
    }

    public /* synthetic */ InsertRequest(boolean z, Returning returning, Count count, boolean z5, boolean z10, d dVar, Map map, String str, Headers headers, int i10, h hVar) {
        this((i10 & 1) != 0 ? false : z, (i10 & 2) != 0 ? Returning.Minimal.INSTANCE : returning, (i10 & 4) != 0 ? null : count, (i10 & 8) != 0 ? false : z5, (i10 & 16) != 0 ? false : z10, dVar, map, str, (i10 & 256) != 0 ? Headers.INSTANCE.getEmpty() : headers);
    }
}
