package m4;

import android.os.Parcel;
import com.google.android.gms.auth.api.identity.SaveAccountLinkingTokenResult;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class c extends m implements z {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ com.google.android.gms.tasks.i f20225c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(com.google.android.gms.tasks.i iVar) {
        super("com.google.android.gms.auth.api.identity.internal.ISaveAccountLinkingTokenCallback");
        this.f20225c = iVar;
    }

    @Override // m4.m
    public final boolean zba(int i10, Parcel parcel, Parcel parcel2, int i11) {
        if (i10 != 1) {
            return false;
        }
        Status status = (Status) n.a(parcel, Status.CREATOR);
        SaveAccountLinkingTokenResult saveAccountLinkingTokenResult = (SaveAccountLinkingTokenResult) n.a(parcel, SaveAccountLinkingTokenResult.CREATOR);
        n.b(parcel);
        boolean zD = status.d();
        com.google.android.gms.tasks.i iVar = this.f20225c;
        if (zD) {
            iVar.b(saveAccountLinkingTokenResult);
        } else {
            iVar.a(com.google.android.gms.common.internal.t.m(status));
        }
        return true;
    }
}
