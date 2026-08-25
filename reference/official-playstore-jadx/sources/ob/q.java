package ob;

import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public abstract class q {
    public static int a(int i10, int i11, int i12) throws IOException {
        if ((i11 & 8) != 0) {
            i10--;
        }
        if (i12 <= i10) {
            return i10 - i12;
        }
        throw new IOException(androidx.compose.foundation.c.n(i12, i10, "PROTOCOL_ERROR padding ", " > remaining length "));
    }
}
