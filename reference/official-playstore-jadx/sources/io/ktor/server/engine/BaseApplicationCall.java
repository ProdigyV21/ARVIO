package io.ktor.server.engine;

import androidx.media3.common.MimeTypes;
import io.ktor.http.Parameters;
import io.ktor.server.application.Application;
import io.ktor.server.application.ApplicationCall;
import io.ktor.util.Attributes;
import io.ktor.util.AttributesJvmKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0004¢\u0006\u0004\b\t\u0010\nR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0016\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u001a\u001a\u00020\u00178&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0007\u001a\u00020\u00068&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Lio/ktor/server/engine/BaseApplicationCall;", "Lio/ktor/server/application/ApplicationCall;", "Lio/ktor/server/application/Application;", MimeTypes.BASE_TYPE_APPLICATION, "<init>", "(Lio/ktor/server/application/Application;)V", "Lio/ktor/server/engine/BaseApplicationResponse;", "response", "Lx6/t0;", "putResponseAttribute", "(Lio/ktor/server/engine/BaseApplicationResponse;)V", "Lio/ktor/server/application/Application;", "getApplication", "()Lio/ktor/server/application/Application;", "Lio/ktor/util/Attributes;", "attributes", "Lio/ktor/util/Attributes;", "getAttributes", "()Lio/ktor/util/Attributes;", "Lio/ktor/http/Parameters;", "getParameters", "()Lio/ktor/http/Parameters;", "parameters", "Lio/ktor/server/engine/BaseApplicationRequest;", "getRequest", "()Lio/ktor/server/engine/BaseApplicationRequest;", "request", "getResponse", "()Lio/ktor/server/engine/BaseApplicationResponse;", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class BaseApplicationCall implements ApplicationCall {
    private final Application application;
    private final Attributes attributes = AttributesJvmKt.Attributes$default(false, 1, null);

    public BaseApplicationCall(Application application) {
        this.application = application;
    }

    public static /* synthetic */ void putResponseAttribute$default(BaseApplicationCall baseApplicationCall, BaseApplicationResponse baseApplicationResponse, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: putResponseAttribute");
        }
        if ((i10 & 1) != 0) {
            baseApplicationResponse = baseApplicationCall.getResponse();
        }
        baseApplicationCall.putResponseAttribute(baseApplicationResponse);
    }

    @Override // io.ktor.server.application.ApplicationCall
    public final Application getApplication() {
        return this.application;
    }

    @Override // io.ktor.server.application.ApplicationCall
    public final Attributes getAttributes() {
        return this.attributes;
    }

    @Override // io.ktor.server.application.ApplicationCall
    public Parameters getParameters() {
        return getRequest().getQueryParameters();
    }

    @Override // io.ktor.server.application.ApplicationCall
    public abstract BaseApplicationRequest getRequest();

    @Override // io.ktor.server.application.ApplicationCall
    public abstract BaseApplicationResponse getResponse();

    public final void putResponseAttribute(BaseApplicationResponse response) {
        this.attributes.put(BaseApplicationResponse.INSTANCE.getEngineResponseAttributeKey(), response);
    }
}
