package androidx.compose.ui.graphics;

import android.credentials.CreateCredentialException;
import android.credentials.CreateCredentialResponse;
import android.credentials.GetCredentialResponse;
import android.graphics.ColorSpace;
import android.view.contentcapture.ContentCaptureSession;
import androidx.compose.ui.graphics.colorspace.ColorSpaces;
import androidx.compose.ui.graphics.colorspace.DoubleFunction;
import androidx.compose.ui.graphics.colorspace.Rgb;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.input.TransformedText;
import androidx.compose.ui.text.input.VisualTransformation;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c implements DoubleFunction, VisualTransformation {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1890a;

    public /* synthetic */ c(int i10) {
        this.f1890a = i10;
    }

    public static /* bridge */ /* synthetic */ CreateCredentialException b(Object obj) {
        return (CreateCredentialException) obj;
    }

    public static /* bridge */ /* synthetic */ CreateCredentialResponse c(Object obj) {
        return (CreateCredentialResponse) obj;
    }

    public static /* bridge */ /* synthetic */ GetCredentialResponse d(Object obj) {
        return (GetCredentialResponse) obj;
    }

    public static /* bridge */ /* synthetic */ ColorSpace f(Object obj) {
        return (ColorSpace) obj;
    }

    public static /* bridge */ /* synthetic */ ContentCaptureSession g(Object obj) {
        return (ContentCaptureSession) obj;
    }

    @Override // androidx.compose.ui.text.input.VisualTransformation
    public TransformedText filter(AnnotatedString annotatedString) {
        return VisualTransformation.Companion.None$lambda$0(annotatedString);
    }

    @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
    public double invoke(double d4) {
        switch (this.f1890a) {
            case 11:
                return ColorSpaces.ExtendedSrgb$lambda$0(d4);
            case 12:
                return ColorSpaces.ExtendedSrgb$lambda$1(d4);
            default:
                return Rgb.DoubleIdentity$lambda$12(d4);
        }
    }
}
