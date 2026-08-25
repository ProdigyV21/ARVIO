package com.google.android.gms.cast.framework.devicesuggestions;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.d;
import com.google.android.gms.common.api.m;
import com.google.android.gms.internal.cast.a0;

/* JADX INFO: loaded from: classes4.dex */
public final class DeviceSuggestions {
    private DeviceSuggestions() {
    }

    public static DeviceSuggestionsClient getClient(Activity activity) {
        return new a0(activity, activity, a0.f13155a, d.f12682a, m.f12824c);
    }

    public static DeviceSuggestionsClient getClient(Context context) {
        return new a0(context, null, a0.f13155a, d.f12682a, m.f12824c);
    }
}
