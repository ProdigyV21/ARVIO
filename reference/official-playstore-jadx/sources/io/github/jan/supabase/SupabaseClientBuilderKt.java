package io.github.jan.supabase;

import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a<\u0010\t\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u000b"}, d2 = {"", "supabaseUrl", "supabaseKey", "Lkotlin/Function1;", "Lio/github/jan/supabase/SupabaseClientBuilder;", "Lx6/t0;", "Lx6/n;", "builder", "Lio/github/jan/supabase/SupabaseClient;", "createSupabaseClient", "(Ljava/lang/String;Ljava/lang/String;Lr7/l;)Lio/github/jan/supabase/SupabaseClient;", "supabase-kt_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class SupabaseClientBuilderKt {
    public static final SupabaseClient createSupabaseClient(String str, String str2, l<? super SupabaseClientBuilder, t0> lVar) {
        SupabaseClientBuilder supabaseClientBuilder = new SupabaseClientBuilder(str, str2);
        lVar.invoke(supabaseClientBuilder);
        return supabaseClientBuilder.build();
    }
}
