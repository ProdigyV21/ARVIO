package m4;

import android.os.Parcel;
import com.google.android.gms.auth.api.identity.AuthorizationResult;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class c0 extends m implements t {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ com.google.android.gms.tasks.i f20226c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c0(com.google.android.gms.tasks.i iVar) {
        super("com.google.android.gms.auth.api.identity.internal.IAuthorizationCallback");
        this.f20226c = iVar;
    }

    @Override // m4.m
    public final boolean zba(int i10, Parcel parcel, Parcel parcel2, int i11) {
        if (i10 != 1) {
            return false;
        }
        Status status = (Status) n.a(parcel, Status.CREATOR);
        AuthorizationResult authorizationResult = (AuthorizationResult) n.a(parcel, AuthorizationResult.CREATOR);
        n.b(parcel);
        boolean zD = status.d();
        com.google.android.gms.tasks.i iVar = this.f20226c;
        if (zD) {
            iVar.b(authorizationResult);
        } else {
            iVar.a(com.google.android.gms.common.internal.t.m(status));
        }
        return true;
    }
}
