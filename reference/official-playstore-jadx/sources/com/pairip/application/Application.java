package com.pairip.application;

import android.content.Context;
import com.arflix.tv.ArflixApplication;
import com.pairip.licensecheck.LicenseClient;

/* JADX INFO: loaded from: classes.dex */
public class Application extends ArflixApplication {
    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        LicenseClient.checkLicense(context);
        super.attachBaseContext(context);
    }
}
