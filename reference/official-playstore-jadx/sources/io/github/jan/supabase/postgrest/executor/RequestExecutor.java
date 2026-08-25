package io.github.jan.supabase.postgrest.executor;

import d7.d;
import io.github.jan.supabase.postgrest.Postgrest;
import io.github.jan.supabase.postgrest.request.PostgrestRequest;
import io.github.jan.supabase.postgrest.result.PostgrestResult;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001J(\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H¦@¢\u0006\u0004\b\t\u0010\n\u0082\u0001\u0001\u000b¨\u0006\f"}, d2 = {"Lio/github/jan/supabase/postgrest/executor/RequestExecutor;", "", "Lio/github/jan/supabase/postgrest/Postgrest;", "postgrest", "", "path", "Lio/github/jan/supabase/postgrest/request/PostgrestRequest;", "request", "Lio/github/jan/supabase/postgrest/result/PostgrestResult;", "execute", "(Lio/github/jan/supabase/postgrest/Postgrest;Ljava/lang/String;Lio/github/jan/supabase/postgrest/request/PostgrestRequest;Ld7/d;)Ljava/lang/Object;", "Lio/github/jan/supabase/postgrest/executor/RestRequestExecutor;", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface RequestExecutor {
    Object execute(Postgrest postgrest, String str, PostgrestRequest postgrestRequest, d<? super PostgrestResult> dVar);
}
