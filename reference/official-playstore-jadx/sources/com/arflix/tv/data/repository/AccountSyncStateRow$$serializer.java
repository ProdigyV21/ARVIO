package com.arflix.tv.data.repository;

import cb.b1;
import cb.d1;
import cb.l1;
import cb.r1;
import kotlin.Metadata;
import kotlinx.serialization.UnknownFieldException;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"com/arflix/tv/data/repository/AccountSyncStateRow.$serializer", "Lcb/e0;", "Lcom/arflix/tv/data/repository/AccountSyncStateRow;", "<init>", "()V", "Lbb/e;", "encoder", "value", "Lx6/t0;", "serialize", "(Lbb/e;Lcom/arflix/tv/data/repository/AccountSyncStateRow;)V", "Lbb/d;", "decoder", "deserialize", "(Lbb/d;)Lcom/arflix/tv/data/repository/AccountSyncStateRow;", "", "Lya/h;", "childSerializers", "()[Lya/h;", "Lab/g;", "descriptor", "Lab/g;", "getDescriptor", "()Lab/g;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
@x6.e
public final /* synthetic */ class AccountSyncStateRow$$serializer implements cb.e0<AccountSyncStateRow> {
    public static final int $stable;
    public static final AccountSyncStateRow$$serializer INSTANCE;
    private static final ab.g descriptor;

    static {
        AccountSyncStateRow$$serializer accountSyncStateRow$$serializer = new AccountSyncStateRow$$serializer();
        INSTANCE = accountSyncStateRow$$serializer;
        $stable = 8;
        d1 d1Var = new d1("com.arflix.tv.data.repository.AccountSyncStateRow", accountSyncStateRow$$serializer, 3);
        d1Var.j("user_id", false);
        d1Var.j("payload", true);
        d1Var.j("updated_at", true);
        descriptor = d1Var;
    }

    private AccountSyncStateRow$$serializer() {
    }

    @Override // cb.e0
    public final ya.h<?>[] childSerializers() {
        r1 r1Var = r1.f7553a;
        return new ya.h[]{r1Var, a.a.Q(r1Var), a.a.Q(r1Var)};
    }

    @Override // ya.c
    public final AccountSyncStateRow deserialize(bb.d decoder) {
        ab.g gVar = descriptor;
        bb.b bVarB = decoder.b(gVar);
        int i10 = 0;
        String strF = null;
        String str = null;
        String str2 = null;
        boolean z = true;
        while (z) {
            int iW = bVarB.w(gVar);
            if (iW == -1) {
                z = false;
            } else if (iW == 0) {
                strF = bVarB.f(gVar, 0);
                i10 |= 1;
            } else if (iW == 1) {
                str = (String) bVarB.j(gVar, 1, r1.f7553a, str);
                i10 |= 2;
            } else {
                if (iW != 2) {
                    throw new UnknownFieldException(iW);
                }
                str2 = (String) bVarB.j(gVar, 2, r1.f7553a, str2);
                i10 |= 4;
            }
        }
        bVarB.u(gVar);
        return new AccountSyncStateRow(i10, strF, str, str2, (l1) null);
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return descriptor;
    }

    @Override // ya.s
    public final void serialize(bb.e encoder, AccountSyncStateRow value) {
        ab.g gVar = descriptor;
        bb.c cVarB = encoder.b(gVar);
        AccountSyncStateRow.write$Self$app_playRelease(value, cVarB, gVar);
        cVarB.e();
    }

    @Override // cb.e0
    public /* bridge */ ya.h<?>[] typeParametersSerializers() {
        return b1.f7464b;
    }
}
