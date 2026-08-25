package androidx.credentials.provider.utils;

import androidx.credentials.provider.CreateEntry;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "entry", "Landroidx/credentials/provider/CreateEntry;", "invoke", "(Landroidx/credentials/provider/CreateEntry;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class BeginCreateCredentialUtil$Companion$convertToJetpackResponse$2 extends r implements l<CreateEntry, Boolean> {
    public static final BeginCreateCredentialUtil$Companion$convertToJetpackResponse$2 INSTANCE = new BeginCreateCredentialUtil$Companion$convertToJetpackResponse$2();

    public BeginCreateCredentialUtil$Companion$convertToJetpackResponse$2() {
        super(1);
    }

    @Override // r7.l
    public final Boolean invoke(CreateEntry createEntry) {
        return Boolean.valueOf(createEntry != null);
    }
}
