package com.example.demo.view

import com.sun.imageio.plugins.common.ImageUtil
import javafx.event.EventHandler
import javafx.scene.Cursor
import javafx.scene.Scene
import javafx.scene.SnapshotParameters
import javafx.scene.canvas.Canvas
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

        val out = out_()
        out.var_type = "image"
        node_content.add(out) //output node


        var inp_img = in_(84.0)
        inp_img.var_type = "image"
        node_content.add(inp_img) //input node
        node_content.add(label {
            text = "image"
            AnchorPane.setBottomAnchor(this, 84.0)
            AnchorPane.setLeftAnchor(this, 25.0)

        })


        var inp_x = in_(61.0)
        inp_x.var_type = "int"
        node_content.add(inp_x) //input node
        node_content.add(label {
            text = "X"
            AnchorPane.setBottomAnchor(this, 61.0)
            AnchorPane.setLeftAnchor(this, 25.0)

        })


        var inp_y = in_(38.0)
        inp_y.var_type = "int"
        node_content.add(inp_y) //input node
        node_content.add(label {
            text = "Y"
            AnchorPane.setBottomAnchor(this, 38.0)
            AnchorPane.setLeftAnchor(this, 25.0)

        })


        var inp_add_text = in_(15.0)
        inp_add_text.var_type = "string"
        node_content.add(inp_add_text) //input node
        node_content.add(label {
            text = "text"
            AnchorPane.setBottomAnchor(this, 15.0)
            AnchorPane.setLeftAnchor(this, 25.0)
        })


        this.refresh_node_but.onAction = EventHandler {
            this.full_upd()
            image = (node_content.children.filter { it.id == inp_img.id }.first() as in_).content as Image?

            inp_x.content = ((node_content.children.filter { it.id == inp_x.id }.first() as in_).content as String?)?.toInt()
            inp_y.content = ((node_content.children.filter { it.id == inp_y.id }.first() as in_).content as String?)?.toInt()

            inp_add_text.content = (node_content.children.filter { it.id == inp_add_text.id }.first() as in_).content as String?

            image = textToImage(image, inp_x.content as Int?, inp_y.content as Int?,
                inp_add_text.content as String?)

            out.content = image

            this.on_refresh(inp_img.id, inp_x.id, inp_y.id, inp_add_text.id)
        }
    }
    fun on_refresh(id_img: String?, id_x: String?, id_y: String?, id_add_text: String?) {
        (node_content.children.filter { it.id == "ImageView" }.first() as ImageView).image = image

    }

    private fun textToImage(image: Image?, x: Int?, y: Int?, text: String?): WritableImage? {
        var image_canv = image?.width?.let { Canvas(it, image.height) }
        if (image_canv != null) {
            image_canv.graphicsContext2D.drawImage(image, 0.0, 0.0)
        }
        if (image_canv != null) {
            x?.toDouble()?.let { y?.toDouble()?.let { it1 -> image_canv.graphicsContext2D.fillText(text, it, it1) } }
        }
        return  image_canv?.snapshot(SnapshotParameters(), null)
    }
}

class add_image_node : DraggableNode() {
    private var image: Image? = null
    private var imageAdd: Image? = null
    private var x: Int? = null
    private var y: Int? = null

    init {
        node_name.text = "Add Image"

        val out = out_()
        out.var_type = "image"
        node_content.add(out) //output node


        var inp_img = in_(84.0)
        inp_img.var_type = "image"
        node_content.add(inp_img) //input node
        node_content.add(label {
            text = "image"
            AnchorPane.setBottomAnchor(this, 84.0)
            AnchorPane.setLeftAnchor(this, 25.0)
        })


        var inp_x = in_(61.0)
        inp_x.var_type = "int"
        node_content.add(inp_x) //input node
        node_content.add(label {
            text = "X"
            AnchorPane.setBottomAnchor(this, 61.0)
            AnchorPane.setLeftAnchor(this, 25.0)
        })


        var inp_y = in_(38.0)
        inp_y.var_type = "int"
        node_content.add(inp_y) //input node
        node_content.add(label {
            text = "Y"
            AnchorPane.setBottomAnchor(this, 38.0)
            AnchorPane.setLeftAnchor(this, 25.0)
        })


        var inp_add_img = in_(15.0)
        inp_add_img.var_type = "image"
        node_content.add(inp_add_img) //input node
        node_content.add(label {
            text = "add image"
            AnchorPane.setBottomAnchor(this, 15.0)
            AnchorPane.setLeftAnchor(this, 25.0)
        })

        this.refresh_node_but.onAction = EventHandler {
            this.full_upd()
            image = (node_content.children.filter { it.id == inp_img.id }.first() as in_).content as Image?

            inp_x.content = ((node_content.children.filter { it.id == inp_x.id }.first() as in_).content as String?)?.toInt()
            inp_y.content = ((node_content.children.filter { it.id == inp_y.id }.first() as in_).content as String?)?.toInt()

            inp_add_img.content = (node_content.children.filter { it.id == inp_add_img.id }.first() as in_).content as Image?

            image = ImageToImage(image, inp_x.content as Int?, inp_y.content as Int?,
                inp_add_img.content as Image?)

            out.content = image

            this.on_refresh(inp_img.id, inp_x.id, inp_y.id, inp_add_img.id)
        }
    }
    fun on_refresh(id_img: String?, id_x: String?, id_y: String?, id_add_img: String?) {
        (node_content.children.filter { it.id == "ImageView" }.first() as ImageView).image = image
    }

    private fun ImageToImage(image: Image?, x: Int?, y: Int?, add_img: Image?): WritableImage? {
        var image_canv = image?.width?.let { Canvas(it, image.height) }
        if (image_canv != null) {
            image_canv.graphicsContext2D.drawImage(image, 0.0, 0.0)
        }
        if (image_canv != null) {
            x?.toDouble()?.let { y?.toDouble()?.let { it1 -> image_canv.graphicsContext2D.drawImage(add_img, it, it1) } }
        }
        return  image_canv?.snapshot(SnapshotParameters(), null)
    }
}

