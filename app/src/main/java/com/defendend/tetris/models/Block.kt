package com.defendend.tetris.models

import android.graphics.Color
import android.graphics.Point
import com.defendend.tetris.constants.FieldConstants
import java.util.*


class Block private constructor(private val shapeIndex: Int, private val color: BlockColor) {
    var frameNumber = 0
        private set
    var position: Point
        private set

    fun setState(frame: Int, position: Point) {
        frameNumber = frame
        this.position = position
    }

    fun getShape(frameNumber: Int): Array<ByteArray> {
        return Shape.values()[shapeIndex].getFrame(
            frameNumber
        ).as2dByteArray()
    }

    val frameCount: Int
        get() = Shape.values()[shapeIndex].frameCount

    fun getColor(): Int {
        return color.rgbValue
    }

    fun getStaticValue() : Byte {
        return color.staticValue
    }

    enum class BlockColor(val rgbValue: Int, val staticValue: Byte) {
        PINK(Color.rgb(255, 105, 180), 2.toByte()), GREEN(
            Color.rgb(0, 128, 0),
            3.toByte()
        ),
        ORANGE(Color.rgb(255, 140, 0), 4.toByte()), YELLOW(
            Color.rgb(255, 255, 0),
            5.toByte()
        ),
        CYAN(Color.rgb(0, 255, 255), 6.toByte());

    }

    companion object {
        fun createBlock(): Block {
            val random = Random()
            val shapeIndex = random.nextInt(Shape.values().size)
            val blockColor = BlockColor.values()[random.nextInt(BlockColor.values().size)]
            val block = Block(shapeIndex, blockColor)
            block.position.x = block.position.x - Shape.values()[shapeIndex].startPosition
            return block
        }

        fun getColor(value: Byte): Int {
            for (colour in BlockColor.values()) {
                if (value == colour.staticValue) {
                    return colour.rgbValue
                }
            }
            return -1
        }
    }

    init {
        position = Point(FieldConstants.COLUMN_COUNT.value / 2, 0)
    }
}