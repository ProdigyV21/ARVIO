package androidx.compose.ui.text.platform.extensions;

import android.text.Spannable;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.Placeholder;
import androidx.compose.ui.text.PlaceholderVerticalAlign;
import androidx.compose.ui.text.android.style.PlaceholderSpan;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitType;
import androidx.emoji2.text.j0;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a/\u0010\b\u001a\u00020\u0007*\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0005H\u0000¢\u0006\u0004\b\b\u0010\t\u001a3\u0010\u000e\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u000e\u0010\u000f\"\u001e\u0010\u0015\u001a\u00020\u000b*\u00020\u00108BX\u0082\u0004¢\u0006\f\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012\"\u001e\u0010\u001b\u001a\u00020\u000b*\u00020\u00168BX\u0082\u0004¢\u0006\f\u0012\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001c"}, d2 = {"Landroid/text/Spannable;", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/Placeholder;", "placeholders", "Landroidx/compose/ui/unit/Density;", "density", "Lx6/t0;", "setPlaceholders", "(Landroid/text/Spannable;Ljava/util/List;Landroidx/compose/ui/unit/Density;)V", "placeholder", "", TtmlNode.START, TtmlNode.END, "setPlaceholder", "(Landroid/text/Spannable;Landroidx/compose/ui/text/Placeholder;IILandroidx/compose/ui/unit/Density;)V", "Landroidx/compose/ui/unit/TextUnit;", "getSpanUnit--R2X_6o", "(J)I", "getSpanUnit--R2X_6o$annotations", "(J)V", "spanUnit", "Landroidx/compose/ui/text/PlaceholderVerticalAlign;", "getSpanVerticalAlign-do9X-Gg", "(I)I", "getSpanVerticalAlign-do9X-Gg$annotations", "(I)V", "spanVerticalAlign", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PlaceholderExtensions_androidKt {
    /* JADX INFO: renamed from: getSpanUnit--R2X_6o, reason: not valid java name */
    private static final int m5449getSpanUnitR2X_6o(long j10) {
        long jM5864getTypeUIouoOA = TextUnit.m5864getTypeUIouoOA(j10);
        TextUnitType.Companion companion = TextUnitType.INSTANCE;
        if (TextUnitType.m5893equalsimpl0(jM5864getTypeUIouoOA, companion.m5898getSpUIouoOA())) {
            return 0;
        }
        return TextUnitType.m5893equalsimpl0(jM5864getTypeUIouoOA, companion.m5897getEmUIouoOA()) ? 1 : 2;
    }

    /* JADX INFO: renamed from: getSpanUnit--R2X_6o$annotations, reason: not valid java name */
    private static /* synthetic */ void m5450getSpanUnitR2X_6o$annotations(long j10) {
    }

    /* JADX INFO: renamed from: getSpanVerticalAlign-do9X-Gg, reason: not valid java name */
    private static final int m5451getSpanVerticalAligndo9XGg(int i10) {
        PlaceholderVerticalAlign.Companion companion = PlaceholderVerticalAlign.INSTANCE;
        if (PlaceholderVerticalAlign.m5119equalsimpl0(i10, companion.m5123getAboveBaselineJ6kI3mc())) {
            return 0;
        }
        if (PlaceholderVerticalAlign.m5119equalsimpl0(i10, companion.m5129getTopJ6kI3mc())) {
            return 1;
        }
        if (PlaceholderVerticalAlign.m5119equalsimpl0(i10, companion.m5124getBottomJ6kI3mc())) {
            return 2;
        }
        if (PlaceholderVerticalAlign.m5119equalsimpl0(i10, companion.m5125getCenterJ6kI3mc())) {
            return 3;
        }
        if (PlaceholderVerticalAlign.m5119equalsimpl0(i10, companion.m5128getTextTopJ6kI3mc())) {
            return 4;
        }
        if (PlaceholderVerticalAlign.m5119equalsimpl0(i10, companion.m5126getTextBottomJ6kI3mc())) {
            return 5;
        }
        if (PlaceholderVerticalAlign.m5119equalsimpl0(i10, companion.m5127getTextCenterJ6kI3mc())) {
            return 6;
        }
        throw new IllegalStateException("Invalid PlaceholderVerticalAlign");
    }

    /* JADX INFO: renamed from: getSpanVerticalAlign-do9X-Gg$annotations, reason: not valid java name */
    private static /* synthetic */ void m5452getSpanVerticalAligndo9XGg$annotations(int i10) {
    }

    private static final void setPlaceholder(Spannable spannable, Placeholder placeholder, int i10, int i11, Density density) {
        for (Object obj : spannable.getSpans(i10, i11, j0.class)) {
            spannable.removeSpan((j0) obj);
        }
        SpannableExtensions_androidKt.setSpan(spannable, new PlaceholderSpan(TextUnit.m5865getValueimpl(placeholder.getWidth()), m5449getSpanUnitR2X_6o(placeholder.getWidth()), TextUnit.m5865getValueimpl(placeholder.getHeight()), m5449getSpanUnitR2X_6o(placeholder.getHeight()), density.getDensity() * density.getFontScale(), m5451getSpanVerticalAligndo9XGg(placeholder.getPlaceholderVerticalAlign())), i10, i11);
    }

    public static final void setPlaceholders(Spannable spannable, List<AnnotatedString.Range<Placeholder>> list, Density density) {
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            AnnotatedString.Range<Placeholder> range = list.get(i10);
            setPlaceholder(spannable, range.component1(), range.getStart(), range.getEnd(), density);
        }
    }
}
