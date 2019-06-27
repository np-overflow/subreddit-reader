package club.npoverflow.subredditreader.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import club.npoverflow.subredditreader.R
import club.npoverflow.subredditreader.feed.data.Post

class FeedAdapter(
    private var posts: List<Post>,
    private val onClickListener: (Post) -> Unit
) : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {
    class FeedViewHolder(val feedView: ViewGroup) : RecyclerView.ViewHolder(feedView) {
        val authorTextView: TextView = feedView.findViewById(R.id.textview_author)
        val subredditTextView: TextView = feedView.findViewById(R.id.textview_subreddit)
        val titleTextView: TextView = feedView.findViewById(R.id.textview_title)
        val imageImageView: ImageView = feedView.findViewById(R.id.imageview_image)
        val contentTextView: TextView = feedView.findViewById(R.id.textview_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val feedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_feed_item, parent, false) as ViewGroup

        return FeedViewHolder(feedView)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val post: Post = posts[position]
        val context = holder.authorTextView.context

        holder.authorTextView.text =  context.getString(R.string.feed_item_posted_by, post.author)
        holder.subredditTextView.text = context.getString(R.string.feed_item_subreddit, post.subreddit)
        holder.titleTextView.text = post.title
        holder.contentTextView.text = post.content

        holder.imageImageView.setImageDrawable(null)
        if (post.image != null) {
            // Image as content
            holder.imageImageView.setImageBitmap(post.image)
        }

        holder.feedView.setOnClickListener {
            onClickListener(post)
        }
    }

    override fun getItemCount() = posts.size

    fun setPosts(newPosts: List<Post>) {
        posts = newPosts
        notifyDataSetChanged()
    }
}