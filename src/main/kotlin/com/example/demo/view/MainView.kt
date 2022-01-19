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
import javafx.scene.layout.VBox
import tornadofx.*

class MainView : View("Node IMG Editor") {
    override val root: VBox by fxml("/view.fxml")

    val node_container: AnchorPane by fxid()

    init {
        node_container.children.add(int_node())
        node_container.children.add(float_node())
        node_container.children.add(image_node())
    }
}