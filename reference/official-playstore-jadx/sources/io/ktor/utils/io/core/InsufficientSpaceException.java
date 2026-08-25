package io.ktor.utils.io.core;

import androidx.compose.foundation.c;
import androidx.compose.material3.d;
import androidx.fragment.app.a2;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006B\u001f\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\tB\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\n\u0012\u0006\u0010\u0005\u001a\u00020\n¢\u0006\u0002\u0010\u000bB\u000f\u0012\b\b\u0002\u0010\f\u001a\u00020\b¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lio/ktor/utils/io/core/InsufficientSpaceException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", ContentDisposition.Parameters.Size, "", "availableSpace", "(II)V", ContentDisposition.Parameters.Name, "", "(Ljava/lang/String;II)V", "", "(JJ)V", "message", "(Ljava/lang/String;)V", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class InsufficientSpaceException extends Exception {
    /* JADX WARN: Multi-variable type inference failed */
    public InsufficientSpaceException() {
        this((String) null, 1, (h) (0 == true ? 1 : 0));
    }

    public InsufficientSpaceException(String str) {
        super(str);
    }

    public /* synthetic */ InsufficientSpaceException(String str, int i10, h hVar) {
        this((i10 & 1) != 0 ? "Not enough free space" : str);
    }

    public InsufficientSpaceException(int i10, int i11) {
        this(c.s("Not enough free space to write ", i10, " bytes, available ", i11, " bytes."));
    }

    public InsufficientSpaceException(String str, int i10, int i11) {
        this(d.j(i11, " bytes.", a2.q("Not enough free space to write ", str, " of ", i10, " bytes, available ")));
    }

    public InsufficientSpaceException(long j10, long j11) {
        this(a0.c.j(j11, " bytes.", d.r(j10, "Not enough free space to write ", " bytes, available ")));
    }
}
