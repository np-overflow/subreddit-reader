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

    fun setFeedSource(subreddit: String, forceRefresh: Boolean = false) {
        viewModelJob.cancelChildren()

        uiScope.async(Dispatchers.IO) {
            val newFeed = feedRepository.getFeed(subreddit, forceRefresh)
            feedLiveData.postValue(newFeed)
        }
    }
}