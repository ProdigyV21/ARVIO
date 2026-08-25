package io.github.jan.supabase.gotrue.user;

import a.a;
import ab.g;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.session.MediaUtils;
import bb.b;
import bb.d;
import cb.b1;
import cb.d1;
import cb.e0;
import cb.l1;
import cb.r1;
import db.b0;
import java.util.List;
import kotlin.Metadata;
import kotlinx.serialization.UnknownFieldException;
import wa.c;
import x6.e;
import ya.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\u0007\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\u000b\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ \u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0016\u001a\u00020\u00138VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"io/github/jan/supabase/gotrue/user/UserInfo.$serializer", "Lcb/e0;", "Lio/github/jan/supabase/gotrue/user/UserInfo;", "<init>", "()V", "", "Lya/h;", "childSerializers", "()[Lya/h;", "Lbb/d;", "decoder", "deserialize", "(Lbb/d;)Lio/github/jan/supabase/gotrue/user/UserInfo;", "Lbb/e;", "encoder", "value", "Lx6/t0;", "serialize", "(Lbb/e;Lio/github/jan/supabase/gotrue/user/UserInfo;)V", "Lab/g;", "getDescriptor", "()Lab/g;", "descriptor", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@e
public final class UserInfo$$serializer implements e0<UserInfo> {
    public static final UserInfo$$serializer INSTANCE;
    private static final /* synthetic */ d1 descriptor;

    static {
        UserInfo$$serializer userInfo$$serializer = new UserInfo$$serializer();
        INSTANCE = userInfo$$serializer;
        d1 d1Var = new d1("io.github.jan.supabase.gotrue.user.UserInfo", userInfo$$serializer, 23);
        d1Var.j("app_metadata", true);
        d1Var.j("aud", false);
        d1Var.j("confirmation_sent_at", true);
        d1Var.j("confirmed_at", true);
        d1Var.j("created_at", true);
        d1Var.j("email", true);
        d1Var.j("email_confirmed_at", true);
        d1Var.j("factors", true);
        d1Var.j(TtmlNode.ATTR_ID, false);
        d1Var.j("identities", true);
        d1Var.j("last_sign_in_at", true);
        d1Var.j("phone", true);
        d1Var.j("role", true);
        d1Var.j("updated_at", true);
        d1Var.j("user_metadata", true);
        d1Var.j("phone_change_sent_at", true);
        d1Var.j("new_phone", true);
        d1Var.j("email_change_sent_at", true);
        d1Var.j("new_email", true);
        d1Var.j("invited_at", true);
        d1Var.j("recovery_sent_at", true);
        d1Var.j("phone_confirmed_at", true);
        d1Var.j("action_link", true);
        descriptor = d1Var;
    }

    private UserInfo$$serializer() {
    }

    @Override // cb.e0
    public h<?>[] childSerializers() {
        h<?>[] hVarArr = UserInfo.$childSerializers;
        db.e0 e0Var = db.e0.f14890a;
        h<?> hVarQ = a.Q(e0Var);
        r1 r1Var = r1.f7553a;
        xa.a aVar = xa.a.f22712a;
        return new h[]{hVarQ, r1Var, a.Q(aVar), a.Q(aVar), a.Q(aVar), a.Q(r1Var), a.Q(aVar), hVarArr[7], r1Var, a.Q(hVarArr[9]), a.Q(aVar), a.Q(r1Var), a.Q(r1Var), a.Q(aVar), a.Q(e0Var), a.Q(aVar), a.Q(r1Var), a.Q(aVar), a.Q(r1Var), a.Q(aVar), a.Q(aVar), a.Q(aVar), a.Q(r1Var)};
    }

