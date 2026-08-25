package com.google.android.gms.internal.cast;

import android.os.Parcel;
import com.google.android.gms.common.api.Status;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public final class l3 extends e0 implements s3 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ com.google.android.gms.tasks.i f13407c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l3(com.google.android.gms.internal.auth.f0 f0Var, com.google.android.gms.tasks.i iVar) {
        super("com.google.android.gms.usagereporting.internal.IUsageReportingCallbacks");
        this.f13407c = iVar;
        Objects.requireNonNull(f0Var);
    }

    @Override // com.google.android.gms.internal.cast.s3
    public void F(Status status) {
        throw new IllegalStateException("Not implemented.");
    }

    @Override // com.google.android.gms.internal.cast.s3
    public final void q(Status status, r3 r3Var) {
        u3 u3Var = new u3(r3Var);
        m3 m3Var = new m3();
        m3Var.f13440a = u3Var;
        qb.d.S(status, m3Var, this.f13407c);
    }

    @Override // com.google.android.gms.internal.cast.e0
    public final boolean zza(int i10, Parcel parcel, Parcel parcel2, int i11) {
        switch (i10) {
            case 2:
                Status status = (Status) a1.b(parcel, Status.CREATOR);
                r3 r3Var = (r3) a1.b(parcel, r3.CREATOR);
                a1.e(parcel);
                q(status, r3Var);
                return true;
            case 3:
                a1.e(parcel);
                throw new IllegalStateException("Not implemented.");
            case 4:
                a1.e(parcel);
                throw new IllegalStateException("Not implemented.");
            case 5:
                Status status2 = (Status) a1.b(parcel, Status.CREATOR);
                a1.e(parcel);
                F(status2);
                return true;
            case 6:
                parcel.createStringArrayList();
                a1.e(parcel);
                throw new IllegalStateException("Not implemented.");
            case 7:
                a1.e(parcel);
                throw new IllegalStateException("Not implemented.");
            case 8:
                parcel.readInt();
                a1.e(parcel);
                throw new IllegalStateException("Not implemented.");
            case 9:
                a1.e(parcel);
                throw new IllegalStateException("Not implemented");
            case 10:
                a1.e(parcel);
                throw new IllegalStateException("Not implemented");
            case 11:
                a1.e(parcel);
                throw new IllegalStateException("Not implemented.");
            case 12:
                a1.e(parcel);
                throw new IllegalStateException("Not implemented.");
            case 13:
                a1.e(parcel);
                throw new IllegalStateException("Not implemented.");
            case 14:
                a1.e(parcel);
                throw new IllegalStateException("Not implemented.");
            case 15:
                a1.e(parcel);
                throw new IllegalStateException("Not implemented.");
            default:
                return false;
        }
    }
}
