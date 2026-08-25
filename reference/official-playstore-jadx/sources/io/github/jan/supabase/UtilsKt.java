package io.github.jan.supabase;

import d7.d;
import db.b;
import db.c0;
import db.m;
import io.github.jan.supabase.annotations.SupabaseInternal;
import io.ktor.client.statement.HttpResponse;
import io.ktor.http.URLBuilder;
import io.ktor.http.URLUtilsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.reflect.b0;
import kotlinx.serialization.MissingFieldException;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a(\u0010\u0004\u001a\u00028\u0000\"\u0006\b\u0000\u0010\u0000\u0018\u0001*\u00020\u00012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0087H¢\u0006\u0004\b\u0004\u0010\u0005\u001a4\u0010\f\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00022\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\nH\u0087\bø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a\u0013\u0010\u000f\u001a\u00020\u000e*\u00020\u0002H\u0007¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u001b\u0010\u0013\u001a\u00020\t*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u0013\u0010\u0014\u001a$\u0010\u0016\u001a\u00028\u0000\"\u0006\b\u0000\u0010\u0000\u0018\u0001*\u00020\u000e2\u0006\u0010\u0015\u001a\u00028\u0000H\u0087\b¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u001e\u0010\u0018\u001a\u0004\u0018\u00018\u0000\"\u0006\b\u0000\u0010\u0000\u0018\u0001*\u00020\u0001H\u0087H¢\u0006\u0004\b\u0018\u0010\u0019\" \u0010\u001b\u001a\u00020\u001a8\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u0012\u0004\b\u001f\u0010 \u001a\u0004\b\u001d\u0010\u001e\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006!"}, d2 = {"T", "Lio/ktor/client/statement/HttpResponse;", "", "context", "safeBody", "(Lio/ktor/client/statement/HttpResponse;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "baseUrl", "Lkotlin/Function1;", "Lio/ktor/http/URLBuilder;", "Lx6/t0;", "Lx6/n;", "init", "buildUrl", "(Ljava/lang/String;Lr7/l;)Ljava/lang/String;", "Ldb/b0;", "toJsonObject", "(Ljava/lang/String;)Ldb/b0;", "Ldb/c0;", "jsonObject", "putJsonObject", "(Ldb/c0;Ldb/b0;)V", "default", "decodeIfNotEmptyOrDefault", "(Ldb/b0;Ljava/lang/Object;)Ljava/lang/Object;", "bodyOrNull", "(Lio/ktor/client/statement/HttpResponse;Ld7/d;)Ljava/lang/Object;", "Ldb/b;", "supabaseJson", "Ldb/b;", "getSupabaseJson", "()Ldb/b;", "getSupabaseJson$annotations", "()V", "supabase-kt_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class UtilsKt {
    private static final b supabaseJson = b0.a(UtilsKt$supabaseJson$1.INSTANCE);

    @SupabaseInternal
    public static final <T> Object bodyOrNull(HttpResponse httpResponse, d<? super T> dVar) {
        try {
            fb.b bVar = getSupabaseJson().f14879b;
            p.h();
            throw null;
        } catch (Exception unused) {
            return null;
        }
    }

    @SupabaseInternal
    public static final String buildUrl(String str, l<? super URLBuilder, t0> lVar) {
        URLBuilder URLBuilder = URLUtilsKt.URLBuilder(str);
        lVar.invoke(URLBuilder);
        return URLBuilder.buildString();
    }

    @SupabaseInternal
    public static final <T> T decodeIfNotEmptyOrDefault(db.b0 b0Var, T t2) {
        if (b0Var.f14881i.isEmpty()) {
            return t2;
        }
        fb.b bVar = getSupabaseJson().f14879b;
        p.h();
        throw null;
    }

    public static final b getSupabaseJson() {
        return supabaseJson;
    }

    @SupabaseInternal
    public static /* synthetic */ void getSupabaseJson$annotations() {
    }

    @SupabaseInternal
    public static final void putJsonObject(c0 c0Var, db.b0 b0Var) {
        for (String str : b0Var.f14881i.keySet()) {
            c0Var.b((m) b0Var.get(str), str);
        }
    }

    @SupabaseInternal
    public static final <T> Object safeBody(HttpResponse httpResponse, String str, d<? super T> dVar) {
        if (str != null) {
            " in ".concat(str);
        }
        try {
            fb.b bVar = getSupabaseJson().f14879b;
            p.h();
            throw null;
        } catch (MissingFieldException unused) {
            p.h();
            throw null;
        }
    }

    public static Object safeBody$default(HttpResponse httpResponse, String str, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        if (str != null) {
            " in ".concat(str);
        }
        try {
            fb.b bVar = getSupabaseJson().f14879b;
            p.h();
            throw null;
        } catch (MissingFieldException unused) {
            p.h();
            throw null;
        }
    }

    @SupabaseInternal
    public static final db.b0 toJsonObject(String str) {
        b bVar = supabaseJson;
        bVar.getClass();
        return (db.b0) bVar.c(str, db.b0.Companion.serializer());
    }
}
