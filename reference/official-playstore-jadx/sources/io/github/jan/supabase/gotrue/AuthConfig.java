package io.github.jan.supabase.gotrue;

import io.github.jan.supabase.plugins.CustomSerializationConfig;
import io.github.jan.supabase.plugins.MainConfig;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016¨\u0006\u001a"}, d2 = {"Lio/github/jan/supabase/gotrue/AuthConfig;", "Lio/github/jan/supabase/plugins/MainConfig;", "Lio/github/jan/supabase/plugins/CustomSerializationConfig;", "Lio/github/jan/supabase/gotrue/AuthConfigDefaults;", "()V", "defaultExternalAuthAction", "Lio/github/jan/supabase/gotrue/ExternalAuthAction;", "getDefaultExternalAuthAction", "()Lio/github/jan/supabase/gotrue/ExternalAuthAction;", "setDefaultExternalAuthAction", "(Lio/github/jan/supabase/gotrue/ExternalAuthAction;)V", "enableLifecycleCallbacks", "", "getEnableLifecycleCallbacks", "()Z", "setEnableLifecycleCallbacks", "(Z)V", "host", "", "getHost", "()Ljava/lang/String;", "setHost", "(Ljava/lang/String;)V", "scheme", "getScheme", "setScheme", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class AuthConfig extends AuthConfigDefaults implements MainConfig, CustomSerializationConfig {
    private String host;
    private String scheme;
    private boolean enableLifecycleCallbacks = true;
    private ExternalAuthAction defaultExternalAuthAction = ExternalAuthAction.EXTERNAL_BROWSER;

    public final ExternalAuthAction getDefaultExternalAuthAction() {
        return this.defaultExternalAuthAction;
    }

    public final boolean getEnableLifecycleCallbacks() {
        return this.enableLifecycleCallbacks;
    }

    public final String getHost() {
        return this.host;
    }

    public final String getScheme() {
        return this.scheme;
    }

    public final void setDefaultExternalAuthAction(ExternalAuthAction externalAuthAction) {
        this.defaultExternalAuthAction = externalAuthAction;
    }

    public final void setEnableLifecycleCallbacks(boolean z) {
        this.enableLifecycleCallbacks = z;
    }

    public final void setHost(String str) {
        this.host = str;
    }

    public final void setScheme(String str) {
        this.scheme = str;
    }
}
