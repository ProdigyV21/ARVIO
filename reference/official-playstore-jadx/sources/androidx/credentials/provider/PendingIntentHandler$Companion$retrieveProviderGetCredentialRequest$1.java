package androidx.credentials.provider;

import android.credentials.CredentialOption;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012\u000e\u0010\u0003\u001a\n \u0002*\u0004\u0018\u00010\u00040\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroidx/credentials/CredentialOption;", "kotlin.jvm.PlatformType", "option", "Landroid/credentials/CredentialOption;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class PendingIntentHandler$Companion$retrieveProviderGetCredentialRequest$1 extends r implements l<CredentialOption, androidx.credentials.CredentialOption> {
    public static final PendingIntentHandler$Companion$retrieveProviderGetCredentialRequest$1 INSTANCE = new PendingIntentHandler$Companion$retrieveProviderGetCredentialRequest$1();

    public PendingIntentHandler$Companion$retrieveProviderGetCredentialRequest$1() {
        super(1);
    }

    @Override // r7.l
    public final androidx.credentials.CredentialOption invoke(CredentialOption credentialOption) {
        return androidx.credentials.CredentialOption.INSTANCE.createFrom(credentialOption.getType(), credentialOption.getCredentialRetrievalData(), credentialOption.getCandidateQueryData(), credentialOption.isSystemProviderRequired(), credentialOption.getAllowedProviders());
    }
}
