package io.github.jan.supabase.postgrest;

import a.a;
import ab.g;
import bb.b;
import bb.c;
import bb.d;
import cb.b1;
import cb.d1;
import cb.e0;
import cb.l1;
import cb.r1;
import kotlin.Metadata;
import kotlinx.serialization.UnknownFieldException;
import x6.e;
import ya.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\u0007\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\u000b\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ \u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0016\u001a\u00020\u00138VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"io/github/jan/supabase/postgrest/PostgrestErrorResponse.$serializer", "Lcb/e0;", "Lio/github/jan/supabase/postgrest/PostgrestErrorResponse;", "<init>", "()V", "", "Lya/h;", "childSerializers", "()[Lya/h;", "Lbb/d;", "decoder", "deserialize", "(Lbb/d;)Lio/github/jan/supabase/postgrest/PostgrestErrorResponse;", "Lbb/e;", "encoder", "value", "Lx6/t0;", "serialize", "(Lbb/e;Lio/github/jan/supabase/postgrest/PostgrestErrorResponse;)V", "Lab/g;", "getDescriptor", "()Lab/g;", "descriptor", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@e
public final class PostgrestErrorResponse$$serializer implements e0<PostgrestErrorResponse> {
    public static final PostgrestErrorResponse$$serializer INSTANCE;
    private static final /* synthetic */ d1 descriptor;

    static {
        PostgrestErrorResponse$$serializer postgrestErrorResponse$$serializer = new PostgrestErrorResponse$$serializer();
        INSTANCE = postgrestErrorResponse$$serializer;
        d1 d1Var = new d1("io.github.jan.supabase.postgrest.PostgrestErrorResponse", postgrestErrorResponse$$serializer, 4);
        d1Var.j("message", false);
        d1Var.j("hint", true);
        d1Var.j("details", true);
        d1Var.j("code", true);
        descriptor = d1Var;
    }

    private PostgrestErrorResponse$$serializer() {
    }

    @Override // cb.e0
    public h<?>[] childSerializers() {
        r1 r1Var = r1.f7553a;
        return new h[]{r1Var, a.Q(r1Var), a.Q(r1Var), a.Q(r1Var)};
    }

    @Override // ya.c
    public PostgrestErrorResponse deserialize(d decoder) {
        g descriptor2 = getDescriptor();
        b bVarB = decoder.b(descriptor2);
        int i10 = 0;
        String strF = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        boolean z = true;
        while (z) {
            int iW = bVarB.w(descriptor2);
            if (iW == -1) {
                z = false;
            } else if (iW == 0) {
                strF = bVarB.f(descriptor2, 0);
                i10 |= 1;
            } else if (iW == 1) {
                str = (String) bVarB.j(descriptor2, 1, r1.f7553a, str);
                i10 |= 2;
            } else if (iW == 2) {
                str2 = (String) bVarB.j(descriptor2, 2, r1.f7553a, str2);
                i10 |= 4;
            } else {
                if (iW != 3) {
                    throw new UnknownFieldException(iW);
                }
                str3 = (String) bVarB.j(descriptor2, 3, r1.f7553a, str3);
                i10 |= 8;
            }
        }
        bVarB.u(descriptor2);
        return new PostgrestErrorResponse(i10, strF, str, str2, str3, (l1) null);
    }

    @Override // ya.s, ya.c
    public g getDescriptor() {
        return descriptor;
    }

    @Override // ya.s
    public void serialize(bb.e encoder, PostgrestErrorResponse value) {
        g descriptor2 = getDescriptor();
        c cVarB = encoder.b(descriptor2);
        PostgrestErrorResponse.write$Self$postgrest_kt_release(value, cVarB, descriptor2);
        cVarB.e();
    }

    @Override // cb.e0
    public h<?>[] typeParametersSerializers() {
        return b1.f7464b;
    }
}
