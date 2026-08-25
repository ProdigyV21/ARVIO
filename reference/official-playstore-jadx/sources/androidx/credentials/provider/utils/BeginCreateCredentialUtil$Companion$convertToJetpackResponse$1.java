package androidx.credentials.provider.utils;

import android.service.credentials.CreateEntry;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroidx/credentials/provider/CreateEntry;", "entry", "Landroid/service/credentials/CreateEntry;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class BeginCreateCredentialUtil$Companion$convertToJetpackResponse$1 extends r implements l<CreateEntry, androidx.credentials.provider.CreateEntry> {
    public static final BeginCreateCredentialUtil$Companion$convertToJetpackResponse$1 INSTANCE = new BeginCreateCredentialUtil$Companion$convertToJetpackResponse$1();

    public BeginCreateCredentialUtil$Companion$convertToJetpackResponse$1() {
        super(1);
    }

    @Override // r7.l
    public final androidx.credentials.provider.CreateEntry invoke(CreateEntry createEntry) {
        return androidx.credentials.provider.CreateEntry.INSTANCE.fromSlice(createEntry.getSlice());
    }
}
