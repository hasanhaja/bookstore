import org.w3c.dom.*
import kotlin.browser.document
import kotlin.browser.window
import kotlin.dom.addClass

class CardBuilder {
    fun build(book: Book): HTMLElement {

        val containerElement = create("div") as HTMLDivElement
        val imageElement = create("img") as HTMLImageElement
        val titleElement = create("div") as HTMLDivElement
        val priceElement = create("div") as HTMLDivElement
        val descriptionElement = create("div") as HTMLDivElement
        val viewDetailsButtonElement = create("button") as HTMLButtonElement

        bind(
            book = book,
            imgElem = imageElement,
            titleElem = titleElement,
            priceElem = priceElement,
            desElem = descriptionElement,
            viewBtnElem = viewDetailsButtonElement
        )

        applyStyle(
            containerElement,
            imgElem = imageElement,
            titleElem = titleElement,
            priceElem = priceElement,
            desElem = descriptionElement,
            viewBtnElem = viewDetailsButtonElement
        )

        containerElement.appendChild(
            imageElement,
            titleElement,
            descriptionElement,
            priceElement,
            viewDetailsButtonElement
        )

        return containerElement

    }

    private fun applyStyle(containerElement: HTMLDivElement,
                           imgElem: HTMLImageElement,
                           titleElem: HTMLDivElement,
                           priceElem: HTMLDivElement,
                           desElem: HTMLDivElement,
                           viewBtnElem: HTMLButtonElement) {

        containerElement.addClass("card", "card-shadow")
        imgElem.addClass("cover-image")
        titleElem.addClass("text-title", "float-left")
        desElem.addClass("text-description", "float-left")
        priceElem.addClass("text-price", "float-left")
        viewBtnElem.addClass("view-details", "ripple", "float-right")

    }

    private fun bind(book: Book,
                     imgElem: HTMLImageElement,
                     titleElem: HTMLDivElement,
                     priceElem: HTMLDivElement,
                     desElem: HTMLDivElement,
                     viewBtnElem: HTMLButtonElement) {

        imgElem.src = book.coverUrl

        titleElem.innerHTML = book.title
        priceElem.innerHTML = book.price
        desElem.innerHTML = book.description
        viewBtnElem.innerHTML = "view details"

        viewBtnElem.addEventListener("click", {
            window.open(book.url)
        })

    }


    private fun create(localName: String): Element = document.createElement(localName)

}

private fun Element.appendChild(vararg elements: Element) {
    elements.forEach { this.appendChild(it) }
}
