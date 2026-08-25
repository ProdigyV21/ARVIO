package androidx.credentials;

import android.content.ComponentName;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.b0;
import kotlin.collections.x;
import kotlin.jvm.internal.h;
import q7.n;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B1\b\u0017\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\u0002\u0010\tBE\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0013"}, d2 = {"Landroidx/credentials/GetPasswordOption;", "Landroidx/credentials/CredentialOption;", "allowedUserIds", "", "", "isAutoSelectAllowed", "", "allowedProviders", "Landroid/content/ComponentName;", "(Ljava/util/Set;ZLjava/util/Set;)V", "requestData", "Landroid/os/Bundle;", "candidateQueryData", "typePriorityHint", "", "(Ljava/util/Set;ZLjava/util/Set;Landroid/os/Bundle;Landroid/os/Bundle;I)V", "getAllowedUserIds", "()Ljava/util/Set;", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class GetPasswordOption extends CredentialOption {
    public static final String BUNDLE_KEY_ALLOWED_USER_IDS = "androidx.credentials.BUNDLE_KEY_ALLOWED_USER_IDS";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Set<String> allowedUserIds;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J+\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\bH\u0001¢\u0006\u0002\b\rJ\u001b\u0010\u000e\u001a\u00020\b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\nH\u0001¢\u0006\u0002\b\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/credentials/GetPasswordOption$Companion;", "", "()V", "BUNDLE_KEY_ALLOWED_USER_IDS", "", "createFrom", "Landroidx/credentials/GetPasswordOption;", "data", "Landroid/os/Bundle;", "allowedProviders", "", "Landroid/content/ComponentName;", "candidateQueryData", "createFrom$credentials_release", "toBundle", "allowUserIds", "toBundle$credentials_release", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @n
        public final GetPasswordOption createFrom$credentials_release(Bundle data, Set<ComponentName> allowedProviders, Bundle candidateQueryData) {
            ArrayList<String> stringArrayList = data.getStringArrayList(GetPasswordOption.BUNDLE_KEY_ALLOWED_USER_IDS);
            return new GetPasswordOption(stringArrayList != null ? x.g1(stringArrayList) : b0.f19686i, data.getBoolean("androidx.credentials.BUNDLE_KEY_IS_AUTO_SELECT_ALLOWED", false), allowedProviders, data, candidateQueryData, data.getInt(CredentialOption.BUNDLE_KEY_TYPE_PRIORITY_VALUE, 1000), null);
        }

        @n
        public final Bundle toBundle$credentials_release(Set<String> allowUserIds) {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList(GetPasswordOption.BUNDLE_KEY_ALLOWED_USER_IDS, new ArrayList<>(allowUserIds));
            return bundle;
        }

        private Companion() {
        }
    }

    public GetPasswordOption() {
        this(null, false, null, 7, null);
    }

    public final Set<String> getAllowedUserIds() {
        return this.allowedUserIds;
    }

    public GetPasswordOption(Set<String> set) {
        this(set, false, null, 6, null);
    }

    public GetPasswordOption(Set<String> set, boolean z) {
        this(set, z, null, 4, null);
    }

    public /* synthetic */ GetPasswordOption(Set set, boolean z, Set set2, Bundle bundle, Bundle bundle2, int i10, h hVar) {
        this(set, z, set2, bundle, bundle2, i10);
    }

    public /* synthetic */ GetPasswordOption(Set set, boolean z, Set set2, Bundle bundle, Bundle bundle2, int i10, int i11, h hVar) {
        this(set, z, set2, bundle, bundle2, (i11 & 32) != 0 ? 1000 : i10);
    }

    private GetPasswordOption(Set<String> set, boolean z, Set<ComponentName> set2, Bundle bundle, Bundle bundle2, int i10) {
        super(PasswordCredential.TYPE_PASSWORD_CREDENTIAL, bundle, bundle2, false, z, set2, i10);
        this.allowedUserIds = set;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ GetPasswordOption(Set set, boolean z, Set set2, int i10, h hVar) {
        int i11 = i10 & 1;
        b0 b0Var = b0.f19686i;
        this(i11 != 0 ? b0Var : set, (i10 & 2) != 0 ? false : z, (i10 & 4) != 0 ? b0Var : set2);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public GetPasswordOption(Set<String> set, boolean z, Set<ComponentName> set2) {
        Companion companion = INSTANCE;
        this(set, z, set2, companion.toBundle$credentials_release(set), companion.toBundle$credentials_release(set), 0, 32, null);
    }
}
