package com.example.demo.view

import javafx.event.EventHandler
import javafx.scene.Cursor
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.image.WritableImage
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.text.Text
import tornadofx.*


class add_text_node : DraggableNode(){
    private var image: Image? = null
    private var textAdd: String? = null
    private  var x: Int? = null
    private  var y: Int? = null

    init {
        node_name.text = "Add text"
        ImageView.cursor = Cursor.CROSSHAIR

        val out = out_()
        out.var_type = "image"
        node_content.add(out) //output node

        node_content.add(label {
            text = "image"
            AnchorPane.setBottomAnchor(this, 84.0)
            AnchorPane.setLeftAnchor(this, 25.0)

        })
        var inp_img = in_(84.0)
        inp_img.var_type = "image"
        node_content.add(inp_img) //input node

        node_content.add(label {
            text = "X"
            AnchorPane.setBottomAnchor(this, 61.0)
            AnchorPane.setLeftAnchor(this, 25.0)

        })
        var inp_x = in_(61.0)
        inp_x.var_type = "int"
        node_content.add(inp_x) //input node

        node_content.add(label {
            text = "Y"
            AnchorPane.setBottomAnchor(this, 38.0)
            AnchorPane.setLeftAnchor(this, 25.0)

        })
        var inp_y = in_(38.0)
        inp_y.var_type = "int"
        node_content.add(inp_y) //input node

        node_content.add(label {
            text = "text"
            AnchorPane.setBottomAnchor(this, 15.0)
            AnchorPane.setLeftAnchor(this, 25.0)
        })
        var inp_add_text = in_(15.0)
        inp_add_text.var_type = "string"
        node_content.add(inp_add_text) //input node

        this.refresh_node_but.onAction = EventHandler {
            this.full_upd()
            image = (node_content.children.filter { it.id == inp_img.id }.first() as in_).content as Image?
            out.content = image
            inp_x.content = (node_content.children.filter { it.id == inp_x.id }.first() as in_).content as Int?
            inp_y.content = (node_content.children.filter { it.id == inp_y.id }.first() as in_).content as Int?
            inp_add_text.content = (node_content.children.filter { it.id == inp_add_text.id }.first() as in_).content as String?
            out.content = textToImage((node_content.children.filter { it.id == inp_add_text.id }.first() as in_).content as String?)

            this.on_refresh(inp_img.id, inp_x.id, inp_y.id, inp_add_text.id)
        }
    }
    fun on_refresh(id_img: String?, id_x: String?, id_y: String?, id_add_text: String?) {
        (node_content.children.filter { it.id == "ImageView" }.first() as ImageView).image =
            (node_content.children.filter { it.id == id_img }.first() as in_).content as Image?

    }

    private fun textToImage(text: String?): WritableImage? {
        val t = Text(text)
        val scene = Scene(StackPane(t))
        return t.snapshot(null, null)
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

