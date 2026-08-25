package com.google.firebase.crashlytics.internal;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.io.File;

/* JADX INFO: loaded from: classes4.dex */
public interface NativeSessionFileProvider {
    File getAppFile();

    CrashlyticsReport.ApplicationExitInfo getApplicationExitInto();

    File getBinaryImagesFile();

    File getDeviceFile();

    File getMetadataFile();

    File getMinidumpFile();

    File getOsFile();

    File getSessionFile();
}
