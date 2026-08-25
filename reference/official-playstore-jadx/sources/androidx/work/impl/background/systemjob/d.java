package androidx.work.impl.background.systemjob;

import android.app.job.JobParameters;
import android.net.Network;

/* JADX INFO: loaded from: classes3.dex */
public abstract class d {
    public static Network a(JobParameters jobParameters) {
        return jobParameters.getNetwork();
    }
}
