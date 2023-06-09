package com.firebirdcss.bingopicker.data.enums

/**
 * ENUM: This enum defines the rows that exist on a
 * typical Bingo Card, which are B-I-N-G-O. It also
 * defines the list of values that are possible
 * for that row number.
 */
enum class BingoRow(val value: List<Int>) {
    B((1..15).toList()),
    I((16..30).toList()),
    N((31..45).toList()),
    G((46..60).toList()),
    O((61..75).toList())
}