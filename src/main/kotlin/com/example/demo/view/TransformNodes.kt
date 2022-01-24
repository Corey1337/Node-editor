package com.example.demo.view

import javafx.event.EventHandler
import javafx.scene.SnapshotParameters
import javafx.scene.canvas.Canvas
import javafx.scene.effect.ColorAdjust
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.image.WritableImage
import javafx.scene.paint.Color
import tornadofx.*

class move_node : DraggableNode(){
    var image: Image? = null
    var delta_x: Double? = null
    var delta_y: Double? = null
    init {
        node_name.text = "Move"

        val out = out_()
        out.var_type = "image"
        node_content.add(out) //output node

        var inp_img = in_(61.0)
        inp_img.var_type = "image"
        node_content.add(inp_img) //input node
        node_content.add(label {
            text = "image"
            setBottomAnchor(this, 61.0)
            setLeftAnchor(this, 25.0)

        })


        var inp_x = in_(38.0)
        inp_x.var_type = "float"
        node_content.add(inp_x) //input node
        node_content.add(label {
            text = "X delta"
            setBottomAnchor(this, 38.0)
            setLeftAnchor(this, 25.0)

        })


        var inp_y = in_(15.0)
        inp_y.var_type = "float"
        node_content.add(inp_y) //input node
        node_content.add(label {
            text = "Y delta"
            setBottomAnchor(this, 15.0)
            setLeftAnchor(this, 25.0)

        })

        this.refresh_node_but.onAction = EventHandler {
            this.full_upd()
            image = (node_content.children.filter { it.id == inp_img.id }.first() as in_).content as Image?
            delta_x = ((node_content.children.filter { it.id == inp_x.id }.first() as in_).content as String?)?.toDouble()
            delta_y = ((node_content.children.filter { it.id == inp_y.id }.first() as in_).content as String?)?.toDouble()

            inp_x.content = delta_x
            inp_y.content = delta_y

            image = image_move(image)

            out.content = image

            this.on_refresh(inp_img.id, inp_x.id, inp_y.id)
        }
    }
    fun on_refresh(id_img: String?, id_x: String?, id_y: String?) {
        this.ImageView.image = image

    }
    fun image_move(img: Image?): WritableImage? {
        if (img != null && delta_x != null && delta_y != null) {
            var img_view = Canvas(img.width + delta_x!!, img.height + delta_y!!)
            img_view.graphicsContext2D.drawImage(img, delta_x!!, delta_y!!)

            return img_view.snapshot(SnapshotParameters(), null)
        }
        else
            return null
    }
}

class scale_node : DraggableNode(){
    var image: Image? = null
    var multiply_x: Double? = null
    var multiply_y: Double? = null

    init {
        node_name.text = "Scale"

        val out = out_()
        out.var_type = "image"
        node_content.add(out) //output node

        var inp_img = in_(61.0)
        inp_img.var_type = "image"
        node_content.add(inp_img) //input node
        node_content.add(label {
            text = "image"
            setBottomAnchor(this, 61.0)
            setLeftAnchor(this, 25.0)

        })


        var inp_x = in_(38.0)
        inp_x.var_type = "float"
        node_content.add(inp_x) //input node
        node_content.add(label {
            text = "X multiply"
            setBottomAnchor(this, 38.0)
            setLeftAnchor(this, 25.0)

        })


        var inp_y = in_(15.0)
        inp_y.var_type = "float"
        node_content.add(inp_y) //input node
        node_content.add(label {
            text = "Y multiply"
            setBottomAnchor(this, 15.0)
            setLeftAnchor(this, 25.0)

        })

        this.refresh_node_but.onAction = EventHandler {
            this.full_upd()
            image = (node_content.children.filter { it.id == inp_img.id }.first() as in_).content as Image?
            multiply_x = ((node_content.children.filter { it.id == inp_x.id }.first() as in_).content as String?)?.toDouble()
            multiply_y = ((node_content.children.filter { it.id == inp_y.id }.first() as in_).content as String?)?.toDouble()

            inp_x.content = multiply_x
            inp_y.content = multiply_y

            image = image_scale(image)

            out.content = image

            this.on_refresh(inp_img.id, inp_x.id, inp_y.id)
        }
    }
    fun on_refresh(id_img: String?, id_x: String?, id_y: String?) {
        this.ImageView.image = image

    }
    fun image_scale(img: Image?): WritableImage? {
        if (img != null && multiply_y != null && multiply_x != null) {
            var img_view = ImageView()

            img_view.image = img
            img_view.fitWidth = img.width * multiply_x!!
            img_view.fitHeight = img.height * multiply_y!!

            var snap_params = SnapshotParameters()
            snap_params.fill = Color.TRANSPARENT

            return img_view.snapshot(snap_params, null)
        }
        else
            return null
    }
}

class rotate_node : DraggableNode(){
    var image: Image? = null
    var rad: Double? = null

    init {
        node_name.text = "Rotate"
        val out = out_()
        out.var_type = "image"
        node_content.add(out) //output node

        var inp_img = in_(38.0)
        inp_img.var_type = "image"
        node_content.add(inp_img) //input node
        node_content.add(label {
            text = "image"
            setBottomAnchor(this, 38.0)
            setLeftAnchor(this, 25.0)

        })


        var inp_rad = in_(15.0)
        inp_rad.var_type = "float"
        node_content.add(inp_rad) //input node
        node_content.add(label {
            text = "rad"
            setBottomAnchor(this, 15.0)
            setLeftAnchor(this, 25.0)

        })

        this.refresh_node_but.onAction = EventHandler {
            this.full_upd()
            image = (node_content.children.filter { it.id == inp_img.id }.first() as in_).content as Image?
            rad = ((node_content.children.filter { it.id == inp_rad.id }.first() as in_).content as String?)?.toDouble()

            inp_rad.content = rad

            image = image_rotate(image)

            out.content = image

            this.on_refresh(inp_img.id, inp_rad.id)
        }

    }
    fun on_refresh(id_img: String?, id_rad: String?) {
        this.ImageView.image = image

    }
    fun image_rotate(img: Image?): WritableImage? {
        if (img != null && rad != null) {
            var img_view = ImageView()
            img_view.image = img
            img_view.rotate = rad!!

            return img_view.snapshot(SnapshotParameters(), null)
        }
        else
            return null
    }
}