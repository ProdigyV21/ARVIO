package androidx.compose.material3;

import android.content.Context;
import android.text.format.DateFormat;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\"\u0014\u0010\u0000\u001a\u00020\u00018AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0002¨\u0006\u0003"}, d2 = {"is24HourFormat", "", "(Landroidx/compose/runtime/Composer;I)Z", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TimeFormat_androidKt {
    public static final boolean is24HourFormat(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-972868615, i10, -1, "androidx.compose.material3.<get-is24HourFormat> (TimeFormat.android.kt:25)");
        }
        boolean zIs24HourFormat = DateFormat.is24HourFormat((Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext()));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return zIs24HourFormat;
    }
}
