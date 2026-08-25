package androidx.compose.ui.platform;

import androidx.compose.ui.text.font.FontFamily;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class CompositionLocalsKt$LocalFontFamilyResolver$1 extends kotlin.jvm.internal.r implements r7.a<FontFamily.Resolver> {
    public static final CompositionLocalsKt$LocalFontFamilyResolver$1 INSTANCE = new CompositionLocalsKt$LocalFontFamilyResolver$1();

    public CompositionLocalsKt$LocalFontFamilyResolver$1() {
        super(0);
    }

    @Override // r7.a
    public final FontFamily.Resolver invoke() {
        CompositionLocalsKt.noLocalProvidedFor("LocalFontFamilyResolver");
        throw new KotlinNothingValueException();
    }
}
