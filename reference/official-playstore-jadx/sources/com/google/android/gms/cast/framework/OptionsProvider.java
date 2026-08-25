package com.google.android.gms.cast.framework;

import android.content.Context;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface OptionsProvider {
    List<SessionProvider> getAdditionalSessionProviders(Context context);

    CastOptions getCastOptions(Context context);
}
