package io.ktor.server.plugins;

import androidx.compose.material3.d;
import io.ktor.http.LinkHeader;
import io.ktor.util.internal.ExceptionUtilsJvmKt;
import ka.y;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\n\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B#\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0000H\u0016¢\u0006\u0004\b\n\u0010\u000bR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0005\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0005\u0010\f\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u0010"}, d2 = {"Lio/ktor/server/plugins/ParameterConversionException;", "Lio/ktor/server/plugins/BadRequestException;", "Lka/y;", "", "parameterName", LinkHeader.Parameters.Type, "", "cause", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V", "createCopy", "()Lio/ktor/server/plugins/ParameterConversionException;", "Ljava/lang/String;", "getParameterName", "()Ljava/lang/String;", "getType", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ParameterConversionException extends BadRequestException implements y<ParameterConversionException> {
    private final String parameterName;
    private final String type;

    public /* synthetic */ ParameterConversionException(String str, String str2, Throwable th, int i10, h hVar) {
        this(str, str2, (i10 & 4) != 0 ? null : th);
    }

    public final String getParameterName() {
        return this.parameterName;
    }

    public final String getType() {
        return this.type;
    }

    public ParameterConversionException(String str, String str2, Throwable th) {
        super(d.n("Request parameter ", str, " couldn't be parsed/converted to ", str2), th);
        this.parameterName = str;
        this.type = str2;
    }

    @Override // ka.y
    public ParameterConversionException createCopy() {
        ParameterConversionException parameterConversionException = new ParameterConversionException(this.parameterName, this.type, this);
        ExceptionUtilsJvmKt.initCauseBridge(parameterConversionException, this);
        return parameterConversionException;
    }
}
