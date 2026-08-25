package androidx.compose.material3;

import android.content.res.Configuration;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u001a\u0010\u0000\u001a\u00020\u00018AX\u0080\u0004¢\u0006\f\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"defaultTimePickerLayoutType", "Landroidx/compose/material3/TimePickerLayoutType;", "getDefaultTimePickerLayoutType$annotations", "()V", "getDefaultTimePickerLayoutType", "(Landroidx/compose/runtime/Composer;I)I", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TimePicker_androidKt {
    public static final int getDefaultTimePickerLayoutType(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1979410629, i10, -1, "androidx.compose.material3.<get-defaultTimePickerLayoutType> (TimePicker.android.kt:25)");
        }
        Configuration configuration = (Configuration) composer.consume(AndroidCompositionLocals_androidKt.getLocalConfiguration());
        int iM2201getHorizontalQJTpgSE = configuration.screenHeightDp < configuration.screenWidthDp ? TimePickerLayoutType.INSTANCE.m2201getHorizontalQJTpgSE() : TimePickerLayoutType.INSTANCE.m2202getVerticalQJTpgSE();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return iM2201getHorizontalQJTpgSE;
    }

    public static /* synthetic */ void getDefaultTimePickerLayoutType$annotations() {
    }
}
