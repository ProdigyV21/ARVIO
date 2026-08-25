package m4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import com.google.android.gms.auth.api.identity.CredentialSavingClient;
import com.google.android.gms.auth.api.identity.SaveAccountLinkingTokenRequest;
import com.google.android.gms.auth.api.identity.SavePasswordRequest;
import com.google.android.gms.auth.api.identity.zbi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.auth.j4;

/* JADX INFO: loaded from: classes4.dex */
public final class e extends com.google.android.gms.common.api.n implements CredentialSavingClient {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final com.google.android.gms.common.api.i f20228b = new com.google.android.gms.common.api.i("Auth.Api.Identity.CredentialSaving.API", new j4(4), new com.google.android.gms.common.api.h());

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f20229a;

    public e(Activity activity, zbi zbiVar) {
        super(activity, activity, f20228b, zbiVar, com.google.android.gms.common.api.m.f12824c);
        this.f20229a = l.a();
    }

    @Override // com.google.android.gms.auth.api.identity.CredentialSavingClient
    public final Status getStatusFromIntent(Intent intent) {
        Status status = Status.f12672q;
        if (intent == null) {
            return status;
        }
        Parcelable.Creator<Status> creator = Status.CREATOR;
        byte[] byteArrayExtra = intent.getByteArrayExtra("status");
        Status status2 = (Status) (byteArrayExtra == null ? null : ac.b.z(byteArrayExtra, creator));
        return status2 == null ? status : status2;
    }

    @Override // com.google.android.gms.auth.api.identity.CredentialSavingClient
    public final com.google.android.gms.tasks.h saveAccountLinkingToken(SaveAccountLinkingTokenRequest saveAccountLinkingTokenRequest) {
        com.google.android.gms.common.internal.t.i(saveAccountLinkingTokenRequest);
        SaveAccountLinkingTokenRequest.Builder builderZba = SaveAccountLinkingTokenRequest.zba(saveAccountLinkingTokenRequest);
        builderZba.zba(this.f20229a);
        SaveAccountLinkingTokenRequest saveAccountLinkingTokenRequestBuild = builderZba.build();
        com.google.android.gms.common.api.internal.x xVarBuilder = com.google.android.gms.common.api.internal.y.builder();
        xVarBuilder.f12809c = new o3.d[]{k.f20240e};
        xVarBuilder.f12807a = new kb.d(this, saveAccountLinkingTokenRequestBuild, 5);
        xVarBuilder.f12808b = false;
        xVarBuilder.f12810d = 1535;
        return doRead(xVarBuilder.a());
    }

    @Override // com.google.android.gms.auth.api.identity.CredentialSavingClient
    public final com.google.android.gms.tasks.h savePassword(SavePasswordRequest savePasswordRequest) {
        com.google.android.gms.common.internal.t.i(savePasswordRequest);
        SavePasswordRequest.Builder builderZba = SavePasswordRequest.zba(savePasswordRequest);
        builderZba.zba(this.f20229a);
        SavePasswordRequest savePasswordRequestBuild = builderZba.build();
        com.google.android.gms.common.api.internal.x xVarBuilder = com.google.android.gms.common.api.internal.y.builder();
        xVarBuilder.f12809c = new o3.d[]{k.f20238c};
        xVarBuilder.f12807a = new kb.d(this, savePasswordRequestBuild, 6);
        xVarBuilder.f12808b = false;
        xVarBuilder.f12810d = 1536;
        return doRead(xVarBuilder.a());
    }

    public e(Context context, zbi zbiVar) {
        super(context, null, f20228b, zbiVar, com.google.android.gms.common.api.m.f12824c);
        this.f20229a = l.a();
    }
}
