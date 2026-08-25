package io.github.jan.supabase.gotrue;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import fi.iki.elonen.f;
import io.github.jan.supabase.SupabaseClient;
import io.github.jan.supabase.gotrue.Auth;
import io.github.jan.supabase.gotrue.user.UserSession;
import k2.c;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001f\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a/\u0010\r\u001a\u00020\u0004*\u00020\u00072\u0006\u0010\t\u001a\u00020\b2\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00040\n¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroid/net/Uri;", "uri", "Lio/github/jan/supabase/gotrue/ExternalAuthAction;", "action", "Lx6/t0;", "openUrl", "(Landroid/net/Uri;Lio/github/jan/supabase/gotrue/ExternalAuthAction;)V", "Lio/github/jan/supabase/SupabaseClient;", "Landroid/content/Intent;", "intent", "Lkotlin/Function1;", "Lio/github/jan/supabase/gotrue/user/UserSession;", "onSessionSuccess", "handleDeeplinks", "(Lio/github/jan/supabase/SupabaseClient;Landroid/content/Intent;Lr7/l;)V", "gotrue-kt_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class AndroidKt {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[ExternalAuthAction.values().length];
            try {
                iArr[ExternalAuthAction.EXTERNAL_BROWSER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ExternalAuthAction.CUSTOM_TABS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[FlowType.values().length];
            try {
                iArr2[FlowType.IMPLICIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[FlowType.PKCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AndroidKt$handleDeeplinks$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lio/github/jan/supabase/gotrue/user/UserSession;", "it", "Lx6/t0;", "invoke", "(Lio/github/jan/supabase/gotrue/user/UserSession;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class AnonymousClass1 extends r implements l<UserSession, t0> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        public final void invoke(UserSession userSession) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((UserSession) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AndroidKt$handleDeeplinks$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 9, 0})
    @e(c = "io.github.jan.supabase.gotrue.AndroidKt$handleDeeplinks$2", f = "Android.kt", l = {49}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ String $code;
        final /* synthetic */ l<UserSession, t0> $onSessionSuccess;
        final /* synthetic */ SupabaseClient $this_handleDeeplinks;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(SupabaseClient supabaseClient, String str, l<? super UserSession, t0> lVar, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$this_handleDeeplinks = supabaseClient;
            this.$code = str;
            this.$onSessionSuccess = lVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass2(this.$this_handleDeeplinks, this.$code, this.$onSessionSuccess, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            AnonymousClass2 anonymousClass2;
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                Auth auth = AuthKt.getAuth(this.$this_handleDeeplinks);
                String str = this.$code;
                this.label = 1;
                anonymousClass2 = this;
                Object objExchangeCodeForSession$default = Auth.DefaultImpls.exchangeCodeForSession$default(auth, str, false, anonymousClass2, 2, null);
                a aVar = a.f15033i;
                if (objExchangeCodeForSession$default == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
                anonymousClass2 = this;
            }
            l<UserSession, t0> lVar = anonymousClass2.$onSessionSuccess;
            UserSession userSessionCurrentSessionOrNull = AuthKt.getAuth(anonymousClass2.$this_handleDeeplinks).currentSessionOrNull();
            if (userSessionCurrentSessionOrNull == null) {
                throw new IllegalStateException("No session available");
            }
            lVar.invoke(userSessionCurrentSessionOrNull);
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public static final void handleDeeplinks(SupabaseClient supabaseClient, Intent intent, l<? super UserSession, t0> lVar) {
        String scheme;
        String host;
        String queryParameter;
        Uri data = intent.getData();
        if (data == null || (scheme = data.getScheme()) == null || (host = data.getHost()) == null || !scheme.equals(AuthKt.getAuth(supabaseClient).getConfig().getScheme()) || !host.equals(AuthKt.getAuth(supabaseClient).getConfig().getHost())) {
            return;
        }
        int i10 = WhenMappings.$EnumSwitchMapping$1[AuthKt.getAuth(supabaseClient).getConfig().getFlowType().ordinal()];
        if (i10 != 1) {
            if (i10 == 2 && (queryParameter = data.getQueryParameter("code")) != null) {
                m0.p(((AuthImpl) AuthKt.getAuth(supabaseClient)).getAuthScope(), null, 0, new AnonymousClass2(supabaseClient, queryParameter, lVar, null), 3);
                return;
            }
            return;
        }
        String fragment = data.getFragment();
        if (fragment == null) {
            return;
        }
        UtilsKt.parseFragmentAndImportSession(AuthKt.getAuth(supabaseClient), fragment, lVar);
    }

    public static /* synthetic */ void handleDeeplinks$default(SupabaseClient supabaseClient, Intent intent, l lVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = AnonymousClass1.INSTANCE;
        }
        handleDeeplinks(supabaseClient, intent, lVar);
    }

    public static final void openUrl(Uri uri, ExternalAuthAction externalAuthAction) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[externalAuthAction.ordinal()];
        if (i10 == 1) {
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            intent.setFlags(268435456);
            SetupPlatformKt.applicationContext().startActivity(intent);
        } else {
            if (i10 != 2) {
                return;
            }
            f fVarA = new l6.l().a();
            Intent intent2 = (Intent) fVarA.f15416i;
            intent2.setFlags(268435456);
            Context contextApplicationContext = SetupPlatformKt.applicationContext();
            intent2.setData(uri);
            contextApplicationContext.startActivity(intent2, (Bundle) fVarA.f15417l);
        }
    }
}
