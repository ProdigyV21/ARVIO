import SwiftUI

enum ArvioTheme {
    static let background = Color(red: 0.031, green: 0.035, blue: 0.039)
    static let panel = Color.white.opacity(0.075)
    static let border = Color.white.opacity(0.12)
    static let textPrimary = Color(red: 0.93, green: 0.93, blue: 0.93)
    static let textSecondary = Color.white.opacity(0.68)
    static let textTertiary = Color.white.opacity(0.44)
    static let gold = Color(red: 1.0, green: 0.72, blue: 0.24)
}

extension Color {
    init(hex: String) {
        let raw = hex.trimmingCharacters(in: CharacterSet.alphanumerics.inverted)
        var value: UInt64 = 0
        Scanner(string: raw).scanHexInt64(&value)
        let red = Double((value >> 16) & 0xff) / 255
        let green = Double((value >> 8) & 0xff) / 255
        let blue = Double(value & 0xff) / 255
        self.init(red: red, green: green, blue: blue)
    }
}
