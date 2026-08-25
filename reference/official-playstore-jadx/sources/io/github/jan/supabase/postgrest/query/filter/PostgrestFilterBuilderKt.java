package io.github.jan.supabase.postgrest.query.filter;

import androidx.compose.material3.d;
import kotlin.Metadata;
import kotlin.collections.h0;
import kotlin.collections.x;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a0\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0002\b\u0003H\u0081\bø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\b"}, d2 = {"Lio/github/jan/supabase/postgrest/query/filter/PostgrestFilterBuilder;", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "filter", "", "formatJoiningFilter", "(Lio/github/jan/supabase/postgrest/query/filter/PostgrestFilterBuilder;Lr7/l;)Ljava/lang/String;", "postgrest-kt_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class PostgrestFilterBuilderKt {
    public static final String formatJoiningFilter(PostgrestFilterBuilder postgrestFilterBuilder, l<? super PostgrestFilterBuilder, t0> lVar) {
        PostgrestFilterBuilder postgrestFilterBuilder2 = new PostgrestFilterBuilder(postgrestFilterBuilder.getPropertyConversionMethod(), null, 2, null);
        lVar.invoke(postgrestFilterBuilder2);
        return d.i(')', "(", x.u0(h0.z0(postgrestFilterBuilder2.getParams()), ",", null, null, PostgrestFilterBuilderKt$formatJoiningFilter$formattedFilter$1.INSTANCE, 30));
    }
}
