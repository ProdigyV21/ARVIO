package androidx.credentials;

import androidx.credentials.PrepareGetCredentialResponse;
import kotlin.Metadata;
import kotlin.jvm.internal.m;
import r7.l;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public /* synthetic */ class PrepareGetCredentialResponse$Builder$setFrameworkResponse$1 extends m implements l<String, Boolean> {
    public PrepareGetCredentialResponse$Builder$setFrameworkResponse$1(Object obj) {
        super(1, 0, PrepareGetCredentialResponse.Builder.class, obj, "hasCredentialType", "hasCredentialType(Ljava/lang/String;)Z");
    }

    @Override // r7.l
    public final Boolean invoke(String str) {
        return Boolean.valueOf(((PrepareGetCredentialResponse.Builder) this.receiver).hasCredentialType(str));
    }
}
