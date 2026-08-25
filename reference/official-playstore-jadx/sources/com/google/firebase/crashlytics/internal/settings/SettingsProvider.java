package com.google.firebase.crashlytics.internal.settings;

import com.google.android.gms.tasks.h;

/* JADX INFO: loaded from: classes4.dex */
public interface SettingsProvider {
    h getSettingsAsync();

    Settings getSettingsSync();
}
