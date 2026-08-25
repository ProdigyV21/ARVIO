package com.google.android.gms.cast.framework.devicesuggestions;

import com.google.android.gms.common.api.e;
import com.google.android.gms.common.api.internal.a;
import com.google.android.gms.common.api.r;
import com.google.android.gms.tasks.h;

/* JADX INFO: loaded from: classes4.dex */
public interface DeviceSuggestionsClient extends r<e> {
    h clearClientData();

    /* synthetic */ a getApiKey();

    h registerCallback(DeviceSuggestionsCallback deviceSuggestionsCallback);

    h requestDeviceSuggestions();

    h unregisterCallback(DeviceSuggestionsCallback deviceSuggestionsCallback);
}
