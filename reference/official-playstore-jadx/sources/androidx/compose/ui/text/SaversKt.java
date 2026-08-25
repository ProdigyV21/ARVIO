package androidx.compose.ui.text;

import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.Locale;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.unit.TextUnit;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import r7.l;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000ø\u0001\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aK\u0010\t\u001a\u00020\b\"\u0014\b\u0000\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0000\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0005\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\t\u0010\n\u001aN\u0010\f\u001a\u0004\u0018\u00018\u0003\"\u0014\b\u0000\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0000\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003\"\u0006\b\u0003\u0010\u000b\u0018\u00012\b\u0010\u0004\u001a\u0004\u0018\u00018\u00022\u0006\u0010\u0005\u001a\u00028\u0000H\u0080\b¢\u0006\u0004\b\f\u0010\r\u001a\u0080\u0001\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0013\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0003*\u00020\b2.\u0010\t\u001a*\u0012\u0004\u0012\u00020\u0006\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0004\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u000e¢\u0006\u0002\b\u00112#\u0010\f\u001a\u001f\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0004\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0012H\u0002¢\u0006\u0004\b\u0014\u0010\u0015\u001a!\u0010\t\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u00012\b\u0010\u0004\u001a\u0004\u0018\u00018\u0000H\u0000¢\u0006\u0004\b\t\u0010\u0016\u001a$\u0010\f\u001a\u0004\u0018\u00018\u0000\"\u0006\b\u0000\u0010\u000b\u0018\u00012\b\u0010\u0004\u001a\u0004\u0018\u00010\bH\u0080\b¢\u0006\u0004\b\f\u0010\u0016\"&\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\b0\u00008\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\".\u0010\u001e\u001a\u001c\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\b0\u001d0\u001c\u0012\u0004\u0012\u00020\b0\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u0019\".\u0010\u001f\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\b0\u001d\u0012\u0004\u0012\u00020\b0\u00008\u0002X\u0082\u0004¢\u0006\f\n\u0004\b\u001f\u0010\u0019\u0012\u0004\b \u0010!\" \u0010#\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\b0\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010\u0019\"&\u0010%\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\b0\u00008\u0002X\u0082\u0004¢\u0006\f\n\u0004\b%\u0010\u0019\u0012\u0004\b&\u0010!\"&\u0010(\u001a\u000e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020\b0\u00008\u0000X\u0080\u0004¢\u0006\f\n\u0004\b(\u0010\u0019\u001a\u0004\b)\u0010\u001b\"&\u0010+\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\b0\u00008\u0000X\u0080\u0004¢\u0006\f\n\u0004\b+\u0010\u0019\u001a\u0004\b,\u0010\u001b\" \u0010.\u001a\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\b0\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010\u0019\" \u00100\u001a\u000e\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u00020\b0\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u0010\u0019\" \u00102\u001a\u000e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020\b0\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u0010\u0019\" \u00104\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020\b0\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u0010\u0019\" \u00106\u001a\u000e\u0012\u0004\u0012\u000205\u0012\u0004\u0012\u00020\b0\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b6\u0010\u0019\" \u00108\u001a\u000e\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020\b0\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b8\u0010\u0019\" \u0010:\u001a\u000e\u0012\u0004\u0012\u000209\u0012\u0004\u0012\u00020\b0\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010\u0019\" \u0010<\u001a\u000e\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020\b0\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b<\u0010=\" \u0010?\u001a\u000e\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020\b0\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010=\" \u0010A\u001a\u000e\u0012\u0004\u0012\u00020@\u0012\u0004\u0012\u00020\b0\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bA\u0010=\" \u0010C\u001a\u000e\u0012\u0004\u0012\u00020B\u0012\u0004\u0012\u00020\b0\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bC\u0010\u0019\" \u0010E\u001a\u000e\u0012\u0004\u0012\u00020D\u0012\u0004\u0012\u00020\b0\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bE\u0010\u0019\"$\u0010I\u001a\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\b0\u0000*\u00020F8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bG\u0010H\"$\u0010I\u001a\u000e\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u00020\b0\u0000*\u00020J8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bG\u0010K\"$\u0010I\u001a\u000e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020\b0\u0000*\u00020L8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bG\u0010M\"$\u0010I\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020\b0\u0000*\u00020N8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bG\u0010O\"$\u0010I\u001a\u000e\u0012\u0004\u0012\u000205\u0012\u0004\u0012\u00020\b0\u0000*\u00020P8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bG\u0010Q\"$\u0010I\u001a\u000e\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020\b0\u0000*\u00020R8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bG\u0010S\"$\u0010I\u001a\u000e\u0012\u0004\u0012\u000209\u0012\u0004\u0012\u00020\b0\u0000*\u00020T8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bG\u0010U\"$\u0010I\u001a\u000e\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020\b0\u0000*\u00020V8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bG\u0010W\"$\u0010I\u001a\u000e\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020\b0\u0000*\u00020X8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bG\u0010Y\"$\u0010I\u001a\u000e\u0012\u0004\u0012\u00020@\u0012\u0004\u0012\u00020\b0\u0000*\u00020Z8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bG\u0010[\"$\u0010I\u001a\u000e\u0012\u0004\u0012\u00020B\u0012\u0004\u0012\u00020\b0\u0000*\u00020\\8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bG\u0010]\"$\u0010I\u001a\u000e\u0012\u0004\u0012\u00020D\u0012\u0004\u0012\u00020\b0\u0000*\u00020^8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bG\u0010_¨\u0006`"}, d2 = {"Landroidx/compose/runtime/saveable/Saver;", "T", "Original", "Saveable", "value", "saver", "Landroidx/compose/runtime/saveable/SaverScope;", "scope", "", "save", "(Ljava/lang/Object;Landroidx/compose/runtime/saveable/Saver;Landroidx/compose/runtime/saveable/SaverScope;)Ljava/lang/Object;", "Result", "restore", "(Ljava/lang/Object;Landroidx/compose/runtime/saveable/Saver;)Ljava/lang/Object;", "Lkotlin/Function2;", "Lx6/y;", ContentDisposition.Parameters.Name, "Lx6/n;", "Lkotlin/Function1;", "Landroidx/compose/ui/text/NonNullValueClassSaver;", "NonNullValueClassSaver", "(Lr7/p;Lr7/l;)Landroidx/compose/ui/text/NonNullValueClassSaver;", "(Ljava/lang/Object;)Ljava/lang/Object;", "Landroidx/compose/ui/text/AnnotatedString;", "AnnotatedStringSaver", "Landroidx/compose/runtime/saveable/Saver;", "getAnnotatedStringSaver", "()Landroidx/compose/runtime/saveable/Saver;", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "AnnotationRangeListSaver", "AnnotationRangeSaver", "getAnnotationRangeSaver$annotations", "()V", "Landroidx/compose/ui/text/VerbatimTtsAnnotation;", "VerbatimTtsAnnotationSaver", "Landroidx/compose/ui/text/UrlAnnotation;", "UrlAnnotationSaver", "getUrlAnnotationSaver$annotations", "Landroidx/compose/ui/text/ParagraphStyle;", "ParagraphStyleSaver", "getParagraphStyleSaver", "Landroidx/compose/ui/text/SpanStyle;", "SpanStyleSaver", "getSpanStyleSaver", "Landroidx/compose/ui/text/style/TextDecoration;", "TextDecorationSaver", "Landroidx/compose/ui/text/style/TextGeometricTransform;", "TextGeometricTransformSaver", "Landroidx/compose/ui/text/style/TextIndent;", "TextIndentSaver", "Landroidx/compose/ui/text/font/FontWeight;", "FontWeightSaver", "Landroidx/compose/ui/text/style/BaselineShift;", "BaselineShiftSaver", "Landroidx/compose/ui/text/TextRange;", "TextRangeSaver", "Landroidx/compose/ui/graphics/Shadow;", "ShadowSaver", "Landroidx/compose/ui/graphics/Color;", "ColorSaver", "Landroidx/compose/ui/text/NonNullValueClassSaver;", "Landroidx/compose/ui/unit/TextUnit;", "TextUnitSaver", "Landroidx/compose/ui/geometry/Offset;", "OffsetSaver", "Landroidx/compose/ui/text/intl/LocaleList;", "LocaleListSaver", "Landroidx/compose/ui/text/intl/Locale;", "LocaleSaver", "Landroidx/compose/ui/text/style/TextDecoration$Companion;", "getSaver", "(Landroidx/compose/ui/text/style/TextDecoration$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Saver", "Landroidx/compose/ui/text/style/TextGeometricTransform$Companion;", "(Landroidx/compose/ui/text/style/TextGeometricTransform$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/style/TextIndent$Companion;", "(Landroidx/compose/ui/text/style/TextIndent$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/font/FontWeight$Companion;", "(Landroidx/compose/ui/text/font/FontWeight$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/style/BaselineShift$Companion;", "(Landroidx/compose/ui/text/style/BaselineShift$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/TextRange$Companion;", "(Landroidx/compose/ui/text/TextRange$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/graphics/Shadow$Companion;", "(Landroidx/compose/ui/graphics/Shadow$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/graphics/Color$Companion;", "(Landroidx/compose/ui/graphics/Color$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/unit/TextUnit$Companion;", "(Landroidx/compose/ui/unit/TextUnit$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/geometry/Offset$Companion;", "(Landroidx/compose/ui/geometry/Offset$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/intl/LocaleList$Companion;", "(Landroidx/compose/ui/text/intl/LocaleList$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/intl/Locale$Companion;", "(Landroidx/compose/ui/text/intl/Locale$Companion;)Landroidx/compose/runtime/saveable/Saver;", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SaversKt {
    private static final Saver<AnnotatedString, Object> AnnotatedStringSaver = SaverKt.Saver(SaversKt$AnnotatedStringSaver$1.INSTANCE, SaversKt$AnnotatedStringSaver$2.INSTANCE);
    private static final Saver<List<AnnotatedString.Range<? extends Object>>, Object> AnnotationRangeListSaver = SaverKt.Saver(SaversKt$AnnotationRangeListSaver$1.INSTANCE, SaversKt$AnnotationRangeListSaver$2.INSTANCE);
    private static final Saver<AnnotatedString.Range<? extends Object>, Object> AnnotationRangeSaver = SaverKt.Saver(SaversKt$AnnotationRangeSaver$1.INSTANCE, SaversKt$AnnotationRangeSaver$2.INSTANCE);
    private static final Saver<VerbatimTtsAnnotation, Object> VerbatimTtsAnnotationSaver = SaverKt.Saver(SaversKt$VerbatimTtsAnnotationSaver$1.INSTANCE, SaversKt$VerbatimTtsAnnotationSaver$2.INSTANCE);
    private static final Saver<UrlAnnotation, Object> UrlAnnotationSaver = SaverKt.Saver(SaversKt$UrlAnnotationSaver$1.INSTANCE, SaversKt$UrlAnnotationSaver$2.INSTANCE);
    private static final Saver<ParagraphStyle, Object> ParagraphStyleSaver = SaverKt.Saver(SaversKt$ParagraphStyleSaver$1.INSTANCE, SaversKt$ParagraphStyleSaver$2.INSTANCE);
    private static final Saver<SpanStyle, Object> SpanStyleSaver = SaverKt.Saver(SaversKt$SpanStyleSaver$1.INSTANCE, SaversKt$SpanStyleSaver$2.INSTANCE);
    private static final Saver<TextDecoration, Object> TextDecorationSaver = SaverKt.Saver(SaversKt$TextDecorationSaver$1.INSTANCE, SaversKt$TextDecorationSaver$2.INSTANCE);
    private static final Saver<TextGeometricTransform, Object> TextGeometricTransformSaver = SaverKt.Saver(SaversKt$TextGeometricTransformSaver$1.INSTANCE, SaversKt$TextGeometricTransformSaver$2.INSTANCE);
    private static final Saver<TextIndent, Object> TextIndentSaver = SaverKt.Saver(SaversKt$TextIndentSaver$1.INSTANCE, SaversKt$TextIndentSaver$2.INSTANCE);
    private static final Saver<FontWeight, Object> FontWeightSaver = SaverKt.Saver(SaversKt$FontWeightSaver$1.INSTANCE, SaversKt$FontWeightSaver$2.INSTANCE);
    private static final Saver<BaselineShift, Object> BaselineShiftSaver = SaverKt.Saver(SaversKt$BaselineShiftSaver$1.INSTANCE, SaversKt$BaselineShiftSaver$2.INSTANCE);
    private static final Saver<TextRange, Object> TextRangeSaver = SaverKt.Saver(SaversKt$TextRangeSaver$1.INSTANCE, SaversKt$TextRangeSaver$2.INSTANCE);
    private static final Saver<Shadow, Object> ShadowSaver = SaverKt.Saver(SaversKt$ShadowSaver$1.INSTANCE, SaversKt$ShadowSaver$2.INSTANCE);
    private static final NonNullValueClassSaver<Color, Object> ColorSaver = NonNullValueClassSaver(SaversKt$ColorSaver$1.INSTANCE, SaversKt$ColorSaver$2.INSTANCE);
    private static final NonNullValueClassSaver<TextUnit, Object> TextUnitSaver = NonNullValueClassSaver(SaversKt$TextUnitSaver$1.INSTANCE, SaversKt$TextUnitSaver$2.INSTANCE);
    private static final NonNullValueClassSaver<Offset, Object> OffsetSaver = NonNullValueClassSaver(SaversKt$OffsetSaver$1.INSTANCE, SaversKt$OffsetSaver$2.INSTANCE);
    private static final Saver<LocaleList, Object> LocaleListSaver = SaverKt.Saver(SaversKt$LocaleListSaver$1.INSTANCE, SaversKt$LocaleListSaver$2.INSTANCE);
    private static final Saver<Locale, Object> LocaleSaver = SaverKt.Saver(SaversKt$LocaleSaver$1.INSTANCE, SaversKt$LocaleSaver$2.INSTANCE);

    private static final <Original, Saveable> NonNullValueClassSaver<Original, Saveable> NonNullValueClassSaver(final p<? super SaverScope, ? super Original, ? extends Saveable> pVar, final l<? super Saveable, ? extends Original> lVar) {
        return new NonNullValueClassSaver<Original, Saveable>() { // from class: androidx.compose.ui.text.SaversKt.NonNullValueClassSaver.1
            @Override // androidx.compose.runtime.saveable.Saver
            public Original restore(Saveable value) {
                return (Original) lVar.invoke(value);
            }

            @Override // androidx.compose.runtime.saveable.Saver
            public Saveable save(SaverScope saverScope, Original original) {
                return (Saveable) pVar.invoke(saverScope, original);
            }
        };
    }

    public static final Saver<AnnotatedString, Object> getAnnotatedStringSaver() {
        return AnnotatedStringSaver;
    }

    private static /* synthetic */ void getAnnotationRangeSaver$annotations() {
    }

    public static final Saver<ParagraphStyle, Object> getParagraphStyleSaver() {
        return ParagraphStyleSaver;
    }

    public static final Saver<TextDecoration, Object> getSaver(TextDecoration.Companion companion) {
        return TextDecorationSaver;
    }

    public static final Saver<SpanStyle, Object> getSpanStyleSaver() {
        return SpanStyleSaver;
    }

    private static /* synthetic */ void getUrlAnnotationSaver$annotations() {
    }

    public static final <T extends Saver<Original, Saveable>, Original, Saveable, Result> Result restore(Saveable saveable, T t2) {
        if ((kotlin.jvm.internal.p.a(saveable, Boolean.FALSE) && !(t2 instanceof NonNullValueClassSaver)) || saveable == null) {
            return null;
        }
        t2.restore(saveable);
        kotlin.jvm.internal.p.h();
        throw null;
    }

    public static final <T> T save(T t2) {
        return t2;
    }

    public static final Saver<TextGeometricTransform, Object> getSaver(TextGeometricTransform.Companion companion) {
        return TextGeometricTransformSaver;
    }

    public static final <T extends Saver<Original, Saveable>, Original, Saveable> Object save(Original original, T t2, SaverScope saverScope) {
        Object objSave;
        return (original == null || (objSave = t2.save(saverScope, original)) == null) ? Boolean.FALSE : objSave;
    }

    public static final Saver<TextIndent, Object> getSaver(TextIndent.Companion companion) {
        return TextIndentSaver;
    }

    public static final Saver<FontWeight, Object> getSaver(FontWeight.Companion companion) {
        return FontWeightSaver;
    }

    public static final <Result> Result restore(Object obj) {
        if (obj == null) {
            return null;
        }
        kotlin.jvm.internal.p.h();
        throw null;
    }

    public static final Saver<BaselineShift, Object> getSaver(BaselineShift.Companion companion) {
        return BaselineShiftSaver;
    }

    public static final Saver<TextRange, Object> getSaver(TextRange.Companion companion) {
        return TextRangeSaver;
    }

    public static final Saver<Shadow, Object> getSaver(Shadow.Companion companion) {
        return ShadowSaver;
    }

    public static final Saver<Color, Object> getSaver(Color.Companion companion) {
        return ColorSaver;
    }

    public static final Saver<TextUnit, Object> getSaver(TextUnit.Companion companion) {
        return TextUnitSaver;
    }

    public static final Saver<Offset, Object> getSaver(Offset.Companion companion) {
        return OffsetSaver;
    }

    public static final Saver<LocaleList, Object> getSaver(LocaleList.Companion companion) {
        return LocaleListSaver;
    }

    public static final Saver<Locale, Object> getSaver(Locale.Companion companion) {
        return LocaleSaver;
    }
}
