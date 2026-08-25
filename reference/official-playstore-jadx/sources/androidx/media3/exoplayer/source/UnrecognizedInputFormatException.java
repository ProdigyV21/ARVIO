package androidx.media3.exoplayer.source;

import android.net.Uri;
import androidx.fragment.app.a2;
import androidx.media3.common.ParserException;
import androidx.media3.extractor.SniffFailure;
import com.google.common.collect.f1;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class UnrecognizedInputFormatException extends ParserException {
    public final h1 sniffFailures;
    public final Uri uri;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated
    public UnrecognizedInputFormatException(String str, Uri uri) {
        this(str, uri, o3.f14078o);
        f1 f1Var = h1.f14020l;
    }

    @Override // androidx.media3.common.ParserException, java.lang.Throwable
    public String getMessage() {
        String message = super.getMessage();
        if (this.sniffFailures.isEmpty()) {
            return message;
        }
        StringBuilder sbP = a2.p(message, "\nsniff failures: ");
        sbP.append(this.sniffFailures);
        return sbP.toString();
    }

    public UnrecognizedInputFormatException(String str, Uri uri, List<? extends SniffFailure> list) {
        super(str, null, false, 1);
        this.uri = uri;
        this.sniffFailures = h1.n(list);
    }
}
