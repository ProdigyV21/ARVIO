package com.arflix.tv;

import androidx.navigation.c1;
import androidx.navigation.l0;
import androidx.navigation.o0;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.navigation.Screen;
import java.net.URLEncoder;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.MainActivityKt$ArflixApp$10$1", f = "MainActivity.kt", l = {}, m = "invokeSuspend", v = 2)
public final class MainActivityKt$ArflixApp$10$1 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ Profile $activeProfile;
    final /* synthetic */ l0 $navController;
    final /* synthetic */ r7.a<t0> $onConsumeInstallPackUrl;
    final /* synthetic */ String $pendingInstallPackUrl;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainActivityKt$ArflixApp$10$1(String str, Profile profile, l0 l0Var, r7.a<t0> aVar, d7.d<? super MainActivityKt$ArflixApp$10$1> dVar) {
        super(2, dVar);
        this.$pendingInstallPackUrl = str;
        this.$activeProfile = profile;
        this.$navController = l0Var;
        this.$onConsumeInstallPackUrl = aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 invokeSuspend$lambda$0(o0 o0Var) {
        o0Var.b(Screen.ProfileSelection.INSTANCE.getRoute());
        o0Var.a(-1);
        c1 c1Var = new c1();
        invokeSuspend$lambda$0$0(c1Var);
        o0Var.f4746f = c1Var.f4536a;
        o0Var.f4747g = c1Var.f4537b;
        o0Var.f4742b = true;
        return t0.f22605a;
    }

    private static final t0 invokeSuspend$lambda$0$0(c1 c1Var) {
        c1Var.f4536a = true;
        return t0.f22605a;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new MainActivityKt$ArflixApp$10$1(this.$pendingInstallPackUrl, this.$activeProfile, this.$navController, this.$onConsumeInstallPackUrl, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        String str = this.$pendingInstallPackUrl;
        t0 t0Var = t0.f22605a;
        if (str == null || this.$activeProfile == null) {
            return t0Var;
        }
        this.$navController.i(androidx.compose.material3.d.C("settings?initialSection=catalogs&installPackUrl=", URLEncoder.encode(str, "UTF-8")), new o(0));
        this.$onConsumeInstallPackUrl.invoke();
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((MainActivityKt$ArflixApp$10$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
