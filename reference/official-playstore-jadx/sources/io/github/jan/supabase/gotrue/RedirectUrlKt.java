package io.github.jan.supabase.gotrue;

import androidx.compose.foundation.c;
import io.github.jan.supabase.annotations.SupabaseInternal;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0007\u001a\u0004\u0018\u00010\u0001*\u00020\bH\u0007\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004¨\u0006\t"}, d2 = {"deepLink", "", "Lio/github/jan/supabase/gotrue/AuthConfig;", "getDeepLink", "(Lio/github/jan/supabase/gotrue/AuthConfig;)Ljava/lang/String;", "deepLinkOrNull", "getDeepLinkOrNull", "defaultRedirectUrl", "Lio/github/jan/supabase/gotrue/Auth;", "gotrue-kt_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class RedirectUrlKt {
    @SupabaseInternal
    public static final String defaultRedirectUrl(Auth auth) {
        return getDeepLinkOrNull(auth.getConfig());
    }

    public static final String getDeepLink(AuthConfig authConfig) {
        String scheme = authConfig.getScheme();
        if (scheme == null) {
            AuthExtensionsKt.noDeeplinkError("scheme");
            throw new KotlinNothingValueException();
        }
        String host = authConfig.getHost();
        if (host != null) {
            return c.t(scheme, "://", host);
        }
        AuthExtensionsKt.noDeeplinkError("host");
        throw new KotlinNothingValueException();
    }

    public static final String getDeepLinkOrNull(AuthConfig authConfig) {
        String host;
        String scheme = authConfig.getScheme();
        if (scheme == null || (host = authConfig.getHost()) == null) {
            return null;
        }
        return c.t(scheme, "://", host);
    }
}
