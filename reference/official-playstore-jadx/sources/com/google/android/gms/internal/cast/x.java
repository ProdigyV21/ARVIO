package com.google.android.gms.internal.cast;

import android.os.Parcel;
import com.google.android.gms.cast.framework.devicesuggestions.DeviceSuggestionResult;

/* JADX INFO: loaded from: classes4.dex */
public final class x extends e0 implements u {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ com.google.android.gms.common.api.internal.n f13671c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(a0 a0Var, com.google.android.gms.common.api.internal.n nVar) {
        super("com.google.android.gms.cast.framework.devicesuggestions.internal.IDeviceSuggestionsCallback");
        this.f13671c = nVar;
    }

    @Override // com.google.android.gms.internal.cast.e0
    public final boolean zza(int i10, Parcel parcel, Parcel parcel2, int i11) {
        com.google.android.gms.common.api.internal.n nVar = this.f13671c;
        if (i10 == 1) {
            DeviceSuggestionResult deviceSuggestionResult = (DeviceSuggestionResult) a1.b(parcel, DeviceSuggestionResult.CREATOR);
            a1.e(parcel);
            w wVar = new w(this, deviceSuggestionResult, 0);
            nVar.getClass();
            nVar.f12767a.execute(new com.google.common.util.concurrent.w0(nVar, wVar, 12));
            return true;
        }
        if (i10 != 2) {
            return false;
        }
        DeviceSuggestionResult deviceSuggestionResult2 = (DeviceSuggestionResult) a1.b(parcel, DeviceSuggestionResult.CREATOR);
        a1.e(parcel);
        w wVar2 = new w(this, deviceSuggestionResult2, 1);
        nVar.getClass();
        nVar.f12767a.execute(new com.google.common.util.concurrent.w0(nVar, wVar2, 12));
        return true;
    }
}
