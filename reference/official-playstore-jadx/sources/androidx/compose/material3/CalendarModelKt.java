package androidx.compose.material3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.text.k;
import kotlin.text.o;
import kotlin.text.u;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"DaysInWeek", "", "MillisecondsIn24Hours", "", "datePatternAsInputFormat", "Landroidx/compose/material3/DateInputFormat;", "localeFormat", "", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CalendarModelKt {
    public static final int DaysInWeek = 7;
    public static final long MillisecondsIn24Hours = 86400000;

    public static final DateInputFormat datePatternAsInputFormat(String str) {
        String strS0 = o.s0(u.O(Pattern.compile("y{1,4}").matcher(Pattern.compile("M{1,2}").matcher(Pattern.compile("d{1,2}").matcher(Pattern.compile("[^dMy/\\-.]").matcher(str).replaceAll("")).replaceAll("dd")).replaceAll("MM")).replaceAll("yyyy"), "My", "M/y", false), ".");
        Matcher matcher = Pattern.compile("[/\\-.]").matcher(strS0);
        int i10 = (!matcher.find(0) ? null : new k(matcher, strS0)).f19948c.a(0).f19944b.f22619i;
        return new DateInputFormat(strS0, strS0.substring(i10, i10 + 1).charAt(0));
    }
}
