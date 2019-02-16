import org.w3c.xhr.XMLHttpRequest

typealias Response = String

class BookStorePresenter : BookStoreContract.Presenter {
    private lateinit var view: BookStoreContract.View

    override fun attach(view: BookStoreContract.View) {
        this.view = view
    }

    override fun loadBooks() {

        view.showLoader()

        getAsync(API_URL) {
            val books = JSON.parse<Array<Book>>(it)

            // Just for debugging
            //books.forEach { book -> println(book.title) }

            view.hideLoader()
            view.showBooks(books.toList())
        }

    }

    // what is this callback doing? Can you substitute coroutines here? Does that make sense?
    private fun getAsync(url: String, callback: (Response) -> Unit) {
        val xmlHttp = XMLHttpRequest()
        xmlHttp.open("GET", url)

        xmlHttp.onload = {
            if (
                xmlHttp.readyState == 4.toShort()
                && xmlHttp.status == 200.toShort()) {

                callback.invoke(xmlHttp.responseText)
            }
        }

        xmlHttp.send()
    }
}