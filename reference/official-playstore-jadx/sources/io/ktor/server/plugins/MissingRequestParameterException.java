package io.ktor.server.plugins;

import a0.c;
import io.ktor.util.internal.ExceptionUtilsJvmKt;
import ka.y;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0000H\u0016¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\t\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lio/ktor/server/plugins/MissingRequestParameterException;", "Lio/ktor/server/plugins/BadRequestException;", "Lka/y;", "", "parameterName", "<init>", "(Ljava/lang/String;)V", "createCopy", "()Lio/ktor/server/plugins/MissingRequestParameterException;", "Ljava/lang/String;", "getParameterName", "()Ljava/lang/String;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MissingRequestParameterException extends BadRequestException implements y<MissingRequestParameterException> {
    private final String parameterName;

    public MissingRequestParameterException(String str) {
        super(c.l("Request parameter ", str, " is missing"), null, 2, null);
        this.parameterName = str;
    }

    public final String getParameterName() {
        return this.parameterName;
    }

    @Override // ka.y
    public MissingRequestParameterException createCopy() {
        MissingRequestParameterException missingRequestParameterException = new MissingRequestParameterException(this.parameterName);
        ExceptionUtilsJvmKt.initCauseBridge(missingRequestParameterException, this);
        return missingRequestParameterException;
    }
}
