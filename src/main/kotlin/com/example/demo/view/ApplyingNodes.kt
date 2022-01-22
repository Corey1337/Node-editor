package com.example.demo.view

import javafx.event.EventHandler
import javafx.scene.Cursor
import javafx.scene.image.Image
import javafx.scene.layout.AnchorPane
import javafx.scene.paint.Color
import tornadofx.*

class add_text_node : DraggableNode(){
    private var image: Image? = null
    private var textAdd: String? = null
    private  var x: Int? = null
    private  var y: Int? = null

    init {
        node_name.text = "Add text"
        ImageView.cursor = Cursor.CROSSHAIR

        node_content.add(rectangle { //output node
            width = 15.0
            height = 15.0
            stroke = Color.AQUA
            AnchorPane.setRightAnchor(this, 7.0)
            AnchorPane.setBottomAnchor(this, 15.0)
            onMouseClicked = EventHandler {
                println("clicked")
            }
        })


        node_content.add(rectangle { //input image
            width = 15.0
            height = 15.0
            stroke = Color.AQUA
            AnchorPane.setLeftAnchor(this, 7.0)
            AnchorPane.setBottomAnchor(this, 84.0)
        })
        node_content.add(label {
            text = "image"
            AnchorPane.setBottomAnchor(this, 84.0)
            AnchorPane.setLeftAnchor(this, 25.0)

        })

        node_content.add(rectangle { //input x
            width = 15.0
            height = 15.0
            stroke = Color.AQUA
            AnchorPane.setLeftAnchor(this, 7.0)
            AnchorPane.setBottomAnchor(this, 61.0)
        })
        node_content.add(label {
            text = "X"
            AnchorPane.setBottomAnchor(this, 61.0)
            AnchorPane.setLeftAnchor(this, 25.0)
        })

        node_content.add(rectangle { //input y
            width = 15.0
            height = 15.0
            stroke = Color.AQUA
            AnchorPane.setLeftAnchor(this, 7.0)
            AnchorPane.setBottomAnchor(this, 38.0)
        })
        node_content.add(label {
            text = "Y"
            AnchorPane.setBottomAnchor(this, 38.0)
            AnchorPane.setLeftAnchor(this, 25.0)
        })

        node_content.add(rectangle { //input addText
            width = 15.0
            height = 15.0
            stroke = Color.AQUA
            AnchorPane.setLeftAnchor(this, 7.0)
            AnchorPane.setBottomAnchor(this, 15.0)
        })
        node_content.add(label {
            text = "text"
            AnchorPane.setBottomAnchor(this, 15.0)
            AnchorPane.setLeftAnchor(this, 25.0)
        })
    }
}

class add_image_node : DraggableNode() {
    private var image: Image? = null
    private var imageAdd: Image? = null
    private var x: Int? = null
    private var y: Int? = null

    init {
        node_name.text = "Add Image"
        node_content.add(rectangle {
            width = 15.0
            height = 15.0
            stroke = Color.AQUA
            AnchorPane.setRightAnchor(this, 7.0)
            AnchorPane.setBottomAnchor(this, 15.0)
        })

        node_content.add(rectangle { //input image
            width = 15.0
            height = 15.0
            stroke = Color.AQUA
            AnchorPane.setLeftAnchor(this, 7.0)
            AnchorPane.setBottomAnchor(this, 84.0)
        })
        node_content.add(label {
            text = "image"
            AnchorPane.setBottomAnchor(this, 84.0)
            AnchorPane.setLeftAnchor(this, 25.0)
        })

        node_content.add(rectangle { //input x
            width = 15.0
            height = 15.0
            stroke = Color.AQUA
            AnchorPane.setLeftAnchor(this, 7.0)
            AnchorPane.setBottomAnchor(this, 61.0)
        })
        node_content.add(label {
            text = "X"
            AnchorPane.setBottomAnchor(this, 61.0)
            AnchorPane.setLeftAnchor(this, 25.0)
        })

        node_content.add(rectangle { //input y
            width = 15.0
            height = 15.0
            stroke = Color.AQUA
            AnchorPane.setLeftAnchor(this, 7.0)
            AnchorPane.setBottomAnchor(this, 38.0)
        })
        node_content.add(label {
            text = "Y"
            AnchorPane.setBottomAnchor(this, 38.0)
            AnchorPane.setLeftAnchor(this, 25.0)
        })

        node_content.add(rectangle { //input addImage
            width = 15.0
            height = 15.0
            stroke = Color.AQUA
            AnchorPane.setLeftAnchor(this, 7.0)
            AnchorPane.setBottomAnchor(this, 15.0)
        })
        node_content.add(label {
            text = "add image"
            AnchorPane.setBottomAnchor(this, 15.0)
            AnchorPane.setLeftAnchor(this, 25.0)
        })
    }
}

