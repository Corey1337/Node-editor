package com.example.demo.view

import javafx.fxml.FXMLLoader
import javafx.scene.Cursor
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import tornadofx.*
import java.util.*
import kotlin.properties.Delegates
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt.child

open class DraggableNode: AnchorPane() {
    lateinit var node_layout: AnchorPane
    lateinit var node_content: AnchorPane
    lateinit var node_name: Label
    lateinit var delete_node_but: Button
    lateinit var refresh_node_but: Button
    lateinit var ImageView: ImageView

    private var mouseAnchorX by Delegates.notNull<Double>()
    private var mouseAnchorY by Delegates.notNull<Double>()

    init {
        val fxmlLoader = FXMLLoader(javaClass.getResource("/draggable_node.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<DraggableNode>()
        node_name.cursor = Cursor.MOVE

        id = UUID.randomUUID().toString()

        node_name.setOnMousePressed { mouseEvent ->
            mouseAnchorX = mouseEvent.x
            mouseAnchorY = mouseEvent.y + 30 // w/o dont work normally
            println(mouseAnchorX)
        }
        node_name.setOnMouseDragged { mouseEvent ->
//            if(node_layout.layoutX < 0) {
//                node_layout.layoutX = 0.0
//            }
//            else if(node_layout.layoutY < 0){
//                node_layout.layoutY = 0.0
//            }
//            else {
//                node_layout.layoutX = mouseEvent.sceneX - mouseAnchorX
//                node_layout.layoutY = mouseEvent.sceneY - mouseAnchorY
//            }
            //if(node_layout.layoutX >= 0 && node_layout.layoutY >= 0) { //mb todo: collision
            var delta_x = node_layout.layoutX - (mouseEvent.sceneX - mouseAnchorX)
            var delta_y = node_layout.layoutY - (mouseEvent.sceneY - mouseAnchorY)

            node_layout.layoutX = mouseEvent.sceneX - mouseAnchorX
            node_layout.layoutY = mouseEvent.sceneY - mouseAnchorY

//            var all_out_ = node_content.lookupAll("out_")
//            var all_in_ = node_content.lookupAll("in_")

            var all_links_out = node_layout.parent.lookupAll("link_in_out").filter{ (it as link_in_out).from?.parent?.parent?.id == id }
            var all_links_in = node_layout.parent.lookupAll("link_in_out").filter{ (it as link_in_out).to?.parent?.parent?.id == id }

            if( all_links_out.size != 0) {
                for (link_out in all_links_out) {
                    (link_out as link_in_out).startX -= delta_x
                    (link_out as link_in_out).startY -= delta_y
                }
            }
            else {
                //println("no out links")
            }

            if( all_links_in.size != 0) {
                for (link_in in all_links_in) {
                    (link_in as link_in_out).endX -= delta_x
                    (link_in as link_in_out).endY -= delta_y
                }
            }
            else {
                //println("no in links")
            }
        }
    }

    fun on_delete(){

        var all_links_ = node_layout.parent.lookupAll("link_in_out").filter{ (it as link_in_out).from?.parent?.parent?.id == id || it.to?.parent?.parent?.id == id}
        //var all_links_in = node_layout.parent.lookupAll("link_in_out").filter{  }
        if(all_links_.size != 0){
            for(link_ in all_links_){
                (link_ as link_in_out).to?.linked = false
                link_.to?.content = null
                (this.parent as Pane).children.remove(link_)
            }
        }

        (this.parent as Pane).children.remove(this)

    }

    fun full_upd(){
        var all_links_in = node_layout.parent.lookupAll("link_in_out").filter{ (it as link_in_out).to?.parent?.parent?.id == id }
        if (all_links_in.size != 0) {
            for (link_in in all_links_in) {
                (link_in as link_in_out).update_content()
            }
        }

        var all_links_out = node_layout.parent.lookupAll("link_in_out").filter{ (it as link_in_out).from?.parent?.parent?.id == id }
        if(all_links_out.size != 0) {
            for(link_out in all_links_out){
                ((link_out as link_in_out).to?.parent?.parent as DraggableNode).refresh_node_but.fire()
            }
        }
    }
}