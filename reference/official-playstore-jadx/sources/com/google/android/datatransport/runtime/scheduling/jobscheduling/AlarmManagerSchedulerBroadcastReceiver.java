package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import com.google.android.datatransport.runtime.i;
import com.google.android.datatransport.runtime.w;
import k3.e;
import k3.f;
import n3.a;
import z2.c;

/* JADX INFO: loaded from: classes4.dex */
public class AlarmManagerSchedulerBroadcastReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f12607a = 0;

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        String queryParameter = intent.getData().getQueryParameter("backendName");
        String queryParameter2 = intent.getData().getQueryParameter("extras");
        int iIntValue = Integer.valueOf(intent.getData().getQueryParameter("priority")).intValue();
        int i10 = intent.getExtras().getInt("attemptNumber");
        w.b(context);
        if (queryParameter == null) {
            throw new NullPointerException("Null backendName");
        }
        c cVarB = a.b(iIntValue);
        byte[] bArrDecode = queryParameter2 != null ? Base64.decode(queryParameter2, 0) : null;
        f fVar = w.a().f12658d;
        fVar.f19492e.execute(new e(fVar, new i(queryParameter, bArrDecode, cVarB), i10, new com.discord.org.webrtc.i(6)));
    }
}
