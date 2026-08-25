package io.ktor.util.date;

import androidx.compose.foundation.c;
import androidx.fragment.app.a2;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00060\u0001j\u0002`\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lio/ktor/util/date/InvalidDateStringException;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "data", "", "at", "", "pattern", "(Ljava/lang/String;ILjava/lang/String;)V", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class InvalidDateStringException extends IllegalStateException {
    public InvalidDateStringException(String str, int i10, String str2) {
        super(c.u(a2.q("Failed to parse date string: \"", str, "\" at index ", i10, ". Pattern: \""), str2, '\"'));
    }
}
