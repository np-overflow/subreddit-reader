package club.npoverflow.subredditreader.feed

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import club.npoverflow.subredditreader.R
import club.npoverflow.subredditreader.feed.data.Post
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {
    private lateinit var feedViewModel: FeedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = FeedViewModelFactory(context!!)
        feedViewModel = ViewModelProviders.of(this, factory).get(FeedViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Prepare for feed RecyclerView
        val viewManager = LinearLayoutManager(context)
        // Start with no elements, update later when the data is ready
        val viewAdapter = FeedAdapter(listOf()) {
            // Handle post click
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
            startActivity(intent)
        }
        // Create divider
        val dividerItemDecoration = DividerItemDecoration(
            recycler_view_feed.context, viewManager.orientation)

        // Set an observer on the Feed LiveData for when the data is ready
        feedViewModel.feedLiveData.observe(this, Observer<List<Post>> {
            viewAdapter.setPosts(it)
        })

        recycler_view_feed.apply {
            layoutManager = viewManager
            adapter = viewAdapter
            addItemDecoration(dividerItemDecoration)
        }

        recycler_view_feed.adapter


        // Bind the "display" button to change the data source
        button_fetch.setOnClickListener {
            feedViewModel.setFeedSource(edittext_subreddit.text.toString())
        }
        button_fetch.setOnLongClickListener {
            feedViewModel.setFeedSource(edittext_subreddit.text.toString(),true)
            true
        }
    }
}