package io.github.kolesnikovdenysdev.portfolio.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import org.jetbrains.compose.web.css.FlexWrap
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.*
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.color
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import io.github.kolesnikovdenysdev.portfolio.*
import io.github.kolesnikovdenysdev.portfolio.components.layouts.PageLayoutData
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Text

@InitRoute
fun initHomePage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Home"))
}

@Page
@Layout(".components.layouts.PageLayout")
@Composable
fun HomePage() {
    Column(Modifier.fillMaxSize()) {
        HeroSection()
        AboutMeSection()
        SkillsSection()
        ExperienceSection()
        ProjectsSection()
        ContactSection()
    }
}

@Composable
fun HeroSection() {
    Box(SectionStyle.toModifier(), contentAlignment = Alignment.Center) {
        Column(ContainerStyle.toModifier(), horizontalAlignment = Alignment.CenterHorizontally) {
            SpanText(
                "Hi there, I’m Denys Kolesnikov! 👋",
                Modifier.fontSize(1.5.cssRem).fontWeight(FontWeight.Medium)
            )
            SpanText(
                "Senior Software Engineer | Android & KMP Specialist",
                HeadlineTextStyle.toModifier().margin(top = 1.cssRem).textAlign(TextAlign.Center).fontSize(2.5.cssRem)
            )
            
            val ctx = rememberPageContext()
            Button(
                onClick = { ctx.router.tryRoutingTo("#contact") },
                Modifier.margin(top = 2.cssRem).padding(leftRight = 2.cssRem).borderRadius(50.px)
            ) {
                Text("Let's Connect")
            }

            Row(Modifier.margin(top = 1.5.cssRem).gap(1.5.cssRem)) {
                SocialLink("GitHub", "https://github.com/kolesnikov-denys-dev")
                SocialLink("LinkedIn", "https://www.linkedin.com/in/denys-kolesnikov-618a21111/")
                SocialLink("Telegram", "https://t.me/kolesnikov_denys")
            }
        }
    }
}

@Composable
fun SocialLink(text: String, url: String) {
    Link(url, Modifier.fontWeight(FontWeight.Bold).color(Colors.DodgerBlue)) {
        Text(text)
    }
}

@Composable
fun AboutMeSection() {
    Box(SectionStyle.toModifier().id("about").backgroundColor(Colors.Transparent)) {
        Column(ContainerStyle.toModifier()) {
            SpanText("💡 About Me", HeadlineTextStyle.toModifier().fontSize(2.cssRem))
            SpanText(
                "I am a dedicated Android Developer with 7+ years of experience. I’m passionate about optimizing development processes, enhancing user experiences, and leveraging modern Android/KMM tools—even at the system level!",
                Modifier.margin(top = 1.cssRem).lineHeight(1.6).opacity(0.8)
            )
            
            SimpleGrid(numColumns(1, md = 2), Modifier.fillMaxWidth().margin(top = 2.cssRem).gap(1.cssRem)) {
                Column {
                    val achievements = listOf(
                        "Created and published 'Story Door' from idea to release on Play Market",
                        "Migrated 'Story Door' from a native Android project to a full Kotlin Multiplatform (KMP) & Compose Multiplatform (iOS, Android, Desktop) architecture",
                        "Collaborated with large, cross-functional teams",
                        "Presented findings at meetups on AOSP-based solutions",
                        "Provided mentorship and guidance to fellow developers"
                    )
                    achievements.forEach { achievement ->
                        Row(Modifier.margin(bottom = 0.5.cssRem)) {
                            SpanText("• ", Modifier.fontWeight(FontWeight.Bold).color(Colors.DodgerBlue))
                            SpanText(achievement, Modifier.opacity(0.8))
                        }
                    }
                }
                
                Column {
                    SpanText("Languages", Modifier.fontWeight(FontWeight.Bold).margin(bottom = 0.5.cssRem))
                    Text("English – Intermediate")
                    Text("Ukrainian – Native")
                    Text("Russian – Native")
                }
            }
        }
    }
}

