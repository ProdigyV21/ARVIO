package m4;

import android.os.Parcel;
import com.google.android.gms.auth.api.identity.SavePasswordResult;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class d extends m implements a0 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ com.google.android.gms.tasks.i f20227c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(com.google.android.gms.tasks.i iVar) {
        super("com.google.android.gms.auth.api.identity.internal.ISavePasswordCallback");
        this.f20227c = iVar;
    }

    @Override // m4.m
    public final boolean zba(int i10, Parcel parcel, Parcel parcel2, int i11) {
        if (i10 != 1) {
            return false;
        }
        Status status = (Status) n.a(parcel, Status.CREATOR);
        SavePasswordResult savePasswordResult = (SavePasswordResult) n.a(parcel, SavePasswordResult.CREATOR);
        n.b(parcel);
        qb.d.S(status, savePasswordResult, this.f20227c);
        return true;
    }
}