    @Override // ya.c
    public UserInfo deserialize(d decoder) {
        String str;
        int i10;
        b0 b0Var;
        b0 b0Var2;
        b0 b0Var3;
        String str2;
        g descriptor2 = getDescriptor();
        b bVarB = decoder.b(descriptor2);
        h[] hVarArr = UserInfo.$childSerializers;
        b0 b0Var4 = null;
        c cVar = null;
        String str3 = null;
        c cVar2 = null;
        String str4 = null;
        String str5 = null;
        c cVar3 = null;
        c cVar4 = null;
        String str6 = null;
        c cVar5 = null;
        c cVar6 = null;
        c cVar7 = null;
        String str7 = null;
        String strF = null;
        String strF2 = null;
        b0 b0Var5 = null;
        c cVar8 = null;
        c cVar9 = null;
        c cVar10 = null;
        String str8 = null;
        c cVar11 = null;
        List list = null;
        List list2 = null;
        int i11 = 0;
        boolean z = true;
        while (z) {
            c cVar12 = cVar2;
            int iW = bVarB.w(descriptor2);
            switch (iW) {
                case -1:
                    b0Var = b0Var4;
                    b0Var2 = b0Var5;
                    z = false;
                    b0Var4 = b0Var;
                    b0Var5 = b0Var2;
                    cVar2 = cVar12;
                    break;
                case 0:
                    b0Var = b0Var4;
                    b0Var2 = (b0) bVarB.j(descriptor2, 0, db.e0.f14890a, b0Var5);
                    i11 |= 1;
                    cVar8 = cVar8;
                    str4 = str4;
                    b0Var4 = b0Var;
                    b0Var5 = b0Var2;
                    cVar2 = cVar12;
                    break;
                case 1:
                    b0Var3 = b0Var4;
                    strF2 = bVarB.f(descriptor2, 1);
                    i11 |= 2;
                    cVar2 = cVar12;
                    b0Var4 = b0Var3;
                    break;
                case 2:
                    b0Var3 = b0Var4;
                    str2 = str4;
                    cVar8 = (c) bVarB.j(descriptor2, 2, xa.a.f22712a, cVar8);
                    i11 |= 4;
                    cVar2 = cVar12;
                    str4 = str2;
                    b0Var4 = b0Var3;
                    break;
                case 3:
                    b0Var3 = b0Var4;
                    str2 = str4;
                    cVar9 = (c) bVarB.j(descriptor2, 3, xa.a.f22712a, cVar9);
                    i11 |= 8;
                    cVar2 = cVar12;
                    str4 = str2;
                    b0Var4 = b0Var3;
                    break;
                case 4:
                    b0Var3 = b0Var4;
                    str2 = str4;
                    cVar10 = (c) bVarB.j(descriptor2, 4, xa.a.f22712a, cVar10);
                    i11 |= 16;
                    cVar2 = cVar12;
                    str4 = str2;
                    b0Var4 = b0Var3;
                    break;
                case 5:
                    b0Var3 = b0Var4;
                    str2 = str4;
                    str8 = (String) bVarB.j(descriptor2, 5, r1.f7553a, str8);
                    i11 |= 32;
                    cVar2 = cVar12;
                    str4 = str2;
                    b0Var4 = b0Var3;
                    break;
                case 6:
                    b0Var3 = b0Var4;
                    str2 = str4;
                    cVar11 = (c) bVarB.j(descriptor2, 6, xa.a.f22712a, cVar11);
                    i11 |= 64;
                    cVar2 = cVar12;
                    str4 = str2;
                    b0Var4 = b0Var3;
                    break;
                case 7:
                    b0Var3 = b0Var4;
                    str2 = str4;
                    list = (List) bVarB.d(descriptor2, 7, hVarArr[7], list);
                    i11 |= 128;
                    cVar2 = cVar12;
                    str4 = str2;
                    b0Var4 = b0Var3;
                    break;
                case 8:
                    b0Var3 = b0Var4;
                    strF = bVarB.f(descriptor2, 8);
                    i11 |= 256;
                    cVar2 = cVar12;
                    b0Var4 = b0Var3;
                    break;
                case 9:
                    b0Var3 = b0Var4;
                    str2 = str4;
                    list2 = (List) bVarB.j(descriptor2, 9, hVarArr[9], list2);
                    i11 |= 512;
                    cVar2 = cVar12;
                    str4 = str2;
                    b0Var4 = b0Var3;
                    break;
                case 10:
                    b0Var3 = b0Var4;
                    str2 = str4;
                    cVar2 = (c) bVarB.j(descriptor2, 10, xa.a.f22712a, cVar12);
                    i11 |= 1024;
                    str4 = str2;
                    b0Var4 = b0Var3;
                    break;
                case 11:
                    b0Var3 = b0Var4;
                    str4 = (String) bVarB.j(descriptor2, 11, r1.f7553a, str4);
                    i11 |= 2048;
                    cVar2 = cVar12;
                    b0Var4 = b0Var3;
                    break;
                case 12:
                    str = str4;
                    str3 = (String) bVarB.j(descriptor2, 12, r1.f7553a, str3);
                    i11 |= 4096;
                    cVar2 = cVar12;
                    str4 = str;
                    break;
                case 13:
                    str = str4;
                    cVar = (c) bVarB.j(descriptor2, 13, xa.a.f22712a, cVar);
                    i11 |= 8192;
                    cVar2 = cVar12;
                    str4 = str;
                    break;
                case 14:
                    str = str4;
                    b0Var4 = (b0) bVarB.j(descriptor2, 14, db.e0.f14890a, b0Var4);
                    i11 |= 16384;
                    cVar2 = cVar12;
                    str4 = str;
                    break;
                case 15:
                    str = str4;
                    cVar3 = (c) bVarB.j(descriptor2, 15, xa.a.f22712a, cVar3);
                    i10 = 32768;
                    i11 |= i10;
                    cVar2 = cVar12;
                    str4 = str;
                    break;
                case 16:
                    str = str4;
                    str5 = (String) bVarB.j(descriptor2, 16, r1.f7553a, str5);
                    i10 = 65536;
                    i11 |= i10;
                    cVar2 = cVar12;
                    str4 = str;
                    break;
                case 17:
                    str = str4;
                    cVar4 = (c) bVarB.j(descriptor2, 17, xa.a.f22712a, cVar4);
                    i10 = 131072;
                    i11 |= i10;
                    cVar2 = cVar12;
                    str4 = str;
                    break;
                case 18:
                    str = str4;
                    str6 = (String) bVarB.j(descriptor2, 18, r1.f7553a, str6);
                    i10 = MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES;
                    i11 |= i10;
                    cVar2 = cVar12;
                    str4 = str;
                    break;
                case 19:
                    str = str4;
                    cVar5 = (c) bVarB.j(descriptor2, 19, xa.a.f22712a, cVar5);
                    i10 = 524288;
                    i11 |= i10;
                    cVar2 = cVar12;
                    str4 = str;
                    break;
                case 20:
                    str = str4;
                    cVar6 = (c) bVarB.j(descriptor2, 20, xa.a.f22712a, cVar6);
                    i10 = ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES;
                    i11 |= i10;
                    cVar2 = cVar12;
                    str4 = str;
                    break;
                case 21:
                    str = str4;
                    cVar7 = (c) bVarB.j(descriptor2, 21, xa.a.f22712a, cVar7);
                    i10 = 2097152;
                    i11 |= i10;
                    cVar2 = cVar12;
                    str4 = str;
                    break;
                case 22:
                    str = str4;
                    str7 = (String) bVarB.j(descriptor2, 22, r1.f7553a, str7);
                    i10 = 4194304;
                    i11 |= i10;
                    cVar2 = cVar12;
                    str4 = str;
                    break;
                default:
                    throw new UnknownFieldException(iW);
            }
        }
        b0 b0Var6 = b0Var4;
        b0 b0Var7 = b0Var5;
        c cVar13 = cVar8;
        bVarB.u(descriptor2);
        c cVar14 = cVar5;
        String str9 = str7;
        return new UserInfo(i11, b0Var7, strF2, cVar13, cVar9, cVar10, str8, cVar11, list, strF, list2, cVar2, str4, str3, cVar, b0Var6, cVar3, str5, cVar4, str6, cVar14, cVar6, cVar7, str9, (l1) null);
    }

    @Override // ya.s, ya.c
    public g getDescriptor() {
        return descriptor;
    }

    @Override // ya.s
    public void serialize(bb.e encoder, UserInfo value) {
        g descriptor2 = getDescriptor();
        bb.c cVarB = encoder.b(descriptor2);
        UserInfo.write$Self$gotrue_kt_release(value, cVarB, descriptor2);
        cVarB.e();
    }

    @Override // cb.e0
    public h<?>[] typeParametersSerializers() {
        return b1.f7464b;
    }
}