@Composable
fun SkillsSection() {
    Box(SectionStyle.toModifier().id("skills")) {
        Column(ContainerStyle.toModifier()) {
            SpanText("🛠️ Tech Stack & Tools", HeadlineTextStyle.toModifier().fontSize(2.cssRem))

            val skillGroups = mapOf(
                "AOSP & OS Development" to listOf(
                    "Custom Launchers", "Notification Shades", "OTA Updates", "System Services", 
                    "Qualcomm Drivers", "MDM Services", "Root-level Apps"
                ),
                "Compose Multiplatform" to listOf(
                    "Compose 1.11", "Navigation Compose", "Resources", "Resources UI Tooling", 
                    "Material 3", "Foundation", "Coil 3", "FileKit", "Krop", "Reorderable"
                ),
                "Networking & Data" to listOf(
                    "Ktor 3.5", "SQLDelight 2.3", "Protobuf", "Ksoup (HTML Parsing)", 
                    "Kotlinx Serialization", "Kotlinx DateTime", "Kotlinx IO"
                ),
                "DI & Auth" to listOf(
                    "Koin 4.2", "KMPAuth", "Android Credentials API", "Google Play Auth", 
                    "Firebase Analytics/Crashlytics"
                ),
                "Hardware & IoT" to listOf(
                    "BLE", "Wi-Fi", "MQTT", "RTSP", "ESP32", "Arduino", "Texas Instruments Drivers"
                ),
                "Frameworks & Arch" to listOf("Native Android", "KMM", "Swift/Xcode", "Flutter", "Clean Architecture", "MVI/MVVM")
            )

            skillGroups.forEach { (group, skills) ->
                SpanText(group, Modifier.margin(top = 1.5.cssRem).fontWeight(FontWeight.Bold).fontSize(1.2.cssRem))
                SimpleGrid(numColumns(2, sm = 3, md = 4, lg = 5), Modifier.fillMaxWidth().margin(top = 1.cssRem)) {
                    skills.forEach { skill ->
                        SkillTag(skill)
                    }
                }
            }
        }
    }
}

@Composable
fun SkillTag(name: String) {
    val palette = ColorMode.current.toPalette()
    val isLight = ColorMode.current.isLight
    val borderColor = palette.color.toRgb().copyf(alpha = if (isLight) 0.2f else 0.25f)
    val bgColor = palette.color.toRgb().copyf(alpha = if (isLight) 0.07f else 0.12f)
    Box(
        Modifier
            .padding(topBottom = 0.4.cssRem, leftRight = 0.8.cssRem)
            .margin(0.3.cssRem)
            .backgroundColor(bgColor)
            .borderRadius(20.px)
            .border(1.px, LineStyle.Solid, borderColor)
    ) {
        SpanText(name, Modifier.fontSize(0.85.cssRem).fontWeight(FontWeight.Medium))
    }
}

@Composable
fun ExperienceSection() {
    Box(SectionStyle.toModifier().id("experience")) {
        Column(ContainerStyle.toModifier()) {
            SpanText("✨ Professional Summary", HeadlineTextStyle.toModifier().fontSize(2.cssRem))

            ExperienceItem(
                title = "Native to KMP Migration Specialist",
                description = "Led the architectural transition of production applications from pure Android to Kotlin Multiplatform (KMP). This involved extracting business logic into shared modules and implementing unified UI with Compose Multiplatform for iOS, Android, and Desktop."
            )
            ExperienceItem(
                title = "MDM & AOSP Specialist",
                description = "Developed MDM solutions for AOSP-based devices from scratch. Specializing in custom launchers, system-level optimizations, and Qualcomm driver integration."
            )
            ExperienceItem(
                title = "Hardware & Embedded Systems",
                description = "Designing control units for power electronics using Arduino/ESP32, implementing touchscreen UIs, and integrating BLE-based firmware updates."
            )
        }
    }
}

@Composable
fun ExperienceItem(title: String, description: String) {
    Column(Modifier.margin(top = 1.5.cssRem).fillMaxWidth()) {
        SpanText(title, Modifier.fontWeight(FontWeight.Bold).fontSize(1.3.cssRem))
        SpanText(description, Modifier.margin(top = 0.5.cssRem).opacity(0.8).lineHeight(1.6))
    }
}

