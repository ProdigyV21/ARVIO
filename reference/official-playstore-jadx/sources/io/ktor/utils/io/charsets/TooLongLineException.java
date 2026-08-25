package io.ktor.utils.io.charsets;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lio/ktor/utils/io/charsets/TooLongLineException;", "Lio/ktor/utils/io/charsets/MalformedInputException;", "message", "", "(Ljava/lang/String;)V", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TooLongLineException extends MalformedInputException {
    public TooLongLineException(String str) {
        super(str);
    }
}
