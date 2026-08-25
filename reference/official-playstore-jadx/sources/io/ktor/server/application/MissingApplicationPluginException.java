package io.ktor.server.application;

import io.ktor.util.AttributeKey;
import io.ktor.util.internal.ExceptionUtilsJvmKt;
import ka.y;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020\u00000\u0003B\u0013\u0012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0000H\u0016¢\u0006\u0004\b\b\u0010\tR\u001b\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0010\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lio/ktor/server/application/MissingApplicationPluginException;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "Lka/y;", "Lio/ktor/util/AttributeKey;", "key", "<init>", "(Lio/ktor/util/AttributeKey;)V", "createCopy", "()Lio/ktor/server/application/MissingApplicationPluginException;", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "", "getMessage", "()Ljava/lang/String;", "message", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MissingApplicationPluginException extends IllegalStateException implements y<MissingApplicationPluginException> {
    private final AttributeKey<?> key;

    public MissingApplicationPluginException(AttributeKey<?> attributeKey) {
        this.key = attributeKey;
    }

    public final AttributeKey<?> getKey() {
        return this.key;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return "Application plugin " + this.key.getName() + " is not installed";
    }

    @Override // ka.y
    public MissingApplicationPluginException createCopy() {
        MissingApplicationPluginException missingApplicationPluginException = new MissingApplicationPluginException(this.key);
        ExceptionUtilsJvmKt.initCauseBridge(missingApplicationPluginException, this);
        return missingApplicationPluginException;
    }
}
