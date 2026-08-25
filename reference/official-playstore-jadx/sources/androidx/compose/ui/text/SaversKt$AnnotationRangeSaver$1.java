package androidx.compose.ui.text;

import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.text.AnnotatedString;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.r;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u000e\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "Landroidx/compose/runtime/saveable/SaverScope;", "it", "Landroidx/compose/ui/text/AnnotatedString$Range;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SaversKt$AnnotationRangeSaver$1 extends r implements p<SaverScope, AnnotatedString.Range<? extends Object>, Object> {
    public static final SaversKt$AnnotationRangeSaver$1 INSTANCE = new SaversKt$AnnotationRangeSaver$1();

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

    public SaversKt$AnnotationRangeSaver$1() {
        super(2);
    }

    @Override // r7.p
    public final Object invoke(SaverScope saverScope, AnnotatedString.Range<? extends Object> range) {
        Object objSave;
        Object item = range.getItem();
        AnnotationType annotationType = item instanceof ParagraphStyle ? AnnotationType.Paragraph : item instanceof SpanStyle ? AnnotationType.Span : item instanceof VerbatimTtsAnnotation ? AnnotationType.VerbatimTts : item instanceof UrlAnnotation ? AnnotationType.Url : AnnotationType.String;
        int i10 = WhenMappings.$EnumSwitchMapping$0[annotationType.ordinal()];
        if (i10 == 1) {
            objSave = SaversKt.save((ParagraphStyle) range.getItem(), SaversKt.getParagraphStyleSaver(), saverScope);
        } else if (i10 == 2) {
            objSave = SaversKt.save((SpanStyle) range.getItem(), SaversKt.getSpanStyleSaver(), saverScope);
        } else if (i10 == 3) {
            objSave = SaversKt.save((VerbatimTtsAnnotation) range.getItem(), SaversKt.VerbatimTtsAnnotationSaver, saverScope);
        } else if (i10 == 4) {
            objSave = SaversKt.save((UrlAnnotation) range.getItem(), SaversKt.UrlAnnotationSaver, saverScope);
        } else {
            if (i10 != 5) {
                throw new NoWhenBranchMatchedException();
            }
            objSave = SaversKt.save(range.getItem());
        }
        return t7.a.a(SaversKt.save(annotationType), objSave, SaversKt.save(Integer.valueOf(range.getStart())), SaversKt.save(Integer.valueOf(range.getEnd())), SaversKt.save(range.getTag()));
    }
}
