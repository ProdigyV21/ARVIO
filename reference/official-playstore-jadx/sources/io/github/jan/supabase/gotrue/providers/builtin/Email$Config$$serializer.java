package io.github.jan.supabase.gotrue.providers.builtin;

import a.a;
import ab.g;
import bb.b;
import bb.c;
import bb.d;
import cb.b1;
import cb.d1;
import cb.e0;
import cb.r1;
import db.b0;
import io.github.jan.supabase.gotrue.providers.builtin.Email;
import kotlin.Metadata;
import kotlinx.serialization.UnknownFieldException;
import x6.e;
import ya.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\u0007\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\u000b\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ \u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0016\u001a\u00020\u00138VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"io/github/jan/supabase/gotrue/providers/builtin/Email.Config.$serializer", "Lcb/e0;", "Lio/github/jan/supabase/gotrue/providers/builtin/Email$Config;", "<init>", "()V", "", "Lya/h;", "childSerializers", "()[Lya/h;", "Lbb/d;", "decoder", "deserialize", "(Lbb/d;)Lio/github/jan/supabase/gotrue/providers/builtin/Email$Config;", "Lbb/e;", "encoder", "value", "Lx6/t0;", "serialize", "(Lbb/e;Lio/github/jan/supabase/gotrue/providers/builtin/Email$Config;)V", "Lab/g;", "getDescriptor", "()Lab/g;", "descriptor", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@e
public final class Email$Config$$serializer implements e0<Email.Config> {
    public static final Email$Config$$serializer INSTANCE;
    private static final /* synthetic */ d1 descriptor;

    static {
        Email$Config$$serializer email$Config$$serializer = new Email$Config$$serializer();
        INSTANCE = email$Config$$serializer;
        d1 d1Var = new d1("io.github.jan.supabase.gotrue.providers.builtin.Email.Config", email$Config$$serializer, 4);
        d1Var.j("gotrue_meta_security", true);
        d1Var.j("data", true);
        d1Var.j("email", true);
        d1Var.j("password", true);
        descriptor = d1Var;
    }

    private Email$Config$$serializer() {
    }

    @Override // cb.e0
    public h<?>[] childSerializers() {
        h<?> hVarQ = a.Q(CaptchaTokenSerializer.INSTANCE);
        h<?> hVarQ2 = a.Q(db.e0.f14890a);
        r1 r1Var = r1.f7553a;
        return new h[]{hVarQ, hVarQ2, r1Var, r1Var};
    }

    @Override // ya.c
    public Email.Config deserialize(d decoder) {
        g descriptor2 = getDescriptor();
        b bVarB = decoder.b(descriptor2);
        int i10 = 0;
        String str = null;
        b0 b0Var = null;
        String strF = null;
        String strF2 = null;
        boolean z = true;
        while (z) {
            int iW = bVarB.w(descriptor2);
            if (iW == -1) {
                z = false;
            } else if (iW == 0) {
                str = (String) bVarB.j(descriptor2, 0, CaptchaTokenSerializer.INSTANCE, str);
                i10 |= 1;
            } else if (iW == 1) {
                b0Var = (b0) bVarB.j(descriptor2, 1, db.e0.f14890a, b0Var);
                i10 |= 2;
            } else if (iW == 2) {
                strF = bVarB.f(descriptor2, 2);
                i10 |= 4;
            } else {
                if (iW != 3) {
                    throw new UnknownFieldException(iW);
                }
                strF2 = bVarB.f(descriptor2, 3);
                i10 |= 8;
            }
        }
        bVarB.u(descriptor2);
        return new Email.Config(i10, str, b0Var, strF, strF2, null);
    }

    @Override // ya.s, ya.c
    public g getDescriptor() {
        return descriptor;
    }

    @Override // ya.s
    public void serialize(bb.e encoder, Email.Config value) {
        g descriptor2 = getDescriptor();
        c cVarB = encoder.b(descriptor2);
        Email.Config.write$Self$gotrue_kt_release(value, cVarB, descriptor2);
        cVarB.e();
    }

    @Override // cb.e0
    public h<?>[] typeParametersSerializers() {
        return b1.f7464b;
    }
}
