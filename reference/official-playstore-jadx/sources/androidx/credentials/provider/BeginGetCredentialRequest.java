package androidx.credentials.provider;

import android.os.Build;
import android.os.Bundle;
import androidx.credentials.provider.utils.BeginGetCredentialUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import q7.n;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \r2\u00020\u0001:\u0002\f\rB!\b\u0007\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Landroidx/credentials/provider/BeginGetCredentialRequest;", "", "beginGetCredentialOptions", "", "Landroidx/credentials/provider/BeginGetCredentialOption;", "callingAppInfo", "Landroidx/credentials/provider/CallingAppInfo;", "(Ljava/util/List;Landroidx/credentials/provider/CallingAppInfo;)V", "getBeginGetCredentialOptions", "()Ljava/util/List;", "getCallingAppInfo", "()Landroidx/credentials/provider/CallingAppInfo;", "Api34Impl", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BeginGetCredentialRequest {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final List<BeginGetCredentialOption> beginGetCredentialOptions;
    private final CallingAppInfo callingAppInfo;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\t\u0010\nJ\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroidx/credentials/provider/BeginGetCredentialRequest$Api34Impl;", "", "<init>", "()V", "Landroid/os/Bundle;", "bundle", "Landroidx/credentials/provider/BeginGetCredentialRequest;", "request", "Lx6/t0;", "asBundle", "(Landroid/os/Bundle;Landroidx/credentials/provider/BeginGetCredentialRequest;)V", "fromBundle", "(Landroid/os/Bundle;)Landroidx/credentials/provider/BeginGetCredentialRequest;", "", "REQUEST_KEY", "Ljava/lang/String;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Api34Impl {
        public static final Api34Impl INSTANCE = new Api34Impl();
        private static final String REQUEST_KEY = "androidx.credentials.provider.BeginGetCredentialRequest";

        private Api34Impl() {
        }

        @n
        public static final void asBundle(Bundle bundle, BeginGetCredentialRequest request) {
            bundle.putParcelable(REQUEST_KEY, BeginGetCredentialUtil.INSTANCE.convertToFrameworkRequest(request));
        }

        @n
        public static final BeginGetCredentialRequest fromBundle(Bundle bundle) {
            android.service.credentials.BeginGetCredentialRequest beginGetCredentialRequest = (android.service.credentials.BeginGetCredentialRequest) bundle.getParcelable(REQUEST_KEY, android.service.credentials.BeginGetCredentialRequest.class);
            if (beginGetCredentialRequest != null) {
                return BeginGetCredentialUtil.INSTANCE.convertToJetpackRequest$credentials_release(beginGetCredentialRequest);
            }
            return null;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\u0004H\u0007¨\u0006\t"}, d2 = {"Landroidx/credentials/provider/BeginGetCredentialRequest$Companion;", "", "()V", "asBundle", "Landroid/os/Bundle;", "request", "Landroidx/credentials/provider/BeginGetCredentialRequest;", "fromBundle", "bundle", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @n
        public final Bundle asBundle(BeginGetCredentialRequest request) {
            Bundle bundle = new Bundle();
            if (Build.VERSION.SDK_INT >= 34) {
                Api34Impl.asBundle(bundle, request);
            }
            return bundle;
        }

        @n
        public final BeginGetCredentialRequest fromBundle(Bundle bundle) {
            if (Build.VERSION.SDK_INT >= 34) {
                return Api34Impl.fromBundle(bundle);
            }
            return null;
        }

        private Companion() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BeginGetCredentialRequest(List<? extends BeginGetCredentialOption> list) {
        this(list, null, 2, 0 == true ? 1 : 0);
    }

    @n
    public static final Bundle asBundle(BeginGetCredentialRequest beginGetCredentialRequest) {
        return INSTANCE.asBundle(beginGetCredentialRequest);
    }

    @n
    public static final BeginGetCredentialRequest fromBundle(Bundle bundle) {
        return INSTANCE.fromBundle(bundle);
    }

    public final List<BeginGetCredentialOption> getBeginGetCredentialOptions() {
        return this.beginGetCredentialOptions;
    }

    public final CallingAppInfo getCallingAppInfo() {
        return this.callingAppInfo;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BeginGetCredentialRequest(List<? extends BeginGetCredentialOption> list, CallingAppInfo callingAppInfo) {
        this.beginGetCredentialOptions = list;
        this.callingAppInfo = callingAppInfo;
    }

    public /* synthetic */ BeginGetCredentialRequest(List list, CallingAppInfo callingAppInfo, int i10, h hVar) {
        this(list, (i10 & 2) != 0 ? null : callingAppInfo);
    }
}
