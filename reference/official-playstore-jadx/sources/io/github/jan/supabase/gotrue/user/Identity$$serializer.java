package io.github.jan.supabase.gotrue.user;

import a.a;
import ab.g;
import androidx.media3.extractor.text.ttml.TtmlNode;
import bb.b;
import bb.c;
import bb.d;
import cb.b1;
import cb.d1;
import cb.e0;
import cb.l1;
import cb.r1;
import db.b0;
import kotlin.Metadata;
import kotlinx.serialization.UnknownFieldException;
import x6.e;
import ya.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\u0007\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\u000b\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ \u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0016\u001a\u00020\u00138VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"io/github/jan/supabase/gotrue/user/Identity.$serializer", "Lcb/e0;", "Lio/github/jan/supabase/gotrue/user/Identity;", "<init>", "()V", "", "Lya/h;", "childSerializers", "()[Lya/h;", "Lbb/d;", "decoder", "deserialize", "(Lbb/d;)Lio/github/jan/supabase/gotrue/user/Identity;", "Lbb/e;", "encoder", "value", "Lx6/t0;", "serialize", "(Lbb/e;Lio/github/jan/supabase/gotrue/user/Identity;)V", "Lab/g;", "getDescriptor", "()Lab/g;", "descriptor", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@e
public final class Identity$$serializer implements e0<Identity> {
    public static final Identity$$serializer INSTANCE;
    private static final /* synthetic */ d1 descriptor;

    static {
        Identity$$serializer identity$$serializer = new Identity$$serializer();
        INSTANCE = identity$$serializer;
        d1 d1Var = new d1("io.github.jan.supabase.gotrue.user.Identity", identity$$serializer, 8);
        d1Var.j(TtmlNode.ATTR_ID, false);
        d1Var.j("identity_data", false);
        d1Var.j("identity_id", true);
        d1Var.j("last_sign_in_at", true);
        d1Var.j("updated_at", true);
        d1Var.j("created_at", true);
        d1Var.j("provider", false);
        d1Var.j("user_id", false);
        descriptor = d1Var;
    }

    private Identity$$serializer() {
    }

    @Override // cb.e0
    public h<?>[] childSerializers() {
        r1 r1Var = r1.f7553a;
        return new h[]{r1Var, db.e0.f14890a, a.Q(r1Var), a.Q(r1Var), a.Q(r1Var), a.Q(r1Var), r1Var, r1Var};
    }

    @Override // ya.c
    public Identity deserialize(d decoder) {
        g descriptor2 = getDescriptor();
        b bVarB = decoder.b(descriptor2);
        int i10 = 0;
        String strF = null;
        b0 b0Var = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String strF2 = null;
        String strF3 = null;
        boolean z = true;
        while (z) {
            int iW = bVarB.w(descriptor2);
            switch (iW) {
                case -1:
                    z = false;
                    break;
                case 0:
                    strF = bVarB.f(descriptor2, 0);
                    i10 |= 1;
                    break;
                case 1:
                    b0Var = (b0) bVarB.d(descriptor2, 1, db.e0.f14890a, b0Var);
                    i10 |= 2;
                    break;
                case 2:
                    str = (String) bVarB.j(descriptor2, 2, r1.f7553a, str);
                    i10 |= 4;
                    break;
                case 3:
                    str2 = (String) bVarB.j(descriptor2, 3, r1.f7553a, str2);
                    i10 |= 8;
                    break;
                case 4:
                    str3 = (String) bVarB.j(descriptor2, 4, r1.f7553a, str3);
                    i10 |= 16;
                    break;
                case 5:
                    str4 = (String) bVarB.j(descriptor2, 5, r1.f7553a, str4);
                    i10 |= 32;
                    break;
                case 6:
                    strF2 = bVarB.f(descriptor2, 6);
                    i10 |= 64;
                    break;
                case 7:
                    strF3 = bVarB.f(descriptor2, 7);
                    i10 |= 128;
                    break;
                default:
                    throw new UnknownFieldException(iW);
            }
        }
        bVarB.u(descriptor2);
        return new Identity(i10, strF, b0Var, str, str2, str3, str4, strF2, strF3, (l1) null);
    }

    @Override // ya.s, ya.c
    public g getDescriptor() {
        return descriptor;
    }

    @Override // ya.s
    public void serialize(bb.e encoder, Identity value) {
        g descriptor2 = getDescriptor();
        c cVarB = encoder.b(descriptor2);
        Identity.write$Self$gotrue_kt_release(value, cVarB, descriptor2);
        cVarB.e();
    }

    @Override // cb.e0
    public h<?>[] typeParametersSerializers() {
        return b1.f7464b;
    }
}
