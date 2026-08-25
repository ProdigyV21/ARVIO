package androidx.work.impl.background.systemjob;

import android.app.job.JobParameters;
import android.net.Uri;

/* JADX INFO: loaded from: classes3.dex */
public abstract class c {
    public static String[] a(JobParameters jobParameters) {
        return jobParameters.getTriggeredContentAuthorities();
    }

    public static Uri[] b(JobParameters jobParameters) {
        return jobParameters.getTriggeredContentUris();
    }
}
