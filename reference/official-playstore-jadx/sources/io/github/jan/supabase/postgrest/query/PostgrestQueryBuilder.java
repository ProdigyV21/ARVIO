package io.github.jan.supabase.postgrest.query;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import db.a;
import db.b;
import io.github.jan.supabase.postgrest.Postgrest;
import io.github.jan.supabase.postgrest.UtilsKt;
import io.github.jan.supabase.postgrest.executor.RestRequestExecutor;
import io.github.jan.supabase.postgrest.request.DeleteRequest;
import io.github.jan.supabase.postgrest.request.SelectRequest;
import io.github.jan.supabase.postgrest.request.UpdateRequest;
import io.github.jan.supabase.postgrest.result.PostgrestResult;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import kotlin.reflect.t;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 02\u00020\u0001:\u00010B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJG\u0010\u0016\u001a\u00020\u00132\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u000b2\u001e\b\u0002\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u0011H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015Je\u0010\u001d\u001a\u00020\u0013\"\n\b\u0000\u0010\u0017\u0018\u0001*\u00020\u00012\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00182\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u001b\u001a\u00020\u000b2\b\b\u0002\u0010\u001c\u001a\u00020\u000b2\u0019\b\u0002\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0011H\u0086H¢\u0006\u0004\b\u001d\u0010\u001eJ_\u0010\u001d\u001a\u00020\u0013\"\n\b\u0000\u0010\u0017\u0018\u0001*\u00020\u00012\u0006\u0010\u001f\u001a\u00028\u00002\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u001b\u001a\u00020\u000b2\b\b\u0002\u0010\u001c\u001a\u00020\u000b2\u0019\b\u0002\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0011H\u0086H¢\u0006\u0004\b\u001d\u0010 JE\u0010!\u001a\u00020\u0013\"\n\b\u0000\u0010\u0017\u0018\u0001*\u00020\u00012\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00182\u0019\b\u0002\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0011H\u0086H¢\u0006\u0004\b!\u0010\"J?\u0010!\u001a\u00020\u0013\"\n\b\u0000\u0010\u0017\u0018\u0001*\u00020\u00012\u0006\u0010\u001f\u001a\u00028\u00002\u0019\b\u0002\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0011H\u0086H¢\u0006\u0004\b!\u0010#JF\u0010%\u001a\u00020\u00132\u0019\b\u0006\u0010%\u001a\u0013\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u00112\u0019\b\u0002\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0011H\u0086H¢\u0006\u0004\b%\u0010&J?\u0010%\u001a\u00020\u0013\"\n\b\u0000\u0010\u0017\u0018\u0001*\u00020\u00012\u0006\u0010\u001f\u001a\u00028\u00002\u0019\b\u0002\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0011H\u0086H¢\u0006\u0004\b%\u0010#J+\u0010'\u001a\u00020\u00132\u0019\b\u0002\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0011H\u0086H¢\u0006\u0004\b'\u0010(R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010)\u001a\u0004\b*\u0010+R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010,\u001a\u0004\b-\u0010.R\u0017\u0010\u0006\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010,\u001a\u0004\b/\u0010.\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00061"}, d2 = {"Lio/github/jan/supabase/postgrest/query/PostgrestQueryBuilder;", "", "Lio/github/jan/supabase/postgrest/Postgrest;", "postgrest", "", "table", "schema", "<init>", "(Lio/github/jan/supabase/postgrest/Postgrest;Ljava/lang/String;Ljava/lang/String;)V", "Lio/github/jan/supabase/postgrest/query/Columns;", "columns", "", TtmlNode.TAG_HEAD, "Lkotlin/Function1;", "Lio/github/jan/supabase/postgrest/query/PostgrestRequestBuilder;", "Lx6/t0;", "Lio/github/jan/supabase/gotrue/PostgrestFilterDSL;", "Lx6/n;", "request", "Lio/github/jan/supabase/postgrest/result/PostgrestResult;", "select-xUsJgKc", "(Ljava/lang/String;ZLr7/l;Ld7/d;)Ljava/lang/Object;", "select", "T", "", "values", "onConflict", "defaultToNull", "ignoreDuplicates", "upsert", "(Ljava/util/List;Ljava/lang/String;ZZLr7/l;Ld7/d;)Ljava/lang/Object;", "value", "(Ljava/lang/Object;Ljava/lang/String;ZZLr7/l;Ld7/d;)Ljava/lang/Object;", "insert", "(Ljava/util/List;Lr7/l;Ld7/d;)Ljava/lang/Object;", "(Ljava/lang/Object;Lr7/l;Ld7/d;)Ljava/lang/Object;", "Lio/github/jan/supabase/postgrest/query/PostgrestUpdate;", "update", "(Lr7/l;Lr7/l;Ld7/d;)Ljava/lang/Object;", "delete", "(Lr7/l;Ld7/d;)Ljava/lang/Object;", "Lio/github/jan/supabase/postgrest/Postgrest;", "getPostgrest", "()Lio/github/jan/supabase/postgrest/Postgrest;", "Ljava/lang/String;", "getTable", "()Ljava/lang/String;", "getSchema", "Companion", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class PostgrestQueryBuilder {
    public static final String HEADER_PREFER = "Prefer";
    private final Postgrest postgrest;
    private final String schema;
    private final String table;

    /* JADX INFO: renamed from: io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder$delete$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/github/jan/supabase/postgrest/query/PostgrestRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/github/jan/supabase/postgrest/query/PostgrestRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
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

    /* JADX INFO: renamed from: io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder$insert$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000*\u00020\u0002H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"", "T", "Lio/github/jan/supabase/postgrest/query/PostgrestRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/github/jan/supabase/postgrest/query/PostgrestRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C17232 extends r implements l<PostgrestRequestBuilder, t0> {
        public static final C17232 INSTANCE = new C17232();

        public C17232() {
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

    /* JADX INFO: renamed from: io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder$insert$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000*\u00020\u0002H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"", "T", "Lio/github/jan/supabase/postgrest/query/PostgrestRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/github/jan/supabase/postgrest/query/PostgrestRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
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

    /* JADX INFO: renamed from: io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder$update$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/github/jan/supabase/postgrest/query/PostgrestUpdate;", "Lx6/t0;", "invoke", "(Lio/github/jan/supabase/postgrest/query/PostgrestUpdate;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C17242 extends r implements l<PostgrestUpdate, t0> {
        public static final C17242 INSTANCE = new C17242();

        public C17242() {
            super(1);
        }

        public final void invoke(PostgrestUpdate postgrestUpdate) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((PostgrestUpdate) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder$update$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/github/jan/supabase/postgrest/query/PostgrestRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/github/jan/supabase/postgrest/query/PostgrestRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class AnonymousClass3 extends r implements l<PostgrestRequestBuilder, t0> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        public AnonymousClass3() {
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

    /* JADX INFO: renamed from: io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder$update$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000*\u00020\u0002H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"", "T", "Lio/github/jan/supabase/postgrest/query/PostgrestRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/github/jan/supabase/postgrest/query/PostgrestRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class AnonymousClass5 extends r implements l<PostgrestRequestBuilder, t0> {
        public static final AnonymousClass5 INSTANCE = new AnonymousClass5();

        public AnonymousClass5() {
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

    /* JADX INFO: renamed from: io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder$upsert$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000*\u00020\u0002H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"", "T", "Lio/github/jan/supabase/postgrest/query/PostgrestRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/github/jan/supabase/postgrest/query/PostgrestRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C17252 extends r implements l<PostgrestRequestBuilder, t0> {
        public static final C17252 INSTANCE = new C17252();

        public C17252() {
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

    /* JADX INFO: renamed from: io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder$upsert$5, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000*\u00020\u0002H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"", "T", "Lio/github/jan/supabase/postgrest/query/PostgrestRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/github/jan/supabase/postgrest/query/PostgrestRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C17265 extends r implements l<PostgrestRequestBuilder, t0> {
        public static final C17265 INSTANCE = new C17265();

        public C17265() {
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

    public PostgrestQueryBuilder(Postgrest postgrest, String str, String str2) {
        this.postgrest = postgrest;
        this.table = str;
        this.schema = str2;
    }

    private final Object delete$$forInline(l<? super PostgrestRequestBuilder, t0> lVar, d<? super PostgrestResult> dVar) {
        PostgrestRequestBuilder postgrestRequestBuilder = new PostgrestRequestBuilder(getPostgrest().getConfig().getPropertyConversionMethod());
        lVar.invoke(postgrestRequestBuilder);
        return RestRequestExecutor.INSTANCE.execute(getPostgrest(), getTable(), new DeleteRequest(postgrestRequestBuilder.getReturning(), postgrestRequestBuilder.getCount(), UtilsKt.mapToFirstValue(postgrestRequestBuilder.getParams()), getSchema(), postgrestRequestBuilder.getHeaders().build()), dVar);
    }

    public static /* synthetic */ Object delete$default(PostgrestQueryBuilder postgrestQueryBuilder, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            lVar = AnonymousClass2.INSTANCE;
        }
        PostgrestRequestBuilder postgrestRequestBuilder = new PostgrestRequestBuilder(postgrestQueryBuilder.getPostgrest().getConfig().getPropertyConversionMethod());
        lVar.invoke(postgrestRequestBuilder);
        return RestRequestExecutor.INSTANCE.execute(postgrestQueryBuilder.getPostgrest(), postgrestQueryBuilder.getTable(), new DeleteRequest(postgrestRequestBuilder.getReturning(), postgrestRequestBuilder.getCount(), UtilsKt.mapToFirstValue(postgrestRequestBuilder.getParams()), postgrestQueryBuilder.getSchema(), postgrestRequestBuilder.getHeaders().build()), dVar);
    }

    public static Object insert$default(PostgrestQueryBuilder postgrestQueryBuilder, List list, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17232.INSTANCE;
        }
        lVar.invoke(new PostgrestRequestBuilder(postgrestQueryBuilder.getPostgrest().getConfig().getPropertyConversionMethod()));
        postgrestQueryBuilder.getPostgrest().getSerializer();
        a aVar = b.f14877d;
        t tVar = t.f19910c;
        p.h();
        throw null;
    }

    /* JADX INFO: renamed from: select-xUsJgKc$$forInline, reason: not valid java name */
    private final Object m6653selectxUsJgKc$$forInline(String str, boolean z, l<? super PostgrestRequestBuilder, t0> lVar, d<? super PostgrestResult> dVar) {
        PostgrestRequestBuilder postgrestRequestBuilder = new PostgrestRequestBuilder(getPostgrest().getConfig().getPropertyConversionMethod());
        lVar.invoke(postgrestRequestBuilder);
        postgrestRequestBuilder.get_params().put("select", Collections.singletonList(str));
        return RestRequestExecutor.INSTANCE.execute(getPostgrest(), getTable(), new SelectRequest(z, postgrestRequestBuilder.getCount(), UtilsKt.mapToFirstValue(postgrestRequestBuilder.getParams()), getSchema(), postgrestRequestBuilder.getHeaders().build()), dVar);
    }

    /* JADX INFO: renamed from: select-xUsJgKc$default, reason: not valid java name */
    public static Object m6654selectxUsJgKc$default(PostgrestQueryBuilder postgrestQueryBuilder, String str, boolean z, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = Columns.INSTANCE.m6648getALLU9NzzuM();
        }
        if ((i10 & 2) != 0) {
            z = false;
        }
        boolean z5 = z;
        if ((i10 & 4) != 0) {
            lVar = PostgrestQueryBuilder$select$2.INSTANCE;
        }
        PostgrestRequestBuilder postgrestRequestBuilder = new PostgrestRequestBuilder(postgrestQueryBuilder.getPostgrest().getConfig().getPropertyConversionMethod());
        lVar.invoke(postgrestRequestBuilder);
        postgrestRequestBuilder.get_params().put("select", Collections.singletonList(str));
        return RestRequestExecutor.INSTANCE.execute(postgrestQueryBuilder.getPostgrest(), postgrestQueryBuilder.getTable(), new SelectRequest(z5, postgrestRequestBuilder.getCount(), UtilsKt.mapToFirstValue(postgrestRequestBuilder.getParams()), postgrestQueryBuilder.getSchema(), postgrestRequestBuilder.getHeaders().build()), dVar);
    }

    private final Object update$$forInline(l<? super PostgrestUpdate, t0> lVar, l<? super PostgrestRequestBuilder, t0> lVar2, d<? super PostgrestResult> dVar) {
        PostgrestRequestBuilder postgrestRequestBuilder = new PostgrestRequestBuilder(getPostgrest().getConfig().getPropertyConversionMethod());
        lVar2.invoke(postgrestRequestBuilder);
        PostgrestUpdate postgrestUpdate = new PostgrestUpdate(getPostgrest().getConfig().getPropertyConversionMethod(), getPostgrest().getSerializer());
        lVar.invoke(postgrestUpdate);
        return RestRequestExecutor.INSTANCE.execute(getPostgrest(), getTable(), new UpdateRequest(postgrestRequestBuilder.getReturning(), postgrestRequestBuilder.getCount(), UtilsKt.mapToFirstValue(postgrestRequestBuilder.getParams()), postgrestUpdate.toJson(), getSchema(), postgrestRequestBuilder.getHeaders().build()), dVar);
    }

    public static /* synthetic */ Object update$default(PostgrestQueryBuilder postgrestQueryBuilder, l lVar, l lVar2, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            lVar = C17242.INSTANCE;
        }
        if ((i10 & 2) != 0) {
            lVar2 = AnonymousClass3.INSTANCE;
        }
        PostgrestRequestBuilder postgrestRequestBuilder = new PostgrestRequestBuilder(postgrestQueryBuilder.getPostgrest().getConfig().getPropertyConversionMethod());
        lVar2.invoke(postgrestRequestBuilder);
        PostgrestUpdate postgrestUpdate = new PostgrestUpdate(postgrestQueryBuilder.getPostgrest().getConfig().getPropertyConversionMethod(), postgrestQueryBuilder.getPostgrest().getSerializer());
        lVar.invoke(postgrestUpdate);
        return RestRequestExecutor.INSTANCE.execute(postgrestQueryBuilder.getPostgrest(), postgrestQueryBuilder.getTable(), new UpdateRequest(postgrestRequestBuilder.getReturning(), postgrestRequestBuilder.getCount(), UtilsKt.mapToFirstValue(postgrestRequestBuilder.getParams()), postgrestUpdate.toJson(), postgrestQueryBuilder.getSchema(), postgrestRequestBuilder.getHeaders().build()), dVar);
    }

    public static Object upsert$default(PostgrestQueryBuilder postgrestQueryBuilder, List list, String str, boolean z, boolean z5, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str = null;
        }
        if ((i10 & 16) != 0) {
            lVar = C17252.INSTANCE;
        }
        PostgrestRequestBuilder postgrestRequestBuilder = new PostgrestRequestBuilder(postgrestQueryBuilder.getPostgrest().getConfig().getPropertyConversionMethod());
        lVar.invoke(postgrestRequestBuilder);
        if (str != null) {
            postgrestRequestBuilder.get_params().put("on_conflict", Collections.singletonList(str));
        }
        postgrestQueryBuilder.getPostgrest().getSerializer();
        a aVar = b.f14877d;
        t tVar = t.f19910c;
        p.h();
        throw null;
    }

    public final Object delete(l<? super PostgrestRequestBuilder, t0> lVar, d<? super PostgrestResult> dVar) {
        PostgrestRequestBuilder postgrestRequestBuilder = new PostgrestRequestBuilder(getPostgrest().getConfig().getPropertyConversionMethod());
        lVar.invoke(postgrestRequestBuilder);
        return RestRequestExecutor.INSTANCE.execute(getPostgrest(), getTable(), new DeleteRequest(postgrestRequestBuilder.getReturning(), postgrestRequestBuilder.getCount(), UtilsKt.mapToFirstValue(postgrestRequestBuilder.getParams()), getSchema(), postgrestRequestBuilder.getHeaders().build()), dVar);
    }

    public final Postgrest getPostgrest() {
        return this.postgrest;
    }

    public final String getSchema() {
        return this.schema;
    }

    public final String getTable() {
        return this.table;
    }

    public final <T> Object insert(T t2, l<? super PostgrestRequestBuilder, t0> lVar, d<? super PostgrestResult> dVar) {
        Collections.singletonList(t2);
        lVar.invoke(new PostgrestRequestBuilder(getPostgrest().getConfig().getPropertyConversionMethod()));
        getPostgrest().getSerializer();
        a aVar = b.f14877d;
        t tVar = t.f19910c;
        p.h();
        throw null;
    }

    /* JADX INFO: renamed from: select-xUsJgKc, reason: not valid java name */
    public final Object m6655selectxUsJgKc(String str, boolean z, l<? super PostgrestRequestBuilder, t0> lVar, d<? super PostgrestResult> dVar) {
        PostgrestRequestBuilder postgrestRequestBuilder = new PostgrestRequestBuilder(getPostgrest().getConfig().getPropertyConversionMethod());
        lVar.invoke(postgrestRequestBuilder);
        postgrestRequestBuilder.get_params().put("select", Collections.singletonList(str));
        return RestRequestExecutor.INSTANCE.execute(getPostgrest(), getTable(), new SelectRequest(z, postgrestRequestBuilder.getCount(), UtilsKt.mapToFirstValue(postgrestRequestBuilder.getParams()), getSchema(), postgrestRequestBuilder.getHeaders().build()), dVar);
    }

    public final Object update(l<? super PostgrestUpdate, t0> lVar, l<? super PostgrestRequestBuilder, t0> lVar2, d<? super PostgrestResult> dVar) {
        PostgrestRequestBuilder postgrestRequestBuilder = new PostgrestRequestBuilder(getPostgrest().getConfig().getPropertyConversionMethod());
        lVar2.invoke(postgrestRequestBuilder);
        PostgrestUpdate postgrestUpdate = new PostgrestUpdate(getPostgrest().getConfig().getPropertyConversionMethod(), getPostgrest().getSerializer());
        lVar.invoke(postgrestUpdate);
        return RestRequestExecutor.INSTANCE.execute(getPostgrest(), getTable(), new UpdateRequest(postgrestRequestBuilder.getReturning(), postgrestRequestBuilder.getCount(), UtilsKt.mapToFirstValue(postgrestRequestBuilder.getParams()), postgrestUpdate.toJson(), getSchema(), postgrestRequestBuilder.getHeaders().build()), dVar);
    }

    public final <T> Object upsert(T t2, String str, boolean z, boolean z5, l<? super PostgrestRequestBuilder, t0> lVar, d<? super PostgrestResult> dVar) {
        Collections.singletonList(t2);
        PostgrestRequestBuilder postgrestRequestBuilder = new PostgrestRequestBuilder(getPostgrest().getConfig().getPropertyConversionMethod());
        lVar.invoke(postgrestRequestBuilder);
        if (str != null) {
            postgrestRequestBuilder.get_params().put("on_conflict", Collections.singletonList(str));
        }
        getPostgrest().getSerializer();
        a aVar = b.f14877d;
        t tVar = t.f19910c;
        p.h();
        throw null;
    }

    public /* synthetic */ PostgrestQueryBuilder(Postgrest postgrest, String str, String str2, int i10, h hVar) {
        this(postgrest, str, (i10 & 4) != 0 ? postgrest.getConfig().getDefaultSchema() : str2);
    }

    public static Object insert$default(PostgrestQueryBuilder postgrestQueryBuilder, Object obj, l lVar, d dVar, int i10, Object obj2) {
        if ((i10 & 2) != 0) {
            lVar = AnonymousClass4.INSTANCE;
        }
        Collections.singletonList(obj);
        lVar.invoke(new PostgrestRequestBuilder(postgrestQueryBuilder.getPostgrest().getConfig().getPropertyConversionMethod()));
        postgrestQueryBuilder.getPostgrest().getSerializer();
        a aVar = b.f14877d;
        t tVar = t.f19910c;
        p.h();
        throw null;
    }

    public final <T> Object insert(List<? extends T> list, l<? super PostgrestRequestBuilder, t0> lVar, d<? super PostgrestResult> dVar) {
        lVar.invoke(new PostgrestRequestBuilder(getPostgrest().getConfig().getPropertyConversionMethod()));
        getPostgrest().getSerializer();
        a aVar = b.f14877d;
        t tVar = t.f19910c;
        p.h();
        throw null;
    }

    public static Object upsert$default(PostgrestQueryBuilder postgrestQueryBuilder, Object obj, String str, boolean z, boolean z5, l lVar, d dVar, int i10, Object obj2) {
        if ((i10 & 2) != 0) {
            str = null;
        }
        if ((i10 & 16) != 0) {
            lVar = C17265.INSTANCE;
        }
        Collections.singletonList(obj);
        PostgrestRequestBuilder postgrestRequestBuilder = new PostgrestRequestBuilder(postgrestQueryBuilder.getPostgrest().getConfig().getPropertyConversionMethod());
        lVar.invoke(postgrestRequestBuilder);
        if (str != null) {
            postgrestRequestBuilder.get_params().put("on_conflict", Collections.singletonList(str));
        }
        postgrestQueryBuilder.getPostgrest().getSerializer();
        a aVar = b.f14877d;
        t tVar = t.f19910c;
        p.h();
        throw null;
    }

    public final <T> Object upsert(List<? extends T> list, String str, boolean z, boolean z5, l<? super PostgrestRequestBuilder, t0> lVar, d<? super PostgrestResult> dVar) {
        PostgrestRequestBuilder postgrestRequestBuilder = new PostgrestRequestBuilder(getPostgrest().getConfig().getPropertyConversionMethod());
        lVar.invoke(postgrestRequestBuilder);
        if (str != null) {
            postgrestRequestBuilder.get_params().put("on_conflict", Collections.singletonList(str));
        }
        getPostgrest().getSerializer();
        a aVar = b.f14877d;
        t tVar = t.f19910c;
        p.h();
        throw null;
    }

    public final <T> Object update(T t2, l<? super PostgrestRequestBuilder, t0> lVar, d<? super PostgrestResult> dVar) {
        PostgrestRequestBuilder postgrestRequestBuilder = new PostgrestRequestBuilder(getPostgrest().getConfig().getPropertyConversionMethod());
        lVar.invoke(postgrestRequestBuilder);
        postgrestRequestBuilder.getReturning();
        postgrestRequestBuilder.getCount();
        UtilsKt.mapToFirstValue(postgrestRequestBuilder.getParams());
        getPostgrest().getSerializer();
        a aVar = b.f14877d;
        p.h();
        throw null;
    }

    public static Object update$default(PostgrestQueryBuilder postgrestQueryBuilder, Object obj, l lVar, d dVar, int i10, Object obj2) {
        if ((i10 & 2) != 0) {
            lVar = AnonymousClass5.INSTANCE;
        }
        PostgrestRequestBuilder postgrestRequestBuilder = new PostgrestRequestBuilder(postgrestQueryBuilder.getPostgrest().getConfig().getPropertyConversionMethod());
        lVar.invoke(postgrestRequestBuilder);
        postgrestRequestBuilder.getReturning();
        postgrestRequestBuilder.getCount();
        UtilsKt.mapToFirstValue(postgrestRequestBuilder.getParams());
        postgrestQueryBuilder.getPostgrest().getSerializer();
        a aVar = b.f14877d;
        p.h();
        throw null;
    }
}
