package io.github.jan.supabase.gotrue;

import db.c0;
import f4.f;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Ldb/c0;", "Lx6/t0;", "invoke", "(Ldb/c0;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
public final class AuthImpl$resendPhone$2$1$1 extends r implements l<c0, t0> {
    final /* synthetic */ String $captchaToken;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AuthImpl$resendPhone$2$1$1(String str) {
        super(1);
        this.$captchaToken = str;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((c0) obj);
        return t0.f22605a;
    }

    public final void invoke(c0 c0Var) {
        f.r(c0Var, "captcha_token", this.$captchaToken);
    }
}
