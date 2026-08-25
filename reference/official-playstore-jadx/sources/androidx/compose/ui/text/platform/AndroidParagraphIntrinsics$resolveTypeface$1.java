package androidx.compose.ui.text.platform;

import android.graphics.Typeface;
import androidx.compose.runtime.State;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.font.TypefaceResult;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\n¢\u0006\u0004\b\n\u0010\u000b"}, d2 = {"<anonymous>", "Landroid/graphics/Typeface;", TtmlNode.ATTR_TTS_FONT_FAMILY, "Landroidx/compose/ui/text/font/FontFamily;", TtmlNode.ATTR_TTS_FONT_WEIGHT, "Landroidx/compose/ui/text/font/FontWeight;", TtmlNode.ATTR_TTS_FONT_STYLE, "Landroidx/compose/ui/text/font/FontStyle;", "fontSynthesis", "Landroidx/compose/ui/text/font/FontSynthesis;", "invoke-DPcqOEQ", "(Landroidx/compose/ui/text/font/FontFamily;Landroidx/compose/ui/text/font/FontWeight;II)Landroid/graphics/Typeface;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class AndroidParagraphIntrinsics$resolveTypeface$1 extends r implements r7.r<FontFamily, FontWeight, FontStyle, FontSynthesis, Typeface> {
    final /* synthetic */ AndroidParagraphIntrinsics this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AndroidParagraphIntrinsics$resolveTypeface$1(AndroidParagraphIntrinsics androidParagraphIntrinsics) {
        super(4);
        this.this$0 = androidParagraphIntrinsics;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        return m5437invokeDPcqOEQ((FontFamily) obj, (FontWeight) obj2, ((FontStyle) obj3).m5305unboximpl(), ((FontSynthesis) obj4).getValue());
    }

    /* JADX INFO: renamed from: invoke-DPcqOEQ, reason: not valid java name */
    public final Typeface m5437invokeDPcqOEQ(FontFamily fontFamily, FontWeight fontWeight, int i10, int i11) {
        State<Object> stateMo5277resolveDPcqOEQ = this.this$0.getFontFamilyResolver().mo5277resolveDPcqOEQ(fontFamily, fontWeight, i10, i11);
        if (stateMo5277resolveDPcqOEQ instanceof TypefaceResult.Immutable) {
            return (Typeface) stateMo5277resolveDPcqOEQ.getValue();
        }
        TypefaceDirtyTrackerLinkedList typefaceDirtyTrackerLinkedList = new TypefaceDirtyTrackerLinkedList(stateMo5277resolveDPcqOEQ, this.this$0.resolvedTypefaces);
        this.this$0.resolvedTypefaces = typefaceDirtyTrackerLinkedList;
        return typefaceDirtyTrackerLinkedList.getTypeface();
    }
}
