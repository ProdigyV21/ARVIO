package io.github.jan.supabase.gotrue;

import d7.d;
import io.github.jan.supabase.gotrue.user.UserSession;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H¦@¢\u0006\u0004\b\u0005\u0010\u0006J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0002H¦@¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0004H¦@¢\u0006\u0004\b\t\u0010\b¨\u0006\n"}, d2 = {"Lio/github/jan/supabase/gotrue/SessionManager;", "", "Lio/github/jan/supabase/gotrue/user/UserSession;", SettingsSessionManager.SETTINGS_KEY, "Lx6/t0;", "saveSession", "(Lio/github/jan/supabase/gotrue/user/UserSession;Ld7/d;)Ljava/lang/Object;", "loadSession", "(Ld7/d;)Ljava/lang/Object;", "deleteSession", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface SessionManager {
    Object deleteSession(d<? super t0> dVar);

    Object loadSession(d<? super UserSession> dVar);

    Object saveSession(UserSession userSession, d<? super t0> dVar);
}
