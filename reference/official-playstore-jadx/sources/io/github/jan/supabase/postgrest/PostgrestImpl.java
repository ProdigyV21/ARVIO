package io.github.jan.supabase.postgrest;

import d7.d;
import f7.c;
import f7.e;
import io.github.jan.supabase.SupabaseClient;
import io.github.jan.supabase.SupabaseSerializer;
import io.github.jan.supabase.annotations.SupabaseInternal;
import io.github.jan.supabase.gotrue.AuthenticatedSupabaseApi;
import io.github.jan.supabase.gotrue.AuthenticatedSupabaseApiKt;
import io.github.jan.supabase.postgrest.Postgrest;
import io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\u000eJ\u0018\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0096@¢\u0006\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\"\u0010\u001b\u001a\u00020\u001a8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001d\u0010\"\u001a\u00020!8\u0006¢\u0006\u0012\n\u0004\b\"\u0010#\u0012\u0004\b&\u0010'\u001a\u0004\b$\u0010%R\u0014\u0010+\u001a\u00020(8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*R\u0014\u0010.\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-¨\u0006/"}, d2 = {"Lio/github/jan/supabase/postgrest/PostgrestImpl;", "Lio/github/jan/supabase/postgrest/Postgrest;", "Lio/github/jan/supabase/SupabaseClient;", "supabaseClient", "Lio/github/jan/supabase/postgrest/Postgrest$Config;", "config", "<init>", "(Lio/github/jan/supabase/SupabaseClient;Lio/github/jan/supabase/postgrest/Postgrest$Config;)V", "", "table", "Lio/github/jan/supabase/postgrest/query/PostgrestQueryBuilder;", "from", "(Ljava/lang/String;)Lio/github/jan/supabase/postgrest/query/PostgrestQueryBuilder;", "schema", "(Ljava/lang/String;Ljava/lang/String;)Lio/github/jan/supabase/postgrest/query/PostgrestQueryBuilder;", "Lio/ktor/client/statement/HttpResponse;", "response", "Lio/github/jan/supabase/exceptions/RestException;", "parseErrorResponse", "(Lio/ktor/client/statement/HttpResponse;Ld7/d;)Ljava/lang/Object;", "Lio/github/jan/supabase/SupabaseClient;", "getSupabaseClient", "()Lio/github/jan/supabase/SupabaseClient;", "Lio/github/jan/supabase/postgrest/Postgrest$Config;", "getConfig", "()Lio/github/jan/supabase/postgrest/Postgrest$Config;", "Lio/github/jan/supabase/SupabaseSerializer;", "serializer", "Lio/github/jan/supabase/SupabaseSerializer;", "getSerializer", "()Lio/github/jan/supabase/SupabaseSerializer;", "setSerializer", "(Lio/github/jan/supabase/SupabaseSerializer;)V", "Lio/github/jan/supabase/gotrue/AuthenticatedSupabaseApi;", "api", "Lio/github/jan/supabase/gotrue/AuthenticatedSupabaseApi;", "getApi", "()Lio/github/jan/supabase/gotrue/AuthenticatedSupabaseApi;", "getApi$annotations", "()V", "", "getApiVersion", "()I", "apiVersion", "getPluginKey", "()Ljava/lang/String;", "pluginKey", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class PostgrestImpl implements Postgrest {
    private final AuthenticatedSupabaseApi api;
    private final Postgrest.Config config;
    private SupabaseSerializer serializer;
    private final SupabaseClient supabaseClient;

    /* JADX INFO: renamed from: io.github.jan.supabase.postgrest.PostgrestImpl$parseErrorResponse$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.postgrest.PostgrestImpl", f = "PostgrestImpl.kt", l = {56}, m = "parseErrorResponse")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PostgrestImpl.this.parseErrorResponse(null, this);
        }
    }

    public PostgrestImpl(SupabaseClient supabaseClient, Postgrest.Config config) {
        this.supabaseClient = supabaseClient;
        this.config = config;
        SupabaseSerializer serializer = getConfig().getSerializer();
        this.serializer = serializer == null ? getSupabaseClient().getDefaultSerializer() : serializer;
        this.api = AuthenticatedSupabaseApiKt.authenticatedSupabaseApi$default(getSupabaseClient(), this, (l) null, 2, (Object) null);
    }

    public static /* synthetic */ void getApi$annotations() {
    }

    @Override // io.github.jan.supabase.plugins.SupabasePlugin
    public Object close(d<? super t0> dVar) {
        return Postgrest.DefaultImpls.close(this, dVar);
    }

    @Override // io.github.jan.supabase.postgrest.Postgrest
    public PostgrestQueryBuilder from(String table) {
        return new PostgrestQueryBuilder(this, table, null, 4, null);
    }

    @Override // io.github.jan.supabase.postgrest.Postgrest
    public PostgrestQueryBuilder get(String str) {
        return Postgrest.DefaultImpls.get(this, str);
    }

    public final AuthenticatedSupabaseApi getApi() {
        return this.api;
    }

    @Override // io.github.jan.supabase.plugins.MainPlugin
    public int getApiVersion() {
        return 1;
    }

    @Override // io.github.jan.supabase.plugins.MainPlugin
    public String getPluginKey() {
        return Postgrest.INSTANCE.getKey();
    }

    @Override // io.github.jan.supabase.plugins.CustomSerializationPlugin
    public SupabaseSerializer getSerializer() {
        return this.serializer;
    }

    @Override // io.github.jan.supabase.plugins.MainPlugin
    public SupabaseClient getSupabaseClient() {
        return this.supabaseClient;
    }

    @Override // io.github.jan.supabase.plugins.MainPlugin
    @SupabaseInternal
    public void init() {
        Postgrest.DefaultImpls.init(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.github.jan.supabase.plugins.MainPlugin
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object parseErrorResponse(io.ktor.client.statement.HttpResponse r11, d7.d<? super io.github.jan.supabase.exceptions.RestException> r12) {
        /*
            Method dump skipped, instruction units count: 219
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.postgrest.PostgrestImpl.parseErrorResponse(io.ktor.client.statement.HttpResponse, d7.d):java.lang.Object");
    }

    @Override // io.github.jan.supabase.plugins.MainPlugin
    public String resolveUrl(String str) {
        return Postgrest.DefaultImpls.resolveUrl(this, str);
    }

    public void setSerializer(SupabaseSerializer supabaseSerializer) {
        this.serializer = supabaseSerializer;
    }

    @Override // io.github.jan.supabase.postgrest.Postgrest
    public PostgrestQueryBuilder from(String schema, String table) {
        return new PostgrestQueryBuilder(this, table, schema);
    }

    @Override // io.github.jan.supabase.postgrest.Postgrest
    public PostgrestQueryBuilder get(String str, String str2) {
        return Postgrest.DefaultImpls.get(this, str, str2);
    }

    @Override // io.github.jan.supabase.plugins.MainPlugin
    public Postgrest.Config getConfig() {
        return this.config;
    }
}
