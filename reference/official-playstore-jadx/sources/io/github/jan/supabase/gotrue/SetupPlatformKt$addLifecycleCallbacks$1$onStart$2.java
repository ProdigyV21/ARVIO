package io.github.jan.supabase.gotrue;

import d7.d;
import f7.e;
import f7.j;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import q2.f;
import r7.a;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 9, 0})
@e(c = "io.github.jan.supabase.gotrue.SetupPlatformKt$addLifecycleCallbacks$1$onStart$2", f = "setupPlatform.kt", l = {43}, m = "invokeSuspend")
public final class SetupPlatformKt$addLifecycleCallbacks$1$onStart$2 extends j implements p<k0, d<? super t0>, Object> {
    final /* synthetic */ Auth $gotrue;
    int label;

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.SetupPlatformKt$addLifecycleCallbacks$1$onStart$2$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements a<String> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(0);
        }

        @Override // r7.a
        public final String invoke() {
            return "No session found for auto refresh";
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SetupPlatformKt$addLifecycleCallbacks$1$onStart$2(Auth auth, d<? super SetupPlatformKt$addLifecycleCallbacks$1$onStart$2> dVar) {
        super(2, dVar);
        this.$gotrue = auth;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new SetupPlatformKt$addLifecycleCallbacks$1$onStart$2(this.$gotrue, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        try {
            if (i10 == 0) {
                c.G(obj);
                Auth auth = this.$gotrue;
                this.label = 1;
                Object objStartAutoRefreshForCurrentSession = auth.startAutoRefreshForCurrentSession(this);
                e7.a aVar = e7.a.f15033i;
                if (objStartAutoRefreshForCurrentSession == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
        } catch (IllegalStateException unused) {
            f.A0(f.f21327l, "Auth", AnonymousClass1.INSTANCE);
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super t0> dVar) {
        return ((SetupPlatformKt$addLifecycleCallbacks$1$onStart$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
