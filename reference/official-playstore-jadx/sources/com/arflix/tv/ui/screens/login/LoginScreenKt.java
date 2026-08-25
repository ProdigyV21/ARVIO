package com.arflix.tv.ui.screens.login;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.text.KeyboardActionScope;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.tv.material3.TextKt;
import com.arflix.tv.ui.theme.ColorKt;
import kotlin.Metadata;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\u001a)\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0001\u001a\u00020\u00002\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u007f\u0010\u001a\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00072\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00030\t2\u0006\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0017\u001a\u00020\u0016H\u0003¢\u0006\u0004\b\u0018\u0010\u0019\u001aG\u0010\u001f\u001a\u00020\u00032\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u00132\b\b\u0002\u0010\u0017\u001a\u00020\u0016H\u0003¢\u0006\u0004\b\u001f\u0010 \"\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#¨\u0006,²\u0006\f\u0010%\u001a\u00020$8\nX\u008a\u0084\u0002²\u0006\u000e\u0010&\u001a\u00020\u00078\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010'\u001a\u00020\u00078\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010(\u001a\u00020\u00138\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010)\u001a\u00020\u00078\n@\nX\u008a\u008e\u0002²\u0006\f\u0010+\u001a\u00020*8\nX\u008a\u0084\u0002"}, d2 = {"Lcom/arflix/tv/ui/screens/login/LoginViewModel;", "viewModel", "Lkotlin/Function0;", "Lx6/t0;", "onLoginSuccess", "LoginScreen", "(Lcom/arflix/tv/ui/screens/login/LoginViewModel;Lr7/a;Landroidx/compose/runtime/Composer;II)V", "", "value", "Lkotlin/Function1;", "onValueChange", "placeholder", "Landroidx/compose/ui/text/input/KeyboardType;", "keyboardType", "Landroidx/compose/ui/text/input/ImeAction;", "imeAction", "Landroidx/compose/foundation/text/KeyboardActions;", "keyboardActions", "onRequestKeyboard", "", "isPassword", "isFocused", "Landroidx/compose/ui/Modifier;", "modifier", "PremiumTextField-qQqZOpQ", "(Ljava/lang/String;Lr7/l;Ljava/lang/String;IILandroidx/compose/foundation/text/KeyboardActions;Lr7/a;ZZLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "PremiumTextField", "onClick", "text", "isPrimary", "enabled", "GradientButton", "(Lr7/a;Ljava/lang/String;ZZZLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/animation/core/CubicBezierEasing;", "EaseInOutCubic", "Landroidx/compose/animation/core/CubicBezierEasing;", "Lcom/arflix/tv/ui/screens/login/LoginUiState;", "uiState", "email", "password", "isSignUpMode", "focusedField", "", "logoAlpha", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class LoginScreenKt {
    private static final CubicBezierEasing EaseInOutCubic = new CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f);

    /* JADX WARN: Removed duplicated region for block: B:57:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0259  */
    /* JADX WARN: Removed duplicated region for block: B:95:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [boolean, int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void GradientButton(r7.a<x6.t0> r32, final java.lang.String r33, final boolean r34, final boolean r35, boolean r36, androidx.compose.ui.Modifier r37, androidx.compose.runtime.Composer r38, int r39, int r40) {
        /*
            Method dump skipped, instruction units count: 622
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.login.LoginScreenKt.GradientButton(r7.a, java.lang.String, boolean, boolean, boolean, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 GradientButton$lambda$0$0(boolean z, long j10, boolean z5, String str, RowScope rowScope, Composer composer, int i10) {
        long textPrimary;
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1148205348, i10, -1, "com.arflix.tv.ui.screens.login.GradientButton.<anonymous>.<anonymous> (LoginScreen.kt:533)");
            }
            long sp = TextUnitKt.getSp(14);
            FontWeight semiBold = FontWeight.INSTANCE.getSemiBold();
            if (z) {
                textPrimary = j10;
            } else {
                textPrimary = z5 ? ColorKt.getTextPrimary() : ColorKt.getTextSecondary();
            }
            TextKt.m6020Text4IGK_g(str, null, textPrimary, sp, null, semiBold, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 199680, 0, 131026);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 GradientButton$lambda$1(r7.a aVar, String str, boolean z, boolean z5, boolean z10, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        GradientButton(aVar, str, z, z5, z10, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x032f  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x033b  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x033f  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x040f  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x041b  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x041f  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x043e  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0480  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x048c  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0490  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x04af  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x06fb  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0707  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x070b  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0733  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0740  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0753  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x07b5  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0892  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x08aa  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x08b6  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x08da  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x08e6  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0913  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x093e  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x094a  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x099a  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x09dd  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x09f6  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0a01  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0a15  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x0a61  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x0a7c  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0a8d  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0abf  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0acb  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0ae0  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0bc1  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0be2  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0bec  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0bf5  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0c06  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x0c3a  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x0c5b  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x0c86  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x0cb9  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x0cc6  */
    /* JADX WARN: Removed duplicated region for block: B:275:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0259  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0267  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void LoginScreen(com.arflix.tv.ui.screens.login.LoginViewModel r99, r7.a<x6.t0> r100, androidx.compose.runtime.Composer r101, int r102, int r103) {
        /*
            Method dump skipped, instruction units count: 3284
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.login.LoginScreenKt.LoginScreen(com.arflix.tv.ui.screens.login.LoginViewModel, r7.a, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LoginUiState LoginScreen$lambda$1(State<LoginUiState> state) {
        return state.getValue();
    }

    private static final void LoginScreen$lambda$10(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String LoginScreen$lambda$12(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final float LoginScreen$lambda$19(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 LoginScreen$lambda$23$0$1$1$0(MutableState mutableState, String str) {
        mutableState.setValue(str);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 LoginScreen$lambda$23$0$1$10$0(MutableState mutableState, FocusState focusState) {
        if (focusState.isFocused()) {
            mutableState.setValue("button");
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 LoginScreen$lambda$23$0$1$11$0(Context context) {
        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://arvio.tv/privacy")));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 LoginScreen$lambda$23$0$1$12$0(MutableState mutableState, FocusState focusState) {
        if (focusState.isFocused()) {
            mutableState.setValue("privacy");
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 LoginScreen$lambda$23$0$1$13$0(MutableState mutableState) {
        LoginScreen$lambda$10(mutableState, !LoginScreen$lambda$9(mutableState));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 LoginScreen$lambda$23$0$1$14$0(MutableState mutableState, FocusState focusState) {
        if (focusState.isFocused()) {
            mutableState.setValue("toggle");
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 LoginScreen$lambda$23$0$1$2$0(FocusRequester focusRequester, KeyboardActionScope keyboardActionScope) {
        focusRequester.requestFocus();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 LoginScreen$lambda$23$0$1$3$0(SoftwareKeyboardController softwareKeyboardController) {
        if (softwareKeyboardController != null) {
            softwareKeyboardController.show();
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 LoginScreen$lambda$23$0$1$4$0(MutableState mutableState, FocusState focusState) {
        if (focusState.isFocused()) {
            mutableState.setValue("email");
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 LoginScreen$lambda$23$0$1$5$0(SoftwareKeyboardController softwareKeyboardController, FocusRequester focusRequester, KeyboardActionScope keyboardActionScope) {
        if (softwareKeyboardController != null) {
            softwareKeyboardController.hide();
        }
        focusRequester.requestFocus();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 LoginScreen$lambda$23$0$1$6$0(MutableState mutableState, FocusState focusState) {
        if (focusState.isFocused()) {
            mutableState.setValue("password");
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 LoginScreen$lambda$23$0$1$7$0(MutableState mutableState, String str) {
        mutableState.setValue(str);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 LoginScreen$lambda$23$0$1$8$0(SoftwareKeyboardController softwareKeyboardController) {
        if (softwareKeyboardController != null) {
            softwareKeyboardController.show();
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 LoginScreen$lambda$23$0$1$9$0(LoginViewModel loginViewModel, MutableState mutableState, MutableState mutableState2, MutableState mutableState3) {
        if (LoginScreen$lambda$9(mutableState)) {
            loginViewModel.signUp(LoginScreen$lambda$3(mutableState2), LoginScreen$lambda$6(mutableState3));
        } else {
            loginViewModel.signIn(LoginScreen$lambda$3(mutableState2), LoginScreen$lambda$6(mutableState3));
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 LoginScreen$lambda$24(LoginViewModel loginViewModel, r7.a aVar, int i10, int i11, Composer composer, int i12) {
        LoginScreen(loginViewModel, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }

    private static final String LoginScreen$lambda$3(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String LoginScreen$lambda$6(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LoginScreen$lambda$9(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0351  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0358  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x03a6  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x03bc  */
    /* JADX WARN: Removed duplicated region for block: B:171:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0105  */
    /* JADX INFO: renamed from: PremiumTextField-qQqZOpQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m6257PremiumTextFieldqQqZOpQ(final java.lang.String r64, final r7.l<? super java.lang.String, x6.t0> r65, final java.lang.String r66, int r67, int r68, androidx.compose.foundation.text.KeyboardActions r69, r7.a<x6.t0> r70, boolean r71, boolean r72, androidx.compose.ui.Modifier r73, androidx.compose.runtime.Composer r74, final int r75, final int r76) {
        /*
            Method dump skipped, instruction units count: 975
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.login.LoginScreenKt.m6257PremiumTextFieldqQqZOpQ(java.lang.String, r7.l, java.lang.String, int, int, androidx.compose.foundation.text.KeyboardActions, r7.a, boolean, boolean, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PremiumTextField_qQqZOpQ$lambda$1$1(String str, String str2, p pVar, Composer composer, int i10) {
        int i11;
        int i12;
        Composer composer2 = composer;
        if ((i10 & 6) == 0) {
            i11 = i10 | (composer2.changedInstance(pVar) ? 4 : 2);
        } else {
            i11 = i10;
        }
        if ((i11 & 19) == 18 && composer2.getSkipping()) {
            composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-488908235, i11, -1, "com.arflix.tv.ui.screens.login.PremiumTextField.<anonymous>.<anonymous> (LoginScreen.kt:460)");
            }
            composer2.startReplaceableGroup(733328855);
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, false, composer2, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
            CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(companion);
            if (composer2.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer2.startReusableNode();
            if (composer2.getInserting()) {
                composer2.createNode(constructor);
            } else {
                composer2.useNode();
            }
            Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composer2);
            p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer2)), composer2, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            if (str.length() == 0) {
                composer2.startReplaceGroup(823511205);
                i12 = i11;
                TextKt.m6020Text4IGK_g(str2, null, ColorKt.getTextTertiary(), TextUnitKt.getSp(15), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 3456, 0, 131058);
                composer2 = composer2;
                composer2.endReplaceGroup();
            } else {
                i12 = i11;
                composer2.startReplaceGroup(823700243);
                composer2.endReplaceGroup();
            }
            pVar.invoke(composer2, Integer.valueOf(i12 & 14));
            composer2.endReplaceableGroup();
            composer2.endNode();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PremiumTextField_qQqZOpQ$lambda$2(String str, l lVar, String str2, int i10, int i11, KeyboardActions keyboardActions, r7.a aVar, boolean z, boolean z5, Modifier modifier, int i12, int i13, Composer composer, int i14) {
        m6257PremiumTextFieldqQqZOpQ(str, lVar, str2, i10, i11, keyboardActions, aVar, z, z5, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i12 | 1), i13);
        return t0.f22605a;
    }
}
