package d5;

import com.google.firebase.crashlytics.internal.persistence.CrashlyticsReportPersistence;
import java.io.File;
import java.io.FilenameFilter;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class a implements FilenameFilter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f14682a;

    public /* synthetic */ a(int i10) {
        this.f14682a = i10;
    }

    @Override // java.io.FilenameFilter
    public final boolean accept(File file, String str) {
        switch (this.f14682a) {
            case 0:
                return CrashlyticsReportPersistence.lambda$static$1(file, str);
            case 1:
                return CrashlyticsReportPersistence.isNormalPriorityEventFile(file, str);
            default:
                return str.endsWith(".envelope");
        }
    }
}
