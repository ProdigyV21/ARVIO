package com.arflix.tv.data.telegram

import com.arflix.tv.util.DeviceType
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class TelegramClientDeviceModelTest {

    @Test
    fun tvWithBlankModelReturnsArvioTv() {
        val model = resolveTelegramDeviceModel(DeviceType.TV, rawModel = "")
        assertThat(model).isEqualTo("ARVIO TV")
    }

    @Test
    fun tvWithGenericModelReturnsArvioTv() {
        assertThat(resolveTelegramDeviceModel(DeviceType.TV, rawModel = "Android TV")).isEqualTo("ARVIO TV")
        assertThat(resolveTelegramDeviceModel(DeviceType.TV, rawModel = "Google TV")).isEqualTo("ARVIO TV")
        assertThat(resolveTelegramDeviceModel(DeviceType.TV, rawModel = "generic")).isEqualTo("ARVIO TV")
        assertThat(resolveTelegramDeviceModel(DeviceType.TV, rawModel = "unknown")).isEqualTo("ARVIO TV")
        assertThat(resolveTelegramDeviceModel(DeviceType.TV, rawModel = "sdk_gphone64_arm64")).isEqualTo("ARVIO TV")
        assertThat(resolveTelegramDeviceModel(DeviceType.TV, rawModel = "Android SDK built for x86")).isEqualTo("ARVIO TV")
    }

    @Test
    fun tvWithSpecificHardwareModelAppendsModelAndTvTag() {
        val model = resolveTelegramDeviceModel(DeviceType.TV, rawModel = "Chromecast")
        assertThat(model).isEqualTo("ARVIO TV (Chromecast)")
    }

    @Test
    fun tvWithModelAlreadyContainingTvDoesNotDuplicateTv() {
        val model = resolveTelegramDeviceModel(DeviceType.TV, rawModel = "SHIELD Android TV")
        assertThat(model).isEqualTo("ARVIO (SHIELD Android TV)")
    }

    @Test
    fun tvWithFireStickHardwareModel() {
        val model = resolveTelegramDeviceModel(DeviceType.TV, rawModel = "AFTMM")
        assertThat(model).isEqualTo("ARVIO TV (AFTMM)")
    }

    @Test
    fun tvWithCustomDeviceNameTakesPrecedence() {
        val model = resolveTelegramDeviceModel(
            deviceType = DeviceType.TV,
            rawModel = "Chromecast",
            customDeviceName = "Living Room TV"
        )
        assertThat(model).isEqualTo("ARVIO (Living Room TV)")
    }

    @Test
    fun tvWithGenericCustomDeviceNameFallsBackToModel() {
        val model = resolveTelegramDeviceModel(
            deviceType = DeviceType.TV,
            rawModel = "Chromecast",
            customDeviceName = "Android TV"
        )
        assertThat(model).isEqualTo("ARVIO TV (Chromecast)")
    }

    @Test
    fun tabletReturnsArvioTablet() {
        assertThat(resolveTelegramDeviceModel(DeviceType.TABLET, rawModel = ""))
            .isEqualTo("ARVIO Tablet")
        assertThat(resolveTelegramDeviceModel(DeviceType.TABLET, rawModel = "SM-X800"))
            .isEqualTo("ARVIO Tablet (SM-X800)")
    }

    @Test
    fun phoneReturnsArvioWithModel() {
        assertThat(resolveTelegramDeviceModel(DeviceType.PHONE, rawModel = ""))
            .isEqualTo("ARVIO")
        assertThat(resolveTelegramDeviceModel(DeviceType.PHONE, rawModel = "Pixel 8"))
            .isEqualTo("ARVIO (Pixel 8)")
    }

    @Test
    fun modelAlreadyContainingArvioIsPreserved() {
        assertThat(resolveTelegramDeviceModel(DeviceType.TV, rawModel = "ARVIO TV Stick"))
            .isEqualTo("ARVIO TV Stick")
    }
}
