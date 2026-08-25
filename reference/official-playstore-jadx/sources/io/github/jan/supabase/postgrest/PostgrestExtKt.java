package io.github.jan.supabase.postgrest;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import db.a;
import db.b;
import db.m;
import io.github.jan.supabase.postgrest.executor.RestRequestExecutor;
import io.github.jan.supabase.postgrest.query.PostgrestRequestBuilder;
import io.github.jan.supabase.postgrest.request.RpcRequest;
import io.github.jan.supabase.postgrest.result.PostgrestResult;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aU\u0010\u000e\u001a\u00020\r\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00028\u00002\b\b\u0002\u0010\u0007\u001a\u00020\u00062\u0019\b\u0002\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086H¢\u0006\u0004\b\u000e\u0010\u000f\u001aA\u0010\u000e\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00062\u0019\b\u0002\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086H¢\u0006\u0004\b\u000e\u0010\u0010¨\u0006\u0011"}, d2 = {"", "T", "Lio/github/jan/supabase/postgrest/Postgrest;", "", "function", "parameters", "", TtmlNode.TAG_HEAD, "Lkotlin/Function1;", "Lio/github/jan/supabase/postgrest/query/PostgrestRequestBuilder;", "Lx6/t0;", "Lx6/n;", "filter", "Lio/github/jan/supabase/postgrest/result/PostgrestResult;", "rpc", "(Lio/github/jan/supabase/postgrest/Postgrest;Ljava/lang/String;Ljava/lang/Object;ZLr7/l;Ld7/d;)Ljava/lang/Object;", "(Lio/github/jan/supabase/postgrest/Postgrest;Ljava/lang/String;ZLr7/l;Ld7/d;)Ljava/lang/Object;", "postgrest-kt_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class PostgrestExtKt {

    /* JADX INFO: renamed from: io.github.jan.supabase.postgrest.PostgrestExtKt$rpc$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000*\u00020\u0002H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"", "T", "Lio/github/jan/supabase/postgrest/query/PostgrestRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/github/jan/supabase/postgrest/query/PostgrestRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class AnonymousClass2 extends r implements l<PostgrestRequestBuilder, t0> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        public final void invoke(PostgrestRequestBuilder postgrestRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((PostgrestRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.postgrest.PostgrestExtKt$rpc$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/github/jan/supabase/postgrest/query/PostgrestRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/github/jan/supabase/postgrest/query/PostgrestRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class AnonymousClass4 extends r implements l<PostgrestRequestBuilder, t0> {
        public static final AnonymousClass4 INSTANCE = new AnonymousClass4();

        public AnonymousClass4() {
            super(1);
        }

        public final void invoke(PostgrestRequestBuilder postgrestRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((PostgrestRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> Object rpc(Postgrest postgrest, String str, T t2, boolean z, l<? super PostgrestRequestBuilder, t0> lVar, d<? super PostgrestResult> dVar) {
        if (!(t2 instanceof m)) {
            postgrest.getSerializer();
            a aVar = b.f14877d;
            p.h();
            throw null;
        }
        PostgrestRequestBuilder postgrestRequestBuilder = new PostgrestRequestBuilder(postgrest.getConfig().getPropertyConversionMethod());
        lVar.invoke(postgrestRequestBuilder);
        return RestRequestExecutor.INSTANCE.execute(postgrest, androidx.compose.material3.d.C("rpc/", str), new RpcRequest(z, postgrestRequestBuilder.getCount(), UtilsKt.mapToFirstValue(postgrestRequestBuilder.getParams()), (m) t2), dVar);
    }

    private static final Object rpc$$forInline(Postgrest postgrest, String str, boolean z, l<? super PostgrestRequestBuilder, t0> lVar, d<? super PostgrestResult> dVar) {
        PostgrestRequestBuilder postgrestRequestBuilder = new PostgrestRequestBuilder(postgrest.getConfig().getPropertyConversionMethod());
        lVar.invoke(postgrestRequestBuilder);
        return RestRequestExecutor.INSTANCE.execute(postgrest, androidx.compose.material3.d.C("rpc/", str), new RpcRequest(z, postgrestRequestBuilder.getCount(), UtilsKt.mapToFirstValue(postgrestRequestBuilder.getParams()), null, 8, null), dVar);
    }

    public static Object rpc$default(Postgrest postgrest, String str, Object obj, boolean z, l lVar, d dVar, int i10, Object obj2) {
        if ((i10 & 4) != 0) {
            z = false;
        }
        if ((i10 & 8) != 0) {
            lVar = AnonymousClass2.INSTANCE;
        }
        if (!(obj instanceof m)) {
            postgrest.getSerializer();
            a aVar = b.f14877d;
            p.h();
            throw null;
        }
        PostgrestRequestBuilder postgrestRequestBuilder = new PostgrestRequestBuilder(postgrest.getConfig().getPropertyConversionMethod());
        lVar.invoke(postgrestRequestBuilder);
        return RestRequestExecutor.INSTANCE.execute(postgrest, androidx.compose.material3.d.C("rpc/", str), new RpcRequest(z, postgrestRequestBuilder.getCount(), UtilsKt.mapToFirstValue(postgrestRequestBuilder.getParams()), (m) obj), dVar);
    }

    public static final Object rpc(Postgrest postgrest, String str, boolean z, l<? super PostgrestRequestBuilder, t0> lVar, d<? super PostgrestResult> dVar) {
        PostgrestRequestBuilder postgrestRequestBuilder = new PostgrestRequestBuilder(postgrest.getConfig().getPropertyConversionMethod());
        lVar.invoke(postgrestRequestBuilder);
        return RestRequestExecutor.INSTANCE.execute(postgrest, androidx.compose.material3.d.C("rpc/", str), new RpcRequest(z, postgrestRequestBuilder.getCount(), UtilsKt.mapToFirstValue(postgrestRequestBuilder.getParams()), null, 8, null), dVar);
    }

    public static /* synthetic */ Object rpc$default(Postgrest postgrest, String str, boolean z, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = false;
        }
        boolean z5 = z;
        if ((i10 & 4) != 0) {
            lVar = AnonymousClass4.INSTANCE;
        }
        PostgrestRequestBuilder postgrestRequestBuilder = new PostgrestRequestBuilder(postgrest.getConfig().getPropertyConversionMethod());
        lVar.invoke(postgrestRequestBuilder);
        return RestRequestExecutor.INSTANCE.execute(postgrest, androidx.compose.material3.d.C("rpc/", str), new RpcRequest(z5, postgrestRequestBuilder.getCount(), UtilsKt.mapToFirstValue(postgrestRequestBuilder.getParams()), null, 8, null), dVar);
    }
}
