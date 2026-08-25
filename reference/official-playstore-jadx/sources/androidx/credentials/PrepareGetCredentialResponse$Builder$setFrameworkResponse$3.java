package androidx.credentials;

import androidx.credentials.PrepareGetCredentialResponse;
import kotlin.Metadata;
import kotlin.jvm.internal.m;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public /* synthetic */ class PrepareGetCredentialResponse$Builder$setFrameworkResponse$3 extends m implements r7.a<Boolean> {
    public PrepareGetCredentialResponse$Builder$setFrameworkResponse$3(Object obj) {
        super(0, 0, PrepareGetCredentialResponse.Builder.class, obj, "hasRemoteResults", "hasRemoteResults()Z");
    }

    @Override // r7.a
    public final Boolean invoke() {
        return Boolean.valueOf(((PrepareGetCredentialResponse.Builder) this.receiver).hasRemoteResults());
    }
}
