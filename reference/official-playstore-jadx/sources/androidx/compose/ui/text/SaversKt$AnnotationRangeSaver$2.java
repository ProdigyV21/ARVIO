package androidx.compose.ui.text;

import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.text.AnnotatedString;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0002\u0018\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/ui/text/AnnotatedString$Range;", "", "it", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SaversKt$AnnotationRangeSaver$2 extends r implements l<Object, AnnotatedString.Range<? extends Object>> {
    public static final SaversKt$AnnotationRangeSaver$2 INSTANCE = new SaversKt$AnnotationRangeSaver$2();

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AnnotationType.values().length];
            try {
                iArr[AnnotationType.Paragraph.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[AnnotationType.Span.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[AnnotationType.VerbatimTts.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[AnnotationType.Url.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[AnnotationType.String.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public SaversKt$AnnotationRangeSaver$2() {
        super(1);
    }

    @Override // r7.l
    public final AnnotatedString.Range<? extends Object> invoke(Object obj) {
        List list = (List) obj;
        Object obj2 = list.get(0);
        AnnotationType annotationType = obj2 != null ? (AnnotationType) obj2 : null;
        Object obj3 = list.get(2);
        int iIntValue = (obj3 != null ? (Integer) obj3 : null).intValue();
        Object obj4 = list.get(3);
        int iIntValue2 = (obj4 != null ? (Integer) obj4 : null).intValue();
        Object obj5 = list.get(4);
        String str = obj5 != null ? (String) obj5 : null;
        int i10 = WhenMappings.$EnumSwitchMapping$0[annotationType.ordinal()];
        if (i10 == 1) {
            Object obj6 = list.get(1);
            Saver<ParagraphStyle, Object> paragraphStyleSaver = SaversKt.getParagraphStyleSaver();
            if ((!p.a(obj6, Boolean.FALSE) || (paragraphStyleSaver instanceof NonNullValueClassSaver)) && obj6 != null) {
                paragraphStyleRestore = paragraphStyleSaver.restore(obj6);
            }
            return new AnnotatedString.Range<>(paragraphStyleRestore, iIntValue, iIntValue2, str);
        }
        if (i10 == 2) {
            Object obj7 = list.get(1);
            Saver<SpanStyle, Object> spanStyleSaver = SaversKt.getSpanStyleSaver();
            if ((!p.a(obj7, Boolean.FALSE) || (spanStyleSaver instanceof NonNullValueClassSaver)) && obj7 != null) {
                paragraphStyleRestore = spanStyleSaver.restore(obj7);
            }
            return new AnnotatedString.Range<>(paragraphStyleRestore, iIntValue, iIntValue2, str);
        }
        if (i10 == 3) {
            Object obj8 = list.get(1);
            Saver saver = SaversKt.VerbatimTtsAnnotationSaver;
            if ((!p.a(obj8, Boolean.FALSE) || (saver instanceof NonNullValueClassSaver)) && obj8 != null) {
                paragraphStyleRestore = (VerbatimTtsAnnotation) saver.restore(obj8);
            }
            return new AnnotatedString.Range<>(paragraphStyleRestore, iIntValue, iIntValue2, str);
        }
        if (i10 != 4) {
            if (i10 != 5) {
                throw new NoWhenBranchMatchedException();
            }
            Object obj9 = list.get(1);
            return new AnnotatedString.Range<>(obj9 != null ? (String) obj9 : null, iIntValue, iIntValue2, str);
        }
        Object obj10 = list.get(1);
        Saver saver2 = SaversKt.UrlAnnotationSaver;
        if ((!p.a(obj10, Boolean.FALSE) || (saver2 instanceof NonNullValueClassSaver)) && obj10 != null) {
            paragraphStyleRestore = (UrlAnnotation) saver2.restore(obj10);
        }
        return new AnnotatedString.Range<>(paragraphStyleRestore, iIntValue, iIntValue2, str);
    }
}
