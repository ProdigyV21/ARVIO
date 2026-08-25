package com.google.android.gms.internal.auth;

import android.accounts.Account;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.auth.api.proxy.ProxyRequest;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class p2 implements j3, com.google.android.gms.common.api.internal.t, w0, k1 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final c2 f13089m = new c2(1);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f13090i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f13091l;

    public /* synthetic */ p2(com.google.android.gms.common.api.n nVar, Object obj, int i10) {
        this.f13090i = i10;
        this.f13091l = obj;
    }

    @Override // com.google.android.gms.common.api.internal.t
    public void accept(Object obj, Object obj2) throws RemoteException {
        switch (this.f13090i) {
            case 1:
                ProxyRequest proxyRequest = (ProxyRequest) this.f13091l;
                e0 e0Var = new e0((com.google.android.gms.tasks.i) obj2, 0);
                b0 b0Var = (b0) ((z) obj).getService();
                Parcel parcelZza = b0Var.zza();
                o0.d(parcelZza, e0Var);
                o0.c(parcelZza, proxyRequest);
                b0Var.zzc(1, parcelZza);
                break;
            case 2:
            case 3:
            default:
                AccountChangeEventsRequest accountChangeEventsRequest = (AccountChangeEventsRequest) this.f13091l;
                m4 m4Var = new m4((com.google.android.gms.tasks.i) obj2);
                i4 i4Var = (i4) ((e4) obj).getService();
                Parcel parcelZza2 = i4Var.zza();
                o0.d(parcelZza2, m4Var);
                o0.c(parcelZza2, accountChangeEventsRequest);
                i4Var.zzc(4, parcelZza2);
                break;
            case 4:
                String str = (String) this.f13091l;
                i4 i4Var2 = (i4) ((e4) obj).getService();
                b bVar = new b(1, (com.google.android.gms.tasks.i) obj2);
                Parcel parcelZza3 = i4Var2.zza();
                o0.d(parcelZza3, bVar);
                parcelZza3.writeString(str);
                i4Var2.zzc(3, parcelZza3);
                break;
            case 5:
                Account account = (Account) this.f13091l;
                i4 i4Var3 = (i4) ((e4) obj).getService();
                b bVar2 = new b(0, (com.google.android.gms.tasks.i) obj2);
                Parcel parcelZza4 = i4Var3.zza();
                o0.d(parcelZza4, bVar2);
                o0.c(parcelZza4, account);
                i4Var3.zzc(6, parcelZza4);
                break;
            case 6:
                m0 m0Var = (m0) this.f13091l;
                i4 i4Var4 = (i4) ((e4) obj).getService();
                l4 l4Var = new l4(0, (com.google.android.gms.tasks.i) obj2);
                Parcel parcelZza5 = i4Var4.zza();
                o0.d(parcelZza5, l4Var);
                o0.c(parcelZza5, m0Var);
                i4Var4.zzc(2, parcelZza5);
                break;
        }
    }

    @Override // com.google.android.gms.internal.auth.k1
    public Object zza() {
        Map eVar;
        int i10 = this.f13090i;
        Object obj = this.f13091l;
        switch (i10) {
            case 2:
                t0 t0Var = (t0) obj;
                Cursor cursorQuery = t0Var.f13116i.query(t0Var.f13117l, t0.f13115r, null, null, null);
                if (cursorQuery == null) {
                    return Collections.EMPTY_MAP;
                }
                try {
                    int count = cursorQuery.getCount();
                    if (count == 0) {
                        eVar = Collections.EMPTY_MAP;
                    } else {
                        eVar = count <= 256 ? new t.e(count) : new HashMap(count, 1.0f);
                        while (cursorQuery.moveToNext()) {
                            eVar.put(cursorQuery.getString(0), cursorQuery.getString(1));
                        }
                    }
                    return eVar;
                } finally {
                    cursorQuery.close();
                }
            default:
                Object obj2 = c1.f13004g;
                return z0.a((Context) obj);
        }
    }

    public /* synthetic */ p2(Object obj, int i10) {
        this.f13090i = i10;
        this.f13091l = obj;
    }

    public p2() {
        u2 u2Var;
        this.f13090i = 0;
        try {
            u2Var = (u2) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", null).invoke(null, null);
        } catch (Exception unused) {
            u2Var = f13089m;
        }
        o2 o2Var = new o2(c2.f13013b, u2Var);
        Charset charset = i2.f13047a;
        this.f13091l = o2Var;
    }
}
