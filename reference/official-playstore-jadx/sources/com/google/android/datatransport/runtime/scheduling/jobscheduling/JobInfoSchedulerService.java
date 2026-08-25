package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Base64;
import com.google.android.datatransport.runtime.i;
import com.google.android.datatransport.runtime.w;
import io.sentry.android.ndk.b;
import k3.e;
import k3.f;
import n3.a;
import z2.c;

/* JADX INFO: loaded from: classes4.dex */
public class JobInfoSchedulerService extends JobService {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final /* synthetic */ int f12608i = 0;

    @Override // android.app.job.JobService
    public final boolean onStartJob(JobParameters jobParameters) {
        String string = jobParameters.getExtras().getString("backendName");
        String string2 = jobParameters.getExtras().getString("extras");
        int i10 = jobParameters.getExtras().getInt("priority");
        int i11 = jobParameters.getExtras().getInt("attemptNumber");
        w.b(getApplicationContext());
        if (string == null) {
            throw new NullPointerException("Null backendName");
        }
        c cVarB = a.b(i10);
        byte[] bArrDecode = string2 != null ? Base64.decode(string2, 0) : null;
        f fVar = w.a().f12658d;
        fVar.f19492e.execute(new e(fVar, new i(string, bArrDecode, cVarB), i11, new b(this, jobParameters, 13)));
        return true;
    }

    @Override // android.app.job.JobService
    public final boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}
