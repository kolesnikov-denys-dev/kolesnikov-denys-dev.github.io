package io.github.kolesnikovdenysdev.portfolio.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.css.WhiteSpace
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
                        "Completed an intensive 6-month Java Enterprise course, building a full-stack e-commerce platform (2017)",
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
                "Frameworks & Arch" to listOf("Native Android", "KMP", "Swift/Xcode", "Flutter", "Clean Architecture", "MVI/MVVM")
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
            SpanText("💼 Commercial Projects", HeadlineTextStyle.toModifier().fontSize(2.cssRem))
            Column(Modifier.fillMaxWidth().margin(top = 2.cssRem, bottom = 3.cssRem).gap(1.5.cssRem)) {
                ProjectCard(
                    title = "AOSP Custom System Services",
                    description = "Implementation of custom system services and hardware drivers (Qualcomm) for industrial Android devices, including root-level applications and MDM.",
                    tags = listOf("AOSP", "Qualcomm", "C/C++", "Java")
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

            SpanText("🎉 Personal Projects", HeadlineTextStyle.toModifier().fontSize(2.cssRem))
            Column(Modifier.fillMaxWidth().margin(top = 2.cssRem).gap(1.5.cssRem)) {
                ProjectCard(
                    title = "Story Door (Android Native to KMP Migration: iOS, Android, Desktop)",
                    status = "Since May 2021 • In Active Development",
                    difficulty = "Expert / Architectural Lead",
                    description = "Comprehensive migration of a native Android writing app to Kotlin Multiplatform (KMP). Built with Clean Architecture and Feature-Based modularization. An offline-first tool for writers featuring Native Google Sign-In and cross-platform synchronization via Google Drive API. Implementation includes HTML parsing with Ksoup, advanced UI with Reorderable/Krop, and unified logic using Koin & Ktor.",
                    tags = listOf("Kotlin", "KMP", "Compose Multiplatform", "Google Drive API", "Google Sign-In", "Ktor", "SQLDelight", "Koin", "Coil", "FileKit", "KmpAuth", "Ksoup", "Krop", "Reorderable", "Clean Architecture", "Feature Based Architecture"),
                    links = listOf(
                        "Read Article 1" to "/article1",
                        "Read Article 2" to "/article2",
                        "Read Article 3" to "/article3"
                    )
                )
                ProjectCard(
                    title = "Movie Note (Android Native)",
                    status = "Nov 2020 • Completed",
                    difficulty = "Medium",
                    description = "An Android Native movie notes application built with Java and MVVM architecture. It integrates with TMDb API for movie data and uses Firebase for authentication and cloud storage. Features include reactive programming with RxJava, dependency injection with Dagger 2, and smooth image loading with Glide.",
                    tags = listOf("Java", "MVVM", "Firebase", "Retrofit", "Dagger 2", "RxJava", "Glide", "Paging", "Material Design"),
                    links = listOf(
                        "View on GitHub" to "https://github.com/kolesnikov-denys-dev/Movie-Note"
                    )
                )
                ProjectCard(
                    title = "Unsplash Wallpapers (Android Native)",
                    status = "2019 • Completed",
                    difficulty = "Medium",
                    description = "An Android Native application developed to demonstrate technical skills. It allows users to search, view, and set high-quality images from the Unsplash API as wallpapers. Features include advanced image searching, zooming, and local downloading.",
                    tags = listOf("Java", "MVP", "Retrofit", "RxJava", "Room", "Picasso", "PhotoView", "Material Design"),
                    links = listOf(
                        "View on GitHub" to "https://github.com/kolesnikov-denys-dev/Unsplash-Wallpapers"
                    )
                )
                ProjectCard(
                    title = "Web Store (Full-stack: Java Backend & Web Frontend)",
                    status = "Nov 2017 • Completed",
                    difficulty = "Core Fundamentals",
                    description = "A full-stack e-commerce platform (Amazon clone) for buying and selling products. Developed as a graduation project for a Java Enterprise course, covering both frontend (HTML/CSS) and backend logic with Java Servlets and MySQL.",
                    tags = listOf("Java", "Java Servlets", "MySQL", "HTML/CSS"),
                    links = listOf(
                        "View on GitHub" to "https://github.com/kolesnikov-denys-dev/Web-Shop-Servlets"
                    )
                )
            }
        }
    }
}

