package m4;

import android.app.PendingIntent;
import android.os.Parcel;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class g extends m implements y {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ com.google.android.gms.tasks.i f20231c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(com.google.android.gms.tasks.i iVar) {
        super("com.google.android.gms.auth.api.identity.internal.IGetSignInIntentCallback");
        this.f20231c = iVar;
    }

    @Override // m4.m
    public final boolean zba(int i10, Parcel parcel, Parcel parcel2, int i11) {
        if (i10 != 1) {
            return false;
        }
        Status status = (Status) n.a(parcel, Status.CREATOR);
        PendingIntent pendingIntent = (PendingIntent) n.a(parcel, PendingIntent.CREATOR);
        n.b(parcel);
        qb.d.S(status, pendingIntent, this.f20231c);
        return true;
    }
}
