package kotlin.reflect.jvm.internal.impl.protobuf;

/* JADX INFO: loaded from: classes5.dex */
public class UninitializedMessageException extends RuntimeException {
    public UninitializedMessageException() {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }
}
