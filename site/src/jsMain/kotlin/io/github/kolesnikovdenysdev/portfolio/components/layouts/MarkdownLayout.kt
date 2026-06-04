package io.github.kolesnikovdenysdev.portfolio.components.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.OverflowWrap
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.data.getValue
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.theme.colors.palette.color
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import com.varabyte.kobwebx.markdown.markdown
import kotlinx.browser.document
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import io.github.kolesnikovdenysdev.portfolio.toSitePalette

val MarkdownStyle = CssStyle {
    base { Modifier.fillMaxSize() }

    cssRule("h1") {
        Modifier
            .fontSize(3.cssRem)
            .fontWeight(400)
            .margin(bottom = 2.5.cssRem)
            .lineHeight(1.2) //1.5x doesn't look as good on very large text
    }

    cssRule("h2") {
        Modifier
            .fontSize(3.cssRem)
            .fontWeight(300)
            .margin(topBottom = 2.cssRem)
    }

    cssRule("h3") {
        Modifier
            .fontSize(2.4.cssRem)
            .fontWeight(300)
            .margin(topBottom = 1.5.cssRem)
    }

    cssRule("h4") {
        Modifier
            .fontSize(1.2.cssRem)
            .fontWeight(FontWeight.Bolder)
            .margin(top = 1.cssRem, bottom = 0.5.cssRem)
    }

    cssRule("ul") {
        Modifier.fillMaxWidth().overflowWrap(OverflowWrap.BreakWord)
    }

    cssRule(" :is(li,ol,ul)") {
        Modifier.margin(bottom = 0.25.cssRem)
    }

    cssRule("code") {
        Modifier
            .color(colorMode.toPalette().color.toRgb().copyf(alpha = 0.8f))
            .fontWeight(FontWeight.Bolder)
    }

    cssRule("pre") {
        Modifier
            .margin(top = 0.5.cssRem, bottom = 2.cssRem)
            .fillMaxWidth()
    }
    cssRule("pre > code") {
        Modifier
            .display(DisplayStyle.Block)
            .fillMaxWidth()
            .backgroundColor(colorMode.toSitePalette().nearBackground)
            .border(1.px, LineStyle.Solid, colorMode.toPalette().color)
            .borderRadius(0.25.cssRem)
            .padding(0.5.cssRem)
            .fontSize(1.cssRem)
            .overflow { x(Overflow.Auto) }
    }
}

@InitRoute
fun initMarkdownLayout(ctx: InitRouteContext) {
    val title = ctx.markdown!!.frontMatter["title"]?.singleOrNull()
    require(title != null) { "Markdown file must set \"title\" in frontmatter" }

    ctx.data.add(PageLayoutData(title))
}

@Composable
@Layout
fun MarkdownLayout(content: @Composable () -> Unit) {
    val ctx = rememberPageContext()
    val data = ctx.data.getValue<PageLayoutData>()
    LaunchedEffect(data.title) {
        document.title = "Denys Kolesnikov - ${data.title}"
    }

    Column(
        Modifier.fillMaxSize().padding(topBottom = 2.cssRem),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(leftRight = 2.cssRem)
                .maxWidth(60.cssRem)
                .margin(bottom = 1.cssRem),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Link("/", variant = UndecoratedLinkVariant.then(UncoloredLinkVariant)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    SpanText("←", Modifier.margin(right = 0.5.cssRem).fontSize(1.5.cssRem))
                    SpanText("Back to Home")
                }
            }
        }

        Div(PageContentStyle.toAttrs()) {
            Div(MarkdownStyle.toAttrs()) {
                content()
            }
        }
    }
}
