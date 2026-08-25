package androidx.credentials.provider.utils;

import android.app.slice.Slice;
import android.os.Bundle;
import android.service.credentials.BeginGetCredentialOption;
import android.service.credentials.BeginGetCredentialRequest;
import android.service.credentials.BeginGetCredentialResponse;
import android.service.credentials.CallingAppInfo;
import androidx.credentials.c;
import androidx.credentials.d;
import androidx.credentials.provider.Action;
import androidx.credentials.provider.AuthenticationAction;
import androidx.credentials.provider.CredentialEntry;
import androidx.credentials.provider.RemoteEntry;
import j$.util.Collection;
import j$.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import q7.n;
import r7.l;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Landroidx/credentials/provider/utils/BeginGetCredentialUtil;", "", "()V", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BeginGetCredentialUtil {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0003¢\u0006\u0004\b\t\u0010\nJ%\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ%\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00042\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u000bH\u0002¢\u0006\u0004\b\u0013\u0010\u000fJ%\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00042\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u000bH\u0002¢\u0006\u0004\b\u0016\u0010\u000fJ\u0017\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0017H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010!\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u001cH\u0001¢\u0006\u0004\b\u001f\u0010 J\u0015\u0010%\u001a\u00020$2\u0006\u0010#\u001a\u00020\"¢\u0006\u0004\b%\u0010&J\u0015\u0010'\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e¢\u0006\u0004\b'\u0010(J\u0015\u0010)\u001a\u00020\"2\u0006\u0010#\u001a\u00020$¢\u0006\u0004\b)\u0010*¨\u0006+"}, d2 = {"Landroidx/credentials/provider/utils/BeginGetCredentialUtil$Companion;", "", "<init>", "()V", "Landroid/service/credentials/BeginGetCredentialResponse$Builder;", "frameworkBuilder", "Landroidx/credentials/provider/RemoteEntry;", "remoteEntry", "Lx6/t0;", "populateRemoteEntry", "(Landroid/service/credentials/BeginGetCredentialResponse$Builder;Landroidx/credentials/provider/RemoteEntry;)V", "", "Landroidx/credentials/provider/AuthenticationAction;", "authenticationActions", "populateAuthenticationEntries", "(Landroid/service/credentials/BeginGetCredentialResponse$Builder;Ljava/util/List;)V", "builder", "Landroidx/credentials/provider/Action;", "actionEntries", "populateActionEntries", "Landroidx/credentials/provider/CredentialEntry;", "credentialEntries", "populateCredentialEntries", "Landroidx/credentials/provider/BeginGetCredentialOption;", "option", "Landroid/service/credentials/BeginGetCredentialOption;", "convertToJetpackBeginOption", "(Landroidx/credentials/provider/BeginGetCredentialOption;)Landroid/service/credentials/BeginGetCredentialOption;", "Landroid/service/credentials/BeginGetCredentialRequest;", "request", "Landroidx/credentials/provider/BeginGetCredentialRequest;", "convertToJetpackRequest$credentials_release", "(Landroid/service/credentials/BeginGetCredentialRequest;)Landroidx/credentials/provider/BeginGetCredentialRequest;", "convertToJetpackRequest", "Landroidx/credentials/provider/BeginGetCredentialResponse;", "response", "Landroid/service/credentials/BeginGetCredentialResponse;", "convertToFrameworkResponse", "(Landroidx/credentials/provider/BeginGetCredentialResponse;)Landroid/service/credentials/BeginGetCredentialResponse;", "convertToFrameworkRequest", "(Landroidx/credentials/provider/BeginGetCredentialRequest;)Landroid/service/credentials/BeginGetCredentialRequest;", "convertToJetpackResponse", "(Landroid/service/credentials/BeginGetCredentialResponse;)Landroidx/credentials/provider/BeginGetCredentialResponse;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final BeginGetCredentialOption convertToFrameworkRequest$lambda$5(l lVar, Object obj) {
            return d.d(lVar.invoke(obj));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final BeginGetCredentialOption convertToJetpackBeginOption(androidx.credentials.provider.BeginGetCredentialOption option) {
            c.t();
            return c.i(option.getId(), option.getType(), option.getCandidateQueryData());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean convertToJetpackResponse$lambda$10(l lVar, Object obj) {
            return ((Boolean) lVar.invoke(obj)).booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Action convertToJetpackResponse$lambda$11(l lVar, Object obj) {
            return (Action) lVar.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final AuthenticationAction convertToJetpackResponse$lambda$12(l lVar, Object obj) {
            return (AuthenticationAction) lVar.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean convertToJetpackResponse$lambda$13(l lVar, Object obj) {
            return ((Boolean) lVar.invoke(obj)).booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final AuthenticationAction convertToJetpackResponse$lambda$14(l lVar, Object obj) {
            return (AuthenticationAction) lVar.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CredentialEntry convertToJetpackResponse$lambda$6(l lVar, Object obj) {
            return (CredentialEntry) lVar.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean convertToJetpackResponse$lambda$7(l lVar, Object obj) {
            return ((Boolean) lVar.invoke(obj)).booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CredentialEntry convertToJetpackResponse$lambda$8(l lVar, Object obj) {
            return (CredentialEntry) lVar.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Action convertToJetpackResponse$lambda$9(l lVar, Object obj) {
            return (Action) lVar.invoke(obj);
        }

        private final void populateActionEntries(BeginGetCredentialResponse.Builder builder, List<Action> actionEntries) {
            Iterator<T> it = actionEntries.iterator();
            while (it.hasNext()) {
                builder.addAction(new android.service.credentials.Action(Action.INSTANCE.toSlice((Action) it.next())));
            }
        }

        private final void populateAuthenticationEntries(BeginGetCredentialResponse.Builder frameworkBuilder, List<AuthenticationAction> authenticationActions) {
            Iterator<T> it = authenticationActions.iterator();
            while (it.hasNext()) {
                frameworkBuilder.addAuthenticationAction(new android.service.credentials.Action(AuthenticationAction.INSTANCE.toSlice((AuthenticationAction) it.next())));
            }
        }

        private final void populateCredentialEntries(BeginGetCredentialResponse.Builder builder, List<? extends CredentialEntry> credentialEntries) {
            for (CredentialEntry credentialEntry : credentialEntries) {
                Slice slice$credentials_release = CredentialEntry.INSTANCE.toSlice$credentials_release(credentialEntry);
                if (slice$credentials_release != null) {
                    builder.addCredentialEntry(new android.service.credentials.CredentialEntry(new BeginGetCredentialOption(credentialEntry.getBeginGetCredentialOption().getId(), credentialEntry.getType(), Bundle.EMPTY), slice$credentials_release));
                }
            }
        }

        private final void populateRemoteEntry(BeginGetCredentialResponse.Builder frameworkBuilder, RemoteEntry remoteEntry) {
            if (remoteEntry == null) {
                return;
            }
            frameworkBuilder.setRemoteCredentialEntry(new android.service.credentials.RemoteEntry(RemoteEntry.INSTANCE.toSlice(remoteEntry)));
        }

        public final BeginGetCredentialRequest convertToFrameworkRequest(androidx.credentials.provider.BeginGetCredentialRequest request) {
            BeginGetCredentialRequest.Builder builder = new BeginGetCredentialRequest.Builder();
            if (request.getCallingAppInfo() != null) {
                builder.setCallingAppInfo(new CallingAppInfo(request.getCallingAppInfo().getPackageName(), request.getCallingAppInfo().getSigningInfo(), request.getCallingAppInfo().getOrigin()));
            }
            return builder.setBeginGetCredentialOptions((List) Collection.EL.stream(request.getBeginGetCredentialOptions()).map(new androidx.credentials.provider.a(BeginGetCredentialUtil$Companion$convertToFrameworkRequest$1.INSTANCE, 4)).collect(Collectors.toList())).build();
        }

        public final BeginGetCredentialResponse convertToFrameworkResponse(androidx.credentials.provider.BeginGetCredentialResponse response) {
            BeginGetCredentialResponse.Builder builder = new BeginGetCredentialResponse.Builder();
            populateCredentialEntries(builder, response.getCredentialEntries());
            populateActionEntries(builder, response.getActions());
            populateAuthenticationEntries(builder, response.getAuthenticationActions());
            populateRemoteEntry(builder, response.getRemoteEntry());
            return builder.build();
        }

        @n
        public final androidx.credentials.provider.BeginGetCredentialRequest convertToJetpackRequest$credentials_release(BeginGetCredentialRequest request) {
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = request.getBeginGetCredentialOptions().iterator();
            while (it.hasNext()) {
                BeginGetCredentialOption beginGetCredentialOptionD = d.d(it.next());
                arrayList.add(androidx.credentials.provider.BeginGetCredentialOption.INSTANCE.createFrom$credentials_release(beginGetCredentialOptionD.getId(), beginGetCredentialOptionD.getType(), beginGetCredentialOptionD.getCandidateQueryData()));
            }
            CallingAppInfo callingAppInfo = request.getCallingAppInfo();
            return new androidx.credentials.provider.BeginGetCredentialRequest(arrayList, callingAppInfo != null ? new androidx.credentials.provider.CallingAppInfo(callingAppInfo.getPackageName(), callingAppInfo.getSigningInfo(), callingAppInfo.getOrigin()) : null);
        }

        public final androidx.credentials.provider.BeginGetCredentialResponse convertToJetpackResponse(BeginGetCredentialResponse response) {
            List list = (List) Collection.EL.stream(response.getCredentialEntries()).map(new androidx.credentials.provider.a(BeginGetCredentialUtil$Companion$convertToJetpackResponse$1.INSTANCE, 5)).filter(new a(BeginGetCredentialUtil$Companion$convertToJetpackResponse$2.INSTANCE, 2)).map(new androidx.credentials.provider.a(BeginGetCredentialUtil$Companion$convertToJetpackResponse$3.INSTANCE, 6)).collect(Collectors.toList());
            List list2 = (List) Collection.EL.stream(response.getActions()).map(new androidx.credentials.provider.a(BeginGetCredentialUtil$Companion$convertToJetpackResponse$4.INSTANCE, 7)).filter(new a(BeginGetCredentialUtil$Companion$convertToJetpackResponse$5.INSTANCE, 3)).map(new androidx.credentials.provider.a(BeginGetCredentialUtil$Companion$convertToJetpackResponse$6.INSTANCE, 8)).collect(Collectors.toList());
            List list3 = (List) Collection.EL.stream(response.getAuthenticationActions()).map(new androidx.credentials.provider.a(BeginGetCredentialUtil$Companion$convertToJetpackResponse$7.INSTANCE, 9)).filter(new a(BeginGetCredentialUtil$Companion$convertToJetpackResponse$8.INSTANCE, 1)).map(new androidx.credentials.provider.a(BeginGetCredentialUtil$Companion$convertToJetpackResponse$9.INSTANCE, 3)).collect(Collectors.toList());
            android.service.credentials.RemoteEntry remoteCredentialEntry = response.getRemoteCredentialEntry();
            return new androidx.credentials.provider.BeginGetCredentialResponse(list, list2, list3, remoteCredentialEntry != null ? RemoteEntry.INSTANCE.fromSlice(remoteCredentialEntry.getSlice()) : null);
        }

        private Companion() {
        }
    }
}
