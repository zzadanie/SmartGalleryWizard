package retrofit

data class FlickPhoto(
        private val media: String,
        private val title: String,
        private val tags: List<String>
)