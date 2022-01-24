package com.example.demo.view

import javafx.embed.swing.SwingFXUtils.fromFXImage
import javafx.embed.swing.SwingFXUtils.toFXImage
import javafx.event.EventHandler
import javafx.scene.SnapshotParameters
import javafx.scene.effect.ColorAdjust
import javafx.scene.effect.GaussianBlur
import javafx.scene.effect.SepiaTone
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.image.WritableImage
import tornadofx.*
import java.awt.Color

class gray_filter_node : DraggableNode(){
    private var image: Image? = null

    init {
        node_name.text = "Grey Filter"

        val out = out_()
        out.var_type = "image"
        node_content.add(out) //output node


        var inp = in_()
        inp.var_type = "image"
        node_content.add(inp) //input node
        node_content.add(label {
            text = "image"
            setBottomAnchor(this, 15.0)
            setLeftAnchor(this, 25.0)

        })

        this.refresh_node_but.onAction = EventHandler {
            this.full_upd()

            image = (node_content.children.filter { it.id == inp.id }.first() as in_).content as Image?
            image = image_gray(image)
            out.content = image

            this.on_refresh(inp.id)
        }
    }
    fun on_refresh(id_img: String?) {
        this.ImageView.image = image

    }
    fun image_gray(img: Image?): WritableImage? {
        if (img != null) {
            var img_view = ImageView()

            img_view.image = img
            val desaturate = ColorAdjust()
            desaturate.saturation = -1.0
            img_view.effect = desaturate

            return img_view.snapshot(SnapshotParameters(), null)
        }
        else
            return null
    }
}

class bright_filter_node : DraggableNode(){
    private var image: Image? = null
    private  var float_br: Double? = null
    init {
        node_name.text = "Brightness"

        val out = out_()
        out.var_type = "image"
        node_content.add(out) //output node


        var inp_img = in_(45.0)
        inp_img.var_type = "image"
        node_content.add(inp_img) //input node
        node_content.add(label {
            text = "image"
            setBottomAnchor(this, 45.0)
            setLeftAnchor(this, 25.0)

        })


        var inp_float = in_()
        inp_float.var_type = "float"
        node_content.add(inp_float) //input node
        node_content.add(label {
            text = "float"
            setBottomAnchor(this, 15.0)
            setLeftAnchor(this, 25.0)

        })

        this.refresh_node_but.onAction = EventHandler {
            this.full_upd()

            image = (node_content.children.filter { it.id == inp_img.id }.first() as in_).content as Image?
            float_br = ((node_content.children.filter { it.id == inp_float.id }.first() as in_).content as String?)?.toDouble()
            image = image_bright(image)
            out.content = image

            this.on_refresh(inp_img.id, inp_float.id)
        }
    }
    fun on_refresh(id_img: String?, id_float: String?) {
        this.ImageView.image = image
    }

    fun image_bright(img: Image?): WritableImage? {
        if (img != null && float_br != null) {
            var img_view = ImageView()

            img_view.image = img
            val brightness = ColorAdjust()
            brightness.brightness = float_br!!
            img_view.effect = brightness

            return img_view.snapshot(SnapshotParameters(), null)
        }
        else
            return null
    }
}

class sepia_filter_node : DraggableNode(){
    private var image: Image? = null
    private  var float_br: Double? = null
    init {
        node_name.text = "Sepia"

        val out = out_()
        out.var_type = "image"
        node_content.add(out) //output node


        var inp_img = in_(45.0)
        inp_img.var_type = "image"
        node_content.add(inp_img) //input node
        node_content.add(label {
            text = "image"
            setBottomAnchor(this, 45.0)
            setLeftAnchor(this, 25.0)

        })


        var inp_float = in_()
        inp_float.var_type = "float"
        node_content.add(inp_float) //input node
        node_content.add(label {
            text = "float"
            setBottomAnchor(this, 15.0)
            setLeftAnchor(this, 25.0)

        })

        this.refresh_node_but.onAction = EventHandler {
            this.full_upd()

            image = (node_content.children.filter { it.id == inp_img.id }.first() as in_).content as Image?
            float_br = ((node_content.children.filter { it.id == inp_float.id }.first() as in_).content as String?)?.toDouble()
            image = image_sepia(image)
            out.content = image

            this.on_refresh(inp_img.id, inp_float.id)
        }
    }

    fun on_refresh(id_img: String?, id_float: String?) {
        this.ImageView.image = image
    }

    fun image_sepia(img: Image?): WritableImage? {
        if (img != null && float_br != null) {
            var img_view = ImageView()

            img_view.image = img
            val sepia = SepiaTone()
            sepia.level = float_br!!
            img_view.effect = sepia

            return img_view.snapshot(SnapshotParameters(), null)
        }
        else
            return null
    }
}

class invert_colors_filter_node : DraggableNode(){
    private var image: Image? = null

    init {
        node_name.text = "Invert colors"

        val out = out_()
        out.var_type = "image"
        node_content.add(out) //output node


        var inp_img = in_()
        inp_img.var_type = "image"
        node_content.add(inp_img) //input node
        node_content.add(label {
            text = "image"
            setBottomAnchor(this, 15.0)
            setLeftAnchor(this, 25.0)
        })

        this.refresh_node_but.onAction = EventHandler {
            this.full_upd()

            image = (node_content.children.filter { it.id == inp_img.id }.first() as in_).content as Image?
            image = image_negative(image)
            out.content = image

            this.on_refresh(inp_img.id)
        }
    }
    fun on_refresh(id_img: String?) {
        this.ImageView.image = image
    }

    fun image_negative(img: Image?): WritableImage? {
        if (img != null) {
            val image = fromFXImage(img, null)
            for(i in 0 until img.height.toInt()){
                for(j in 0 until  img.width.toInt()){
                    val pix: Int = image.getRGB(j, i)
                    var color = Color(pix, true)
                    color = Color(255 - color.red, 255 - color.green, 255 - color.blue)
                    image.setRGB(j, i, color.rgb)
                }
            }
            return toFXImage(image, null)
        }
        else
            return null
    }
}

class blur_filter_node : DraggableNode(){
    var image: Image? = null
    var float_br: Double? = null

    init {
        node_name.text = "Blur"

        val out = out_()
        out.var_type = "image"
        node_content.add(out) //output node


        var inp_img = in_(45.0)
        inp_img.var_type = "image"
        node_content.add(inp_img) //input node
        node_content.add(label {
            text = "image"
            setBottomAnchor(this, 45.0)
            setLeftAnchor(this, 25.0)

        })


        var inp_float = in_()
        inp_float.var_type = "float"
        node_content.add(inp_float) //input node
        node_content.add(label {
            text = "float"
            setBottomAnchor(this, 15.0)
            setLeftAnchor(this, 25.0)

        })

        this.refresh_node_but.onAction = EventHandler {
            this.full_upd()

            image = (node_content.children.filter { it.id == inp_img.id }.first() as in_).content as Image?
            float_br = ((node_content.children.filter { it.id == inp_float.id }.first() as in_).content as String?)?.toDouble()

            image = image_blur(image)
            out.content = image

            this.on_refresh(inp_img.id, inp_float.id)
        }
    }
    fun on_refresh(id_img: String?, id_float: String?) {
        this.ImageView.image = image
    }

    fun image_blur(img: Image?): WritableImage? {
        if (img != null && float_br != null) {
            var img_view = ImageView()

            img_view.image = img
            val blur = GaussianBlur()
            blur.radius = float_br!!
            img_view.effect = blur

            return img_view.snapshot(SnapshotParameters(), null)
        }
        else
            return null
    }
}