package ru.netology.nmedia

fun counterString(count: Int): String {
    return when (count) {
        0 -> ""
        in 1..999 -> count.toString()
        in 1000..999_999 -> (count % 1000).toString() + "K"
        else -> (count % 1_000_000).toString() + "M"
    }
}

