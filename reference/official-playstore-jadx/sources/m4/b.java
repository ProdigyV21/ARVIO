package m4;

import android.content.Intent;
import android.os.Parcelable;
import com.google.android.gms.auth.api.identity.AuthorizationClient;
import com.google.android.gms.auth.api.identity.AuthorizationRequest;
import com.google.android.gms.auth.api.identity.AuthorizationResult;
import com.google.android.gms.auth.api.identity.zbc;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.auth.j4;

/* JADX INFO: loaded from: classes4.dex */
public final class b extends com.google.android.gms.common.api.n implements AuthorizationClient {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final com.google.android.gms.common.api.i f20224a = new com.google.android.gms.common.api.i("Auth.Api.Identity.Authorization.API", new j4(6), new com.google.android.gms.common.api.h());

    @Override // com.google.android.gms.auth.api.identity.AuthorizationClient
    public final com.google.android.gms.tasks.h authorize(AuthorizationRequest authorizationRequest) {
        com.google.android.gms.common.internal.t.i(authorizationRequest);
        AuthorizationRequest.Builder builderZba = AuthorizationRequest.zba(authorizationRequest);
        builderZba.zbc(((zbc) getApiOptions()).zbb());
        AuthorizationRequest authorizationRequestBuild = builderZba.build();
        com.google.android.gms.common.api.internal.x xVarBuilder = com.google.android.gms.common.api.internal.y.builder();
        xVarBuilder.f12809c = new o3.d[]{k.f20237b};
        xVarBuilder.f12807a = new kb.d(this, authorizationRequestBuild, 10);
        xVarBuilder.f12808b = false;
        xVarBuilder.f12810d = 1534;
        return doRead(xVarBuilder.a());
    }

    @Override // com.google.android.gms.auth.api.identity.AuthorizationClient
    public final AuthorizationResult getAuthorizationResultFromIntent(Intent intent) throws ApiException {
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
        Parcelable.Creator<AuthorizationResult> creator2 = AuthorizationResult.CREATOR;
        byte[] byteArrayExtra2 = intent.getByteArrayExtra("authorization_result");
        AuthorizationResult authorizationResult = (AuthorizationResult) (byteArrayExtra2 != null ? ac.b.z(byteArrayExtra2, creator2) : null);
        if (authorizationResult != null) {
            return authorizationResult;
        }
        throw new ApiException(status);
    }
}
