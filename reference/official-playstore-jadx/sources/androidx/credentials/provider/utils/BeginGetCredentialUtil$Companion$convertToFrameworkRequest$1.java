package androidx.credentials.provider.utils;

import androidx.credentials.provider.BeginGetCredentialOption;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012\u000e\u0010\u0003\u001a\n \u0002*\u0004\u0018\u00010\u00040\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroid/service/credentials/BeginGetCredentialOption;", "kotlin.jvm.PlatformType", "option", "Landroidx/credentials/provider/BeginGetCredentialOption;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class BeginGetCredentialUtil$Companion$convertToFrameworkRequest$1 extends r implements l<BeginGetCredentialOption, android.service.credentials.BeginGetCredentialOption> {
    public static final BeginGetCredentialUtil$Companion$convertToFrameworkRequest$1 INSTANCE = new BeginGetCredentialUtil$Companion$convertToFrameworkRequest$1();

    public BeginGetCredentialUtil$Companion$convertToFrameworkRequest$1() {
        super(1);
    }

    @Override // r7.l
    public final android.service.credentials.BeginGetCredentialOption invoke(BeginGetCredentialOption beginGetCredentialOption) {
        return BeginGetCredentialUtil.INSTANCE.convertToJetpackBeginOption(beginGetCredentialOption);
    }
}
