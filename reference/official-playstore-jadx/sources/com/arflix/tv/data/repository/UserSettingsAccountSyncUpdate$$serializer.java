package com.arflix.tv.data.repository;

import cb.b1;
import cb.d1;
import cb.r1;
import kotlin.Metadata;
import kotlinx.serialization.UnknownFieldException;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"com/arflix/tv/data/repository/UserSettingsAccountSyncUpdate.$serializer", "Lcb/e0;", "Lcom/arflix/tv/data/repository/UserSettingsAccountSyncUpdate;", "<init>", "()V", "Lbb/e;", "encoder", "value", "Lx6/t0;", "serialize", "(Lbb/e;Lcom/arflix/tv/data/repository/UserSettingsAccountSyncUpdate;)V", "Lbb/d;", "decoder", "deserialize", "(Lbb/d;)Lcom/arflix/tv/data/repository/UserSettingsAccountSyncUpdate;", "", "Lya/h;", "childSerializers", "()[Lya/h;", "Lab/g;", "descriptor", "Lab/g;", "getDescriptor", "()Lab/g;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
@x6.e
public final /* synthetic */ class UserSettingsAccountSyncUpdate$$serializer implements cb.e0<UserSettingsAccountSyncUpdate> {
    public static final int $stable;
    public static final UserSettingsAccountSyncUpdate$$serializer INSTANCE;
    private static final ab.g descriptor;

    static {
        UserSettingsAccountSyncUpdate$$serializer userSettingsAccountSyncUpdate$$serializer = new UserSettingsAccountSyncUpdate$$serializer();
        INSTANCE = userSettingsAccountSyncUpdate$$serializer;
        $stable = 8;
        d1 d1Var = new d1("com.arflix.tv.data.repository.UserSettingsAccountSyncUpdate", userSettingsAccountSyncUpdate$$serializer, 2);
        d1Var.j("user_id", false);
        d1Var.j("settings", false);
        descriptor = d1Var;
    }

    private UserSettingsAccountSyncUpdate$$serializer() {
    }

    @Override // cb.e0
    public final ya.h<?>[] childSerializers() {
        return new ya.h[]{r1.f7553a, db.e0.f14890a};
    }

    @Override // ya.c
    public final UserSettingsAccountSyncUpdate deserialize(bb.d decoder) {
        ab.g gVar = descriptor;
        bb.b bVarB = decoder.b(gVar);
        boolean z = true;
        int i10 = 0;
        String strF = null;
        db.b0 b0Var = null;
        while (z) {
            int iW = bVarB.w(gVar);
            if (iW == -1) {
                z = false;
            } else if (iW == 0) {
                strF = bVarB.f(gVar, 0);
                i10 |= 1;
            } else {
                if (iW != 1) {
                    throw new UnknownFieldException(iW);
                }
                b0Var = (db.b0) bVarB.d(gVar, 1, db.e0.f14890a, b0Var);
                i10 |= 2;
            }
        }
        bVarB.u(gVar);
        return new UserSettingsAccountSyncUpdate(i10, strF, b0Var, null);
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return descriptor;
    }

    @Override // ya.s
    public final void serialize(bb.e encoder, UserSettingsAccountSyncUpdate value) {
        ab.g gVar = descriptor;
        bb.c cVarB = encoder.b(gVar);
        UserSettingsAccountSyncUpdate.write$Self$app_playRelease(value, cVarB, gVar);
        cVarB.e();
    }

    @Override // cb.e0
    public /* bridge */ ya.h<?>[] typeParametersSerializers() {
        return b1.f7464b;
    }
}
