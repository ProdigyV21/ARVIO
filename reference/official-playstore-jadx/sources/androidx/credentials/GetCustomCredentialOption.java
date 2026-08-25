package androidx.credentials;

import android.content.ComponentName;
import android.os.Bundle;
import io.ktor.http.LinkHeader;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.b0;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001BA\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\u0010\rBK\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010BK\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Landroidx/credentials/GetCustomCredentialOption;", "Landroidx/credentials/CredentialOption;", LinkHeader.Parameters.Type, "", "requestData", "Landroid/os/Bundle;", "candidateQueryData", "isSystemProviderRequired", "", "isAutoSelectAllowed", "allowedProviders", "", "Landroid/content/ComponentName;", "(Ljava/lang/String;Landroid/os/Bundle;Landroid/os/Bundle;ZZLjava/util/Set;)V", "typePriorityHint", "", "(Ljava/lang/String;Landroid/os/Bundle;Landroid/os/Bundle;ZZLjava/util/Set;I)V", "(Landroid/os/Bundle;Ljava/lang/String;Landroid/os/Bundle;ZZLjava/util/Set;I)V", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class GetCustomCredentialOption extends CredentialOption {
    public GetCustomCredentialOption(String str, Bundle bundle, Bundle bundle2, boolean z) {
        this(str, bundle, bundle2, z, false, null, 48, null);
    }

    public GetCustomCredentialOption(String str, Bundle bundle, Bundle bundle2, boolean z, boolean z5) {
        this(str, bundle, bundle2, z, z5, null, 32, null);
    }

    public /* synthetic */ GetCustomCredentialOption(Bundle bundle, String str, Bundle bundle2, boolean z, boolean z5, Set set, int i10, int i11, h hVar) {
        this(bundle, str, bundle2, z, (i11 & 16) != 0 ? false : z5, (Set<ComponentName>) ((i11 & 32) != 0 ? b0.f19686i : set), (i11 & 64) != 0 ? 2000 : i10);
    }

    public GetCustomCredentialOption(Bundle bundle, String str, Bundle bundle2, boolean z, boolean z5, Set<ComponentName> set, int i10) {
        super(str, bundle, bundle2, z, z5, set, i10);
        if (str.length() <= 0) {
            throw new IllegalArgumentException("type should not be empty");
        }
        if (i10 == 100) {
            throw new IllegalArgumentException("Custom types should not have passkey level priority.");
        }
    }

    public /* synthetic */ GetCustomCredentialOption(String str, Bundle bundle, Bundle bundle2, boolean z, boolean z5, Set set, int i10, h hVar) {
        this(str, bundle, bundle2, z, (i10 & 16) != 0 ? false : z5, (i10 & 32) != 0 ? b0.f19686i : set);
    }

    public GetCustomCredentialOption(String str, Bundle bundle, Bundle bundle2, boolean z, boolean z5, Set<ComponentName> set) {
        this(bundle, str, bundle2, z, z5, set, 0, 64, (h) null);
    }

    public /* synthetic */ GetCustomCredentialOption(String str, Bundle bundle, Bundle bundle2, boolean z, boolean z5, Set set, int i10, int i11, h hVar) {
        this(str, bundle, bundle2, z, (i11 & 16) != 0 ? false : z5, (Set<ComponentName>) ((i11 & 32) != 0 ? b0.f19686i : set), (i11 & 64) != 0 ? 2000 : i10);
    }

    public GetCustomCredentialOption(String str, Bundle bundle, Bundle bundle2, boolean z, boolean z5, Set<ComponentName> set, int i10) {
        this(bundle, str, bundle2, z, z5, set, i10);
    }
}
