package io.github.jan.supabase.gotrue;

import d7.d;
import io.github.jan.supabase.gotrue.user.UserSession;
import ja.a;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0096@¢\u0006\u0004\b\u0007\u0010\bJ\u0012\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0096@¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b\u000b\u0010\nR/\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u00028B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0005¨\u0006\u0012"}, d2 = {"Lio/github/jan/supabase/gotrue/MemorySessionManager;", "Lio/github/jan/supabase/gotrue/SessionManager;", "Lio/github/jan/supabase/gotrue/user/UserSession;", SettingsSessionManager.SETTINGS_KEY, "<init>", "(Lio/github/jan/supabase/gotrue/user/UserSession;)V", "Lx6/t0;", "saveSession", "(Lio/github/jan/supabase/gotrue/user/UserSession;Ld7/d;)Ljava/lang/Object;", "loadSession", "(Ld7/d;)Ljava/lang/Object;", "deleteSession", "<set-?>", "session$delegate", "Lja/a;", "getSession", "()Lio/github/jan/supabase/gotrue/user/UserSession;", "setSession", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class MemorySessionManager implements SessionManager {

    /* JADX INFO: renamed from: session$delegate, reason: from kotlin metadata */
    private final a session;

    /* JADX WARN: Multi-variable type inference failed */
    public MemorySessionManager() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    private final UserSession getSession() {
        return (UserSession) this.session.b();
    }

    private final void setSession(UserSession userSession) {
        this.session.c(userSession);
    }

    @Override // io.github.jan.supabase.gotrue.SessionManager
    public Object deleteSession(d<? super t0> dVar) {
        setSession(null);
        return t0.f22605a;
    }

    @Override // io.github.jan.supabase.gotrue.SessionManager
    public Object loadSession(d<? super UserSession> dVar) {
        return getSession();
    }

    @Override // io.github.jan.supabase.gotrue.SessionManager
    public Object saveSession(UserSession userSession, d<? super t0> dVar) {
        setSession(userSession);
        return t0.f22605a;
    }

    public MemorySessionManager(UserSession userSession) {
        this.session = new a(userSession);
    }

    public /* synthetic */ MemorySessionManager(UserSession userSession, int i10, h hVar) {
        this((i10 & 1) != 0 ? null : userSession);
    }
}
