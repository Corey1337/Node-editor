package com.example.demo.view

import com.example.demo.app.Styles
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.input.ClipboardContent
import javafx.scene.input.TransferMode
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.scene.shape.Line
import javafx.scene.shape.Rectangle
import tornadofx.*

var from: Rectangle? = null
var to: Rectangle? = null
var from_type: String? = null
var to_type: String? = null
var from_var: String? = null
var to_var: String? = null


fun a(){
    if (from_type != to_type && from_var == to_var)
    {
        println("con")
        var par = from?.parent?.parent
        var mot = to?.parent?.parent
        var cont = par?.parent

        cont?.add(Line(par!!.layoutX.toDouble(), par.layoutY.toDouble(), mot!!.layoutX.toDouble(), mot.layoutY.toDouble()))
        println(par?.id)
        println(cont?.getChildList())

        from = null
        to = null
    }
}

class MainView : View("Node IMG Editor") {
    override val root: VBox by fxml("/view.fxml")

    val node_container: AnchorPane by fxid()

    init {

        node_container.children.add(input_image_node())
        node_container.children.add(output_image_node())
        node_container.children.add(add_text_node())
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
}