package io.github.jan.supabase.gotrue;

import android.content.Context;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.lifecycle.a0;
import androidx.lifecycle.e;
import androidx.lifecycle.y;
import io.github.jan.supabase.annotations.SupabaseInternal;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import q2.f;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u000f\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0001\u0010\u0002\u001a\u0013\u0010\u0005\u001a\u00020\u0004*\u00020\u0003H\u0007¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u0017\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\b\u0010\u0006\"\u0018\u0010\t\u001a\u0004\u0018\u00010\u00008\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroid/content/Context;", "applicationContext", "()Landroid/content/Context;", "Lio/github/jan/supabase/gotrue/Auth;", "Lx6/t0;", "setupPlatform", "(Lio/github/jan/supabase/gotrue/Auth;)V", "gotrue", "addLifecycleCallbacks", "appContext", "Landroid/content/Context;", "gotrue-kt_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class SetupPlatformKt {
    private static Context appContext;

    private static final void addLifecycleCallbacks(final Auth auth) {
        if (auth.getConfig().getEnableLifecycleCallbacks()) {
            ProcessLifecycleOwner processLifecycleOwner = ProcessLifecycleOwner.f3185s;
            a0 a0Var = ProcessLifecycleOwner.f3185s.f3191p;
            final k0 authScope = ((AuthImpl) auth).getAuthScope();
            a0Var.addObserver(new DefaultLifecycleObserver() { // from class: io.github.jan.supabase.gotrue.SetupPlatformKt.addLifecycleCallbacks.1
                @Override // androidx.lifecycle.DefaultLifecycleObserver
                public final /* synthetic */ void onCreate(y yVar) {
                    e.a(this, yVar);
                }

                @Override // androidx.lifecycle.DefaultLifecycleObserver
                public final /* synthetic */ void onDestroy(y yVar) {
                    e.b(this, yVar);
                }

                @Override // androidx.lifecycle.DefaultLifecycleObserver
                public final /* synthetic */ void onPause(y yVar) {
                    e.c(this, yVar);
                }

                @Override // androidx.lifecycle.DefaultLifecycleObserver
                public final /* synthetic */ void onResume(y yVar) {
                    e.d(this, yVar);
                }

                @Override // androidx.lifecycle.DefaultLifecycleObserver
                public void onStart(y owner) {
                    if (auth.isAutoRefreshRunning() || !auth.getConfig().getAlwaysAutoRefresh()) {
                        return;
                    }
                    f.A0(f.f21327l, "Auth", SetupPlatformKt$addLifecycleCallbacks$1$onStart$1.INSTANCE);
                    m0.p(authScope, null, 0, new SetupPlatformKt$addLifecycleCallbacks$1$onStart$2(auth, null), 3);
                }

                @Override // androidx.lifecycle.DefaultLifecycleObserver
                public void onStop(y owner) {
                    if (auth.isAutoRefreshRunning()) {
                        f.A0(f.f21327l, "Auth", SetupPlatformKt$addLifecycleCallbacks$1$onStop$1.INSTANCE);
                        m0.p(authScope, null, 0, new SetupPlatformKt$addLifecycleCallbacks$1$onStop$2(auth, null), 3);
                    }
                }
            });
        }
    }

    public static final Context applicationContext() {
        Context context = appContext;
        if (context != null) {
            return context;
        }
        throw new IllegalStateException("Application context not initialized");
    }

    @SupabaseInternal
    public static final void setupPlatform(Auth auth) {
        addLifecycleCallbacks(auth);
    }
}
