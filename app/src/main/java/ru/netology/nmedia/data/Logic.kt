package ru.netology.nmedia.data

fun countToString(count: Int): String {
    return when (count) {
        0 -> ""
        in 1..999 -> count.toString()
        in 1000..999_999 -> "%.1f".format((count.toDouble() / 1000)) + "K"
        else -> "%.1f".format((count.toDouble() / 1_000_000)) + "M"
    }
}