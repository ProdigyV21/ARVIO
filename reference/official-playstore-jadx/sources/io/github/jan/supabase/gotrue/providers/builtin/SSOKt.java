package io.github.jan.supabase.gotrue.providers.builtin;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.github.jan.supabase.SupabaseClient;
import io.github.jan.supabase.gotrue.Auth;
import io.github.jan.supabase.gotrue.AuthKt;
import io.github.jan.supabase.gotrue.Utils_androidKt;
import io.github.jan.supabase.gotrue.providers.builtin.SSO;
import io.github.jan.supabase.gotrue.user.UserSession;
import k2.c;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aa\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u00002\"\u0010\u0007\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00022\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0019\u0010\r\u001a\u0015\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0005\u0018\u00010\n¢\u0006\u0002\b\fH\u0080@¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lio/github/jan/supabase/SupabaseClient;", "supabaseClient", "Lkotlin/Function2;", "Lio/github/jan/supabase/gotrue/user/UserSession;", "Ld7/d;", "Lx6/t0;", "", "onSuccess", "", "redirectUrl", "Lkotlin/Function1;", "Lio/github/jan/supabase/gotrue/providers/builtin/SSO$Config;", "Lx6/n;", "config", "loginWithSSO", "(Lio/github/jan/supabase/SupabaseClient;Lr7/p;Ljava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "gotrue-kt_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class SSOKt {

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.providers.builtin.SSOKt$loginWithSSO$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u008a@"}, d2 = {"<anonymous>", "", "it"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.providers.builtin.SSOKt$loginWithSSO$2", f = "SSO.kt", l = {72}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<String, d<? super String>, Object> {
        final /* synthetic */ l<SSO.Config, t0> $config;
        final /* synthetic */ SupabaseClient $supabaseClient;
        /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.providers.builtin.SSOKt$loginWithSSO$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/builtin/SSO$Config;", "Lx6/t0;", "invoke", "(Lio/github/jan/supabase/gotrue/providers/builtin/SSO$Config;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
        public static final class AnonymousClass1 extends r implements l<SSO.Config, t0> {
            final /* synthetic */ l<SSO.Config, t0> $config;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass1(l<? super SSO.Config, t0> lVar) {
                super(1);
                this.$config = lVar;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((SSO.Config) obj);
                return t0.f22605a;
            }

            public final void invoke(SSO.Config config) {
                l<SSO.Config, t0> lVar = this.$config;
                if (lVar != null) {
                    lVar.invoke(config);
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(SupabaseClient supabaseClient, l<? super SSO.Config, t0> lVar, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$supabaseClient = supabaseClient;
            this.$config = lVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$supabaseClient, this.$config, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                String str = (String) this.L$0;
                Auth auth = AuthKt.getAuth(this.$supabaseClient);
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$config);
                this.label = 1;
                obj = auth.retrieveSSOUrl(str, anonymousClass1, this);
                a aVar = a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return ((SSO.Result) obj).getUrl();
        }

        @Override // r7.p
        public final Object invoke(String str, d<? super String> dVar) {
            return ((AnonymousClass2) create(str, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public static final Object loginWithSSO(SupabaseClient supabaseClient, p<? super UserSession, ? super d<? super t0>, ? extends Object> pVar, String str, l<? super SSO.Config, t0> lVar, d<? super t0> dVar) {
        Object objStartExternalAuth = Utils_androidKt.startExternalAuth(AuthKt.getAuth(supabaseClient), str, new AnonymousClass2(supabaseClient, lVar, null), pVar, dVar);
        return objStartExternalAuth == a.f15033i ? objStartExternalAuth : t0.f22605a;
    }
}
