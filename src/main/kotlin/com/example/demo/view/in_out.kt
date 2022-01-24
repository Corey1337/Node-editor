package com.example.demo.view

import javafx.event.EventHandler
import javafx.scene.layout.AnchorPane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import java.util.*

class in_(b: Double = 15.0, w: Double = 7.0) : Rectangle(){
    var var_type: String? = null
    var x: Double? = null
    var y: Double? = null
    var linked: Boolean = false
    var content: Any? = null

    val type = "in"

    init{
        id = UUID.randomUUID().toString()

        width = 15.0
        height = 15.0
        stroke = Color.AQUA
        AnchorPane.setBottomAnchor(this, b)
        AnchorPane.setLeftAnchor(this, w)

        onMouseClicked = EventHandler { mouseEvent ->
            //println(type)
            if (from != null) {
                x = mouseEvent.sceneX
                y = mouseEvent.sceneY - 30
                to = this
                println(from.toString() + " + " + to.toString())

                nodes_connect()
            }
        }
    }
}

class out_(b: Double = 15.0, w: Double = 7.0) : Rectangle(){
    var var_type: String? = null
    var x: Double? = null
    var y: Double? = null
    var content: Any? = null

    val type = "out"

    init{
        id = UUID.randomUUID().toString()

        width = 15.0
        height = 15.0
        stroke = Color.AQUA
        AnchorPane.setBottomAnchor(this, b)
        AnchorPane.setRightAnchor(this, w)

        onMouseClicked = EventHandler { mouseEvent ->
            //println(type)
            x = mouseEvent.sceneX
            y = mouseEvent.sceneY - 30
            from = this
        }
    }
}