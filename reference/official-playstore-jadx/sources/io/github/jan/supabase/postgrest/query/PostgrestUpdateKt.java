package io.github.jan.supabase.postgrest.query;

import db.b0;
import io.github.jan.supabase.SupabaseSerializer;
import io.github.jan.supabase.annotations.SupabaseInternal;
import io.github.jan.supabase.postgrest.PropertyConversionMethod;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a>\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0087\bø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\f"}, d2 = {"Lio/github/jan/supabase/postgrest/PropertyConversionMethod;", "propertyConversionMethod", "Lio/github/jan/supabase/SupabaseSerializer;", "serializer", "Lkotlin/Function1;", "Lio/github/jan/supabase/postgrest/query/PostgrestUpdate;", "Lx6/t0;", "Lx6/n;", "block", "Ldb/b0;", "buildPostgrestUpdate", "(Lio/github/jan/supabase/postgrest/PropertyConversionMethod;Lio/github/jan/supabase/SupabaseSerializer;Lr7/l;)Ldb/b0;", "postgrest-kt_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class PostgrestUpdateKt {
    @SupabaseInternal
    public static final b0 buildPostgrestUpdate(PropertyConversionMethod propertyConversionMethod, SupabaseSerializer supabaseSerializer, l<? super PostgrestUpdate, t0> lVar) {
        PostgrestUpdate postgrestUpdate = new PostgrestUpdate(propertyConversionMethod, supabaseSerializer);
        lVar.invoke(postgrestUpdate);
        return postgrestUpdate.toJson();
    }

    public static /* synthetic */ b0 buildPostgrestUpdate$default(PropertyConversionMethod propertyConversionMethod, SupabaseSerializer supabaseSerializer, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            propertyConversionMethod = PropertyConversionMethod.INSTANCE.getSERIAL_NAME();
        }
        PostgrestUpdate postgrestUpdate = new PostgrestUpdate(propertyConversionMethod, supabaseSerializer);
        lVar.invoke(postgrestUpdate);
        return postgrestUpdate.toJson();
    }
}
