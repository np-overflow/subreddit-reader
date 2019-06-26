package club.npoverflow.subredditreader.feed.data.parser

import club.npoverflow.subredditreader.feed.data.Post
import club.npoverflow.subredditreader.feed.data.parser.orgjson.orgjsonParse

fun parse(Json: String): List<Post> = orgjsonParse(Json)
