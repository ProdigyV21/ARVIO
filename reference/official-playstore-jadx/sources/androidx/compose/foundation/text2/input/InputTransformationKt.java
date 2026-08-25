package androidx.compose.foundation.text2.input;

import io.ktor.http.LinkHeader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0007\u001a\u001f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0002\b\u0003¨\u0006\u0004"}, d2 = {"then", "Landroidx/compose/foundation/text2/input/InputTransformation;", LinkHeader.Rel.Next, "thenOrNull", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class InputTransformationKt {
    public static final InputTransformation then(InputTransformation inputTransformation, InputTransformation inputTransformation2) {
        return new FilterChain(inputTransformation, inputTransformation2);
    }

    public static final InputTransformation thenOrNull(InputTransformation inputTransformation, InputTransformation inputTransformation2) {
        return inputTransformation == null ? inputTransformation2 : inputTransformation2 == null ? inputTransformation : then(inputTransformation, inputTransformation2);
    }
}
