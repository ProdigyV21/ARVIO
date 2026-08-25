package io.github.jan.supabase.postgrest.query;

import io.github.jan.supabase.annotations.SupabaseInternal;
import io.github.jan.supabase.postgrest.PropertyConversionMethod;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a6\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\u0001\u001a\u00020\u00002\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0002\b\u0005H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, d2 = {"Lio/github/jan/supabase/postgrest/PropertyConversionMethod;", "propertyConversionMethod", "Lkotlin/Function1;", "Lio/github/jan/supabase/postgrest/query/PostgrestRequestBuilder;", "Lx6/t0;", "Lx6/n;", "block", "postgrestRequest", "(Lio/github/jan/supabase/postgrest/PropertyConversionMethod;Lr7/l;)Lio/github/jan/supabase/postgrest/query/PostgrestRequestBuilder;", "postgrest-kt_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class PostgrestRequestBuilderKt {
    @SupabaseInternal
    public static final PostgrestRequestBuilder postgrestRequest(PropertyConversionMethod propertyConversionMethod, l<? super PostgrestRequestBuilder, t0> lVar) {
        PostgrestRequestBuilder postgrestRequestBuilder = new PostgrestRequestBuilder(propertyConversionMethod);
        lVar.invoke(postgrestRequestBuilder);
        return postgrestRequestBuilder;
    }

    public static /* synthetic */ PostgrestRequestBuilder postgrestRequest$default(PropertyConversionMethod propertyConversionMethod, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            propertyConversionMethod = PropertyConversionMethod.INSTANCE.getCAMEL_CASE_TO_SNAKE_CASE();
        }
        PostgrestRequestBuilder postgrestRequestBuilder = new PostgrestRequestBuilder(propertyConversionMethod);
        lVar.invoke(postgrestRequestBuilder);
        return postgrestRequestBuilder;
    }
}
