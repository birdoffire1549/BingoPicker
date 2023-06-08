package com.firebirdcss.bingopicker.data.enums

enum class BingoRow(val value: List<Int>) {
    B((1..15).toList()),
    I((16..30).toList()),
    N((31..45).toList()),
    G((46..60).toList()),
    O((61..75).toList())
}