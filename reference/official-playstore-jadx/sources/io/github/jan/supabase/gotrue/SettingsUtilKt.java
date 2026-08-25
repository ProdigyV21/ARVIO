package io.github.jan.supabase.gotrue;

import a8.e;
import android.content.Context;
import androidx.work.impl.t;
import h6.d;
import io.github.jan.supabase.annotations.SupabaseInternal;
import io.ktor.util.PlatformUtils;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u000f\u0010\u0001\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u0001\u0010\u0002\u001a\u0013\u0010\u0005\u001a\u00020\u0004*\u00020\u0003H\u0007¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u0013\u0010\b\u001a\u00020\u0007*\u00020\u0003H\u0007¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lh6/d;", "createDefaultSettings", "()Lh6/d;", "Lio/github/jan/supabase/gotrue/Auth;", "Lio/github/jan/supabase/gotrue/SessionManager;", "createDefaultSessionManager", "(Lio/github/jan/supabase/gotrue/Auth;)Lio/github/jan/supabase/gotrue/SessionManager;", "Lio/github/jan/supabase/gotrue/CodeVerifierCache;", "createDefaultCodeVerifierCache", "(Lio/github/jan/supabase/gotrue/Auth;)Lio/github/jan/supabase/gotrue/CodeVerifierCache;", "gotrue-kt_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class SettingsUtilKt {
    @SupabaseInternal
    public static final CodeVerifierCache createDefaultCodeVerifierCache(Auth auth) {
        return !PlatformUtils.INSTANCE.getIS_NODE() ? new SettingsCodeVerifierCache(null, 1, null) : new MemoryCodeVerifierCache();
    }

    @SupabaseInternal
    public static final SessionManager createDefaultSessionManager(Auth auth) {
        return !PlatformUtils.INSTANCE.getIS_NODE() ? new SettingsSessionManager(null, 1, null) : new MemorySessionManager(null, 1, null);
    }

    @SupabaseInternal
    public static final d createDefaultSettings() {
        try {
            Context context = t.f6985l;
            return new e(context.getSharedPreferences(context.getPackageName() + "_preferences", 0), 20);
        } catch (Exception unused) {
            throw new IllegalStateException("Failed to create default settings for SettingsSessionManager. You might have to provide a custom settings instance or a custom session manager. Learn more at https://github.com/supabase-community/supabase-kt/wiki/Session-Saving");
        }
    }
}
