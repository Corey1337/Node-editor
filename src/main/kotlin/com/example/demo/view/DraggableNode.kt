package com.example.demo.view

import javafx.event.EventHandler
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import kotlin.properties.Delegates

open class DraggableNode: AnchorPane() {
    lateinit var node_layout: AnchorPane
    lateinit var node_content: AnchorPane
    lateinit var node_name: Label
    lateinit var delete_node_but: Button
    lateinit var ImageView: ImageView

    private var mouseAnchorX by Delegates.notNull<Double>()
    private var mouseAnchorY by Delegates.notNull<Double>()


    init {
        val fxmlLoader = FXMLLoader(javaClass.getResource("/draggable_node.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<DraggableNode>()

        node_name.setOnMousePressed { mouseEvent ->
            mouseAnchorX = mouseEvent.x
            mouseAnchorY = mouseEvent.y + 30 // w/o dont work normally
            //println("x:" + mouseAnchorX)
            //println("y:" + mouseAnchorY)
        }
        node_name.setOnMouseDragged { mouseEvent ->
            //if(node_layout.layoutX >= 0 && node_layout.layoutY >= 0) { //mb todo: collision
                node_layout.layoutX = mouseEvent.sceneX - mouseAnchorX
                node_layout.layoutY = mouseEvent.sceneY - mouseAnchorY
//                println("x:" + node_layout.layoutX)
//                println("y:" + node_layout.layoutY)

        }
    }

    fun on_delete(){
        (this.parent as Pane).children.remove(this)
    }
}