package io.github.jan.supabase.postgrest.result;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import io.github.jan.supabase.postgrest.Postgrest;
import io.ktor.http.Headers;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.reflect.t;
import kotlin.text.o;
import kotlin.text.u;
import x7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0013\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u001c\u0010\u0011\u001a\u00028\u0000\"\n\b\u0000\u0010\u0010\u0018\u0001*\u00020\u0001H\u0086\b¢\u0006\u0004\b\u0011\u0010\u0012J\u001e\u0010\u0013\u001a\u0004\u0018\u00018\u0000\"\n\b\u0000\u0010\u0010\u0018\u0001*\u00020\u0001H\u0086\b¢\u0006\u0004\b\u0013\u0010\u0012J\"\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014\"\n\b\u0000\u0010\u0010\u0018\u0001*\u00020\u0001H\u0086\b¢\u0006\u0004\b\u0015\u0010\u0016J\u001c\u0010\u0017\u001a\u00028\u0000\"\n\b\u0000\u0010\u0010\u0018\u0001*\u00020\u0001H\u0086\b¢\u0006\u0004\b\u0017\u0010\u0012J\u001e\u0010\u0018\u001a\u0004\u0018\u00018\u0000\"\n\b\u0000\u0010\u0010\u0018\u0001*\u00020\u0001H\u0086\b¢\u0006\u0004\b\u0018\u0010\u0012J\u0010\u0010\u0019\u001a\u00020\u0002H\u0086\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0004H\u0086\u0002¢\u0006\u0004\b\u001b\u0010\u001cR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001d\u001a\u0004\b\u001e\u0010\u001aR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001f\u001a\u0004\b \u0010\u001cR \u0010\u0007\u001a\u00020\u00068\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\b\u0007\u0010!\u0012\u0004\b$\u0010%\u001a\u0004\b\"\u0010#R\u0016\u0010&\u001a\u0004\u0018\u00010\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010\u001d¨\u0006'"}, d2 = {"Lio/github/jan/supabase/postgrest/result/PostgrestResult;", "", "", "data", "Lio/ktor/http/Headers;", "headers", "Lio/github/jan/supabase/postgrest/Postgrest;", "postgrest", "<init>", "(Ljava/lang/String;Lio/ktor/http/Headers;Lio/github/jan/supabase/postgrest/Postgrest;)V", "", "countOrNull", "()Ljava/lang/Long;", "Lx7/l;", "rangeOrNull", "()Lx7/l;", "T", "decodeAs", "()Ljava/lang/Object;", "decodeAsOrNull", "", "decodeList", "()Ljava/util/List;", "decodeSingle", "decodeSingleOrNull", "component1", "()Ljava/lang/String;", "component2", "()Lio/ktor/http/Headers;", "Ljava/lang/String;", "getData", "Lio/ktor/http/Headers;", "getHeaders", "Lio/github/jan/supabase/postgrest/Postgrest;", "getPostgrest", "()Lio/github/jan/supabase/postgrest/Postgrest;", "getPostgrest$annotations", "()V", "contentRange", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class PostgrestResult {
    private final String contentRange;
    private final String data;
    private final Headers headers;
    private final Postgrest postgrest;

    public PostgrestResult(String str, Headers headers, Postgrest postgrest) {
        this.data = str;
        this.headers = headers;
        this.postgrest = postgrest;
        this.contentRange = headers.get("Content-Range");
    }

    public static /* synthetic */ void getPostgrest$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getData() {
        return this.data;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Headers getHeaders() {
        return this.headers;
    }

    public final Long countOrNull() {
        String str = this.contentRange;
        if (str != null) {
            return u.S(o.D0(str, DomExceptionUtils.SEPARATOR, str));
        }
        return null;
    }

    public final <T> T decodeAs() {
        getPostgrest().getSerializer();
        getData();
        p.h();
        throw null;
    }

    public final <T> T decodeAsOrNull() {
        try {
            getPostgrest().getSerializer();
            getData();
            p.h();
            throw null;
        } catch (Exception unused) {
            return null;
        }
    }

    public final <T> List<T> decodeList() {
        getPostgrest().getSerializer();
        getData();
        t tVar = t.f19910c;
        p.h();
        throw null;
    }

    public final <T> T decodeSingle() {
        getPostgrest().getSerializer();
        getData();
        t tVar = t.f19910c;
        p.h();
        throw null;
    }

    public final <T> T decodeSingleOrNull() {
        getPostgrest().getSerializer();
        getData();
        t tVar = t.f19910c;
        p.h();
        throw null;
    }

    public final String getData() {
        return this.data;
    }

    public final Headers getHeaders() {
        return this.headers;
    }

    public final Postgrest getPostgrest() {
        return this.postgrest;
    }

    public final l rangeOrNull() {
        String str = this.contentRange;
        if (str == null) {
            return null;
        }
        List listY0 = o.y0(o.H0(str, DomExceptionUtils.SEPARATOR), new String[]{"-"}, 0, 6);
        return new l(Long.parseLong((String) listY0.get(0)), Long.parseLong((String) listY0.get(1)));
    }
}