@Composable
fun ProjectsSection() {
    Box(SectionStyle.toModifier().id("projects")) {
        Column(ContainerStyle.toModifier()) {
            SpanText("🎉 Personal Projects", HeadlineTextStyle.toModifier().fontSize(2.cssRem))
            SimpleGrid(numColumns(1, md = 2), Modifier.fillMaxWidth().margin(top = 2.cssRem).gap(1.cssRem)) {
                ProjectCard(
                    title = "Story Door (Native to KMP Migration)",
                    description = "Comprehensive migration of a native Android writing app to Kotlin Multiplatform (KMP). An offline-first tool for writers featuring cross-platform Google synchronization, high scalability for large manuscripts, and EPUB export. Shared business logic with KMM and unified UI using Compose Multiplatform (iOS, Android, Desktop).",
                    tags = listOf("KMP", "Compose Multiplatform", "Offline-First", "Google Sync", "EPUB Export", "SQLDelight 2.3")
                )
                ProjectCard(
                    title = "AOSP Custom System Services",
                    description = "Implementation of custom system services and hardware drivers (Qualcomm) for industrial Android devices, including root-level applications and MDM.",
                    tags = listOf("AOSP", "Qualcomm", "C/C++", "Java", "Kotlin")
                )
                ProjectCard(
                    title = "Touchscreen Mini-Computers",
                    description = "Arduino-based systems with touchscreen UIs for power electronics control. Embedded hardware tinkering.",
                    tags = listOf("Arduino", "C++", "Electronics")
                )
                ProjectCard(
                    title = "Smart Home Gadgets",
                    description = "Custom IoT solutions using ESP32, BLE, and Wi-Fi integrated with Android apps.",
                    tags = listOf("IoT", "ESP32", "BLE", "Wi-Fi")
                )
            }
        }
    }
}

@Composable
fun ProjectCard(title: String, description: String, tags: List<String>) {
    Column(ProjectCardStyle.toModifier().fillMaxHeight()) {
        SpanText(title, Modifier.fontWeight(FontWeight.Bold).fontSize(1.2.cssRem))
        SpanText(description, Modifier.margin(top = 1.cssRem).opacity(0.8).flexGrow(1).lineHeight(1.5))
        Row(Modifier.margin(top = 1.cssRem).flexWrap(FlexWrap.Wrap)) {
            tags.forEach { tag ->
                Box(
                    Modifier
                        .padding(topBottom = 0.2.cssRem, leftRight = 0.6.cssRem)
                        .margin(right = 0.5.cssRem, bottom = 0.5.cssRem)
                        .backgroundColor(Colors.LightGray.copyf(alpha = 0.1f))
                        .borderRadius(4.px)
                ) {
                    SpanText(tag, Modifier.fontSize(0.75.cssRem).opacity(0.7))
                }
            }
        }
    }
}

@Composable
fun ContactSection() {
    Box(SectionStyle.toModifier().id("contact")) {
        Column(ContainerStyle.toModifier(), horizontalAlignment = Alignment.CenterHorizontally) {
            SpanText("🤝 Let's Connect", HeadlineTextStyle.toModifier().fontSize(2.cssRem))
            SpanText(
                "Feel free to reach out for collaborations or just to say hi!",
                Modifier.margin(top = 1.cssRem).opacity(0.8).textAlign(TextAlign.Center)
            )
            
            Row(Modifier.margin(top = 2.cssRem).gap(2.cssRem).flexWrap(FlexWrap.Wrap), verticalAlignment = Alignment.CenterVertically) {
                ContactLink("Email", "mailto:kolesnikov.denys.dev@gmail.com")
                ContactLink("Telegram", "https://t.me/kolesnikov_denys")
                ContactLink("LinkedIn", "https://www.linkedin.com/in/denys-kolesnikov-618a21111/")
                ContactLink("GitHub", "https://github.com/kolesnikov-denys-dev")
            }
            
            SpanText(
                "Made with ❤️ for Android Development",
                Modifier.margin(top = 4.cssRem).fontSize(0.9.cssRem).opacity(0.5)
            )
        }
    }
}

@Composable
fun ContactLink(text: String, url: String) {
    Link(url, Modifier.fontWeight(FontWeight.Bold).fontSize(1.1.cssRem).color(Colors.DodgerBlue)) {
        Text(text)
    }
}
