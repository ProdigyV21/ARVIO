package io.ktor.utils.io;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"CLOSED_SUCCESS", "Lio/ktor/utils/io/CloseElement;", "getCLOSED_SUCCESS", "()Lio/ktor/utils/io/CloseElement;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CloseElementKt {
    private static final CloseElement CLOSED_SUCCESS = new CloseElement(null);

    public static final CloseElement getCLOSED_SUCCESS() {
        return CLOSED_SUCCESS;
    }
}
