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
import io.github.jan.supabase.gotrue.providers.IDTokenProvider;
import io.github.jan.supabase.gotrue.providers.builtin.IDToken;
import kotlin.Metadata;
import kotlinx.serialization.UnknownFieldException;
import x6.e;
import ya.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\u0007\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\u000b\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ \u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0016\u001a\u00020\u00138VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"io/github/jan/supabase/gotrue/providers/builtin/IDToken.Config.$serializer", "Lcb/e0;", "Lio/github/jan/supabase/gotrue/providers/builtin/IDToken$Config;", "<init>", "()V", "", "Lya/h;", "childSerializers", "()[Lya/h;", "Lbb/d;", "decoder", "deserialize", "(Lbb/d;)Lio/github/jan/supabase/gotrue/providers/builtin/IDToken$Config;", "Lbb/e;", "encoder", "value", "Lx6/t0;", "serialize", "(Lbb/e;Lio/github/jan/supabase/gotrue/providers/builtin/IDToken$Config;)V", "Lab/g;", "getDescriptor", "()Lab/g;", "descriptor", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@e
public final class IDToken$Config$$serializer implements e0<IDToken.Config> {
    public static final IDToken$Config$$serializer INSTANCE;
    private static final /* synthetic */ d1 descriptor;

    static {
        IDToken$Config$$serializer iDToken$Config$$serializer = new IDToken$Config$$serializer();
        INSTANCE = iDToken$Config$$serializer;
        d1 d1Var = new d1("io.github.jan.supabase.gotrue.providers.builtin.IDToken.Config", iDToken$Config$$serializer, 6);
        d1Var.j("gotrue_meta_security", true);
        d1Var.j("data", true);
        d1Var.j("id_token", true);
        d1Var.j("provider", true);
        d1Var.j("access_token", true);
        d1Var.j("nonce", true);
        descriptor = d1Var;
    }

    private IDToken$Config$$serializer() {
    }

    @Override // cb.e0
    public h<?>[] childSerializers() {
        h<?> hVarQ = a.Q(CaptchaTokenSerializer.INSTANCE);
        h<?> hVarQ2 = a.Q(db.e0.f14890a);
        r1 r1Var = r1.f7553a;
        return new h[]{hVarQ, hVarQ2, r1Var, a.Q(IDTokenProvider.INSTANCE), a.Q(r1Var), a.Q(r1Var)};
    }

    @Override // ya.c
    public IDToken.Config deserialize(d decoder) {
        g descriptor2 = getDescriptor();
        b bVarB = decoder.b(descriptor2);
        int i10 = 0;
        String str = null;
        b0 b0Var = null;
        String strF = null;
        IDTokenProvider iDTokenProvider = null;
        String str2 = null;
        String str3 = null;
        boolean z = true;
        while (z) {
            int iW = bVarB.w(descriptor2);
            switch (iW) {
                case -1:
                    z = false;
                    break;
                case 0:
                    str = (String) bVarB.j(descriptor2, 0, CaptchaTokenSerializer.INSTANCE, str);
                    i10 |= 1;
                    break;
                case 1:
                    b0Var = (b0) bVarB.j(descriptor2, 1, db.e0.f14890a, b0Var);
                    i10 |= 2;
                    break;
                case 2:
                    strF = bVarB.f(descriptor2, 2);
                    i10 |= 4;
                    break;
                case 3:
                    iDTokenProvider = (IDTokenProvider) bVarB.j(descriptor2, 3, IDTokenProvider.INSTANCE, iDTokenProvider);
                    i10 |= 8;
                    break;
                case 4:
                    str2 = (String) bVarB.j(descriptor2, 4, r1.f7553a, str2);
                    i10 |= 16;
                    break;
                case 5:
                    str3 = (String) bVarB.j(descriptor2, 5, r1.f7553a, str3);
                    i10 |= 32;
                    break;
                default:
                    throw new UnknownFieldException(iW);
            }
        }
        bVarB.u(descriptor2);
        return new IDToken.Config(i10, str, b0Var, strF, iDTokenProvider, str2, str3, null);
    }

    @Override // ya.s, ya.c
    public g getDescriptor() {
        return descriptor;
    }

    @Override // ya.s
    public void serialize(bb.e encoder, IDToken.Config value) {
        g descriptor2 = getDescriptor();
        c cVarB = encoder.b(descriptor2);
        IDToken.Config.write$Self$gotrue_kt_release(value, cVarB, descriptor2);
        cVarB.e();
    }

    @Override // cb.e0
    public h<?>[] typeParametersSerializers() {
        return b1.f7464b;
    }
}
