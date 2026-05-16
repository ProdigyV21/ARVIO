import SwiftUI

struct SettingsView: View {
    var body: some View {
        VStack(alignment: .leading, spacing: 18) {
            Text("Settings")
                .font(.system(size: 38, weight: .bold))
                .foregroundStyle(ArvioTheme.textPrimary)

            SettingsRow(title: "Profile", value: "Default")
            SettingsRow(title: "Cloud sync", value: "Ready")
            SettingsRow(title: "Trakt", value: "Connect later")
            SettingsRow(title: "Playback", value: "Native iOS player")

            Spacer()
        }
        .padding(28)
    }
}

struct SettingsRow: View {
    let title: String
    let value: String

    var body: some View {
        HStack {
            Text(title)
                .font(.system(size: 17, weight: .semibold))
                .foregroundStyle(ArvioTheme.textPrimary)
            Spacer()
            Text(value)
                .font(.system(size: 15, weight: .medium))
                .foregroundStyle(ArvioTheme.textSecondary)
        }
        .padding(18)
        .background(RoundedRectangle(cornerRadius: 8).fill(ArvioTheme.panel))
        .overlay(RoundedRectangle(cornerRadius: 8).stroke(ArvioTheme.border, lineWidth: 1))
    }
}
