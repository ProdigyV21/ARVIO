package com.arflix.tv.ui.theme;

import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontFamilyKt;
import androidx.compose.ui.text.font.FontKt;
import androidx.compose.ui.text.font.FontWeight;
import com.arflix.tv.R;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"InterFontFamily", "Landroidx/compose/ui/text/font/FontFamily;", "getInterFontFamily", "()Landroidx/compose/ui/text/font/FontFamily;", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class FontsKt {
    private static final FontFamily InterFontFamily;

    static {
        int i10 = R.font.inter_variablefont_opsz_wght;
        FontWeight.Companion companion = FontWeight.INSTANCE;
        InterFontFamily = FontFamilyKt.FontFamily(FontKt.m5285FontYpTlLL0$default(i10, companion.getNormal(), 0, 0, 12, null), FontKt.m5285FontYpTlLL0$default(R.font.inter_variablefont_opsz_wght, companion.getMedium(), 0, 0, 12, null), FontKt.m5285FontYpTlLL0$default(R.font.inter_variablefont_opsz_wght, companion.getSemiBold(), 0, 0, 12, null), FontKt.m5285FontYpTlLL0$default(R.font.inter_variablefont_opsz_wght, companion.getBold(), 0, 0, 12, null), FontKt.m5285FontYpTlLL0$default(R.font.inter_variablefont_opsz_wght, companion.getBlack(), 0, 0, 12, null));
    }

    public static final FontFamily getInterFontFamily() {
        return InterFontFamily;
    }
}
