package com.example.demo.view

import javafx.scene.shape.Line
import tornadofx.*
import java.util.*

fun nodes_connect(){
    if (from?.type != to?.type && from?.var_type == to?.var_type && from?.parent?.parent?.id != to?.parent?.parent?.id && to?.linked == false)
    {
        var par = from?.parent?.parent
        var cont = par?.parent

        var con_line = link_in_out(from, to)
        cont?.add(con_line)

        println("connected")

        //(to?.parent?.parent as output_image_node).on_refresh(to?.id)

        //println(par?.id)
        //println(cont?.getChildList())

    }
    else
        println("nope")

    from = null
    to = null
}

class link_in_out(from_: out_?, to_: in_?) : Line() {
    var from: out_? = null
    var to: in_? = null

    init {
        id = UUID.randomUUID().toString()

        from = from_
        to = to_

        startX = from?.x!!
        startY = from?.y!!
        endX = to?.x!!
        endY = to?.y!!

        (from!!.parent.parent as DraggableNode).refresh_node_but.fire()
        (to!!.parent.parent as DraggableNode).refresh_node_but.fire()
        to?.linked = true
        to?.content = from?.content
        (from!!.parent.parent as DraggableNode).refresh_node_but.fire()
        (to!!.parent.parent as DraggableNode).refresh_node_but.fire()

    }

    fun update_content(){
        to?.content = from?.content
    }
}