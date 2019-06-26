package club.npoverflow.subredditreader.feed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import club.npoverflow.subredditreader.feed.data.Post
import kotlinx.coroutines.*

class FeedViewModel(
    private val feedRepository: FeedRepository
) : ViewModel() {
    // Kotlin coroutine scope
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val feedLiveData: MutableLiveData<List<Post>> = MutableLiveData()

    fun setFeedSource(subreddit: String) {
        // TODO: check if this works
        viewModelJob.cancelChildren()

        // TODO: Check if this works
        uiScope.async {
            feedLiveData.value = feedRepository.getFeed(subreddit)
        }
    }
}