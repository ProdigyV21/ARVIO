package androidx.compose.ui.text;

import androidx.compose.ui.text.style.Hyphens;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.TextUnit;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b(\b\u0007\u0018\u00002\u00020\u0001Bi\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u0014\u0010\u0015Bs\b\u0017\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u0014\u0010\u0016B7\b\u0017\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u0014\u0010\u0017BO\b\u0017\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0014\u0010\u0018Bg\b\u0017\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u0014\u0010\u0019J\u001b\u0010\u001b\u001a\u00020\u00002\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0000H\u0007¢\u0006\u0004\b\u001b\u0010\u001cJ\u0018\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u0000H\u0087\u0002¢\u0006\u0004\b\u001d\u0010\u001cJ@\u0010 \u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJX\u0010 \u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\fH\u0007ø\u0001\u0000¢\u0006\u0004\b!\u0010\"Jp\u0010 \u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0007ø\u0001\u0000¢\u0006\u0004\b#\u0010$J|\u0010 \u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0007ø\u0001\u0000¢\u0006\u0004\b%\u0010&Jr\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012ø\u0001\u0000¢\u0006\u0004\b'\u0010(J\u001a\u0010*\u001a\u00020)2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b*\u0010+J\u000f\u0010-\u001a\u00020,H\u0016¢\u0006\u0004\b-\u0010.J\u000f\u00100\u001a\u00020/H\u0016¢\u0006\u0004\b0\u00101R\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0003\u00102\u001a\u0004\b3\u0010.R\u001d\u0010\u0005\u001a\u00020\u00048\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0005\u00102\u001a\u0004\b4\u0010.R\u001d\u0010\u0007\u001a\u00020\u00068\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0007\u00105\u001a\u0004\b6\u00107R\u0019\u0010\t\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\t\u00108\u001a\u0004\b9\u0010:R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010;\u001a\u0004\b<\u0010=R\u0019\u0010\r\u001a\u0004\u0018\u00010\f8\u0006¢\u0006\f\n\u0004\b\r\u0010>\u001a\u0004\b?\u0010@R\u001d\u0010\u000f\u001a\u00020\u000e8\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u000f\u00102\u001a\u0004\bA\u0010.R\u001d\u0010\u0011\u001a\u00020\u00108\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0011\u00102\u001a\u0004\bB\u0010.R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010C\u001a\u0004\bD\u0010ER\"\u0010J\u001a\u0004\u0018\u00010\u00028GX\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\bH\u0010I\u001a\u0004\bF\u0010GR\"\u0010N\u001a\u0004\u0018\u00010\u00048GX\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\bM\u0010I\u001a\u0004\bK\u0010LR\"\u0010R\u001a\u0004\u0018\u00010\u00108GX\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\bQ\u0010I\u001a\u0004\bO\u0010PR\"\u0010V\u001a\u0004\u0018\u00010\u000e8GX\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\bU\u0010I\u001a\u0004\bS\u0010T\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006W"}, d2 = {"Landroidx/compose/ui/text/ParagraphStyle;", "", "Landroidx/compose/ui/text/style/TextAlign;", TtmlNode.ATTR_TTS_TEXT_ALIGN, "Landroidx/compose/ui/text/style/TextDirection;", "textDirection", "Landroidx/compose/ui/unit/TextUnit;", "lineHeight", "Landroidx/compose/ui/text/style/TextIndent;", "textIndent", "Landroidx/compose/ui/text/PlatformParagraphStyle;", "platformStyle", "Landroidx/compose/ui/text/style/LineHeightStyle;", "lineHeightStyle", "Landroidx/compose/ui/text/style/LineBreak;", "lineBreak", "Landroidx/compose/ui/text/style/Hyphens;", "hyphens", "Landroidx/compose/ui/text/style/TextMotion;", "textMotion", "<init>", "(IIJLandroidx/compose/ui/text/style/TextIndent;Landroidx/compose/ui/text/PlatformParagraphStyle;Landroidx/compose/ui/text/style/LineHeightStyle;IILandroidx/compose/ui/text/style/TextMotion;Lkotlin/jvm/internal/h;)V", "(Landroidx/compose/ui/text/style/TextAlign;Landroidx/compose/ui/text/style/TextDirection;JLandroidx/compose/ui/text/style/TextIndent;Landroidx/compose/ui/text/PlatformParagraphStyle;Landroidx/compose/ui/text/style/LineHeightStyle;Landroidx/compose/ui/text/style/LineBreak;Landroidx/compose/ui/text/style/Hyphens;Landroidx/compose/ui/text/style/TextMotion;Lkotlin/jvm/internal/h;)V", "(Landroidx/compose/ui/text/style/TextAlign;Landroidx/compose/ui/text/style/TextDirection;JLandroidx/compose/ui/text/style/TextIndent;Lkotlin/jvm/internal/h;)V", "(Landroidx/compose/ui/text/style/TextAlign;Landroidx/compose/ui/text/style/TextDirection;JLandroidx/compose/ui/text/style/TextIndent;Landroidx/compose/ui/text/PlatformParagraphStyle;Landroidx/compose/ui/text/style/LineHeightStyle;Lkotlin/jvm/internal/h;)V", "(Landroidx/compose/ui/text/style/TextAlign;Landroidx/compose/ui/text/style/TextDirection;JLandroidx/compose/ui/text/style/TextIndent;Landroidx/compose/ui/text/PlatformParagraphStyle;Landroidx/compose/ui/text/style/LineHeightStyle;Landroidx/compose/ui/text/style/LineBreak;Landroidx/compose/ui/text/style/Hyphens;Lkotlin/jvm/internal/h;)V", "other", "merge", "(Landroidx/compose/ui/text/ParagraphStyle;)Landroidx/compose/ui/text/ParagraphStyle;", "plus", "copy-Elsmlbk", "(Landroidx/compose/ui/text/style/TextAlign;Landroidx/compose/ui/text/style/TextDirection;JLandroidx/compose/ui/text/style/TextIndent;)Landroidx/compose/ui/text/ParagraphStyle;", "copy", "copy-xPh5V4g", "(Landroidx/compose/ui/text/style/TextAlign;Landroidx/compose/ui/text/style/TextDirection;JLandroidx/compose/ui/text/style/TextIndent;Landroidx/compose/ui/text/PlatformParagraphStyle;Landroidx/compose/ui/text/style/LineHeightStyle;)Landroidx/compose/ui/text/ParagraphStyle;", "copy-ciSxzs0", "(Landroidx/compose/ui/text/style/TextAlign;Landroidx/compose/ui/text/style/TextDirection;JLandroidx/compose/ui/text/style/TextIndent;Landroidx/compose/ui/text/PlatformParagraphStyle;Landroidx/compose/ui/text/style/LineHeightStyle;Landroidx/compose/ui/text/style/LineBreak;Landroidx/compose/ui/text/style/Hyphens;)Landroidx/compose/ui/text/ParagraphStyle;", "copy-NH1kkwU", "(Landroidx/compose/ui/text/style/TextAlign;Landroidx/compose/ui/text/style/TextDirection;JLandroidx/compose/ui/text/style/TextIndent;Landroidx/compose/ui/text/PlatformParagraphStyle;Landroidx/compose/ui/text/style/LineHeightStyle;Landroidx/compose/ui/text/style/LineBreak;Landroidx/compose/ui/text/style/Hyphens;Landroidx/compose/ui/text/style/TextMotion;)Landroidx/compose/ui/text/ParagraphStyle;", "copy-ykzQM6k", "(IIJLandroidx/compose/ui/text/style/TextIndent;Landroidx/compose/ui/text/PlatformParagraphStyle;Landroidx/compose/ui/text/style/LineHeightStyle;IILandroidx/compose/ui/text/style/TextMotion;)Landroidx/compose/ui/text/ParagraphStyle;", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "I", "getTextAlign-e0LSkKk", "getTextDirection-s_7X-co", "J", "getLineHeight-XSAIIZE", "()J", "Landroidx/compose/ui/text/style/TextIndent;", "getTextIndent", "()Landroidx/compose/ui/text/style/TextIndent;", "Landroidx/compose/ui/text/PlatformParagraphStyle;", "getPlatformStyle", "()Landroidx/compose/ui/text/PlatformParagraphStyle;", "Landroidx/compose/ui/text/style/LineHeightStyle;", "getLineHeightStyle", "()Landroidx/compose/ui/text/style/LineHeightStyle;", "getLineBreak-rAG3T2k", "getHyphens-vmbZdU8", "Landroidx/compose/ui/text/style/TextMotion;", "getTextMotion", "()Landroidx/compose/ui/text/style/TextMotion;", "getTextAlign-buA522U", "()Landroidx/compose/ui/text/style/TextAlign;", "getTextAlign-buA522U$annotations", "()V", "deprecated_boxing_textAlign", "getTextDirection-mmuk1to", "()Landroidx/compose/ui/text/style/TextDirection;", "getTextDirection-mmuk1to$annotations", "deprecated_boxing_textDirection", "getHyphens-EaSxIns", "()Landroidx/compose/ui/text/style/Hyphens;", "getHyphens-EaSxIns$annotations", "deprecated_boxing_hyphens", "getLineBreak-LgCVezo", "()Landroidx/compose/ui/text/style/LineBreak;", "getLineBreak-LgCVezo$annotations", "deprecated_boxing_lineBreak", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ParagraphStyle {
    public static final int $stable = 0;
    private final int hyphens;
    private final int lineBreak;
    private final long lineHeight;
    private final LineHeightStyle lineHeightStyle;
    private final PlatformParagraphStyle platformStyle;
    private final int textAlign;
    private final int textDirection;
    private final TextIndent textIndent;
    private final TextMotion textMotion;

    public /* synthetic */ ParagraphStyle(int i10, int i11, long j10, TextIndent textIndent, PlatformParagraphStyle platformParagraphStyle, LineHeightStyle lineHeightStyle, int i12, int i13, TextMotion textMotion, h hVar) {
        this(i10, i11, j10, textIndent, platformParagraphStyle, lineHeightStyle, i12, i13, textMotion);
    }

    /* JADX INFO: renamed from: copy-Elsmlbk$default, reason: not valid java name */
    public static /* synthetic */ ParagraphStyle m5087copyElsmlbk$default(ParagraphStyle paragraphStyle, TextAlign textAlign, TextDirection textDirection, long j10, TextIndent textIndent, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            textAlign = TextAlign.m5575boximpl(paragraphStyle.textAlign);
        }
        if ((i10 & 2) != 0) {
            textDirection = TextDirection.m5589boximpl(paragraphStyle.textDirection);
        }
        if ((i10 & 4) != 0) {
            j10 = paragraphStyle.lineHeight;
        }
        if ((i10 & 8) != 0) {
            textIndent = paragraphStyle.textIndent;
        }
        TextIndent textIndent2 = textIndent;
        return paragraphStyle.m5096copyElsmlbk(textAlign, textDirection, j10, textIndent2);
    }

    /* JADX INFO: renamed from: copy-NH1kkwU$default, reason: not valid java name */
    public static /* synthetic */ ParagraphStyle m5088copyNH1kkwU$default(ParagraphStyle paragraphStyle, TextAlign textAlign, TextDirection textDirection, long j10, TextIndent textIndent, PlatformParagraphStyle platformParagraphStyle, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens, TextMotion textMotion, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            textAlign = TextAlign.m5575boximpl(paragraphStyle.textAlign);
        }
        if ((i10 & 2) != 0) {
            textDirection = TextDirection.m5589boximpl(paragraphStyle.textDirection);
        }
        if ((i10 & 4) != 0) {
            j10 = paragraphStyle.lineHeight;
        }
        if ((i10 & 8) != 0) {
            textIndent = paragraphStyle.textIndent;
        }
        if ((i10 & 16) != 0) {
            platformParagraphStyle = paragraphStyle.platformStyle;
        }
        if ((i10 & 32) != 0) {
            lineHeightStyle = paragraphStyle.lineHeightStyle;
        }
        if ((i10 & 64) != 0) {
            lineBreak = LineBreak.m5495boximpl(paragraphStyle.lineBreak);
        }
        if ((i10 & 128) != 0) {
            hyphens = Hyphens.m5485boximpl(paragraphStyle.hyphens);
        }
        if ((i10 & 256) != 0) {
            textMotion = paragraphStyle.textMotion;
        }
        Hyphens hyphens2 = hyphens;
        TextMotion textMotion2 = textMotion;
        long j11 = j10;
        return paragraphStyle.m5097copyNH1kkwU(textAlign, textDirection, j11, textIndent, platformParagraphStyle, lineHeightStyle, lineBreak, hyphens2, textMotion2);
    }

    /* JADX INFO: renamed from: copy-ciSxzs0$default, reason: not valid java name */
    public static /* synthetic */ ParagraphStyle m5089copyciSxzs0$default(ParagraphStyle paragraphStyle, TextAlign textAlign, TextDirection textDirection, long j10, TextIndent textIndent, PlatformParagraphStyle platformParagraphStyle, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            textAlign = TextAlign.m5575boximpl(paragraphStyle.textAlign);
        }
        if ((i10 & 2) != 0) {
            textDirection = TextDirection.m5589boximpl(paragraphStyle.textDirection);
        }
        if ((i10 & 4) != 0) {
            j10 = paragraphStyle.lineHeight;
        }
        if ((i10 & 8) != 0) {
            textIndent = paragraphStyle.textIndent;
        }
        if ((i10 & 16) != 0) {
            platformParagraphStyle = paragraphStyle.platformStyle;
        }
        if ((i10 & 32) != 0) {
            lineHeightStyle = paragraphStyle.lineHeightStyle;
        }
        if ((i10 & 64) != 0) {
            lineBreak = LineBreak.m5495boximpl(paragraphStyle.lineBreak);
        }
        if ((i10 & 128) != 0) {
            hyphens = Hyphens.m5485boximpl(paragraphStyle.hyphens);
        }
        Hyphens hyphens2 = hyphens;
        LineHeightStyle lineHeightStyle2 = lineHeightStyle;
        TextIndent textIndent2 = textIndent;
        long j11 = j10;
        return paragraphStyle.m5098copyciSxzs0(textAlign, textDirection, j11, textIndent2, platformParagraphStyle, lineHeightStyle2, lineBreak, hyphens2);
    }

    /* JADX INFO: renamed from: copy-xPh5V4g$default, reason: not valid java name */
    public static /* synthetic */ ParagraphStyle m5090copyxPh5V4g$default(ParagraphStyle paragraphStyle, TextAlign textAlign, TextDirection textDirection, long j10, TextIndent textIndent, PlatformParagraphStyle platformParagraphStyle, LineHeightStyle lineHeightStyle, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            textAlign = TextAlign.m5575boximpl(paragraphStyle.textAlign);
        }
        if ((i10 & 2) != 0) {
            textDirection = TextDirection.m5589boximpl(paragraphStyle.textDirection);
        }
        if ((i10 & 4) != 0) {
            j10 = paragraphStyle.lineHeight;
        }
        if ((i10 & 8) != 0) {
            textIndent = paragraphStyle.textIndent;
        }
        if ((i10 & 16) != 0) {
            platformParagraphStyle = paragraphStyle.platformStyle;
        }
        if ((i10 & 32) != 0) {
            lineHeightStyle = paragraphStyle.lineHeightStyle;
        }
        LineHeightStyle lineHeightStyle2 = lineHeightStyle;
        TextIndent textIndent2 = textIndent;
        long j11 = j10;
        return paragraphStyle.m5099copyxPh5V4g(textAlign, textDirection, j11, textIndent2, platformParagraphStyle, lineHeightStyle2);
    }

    /* JADX INFO: renamed from: copy-ykzQM6k$default, reason: not valid java name */
    public static /* synthetic */ ParagraphStyle m5091copyykzQM6k$default(ParagraphStyle paragraphStyle, int i10, int i11, long j10, TextIndent textIndent, PlatformParagraphStyle platformParagraphStyle, LineHeightStyle lineHeightStyle, int i12, int i13, TextMotion textMotion, int i14, Object obj) {
        if ((i14 & 1) != 0) {
            i10 = paragraphStyle.textAlign;
        }
        if ((i14 & 2) != 0) {
            i11 = paragraphStyle.textDirection;
        }
        if ((i14 & 4) != 0) {
            j10 = paragraphStyle.lineHeight;
        }
        if ((i14 & 8) != 0) {
            textIndent = paragraphStyle.textIndent;
        }
        if ((i14 & 16) != 0) {
            platformParagraphStyle = paragraphStyle.platformStyle;
        }
        if ((i14 & 32) != 0) {
            lineHeightStyle = paragraphStyle.lineHeightStyle;
        }
        if ((i14 & 64) != 0) {
            i12 = paragraphStyle.lineBreak;
        }
        if ((i14 & 128) != 0) {
            i13 = paragraphStyle.hyphens;
        }
        if ((i14 & 256) != 0) {
            textMotion = paragraphStyle.textMotion;
        }
        int i15 = i13;
        TextMotion textMotion2 = textMotion;
        long j11 = j10;
        return paragraphStyle.m5100copyykzQM6k(i10, i11, j11, textIndent, platformParagraphStyle, lineHeightStyle, i12, i15, textMotion2);
    }

    @e
    /* JADX INFO: renamed from: getHyphens-EaSxIns$annotations, reason: not valid java name */
    public static /* synthetic */ void m5092getHyphensEaSxIns$annotations() {
    }

    @e
    /* JADX INFO: renamed from: getLineBreak-LgCVezo$annotations, reason: not valid java name */
    public static /* synthetic */ void m5093getLineBreakLgCVezo$annotations() {
    }

    @e
    /* JADX INFO: renamed from: getTextAlign-buA522U$annotations, reason: not valid java name */
    public static /* synthetic */ void m5094getTextAlignbuA522U$annotations() {
    }

    @e
    /* JADX INFO: renamed from: getTextDirection-mmuk1to$annotations, reason: not valid java name */
    public static /* synthetic */ void m5095getTextDirectionmmuk1to$annotations() {
    }

    public static /* synthetic */ ParagraphStyle merge$default(ParagraphStyle paragraphStyle, ParagraphStyle paragraphStyle2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            paragraphStyle2 = null;
        }
        return paragraphStyle.merge(paragraphStyle2);
    }

    @e
    /* JADX INFO: renamed from: copy-Elsmlbk, reason: not valid java name */
    public final /* synthetic */ ParagraphStyle m5096copyElsmlbk(TextAlign textAlign, TextDirection textDirection, long lineHeight, TextIndent textIndent) {
        return new ParagraphStyle(textAlign != null ? textAlign.getValue() : TextAlign.INSTANCE.m5588getUnspecifiede0LSkKk(), textDirection != null ? textDirection.getValue() : TextDirection.INSTANCE.m5601getUnspecifieds_7Xco(), lineHeight, textIndent, this.platformStyle, this.lineHeightStyle, this.lineBreak, this.hyphens, this.textMotion, (h) null);
    }

    @e
    /* JADX INFO: renamed from: copy-NH1kkwU, reason: not valid java name */
    public final /* synthetic */ ParagraphStyle m5097copyNH1kkwU(TextAlign textAlign, TextDirection textDirection, long lineHeight, TextIndent textIndent, PlatformParagraphStyle platformStyle, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens, TextMotion textMotion) {
        return new ParagraphStyle(textAlign != null ? textAlign.getValue() : TextAlign.INSTANCE.m5588getUnspecifiede0LSkKk(), textDirection != null ? textDirection.getValue() : TextDirection.INSTANCE.m5601getUnspecifieds_7Xco(), lineHeight, textIndent, platformStyle, lineHeightStyle, lineBreak != null ? lineBreak.getMask() : LineBreak.INSTANCE.m5515getUnspecifiedrAG3T2k(), hyphens != null ? hyphens.getValue() : Hyphens.INSTANCE.m5494getUnspecifiedvmbZdU8(), textMotion, (h) null);
    }

    @e
    /* JADX INFO: renamed from: copy-ciSxzs0, reason: not valid java name */
    public final /* synthetic */ ParagraphStyle m5098copyciSxzs0(TextAlign textAlign, TextDirection textDirection, long lineHeight, TextIndent textIndent, PlatformParagraphStyle platformStyle, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens) {
        return new ParagraphStyle(textAlign != null ? textAlign.getValue() : TextAlign.INSTANCE.m5588getUnspecifiede0LSkKk(), textDirection != null ? textDirection.getValue() : TextDirection.INSTANCE.m5601getUnspecifieds_7Xco(), lineHeight, textIndent, platformStyle, lineHeightStyle, lineBreak != null ? lineBreak.getMask() : LineBreak.INSTANCE.m5515getUnspecifiedrAG3T2k(), hyphens != null ? hyphens.getValue() : Hyphens.INSTANCE.m5494getUnspecifiedvmbZdU8(), this.textMotion, (h) null);
    }

    @e
    /* JADX INFO: renamed from: copy-xPh5V4g, reason: not valid java name */
    public final /* synthetic */ ParagraphStyle m5099copyxPh5V4g(TextAlign textAlign, TextDirection textDirection, long lineHeight, TextIndent textIndent, PlatformParagraphStyle platformStyle, LineHeightStyle lineHeightStyle) {
        return new ParagraphStyle(textAlign != null ? textAlign.getValue() : TextAlign.INSTANCE.m5588getUnspecifiede0LSkKk(), textDirection != null ? textDirection.getValue() : TextDirection.INSTANCE.m5601getUnspecifieds_7Xco(), lineHeight, textIndent, platformStyle, lineHeightStyle, this.lineBreak, this.hyphens, this.textMotion, (h) null);
    }

    /* JADX INFO: renamed from: copy-ykzQM6k, reason: not valid java name */
    public final ParagraphStyle m5100copyykzQM6k(int textAlign, int textDirection, long lineHeight, TextIndent textIndent, PlatformParagraphStyle platformStyle, LineHeightStyle lineHeightStyle, int lineBreak, int hyphens, TextMotion textMotion) {
        return new ParagraphStyle(textAlign, textDirection, lineHeight, textIndent, platformStyle, lineHeightStyle, lineBreak, hyphens, textMotion, (h) null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ParagraphStyle)) {
            return false;
        }
        ParagraphStyle paragraphStyle = (ParagraphStyle) other;
        return TextAlign.m5578equalsimpl0(this.textAlign, paragraphStyle.textAlign) && TextDirection.m5592equalsimpl0(this.textDirection, paragraphStyle.textDirection) && TextUnit.m5862equalsimpl0(this.lineHeight, paragraphStyle.lineHeight) && p.a(this.textIndent, paragraphStyle.textIndent) && p.a(this.platformStyle, paragraphStyle.platformStyle) && p.a(this.lineHeightStyle, paragraphStyle.lineHeightStyle) && LineBreak.m5501equalsimpl0(this.lineBreak, paragraphStyle.lineBreak) && Hyphens.m5488equalsimpl0(this.hyphens, paragraphStyle.hyphens) && p.a(this.textMotion, paragraphStyle.textMotion);
    }

    /* JADX INFO: renamed from: getHyphens-EaSxIns, reason: not valid java name */
    public final Hyphens m5101getHyphensEaSxIns() {
        return Hyphens.m5485boximpl(this.hyphens);
    }

    /* JADX INFO: renamed from: getHyphens-vmbZdU8, reason: not valid java name and from getter */
    public final int getHyphens() {
        return this.hyphens;
    }

    /* JADX INFO: renamed from: getLineBreak-LgCVezo, reason: not valid java name */
    public final LineBreak m5103getLineBreakLgCVezo() {
        return LineBreak.m5495boximpl(this.lineBreak);
    }

    /* JADX INFO: renamed from: getLineBreak-rAG3T2k, reason: not valid java name and from getter */
    public final int getLineBreak() {
        return this.lineBreak;
    }

    /* JADX INFO: renamed from: getLineHeight-XSAIIZE, reason: not valid java name and from getter */
    public final long getLineHeight() {
        return this.lineHeight;
    }

    public final LineHeightStyle getLineHeightStyle() {
        return this.lineHeightStyle;
    }

    public final PlatformParagraphStyle getPlatformStyle() {
        return this.platformStyle;
    }

    /* JADX INFO: renamed from: getTextAlign-buA522U, reason: not valid java name */
    public final TextAlign m5106getTextAlignbuA522U() {
        return TextAlign.m5575boximpl(this.textAlign);
    }

    /* JADX INFO: renamed from: getTextAlign-e0LSkKk, reason: not valid java name and from getter */
    public final int getTextAlign() {
        return this.textAlign;
    }

    /* JADX INFO: renamed from: getTextDirection-mmuk1to, reason: not valid java name */
    public final TextDirection m5108getTextDirectionmmuk1to() {
        return TextDirection.m5589boximpl(this.textDirection);
    }

    /* JADX INFO: renamed from: getTextDirection-s_7X-co, reason: not valid java name and from getter */
    public final int getTextDirection() {
        return this.textDirection;
    }

    public final TextIndent getTextIndent() {
        return this.textIndent;
    }

    public final TextMotion getTextMotion() {
        return this.textMotion;
    }

    public int hashCode() {
        int iM5866hashCodeimpl = (TextUnit.m5866hashCodeimpl(this.lineHeight) + ((TextDirection.m5593hashCodeimpl(this.textDirection) + (TextAlign.m5579hashCodeimpl(this.textAlign) * 31)) * 31)) * 31;
        TextIndent textIndent = this.textIndent;
        int iHashCode = (iM5866hashCodeimpl + (textIndent != null ? textIndent.hashCode() : 0)) * 31;
        PlatformParagraphStyle platformParagraphStyle = this.platformStyle;
        int iHashCode2 = (iHashCode + (platformParagraphStyle != null ? platformParagraphStyle.hashCode() : 0)) * 31;
        LineHeightStyle lineHeightStyle = this.lineHeightStyle;
        int iM5489hashCodeimpl = (Hyphens.m5489hashCodeimpl(this.hyphens) + ((LineBreak.m5505hashCodeimpl(this.lineBreak) + ((iHashCode2 + (lineHeightStyle != null ? lineHeightStyle.hashCode() : 0)) * 31)) * 31)) * 31;
        TextMotion textMotion = this.textMotion;
        return iM5489hashCodeimpl + (textMotion != null ? textMotion.hashCode() : 0);
    }

    public final ParagraphStyle merge(ParagraphStyle other) {
        return other == null ? this : ParagraphStyleKt.m5110fastMergej5T8yCg(this, other.textAlign, other.textDirection, other.lineHeight, other.textIndent, other.platformStyle, other.lineHeightStyle, other.lineBreak, other.hyphens, other.textMotion);
    }

    public final ParagraphStyle plus(ParagraphStyle other) {
        return merge(other);
    }

    public String toString() {
        return "ParagraphStyle(textAlign=" + ((Object) TextAlign.m5580toStringimpl(this.textAlign)) + ", textDirection=" + ((Object) TextDirection.m5594toStringimpl(this.textDirection)) + ", lineHeight=" + ((Object) TextUnit.m5872toStringimpl(this.lineHeight)) + ", textIndent=" + this.textIndent + ", platformStyle=" + this.platformStyle + ", lineHeightStyle=" + this.lineHeightStyle + ", lineBreak=" + ((Object) LineBreak.m5506toStringimpl(this.lineBreak)) + ", hyphens=" + ((Object) Hyphens.m5490toStringimpl(this.hyphens)) + ", textMotion=" + this.textMotion + ')';
    }

    @e
    public /* synthetic */ ParagraphStyle(TextAlign textAlign, TextDirection textDirection, long j10, TextIndent textIndent, PlatformParagraphStyle platformParagraphStyle, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens, TextMotion textMotion, h hVar) {
        this(textAlign, textDirection, j10, textIndent, platformParagraphStyle, lineHeightStyle, lineBreak, hyphens, textMotion);
    }

    @e
    public /* synthetic */ ParagraphStyle(TextAlign textAlign, TextDirection textDirection, long j10, TextIndent textIndent, PlatformParagraphStyle platformParagraphStyle, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens, h hVar) {
        this(textAlign, textDirection, j10, textIndent, platformParagraphStyle, lineHeightStyle, lineBreak, hyphens);
    }

    @e
    public /* synthetic */ ParagraphStyle(TextAlign textAlign, TextDirection textDirection, long j10, TextIndent textIndent, PlatformParagraphStyle platformParagraphStyle, LineHeightStyle lineHeightStyle, h hVar) {
        this(textAlign, textDirection, j10, textIndent, platformParagraphStyle, lineHeightStyle);
    }

    @e
    public /* synthetic */ ParagraphStyle(TextAlign textAlign, TextDirection textDirection, long j10, TextIndent textIndent, h hVar) {
        this(textAlign, textDirection, j10, textIndent);
    }

    private ParagraphStyle(int i10, int i11, long j10, TextIndent textIndent, PlatformParagraphStyle platformParagraphStyle, LineHeightStyle lineHeightStyle, int i12, int i13, TextMotion textMotion) {
        this.textAlign = i10;
        this.textDirection = i11;
        this.lineHeight = j10;
        this.textIndent = textIndent;
        this.platformStyle = platformParagraphStyle;
        this.lineHeightStyle = lineHeightStyle;
        this.lineBreak = i12;
        this.hyphens = i13;
        this.textMotion = textMotion;
        if (TextUnit.m5862equalsimpl0(j10, TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE()) || TextUnit.m5865getValueimpl(j10) >= 0.0f) {
            return;
        }
        throw new IllegalStateException(("lineHeight can't be negative (" + TextUnit.m5865getValueimpl(j10) + ')').toString());
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ ParagraphStyle(int i10, int i11, long j10, TextIndent textIndent, PlatformParagraphStyle platformParagraphStyle, LineHeightStyle lineHeightStyle, int i12, int i13, TextMotion textMotion, int i14, h hVar) {
        int iM5588getUnspecifiede0LSkKk = (i14 & 1) != 0 ? TextAlign.INSTANCE.m5588getUnspecifiede0LSkKk() : i10;
        int iM5601getUnspecifieds_7Xco = (i14 & 2) != 0 ? TextDirection.INSTANCE.m5601getUnspecifieds_7Xco() : i11;
        long jM5876getUnspecifiedXSAIIZE = (i14 & 4) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : j10;
        TextIndent textIndent2 = (i14 & 8) != 0 ? null : textIndent;
        PlatformParagraphStyle platformParagraphStyle2 = (i14 & 16) != 0 ? null : platformParagraphStyle;
        LineHeightStyle lineHeightStyle2 = (i14 & 32) != 0 ? null : lineHeightStyle;
        int iM5515getUnspecifiedrAG3T2k = (i14 & 64) != 0 ? LineBreak.INSTANCE.m5515getUnspecifiedrAG3T2k() : i12;
        int iM5494getUnspecifiedvmbZdU8 = (i14 & 128) != 0 ? Hyphens.INSTANCE.m5494getUnspecifiedvmbZdU8() : i13;
        this(iM5588getUnspecifiede0LSkKk, iM5601getUnspecifieds_7Xco, jM5876getUnspecifiedXSAIIZE, textIndent2, platformParagraphStyle2, lineHeightStyle2, iM5515getUnspecifiedrAG3T2k, iM5494getUnspecifiedvmbZdU8, (i14 & 256) == 0 ? textMotion : null, (h) null);
    }

    public /* synthetic */ ParagraphStyle(TextAlign textAlign, TextDirection textDirection, long j10, TextIndent textIndent, PlatformParagraphStyle platformParagraphStyle, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens, TextMotion textMotion, int i10, h hVar) {
        this((i10 & 1) != 0 ? null : textAlign, (i10 & 2) != 0 ? null : textDirection, (i10 & 4) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : j10, (i10 & 8) != 0 ? null : textIndent, (i10 & 16) != 0 ? null : platformParagraphStyle, (i10 & 32) != 0 ? null : lineHeightStyle, (i10 & 64) != 0 ? null : lineBreak, (i10 & 128) != 0 ? null : hyphens, (i10 & 256) == 0 ? textMotion : null, (h) null);
    }

    private ParagraphStyle(TextAlign textAlign, TextDirection textDirection, long j10, TextIndent textIndent, PlatformParagraphStyle platformParagraphStyle, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens, TextMotion textMotion) {
        this(textAlign != null ? textAlign.getValue() : TextAlign.INSTANCE.m5588getUnspecifiede0LSkKk(), textDirection != null ? textDirection.getValue() : TextDirection.INSTANCE.m5601getUnspecifieds_7Xco(), j10, textIndent, platformParagraphStyle, lineHeightStyle, lineBreak != null ? lineBreak.getMask() : LineBreak.INSTANCE.m5515getUnspecifiedrAG3T2k(), hyphens != null ? hyphens.getValue() : Hyphens.INSTANCE.m5494getUnspecifiedvmbZdU8(), textMotion, (h) null);
    }

    public /* synthetic */ ParagraphStyle(TextAlign textAlign, TextDirection textDirection, long j10, TextIndent textIndent, int i10, h hVar) {
        this((i10 & 1) != 0 ? null : textAlign, (i10 & 2) != 0 ? null : textDirection, (i10 & 4) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : j10, (i10 & 8) != 0 ? null : textIndent, null);
    }

    private ParagraphStyle(TextAlign textAlign, TextDirection textDirection, long j10, TextIndent textIndent) {
        this(textAlign != null ? textAlign.getValue() : TextAlign.INSTANCE.m5588getUnspecifiede0LSkKk(), textDirection != null ? textDirection.getValue() : TextDirection.INSTANCE.m5601getUnspecifieds_7Xco(), j10, textIndent, (PlatformParagraphStyle) null, (LineHeightStyle) null, LineBreak.INSTANCE.m5515getUnspecifiedrAG3T2k(), Hyphens.INSTANCE.m5494getUnspecifiedvmbZdU8(), (TextMotion) null, (h) null);
    }

    public /* synthetic */ ParagraphStyle(TextAlign textAlign, TextDirection textDirection, long j10, TextIndent textIndent, PlatformParagraphStyle platformParagraphStyle, LineHeightStyle lineHeightStyle, int i10, h hVar) {
        this((i10 & 1) != 0 ? null : textAlign, (i10 & 2) != 0 ? null : textDirection, (i10 & 4) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : j10, (i10 & 8) != 0 ? null : textIndent, (i10 & 16) != 0 ? null : platformParagraphStyle, (i10 & 32) != 0 ? null : lineHeightStyle, null);
    }

    private ParagraphStyle(TextAlign textAlign, TextDirection textDirection, long j10, TextIndent textIndent, PlatformParagraphStyle platformParagraphStyle, LineHeightStyle lineHeightStyle) {
        this(textAlign != null ? textAlign.getValue() : TextAlign.INSTANCE.m5588getUnspecifiede0LSkKk(), textDirection != null ? textDirection.getValue() : TextDirection.INSTANCE.m5601getUnspecifieds_7Xco(), j10, textIndent, platformParagraphStyle, lineHeightStyle, LineBreak.INSTANCE.m5515getUnspecifiedrAG3T2k(), Hyphens.INSTANCE.m5494getUnspecifiedvmbZdU8(), (TextMotion) null, (h) null);
    }

    public /* synthetic */ ParagraphStyle(TextAlign textAlign, TextDirection textDirection, long j10, TextIndent textIndent, PlatformParagraphStyle platformParagraphStyle, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens, int i10, h hVar) {
        this((i10 & 1) != 0 ? null : textAlign, (i10 & 2) != 0 ? null : textDirection, (i10 & 4) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : j10, (i10 & 8) != 0 ? null : textIndent, (i10 & 16) != 0 ? null : platformParagraphStyle, (i10 & 32) != 0 ? null : lineHeightStyle, (i10 & 64) != 0 ? null : lineBreak, (i10 & 128) == 0 ? hyphens : null, (h) null);
    }

    private ParagraphStyle(TextAlign textAlign, TextDirection textDirection, long j10, TextIndent textIndent, PlatformParagraphStyle platformParagraphStyle, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens) {
        this(textAlign != null ? textAlign.getValue() : TextAlign.INSTANCE.m5588getUnspecifiede0LSkKk(), textDirection != null ? textDirection.getValue() : TextDirection.INSTANCE.m5601getUnspecifieds_7Xco(), j10, textIndent, platformParagraphStyle, lineHeightStyle, lineBreak != null ? lineBreak.getMask() : LineBreak.INSTANCE.m5515getUnspecifiedrAG3T2k(), hyphens != null ? hyphens.getValue() : Hyphens.INSTANCE.m5494getUnspecifiedvmbZdU8(), (TextMotion) null, (h) null);
    }
}
