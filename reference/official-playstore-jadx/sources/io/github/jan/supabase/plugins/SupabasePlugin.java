package io.github.jan.supabase.plugins;

import d7.d;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u0002H\u0096@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lio/github/jan/supabase/plugins/SupabasePlugin;", "", "Lx6/t0;", "close", "(Ld7/d;)Ljava/lang/Object;", "supabase-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface SupabasePlugin {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static Object close(SupabasePlugin supabasePlugin, d<? super t0> dVar) {
            return t0.f22605a;
        }
    }

    Object close(d<? super t0> dVar);
}
