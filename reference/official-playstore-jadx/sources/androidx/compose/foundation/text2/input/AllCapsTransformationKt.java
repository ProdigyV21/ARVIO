package androidx.compose.foundation.text2.input;

import androidx.compose.foundation.text2.input.InputTransformation;
import androidx.compose.ui.text.intl.Locale;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"allCaps", "Landroidx/compose/foundation/text2/input/InputTransformation;", "Landroidx/compose/foundation/text2/input/InputTransformation$Companion;", "locale", "Landroidx/compose/ui/text/intl/Locale;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AllCapsTransformationKt {
    public static final InputTransformation allCaps(InputTransformation.Companion companion, Locale locale) {
        return new AllCapsTransformation(locale);
    }
}
