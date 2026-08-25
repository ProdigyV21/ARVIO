package androidx.compose.foundation.text2.input.internal;

import android.text.TextUtils;
import androidx.compose.foundation.text2.input.TextFieldCharSequence;
import androidx.compose.foundation.text2.input.TextFieldCharSequenceKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0010\r\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a3\u0010\b\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"", "", "destination", "", "destinationOffset", "startIndex", "endIndex", "Lx6/t0;", "toCharArray", "(Ljava/lang/CharSequence;[CIII)V", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ToCharArray_androidKt {
    public static final void toCharArray(CharSequence charSequence, char[] cArr, int i10, int i11, int i12) {
        if (charSequence instanceof TextFieldCharSequence) {
            TextFieldCharSequenceKt.toCharArray((TextFieldCharSequence) charSequence, cArr, i10, i11, i12);
        } else {
            TextUtils.getChars(charSequence, i11, i12, cArr, i10);
        }
    }
}
