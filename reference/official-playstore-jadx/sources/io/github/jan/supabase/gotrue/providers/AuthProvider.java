package io.github.jan.supabase.gotrue.providers;

import d7.d;
import io.github.jan.supabase.SupabaseClient;
import io.github.jan.supabase.gotrue.user.UserSession;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003Je\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\"\u0010\n\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00062\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\u001b\b\u0002\u0010\u000f\u001a\u0015\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\t\u0018\u00010\r¢\u0006\u0002\b\u000eH¦@¢\u0006\u0004\b\u0010\u0010\u0011Jg\u0010\u0012\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0005\u001a\u00020\u00042\"\u0010\n\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00062\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\u001b\b\u0002\u0010\u000f\u001a\u0015\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\t\u0018\u00010\r¢\u0006\u0002\b\u000eH¦@¢\u0006\u0004\b\u0012\u0010\u0011¨\u0006\u0013"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/AuthProvider;", "C", "R", "", "Lio/github/jan/supabase/SupabaseClient;", "supabaseClient", "Lkotlin/Function2;", "Lio/github/jan/supabase/gotrue/user/UserSession;", "Ld7/d;", "Lx6/t0;", "onSuccess", "", "redirectUrl", "Lkotlin/Function1;", "Lx6/n;", "config", "login", "(Lio/github/jan/supabase/SupabaseClient;Lr7/p;Ljava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "signUp", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface AuthProvider<C, R> {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Object login$default(AuthProvider authProvider, SupabaseClient supabaseClient, p pVar, String str, l lVar, d dVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: login");
            }
            if ((i10 & 4) != 0) {
                str = null;
            }
            if ((i10 & 8) != 0) {
                lVar = null;
            }
            return authProvider.login(supabaseClient, pVar, str, lVar, dVar);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Object signUp$default(AuthProvider authProvider, SupabaseClient supabaseClient, p pVar, String str, l lVar, d dVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: signUp");
            }
            if ((i10 & 4) != 0) {
                str = null;
            }
            if ((i10 & 8) != 0) {
                lVar = null;
            }
            return authProvider.signUp(supabaseClient, pVar, str, lVar, dVar);
        }
    }

    Object login(SupabaseClient supabaseClient, p<? super UserSession, ? super d<? super t0>, ? extends Object> pVar, String str, l<? super C, t0> lVar, d<? super t0> dVar);

    Object signUp(SupabaseClient supabaseClient, p<? super UserSession, ? super d<? super t0>, ? extends Object> pVar, String str, l<? super C, t0> lVar, d<? super R> dVar);
}
