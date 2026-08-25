package io.github.jan.supabase.postgrest.executor;

import d7.d;
import f7.c;
import f7.e;
import io.github.jan.supabase.postgrest.request.PostgrestRequest;
import io.ktor.client.request.HttpRequestBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bÁ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0096@¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012HÖ\u0003¢\u0006\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Lio/github/jan/supabase/postgrest/executor/RestRequestExecutor;", "Lio/github/jan/supabase/postgrest/executor/RequestExecutor;", "<init>", "()V", "Lio/github/jan/supabase/postgrest/Postgrest;", "postgrest", "", "path", "Lio/github/jan/supabase/postgrest/request/PostgrestRequest;", "request", "Lio/github/jan/supabase/postgrest/result/PostgrestResult;", "execute", "(Lio/github/jan/supabase/postgrest/Postgrest;Ljava/lang/String;Lio/github/jan/supabase/postgrest/request/PostgrestRequest;Ld7/d;)Ljava/lang/Object;", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class RestRequestExecutor implements RequestExecutor {
    public static final RestRequestExecutor INSTANCE = new RestRequestExecutor();

    /* JADX INFO: renamed from: io.github.jan.supabase.postgrest.executor.RestRequestExecutor$execute$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.postgrest.executor.RestRequestExecutor", f = "RestRequestExecutor.kt", l = {17, 19}, m = "execute")
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
            return RestRequestExecutor.this.execute(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.postgrest.executor.RestRequestExecutor$execute$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class AnonymousClass2 extends r implements l<HttpRequestBuilder, t0> {
        final /* synthetic */ PostgrestRequest $request;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(PostgrestRequest postgrestRequest) {
            super(1);
            this.$request = postgrestRequest;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
            PostgrestHttpExtensionKt.configurePostgrestRequest(httpRequestBuilder, this.$request);
        }
    }

    private RestRequestExecutor() {
    }

    public boolean equals(Object other) {
        return this == other || (other instanceof RestRequestExecutor);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.github.jan.supabase.postgrest.executor.RequestExecutor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object execute(io.github.jan.supabase.postgrest.Postgrest r6, java.lang.String r7, io.github.jan.supabase.postgrest.request.PostgrestRequest r8, d7.d<? super io.github.jan.supabase.postgrest.result.PostgrestResult> r9) {
        /*
            r5 = this;
            boolean r0 = r9 instanceof io.github.jan.supabase.postgrest.executor.RestRequestExecutor.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r9
            io.github.jan.supabase.postgrest.executor.RestRequestExecutor$execute$1 r0 = (io.github.jan.supabase.postgrest.executor.RestRequestExecutor.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.github.jan.supabase.postgrest.executor.RestRequestExecutor$execute$1 r0 = new io.github.jan.supabase.postgrest.executor.RestRequestExecutor$execute$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L3a
            if (r1 == r3) goto L32
            if (r1 != r2) goto L2a
            k2.c.G(r9)
            return r9
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L32:
            java.lang.Object r6 = r0.L$0
            io.github.jan.supabase.postgrest.Postgrest r6 = (io.github.jan.supabase.postgrest.Postgrest) r6
            k2.c.G(r9)
            goto L54
        L3a:
            k2.c.G(r9)
            r9 = r6
            io.github.jan.supabase.postgrest.PostgrestImpl r9 = (io.github.jan.supabase.postgrest.PostgrestImpl) r9
            io.github.jan.supabase.gotrue.AuthenticatedSupabaseApi r9 = r9.getApi()
            io.github.jan.supabase.postgrest.executor.RestRequestExecutor$execute$2 r1 = new io.github.jan.supabase.postgrest.executor.RestRequestExecutor$execute$2
            r1.<init>(r8)
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r9 = r9.request(r7, r1, r0)
            if (r9 != r4) goto L54
            goto L61
        L54:
            io.ktor.client.statement.HttpResponse r9 = (io.ktor.client.statement.HttpResponse) r9
            r7 = 0
            r0.L$0 = r7
            r0.label = r2
            java.lang.Object r6 = io.github.jan.supabase.postgrest.executor.PostgrestHttpExtensionKt.asPostgrestResult(r9, r6, r0)
            if (r6 != r4) goto L62
        L61:
            return r4
        L62:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.postgrest.executor.RestRequestExecutor.execute(io.github.jan.supabase.postgrest.Postgrest, java.lang.String, io.github.jan.supabase.postgrest.request.PostgrestRequest, d7.d):java.lang.Object");
    }

    public int hashCode() {
        return -105643338;
    }

    public String toString() {
        return "RestRequestExecutor";
    }
}
