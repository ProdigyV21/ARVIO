package io.github.jan.supabase.gotrue;

import io.github.jan.supabase.gotrue.user.UserSession;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import q7.g;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u0082\u0001\u0004\u0006\u0007\b\t¨\u0006\n"}, d2 = {"Lio/github/jan/supabase/gotrue/SessionStatus;", "", "Authenticated", "LoadingFromStorage", "NetworkError", "NotAuthenticated", "Lio/github/jan/supabase/gotrue/SessionStatus$Authenticated;", "Lio/github/jan/supabase/gotrue/SessionStatus$LoadingFromStorage;", "Lio/github/jan/supabase/gotrue/SessionStatus$NetworkError;", "Lio/github/jan/supabase/gotrue/SessionStatus$NotAuthenticated;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface SessionStatus {

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0087@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0003¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002¨\u0006\u0016"}, d2 = {"Lio/github/jan/supabase/gotrue/SessionStatus$Authenticated;", "Lio/github/jan/supabase/gotrue/SessionStatus;", SettingsSessionManager.SETTINGS_KEY, "Lio/github/jan/supabase/gotrue/user/UserSession;", "constructor-impl", "(Lio/github/jan/supabase/gotrue/user/UserSession;)Lio/github/jan/supabase/gotrue/user/UserSession;", "getSession", "()Lio/github/jan/supabase/gotrue/user/UserSession;", "equals", "", "other", "", "equals-impl", "(Lio/github/jan/supabase/gotrue/user/UserSession;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Lio/github/jan/supabase/gotrue/user/UserSession;)I", "toString", "", "toString-impl", "(Lio/github/jan/supabase/gotrue/user/UserSession;)Ljava/lang/String;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @g
    public static final class Authenticated implements SessionStatus {
        private final UserSession session;

        private /* synthetic */ Authenticated(UserSession userSession) {
            this.session = userSession;
        }

        /* JADX INFO: renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ Authenticated m6634boximpl(UserSession userSession) {
            return new Authenticated(userSession);
        }

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        public static UserSession m6635constructorimpl(UserSession userSession) {
            return userSession;
        }

        /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
        public static boolean m6636equalsimpl(UserSession userSession, Object obj) {
            return (obj instanceof Authenticated) && p.a(userSession, ((Authenticated) obj).m6640unboximpl());
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m6637equalsimpl0(UserSession userSession, UserSession userSession2) {
            return p.a(userSession, userSession2);
        }

        /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
        public static int m6638hashCodeimpl(UserSession userSession) {
            return userSession.hashCode();
        }

        /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
        public static String m6639toStringimpl(UserSession userSession) {
            return "Authenticated(session=" + userSession + ')';
        }

        public boolean equals(Object obj) {
            return m6636equalsimpl(this.session, obj);
        }

        public final UserSession getSession() {
            return this.session;
        }

        public int hashCode() {
            return m6638hashCodeimpl(this.session);
        }

        public String toString() {
            return m6639toStringimpl(this.session);
        }

        /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
        public final /* synthetic */ UserSession m6640unboximpl() {
            return this.session;
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÖ\u0003J\t\u0010\u0007\u001a\u00020\bHÖ\u0001J\t\u0010\t\u001a\u00020\nHÖ\u0001¨\u0006\u000b"}, d2 = {"Lio/github/jan/supabase/gotrue/SessionStatus$LoadingFromStorage;", "Lio/github/jan/supabase/gotrue/SessionStatus;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class LoadingFromStorage implements SessionStatus {
        public static final LoadingFromStorage INSTANCE = new LoadingFromStorage();

        private LoadingFromStorage() {
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof LoadingFromStorage);
        }

        public int hashCode() {
            return -751717533;
        }

        public String toString() {
            return "LoadingFromStorage";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÖ\u0003J\t\u0010\u0007\u001a\u00020\bHÖ\u0001J\t\u0010\t\u001a\u00020\nHÖ\u0001¨\u0006\u000b"}, d2 = {"Lio/github/jan/supabase/gotrue/SessionStatus$NetworkError;", "Lio/github/jan/supabase/gotrue/SessionStatus;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class NetworkError implements SessionStatus {
        public static final NetworkError INSTANCE = new NetworkError();

        private NetworkError() {
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof NetworkError);
        }

        public int hashCode() {
            return 1151566280;
        }

        public String toString() {
            return "NetworkError";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÖ\u0003J\t\u0010\u0007\u001a\u00020\bHÖ\u0001J\t\u0010\t\u001a\u00020\nHÖ\u0001¨\u0006\u000b"}, d2 = {"Lio/github/jan/supabase/gotrue/SessionStatus$NotAuthenticated;", "Lio/github/jan/supabase/gotrue/SessionStatus;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class NotAuthenticated implements SessionStatus {
        public static final NotAuthenticated INSTANCE = new NotAuthenticated();

        private NotAuthenticated() {
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof NotAuthenticated);
        }

        public int hashCode() {
            return 1442193002;
        }

        public String toString() {
            return "NotAuthenticated";
        }
    }
}
