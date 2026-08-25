package com.russhwolf.settings;

import android.content.Context;
import androidx.work.impl.t;
import f2.b;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/russhwolf/settings/SettingsInitializer;", "Lf2/b;", "Landroid/content/Context;", "<init>", "()V", "multiplatform-settings-no-arg_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SettingsInitializer implements b<Context> {
    @Override // f2.b
    public final Object create(Context context) {
        Context applicationContext = context.getApplicationContext();
        t.f6985l = applicationContext;
        return applicationContext;
    }

    @Override // f2.b
    public final List dependencies() {
        return z.f19728i;
    }
}
