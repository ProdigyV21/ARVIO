package o3;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/* JADX INFO: loaded from: classes4.dex */
public final class l extends androidx.loader.content.j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f20754b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ e f20755c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(e eVar, Context context) {
        super(Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper(), 3);
        this.f20755c = eVar;
        this.f20754b = context.getApplicationContext();
    }

    @Override // androidx.loader.content.j, android.os.Handler
    public final void handleMessage(Message message) {
        int i10 = message.what;
        if (i10 != 1) {
            StringBuilder sb2 = new StringBuilder(String.valueOf(i10).length() + 39);
            sb2.append("Don't know how to handle this message: ");
            sb2.append(i10);
            Log.w("GoogleApiAvailability", sb2.toString());
            return;
        }
        int i11 = f.f20742a;
        e eVar = this.f20755c;
        Context context = this.f20754b;
        int iB = eVar.b(context, i11);
        int i12 = g.f20748e;
        if (iB == 1 || iB == 2 || iB == 3 || iB == 9) {
            Intent intentA = eVar.a(context, iB, "n");
            eVar.f(context, iB, intentA == null ? null : PendingIntent.getActivity(context, 0, intentA, 201326592));
        }
    }
}
