package m4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest;
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.identity.zbv;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.auth.j4;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class i extends com.google.android.gms.common.api.n implements SignInClient {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final com.google.android.gms.common.api.i f20233b = new com.google.android.gms.common.api.i("Auth.Api.Identity.SignIn.API", new j4(5), new com.google.android.gms.common.api.h());

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f20234a;

    public i(Activity activity, zbv zbvVar) {
        super(activity, activity, f20233b, zbvVar, com.google.android.gms.common.api.m.f12824c);
        this.f20234a = l.a();
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final com.google.android.gms.tasks.h beginSignIn(BeginSignInRequest beginSignInRequest) {
        com.google.android.gms.common.internal.t.i(beginSignInRequest);
        BeginSignInRequest.Builder builderZba = BeginSignInRequest.zba(beginSignInRequest);
        builderZba.zba(this.f20234a);
        BeginSignInRequest beginSignInRequestBuild = builderZba.build();
        com.google.android.gms.common.api.internal.x xVarBuilder = com.google.android.gms.common.api.internal.y.builder();
        xVarBuilder.f12809c = new o3.d[]{new o3.d("auth_api_credentials_begin_sign_in", 8L)};
        xVarBuilder.f12807a = new kb.d(this, beginSignInRequestBuild, 8);
        xVarBuilder.f12808b = false;
        xVarBuilder.f12810d = 1553;
        return doRead(xVarBuilder.a());
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final String getPhoneNumberFromIntent(Intent intent) throws ApiException {
        Status status = Status.f12672q;
        if (intent == null) {
            throw new ApiException(status);
        }
        Parcelable.Creator<Status> creator = Status.CREATOR;
        byte[] byteArrayExtra = intent.getByteArrayExtra("status");
        Status status2 = (Status) (byteArrayExtra == null ? null : ac.b.z(byteArrayExtra, creator));
        if (status2 == null) {
            throw new ApiException(Status.f12674s);
        }
        if (!status2.d()) {
            throw new ApiException(status2);
        }
        String stringExtra = intent.getStringExtra("phone_number_hint_result");
        if (stringExtra != null) {
            return stringExtra;
        }
        throw new ApiException(status);
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final com.google.android.gms.tasks.h getPhoneNumberHintIntent(GetPhoneNumberHintIntentRequest getPhoneNumberHintIntentRequest) {
        com.google.android.gms.common.internal.t.i(getPhoneNumberHintIntentRequest);
        com.google.android.gms.common.api.internal.x xVarBuilder = com.google.android.gms.common.api.internal.y.builder();
        xVarBuilder.f12809c = new o3.d[]{k.f20241f};
        xVarBuilder.f12807a = new fi.iki.elonen.f(this, getPhoneNumberHintIntentRequest);
        xVarBuilder.f12810d = 1653;
        return doRead(xVarBuilder.a());
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final SignInCredential getSignInCredentialFromIntent(Intent intent) throws ApiException {
        Status status = Status.f12672q;
        if (intent == null) {
            throw new ApiException(status);
        }
        Parcelable.Creator<Status> creator = Status.CREATOR;
        byte[] byteArrayExtra = intent.getByteArrayExtra("status");
        Status status2 = (Status) (byteArrayExtra == null ? null : ac.b.z(byteArrayExtra, creator));
        if (status2 == null) {
            throw new ApiException(Status.f12674s);
        }
        if (!status2.d()) {
            throw new ApiException(status2);
        }
        Parcelable.Creator<SignInCredential> creator2 = SignInCredential.CREATOR;
        byte[] byteArrayExtra2 = intent.getByteArrayExtra("sign_in_credential");
        SignInCredential signInCredential = (SignInCredential) (byteArrayExtra2 != null ? ac.b.z(byteArrayExtra2, creator2) : null);
        if (signInCredential != null) {
            return signInCredential;
        }
        throw new ApiException(status);
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final com.google.android.gms.tasks.h getSignInIntent(GetSignInIntentRequest getSignInIntentRequest) {
        com.google.android.gms.common.internal.t.i(getSignInIntentRequest);
        GetSignInIntentRequest.Builder builderZba = GetSignInIntentRequest.zba(getSignInIntentRequest);
        builderZba.zba(this.f20234a);
        GetSignInIntentRequest getSignInIntentRequestBuild = builderZba.build();
        com.google.android.gms.common.api.internal.x xVarBuilder = com.google.android.gms.common.api.internal.y.builder();
        xVarBuilder.f12809c = new o3.d[]{k.f20239d};
        xVarBuilder.f12807a = new kb.d(this, getSignInIntentRequestBuild, 9);
        xVarBuilder.f12810d = 1555;
        return doRead(xVarBuilder.a());
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final com.google.android.gms.tasks.h signOut() {
        getApplicationContext().getSharedPreferences("com.google.android.gms.signin", 0).edit().clear().apply();
        Set set = com.google.android.gms.common.api.q.f12827a;
        synchronized (set) {
        }
        Iterator it = set.iterator();
        if (it.hasNext()) {
            ((com.google.android.gms.common.api.q) it.next()).getClass();
            throw new UnsupportedOperationException();
        }
        com.google.android.gms.common.api.internal.h.a();
        com.google.android.gms.common.api.internal.x xVarBuilder = com.google.android.gms.common.api.internal.y.builder();
        xVarBuilder.f12809c = new o3.d[]{k.f20236a};
        xVarBuilder.f12807a = new kb.d(this, 7);
        xVarBuilder.f12808b = false;
        xVarBuilder.f12810d = 1554;
        return doWrite(xVarBuilder.a());
    }

    public i(Context context, zbv zbvVar) {
        super(context, null, f20233b, zbvVar, com.google.android.gms.common.api.m.f12824c);
        this.f20234a = l.a();
    }
}
