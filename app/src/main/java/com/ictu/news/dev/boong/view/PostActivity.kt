package com.ictu.news.dev.boong.view

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.ictu.news.R
import com.ictu.news.dev.boong.collection.PostContentCollection
import com.ictu.news.dev.boong.model.RequestPostContent
import com.ictu.news.dev.boong.view.inteface.OnRequestPostContentResult
import kotlinx.android.synthetic.main.activity_post.*

class PostActivity : AppCompatActivity() {

    private lateinit var requestPostContent: RequestPostContent

    private val onRequestPostContent by lazy {
        object : OnRequestPostContentResult {
            override fun onDone(postContentResult: PostContentCollection) {
                progress_bar.visibility = GONE
                scroll_view.visibility = VISIBLE
                setTitle(postContentResult.post_content.post_name)
                for (text in postContentResult.post_content.content)
                    setContent(text)
            }

            override fun onFail(t: String) {
                Log.d("t", t)
            }
        }
    }

    private fun setTitle(title: String) {
        title_post_content.text = title
    }

    private fun setContent(content: String) {
        when {
            content.contains("<IMG>") && content.contains("</IMG>") -> {
                val link = content.substring("<IMG>".length, content.lastIndexOf("</IMG>"))
                setImage(link)
            }
            content.contains("<H") && content.contains("</H") -> {
                val text = content.substring("<H".length + 2, content.lastIndexOf("</H"))
                setTextHeader(text)
            }
            content.contains("<IFRAME>") && content.contains("</IFRAME>") -> {
                val iframe = content.substring("<IFRAME>".length, content.lastIndexOf("</IFRAME>"))
                setYoutubeView(iframe)
            }
            content.contains("<STRONG>") && content.contains("</STRONG>") -> {
                val text = content.substring("<STRONG>".length , content.lastIndexOf("</STRONG>"))
                setTextHeader(text)
            }
            else -> {
                setText(content)
            }
        }

    }

    private fun setYoutubeView(iframe: String) {
        val webView = WebView(this)
        post_content.addView(webView)

        webView.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        webView.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        webView.measure(0, 0)

        webView.settings.javaScriptEnabled = true
                webView.loadData("<style>.video-container {\n" +
                        "\tposition:relative;\n" +
                        "\tpadding-bottom:56.25%;\n" +
                        "\tpadding-top:30px;\n" +
                        "\theight:0;\n" +
                        "\toverflow:hidden;\n" +
                        "}\n" +
                        "\n" +
                        ".video-container iframe, .video-container object, .video-container embed {\n" +
                        "\tposition:absolute;\n" +
                        "\ttop:0;\n" +
                        "\tleft:0;\n" +
                        "\twidth:100%;\n" +
                        "\theight:100%;\n" +
                        "}</style>" +
                        "<html><body>" +
                        "<div class=\"video-container\"><iframe width=\"auto\" height=\"auto\" src=\"$iframe\" frameborder=\"0\" allowfullscreen></iframe></div>" +
                        "</body></html>",
                    "text/html", "utf-8")
    }

    private fun setTextHeader(content: String) {
        val textView = TextView(this)
        textView.text = content + "\n"
        textView.textSize = 19f
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.setTextColor(resources.getColor(R.color.colorBlack))

        post_content.addView(textView)
    }

    private fun setText(content: String) {
        val textView = TextView(this)
        textView.text = content + "\n"
        textView.textSize = 17f
        textView.setTextColor(resources.getColor(R.color.colorGray))
        post_content.addView(textView)
    }

    private fun setImage(url: String) {
        val imageView = ImageView(this)
        val layout = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layout.setMargins(0, 0, 0, 100)
        post_content.addView(imageView)

        imageView.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        imageView.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        imageView.layoutParams = layout

        Glide
            .with(this)
            .load(url)
            .into(imageView)
    }


    private fun run() {
        val link = intent.getStringExtra("link")
        val source = intent.getStringExtra("source")

        requestPostContent = RequestPostContent(onRequestPostContent)

        if (!link.isNullOrBlank() && !source.isNullOrBlank())
            requestPostContent.request(source, link)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        title = "Quay lại"

        run()
    }

    override fun onBackPressed() {
        requestPostContent.call.cancel()
        finish()
    }
}
