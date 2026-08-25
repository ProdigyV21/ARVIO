package com.google.android.gms.common.internal;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiActivity;

/* JADX INFO: loaded from: classes4.dex */
public final class e0 implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f12835i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Intent f12836l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f12837m;

    public /* synthetic */ e0(Intent intent, Object obj, int i10) {
        this.f12835i = i10;
        this.f12836l = intent;
        this.f12837m = obj;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.google.android.gms.common.api.internal.k, java.lang.Object] */
    public final void a() {
        switch (this.f12835i) {
            case 0:
                Intent intent = this.f12836l;
                if (intent != null) {
                    ((GoogleApiActivity) this.f12837m).startActivityForResult(intent, 2);
                }
                break;
            default:
                Intent intent2 = this.f12836l;
                if (intent2 != null) {
                    this.f12837m.startActivityForResult(intent2, 2);
                }
                break;
        }
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i10) {
        try {
            try {
                a();
            } catch (ActivityNotFoundException e5) {
                Log.e("DialogRedirect", true == Build.FINGERPRINT.contains("generic") ? "Failed to start resolution intent. This may occur when resolving Google Play services connection issues on emulators with Google APIs but not Google Play Store." : "Failed to start resolution intent.", e5);
            }
        } finally {
            dialogInterface.dismiss();
        }
    }
}
