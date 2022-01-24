package com.example.demo.view

import com.example.demo.app.Styles
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.input.ClipboardContent
import javafx.scene.input.TransferMode
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.scene.shape.CubicCurve
import javafx.scene.shape.Line
import javafx.scene.shape.Rectangle
import tornadofx.*

var from: out_? = null
var to: in_? = null


class MainView : View("Node IMG Editor") {
    override val root: VBox by fxml("/view.fxml")

    val node_container: AnchorPane by fxid()

    init {

        node_container.children.add(input_image_node())
        node_container.children.add(output_image_node())

        node_container.children.add(rotate_node())
    }

    fun create_float(){
        node_container.children.add(float_node())
    }

    fun create_int(){
        node_container.children.add(int_node())
    }

    fun create_string(){
        node_container.children.add(string_node())
    }

    fun create_image(){
        node_container.children.add(image_node())
    }

    fun create_add_text(){
        node_container.children.add(add_text_node())
    }

    fun create_add_image(){
        node_container.children.add(add_image_node())
    }

    fun create_gray_filter(){
        node_container.children.add(gray_filter_node())
    }

    fun create_bright_filter(){
        node_container.children.add(bright_filter_node())
    }

    fun create_sepia_filter(){
        node_container.children.add(sepia_filter_node())
    }

    fun create_invert_colors_filter(){
        node_container.children.add(invert_colors_filter_node())
    }

    fun create_blur_filter(){
        node_container.children.add(blur_filter_node())
    }

    fun create_move_image(){
        node_container.children.add(move_node())
    }

    fun create_scale_image(){
        node_container.children.add(scale_node())
    }

    fun create_rotate_image(){
        node_container.children.add(rotate_node())
    }
}