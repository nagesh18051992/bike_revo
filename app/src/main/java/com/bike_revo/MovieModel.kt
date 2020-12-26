package com.bike_revo

import android.widget.ImageView

class MovieModel(title: String?, Imageview: Int) {

    private var title: String
    private var imageview:Int

    init {
        this.title = title!!
        this.imageview=Imageview!!
    }
    fun getTitle(): String? {
        return title
    }
    fun setTitle(name: String?) {
        title = name!!
    }
    fun getImageview():  Int? {
        return imageview
    }
    fun setImageview(imageview: Int?){
        this.imageview =imageview!!
    }


}