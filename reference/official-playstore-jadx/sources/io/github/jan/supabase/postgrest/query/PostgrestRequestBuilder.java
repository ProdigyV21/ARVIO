package io.github.jan.supabase.postgrest.query;

import androidx.fragment.app.a2;
import io.github.jan.supabase.gotrue.PostgrestFilterDSL;
import io.github.jan.supabase.postgrest.PropertyConversionMethod;
import io.github.jan.supabase.postgrest.query.Returning;
import io.github.jan.supabase.postgrest.query.filter.PostgrestFilterBuilder;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaders;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.h0;
import kotlin.collections.x;
import r7.l;
import t7.a;
import x6.t0;
import z6.c;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\tJ\u001a\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\nø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\bH\u0007¢\u0006\u0004\b\u000f\u0010\u0010J3\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u00152\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0014\u0010\u0018J!\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00192\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u001a\u0010\u001bJ)\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u00192\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u001e\u0010\u001fJ!\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020 2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u001e\u0010!J\r\u0010\"\u001a\u00020\b¢\u0006\u0004\b\"\u0010\u0010J\r\u0010#\u001a\u00020\b¢\u0006\u0004\b#\u0010\u0010JI\u0010*\u001a\u00020\b2\b\b\u0002\u0010$\u001a\u00020\u00152\b\b\u0002\u0010%\u001a\u00020\u00152\b\b\u0002\u0010&\u001a\u00020\u00152\b\b\u0002\u0010'\u001a\u00020\u00152\b\b\u0002\u0010(\u001a\u00020\u00152\b\b\u0002\u0010)\u001a\u00020\u0011¢\u0006\u0004\b*\u0010+J1\u00101\u001a\u00020\b2\u001c\u00100\u001a\u0018\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\b0,¢\u0006\u0002\b.¢\u0006\u0002\b/H\u0086\bø\u0001\u0001¢\u0006\u0004\b1\u00102R \u0010\u0003\u001a\u00020\u00028\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\b\u0003\u00103\u0012\u0004\b6\u0010\u0010\u001a\u0004\b4\u00105R(\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u00107\u001a\u0004\u0018\u00010\u00068\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u0007\u00108\u001a\u0004\b9\u0010:R$\u0010<\u001a\u00020;2\u0006\u00107\u001a\u00020;8\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?R2\u0010B\u001a\u0014\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110A0@8\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\bB\u0010C\u0012\u0004\bF\u0010\u0010\u001a\u0004\bD\u0010ER \u0010H\u001a\u00020G8\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\bH\u0010I\u0012\u0004\bL\u0010\u0010\u001a\u0004\bJ\u0010KR#\u0010O\u001a\u0014\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110A0M8F¢\u0006\u0006\u001a\u0004\bN\u0010E\u0082\u0002\u000e\n\u0005\b¡\u001e0\u0001\n\u0005\b\u009920\u0001¨\u0006P"}, d2 = {"Lio/github/jan/supabase/postgrest/query/PostgrestRequestBuilder;", "", "Lio/github/jan/supabase/postgrest/PropertyConversionMethod;", "propertyConversionMethod", "<init>", "(Lio/github/jan/supabase/postgrest/PropertyConversionMethod;)V", "Lio/github/jan/supabase/postgrest/query/Count;", "count", "Lx6/t0;", "(Lio/github/jan/supabase/postgrest/query/Count;)V", "Lio/github/jan/supabase/postgrest/query/Columns;", "columns", "select-fYsiLaM", "(Ljava/lang/String;)V", "select", "single", "()V", "", "column", "Lio/github/jan/supabase/postgrest/query/Order;", "order", "", "nullsFirst", "referencedTable", "(Ljava/lang/String;Lio/github/jan/supabase/postgrest/query/Order;ZLjava/lang/String;)V", "", "limit", "(JLjava/lang/String;)V", "from", "to", "range", "(JJLjava/lang/String;)V", "Lx7/l;", "(Lx7/l;Ljava/lang/String;)V", "geojson", "csv", "analyze", "verbose", "settings", "buffers", "wal", "format", "explain", "(ZZZZZLjava/lang/String;)V", "Lkotlin/Function1;", "Lio/github/jan/supabase/postgrest/query/filter/PostgrestFilterBuilder;", "Lio/github/jan/supabase/gotrue/PostgrestFilterDSL;", "Lx6/n;", "block", "filter", "(Lr7/l;)V", "Lio/github/jan/supabase/postgrest/PropertyConversionMethod;", "getPropertyConversionMethod", "()Lio/github/jan/supabase/postgrest/PropertyConversionMethod;", "getPropertyConversionMethod$annotations", "<set-?>", "Lio/github/jan/supabase/postgrest/query/Count;", "getCount", "()Lio/github/jan/supabase/postgrest/query/Count;", "Lio/github/jan/supabase/postgrest/query/Returning;", "returning", "Lio/github/jan/supabase/postgrest/query/Returning;", "getReturning", "()Lio/github/jan/supabase/postgrest/query/Returning;", "", "", "_params", "Ljava/util/Map;", "get_params", "()Ljava/util/Map;", "get_params$annotations", "Lio/ktor/http/HeadersBuilder;", "headers", "Lio/ktor/http/HeadersBuilder;", "getHeaders", "()Lio/ktor/http/HeadersBuilder;", "getHeaders$annotations", "", "getParams", "params", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@PostgrestFilterDSL
public final class PostgrestRequestBuilder {
    private Count count;
    private final PropertyConversionMethod propertyConversionMethod;
    private Returning returning = Returning.Minimal.INSTANCE;
    private final Map<String, List<String>> _params = new LinkedHashMap();
    private final HeadersBuilder headers = new HeadersBuilder(0, 1, null);

    public PostgrestRequestBuilder(PropertyConversionMethod propertyConversionMethod) {
        this.propertyConversionMethod = propertyConversionMethod;
    }

    public static /* synthetic */ void explain$default(PostgrestRequestBuilder postgrestRequestBuilder, boolean z, boolean z5, boolean z10, boolean z11, boolean z12, String str, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        if ((i10 & 2) != 0) {
            z5 = false;
        }
        if ((i10 & 4) != 0) {
            z10 = false;
        }
        if ((i10 & 8) != 0) {
            z11 = false;
        }
        if ((i10 & 16) != 0) {
            z12 = false;
        }
        if ((i10 & 32) != 0) {
            str = "text";
        }
        postgrestRequestBuilder.explain(z, z5, z10, z11, z12, str);
    }

    public static /* synthetic */ void getHeaders$annotations() {
    }

    public static /* synthetic */ void getPropertyConversionMethod$annotations() {
    }

    public static /* synthetic */ void get_params$annotations() {
    }

    public static /* synthetic */ void limit$default(PostgrestRequestBuilder postgrestRequestBuilder, long j10, String str, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str = null;
        }
        postgrestRequestBuilder.limit(j10, str);
    }

    public static /* synthetic */ void order$default(PostgrestRequestBuilder postgrestRequestBuilder, String str, Order order, boolean z, String str2, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            z = false;
        }
        if ((i10 & 8) != 0) {
            str2 = null;
        }
        postgrestRequestBuilder.order(str, order, z, str2);
    }

    public static /* synthetic */ void range$default(PostgrestRequestBuilder postgrestRequestBuilder, long j10, long j11, String str, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            str = null;
        }
        postgrestRequestBuilder.range(j10, j11, str);
    }

    /* JADX INFO: renamed from: select-fYsiLaM$default, reason: not valid java name */
    public static /* synthetic */ void m6656selectfYsiLaM$default(PostgrestRequestBuilder postgrestRequestBuilder, String str, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = Columns.INSTANCE.m6648getALLU9NzzuM();
        }
        postgrestRequestBuilder.m6657selectfYsiLaM(str);
    }

    public final void count(Count count) {
        this.count = count;
    }

    public final void csv() {
        this.headers.set(HttpHeaders.INSTANCE.getAccept(), "text/csv");
    }

    public final void explain(boolean analyze, boolean verbose, boolean settings, boolean buffers, boolean wal, String format) {
        c cVarN = a.n();
        if (analyze) {
            cVarN.add("analyze");
        }
        if (verbose) {
            cVarN.add("verbose");
        }
        if (settings) {
            cVarN.add("settings");
        }
        if (buffers) {
            cVarN.add("buffers");
        }
        if (wal) {
            cVarN.add("wal");
        }
        String strU0 = x.u0(a.e(cVarN), "|", null, null, null, 62);
        String str = this.headers.get("Accept");
        if (str == null) {
            str = "application/json";
        }
        HeadersBuilder headersBuilder = this.headers;
        String accept = HttpHeaders.INSTANCE.getAccept();
        StringBuilder sbR = a2.r("application/vnd.pgrst.plan+", format, "; for=\"", str, "\"; options=");
        sbR.append(strU0);
        sbR.append(';');
        headersBuilder.set(accept, sbR.toString());
    }

    public final void filter(l<? super PostgrestFilterBuilder, t0> block) {
        block.invoke(new PostgrestFilterBuilder(getPropertyConversionMethod(), get_params()));
    }

    public final void geojson() {
        this.headers.set(HttpHeaders.INSTANCE.getAccept(), "application/geo+json");
    }

    public final Count getCount() {
        return this.count;
    }

    public final HeadersBuilder getHeaders() {
        return this.headers;
    }

    public final Map<String, List<String>> getParams() {
        return h0.B0(this._params);
    }

    public final PropertyConversionMethod getPropertyConversionMethod() {
        return this.propertyConversionMethod;
    }

    public final Returning getReturning() {
        return this.returning;
    }

    public final Map<String, List<String>> get_params() {
        return this._params;
    }

    public final void limit(long count, String referencedTable) {
        this._params.put(referencedTable == null ? "limit" : a0.c.l("\"", referencedTable, "\".limit"), Collections.singletonList(String.valueOf(count)));
    }

    public final void order(String column, Order order, boolean nullsFirst, String referencedTable) {
        String strL = referencedTable == null ? "order" : a0.c.l("\"", referencedTable, "\".order");
        Map<String, List<String>> map = this._params;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(column);
        sb2.append('.');
        sb2.append(order.getValue());
        sb2.append('.');
        sb2.append(nullsFirst ? "nullsfirst" : "nullslast");
        map.put(strL, Collections.singletonList(sb2.toString()));
    }

    public final void range(long from, long to, String referencedTable) {
        String strL = referencedTable == null ? "offset" : a0.c.l("\"", referencedTable, "\".offset");
        String strL2 = referencedTable == null ? "limit" : a0.c.l("\"", referencedTable, "\".limit");
        this._params.put(strL, Collections.singletonList(String.valueOf(from)));
        this._params.put(strL2, Collections.singletonList(String.valueOf((to - from) + 1)));
    }

    /* JADX INFO: renamed from: select-fYsiLaM, reason: not valid java name */
    public final void m6657selectfYsiLaM(String columns) {
        this.returning = new Returning.Representation(columns, null);
    }

    public final void single() {
        this.headers.set(HttpHeaders.INSTANCE.getAccept(), "application/vnd.pgrst.object+json");
    }

    public static /* synthetic */ void range$default(PostgrestRequestBuilder postgrestRequestBuilder, x7.l lVar, String str, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str = null;
        }
        postgrestRequestBuilder.range(lVar, str);
    }

    public final void range(x7.l range, String referencedTable) {
        range(range.f22627i, range.f22628l, referencedTable);
    }
}
