package io.github.jan.supabase.gotrue.admin;

import ab.a;
import cb.g;
import cb.h1;
import cb.r1;
import db.b0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lab/a;", "Lx6/t0;", "invoke", "(Lab/a;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
public final class AdminUserBuilder$Companion$descriptor$1 extends r implements l<a, t0> {
    public static final AdminUserBuilder$Companion$descriptor$1 INSTANCE = new AdminUserBuilder$Companion$descriptor$1();

    public AdminUserBuilder$Companion$descriptor$1() {
        super(1);
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((a) obj);
        return t0.f22605a;
    }

    public final void invoke(a aVar) {
        h1 h1Var = r1.f7554b;
        aVar.a("password", h1Var, (12 & 8) == 0);
        aVar.a("email", h1Var, (12 & 8) == 0);
        h1 h1Var2 = g.f7500b;
        aVar.a("email_confirm", h1Var2, (12 & 8) == 0);
        aVar.a("phone", h1Var, (12 & 8) == 0);
        aVar.a("phone_confirm", h1Var2, (12 & 8) == 0);
        aVar.a("user_metadata", b0.Companion.serializer().getDescriptor(), (12 & 8) == 0);
    }
}
