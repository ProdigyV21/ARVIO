package androidx.compose.ui.text.input;

import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0007\u0018\u0000 )2\u00020\u0001:\u0001)BE\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0002\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\r\u0010\u000eB;\b\u0017\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0002\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0004\b\r\u0010\u000fJN\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000bø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011JD\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\tH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u001a\u0010\u0016\u001a\u00020\u00022\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001c\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001d\u0010\u0005\u001a\u00020\u00048\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0005\u0010!\u001a\u0004\b\"\u0010\u001aR\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001e\u001a\u0004\b#\u0010 R\u001d\u0010\b\u001a\u00020\u00078\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\b\u0010!\u001a\u0004\b$\u0010\u001aR\u001d\u0010\n\u001a\u00020\t8\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\n\u0010!\u001a\u0004\b%\u0010\u001aR\u0019\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010&\u001a\u0004\b'\u0010(\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006*"}, d2 = {"Landroidx/compose/ui/text/input/ImeOptions;", "", "", "singleLine", "Landroidx/compose/ui/text/input/KeyboardCapitalization;", "capitalization", "autoCorrect", "Landroidx/compose/ui/text/input/KeyboardType;", "keyboardType", "Landroidx/compose/ui/text/input/ImeAction;", "imeAction", "Landroidx/compose/ui/text/input/PlatformImeOptions;", "platformImeOptions", "<init>", "(ZIZIILandroidx/compose/ui/text/input/PlatformImeOptions;Lkotlin/jvm/internal/h;)V", "(ZIZIILkotlin/jvm/internal/h;)V", "copy-YTHSh70", "(ZIZIILandroidx/compose/ui/text/input/PlatformImeOptions;)Landroidx/compose/ui/text/input/ImeOptions;", "copy", "copy-uxg59PA", "(ZIZII)Landroidx/compose/ui/text/input/ImeOptions;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Z", "getSingleLine", "()Z", "I", "getCapitalization-IUNYP9k", "getAutoCorrect", "getKeyboardType-PjHm6EE", "getImeAction-eUduSuo", "Landroidx/compose/ui/text/input/PlatformImeOptions;", "getPlatformImeOptions", "()Landroidx/compose/ui/text/input/PlatformImeOptions;", "Companion", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ImeOptions {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ImeOptions Default = new ImeOptions(false, 0, false, 0, 0, null, 63, null);
    private final boolean autoCorrect;
    private final int capitalization;
    private final int imeAction;
    private final int keyboardType;
    private final PlatformImeOptions platformImeOptions;
    private final boolean singleLine;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/ui/text/input/ImeOptions$Companion;", "", "()V", "Default", "Landroidx/compose/ui/text/input/ImeOptions;", "getDefault", "()Landroidx/compose/ui/text/input/ImeOptions;", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final ImeOptions getDefault() {
            return ImeOptions.Default;
        }

        private Companion() {
        }
    }

    public /* synthetic */ ImeOptions(boolean z, int i10, boolean z5, int i11, int i12, PlatformImeOptions platformImeOptions, h hVar) {
        this(z, i10, z5, i11, i12, platformImeOptions);
    }

    /* JADX INFO: renamed from: copy-YTHSh70$default, reason: not valid java name */
    public static /* synthetic */ ImeOptions m5375copyYTHSh70$default(ImeOptions imeOptions, boolean z, int i10, boolean z5, int i11, int i12, PlatformImeOptions platformImeOptions, int i13, Object obj) {
        if ((i13 & 1) != 0) {
            z = imeOptions.singleLine;
        }
        if ((i13 & 2) != 0) {
            i10 = imeOptions.capitalization;
        }
        if ((i13 & 4) != 0) {
            z5 = imeOptions.autoCorrect;
        }
        if ((i13 & 8) != 0) {
            i11 = imeOptions.keyboardType;
        }
        if ((i13 & 16) != 0) {
            i12 = imeOptions.imeAction;
        }
        if ((i13 & 32) != 0) {
            platformImeOptions = imeOptions.platformImeOptions;
        }
        int i14 = i12;
        PlatformImeOptions platformImeOptions2 = platformImeOptions;
        return imeOptions.m5377copyYTHSh70(z, i10, z5, i11, i14, platformImeOptions2);
    }

    /* JADX INFO: renamed from: copy-uxg59PA$default, reason: not valid java name */
    public static /* synthetic */ ImeOptions m5376copyuxg59PA$default(ImeOptions imeOptions, boolean z, int i10, boolean z5, int i11, int i12, int i13, Object obj) {
        if ((i13 & 1) != 0) {
            z = imeOptions.singleLine;
        }
        if ((i13 & 2) != 0) {
            i10 = imeOptions.capitalization;
        }
        if ((i13 & 4) != 0) {
            z5 = imeOptions.autoCorrect;
        }
        if ((i13 & 8) != 0) {
            i11 = imeOptions.keyboardType;
        }
        if ((i13 & 16) != 0) {
            i12 = imeOptions.imeAction;
        }
        int i14 = i12;
        boolean z10 = z5;
        return imeOptions.m5378copyuxg59PA(z, i10, z10, i11, i14);
    }

    /* JADX INFO: renamed from: copy-YTHSh70, reason: not valid java name */
    public final ImeOptions m5377copyYTHSh70(boolean singleLine, int capitalization, boolean autoCorrect, int keyboardType, int imeAction, PlatformImeOptions platformImeOptions) {
        return new ImeOptions(singleLine, capitalization, autoCorrect, keyboardType, imeAction, platformImeOptions, (h) null);
    }

    @e
    /* JADX INFO: renamed from: copy-uxg59PA, reason: not valid java name */
    public final /* synthetic */ ImeOptions m5378copyuxg59PA(boolean singleLine, int capitalization, boolean autoCorrect, int keyboardType, int imeAction) {
        return new ImeOptions(singleLine, capitalization, autoCorrect, keyboardType, imeAction, this.platformImeOptions, (h) null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ImeOptions)) {
            return false;
        }
        ImeOptions imeOptions = (ImeOptions) other;
        return this.singleLine == imeOptions.singleLine && KeyboardCapitalization.m5387equalsimpl0(this.capitalization, imeOptions.capitalization) && this.autoCorrect == imeOptions.autoCorrect && KeyboardType.m5402equalsimpl0(this.keyboardType, imeOptions.keyboardType) && ImeAction.m5355equalsimpl0(this.imeAction, imeOptions.imeAction) && p.a(this.platformImeOptions, imeOptions.platformImeOptions);
    }

    public final boolean getAutoCorrect() {
        return this.autoCorrect;
    }

    /* JADX INFO: renamed from: getCapitalization-IUNYP9k, reason: not valid java name and from getter */
    public final int getCapitalization() {
        return this.capitalization;
    }

    /* JADX INFO: renamed from: getImeAction-eUduSuo, reason: not valid java name and from getter */
    public final int getImeAction() {
        return this.imeAction;
    }

    /* JADX INFO: renamed from: getKeyboardType-PjHm6EE, reason: not valid java name and from getter */
    public final int getKeyboardType() {
        return this.keyboardType;
    }

    public final PlatformImeOptions getPlatformImeOptions() {
        return this.platformImeOptions;
    }

    public final boolean getSingleLine() {
        return this.singleLine;
    }

    public int hashCode() {
        int iM5356hashCodeimpl = (ImeAction.m5356hashCodeimpl(this.imeAction) + ((KeyboardType.m5403hashCodeimpl(this.keyboardType) + ((((KeyboardCapitalization.m5388hashCodeimpl(this.capitalization) + ((this.singleLine ? 1231 : 1237) * 31)) * 31) + (this.autoCorrect ? 1231 : 1237)) * 31)) * 31)) * 31;
        PlatformImeOptions platformImeOptions = this.platformImeOptions;
        return iM5356hashCodeimpl + (platformImeOptions != null ? platformImeOptions.hashCode() : 0);
    }

    public String toString() {
        return "ImeOptions(singleLine=" + this.singleLine + ", capitalization=" + ((Object) KeyboardCapitalization.m5389toStringimpl(this.capitalization)) + ", autoCorrect=" + this.autoCorrect + ", keyboardType=" + ((Object) KeyboardType.m5404toStringimpl(this.keyboardType)) + ", imeAction=" + ((Object) ImeAction.m5357toStringimpl(this.imeAction)) + ", platformImeOptions=" + this.platformImeOptions + ')';
    }

    @e
    public /* synthetic */ ImeOptions(boolean z, int i10, boolean z5, int i11, int i12, h hVar) {
        this(z, i10, z5, i11, i12);
    }

    private ImeOptions(boolean z, int i10, boolean z5, int i11, int i12, PlatformImeOptions platformImeOptions) {
        this.singleLine = z;
        this.capitalization = i10;
        this.autoCorrect = z5;
        this.keyboardType = i11;
        this.imeAction = i12;
        this.platformImeOptions = platformImeOptions;
    }

    public /* synthetic */ ImeOptions(boolean z, int i10, boolean z5, int i11, int i12, PlatformImeOptions platformImeOptions, int i13, h hVar) {
        this((i13 & 1) != 0 ? false : z, (i13 & 2) != 0 ? KeyboardCapitalization.INSTANCE.m5396getNoneIUNYP9k() : i10, (i13 & 4) != 0 ? true : z5, (i13 & 8) != 0 ? KeyboardType.INSTANCE.m5422getTextPjHm6EE() : i11, (i13 & 16) != 0 ? ImeAction.INSTANCE.m5367getDefaulteUduSuo() : i12, (i13 & 32) != 0 ? null : platformImeOptions, (h) null);
    }

    public /* synthetic */ ImeOptions(boolean z, int i10, boolean z5, int i11, int i12, int i13, h hVar) {
        this((i13 & 1) != 0 ? false : z, (i13 & 2) != 0 ? KeyboardCapitalization.INSTANCE.m5396getNoneIUNYP9k() : i10, (i13 & 4) != 0 ? true : z5, (i13 & 8) != 0 ? KeyboardType.INSTANCE.m5422getTextPjHm6EE() : i11, (i13 & 16) != 0 ? ImeAction.INSTANCE.m5367getDefaulteUduSuo() : i12, (h) null);
    }

    private ImeOptions(boolean z, int i10, boolean z5, int i11, int i12) {
        this(z, i10, z5, i11, i12, (PlatformImeOptions) null, (h) null);
    }
}
