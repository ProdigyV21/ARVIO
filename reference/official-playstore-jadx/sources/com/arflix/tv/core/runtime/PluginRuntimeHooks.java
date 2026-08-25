package com.arflix.tv.core.runtime;

import android.app.Activity;
import android.app.Application;
import androidx.media3.common.MimeTypes;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\u0006¢\u0006\u0004\b\r\u0010\u0003¨\u0006\u000e"}, d2 = {"Lcom/arflix/tv/core/runtime/PluginRuntimeHooks;", "", "<init>", "()V", "Landroid/app/Application;", MimeTypes.BASE_TYPE_APPLICATION, "Lx6/t0;", "onApplicationCreate", "(Landroid/app/Application;)V", "Landroid/app/Activity;", "activity", "onActivityCreate", "(Landroid/app/Activity;)V", "onActivityDestroy", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PluginRuntimeHooks {
    public static final int $stable = 0;
    public static final PluginRuntimeHooks INSTANCE = new PluginRuntimeHooks();

    private PluginRuntimeHooks() {
    }

    public final void onActivityCreate(Activity activity) {
    }

    public final void onActivityDestroy() {
    }

    public final void onApplicationCreate(Application application) {
    }
}
