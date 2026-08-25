package androidx.credentials.provider.utils;

import android.service.credentials.CredentialEntry;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroidx/credentials/provider/CredentialEntry;", "entry", "Landroid/service/credentials/CredentialEntry;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class BeginGetCredentialUtil$Companion$convertToJetpackResponse$1 extends r implements l<CredentialEntry, androidx.credentials.provider.CredentialEntry> {
    public static final BeginGetCredentialUtil$Companion$convertToJetpackResponse$1 INSTANCE = new BeginGetCredentialUtil$Companion$convertToJetpackResponse$1();

    public BeginGetCredentialUtil$Companion$convertToJetpackResponse$1() {
        super(1);
    }

    @Override // r7.l
    public final androidx.credentials.provider.CredentialEntry invoke(CredentialEntry credentialEntry) {
        return androidx.credentials.provider.CredentialEntry.INSTANCE.fromSlice$credentials_release(credentialEntry.getSlice());
    }
}
