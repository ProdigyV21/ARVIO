package io.github.jan.supabase.gotrue.mfa;

import ab.g;
import bb.b;
import bb.c;
import bb.d;
import cb.b1;
import cb.d1;
import cb.e0;
import cb.r1;
import io.github.jan.supabase.gotrue.mfa.FactorType;
import kotlin.Metadata;
import kotlinx.serialization.UnknownFieldException;
import x6.e;
import ya.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\u0007\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\u000b\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ \u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0016\u001a\u00020\u00138VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"io/github/jan/supabase/gotrue/mfa/FactorType.TOTP.Response.$serializer", "Lcb/e0;", "Lio/github/jan/supabase/gotrue/mfa/FactorType$TOTP$Response;", "<init>", "()V", "", "Lya/h;", "childSerializers", "()[Lya/h;", "Lbb/d;", "decoder", "deserialize", "(Lbb/d;)Lio/github/jan/supabase/gotrue/mfa/FactorType$TOTP$Response;", "Lbb/e;", "encoder", "value", "Lx6/t0;", "serialize", "(Lbb/e;Lio/github/jan/supabase/gotrue/mfa/FactorType$TOTP$Response;)V", "Lab/g;", "getDescriptor", "()Lab/g;", "descriptor", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@e
public final class FactorType$TOTP$Response$$serializer implements e0<FactorType.TOTP.Response> {
    public static final FactorType$TOTP$Response$$serializer INSTANCE;
    private static final /* synthetic */ d1 descriptor;

    static {
        FactorType$TOTP$Response$$serializer factorType$TOTP$Response$$serializer = new FactorType$TOTP$Response$$serializer();
        INSTANCE = factorType$TOTP$Response$$serializer;
        d1 d1Var = new d1("io.github.jan.supabase.gotrue.mfa.FactorType.TOTP.Response", factorType$TOTP$Response$$serializer, 3);
        d1Var.j("secret", false);
        d1Var.j("qr_code", false);
        d1Var.j("uri", false);
        descriptor = d1Var;
    }

    private FactorType$TOTP$Response$$serializer() {
    }

    @Override // cb.e0
    public h<?>[] childSerializers() {
        r1 r1Var = r1.f7553a;
        return new h[]{r1Var, r1Var, r1Var};
    }

    @Override // ya.c
    public FactorType.TOTP.Response deserialize(d decoder) {
        g descriptor2 = getDescriptor();
        b bVarB = decoder.b(descriptor2);
        int i10 = 0;
        String strF = null;
        String strF2 = null;
        String strF3 = null;
        boolean z = true;
        while (z) {
            int iW = bVarB.w(descriptor2);
            if (iW == -1) {
                z = false;
            } else if (iW == 0) {
                strF = bVarB.f(descriptor2, 0);
                i10 |= 1;
            } else if (iW == 1) {
                strF2 = bVarB.f(descriptor2, 1);
                i10 |= 2;
            } else {
                if (iW != 2) {
                    throw new UnknownFieldException(iW);
                }
                strF3 = bVarB.f(descriptor2, 2);
                i10 |= 4;
            }
        }
        bVarB.u(descriptor2);
        return new FactorType.TOTP.Response(i10, strF, strF2, strF3, null);
    }

    @Override // ya.s, ya.c
    public g getDescriptor() {
        return descriptor;
    }

    @Override // ya.s
    public void serialize(bb.e encoder, FactorType.TOTP.Response value) {
        g descriptor2 = getDescriptor();
        c cVarB = encoder.b(descriptor2);
        FactorType.TOTP.Response.write$Self$gotrue_kt_release(value, cVarB, descriptor2);
        cVarB.e();
    }

    @Override // cb.e0
    public h<?>[] typeParametersSerializers() {
        return b1.f7464b;
    }
}
