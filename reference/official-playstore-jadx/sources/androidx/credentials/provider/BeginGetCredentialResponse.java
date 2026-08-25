package androidx.credentials.provider;

import android.os.Build;
import android.os.Bundle;
import androidx.credentials.provider.utils.BeginGetCredentialUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import q7.n;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 \u00142\u00020\u0001:\u0003\u0012\u0013\u0014BA\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Landroidx/credentials/provider/BeginGetCredentialResponse;", "", "credentialEntries", "", "Landroidx/credentials/provider/CredentialEntry;", "actions", "Landroidx/credentials/provider/Action;", "authenticationActions", "Landroidx/credentials/provider/AuthenticationAction;", "remoteEntry", "Landroidx/credentials/provider/RemoteEntry;", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Landroidx/credentials/provider/RemoteEntry;)V", "getActions", "()Ljava/util/List;", "getAuthenticationActions", "getCredentialEntries", "getRemoteEntry", "()Landroidx/credentials/provider/RemoteEntry;", "Api34Impl", "Builder", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BeginGetCredentialResponse {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final List<Action> actions;
    private final List<AuthenticationAction> authenticationActions;
    private final List<CredentialEntry> credentialEntries;
    private final RemoteEntry remoteEntry;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\t\u0010\nJ\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroidx/credentials/provider/BeginGetCredentialResponse$Api34Impl;", "", "<init>", "()V", "Landroid/os/Bundle;", "bundle", "Landroidx/credentials/provider/BeginGetCredentialResponse;", "response", "Lx6/t0;", "asBundle", "(Landroid/os/Bundle;Landroidx/credentials/provider/BeginGetCredentialResponse;)V", "fromBundle", "(Landroid/os/Bundle;)Landroidx/credentials/provider/BeginGetCredentialResponse;", "", "REQUEST_KEY", "Ljava/lang/String;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Api34Impl {
        public static final Api34Impl INSTANCE = new Api34Impl();
        private static final String REQUEST_KEY = "androidx.credentials.provider.BeginGetCredentialResponse";

        private Api34Impl() {
        }

        @n
        public static final void asBundle(Bundle bundle, BeginGetCredentialResponse response) {
            bundle.putParcelable(REQUEST_KEY, BeginGetCredentialUtil.INSTANCE.convertToFrameworkResponse(response));
        }

        @n
        public static final BeginGetCredentialResponse fromBundle(Bundle bundle) {
            android.service.credentials.BeginGetCredentialResponse beginGetCredentialResponse = (android.service.credentials.BeginGetCredentialResponse) bundle.getParcelable(REQUEST_KEY, android.service.credentials.BeginGetCredentialResponse.class);
            if (beginGetCredentialResponse != null) {
                return BeginGetCredentialUtil.INSTANCE.convertToJetpackResponse(beginGetCredentialResponse);
            }
            return null;
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u0005J\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0007J\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\tJ\u0006\u0010\u0012\u001a\u00020\u0013J\u0014\u0010\u0014\u001a\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0015J\u0014\u0010\u0016\u001a\u00020\u00002\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\u0015J\u0014\u0010\u0018\u001a\u00020\u00002\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\t0\u0015J\u0010\u0010\u001a\u001a\u00020\u00002\b\u0010\n\u001a\u0004\u0018\u00010\u000bR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Landroidx/credentials/provider/BeginGetCredentialResponse$Builder;", "", "()V", "actions", "", "Landroidx/credentials/provider/Action;", "authenticationActions", "Landroidx/credentials/provider/AuthenticationAction;", "credentialEntries", "Landroidx/credentials/provider/CredentialEntry;", "remoteEntry", "Landroidx/credentials/provider/RemoteEntry;", "addAction", "action", "addAuthenticationAction", "authenticationAction", "addCredentialEntry", "entry", "build", "Landroidx/credentials/provider/BeginGetCredentialResponse;", "setActions", "", "setAuthenticationActions", "authenticationEntries", "setCredentialEntries", "entries", "setRemoteEntry", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Builder {
        private RemoteEntry remoteEntry;
        private List<CredentialEntry> credentialEntries = new ArrayList();
        private List<Action> actions = new ArrayList();
        private List<AuthenticationAction> authenticationActions = new ArrayList();

        public final Builder addAction(Action action) {
            this.actions.add(action);
            return this;
        }

        public final Builder addAuthenticationAction(AuthenticationAction authenticationAction) {
            this.authenticationActions.add(authenticationAction);
            return this;
        }

        public final Builder addCredentialEntry(CredentialEntry entry) {
            this.credentialEntries.add(entry);
            return this;
        }

        public final BeginGetCredentialResponse build() {
            return new BeginGetCredentialResponse(x.c1(this.credentialEntries), x.c1(this.actions), x.c1(this.authenticationActions), this.remoteEntry);
        }

        public final Builder setActions(List<Action> actions) {
            this.actions = new ArrayList(actions);
            return this;
        }

        public final Builder setAuthenticationActions(List<AuthenticationAction> authenticationEntries) {
            this.authenticationActions = new ArrayList(authenticationEntries);
            return this;
        }

        public final Builder setCredentialEntries(List<? extends CredentialEntry> entries) {
            this.credentialEntries = new ArrayList(entries);
            return this;
        }

        public final Builder setRemoteEntry(RemoteEntry remoteEntry) {
            this.remoteEntry = remoteEntry;
            return this;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\u0004H\u0007¨\u0006\t"}, d2 = {"Landroidx/credentials/provider/BeginGetCredentialResponse$Companion;", "", "()V", "asBundle", "Landroid/os/Bundle;", "response", "Landroidx/credentials/provider/BeginGetCredentialResponse;", "fromBundle", "bundle", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @n
        public final Bundle asBundle(BeginGetCredentialResponse response) {
            Bundle bundle = new Bundle();
            if (Build.VERSION.SDK_INT >= 34) {
                Api34Impl.asBundle(bundle, response);
            }
            return bundle;
        }

        @n
        public final BeginGetCredentialResponse fromBundle(Bundle bundle) {
            if (Build.VERSION.SDK_INT >= 34) {
                return Api34Impl.fromBundle(bundle);
            }
            return null;
        }

        private Companion() {
        }
    }

    public BeginGetCredentialResponse() {
        this(null, null, null, null, 15, null);
    }

    @n
    public static final Bundle asBundle(BeginGetCredentialResponse beginGetCredentialResponse) {
        return INSTANCE.asBundle(beginGetCredentialResponse);
    }

    @n
    public static final BeginGetCredentialResponse fromBundle(Bundle bundle) {
        return INSTANCE.fromBundle(bundle);
    }

    public final List<Action> getActions() {
        return this.actions;
    }

    public final List<AuthenticationAction> getAuthenticationActions() {
        return this.authenticationActions;
    }

    public final List<CredentialEntry> getCredentialEntries() {
        return this.credentialEntries;
    }

    public final RemoteEntry getRemoteEntry() {
        return this.remoteEntry;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BeginGetCredentialResponse(List<? extends CredentialEntry> list, List<Action> list2, List<AuthenticationAction> list3, RemoteEntry remoteEntry) {
        this.credentialEntries = list;
        this.actions = list2;
        this.authenticationActions = list3;
        this.remoteEntry = remoteEntry;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ BeginGetCredentialResponse(List list, List list2, List list3, RemoteEntry remoteEntry, int i10, h hVar) {
        int i11 = i10 & 1;
        z zVar = z.f19728i;
        this(i11 != 0 ? zVar : list, (i10 & 2) != 0 ? zVar : list2, (i10 & 4) != 0 ? zVar : list3, (i10 & 8) != 0 ? null : remoteEntry);
    }
}
