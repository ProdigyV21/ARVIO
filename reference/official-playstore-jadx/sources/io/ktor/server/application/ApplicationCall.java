package io.ktor.server.application;

import androidx.media3.common.MimeTypes;
import io.ktor.http.Parameters;
import io.ktor.server.request.ApplicationRequest;
import io.ktor.server.response.ApplicationResponse;
import io.ktor.util.Attributes;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0012\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lio/ktor/server/application/ApplicationCall;", "", MimeTypes.BASE_TYPE_APPLICATION, "Lio/ktor/server/application/Application;", "getApplication", "()Lio/ktor/server/application/Application;", "attributes", "Lio/ktor/util/Attributes;", "getAttributes", "()Lio/ktor/util/Attributes;", "parameters", "Lio/ktor/http/Parameters;", "getParameters", "()Lio/ktor/http/Parameters;", "request", "Lio/ktor/server/request/ApplicationRequest;", "getRequest", "()Lio/ktor/server/request/ApplicationRequest;", "response", "Lio/ktor/server/response/ApplicationResponse;", "getResponse", "()Lio/ktor/server/response/ApplicationResponse;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ApplicationCall {
    Application getApplication();

    Attributes getAttributes();

    Parameters getParameters();

    ApplicationRequest getRequest();

    ApplicationResponse getResponse();
}
