package club.npoverflow.subredditreader.feed

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class FeedViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FeedViewModel(FeedRepository.getInstance(context)) as T
    }
}