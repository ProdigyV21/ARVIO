package com.arflix.tv.data.repository;

import cb.b1;
import cb.d1;
import cb.r1;
import kotlin.Metadata;
import kotlinx.serialization.UnknownFieldException;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0002\u0000\u0002\bË\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"com/arflix/tv/data/repository/AuthRepository$saveAddonsToProfile$AddonsUpdate..serializer", "Lcb/e0;", "com/arflix/tv/data/repository/AuthRepository$saveAddonsToProfile$AddonsUpdate", "<init>", "()V", "Lbb/e;", "encoder", "value", "Lx6/t0;", "serialize", "(Lbb/e;Lcom/arflix/tv/data/repository/AuthRepository$saveAddonsToProfile$AddonsUpdate;)V", "Lbb/d;", "decoder", "deserialize", "(Lbb/d;)Lcom/arflix/tv/data/repository/AuthRepository$saveAddonsToProfile$AddonsUpdate;", "", "Lya/h;", "childSerializers", "()[Lya/h;", "Lab/g;", "descriptor", "Lab/g;", "getDescriptor", "()Lab/g;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
@x6.e
public final /* synthetic */ class AuthRepository$saveAddonsToProfile$AddonsUpdate$$serializer implements cb.e0<AuthRepository$saveAddonsToProfile$AddonsUpdate> {
    public static final AuthRepository$saveAddonsToProfile$AddonsUpdate$$serializer INSTANCE;
    private static final ab.g descriptor;

    static {
        AuthRepository$saveAddonsToProfile$AddonsUpdate$$serializer authRepository$saveAddonsToProfile$AddonsUpdate$$serializer = new AuthRepository$saveAddonsToProfile$AddonsUpdate$$serializer();
        INSTANCE = authRepository$saveAddonsToProfile$AddonsUpdate$$serializer;
        d1 d1Var = new d1("com.arflix.tv.data.repository.AuthRepository.saveAddonsToProfile.AddonsUpdate", authRepository$saveAddonsToProfile$AddonsUpdate$$serializer, 1);
        d1Var.j("addons", false);
        descriptor = d1Var;
    }

    private AuthRepository$saveAddonsToProfile$AddonsUpdate$$serializer() {
    }

    @Override // cb.e0
    public final ya.h<?>[] childSerializers() {
        return new ya.h[]{r1.f7553a};
    }

    @Override // ya.c
    public final AuthRepository$saveAddonsToProfile$AddonsUpdate deserialize(bb.d decoder) {
        ab.g gVar = descriptor;
        bb.b bVarB = decoder.b(gVar);
        boolean z = true;
        int i10 = 0;
        String strF = null;
        while (z) {
            int iW = bVarB.w(gVar);
            if (iW == -1) {
                z = false;
            } else {
                if (iW != 0) {
                    throw new UnknownFieldException(iW);
                }
                strF = bVarB.f(gVar, 0);
                i10 = 1;
            }
        }
        bVarB.u(gVar);
        return new AuthRepository$saveAddonsToProfile$AddonsUpdate(i10, strF, null);
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return descriptor;
    }

    @Override // ya.s
    public final void serialize(bb.e encoder, AuthRepository$saveAddonsToProfile$AddonsUpdate value) {
        ab.g gVar = descriptor;
        bb.c cVarB = encoder.b(gVar);
        cVarB.x(gVar, 0, value.addons);
        cVarB.e();
    }

    @Override // cb.e0
    public /* bridge */ ya.h<?>[] typeParametersSerializers() {
        return b1.f7464b;
    }
}