@Composable
fun ProjectCard(
    title: String,
    description: String,
    tags: List<String>,
    status: String? = null,
    difficulty: String? = null,
    links: List<Pair<String, String>> = emptyList()
) {
    Column(ProjectCardStyle.toModifier().fillMaxWidth()) {
        Row(Modifier.fillMaxWidth().flexWrap(FlexWrap.Wrap).columnGap(1.cssRem).rowGap(0.5.cssRem), verticalAlignment = Alignment.CenterVertically) {
            SpanText(title, Modifier.fontWeight(FontWeight.Bold).fontSize(1.2.cssRem))
            if (status != null) {
                val statusColor = when {
                    status.contains("Completed") -> Colors.Crimson
                    status.contains("Active Development") -> Colors.MediumSeaGreen
                    else -> Colors.DodgerBlue
                }
                Box(
                    Modifier
                        .padding(leftRight = 0.5.cssRem, topBottom = 0.2.cssRem)
                        .backgroundColor(statusColor.copyf(alpha = 0.1f))
                        .borderRadius(4.px)
                        .whiteSpace(WhiteSpace.NoWrap)
                ) {
                    SpanText(status, Modifier.fontSize(0.7.cssRem).color(statusColor).fontWeight(FontWeight.Bold))
                }
            }
        }

        if (difficulty != null) {
            Row(Modifier.margin(top = 0.5.cssRem), verticalAlignment = Alignment.CenterVertically) {
                SpanText("Complexity: ", Modifier.fontSize(0.8.cssRem).opacity(0.6))
                val isLight = ColorMode.current.isLight
                val difficultyColor = if (isLight) com.varabyte.kobweb.compose.ui.graphics.Color.rgb(160, 110, 0) else Colors.Gold
                SpanText(difficulty, Modifier.fontSize(0.8.cssRem).fontWeight(FontWeight.Bold).color(difficultyColor))
            }
        }

        SpanText(description, Modifier.margin(top = 1.cssRem).opacity(0.8).lineHeight(1.5))
        
        if (links.isNotEmpty()) {
            Row(Modifier.margin(top = 1.cssRem).gap(1.cssRem).flexWrap(FlexWrap.Wrap)) {
                links.forEach { (text, url) ->
                    Link(url, Modifier.textDecorationLine(TextDecorationLine.None)) {
                        Button(
                            onClick = {},
                            variant = ProjectLinkVariant,
                            modifier = Modifier.fontSize(0.8.cssRem)
                        ) {
                            Text(text)
                        }
                    }
                }
            }
        }

        Row(Modifier.margin(top = 1.cssRem).flexWrap(FlexWrap.Wrap)) {
            val isLight = ColorMode.current.isLight
            tags.forEach { tag ->
                val (bgColor, textColor) = getTagColors(tag, isLight)
                Box(
                    Modifier
                        .padding(leftRight = 0.6.cssRem, topBottom = 0.2.cssRem)
                        .margin(right = 0.5.cssRem, bottom = 0.5.cssRem)
                        .backgroundColor(bgColor)
                        .borderRadius(4.px)
                        .border(1.px, LineStyle.Solid, textColor.copyf(alpha = if (isLight) 0.4f else 0.3f))
                ) {
                    SpanText(tag, Modifier.fontSize(0.75.cssRem).color(textColor).fontWeight(FontWeight.Medium))
                }
            }
        }
    }
}

fun getTagColors(tag: String, isLight: Boolean): Pair<com.varabyte.kobweb.compose.ui.graphics.Color.Rgb, com.varabyte.kobweb.compose.ui.graphics.Color.Rgb> {
    val tagLower = tag.lowercase()
    val baseColor = when {
        tagLower.contains("kmp") || tagLower.contains("multiplatform") || tagLower.contains("kotlin") -> Colors.Indigo
        tagLower.contains("compose") -> Colors.Teal
        tagLower.contains("java") && !tagLower.contains("rx") -> if (isLight) Colors.Brown else Colors.OrangeRed
        tagLower.contains("firebase") -> if (isLight) com.varabyte.kobweb.compose.ui.graphics.Color.rgb(191, 118, 0) else Colors.Orange
        tagLower.contains("sqldelight") || tagLower.contains("sql") -> Colors.DodgerBlue
        tagLower.contains("ktor") || tagLower.contains("ksoup") -> Colors.SkyBlue
        tagLower.contains("koin") -> Colors.MediumOrchid
        tagLower.contains("coil") || tagLower.contains("filekit") -> Colors.LightCoral
        tagLower.contains("krop") || tagLower.contains("reorderable") || tagLower.contains("kmpauth") -> Colors.DarkOrange
        tagLower.contains("rxjava") -> Colors.Purple
        tagLower.contains("dagger") -> Colors.RoyalBlue
        tagLower.contains("retrofit") -> if (isLight) Colors.DarkGreen else Colors.ForestGreen
        tagLower.contains("mvvm") || tagLower.contains("mvp") -> if (isLight) Colors.DarkSlateGray else Colors.SlateGray
        tagLower.contains("clean") -> if (isLight) Colors.DarkCyan else Colors.DarkCyan
        tagLower.contains("aosp") || tagLower.contains("qualcomm") -> if (isLight) Colors.FireBrick else Colors.DarkRed
        tagLower.contains("arduino") || tagLower.contains("esp32") -> if (isLight) Colors.DarkGreen else Colors.DarkGreen
        else -> Colors.Gray
    }
    
    val bgColor = baseColor.toRgb().copyf(alpha = if (isLight) 0.08f else 0.12f)
    val textColor = baseColor.toRgb()
    
    return bgColor to textColor
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
