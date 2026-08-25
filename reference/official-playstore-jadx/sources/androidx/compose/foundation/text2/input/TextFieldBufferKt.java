package androidx.compose.foundation.text2.input;

import androidx.compose.foundation.text2.input.TextFieldBuffer;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.p;
import r7.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001a#\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u0006\u0010\u0007\u001a#\u0010\n\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\n\u0010\u000b\u001a\u0013\u0010\f\u001a\u00020\u0005*\u00020\u0000H\u0007¢\u0006\u0004\b\f\u0010\r\u001a\u0013\u0010\u000e\u001a\u00020\u0005*\u00020\u0000H\u0007¢\u0006\u0004\b\u000e\u0010\r\u001aL\u0010\u0017\u001a\u00020\u0005*\u00020\u000f26\u0010\u0016\u001a2\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00050\u0010H\u0087\b¢\u0006\u0004\b\u0017\u0010\u0018\u001aL\u0010\u0019\u001a\u00020\u0005*\u00020\u000f26\u0010\u0016\u001a2\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00050\u0010H\u0087\b¢\u0006\u0004\b\u0019\u0010\u0018\u001a\u0082\u0001\u0010#\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001a2`\u0010\"\u001a\\\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u00050\u001dH\u0080\b¢\u0006\u0004\b#\u0010$¨\u0006%"}, d2 = {"Landroidx/compose/foundation/text2/input/TextFieldBuffer;", "", "index", "", "text", "Lx6/t0;", "insert", "(Landroidx/compose/foundation/text2/input/TextFieldBuffer;ILjava/lang/String;)V", TtmlNode.START, TtmlNode.END, "delete", "(Landroidx/compose/foundation/text2/input/TextFieldBuffer;II)V", "placeCursorAtEnd", "(Landroidx/compose/foundation/text2/input/TextFieldBuffer;)V", "selectAll", "Landroidx/compose/foundation/text2/input/TextFieldBuffer$ChangeList;", "Lkotlin/Function2;", "Landroidx/compose/ui/text/TextRange;", "Lx6/y;", ContentDisposition.Parameters.Name, "range", "originalRange", "block", "forEachChange", "(Landroidx/compose/foundation/text2/input/TextFieldBuffer$ChangeList;Lr7/p;)V", "forEachChangeReversed", "", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "Lkotlin/Function4;", "aPrefixStart", "aSuffixStart", "bPrefixStart", "bSuffixStart", "onFound", "findCommonPrefixAndSuffix", "(Ljava/lang/CharSequence;Ljava/lang/CharSequence;Lr7/r;)V", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextFieldBufferKt {
    public static final void delete(TextFieldBuffer textFieldBuffer, int i10, int i11) {
        textFieldBuffer.replace(i10, i11, "");
    }

    public static final void findCommonPrefixAndSuffix(CharSequence charSequence, CharSequence charSequence2, r<? super Integer, ? super Integer, ? super Integer, ? super Integer, t0> rVar) {
        int i10;
        int length = charSequence.length();
        int length2 = charSequence2.length();
        int i11 = 0;
        if (charSequence.length() <= 0 || charSequence2.length() <= 0) {
            i10 = 0;
        } else {
            int i12 = 0;
            i10 = 0;
            boolean z = false;
            while (true) {
                if (i11 == 0) {
                    if (charSequence.charAt(i12) == charSequence2.charAt(i10)) {
                        i12++;
                        i10++;
                    } else {
                        i11 = 1;
                    }
                }
                if (!z) {
                    if (charSequence.charAt(length - 1) == charSequence2.charAt(length2 - 1)) {
                        length--;
                        length2--;
                    } else {
                        z = true;
                    }
                }
                if (i12 >= length || i10 >= length2 || (i11 != 0 && z)) {
                    break;
                }
            }
            i11 = i12;
        }
        if (i11 < length || i10 < length2) {
            rVar.invoke(Integer.valueOf(i11), Integer.valueOf(length), Integer.valueOf(i10), Integer.valueOf(length2));
        }
    }

    public static final void forEachChange(TextFieldBuffer.ChangeList changeList, p<? super TextRange, ? super TextRange, t0> pVar) {
        for (int i10 = 0; i10 < changeList.getChangeCount(); i10++) {
            pVar.invoke(TextRange.m5180boximpl(changeList.mo1054getRangejx7JFs(i10)), TextRange.m5180boximpl(changeList.mo1053getOriginalRangejx7JFs(i10)));
        }
    }

    public static final void forEachChangeReversed(TextFieldBuffer.ChangeList changeList, p<? super TextRange, ? super TextRange, t0> pVar) {
        for (int changeCount = changeList.getChangeCount() - 1; changeCount >= 0; changeCount--) {
            pVar.invoke(TextRange.m5180boximpl(changeList.mo1054getRangejx7JFs(changeCount)), TextRange.m5180boximpl(changeList.mo1053getOriginalRangejx7JFs(changeCount)));
        }
    }

    public static final void insert(TextFieldBuffer textFieldBuffer, int i10, String str) {
        textFieldBuffer.replace(i10, i10, str);
    }

    public static final void placeCursorAtEnd(TextFieldBuffer textFieldBuffer) {
        textFieldBuffer.placeCursorBeforeCharAt(textFieldBuffer.getLength());
    }

    public static final void selectAll(TextFieldBuffer textFieldBuffer) {
        textFieldBuffer.m1061selectCharsIn5zctL8(TextRangeKt.TextRange(0, textFieldBuffer.getLength()));
    }
}
