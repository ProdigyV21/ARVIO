package com.google.android.gms.auth.api.signin;

import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import java.util.Comparator;

/* JADX INFO: loaded from: classes4.dex */
final /* synthetic */ class zaa implements Comparator {
    static final /* synthetic */ zaa zaa = new zaa();

    private /* synthetic */ zaa() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        Parcelable.Creator<GoogleSignInAccount> creator = GoogleSignInAccount.CREATOR;
        return ((Scope) obj).f12669l.compareTo(((Scope) obj2).f12669l);
    }
}
