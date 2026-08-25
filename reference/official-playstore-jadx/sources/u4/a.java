package u4;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.api.internal.v0;
import com.google.android.gms.common.api.o;
import com.google.android.gms.common.api.p;
import com.google.android.gms.common.internal.h;
import com.google.android.gms.common.internal.i0;
import com.google.android.gms.common.internal.k;
import com.google.android.gms.common.internal.t;
import com.google.android.gms.common.internal.u;

/* JADX INFO: loaded from: classes4.dex */
public final class a extends k implements t4.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f22044a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final h f22045b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Bundle f22046c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Integer f22047d;

    public a(Context context, Looper looper, h hVar, Bundle bundle, o oVar, p pVar) {
        super(context, looper, 44, hVar, oVar, pVar);
        this.f22044a = true;
        this.f22045b = hVar;
        this.f22046c = bundle;
        this.f22047d = hVar.f12852h;
    }

    @Override // t4.c
    public final void a() {
        connect(new u(this));
    }

    @Override // t4.c
    public final void b(v0 v0Var) {
        try {
            Account account = this.f22045b.f12845a;
            if (account == null) {
                account = new Account(com.google.android.gms.common.internal.f.DEFAULT_ACCOUNT, "com.google");
            }
            GoogleSignInAccount savedDefaultGoogleSignInAccount = com.google.android.gms.common.internal.f.DEFAULT_ACCOUNT.equals(account.name) ? Storage.getInstance(getContext()).getSavedDefaultGoogleSignInAccount() : null;
            Integer num = this.f22047d;
            t.i(num);
            i0 i0Var = new i0(2, account, num.intValue(), savedDefaultGoogleSignInAccount);
            e eVar = (e) getService();
            eVar.getClass();
            Parcel parcelObtain = Parcel.obtain();
            parcelObtain.writeInterfaceToken(eVar.f20769e);
            int i10 = o4.c.f20770a;
            parcelObtain.writeInt(1);
            int iM0 = a.a.m0(20293, parcelObtain);
            a.a.l0(parcelObtain, 1, 4);
            parcelObtain.writeInt(1);
            a.a.f0(parcelObtain, 2, i0Var, 0, false);
            a.a.n0(iM0, parcelObtain);
            parcelObtain.writeStrongBinder(v0Var.asBinder());
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                eVar.f20768d.transact(12, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
            } finally {
                parcelObtain.recycle();
                parcelObtain2.recycle();
            }
        } catch (RemoteException e5) {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try {
                v0Var.f0(new g(1, new o3.b(8, null, null), null));
            } catch (RemoteException unused) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", e5);
            }
        }
    }

    @Override // com.google.android.gms.common.internal.f
    public final IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
        return iInterfaceQueryLocalInterface instanceof e ? (e) iInterfaceQueryLocalInterface : new e(iBinder, "com.google.android.gms.signin.internal.ISignInService", 0);
    }

    @Override // com.google.android.gms.common.internal.f
    public final Bundle getGetServiceRequestExtraArgs() {
        h hVar = this.f22045b;
        boolean zEquals = getContext().getPackageName().equals(hVar.f12849e);
        Bundle bundle = this.f22046c;
        if (!zEquals) {
            bundle.putString("com.google.android.gms.signin.internal.realClientPackageName", hVar.f12849e);
        }
        return bundle;
    }

    @Override // com.google.android.gms.common.internal.f, com.google.android.gms.common.api.g
    public final int getMinApkVersion() {
        return 12451000;
    }

    @Override // com.google.android.gms.common.internal.f
    public final String getServiceDescriptor() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    @Override // com.google.android.gms.common.internal.f
    public final String getStartServiceAction() {
        return "com.google.android.gms.signin.service.START";
    }

    @Override // com.google.android.gms.common.internal.f, com.google.android.gms.common.api.g
    public final boolean requiresSignIn() {
        return this.f22044a;
    }
}
