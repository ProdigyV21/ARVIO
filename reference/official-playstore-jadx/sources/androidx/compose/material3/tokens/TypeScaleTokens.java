package androidx.compose.material3.tokens;

import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.font.GenericFontFamily;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000%\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0003\b\u008f\u0001\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\u0007\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0019\u0010\f\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\r\u0010\nR\u0019\u0010\u000e\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u000f\u0010\nR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0006R\u0019\u0010\u0016\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u0017\u0010\nR\u0019\u0010\u0018\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u0019\u0010\nR\u0019\u0010\u001a\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u001b\u0010\nR\u0011\u0010\u001c\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013R\u0011\u0010\u001e\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0006R\u0019\u0010 \u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b!\u0010\nR\u0019\u0010\"\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b#\u0010\nR\u0019\u0010$\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b%\u0010\nR\u0011\u0010&\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0013R\u0011\u0010(\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0006R\u0019\u0010*\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b+\u0010\nR\u0019\u0010,\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b-\u0010\nR\u0019\u0010.\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b/\u0010\nR\u0011\u00100\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u0013R\u0011\u00102\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0006R\u0019\u00104\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b5\u0010\nR\u0019\u00106\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b7\u0010\nR\u0019\u00108\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b9\u0010\nR\u0011\u0010:\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b;\u0010\u0013R\u0011\u0010<\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u0010\u0006R\u0019\u0010>\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b?\u0010\nR\u0019\u0010@\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\bA\u0010\nR\u0019\u0010B\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\bC\u0010\nR\u0011\u0010D\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\bE\u0010\u0013R\u0011\u0010F\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bG\u0010\u0006R\u0019\u0010H\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\bI\u0010\nR\u0019\u0010J\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\bK\u0010\nR\u0019\u0010L\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\bM\u0010\nR\u0011\u0010N\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\bO\u0010\u0013R\u0011\u0010P\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010\u0006R\u0019\u0010R\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\bS\u0010\nR\u0019\u0010T\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\bU\u0010\nR\u0019\u0010V\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\bW\u0010\nR\u0011\u0010X\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\bY\u0010\u0013R\u0011\u0010Z\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b[\u0010\u0006R\u0019\u0010\\\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b]\u0010\nR\u0019\u0010^\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b_\u0010\nR\u0019\u0010`\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\ba\u0010\nR\u0011\u0010b\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\bc\u0010\u0013R\u0011\u0010d\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\be\u0010\u0006R\u0019\u0010f\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\bg\u0010\nR\u0019\u0010h\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\bi\u0010\nR\u0019\u0010j\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\bk\u0010\nR\u0011\u0010l\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\bm\u0010\u0013R\u0011\u0010n\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bo\u0010\u0006R\u0019\u0010p\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\bq\u0010\nR\u0019\u0010r\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\bs\u0010\nR\u0019\u0010t\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\bu\u0010\nR\u0011\u0010v\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\bw\u0010\u0013R\u0011\u0010x\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\by\u0010\u0006R\u0019\u0010z\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b{\u0010\nR\u0019\u0010|\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b}\u0010\nR\u0019\u0010~\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u007f\u0010\nR\u0013\u0010\u0080\u0001\u001a\u00020\u0011¢\u0006\t\n\u0000\u001a\u0005\b\u0081\u0001\u0010\u0013R\u0013\u0010\u0082\u0001\u001a\u00020\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0083\u0001\u0010\u0006R\u001b\u0010\u0084\u0001\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\u000b\n\u0002\u0010\u000b\u001a\u0005\b\u0085\u0001\u0010\nR\u001b\u0010\u0086\u0001\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\u000b\n\u0002\u0010\u000b\u001a\u0005\b\u0087\u0001\u0010\nR\u001b\u0010\u0088\u0001\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\u000b\n\u0002\u0010\u000b\u001a\u0005\b\u0089\u0001\u0010\nR\u0013\u0010\u008a\u0001\u001a\u00020\u0011¢\u0006\t\n\u0000\u001a\u0005\b\u008b\u0001\u0010\u0013R\u0013\u0010\u008c\u0001\u001a\u00020\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u008d\u0001\u0010\u0006R\u001b\u0010\u008e\u0001\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\u000b\n\u0002\u0010\u000b\u001a\u0005\b\u008f\u0001\u0010\nR\u001b\u0010\u0090\u0001\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\u000b\n\u0002\u0010\u000b\u001a\u0005\b\u0091\u0001\u0010\nR\u001b\u0010\u0092\u0001\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\u000b\n\u0002\u0010\u000b\u001a\u0005\b\u0093\u0001\u0010\nR\u0013\u0010\u0094\u0001\u001a\u00020\u0011¢\u0006\t\n\u0000\u001a\u0005\b\u0095\u0001\u0010\u0013R\u0013\u0010\u0096\u0001\u001a\u00020\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0097\u0001\u0010\u0006R\u001b\u0010\u0098\u0001\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\u000b\n\u0002\u0010\u000b\u001a\u0005\b\u0099\u0001\u0010\nR\u001b\u0010\u009a\u0001\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\u000b\n\u0002\u0010\u000b\u001a\u0005\b\u009b\u0001\u0010\nR\u001b\u0010\u009c\u0001\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\u000b\n\u0002\u0010\u000b\u001a\u0005\b\u009d\u0001\u0010\nR\u0013\u0010\u009e\u0001\u001a\u00020\u0011¢\u0006\t\n\u0000\u001a\u0005\b\u009f\u0001\u0010\u0013\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006 \u0001"}, d2 = {"Landroidx/compose/material3/tokens/TypeScaleTokens;", "", "()V", "BodyLargeFont", "Landroidx/compose/ui/text/font/GenericFontFamily;", "getBodyLargeFont", "()Landroidx/compose/ui/text/font/GenericFontFamily;", "BodyLargeLineHeight", "Landroidx/compose/ui/unit/TextUnit;", "getBodyLargeLineHeight-XSAIIZE", "()J", "J", "BodyLargeSize", "getBodyLargeSize-XSAIIZE", "BodyLargeTracking", "getBodyLargeTracking-XSAIIZE", "BodyLargeWeight", "Landroidx/compose/ui/text/font/FontWeight;", "getBodyLargeWeight", "()Landroidx/compose/ui/text/font/FontWeight;", "BodyMediumFont", "getBodyMediumFont", "BodyMediumLineHeight", "getBodyMediumLineHeight-XSAIIZE", "BodyMediumSize", "getBodyMediumSize-XSAIIZE", "BodyMediumTracking", "getBodyMediumTracking-XSAIIZE", "BodyMediumWeight", "getBodyMediumWeight", "BodySmallFont", "getBodySmallFont", "BodySmallLineHeight", "getBodySmallLineHeight-XSAIIZE", "BodySmallSize", "getBodySmallSize-XSAIIZE", "BodySmallTracking", "getBodySmallTracking-XSAIIZE", "BodySmallWeight", "getBodySmallWeight", "DisplayLargeFont", "getDisplayLargeFont", "DisplayLargeLineHeight", "getDisplayLargeLineHeight-XSAIIZE", "DisplayLargeSize", "getDisplayLargeSize-XSAIIZE", "DisplayLargeTracking", "getDisplayLargeTracking-XSAIIZE", "DisplayLargeWeight", "getDisplayLargeWeight", "DisplayMediumFont", "getDisplayMediumFont", "DisplayMediumLineHeight", "getDisplayMediumLineHeight-XSAIIZE", "DisplayMediumSize", "getDisplayMediumSize-XSAIIZE", "DisplayMediumTracking", "getDisplayMediumTracking-XSAIIZE", "DisplayMediumWeight", "getDisplayMediumWeight", "DisplaySmallFont", "getDisplaySmallFont", "DisplaySmallLineHeight", "getDisplaySmallLineHeight-XSAIIZE", "DisplaySmallSize", "getDisplaySmallSize-XSAIIZE", "DisplaySmallTracking", "getDisplaySmallTracking-XSAIIZE", "DisplaySmallWeight", "getDisplaySmallWeight", "HeadlineLargeFont", "getHeadlineLargeFont", "HeadlineLargeLineHeight", "getHeadlineLargeLineHeight-XSAIIZE", "HeadlineLargeSize", "getHeadlineLargeSize-XSAIIZE", "HeadlineLargeTracking", "getHeadlineLargeTracking-XSAIIZE", "HeadlineLargeWeight", "getHeadlineLargeWeight", "HeadlineMediumFont", "getHeadlineMediumFont", "HeadlineMediumLineHeight", "getHeadlineMediumLineHeight-XSAIIZE", "HeadlineMediumSize", "getHeadlineMediumSize-XSAIIZE", "HeadlineMediumTracking", "getHeadlineMediumTracking-XSAIIZE", "HeadlineMediumWeight", "getHeadlineMediumWeight", "HeadlineSmallFont", "getHeadlineSmallFont", "HeadlineSmallLineHeight", "getHeadlineSmallLineHeight-XSAIIZE", "HeadlineSmallSize", "getHeadlineSmallSize-XSAIIZE", "HeadlineSmallTracking", "getHeadlineSmallTracking-XSAIIZE", "HeadlineSmallWeight", "getHeadlineSmallWeight", "LabelLargeFont", "getLabelLargeFont", "LabelLargeLineHeight", "getLabelLargeLineHeight-XSAIIZE", "LabelLargeSize", "getLabelLargeSize-XSAIIZE", "LabelLargeTracking", "getLabelLargeTracking-XSAIIZE", "LabelLargeWeight", "getLabelLargeWeight", "LabelMediumFont", "getLabelMediumFont", "LabelMediumLineHeight", "getLabelMediumLineHeight-XSAIIZE", "LabelMediumSize", "getLabelMediumSize-XSAIIZE", "LabelMediumTracking", "getLabelMediumTracking-XSAIIZE", "LabelMediumWeight", "getLabelMediumWeight", "LabelSmallFont", "getLabelSmallFont", "LabelSmallLineHeight", "getLabelSmallLineHeight-XSAIIZE", "LabelSmallSize", "getLabelSmallSize-XSAIIZE", "LabelSmallTracking", "getLabelSmallTracking-XSAIIZE", "LabelSmallWeight", "getLabelSmallWeight", "TitleLargeFont", "getTitleLargeFont", "TitleLargeLineHeight", "getTitleLargeLineHeight-XSAIIZE", "TitleLargeSize", "getTitleLargeSize-XSAIIZE", "TitleLargeTracking", "getTitleLargeTracking-XSAIIZE", "TitleLargeWeight", "getTitleLargeWeight", "TitleMediumFont", "getTitleMediumFont", "TitleMediumLineHeight", "getTitleMediumLineHeight-XSAIIZE", "TitleMediumSize", "getTitleMediumSize-XSAIIZE", "TitleMediumTracking", "getTitleMediumTracking-XSAIIZE", "TitleMediumWeight", "getTitleMediumWeight", "TitleSmallFont", "getTitleSmallFont", "TitleSmallLineHeight", "getTitleSmallLineHeight-XSAIIZE", "TitleSmallSize", "getTitleSmallSize-XSAIIZE", "TitleSmallTracking", "getTitleSmallTracking-XSAIIZE", "TitleSmallWeight", "getTitleSmallWeight", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TypeScaleTokens {
    public static final int $stable = 0;
    private static final GenericFontFamily BodyLargeFont;
    private static final long BodyLargeLineHeight;
    private static final long BodyLargeSize;
    private static final long BodyLargeTracking;
    private static final FontWeight BodyLargeWeight;
    private static final GenericFontFamily BodyMediumFont;
    private static final long BodyMediumLineHeight;
    private static final long BodyMediumSize;
    private static final long BodyMediumTracking;
    private static final FontWeight BodyMediumWeight;
    private static final GenericFontFamily BodySmallFont;
    private static final long BodySmallLineHeight;
    private static final long BodySmallSize;
    private static final long BodySmallTracking;
    private static final FontWeight BodySmallWeight;
    private static final GenericFontFamily DisplayLargeFont;
    private static final long DisplayLargeLineHeight;
    private static final long DisplayLargeSize;
    private static final long DisplayLargeTracking;
    private static final FontWeight DisplayLargeWeight;
    private static final GenericFontFamily DisplayMediumFont;
    private static final long DisplayMediumLineHeight;
    private static final long DisplayMediumSize;
    private static final long DisplayMediumTracking;
    private static final FontWeight DisplayMediumWeight;
    private static final GenericFontFamily DisplaySmallFont;
    private static final long DisplaySmallLineHeight;
    private static final long DisplaySmallSize;
    private static final long DisplaySmallTracking;
    private static final FontWeight DisplaySmallWeight;
    private static final GenericFontFamily HeadlineLargeFont;
    private static final long HeadlineLargeLineHeight;
    private static final long HeadlineLargeSize;
    private static final long HeadlineLargeTracking;
    private static final FontWeight HeadlineLargeWeight;
    private static final GenericFontFamily HeadlineMediumFont;
    private static final long HeadlineMediumLineHeight;
    private static final long HeadlineMediumSize;
    private static final long HeadlineMediumTracking;
    private static final FontWeight HeadlineMediumWeight;
    private static final GenericFontFamily HeadlineSmallFont;
    private static final long HeadlineSmallLineHeight;
    private static final long HeadlineSmallSize;
    private static final long HeadlineSmallTracking;
    private static final FontWeight HeadlineSmallWeight;
    public static final TypeScaleTokens INSTANCE = new TypeScaleTokens();
    private static final GenericFontFamily LabelLargeFont;
    private static final long LabelLargeLineHeight;
    private static final long LabelLargeSize;
    private static final long LabelLargeTracking;
    private static final FontWeight LabelLargeWeight;
    private static final GenericFontFamily LabelMediumFont;
    private static final long LabelMediumLineHeight;
    private static final long LabelMediumSize;
    private static final long LabelMediumTracking;
    private static final FontWeight LabelMediumWeight;
    private static final GenericFontFamily LabelSmallFont;
    private static final long LabelSmallLineHeight;
    private static final long LabelSmallSize;
    private static final long LabelSmallTracking;
    private static final FontWeight LabelSmallWeight;
    private static final GenericFontFamily TitleLargeFont;
    private static final long TitleLargeLineHeight;
    private static final long TitleLargeSize;
    private static final long TitleLargeTracking;
    private static final FontWeight TitleLargeWeight;
    private static final GenericFontFamily TitleMediumFont;
    private static final long TitleMediumLineHeight;
    private static final long TitleMediumSize;
    private static final long TitleMediumTracking;
    private static final FontWeight TitleMediumWeight;
    private static final GenericFontFamily TitleSmallFont;
    private static final long TitleSmallLineHeight;
    private static final long TitleSmallSize;
    private static final long TitleSmallTracking;
    private static final FontWeight TitleSmallWeight;

    static {
        TypefaceTokens typefaceTokens = TypefaceTokens.INSTANCE;
        BodyLargeFont = typefaceTokens.getPlain();
        BodyLargeLineHeight = TextUnitKt.getSp(24.0d);
        BodyLargeSize = TextUnitKt.getSp(16);
        BodyLargeTracking = TextUnitKt.getSp(0.5d);
        BodyLargeWeight = typefaceTokens.getWeightRegular();
        BodyMediumFont = typefaceTokens.getPlain();
        BodyMediumLineHeight = TextUnitKt.getSp(20.0d);
        BodyMediumSize = TextUnitKt.getSp(14);
        BodyMediumTracking = TextUnitKt.getSp(0.2d);
        BodyMediumWeight = typefaceTokens.getWeightRegular();
        BodySmallFont = typefaceTokens.getPlain();
        BodySmallLineHeight = TextUnitKt.getSp(16.0d);
        BodySmallSize = TextUnitKt.getSp(12);
        BodySmallTracking = TextUnitKt.getSp(0.4d);
        BodySmallWeight = typefaceTokens.getWeightRegular();
        DisplayLargeFont = typefaceTokens.getBrand();
        DisplayLargeLineHeight = TextUnitKt.getSp(64.0d);
        DisplayLargeSize = TextUnitKt.getSp(57);
        long sp = TextUnitKt.getSp(0.2d);
        TextUnitKt.m5878checkArithmeticR2X_6o(sp);
        DisplayLargeTracking = TextUnitKt.pack(TextUnit.m5863getRawTypeimpl(sp), -TextUnit.m5865getValueimpl(sp));
        DisplayLargeWeight = typefaceTokens.getWeightRegular();
        DisplayMediumFont = typefaceTokens.getBrand();
        DisplayMediumLineHeight = TextUnitKt.getSp(52.0d);
        DisplayMediumSize = TextUnitKt.getSp(45);
        DisplayMediumTracking = TextUnitKt.getSp(0.0d);
        DisplayMediumWeight = typefaceTokens.getWeightRegular();
        DisplaySmallFont = typefaceTokens.getBrand();
        DisplaySmallLineHeight = TextUnitKt.getSp(44.0d);
        DisplaySmallSize = TextUnitKt.getSp(36);
        DisplaySmallTracking = TextUnitKt.getSp(0.0d);
        DisplaySmallWeight = typefaceTokens.getWeightRegular();
        HeadlineLargeFont = typefaceTokens.getBrand();
        HeadlineLargeLineHeight = TextUnitKt.getSp(40.0d);
        HeadlineLargeSize = TextUnitKt.getSp(32);
        HeadlineLargeTracking = TextUnitKt.getSp(0.0d);
        HeadlineLargeWeight = typefaceTokens.getWeightRegular();
        HeadlineMediumFont = typefaceTokens.getBrand();
        HeadlineMediumLineHeight = TextUnitKt.getSp(36.0d);
        HeadlineMediumSize = TextUnitKt.getSp(28);
        HeadlineMediumTracking = TextUnitKt.getSp(0.0d);
        HeadlineMediumWeight = typefaceTokens.getWeightRegular();
        HeadlineSmallFont = typefaceTokens.getBrand();
        HeadlineSmallLineHeight = TextUnitKt.getSp(32.0d);
        HeadlineSmallSize = TextUnitKt.getSp(24);
        HeadlineSmallTracking = TextUnitKt.getSp(0.0d);
        HeadlineSmallWeight = typefaceTokens.getWeightRegular();
        LabelLargeFont = typefaceTokens.getPlain();
        LabelLargeLineHeight = TextUnitKt.getSp(20.0d);
        LabelLargeSize = TextUnitKt.getSp(14);
        LabelLargeTracking = TextUnitKt.getSp(0.1d);
        LabelLargeWeight = typefaceTokens.getWeightMedium();
        LabelMediumFont = typefaceTokens.getPlain();
        LabelMediumLineHeight = TextUnitKt.getSp(16.0d);
        LabelMediumSize = TextUnitKt.getSp(12);
        LabelMediumTracking = TextUnitKt.getSp(0.5d);
        LabelMediumWeight = typefaceTokens.getWeightMedium();
        LabelSmallFont = typefaceTokens.getPlain();
        LabelSmallLineHeight = TextUnitKt.getSp(16.0d);
        LabelSmallSize = TextUnitKt.getSp(11);
        LabelSmallTracking = TextUnitKt.getSp(0.5d);
        LabelSmallWeight = typefaceTokens.getWeightMedium();
        TitleLargeFont = typefaceTokens.getBrand();
        TitleLargeLineHeight = TextUnitKt.getSp(28.0d);
        TitleLargeSize = TextUnitKt.getSp(22);
        TitleLargeTracking = TextUnitKt.getSp(0.0d);
        TitleLargeWeight = typefaceTokens.getWeightRegular();
        TitleMediumFont = typefaceTokens.getPlain();
        TitleMediumLineHeight = TextUnitKt.getSp(24.0d);
        TitleMediumSize = TextUnitKt.getSp(16);
        TitleMediumTracking = TextUnitKt.getSp(0.2d);
        TitleMediumWeight = typefaceTokens.getWeightMedium();
        TitleSmallFont = typefaceTokens.getPlain();
        TitleSmallLineHeight = TextUnitKt.getSp(20.0d);
        TitleSmallSize = TextUnitKt.getSp(14);
        TitleSmallTracking = TextUnitKt.getSp(0.1d);
        TitleSmallWeight = typefaceTokens.getWeightMedium();
    }

    private TypeScaleTokens() {
    }

    public final GenericFontFamily getBodyLargeFont() {
        return BodyLargeFont;
    }

    /* JADX INFO: renamed from: getBodyLargeLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2900getBodyLargeLineHeightXSAIIZE() {
        return BodyLargeLineHeight;
    }

    /* JADX INFO: renamed from: getBodyLargeSize-XSAIIZE, reason: not valid java name */
    public final long m2901getBodyLargeSizeXSAIIZE() {
        return BodyLargeSize;
    }

    /* JADX INFO: renamed from: getBodyLargeTracking-XSAIIZE, reason: not valid java name */
    public final long m2902getBodyLargeTrackingXSAIIZE() {
        return BodyLargeTracking;
    }

    public final FontWeight getBodyLargeWeight() {
        return BodyLargeWeight;
    }

    public final GenericFontFamily getBodyMediumFont() {
        return BodyMediumFont;
    }

    /* JADX INFO: renamed from: getBodyMediumLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2903getBodyMediumLineHeightXSAIIZE() {
        return BodyMediumLineHeight;
    }

    /* JADX INFO: renamed from: getBodyMediumSize-XSAIIZE, reason: not valid java name */
    public final long m2904getBodyMediumSizeXSAIIZE() {
        return BodyMediumSize;
    }

    /* JADX INFO: renamed from: getBodyMediumTracking-XSAIIZE, reason: not valid java name */
    public final long m2905getBodyMediumTrackingXSAIIZE() {
        return BodyMediumTracking;
    }

    public final FontWeight getBodyMediumWeight() {
        return BodyMediumWeight;
    }

    public final GenericFontFamily getBodySmallFont() {
        return BodySmallFont;
    }

    /* JADX INFO: renamed from: getBodySmallLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2906getBodySmallLineHeightXSAIIZE() {
        return BodySmallLineHeight;
    }

    /* JADX INFO: renamed from: getBodySmallSize-XSAIIZE, reason: not valid java name */
    public final long m2907getBodySmallSizeXSAIIZE() {
        return BodySmallSize;
    }

    /* JADX INFO: renamed from: getBodySmallTracking-XSAIIZE, reason: not valid java name */
    public final long m2908getBodySmallTrackingXSAIIZE() {
        return BodySmallTracking;
    }

    public final FontWeight getBodySmallWeight() {
        return BodySmallWeight;
    }

    public final GenericFontFamily getDisplayLargeFont() {
        return DisplayLargeFont;
    }

    /* JADX INFO: renamed from: getDisplayLargeLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2909getDisplayLargeLineHeightXSAIIZE() {
        return DisplayLargeLineHeight;
    }

    /* JADX INFO: renamed from: getDisplayLargeSize-XSAIIZE, reason: not valid java name */
    public final long m2910getDisplayLargeSizeXSAIIZE() {
        return DisplayLargeSize;
    }

    /* JADX INFO: renamed from: getDisplayLargeTracking-XSAIIZE, reason: not valid java name */
    public final long m2911getDisplayLargeTrackingXSAIIZE() {
        return DisplayLargeTracking;
    }

    public final FontWeight getDisplayLargeWeight() {
        return DisplayLargeWeight;
    }

    public final GenericFontFamily getDisplayMediumFont() {
        return DisplayMediumFont;
    }

    /* JADX INFO: renamed from: getDisplayMediumLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2912getDisplayMediumLineHeightXSAIIZE() {
        return DisplayMediumLineHeight;
    }

    /* JADX INFO: renamed from: getDisplayMediumSize-XSAIIZE, reason: not valid java name */
    public final long m2913getDisplayMediumSizeXSAIIZE() {
        return DisplayMediumSize;
    }

    /* JADX INFO: renamed from: getDisplayMediumTracking-XSAIIZE, reason: not valid java name */
    public final long m2914getDisplayMediumTrackingXSAIIZE() {
        return DisplayMediumTracking;
    }

    public final FontWeight getDisplayMediumWeight() {
        return DisplayMediumWeight;
    }

    public final GenericFontFamily getDisplaySmallFont() {
        return DisplaySmallFont;
    }

    /* JADX INFO: renamed from: getDisplaySmallLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2915getDisplaySmallLineHeightXSAIIZE() {
        return DisplaySmallLineHeight;
    }

    /* JADX INFO: renamed from: getDisplaySmallSize-XSAIIZE, reason: not valid java name */
    public final long m2916getDisplaySmallSizeXSAIIZE() {
        return DisplaySmallSize;
    }

    /* JADX INFO: renamed from: getDisplaySmallTracking-XSAIIZE, reason: not valid java name */
    public final long m2917getDisplaySmallTrackingXSAIIZE() {
        return DisplaySmallTracking;
    }

    public final FontWeight getDisplaySmallWeight() {
        return DisplaySmallWeight;
    }

    public final GenericFontFamily getHeadlineLargeFont() {
        return HeadlineLargeFont;
    }

    /* JADX INFO: renamed from: getHeadlineLargeLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2918getHeadlineLargeLineHeightXSAIIZE() {
        return HeadlineLargeLineHeight;
    }

    /* JADX INFO: renamed from: getHeadlineLargeSize-XSAIIZE, reason: not valid java name */
    public final long m2919getHeadlineLargeSizeXSAIIZE() {
        return HeadlineLargeSize;
    }

    /* JADX INFO: renamed from: getHeadlineLargeTracking-XSAIIZE, reason: not valid java name */
    public final long m2920getHeadlineLargeTrackingXSAIIZE() {
        return HeadlineLargeTracking;
    }

    public final FontWeight getHeadlineLargeWeight() {
        return HeadlineLargeWeight;
    }

    public final GenericFontFamily getHeadlineMediumFont() {
        return HeadlineMediumFont;
    }

    /* JADX INFO: renamed from: getHeadlineMediumLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2921getHeadlineMediumLineHeightXSAIIZE() {
        return HeadlineMediumLineHeight;
    }

    /* JADX INFO: renamed from: getHeadlineMediumSize-XSAIIZE, reason: not valid java name */
    public final long m2922getHeadlineMediumSizeXSAIIZE() {
        return HeadlineMediumSize;
    }

    /* JADX INFO: renamed from: getHeadlineMediumTracking-XSAIIZE, reason: not valid java name */
    public final long m2923getHeadlineMediumTrackingXSAIIZE() {
        return HeadlineMediumTracking;
    }

    public final FontWeight getHeadlineMediumWeight() {
        return HeadlineMediumWeight;
    }

    public final GenericFontFamily getHeadlineSmallFont() {
        return HeadlineSmallFont;
    }

    /* JADX INFO: renamed from: getHeadlineSmallLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2924getHeadlineSmallLineHeightXSAIIZE() {
        return HeadlineSmallLineHeight;
    }

    /* JADX INFO: renamed from: getHeadlineSmallSize-XSAIIZE, reason: not valid java name */
    public final long m2925getHeadlineSmallSizeXSAIIZE() {
        return HeadlineSmallSize;
    }

    /* JADX INFO: renamed from: getHeadlineSmallTracking-XSAIIZE, reason: not valid java name */
    public final long m2926getHeadlineSmallTrackingXSAIIZE() {
        return HeadlineSmallTracking;
    }

    public final FontWeight getHeadlineSmallWeight() {
        return HeadlineSmallWeight;
    }

    public final GenericFontFamily getLabelLargeFont() {
        return LabelLargeFont;
    }

    /* JADX INFO: renamed from: getLabelLargeLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2927getLabelLargeLineHeightXSAIIZE() {
        return LabelLargeLineHeight;
    }

    /* JADX INFO: renamed from: getLabelLargeSize-XSAIIZE, reason: not valid java name */
    public final long m2928getLabelLargeSizeXSAIIZE() {
        return LabelLargeSize;
    }

    /* JADX INFO: renamed from: getLabelLargeTracking-XSAIIZE, reason: not valid java name */
    public final long m2929getLabelLargeTrackingXSAIIZE() {
        return LabelLargeTracking;
    }

    public final FontWeight getLabelLargeWeight() {
        return LabelLargeWeight;
    }

    public final GenericFontFamily getLabelMediumFont() {
        return LabelMediumFont;
    }

    /* JADX INFO: renamed from: getLabelMediumLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2930getLabelMediumLineHeightXSAIIZE() {
        return LabelMediumLineHeight;
    }

    /* JADX INFO: renamed from: getLabelMediumSize-XSAIIZE, reason: not valid java name */
    public final long m2931getLabelMediumSizeXSAIIZE() {
        return LabelMediumSize;
    }

    /* JADX INFO: renamed from: getLabelMediumTracking-XSAIIZE, reason: not valid java name */
    public final long m2932getLabelMediumTrackingXSAIIZE() {
        return LabelMediumTracking;
    }

    public final FontWeight getLabelMediumWeight() {
        return LabelMediumWeight;
    }

    public final GenericFontFamily getLabelSmallFont() {
        return LabelSmallFont;
    }

    /* JADX INFO: renamed from: getLabelSmallLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2933getLabelSmallLineHeightXSAIIZE() {
        return LabelSmallLineHeight;
    }

    /* JADX INFO: renamed from: getLabelSmallSize-XSAIIZE, reason: not valid java name */
    public final long m2934getLabelSmallSizeXSAIIZE() {
        return LabelSmallSize;
    }

    /* JADX INFO: renamed from: getLabelSmallTracking-XSAIIZE, reason: not valid java name */
    public final long m2935getLabelSmallTrackingXSAIIZE() {
        return LabelSmallTracking;
    }

    public final FontWeight getLabelSmallWeight() {
        return LabelSmallWeight;
    }

    public final GenericFontFamily getTitleLargeFont() {
        return TitleLargeFont;
    }

    /* JADX INFO: renamed from: getTitleLargeLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2936getTitleLargeLineHeightXSAIIZE() {
        return TitleLargeLineHeight;
    }

    /* JADX INFO: renamed from: getTitleLargeSize-XSAIIZE, reason: not valid java name */
    public final long m2937getTitleLargeSizeXSAIIZE() {
        return TitleLargeSize;
    }

    /* JADX INFO: renamed from: getTitleLargeTracking-XSAIIZE, reason: not valid java name */
    public final long m2938getTitleLargeTrackingXSAIIZE() {
        return TitleLargeTracking;
    }

    public final FontWeight getTitleLargeWeight() {
        return TitleLargeWeight;
    }

    public final GenericFontFamily getTitleMediumFont() {
        return TitleMediumFont;
    }

    /* JADX INFO: renamed from: getTitleMediumLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2939getTitleMediumLineHeightXSAIIZE() {
        return TitleMediumLineHeight;
    }

    /* JADX INFO: renamed from: getTitleMediumSize-XSAIIZE, reason: not valid java name */
    public final long m2940getTitleMediumSizeXSAIIZE() {
        return TitleMediumSize;
    }

    /* JADX INFO: renamed from: getTitleMediumTracking-XSAIIZE, reason: not valid java name */
    public final long m2941getTitleMediumTrackingXSAIIZE() {
        return TitleMediumTracking;
    }

    public final FontWeight getTitleMediumWeight() {
        return TitleMediumWeight;
    }

    public final GenericFontFamily getTitleSmallFont() {
        return TitleSmallFont;
    }

    /* JADX INFO: renamed from: getTitleSmallLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2942getTitleSmallLineHeightXSAIIZE() {
        return TitleSmallLineHeight;
    }

    /* JADX INFO: renamed from: getTitleSmallSize-XSAIIZE, reason: not valid java name */
    public final long m2943getTitleSmallSizeXSAIIZE() {
        return TitleSmallSize;
    }

    /* JADX INFO: renamed from: getTitleSmallTracking-XSAIIZE, reason: not valid java name */
    public final long m2944getTitleSmallTrackingXSAIIZE() {
        return TitleSmallTracking;
    }

    public final FontWeight getTitleSmallWeight() {
        return TitleSmallWeight;
    }
}
