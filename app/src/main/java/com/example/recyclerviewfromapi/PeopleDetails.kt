package com.example.recyclerviewfromapi

import com.google.gson.annotations.SerializedName

class PeopleDetails {

    var data: List<Datum>? = null

    class Datum {

        @SerializedName("name")
        var name: String? = null

    }
}