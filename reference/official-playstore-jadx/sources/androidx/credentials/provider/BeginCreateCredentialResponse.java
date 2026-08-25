package androidx.credentials.provider;

import android.os.Build;
import android.os.Bundle;
import androidx.credentials.provider.utils.BeginCreateCredentialUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import q7.n;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u000e2\u00020\u0001:\u0003\f\r\u000eB!\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000f"}, d2 = {"Landroidx/credentials/provider/BeginCreateCredentialResponse;", "", "createEntries", "", "Landroidx/credentials/provider/CreateEntry;", "remoteEntry", "Landroidx/credentials/provider/RemoteEntry;", "(Ljava/util/List;Landroidx/credentials/provider/RemoteEntry;)V", "getCreateEntries", "()Ljava/util/List;", "getRemoteEntry", "()Landroidx/credentials/provider/RemoteEntry;", "Api34Impl", "Builder", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BeginCreateCredentialResponse {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final List<CreateEntry> createEntries;
    private final RemoteEntry remoteEntry;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\t\u0010\nJ\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroidx/credentials/provider/BeginCreateCredentialResponse$Api34Impl;", "", "<init>", "()V", "Landroid/os/Bundle;", "bundle", "Landroidx/credentials/provider/BeginCreateCredentialResponse;", "response", "Lx6/t0;", "asBundle", "(Landroid/os/Bundle;Landroidx/credentials/provider/BeginCreateCredentialResponse;)V", "fromBundle", "(Landroid/os/Bundle;)Landroidx/credentials/provider/BeginCreateCredentialResponse;", "", "REQUEST_KEY", "Ljava/lang/String;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Api34Impl {
        public static final Api34Impl INSTANCE = new Api34Impl();
        private static final String REQUEST_KEY = "androidx.credentials.provider.BeginCreateCredentialResponse";

        private Api34Impl() {
        }

        @n
        public static final void asBundle(Bundle bundle, BeginCreateCredentialResponse response) {
            bundle.putParcelable(REQUEST_KEY, BeginCreateCredentialUtil.INSTANCE.convertToFrameworkResponse(response));
        }

        @n
        public static final BeginCreateCredentialResponse fromBundle(Bundle bundle) {
            android.service.credentials.BeginCreateCredentialResponse beginCreateCredentialResponse = (android.service.credentials.BeginCreateCredentialResponse) bundle.getParcelable(REQUEST_KEY, android.service.credentials.BeginCreateCredentialResponse.class);
            if (beginCreateCredentialResponse != null) {
                return BeginCreateCredentialUtil.INSTANCE.convertToJetpackResponse(beginCreateCredentialResponse);
            }
            return null;
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0005J\u0006\u0010\n\u001a\u00020\u000bJ\u0014\u0010\f\u001a\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\rJ\u0010\u0010\u000e\u001a\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/credentials/provider/BeginCreateCredentialResponse$Builder;", "", "()V", "createEntries", "", "Landroidx/credentials/provider/CreateEntry;", "remoteEntry", "Landroidx/credentials/provider/RemoteEntry;", "addCreateEntry", "createEntry", "build", "Landroidx/credentials/provider/BeginCreateCredentialResponse;", "setCreateEntries", "", "setRemoteEntry", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Builder {
        private List<CreateEntry> createEntries = new ArrayList();
        private RemoteEntry remoteEntry;

        public final Builder addCreateEntry(CreateEntry createEntry) {
            this.createEntries.add(createEntry);
            return this;
        }

        public final BeginCreateCredentialResponse build() {
            return new BeginCreateCredentialResponse(x.c1(this.createEntries), this.remoteEntry);
        }

        public final Builder setCreateEntries(List<CreateEntry> createEntries) {
            this.createEntries = new ArrayList(createEntries);
            return this;
        }

        public final Builder setRemoteEntry(RemoteEntry remoteEntry) {
            this.remoteEntry = remoteEntry;
            return this;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\u0004H\u0007¨\u0006\t"}, d2 = {"Landroidx/credentials/provider/BeginCreateCredentialResponse$Companion;", "", "()V", "asBundle", "Landroid/os/Bundle;", "response", "Landroidx/credentials/provider/BeginCreateCredentialResponse;", "fromBundle", "bundle", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @n
        public final Bundle asBundle(BeginCreateCredentialResponse response) {
            Bundle bundle = new Bundle();
            if (Build.VERSION.SDK_INT >= 34) {
                Api34Impl.asBundle(bundle, response);
            }
            return bundle;
        }

        @n
        public final BeginCreateCredentialResponse fromBundle(Bundle bundle) {
            if (Build.VERSION.SDK_INT >= 34) {
                return Api34Impl.fromBundle(bundle);
            }
            return null;
        }

        private Companion() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BeginCreateCredentialResponse() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @n
    public static final Bundle asBundle(BeginCreateCredentialResponse beginCreateCredentialResponse) {
        return INSTANCE.asBundle(beginCreateCredentialResponse);
    }

    @n
    public static final BeginCreateCredentialResponse fromBundle(Bundle bundle) {
        return INSTANCE.fromBundle(bundle);
    }

    public final List<CreateEntry> getCreateEntries() {
        return this.createEntries;
    }

    public final RemoteEntry getRemoteEntry() {
        return this.remoteEntry;
    }

    public BeginCreateCredentialResponse(List<CreateEntry> list, RemoteEntry remoteEntry) {
        this.createEntries = list;
        this.remoteEntry = remoteEntry;
    }

    public /* synthetic */ BeginCreateCredentialResponse(List list, RemoteEntry remoteEntry, int i10, h hVar) {
        this((i10 & 1) != 0 ? z.f19728i : list, (i10 & 2) != 0 ? null : remoteEntry);
    }
}
