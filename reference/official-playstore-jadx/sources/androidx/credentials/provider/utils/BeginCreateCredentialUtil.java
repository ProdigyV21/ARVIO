package androidx.credentials.provider.utils;

import android.app.slice.Slice;
import android.os.Bundle;
import android.service.credentials.BeginCreateCredentialRequest;
import android.service.credentials.BeginCreateCredentialResponse;
import android.service.credentials.CallingAppInfo;
import androidx.credentials.PasswordCredential;
import androidx.credentials.PublicKeyCredential;
import androidx.credentials.c;
import androidx.credentials.internal.FrameworkClassParsingException;
import androidx.credentials.provider.BeginCreateCustomCredentialRequest;
import androidx.credentials.provider.BeginCreatePasswordCredentialRequest;
import androidx.credentials.provider.BeginCreatePublicKeyCredentialRequest;
import androidx.credentials.provider.CreateEntry;
import androidx.credentials.provider.RemoteEntry;
import j$.util.Collection;
import j$.util.stream.Collectors;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import q7.n;
import r7.l;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0001\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Landroidx/credentials/provider/utils/BeginCreateCredentialUtil;", "", "()V", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BeginCreateCredentialUtil {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0003¢\u0006\u0004\b\t\u0010\nJ%\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0004\b\u001b\u0010\u001cJ\u0015\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u0018¢\u0006\u0004\b\u001e\u0010\u001f¨\u0006 "}, d2 = {"Landroidx/credentials/provider/utils/BeginCreateCredentialUtil$Companion;", "", "<init>", "()V", "Landroid/service/credentials/BeginCreateCredentialResponse$Builder;", "frameworkBuilder", "Landroidx/credentials/provider/RemoteEntry;", "remoteEntry", "Lx6/t0;", "populateRemoteEntry", "(Landroid/service/credentials/BeginCreateCredentialResponse$Builder;Landroidx/credentials/provider/RemoteEntry;)V", "", "Landroidx/credentials/provider/CreateEntry;", "createEntries", "populateCreateEntries", "(Landroid/service/credentials/BeginCreateCredentialResponse$Builder;Ljava/util/List;)V", "Landroid/service/credentials/BeginCreateCredentialRequest;", "request", "Landroidx/credentials/provider/BeginCreateCredentialRequest;", "convertToJetpackRequest$credentials_release", "(Landroid/service/credentials/BeginCreateCredentialRequest;)Landroidx/credentials/provider/BeginCreateCredentialRequest;", "convertToJetpackRequest", "Landroidx/credentials/provider/BeginCreateCredentialResponse;", "response", "Landroid/service/credentials/BeginCreateCredentialResponse;", "convertToFrameworkResponse", "(Landroidx/credentials/provider/BeginCreateCredentialResponse;)Landroid/service/credentials/BeginCreateCredentialResponse;", "convertToFrameworkRequest", "(Landroidx/credentials/provider/BeginCreateCredentialRequest;)Landroid/service/credentials/BeginCreateCredentialRequest;", "frameworkResponse", "convertToJetpackResponse", "(Landroid/service/credentials/BeginCreateCredentialResponse;)Landroidx/credentials/provider/BeginCreateCredentialResponse;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CreateEntry convertToJetpackResponse$lambda$5(l lVar, Object obj) {
            return (CreateEntry) lVar.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean convertToJetpackResponse$lambda$6(l lVar, Object obj) {
            return ((Boolean) lVar.invoke(obj)).booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CreateEntry convertToJetpackResponse$lambda$7(l lVar, Object obj) {
            return (CreateEntry) lVar.invoke(obj);
        }

        private final void populateCreateEntries(BeginCreateCredentialResponse.Builder frameworkBuilder, List<CreateEntry> createEntries) {
            Iterator<T> it = createEntries.iterator();
            while (it.hasNext()) {
                Slice slice = CreateEntry.INSTANCE.toSlice((CreateEntry) it.next());
                if (slice != null) {
                    frameworkBuilder.addCreateEntry(new android.service.credentials.CreateEntry(slice));
                }
            }
        }

        private final void populateRemoteEntry(BeginCreateCredentialResponse.Builder frameworkBuilder, RemoteEntry remoteEntry) {
            if (remoteEntry == null) {
                return;
            }
            frameworkBuilder.setRemoteCreateEntry(new android.service.credentials.RemoteEntry(RemoteEntry.INSTANCE.toSlice(remoteEntry)));
        }

        public final BeginCreateCredentialRequest convertToFrameworkRequest(androidx.credentials.provider.BeginCreateCredentialRequest request) {
            CallingAppInfo callingAppInfoJ;
            if (request.getCallingAppInfo() != null) {
                c.r();
                callingAppInfoJ = c.j(request.getCallingAppInfo().getPackageName(), request.getCallingAppInfo().getSigningInfo(), request.getCallingAppInfo().getOrigin());
            } else {
                callingAppInfoJ = null;
            }
            c.s();
            return c.h(request.getType(), request.getCandidateQueryData(), callingAppInfoJ);
        }

        public final BeginCreateCredentialResponse convertToFrameworkResponse(androidx.credentials.provider.BeginCreateCredentialResponse response) {
            BeginCreateCredentialResponse.Builder builder = new BeginCreateCredentialResponse.Builder();
            populateCreateEntries(builder, response.getCreateEntries());
            populateRemoteEntry(builder, response.getRemoteEntry());
            return builder.build();
        }

        @n
        public final androidx.credentials.provider.BeginCreateCredentialRequest convertToJetpackRequest$credentials_release(BeginCreateCredentialRequest request) {
            try {
                String type = request.getType();
                int iHashCode = type.hashCode();
                if (iHashCode != -543568185) {
                    if (iHashCode == -95037569 && type.equals(PublicKeyCredential.TYPE_PUBLIC_KEY_CREDENTIAL)) {
                        BeginCreatePublicKeyCredentialRequest.Companion companion = BeginCreatePublicKeyCredentialRequest.INSTANCE;
                        Bundle data = request.getData();
                        CallingAppInfo callingAppInfo = request.getCallingAppInfo();
                        return companion.createFrom$credentials_release(data, callingAppInfo != null ? new androidx.credentials.provider.CallingAppInfo(callingAppInfo.getPackageName(), callingAppInfo.getSigningInfo(), callingAppInfo.getOrigin()) : null);
                    }
                } else if (type.equals(PasswordCredential.TYPE_PASSWORD_CREDENTIAL)) {
                    BeginCreatePasswordCredentialRequest.Companion companion2 = BeginCreatePasswordCredentialRequest.INSTANCE;
                    Bundle data2 = request.getData();
                    CallingAppInfo callingAppInfo2 = request.getCallingAppInfo();
                    return companion2.createFrom$credentials_release(data2, callingAppInfo2 != null ? new androidx.credentials.provider.CallingAppInfo(callingAppInfo2.getPackageName(), callingAppInfo2.getSigningInfo(), callingAppInfo2.getOrigin()) : null);
                }
                String type2 = request.getType();
                Bundle data3 = request.getData();
                CallingAppInfo callingAppInfo3 = request.getCallingAppInfo();
                return new BeginCreateCustomCredentialRequest(type2, data3, callingAppInfo3 != null ? new androidx.credentials.provider.CallingAppInfo(callingAppInfo3.getPackageName(), callingAppInfo3.getSigningInfo(), callingAppInfo3.getOrigin()) : null);
            } catch (FrameworkClassParsingException unused) {
                String type3 = request.getType();
                Bundle data4 = request.getData();
                CallingAppInfo callingAppInfo4 = request.getCallingAppInfo();
                return new BeginCreateCustomCredentialRequest(type3, data4, callingAppInfo4 != null ? new androidx.credentials.provider.CallingAppInfo(callingAppInfo4.getPackageName(), callingAppInfo4.getSigningInfo(), callingAppInfo4.getOrigin()) : null);
            }
        }

        public final androidx.credentials.provider.BeginCreateCredentialResponse convertToJetpackResponse(BeginCreateCredentialResponse frameworkResponse) {
            List list = (List) Collection.EL.stream(frameworkResponse.getCreateEntries()).map(new androidx.credentials.provider.a(BeginCreateCredentialUtil$Companion$convertToJetpackResponse$1.INSTANCE, 1)).filter(new a(BeginCreateCredentialUtil$Companion$convertToJetpackResponse$2.INSTANCE, 0)).map(new androidx.credentials.provider.a(BeginCreateCredentialUtil$Companion$convertToJetpackResponse$3.INSTANCE, 2)).collect(Collectors.toList());
            android.service.credentials.RemoteEntry remoteCreateEntry = frameworkResponse.getRemoteCreateEntry();
            return new androidx.credentials.provider.BeginCreateCredentialResponse(list, remoteCreateEntry != null ? RemoteEntry.INSTANCE.fromSlice(remoteCreateEntry.getSlice()) : null);
        }

        private Companion() {
        }
    }
}
