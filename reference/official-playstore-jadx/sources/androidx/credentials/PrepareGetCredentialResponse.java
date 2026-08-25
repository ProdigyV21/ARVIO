package androidx.credentials;

import android.credentials.PrepareGetCredentialResponse;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.l;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0019\b\u0007\u0018\u00002\u00020\u0001:\u0003\"#$Bc\b\u0002\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004j\u0004\u0018\u0001`\u0006\u0012\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004j\u0004\u0018\u0001`\b\u0012\u001a\u0010\r\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0005\u0018\u00010\nj\u0004\u0018\u0001`\f\u0012\u0006\u0010\u000e\u001a\u00020\u0005¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\u0016\u0010\u0015R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R%\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004j\u0004\u0018\u0001`\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR%\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004j\u0004\u0018\u0001`\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\u001a\u001a\u0004\b\u001d\u0010\u001cR+\u0010\r\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0005\u0018\u00010\nj\u0004\u0018\u0001`\f8\u0006¢\u0006\f\n\u0004\b\r\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0017\u0010\u000e\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u000e\u0010!\u001a\u0004\b\u000e\u0010\u0015¨\u0006%"}, d2 = {"Landroidx/credentials/PrepareGetCredentialResponse;", "", "Landroidx/credentials/PrepareGetCredentialResponse$PendingGetCredentialHandle;", "pendingGetCredentialHandle", "Lkotlin/Function0;", "", "Landroidx/credentials/HasRemoteResultsDelegate;", "hasRemoteResultsDelegate", "Landroidx/credentials/HasAuthenticationResultsDelegate;", "hasAuthResultsDelegate", "Lkotlin/Function1;", "", "Landroidx/credentials/HasCredentialResultsDelegate;", "credentialTypeDelegate", "isNullHandlesForTest", "<init>", "(Landroidx/credentials/PrepareGetCredentialResponse$PendingGetCredentialHandle;Lr7/a;Lr7/a;Lr7/l;Z)V", "credentialType", "hasCredentialResults", "(Ljava/lang/String;)Z", "hasAuthenticationResults", "()Z", "hasRemoteResults", "Landroidx/credentials/PrepareGetCredentialResponse$PendingGetCredentialHandle;", "getPendingGetCredentialHandle", "()Landroidx/credentials/PrepareGetCredentialResponse$PendingGetCredentialHandle;", "Lr7/a;", "getHasRemoteResultsDelegate", "()Lr7/a;", "getHasAuthResultsDelegate", "Lr7/l;", "getCredentialTypeDelegate", "()Lr7/l;", "Z", "Builder", "PendingGetCredentialHandle", "TestBuilder", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PrepareGetCredentialResponse {
    private final l<String, Boolean> credentialTypeDelegate;
    private final r7.a<Boolean> hasAuthResultsDelegate;
    private final r7.a<Boolean> hasRemoteResultsDelegate;
    private final boolean isNullHandlesForTest;
    private final PendingGetCredentialHandle pendingGetCredentialHandle;

    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0006H\u0003¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0006H\u0003¢\u0006\u0004\b\u000b\u0010\nJ\u0017\u0010\u000e\u001a\u00020\u00002\b\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0015\u0010\u0016R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R$\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0019j\u0004\u0018\u0001`\u001a8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR$\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0019j\u0004\u0018\u0001`\u001d8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u001cR*\u0010!\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u001fj\u0004\u0018\u0001` 8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010\"R\u0018\u0010#\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010$¨\u0006%"}, d2 = {"Landroidx/credentials/PrepareGetCredentialResponse$Builder;", "", "<init>", "()V", "", "credentialType", "", "hasCredentialType", "(Ljava/lang/String;)Z", "hasAuthenticationResults", "()Z", "hasRemoteResults", "Landroid/credentials/PrepareGetCredentialResponse;", "resp", "setFrameworkResponse", "(Landroid/credentials/PrepareGetCredentialResponse;)Landroidx/credentials/PrepareGetCredentialResponse$Builder;", "Landroidx/credentials/PrepareGetCredentialResponse$PendingGetCredentialHandle;", "handle", "setPendingGetCredentialHandle", "(Landroidx/credentials/PrepareGetCredentialResponse$PendingGetCredentialHandle;)Landroidx/credentials/PrepareGetCredentialResponse$Builder;", "Landroidx/credentials/PrepareGetCredentialResponse;", "build", "()Landroidx/credentials/PrepareGetCredentialResponse;", "pendingGetCredentialHandle", "Landroidx/credentials/PrepareGetCredentialResponse$PendingGetCredentialHandle;", "Lkotlin/Function0;", "Landroidx/credentials/HasRemoteResultsDelegate;", "hasRemoteResultsDelegate", "Lr7/a;", "Landroidx/credentials/HasAuthenticationResultsDelegate;", "hasAuthResultsDelegate", "Lkotlin/Function1;", "Landroidx/credentials/HasCredentialResultsDelegate;", "hasCredentialResultsDelegate", "Lr7/l;", "frameworkResponse", "Landroid/credentials/PrepareGetCredentialResponse;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Builder {
        private android.credentials.PrepareGetCredentialResponse frameworkResponse;
        private r7.a<Boolean> hasAuthResultsDelegate;
        private l<? super String, Boolean> hasCredentialResultsDelegate;
        private r7.a<Boolean> hasRemoteResultsDelegate;
        private PendingGetCredentialHandle pendingGetCredentialHandle;

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean hasAuthenticationResults() {
            return this.frameworkResponse.hasAuthenticationResults();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean hasCredentialType(String credentialType) {
            return this.frameworkResponse.hasCredentialResults(credentialType);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean hasRemoteResults() {
            return this.frameworkResponse.hasRemoteResults();
        }

        public final PrepareGetCredentialResponse build() {
            return new PrepareGetCredentialResponse(this.pendingGetCredentialHandle, this.hasRemoteResultsDelegate, this.hasAuthResultsDelegate, this.hasCredentialResultsDelegate, false, null);
        }

        public final Builder setFrameworkResponse(android.credentials.PrepareGetCredentialResponse resp) {
            this.frameworkResponse = resp;
            if (resp != null) {
                this.hasCredentialResultsDelegate = new PrepareGetCredentialResponse$Builder$setFrameworkResponse$1(this);
                this.hasAuthResultsDelegate = new PrepareGetCredentialResponse$Builder$setFrameworkResponse$2(this);
                this.hasRemoteResultsDelegate = new PrepareGetCredentialResponse$Builder$setFrameworkResponse$3(this);
            }
            return this;
        }

        public final Builder setPendingGetCredentialHandle(PendingGetCredentialHandle handle) {
            this.pendingGetCredentialHandle = handle;
            return this;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/credentials/PrepareGetCredentialResponse$PendingGetCredentialHandle;", "", "frameworkHandle", "Landroid/credentials/PrepareGetCredentialResponse$PendingGetCredentialHandle;", "(Landroid/credentials/PrepareGetCredentialResponse$PendingGetCredentialHandle;)V", "getFrameworkHandle", "()Landroid/credentials/PrepareGetCredentialResponse$PendingGetCredentialHandle;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class PendingGetCredentialHandle {
        private final PrepareGetCredentialResponse.PendingGetCredentialHandle frameworkHandle;

        public PendingGetCredentialHandle(PrepareGetCredentialResponse.PendingGetCredentialHandle pendingGetCredentialHandle) {
            this.frameworkHandle = pendingGetCredentialHandle;
        }

        public final PrepareGetCredentialResponse.PendingGetCredentialHandle getFrameworkHandle() {
            return this.frameworkHandle;
        }
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\u00020\u00002\u0016\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u0002`\u0007H\u0007¢\u0006\u0004\b\t\u0010\nJ!\u0010\r\u001a\u00020\u00002\u0010\u0010\b\u001a\f\u0012\u0004\u0012\u00020\u00060\u000bj\u0002`\fH\u0007¢\u0006\u0004\b\r\u0010\u000eJ!\u0010\u0010\u001a\u00020\u00002\u0010\u0010\b\u001a\f\u0012\u0004\u0012\u00020\u00060\u000bj\u0002`\u000fH\u0007¢\u0006\u0004\b\u0010\u0010\u000eJ\r\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013R$\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000bj\u0004\u0018\u0001`\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R$\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000bj\u0004\u0018\u0001`\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010\u0015R*\u0010\u0017\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004j\u0004\u0018\u0001`\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Landroidx/credentials/PrepareGetCredentialResponse$TestBuilder;", "", "<init>", "()V", "Lkotlin/Function1;", "", "", "Landroidx/credentials/HasCredentialResultsDelegate;", "handler", "setCredentialTypeDelegate", "(Lr7/l;)Landroidx/credentials/PrepareGetCredentialResponse$TestBuilder;", "Lkotlin/Function0;", "Landroidx/credentials/HasAuthenticationResultsDelegate;", "setHasAuthResultsDelegate", "(Lr7/a;)Landroidx/credentials/PrepareGetCredentialResponse$TestBuilder;", "Landroidx/credentials/HasRemoteResultsDelegate;", "setHasRemoteResultsDelegate", "Landroidx/credentials/PrepareGetCredentialResponse;", "build", "()Landroidx/credentials/PrepareGetCredentialResponse;", "hasRemoteResultsDelegate", "Lr7/a;", "hasAuthResultsDelegate", "hasCredentialResultsDelegate", "Lr7/l;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class TestBuilder {
        private r7.a<Boolean> hasAuthResultsDelegate;
        private l<? super String, Boolean> hasCredentialResultsDelegate;
        private r7.a<Boolean> hasRemoteResultsDelegate;

        public final PrepareGetCredentialResponse build() {
            return new PrepareGetCredentialResponse(null, this.hasRemoteResultsDelegate, this.hasAuthResultsDelegate, this.hasCredentialResultsDelegate, true, null);
        }

        public final TestBuilder setCredentialTypeDelegate(l<? super String, Boolean> handler) {
            this.hasCredentialResultsDelegate = handler;
            return this;
        }

        public final TestBuilder setHasAuthResultsDelegate(r7.a<Boolean> handler) {
            this.hasAuthResultsDelegate = handler;
            return this;
        }

        public final TestBuilder setHasRemoteResultsDelegate(r7.a<Boolean> handler) {
            this.hasRemoteResultsDelegate = handler;
            return this;
        }
    }

    public /* synthetic */ PrepareGetCredentialResponse(PendingGetCredentialHandle pendingGetCredentialHandle, r7.a aVar, r7.a aVar2, l lVar, boolean z, h hVar) {
        this(pendingGetCredentialHandle, aVar, aVar2, lVar, z);
    }

    public final l<String, Boolean> getCredentialTypeDelegate() {
        return this.credentialTypeDelegate;
    }

    public final r7.a<Boolean> getHasAuthResultsDelegate() {
        return this.hasAuthResultsDelegate;
    }

    public final r7.a<Boolean> getHasRemoteResultsDelegate() {
        return this.hasRemoteResultsDelegate;
    }

    public final PendingGetCredentialHandle getPendingGetCredentialHandle() {
        return this.pendingGetCredentialHandle;
    }

    public final boolean hasAuthenticationResults() {
        r7.a<Boolean> aVar = this.hasAuthResultsDelegate;
        if (aVar != null) {
            return ((Boolean) aVar.invoke()).booleanValue();
        }
        return false;
    }

    public final boolean hasCredentialResults(String credentialType) {
        l<String, Boolean> lVar = this.credentialTypeDelegate;
        if (lVar != null) {
            return ((Boolean) lVar.invoke(credentialType)).booleanValue();
        }
        return false;
    }

    public final boolean hasRemoteResults() {
        r7.a<Boolean> aVar = this.hasRemoteResultsDelegate;
        if (aVar != null) {
            return ((Boolean) aVar.invoke()).booleanValue();
        }
        return false;
    }

    /* JADX INFO: renamed from: isNullHandlesForTest, reason: from getter */
    public final boolean getIsNullHandlesForTest() {
        return this.isNullHandlesForTest;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private PrepareGetCredentialResponse(PendingGetCredentialHandle pendingGetCredentialHandle, r7.a<Boolean> aVar, r7.a<Boolean> aVar2, l<? super String, Boolean> lVar, boolean z) {
        this.pendingGetCredentialHandle = pendingGetCredentialHandle;
        this.hasRemoteResultsDelegate = aVar;
        this.hasAuthResultsDelegate = aVar2;
        this.credentialTypeDelegate = lVar;
        this.isNullHandlesForTest = z;
    }
}
